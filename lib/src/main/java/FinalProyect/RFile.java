package FinalProyect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
public class RFile {
    private DirectedGraph<User> directedGraph;
    private FileReader fileReader;
    private Scanner scanner;
    public RFile() throws FileNotFoundException {
        this.directedGraph = new DirectedGraph<>();
        this.fileReader = new FileReader("lib/src/main/resources/Github/Github.txt");
        this.scanner = new Scanner(fileReader);
    }

    public void createUsers() { // n log n
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
                continue;
            } else if (directedGraph.getNode(new User(id1)) == null && directedGraph.getNode(new User(id2)) != null) {
                Node<User> user2 = directedGraph.getNode(new User(id2));
                directedGraph.addEdge(new Node<User>(new User(id1)), user2);
                continue;
            } else if (directedGraph.getNode(new User(id1)) != null && directedGraph.getNode(new User(id2)) != null) {
                Node<User> user1 = directedGraph.getNode(new User(id1));
                Node<User> user2 = directedGraph.getNode(new User(id2));
                directedGraph.addEdge(user1, user2);
                continue;
            }
            
            directedGraph.addEdge(new Node<User>(new User(id1)),new Node<User>(new User(id2)));
        }
        iterations = Integer.parseInt(scanner.nextLine());
        topicList(iterations); // n log n
        iterations = Integer.parseInt(scanner.nextLine());
        repositoryList(iterations); //n log n
        // Node<User> user1 = directedGraph.getNode(new User(100));
        // System.out.println(user1.getValue().getTopicList());
        // System.out.println(user1.getValue().getRepositoriesFollowList());
    }

    public void topicList(int iterations) {
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

    public void repositoryList(int iterations) {
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
        scanner.close();;
    }

    public DirectedGraph<User> getDirectedGraph() {
        return directedGraph;
    }

    public DirectedGraph<User> getDirectedGraph() {
        return directedGraph;
    }
}