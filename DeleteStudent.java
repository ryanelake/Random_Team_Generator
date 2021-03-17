import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DeleteStudent extends Frame
{
    public DeleteStudent()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel middle = new Panel(new FlowLayout()); // Creates flow layout for the middle portion of the screen
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen

        Label students = new Label("Students", Label.LEFT);
        Choice kidList = new Choice();

        //Adds each student to the choice list of students
        for(int i = 0; i < MainHub.periods.get(0).studentList.size(); i++)
        {
            kidList.add(MainHub.periods.get(0).studentList.get(i).name);
        }

        Button close = new Button("Close");
        Label select = new Label ("Select a Student", Label.CENTER);
        Button delete = new Button("Delete");

        middle.add(students);
        middle.add(kidList);

        bottom.add(close);
        bottom.add(select);
        bottom.add(delete);

        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        /*
         * Creates and assigns an action listener to the close button so the
         * window closes when the button is clicked
         */
        close.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    new MainHub();
                    dispose(); // "closes" window
                }
            }
        );

        /*
         * Creates and assigns an action listener to the delete button
         * that deletes the chosen student when clicked
         */
        delete.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    for(int i = 0; i < MainHub.periods.get(0).studentList.size(); i++)
                    {
                        if(MainHub.periods.get(0).studentList.get(i).name.equalsIgnoreCase(kidList.getSelectedItem()))
                        {
                            MainHub.deleteAStudent(MainHub.periods.get(0).studentList.get(i));
                        }
                    }
                    new MainHub();
                    dispose(); // "closes" window
                }
            }
        );

        setTitle("Delete Student"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
