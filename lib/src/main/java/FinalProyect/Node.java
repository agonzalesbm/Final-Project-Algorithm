package FinalProyect;

public class Node<T> {
    private final T value;

    public Node(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Node)) return false;
        return ((Node<?>) o).value.equals(this.value) || o == this;
    }

    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return " " + this.value;
    }

    public T getValue() {
        return value;
    }
}
