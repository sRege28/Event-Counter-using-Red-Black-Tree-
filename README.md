# Event-Counter-using-Red-Black-Tree-


This report details the structure and working of bbst, a Java program creating an event counter using red-black trees as a data structure.
This project was created in Java using Eclipse IDE over Windows and tested on a Linux VM. 
The report also describes the functionality of the event counter, the function prototypes that I used 
and the general structure of the program.


# How to Run

To run the program, simply call the makefile in the folder to compile the source code and create the executable. One can then run the program by using terminal or cmd with the command
 
java bbst <input_file commands

The input file is taken as a string specifying the location of the file in the standard input stream (System.in) , 
and the commands file can be passed as an argument to the program. The possible commands are:

Increase(id,n) : Increases the count of the event with ID id by n. If not found, inserts an event with elements<id, n>. Prints the event with updated count.

Reduce(id,n) : Reduces the count of the event with ID id by n. If count goes below 0, deletes that event from the structure. Prints the event with updated count.

Count(id) : Prints the count of the event with ID id, if present. If not, prints 0.

InRange(id1,id2): Prints the total count of the events with IDs between id1 and id2. If none present, print 0.

Prev(id) : Prints the element previous to the Id given. If none present, prints “0 0”.

Next(id) : Prints the element after the Id given. If none present, prints “0 0”.

These functions are implemented in the bbst class and are called from the main() function.

