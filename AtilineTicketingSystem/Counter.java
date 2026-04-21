package AtilineTicketingSystem;

public class Counter {
    private Passenger passenger; // Holds the passenger currently at the counter

    // Initially, the counter is empty
    public Counter() { this.passenger = null; }

    // Set a passenger to the counter
    public void setPassenger(Passenger p) { this.passenger = p; }

    // Get the passenger currently at the counter
    public Passenger getPassenger() { return passenger; }

    // Check if the counter is empty
    public boolean isEmpty() { return passenger == null; }
}