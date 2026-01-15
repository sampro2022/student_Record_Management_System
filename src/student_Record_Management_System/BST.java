package student_Record_Management_System;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    /* ================= INSERT (Rule 1 & 3) ================= */
    public Node insert(Node root, String matric, String name, double cgpa) {

        if (root == null) {
            return new Node(matric, name, cgpa);
        }

        if (matric.equals(root.matric)) {
            System.out.println("Duplicate matric rejected: " + matric);
            return root;
        }

        if (matric.compareTo(root.matric) < 0) {
            root.left = insert(root.left, matric, name, cgpa);
        } else {
            root.right = insert(root.right, matric, name, cgpa);
        }

        return root;
    }

    /* ================= SEARCH (Rule 4) ================= */
    public Node search(Node root, String matric) {
        if (root == null || root.matric.equals(matric)) {
            return root;
        }

        if (matric.compareTo(root.matric) < 0) {
            return search(root.left, matric);
        }

        return search(root.right, matric);
    }

    /* ================= DELETE (Rule 4) ================= */
    public Node delete(Node root, String matric) {

        if (root == null) {
            return null;
        }

        if (matric.compareTo(root.matric) < 0) {
            root.left = delete(root.left, matric);
        } else if (matric.compareTo(root.matric) > 0) {
            root.right = delete(root.right, matric);
        } else {

            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: One child
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            Node successor = minNode(root.right);
            root.matric = successor.matric;
            root.name = successor.name;
            root.cgpa = successor.cgpa;

            root.right = delete(root.right, successor.matric);
        }

        return root;
    }

    /* ================= TRAVERSALS (Rule 4) ================= */
    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.matric + " | " + root.name + " | " + root.cgpa);
            inorder(root.right);
        }
    }

    public void preorder(Node root) {
        if (root != null) {
            System.out.println(root.matric + " | " + root.name + " | " + root.cgpa);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.matric + " | " + root.name + " | " + root.cgpa);
        }
    }

    /* ================= UTILITIES (Rule 6) ================= */
    public int height(Node root) {
        if (root == null) return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public Node minNode(Node root) {
        Node current = root;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    public Node maxNode(Node root) {
        Node current = root;
        while (current != null && current.right != null) {
            current = current.right;
        }
        return current;
    }

    /* ===== BONUS: LEVEL ORDER (Allowed) ===== */
    public void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node current = q.poll();
            System.out.println(current.matric + " | " + current.name + " | " + current.cgpa);

            if (current.left != null) q.add(current.left);
            if (current.right != null) q.add(current.right);
        }
    }
}
