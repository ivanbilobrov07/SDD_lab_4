package Payment;

import Order.Order;


/**
 * Interface for defining payment strategies.
 */
public interface PaymentStrategy {
    /**
     * Starts the payment process for the given order.
     *
     * @param order the order for which the payment is to be processed
     */
    void startPayment(Order order);
}