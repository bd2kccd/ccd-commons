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

import java.nio.file.Path;

/**
 *
 * Jul 15, 2015 2:11:16 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class BasicFileInfo {

    private final String filename;
    private final Path absolutePath;
    private final long creationTime;
    private final long lastAccessTime;
    private final long lastModifiedTime;
    private final long size;
    private final boolean directory;
    private final boolean regularFile;
    private final boolean symbolicLink;
    private final boolean hidden;

    public BasicFileInfo(String filename, Path absolutePath, long creationTime, long lastAccessTime, long lastModifiedTime, long size, boolean directory, boolean regularFile, boolean symbolicLink, boolean hidden) {
        this.filename = filename;
        this.absolutePath = absolutePath;
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
        this.directory = directory;
        this.regularFile = regularFile;
        this.symbolicLink = symbolicLink;
        this.hidden = hidden;
    }

    public String getFilename() {
        return filename;
    }

    public Path getAbsolutePath() {
        return absolutePath;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public long getSize() {
        return size;
    }

    public boolean isDirectory() {
        return directory;
    }

    public boolean isRegularFile() {
        return regularFile;
    }

    public boolean isSymbolicLink() {
        return symbolicLink;
    }

    public boolean isHidden() {
        return hidden;
    }

}
