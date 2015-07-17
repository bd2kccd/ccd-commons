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

import edu.pitt.dbmi.ccd.commons.file.AbstractFileTest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Jul 16, 2015 10:12:15 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FileInfosTest extends AbstractFileTest {

    public FileInfosTest() {
    }

    /**
     * Test of getBasicInfos method, of class FileInfos.
     *
     * @throws IOException
     */
    @Test
    public void testGetBasicInfos() throws IOException {
        System.out.println("getBasicInfos");

        Path file = tempFolder.newFile("test.txt").toPath();
        Files.write(file, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        Path hiddenFile = tempFolder.newFile(".test_hidden.txt").toPath();
        Files.write(hiddenFile, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        Path folder = tempFolder.newFolder("test_folder").toPath();

        Path dir = tempFolder.getRoot().toPath();
        boolean showHidden = true;
        List<Path> pathList = FileInfos.listDirectory(dir, showHidden);

        List<BasicFileInfo> result = FileInfos.listBasicPathInfo(pathList);
        Assert.assertTrue(!result.isEmpty());
    }

    /**
     * Test of getDirectoryListing method, of class FileInfos.
     *
     * @throws IOException
     */
    @Test
    public void testListDirectory() throws IOException {
        System.out.println("listDirectory");

        Path file = tempFolder.newFile("test.txt").toPath();
        Files.write(file, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        Path hiddenFile = tempFolder.newFile(".test_hidden.txt").toPath();
        Files.write(hiddenFile, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        tempFolder.newFolder("test_folder");

        Path dir = tempFolder.getRoot().toPath();
        boolean showHidden = true;
        List<Path> result = FileInfos.listDirectory(dir, showHidden);
        Assert.assertTrue(!result.isEmpty());
    }

}
