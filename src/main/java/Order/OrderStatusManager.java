package Order;

/**
 * Interface for managing order status and notifying observers about status changes.
 */
public interface OrderStatusManager {
    /**
     * Registers an observer to be notified of order status changes.
     *
     * @param observer the observer to register
     */
    void registerObserver(OrderStatusObserver observer);

    /**
     * Removes a registered observer so that it will no longer be notified of order status changes.
     *
     * @param observer the observer to remove
     */
    void removeObserver(OrderStatusObserver observer);

    /**
     * Notifies all registered observers of an order status change.
     */
    void notifyObservers();
}
