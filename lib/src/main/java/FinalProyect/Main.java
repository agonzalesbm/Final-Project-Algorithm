package FinalProyect;

import java.io.File;
import java.io.FileNotFoundException;

import FinalProyect.Graph.DirectedGraph;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("------------------------------------- METHOD 1 -------------------------------------");
        String path = "lib" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Github" + File.separator;
        RFile rFile = new RFile(path + "caseMethod1.txt");
        DirectedGraph<User> directedGraph = rFile.getDirectedGraph();
        AudieMethod audieMethod = new AudieMethod();
        audieMethod.method1(directedGraph, 100, 3);
        audieMethod.method1(directedGraph, 2000, 2);
        audieMethod.method1(directedGraph, 2002, 1);
        System.out.println("-------------------------------------------------------------------------------------");

        System.out.println("------------------------------------- METHOD 2 -------------------------------------");
        rFile = new RFile(path + "caseMethod2.txt");
        directedGraph = rFile.getDirectedGraph();
        audieMethod.method2(directedGraph, 1);
        audieMethod.method2(directedGraph, 2);
        audieMethod.method2(directedGraph, 3);
        audieMethod.method2(directedGraph, 10);
        System.out.println("-------------------------------------------------------------------------------------");

        System.out.println("------------------------------------- METHOD 3 -------------------------------------");
        rFile = new RFile(path + "caseOneMethod3.txt");
        directedGraph = rFile.getDirectedGraph();
        User user1 = new User(100);
        audieMethod.method3(user1, 2, directedGraph);
        user1 = new User(2);
        audieMethod.method3(user1, 2, directedGraph);
        System.out.println("-------------------------------------------------------------------------------------");
        rFile = new RFile(path + "caseTwoMethod3.txt");
        directedGraph = rFile.getDirectedGraph();
        user1 = new User(200);
        audieMethod.method3(user1, 2, directedGraph);
        System.out.println("-------------------------------------------------------------------------------------");

        System.out.println("------------------------------------- METHOD 4 -------------------------------------");
        rFile = new RFile(path + "caseMethod4.txt");
        directedGraph = rFile.getDirectedGraph();
        audieMethod.method4(directedGraph);
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
