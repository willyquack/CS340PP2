import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UndirectedGraph {

    public static void main(String args[]) throws IOException {
        //DO NOT CHANGE main
        //This code assumes the input file is syntactically correct
        UndirectedGraph g = new UndirectedGraph();
        BufferedReader b = new BufferedReader(new FileReader(args[0]));
        String line = b.readLine();
        Scanner scan = new Scanner(line);
        while (scan.hasNext()) {
            g.addVertex(scan.next());
        }
        line = b.readLine();
        while (line != null) {
            scan = new Scanner(line);
            g.addEdge(scan.next(), scan.next());
            line = b.readLine();
        }
        g.printGraph();
    }

    public boolean addVertex(String s) {
        return true;
    }

    public void addEdge(String v1, String v2) {

    }

    public void printGraph() {

    }

    private class EdgeNode {
        private VertexNode edge[]; //the two vertices that make the edge
        private EdgeNode nextE[]; //the next edge node for each edge list
        private EdgeNode(VertexNode v1, VertexNode v2) {
            //PRE: v1.vertexName < v2.vertexName
            edge = new VertexNode[2];
            edge[0] = v1;
            edge[1] = v2;
            nextE = new EdgeNode[2];
        }
    }

    private class VertexNode {
        private String vertexName;
        private EdgeNode edges[];
        private VertexNode nextV;

        private VertexNode(String v, VertexNode n) {
            vertexName = new String(v);
            edges = new EdgeNode[2];
            nextV = n;
        }
    }
}
