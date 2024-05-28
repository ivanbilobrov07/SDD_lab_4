package Payment;

import Order.*;

/**
 * Implementation of PaymentStrategy that handles cash on delivery payments.
 */
public class CashOnDeliveryPayment implements PaymentStrategy {
    /**
     * Starts the payment process for the given order using cash on delivery.
     *
     * @param order the order for which the payment is to be processed
     */
    @Override
    public void startPayment(Order order) {
        System.out.println("Cash on delivery");
    }

    /**
     * Returns a string representation of this payment strategy.
     *
     * @return a string representation of this payment strategy
     */
    @Override
    public String toString() {
        return "CashOnDeliveryPayment";
    }
}
