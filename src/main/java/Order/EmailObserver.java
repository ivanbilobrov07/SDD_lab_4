package Order;

/**
 * Implementation of OrderStatusObserver that sends email notifications when the order status changes.
 */
public class EmailObserver implements OrderStatusObserver {
    /**
     * Called when an order status changes. Sends an email notification with the new status.
     *
     * @param status the new status of the order
     * @param email  the email associated with the order
     */
    @Override
    public void update(String status, String email) {
        sendEmail(status, email);
    }

    /**
     * Sends an email notification with the given status to the specified email address.
     *
     * @param status the new status of the order
     * @param email  the email address to send the notification to
     */
    private void sendEmail(String status, String email) {
        System.out.println("Sending email to " + email + ": Your order status is now '" + status + "'.");
    }
}