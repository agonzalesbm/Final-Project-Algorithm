package FinalProyect;

public class Main {
    public static void main(String[] args){
        DirectedGraph<String> first = new DirectedGraph<String>();
        Node<String> audie = new Node<>("100");
        first.addNode("100");
        first.addNode("10011");
        first.addNode("10012");
        first.addEdge("100", "102");
        first.addEdge("10012", "100");
        first.addEdge("10011", "10012");
        first.addEdge("102", "103");
        first.addEdge("2000", "200");
        first.addEdge("100", "10200");
        System.out.println(first.toString());
        System.out.println(first.getEnteringEdge("100"));
        System.out.println(first.getOutgoingNode("100"));
    }
}
