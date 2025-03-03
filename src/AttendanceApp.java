/**
 * Basketball Stats Application
 *
 * @author RivJams
 * @version 1.0 beta
 * @since 2025.02.21
 * @see <a href="https://github.com/dejohns2/JavaSection3_AttendanceApp_Spring2025.git">GitHub Repository</a>
 */
public class AttendanceApp {

	/** Standard double dash line for display output */
	private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

	/** Standard single dash line for display output */
	private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

	/** The first section. */
	private final Course section1;

	/** The second section. */
	private final Course section2;
    
    /** Default constructor that instantiate both teams */
    public AttendanceApp() {
    	
    	section1 = new Course();
        section2 = new Course();
        
    } // end of default constructor
    
    /**
     *  Displays the Attendance App header. Example:<br>
	 *  <pre>
	 *  =========================================
	 *  Welcome to the Attendance App
	 *  =========================================
	 *  </pre>
     */
    private void displayAppHeading() {
    	
		System.out.println(DOUBLE_DASH_LINE);
		System.out.println("Welcome to the Attendance App");
		System.out.println(DOUBLE_DASH_LINE);
		System.out.println();
		
    } // end of displayAppHeading
    
    /**
     * Sets the course's names and setup each course's students. Example:<br>
	 * <pre>
	 * Enter Section 1's course name: user input
	 * -----------------------------------------
	 * Enter Section 2's course name: uer input
	 * </pre>
	 * @throw Exception if the setters throw an error back due to data validation.
     */
    private void setupCourses() throws Exception {
    	
    	String userInput;
  		
		userInput = Input.getLine("Enter Section 1's course name: ");
		section1.setName(userInput);
		setupStudents(section1);

		System.out.println();
		System.out.println(SINGLE_DASH_LINE);
		System.out.println();

		userInput = Input.getLine("Enter Section 2's course name: ");
		section2.setName(userInput);
		setupStudents(section2);
    	
    } // end of setupTeams
    
    /**
     * Sets up the course's students.<br>
	 * Calls Input.getLine to get the student's name<br>
	 * Calls Input.getIntRange to get the student's seat between 0 and 55<br>
	 * Example:<br>
	 * <pre>
     * Enter 9:00 am student's name or 'q' to quit: user input
	 * Enter Billy's seat number: user input
	 * </pre>
     * @param course The couse to setup students for.
     */
    private void setupStudents(Course course) {
    	String courseName = course.getName();
    	String name;
    	int seat;

    	while (true) {
			System.out.println();
			name = Input.getLine("Enter " + courseName + " student's name or 'q' to quit: ");
			
			if (name.equals("q"))
				return;
			
			try { 
				seat = Input.getIntRange("Enter " + name + "'s seat number: ", 0, 55);
				course.addStudent(name, seat);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Unable to add student!");
			}
			
    	}
		    	
    } // end of setupPlayers
    
    /**
     * Keeps the application running via menu options. Example:<br>
	 * <pre>
	 * -----------------------------------------
	 * Main Menu
	 * -----------------------------------------
	 * 0 = End Attendance App
	 * 1 = Take 9:00 am Attendance
	 * 2 = Take 10:00 am Attendance
	 * 3 = Display All Attendance Report
	 * -----------------------------------------
	 * Menu Choice:
	 * </pre>
     * @throws Exception updateTeamStats could throw an error if an invalid option is selected
     */
    private void mainMenu() throws Exception {
    	
    	boolean runAttendance = true;
    	int userInput;
    	
    	System.out.println();
    	System.out.println(DOUBLE_DASH_LINE);    	
    	System.out.println("Recording Attendance!");
    	System.out.println(DOUBLE_DASH_LINE);
    	System.out.println();
    	
    	
    	while (runAttendance) {
    		
    		System.out.println(SINGLE_DASH_LINE);
    		System.out.println("Main Menu");
    		System.out.println(SINGLE_DASH_LINE);
    		
    		System.out.println("0 = End Attendance App");
    		System.out.println("1 = Take " + section1.getName() + " Attendance");
    		System.out.println("2 = Take " + section2.getName() + " Attendance");
    		System.out.println("3 = Display All Attendance Report");
    		
    		System.out.println(SINGLE_DASH_LINE);
    		userInput = Input.getIntRange("Menu Choice: ", 0, 3);
    		System.out.println(SINGLE_DASH_LINE);
    		
    		System.out.println();
    		
    		switch (userInput) {
    		case 1:
    			runAttendance = false;
    			displayDetailReports();
        		System.out.println();
        		break;
        		
    		case 2:
    		case 3:
    			
    			if (userInput == 1)
    				courseAttendance(section1);
    			else
    				courseAttendance(section2);
    			
    			System.out.println();
				/** Look at later in case of change */
        		displayDetailReports();
        		System.out.println();
        		break;
        		
    		case 4:
				/** Look at later in case of change */
    			displayDetailReports();
    			break;
    			
    		default:
    			System.out.println("Invalid menu choice = " + userInput);
    			
    		} // end of switch
    	}

    } // end of runAttendance
    
    /**
     * Update the selected course's stats.<br>
	 * Calls Input.getIntRange using range from 1 and 55.<br>
	 * Example: <br>
	 * <pre>
	 * Enter Student's seat # user input
	 * </pre>
	 * @param course The course to update stats for.
     * @throws Exception getStudent could throw an invalid seat error
     */
    private void courseAttendance(Course course) throws Exception {

    	int seat;
    	Student student;
    	
		while (true) {
			seat = Input.getIntRange("Enter " + course.getName() + "'s seat # ", 1, 55);
			
			student = course.getStudent(seat);
			
			if (student == null) {
				System.out.println("Invalid #, please try again!");
				continue;
			}
			
			studentAttendance(student);
			
			break;
		}
			
		System.out.println();
		System.out.println(SINGLE_DASH_LINE);
			
    } // end of updateTeam
    
    /**
     * Displays the student's name along with the stats menu. Example:
	 * <pre>
 	 * -----------------------------------------
	 * Enter #10 Billy Attendance
	 * -----------------------------------------
	 * 1 = On Time
	 * 2 = Late
	 * 3 = Excused
	 * 4 = Unexcused
	 * -----------------------------------------
	 * Enter Stat Type: 0
	 * -----------------------------------------
	 * #10 Billy OnTime=1 Late=0 Excused=0 Unexcused=0
	 * -----------------------------------------
     * </pre>
     * @param student The student to enter attendance for
     */
    private void studentAttendance(Student student) {
    	int type;

        System.out.println();
		
		System.out.println(SINGLE_DASH_LINE);
		System.out.println("Enter #" + student.getSeat() + " " +student.getName() + " Attendance");
		System.out.println(SINGLE_DASH_LINE);
		
		System.out.println("0 = On Time");
		System.out.println("1 = Late");
		System.out.println("2 = Excused");
		System.out.println("3 = Unexcused");
		
		System.out.println(SINGLE_DASH_LINE);
		type = Input.getIntRange("Enter Status: ", 0, 3);
		System.out.println(SINGLE_DASH_LINE);
		
		try {
			student.updateAttendance(type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Unable to update student's stats!");
		}

		student.displayAttendance();

    }
    
    /**
     * Display the updated attendance for both courses. Calls the Course's display
	 * for both the home and away teams.
     */
    private void displayDetailReports() {
    	
    	section1.displaySummaryReport();
    	section2.displaySummaryReport();
    	
    }
    
	/**
	 * Main method that creates the AttendanceApp object and then
	 * setups up the courses and runs the application via menu options.
	 * <br>
	 * 1) creates a new attendance tracker<br>
	 * 2) calls the displayAppHeading method<br>
	 * 3) using a try-catch calls setupTeams and mainMenu methods<br>
	 * <br>
	 * @param args No command line input args are used for this application
     */
	public static void main(String[] args) {

		AttendanceApp attendanceboard = new AttendanceApp();

		attendanceboard.displayAppHeading();
		
		try {
			attendanceboard.setupCourses();
			attendanceboard.mainMenu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry but this program ended with an error. Please contact Princess Debbie!");
		}
		
		Input.sc.close();
		
	} // end of main
	
} // end of BB_Stats_App class
