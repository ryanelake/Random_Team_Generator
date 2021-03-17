import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * Holds the name of a student and whether or not they are here
 * and can be stored in an ArrayList
 */
public class Student implements Serializable
{
    String name;
    boolean here;

    public Student(String name)
    {
        this.name = name;
        here = true;
    }
}
