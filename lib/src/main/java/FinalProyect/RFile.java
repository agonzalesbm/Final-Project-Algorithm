package FinalProyect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RFile {
    UndirectedGraph<User> undirectedGraph;
    FileReader fileReader;
    Scanner scanner;

    public RFile() throws FileNotFoundException {
        this.undirectedGraph = new UndirectedGraph<>();
        this.fileReader = new FileReader(Path.DATA.getGithub());
        this.scanner = new Scanner(fileReader);
    }

    public void createUsers() throws FileNotFoundException { // n log n        
        User audie = new User(Integer.parseInt(scanner.nextLine()));
        undirectedGraph.addNode(new Node<User>(audie));

        int iterations = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < iterations; i++) { // n
            String[] s = scanner.nextLine().split(" ");
            int user1 = Integer.parseInt(s[0]);
            int user2 = Integer.parseInt(s[1]);
            undirectedGraph.addEdge(new Node<User>(new User(user1)), new Node<User>(new User(user2)));
        }

        iterations = Integer.parseInt(scanner.nextLine());
        topicList(iterations); // n log n
        iterations = Integer.parseInt(scanner.nextLine());
        repositorieList(iterations); //n log n
        Node<User> user1 = undirectedGraph.getNode(new User(2002));
        System.out.println(user1.getValue().getTopicList());
        System.out.println(user1.getValue().getRepositoriesFollowList());
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
            Node<User> nodeUser = undirectedGraph.getNode(new User(userId));
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

    public void repositorieList(int iterations) {        
        if (iterations == 0) {
            return;
        }
        String nextLine = scanner.nextLine();
        if (iterations > 0) {
            String[] repositories = nextLine.split(", ");
            String[] getUser = repositories[0].split(" ");
            int userId = Integer.parseInt(getUser[0]);
            Node<User> nodeUser = undirectedGraph.getNode(new User(userId));
            String firstRepo = "";
            if (getUser.length > 2) {
                firstRepo = getUser[1] + " " + getUser[2];
            } else {
                firstRepo = getUser[1];
            }
            nodeUser.getValue().addRepositorie(firstRepo);
            if (repositories.length == 1) {                
                iterations--;
                repositorieList(iterations);
                return;
            }
            for (int i = 1; i < repositories.length; i++) { // n
                nodeUser.getValue().addRepositorie(repositories[i]);
            }

            iterations--;            
            repositorieList(iterations); // log n
            return;
        }
        
    }

    public static void main(String[] args) throws FileNotFoundException {
        RFile rFile = new RFile();
        rFile.createUsers();
        
        // String string = "100 Java";
        // String[] s = string.split(", ");
        // String[] s = string.split(", ");
        // System.out.println(Arrays.toString(s));

    }

    // public void followersAudieFollows(int N) {
    //     for (Map.Entry<Node<User>, List<Edge<User>>> entry : undirectedGraph.getAdjList().entrySet()) {
    //         System.out.println(entry.getValue());
    //     }
    // }
}
