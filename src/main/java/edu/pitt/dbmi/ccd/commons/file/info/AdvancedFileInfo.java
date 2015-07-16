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

import java.nio.file.Path;

/**
 *
 * Jul 16, 2015 10:20:14 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class AdvancedFileInfo extends BasicFileInfo {

    protected String md5Hash;

    public AdvancedFileInfo(String md5Hash, String filename, Path absolutePath, long creationTime, long lastAccessTime, long lastModifiedTime, long size, boolean directory, boolean regularFile, boolean symbolicLink, boolean hidden) {
        super(filename, absolutePath, creationTime, lastAccessTime, lastModifiedTime, size, directory, regularFile, symbolicLink, hidden);
        this.md5Hash = md5Hash;
    }

    @Override
    public String toString() {
        return "AdvancedFileInfo{"
                + "md5Hash=" + md5Hash
                + ", filename=" + filename
                + ", absolutePath=" + absolutePath
                + ", creationTime=" + creationTime
                + ", lastAccessTime=" + lastAccessTime
                + ", lastModifiedTime=" + lastModifiedTime
                + ", size=" + size
                + ", directory=" + directory
                + ", regularFile=" + regularFile
                + ", symbolicLink=" + symbolicLink
                + ", hidden=" + hidden
                + '}';
    }

    public String getMd5Hash() {
        return md5Hash;
    }

}
