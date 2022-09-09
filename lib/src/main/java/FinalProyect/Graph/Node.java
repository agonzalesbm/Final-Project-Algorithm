package FinalProyect.Graph;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    private T value;
    private List<Edge<T>> edgeList;
    private boolean visited;

    public Node(T value) {
        this.value = value;
        edgeList = new LinkedList<>();
        visited = false;
    }

    public boolean addEdge(Edge<T> newEdge) {
        evaluateEdge(newEdge);
        if (!edgeList.contains(newEdge)) {
            edgeList.add(newEdge);
            return true;
        }
        return false;
    }

    public boolean isConnectedWith(Node<T> node) {
        for (Edge<T> edge : edgeList) {
            if (edge.getDestination().equals(node)) {
                return true;
            }
        }
        return false;
    }

    public Edge<T> getEdgeWithConnectionTo(Node<T> node) {
        if (isConnectedWith(node)) {
            for (Edge<T> edge : edgeList) {
                if (edge.getDestination().equals(node)) {
                    return edge;
                }
            }
        }
        return null;
    }

    public List<Edge<T>> getEdgeList() {
        return this.edgeList;
    }

    public boolean removeEdge(Edge<T> edg) {
        if (edgeList.contains(edg)) {
            edgeList.remove(edg);
            return true;
        }
        return false;
    }

    public T getValue() {
        return this.value;
    }

    public void visit() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void unVisitNode() {
        this.visited = false;
    }

    public void evaluateEdge(Edge<T> edg) {
        if (edg == null) {
            throw new NullPointerException("Not valid null value");
        }
    }

    public void evaluateGivenNode(Node<T> node) {
        if (node == null) {
            throw new NullPointerException("Not valid null value");
        }
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Node<T>node = (Node<T>) obj;
        return value.equals(node.getValue());
    }

}
