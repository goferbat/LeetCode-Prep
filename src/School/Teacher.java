package School;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import School.interfaces.Employee;

public class Teacher implements Employee {

    private String id;
    private String name;
    private List<Class> classes;

    public Teacher (String id, String name, List<Class> classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    public String getId() { return this.id;}
    public String getName() {return this.name;}
    public List<Class> getClasses() {return this.classes;}

    public void addClass(Class class1) {
        this.classes.add(class1);
    }
    
    public void gradeClass() {

    }

    public static List<Teacher> createTeachers() {
        Teacher mrHu = new Teacher("01", "Mr.Hu", new ArrayList<>());
        Teacher mrsHalbert = new Teacher("02", "Mrs. Halbert", new ArrayList<>());

        return List.of(mrHu,mrsHalbert);
    }

    @Override
    public String toString() {
        return "\nTeacher: " + this.name
        + "\nID: " + this.id
        + "\nTeaches: " + classes.stream()
            .map(class1 -> class1.getName())
            .collect(Collectors.toList());
    }
    
}
