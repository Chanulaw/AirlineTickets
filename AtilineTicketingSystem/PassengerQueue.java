package AtilineTicketingSystem;

public class PassengerQueue {
    private Passenger[] waitingList = new Passenger[10]; // Array to store 10 waiting passengers
    private int front = 0, rear = 0, count = 0;

    // Add a passenger to the end of the queue
    public void enQueue(Passenger p) {
        if (count < 10) {
            waitingList[rear] = p;
            rear = (rear + 1) % 10; // Circular queue logic
            count++;
            System.out.println(p.getFirstName() + " added to Waiting List.");
        } else {
            System.out.println("Waiting List is Full!");
        }
    }

    // Remove and return the passenger from the front of the queue
    public Passenger deQueue() {
        if (count > 0) {
            Passenger p = waitingList[front];
            front = (front + 1) % 10; // Circular queue logic
            count--;
            return p;
        }
        return null; // Returns null if queue is empty
    }

    // Get the number of passengers in the queue
    public int getCount() { return count; }

    // Get a specific passenger from the queue for saving purposes
    public Passenger getPassengerAt(int position) {
        int index = (front + position) % 10;
        return waitingList[index];
    }
}