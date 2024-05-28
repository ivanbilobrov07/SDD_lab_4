package Order;

import Cart.Cart;
import Delivery.DeliveryStrategy;
import Payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for managing order status and notifying observers about status changes.
 */
public class Order implements OrderStatusManager {
    private List<OrderStatusObserver> observers;
    private String status;
    private Cart cart;
    private String email;
    private PaymentStrategy paymentStrategy;
    private DeliveryStrategy deliveryStrategy;

    /**
     * Gets the total price of the order.
     *
     * @return the total price of the order
     */
    public float getTotalPrice() {
        return cart.getTotalPrice();
    }

    /**
     * Sets the status of the order and notifies all registered observers of the status change.
     *
     * @param status the new status of the order
     */
    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    /**
     * Constructs a new Order with the specified cart, email, payment strategy, and delivery strategy.
     *
     * @param cart the cart associated with this order
     * @param email the email associated with this order
     * @param paymentStrategy the payment strategy to use for this order
     * @param deliveryStrategy the delivery strategy to use for this order
     */
    public Order(Cart cart, String email, PaymentStrategy paymentStrategy, DeliveryStrategy deliveryStrategy){
        this.cart = cart;
        this.email = email;
        this.paymentStrategy = paymentStrategy;
        this.deliveryStrategy = deliveryStrategy;
        this.observers = new ArrayList<>();

        this.cart.removeQuantityInProducts();
    }

    /**
     * Registers an observer to be notified of order status changes.
     *
     * @param observer the observer to register
     */
    public void registerObserver(OrderStatusObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes a registered observer so that it will no longer be notified of order status changes.
     *
     * @param observer the observer to remove
     */
    @Override
    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of an order status change.
     */
    @Override
    public void notifyObservers() {
        for (OrderStatusObserver observer : observers) {
            observer.update(status, email);
        }
    }

    /**
     * Starts the payment process for the order using the specified payment strategy.
     */
    public void startPayment(){
        this.paymentStrategy.startPayment(this);
    }

    /**
     * Retrieves the delivery address using the specified delivery strategy.
     */
    public void getDeliveryAddress() {
        this.deliveryStrategy.getDeliveryAddress();
    }

    /**
     * Returns a string representation of the order, including the cart, email, payment strategy, and delivery strategy.
     *
     * @return a string representation of the order
     */
    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", email='" + email  +
                ", paymentStrategy=" + paymentStrategy +
                ", deliveryStrategy=" + deliveryStrategy +
                '}';
    }

    /**
     * Returns detailed information about the order, including the cart, email, payment strategy, delivery strategy, and status.
     *
     * @return detailed information about the order
     */
    public String getInfo() {
        return "Order{" +
                "cart=" + cart +
                ", email='" + email  +
                ", paymentStrategy=" + paymentStrategy +
                ", deliveryStrategy=" + deliveryStrategy +
                ", status=" + status +
                '}';
    }
}
