package FinalProyect;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.TreeMultiset;
import com.google.common.collect.Multiset.Entry;

import FinalProyect.Graph.DirectedGraph;
import FinalProyect.Graph.Edge;
import FinalProyect.Graph.Node;

public class AudieMethod {
    public List<User> method1(DirectedGraph<User> directedGraph, int id, int N) {
        MethodsAux1 m = new MethodsAux1();
        User user = directedGraph.getNode(new User(id)).getValue();
        List<User> listNotFollow = m.generateListFollow(user, directedGraph, N);
        m.sortList(listNotFollow, user);
        System.out.println(listNotFollow);
        return listNotFollow;
    }

    public void method2(DirectedGraph<User> directedGraph, int N) throws FileNotFoundException { // O(n^2)
        var count = 0;
        List<Node<User>> listUsers = new ArrayList<>(directedGraph.getAllNodes());
        User audie = directedGraph.getNode(new User(100)).getValue();
        List<User> audieUsersToFollow = audie.getUsersThatUserFollow();
        List<User> result = new ArrayList<>();

        for (int i = 1; i < listUsers.size(); i++) { // O(n^2)
            List<User> list = listUsers.get(i).getValue().getUsersThatUserFollow();
            count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (audieUsersToFollow.contains(list.get(j))) {
                    count++;
                    if (count == N) {
                        result.add(listUsers.get(i).getValue());
                        count = 0;
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }

    public List<User> method3(User user1, int N, DirectedGraph<User> directedGraph) { // O(n log n)
        Node<User> user = directedGraph.getNode(user1);
        List<User> listUserFollowing = listFollowing(user, directedGraph);
        List<User> listFinal = new LinkedList<>();
        managUserList(N - 1, 0, user.getValue().getUserFollowList(), listFinal); // O(n log n)
        listFinal.removeAll(listUserFollowing);
        listFinal.remove(user.getValue());
        System.out.println(listFinal);
        return listFinal;
    }

    private void managUserList(int N, int cont, List<User> users, List<User> listFinal) { // O(n log n)
        if (N == cont) {
            listFinal.addAll(users);
            return;
        }
        for (User user : users) { // O(n)
            managUserList(N, cont + 1, user.getUserFollowList(), listFinal);
        }
    }

    private List<User> listFollowing(Node<User> user, DirectedGraph<User> directedGraph) { // O(n)
        List<Edge<User>> edges = directedGraph.getAdjList().get(user);
        List<User> listFollowing = new LinkedList<>();
        for (Edge<User> edge : edges) {
            listFollowing.add(edge.getDestination().getValue());
        }
        return listFollowing;
    }

    private String mostPopularTopic(DirectedGraph<User> directedGraph) throws FileNotFoundException { // O(n^2)
        LinkedList<String> topicList = (LinkedList<String>) getTopicList(directedGraph); // O(n^2)
        TreeMultiset<String> topics = TreeMultiset.create();
        for (String string : topicList) { // O(n)
            topics.add(string);
        }
        Set<Entry<String>> setTopics = topics.entrySet();
        LinkedList<Entry<String>> linkedList = new LinkedList<>(setTopics);
        Collections.sort(linkedList, new Comparator<Entry<String>>() {
            @Override
            public int compare(Entry<String> o1, Entry<String> o2) {
                if (o1.getCount() == o2.getCount()) {
                    return 0;
                }
                return o1.getCount() > o2.getCount() ? -1 : 1;
            }
        });

        return linkedList.get(0).toString();
    }

    private List<String> getTopicList(DirectedGraph<User> directedGraph) throws FileNotFoundException { // O(n^2)
        List<Node<User>> userList = new ArrayList<>(directedGraph.getAllNodes());
        List<String> topicList = new LinkedList<>();
        for (int i = 0; i < userList.size(); i++) {
            LinkedList<String> list = userList.get(i).getValue().getTopicList();
            for (String string : list) {
                topicList.add(string);
            }
        }
        return topicList;
    }

    private String mostPopularRepo(DirectedGraph<User> directedGraph) throws FileNotFoundException { // O(n^2)
        LinkedList<String> repoList = (LinkedList<String>) getReposList(directedGraph);
        TreeMultiset<String> repos = TreeMultiset.create();
        for (String string : repoList) {
            repos.add(string);
        }
        Set<Entry<String>> setRepos = repos.entrySet();
        LinkedList<Entry<String>> linkedList = new LinkedList<>(setRepos);
        Collections.sort(linkedList, new Comparator<Entry<String>>() {
            @Override
            public int compare(Entry<String> o1, Entry<String> o2) {
                if (o1.getCount() == o2.getCount()) {
                    return 0;
                }
                return o1.getCount() > o2.getCount() ? -1 : 1;
            }
        });
        return linkedList.get(0).toString();
    }

    private List<String> getReposList(DirectedGraph<User> directedGraph) throws FileNotFoundException { // O(n^2)
        List<Node<User>> userList = new ArrayList<>(directedGraph.getAllNodes());
        List<String> reposList = new LinkedList<>();
        for (int i = 0; i < userList.size(); i++) {
            LinkedList<String> list = userList.get(i).getValue().getRepositoriesFollowList();
            for (String string : list) {
                reposList.add(string);
            }
        }
        return reposList;
    }

    public void method4(DirectedGraph<User> directedGraph) throws FileNotFoundException {
        System.out.println("Most popular topic: " + mostPopularTopic(directedGraph) + ", "  + "Most popular repositorie: " + mostPopularRepo(directedGraph));
    }
}
