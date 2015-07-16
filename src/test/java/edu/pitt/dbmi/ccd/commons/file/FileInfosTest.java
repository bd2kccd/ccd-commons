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

import edu.pitt.dbmi.ccd.commons.file.info.AdvancedFileInfo;
import edu.pitt.dbmi.ccd.commons.file.info.BasicFileInfo;
import edu.pitt.dbmi.ccd.commons.file.info.FileInfos;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Ignore;
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
     *
     * @throws IOException
     */
    @Ignore
    @Test
    public void testGetDirectoryBasicInfos() throws IOException {
        System.out.println("getDirectoryBasicInfos");
        Path dir = Paths.get(".");
        List<BasicFileInfo> result = FileInfos.getDirectoryBasicInfos(dir, true);
        result.stream().forEach(info -> {
            System.out.println(info);
            System.out.println();
        });
    }

    /**
     * Test of getDirectoryAdvancedInfos method, of class FileInfos.
     *
     * @throws IOException
     */
    @Ignore
    @Test
    public void testGetDirectoryAdvancedInfos() throws IOException {
        System.out.println("getDirectoryAdvancedInfos");
        Path dir = Paths.get(".");
        List<AdvancedFileInfo> result = FileInfos.getDirectoryAdvancedInfos(dir, true);
        result.stream().forEach(info -> {
            System.out.println(info);
            System.out.println();
        });
    }

}
