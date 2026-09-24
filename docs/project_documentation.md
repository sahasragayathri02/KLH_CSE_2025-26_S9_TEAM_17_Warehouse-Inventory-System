# Warehouse Inventory System – Product Locator

## 1. Introduction

The Warehouse Inventory System – Product Locator is a Java-based Data Structures and Algorithms project. The system is designed to locate products in a warehouse inventory using the Knuth-Morris-Pratt (KMP) pattern matching algorithm.

The inventory data is divided into different categories such as electronics, furniture, groceries, office supplies, and appliances. The user enters a product name, and the system searches the inventory records to identify the matching product and its warehouse location.

---

## 2. Problem Statement

In a warehouse containing a large number of products, manually searching inventory records can be time-consuming and inefficient.

The objective of this project is to develop a simple product locator that uses an efficient string pattern matching algorithm to search product records and identify the location of a requested product.

---

## 3. Objectives

The main objectives of the project are:

* To develop a warehouse product locator using Java.
* To implement the Knuth-Morris-Pratt pattern matching algorithm.
* To store warehouse inventory in categorized text files.
* To search product names efficiently.
* To display the Product ID, Product Name, and Warehouse Location.
* To demonstrate the practical application of Data Structures and Algorithms.
* To analyze the time and space complexity of the KMP algorithm.

---

## 4. System Overview

The system consists of the following main components:

1. User Input
2. Inventory Data Files
3. File Reading Module
4. Product Record Extraction
5. KMP Pattern Matching
6. Product Location Display

The user provides a product name as input. The program reads the inventory files stored in the `data` directory and searches the product names using KMP pattern matching.

If the product is found, the system displays its Product ID, Product Name, Location, and source file.

---

## 5. Dataset

The project uses five categorized inventory files:

* `electronics.txt`
* `furniture.txt`
* `groceries.txt`
* `office_supplies.txt`
* `appliances.txt`

Each product record contains:

* Product ID
* Product Name
* Location

The dataset contains 150 product records in total, with 30 products in each category.

---

## 6. Algorithm Used

### Knuth-Morris-Pratt (KMP) Pattern Matching

The Knuth-Morris-Pratt algorithm is a string searching algorithm used to find occurrences of a pattern within a text.

Instead of repeatedly comparing characters from the beginning of the pattern after a mismatch, KMP uses information about previously matched characters to skip unnecessary comparisons.

The algorithm uses an LPS array.

### LPS Array

LPS stands for:

**Longest Proper Prefix which is also a Suffix**

The LPS array stores the length of the longest proper prefix of the pattern that is also a suffix for each position in the pattern.

This allows the algorithm to efficiently continue searching after a mismatch.

---

## 7. Working of the System

The system works through the following steps:

### Step 1: Accept User Input

The program asks the user to enter the product name.

Example:

`Wireless Mouse`

### Step 2: Access Inventory Directory

The program accesses the `data` directory containing the inventory files.

### Step 3: Read Inventory Files

Each inventory file is opened and read line by line using Java file handling.

### Step 4: Extract Product Information

The program identifies:

* Product ID
* Product Name
* Location

from each product record.

### Step 5: Apply KMP

The KMP algorithm compares the requested product name with the stored product name.

The comparison is case-insensitive.

### Step 6: Display Result

If a match is found, the program displays:

* Product ID
* Product Name
* Location
* Source File

If no match is found, the program displays a Product Not Found message.

---

## 8. Pseudocode

```text
START

Display project title

Read product name from user

Set found = false

Open data directory

FOR each inventory file

    Read file line by line

    Extract Product ID
    Extract Product Name
    Extract Location

    Apply KMP search on Product Name

    IF product matches search pattern

        Display Product ID
        Display Product Name
        Display Location
        Display source file

        Set found = true

    END IF

END FOR

IF found is false

    Display Product Not Found

END IF

STOP
```

---

## 9. Complexity Analysis

Let:

* `n` = length of the text
* `m` = length of the search pattern

### LPS Construction

Time Complexity:

`O(m)`

### KMP Pattern Search

Time Complexity:

`O(n + m)`

### Auxiliary Space

Space Complexity:

`O(m)`

The KMP algorithm is efficient because it avoids unnecessary repeated comparisons.

---

## 10. Technologies Used

* Java
* Knuth-Morris-Pratt Algorithm
* File Handling
* BufferedReader
* Scanner
* Arrays
* Strings
* Visual Studio Code
* Git
* GitHub

---

## 11. Project Structure

```text
WarehousePatternSearch/
│
├── src/
│   └── Main.java
│
├── data/
│   ├── electronics.txt
│   ├── furniture.txt
│   ├── groceries.txt
│   ├── office_supplies.txt
│   └── appliances.txt
│
├── docs/
│   └── project_documentation.md
│
├── results/
│   └── sample_output.txt
│
└── reports/
    └── README.md
```

---

## 12. Testing

The system was tested using both successful and unsuccessful product searches.

### Successful Search Cases

* Wireless Mouse
* Microwave Oven
* Office Chair
* Basmati Rice

### Unsuccessful Search Case

* iPhone

The successful searches returned the corresponding product details and warehouse locations, while the unsuccessful search displayed a Product Not Found message.

---

## 13. Expected Outcome

The system should successfully locate products from the warehouse inventory and display their corresponding product ID, name, warehouse location, and source file.

The system should also correctly identify products that are not present in the inventory.

---

## 14. Limitations

* The inventory is currently stored in text files.
* The system does not maintain product quantities.
* The system does not provide a graphical user interface.
* Product records are currently static.
* The current implementation focuses primarily on product name searching.

---

## 15. Future Enhancements

Possible future improvements include:

* Database integration
* Inventory quantity tracking
* Product addition and deletion
* Product location updates
* Product ID searching
* Sorting and filtering
* Graphical User Interface
* Search history
* Inventory management dashboard

---

## 16. Conclusion

The Warehouse Inven
