package AtilineTicketingSystem;

public class Passenger {
    // Variables to store passenger information
    private String firstName, surname, passportNumber, city, seatClass;

    // Constructor to initialize a new passenger object
    public Passenger(String fName, String sName, String passport, String city, String sClass) {
        this.firstName = fName;
        this.surname = sName;
        this.passportNumber = passport;
        this.city = city;
        this.seatClass = sClass;
    }

    // Getter methods to retrieve passenger data
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getPassportNumber() { return passportNumber; }
    public String getCity() { return city; }
    public String getSeatClass() { return seatClass; }

    // Method to return all details as a single string
    public String getDetails() {
        return firstName + " " + surname + " (Passport: " + passportNumber + ", City: " + city + ") - " + seatClass ;
    }
}