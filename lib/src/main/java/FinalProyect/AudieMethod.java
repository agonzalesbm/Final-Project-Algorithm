package FinalProyect;

import java.io.FileNotFoundException;
import java.util.List;

public class AudieMethod {
    public List<User> method1(DirectedGraph<User>directedGraph, int id, int limit){
        MethodsAux1 m = new MethodsAux1();
        User user = directedGraph.getNode(new User(id)).getValue();
        List<User> listNotFollow = m.generateListFollow( user,directedGraph, limit);
        m.sortList(listNotFollow, user);
        return m.selectionToLimit(listNotFollow, limit);
    }

    public void method2(int N) throws FileNotFoundException{
        
    }

    public void method3(){
    }

}
