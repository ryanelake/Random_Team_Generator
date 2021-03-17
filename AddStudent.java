import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * The screen for the addition of a new student
 */
public class AddStudent extends Frame
{
    public AddStudent()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel middle = new Panel(new FlowLayout()); // Creates flow layout for the middle portion of the screen
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen

        Label addStudent = new Label("Add Student", Label.CENTER);
        Label name = new Label("Student Name", Label.LEFT);
        TextField entry = new TextField("", 20);
        Button close = new Button("Close");
        Button create = new Button("Add Student");

        middle.add(name);
        middle.add(entry);

        bottom.add(close);
        bottom.add(create);

        add(addStudent, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        /*
         * Creates and assigns an action listener to the text box so the student
         * is addedd when the enter key is pressed
         */
        entry.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    if(!entry.getText().equals(""))
                    {
                        Student temp = new Student(entry.getText());
                        MainHub.addAStudent(temp);
                        new MainHub();
                        dispose();
                    }
                }
            }
        );

        /*
         * Creates and assigns an action listener to the add student button so the student
         * is added when the button is clicked
         */
        create.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    if(!entry.getText().equals(""))
                    {
                        Student temp = new Student(entry.getText());
                        MainHub.addAStudent(temp);
                        new MainHub();
                        dispose();
                    }
                }
            }
        );

        /*
         * Creates and assigns an action listener to the close button so the
         * program closes when the button is clicked
         */
        close.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    new MainHub();
                    dispose();
                }
            }
        );

        setTitle("Add Student"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
