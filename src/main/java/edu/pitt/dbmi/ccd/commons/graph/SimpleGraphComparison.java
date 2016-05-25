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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * Sep 1, 2015 1:25:54 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class SimpleGraphComparison {

    private final Set<String> distinctEdges;

    private final Set<String> edgesInAll;

    private final Set<String> edgesNotInAll;

    private final Set<String> sameEdgeTypes;

    public SimpleGraphComparison() {
        this.distinctEdges = new HashSet<>();
        this.edgesInAll = new HashSet<>();
        this.edgesNotInAll = new HashSet<>();
        this.sameEdgeTypes = new HashSet<>();
    }

    public void compare(List<SimpleGraph> simpleGraphs) {
        if (simpleGraphs == null) {
            return;
        }

        Pattern delimiter = Pattern.compile(",");

        // gather all the edges in the graph together
        simpleGraphs.forEach(simpleGraph -> {
            List<String> edges = simpleGraph.getEdges();
            edges.forEach(edge -> {
                String[] endPoints = delimiter.split(edge);
                if (endPoints.length == 2) {
                    String reverseEdge = endPoints[1] + "," + endPoints[0];
                    if (!(distinctEdges.contains(reverseEdge) || distinctEdges.contains(edge))) {
                        distinctEdges.add(edge);
                    }
                }
            });
        });

        // determine if each distinct edge is in all graph
        distinctEdges.forEach(edge -> {
            String[] endPoints = delimiter.split(edge);
            if (endPoints.length == 2) {
                String reverseEdge = endPoints[1] + "," + endPoints[0];

                boolean inAll = false;
                for (SimpleGraph simpleGraph : simpleGraphs) {
                    Map<String, String> edgeMap = simpleGraph.getEdgeMap();
                    inAll = edgeMap.containsKey(edge) || edgeMap.containsKey(reverseEdge);
                    if (!inAll) {
                        break;
                    }
                }

                if (inAll) {
                    edgesInAll.add(edge);
                } else {
                    edgesNotInAll.add(edge);
                }
            }
        });

        // determine if each of the edge that is in all of the graphs has the same edge type
        edgesInAll.forEach(edge -> {
            boolean sameEdgeType = true;
            String edgeType = null;
            for (SimpleGraph simpleGraph : simpleGraphs) {
                Map<String, String> edgeMap = simpleGraph.getEdgeMap();
                String nodeEdgeType = edgeMap.get(edge);
                if (edgeType == null) {
                    edgeType = nodeEdgeType;
                } else {
                    sameEdgeType = edgeType.equals(nodeEdgeType);
                    if (!sameEdgeType) {
                        break;
                    }
                }
            }
            if (sameEdgeType) {
                sameEdgeTypes.add(edge);
            }
        });
    }

    public Set<String> getDistinctEdges() {
        return distinctEdges;
    }

    public Set<String> getEdgesInAll() {
        return edgesInAll;
    }

    public Set<String> getEdgesNotInAll() {
        return edgesNotInAll;
    }

    public Set<String> getSameEdgeTypes() {
        return sameEdgeTypes;
    }

}
