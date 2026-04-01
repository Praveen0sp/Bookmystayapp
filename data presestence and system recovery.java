/**
 * Hotel Booking Application - Data Persistence & Recovery
 * 
 * Demonstrates saving and restoring system state using
 * serialization and file handling.
 * 
 * @author Praveen
 * @version 12.0
 */

import java.io.*;
import java.util.*;
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;
    private String roomType;
    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;
    Map<String, Integer> inventory;
    List<Reservation> bookings;
    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}
class PersistenceService {

    private static final String FILE_NAME = "hotel_state.ser";

    public void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            SystemState state = (SystemState) ois.readObject();
            System.out.println("System state loaded successfully.");
            return state;

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v12.0 =====");

        PersistenceService persistence = new PersistenceService();
        SystemState state = persistence.load();
        Map<String, Integer> inventory;
        List<Reservation> bookings;
        if (state == null) {
            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            bookings = new ArrayList<>();
            bookings.add(new Reservation("SR-1", "Alice", "Single Room"));
            bookings.add(new Reservation("DR-1", "Bob", "Double Room"));
        } else {
            inventory = state.inventory;
            bookings = state.bookings;
        }
        System.out.println("\n--- Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("\n--- Booking History ---");
        for (Reservation r : bookings) {
            r.display();
        }
        persistence.save(new SystemState(inventory, bookings));
    }
}
