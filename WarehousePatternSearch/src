import java.io.*;
import java.util.*;

/**
 * Warehouse Inventory System - Product Locator
 *
 * This program uses the KMP (Knuth-Morris-Pratt)
 * pattern matching algorithm to search for products
 * in warehouse inventory files.
 */
public class Main {

    /*
     * ============================================================
     * KMP SEARCH
     * ============================================================
     *
     * Searches for a pattern inside a text using
     * the Knuth-Morris-Pratt algorithm.
     *
     * Returns true if the pattern is found.
     */
    public static boolean kmpSearch(String text, String pattern) {

        // Convert both strings to lowercase.
        // This makes the search case-insensitive.
        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        // Create the LPS array for the pattern.
        int[] lps = computeLPS(pattern);

        // i -> position in text
        // j -> position in pattern
        int i = 0;
        int j = 0;

        // Continue until we reach the end of the text.
        while (i < text.length()) {

            // If characters match
            if (text.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;

                // Entire pattern has been found.
                if (j == pattern.length()) {
                    return true;
                }

            } else {

                /*
                 * If some characters have already matched,
                 * use the LPS array to avoid unnecessary
                 * comparisons.
                 */
                if (j != 0) {

                    j = lps[j - 1];

                } else {

                    // No partial match, move to next text character.
                    i++;
                }
            }
        }

        // Pattern was not found.
        return false;
    }


    /*
     * ============================================================
     * COMPUTE LPS ARRAY
     * ============================================================
     *
     * LPS = Longest Proper Prefix which is also a Suffix.
     *
     * The LPS array helps KMP skip unnecessary comparisons.
     */
    public static int[] computeLPS(String pattern) {

        // Create LPS array.
        int[] lps = new int[pattern.length()];

        // Length of the previous longest prefix-suffix.
        int length = 0;

        // Start from the second character.
        int i = 1;

        while (i < pattern.length()) {

            // Characters match.
            if (pattern.charAt(i) == pattern.charAt(length)) {

                length++;

                lps[i] = length;

                i++;

            } else {

                /*
                 * If there is a mismatch and length is not zero,
                 * use the previous LPS value.
                 */
                if (length != 0) {

                    length = lps[length - 1];

                } else {

                    // No matching prefix exists.
                    lps[i] = 0;

                    i++;
                }
            }
        }

        return lps;
    }


    /*
     * ============================================================
     * MAIN METHOD
     * ============================================================
     *
     * Starts the Warehouse Product Locator.
     */
    public static void main(String[] args) {

        // Scanner takes input from the user.
        Scanner scanner = new Scanner(System.in);

        /*
         * IMPORTANT:
         * The inventory files are inside the "data" folder.
         *
         * We run the program from the project root:
         *
         * WarehousePatternSearch/
         */
        String folder = "data";


        /*
         * ========================================================
         * PROJECT TITLE
         * ========================================================
         */
        System.out.println();
        System.out.println("==============================================");
        System.out.println("       WAREHOUSE INVENTORY SYSTEM");
        System.out.println("          PRODUCT LOCATOR");
        System.out.println("==============================================");
        System.out.println();


        /*
         * ========================================================
         * USER INPUT
         * ========================================================
         */
        System.out.print("Enter product name to locate: ");

        String searchProduct = scanner.nextLine().trim();


        /*
         * Check if the user entered nothing.
         */
        if (searchProduct.isEmpty()) {

            System.out.println("Please enter a product name.");

            scanner.close();

            return;
        }


        System.out.println();
        System.out.println("Searching product records...");
        System.out.println();


        /*
         * ========================================================
         * CHECK DATA FOLDER
         * ========================================================
         */
        File dataFolder = new File(folder);

        if (!dataFolder.exists() || !dataFolder.isDirectory()) {

            System.out.println("Error: Data folder not found.");

            scanner.close();

            return;
        }


        /*
         * Get all files inside the data folder.
         */
        File[] files = dataFolder.listFiles();

        // Keeps track of whether a product was found.
        boolean found = false;


        /*
         * ========================================================
         * SEARCH ALL INVENTORY FILES
         * ========================================================
         */
        if (files != null) {

            /*
             * Go through every file inside the data folder.
             */
            for (File file : files) {

                // Ignore folders.
                if (!file.isFile()) {
                    continue;
                }


                /*
                 * ====================================================
                 * READ FILE
                 * ====================================================
                 */
                try (BufferedReader reader =
                             new BufferedReader(new FileReader(file))) {

                    String line;


                    /*
                     * Variables for storing product information.
                     */
                    String productId = "";
                    String productName = "";
                    String location = "";


                    /*
                     * Read the file line by line.
                     */
                    while ((line = reader.readLine()) != null) {

                        // Remove unnecessary spaces.
                        line = line.trim();


                        /*
                         * =================================================
                         * PRODUCT ID
                         * =================================================
                         */
                        if (line.startsWith("Product ID:")) {

                            productId =
                                    line.substring("Product ID:".length()).trim();
                        }


                        /*
                         * =================================================
                         * PRODUCT NAME
                         * =================================================
                         */
                        else if (line.startsWith("Product Name:")) {

                            productName =
                                    line.substring("Product Name:".length()).trim();
                        }


                        /*
                         * =================================================
                         * LOCATION
                         * =================================================
                         *
                         * When we reach the Location line,
                         * we have the complete product record.
                         */
                        else if (line.startsWith("Location:")) {

                            location =
                                    line.substring("Location:".length()).trim();


                            /*
                             * =================================================
                             * KMP PATTERN SEARCH
                             * =================================================
                             *
                             * Search for the user's product name
                             * inside the current product name.
                             */
                            if (kmpSearch(productName, searchProduct)) {

                                System.out.println(
                                        "----------------------------------------------"
                                );

                                System.out.println("✓ PRODUCT FOUND");

                                System.out.println(
                                        "----------------------------------------------"
                                );

                                /*
                                 * Display product information.
                                 */
                                System.out.println(
                                        "Product ID   : " + productId
                                );

                                System.out.println(
                                        "Product Name : " + productName
                                );

                                System.out.println(
                                        "Location     : " + location
                                );

                                System.out.println(
                                        "File         : " + file.getName()
                                );

                                System.out.println(
                                        "----------------------------------------------"
                                );

                                System.out.println();

                                // Product has been found.
                                found = true;
                            }


                            /*
                             * Reset variables before reading
                             * the next product record.
                             */
                            productId = "";
                            productName = "";
                            location = "";
                        }
                    }

                }

                /*
                 * Handle file-reading errors.
                 */
                catch (IOException e) {

                    System.out.println(
                            "Error reading file: " + file.getName()
                    );
                }
            }
        }


        /*
         * ========================================================
         * PRODUCT NOT FOUND
         * ========================================================
         */
        if (!found) {

            System.out.println(
                    "----------------------------------------------"
            );

            System.out.println("✗ PRODUCT NOT FOUND");

            System.out.println(
                    "----------------------------------------------"
            );

            System.out.println(
                    "No product matching \"" +
                    searchProduct +
                    "\" was found."
            );

            System.out.println(
                    "----------------------------------------------"
            );
        }


        /*
         * Close Scanner.
         */
        scanner.close();
    }
}
