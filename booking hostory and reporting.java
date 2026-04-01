/**
 * Hotel Booking Application - Booking History & Reporting
 * 
 * Demonstrates storing confirmed bookings and generating reports
 * using List to maintain chronological order.
 * 
 * @author Praveen
 * @version 8.0
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
    public String getGuestName() {
        return guestName;
    }
    public String getRoomType() {
        return roomType;
    }
    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }
    public List<Reservation> getAllReservations() {
        return history;
    }
}
class BookingReportService {
    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("\n---- Booking History ----");
        for (Reservation r : reservations) {
            r.display();
        }
    }
    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n---- Booking Summary Report ----");
        Map<String, Integer> countByRoom = new HashMap<>();
        for (Reservation r : reservations) {
            countByRoom.put(
                r.getRoomType(),
                countByRoom.getOrDefault(r.getRoomType(), 0) + 1
            );
        }
        for (Map.Entry<String, Integer> entry : countByRoom.entrySet()) {
            System.out.println(entry.getKey() + " Bookings: " + entry.getValue());
        }
        System.out.println("Total Bookings: " + reservations.size());
    }
}
public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v8.0 =====");
        BookingHistory history = new BookingHistory();
        history.addReservation(new Reservation("SR-1", "Alice", "Single Room"));
        history.addReservation(new Reservation("DR-1", "Bob", "Double Room"));
        history.addReservation(new Reservation("SR-2", "Charlie", "Single Room"));
        history.addReservation(new Reservation("SU-1", "David", "Suite Room"));
        BookingReportService reportService = new BookingReportService();
        reportService.displayAllBookings(history.getAllReservations());
        reportService.generateSummary(history.getAllReservations());
    }
}
