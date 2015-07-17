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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

/**
 *
 * Jul 16, 2015 10:12:15 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FileInfosTest {

    public FileInfosTest() {
    }

    /**
     * Test of getAdvancedInfos method, of class FileInfos.
     */
    @Test
    public void testGetAdvancedInfos() throws IOException {
        System.out.println();
        System.out.println("getAdvancedInfos");

        Path dir = Paths.get(".");
        boolean showHidden = false;
        List<Path> files = FileInfos.getDirectoryListing(dir, showHidden);

        List<AdvancedFileInfo> result = FileInfos.getAdvancedInfos(files);
        result.forEach(info -> {
            System.out.println(info);
            System.out.println();
        });

        System.out.println();
    }

    /**
     * Test of getBasicInfos method, of class FileInfos.
     */
    @Test
    public void testGetBasicInfos() throws IOException {
        System.out.println();
        System.out.println("getBasicInfos");

        Path dir = Paths.get(".");
        boolean showHidden = false;
        List<Path> files = FileInfos.getDirectoryListing(dir, showHidden);

        List<BasicFileInfo> result = FileInfos.getBasicInfos(files);
        result.forEach(info -> {
            System.out.println(info);
            System.out.println();
        });

        System.out.println();
    }

    /**
     * Test of getDirectoryListing method, of class FileInfos.
     */
    @Test
    public void testGetDirectoryListing() throws IOException {
        System.out.println();
        System.out.println("getDirectoryListing");

        Path dir = Paths.get(".");
        boolean showHidden = false;
        List<Path> result = FileInfos.getDirectoryListing(dir, showHidden);
        result.forEach(path -> {
            System.out.println(path.getFileName());
        });

        System.out.println();
    }

}
