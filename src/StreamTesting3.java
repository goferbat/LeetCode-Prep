import java.util.*;
import java.util.stream.Collectors;

public class StreamTesting3 {

    public class Movie {
        private int id;
        private String title;
        private int year;
        private String imdb;
        private List<Genre> genres;
        private List<Director> directors;
        
        {
            genres = new ArrayList<>();
            directors = new ArrayList<>();
        }

        public Movie() {

        }

        public Movie(int id, String title, int year, String imdb) {
            this.id = id;
            this.title = title;
            this.year = year;
            this.imdb = imdb;
        }

        @Override   
        public String toString() {
            return "Movie [title=" + title + ", year=" + year + "]";
        }
    }

    public class Director {
        private int id;
        private String name;
        private String imdb;
        private List<Movie> movies= new ArrayList<>();

        public Director() {
        }

        public Director(int id, String name, String imdb) {
            this.id = id;
            this.name = name;
            this.imdb = imdb;
        }

        // getters and setters
        @Override   
        public String toString() {
            return "Director [id=" + id + ", name=" + name + ", imdb=" + imdb + "]";
        }
    }

    public class Genre {
        private int id;
        private String name;

        public Genre() {
        }

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }

    // getters and setters

        @Override   
        public String toString() {
            return "Genre [id=" + id + ", name=" + name + "]";
        }

    }

    public class Country {
        private String code;
        private String name;
        private String continent;
        private double surfaceArea;
        private int population;
        private double gnp;
        private int capital;
        private List<City> cities;
        {
        cities = new ArrayList<>();
        }
 
        public Country() {
        }
 
        public Country(String code, String name, String continent, int population,
            double surfaceArea, double gnp, int capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.capital = capital;
        this.gnp = gnp;
        }
 
        // getters and setters
    
        @Override   
        public String toString() {
        return "Country [ name=" + name + ", population=" + population + "]";
        }
 
    }

    public class City {
        private int id;
        private String name;
        private int population;
        private String countryCode;
    
        public City() {
        }
 
        public City(int id, String name, String countryCode, int population) {
            this.id = id;
            this.name = name;
            this.population = population;
            this.countryCode = countryCode;
        }
    
        // getters and setters
        
        @Override   
        public String toString() {
            return "City [id=" + id + ", name=" + name + ", population=" + population + ", countryCode=" + countryCode + "]";
        };
 
    }

    public class Exercise1 {

        public static void main(String[] args) {
           CountryDao countryDao= InMemoryWorldDao.getInstance();
           List<City> highPopulatedCitiesOfCountries = countryDao.findAllCountries()
                     .stream()
                     .map( country -> country.getCities().stream().max(Comparator.comparing(City::getPopulation)))
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                       .collect(Collectors.toList());
           highPopulatedCitiesOfCountries.forEach(System.out::println);
        }
     

}


