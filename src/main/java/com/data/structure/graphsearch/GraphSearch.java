/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.data.structure.graphsearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author nicol
 */
public class GraphSearch {

    public static final String[] NODES = {
            "Lajeado",
            "Arroio do Meio",
            "Estrela",
            "Bento Gonçalves",
            "Encantado",
            "Venâncio Aires",
            "Taquari",
            "Arvorezinha",
            "Teutônia",
            "Carlos Barbosa" };

    public static final Integer NODE_LAJEADO = 0;
    public static final Integer NODE_ARROIO_DO_MEIO = 1;
    public static final Integer NODE_ESTRELA = 2;
    public static final Integer NODE_BENTO = 3;
    public static final Integer NODE_ENCANTADO = 4;
    public static final Integer NODE_VENANCIO = 5;
    public static final Integer NODE_TAQUARI = 6;
    public static final Integer NODE_ARVOREZINHA = 7;
    public static final Integer NODE_TEUTONIA = 8;
    public static final Integer NODE_BARBOSA = 9;

    public enum NodeId {
        ID_LA("7"),
        ID_LE("5"),
        ID_LV("25"),
        ID_ETE("14"),
        ID_ETA("32"),
        ID_AE("17"),
        ID_VA("84"),
        ID_VT("37"),
        ID_TT("37"),
        ID_TB("38"),
        ID_BB("13"),
        ID_EB("36"),
        ID_EA("51");

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

        graph.addEdge(NodeId.ID_LA.ordinal() + "", nodes.get(NODE_LAJEADO), nodes.get(NODE_ARROIO_DO_MEIO));
        graph.addEdge(NodeId.ID_LE.ordinal() + "", nodes.get(NODE_LAJEADO), nodes.get(NODE_ESTRELA));
        graph.addEdge(NodeId.ID_LV.ordinal() + "", nodes.get(NODE_LAJEADO), nodes.get(NODE_VENANCIO));

        graph.addEdge(NodeId.ID_ETE.ordinal() + "", nodes.get(NODE_ESTRELA), nodes.get(NODE_TEUTONIA));
        graph.addEdge(NodeId.ID_ETA.ordinal() + "", nodes.get(NODE_ESTRELA), nodes.get(NODE_TAQUARI));

        graph.addEdge(NodeId.ID_AE.ordinal() + "", nodes.get(NODE_ARROIO_DO_MEIO), nodes.get(NODE_ENCANTADO));

        graph.addEdge(NodeId.ID_VA.ordinal() + "", nodes.get(NODE_VENANCIO), nodes.get(NODE_ARVOREZINHA));
        graph.addEdge(NodeId.ID_VT.ordinal() + "", nodes.get(NODE_VENANCIO), nodes.get(NODE_TAQUARI));

        graph.addEdge(NodeId.ID_TT.ordinal() + "", nodes.get(NODE_TAQUARI), nodes.get(NODE_TEUTONIA));

        graph.addEdge(NodeId.ID_TB.ordinal() + "", nodes.get(NODE_TEUTONIA), nodes.get(NODE_BARBOSA));

        graph.addEdge(NodeId.ID_BB.ordinal() + "", nodes.get(NODE_BARBOSA), nodes.get(NODE_BENTO));

        graph.addEdge(NodeId.ID_EB.ordinal() + "", nodes.get(NODE_ENCANTADO), nodes.get(NODE_BENTO));
        graph.addEdge(NodeId.ID_EA.ordinal() + "", nodes.get(NODE_ENCANTADO), nodes.get(NODE_ARVOREZINHA));

        graph.setAttribute("ui.stylesheet", ""
                + "edge {"
                + "   size: 2px;"
                + "   fill-color: gray;"
                + "}");

        for (Edge edge : graph.getEdgeSet().stream().collect(Collectors.toList())) {
            edge.setAttribute("weight", NodeId.values()[Integer.parseInt(edge.getId())].getWeight());
        }

        int i = 0;

        for (Node node : nodes) {
            node.setAttribute("ui.label", NODES[i]);
            i++;
        }

        Node lajeado = graph.getNode(NODES[NODE_LAJEADO]);

        System.out.println("DEEP FIRST ITERATOR");
        deepFirst(lajeado);

        System.out.println("\nDEEP BREADTH ITERATOR");
        breadthFirst(lajeado);

        System.out.println("\nCAMINHO MAIS CURTO PARA CARA LOCAL");
        shorstPath(graph);

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

    public static void shorstPath(Graph graph) {
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "weight", "weight");

        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode(NODES[NODE_LAJEADO]));
        dijkstra.compute();

        for (Node node : graph)
            System.out.printf("%s->%s:%6.2f%n", dijkstra.getSource(), node, dijkstra.getPathLength(node));


        for (Node node : dijkstra.getPathNodes(graph.getNode(NODES[NODE_ARVOREZINHA])))
            node.addAttribute("ui.style", "fill-color: blue;");


        for (Edge edge : dijkstra.getTreeEdges())
            edge.addAttribute("ui.style", "fill-color: red;");

        
        System.out.println("CAMINHO MAIS CURTO DE LAJEADO PARA ARVOREZINHA:\n"
                + dijkstra.getPath(graph.getNode(NODES[NODE_ARVOREZINHA])));

        List<Node> list1 = new ArrayList<Node>();
        for (Node node : dijkstra.getPathNodes(graph.getNode(NODES[NODE_ARVOREZINHA])))
            list1.add(0, node);
    }

}
