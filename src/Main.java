import java.io.*;
import java.util.*;

public class Main {

    // =========================================================
    // PRODUCT CLASS
    // =========================================================

    static class Product {
        String productId;
        String productName;
        String location;
        String fileName;

        Product(String productId, String productName,
                String location, String fileName) {

            this.productId = productId;
            this.productName = productName;
            this.location = location;
            this.fileName = fileName;
        }

        void display() {

            System.out.println("----------------------------------------------");
            System.out.println("PRODUCT FOUND");
            System.out.println("----------------------------------------------");
            System.out.println("Product ID   : " + productId);
            System.out.println("Product Name : " + productName);
            System.out.println("Location     : " + location);
            System.out.println("File         : " + fileName);
            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // LOAD PRODUCTS
    // =========================================================

    public static HashMap<String, Product> loadProducts(String folderPath) {

        HashMap<String, Product> productMap = new HashMap<>();

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: data folder not found.");
            return productMap;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            return productMap;
        }

        for (File file : files) {

            if (!file.isFile()
                    || !file.getName().toLowerCase().endsWith(".txt")) {
                continue;
            }

            String productId = "";
            String productName = "";
            String location = "";

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    line = line.trim();

                    if (line.startsWith("Product ID:")) {

                        productId = line.substring(11).trim();

                    } else if (line.startsWith("Product Name:")) {

                        productName = line.substring(13).trim();

                    } else if (line.startsWith("Location:")) {

                        location = line.substring(9).trim();

                        if (!productId.isEmpty()
                                && !productName.isEmpty()
                                && !location.isEmpty()) {

                            Product product = new Product(
                                    productId,
                                    productName,
                                    location,
                                    file.getName()
                            );

                            productMap.put(
                                    productId.toLowerCase(),
                                    product
                            );

                            productId = "";
                            productName = "";
                            location = "";
                        }
                    }
                }

            } catch (IOException e) {

                System.out.println(
                        "Error reading file: " + file.getName()
                );
            }
        }

        return productMap;
    }

    // =========================================================
    // CO2 - KMP
    // =========================================================

    public static int[] computeLPS(String pattern) {

        int[] lps = new int[pattern.length()];

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {

                length++;
                lps[i] = length;
                i++;

            } else {

                if (length != 0) {

                    length = lps[length - 1];

                } else {

                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static boolean kmpSearch(
            String text,
            String pattern) {

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        if (pattern.isEmpty()) {
            return true;
        }

        if (pattern.length() > text.length()) {
            return false;
        }

        int[] lps = computeLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;

                if (j == pattern.length()) {
                    return true;
                }

            } else {

                if (j != 0) {

                    j = lps[j - 1];

                } else {

                    i++;
                }
            }
        }

        return false;
    }

    // =========================================================
    // CO2 - PRODUCT NAME SEARCH
    // =========================================================

    public static void searchByName(
            String folderPath,
            String searchProduct) {

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {

            System.out.println("Error: data folder not found.");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null) {

            System.out.println("No inventory files found.");
            return;
        }

        boolean found = false;

        System.out.println();
        System.out.println("Searching product records using KMP...");
        System.out.println();

        for (File file : files) {

            if (!file.isFile()
                    || !file.getName().toLowerCase().endsWith(".txt")) {
                continue;
            }

            String productId = "";
            String productName = "";
            String location = "";

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    line = line.trim();

                    if (line.startsWith("Product ID:")) {

                        productId = line.substring(11).trim();

                    } else if (line.startsWith("Product Name:")) {

                        productName = line.substring(13).trim();

                    } else if (line.startsWith("Location:")) {

                        location = line.substring(9).trim();

                        if (!productName.isEmpty()
                                && kmpSearch(
                                productName,
                                searchProduct)) {

                            Product product = new Product(
                                    productId,
                                    productName,
                                    location,
                                    file.getName()
                            );

                            product.display();

                            found = true;
                        }

                        productId = "";
                        productName = "";
                        location = "";
                    }
                }

            } catch (IOException e) {

                System.out.println(
                        "Error reading file: " + file.getName()
                );
            }
        }

        if (!found) {

            System.out.println("----------------------------------------------");
            System.out.println("PRODUCT NOT FOUND");
            System.out.println("----------------------------------------------");
            System.out.println(
                    "No product matching \"" +
                            searchProduct +
                            "\" was found."
            );
            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // HASHMAP - PRODUCT ID SEARCH
    // =========================================================

    public static void searchById(
            HashMap<String, Product> productMap,
            String productId) {

        System.out.println();
        System.out.println("Searching product using HashMap...");
        System.out.println();

        Product product =
                productMap.get(productId.toLowerCase());

        if (product != null) {

            product.display();

        } else {

            System.out.println("----------------------------------------------");
            System.out.println("PRODUCT NOT FOUND");
            System.out.println("----------------------------------------------");

            System.out.println(
                    "No product with ID \"" +
                            productId +
                            "\" was found."
            );

            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // CO3 - LEVENSHTEIN DISTANCE
    // =========================================================

    public static int levenshteinDistance(
            String first,
            String second) {

        first = first.toLowerCase();
        second = second.toLowerCase();

        int n = first.length();
        int m = second.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                int cost;

                if (first.charAt(i - 1)
                        == second.charAt(j - 1)) {

                    cost = 0;

                } else {

                    cost = 1;
                }

                int insert =
                        dp[i][j - 1] + 1;

                int delete =
                        dp[i - 1][j] + 1;

                int replace =
                        dp[i - 1][j - 1] + cost;

                dp[i][j] = Math.min(
                        Math.min(insert, delete),
                        replace
                );
            }
        }

        return dp[n][m];
    }

    // =========================================================
    // CO3 - FUZZY SEARCH
    // =========================================================

    public static void fuzzySearch(
            HashMap<String, Product> productMap,
            String searchProduct) {

        System.out.println();
        System.out.println(
                "Searching product using Fuzzy Matching..."
        );
        System.out.println();

        String search =
                searchProduct.toLowerCase();

        Product bestMatch = null;

        int bestDistance =
                Integer.MAX_VALUE;

        for (Product product :
                productMap.values()) {

            String productName =
                    product.productName.toLowerCase();

            int distance =
                    levenshteinDistance(
                            search,
                            productName
                    );

            if (distance < bestDistance) {

                bestDistance = distance;
                bestMatch = product;
            }
        }

        int allowedDistance;

        if (search.length() <= 4) {

            allowedDistance = 1;

        } else if (search.length() <= 8) {

            allowedDistance = 2;

        } else {

            allowedDistance = 3;
        }

        if (bestMatch != null
                && bestDistance <= allowedDistance) {

            System.out.println("----------------------------------------------");
            System.out.println("FUZZY MATCH FOUND");
            System.out.println("----------------------------------------------");

            System.out.println(
                    "Search Input : " +
                            searchProduct
            );

            System.out.println(
                    "Edit Distance: " +
                            bestDistance
            );

            System.out.println();

            bestMatch.display();

        } else {

            System.out.println("----------------------------------------------");
            System.out.println("NO CLOSE MATCH FOUND");
            System.out.println("----------------------------------------------");

            System.out.println(
                    "No sufficiently similar product was found for \"" +
                            searchProduct +
                            "\"."
            );

            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // CO1 - ALGORITHM SELECTION
    // =========================================================

    public static void algorithmSelectionModule(
            Scanner scanner) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       CO1 - ALGORITHM SELECTION");
        System.out.println("==============================================");

        System.out.println();
        System.out.println("What type of problem do you want to solve?");
        System.out.println();

        System.out.println("1. Find a product name");
        System.out.println("2. Find a product despite spelling mistakes");
        System.out.println("3. Calculate product distribution capacity");
        System.out.println("4. Optimize difficult warehouse coverage");

        System.out.println();
        System.out.print("Enter your choice: ");

        String choice =
                scanner.nextLine().trim();

        System.out.println();

        switch (choice) {

            case "1":

                System.out.println(
                        "Problem Type: Exact Pattern Matching"
                );

                System.out.println(
                        "Recommended Algorithm: KMP"
                );

                System.out.println(
                        "Reason: KMP efficiently searches for a pattern"
                );

                System.out.println(
                        "inside product names."
                );

                break;

            case "2":

                System.out.println(
                        "Problem Type: Approximate String Matching"
                );

                System.out.println(
                        "Recommended Algorithm: Levenshtein Distance"
                );

                System.out.println(
                        "Reason: Dynamic Programming can measure"
                );

                System.out.println(
                        "the minimum edit operations between strings."
                );

                break;

            case "3":

                System.out.println(
                        "Problem Type: Network Flow"
                );

                System.out.println(
                        "Recommended Algorithm: Edmonds-Karp"
                );

                System.out.println(
                        "Reason: It calculates the maximum possible"
                );

                System.out.println(
                        "flow through a capacity-constrained network."
                );

                break;

            case "4":

                System.out.println(
                        "Problem Type: NP-Hard Optimization"
                );

                System.out.println(
                        "Recommended Strategy: Approximation"
                );

                System.out.println(
                        "Reason: Approximation algorithms provide"
                );

                System.out.println(
                        "efficient solutions with guarantees."
                );

                break;

            default:

                System.out.println(
                        "Invalid choice."
                );
        }

        System.out.println();
        System.out.println("==============================================");
    }

    // =========================================================
    // CO4 - BFS FOR EDMONDS-KARP
    // =========================================================

    public static boolean bfsFlow(
            int[][] capacity,
            int[][] flow,
            int source,
            int sink,
            int[] parent) {

        int n =
                capacity.length;

        Arrays.fill(
                parent,
                -1
        );

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(source);

        parent[source] =
                source;

        while (!queue.isEmpty()) {

            int u =
                    queue.poll();

            for (int v = 0;
                    v < n;
                    v++) {

                int residualCapacity =
                        capacity[u][v]
                                - flow[u][v];

                if (parent[v] == -1
                        && residualCapacity > 0) {

                    parent[v] =
                            u;

                    queue.add(v);

                    if (v == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // =========================================================
    // CO4 - EDMONDS-KARP
    // =========================================================

    public static int edmondsKarp(
            int[][] capacity,
            int source,
            int sink) {

        int n =
                capacity.length;

        int[][] flow =
                new int[n][n];

        int[] parent =
                new int[n];

        int maxFlow =
                0;

        while (bfsFlow(
                capacity,
                flow,
                source,
                sink,
                parent)) {

            int pathFlow =
                    Integer.MAX_VALUE;

            int v =
                    sink;

            while (v != source) {

                int u =
                        parent[v];

                pathFlow =
                        Math.min(
                                pathFlow,
                                capacity[u][v]
                                        - flow[u][v]
                        );

                v =
                        u;
            }

            v =
                    sink;

            while (v != source) {

                int u =
                        parent[v];

                flow[u][v] +=
                        pathFlow;

                flow[v][u] -=
                        pathFlow;

                v =
                        u;
            }

            maxFlow +=
                    pathFlow;
        }

        return maxFlow;
    }

    // =========================================================
    // CO4 - WAREHOUSE DISTRIBUTION
    // =========================================================

    public static void networkFlowModule() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       CO4 - WAREHOUSE DISTRIBUTION");
        System.out.println("==============================================");

        /*
         * Nodes:
         *
         * 0 = Source
         * 1 = Warehouse A
         * 2 = Warehouse B
         * 3 = Destination
         *
         * Capacities represent maximum units
         * that can move through each connection.
         */

        int[][] capacity = {

                {0, 10, 8, 0},

                {0, 0, 2, 7},

                {0, 0, 0, 5},

                {0, 0, 0, 0}
        };

        int source =
                0;

        int sink =
                3;

        int maxFlow =
                edmondsKarp(
                        capacity,
                        source,
                        sink
                );

        System.out.println();

        System.out.println(
                "Warehouse distribution network:"
        );

        System.out.println(
                "Source -> Warehouse A : 10 units"
        );

        System.out.println(
                "Source -> Warehouse B : 8 units"
        );

        System.out.println(
                "Warehouse A -> Warehouse B : 2 units"
        );

        System.out.println(
                "Warehouse A -> Destination : 7 units"
        );

        System.out.println(
                "Warehouse B -> Destination : 5 units"
        );

        System.out.println();

        System.out.println(
                "Maximum Distribution Capacity = "
                        + maxFlow
                        + " units"
        );

        System.out.println();

        System.out.println(
                "Edmonds-Karp uses BFS to find"
        );

        System.out.println(
                "augmenting paths and calculates"
        );

        System.out.println(
                "the maximum possible flow."
        );

        System.out.println("==============================================");
    }

    // =========================================================
    // CO5 - VERTEX COVER APPROXIMATION
    // =========================================================

    public static void approximationModule() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       CO5 - WAREHOUSE COVERAGE");
        System.out.println("==============================================");

        /*
         * Each vertex represents a warehouse/service point.
         *
         * Each edge represents a connection that must
         * be covered.
         */

        int vertices =
                6;

        int[][] edges = {

                {0, 1},
                {0, 2},
                {1, 3},
                {2, 4},
                {3, 5},
                {4, 5}
        };

        boolean[] selected =
                new boolean[vertices];

        for (int[] edge : edges) {

            int u =
                    edge[0];

            int v =
                    edge[1];

            if (!selected[u]
                    && !selected[v]) {

                selected[u] =
                        true;

                selected[v] =
                        true;
            }
        }

        ArrayList<Integer> cover =
                new ArrayList<>();

        for (int i = 0;
                i < vertices;
                i++) {

            if (selected[i]) {

                cover.add(i);
            }
        }

        System.out.println();

        System.out.println(
                "Warehouse network connections:"
        );

        for (int[] edge : edges) {

            System.out.println(
                    "Warehouse "
                            + (edge[0] + 1)
                            + " <-> Warehouse "
                            + (edge[1] + 1)
            );
        }

        System.out.println();

        System.out.println(
                "Selected warehouses for coverage:"
        );

        for (int warehouse :
                cover) {

            System.out.println(
                    "Warehouse "
                            + (warehouse + 1)
            );
        }

        System.out.println();

        System.out.println(
                "Number of selected warehouses: "
                        + cover.size()
        );

        System.out.println();

        System.out.println(
                "The edge-pairing method provides"
        );

        System.out.println(
                "a 2-approximation for Vertex Cover."
        );

        System.out.println();

        System.out.println(
                "This demonstrates how an NP-hard"
        );

        System.out.println(
                "optimization problem can be solved"
        );

        System.out.println(
                "efficiently using approximation."
        );

        System.out.println("==============================================");
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        String folder =
                "data";

        HashMap<String, Product> productMap =
                loadProducts(folder);

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       WAREHOUSE INVENTORY SYSTEM");
        System.out.println("          PRODUCT LOCATOR");
        System.out.println("==============================================");

        System.out.println();

        System.out.println(
                "Total products loaded: "
                        + productMap.size()
        );

        while (true) {

            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("1. Search by Product Name (KMP)       [CO2]");
            System.out.println("2. Search by Product ID (HashMap)     [Support]");
            System.out.println("3. Fuzzy Product Search (Levenshtein) [CO3]");
            System.out.println("4. Algorithm Selection                [CO1]");
            System.out.println("5. Warehouse Distribution             [CO4]");
            System.out.println("6. Warehouse Coverage                 [CO5]");
            System.out.println("7. Exit");
            System.out.println("----------------------------------------------");

            System.out.print(
                    "Enter your choice: "
            );

            String choice =
                    scanner.nextLine().trim();

            // -------------------------------------------------
            // OPTION 1 - KMP
            // -------------------------------------------------

            if (choice.equals("1")) {

                System.out.println();

                System.out.print(
                        "Enter product name to locate: "
                );

                String searchProduct =
                        scanner.nextLine().trim();

                if (searchProduct.isEmpty()) {

                    System.out.println(
                            "Please enter a product name."
                    );

                } else {

                    searchByName(
                            folder,
                            searchProduct
                    );
                }

            // -------------------------------------------------
            // OPTION 2 - HASHMAP
            // -------------------------------------------------

            } else if (choice.equals("2")) {

                System.out.println();

                System.out.print(
                        "Enter Product ID: "
                );

                String productId =
                        scanner.nextLine().trim();

                if (productId.isEmpty()) {

                    System.out.println(
                            "Please enter a Product ID."
                    );

                } else {

                    searchById(
                            productMap,
                            productId
                    );
                }

            // -------------------------------------------------
            // OPTION 3 - FUZZY SEARCH
            // -------------------------------------------------

            } else if (choice.equals("3")) {

                System.out.println();

                System.out.print(
                        "Enter approximate product name: "
                );

                String searchProduct =
                        scanner.nextLine().trim();

                if (searchProduct.isEmpty()) {

                    System.out.println(
                            "Please enter a product name."
                    );

                } else {

                    fuzzySearch(
                            productMap,
                            searchProduct
                    );
                }

            // -------------------------------------------------
            // OPTION 4 - CO1
            // -------------------------------------------------

            } else if (choice.equals("4")) {

                algorithmSelectionModule(
                        scanner
                );

            // -------------------------------------------------
            // OPTION 5 - CO4
            // -------------------------------------------------

            } else if (choice.equals("5")) {

                networkFlowModule();

            // -------------------------------------------------
            // OPTION 6 - CO5
            // -------------------------------------------------

            } else if (choice.equals("6")) {

                approximationModule();

            // -------------------------------------------------
            // OPTION 7 - EXIT
            // -------------------------------------------------

            } else if (choice.equals("7")) {

                System.out.println();

                System.out.println(
                        "Thank you for using the"
                );

                System.out.println(
                        "Warehouse Inventory System!"
                );

                System.out.println();

                break;

            } else {

                System.out.println();

                System.out.println(
                        "Invalid choice. Please enter 1-7."
                );
            }
        }

        scanner.close();
    }
}
        int[] lps = new int[pattern.length()];
        int length = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {

                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // Searches for a pattern inside text using KMP
    public static boolean kmpSearch(String text, String pattern) {

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        if (pattern.isEmpty()) {
            return true;
        }

        if (pattern.length() > text.length()) {
            return false;
        }

        int[] lps = computeLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;

                if (j == pattern.length()) {
                    return true;
                }

            } else {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }

    // =========================================================
    // LOAD PRODUCTS
    // =========================================================

    // Loads all products into a HashMap
    public static HashMap<String, Product> loadProducts(String folderPath) {

        HashMap<String, Product> productMap = new HashMap<>();

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: data folder not found.");
            return productMap;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            return productMap;
        }

        for (File file : files) {

            if (!file.isFile()
                    || !file.getName().toLowerCase().endsWith(".txt")) {
                continue;
            }

            String productId = "";
            String productName = "";
            String location = "";

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    line = line.trim();

                    if (line.startsWith("Product ID:")) {

                        productId = line.substring(11).trim();

                    } else if (line.startsWith("Product Name:")) {

                        productName = line.substring(13).trim();

                    } else if (line.startsWith("Location:")) {

                        location = line.substring(9).trim();

                        if (!productId.isEmpty()
                                && !productName.isEmpty()
                                && !location.isEmpty()) {

                            Product product = new Product(
                                    productId,
                                    productName,
                                    location,
                                    file.getName()
                            );

                            productMap.put(
                                    productId.toLowerCase(),
                                    product
                            );

                            productId = "";
                            productName = "";
                            location = "";
                        }
                    }
                }

            } catch (IOException e) {

                System.out.println(
                        "Error reading file: " + file.getName()
                );
            }
        }

        return productMap;
    }

    // =========================================================
    // KMP PRODUCT NAME SEARCH
    // =========================================================

    public static void searchByName(
            String folderPath,
            String searchProduct) {

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: data folder not found.");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No inventory files found.");
            return;
        }

        boolean found = false;

        System.out.println();
        System.out.println("Searching product records using KMP...");
        System.out.println();

        for (File file : files) {

            if (!file.isFile()
                    || !file.getName().toLowerCase().endsWith(".txt")) {
                continue;
            }

            String productId = "";
            String productName = "";
            String location = "";

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    line = line.trim();

                    if (line.startsWith("Product ID:")) {

                        productId = line.substring(11).trim();

                    } else if (line.startsWith("Product Name:")) {

                        productName = line.substring(13).trim();

                    } else if (line.startsWith("Location:")) {

                        location = line.substring(9).trim();

                        if (!productName.isEmpty()
                                && kmpSearch(productName, searchProduct)) {

                            Product product = new Product(
                                    productId,
                                    productName,
                                    location,
                                    file.getName()
                            );

                            product.display();

                            found = true;
                        }

                        productId = "";
                        productName = "";
                        location = "";
                    }
                }

            } catch (IOException e) {

                System.out.println(
                        "Error reading file: " + file.getName()
                );
            }
        }

        if (!found) {

            System.out.println("----------------------------------------------");
            System.out.println("PRODUCT NOT FOUND");
            System.out.println("----------------------------------------------");
            System.out.println(
                    "No product matching \"" + searchProduct + "\" was found."
            );
            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // HASHMAP PRODUCT ID SEARCH
    // =========================================================

    public static void searchById(
            HashMap<String, Product> productMap,
            String productId) {

        System.out.println();
        System.out.println("Searching product using HashMap...");
        System.out.println();

        Product product = productMap.get(productId.toLowerCase());

        if (product != null) {

            product.display();

        } else {

            System.out.println("----------------------------------------------");
            System.out.println("PRODUCT NOT FOUND");
            System.out.println("----------------------------------------------");
            System.out.println(
                    "No product with ID \"" + productId + "\" was found."
            );
            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // LEVENSHTEIN DISTANCE
    // =========================================================

    /*
     * Calculates the minimum number of single-character
     * operations needed to convert one string into another.
     *
     * Operations:
     * 1. Insert
     * 2. Delete
     * 3. Replace
     */
    public static int levenshteinDistance(
            String first,
            String second) {

        first = first.toLowerCase();
        second = second.toLowerCase();

        int n = first.length();
        int m = second.length();

        int[][] dp = new int[n + 1][m + 1];

        // Base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        // Dynamic programming
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                int cost;

                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                int insert = dp[i][j - 1] + 1;
                int delete = dp[i - 1][j] + 1;
                int replace = dp[i - 1][j - 1] + cost;

                dp[i][j] = Math.min(
                        Math.min(insert, delete),
                        replace
                );
            }
        }

        return dp[n][m];
    }

    // =========================================================
    // FUZZY SEARCH
    // =========================================================

    public static void fuzzySearch(
            HashMap<String, Product> productMap,
            String searchProduct) {

        System.out.println();
        System.out.println(
                "Searching product using Fuzzy Matching..."
        );
        System.out.println();

        String search = searchProduct.toLowerCase();

        Product bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;

        for (Product product : productMap.values()) {

            String productName = product.productName.toLowerCase();

            int distance =
                    levenshteinDistance(search, productName);

            if (distance < bestDistance) {

                bestDistance = distance;
                bestMatch = product;
            }
        }

        /*
         * Allow a match only when the distance is reasonably
         * small compared to the product name length.
         */
        int allowedDistance;

        if (search.length() <= 4) {
            allowedDistance = 1;
        } else if (search.length() <= 8) {
            allowedDistance = 2;
        } else {
            allowedDistance = 3;
        }

        if (bestMatch != null
                && bestDistance <= allowedDistance) {

            System.out.println("----------------------------------------------");
            System.out.println("FUZZY MATCH FOUND");
            System.out.println("----------------------------------------------");
            System.out.println(
                    "Search Input : " + searchProduct
            );
            System.out.println(
                    "Edit Distance: " + bestDistance
            );
            System.out.println();

            bestMatch.display();

        } else {

            System.out.println("----------------------------------------------");
            System.out.println("NO CLOSE MATCH FOUND");
            System.out.println("----------------------------------------------");
            System.out.println(
                    "No sufficiently similar product was found for \""
                            + searchProduct + "\"."
            );
            System.out.println("----------------------------------------------");
        }
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String folder = "data";

        HashMap<String, Product> productMap =
                loadProducts(folder);

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       WAREHOUSE INVENTORY SYSTEM");
        System.out.println("          PRODUCT LOCATOR");
        System.out.println("==============================================");

        System.out.println();
        System.out.println(
                "Total products loaded: " + productMap.size()
        );

        while (true) {

            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("1. Search by Product Name (KMP)");
            System.out.println("2. Search by Product ID (HashMap)");
            System.out.println("3. Fuzzy Product Name Search");
            System.out.println("4. Exit");
            System.out.println("----------------------------------------------");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            // -------------------------------------------------
            // OPTION 1: KMP
            // -------------------------------------------------

            if (choice.equals("1")) {

                System.out.println();
                System.out.print(
                        "Enter product name to locate: "
                );

                String searchProduct =
                        scanner.nextLine().trim();

                if (searchProduct.isEmpty()) {

                    System.out.println(
                            "Please enter a product name."
                    );

                } else {

                    searchByName(folder, searchProduct);
                }

            // -------------------------------------------------
            // OPTION 2: HASHMAP
            // -------------------------------------------------

            } else if (choice.equals("2")) {

                System.out.println();
                System.out.print("Enter Product ID: ");

                String productId =
                        scanner.nextLine().trim();

                if (productId.isEmpty()) {

                    System.out.println(
                            "Please enter a Product ID."
                    );

                } else {

                    searchById(productMap, productId);
                }

            // -------------------------------------------------
            // OPTION 3: FUZZY SEARCH
            // -------------------------------------------------

            } else if (choice.equals("3")) {

                System.out.println();
                System.out.print(
                        "Enter approximate product name: "
                );

                String searchProduct =
                        scanner.nextLine().trim();

                if (searchProduct.isEmpty()) {

                    System.out.println(
                            "Please enter a product name."
                    );

                } else {

                    fuzzySearch(
                            productMap,
                            searchProduct
                    );
                }

            // -------------------------------------------------
            // OPTION 4: EXIT
            // -------------------------------------------------

            } else if (choice.equals("4")) {

                System.out.println();
                System.out.println(
                        "Thank you for using the"
                );
                System.out.println(
                        "Warehouse Inventory System!"
                );
                System.out.println();

                break;

            } else {

                System.out.println();
                System.out.println(
                        "Invalid choice. Please enter 1, 2, 3, or 4."
                );
            }
        }

        scanner.close();
    }
}
