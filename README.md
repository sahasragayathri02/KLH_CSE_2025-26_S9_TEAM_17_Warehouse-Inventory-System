# 📦 Warehouse Inventory System — Product Locator

A Java-based **Warehouse Inventory System** that uses the **Knuth-Morris-Pratt (KMP) Pattern Matching Algorithm** to efficiently locate products from warehouse inventory records.

## 📌 Project Overview

In a warehouse, searching manually through multiple product records can be time-consuming. This project provides a simple product locator that allows users to enter a product name and search for it across multiple warehouse inventory files.

The system uses the **KMP string pattern matching algorithm** to search product names and displays the product's ID, name, warehouse location, and source file when a match is found.

## 🎯 Objectives

* Develop a simple warehouse product locator.
* Implement the **KMP Pattern Matching Algorithm**.
* Search product records stored across multiple text files.
* Display the location of a requested product.
* Demonstrate the practical application of string matching algorithms.
* Analyze the time and space complexity of the algorithm.

## 🧠 Data Structures & Algorithm

### KMP Pattern Matching

The project uses the **Knuth-Morris-Pratt (KMP)** algorithm for product-name searching.

KMP improves pattern searching by using an **LPS (Longest Proper Prefix which is also a Suffix)** array.

Instead of repeatedly comparing characters from the beginning after a mismatch, KMP uses the LPS array to skip unnecessary comparisons.

### Complexity

| Operation              | Complexity |
| ---------------------- | ---------- |
| LPS Array Construction | O(m)       |
| KMP Pattern Search     | O(n + m)   |
| Auxiliary Space        | O(m)       |

Where:

* `n` = length of the text
* `m` = length of the search pattern

## 🏗️ Project Structure

```text
WarehousePatternSearch/
│
├── Main.java
│
└── corpus/
    ├── electronics.txt
    ├── furniture.txt
    ├── groceries.txt
    ├── office_supplies.txt
    └── appliances.txt
```

### `Main.java`

Contains:

* User input handling
* KMP pattern matching
* LPS array generation
* File reading
* Product record processing
* Search result display

### `corpus/`

Contains the warehouse inventory records divided into different categories.

Each product record contains:

```text
Product ID
Product Name
Location
```

## 🔄 System Workflow

```text
User enters product name
          ↓
Open warehouse corpus
          ↓
Read product files
          ↓
Extract product information
          ↓
Apply KMP Pattern Search
          ↓
       Match?
       /    \
     Yes     No
      ↓       ↓
Display     Continue
product     searching
details
```

## 💻 Technologies Used

* **Java**
* **KMP Pattern Matching Algorithm**
* **File Handling**
* **BufferedReader**
* **Java Scanner**
* **Git & GitHub**
* **VS Code**

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
```

### 2. Open the project

```bash
cd WarehousePatternSearch
```

### 3. Compile the program

```bash
javac Main.java
```

### 4. Run the program

```bash
java Main
```

## 🧪 Sample Input

```text
Wireless Mouse
```

## ✅ Sample Output

```text
==============================================
       WAREHOUSE INVENTORY SYSTEM
          PRODUCT LOCATOR
==============================================

Enter product name to locate: Wireless Mouse

Searching product records...

----------------------------------------------
✓ PRODUCT FOUND
----------------------------------------------
Product ID   : P102
Product Name : Wireless Mouse
Location     : Section A-14
File         : electronics.txt
----------------------------------------------
```

## ❌ Product Not Found

If the requested product does not exist in the warehouse records:

```text
----------------------------------------------
✗ PRODUCT NOT FOUND
----------------------------------------------
No product matching "iPhone" was found.
----------------------------------------------
```

## 📚 Concepts Demonstrated

* String Pattern Matching
* KMP Algorithm
* LPS Array
* Arrays
* Strings
* File Handling
* Sequential File Processing
* User Input
* Time Complexity
* Space Complexity

## 🚀 Future Enhancements

The project can be extended with:

* Product ID-based searching
* Multiple product search results
* Adding and deleting inventory records
* Updating product locations
* A graphical user interface
* Database integration
* Inventory quantity tracking
* Sorting and filtering products
* Search history

## 👥 Project Type

**Data Structures and Algorithms Project**

### Main DSA Concept

**Knuth-Morris-Pratt (KMP) Pattern Matching Algorithm**

---

## 📄 License

This project is created for **educational and academic purposes**.
