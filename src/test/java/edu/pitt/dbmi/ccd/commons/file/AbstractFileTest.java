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

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * Jul 17, 2015 9:00:36 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public abstract class AbstractFileTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Generated 5 paragraphs, 493 words, 3366 bytes of Lorem Ipsum.
     */
    protected final String[] FILE_CONTENTS = {
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin fermentum scelerisque tortor, sit amet mollis sem ultricies in. Suspendisse potenti. Mauris sed efficitur nibh. Etiam ut bibendum tellus. Curabitur ultricies tristique enim, sed gravida nisl. Fusce consectetur eu metus eu auctor. Fusce bibendum pulvinar varius. Morbi non faucibus lacus.",
        "Etiam sollicitudin urna dapibus neque pellentesque mattis. Proin dignissim massa vel nibh porta vehicula. Maecenas id blandit felis. Suspendisse quis nulla ex. Morbi non hendrerit tortor, ut egestas ex. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum eget tempor ligula, eget ultrices elit. Duis maximus molestie mi id sollicitudin. Nullam pellentesque orci neque, tincidunt luctus arcu dignissim vel. Phasellus quis ligula sit amet ipsum consequat molestie. Vivamus faucibus efficitur tristique. Fusce pulvinar magna ut nulla luctus, vitae ullamcorper libero rutrum. Nam non orci varius, lacinia lectus sit amet, accumsan libero. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;",
        "Duis nisi lorem, congue auctor neque quis, vulputate tempor magna. Nulla eu gravida lacus, id semper dui. Sed eget nulla diam. Etiam accumsan, lectus sed accumsan consectetur, neque metus semper quam, at dignissim magna erat eget quam. In egestas mauris ante, sit amet tristique libero gravida eget. Aenean aliquet vel tellus eu pulvinar. Aenean massa nunc, ornare nec ultrices lacinia, euismod tempor lorem. Morbi neque ipsum, auctor ac purus in, facilisis gravida lacus. Ut ultricies semper nulla, commodo vestibulum diam efficitur sit amet. Pellentesque eget lorem non erat scelerisque pharetra. Morbi aliquet molestie ipsum at convallis. Nulla pharetra efficitur sapien. Mauris maximus, sem sit amet egestas aliquam, lectus odio elementum felis, quis lobortis nulla ex sit amet eros. Phasellus elementum gravida ultricies.",
        "Nulla convallis fermentum malesuada. Nam id tempus lectus. Proin eu ligula velit. Vestibulum semper, metus id tincidunt ullamcorper, dolor ante pellentesque nunc, at pulvinar orci justo eu libero. Donec eu viverra mauris. Curabitur vitae semper leo. Sed lacinia accumsan neque sed ornare. Ut tristique lobortis quam, at mattis dui vulputate sed. Aenean ornare sem erat. Integer maximus neque elit, ac convallis elit ultricies eget. Donec iaculis dolor ac tincidunt tincidunt. Etiam accumsan, orci in gravida ullamcorper, lectus velit placerat est, nec dignissim neque leo et quam. Donec quis elit at eros rutrum interdum sed nec est. Etiam sit amet gravida tellus. Suspendisse potenti. Morbi vestibulum massa nec massa sollicitudin sollicitudin.",
        "Quisque quis sem vitae nunc egestas eleifend commodo lacinia elit. Nunc at tincidunt arcu. Quisque pharetra ex orci, ac porttitor odio consectetur fringilla. Sed eget nulla a magna pharetra tempor. Morbi gravida justo nec libero malesuada, porta rhoncus leo rutrum. Sed tempor placerat lorem, non rutrum quam suscipit sed. Etiam feugiat nisi non quam condimentum aliquam. Nulla laoreet est a sem tempus, a pharetra nunc gravida. Sed ut porttitor dolor. Proin vel porta sapien, sed eleifend tellus. Donec mauris magna, tincidunt ac turpis sed, vehicula pulvinar enim. Aenean dapibus, quam et cursus molestie, nisi massa dapibus sem, sed congue felis enim sed turpis."
    };

    protected final String[] PC_STABLE_RESULT1 = {
        "Graph Parameters:",
        "depth = 3",
        "alpha = 0.000100",
        "",
        "",
        "Graph Nodes:",
        "X0 X1 X2 X3 X4 X5 X6 X7 X8 X9 X10 X11 X12 X13 X14 X15 X16 X17 X18 X19 ",
        "",
        "Graph Edges: ",
        "1. X1 --- X16",
        "2. X13 --> X14",
        "3. X15 --> X14",
        "4. X18 --> X14",
        "5. X18 --> X3",
        "6. X3 <-> X17",
        "7. X4 --- X9",
        "8. X5 --> X17",
        "9. X6 --- X8",
        "10. X7 --> X2",
        "11. X8 --> X2",
        "12. X9 --- X19",
        ""
    };

    protected final String[] PC_STABLE_RESULT2 = {
        "Graph Parameters:",
        "depth = 4",
        "alpha = 0.050000",
        "",
        "",
        "Graph Nodes:",
        "X0 X1 X2 X3 X4 X5 X6 X7 X8 X9 X10 X11 X12 X13 X14 X15 X16 X17 X18 X19 ",
        "",
        "Graph Edges: ",
        "1. X0 --- X9",
        "2. X1 --> X16",
        "3. X11 --> X13",
        "4. X13 <-> X14",
        "5. X15 --> X14",
        "6. X18 --> X14",
        "7. X18 --> X3",
        "8. X3 <-> X17",
        "9. X4 --- X9",
        "10. X5 --> X17",
        "11. X6 <-> X16",
        "12. X7 --> X2",
        "13. X8 --- X12",
        "14. X8 --> X2",
        "15. X8 --> X6",
        "16. X9 --- X19",
        ""
    };

}
