/**
 * Hotel Booking Application - Booking Cancellation & Rollback
 * 
 * Demonstrates safe cancellation using Stack (LIFO) and
 * restoring inventory correctly.
 * 
 * @author Praveen
 * @version 10.0
 */
import java.util.*;
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getReservationId() {
        return reservationId;
    }
    public String getRoomType() {
        return roomType;
    }
    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }
    public void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }
    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

class CancellationService {
    private Stack<String> rollbackStack = new Stack<>();
    private Map<String, Reservation> activeBookings = new HashMap<>();
    public void addBooking(Reservation r) {
        activeBookings.put(r.getReservationId(), r);
    }
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID");
            return;
        }

        Reservation r = activeBookings.get(reservationId);
        rollbackStack.push(reservationId);
        inventory.increaseAvailability(r.getRoomType());
        activeBookings.remove(reservationId);

        System.out.println("Booking Cancelled: " + reservationId);
    }
    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Recent First): " + rollbackStack);
    }
}
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v10.0 =====");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();
        Reservation r1 = new Reservation("SR-1", "Alice", "Single Room");
        Reservation r2 = new Reservation("DR-1", "Bob", "Double Room");

        service.addBooking(r1);
        service.addBooking(r2);
        service.cancelBooking("SR-1", inventory);
        service.cancelBooking("XX-1", inventory); // invalid
        inventory.display();
        service.showRollbackStack();
    }
}
