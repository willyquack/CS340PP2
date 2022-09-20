import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UndirectedGraph {

    private VertexNode vertices; //head of the list of vertices
    public UndirectedGraph() {
        vertices = null; //no sentinel node
    }

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
        if(contains(s)) { return false; }
        if(vertices == null) { vertices = new VertexNode(s,null); return true; }
        if(vertices.vertexName.toLowerCase().compareTo(s.toLowerCase()) > 0) {
            vertices = new VertexNode(s, vertices);
            return true;
        }
        VertexNode curNode = vertices;
        while(curNode.nextV != null && curNode.nextV.vertexName.toLowerCase().compareTo(s.toLowerCase()) < 0) {
            curNode = curNode.nextV;
        }
        curNode.nextV = new VertexNode(s,curNode.nextV);
        return true;
    }

    private boolean contains(String s) {
        VertexNode curNode = vertices;
        while(curNode != null) {
            if(curNode.vertexName.equals(s)) {
                return true;
            }
            curNode = curNode.nextV;
        }
        return false;
    }

    public String vertexListToString() {
        VertexNode curNode = vertices;
        StringBuilder result = new StringBuilder();
        while(curNode != null) {
            result.append(curNode.vertexName + " ");
            curNode = curNode.nextV;
        }
        return result.toString();
    }

    private VertexNode getVertex(String vertexName) {
        VertexNode curNode = vertices;
        while(curNode != null) {
            if(curNode.vertexName.equals(vertexName)){
                return curNode;
            }
            curNode = curNode.nextV;
        }
        return curNode;
    }

    public void addEdge(String v1, String v2) {
        String[] inOrder = new String[2];
        if(v1.toLowerCase().compareTo(v2.toLowerCase()) < 0) {
            inOrder[0] = v1;
            inOrder[1] = v2;
        } else {
            inOrder[0] = v2;
            inOrder[1] = v1;
        }
        VertexNode vertexNodeA = getVertex(inOrder[0]);
        VertexNode vertexNodeB = getVertex(inOrder[1]);
        EdgeNode toAdd = new EdgeNode(vertexNodeA,vertexNodeB);
        addEdgeToVertexEdgeList0(vertexNodeA,vertexNodeB);
        addEdgeToVertexEdgeList1(vertexNodeB,vertexNodeA);
    }

    private void addEdgeToVertexEdgeList1(EdgeNode toAdd, VertexNode vertexNodeA, VertexNode vertexNodeB) {
        if(vertexNodeA.edges[1] == null) { vertexNodeA.edges[1] = new EdgeNode(vertexNodeA,vertexNodeB); return; }
        if(vertexNodeA.edges[1].edge[1].vertexName.compareTo(vertexNodeA.vertexName) < 0) {
            EdgeNode temp = new EdgeNode(vertexNodeA,vertexNodeB);
            temp.nextE[1] = vertexNodeA.edges[1].nextE[1];
            vertexNodeA.edges[1].nextE[1] = temp;
            return;
        }
        EdgeNode curEdge = vertexNodeA.edges[1];
        while(curEdge.nextE[1] != null && curEdge.nextE[1].edge[1].vertexName.compareTo(vertexNodeA.vertexName) < 0) {
            curEdge = curEdge.nextE[1];
        }
        EdgeNode temp = new EdgeNode(vertexNodeA,vertexNodeB);
        temp.nextE[1] = curEdge.nextE[1];
        curEdge.nextE[1] = temp;
    }

    private void addEdgeToVertexEdgeList0(EdgeNode toAdd) {
        if(toAdd.edge[0].edges[0] == null) { toAdd.edge[0].edges[0] = toAdd; }
        if(toAdd.edge[0].vertexName.compareTo(toAdd.edge[0].edges[0].edge[0].vertexName) > 0) {
            toAdd.nextE[0] = toAdd.edge[0].edges[0].edge[0];
            toAdd.edge[0].
                    
        }
    }

//    private void addEdgeToVertexEdgeList0(VertexNode vertexNodeA, VertexNode vertexNodeB) {
//        if(vertexNodeA.edges[0] == null) { vertexNodeA.edges[0] = new EdgeNode(vertexNodeB,vertexNodeA); return; }
//        if(vertexNodeA.edges[0].edge[0].vertexName.compareTo(vertexNodeA.vertexName) > 0) {
//            EdgeNode temp = new EdgeNode(vertexNodeB,vertexNodeA);
//            temp.nextE[0] = vertexNodeA.edges[0].nextE[0];
//            vertexNodeA.edges[0].nextE[0] = temp;
//            return;
//        }
//        EdgeNode curEdge = vertexNodeA.edges[0];
//        while(curEdge.nextE[0] != null && curEdge.nextE[0].edge[0].vertexName.compareTo(vertexNodeA.vertexName) > 0) {
//            curEdge = curEdge.nextE[0];
//        }
//        EdgeNode temp = new EdgeNode(vertexNodeB,vertexNodeA);
//        temp.nextE[0] = curEdge.nextE[0];
//        curEdge.nextE[0] = temp;
//    }

    public void printGraph() {
        VertexNode curVertex = vertices;
        EdgeNode curEdge;
        StringBuilder result = new StringBuilder();
        while(curVertex != null) {
            result.append(curVertex.vertexName + " ");
            curEdge = curVertex.edges[0];
            while(curEdge != null) {
                result.append(curEdge.edge[0].vertexName + " ");
                curEdge = curEdge.nextE[0];
            }
            result.append("\n  ");
            curEdge = curVertex.edges[1];
            while(curEdge != null) {
                result.append(curEdge.edge[1].vertexName + " ");
                curEdge = curEdge.nextE[1];
            }
            result.append("\n");
            curVertex = curVertex.nextV;
        }
        System.out.println(result.toString());
    }

    public String oneNodeVertexListToString(String vertexName) {
        VertexNode curNode = vertices;
        while(curNode != null && !curNode.vertexName.equals(vertexName)) {
            curNode = curNode.nextV;
        }
        StringBuilder result = new StringBuilder();
        EdgeNode curEdge = curNode.edges[0];
        while(curEdge != null) {
            result.append(curEdge.edge[0].vertexName + " ");
            curEdge = curEdge.nextE[0];
        }
        curEdge = curNode.edges[1];
        result.append("\n");
        while(curEdge != null) {
            result.append(curEdge.edge[1].vertexName + " ");
            curEdge = curEdge.nextE[1];
        }
        return result.toString();
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
