package FinalProyect;

import java.util.LinkedList;

public class User {
    private int id;
    private LinkedList<User> userFollowList;
    private LinkedList<User> usersThatUserFollow;
    private LinkedList<String> topicList;
    private LinkedList<String> repositoriesFollowList;
    
    public User(int id) {
        this.id = id;
        this.userFollowList = new LinkedList<>();
        this.usersThatUserFollow = new LinkedList<>();
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

    public boolean addUsersThatUserFollow(User user) {
        if (!usersThatUserFollow.contains(user)) {
            usersThatUserFollow.add(user);
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

    public LinkedList<User> getUsersThatUserFollow() {
        return usersThatUserFollow;
    }

    public LinkedList<String> getTopicList() {
        return topicList;
    }

    public LinkedList<String> getRepositoriesFollowList() {
        return repositoriesFollowList;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (repositoriesFollowList == null) {
            if (other.repositoriesFollowList != null)
                return false;
        } else if (!repositoriesFollowList.equals(other.repositoriesFollowList))
            return false;
        if (topicList == null) {
            if (other.topicList != null)
                return false;
        } else if (!topicList.equals(other.topicList))
            return false;
        if (userFollowList == null) {
            if (other.userFollowList != null)
                return false;
        } else if (!userFollowList.equals(other.userFollowList))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString() {
        return id + "";
    }    
}
