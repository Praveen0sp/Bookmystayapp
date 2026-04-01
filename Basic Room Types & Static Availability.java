/**
 * Hotel Booking Application - Room Initialization
 * 
 * This class demonstrates abstraction, inheritance, and static availability
 * of different room types.
 * 
 * @author Praveen
 * @version 2.1
 */

abstract class Room {
    private int beds;
    private double price;
    private String type;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1000);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2000);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v2.1 =====");
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("---------------------");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("---------------------");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}
