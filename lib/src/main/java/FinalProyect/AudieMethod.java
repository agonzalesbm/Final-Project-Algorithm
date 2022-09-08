package FinalProyect;

import java.io.FileNotFoundException;

public class AudieMethod {
    public void method1(){
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
