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
package edu.pitt.dbmi.ccd.commons.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Jul 15, 2015 2:01:08 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FileInfos {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileInfos.class);

    private FileInfos() {
    }

    public static List<BasicFileInfo> getDirectoryBasicInfos(Path dir, boolean hidden) {
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException(
                    String.format("'%s' is not a directory.", dir.getFileName().toString()));
        }

        List<BasicFileInfo> list = new LinkedList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            directoryStream.forEach(path -> {
                try {
                    if (!Files.isHidden(path)) {
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
                                        attrs.isSymbolicLink()));
                    }
                } catch (IOException exception) {
                    LOGGER.error(exception.getMessage());
                }
            });
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }

        return list;
    }

}