/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.data.structure.studentsPreview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author nicol
 */
public class StudentGraph {

    public static final String[] NODES = {
            "Introdução às ciências exatas",
            "Álgebra linear e geometria analítica",
            "Introdução à computação e à informática",
            "Modelagem de dados",
            "Algoritmos e programação",
            "Programação orientada a objetos",
            "Cálculo I",
            "Probabilidade e estatística",
            "Banco de dados",
            "Programação de aplicações"};

    public static final Integer NODE_INTRODUCTION = 0;
    public static final Integer NODE_LINEAR = 1;
    public static final Integer NODE_COMPUTION = 2;
    public static final Integer NODE_DATA = 3;
    public static final Integer NODE_PROGRAMATION = 4;
    public static final Integer NODE_OBJECTS = 5;
    public static final Integer NODE_CALC_I = 6;
    public static final Integer NODE_PROBABILITY = 7;
    public static final Integer NODE_DATABASE = 8;
    public static final Integer NODE_APPLICATIONS = 9;

    public enum NodeId {
        ID_CD("1"),
        ID_CP("2"),
        ID_CI("3"),
        ID_IC("4"),
        ID_PO("5"),
        ID_DD("6"),
        ID_PD("7"),
        ID_DA("8"),
        ID_OA("9"),
        ID_IL("10"),
        ID_CPR("11"),
        ID_LP("12");

        private NodeId(String weight) {
            this.weight = weight;
        }

        private String weight;

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

    }

    public static List<Node> visited = new ArrayList<>();

    public static void main(String[] args) {
        Graph graph = new SingleGraph("GraphSearch");
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < NODES.length; i++) {
            nodes.add(graph.addNode(NODES[i]));
        }

        
        graph.addEdge(NodeId.ID_CD.ordinal() + "", nodes.get(NODE_COMPUTION), nodes.get(NODE_DATA));
        graph.addEdge(NodeId.ID_CP.ordinal() + "", nodes.get(NODE_COMPUTION), nodes.get(NODE_PROGRAMATION));
        graph.addEdge(NodeId.ID_CI.ordinal() + "", nodes.get(NODE_COMPUTION), nodes.get(NODE_INTRODUCTION));
        graph.addEdge(NodeId.ID_IC.ordinal() + "", nodes.get(NODE_INTRODUCTION), nodes.get(NODE_CALC_I));
        
        graph.addEdge(NodeId.ID_PO.ordinal() + "", nodes.get(NODE_PROGRAMATION), nodes.get(NODE_OBJECTS));
        graph.addEdge(NodeId.ID_DD.ordinal() + "", nodes.get(NODE_DATA), nodes.get(NODE_DATABASE));

        graph.addEdge(NodeId.ID_PD.ordinal() + "", nodes.get(NODE_PROGRAMATION), nodes.get(NODE_DATABASE));
        
        graph.addEdge(NodeId.ID_DA.ordinal() + "", nodes.get(NODE_DATABASE), nodes.get(NODE_APPLICATIONS));
        graph.addEdge(NodeId.ID_OA.ordinal() + "", nodes.get(NODE_OBJECTS), nodes.get(NODE_APPLICATIONS));

        graph.addEdge(NodeId.ID_IL.ordinal() + "", nodes.get(NODE_INTRODUCTION), nodes.get(NODE_LINEAR));
        
        graph.addEdge(NodeId.ID_CPR.ordinal() + "", nodes.get(NODE_CALC_I), nodes.get(NODE_PROBABILITY));
        graph.addEdge(NodeId.ID_LP.ordinal() + "", nodes.get(NODE_LINEAR), nodes.get(NODE_PROBABILITY));

        graph.setAttribute("ui.stylesheet", ""
                + "edge {"
                + "   size: 2px;"
                + "   fill-color: gray;"
                + "}");

        for (Edge edge : graph.getEdgeSet().stream().collect(Collectors.toList())) {
            edge.setAttribute("weight", NodeId.values()[Integer.parseInt(edge.getId())].getWeight());
        }

        System.out.println( "\nDISCIPLINAS:" );

        int i = 0;

        for (Node node : nodes) {
            node.setAttribute("ui.label", NODES[i]);
            System.out.println(NODES[i]);
            i++;
        }

        Node computacao = graph.getNode(NODES[NODE_COMPUTION]);

        System.out.println("\nDEEP FIRST ITERATOR");
        deepFirst(computacao);

        System.out.println("\nDEEP BREADTH ITERATOR");
        breadthFirst(computacao);
    }

    public static void deepFirst(Node first) {
        Iterator<Node> deepFirst = first.getDepthFirstIterator();

        while (deepFirst.hasNext()) {
            System.out.println("Node -> " + deepFirst.next());
        }
    }

    public static void breadthFirst(Node first) {
        Iterator<Node> breadthFirst = first.getBreadthFirstIterator();

        while (breadthFirst.hasNext()) {
            System.out.println("Node -> " + breadthFirst.next());
        }
    }
}
