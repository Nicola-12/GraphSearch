/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.data.structure.graphsearch;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author nicol
 */
public class GraphSearch {

    public static void main(String[] args) {
        Graph graph = new SingleGraph("Tutorial 1");
        graph.display();
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("AB", "A", "B");
        graph.addNode("C");
        graph.addEdge("BC", "B", "C", true); // Directed edge.
        graph.addEdge("CA", "C", "A");

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setAttribute("ui.stylesheet", ""
                + "edge {"
                + "   size: 2px;"
                + "   fill-color: gray;"
                + "}");

        Edge ab = graph.getEdge("AB");
        Edge bc = graph.getEdge("BC");
        Edge ca = graph.getEdge("CA");

        ab.setAttribute("ui.label", "AB");
        bc.setAttribute("ui.label", "BC");
        ca.setAttribute("ui.label", "CA");
    }
}
