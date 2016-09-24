JC=javac
default:bbst.class Tree.class Node.class
bbst.class: bbst.java
	$(JC)$(JFLAGS) bbst.java
Tree.class: bbst.java
	$(JC)$(JFLAGS) bbst.java
Node.class: bbst.java
	$(JC)$(JFLAGS) bbst.java
clean:
	rm *.class
