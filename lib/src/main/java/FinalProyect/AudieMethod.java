package FinalProyect;

import java.io.FileNotFoundException;
import java.util.List;

public class AudieMethod {
    public List<User> method1(DirectedGraph<User>directedGraph, int id, int limit){
        MethodsAux1 m = new MethodsAux1();
        User user = directedGraph.getNode(new User(id)).getValue();
        List<User> listNotFollow = m.generateListFollow( user,directedGraph, limit);
        m.sortList(listNotFollow, user);
        return listNotFollow;
    }

    public void method2(int N) throws FileNotFoundException{
        RFile rFile = new RFile();
        rFile.createUsers();
        Node<User> user = rFile.getDirectedGraph().getNode(new User(100));
        Node<User> user2 = rFile.getDirectedGraph().getNode(new User(10012));

        System.out.println(user.getValue().getUserFollowList());
        System.out.println(user2.getValue().getUserFollowList());
        //System.out.println(rFile.getDirectedGraph());
    }

    public void method3(){
    }

    public static void main(String[] args) throws FileNotFoundException {
        AudieMethod audieMethod = new AudieMethod();
        audieMethod.method2(3);
    }
}
