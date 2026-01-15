package student_Record_Management_System;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BST bst = new BST();
        File file = new File("src/student_records.csv");
        CSVHandler csv = new CSVHandler(file);

        Node root = null;

        /* =====================================================
           TEST CASE 1: FUNCTIONAL TESTING
           ===================================================== */
        System.out.println("===== TEST CASE 1: FUNCTIONAL TEST =====");

        root = bst.insert(root, "A22CS001", "Ali Ahmad", 3.45);
        root = bst.insert(root, "A22CS002", "Siti Aminah", 3.78);
        root = bst.insert(root, "A22CS003", "John Tan", 2.95);
        root = bst.insert(root, "A22CS004", "Nur Aisyah", 3.12);
        root = bst.insert(root, "A22CS005", "Daniel Lee", 3.88);
        root = bst.insert(root, "A22CS006", "Farah Zainal", 3.21);
        root = bst.insert(root, "A22CS007", "Ahmad Firdaus", 2.75);
        root = bst.insert(root, "A22CS008", "Sofia Karim", 3.66);
        root = bst.insert(root, "A22CS009", "Hafiz Rahman", 3.09);
        root = bst.insert(root, "A22CS010", "Aina Yusuf", 3.91);
        root = bst.insert(root, "A22CS011", "Adam Iskandar", 3.34);
        root = bst.insert(root, "A22CS012", "Nabila Hassan", 3.58);
        root = bst.insert(root, "A22CS013", "Irfan Hakim", 2.88);
        root = bst.insert(root, "A22CS014", "Amirah Noor", 3.47);
        root = bst.insert(root, "A22CS015", "Syafiq Anuar", 3.02);

        System.out.println("\nInorder Traversal (Sorted by Matric):");
        bst.inorder(root);

        System.out.println("\nSearch Results:");
        System.out.println(bst.search(root, "A22CS003") != null ? "Found A22CS003" : "Not Found");
        System.out.println(bst.search(root, "A22CS010") != null ? "Found A22CS010" : "Not Found");
        System.out.println(bst.search(root, "A22CS020") != null ? "Found A22CS020" : "Not Found");

        System.out.println("\nDelete Leaf Node (A22CS015):");
        root = bst.delete(root, "A22CS015");
        bst.inorder(root);

        System.out.println("\nDelete One Child Node (A22CS007):");
        root = bst.delete(root, "A22CS007");
        bst.inorder(root);

        System.out.println("\nDelete Two Children Node (A22CS002):");
        root = bst.delete(root, "A22CS002");
        bst.inorder(root);

        /* =====================================================
           TEST CASE 2: EDGE CASES
           ===================================================== */
        System.out.println("\n===== TEST CASE 2: EDGE CASES =====");

        System.out.println("Duplicate Insert:");
        bst.insert(root, "A22CS001", "Ali Ahmad", 3.45);

        System.out.println("Delete Non .Existing:");
        bst.delete(root, "A22CS999");

        System.out.println("Empty Tree Test:");
        Node emptyRoot = null;
        bst.inorder(emptyRoot);

        System.out.println("Single Node Tree:");
        Node single = null;
        single = bst.insert(single, "A22CS100", "Single Student", 3.00);
        bst.inorder(single);

        /* =====================================================
           TEST CASE 3: PERFORMANCE TESTING
           ===================================================== */
        System.out.println("\n===== TEST CASE 3: PERFORMANCE TEST =====");

        Node perfRoot = null;

        long startLoad = System.nanoTime();
        perfRoot = csv.loadCSV(bst);
        long endLoad = System.nanoTime();
        System.out.println("CSV Load Time (ns): " + (endLoad - startLoad));

        Random rand = new Random();

        long startSearch = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            String matric = "A22CS" + String.format("%03d", rand.nextInt(1000) + 1);
            bst.search(perfRoot, matric);
        }
        long endSearch = System.nanoTime();
        System.out.println("100 Random Searches Time (ns): " + (endSearch - startSearch));

        long startDelete = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            String matric = "A22CS" + String.format("%03d", rand.nextInt(1000) + 1);
            perfRoot = bst.delete(perfRoot, matric);
        }
        long endDelete = System.nanoTime();
        System.out.println("100 Random Deletes Time (ns): " + (endDelete - startDelete));

        long startTraversal = System.nanoTime();
        bst.inorder(perfRoot);
        long endTraversal = System.nanoTime();
        System.out.println("Traversal Time (ns): " + (endTraversal - startTraversal));
//        bst.delete(perfRoot, "A22CS003");
//        System.out.println("after delete: ");
//        bst.inorder(perfRoot);

        /* =====================================================
           SORTING DEMONSTRATION (RULE 5)
           ===================================================== */

        Sorting sorting1 = new Sorting();

        /* Convert BST to list once */
        ArrayList<Node> originalList = new ArrayList<>();
        collectInorder(perfRoot, originalList);

        /* -------- MERGE SORT TEST (BY NAME) -------- */
        ArrayList<Node> mergeList = new ArrayList<>(originalList);

        long startMerge = System.nanoTime();
        sorting1.mergeSortByName(mergeList);
        long endMerge = System.nanoTime();

        System.out.println("\n===== MERGE SORT (BY NAME) =====");
        for (Node n : mergeList) {
            System.out.println(n.name + " | " + n.matric + " | " + n.cgpa);
        }
        System.out.println("Merge Sort Time (ns): " + (endMerge - startMerge));


        /* -------- QUICK SORT TEST (BY CGPA) -------- */
        ArrayList<Node> quickList = new ArrayList<>(originalList);

        long startQuick = System.nanoTime();
        sorting1.quickSortByCGPA(quickList);
        long endQuick = System.nanoTime();

        System.out.println("\n===== QUICK SORT (BY CGPA) =====");
        for (Node n : quickList) {
            System.out.println(n.cgpa + " | " + n.matric + " | " + n.name);
        }
        System.out.println("Quick Sort Time (ns): " + (endQuick - startQuick));

        System.out.println("\n===== ALL SORTING TESTS EXECUTED IN MAIN =====");
     // ================= UTILITY METHODS TEST (RULE 6) =================

        System.out.println("\n===== BST UTILITY METHODS =====");

        System.out.println("Total number of nodes: " + bst.countNodes(perfRoot));

        System.out.println("Height of BST: " + bst.height(perfRoot));

        Node min = bst.minNode(perfRoot);
        if (min != null) {
            System.out.println("Minimum Matric: " + min.matric +
                               " | " + min.name +
                               " | " + min.cgpa);
        }

        Node max = bst.maxNode(perfRoot);
        if (max != null) {
            System.out.println("Maximum Matric: " + max.matric +
                               " | " + max.name +
                               " | " + max.cgpa);
        }


        System.out.println("\n===== ALL TEST CASES EXECUTED IN MAIN =====");
        
    }

    /* Helper: Convert BST to ArrayList */
    private static void collectInorder(Node root, ArrayList<Node> list) {
        if (root != null) {
            collectInorder(root.left, list);
            list.add(root);
            collectInorder(root.right, list);
        }
    }
}
