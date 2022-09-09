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

public class AudieMethod {
    public void method1(){
    }

    public void generateFollowList(int id) {
        
    }

    public void method2(int N) throws FileNotFoundException{
        RFile rFile = new RFile();
        rFile.createUsers();
        var count = 0;
        List<Node<User>> listUsers = new ArrayList<>(rFile.getDirectedGraph().getAllNodes());
        User audie = rFile.getDirectedGraph().getNode(new User(100)).getValue();
        List<User> audieUsersToFollow = audie.getUsersThatUserFollow();
        List<User> result = new ArrayList<>();

        for (int i = 1; i < listUsers.size(); i++) {
            List<User> list = listUsers.get(i).getValue().getUsersThatUserFollow();
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

    public void method3(){
    }

    public String mostPopularTopic() throws FileNotFoundException {
        LinkedList<String> topicList = (LinkedList<String>) getTopicList();
        TreeMultiset<String> topics = TreeMultiset.create();
        for (String string : topicList) {
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
                return o1.getCount() > o2.getCount()?-1:1;
            }
        });

        return linkedList.get(0).toString();
    }

    public List<String> getTopicList() throws FileNotFoundException {
        RFile rFile = new RFile();
        rFile.createUsers();
        List<Node<User>> userList = new ArrayList<>(rFile.getDirectedGraph().getAllNodes());
        List<String> topicList = new LinkedList<>();
        for (int i = 0; i < userList.size(); i++) {
            LinkedList<String> list = userList.get(i).getValue().getTopicList();
            for (String string : list) {
                topicList.add(string);
            }
        }
        return topicList;
    }

    public String mostPopularRepo() throws FileNotFoundException {
        LinkedList<String> repoList = (LinkedList<String>) getReposList();
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
                return o1.getCount() > o2.getCount()?-1:1;
            }
        });
        return linkedList.get(0).toString();
    }
}
