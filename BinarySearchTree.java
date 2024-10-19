import java.util.*;

class Node { // Stores music
    String music;
    Node left, right;

    public Node(String music) {
        this.music = music;
        left = right = null;
    }
}

class BST {
    private Node root;

    public BST() {
        root = null;
    }

    private Node insertRec(Node root, String music) {
        if (root == null) {
            root = new Node(music);
            return root;
        }

        if (music.compareToIgnoreCase(root.music) < 0) {
            root.left = insertRec(root.left, music);
        } else if (music.compareToIgnoreCase(root.music) > 0) {
            root.right = insertRec(root.right, music);
        }

        return root;
    }

    public void insert(String music) {
        root = insertRec(root, music);
    }

    private void searchByTypeRec(Node node, String word, List<String> result) {
        if (node == null) {
            return;
        }

        if (node.music.toLowerCase().startsWith(word)) {
            result.add(node.music);
        }

        searchByTypeRec(node.left, word, result);
        searchByTypeRec(node.right, word, result);
    }

    public List<String> searchByType(String word) {
        List<String> result = new ArrayList<>();
        searchByTypeRec(root, word.toLowerCase(), result);
        return result;
    }

}



public class BinarySearchTree {
    public static void main(String[] args) {
        BST bst = new BST();

        String[] music = {
            "Waking Up Together With You - Ardhito Pramono", "Kupu Kupu - Tiara Andini", "Heaven has to wait - Rachel Stevens"
        };

        for (String musics : music) {
            bst.insert(musics);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Search Music : ");
        String search = input.nextLine();

        List<String> matchMusic = bst.searchByType(search);

        if (!matchMusic.isEmpty()) {
            System.out.println("Result of " + search + " : ");
            for (String musics : matchMusic) {
                System.out.println(" - " + musics);
            }
        } else {
            System.out.println("No music found.");
        }
        input.close();
    }
}