import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * The screen for the generation of a new period
 */
public class NewPeriod extends Frame
{
    public NewPeriod()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel middle = new Panel(new FlowLayout()); // Creates flow layout for the middle portion of the screen
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen

        Label addPeriod = new Label("Add Period", Label.CENTER);
        Label name = new Label("Period Name", Label.LEFT);
        TextField entry = new TextField("",20);
        Button close = new Button("Close");
        Button create = new Button("Create Period");

        middle.add(name);
        middle.add(entry);

        bottom.add(close);
        bottom.add(create);

        add(addPeriod, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        /*
         * Creates and assigns an action listener to the text box so the period
         * is created when the enter key is pressed
         */
        entry.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    if(!entry.getText().equals(""))
                    {
                        Period temp = new Period(entry.getText()); // creates the period for the main hub to read and write to
                        MainHub.addPeriod(temp);
                        new MainHub();
                        dispose();
                    }
                }
            }
        );

        /*
         * Creates and assigns an action listener to the create period button so the period
         * is created when the button is clicked
         */
        create.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    if(!entry.getText().equals(""))
                    {
                        Period temp = new Period(entry.getText()); // creates the period for the main hub to read and write to
                        MainHub.addPeriod(temp);
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

        setTitle("Add Period"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
