import java.util.*;

public class App extends BadgeAccess {
    public static void main(String[] args) throws Exception {
        createBadges();
    }
}


class BadgeAccess {

    // Problem: We are working on a security system for a badged-access room in our company's building.
    // Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
    
    // All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. 
    // (All employees are required to leave the room before the log ends.)

    // All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. 
    // (The room is empty when the log begins.)

    // Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

    // Expected output: ["Curtis", "Paul"], ["Martha", "Curtis"]

    public static void createBadges() {
        String[][] names = {
            {"Martha", "exit"},
            {"Paul", "enter"},
            {"Martha", "enter"},
            {"Martha", "exit"},
            {"Jennifer", "enter"},
            {"Paul", "enter"},
            {"Curtis", "exit"},
            {"Curtis", "enter"},
            {"Paul", "exit"},
            {"Martha", "enter"},
            {"Martha", "exit"},
            {"Jennifer", "exit"},
            {"Paul", "enter"},
            {"Paul", "enter"},
            {"Martha", "exit"}
        };

        Set<String> didntExitNames = didntExit(names);
        Set<String> didntEnter = didntEnter(names);

        System.out.println(didntExitNames + "," + didntEnter);

    }

    public static Set<String> didntExit(String[][] names) {
        Set<String> ret = new HashSet<>();
        for (String[] name : names) {
            if (name[1].equals("enter")) {
                ret.add(name[0]);
            }
            if (name[1].equals("exit") && ret.contains(name[0])) {
                ret.remove(name[0]);
            }
        }
        return ret;
    }

    public static Set<String> didntEnter(String[][] names) {
        Set<String> ret = new HashSet<>();
        Set<String> ret2 = new HashSet<>();
        for (String[] name : names) {
            if (name[1].equals("exit") && !ret2.contains(name[0])) {
                ret.add(name[0]);
            }
            if (name[1].equals("enter")) ret2.add(name[0]);
        }
        return ret;
    }

}

