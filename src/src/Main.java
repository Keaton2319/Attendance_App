public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Student x1 = new Student(1, "Alex");
            System.out.println(x1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Student x2 = new Student(1, "Tim");
            System.out.println(x2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
