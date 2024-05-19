import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tree<T> {
    private TreeNode<T> root;

    public Tree(T rootData) {
        root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }
    
    public void setRoot(TreeNode<T> rootData) {
    	root = rootData;
    }
    

    
    //Reading and writing to file
    public void writeTreeToFile(TreeNode<T> root, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writeNode(writer, root, 0);
        }
    }

    private void writeNode(BufferedWriter writer, TreeNode<T> node, int depth) throws IOException {
        for (int i = 0; i < depth; i++) {
            writer.write("\t"); // Use tabs to represent depth
        }
        writer.write(node.getData().toString());
        writer.newLine();
        for (TreeNode<T> child : node.getChildren()) {
            writeNode(writer, child, depth + 1);
        }
    }

    public  TreeNode<T> readTreeFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            TreeNode<T> root = null;
            TreeNode<T> currentNode = null;
            int currentDepth = -1;

            while ((line = reader.readLine()) != null) {
                int depth = countLeadingTabs(line);
                String data = line.trim();

                // Assume data is comma-separated
                String[] parts = data.split(",");

                int value = Integer.parseInt(parts[0]);
                String player = parts[1];
                int bigX = Integer.parseInt(parts[2]);
                int bigY = Integer.parseInt(parts[3]);
                int smallX = Integer.parseInt(parts[4]);
                int smallY = Integer.parseInt(parts[5]);
                int valueType = Integer.parseInt(parts[6]);

                Square square = new Square(value, player, bigX, bigY, smallX, smallY,valueType);

                TreeNode<T> newNode = new TreeNode<>(null);
                newNode.setData((T) square);
                

                if (depth == 0) {
                    root = newNode;
                    currentNode = root;
                    currentDepth = 0;
                } else if (depth > currentDepth) {
                    currentNode.addChild(newNode);
                    currentNode = newNode;
                    currentDepth = depth;
                } else {
                    while (depth <= currentDepth && currentNode.getParent() != null) {
                        currentNode = currentNode.getParent();
                        currentDepth--;
                    }
                    currentNode.addChild(newNode);
                    currentNode = newNode;
                    currentDepth = depth;
                }
            }

            return root;
        }
    }
    
    public  TreeNode<T> readBoardsFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            TreeNode<T> root = null;
            TreeNode<T> currentNode = null;
            int currentDepth = -1;

            while ((line = reader.readLine()) != null) {
                int depth = countLeadingTabs(line);
                String data = line.trim();

                // Assume data is comma-separated
                String[] parts = data.split(",");
                
                int length = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);
                int nextX = Integer.parseInt(parts[2]);
                int nextY = Integer.parseInt(parts[3]);
                String boardCurrentMove = parts[4];

                BoardState boardState = new BoardState(length,value, nextX, nextY,boardCurrentMove);
           

                TreeNode<T> newNode = new TreeNode<>(null);
                newNode.setData((T) boardState);
                

                if (depth == 0) {
                    root = newNode;
                    currentNode = root;
                    currentDepth = 0;
                } else if (depth > currentDepth) {
                    currentNode.addChild(newNode);
                    currentNode = newNode;
                    currentDepth = depth;
                } else {
                    while (depth <= currentDepth && currentNode.getParent() != null) {
                        currentNode = currentNode.getParent();
                        currentDepth--;
                    }
                    currentNode.addChild(newNode);
                    currentNode = newNode;
                    currentDepth = depth;
                }
            }

            return root;
        }
    }

    private int countLeadingTabs(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == '\t') {
            count++;
        }
        return count;
    }
    
    
    
    
}