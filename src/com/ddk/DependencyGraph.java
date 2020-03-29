package com.ddk;

import java.util.*;

class Vertex {
    String data;
    public Vertex(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object vertex) {
        if ( vertex == this )
            return true;
        if ( !(vertex instanceof Vertex) )
            return false;
        Vertex v = (Vertex) vertex;
        return (v.data == this.data);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((this.data == null) ? 0 : this.data.hashCode());
    }
}

class Graph {
    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public Map<Vertex, List<Vertex>> getAdjVertices() {
        return this.adjVertices;
    }

    public Graph() {
        adjVertices = new LinkedHashMap<>();
    }

    private Map<Vertex, List<Vertex>> adjVertices;
}

public class DependencyGraph {

    public static void main(String[] args) {
        Map<String, List<String>> dependencies = new Hashtable<>();
/*
        dependencies.put("A", new ArrayList<String>(){{ add("B"); add("C");}});
        dependencies.put("B", new ArrayList<String>(){{ add("C"); add("D");}});
        dependencies.put("C", new ArrayList<String>(){{ add("D");}});
        dependencies.put("D", new ArrayList<String>(){{ add("E");}});
        dependencies.put("E", new ArrayList<>());
*/

        dependencies.put("A", new ArrayList<String>(){{ add("B"); add("C");}});
        dependencies.put("B", new ArrayList<String>(){{ add("A"); add("C");}});
        dependencies.put("C", new ArrayList<String>(){{ add("A"); add("B");}});

        Graph graph = new Graph();
        for (String keys : dependencies.keySet()) {
            graph.addVertex(keys);
        }
        for (Map.Entry<String, List<String>> entry : dependencies.entrySet()) {
            for (String dep : entry.getValue())
                graph.addEdge(entry.getKey(), dep);
        }

        for ( Map.Entry<Vertex, List<Vertex>> entry : graph.getAdjVertices().entrySet() ) {
            System.out.print(entry.getKey().data + " => ");
            entry.getValue().stream().forEach( e -> System.out.print(e.data + " "));
            System.out.println();
        }
    }
}
