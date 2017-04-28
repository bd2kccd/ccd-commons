/*
 * Copyright (C) 2017 University of Pittsburgh.
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Apr 28, 2017 12:11:51 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class BasicFileInfos {

    private BasicFileInfos() {
    }

    public static BasicFileInfo getBasicFileInfo(Path file) throws IOException {
        BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
        String filename = file.getFileName().toString();
        Path absolutePath = file.getParent().toRealPath();
        long creationTime = attrs.creationTime().toMillis();
        long lastAccessTime = attrs.lastAccessTime().toMillis();
        long lastModifiedTime = attrs.lastModifiedTime().toMillis();
        long size = attrs.size();
        boolean directory = attrs.isDirectory();
        boolean regularFile = attrs.isRegularFile();
        boolean symbolicLink = attrs.isSymbolicLink();
        boolean hidden = Files.isHidden(file);

        return new BasicFileInfo(filename, absolutePath, creationTime, lastAccessTime, lastModifiedTime, size, directory, regularFile, symbolicLink, hidden);
    }

    public static List<BasicFileInfo> getBasicFileInfos(List<Path> files) throws IOException {
        List<BasicFileInfo> list = new LinkedList<>();

        for (Path file : files) {
            list.add(getBasicFileInfo(file));
        }

        return list;
    }

}
