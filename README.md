## Overview

This project implements a simplified version of Shamir's Secret Sharing algorithm to find the constant term c of an unknown polynomial. The polynomial's roots are provided in a JSON format, with each root encoded in a specified base. The code reads these roots, decodes the values, and uses Lagrange interpolation to determine the constant term.
## Prerequisites

- Java Development Kit (JDK) 8 or higher
- A text editor or Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse

## Setup Instructions

1. **Download or Clone the Repository**

   If you have this code in a repository, clone it using:
   ```bash
   git clone <repository-url>

## Navigate Project Directory

 If you have this code in a repository, clone it using:
   ```bash
cd <project-directory>
```
## 2. Install JDK
Ensure you have JDK installed. You can download it from Oracle’s JDK website or use OpenJDK.

Verify the installation by running:

 ```bash
java -version
 ```
3. Compile the Java Program
Open a terminal or command prompt and navigate to the directory containing Main.java.

Compile the Java program using the javac command:
```bash
javac Main.java
```
4. Run the Java Program
Execute the compiled Java program using the java command:
```bash
java Main
```
The program will output the constant term computed by Lagrange interpolation based on the provided input data.
