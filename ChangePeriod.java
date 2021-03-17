import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChangePeriod extends Frame
{
    public ChangePeriod()
    {
        setLayout(new BorderLayout()); // Sets main layout
        Panel middle = new Panel(new FlowLayout()); // Creates flow layout for the middle portion of the screen
        Panel bottom = new Panel(new FlowLayout()); // Creates flow layout for the bottom portion of the screen
        
        Label yourPeriods = new Label("Your Periods", Label.LEFT);
        Choice periodList = new Choice();
        
        //Adds each period to the choice list of periods
        for(int i = 0; i < MainHub.periods.size(); i++)
        {
            periodList.add(MainHub.periods.get(i).periodName);
        }
        
        Button close = new Button("Close");
        Label select = new Label ("Select a Period", Label.CENTER);
        Button change = new Button("Change");
        
        middle.add(yourPeriods);
        middle.add(periodList);
        
        bottom.add(close);
        bottom.add(select);
        bottom.add(change);
        
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
         * Creates and assigns an action listener to the change button so
         * the current period is changed to the one chosen on the
         * choice list
         */
        change.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    for(int i = 0; i < MainHub.periods.size(); i++)
                    {
                        if(MainHub.periods.get(i).periodName.equalsIgnoreCase(periodList.getSelectedItem()))
                        {
                            MainHub.changeCurrent(MainHub.periods.get(i));
                        }
                    }
                    new MainHub();
                    dispose(); // "closes" window
                }
            }
        );
        
        setTitle("Change Period"); // Frame sets title
        setSize(300, 150);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }
}
