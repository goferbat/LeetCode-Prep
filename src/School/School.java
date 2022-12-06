package School;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class School {

    public static Random rndm = new Random();

    public static void main (String args[]) {

        List<Student> students = Student.createStudents(); // Create a list of Students
        List<Class> classes = Class.createClasses(); // Create a list of classes
        Class.AddRandomClasses(students, classes); // Adding random number of classes to each student
        // maybe add something here for adding random friends to the students

        
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

}