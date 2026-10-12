# Warehouse Inventory System – Product Locator

## 1. Project Information 

**Project Title:** Warehouse Inventory System – Product Locator  
**Domain:** Data Structures and Algorithms  
**Programming Language:** Java  
**Development Environment:** Visual Studio Code  
**Repository:** [KLH CSE 2025–26 S9 Team 17](https://github.com/sahasragayathri02/KLH_CSE_2025-26_S9_TEAM_17_Warehouse-Inventory-System)

### Team Members

| Name | Student ID |
|---|---|
| Ch Sahasra Gayathri | 25200303036 |
| V Sameer Reddy | 2520030276 |
| Vennela Edala | 252030288 |

**Faculty Supervisor:** [Dr. Vinay Kumar]

---

## 2. Abstract

The Warehouse Inventory System – Product Locator is a Java-based application designed to simplify product searching and demonstrate the practical application of Data Structures and Algorithms in warehouse management.

The system supports product-name searching using the Knuth–Morris–Pratt (KMP) algorithm, product-ID lookup using a HashMap, and approximate product-name matching using Levenshtein distance. Additional algorithm modules demonstrate algorithm selection, warehouse distribution using the Edmonds–Karp maximum-flow algorithm, and warehouse coverage using a Vertex Cover approximation.

The project provides a menu-driven interface and uses structured product records to demonstrate how algorithmic techniques can support inventory retrieval and warehouse-related optimization problems.

## 3. Problem Statement

Searching for products in a warehouse can become difficult when inventory records grow in size. Simple searching methods may require repeated comparisons, and users may fail to locate products when they enter incomplete or slightly incorrect names.

A warehouse may also need to study how inventory moves through a distribution network and how important locations can be selected for monitoring or coverage.

This project demonstrates algorithmic solutions to these problems through efficient searching, approximate matching, maximum-flow computation, and graph-based coverage.

## 4. Objectives

- Implement efficient product-name searching using KMP.
- Enable product-ID lookup using a HashMap.
- Support approximate name matching using Levenshtein distance.
- Demonstrate algorithm selection based on problem requirements.
- Model warehouse distribution using maximum flow.
- Demonstrate approximate vertex cover for warehouse coverage.
- Apply Data Structures and Algorithms concepts to a practical scenario.
- Evaluate the algorithms using time and space complexity.

## 5. Technologies Used

- **Java:** Application development.
- **Visual Studio Code:** Code editing and execution.
- **Java Collections Framework:** HashMap and queue-based operations.
- **File Handling:** Reading structured product records.
- **Graph Algorithms:** Breadth-First Search, maximum flow, and vertex cover approximation.
- **Git and GitHub:** Version control and team collaboration.

## 6. System Modules

### 6.1 Product-Name Search – KMP Algorithm

The Knuth–Morris–Pratt algorithm searches for a pattern within a text without repeatedly comparing characters that have already been matched.

The algorithm preprocesses the search pattern to build an LPS (Longest Proper Prefix which is also a Suffix) array. This helps it skip unnecessary comparisons.

**Purpose:** Locate products by name.

**Time Complexity:** O(n + m)  
**Auxiliary Space Complexity:** O(m)

Here, n is the length of the searched text and m is the length of the pattern.

### 6.2 Product-ID Search – HashMap

A HashMap stores products using their unique product IDs as keys. This allows the program to retrieve a product directly using its ID.

**Purpose:** Find a product using its unique identifier.

**Average Time Complexity:** O(1)  
**Worst-Case Time Complexity:** O(n)

The worst-case bound depends on hash collisions and the implementation.

### 6.3 Fuzzy Product Search – Levenshtein Distance

Levenshtein distance measures the minimum number of single-character insertions, deletions, and substitutions required to transform one string into another.

The system can use this distance to identify product names that resemble the user's search term.

**Purpose:** Help users find products despite small spelling mistakes.

**Time Complexity:** O(mn) per string comparison  
**Space Complexity:** O(mn) for the standard dynamic programming table

Here, m and n represent the lengths of the two strings.

### 6.4 Algorithm Selection – CO1

This module demonstrates how an algorithm can be selected according to the problem's requirements, input characteristics, expected performance, and available resources.

The choice of algorithm should consider correctness, time complexity, space complexity, and suitability for the task.

**Purpose:** Demonstrate algorithm-selection principles.

### 6.5 Warehouse Distribution – Edmonds–Karp Algorithm

This module models a distribution network as a directed graph. Vertices represent network locations, and edges represent connections with limited capacities.

The Edmonds–Karp algorithm repeatedly uses Breadth-First Search (BFS) to find augmenting paths in the residual graph. It increases the flow along these paths until no further augmenting path can be found.

**Purpose:** Calculate the maximum possible flow through the modeled network.

**Time Complexity:** O(VE²)

Here, V represents the number of vertices and E represents the number of edges.

**Note:** The result applies to the network and capacities configured in the program; it does not represent live warehouse logistics.

### 6.6 Warehouse Coverage – Vertex Cover Approximation

The Vertex Cover problem involves selecting vertices so that every edge in an undirected graph has at least one selected endpoint.

An approximation algorithm can select an uncovered edge and add both of its endpoints to the cover. Repeating this process until all edges are covered produces a valid vertex cover.

**Purpose:** Demonstrate a graph-based approach to selecting locations for coverage.

**Approximation Guarantee:** The standard edge-based 2-approximation algorithm produces a cover of size at most twice the size of a minimum vertex cover.

**Time Complexity:** O(V + E) for a suitable implementation using adjacency information and covered-edge tracking.

Here, V represents vertices and E represents edges.

## 7. Algorithm and Course Outcome Mapping

| Module | Algorithm / Concept | Course Outcome |
|---|---|---|
| Product-name search | KMP pattern matching | CO2 |
| Product-ID search | HashMap | Supporting data structure |
| Fuzzy search | Levenshtein dynamic programming | CO3, as an application of DP |
| Algorithm selection | Complexity and algorithm analysis | CO1 |
| Warehouse distribution | Edmonds–Karp maximum flow | CO4 |
| Warehouse coverage | Vertex Cover approximation | CO5 |

The mapping should be verified against the official course-outcome definitions provided by the faculty.

## 8. System Workflow

1. The program starts and displays the main menu.
2. The user selects a product-search or algorithm module.
3. For product searches, the program reads the inventory records and processes the requested search.
4. The selected algorithm performs the operation.
5. The program displays the matching products or algorithm result.
6. The user can return to the menu to perform another operation.
7. Selecting Exit terminates the program.

## 9. Data File Format

Product records use labeled fields. A record may follow this format:

    Product ID: P9001
    Product Name: Bluetooth Speaker
    Location: Section A-01

The actual field labels and record structure must match the format expected by the Java file parser.

Category-based inventory files can be stored in the project's `data/` directory. The program must use the correct file paths and handle missing or incorrectly formatted files appropriately.

## 10. Project Structure

    WarehousePatternSearch/
    ├── README.md
    ├── src/
    │   └── Main.java
    ├── data/
    ├── docs/
    │   └── project_documentation.md
    ├── results/
    │   └── sample_output.txt
    └── reports/
        └── README.md

This is the intended structure. The documentation should be adjusted if the actual repository differs.

## 11. Installation and Execution

### Prerequisites

- Java Development Kit (JDK) installed.
- Visual Studio Code or another Java-compatible editor.
- The project repository cloned or downloaded.
- Required inventory data files available in the expected directory.

### Compile

From the project root, run:

    javac src/Main.java

### Execute

    java -cp src Main

These commands assume that `Main.java` has no external package declaration and that its data-file paths work when executed from the project root.

## 12. Testing Strategy

The following tests should be performed before submission.

| Test Case | Expected Behaviour |
|---|---|
| Search for an existing product name | Matching product is displayed |
| Search for a nonexistent name | No-match message is displayed |
| Search using a valid product ID | Corresponding product is displayed |
| Search using a misspelled name | Similar product may be identified |
| Run algorithm-selection module | Selection information is displayed |
| Run maximum-flow module | Maximum flow for the configured graph is displayed |
| Run vertex-cover module | A valid cover for the configured graph is displayed |
| Select Exit | Program terminates correctly |
| Missing or invalid data file | Program handles the error appropriately |

These are test cases and expected outcomes, not claims that every test has already passed. Record the actual results after executing the program.

## 13. Limitations

- Product results depend on the quality and format of the stored inventory data.
- HashMap lookup assumes product IDs are handled consistently.
- Levenshtein distance can require substantial time and memory for long strings.
- The distribution module uses a predefined graph and capacity values unless configurable input is implemented.
- The coverage module demonstrates an approximation rather than guaranteeing a minimum vertex cover.
- The application is a demonstration project and does not automatically connect to a live warehouse database.

## 14. Future Enhancements

- Add a graphical user interface.
- Integrate a database for persistent inventory storage.
- Support adding, editing, and deleting products.
- Add sorting and category filters.
- Allow users to configure distribution networks.
- Improve input validation and error handling.
- Add automated unit tests.
- Generate reports on search performance and inventory distribution.

## 15. Team Contributions

The following is a suggested contribution outline. Update it to accurately reflect the work completed by each member.

- **Ch Sahasra Gayathri:** Project coordination, integration, algorithm implementation, and documentation.
- **V Sameer Reddy:** Algorithm implementation, inventory-data preparation, and testing.
- **Vennela Edala:** Algorithm implementation, testing, and project documentation.

## 16. Conclusion

The Warehouse Inventory System – Product Locator demonstrates how Data Structures and Algorithms can be applied to inventory searching and warehouse-network problems. It combines string matching, hashing, dynamic programming, graph traversal, maximum flow, and approximation techniques within a menu-driven Java application.

The project provides a foundation for understanding algorithm efficiency and demonstrates how different computational techniques can address different requirements in a warehouse environment.

## 17. References

- Java documentation: https://docs.oracle.com/en/java/
- Knuth–Morris–Pratt string-searching algorithm.
- Levenshtein distance and dynamic programming.
- Edmonds–Karp maximum-flow algorithm.
- Vertex Cover and approximation algorithms.
