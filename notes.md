# Truffula Notes
As part of Wave 0, please fill out notes for each of the below files. They are in the order I recommend you go through them. A few bullet points for each file is enough. You don't need to have a perfect understanding of everything, but you should work to gain an idea of how the project is structured and what you'll need to implement. Note that there are programming techniques used here that we have not covered in class! You will need to do some light research around things like enums and and `java.io.File`.

PLEASE MAKE FREQUENT COMMITS AS YOU FILL OUT THIS FILE.

## App.java
- This file contains the main method, which is where the program starts running.
- The program is used to print a directory tree from a given path.
- There are options that control how the tree is displayed.
- These include showing hidden files, turning color on/off, and setting the starting directory path. 

## ConsoleColor.java
- This file uses an enum which is a special class that represents a group of constants
- A color is linked to an ANSI escape code that changes how the text looks in the console 
- It also can reset the text color to the terminal's default color.

## ColorPrinter.java / ColorPrinterTest.java
- ColorPrinter- this file prints the colored text to the console using PrintStream.
- It also keeps track of the current color to print out the specific colored text if the terminal supports ANSI codes.
- ColorPrinterTest- this is the test file that checks if ColorPrinter class works correctly.

## TruffulaOptions.java / TruffulaOptionsTest.java
- TruffalaOptions- this file contains the logic for the options, like whether to display hidden files, whether to use color, and what root directory path to start from. 
- TruffulaOptionsTest- this is the test file that checks if the TruffalaOptions class works correctly. 

## TruffulaPrinter.java / TruffulaPrinterTest.java
- TruffulaPrinter- this file prints the directory tree based on the given options that handles colors and sorting files.
- TruffulaPrinterTest- this is the test file that creates a sample directory and checks if it prints correctly. 

## AlphabeticalFileSorter.java
- This file is used to sort files alphabetically by their names and ignores case differences.
- Lambda is a shorter way of writing a function. 