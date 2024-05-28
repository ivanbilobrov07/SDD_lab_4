package Delivery;

import java.util.Scanner;

/**
 * Implements {@link DeliveryStrategy} for Ukrposhta delivery method.
 * This class manages the details required for deliveries using Ukrposhta,
 * specifically by collecting the post office address where the package will be delivered.
 */
public class UkrposhtaDelivery implements DeliveryStrategy{
    private String postOffice;

    /**
     * Prompts the user to enter the post office number/address for delivery.
     * This method collects input directly from the user via the console,
     * and stores it as the delivery address in the postOffice field.
     */
    @Override
    public void getDeliveryAddress() {
        System.out.println("Enter your post office");
        postOffice = new Scanner(System.in).next();
    }

    /**
     * Returns a string representation of this delivery instance,
     * highlighting the specific post office used for Ukrposhta delivery.
     *
     * @return a formatted string indicating the Ukrposhta delivery post office
     */
    @Override
    public String toString() {
        return "UkrposhtaDelivery, post office - " + postOffice;
    }
}
