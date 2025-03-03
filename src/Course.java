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
	 * @param name The Student's name.
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
	 * Display the Student's summary stats using the Course.getOnTime, getLate, getExcused, and getUnexcused methods.<br>
	 * Example: <br>
	 * <pre>Course 1 OnTime=4 Late=3 Excused=2 Unexcused=1 </pre>
	 */
	public void displaySummaryReport() {
		int totalOnTime = 0;
		int totalLate = 0;
		int totalExcused = 0;
		int totalUnexcused = 0;

		for (Student value : allStudents) {
			Student student = value;

			totalOnTime += student.getOnTime();
			totalLate += student.getLate();
			totalExcused += student.getExcused();
			totalUnexcused += student.getUnexcused();
		}

		System.out.println("Course " + name + " OnTime=" + totalOnTime + " Late=" + totalLate + " Excused=" + totalExcused + " Unexcused=" + totalUnexcused);
	}
	
	/**
	 * Displays each Student's detail stats for the entire Course using the Student's getter methods.<br>
	 * This method uses the printf method for proper status alignment. Example:<br>
	 * <pre>
	 * Seat Name            On Time Late Excused Unexcused
	 * ==== =============== ======= ==== ======= =========
	 *    1 Debbie                3    0       1         0
	 *    2 Fred                  2    2       0         0
	 * </pre>
	 */
	public void displayDetailReport() {

		Student student;

		displaySummaryReport();

		System.out.println("Seat Name            On Time Late Excused Unexcused");
		System.out.println("==== =============== ======= ==== ======= =========");

        for (Student value : allStudents) {

            student = value;


            System.out.printf("%6d %-15s %5d %3d %3d %3d %5d\n",
                    student.getSeat(),
                    student.getName(),
                    student.getOnTime(),
                    student.getLate(),
                    student.getExcused(),
                    student.getUnexcused());
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