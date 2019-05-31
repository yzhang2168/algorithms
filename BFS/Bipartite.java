package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 * Determine if an undirected graph is bipartite. 
 * A bipartite graph is one in which the nodes can be divided into two groups such that 
 * no nodes have direct edges to other nodes in the same group.
 * Examples
	1  --   2
	
	    /   
	
	3  --   4
	
	is bipartite (1, 3 in group 1 and 2, 4 in group 2).
	
	1  --   2
	
	    /   |
	
	3  --   4

is not bipartite.
 * Assumptions
 * The graph is represented by a list of nodes, and the list of nodes is not null.
 * */
public class Bipartite<E> {

	static class GraphNode<Ei> {
		private Ei key;
		private List<GraphNode<Ei>> neighbors;
		
		public GraphNode(Ei key) {
			this.key = key;
			this.neighbors = new ArrayList<GraphNode<Ei>>();
		}
	}
	
	public boolean isBipartite(List<GraphNode<E>> graph) {
		if (graph == null) {
			return true;
		}
		
		// <node, group assignment>
		HashMap<GraphNode<E>, Integer> grouping = new HashMap<>();
		for (GraphNode<E> node : graph) {
			// not bfs-ed yet
			if (!grouping.containsKey(node)) {
				boolean isBipartite = bfs(node, grouping);
				if (!isBipartite) {
					return false;
				} // else: bfs the next node
			}
		}
		return true;
	}
	
	private boolean bfs(GraphNode<E> source, HashMap<GraphNode<E>, Integer> grouping) {
		Queue<GraphNode<E>> queue = new ArrayDeque<GraphNode<E>>();
		queue.offer(source);
		grouping.put(source,  0);
		
		while (!queue.isEmpty()) {
			GraphNode<E> curr = queue.poll();
			int currGroup = grouping.get(curr);
			int neighborGroup = currGroup == 0 ? 1 : 0;

			for (GraphNode<E> neighbor : curr.neighbors) {
				// not traversed yet
				if (!grouping.containsKey(neighbor)) {
					queue.offer(neighbor);
					grouping.put(neighbor, neighborGroup);
				} else if (grouping.get(neighbor) != neighborGroup) {
					return false;
				} // else: ignore and traverse the next node
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Bipartite<Integer> test = new Bipartite<>();

		List<GraphNode<Integer>> graph = new ArrayList<>();
		GraphNode<Integer> one = new GraphNode<Integer>(1);
		GraphNode<Integer> two = new GraphNode<Integer>(2);
		GraphNode<Integer> three = new GraphNode<Integer>(3);
		GraphNode<Integer> four = new GraphNode<Integer>(4);
		
		System.out.println("\ngraph is empty: " + test.isBipartite(graph));
		
		one.neighbors.add(two);
		two.neighbors.add(three);
		three.neighbors.add(four);
		graph.add(one);
		graph.add(two);
		graph.add(three);
		graph.add(four);
		
		System.out.println("\ngraph is bipartite: " + test.isBipartite(graph));

		two.neighbors.add(four);
		four.neighbors.add(two);
		System.out.println("\ngraph is not bipartite: " + test.isBipartite(graph));
	}
}
