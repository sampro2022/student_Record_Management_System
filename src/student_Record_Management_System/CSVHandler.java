package student_Record_Management_System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {

    private File file;

    public CSVHandler(File file) {
        this.file = file;
    }

    /* =====================================================
       LOAD CSV INTO BST (Rule 5 + Rule 7 compliant)
       ===================================================== */
    public Node loadCSV(BST bst) {
        Node root = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3) {
                    String matric = data[0].trim();
                    String name = data[1].trim();
                    double cgpa = Double.parseDouble(data[2].trim());

                    root = bst.insert(root, matric, name, cgpa);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file.");
        }

        return root;
    }

    /* =====================================================
       APPEND NEW RECORD TO CSV (Rule‑safe)
       ===================================================== */
    public void appendCSV(String matric, String name, double cgpa) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.newLine();
            bw.write(matric + "," + name + "," + cgpa);
        } catch (IOException e) {
            System.out.println("Error writing to CSV file.");
        }
    }
}

