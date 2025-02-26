import java.util.ArrayList;
import java.util.List;

/**
 * Used to keep track of all seats in the course along with all of their attendance totals.
 * @author RivJams
 * @author Keaton2319
 * @since 2025.02.22
 * @version 1.0 beta
 * @see <a href="https://github.com/Keaton2319/Attendance_App">GitHub Repository</a>
 */
public class Course {

	/**
	 * The course's name.
	 */
	private String name;

	/** The students in class. */
	private final List<Student> allStudents;

	/** Sets the course's name to "Unknown", and assigns students to an empty new ArrayList */
	public Course() {
		name = "Unknown";
		allStudents = new ArrayList<>();
	}

	/**
	 * Calls the default constructor, and then set's the course's name using the setter for data validation.
	 * @param name the course's name
	 * @throw Exception if the setName fails due to a blank name
	 */
	public Course(String name) throws Exception {
		this();
		setName(name);
	}

	/**
	 * Gets the course's name.
	 * @return The course's name.
	 */
	public String getName() { return name; }

	/**
	 * Set the course's name.
	 * @param name the course's name
	 * @throws Exception if the team's name is blank (whitespace or empty)<br>
	 * Error Example: Student name can not be blank.
	 */
	public void setName(String name) throws Exception {
		name = name.trim(); // remove leading and trailing whitespace

		//isBlank checks for both empty or whitespace
		if (name.isBlank())
			throw new Exception("Student name can not be blank.");

		this.name = name;
	} // end of setName method
	
	/**
	 * Get a Student by their seat number using the ArrayList.indexOf method<br>
	 * If the indexOf method returns -1 then this method returns null otherwise,<br>
	 * it returns the Student object associated with the seat number.
	 * @param seat The Student's seat number.
	 * @return If a Student is found, it will return the Student object otherwise a null value.
	 * @throws Exception Creating a student with an invalid seat number could throw an error
	 */
	public Student getStudent(int seat) throws Exception {

		int index = allStudents.indexOf(new Student(seat));

		if (index == -1)
			return null;
		else
			return allStudents.get(index);

	}

	/**
	 * Add a student to the Course, by using the overload constructor that allows setting their name and seat number too.<br>
	 * This method will verify that the seat number is not already used by another student by calling the Course.getStudent method.<br>
	 * and if it is, then it will throw an exception back to the calling method
	 * @param name The Player's name.
	 * @param seat The Student's seat number.
	 * @throws Exception Seat number # already assigned.
	 */
	public void addStudent(String name, int seat) throws Exception {
		Student student = getStudent(seat);
		if(student == null) {
			allStudents.add(new Student(seat, name));
		} else {
			throw new Exception("Seat #" + seat + " already assigned to " + student.getName() + "!");
		}
	}

	/**
	 * Display the Team's summary stats using the Team.getTeamFouls and getTeamPoints methods.<br>
	 * Example: <br>
	 * <pre>Team Wildcats Fouls=4 Points=23</pre>
	 */
	public void displaySummaryReport() {
		System.out.println("Course " + name + " OnTime=" + getOnTime() + " Late=" + getTeamPoints() + "Excused=" + getExcused() + "Unexcused" + getUnexcused());
	}
	
	/**
	 * Displays each Player's detail stats for the entire Team using the Player's getter methods.<br>
	 * This method uses the printf method for proper stats alignment. Example:<br>
	 * <pre>
	 * Jersey Name            Fouls 1pt 2pt 3pt Total
	 * ====== =============== ===== === === === =====
	 *    10  Billy               1   2   3   1    10
	 *    24  Tammy               0   0   2   0     4
	 * </pre>
	 */
	public void displayDetailReport() {

		Player player;

		displaySummaryReport();

		System.out.println("Jersey Name            Fouls 1pt 2pt 3pt Total");
		System.out.println("====== =============== ===== === === === =====");

        for (Student value : allStudents) {

            student = value;

            System.out.printf("%6d %-15s %5d %3d %3d %3d %5d\n",
                    student.getJersey(),
                    student.getName(),
                    student.getFouls(),
                    student.getFieldGoals_1pt(),
                    student.getFieldGoals_2pt(),
                    student.getFieldGoals_3pt(),
                    student.getPoints());
        }
    	
    	System.out.println();
	}

	/**
	 * Returns the team's name.
	 * @return Team name.
	 */
	@Override
	public String toString(){
		return name;
	}
}
