import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * The screen for the addition of a new student
 */
public class AddStudentList extends Frame
{
    public AddStudentList()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel middle = new Panel(new FlowLayout()); // Creates flow layout for the middle portion of the screen
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen

        Label addStudent = new Label("Add Students", Label.CENTER);
        Label name = new Label("Student Name", Label.LEFT);
        TextArea entry = new TextArea("",5, 40);
        Button close = new Button("Close");
        Button create = new Button("Add Students");

        middle.add(name);
        middle.add(entry);

        bottom.add(close);
        bottom.add(create);

        add(addStudent, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        /*
         * Creates and assigns an action listener to the add students button so the
         * list of students is added when the button is clicked
         */
        create.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    if(!entry.getText().equals(""))
                    {
                        int lastIndex = 0;
                        String kids = entry.getText().replace("\r","");
                        for(int i = 0; i < kids.length(); i++)
                        {
                            if(kids.charAt(i) == '\n')
                            {
                                Student temp = new Student(kids.substring(lastIndex,i));
                                MainHub.addAStudent(temp);
                                lastIndex = i+1;
                            }
                        }
                        Student temp = new Student(kids.substring(lastIndex));
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

        setTitle("Add Students"); // Frame sets title
        setSize(500, 200);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
