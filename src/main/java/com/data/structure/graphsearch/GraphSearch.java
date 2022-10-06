/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.data.structure.graphsearch;

import java.util.ArrayList;
import java.util.List;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author nicol
 */
public class GraphSearch {

    public static final String[] NODES
            = {
                "Lajeado",
                "Arroio do Meio",
                "Estrela",
                "Bento Gonçalves",
                "Encantado",
                "Venâncio Aires",
                "Taquari",
                "Arvorezinha",
                "Teutônia",
                "Carlos Barbosa"};

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

    public static void main(String[] args) {
        Graph graph = new SingleGraph("GraphSearch");
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

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setAttribute("ui.stylesheet", ""
                + "edge {"
                + "   size: 2px;"
                + "   fill-color: gray;"
                + "}");

        for (Edge edge : graph.getEdgeSet().toArray(new Edge[0])) {
            edge.setAttribute("ui.label", NodeId.values()[Integer.parseInt(edge.getId())].getWeight());
        }

        int i = 0;

        for (Node node : nodes) {
            node.setAttribute("ui.label", NODES[i]);
            i++;
        }
        
        
    }
}
