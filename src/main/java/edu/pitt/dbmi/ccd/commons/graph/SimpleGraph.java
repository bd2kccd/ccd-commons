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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * Sep 7, 2015 9:29:21 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class SimpleGraph {

    private final List<String> edges;

    private final Map<String, String> edgeMap;

    public SimpleGraph() {
        this.edges = new LinkedList<>();
        this.edgeMap = new HashMap<>();
    }

    public List<String> getEdges() {
        return edges;
    }

    public Map<String, String> getEdgeMap() {
        return edgeMap;
    }

}
