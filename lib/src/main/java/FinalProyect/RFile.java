package FinalProyect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RFile {
    public static void main(String[] args) throws FileNotFoundException {
        UndirectedGraph<User> undirectedGraph = new UndirectedGraph<>();
        FileReader fileReader = new FileReader(Path.DATA.getGithub());
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
                String[] s = scanner.nextLine().split(" ");
                int user1 = Integer.parseInt(s[0]);
                String[] newS = new String[s.length - 1];
                System.arraycopy(s, 1, newS, 0, newS.length);
                String[] splitNewS = newS[0].split(",");
                for (int j = 0; j < splitNewS.length; j++) {
                    for (Map.Entry<Node<User>, List<Edge<User>>> entry : undirectedGraph.getAdjList().entrySet()) {
                        if (entry.getKey().getValue().getId() == user1) {
                            entry.getKey().getValue().getTopicList().add(splitNewS[j]);
                            break;
                        }
                    }
                }
            }

            iterations = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < iterations; i++) {
                String[] s = scanner.nextLine().split(" ");
                int user1 = Integer.parseInt(s[0]);
                String[] newS = new String[s.length - 1];
                System.arraycopy(s, 1, newS, 0, newS.length);
                String[] splitNewS = newS[0].split(",");
                for (int j = 0; j < splitNewS.length; j++) {
                    for (Map.Entry<Node<User>, List<Edge<User>>> entry : undirectedGraph.getAdjList().entrySet()) {
                        if (entry.getKey().getValue().getId() == user1) {
                            entry.getKey().getValue().getRepositoriesFollowList().add(splitNewS[j]);
                            User user = entry.getKey().getValue();
                            break;
                        }
                    }
                }
            }
        }
    }
}
