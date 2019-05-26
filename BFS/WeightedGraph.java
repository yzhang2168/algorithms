package BFS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;


public class WeightedGraph {

	static class GraphNode {
		int key;
		List<Edge> neighbors;
		
		public GraphNode(int key) {
			this.key = key;
			neighbors = new ArrayList<Edge>();
		}
		
		public void addEdge(Edge edge) {
			neighbors.add(edge);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GraphNode other = (GraphNode) obj;
			if (key != other.key)
				return false;
			if (neighbors == null) {
				if (other.neighbors != null)
					return false;
			} else if (!neighbors.equals(other.neighbors))
				return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + key;
			result = prime * result + ((neighbors == null) ? 0 : neighbors.hashCode());
			return result;
		}
		
		@Override
		public String toString() {
			return key + ": " + neighbors.toString();
		}
	}
	
	static class Edge {
		GraphNode toNode;
		int weight;
		
		public Edge(GraphNode toNode, int weight) {
			this.toNode = toNode;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return toNode.key + ":" + weight;
		}
	}
	
	static class NodeDistance {
		GraphNode toNode;
		int distance;
		
		public NodeDistance(GraphNode toNode, int distance) {
			this.toNode = toNode;
			this.distance = distance;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			} else if (! (o instanceof NodeDistance)) {
				return false;
			} else {
				NodeDistance other = (NodeDistance) o;
				return this.toNode.equals(other.toNode) && this.distance == other.distance;
			}
		}		
		
		@Override
		public String toString() {
			return "<" + toNode.key + "," + distance + ">";
		}
	}
	
	List<GraphNode> adjacencyList;	
	
	public WeightedGraph(List<GraphNode> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (GraphNode node : adjacencyList) {
			sb.append(node.toString()).append("\n");
		}
		return sb.toString();
	}
	
	/** min distances from source to all connected nodes */
	public List<NodeDistance> dijkstra(GraphNode source) {
		List<NodeDistance> result = new ArrayList<NodeDistance>();
		HashMap<GraphNode, Integer> distances = new HashMap<>();	
		dijkstra(source, result, distances);
		return result;
	}
	
	/** min distances from 1st GraphNode in adjacencyList to all connected nodes */
	public List<NodeDistance> dijkstra() {
		List<NodeDistance> result = new ArrayList<NodeDistance>();
		HashMap<GraphNode, Integer> distances = new HashMap<>();	
		for (GraphNode node : this.adjacencyList) {
			if (!distances.containsKey(node)) {
				dijkstra(node, result, distances);
			}
		}
		return result;
	}
	
	private void dijkstra(GraphNode source, List<NodeDistance> result, HashMap<GraphNode, Integer> distances) {
		HashSet<GraphNode> expandedNodes = new HashSet<>();	
		PriorityQueue<NodeDistance> minheap = new PriorityQueue<NodeDistance>(new Comparator<NodeDistance> () {
			@Override
			public int compare(NodeDistance o1, NodeDistance o2) {
				return Integer.valueOf(o1.distance).compareTo(o2.distance);
			}
		});
		
		minheap.offer(new NodeDistance(source, 0));
		distances.put(source, 0);
		
		// can check result size to see if all nodes have been expanded 
		while (!minheap.isEmpty()) {
			NodeDistance curr = minheap.poll();			
			// a node is expanded once and only once
			//if (!result.contains(new NodeDistance(curr.toNode, 0))) {
			if (!expandedNodes.contains(curr.toNode)) {
				expandedNodes.add(curr.toNode);
				result.add(curr);
			}
			
			// <toNode, weight>
			for (Edge edge : curr.toNode.neighbors) {
				int currToNeighbor = curr.distance + edge.weight;
				if (!distances.containsKey(edge.toNode) || distances.get(edge.toNode) > currToNeighbor) {
					minheap.offer(new NodeDistance(edge.toNode, currToNeighbor));
					distances.put(edge.toNode, currToNeighbor);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		GraphNode one = new GraphNode(1);
		GraphNode two = new GraphNode(2);
		GraphNode three = new GraphNode(3);
		GraphNode four = new GraphNode(4);
		GraphNode five = new GraphNode(5);
		GraphNode six = new GraphNode(6);
		one.addEdge(new Edge(two, 1));
		one.addEdge(new Edge(five, 1));
		two.addEdge(new Edge(one, 1));
		two.addEdge(new Edge(three, 1));
		two.addEdge(new Edge(five, 1));
		three.addEdge(new Edge(two, 1));
		three.addEdge(new Edge(four, 1));
		four.addEdge(new Edge(three, 1));
		four.addEdge(new Edge(five, 10));
		four.addEdge(new Edge(six, 1));
		five.addEdge(new Edge(one, 1));
		five.addEdge(new Edge(two, 1));
		five.addEdge(new Edge(four, 10));
		six.addEdge(new Edge(four, 1));

		List<GraphNode> adjacenceyList = new ArrayList<>();
		adjacenceyList.add(one);
		adjacenceyList.add(two);
		adjacenceyList.add(three);
		adjacenceyList.add(four);
		adjacenceyList.add(five);
		adjacenceyList.add(six);
		
		GraphNode seven = new GraphNode(7);
		GraphNode eight = new GraphNode(8);
		adjacenceyList.add(seven);
		adjacenceyList.add(eight);
		
		WeightedGraph graph = new WeightedGraph(adjacenceyList);
		System.out.println(graph);
		
		System.out.println(graph.dijkstra());
		System.out.println(graph.dijkstra(one));
		System.out.println(graph.dijkstra(four));
	}
}