package FinalProyect;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindTopic {
    private Node<User> findNode(UndirectedGraph<User>undirectedGraph, User user) {
        Set<Node<User>> list = undirectedGraph.getAllNodes();
        for (Node<User> node : list) {
            if (node.getValue().hashCode() == user.hashCode()) {
                return node;
            }
        }
        return null;
    }

public List<User> getFollowersByGrade(User user, int grade){
    List<User> idList;
    idList = user.getUserFollowList();
    for (int i = 0; i < idList.size(); i++) {

        for (int j = 0; j < grade-1; j++) {
            
        }
    }
    return idList;
}


    private List<User> method1(UndirectedGraph<User>undirectedGraph, User user, int limit) {
        Node<User>node = findNode(undirectedGraph, user);
        List<Edge<User>> listEdge = node.getEdgeList();
        List<User> listUser = getElementeSecondOFEdge(listEdge);
        List<User>userGrap = converterNodeToList(undirectedGraph.getAllNodes());
        userGrap.removeAll(listUser);
        sortList(userGrap, user);
        List<User>Listresult = new LinkedList<>();

        for (int i = 0; i < limit; i++) {
            Listresult.add(userGrap.get(i));
        }
        return Listresult;
    }

    private List<User> converterNodeToList(Set<Node<User>>list) {
        List<User>users = new LinkedList<>();
        for (Node<User> node : list) {
            users.add(node.getValue());
        }
        return users;
    }
    private int findInComun(List<String>list, List<String>listComun) {
        int cont = 0;
        for (String value : list) {
            if (listComun.contains(value)) {
                cont++;
            }
        }
        return cont;
    }

    private List<User> getElementeSecondOFEdge(List<Edge<User>>edges) {
        List<User>users = new LinkedList<>();
        for (Edge<User>edge : edges) {
            User user = edge.getDestination().getValue();
            users.add(user);
        }
        return users;
    }

    public List<User> sortList(List<User>list, User userComparator) {
        Collections.sort(list,new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int comunTopic1 = findInComun(userComparator.getTopicList(), o1.getTopicList());
                int comunTopic2 = findInComun(userComparator.getTopicList(), o2.getTopicList());
                int comunRepos1 = findInComun(userComparator.getRepositoriesFollowList(), o1.getRepositoriesFollowList());
                int comunRepos2 = findInComun(userComparator.getRepositoriesFollowList(), o2.getRepositoriesFollowList());

                if (comunTopic1 > comunTopic2) {
                    return 1;
                }
                if (comunTopic1 == comunTopic2) {
                    if (comunRepos1 > comunRepos2) {
                        return 1;
                    }
                    if (comunRepos1 == comunRepos2) {
                        return 0;
                    }
                    return -1;
                }
                return -1;
            }
        });
        return list;
    }
}
