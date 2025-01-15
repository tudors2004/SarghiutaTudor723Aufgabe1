package org.example;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that reads from a JSON file.
 */
public class Main{
    static class Euro2024{
        @JsonProperty("Id")
        private int id;
        @JsonProperty("Team1")
        private String team1;
        @JsonProperty("Team2")
        private String team2;
        @JsonProperty("Datum")
        private LocalDate date;
        @JsonProperty("Spielort")
        private String location;
        @JsonProperty("Kapazität")
        private int capacity;

        public Euro2024(int id, String team1, String team2, LocalDate date, String location, int capacity) {
            this.id = id;
            this.team1 = team1;
            this.team2 = team2;
            this.date = date;
            this.location = location;
            this.capacity = capacity;
        }
        public Euro2024() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTeam1() {
            return team1;
        }

        public void setTeam1(String team1) {
            this.team1 = team1;
        }

        public String getTeam2() {
            return team2;
        }

        public void setTeam2(String team2) {
            this.team2 = team2;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return "Euro2024{" +
                    "id=" + id +
                    ", team1='" + team1 + '\'' +
                    ", team2='" + team2 + '\'' +
                    ", date=" + date +
                    ", location='" + location + '\'' +
                    ", capacity=" + capacity +
                    '}';
        }
    }

    /**
     * Function that reads from a JSON file.
     * @param filePath - the path to the JSON file
     * @return euro2024List
     */
    public static List<Euro2024> readFromFile(String filePath) {
        List<Euro2024> euro2024List = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            euro2024List = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Euro2024.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return euro2024List;
    }
    /**
     * Function that displays the matches with a capacity greater than the one given by the user.
     * @param euro2024List - the list of matches
     * @param capacity - the capacity given by the user
     */
    public static void displayMatches(List<Euro2024> euro2024List, int capacity) {
        for(Euro2024 euro2024 : euro2024List){
            if(euro2024.getCapacity() >= capacity){
                System.out.println(euro2024.getTeam1() + " vs " + euro2024.getTeam2() + " - Date: " + euro2024.getDate() + " - Venue: " + euro2024.getLocation());
            }
        }
    }
    /**
     * Function that displays the matches that will take place in München after 30.06.2024.
     * @param euro2024List - the list of matches
     */
    public static void displayMatchInMunchen(List<Euro2024> euro2024List){
        euro2024List.stream()
                .filter(euro2024 -> euro2024.getDate().isAfter(LocalDate.of(2024, 6, 30))&&euro2024.getLocation().equals("München"))
                .sorted(Comparator.comparing(Euro2024::getDate))
                .forEach(euro2024 -> System.out.println(euro2024.getDate() + ": " + euro2024.getTeam1() + " vs " + euro2024.getTeam2()));
    }

    /**
     * Main function that reads from a JSON file and displays the matches.
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        List<Euro2024> euro2024List = readFromFile("G:\\Proiecte JAVA\\SarghiutaTudor723Aufgabe1\\SarghiutaTudor723Aufgabe1\\src\\main\\java\\org\\example\\spielorte.json");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdu capacitatea: ");
        int capacity = scanner.nextInt();
        displayMatches(euro2024List, capacity);
        displayMatchInMunchen(euro2024List);
    }

}
