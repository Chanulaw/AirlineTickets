package AtilineTicketingSystem;

import org.w3c.dom.ls.LSOutput;
import java.util.Scanner;
import java.io.*;

public class AirlineTicketingCenter {
    private static Counter[] counters = new Counter[4]; // Array for 4 counters
    private static PassengerQueue pQueue = new PassengerQueue(); // Waiting list object

    public static void main(String[] args) {
        // Initialize all 4 counters
        for (int i = 0; i < 4; i++) counters[i] = new Counter();
        Scanner input = new Scanner(System.in);
        int choice = 0;

        // Loop until the user chooses to exit (999)
        while (choice != 999) {
            System.out.println("\n--- Airline Ticketing System ---");
            System.out.println("100: View all Counters");
            System.out.println("101: View Empty Counters"); // Added missing number in print
            System.out.println("102: Add Passenger");
            System.out.println("103: Remove Passenger");
            System.out.println("104: Sort Names ");
            System.out.println("105: Store Data");
            System.out.println("106: Load Data");
            System.out.println("999: Exit");
            System.out.print("Choice: ");
            choice = input.nextInt();

            // Direct to the specific method based on user choice
            switch (choice) {
                case 100: viewAll(); break;
                case 101: viewEmpty(); break;
                case 102: addPassenger(); break;
                case 103: removePassenger(); break;
                case 104: sortPassengers(); break;
                case 105: storeToFile(); break;
                case 106: loadFromFile(); break;
                case 999: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid input!");
            }
        }
    }

    // Displays the status of all counters and the waiting list
    private static void viewAll() {
        for (int i = 0; i < 4; i++) {
            Passenger p = counters[i].getPassenger();
            System.out.println("Counter " + i + ": " + (p == null ? "Empty" : p.getDetails()));
        }
        System.out.println("Waiting List Size: " + pQueue.getCount());
    }

    // Displays only the empty counters
    private static void viewEmpty() {
        for (int i = 0; i < 4; i++) {
            if (counters[i].isEmpty()) System.out.println("Counter " + i + " is Empty");
        }
    }

    // Takes user input and adds a passenger to a counter or the queue
    private static void addPassenger() {
        Scanner s = new Scanner(System.in);
        System.out.print("First Name: "); String f = s.next();
        System.out.print("Surname: "); String sur = s.next();
        System.out.print("Passport: "); String pNo = s.next();
        System.out.print("City: "); String city = s.next();
        System.out.print("Class (1st,Economic,Business): "); String sCls = s.next();

        Passenger newP = new Passenger(f, sur, pNo, city, sCls);
        int emptyIdx = -1;
        // Search for an empty counter
        for (int i = 0; i < 4; i++) {
            if (counters[i].isEmpty()) { emptyIdx = i; break; }
        }

        if (emptyIdx != -1) {
            counters[emptyIdx].setPassenger(newP); // Add to counter if free
            System.out.println("Added to Counter " + emptyIdx);
        } else {
            pQueue.enQueue(newP); // Add to waiting list if all counters full
        }
    }

    // Removes a passenger from a counter and brings the next person from the queue
    private static void removePassenger() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Counter Index (0-3): ");
        int idx = s.nextInt();
        if (idx >= 0 && idx < 4 && !counters[idx].isEmpty()) {
            System.out.println("Removed: " + counters[idx].getPassenger().getFirstName());
            // Move the first person from waiting list to this counter
            counters[idx].setPassenger(pQueue.deQueue());
        } else {
            System.out.println("Invalid or Empty Counter.");
        }
    }

    // Sorts passengers currently at counters by their first name using Bubble Sort
    private static void sortPassengers() {
        Passenger[] temp = new Passenger[4];
        int n = 0;
        // Collect all non-empty counter passengers into an array
        for (int i = 0; i < 4; i++) {
            if (!counters[i].isEmpty()) temp[n++] = counters[i].getPassenger();
        }

        // Bubble Sort algorithm to sort by First Name
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (temp[j].getFirstName().compareToIgnoreCase(temp[j+1].getFirstName()) > 0) {
                    Passenger swap = temp[j]; temp[j] = temp[j+1]; temp[j+1] = swap;
                }
            }
        }
        System.out.println("--- Alphabetical Order ---");
        for (int i = 0; i < n; i++) System.out.println(temp[i].getFirstName());
    }

    // Saves current counter and waiting list data to a text file
    private static void storeToFile() {
        try (PrintWriter out = new PrintWriter("airline_data.txt")) {
            for (Counter c : counters) {
                Passenger p = c.getPassenger();
                if (p != null) out.println("C," + p.getFirstName() + "," + p.getSurname() + "," + p.getPassportNumber() + "," + p.getCity() + "," + p.getSeatClass());
                else out.println("C,empty");
            }
            // Save waiting list passengers
            for (int i = 0; i < pQueue.getCount(); i++) {
                Passenger p = pQueue.getPassengerAt(i);
                out.println("W," + p.getFirstName() + "," + p.getSurname() + "," + p.getPassportNumber() + "," + p.getCity() + "," + p.getSeatClass());
            }
            System.out.println("Data Stored Successfully.");
        } catch (IOException e) { System.out.println("Error storing data."); }
    }

    // Loads data from the text file (Logic to be completed)
    private static void loadFromFile() {
        try (Scanner fs = new Scanner(new File("airline_data.txt"))) {
            // Logic to clear existing data and reload from file would go here
            System.out.println("Data Loaded Successfully.");
        } catch (FileNotFoundException e) { System.out.println("No data file found."); }
    }
}