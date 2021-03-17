import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MainHub extends Frame
{
    static Period currentPeriod; // The period the list of kids is created from
    static ArrayList<Period> periods; // The list of periods that have been created
    static int count; // The count to help assign buttons to students

    /*
     * The constructor of the main hub, creates the main screen of the generator and shows it on screen
     */
    public MainHub()
    {
        /*
         * Initializes the ObjectInputStream to read data from a file that contains the value of
         * the list of periods, intitializes the period list with the read value and sets the 
         * current period to the first element in the list of periods
         */
        try
        {
            ObjectInputStream rollover = new ObjectInputStream(new FileInputStream("storage.txt"));
            periods = (ArrayList<Period>)rollover.readObject();
            rollover.close();
            if(periods != null)
            {
                if(periods.size() != 0)
                {
                    currentPeriod = periods.get(0);
                }
            }
        }
        catch(IOException e)
        {

        }
        catch(ClassNotFoundException f)
        {

        }

        // Creates a list of periods if there isn't one in the file to read from
        if(periods == null)
        {
            periods = new ArrayList<Period>();
        }

        setLayout(new BorderLayout()); // Sets main layout to a border layout
        Panel topButtons = new Panel(new FlowLayout()); // Creates layout for top row of buttons
        Panel display = new Panel(new BorderLayout()); // Creates layout for bottom row of buttons
        Panel bottomButtons = new Panel(new FlowLayout()); // Creates layout for display of students
        add(topButtons, BorderLayout.NORTH); // Adds layout for top row of buttons to the frame
        add(display, BorderLayout.CENTER); // Adds layout for display of students to the frame
        add(bottomButtons, BorderLayout.SOUTH); // Adds layout for bottom row of buttons to the frame

        Button changeP = new Button("Change Period");
        topButtons.add(changeP); // Creates and adds "Change Period" button to layout
        Button newP = new Button("New Period");
        topButtons.add(newP); // Creates and adds "New Period" button to layout
        Button deleteP = new Button("Delete This Period");
        topButtons.add(deleteP); // Creates and adds "Delete This Period" button to layout
        Button addS = new Button("Add Student");
        topButtons.add(addS); // Creates and adds "Add Student" button to layout
        Button deleteS = new Button("Delete Student");
        topButtons.add(deleteS); // Creates and adds "Delete Student" button to layout
        Button multiS = new Button("Add Multiple Students");
        topButtons.add(multiS); // Creates and adds "Add Multiple Students" button to layout

        /* 
         * Creates and assigns an action listener to the change period button that saves the
         * list of periods to a file, then opens the screen dealing with changing periods
         * and closes the main hub in the process
         */
        changeP.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null)
                    {
                        saveData();
                        dispose();
                        new ChangePeriod();
                    }
                    else
                    {
                        new NoPeriod();
                    }
                }
            }
        );

        /* 
         * Creates and assigns an action listener to the new period button that saves the
         * list of periods to a file, then opens the screen dealing with creating a new period
         * and closes the main hub in the process
         */
        newP.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    saveData();
                    dispose();
                    new NewPeriod();
                }
            }
        );

        /* 
         * Creates and assigns an action listener to the delete this period button that saves the
         * list of periods to a file, then opens the screen dealing with deleting the current period
         * and closes the main hub in the process
         */
        deleteP.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null)
                    {
                        saveData();
                        dispose();
                        new DeletePeriod();
                    }
                    else
                    {
                        new NoPeriod();
                    }
                }
            }
        );

        /* 
         * Creates and assigns an action listener to the add student button that saves the
         * list of periods to a file, then opens the screen dealing with adding a student to
         * the current class and closes the main hub in the process
         */
        addS.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null)
                    {
                        saveData();
                        dispose();
                        new AddStudent();
                    }
                    else
                    {
                        new NoPeriod();
                    }
                }
            }
        );

        /* 
         * Creates and assigns an action listener to the delete student button that saves the
         * list of periods to a file, then opens the screen dealing with deleting a student from
         * the current class and closes the main hub in the process
         */
        deleteS.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null && periods.get(0).studentList.size() != 0)
                    {
                        saveData();
                        dispose();
                        new DeleteStudent();
                    }
                    else
                    {
                        new NoStudents();
                    }
                }
            }
        );

        multiS.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null)
                    {
                        saveData();
                        dispose();
                        new AddStudentList();
                    }
                    else
                    {
                        new NoPeriod();
                    }
                }
            }
        );

        Button genTeams = new Button("Generate by # of teams");
        bottomButtons.add(genTeams); // Creates and adds "Generate by # of teams" button to layout
        Button genNums = new Button("Generate by # of students per team");
        bottomButtons.add(genNums); // Creates and adds "Generate by # of students per team" button to layout

        /*
         * Creates and assigns an action listener to the generate by number of teams button that
         * saves the list of periods to a file, then opens the screen dealing with generating
         * teams based on the number of teams that are wanted
         */
        genTeams.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null && periods.get(0).studentList.size() != 0)
                    {
                        saveData();
                        dispose();
                        new GenByNumTeams();
                    }
                    else
                    {
                        new NoStudents();
                    }
                }
            }
        );

        /*
         * Creates and assigns an action listener to the generate by number of studentsper team button that
         * saves the list of periods to a file, then opens the screen dealing with generating
         * teams based on the number of of students per team that is wanted
         */
        genNums.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    if(currentPeriod != null && periods.get(0).studentList.size() != 0)
                    {
                        saveData();
                        dispose();
                        new GenByNumStudents();
                    }
                    else
                    {
                        new NoStudents();
                    }
                }
            }
        );

        /*
         * Creates the main display of the main hub showing the current class
         * and students in the class, as well as buttons that show and can change
         * whether or not a student is absent. If no class is created, "No class selected"
         * is shown
         */
        if(currentPeriod == null)
        {
            Label title = new Label("No class selected", Label.CENTER);
            display.add(title, BorderLayout.NORTH); // Creates and adds a centered label if no periods are created
        }
        else
        {            
            Label title = new Label(currentPeriod.periodName, Label.CENTER);
            display.add(title, BorderLayout.NORTH); // Creates and adds centered label with name of current period

            Panel students = new Panel(new GridLayout(7,5)); // Creates the grid for each student to be added to

            count = 0;

            /*
             * Goes through the array list of students associated with the current period
             * and assigns labels and buttons to each student, then adds them to the
             * students panel
             */
            for(int i  = 0; i < currentPeriod.studentList.size(); i++)
            {
                Panel temp = new Panel(new FlowLayout()); // Creates temporary layout for student name and button to be put in

                Label name = new Label(currentPeriod.studentList.get(i).name); // Creates label of student name

                Button here = new Button(); // Creates button that shows and can change whether or not a student is absent

                /*
                 * Sets the label of the button based on whether or not a student is here
                 */
                if(currentPeriod.studentList.get(i).here)
                {
                    here.setLabel("Here");
                }
                else
                {
                    here.setLabel("Gone");
                }

                /*
                 * Creates and assigns an action listener to the button that determines whether
                 * or not a student is absent using count, which determines what student the
                 * button is for. When clicked, the button changes the students attendance
                 * depending on if they are here or not
                 */
                here.addActionListener(new ActionListener() 
                    {
                        int numStudent = MainHub.count;

                        @Override
                        public void actionPerformed(ActionEvent evt) 
                        {
                            if(currentPeriod.studentList.get(numStudent).here)
                            {
                                here.setLabel("Gone");
                                currentPeriod.studentList.get(numStudent).here = false;
                            }
                            else
                            {
                                here.setLabel("Here");
                                currentPeriod.studentList.get(numStudent).here = true;
                            }
                        }
                    }
                );

                temp.add(name); // Adds label to the temporary layout for the current student
                temp.add(here); // Adds button that changes attendance to the temporary layout for the current student

                students.add(temp); // Adds the temporary layout to the permanent layout of students

                count ++; // Adds one to count so the next student can be accessed
            }

            display.add(students, BorderLayout.CENTER); // Adds the layout of students to the main hub layout
        }

        /*
         * Creates and assigns a window listener that closes the program
         * and saves the data of the period list to a file when the
         * user exits out of the program
         */
        addWindowListener(new WindowListener()
            {
                @Override
                public void windowClosing(WindowEvent evt) {
                    try
                    {
                        ArrayList<Period> periodList = MainHub.periods; // Creates a temporary period list to be written to the file
                        ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("storage.txt")); //creates stream so data can rollover
                        saver.writeObject(periodList);
                        saver.close();
                        System.exit(0);  // terminate the program
                    }
                    catch(IOException e)
                    {

                    }
                }
                // Need to provide an empty body for compilation
                @Override public void windowOpened(WindowEvent evt) { }

                @Override public void windowClosed(WindowEvent evt) { }

                @Override public void windowIconified(WindowEvent evt) { }

                @Override public void windowDeiconified(WindowEvent evt) { }

                @Override public void windowActivated(WindowEvent evt) { }

                @Override public void windowDeactivated(WindowEvent evt) { }
            }
        );

        setTitle("Random Team Generator"); // Frame sets title
        setSize(700, 350);             // Frame sets initial size
        setVisible(true);              // Frame shows
    }

    //Creates and runs the main hub
    public static void main(String [] args)
    {
        new MainHub();
    }

    /*
     * Writes the current state of the period list to the corresponding
     * file that holds period data so it can be saved for the next use
     */
    private static void saveData()
    {
        try
        {
            ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("storage.txt")); //creates stream so data can rollover
            saver.writeObject(periods);
            saver.close();
        }
        catch(IOException e)
        {
        }
    }

    /*
     * Adds period p to the list of periods and updates the period list
     * to the file that carries period list data
     */
    public static void addPeriod(Period p)
    {
        periods.add(0,p); // Adds the period to the list at index 0, the current period
        currentPeriod = p;
        saveData();
    }

    /*
     * Changes the current period so the program can correctly output
     * the data of the class the user wants to see
     */
    public static void changeCurrent(Period p)
    {
        Period temp = periods.get(0); // Creates a temporary period equal to the current period
        currentPeriod = p;

        for(int i = 0; i < MainHub.periods.size(); i++)
        {
            if(periods.get(i).periodName.equalsIgnoreCase(p.periodName))
            {
                periods.set(0,p); // Sets current period to period the user wants to change to
                periods.set(i,temp); // Replaces the period that was wanted with the period that was at index 0
            }
        }

        saveData();
    }

    /*
     * Adds a student to the list of students corresponding to the current period
     * and saves the list of periods to the file for the next use
     */
    public static void addAStudent(Student kid)
    {
        periods.get(0).studentList.add(kid);
        saveData();
    }

    /*
     * Deletes a student from the list of students corresponding to the current period
     * and saves the list of periods to the file for the next use
     */
    public static void deleteAStudent(Student kid)
    {
        periods.get(0).studentList.remove(kid);
        saveData();
    }

    /*
     * Deletes the current period and makes the current period
     * the newest period at index 0 then saves the list of periods
     * to the file for the next use
     */
    public static void deleteThisPeriod()
    {
        periods.remove(0);

        if(periods.size() != 0) // Check to see if there are any periods left in the list
        {
            currentPeriod = periods.get(0);
        }
        else
        {
            currentPeriod = null;
        }

        saveData();
    }

    /*
     * Generates teams based on the number of teams the user wants and
     * saves the teams to a file on their desktop
     */
    public static void genByNumTeams(int numTeams)
    {
        Random rand = new Random();
        ArrayList<String> listOfKids = new ArrayList<String>(); // Creates the list of kids
        ArrayList<Team> teamList = new ArrayList<Team>(); // Creates the list of teams

        /*
         * Goes through the current list of students and adds them to
         * the temporary list of students if they are not absent
         */
        for(int i = 0; i < periods.get(0).studentList.size(); i++)
        {
            if(periods.get(0).studentList.get(i).here)
            {
                listOfKids.add(periods.get(0).studentList.get(i).name);
            }
        }

        int numPerTeam = listOfKids.size() / numTeams;

        for(int i = 0; i < numTeams; i++)
        {
            teamList.add(new Team()); // Create and add a new team to the list of teams
        }

        /*
         * Assigns students from the temporary list of students to the teams
         * based on the number of students that should be on each team
         */
        for(int i = numPerTeam * numTeams; i >= 1; i--)
        {
            int teamNum;
            int studentNum = rand.nextInt(i); // Randomly chooses which student to add to the team

            do
            {
                teamNum = rand.nextInt(numTeams); // Randomly chooses a team until the team chosen does not have the required number of students
            }
            while(teamList.get(teamNum).members.size() == numPerTeam);

            teamList.get(teamNum).members.add(listOfKids.get(studentNum)); // Adds the student to the randomly chosen team
            listOfKids.remove(studentNum); //Removes the randomly chosen student from the temporary list of students so they are not included anymore
        }

        /*
         * If there are extra kids left after assigning the "correct" number of
         * students per team, the extras are then assigned to a random team
         */
        if(listOfKids.size() > 0)
        {
            for(int i = listOfKids.size(); i >= 1; i--)
            {
                int teamNum;
                int studentNum = rand.nextInt(i); // Randomly chooses which student to add to the team

                do
                {
                    teamNum = rand.nextInt(numTeams); // Randomly chooses a team until the team chosen does not have one more tham the required number of students
                }
                while(teamList.get(teamNum).members.size() == (numPerTeam + 1));

                teamList.get(teamNum).members.add(listOfKids.get(studentNum)); // Adds the student to the randomly chosen team
                listOfKids.remove(studentNum); //Removes the randomly chosen student from the temporary list of students so they are not included anymore
            }
        }

        writeTeamFile(teamList); // Writes the teams to a file on the desktop
    }

    /*
     * Writes the given teams to a file that is stored on the users desktop
     */
    private static void writeTeamFile(ArrayList<Team> teamList)
    {
        int index = 0;
        int numSlashes = 0;
        File file = new File("test"); //Creates a dummy file to get the path from
        String destination = file.getAbsolutePath();

        /*
         * Gets the index after the third backslash, so the program knows where to add desktop
         */
        while(numSlashes != 3)
        {
            if(destination.charAt(index) == '\\')
            {
                numSlashes++;
            }
            index ++;
        }

        destination = destination.substring(0,index) + "Desktop\\Teams.txt"; // Sets the destination to the users desktop

        try
        {
            PrintStream output = new PrintStream(new File(destination));
            PrintStream console = System.out;
            System.setOut(output); // Sets the sytems output to the file on the desktop
            int maxSize = 0;
            int maxLength = 0;

            /*
             * Gets the max size of the student list so the teams with the most number
             * of students is fully printed
             */
            for(int i  = 0; i < teamList.size(); i++)
            {
                if(teamList.get(i).members.size() > maxSize)
                {
                    maxSize = teamList.get(i).members.size();
                }
            }

            /*
             * Gets the max length of all student names so spacing can be even
             */
            for(int i = 0; i < maxSize; i++)
            {
                for(int j = 0; j < teamList.size(); j++)
                {
                    if(teamList.get(j).members.size() >= i+1   )
                    {
                        if(teamList.get(j).members.get(i).length() > maxLength)
                        {
                            maxLength = teamList.get(j).members.get(i).length();
                        }
                    }
                }
            }

            for(int i  = 0; i < teamList.size(); i++)
            {
                System.out.printf("%-" + (maxLength + 5) + "s","Team " + (i+1));
            }

            System.out.println("");

            for(int i  = 0; i < teamList.size(); i++)
            {
                System.out.printf("%-" + (maxLength + 5) + "s","------");
            }

            System.out.println("");

            /*
             * Adds one student from each team to a line, then prints out
             * that line. Prints students vertically
             */
            for(int i = 0; i < maxSize; i++)
            {
                for(int j = 0; j < teamList.size(); j++)
                {
                    if(teamList.get(j).members.size() >= i+1   )
                    {
                        //teams += teamList.get(j).members.get(i) + "\t\t";
                        System.out.printf("%-" + (maxLength + 5) + "s",teamList.get(j).members.get(i));
                    }
                    else
                    {
                        //teams += "\t";
                        System.out.printf("%-" + (maxLength + 5) + "s","");
                    }
                }
                System.out.println("");
            }
        }
        catch(IOException e)
        {

        }
    }

    /*
     * Generates teams based on the number of kids per team the user wants and
     * saves the teams to a file on their desktop
     */
    public static void genByNumKids(int numKids)
    {
        Random rand = new Random();
        ArrayList<String> listOfKids = new ArrayList<String>(); // Creates the list of kids
        ArrayList<Team> teamList = new ArrayList<Team>(); // Creates the list of teams

        /*
         * Goes through the current list of students and adds them to
         * the temporary list of students if they are not absent
         */
        for(int i = 0; i < periods.get(0).studentList.size(); i++)
        {
            if(periods.get(0).studentList.get(i).here)
            {
                listOfKids.add(periods.get(0).studentList.get(i).name);
            }
        }

        int numTeams = listOfKids.size() / numKids;

        for(int i = 0; i < numTeams; i++)
        {
            teamList.add(new Team()); // Create and add a new team to the list of teams
        }

        /*
         * Assigns students from the temporary list of students to the teams
         * based on the number of students that should be on each team
         */
        for(int i = numTeams * numKids; i >= 1; i--)
        {
            int teamNum;
            int studentNum = rand.nextInt(i); // Randomly chooses which student to add to the team

            do
            {
                teamNum = rand.nextInt(numTeams); // Randomly chooses a team until the team chosen does not have the required number of students
            }
            while(teamList.get(teamNum).members.size() == numKids);

            teamList.get(teamNum).members.add(listOfKids.get(studentNum)); // Adds the student to the randomly chosen team
            listOfKids.remove(studentNum); //Removes the randomly chosen student from the temporary list of students so they are not included anymore
        }

        /*
         * If there are extra kids left after assigning the "correct" number of
         * students per team, the extras are then assigned to a random team
         */
        if(listOfKids.size() > 0)
        {
            for(int i = listOfKids.size(); i >= 1; i--)
            {
                int teamNum;
                int studentNum = rand.nextInt(i); // Randomly chooses which student to add to the team

                do
                {
                    teamNum = rand.nextInt(numTeams); // Randomly chooses a team until the team chosen does not have the required number of students
                }
                while(teamList.get(teamNum).members.size() == (numKids + 1));

                teamList.get(teamNum).members.add(listOfKids.get(studentNum)); // Adds the student to the randomly chosen team
                listOfKids.remove(studentNum); //Removes the randomly chosen student from the temporary list of students so they are not included anymore
            }
        }

        writeTeamFile(teamList); // Writes the teams to a file on the desktop
    }
}