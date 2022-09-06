package FinalProyect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class UndirectedWeightedGraph<T> {
    private Map<Node<T>, List<Edge<T>>> adjList;
    private Map<Node<T>, Integer> elementDictionaryIndex;

    public UndirectedWeightedGraph() {
        adjList = new LinkedHashMap<>();
        elementDictionaryIndex = new LinkedHashMap<>();
    }

    public boolean addNode(Node<T> node) {
        evaluateGivenNode(node);
        if (!adjList.containsKey(node)) {
            adjList.put(node, node.getEdgeList());
            elementDictionaryIndex.put(node, adjList.size() - 1);
            return true;
        }
        return false;
    }

    public void addNodes(Node<T>... nodes) {
        for (Node<T> actualNode : nodes) {
            addNode(actualNode);
        }
    }

    public boolean removeNode(Node<T> node) {
        evaluateGivenNode(node);
        if (adjList.containsKey(node)) {
            adjList.remove(node);
            for (Node<T> actNode : adjList.keySet()) {
                adjList.get(actNode).removeIf(edge -> edge.getDestination().equals(node));
                if (actNode.getEdgeList().size() == 0) {
                    removeNode(actNode);
                }
            }
            return true;
        }
        return false;
    }

    public boolean addEdge(Node<T> source, Node<T> destination, Weight weight) {
        evaluateGivenNode(source);
        evaluateGivenNode(destination);
        if (adjList.containsKey(source) && adjList.containsKey(destination)) {
            if (source == null && destination == null) {
                return false;
            }
            source.addEdge(new Edge<>(source, destination, weight));
            destination.addEdge(new Edge<>(destination, source, weight.getInverse()));
            return true;
        }
        return false;
    }

    public boolean removeEdge(Node<T> source, Node<T> destiny) {
        evaluateGivenNode(source);
        evaluateGivenNode(destiny);
        if (adjList.containsKey(source) && adjList.containsKey(destiny)) {
            for (Node<T> actNode : adjList.keySet()) {
                adjList.get(actNode).removeIf(edge -> edge.getDestination().equals(destiny));
                adjList.get(actNode).removeIf(edge -> edge.getSource().equals(source));
            }
            return true;
        }
        return false;
    }

    public Set<T> breadthFirstSearch(Node<T> n) {
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        Set<T> collectedData = new HashSet<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            Node<T> root = queue.remove();
            if (root.isVisited()) {
                continue;
            }
            root.visit();
            collectedData.add(root.getValue());
            List<Edge<T>> neighbors = adjList.get(root);
            for (Edge<T> neigboor : neighbors) {
                if (!neigboor.getDestination().isVisited()) {
                    queue.add((Node<T>) neigboor.getDestination());
                }
            }
        }
        return collectedData;
    }

    public List<List<Node<T>>> findAllRoutes(Node<T> start, Node<T> end) {
        List<List<Node<T>>> totalPaths = new ArrayList<>();
        if (adjList.containsKey(start) && adjList.containsKey(end)) {
            boolean[] isVisited = new boolean[adjList.size()];
            List<Node<T>> pathList = new ArrayList<>();
            pathList.add(start);
            exploreRoutes(start, end, isVisited, pathList, totalPaths);
        }
        return totalPaths;
    }

    private void exploreRoutes(Node<T> source, Node<T> destination, boolean[] isVisited, List<Node<T>> pathList,
            List<List<Node<T>>> totalPaths) {
        if (source.equals(destination)) {
            totalPaths.add((new ArrayList<>(pathList)));
            return;
        }
        int position = elementDictionaryIndex.get(source);
        isVisited[position] = true;
        for (Edge<T> edg : adjList.get(source)) {
            position = elementDictionaryIndex.get(edg.getDestination());
            if (!isVisited[position]) {
                pathList.add(edg.getDestination());
                exploreRoutes(edg.getDestination(), destination, isVisited, pathList, totalPaths);
                pathList.remove(edg.getDestination());
            }
        }
        isVisited[elementDictionaryIndex.get(source)] = false;
    }

    private void evaluateGivenNode(Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("INVALID  NULL  ARGUMENT NODE");
        }
    }

    public void markAllAsUnvisited() {
        for (var node : adjList.keySet()) {
            node.unVisitNode();
        }
    }

    public void markAllAsNotDrawn() {
        for (var node : adjList.keySet()) {
            node.markAsNotDrawn();
        }
    }

    public Set<Node<T>> getAllNodes() {
        return adjList.keySet();
    }

    @Override
    public String toString() {
        return "UndirectedWeightedGraph{" +
                "adjList=" + adjList +
                '}';
    }

    public boolean removeAllNodes() {
        adjList.clear();
        elementDictionaryIndex.clear();
        return true;
    }
}
