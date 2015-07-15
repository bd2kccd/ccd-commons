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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;

/**
 *
 * Jul 15, 2015 2:43:39 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FileInfosTest {

    public FileInfosTest() {
    }

    /**
     * Test of getDirectoryBasicInfos method, of class FileInfos.
     */
    @Test
    public void testGetDirectoryBasicInfos() {
        System.out.println("getDirectoryBasicInfos");
        Path dir = Paths.get(".");
        List<BasicFileInfo> result = FileInfos.getDirectoryBasicInfos(dir, true);
        result.stream().forEach(System.out::println);
    }

}