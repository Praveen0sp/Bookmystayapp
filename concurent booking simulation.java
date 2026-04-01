/**
 * Hotel Booking Application - Concurrent Booking Simulation
 * 
 * Demonstrates thread-safe booking using synchronization
 * to prevent race conditions and double booking.
 * 
 * @author Praveen
 * @version 11.0
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
    private Map<String, Integer> inventory = new HashMap<>();
    public RoomInventory() {
        inventory.put("Single Room", 2);
    }
    public synchronized boolean allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }
    public synchronized int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.offer(r);
    }
    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            Reservation r;
            synchronized (queue) {
                r = queue.getRequest();
            }
            if (r == null) break;
            boolean success = inventory.allocateRoom(r.getRoomType());

            if (success) {
                System.out.println(Thread.currentThread().getName() +
                        " → Booking Confirmed: " + r.getGuestName());
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " → Booking Failed (No Rooms): " + r.getGuestName());
            }
        }
    }
}
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        System.out.println("===== Book My Stay App v11.0 =====");
        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Single Room"));
        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);
        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t1.start();
        t2.start();
    }
}
