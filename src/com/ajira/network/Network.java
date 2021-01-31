package com.ajira.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ajira.exceptions.InvalidConnectionException;
import com.ajira.exceptions.InvalidDeviceException;
import com.ajira.exceptions.InvalidNodeException;
import com.ajira.exceptions.PathNotExistException;
import com.ajira.network.operations.NetworkOperations;
import com.ajira.node.DeviceType;
import com.ajira.node.Node;

/**
 * Network class parameters nodesIndexMapping nodes to index connections
 * connections implements behavior of adjacency list
 * 
 * @author yatharth.govil
 *
 */
public class Network implements NetworkOperations {

	private Map<String, Integer> nodesIndexMapping = new HashMap<String, Integer>();
	private List<Node> nodes = new ArrayList<Node>();
	private List<Set<Integer>> connections = new ArrayList<Set<Integer>>();
	private int curIndex = -1;

	/**
	 * adds device to current network
	 */
	public int add(String name, DeviceType dType) {
		if (nodesIndexMapping.containsKey(name)) {
			throw new InvalidNodeException("Node Already added"); // key alreaady exists
		}

		nodesIndexMapping.put(name, ++curIndex);
		Node node = new Node(curIndex, name, dType);
		nodes.add(node);
		connections.add(new HashSet<Integer>());
		return 1;// successfully added

	}

	/*
	 * connects two nodes
	 */
	public int connect(String n1, String n2) {

		if (!nodesIndexMapping.containsKey(n1) || !nodesIndexMapping.containsKey(n2)) {
			throw new InvalidNodeException("Node doesnt exist");

		}
		int index1 = nodesIndexMapping.get(n1);
		int index2 = nodesIndexMapping.get(n2);
		Set<Integer> connectedSet1 = connections.get(index1);
		Set<Integer> connectedSet2 = connections.get(index2);
		if (connectedSet1.contains(index2))
			throw new InvalidConnectionException("Already Connected");
		else {
			connectedSet1.add(index2);
			connectedSet2.add(index1);
		}
		return 1;// Node successfully added
	}

	/**
	 * sets strength of the node
	 * 
	 * @param name
	 * @param strength
	 * @return
	 */
	public int setStrength(String name, int strength) {

		if (!nodesIndexMapping.containsKey(name))
			throw new InvalidNodeException("Invalid Node");

		Node node = nodes.get(nodesIndexMapping.get(name));
		node.setStrength(strength);
		return 1; // success
	}

	/**
	 * prints route between two nodes. caching can be implemented to improve prformance
	 * can be applied
	 */
	public void infoRoute(String n1, String n2) {

		if (!nodesIndexMapping.containsKey(n1) || !nodesIndexMapping.containsKey(n2))
			throw new InvalidNodeException("Node Doesnt exist");
		int index1 = nodesIndexMapping.get(n1);
		int index2 = nodesIndexMapping.get(n2);
		Node node1 = nodes.get(index1);
		Node node2 = nodes.get(index2);
		if (node1.getdType().equals(DeviceType.REPEATER) || node2.getdType().equals(DeviceType.REPEATER)) {
			throw new InvalidDeviceException("Invalid device");
		}

		Set<Integer> visited = new HashSet<Integer>();
		int curstrength = nodes.get(index1).getStrength();
		List<Integer> path = new ArrayList<Integer>();
		boolean pathExist = dfsPath(index1, index2, curstrength, path, visited);

		if (pathExist) {
			System.out.print(nodes.get(index1).getName());
			for (int i = path.size() - 1; i >= 0; i--)
				System.out.print("->" + nodes.get(path.get(i)).getName());
		} else {
			throw new PathNotExistException("Path doesnt exist");
		}
	}

	/**
	 * implements modified dfs to search path between two nodes
	 * 
	 * @param index
	 * @param searchIndex
	 * @param strength
	 * @param path
	 * @param visited
	 * @return
	 */
	private boolean dfsPath(int index, int searchIndex, int strength, List<Integer> path, Set<Integer> visited) {

		Set<Integer> connectedSet = connections.get(index);
		boolean pathFound;
		if (strength == 0) {
			return false;
		}

		visited.add(index);
		for (Integer idx : connectedSet) {
			if (!visited.contains(idx)) {
				if (searchIndex == idx) {
					pathFound = true;
					path.add(idx);
					return true;
				}
				if (nodes.get(idx).getdType().equals(DeviceType.COMPUTER))
					pathFound = dfsPath(idx, searchIndex, strength - 1, path, visited);
				else
					pathFound = dfsPath(idx, searchIndex, 2 * strength, path, visited);

				if (pathFound) {
					path.add(idx);
					return true;
				}
			}
		}
		visited.remove(index);
		return false;

	}

	public Map<String, Integer> getNodesMap() {
		return nodesIndexMapping;
	}

	public void setNodesMap(Map<String, Integer> nodesMap) {
		this.nodesIndexMapping = nodesMap;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Set<Integer>> getConnections() {
		return connections;
	}

	public int getCurIndex() {
		return curIndex;
	}

}
