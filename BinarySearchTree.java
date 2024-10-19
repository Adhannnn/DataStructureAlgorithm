/* 
 * Wahyu Ramadhan 
 * 001202300005
 * IT 2
 * Data Structure and Algorithm - Assignment 1
 * 
 * R.B. Wahyu <- Lecturer
*/

import java.util.*;

class Node { // Stores music
    String music;
    Node left, right;

    public Node(String music) {
        this.music = music;
        left = right = null;
    }
}

class BST { // Using Binary Search Tree
    private Node root;

    public BST() {
        root = null;
    }

    private Node insertRec(Node root, String music) { 
        if (root == null) { // If the tree is empty, create a new node
            root = new Node(music);
            return root;
        }

        // Insert in the left or right subtree based on comparing the word
        if (music.compareToIgnoreCase(root.music) < 0) {
            root.left = insertRec(root.left, music);
        } else if (music.compareToIgnoreCase(root.music) > 0) {
            root.right = insertRec(root.right, music);
        }

        return root;
    }

    public void insert(String music) { // Method to insert music into the Binary Search Tree
        root = insertRec(root, music);
    }

    private void searchByTypeRec(Node node, String word, List<String> result) {
        if (node == null) {
            return;
        }

        if (node.music.toLowerCase().startsWith(word)) { // Check if the music start with the word (prefix)
            result.add(node.music); // Add to the result list
        }

        searchByTypeRec(node.left, word, result); // Search the left subtrees
        searchByTypeRec(node.right, word, result); // Search the right subtrees
    }

    public List<String> searchByType(String word) { // To search for music based on a word
        List<String> result = new ArrayList<>();
        searchByTypeRec(root, word.toLowerCase(), result);
        return result;
    }

}



public class BinarySearchTree {
    public static void main(String[] args) {
        BST bst = new BST();

        // The music
        String[] music = {
            "Waking Up Together With You - Ardhito Pramono", "Kupu Kupu - Tiara Andini", "Heaven has to wait - Rachel Stevens"
        };

        // Store music into the Binary Search Tree
        for (String musics : music) {
            bst.insert(musics);
        }

        // Scanner for input String //
        Scanner input = new Scanner(System.in);
        System.out.print("Search Music : ");
        String search = input.nextLine(); // Input by User

        List<String> matchMusic = bst.searchByType(search); // Result list

        // Display Result
        if (!matchMusic.isEmpty()) { // If music in the Binary Search Tree, it will show the result in Display
            System.out.println("Result of " + search + " : ");
            for (String musics : matchMusic) {
                System.out.println(" - " + musics);
            }
        } else { // If not, it will print "No music found."
            System.out.println("No music found.");
        }
        input.close(); // Closing the scanner
    }
}