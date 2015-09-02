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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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

    private final List<String> edgesInAll;

    private final List<String> edgesNotInAll;

    private final List<String> sameEndPoints;

    public SimpleGraphComparison() {
        this.distinctEdges = new HashSet<>();
        this.edgesInAll = new LinkedList<>();
        this.edgesNotInAll = new LinkedList<>();
        this.sameEndPoints = new LinkedList<>();
    }

    public void compare(Path... paths) {
        List<Graph> graphs = new LinkedList<>();
        for (Path path : paths) {
            graphs.add(readInGraph(path));
        }

        distinctEdges.forEach(edge -> {
            boolean inAll = true;
            for (Graph graph : graphs) {
                Map<String, String> edgeMap = graph.getEdgeMap();
                inAll = inAll && edgeMap.containsKey(edge);
            }
            if (inAll) {
                edgesInAll.add(edge);
            } else {
                edgesNotInAll.add(edge);
            }
        });

        edgesInAll.forEach(edge -> {
            boolean sameEndPoint = true;
            String endpoint = null;
            for (Graph graph : graphs) {
                Map<String, String> edgeMap = graph.getEdgeMap();
                String edgeEndPoint = edgeMap.get(edge);
                if (endpoint == null) {
                    endpoint = edgeEndPoint;
                } else {
                    sameEndPoint = sameEndPoint && edgeEndPoint.equals(edgeEndPoint);
                }
            }
            if (sameEndPoint) {
                sameEndPoints.add(edge);
            }
        });
    }

    private Graph readInGraph(Path path) {
        List<String> edges = new LinkedList<>();
        Map<String, String> edgeMap = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            Pattern space = Pattern.compile("\\s+");
            boolean isData = false;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                line = line.trim();

                if (isData) {
                    String[] data = space.split(line);
                    if (data.length == 4) {
                        String endpoint = data[2];
                        String edge1 = data[1];
                        String edge2 = data[3];

                        String forwardEdge = edge1 + "," + edge2;
                        String reverseEdge = edge2 + "," + edge1;
                        switch (endpoint) {
                            case "---":
                                edgeMap.put(forwardEdge, endpoint);
                                edgeMap.put(reverseEdge, endpoint);
                                break;
                            case "<->":
                                edgeMap.put(forwardEdge, endpoint);
                                edgeMap.put(reverseEdge, endpoint);
                                break;
                            case "o-o":
                                edgeMap.put(forwardEdge, endpoint);
                                edgeMap.put(reverseEdge, endpoint);
                                break;
                            default:
                                edgeMap.put(forwardEdge, endpoint);
                                break;
                        }
                        edges.add(forwardEdge);

                        if (!(distinctEdges.contains(forwardEdge) || distinctEdges.contains(reverseEdge))) {
                            distinctEdges.add(forwardEdge);
                        }
                    }
                } else if ("Graph Edges:".equals(line)) {
                    isData = true;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace(System.err);
        }

        return new Graph(edges, edgeMap);
    }

    public Set<String> getDistinctEdges() {
        return distinctEdges;
    }

    public List<String> getEdgesInAll() {
        return edgesInAll;
    }

    public List<String> getEdgesNotInAll() {
        return edgesNotInAll;
    }

    public List<String> getSameEndPoints() {
        return sameEndPoints;
    }

    private class Graph {

        private final List<String> edges;

        private final Map<String, String> edgeMap;

        public Graph(List<String> edges, Map<String, String> edgeMap) {
            this.edges = edges;
            this.edgeMap = edgeMap;
        }

        public List<String> getEdges() {
            return edges;
        }

        public Map<String, String> getEdgeMap() {
            return edgeMap;
        }

    }
}
