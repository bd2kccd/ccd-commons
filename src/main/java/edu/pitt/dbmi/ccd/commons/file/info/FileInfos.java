/*
 * Copyright (C) 2015 University of Pittsburgh.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.pitt.dbmi.ccd.commons.file.info;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Jul 15, 2015 2:01:08 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FileInfos {

    private static final byte NEW_LINE = '\n';

    private static final byte CARRIAGE_RETURN = '\r';

    private FileInfos() {
    }

    public static char delimiterStringToChar(String delimiter) {
        switch (delimiter) {
            case "tab":
                return '\t';
            case "comma":
                return ',';
            default:
                throw new IllegalArgumentException(String.format("Unknow delimiter: %s", delimiter));
        }
    }

    /**
     * Counts the number of column of the first line in the file.
     *
     * @param file dataset
     * @param delimiter a single character used to separate the data
     * @return
     * @throws IOException
     */
    public static int countColumn(File file, char delimiter) throws IOException {
        int count = 0;

        byte delim = (byte) delimiter;
        try (FileChannel fc = new RandomAccessFile(file, "r").getChannel()) {
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            byte currentChar = -1;
            byte prevChar = NEW_LINE;
            while (buffer.hasRemaining()) {
                currentChar = buffer.get();
                if (currentChar == CARRIAGE_RETURN) {
                    currentChar = NEW_LINE;
                }

                if (currentChar == delim || (currentChar == NEW_LINE && prevChar != NEW_LINE)) {
                    count++;
                    if (currentChar == NEW_LINE) {
                        break;
                    }
                }

                prevChar = currentChar;
            }

            // take care of cases where there's no newline at the end of the file
            if (!(currentChar == -1 || currentChar == NEW_LINE)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Counts the number of lines that contain data.
     *
     * @param file dataset
     * @return
     * @throws IOException
     */
    public static int countLine(File file) throws IOException {
        int count = 0;

        try (FileChannel fc = new RandomAccessFile(file, "r").getChannel()) {
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            byte prevChar = NEW_LINE;
            while (buffer.hasRemaining()) {
                byte currentChar = buffer.get();
                if (currentChar == CARRIAGE_RETURN) {
                    currentChar = NEW_LINE;
                }

                if (currentChar == NEW_LINE && prevChar != NEW_LINE) {
                    count++;
                }

                prevChar = currentChar;
            }

            if (prevChar != NEW_LINE) {
                count++;
            }
        }

        return count;
    }

    public static BasicFileInfo basicPathInfo(Path path) throws IOException {
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        BasicFileInfo info = new BasicFileInfo(
                path.getFileName().toString(),
                path.getParent().toRealPath(),
                attrs.creationTime().toMillis(),
                attrs.lastAccessTime().toMillis(),
                attrs.lastModifiedTime().toMillis(),
                attrs.size(),
                attrs.isDirectory(),
                attrs.isRegularFile(),
                attrs.isSymbolicLink(),
                Files.isHidden(path));

        return info;
    }

    public static List<BasicFileInfo> listBasicPathInfo(List<Path> pathList) throws IOException {
        List<BasicFileInfo> list = new LinkedList<>();

        for (Path path : pathList) {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            list.add(
                    new BasicFileInfo(
                            path.getFileName().toString(),
                            path.getParent().toRealPath(),
                            attrs.creationTime().toMillis(),
                            attrs.lastAccessTime().toMillis(),
                            attrs.lastModifiedTime().toMillis(),
                            attrs.size(),
                            attrs.isDirectory(),
                            attrs.isRegularFile(),
                            attrs.isSymbolicLink(),
                            Files.isHidden(path)));
        }

        return list;
    }

    public static List<Path> listDirectory(Path dir, boolean showHidden) throws IOException {
        List<Path> list = new LinkedList<>();

        if (showHidden) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
                directoryStream.forEach(list::add);
            }
        } else {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, path -> {
                return !Files.isHidden(path);
            })) {
                directoryStream.forEach(list::add);
            }
        }

        return list;
    }

}
