/**
 * Hotel Booking Application - Error Handling & Validation
 * 
 * Demonstrates input validation, custom exceptions,
 * and fail-fast error handling.
 * 
 * @author Praveen
 * @version 9.0
 */

import java.util.*;
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
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
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 1);
    }
    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void reduceAvailability(String roomType) throws InvalidBookingException {
        int current = getAvailability(roomType);
        if (current <= 0) {
            throw new InvalidBookingException("No availability for " + roomType);
        }
        inventory.put(roomType, current - 1);
    }
}
class InvalidBookingValidator {
    public void validate(Reservation reservation, RoomInventory inventory)
            throws InvalidBookingException {
        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }
        if (!inventory.isValidRoomType(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + reservation.getRoomType());
        }
        if (inventory.getAvailability(reservation.getRoomType()) <= 0) {
            throw new InvalidBookingException("Room not available: " + reservation.getRoomType());
        }
    }
}
class BookingService {
    private InvalidBookingValidator validator = new InvalidBookingValidator();
    public void processBooking(Reservation reservation, RoomInventory inventory) {
        try {
            validator.validate(reservation, inventory);
            inventory.reduceAvailability(reservation.getRoomType());
            System.out.println("Booking Successful:");
            System.out.println("Guest: " + reservation.getGuestName());
            System.out.println("Room: " + reservation.getRoomType());
            System.out.println("----------------------");

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
            System.out.println("----------------------");
        }
    }
}
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("===== Book My Stay App v9.0 =====");
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService();
        bookingService.processBooking(
                new Reservation("Alice", "Single Room"), inventory);
        bookingService.processBooking(
                new Reservation("Bob", "Luxury Room"), inventory);
        bookingService.processBooking(
                new Reservation("Charlie", "Double Room"), inventory);
        bookingService.processBooking(
                new Reservation("", "Suite Room"), inventory);
    }
}
