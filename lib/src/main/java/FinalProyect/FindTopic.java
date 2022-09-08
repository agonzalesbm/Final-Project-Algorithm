package FinalProyect;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindTopic {
    private Node<User> findNode(DirectedGraph<User>directedGraph, User user) {
        Map<User, Node<User>> map = directedGraph.getNodeHashMap();
        if (map.containsKey(user)) {
            return map.get(user);
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


    private List<User> method1(DirectedGraph<User>undirectedGraph, User user, int limit) {
        Node<User>node = findNode(undirectedGraph, user);
        //List<Edge<User>> listEdge = node.getEdgeList();
        //List<User> listUser = getElementeSecondOFEdge(listEdge);
        //List<User>userGrap = converterNodeToList(undirectedGraph.getAllNodes());
        //userGrap.removeAll(listUser);
        //sortList(userGrap, user);
        //List<User>Listresult = new LinkedList<>();

        //for (int i = 0; i < limit; i++) {
        //     Listresult.add(userGrap.get(i));
        // }
        // return Listresult;
        return null;
    }

    public List<Node<User>> case1(DirectedGraph<User>directedGraph, int id, int limit) {
        User user = new User(id);
        List<Node<User>> listNotFollow = generateListFollow( user,directedGraph, limit);
        return listNotFollow;
    }

    private List<Node<User>> generateListFollow(User user, DirectedGraph<User> directedGraph, int limit) {
        Set<Node<User>> set = directedGraph.getGraph().keySet();
        List<Node<User>>list = new LinkedList<>();
        set.remove(new Node<User>(user));
        for (Node<User> node : set) {
            List<User> lisFollow = node.getValue().getUserFollowList();
            if (!lisFollow.contains(user)) {
                int comunTopic = findInComun(user.getTopicList(), node.getValue().getTopicList());
                int comunRepos = findInComun(user.getRepositoriesFollowList(), node.getValue().getRepositoriesFollowList());
                if (comunRepos >= limit || comunTopic >= limit) {
                    list.add(node);//TODO could be User

                }
            }
        }
        return list;
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

    public static void main(String[] args) throws FileNotFoundException {
        FindTopic topic = new FindTopic();
        RFile file = new RFile();
        file.createUsers();

        DirectedGraph<User> graph = file.getDirectedGraph();
        System.out.println("-->   "+ graph +" --> ");
        List<Node<User>> list = topic.case1(graph, 100, 2);
        System.out.println();
        System.out.println(graph.getGraph());
        System.out.println(list);
    }
}
