package School;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import School.enums.Grade;

class Class {
    private String name;
    private Teacher teacher;
    private int level;

    public Class (String name, Teacher teacher, int level) {
        this.name = name;
        this.teacher = teacher;
        this.level = level;
        teacher.addClass(this);
    }

    public static List<Class> createClasses(List<Teacher> teachers) {

        Class math = new Class("Math 202", teachers.get(0), 2);
        Class science = new Class("Science 300", teachers.get(0), 3);
        Class history = new Class("History 101", teachers.get(1), 1);
        Class chinese = new Class("Chinese 100", teachers.get(1), 1);

        return List.of(math,science,history,chinese);
    }

    public static void AddRandomClasses(List<Student> students, List<Class> classes) {
        Random rndm = new Random();

        for (Student i : students) {
            List<Class> classesToAdd = new ArrayList<>();
            int numofClasses = rndm.nextInt(1,classes.size());
            while (numofClasses > 0) {
                int classtoAdd = rndm.nextInt(classes.size());
                if (!classesToAdd.contains(classes.get(classtoAdd))) {
                    classesToAdd.add(classes.get(classtoAdd));
                    i.getGrades().put(classes.get(classtoAdd), Grade.NULL);
                }
                numofClasses--;
            }
            i.addClasses(classesToAdd);
        }

    }
    
    public String getName() {return this.name;}
    public String getTeacher() {return this.teacher.getName();}
    public int getLevel() {return this.level;}
    public void addTeacher(Teacher teacher) { this.teacher = teacher;}

    @Override
    public String toString() {
        return "\nClass: " + this.name
        + this.teacher.toString()
        + "\nLevel: " + this.level;
    }
}
