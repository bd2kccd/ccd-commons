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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * Sep 7, 2015 9:40:53 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class SimpleGraphUtil {

    private SimpleGraphUtil() {
    }

    public static SimpleGraph readInSimpleGraph(BufferedReader reader) throws IOException {
        SimpleGraph simpleGraph = new SimpleGraph();

        CharSequence[] edgeTypes = {
            "---", "-->", "<--", "<->", "o->", "<-o", "o-o"
        };
        CharSequence emptyString = "";

        List<String> edges = simpleGraph.getEdges();
        Map<String, String> edgeMap = simpleGraph.getEdgeMap();
        Pattern space = Pattern.compile("\\s+");
        boolean isData = false;
        CharSequence delimiter = ";";
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            line = line.trim();
            if (isData) {
                String[] data = space.split(line, 2);
                if (data.length == 2) {
                    String value = data[1].trim();
                    for (CharSequence edgeType : edgeTypes) {
                        value = value.replace(edgeType, delimiter);
                    }
                    String[] nodeNames = value.split(delimiter.toString());
                    if (nodeNames.length == 2) {
                        CharSequence source = nodeNames[0].trim();
                        CharSequence target = nodeNames[1].trim();
                        String edgeType = value.replace(source, emptyString).replace(target, emptyString).trim();

                        String forwardEdge = source + "," + target;
                        String reverseEdge = target + "," + source;
                        switch (edgeType) {
                            case "---":
                                edgeMap.put(forwardEdge, edgeType);
                                edgeMap.put(reverseEdge, edgeType);
                                break;
                            case "<->":
                                edgeMap.put(forwardEdge, edgeType);
                                edgeMap.put(reverseEdge, edgeType);
                                break;
                            case "o-o":
                                edgeMap.put(forwardEdge, edgeType);
                                edgeMap.put(reverseEdge, edgeType);
                                break;
                            default:
                                edgeMap.put(forwardEdge, edgeType);
                                break;
                        }
                        edges.add(forwardEdge);
                    }
                }
            } else if ("Graph Edges:".equals(line)) {
                isData = true;
            }
        }

        return simpleGraph;
    }

}
