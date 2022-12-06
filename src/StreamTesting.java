import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import enums.Gender;

public class StreamTesting {

    static class Dog {
        private final String name;
        private final int age;
        private final String breed;
        private final Gender gender;

        Dog(String name, int age, String breed, Gender gender) {
            this.name = name;
            this.age = age;
            this.breed = breed;
            this.gender = gender;
        }       

        public String getName() { return this.name; }    
        public int getAge() { return this.age; }   
        public String getBreed() { return this.breed; }
        public Gender getGender() { return gender; }

        @Override
        public String toString() {
            String ret = "Dog: " + this.getName() + " , " + this.getBreed() + " , " + this.getAge() + " , " + this.getGender();
            return ret;
        }

    }


    static class Dogwalker {

        static void Dogwalk () {

            List<Dog> currentDogs = getDogs();

            Integer top3dogAgesSummed = currentDogs.stream()
                                                .map(dog -> dog.getAge())
                                                .sorted(Comparator.reverseOrder())
                                                .limit(3)
                                                .mapToInt(Integer::intValue).sum();

            // FILTER
            List<Dog> age2Dogs = currentDogs.stream().filter(dog -> dog.getAge() == 2).collect(Collectors.toList());
            
            System.out.println("FILTER DOGS BY AGE OF 2");
            age2Dogs.forEach(System.out::println);
            System.out.println("\n");

            // SORT
            List<Dog> sortedDogs = currentDogs.stream()
                .sorted(Comparator.comparing(dog -> dog.getAge()))
                .collect(Collectors.toList());

            System.out.println("SORTED DOGS BASED ON NAME");
            sortedDogs.forEach(System.out::println);
            System.out.println("\n");

            // REVERSE ORDER SORT
            List<Dog> reverseSortedDogs = currentDogs.stream()
                .sorted(Comparator.comparing(Dog::getAge).reversed())
                .collect(Collectors.toList());

            System.out.println("REVERSE SORTED DOGS BASED ON NAME");
            reverseSortedDogs.forEach(System.out::println);
            System.out.println("\n");

            // ALL MATCH
            boolean allMatch = currentDogs.stream()
                .allMatch(dog -> dog.getAge() > 5);

            System.out.println("Do all dogs have an age over 5?\n" + allMatch);
            System.out.println("\n");

            // ANY MATCH
            boolean anyMatch = currentDogs.stream()
                .anyMatch(dog -> dog.getAge() > 5);
        
            System.out.println("Does any dog have an age over 5?\n" + anyMatch);
            System.out.println("\n");

            // NONE MATCH
            boolean noneMatch = currentDogs.stream()
                .noneMatch(dog -> dog.getAge() > 20);

            System.out.println("Do no dogs have an age over 20?\n" + noneMatch);
            System.out.println("\n");

            //MAX
            Optional<Dog> oldDog = currentDogs.stream()
                .max(Comparator.comparing(Dog::getAge));
            
            oldDog.ifPresent(System.out::println);
            System.out.println("\n");

            //MIN
            Optional<Dog> youngDog = currentDogs.stream()
                .min(Comparator.comparing(Dog::getAge));
            
            youngDog.ifPresent(System.out::println);
            System.out.println("\n");

            //GROUPING
            Map<Gender, List<Dog>> map = currentDogs.stream()
                .collect(Collectors.groupingBy(Dog::getGender));
            
            map.forEach((gender, dog) -> {
                System.out.println(gender);
                dog.forEach(System.out::println);
                System.out.println();
            });

            //CHAINING

            String oldestFemaleDog = currentDogs.stream()
            .filter(dog -> dog.getGender().equals(Gender.FEMALE))
            .max(Comparator.comparing(Dog::getAge))
            .map(Dog::getName).get();

            System.out.print("Oldest female dog is : ");
            System.out.println(oldestFemaleDog);
            System.out.println("\n");


            long maleDogsAgeTwice = currentDogs.stream()
                .filter(dog -> dog.getGender().equals(Gender.MALE))
                .map(dog -> dog.getAge()*dog.getAge())
                .count();

            // int count = 0;

            // currentDogs.stream()
            // .filter(dog -> dog.gender.equals(Gender.FEMALE))
            // .sorted(Comparator.comparing(Dog::getAge).reversed())
            // .anyMatch(dog -> dog.age == 4)
            // .forEach(count++);

            // System.out.println(femOldDogAge);

            Map<Boolean, List<Dog>> map2 = currentDogs.stream()
            .filter(dog -> dog.getAge() < 20)
            .collect(Collectors.groupingBy(dog -> dog.getBreed().equals("Poodle")));

            map2.forEach((object, dog) -> {
                System.out.println(object);
                dog.forEach(System.out::println);
                System.out.println();
            });

            Function<Dog, Integer> multby10 = number -> number.getAge() * 10;
            currentDogs.stream()
            .filter(dog -> dog.getAge() < 10)
            .map(multby10).collect(Collectors.toList());

            HashMap<Integer,Integer> hm = new HashMap<>();
            Integer i = hm.values().stream().max(Comparator.naturalOrder()).get();
            Integer k = hm.values().stream().sorted(Comparator.reverseOrder()).limit(10).mapToInt(Integer::intValue).sum();
            
            Integer ko = k.stream().mapToInt(Integer::intValue).sum();

             
        }

        public static void main (String args[]) {
            Dogwalk();
        }

        static List<Dog> getDogs() {
            return List.of(
                new Dog("Chewwy", 2, "Chihuahua", Gender.MALE),
                new Dog("Dobby", 13, "Doberman", Gender.FEMALE),
                new Dog("Gus", 16, "Poodle", Gender.MALE),
                new Dog("George", 4, "Chihuahua", Gender.FEMALE),
                new Dog("Iggy", 10, "Doberman", Gender.FEMALE),
                new Dog("Izelia", 8, "Poodle", Gender.MALE),
                new Dog("Kennan", 9, "Poodle", Gender.FEMALE)
            );
        }
    }
}