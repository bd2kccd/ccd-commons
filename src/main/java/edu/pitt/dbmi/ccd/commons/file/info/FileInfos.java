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

import edu.pitt.dbmi.ccd.commons.file.MessageDigestHash;
import java.io.IOException;
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

    private FileInfos() {
    }

    public static List<AdvancedFileInfo> getAdvancedInfos(List<Path> files) throws IOException {
        List<AdvancedFileInfo> list = new LinkedList<>();

        for (Path path : files) {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            list.add(
                    new AdvancedFileInfo(
                            attrs.isRegularFile() ? MessageDigestHash.computeMD5Hash(path) : "",
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

    public static List<BasicFileInfo> getBasicInfos(List<Path> files) throws IOException {
        List<BasicFileInfo> list = new LinkedList<>();

        for (Path path : files) {
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

    public static List<Path> getDirectoryListing(Path dir, boolean showHidden) throws IOException {
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
