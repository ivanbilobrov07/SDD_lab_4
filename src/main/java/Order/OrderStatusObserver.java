package Order;

/**
 * Interface for observers that want to be notified about order status changes.
 */
public interface OrderStatusObserver {
    /**
     * Called when an order status changes.
     *
     * @param status the new status of the order
     * @param email  the email associated with the order
     */
    void update(String status, String email);
}
