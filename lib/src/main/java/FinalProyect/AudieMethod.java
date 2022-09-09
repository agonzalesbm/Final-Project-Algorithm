package FinalProyect;

import java.io.FileNotFoundException;
import java.util.*;

public class AudieMethod<T> {


    public void method1(){
    }

    public void method2(int N) throws FileNotFoundException{
        RFile rFile = new RFile();
        rFile.createUsers();
        Node<User> user = rFile.getDirectedGraph().getNode(new User(102));

        System.out.println(rFile.getDirectedGraph().getAdjList().size());
        System.out.println(rFile.getDirectedGraph());
    }

    public List<User> method3( User user1, int limit,DirectedGraph<User>directedGraph) {
        Node<User> user = directedGraph.getNode(user1);
        List<User> listUserFollowing = listFollowing(user, directedGraph);
        List<User>listFinal = new LinkedList<>();
        managUserList(limit-1, 0, user.getValue().getUserFollowList(), listFinal);
        listFinal.removeAll(listUserFollowing);
        listFinal.remove(user.getValue());
        System.out.println(listFinal);
        return listFinal;
    }
    private void managUserList(int limit, int cont, List<User>users, List<User>listFinal) {
        if (limit == cont) {
            listFinal.addAll(users);
            return;
        }
        for (User user : users) {
            managUserList(limit, cont + 1, user.getUserFollowList(),listFinal);
        }
    }
    private List<User> listFollowing(Node<User> user,DirectedGraph<User>directedGraph) {
        List<Edge<User>> edges = directedGraph.getAdjList().get(user);
        List<User>listFollowing = new LinkedList<>();
        for (Edge<User> edge : edges) {
            listFollowing.add(edge.getDestination().getValue());
        }
        return listFollowing;
    }

    public static void main(String[] args) throws FileNotFoundException {
        RFile rFile = new RFile();
        rFile.createUsers();
        AudieMethod audieMethod = new AudieMethod();
        User user1 = new User(100);
        DirectedGraph<User> followers = rFile.getDirectedGraph();
        audieMethod.method3(user1,2,followers);

    }
}
