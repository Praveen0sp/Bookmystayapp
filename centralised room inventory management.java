/**
 * Hotel Booking Application - Centralized Room Inventory
 * 
 * This class demonstrates the use of HashMap to manage
 * room availability as a single source of truth.
 * 
 * @author Praveen
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;
class RoomInventory {

    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + change);
    }
    public void displayInventory() {
        System.out.println("---- Current Room Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        System.out.println("===== Book My Stay App v3.1 =====");
        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
        System.out.println("--------------------------");
        inventory.updateAvailability("Single Room", -1);
        System.out.println("After Booking 1 Single Room:");
        inventory.displayInventory();
    }
}
