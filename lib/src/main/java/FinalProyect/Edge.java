package FinalProyect;

public  class Edge<T> {
    final Node<T> source;
    final Node<T> destination;

    public Edge(Node<T> source, Node<T> destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString(){
        return source + "->" + destination + "\t";
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Edge)) return false;
        return (((Edge<?>) o).source.equals(this.source) && ((Edge<?>) o).destination.equals(this.destination)) || o == this;
    }

    @Override
    public int hashCode(){
        return this.source.hashCode()+this.destination.hashCode();
    }
}