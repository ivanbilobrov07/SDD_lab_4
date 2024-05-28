package Payment;

/**
 * Enum representing different payment methods.
 */
public enum PaymentMethod {
    CASH_ON_DELIVERY, CARD;

    /**
     * Converts the enum name to a lowercase string with underscores replaced by spaces.
     *
     * @return the name of the enum in lowercase with spaces instead of underscores
     */

    public String toLowerCase() {
        return this.name().toLowerCase().replace('_', ' ');
    }
}
