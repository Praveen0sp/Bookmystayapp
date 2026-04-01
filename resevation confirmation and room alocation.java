/**
 * Hotel Booking Application - Room Allocation Service
 * 
 * Demonstrates booking confirmation, unique room allocation,
 * and inventory synchronization to prevent double-booking.
 * 
 * @author Praveen
 * @version 6.0
 */

import java.util.*;
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, getAvailability(roomType) - 1);
    }
}
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // FIFO
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
class BookingService {

    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomAllocations = new HashMap<>();
    public void processBookings(BookingRequestQueue queue, RoomInventory inventory) {
        System.out.println("---- Processing Bookings ----");
        while (!queue.isEmpty()) {
            Reservation request = queue.getNextRequest();
            String type = request.getRoomType();
            if (inventory.getAvailability(type) > 0) {
                String roomId = generateRoomId(type);
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);
                    roomAllocations.putIfAbsent(type, new HashSet<>());
                    roomAllocations.get(type).add(roomId);
                    inventory.reduceAvailability(type);
                    System.out.println("Booking Confirmed:");
                    System.out.println("Guest: " + request.getGuestName());
                    System.out.println("Room Type: " + type);
                    System.out.println("Room ID: " + roomId);
                    System.out.println("------------------------");
                }

            } else {
                System.out.println("Booking Failed (No Availability): "
                        + request.getGuestName() + " - " + type);
            }
        }
    }
    private String generateRoomId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" + (allocatedRoomIds.size() + 1);
    }
}
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("===== Book My Stay App v6.0 =====");
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        BookingService bookingService = new BookingService();
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room")); // should fail
        queue.addRequest(new Reservation("David", "Suite Room"));
        bookingService.processBookings(queue, inventory);
    }
}
