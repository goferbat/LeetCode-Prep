package School;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Class {
    private String name;
    private String teacher;
    private int level;

    public Class (String name, String teacher, int level) {
        this.name = name;
        this.teacher = teacher;
        this.level = level;
    }

    
    public static List<Class> createClasses() {
        Class math = new Class("Math 202", "Mr. Hu", 2);
        Class science = new Class("Science 300", "Prof. Dandy", 3);
        Class history = new Class("History 101", "Mr. Tribec", 1);
        Class chinese = new Class("Chinese 100", "Mrs. Tsai", 1);

        return List.of(math,science,history,chinese);

    }

    public static void AddRandomClasses(List<Student> students, List<Class> classes) {
        Random rndm = new Random();

        for (Student i : students) {
            List<Class> classesToAdd = new ArrayList<>();
            int numofClasses = rndm.nextInt(1,classes.size());
            while (numofClasses > 0) {
                int classtoAdd = rndm.nextInt(classes.size());
                if (!classesToAdd.contains(classes.get(classtoAdd))) classesToAdd.add(classes.get(classtoAdd));
                numofClasses--;
            }
            i.addClasses(classesToAdd);
        }

    }

    public String getName() {return this.name;}
    public String getTeacher() {return this.teacher;}
    public int getLevel() {return this.level;}
}
