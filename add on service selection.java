/**
 * Hotel Booking Application - Add-On Service Selection
 * 
 * Demonstrates adding optional services to reservations
 * without modifying core booking or inventory logic.
 * 
 * @author Praveen
 * @version 7.0
 */
import java.util.*;
class AddOnService {
    private String serviceName;
    private double price;
    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }
    public String getServiceName() {
        return serviceName;
    }
    public double getPrice() {
        return price;
    }
}
class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Service added: " + service.getServiceName()
                + " for Reservation ID: " + reservationId);
    }
    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("\n--- Services for Reservation: " + reservationId + " ---");
        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }
        double total = 0;
        for (AddOnService s : services) {
            System.out.println(s.getServiceName() + " : ₹" + s.getPrice());
            total += s.getPrice();
        }
        System.out.println("Total Add-On Cost: ₹" + total);
    }
}
public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("===== Book My Stay App v7.0 =====");
        String res1 = "SR-1";
        String res2 = "DR-1";
        AddOnService wifi = new AddOnService("WiFi", 200);
        AddOnService breakfast = new AddOnService("Breakfast", 300);
        AddOnService spa = new AddOnService("Spa", 1000);

        AddOnServiceManager manager = new AddOnServiceManager();
        manager.addService(res1, wifi);
        manager.addService(res1, breakfast);
        manager.addService(res2, spa);
        manager.displayServices(res1);
        manager.displayServices(res2);
    }
}
