import java.io.*;
import java.util.ArrayList;

/*
 * Holds the name of the period and the list of students for that period
 * and can be stored in an ArrayList
 */
public class Period implements Serializable
{
    String periodName;
    ArrayList<Student> studentList;
    
    public Period(String name)
    {
        periodName = name;
        studentList = new ArrayList<Student>();
    }
}
