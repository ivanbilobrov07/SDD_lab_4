package Delivery;

import java.util.Scanner;

/**
 * Implements {@link DeliveryStrategy} for Nova Poshta delivery method.
 * This class is responsible for handling the specifics of obtaining a delivery
 * address for Nova Poshta deliveries, which primarily involves specifying a post office.
 */
public class NovaPoshtaDelivery implements DeliveryStrategy{
    private String postOffice;

    /**
     * Prompts the user to enter the post office number/address for delivery.
     * The input is read from the console and stored as the delivery address.
     */
    @Override
    public void getDeliveryAddress() {
        System.out.println("Enter your post office");
        postOffice = new Scanner(System.in).next();
    }

    /**
     * Returns a string representation of this delivery instance,
     * showing the specific post office used for the delivery.
     *
     * @return formatted string indicating the Nova Poshta delivery post office
     */
    @Override
    public String toString() {
        return "NovaPoshtaDelivery, post office - " + postOffice;
    }
}
