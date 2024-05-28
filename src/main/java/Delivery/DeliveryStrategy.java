package Delivery;

/**
 * Interface for implementing various delivery strategies.
 * This interface ensures that all delivery strategy implementations
 * provide a method for obtaining a delivery address.
 */
public interface DeliveryStrategy {
    /**
     * Retrieves the delivery address from the user
     * specific to the implementation of the delivery strategy.
     */
    void getDeliveryAddress();
}
