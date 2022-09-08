package FinalProyect;


import java.util.*;

public class DirectedGraph<T> {
    private final Map<Node<T>, List<Edge<T>>> graph = new HashMap<>();
    private Map<T, Node<T>> nodeHashMap = new HashMap<>();
    private final Map<String, Edge<T>> edgeHashMap = new HashMap<>();

    public DirectedGraph() {
        this.nodeHashMap = nodeHashMap;
    }

    private Node<T> insertNode(T value){
        Node<T> node = new Node<T>(value);
        if (!hasNode(node.getValue()))
            nodeHashMap.put(value, node);
        return node;
    }
    private Edge<T> insertEdge(T source, T destination){
        Edge<T> edge = new Edge<T>(insertNode(source), insertNode(destination));
        if (!hasEdge(edge.getSource().getValue(),edge.getDestination().getValue()))
            edgeHashMap.put(source + "->" + destination, edge);
        return edge;
    }

    public void addNode(T value) {
        if (!hasNode(value)) {
            List<Edge<T>> edgeList = new ArrayList<>();
            this.graph.put(insertNode(value), edgeList);
        }
    }

    public void addNode(Node<T> node) {
        addNode(node.getValue());
    }

    private boolean hasNode(T value) {
        return nodeHashMap.containsKey(value);
    }

    private boolean hasEdge(T source, T destination) {
        return edgeHashMap.containsKey(source + "->" + destination);
    }

    public void addEdge(T source, T destination) {
        if (!hasNode(source)) addNode(source);
        if (!hasNode(destination)) addNode(destination);
        if (!hasEdge(source, destination)) {
            List<Edge<T>> edgeSource = graph.get(insertNode(source));
            List<Edge<T>> edgeDestination = graph.get(insertNode(destination));
            edgeSource.add(insertEdge(source, destination));
            edgeDestination.add(insertEdge(source, destination));
            this.graph.put(insertNode(source), edgeSource);
            this.graph.put(insertNode(destination), edgeDestination);
        }
    }

    public void addEdge(Edge<T> edge) {
        addEdge(edge.getSource().getValue(),edge.getDestination().getValue());
    }

    public void deleteEdge(T source, T destination) {
        if (hasEdge(source, destination)) {
            List<Edge<T>> edgeSource = graph.get(insertNode(source));
            List<Edge<T>> edgeDestination = graph.get(insertNode(destination));
            edgeSource.remove(insertEdge(source, destination));
            edgeDestination.remove(insertEdge(source, destination));
            this.graph.put(insertNode(source), edgeSource);
            this.graph.put(insertNode(destination), edgeDestination);
            edgeHashMap.remove(source + "->" + destination);
        }
    }

    public void deleteEdge(Edge<T> edge) {
        deleteEdge(edge.getSource().getValue(), edge.getDestination().getValue());
    }

    public void deleteNode(T value) {
        if (hasNode(value)) {
            List<Edge<T>> edges = new ArrayList<>(graph.get(insertNode(value)));
            for (Edge<T> edge : edges) {
                deleteEdge(edge.getSource().getValue(), edge.getDestination().getValue());
            }
            graph.remove(insertNode(value));
            nodeHashMap.remove(value);
        }
    }

    public void deleteNode(Node<T> node) {
        deleteNode(node.getValue());
    }

    public List<Edge<T>> getEnteringEdge(T value) {
        if (hasNode(value)) {
            List<Edge<T>> edges = new ArrayList<>();
            for (Edge<T> edge : graph.get(insertNode(value))) {
                if (edge.getDestination().equals(insertNode(value)))
                    edges.add(edge);
            }
            return edges;
        } else return new ArrayList<>();
    }

    public List<Edge<T>> getEnteringEdge(Node<T> node) {
        return getEnteringEdge(node.getValue());
    }

    public List<Edge<T>> getOutgoingNode(T value) {
        if (hasNode(value)) {
            List<Edge<T>> edges = new ArrayList<>();
            for (Edge<T> edge : graph.get(insertNode(value))) {
                if (edge.getSource().equals(insertNode(value)))
                    edges.add(edge);
            }
            return edges;
        } else return new ArrayList<>();
    }

    public List<Edge<T>> getOutgoingNode(Node<T> node) {
        return getOutgoingNode(node.getValue());
    }

    public List<T> getNode(){
        List<T> list = new ArrayList<>();
        for(Map.Entry<Node<T>, List<Edge<T>>> item : graph.entrySet()) {
            list.add(item.getKey().getValue());
        }
        return list;
    }

    public List<String> getEdge(){
        List<String> list = new ArrayList<>();
        for(Map.Entry<Node<T>, List<Edge<T>>> item : graph.entrySet()) {
            for (Edge<T> edge : item.getValue()){
                if (!list.contains(edge.toString())){
                    list.add(edge.toString());
                }
            }
        }
        return list;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Map.Entry<Node<T>, List<Edge<T>>> item : graph.entrySet()) {
            s.append("Node: ").append(item.getKey()).append("\t\tEdge: ").append(item.getValue()).append("\n");
        }
        return s.toString();
    }

    public Node<T> getNode(T value) {
        for (Node<T> node : graph.keySet()) {
            if (node.getValue().hashCode() == value.hashCode()) {
                return node;
            }
        }
        return null;
    }
}