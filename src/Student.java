/**
 * A Student and the student's stats.
 *
 * @author Keaton2319
 * @author RivJams
 * @since 2025.02.22
 * @version 1.0 beta
 * @see <a href="https://github.com/Keaton2319/Attendance_App">GitHub Repository</a>
 */
public class Student {

    /** The Student's seat. */
    private int seat;

    /** The Student's name. */
    private String name;

    /** The Student's number of being on time. */
    private int onTime;

    /** The Student's number of being late. */
    private int late;

    /** The Student's number of being excused. */
    private int excused;

    /** The Player's number of being unexcused. */
    private int unexcused;

    /**
     * Set the Student's fields to default values null and zeroes.
     * The default constructor is PRIVATE, so it is ONLY called by the overload constructor.
     */
    private Student() {
        name = null;
        seat = 0;
        onTime = 0;
        late = 0;
        excused = 0;
        unexcused = 0;
    } // end of default constructor

    /**
     * This overload constructor should only be used by the ArrayList indexOf method
     * to detect if a seat number is already taken by overriding the equals method to test
     * if two students are equal based on their seat number.
     * @param seat The Student's seat number.
     * @throws Exception if the setSeat method throws a data validation error.
     */
    public Student(int seat) throws Exception {
        this();
        setSeat(seat);
    } // end of seat overload constructor

    /**
     * Allow creating a Student plus setting their name and seat number.
     * The constructor first calls the default constructor and then
     * calls both the setName and setSeat methods for data validation.
     * @param name The Student's name
     * @param seat The Student's seat #
     * @throws Exception if the name or seat setters throws an error
     */
    public Student(int seat, String name) throws Exception {
        this();
        setSeat(seat);
        setName(name);
    } // end of seat and name overload constructor

    /**
     * Get the Student's name.
     * @return The Student's name.
     */
    public String getName() { return name;	}

    /**
     * Get the Student seat number.
     * @return The Student's seat number
     */
    public int getSeat() { return seat; }

    /**
     * Get the Student's number of onTime.
     * @return the number of onTime by the Student.
     */
    public int getOnTime() {return onTime; }

    /**
     * Get the Student's number of late attendances.
     * @return The number of late attendances made by the Student.
     */
    public int getLate() { return late; }

    /**
     * Get the Student's number of excused absences.
     * @return The number of excused absences by the Student.
     */
    public int getExcused() { return excused; }

    /**
     * Get the Student's number of unexcused absences.
     * @return the number of unexcused absences by the Student.
     */
    public int getUnexcused() {return unexcused; }

    /**
     * Set the Student's seat number if it's a positive number between 1 and 55
     * otherwise it throws an error.
     * @param seat the Student's seat number
     * @throws Exception if the seat number isn't between 0 and 55 inclusively.<br>
     * Error Example: Invalid seat number #10 for name Bob!
     */
    public void setSeat(int seat) throws Exception {
        if (seat >= 0 && seat <= 55)
            this.seat = seat;
        else
            throw new Exception("Invalid seat number #" + seat + " for name " + name + "!");
    } // end of setSeat method

    /**
     * Set the Student's name. Uses the trim method to remove leading and trailing spaces
     * and then if the name is an empty string, it will throw an error
     * if not then it will set the student's name.
     * @param name The Student's name
     * @throw Exception if the student's name is blank (whitespace or empty)<br>
     * Error Example: Name cannot be blank for seat number #10!
     */
    public void setName(String name) throws Exception {

        name = name.trim();

        if ( name.isBlank())
            throw new Exception("Name cannot be blank for seat number #" + seat + "!");
        else
            this.name = name;
    } // end of setName method

    /**
     * Increment the appropriate field goal type, using a switch that also handles for invalid data.<br>
     * case 1 is received then increment onTime by 1<br>
     * case 2 is received then increment late by 1<br>
     * case 3 is received then increment excused by 1<br>
     * case 4 is received then increment unexcused by 1<br>
     * default throw an exception displaying the invalid value that was received
     * @param status The status type 1=On Time, 2=Late, 3=Excused, 4=Unexcused
     * @throws Exception if an invalid status is received (valid 1-4)<br>
     * Error Example: Invalid status = 0
     */
    public void updateAttendance(int status) throws Exception {

        switch (status) {
            case 1:
                onTime++;
                break;
            case 2:
                late++;
                break;
            case 3:
                excused++;
                break;
            case 4:
                unexcused++;
                break;
            default:
                throw new Exception("Invalid statsType = " + status);
        }
    } // end of updateAttendance method

    /**
     * Display the Student's seat number, name, # of times on time, # of times late, # of times excused, and # of times unexcused
     * Example:#10 Billy On Time=10 Late=5 Excused=2 Unexcused=1
     */
    public void displayAttendance() {
        System.out.print("#" + seat + " " + name + " On Time=" + onTime + " Late=" + late + " Excused=" + excused + " Unexcused=" + unexcused);
    }

    /**
     * Instead of verifying two students are identical by equal identities.<br>
     * This method will verify two students are the same by ONLY their seat number.<br>
     * This allows the ArrayList.getIndex() method to return a student's by only checking their seat number.
     * @param object a student object to check equality of ONLY the seat number
     * @return true if the two student's seat are equal otherwise false
     */
    @Override
    public boolean equals(Object object) {

        if(!(object instanceof Student))
            return false;

        Student other = (Student)object;

        return this.seat == other.getSeat();
    } // end of override equals

    /**
     * Returns the seat number and student name. Example:<br>
     * #10 Billy
     * @return The student's seat and name
     */
    @Override
    public String toString(){
        return "#" + seat + " " + name;
    }
}