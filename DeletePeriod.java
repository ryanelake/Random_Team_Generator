import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * The screen dealing with the deletion of the
 * current period
 */
public class DeletePeriod extends Frame
{
    public DeletePeriod()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen
        
        Label areYouSure = new Label("Are you sure you want to delete this period?", Label.CENTER);
        
        Button yes = new Button("Yes");
        Button no = new Button("No");
        
        bottom.add(yes);
        bottom.add(no);
        
        add(areYouSure, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        
        /*
         * Creates and assigns an action listener to the no button so the
         * screen is closed when the button is clicked
         */
        no.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    new MainHub();
                    dispose();
                }
            }
        );
        
        /*
         * Creates and assigns an action listener to the yes button that
         * deletes the current period and changes to the next one
         */
        yes.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    MainHub.deleteThisPeriod();
                    new MainHub();
                    dispose();
                }
            }
        );
        
        setTitle("Delete This Period"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
