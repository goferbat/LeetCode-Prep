import java.util.*;
import java.util.stream.*;
 
 class TempStudent {
    public String name;
    public int age;
    public Address address;
    public List<MobileNumber> mobileNumbers;
 
    public TempStudent(String name, int age, Address address, List<MobileNumber> mobileNumbers) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.mobileNumbers = mobileNumbers;
    }
}
 
class Student{
    private String name;
    private int age;
    private Address address;
    private List<MobileNumber> mobileNumbers;
 
    public Student(String name, int age, Address address, List<MobileNumber> mobileNumbers) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.mobileNumbers = mobileNumbers;
    }
 
    public String getName() {
        return name;
    }
 
    public int getAge() {
        return age;
    }
 
    public Address getAddress() {
        return address;
    }
 
    public List<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public void setAddress(Address address) {
        this.address = address;
    }
 
    public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }
 
    @Override
    public String toString() {
        return "Student{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", address=" + address +
            ", mobileNumbers=" + mobileNumbers +
            '}';
    }
}
 
class Address{
    private String zipcode;
 
    public Address(String zipcode) {
        this.zipcode = zipcode;
    }
 
    public String getZipcode() {
        return zipcode;
    }
 
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
 
class MobileNumber{
    private String number;
 
    public MobileNumber(String number) {
        this.number = number;
    }
 
    public String getNumber() {
        return number;
    }
 
    public void setNumber(String number) {
        this.number = number;
    }
}

// By looking at above example class, answer the following questions, 

// 1 Get student with exact match name "jayesh"
// 2 Get student with matching address "1235"
// 3 Get all student having mobile numbers 3333.
// 4 Get all student having mobile number 1233 and 1234
// 5 Create a List<Student> from the List<TempStudent>
// 6 Convert List<Student> to List<String> of student name
// 7 Convert List<students> to String
// 8 Change the case of List<String>
// 9 Sort List<String>
// 10 Conditionally apply Filter condition, say if flag is enabled then.

class StreamTest {
    public static void main(String[] args) {
 
        Student student1 = new Student(
            "Jayesh",
            20,
            new Address("1234"),
            Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));
 
        Student student2 = new Student(
            "Dong",
            20,
            new Address("1235"),
            Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));
 
        Student student3 = new Student(
            "Jason",
            20,
            new Address("1236"),
            Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        List<Student> students = Arrays.asList(student1,student2,student3);

        // 1
        Optional<Student> ans = students.stream().filter(student -> student.getName().equals("Jayesh")).findFirst();
        System.out.println("1: ");
        System.out.println(ans.isPresent() ? ans.get() : "no student with name Jayesh exists");
        System.out.println("\n");


        // 2
        Optional<Student> ans2 = students.stream().filter(student -> student.getAddress().getZipcode().equals("1235")).findFirst();
        System.out.println("2: ");
        System.out.println(ans2.isPresent() ? ans2.get() : "no student found");
        System.out.println("\n");
        
        // 3
        List<Student> ans3 = students.stream()
            .filter(student -> student.getMobileNumbers().stream().anyMatch(number -> number.getNumber().equals("3333")))
            .collect(Collectors.toList());

        System.out.println("3: ");
        ans3.forEach(System.out::println);
        System.out.println("\n");

        // 4
        List<Student> ans4 = students.stream()
            .filter(student -> student.getMobileNumbers().stream().allMatch(number -> number.getNumber().equals("1233") || number.getNumber().equals("1234")))
            .collect(Collectors.toList());

        System.out.println("4: ");
        ans4.forEach(System.out::println);
        System.out.println("\n");

        // 5
        TempStudent tmpStud1 = new TempStudent(
            "Jayesh1",
            201,
            new Address("12341"),
            Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));
 
        TempStudent tmpStud2 = new TempStudent(
            "Khyati1",
            202,
            new Address("12351"),
            Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

        List<TempStudent> tempStudents = Arrays.asList(tmpStud1,tmpStud2);

        List<Student> ans5 = tempStudents.stream()
            .map(tmpStudent -> new Student(tmpStudent.name, tmpStudent.age, tmpStudent.address, tmpStudent.mobileNumbers))
            .collect(Collectors.toList());

        System.out.println("5: ");
        ans5.forEach(System.out::println);
        System.out.println("\n");

        // 6 Convert List<Student> to List<String> of student name
        List<String> ans6 = students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println("6: ");
        ans6.forEach(System.out::println);
        System.out.println("\n");

        // 7 Convert List<students> to String
        String ans7 = students.stream()
            .map(Student::getName)
            .collect(Collectors.joining(" , ","[ "," ]"));
        System.out.println("7: ");
        System.out.println(ans7);
        System.out.println("\n");

        // 8 Change the case of List<String>
        List<String> ans8 = ans6.stream()
        .map(student -> student.toUpperCase())
        .collect(Collectors.toList());
        System.out.println("8: ");
        System.out.println(ans8);
        System.out.println("\n");

        // 9 Sort List<String>
        List<String> ans9 = ans6.stream().sorted(Comparator.comparing(name -> name)).collect(Collectors.toList());
        System.out.println("9: ");
        System.out.println(ans9);
        System.out.println("\n");

        // 10 Conditionally apply Filter condition, say if flag is enabled then.
        boolean sortConditionFlag = true;
        Stream<Student> conditional = students.stream().filter(student -> student.getName().startsWith("J"));
        if (sortConditionFlag) {
            conditional = conditional.sorted(Comparator.comparing(student -> student.getName()));
        }

        System.out.println("10:\nBefore sorting :");
        students.forEach(s -> System.out.println(s.getName()));
 
        List<Student> list = conditional.collect(Collectors.toList());
        System.out.println("\nAfter filter and conditional sorting :");
        list.forEach(s -> System.out.println(s.getName()));

    }
}