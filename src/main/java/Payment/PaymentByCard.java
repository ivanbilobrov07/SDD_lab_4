package Payment;

import org.example.Console;
import Order.Order;

/**
 * Implementation of PaymentStrategy that handles payments by card.
 */
public class PaymentByCard implements PaymentStrategy {
    /**
     * Starts the payment process for the given order using a card.
     *
     * @param order the order for which the payment is to be processed
     */
    @Override
    public void startPayment(Order order) {
        String card = Console.askPaymentDetails();
        System.out.println("Successfully paid " + order.getTotalPrice() +  " with card: " + card);

        order.setStatus("paid");
    }

    /**
     * Returns a string representation of this payment strategy.
     *
     * @return a string representation of this payment strategy
     */
    @Override
    public String toString() {
        return "PaymentByCard";
    }
}