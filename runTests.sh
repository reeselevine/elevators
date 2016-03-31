#!/bin/bash
javac *.java
java -cp . org.junit.runner.JUnitCore ElevatorControlSystemTest
rm *.class

