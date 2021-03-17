import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * The error screen for when there are no periods created
 */
public class NoPeriod extends Frame
{
    public NoPeriod()
    {
        setLayout(new BorderLayout()); // The layout is set
        
        Label message = new Label("There are not currently any periods.", Label.CENTER);
        Button ok = new Button("Ok");
        
        add(message, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);
        
        /*
         * Creates and assigns an action listener to the ok button so the program is closed
         * when the ok button is clicked
         */
        ok.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    dispose();
                }
            }
        );
        
        setTitle("Error"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
