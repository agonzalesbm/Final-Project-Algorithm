package FinalProyect;

import java.util.LinkedList;
import java.util.List;

public class Method {

    public List<Integer> getFollowersByGrade(User user, int grade){

        List<User> followers;
        List<Integer> idList = new LinkedList<>();
        followers = user.getUserFollowList();
        for (int i = 0; i < followers.size(); i++) {
            for (int j = 0; j < followers.size(); j++) {
                System.out.println("tu no te llamas Moon");
                idList.add(user.getId());
            }
        }
        return idList;
    }

}

