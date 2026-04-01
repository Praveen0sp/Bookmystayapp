/**
 * Hotel Booking Application - Room Search
 * 
 * Demonstrates read-only search functionality using centralized inventory.
 * Only available rooms are displayed without modifying system state.
 * 
 * @author Praveen
 * @version 4.0
 */

import java.util.*;
abstract class Room {
    private String type;
    private int beds;
    private double price;
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
class RoomInventory {
    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}
class RoomSearchService {
    public void searchAvailableRooms(RoomInventory inventory, List<Room> rooms) {
        System.out.println("---- Available Rooms ----");
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getType());
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("----------------------");
            }
        }
    }
}
public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("===== Book My Stay App v4.0 =====");
        RoomInventory inventory = new RoomInventory();
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, rooms);
    }
}
