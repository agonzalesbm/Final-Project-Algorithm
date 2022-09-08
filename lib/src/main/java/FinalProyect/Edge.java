package FinalProyect;

public  class Edge<T> {
    private Node<T> destination;
    private Node<T> source;

    public Edge(Node<T> source, Node<T> destination) {
        evaluateSourceAndGivenValues(source, destination);
        this.destination = destination;
        this.source = source;
    }

    public Node<T> getDestination() {
        return destination;
    }

    public void setDestination(Node<T> destination) {
        this.destination = destination;
    }

    public Node<T> getSource() {
        return source;
    }

    public void setSource(Node<T> source) {
        this.source = source;
    }    

    @Override
    public String toString() {
        return "[source=" + source + ", destination=" + destination + "]\n";
    }

    private void evaluateSourceAndGivenValues(Node<T> source, Node<T> destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("INVALID ARGUMENTS");
        }
    }

}