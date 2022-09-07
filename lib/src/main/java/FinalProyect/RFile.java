package FinalProyect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RFile {
    private Node<User> findNode(UndirectedGraph<User>undirectedGraph, User user) {
        Set<Node<User>> list = undirectedGraph.getAllNodes();
        for (Node<User> node : list) {
            if (node.getValue().hashCode() == user.hashCode()) {
                return node;
            }
        }
        return null;
    }

    private List<User> method1(UndirectedGraph<User>undirectedGraph, User user, int limit) {
        Node<User>node = findNode(undirectedGraph, user);
        List<Edge<User>> listEdge = node.getEdgeList();
        List<User> listUser = getElementeSecondOFEdge(listEdge);
        List<User>userGrap = converterNodeToList(undirectedGraph.getAllNodes());
        userGrap.removeAll(listUser);
        sortList(userGrap);
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

    public List<User> sortList(List<User>list) {
        Collections.sort(list,new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int comunTopic1 = findInComun(o1.getTopicList(), o2.getTopicList());
                int comunTopic2 = findInComun(o2.getTopicList(), o1.getTopicList());
                int comunRepos1 = findInComun(o1.getRepositoriesFollowList(), o2.getRepositoriesFollowList());
                int comunRepos2 = findInComun(o2.getRepositoriesFollowList(), o1.getRepositoriesFollowList());

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
        UndirectedGraph<User> undirectedGraph = new UndirectedGraph<>();
        File file = new File("lib/src/main/resources/Github/Github.txt");
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);
        User audie = new User(Integer.parseInt(scanner.nextLine()));
        undirectedGraph.addNode(new Node<User>(audie));

        while (scanner.hasNext()) {
            int iterations = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < iterations; i++) {
                String[] s = scanner.nextLine().split(" ");
                int user1 = Integer.parseInt(s[0]);
                int user2 = Integer.parseInt(s[1]);
                undirectedGraph.addEdge(new Node<User>(new User(user1)), new Node<User>(new User(user2)));
            }

            iterations = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < iterations; i++) {
                String[] s = scanner.nextLine().split(" ");
                int user1 = Integer.parseInt(s[0]);
                String[] newS = new String[s.length - 1];
                System.arraycopy(s, 1, newS, 0, newS.length);
                String[] splitNewS = newS[0].split(",");
                for (int j = 0; j < splitNewS.length; j++) {
                    for (Map.Entry<Node<User>, List<Edge<User>>> entry : undirectedGraph.getAdjList().entrySet()) {
                        if (entry.getKey().getValue().getId() == user1) {
                            entry.getKey().getValue().getTopicList().add(splitNewS[j]);
                            break;
                        }
                    }
                }
            }

            iterations = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < iterations; i++) {
                String[] s = scanner.nextLine().split(" ");
                int user1 = Integer.parseInt(s[0]);
                String[] newS = new String[s.length - 1];
                System.arraycopy(s, 1, newS, 0, newS.length);
                String[] splitNewS = newS[0].split(",");
                for (int j = 0; j < splitNewS.length; j++) {
                    for (Map.Entry<Node<User>, List<Edge<User>>> entry : undirectedGraph.getAdjList().entrySet()) {
                        if (entry.getKey().getValue().getId() == user1) {
                            entry.getKey().getValue().getRepositoriesFollowList().add(splitNewS[j]);
                            User user = entry.getKey().getValue();
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(undirectedGraph);
    }
}
