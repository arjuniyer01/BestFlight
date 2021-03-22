import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

/**
 * Tests the implementation of MyGraph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTester {

	private MyGraph<Integer> graph;

	@BeforeEach
	/**
	 * Instantiate graph from last week's shortest path activity.
	 */
	public void createGraph() {
		graph = new MyGraph<>();
		// insert verticies 0-9
		for (int i = 0; i < 10; i++)
			graph.insertVertex(i);
		// insert edges from Week 08. Dijkstra's Activity
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 7, 2);
		graph.insertEdge(1, 8, 4);
		graph.insertEdge(2, 4, 4);
		graph.insertEdge(2, 6, 3);
		graph.insertEdge(3, 1, 6);
		graph.insertEdge(3, 7, 2);
		graph.insertEdge(4, 5, 4);
		graph.insertEdge(5, 0, 2);
		graph.insertEdge(5, 1, 4);
		graph.insertEdge(5, 9, 1);
		graph.insertEdge(6, 3, 1);
		graph.insertEdge(7, 0, 3);
		graph.insertEdge(7, 6, 1);
		graph.insertEdge(8, 9, 3);
		graph.insertEdge(9, 4, 5);
	}

	/**
	 * Checks the distance/total weight cost from the vertex labelled 8 to 3 (should
	 * be 19)
	 */
	@Test
	public void individualPathCostTest() {
		assertTrue(graph.getPathCost(8, 3) == 19);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 8 to 3
	 */
	@Test
	public void individualPathContentsTest() {
		assertTrue(graph.shortestPath(8, 3).toString().equals("[8, 9, 4, 5, 0, 2, 6, 3]"));
	}

	/**
	 * Checks for NoSuchElementException being thrown when a vertex that does not
	 * exist in the graph is passed as the start and as the end
	 */
	@Test
	public void additionalTester1() {
		Exception e;
		e = assertThrows(NoSuchElementException.class, () -> graph.shortestPath(99, 8));
		assertTrue(e.getMessage().equals("Vertex with data 99 does not exist in graph."));
		e = assertThrows(NoSuchElementException.class, () -> graph.shortestPath(8, 99));
		assertTrue(e.getMessage().equals("Vertex with data 99 does not exist in graph."));
	}

	/**
	 * Initially adds a vertex, creates an edge and checks that a certain vertex can
	 * be reached from this new vertex. Then, tries to reach this vertex from
	 * another vertex in the graph and check if a NoSuchElementException is thrown.
	 * Once done, checks if new edge and vertex are removed without errors
	 */
	@Test
	public void additionalTester2() {

		graph.insertVertex(99);
		graph.insertEdge(99, 4, 5);
		assertTrue(graph.getPathCost(99, 4) == 5);
		Exception e = assertThrows(Exception.class, () -> graph.shortestPath(4, 99));
		System.out.println(e.getMessage());
		assertTrue(e.getMessage().equals("A path between 4 and 99 does not exist."));
		graph.removeEdge(99, 4);
		graph.removeVertex(99);
	}

	/**
	 * Checks the distance/total weight cost from the vertex labelled 0 to 8 (should
	 * be 15), and from the vertex labelled 9 to 8 (should be 17).
	 */
	@Test
	public void providedTestToCheckPathCosts() {
		assertTrue(graph.getPathCost(0, 8) == 15);
		assertTrue(graph.getPathCost(9, 8) == 17);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 0 to 8, and from the vertex labelled 9 to 8.
	 */
	@Test
	public void providedTestToCheckPathContents() {
		assertTrue(graph.shortestPath(0, 8).toString().equals("[0, 2, 6, 3, 1, 8]"));
		assertTrue(graph.shortestPath(9, 8).toString().equals("[9, 4, 5, 1, 8]"));
	}

}