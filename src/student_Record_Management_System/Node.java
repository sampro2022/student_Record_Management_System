package student_Record_Management_System;

public class Node {
    String matric;
    String name;
    double cgpa;
    Node left, right;

    public Node(String matric, String name, double cgpa) {
        this.matric = matric;
        this.name = name;
        this.cgpa = cgpa;
        this.left = null;
        this.right = null;
    }
}

