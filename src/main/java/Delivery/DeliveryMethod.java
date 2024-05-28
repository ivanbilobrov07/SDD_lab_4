package Delivery;


/**
 * Enum representing the delivery methods available for shipping products.
 * Currently supports delivery services like NOVA_POSHTA and UKRPOSHTA.
 */
public enum DeliveryMethod {
    NOVA_POSHTA, UKRPOSHTA;

    /**
     * Converts the name of the enum constant to a lowercase format suitable for display or logging.
     * This method is particularly useful for user interfaces or communications where a more readable format is desired.
     * For instance, 'NOVA_POSHTA' becomes 'nova poshta'.
     *
     * @return a lowercase, space-separated version of the enum constant name
     */
    public String toLowerCase() {
        return this.name().toLowerCase().replace('_', ' ');
    }
}
