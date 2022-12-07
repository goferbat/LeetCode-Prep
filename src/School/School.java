package School;

import java.util.*;
import java.util.stream.Collectors;

import javax.xml.transform.Source;

import School.enums.Grade;

class School {

    public static Random rndm = new Random();
    static List<Student> students;
    static List<Teacher> teachers;
    static List<Class> classes;

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);
        Student student2 = new Student(scanner.nextLine(), new ArrayList<>(), scanner.nextInt(), new ArrayList<>(), new HashMap<Class,Grade>());

        teachers = Teacher.createTeachers();
        classes = Class.createClasses(teachers); // Create a list of classes
        students = Student.createStudents(classes); // Create a list of Students
        students.add(student2);
        Class.AddRandomClasses(students, classes); // Adding random number of classes to each student

        rollCall();
        // streamTesting();
        
    }

    public static void rollCall() {
        System.out.println("-------------------------ROLL CALL-----------------------");
        for (Student s : students) System.out.println(s);
        System.out.println("-------------------------CLASSES-----------------------");
        for (Class i : classes) System.out.println(i);
        System.out.println("-------------------------END ROLL CALL-----------------------");

    }

    public static void streamTesting() {
        // Same Year Students
        int year = rndm.nextInt(1,5);
        List<String> shareOneClass = sameYear(students, year);
        System.out.println(year + " year students: " + Arrays.toString(shareOneClass.toArray()));

        // All Who Take a Certain Class
        int classtoCompare = rndm.nextInt(classes.size());
        List<String> shareSameClass = sameClass(students, classes.get(classtoCompare));
        System.out.println("Students who take " + classes.get(classtoCompare).getName() + ": " + Arrays.toString(shareSameClass.toArray()));

        // All Students who take a course that is on a level lower than their grade (Declan takes History 101 when he is a 4th year)
        final int classtoCompare2 = rndm.nextInt(classes.size());
        List<Student> takeLowerClass = takeClassBelowYear(students, classes.get(classtoCompare2));
        takeLowerClass.stream().forEach(student -> System.out.println(student.getName() + " is a year " + student.getYear() + " student and takes: " + classes.get(classtoCompare2).getName() + " which is for level: " + classes.get(classtoCompare2).getLevel()));

        // SCHOOL PROJECT IDEA
        schoolProject(students, classes.get(0));
    }

    public static List<String> sameYear (List<Student> students, int year) {

        List<String> sameYearList = students.stream()
            .filter(student -> student.getYear() == year)
            .map(Student::getName)
            .collect(Collectors.toList());
        
        return sameYearList;
    }

    public static List<String> sameClass (List<Student> students, Class class1) {

        return students.stream()
        .filter(student -> student.getClasses().contains(class1))
        .map(Student::getName)
        .collect(Collectors.toList());
    }

    public static List<Student> takeClassBelowYear (List<Student> students, Class class1) {
        return students.stream()
        .filter(student -> student.getYear() > class1.getLevel()).collect(Collectors.toList());
    }

    public static void schoolProject(List<Student> students, Class class1) { // School project, random grade, updates grade, will return who did the best on the project and update their Grade

        for (Student student : students) {
            int score = rndm.nextInt(0,4);
            Grade grade = Grade.values()[score];
            System.out.println(grade);
            if (student.getGrades().containsKey(class1)) {
                student.getGrades().put(class1, grade);
            }
        }

        // Optional<Student> winner = students.stream().filter(student -> student.getGrades().get(class1).equals(Grade.A)).findFirst();

        List<Student> winners = students.stream()
        .filter(student -> student.getGrades().containsKey(class1)) // should probably fix this
        .filter(student -> student.getGrades().get(class1).equals(Grade.A)).collect(Collectors.toList());
        System.out.println("\nSCHOOL PROJECT WINNERS");
        winners.stream().forEach(System.out::println);
    }

}