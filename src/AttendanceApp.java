/**
 * Basketball Stats Application
 *
 * @author RivJams
 * @version 1.0 beta
 * @since 2025.02.21
 * @see <a href="https://github.com/dejohns2/JavaSection3_AttendanceApp_Spring2025.git">GitHub Repository</a>
 */
public class AttendanceApp {
	
    /** The home team playing. */
    private final Course section1;
    
    /** The away team playing. */
    private final Course section2;

	/** Standard double dash line for display output */
	private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

	/** Standard single dash line for display output */
	private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');
    
    /** Default constructor that instantiate both teams */
    public AttendanceApp() {
    	
    	section1 = new Course();
        section2 = new Course();
        
    } // end of default constructor
    
    /**
     *  Displays the BB Stats App header. Example:<br>
	 *  <pre>
	 *  =========================================
	 *  Welcome to the BB Scoreboard App
	 *  =========================================
	 *  </pre>
     */
    private void displayAppHeading() {
    	
		System.out.println(DOUBLE_DASH_LINE);
		System.out.println("Welcome to the BB Scoreboard App");
		System.out.println(DOUBLE_DASH_LINE);
		System.out.println();
		
    } // end of displayAppHeading
    
    /**
     * Sets the teams names and calls setupPlayers to setup each team's players. Example:<br>
	 * <pre>
	 * Enter the HOME Team name: user input
	 * -----------------------------------------
	 * Enter the AWAY Team name: user input
	 * </pre>
	 * @throw Exception if the setters throw an error back due to data validation.
     */
    private void setupCourses() throws Exception {
    	
    	String userInput;
  		
		userInput = Input.getLine("Enter the HOME Team name: ");
		section1.setName(userInput);
		setupPlayers(section1);

		System.out.println();
		System.out.println(SINGLE_DASH_LINE);
		System.out.println();

		userInput = Input.getLine("Enter the AWAY TEAM name: ");
		section2.setName(userInput);
		setupPlayers(section2);
    	
    } // end of setupTeams
    
    /**
     * Sets up the team's players.<br>
	 * Calls Input.getLine to get the player's name<br>
	 * Calls Input.getIntRange to get the player's jersery between 0 and 55<br>
	 * Example:<br>
	 * <pre>
     * Enter Wildcats player's name or 'q' to quit: user input
	 * Enter Billy's jersey number: user input
	 * </pre>
     * @param team The team to setup players for.
     */
    private void setupStudents(Course course) {
    	String courseName = course.getName();
    	String name;
    	int seat;

    	while (true) {
			System.out.println();
			name = Input.getLine("Enter " + courseName + " player's name or 'q' to quit: ");
			
			if (name.equals("q"))
				return;
			
			try { 
				seat = Input.getIntRange("Enter " + name + "'s jersey number: ", 0, 55);
				course.addStudent(name, seat);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Unable to add student!");
			}
			
    	}
		    	
    } // end of setupPlayers
    
    /**
     * Keeps the game running via menu options. Example:<br>
	 * <pre>
	 * -----------------------------------------
	 * Main Menu
	 * -----------------------------------------
	 * 0 = End Game
	 * 1 = Enter Wildcats Team's Stats
	 * 2 = Enter Blue Devils Team's Stats
	 * 3 = Display Game Stats
	 * -----------------------------------------
	 * Menu Choice:
	 * </pre>
     * @throws Exception updateTeamStats could throw an error if an invalid option is selected
     */
    private void mainMenu() throws Exception {
    	
    	boolean playGame = true;
    	int userInput;
    	
    	System.out.println();
    	System.out.println(DOUBLE_DASH_LINE);    	
    	System.out.println("Game Started!");
    	System.out.println(DOUBLE_DASH_LINE);
    	System.out.println();
    	
    	
    	while (playGame) {
    		
    		System.out.println(SINGLE_DASH_LINE);
    		System.out.println("Main Menu");
    		System.out.println(SINGLE_DASH_LINE);
    		
    		System.out.println("0 = End Game");
    		System.out.println("1 = Enter " + section1.getName() + " Team's Stats");
    		System.out.println("2 = Enter " + section2.getName() + " Team's Stats");
    		System.out.println("3 = Display Game Stats");
    		
    		System.out.println(SINGLE_DASH_LINE);
    		userInput = Input.getIntRange("Menu Choice: ", 0, 3);
    		System.out.println(SINGLE_DASH_LINE);
    		
    		System.out.println();
    		
    		switch (userInput) {
    		case 0:
    			playGame = false;
    			updateScoreboard();
        		System.out.println();
        		break;
        		
    		case 1:
    		case 2:
    			
    			if (userInput == 1)
    				updateTeamStats(section1);
    			else
    				updateTeamStats(section2);
    			
    			System.out.println();
        		updateScoreboard();
        		System.out.println();
        		break;
        		
    		case 3:
    			displayGameStatus();
    			break;
    			
    		default:
    			System.out.println("Invalid menu choice = " + userInput);
    			
    		} // end of switch
    	}

    } // end of playGame
    
    /**
     * Update the select team's stats.<br>
	 * Calls Input.getIntRange using range from 1 and 55.<br>
	 * Example: <br>
	 * <pre>
	 * Enter Wildcats's Jersey # user input
	 * </pre>
	 * @param team The team to update stats for.
     * @throws Exception getPlayer could throw an invalid jersey error
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
			
			courseAttendance(student);
			
			break;
		}
			
		System.out.println();
		System.out.println(SINGLE_DASH_LINE);
			
    } // end of updateTeam
    
    /**
     * Displays the player's name along with the stats menu. Example:
	 * <pre>
 	 * -----------------------------------------
	 * Enter #10 Billy Stats
	 * -----------------------------------------
	 * 0 = foul
	 * 1 = free throw
	 * 2 = 2pt field goal
	 * 3 = 3pt field goal
	 * -----------------------------------------
	 * Enter Stat Type: 2
	 * -----------------------------------------
	 * #10 Billy Fouls=0 Points=2
	 * -----------------------------------------
     * </pre>
     * @param player The player to enter stats for
     */
    private void updatePlayerStats(Player player) {
    	int type;

        System.out.println();
		
		System.out.println(SINGLE_DASH_LINE);
		System.out.println("Enter #" + player.getJersey() + " " +player.getName() + " Stats");
		System.out.println(SINGLE_DASH_LINE);
		
		System.out.println("0 = foul");
		System.out.println("1 = free throw");
		System.out.println("2 = 2pt field goal");
		System.out.println("3 = 3pt field goal");
		
		System.out.println(SINGLE_DASH_LINE);
		type = Input.getIntRange("Enter Stat Type: ", 0, 3);
		System.out.println(SINGLE_DASH_LINE);
		
		try {
			player.updateStats(type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Unable to update player's stats!");
		}

		player.displayStats();

    }
    
    /**
     * Display the updated Scoreboard for both teams. Calls the Team's displayTeamStats
	 * for both the home and away teams.
     */
    private void updateScoreboard() {
    	
    	homeTeam.displayTeamStats();
    	awayTeam.displayTeamStats();
    	
    }
    
    /**
     * Display all team player's detail stats. call the displayDetailsStats
	 * for both the home and away teams.
     */
    private void displayGameStatus() {

    	homeTeam.displayDetailStats();
    	awayTeam.displayDetailStats();

    } // end of playGame
    
	/**
	 * Main method that creates the BB_Stats_App object and then
	 * setups up the teams and play the game via menu options.
	 * <br>
	 * 1) creates a new scoreboard<br>
	 * 2) calls the displayAppHeading method<br>
	 * 3) using a try-catch calls setupTeams and mainMenu methods<br>
	 * <br>
	 * @param args No command line input args are used for this application
     */
	public static void main(String[] args) {

		AttendanceApp scoreboard = new AttendanceApp();

		scoreboard.displayAppHeading();
		
		try {
			scoreboard.setupTeams();
			scoreboard.mainMenu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry but this program ended with an error. Please contact Princess Debbie!");
		}
		
		Input.sc.close();
		
	} // end of main
	
} // end of BB_Stats_App class
