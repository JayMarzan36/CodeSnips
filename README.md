# CodeSnips
CodeSnips is a java application that enables users to search a database of their own programming files to find what files contain a keyword that is related to what the user inputs.

<video width = "320" height = "240" controls>
    <source src="documentation/Video/CodeSnipsDemo.mp4" type="video/mp4">
    Your browser does not support HTML5 video.
</video>

## Overview
The main driving program for this app is **dataParser.java** and **dataBaseSearch.java**.
**dataParser.java** is the program that takes the file **keyWords.txt** (which holds the keywords that are used to search the files and by how the user should input) and searches a selected folder. A folder which holds program files ending with .java, .py, .cpp by default. But which can be modified to handle any file type the user wants in **extensionsToInclude.txt**.

After a folder has been inputted and parsed through, the path, keywords found, and lines are put into a file called **DataBase.txt** which is then used by **dataBaseSearch.java**. **dataBaseSearch.java** takes input from the user and then searches through **DataBase.txt** for the inputted keyword and returns the file paths.

In the gui version, the **dataBaseSearch.java** return is shown as a list of file paths that then can be clicked on to show the contents of the file.

In the text/command line version, the **dataBaseSearch.java** return is shown as a list of file paths.

### Versions
In this repository there are two versions of this application.
* Gui
* Text based

The gui is an app, and the text based is more command line driven. 