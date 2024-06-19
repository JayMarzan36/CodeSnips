# CodeSnips
CodeSnips is a Java application that enables users to search a database of their programming files to find what files contain a keyword related to what the user inputs.

Demo video
[![Video](https://img.youtube.com/vi/0NBn988YiAk/0.jpg%29)](https://www.youtube.com/watch?v=0NBn988YiAk)

## Overview
The main driving program for this app is **dataParser.java** and **dataBaseSearch.java**.
**dataParser.java** is the program that takes the file **keyWords.txt** (which holds the keywords used to search the files and how the user should input) and searches a selected folder. A folder that holds program files ending with .java, .py, .cpp by default. But it can be modified to handle any file type the user wants in **extensionsToInclude.txt**.

After a folder has been inputted and parsed, the path, keywords found, and lines are put into a file called **DataBase.txt** which is then used by **dataBaseSearch.java**. **dataBaseSearch.java** takes input from the user and then searches through **DataBase.txt** for the inputted keyword and returns the file paths.

In the GUI version, the **dataBaseSearch.java** return is shown as a list of file paths that can then be clicked on to show the file's contents.

In the text/command line version, the **dataBaseSearch.java** return is shown as a list of file paths.

### Versions
In this repository, there are two versions of this application.
* Gui
* Text-based

The GUI is an app, and the text-based is more command-line driven.

## Additional info

The **DataBase.txt**, **extensionsToInclude.txt**, and **keyWords.txt** can all be found in the data folder. These files can be modified to suit the user's needs.

Modifying the following files affects such
* **DataBase.txt**
  * This is where the results of **dataParser.java** get put. The only time it is recommended to modify this file is when the user is modifying **keyWords.txt** or **extensionsToInclude.txt**.

* **extensionsToInclude.txt**
  * This is a list of the file extensions to include are, modifying such would change what files show up in **DataBase.txt**. The user can modify this file to add file extensions that work with their needs.

* **keyWords.txt**
  * This is a list of the keywords used in the **dataParser.java**, which looks through a file for said keywords and reports on whether they are in the file. Users can modify this file to add keywords that are related to the user's needs.
