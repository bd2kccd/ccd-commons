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
package edu.pitt.dbmi.ccd.commons.graph;

import edu.pitt.dbmi.ccd.commons.file.AbstractFileTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 *
 * Sep 1, 2015 1:29:46 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class SimpleGraphComparisonTest extends AbstractFileTest {

    public SimpleGraphComparisonTest() {
    }

    /**
     * Test of compare method, of class SimpleGraphComparison.
     *
     * @throws Exception
     */
    @Test
    public void testCompare() throws Exception {
        Path pcstable1 = tempFolder.newFile("pcstable1.txt").toPath();
        Path pcstable2 = tempFolder.newFile("pcstable2.txt").toPath();

        Files.write(pcstable1, Arrays.asList(PC_STABLE_RESULT1), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        Files.write(pcstable2, Arrays.asList(PC_STABLE_RESULT2), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        Path[] files = {pcstable1, pcstable2};

        List<SimpleGraph> graphs = new LinkedList<>();
        for (Path file : files) {
            try (BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset())) {
                graphs.add(SimpleGraphUtil.readInSimpleGraph(reader));
            } catch (IOException exception) {
                exception.printStackTrace(System.err);
            }
        }

        SimpleGraphComparison simpleGraphComparison = new SimpleGraphComparison();
        simpleGraphComparison.compare(graphs);

        Set<String> distinctEdges = simpleGraphComparison.getDistinctEdges();
        Set<String> edgesInAll = simpleGraphComparison.getEdgesInAll();
        Set<String> edgesNotInAll = simpleGraphComparison.getEdgesNotInAll();
        Set<String> sameEdgeTypes = simpleGraphComparison.getSameEdgeTypes();

        List<String> results = new LinkedList<>();
        distinctEdges.forEach(edge -> {
            StringBuilder sb = new StringBuilder(edge + " -> ");
            if (edgesInAll.contains(edge)) {
                sb.append(",X");
            } else {
                sb.append(", ");
            }

            if (edgesNotInAll.contains(edge)) {
                sb.append(",X");
            } else {
                sb.append(", ");
            }

            if (sameEdgeTypes.contains(edge)) {
                sb.append(",X");
            } else {
                sb.append(", ");
            }

            results.add(sb.toString());
        });

//        System.out.println("================================================================================");
//        results.forEach(result -> {
//            System.out.println(result);
//        });
//        System.out.println("================================================================================");
//        System.out.println("================================================================================");
//        System.out.println(distinctEdges);
//        System.out.println(edgesInAll);
//        System.out.println(edgesNotInAll);
//        System.out.println(sameEndPoints);
//        System.out.println("================================================================================");
    }

}
