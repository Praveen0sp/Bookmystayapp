/**
 * Hotel Booking Application - Booking Request Queue
 * 
 * Demonstrates FIFO booking request handling using Queue.
 * Requests are collected but NOT processed yet.
 * 
 * @author Praveen
 * @version 5.0
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
    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}
class BookingRequestQueue {
    private Queue<Reservation> queue;
    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added:");
        reservation.display();
    }
    public void displayQueue() {
        System.out.println("\n---- Booking Requests Queue ----");
        for (Reservation r : queue) {
            r.display();
        }
    }
}
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v5.0 =====");
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.displayQueue();
    }
}
