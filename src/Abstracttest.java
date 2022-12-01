abstract class Dog {
    private String name;

    void setName(String name) { // Encapsulation by hiding details
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void bark () {
        System.out.println("bark!");
    }

    public abstract void poop(); // abstraction, dogs poop in different ways I guess
}

class Dober extends Dog { //inheritance of Dog

    static int count;
    
    public Dober (String name) {
        setName(name);
        Dober.count += 1;
    }
    
    public void poop () {
        System.out.println(getName() + " pooped");
    }
}

class Chihuahua extends Dog {
    static int count;

    public Chihuahua(String name) {
        setName(name);
        Chihuahua.count++;
    }

    public void poop() {
        System.out.println(getName() + "took a big one!");
    }

    @Override
    void bark () {
        System.out.println(getName() + "made a tiny bark!");
    }
    
}

public class Abstracttest {

    public static void main (String args[]) {
        Dober d = new Dober("Doggo");
        Chihuahua c = new Chihuahua("Chewwy");
        Chihuahua b = new Chihuahua("Bacca");
        d.bark();
        d.poop();
        c.poop();
        c.bark();
        System.out.println("Dober count: " + Dober.count + "\n" + "Chihuahua count: " + Chihuahua.count);
    }
}
