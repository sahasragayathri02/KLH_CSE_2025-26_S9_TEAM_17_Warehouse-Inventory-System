# Warehouse Inventory System – Product Locator

A Java-based Warehouse Inventory System that helps users locate products efficiently using string-matching algorithms, hash-based searching, Dynamic Programming, network-flow algorithms, and approximation algorithms.

## Team Members

| S. No. | Name | Roll Number / Student ID |
|---|---|---|
| 1 | Ch Sahasra Gayathri | 2520030306 |
| 2 | V Sameer Reddy | 2520030276 |
| 3 | Vennela Edala | 252030288 |

**Faculty Supervisor:** [Dr. Vinay Kumar]

**Department:** Computer Science and Engineering  
**Institution:** KLH  
**Academic Year:** 2025–2026

---

## 1. Abstract

The Warehouse Inventory System – Product Locator is a Java-based application designed to simplify product searching and demonstrate the practical application of Data Structures and Algorithms. In a warehouse containing numerous products across different categories, manually locating a product can be time-consuming. This project addresses the problem by providing product-name searching, product-ID retrieval, and fuzzy searching for similar product names.

The system loads product information from category-wise text files and uses the Knuth-Morris-Pratt (KMP) string-matching algorithm to search product names. A HashMap supports efficient product retrieval using unique product IDs. Levenshtein Distance, implemented using Dynamic Programming, supports approximate string matching.

The application also demonstrates algorithm selection for different problem types, maximum-flow calculation using the Edmonds-Karp algorithm, and an approximation algorithm for the Vertex Cover problem. These features connect practical inventory-search operations with selected concepts from the Design and Analysis of Algorithms course outcomes CO1–CO5.

The project is implemented in Java and organized into source-code, data, documentation, results, and reports folders. It demonstrates how appropriate data structures and algorithms can improve searching and provide solutions to warehouse-inspired computational problems.

## 2. Problem Statement

Warehouses contain products belonging to multiple categories, making manual product identification and retrieval inconvenient as the inventory grows.

A user may know a product's name, unique ID, or approximate spelling but may not know its exact storage location. Searching for products manually can take unnecessary time.

The project aims to provide a simple application that supports efficient product identification and demonstrates algorithms for searching, network distribution, and graph optimization.

## 3. Objectives

- Organize product information into category-wise text files.
- Load product records into the Java application.
- Search product names using KMP string matching.
- Retrieve products using their unique IDs through a HashMap.
- Support approximate product-name searching using Levenshtein Distance.
- Demonstrate algorithm selection based on problem characteristics.
- Calculate maximum flow in a predefined warehouse-distribution network.
- Demonstrate an approximation algorithm for warehouse coverage.
- Apply and analyze relevant data structures and algorithms.
- Maintain a modular, documented, and version-controlled project.

## 4. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development and algorithm implementation |
| Java Collections Framework | Product collections and HashMap-based lookup |
| Text files (`.txt`) | Category-wise product storage |
| VS Code | Code editing and execution |
| Git | Version control |
| GitHub | Collaborative development and repository hosting |

## 5. Project Modules

### Module 1: Product Name Search — KMP (CO2)

The Knuth-Morris-Pratt algorithm searches for a pattern within a product name.

The implementation uses:
- `computeLPS()` to construct the Longest Proper Prefix which is also a Suffix array.
- `kmpSearch()` to search for the pattern while avoiding unnecessary repeated comparisons.
- Product-search logic to identify and display matching products.

**Standard complexity:** O(n + m) time and O(m) auxiliary space, where n is the text length and m is the pattern length.

### Module 2: Product ID Search — HashMap

A HashMap stores product IDs as keys and corresponding product objects as values.

When the user enters a product ID, the application retrieves the corresponding product from the map.

**Average lookup complexity:** O(1), assuming suitable hash distribution and an already constructed map.

This module supports efficient inventory retrieval.

### Module 3: Fuzzy Product Search — Levenshtein Distance

This module compares the entered product name with available product names to identify similar strings.

The Levenshtein Distance algorithm calculates the minimum number of insertions, deletions, and substitutions needed to transform one string into another.

Dynamic Programming stores intermediate results in a two-dimensional table.

**Standard complexity:** O(mn) time and O(mn) space for strings of lengths m and n.

The products displayed depend on the matching or distance threshold used by the implementation.

### Module 4: Algorithm Selection (CO1)

This module demonstrates how different computational problems require different algorithmic strategies.

Based on the selected problem type, the application recommends an appropriate algorithmic approach, such as string matching, dynamic programming, network flow, or approximation.

### Module 5: Warehouse Distribution — Edmonds-Karp (CO4)

This module represents warehouse distribution as a directed graph.

- Vertices represent warehouses or distribution points.
- Directed edges represent connections.
- Edge capacities represent maximum permitted flow.

The implementation uses a capacity matrix, Breadth-First Search (BFS), a queue, a parent array, and residual capacities to calculate the maximum flow from a source to a sink.

**Time complexity:** O(VE²), where V is the number of vertices and E is the number of edges.

The current implementation uses a predefined example network.

### Module 6: Warehouse Coverage — Vertex Cover Approximation (CO5)

This module represents warehouse connections as a graph and demonstrates a 2-approximation algorithm for Vertex Cover.

The algorithm selects both endpoints of an uncovered edge. It continues until all edges are covered.

The resulting set is a valid vertex cover whose size is at most twice the size of an optimal minimum vertex cover.

**Time complexity:** O(E) for the straightforward edge-scanning implementation, excluding additional representation or output costs.

The current implementation uses a predefined graph.

## 6. Course Outcome Mapping

| Course Outcome | Project Component | Demonstrated Concept |
|---|---|---|
| CO1 | Algorithm Selection | Choosing an algorithmic strategy based on problem type |
| CO2 | KMP Product Name Search | Linear-time string matching and LPS preprocessing |
| CO3 | Fuzzy Product Search | Dynamic Programming using edit distance |
| CO4 | Warehouse Distribution | Maximum flow using Edmonds-Karp |
| CO5 | Warehouse Coverage | Approximation algorithm for Vertex Cover |

**Scope note:** The project demonstrates selected concepts from CO1–CO5; it does not implement every algorithm or theoretical topic listed in the complete course outcomes.

## 7. Project Structure

```text
WarehousePatternSearch/
├── README.md
├── src/
│   └── Main.java
├── data/
│   ├── electronics.txt
│   ├── furniture.txt
│   ├── groceries.txt
│   └── other category files
├── docs/
│   └── project_documentation.md
├── results/
│   └── sample_output.txt
└── reports/
    └── README.md
```

The filenames shown are examples of the intended organization. Refer to the actual repository for the complete list of files.

## 8. Product Data Format

Product information is stored in category-wise text files.

Example:

```text
Product ID: P9001
Product Name: Bluetooth Speaker
Location: Section A-01

Product ID: P9002
Product Name: Wireless Keyboard
Location: Section A-02
```

The Java application reads the labeled fields to extract product information.

Product IDs should be unique across the complete inventory to avoid accidental replacement or ambiguity during lookup.

## 9. Installation and Execution

### Prerequisites

- Java Development Kit (JDK) installed.
- Git installed if cloning the repository.
- VS Code or another Java-compatible editor (optional).

### Step 1: Clone the repository

```bash
git clone https://github.com/sahasragayathri02/KLH_CSE_2025-26_S9_TEAM_17_Warehouse-Inventory-System.git
```

### Step 2: Open the project directory

```bash
cd KLH_CSE_2025-26_S9_TEAM_17_Warehouse-Inventory-System
```

### Step 3: Compile the Java source

```bash
javac src/Main.java
```

### Step 4: Run the application

```bash
java -cp src Main
```

### Step 5: Use the menu

Select the required option from the displayed menu:

1. Search by Product Name (KMP)
2. Search by Product ID (HashMap)
3. Fuzzy Product Search (Levenshtein)
4. Algorithm Selection
5. Warehouse Distribution
6. Warehouse Coverage
7. Exit

**Note:** Run the application from the project root so that relative paths to the `data` directory resolve correctly. If the program uses a different data path, adjust the working directory or path accordingly.

## 10. Testing

The following test cases should be used to verify the application:

| Test Case | Expected Behavior |
|---|---|
| Search for an existing product name | Matching product is displayed |
| Search for a valid product ID | Corresponding product is retrieved |
| Search using a similar spelling | Matching products are returned according to the implemented fuzzy-search rule |
| Search for a nonexistent product | Appropriate not-found message is displayed |
| Select an algorithm type | Relevant algorithm recommendation is displayed |
| Run warehouse distribution | Maximum flow is calculated for the example network |
| Run warehouse coverage | A vertex cover is produced for the example graph |
| Enter an invalid menu option | Invalid input is handled appropriately |

Actual results should be recorded after running the final version of the application.

## 11. Limitations

- Product data is stored in text files rather than a database.
- Search performance depends on inventory size and the implementation of product loading and lookup.
- Fuzzy-search results depend on the chosen distance threshold or matching condition.
- The warehouse-distribution and coverage modules use predefined graph examples.
- The current project demonstrates selected algorithms rather than every topic in CO1–CO5.
- The application is a console-based implementation and does not currently provide a graphical interface.

## 12. Future Enhancements

- Integrate a database such as MySQL or SQLite.
- Support adding, updating, and deleting products.
- Add stock
