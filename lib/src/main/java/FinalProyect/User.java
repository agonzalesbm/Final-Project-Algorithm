package FinalProyect;

import java.util.LinkedList;

public class User {
    private int id;
    private LinkedList<User> userFollowList;
    private LinkedList<String> topicList;
    private LinkedList<String> repositoriesFollowList;
    
    public User(int id) {
        this.id = id;
        this.userFollowList = new LinkedList<>();
        this.topicList = new LinkedList<>();
        this.repositoriesFollowList = new LinkedList<>();
    }

    public boolean addUserFollowList(User user) {
        if (!userFollowList.contains(user)) {
            userFollowList.add(user);
            return true;
        }

        return false;
    }

    public boolean addTopic(String s) {
        if (!topicList.contains(s)) {
            topicList.add(s);
            return true;
        }

        return false;
    }

    public boolean addRepositorie(String s) {
        if (!repositoriesFollowList.contains(s)) {
            repositoriesFollowList.add(s);
            return true;
        }

        return false;
    }

    public int getId() {
        return id;
    }

    public LinkedList<User> getUserFollowList() {
        return userFollowList;
    }

    public LinkedList<String> getTopicList() {
        return topicList;
    }

    public LinkedList<String> getRepositoriesFollowList() {
        return repositoriesFollowList;
    }

    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString() {
        return id + "";
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.hashCode() == this.hashCode();
    }
}
