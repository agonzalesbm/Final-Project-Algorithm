package FinalProyect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import FinalProyect.Graph.DirectedGraph;
import FinalProyect.Graph.Node;

public class RFile {
    private DirectedGraph<User> directedGraph;
    private FileReader fileReader;
    private Scanner scanner;

    public RFile(String path) throws FileNotFoundException {
        this.directedGraph = new DirectedGraph<>();
        this.fileReader = new FileReader(path);
        this.scanner = new Scanner(fileReader);
        createUsers();
    }

    private void createUsers() { // n log n
        User audie = new User(Integer.parseInt(scanner.nextLine()));
        directedGraph.addNode(new Node<User>(audie));
        int iterations = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < iterations; i++) { // n
            String[] s = scanner.nextLine().split(" ");
            int id1 = Integer.parseInt(s[0]);
            int id2 = Integer.parseInt(s[1]);

            if (directedGraph.getNode(new User(id1)) != null && directedGraph.getNode(new User(id2)) == null) {
                Node<User> user1 = directedGraph.getNode(new User(id1));
                directedGraph.addEdge(user1, new Node<User>(new User(id2)));
                User user2 = directedGraph.getNode(new User(id2)).getValue();
                user2.addUserFollowList(user1.getValue());
                user1.getValue().addUsersThatUserFollow(user2);
                continue;
            } else if (directedGraph.getNode(new User(id1)) == null && directedGraph.getNode(new User(id2)) != null) {
                Node<User> user2 = directedGraph.getNode(new User(id2));
                directedGraph.addEdge(new Node<User>(new User(id1)), user2);

                User user1 = directedGraph.getNode(new User(id1)).getValue();
                user2.getValue().addUserFollowList(user1);
                user1.addUsersThatUserFollow(user2.getValue());
                continue;
            } else if (directedGraph.getNode(new User(id1)) != null && directedGraph.getNode(new User(id2)) != null) {
                Node<User> user1 = directedGraph.getNode(new User(id1));
                Node<User> user2 = directedGraph.getNode(new User(id2));
                directedGraph.addEdge(user1, user2);

                user2.getValue().addUserFollowList(user1.getValue());
                user1.getValue().addUsersThatUserFollow(user2.getValue());
                continue;
            }

            directedGraph.addEdge(new Node<User>(new User(id1)), new Node<User>(new User(id2)));
            Node<User> user1 = directedGraph.getNode(new User(id1));
            Node<User> user2 = directedGraph.getNode(new User(id2));
            user2.getValue().addUserFollowList(user1.getValue());

        }
        iterations = Integer.parseInt(scanner.nextLine());
        topicList(iterations); // n log n
        iterations = Integer.parseInt(scanner.nextLine());
        repositoryList(iterations); // n log n
    }

    private void topicList(int iterations) {
        if (iterations == 0) {
            return;
        }
        String nextLine = scanner.nextLine();
        if (iterations > 0) {
            String[] topics = nextLine.split(", ");
            String[] getUser = topics[0].split(" ");
            int userId = Integer.parseInt(getUser[0]);
            Node<User> nodeUser = directedGraph.getNode(new User(userId));
            String firstTopic = "";
            if (getUser.length > 2) {
                firstTopic = getUser[1] + " " + getUser[2];
            } else {
                firstTopic = getUser[1];
            }
            nodeUser.getValue().addTopic(firstTopic);
            if (topics.length == 1) {
                iterations--;
                topicList(iterations);
                return;
            }
            for (int i = 1; i < topics.length; i++) { // n
                nodeUser.getValue().addTopic(topics[i]);
            }
            iterations--;
            topicList(iterations); // log n
            return;
        }
    }

    private void repositoryList(int iterations) {
        if (iterations == 0) {
            return;
        }
        String nextLine = scanner.nextLine();
        if (iterations > 0) {
            String[] repositories = nextLine.split(", ");
            String[] getUser = repositories[0].split(" ");
            int userId = Integer.parseInt(getUser[0]);
            Node<User> nodeUser = directedGraph.getNode(new User(userId));
            String firstRepo = "";
            if (getUser.length > 2) {
                firstRepo = getUser[1] + " " + getUser[2];
            } else {
                firstRepo = getUser[1];
            }
            nodeUser.getValue().addRepositorie(firstRepo);
            if (repositories.length == 1) {
                iterations--;
                repositoryList(iterations);
                return;
            }
            for (int i = 1; i < repositories.length; i++) { // n
                nodeUser.getValue().addRepositorie(repositories[i]);
            }
            iterations--;
            repositoryList(iterations); // log n
            return;
        }
        scanner.close();
        ;
    }

    public DirectedGraph<User> getDirectedGraph() {
        return directedGraph;
    }

}