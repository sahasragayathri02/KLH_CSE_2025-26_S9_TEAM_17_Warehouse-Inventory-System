import java.io.*;
import java.util.*;

public class Main {

    /*
     * ============================================================
     * KMP PATTERN SEARCH ALGORITHM
     * ============================================================
     *
     * This method searches for a pattern inside a given text
     * using the KMP (Knuth-Morris-Pratt) algorithm.
     *
     * Returns:
     * true  -> if the product name is found
     * false -> if the product name is not found
     */
    public static boolean kmpSearch(String text, String pattern) {

        // Convert both strings to lowercase so that the search
        // is not affected by uppercase/lowercase differences.
        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        // Create the LPS array required by KMP.
        int[] lps = computeLPS(pattern);

        int i = 0; // Pointer for text
        int j = 0; // Pointer for pattern

        /*
         * Compare the text and pattern characters.
         * KMP avoids unnecessary comparisons when a mismatch occurs.
         */
        while (i < text.length()) {

            // Characters match
            if (text.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;

                // Entire pattern has been matched
                if (j == pattern.length()) {
                    return true;
                }

            } else {

                /*
                 * If a mismatch occurs and some characters of the
                 * pattern have already matched, use the LPS array
                 * instead of starting from the beginning.
                 */
                if (j != 0) {

                    j = lps[j - 1];

                } else {

                    // No partial match, so move to the next text character.
                    i++;
                }
            }
        }

        // Pattern was not found.
        return false;
    }


    /*
     * ============================================================
     * LPS ARRAY
     * ============================================================
     *
     * LPS stands for:
     * Longest Proper Prefix which is also a Suffix.
     *
     * The LPS array helps KMP skip unnecessary comparisons.
     */
    public static int[] computeLPS(String pattern) {

        // Create an array with the same size as the pattern.
        int[] lps = new int[pattern.length()];

        int length = 0;

        // Start from the second character.
        int i = 1;

        while (i < pattern.length()) {

            // If the characters match
            if (pattern.charAt(i) == pattern.charAt(length)) {

                length++;

                // Store the length of the matching prefix.
                lps[i] = length;

                i++;

            } else {

                /*
                 * If there is a mismatch but we already have
                 * a matching prefix, use the previous LPS value.
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
     * This is where the Product Locator system starts.
     */
    public static void main(String[] args) {

        // Scanner is used to take product name from the user.
        Scanner scanner = new Scanner(System.in);

        // Folder containing all warehouse product records.
        String folder = "corpus";


        /*
         * --------------------------------------------------------
         * DISPLAY PROJECT TITLE
         * --------------------------------------------------------
         */
        System.out.println();
        System.out.println("==============================================");
        System.out.println("       WAREHOUSE INVENTORY SYSTEM");
        System.out.println("          PRODUCT LOCATOR");
        System.out.println("==============================================");
        System.out.println();


        /*
         * --------------------------------------------------------
         * TAKE PRODUCT NAME FROM USER
         * --------------------------------------------------------
         */
        System.out.print("Enter product name to locate: ");

        String searchProduct = scanner.nextLine().trim();


        /*
         * Check whether the user entered an empty product name.
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
         * --------------------------------------------------------
         * CHECK WHETHER CORPUS FOLDER EXISTS
         * --------------------------------------------------------
         */
        File corpusFolder = new File(folder);

        if (!corpusFolder.exists()) {

            System.out.println("Error: Corpus folder not found.");

            scanner.close();

            return;
        }


        /*
         * Get all files present inside the corpus folder.
         */
        File[] files = corpusFolder.listFiles();

        // This variable keeps track of whether a product was found.
        boolean found = false;


        /*
         * --------------------------------------------------------
         * SEARCH THROUGH ALL PRODUCT FILES
         * --------------------------------------------------------
         */
        if (files != null) {

            // Go through every file in the corpus folder.
            for (File file : files) {

                // Ignore folders and process only files.
                if (!file.isFile()) {
                    continue;
                }


                try {

                    /*
                     * BufferedReader reads the product records
                     * line by line from each file.
                     */
                    BufferedReader reader =
                            new BufferedReader(new FileReader(file));

                    String line;


                    /*
                     * Variables used to store information about
                     * the current product.
                     */
                    String productId = "";
                    String productName = "";
                    String location = "";


                    /*
                     * Read the file one line at a time.
                     */
                    while ((line = reader.readLine()) != null) {

                        // Remove unnecessary spaces.
                        line = line.trim();


                        /*
                         * ------------------------------------------------
                         * READ PRODUCT ID
                         * ------------------------------------------------
                         */
                        if (line.startsWith("Product ID:")) {

                            productId =
                                    line.substring("Product ID:".length()).trim();
                        }


                        /*
                         * ------------------------------------------------
                         * READ PRODUCT NAME
                         * ------------------------------------------------
                         */
                        else if (line.startsWith("Product Name:")) {

                            productName =
                                    line.substring("Product Name:".length()).trim();
                        }


                        /*
                         * ------------------------------------------------
                         * READ PRODUCT LOCATION
                         * ------------------------------------------------
                         *
                         * Location is the last part of each product
                         * record.
                         *
                         * Once we reach the location line, we have
                         * collected the complete product information.
                         */
                        else if (line.startsWith("Location:")) {

                            location =
                                    line.substring("Location:".length()).trim();


                            /*
                             * ------------------------------------------------
                             * APPLY KMP SEARCH
                             * ------------------------------------------------
                             *
                             * We search the user's product name inside
                             * the product name from the file.
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
                                 * Display complete product information.
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


                                // Mark the product as found.
                                found = true;
                            }


                            /*
                             * Reset the variables before reading
                             * the next product record.
                             */
                            productId = "";
                            productName = "";
                            location = "";
                        }
                    }


                    // Close the file after reading.
                    reader.close();

                }


                /*
                 * Handle errors while reading files.
                 */
                catch (IOException e) {

                    System.out.println(
                            "Error reading file: " + file.getName()
                    );
                }
            }
        }


        /*
         * --------------------------------------------------------
         * PRODUCT NOT FOUND
         * --------------------------------------------------------
         *
         * If no product matched the user's search,
         * display an appropriate message.
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
         * Close Scanner before ending the program.
         */
        scanner.close();
    }
}
