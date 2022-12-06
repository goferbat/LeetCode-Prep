import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

class UndergroundSystem {

    class Person {
        int id;
        String stationName;
        int time;
        public Person(int id, String stationName, int time) {
            this.id = id;
            this.stationName = stationName;
            this.time = time;
        }
        public int getId() { return this.id;}
        public String getStationName() {return this.stationName;}
        public int getTime() {return this.time;}
    }

    class AverageTime {
        String stationOne;
        String stationTwo;
        double average;
        List<Integer> times;
        public AverageTime(String stationOne, String stationTwo, double average, List<Integer> times){
            this.stationOne = stationOne;
            this.stationTwo = stationTwo;
            this.average = average;
            this.times = times;
        }

        public double getAverage() { return this.average; }
        public String getStationOne() { return this.stationOne;}
        public String getStationTwo() { return this.stationTwo;}
        public List<Integer> getTimes() { return this.times;}
        public void setTimes(int num) {this.times.add(num);}
        public void setAverage(double toSet) {this.average = toSet;}
    }

    List<Person> list;
    List<AverageTime> averageTimeList;

    public UndergroundSystem() { 
        list = new ArrayList<>();
        averageTimeList = new ArrayList<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        Person newPerson = new Person(id, stationName, t);
        list.add(newPerson);
    }
    
    public void checkOut(int id, String stationName, int t) {
        Person checkedOutPerson = list.stream().filter(person -> person.getId() == id).findFirst().get();
        list.remove(checkedOutPerson);

        int timeTaken = t - checkedOutPerson.getTime();

        Optional<AverageTime> averageTime = averageTimeList.stream()
            .filter(time -> time.getStationOne().equals(checkedOutPerson.getStationName()) && time.getStationTwo().equals(stationName))
            .findFirst();
        if (averageTime.isPresent()) {
            averageTime.get().setTimes(timeTaken);
            Double x = averageTime.get().getTimes().stream().mapToDouble(num -> num).sum() / averageTime.get().getTimes().size();
            averageTime.get().setAverage(x);
        }
        else {
            AverageTime averageTime2 = new AverageTime(checkedOutPerson.getStationName(), stationName, timeTaken, new ArrayList<Integer>());
            averageTime2.setTimes(timeTaken);
            averageTimeList.add(averageTime2);
        }
    }
    
    public double getAverageTime(String startStation, String endStation) {
        AverageTime toGet = averageTimeList.stream()
            .filter(time -> time.getStationOne().equals(startStation) && time.getStationTwo().equals(endStation))
            .findFirst().get();
        return toGet.getAverage();
    }
}