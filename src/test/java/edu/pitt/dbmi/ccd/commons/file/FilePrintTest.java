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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Jul 16, 2015 12:55:43 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FilePrintTest extends AbstractFileTest {

    public FilePrintTest() {
    }

    /**
     * Test of unixFileDateTime method, of class FilePrint.
     *
     * @throws IOException
     */
    @Test
    public void testFileTimestamp() throws IOException {
        System.out.println("fileTimestamp");

        Path file = tempFolder.newFile("test.txt").toPath();
        Files.write(file, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        long lastModifiedTime = Files.getLastModifiedTime(file).toMillis();
        String result = FilePrint.fileTimestamp(lastModifiedTime);
        Assert.assertNotNull(result);
    }

    /**
     * Test of humanReadableSize method, of class FilePrint.
     *
     * @throws IOException
     */
    @Test
    public void testHumanReadableSize() throws IOException {
        System.out.println("humanReadableSize");

        Path file = tempFolder.newFile("test.txt").toPath();
        Files.write(file, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        long fileSize = Files.size(file);
        boolean si = true;
        String expResult = "3.37 kB";
        String result = FilePrint.humanReadableSize(fileSize, si);
        Assert.assertEquals(expResult, result);
    }

}
