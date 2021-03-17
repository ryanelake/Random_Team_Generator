import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GenByNumStudents extends Frame
{
    public GenByNumStudents()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel middle = new Panel(new FlowLayout()); // Creates flow layout for the middle portion of the screen
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen

        Label numKids = new Label("Enter Number of Students per Team", Label.CENTER);
        Label name = new Label("# of students:", Label.LEFT);
        TextField entry = new TextField("", 20);
        Button close = new Button("Close");
        Button create = new Button("Generate");

        middle.add(name);
        middle.add(entry);

        bottom.add(close);
        bottom.add(create);

        add(numKids, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        /*
         * Creates and assigns an action listener to the generate button
         * so the teams are generated when the button is clicked
         */
        create.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    int numKids = Integer.valueOf(entry.getText());
                    MainHub.genByNumKids(numKids);
                    new MainHub();
                    dispose();
                }
            }
        );

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
                    dispose();
                }
            }
        );

        setTitle("Generate by Number of Students"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
