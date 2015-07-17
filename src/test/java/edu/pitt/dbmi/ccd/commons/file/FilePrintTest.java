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
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * Jul 16, 2015 12:55:43 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FilePrintTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Generated 5 paragraphs, 493 words, 3366 bytes of Lorem Ipsum.
     */
    private final String[] FILE_CONTENTS = {
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin fermentum scelerisque tortor, sit amet mollis sem ultricies in. Suspendisse potenti. Mauris sed efficitur nibh. Etiam ut bibendum tellus. Curabitur ultricies tristique enim, sed gravida nisl. Fusce consectetur eu metus eu auctor. Fusce bibendum pulvinar varius. Morbi non faucibus lacus.",
        "Etiam sollicitudin urna dapibus neque pellentesque mattis. Proin dignissim massa vel nibh porta vehicula. Maecenas id blandit felis. Suspendisse quis nulla ex. Morbi non hendrerit tortor, ut egestas ex. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum eget tempor ligula, eget ultrices elit. Duis maximus molestie mi id sollicitudin. Nullam pellentesque orci neque, tincidunt luctus arcu dignissim vel. Phasellus quis ligula sit amet ipsum consequat molestie. Vivamus faucibus efficitur tristique. Fusce pulvinar magna ut nulla luctus, vitae ullamcorper libero rutrum. Nam non orci varius, lacinia lectus sit amet, accumsan libero. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;",
        "Duis nisi lorem, congue auctor neque quis, vulputate tempor magna. Nulla eu gravida lacus, id semper dui. Sed eget nulla diam. Etiam accumsan, lectus sed accumsan consectetur, neque metus semper quam, at dignissim magna erat eget quam. In egestas mauris ante, sit amet tristique libero gravida eget. Aenean aliquet vel tellus eu pulvinar. Aenean massa nunc, ornare nec ultrices lacinia, euismod tempor lorem. Morbi neque ipsum, auctor ac purus in, facilisis gravida lacus. Ut ultricies semper nulla, commodo vestibulum diam efficitur sit amet. Pellentesque eget lorem non erat scelerisque pharetra. Morbi aliquet molestie ipsum at convallis. Nulla pharetra efficitur sapien. Mauris maximus, sem sit amet egestas aliquam, lectus odio elementum felis, quis lobortis nulla ex sit amet eros. Phasellus elementum gravida ultricies.",
        "Nulla convallis fermentum malesuada. Nam id tempus lectus. Proin eu ligula velit. Vestibulum semper, metus id tincidunt ullamcorper, dolor ante pellentesque nunc, at pulvinar orci justo eu libero. Donec eu viverra mauris. Curabitur vitae semper leo. Sed lacinia accumsan neque sed ornare. Ut tristique lobortis quam, at mattis dui vulputate sed. Aenean ornare sem erat. Integer maximus neque elit, ac convallis elit ultricies eget. Donec iaculis dolor ac tincidunt tincidunt. Etiam accumsan, orci in gravida ullamcorper, lectus velit placerat est, nec dignissim neque leo et quam. Donec quis elit at eros rutrum interdum sed nec est. Etiam sit amet gravida tellus. Suspendisse potenti. Morbi vestibulum massa nec massa sollicitudin sollicitudin.",
        "Quisque quis sem vitae nunc egestas eleifend commodo lacinia elit. Nunc at tincidunt arcu. Quisque pharetra ex orci, ac porttitor odio consectetur fringilla. Sed eget nulla a magna pharetra tempor. Morbi gravida justo nec libero malesuada, porta rhoncus leo rutrum. Sed tempor placerat lorem, non rutrum quam suscipit sed. Etiam feugiat nisi non quam condimentum aliquam. Nulla laoreet est a sem tempus, a pharetra nunc gravida. Sed ut porttitor dolor. Proin vel porta sapien, sed eleifend tellus. Donec mauris magna, tincidunt ac turpis sed, vehicula pulvinar enim. Aenean dapibus, quam et cursus molestie, nisi massa dapibus sem, sed congue felis enim sed turpis."
    };

    public FilePrintTest() {
    }

    /**
     * Test of unixFileDateTime method, of class FilePrint.
     *
     * @throws IOException
     */
    @Ignore
    @Test
    public void testFileTimestamp() throws IOException {
        System.out.println();
        System.out.println("fileTimestamp");

        Path file = tempFolder.newFile("test.txt").toPath();
        Files.write(file, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        long lastModifiedTime = Files.getLastModifiedTime(file).toMillis();
        String result = FilePrint.fileTimestamp(lastModifiedTime);
        System.out.printf("File Timestamp: %s\n", result);

        System.out.println();
    }

    /**
     * Test of humanReadableSize method, of class FilePrint.
     *
     * @throws IOException
     */
    @Ignore
    @Test
    public void testHumanReadableSize() throws IOException {
        System.out.println();
        System.out.println("humanReadableSize");

        Path file = tempFolder.newFile("test.txt").toPath();
        Files.write(file, Arrays.asList(FILE_CONTENTS), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        long fileSize = Files.size(file);
        String result = FilePrint.humanReadableSize(fileSize, false);
        System.out.printf("Raw File Size: %d\n", fileSize);
        System.out.printf("Print Pretty File Size: %s\n", result);
        System.out.println();
    }

}
