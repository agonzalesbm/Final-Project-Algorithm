package FinalProyect;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import FinalProyect.Graph.DirectedGraph;
import FinalProyect.Graph.Node;

public class MethodsAux1 {
    public List<User> generateListFollow(User user1, DirectedGraph<User> directedGraph, int limit) {
        Set<Node<User>> set = directedGraph.getAllNodes();
        List<User> list = new LinkedList<>();
        User user = directedGraph.getNode(user1).getValue();
        set.remove(new Node<User>(user1));
        for (Node<User> node : set) {
            List<User> lisFollow = node.getValue().getUserFollowList();
            if (!lisFollow.contains(user)) {
                int comunTopic = findInComun(user.getTopicList(), node.getValue().getTopicList());
                int comunRepos = findInComun(user.getRepositoriesFollowList(),
                node.getValue().getRepositoriesFollowList());
                if (comunRepos >= limit || comunTopic >= limit) {
                    list.add(node.getValue());
                }
            }
        }
        return list;
    }

    private int findInComun(List<String> list, List<String> listComun) {
        int cont = 0;
        for (String value : list) {
            if (listComun.contains(value)) {
                cont++;
            }
        }
        return cont;
    }

    public List<User> sortList(List<User> list, User userComparator) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int comunTopic1 = findInComun(userComparator.getTopicList(), o1.getTopicList());
                int comunTopic2 = findInComun(userComparator.getTopicList(), o2.getTopicList());
                int comunRepos1 = findInComun(userComparator.getRepositoriesFollowList(),
                        o1.getRepositoriesFollowList());
                int comunRepos2 = findInComun(userComparator.getRepositoriesFollowList(),
                        o2.getRepositoriesFollowList());

                if (comunTopic1 > comunTopic2) {
                    return -1;
                }
                if (comunTopic1 == comunTopic2) {
                    if (comunRepos1 > comunRepos2) {
                        return -1;
                    }
                    if (comunRepos1 == comunRepos2) {
                        return 0;
                    }
                    return 1;
                }
                return 1;
            }
        });
        return list;
    }
}
