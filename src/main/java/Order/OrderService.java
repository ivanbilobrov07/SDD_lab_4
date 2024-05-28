package Order;

import Cart.Cart;
import Delivery.DeliveryMethod;
import Delivery.DeliveryStrategy;
import Delivery.NovaPoshtaDelivery;
import Delivery.UkrposhtaDelivery;
import Payment.CashOnDeliveryPayment;
import Payment.PaymentByCard;
import Payment.PaymentMethod;
import Payment.PaymentStrategy;
import org.example.Console;
import Exceptions.EmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing orders.
 */
public class OrderService {
    public List<Order> orders;

    /**
     * Retrieves the list of orders.
     *
     * @return the list of orders
     */
    public List<Order> getOrders(){
        return orders;
    }

    /**
     * Constructs a new OrderService with an empty list of orders.
     */
    public OrderService() {
        orders = new ArrayList<>();
    }

    /**
     * Determines the appropriate payment strategy based on user input and the provided cart.
     *
     * @param cart the cart associated with the order
     * @return the selected payment strategy
     */
    private PaymentStrategy getPaymentStrategy(Cart cart){
        PaymentMethod paymentMethod = Console.askSelection(PaymentMethod.class);
        PaymentStrategy paymentStrategy = new PaymentByCard();

        System.out.println(cart.getTotalPrice() + " to pay");

        switch (paymentMethod) {
            case CARD -> paymentStrategy = new PaymentByCard();
            case CASH_ON_DELIVERY -> paymentStrategy = new CashOnDeliveryPayment();
        }

        return paymentStrategy;
    }

    /**
     * Determines the appropriate delivery strategy based on user input.
     *
     * @return the selected delivery strategy
     */
    private DeliveryStrategy getDeliveryStrategy(){
        DeliveryMethod deliveryMethod = Console.askSelection(DeliveryMethod.class);
        DeliveryStrategy deliveryStrategy = new NovaPoshtaDelivery();

        switch (deliveryMethod) {
            case NOVA_POSHTA -> deliveryStrategy = new NovaPoshtaDelivery();
            case UKRPOSHTA -> deliveryStrategy = new UkrposhtaDelivery();
        }

        return deliveryStrategy;
    }

    /**
     * Creates a new order based on the provided cart.
     *
     * @param cart the cart associated with the new order
     * @return the created order
     * @throws EmptyException if the cart is empty
     */
    public Order createOrder (Cart cart) throws EmptyException {
        if(cart.isEmpty()){
            throw new EmptyException("Your cart is empty");
        }

        String email = Console.askEmail();
        PaymentStrategy paymentStrategy = this.getPaymentStrategy(cart);
        DeliveryStrategy deliveryStrategy = this.getDeliveryStrategy();

        Order order = new Order(cart, email, paymentStrategy, deliveryStrategy);

        orders.add(order);

        order.getDeliveryAddress();

        return order;
    }
}
