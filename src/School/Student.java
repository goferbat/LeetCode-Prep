package School;
import School.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Student {
    private String name;
    private List<Class> classes;
    private int year;
    private List<Student> friendsList;

    public Student (String name, List<Class> classes, int year, List<Student> friendsList) {
        this.name = name;
        this.classes = classes;
        this.year = year;
        this.friendsList = friendsList;
    }

    public String getName() {return this.name;}
    public List<Class> getClasses() {return this.classes;}
    public int getYear() {return this.year;}
    public List<Student> getFriends() {return this.friendsList;}
    public void addFriend(Student student) { // two way
        this.friendsList.add(student);
        student.friendsList.add(this);
    }
    public void addFriends(List<Student> students) {
        this.friendsList.addAll(students);
        for (Student student : students) student.addFriend(this);
    }
    public void addClass(Class toAdd) {
        this.classes.add(toAdd);
    }
    public void addClasses(List<Class> classes) {
        this.classes.addAll(classes);
    }

    public static List<Student> createStudents() {
        Student declan = new Student("Declan", new ArrayList<>(), 4, new ArrayList<>());
        Student jack = new Student("Jack", new ArrayList<>(), 3, new ArrayList<>());
        Student chris = new Student("Chris", new ArrayList<>(), 2, new ArrayList<>());
        Student wyatt = new Student("Wyatt", new ArrayList<>(), 4, new ArrayList<>());
        Student nicole = new Student("Nicole", new ArrayList<>(), 4, new ArrayList<>());

        declan.addFriends(List.of(jack, chris, nicole));
        chris.addFriend(wyatt);

        return List.of(declan,jack,chris,wyatt,nicole);
    }

    @Override
    public String toString() {
        return "\nStudent: " + name + "\nClasses: " 
        + classes.stream().map(Class::getName).collect(Collectors.toList())
        + "\nYear: " + year + "\nFriends: " + friendsList.stream().map(friend -> friend.getName()).collect(Collectors.toList()) + "\n";
    }
}
