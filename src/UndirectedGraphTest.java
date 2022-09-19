import junit.framework.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.rmi.server.UnicastRemoteObject;

public class UndirectedGraphTest {

    @Test
    public void addVertex() {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addVertex("E");
        graph.addVertex("D");
        graph.addVertex("F");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        String result = "A B C D E F ";
        Assert.assertEquals(result,graph.vertexListToString());
    }

    @Test
    public void addVertexWithDuplicate() {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addVertex("E");
        graph.addVertex("E");
        graph.addVertex("D");
        graph.addVertex("F");
        graph.addVertex("A");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("E");
        graph.addVertex("C");
        String result = "A B C D E F ";
        Assert.assertEquals(result,graph.vertexListToString());
    }

    @Test
    public void addEdge() {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addVertex("E");
        graph.addVertex("A");
        graph.addEdge("E", "A");
        String result = "E\n ";
        Assert.assertEquals(result,graph.oneNodeVertexListToString("A"));
    }

    @Test
    public void printGraph() {
    }
}