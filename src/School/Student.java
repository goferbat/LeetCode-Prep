package School;
import School.enums.Grade;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private List<Class> classes;
    private int year;
    private List<Student> friendsList;
    private HashMap<Class, Grade> grades;

    public Student (String name, List<Class> classes, int year, List<Student> friendsList, HashMap<Class,Grade> grades) {
        this.name = name;
        this.classes = classes;
        this.year = year;
        this.friendsList = friendsList;
        this.grades = grades;
        addClasses(classes);
    }

    public String getName() {return this.name;}
    public List<Class> getClasses() {return this.classes;}
    public int getYear() {return this.year;}
    public List<Student> getFriends() {return this.friendsList;}
    public HashMap<Class, Grade> getGrades() {return this.grades;}
    public void addFriend(Student student) { // two way
        this.friendsList.add(student);
        student.friendsList.add(this);
    }
    public void addFriends(List<Student> students) {
        for (Student student : students) student.addFriend(this);
    }
    public void addClass(Class toAdd) {
        this.classes.add(toAdd);
        this.grades.put(toAdd, Grade.NULL);
    }
    public void addClasses(List<Class> classes) {
        this.classes.addAll(classes);
        for (Class c : classes) {
            this.grades.put(c, Grade.NULL);
        }
    }

    public static List<Student> createStudents(List<Class> classes) {
        
        Student declan = new Student("Declan", new ArrayList<>(), 4, new ArrayList<>(), new HashMap<Class,Grade>());
        Student jack = new Student("Jack", new ArrayList<>(), 3, new ArrayList<>(), new HashMap<Class,Grade>());
        Student chris = new Student("Chris", new ArrayList<>(), 2, new ArrayList<>(), new HashMap<Class,Grade>());
        Student wyatt = new Student("Wyatt", new ArrayList<>(), 4, new ArrayList<>(), new HashMap<Class,Grade>());
        Student nicole = new Student("Nicole", new ArrayList<>(), 4, new ArrayList<>(), new HashMap<Class,Grade>());

        declan.addFriends(List.of(jack, chris, nicole));
        chris.addFriend(wyatt);

        return List.of(declan,jack,chris,wyatt,nicole);
    }

    @Override
    public String toString() {
        return "\nStudent: " + name + "\nClasses: " 
        + classes.stream().map(Class::getName).collect(Collectors.toList())
        + "\nYear: " + year + "\nFriends: " + friendsList.stream().map(friend -> friend.getName()).collect(Collectors.toList()) + "\n"
        + "Grades: " + this.getGrades().values() + "\n";
    }
}
