package FinalProyect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFileTxt {
    public static void main(String[] args) {
        ReadFileTxt read = new ReadFileTxt();
        UndirectedGraph<User> graph = new UndirectedGraph<>();
        UndirectedGraph<User> grap = read.createUsers(graph, "lib/src/main/resources/Github/input.txt");
        System.out.println(grap);
        System.out.println();
        System.out.println(grap.getNode(new User(100)).getValue().getTopicList());
        System.out.println(grap.getNode(new User(100)).getValue().getRepositoriesFollowList());

    }

    public UndirectedGraph<User> createUsers(UndirectedGraph<User> undirectedGraph, String path) {
        try {

            FileReader fileReader = new FileReader(path);
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
                    String line = scanner.nextLine();
                    int id = extractID(line);
                    extractTopicToLine(line, (id + "").length(), undirectedGraph, id);
                }
                iterations = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < iterations; i++) {
                    String line = scanner.nextLine();
                    int id = extractID(line);
                    extractReposToLine(line, (id + "").length(), undirectedGraph, id);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return undirectedGraph;
    }

    private UndirectedGraph<User> extractTopicToLine(String line, int beginIndex, UndirectedGraph<User> graph, int id) {
        Node<User> node = graph.getNode(new User(id));
        String lineTopic = line.substring(beginIndex);
        String[] topis = lineTopic.split(", ");
        for (int i = 0; i < topis.length; i++) {
            node.getValue().addTopic(topis[i]);
        }
        return graph;
    }

    private UndirectedGraph<User> extractReposToLine(String line, int beginIndex, UndirectedGraph<User> graph, int id) {
        Node<User> node = graph.getNode(new User(id));
        String lineRepos = line.substring(beginIndex);
        String[] repos = lineRepos.split(", ");
        for (int index = 0; index < repos.length; index++) {
            node.getValue().addRepositorieList(repos[index]);
            System.out.println(repos[index]);
        }
        return graph;
    }

    private void addRepos(UndirectedGraph<User> graph, String repos, int id) {
        Node<User> node = graph.getNode(new User(id));
        node.getValue().addRepositorieList(repos);
    }

    private void addTopic(UndirectedGraph<User> graph, String topic, int id) {
        Node<User> node = graph.getNode(new User(id));
        node.getValue().addTopic(topic);
    }

    private int extractID(String line) {
        String[] split = line.split(" ");
        return Integer.parseInt(split[0]);
    }
}
