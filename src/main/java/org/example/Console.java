package org.example;
import Exceptions.BackStepException;
import Product.Product;
import Product.ProductBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * The class provides an interface for users to interact with the store system,
 * allowing various functionalities such as managing products, orders, and user details.
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );
    private static final Pattern CARD_PATTERN = Pattern.compile(
            "^\\d{16}$"
    );

    /**
     * Formats enum names into a more readable format by converting to lowercase and replacing underscores with spaces.
     *
     * @param name the name of the enum to format
     * @return a more readable string representation of the enum name
     */
    private static String formatEnumName(String name) {
        return name.toLowerCase().replace('_', ' ');
    }

    /**
     * Validates if the given response is within the specified range.
     *
     * @param response the user's response as a string
     * @param range the maximum valid integer value
     * @return true if the response is a valid integer within the range, false otherwise
     */
    public static boolean validateResponse(String response, int range) {
        try {
            int value = Integer.parseInt(response);
            return value >= 1 && value <= range;
        } catch (NumberFormatException e) {
             return false;
        }
    }

    /**
     * Validates the price input ensuring it's a valid float representing a non-negative value.
     *
     * @param priceStr the string input for the price
     * @return true if the input string can be parsed as a valid, non-negative float, false otherwise
     */
    public static boolean validatePrice(String priceStr){
        try {
            float price = Float.parseFloat(priceStr);
            return price >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates the quantity input to ensure it represents a non-negative integer.
     *
     * @param quantityStr the string input for quantity
     * @return true if the input string can be parsed as a valid, non-negative integer, false otherwise
     */
    public static boolean validateQuantity(String quantityStr){
        try {
            int quantity = Integer.parseInt(quantityStr);
            return quantity >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Prompts the user with a menu, validates the response, and handles the potential to go back a step in the menu flow.
     *
     * @param menu the string representing the menu to be displayed
     * @param range the valid range of integer inputs
     * @param checkLastValue if true, the last value in the range is used to throw a BackStepException to move back in menu flow
     * @return the validated user input as a string
     * @throws BackStepException if the last menu value is selected and checkLastValue is true
     */
    private static String promptUser(String menu, int range, boolean checkLastValue) throws BackStepException {
        System.out.println(menu);
        String action = new Scanner(System.in).nextLine();

        while (!validateResponse(action, range)) {
            System.out.println("Incorrect value");
            System.out.println(menu);
            action = new Scanner(System.in).nextLine();
        }

        if(checkLastValue && Objects.equals(action, Integer.toString(range))){
            throw new BackStepException();
        }

        return action;
    }

    /**
     * Requests user to choose their role with specified options and navigates to the corresponding step.
     *
     * @return the next step in the flow based on user role selection
     */
    public static String askUserType() throws BackStepException {
        String response = promptUser("""
                Choose your role:
                1. Customer
                2. ADMIN""", 2, false);

        return Objects.equals(response, "1") ? "Customer_Step1" : "Admin_Step1";
    }

    /**
     * Handles customer actions allowing navigation through various product and order management functionalities.
     *
     * @return the next action for the customer based on their selection
     * @throws BackStepException to signal the need to go back in the menu flow
     */
    public static String askCustomerActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. View products
                2. Manage products filters
                3. Manage sorting criteria
                4. Manage cart
                5. Create Order
                6. Exit""", 6, true);
    }

    /**
     * Allows customers to interact with specific product functionalities such as viewing details or searching by name.
     *
     * @return the selected product action
     */
    public static String askCustomerProductsActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. View some product details
                2. Search by product name
                3. Back""", 3, false);
    }

    /**
     * Provides options for admin users to manage products, including adding, deleting, or modifying products.
     *
     * @param hasProducts indicates whether there are products already in the system
     * @return the selected action for managing products
     * @throws BackStepException to signal the need to go back or exit in the product management flow
     */
    public static String askAdminProductsActions(boolean hasProducts) throws BackStepException {
        if(hasProducts) {
            return promptUser("""
                Choose action:
                1. Add a new product
                2. Delete a product
                3. Change product quantity
                4. Back""", 4, true);
        } else {
            return promptUser("""
                Choose action:
                1. Add a new product
                2. Back""", 2, true);
        }
    }

    /**
     * Prompts the user to choose actions related to a single product, such as adding it to the cart or going back.
     *
     * @return the next action to be taken with the product
     * @throws BackStepException to signal the need to go back in the product interaction flow
     */
    public static String askProductActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. Add product to the cart
                2. Back""", 2, true);
    }

    /**
     * Allows the user to modify the sorting criteria for product listing.
     *
     * @return the action chosen for changing sorting criteria
     * @throws BackStepException to handle the back step in navigation
     */
    public static String askSortingActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. Change sorting criteria
                2. Back""", 2, true);
    }

    /**
     * Provides options for applying various filters to product searches, such as by brand, category, or price.
     *
     * @return the action chosen for configuring filters
     * @throws BackStepException to signal the need to go back in the filter configuration flow
     */
    public static String askFilterActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. Configure brand filtering
                2. Configure category filtering
                3. Configure min price value
                4. Configure max price value
                5. Clear all filters
                6. Back""", 6, true);
    }

    /**
     * Allows the user to manage a list of filters, providing options to add, remove, or clear all filters.
     *
     * @return the selected action for managing an array of filters
     * @throws BackStepException to handle the back step in the array filter management flow
     */
    public static String askArrayFilterActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. Add a new filter
                2. Remove one filter
                3. Clear filters
                4. Back""", 4, true);
    }

    /**
     * Collects a new filter value from the user for a specified filter type.
     *
     * @param filterType the type of filter (brand, category, etc.) for which a new value is being added
     * @return the filter value as entered by the user
     */
    public static String askNewArrayFilter(String filterType){
        System.out.println("Enter a new " + filterType + " to add");

        String value = scanner.nextLine().trim();

        while(value.isEmpty()){
            System.out.println("Please provide a valid " + filterType);

            value = scanner.nextLine().trim();
        }

        return value;
    }

    /**
     * Handles the modification or removal of a filter value based on whether a filter already exists.
     *
     * @param alreadyHasValue indicates if the filter currently has a value
     * @return the action chosen for modifying or removing a filter
     * @throws BackStepException if a back step is needed based on user input
     */
    public static String askConcreteValueFilterActions(boolean alreadyHasValue) throws BackStepException {
        if(alreadyHasValue){
            return promptUser("Choose action:\n1. Change a filter value\n2. Remove a filter value\n3. Back\n0. Exit", 3, true);
        } else {
            return promptUser("Choose action:\n1. Add a filter value\n2. Back\n0. Exit", 2, true);
        }
    }

    /**
     * Prompts the user to enter a price value for a specified filter and validates it.
     *
     * @param filterType describes the filter for which the price is being set (min or max price)
     * @return the validated price as a float
     */
    public static float askNewPriceFilter(String filterType){
        System.out.println("Enter " + filterType + " value: ");

        String value = scanner.nextLine();

        while (!validatePrice(value)){
            System.out.println("Incorrect value");
            System.out.println("Enter " + filterType + " value: ");

            value = scanner.nextLine();
        }

        return  Float.parseFloat(value);
    }

    /**
     * Allows the user to choose an action related to cart management, such as removing a product or adjusting quantities.
     *
     * @return the chosen cart action as a string
     * @throws BackStepException if the user chooses to go back
     */
    public static String askCartAction() throws BackStepException {
        return promptUser("""
                Choose action:
                1. Remove product from cart
                2. Manage products quantity
                3. Back""", 3, true);
    }


    /**
     * Retrieves an item from a list based on user selection.
     *
     * @param <T> the type of the items in the list
     * @param list the list of items to choose from
     * @return the selected item, or null if the list is empty
     */
    public static <T> T getItemFromList(List<T> list){
        if (list.isEmpty()) {
            return null;
        }

        for (int i = 0; i < list.size(); i++){
            System.out.println(i + 1 + ". " + list.get(i));
        }

        String selectedIndex = scanner.nextLine();

        while (!validateResponse(selectedIndex, list.size())){
            for (int i = 0; i < list.size(); i++){
                System.out.println(i + 1 + ". " + list.get(i));
            }

            selectedIndex = scanner.nextLine();
        }

        return list.get(Integer.parseInt(selectedIndex) - 1);
    }

    /**
     * Prompts the user to choose a quantity and validates the input.
     *
     * @return the chosen quantity as an integer
     */
    public static int askQuantity(){
        System.out.println("Choose quantity: ");

        String quantity = new Scanner(System.in).nextLine();

        while (!validateQuantity(quantity)){
            System.out.println("Incorrect quantity value");
            System.out.println("Choose quantity: ");

            quantity = scanner.nextLine();
        }

        return Integer.parseInt(quantity);
    }

    /**
     * Prompts the user to enter a valid email address and validates it against a regular expression.
     *
     * @return the validated email address as a string
     */
    public static String askEmail() {
        while (true) {
            System.out.print("Please enter your email: ");
            String email = scanner.nextLine().trim();
            if (EMAIL_PATTERN.matcher(email).matches()) {
                return email;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to enter a valid 16-digit credit card number and validates it.
     *
     * @return the validated card number as a string
     */
    public static String askPaymentDetails() {
        while (true) {
            System.out.print("Please enter your 16-digit card number: ");
            String cardNumber = scanner.nextLine().trim();
            if (CARD_PATTERN.matcher(cardNumber).matches()) {
                return cardNumber;
            } else {
                System.out.println("Invalid card number format. Please enter exactly 16 digits.");
            }
        }
    }

    /**
     * Allows the user to select an option from an enumeration.
     *
     * @param <T> the enum type
     * @param enumClass the class of the enum
     * @return the selected enum constant
     */
    public static <T extends Enum<T>> T askSelection(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();

        while (true) {
            System.out.print("Choose an option: \n");

            for (int i = 0; i < enumConstants.length; i++) {
                System.out.println(i + 1 + ". " + formatEnumName(enumConstants[i].name()));
            }

            String indexStr = scanner.nextLine();

            if (validateResponse(indexStr, enumConstants.length)) {
                return enumConstants[Integer.parseInt(indexStr) - 1];
            } else {
                System.out.println("Incorrect index. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to enter the name of a product to search for.
     *
     * @return the product name as entered by the user
     */
    public static String askProductNameToSearch(){
        System.out.println("Enter the name of the product to find: ");

        return scanner.nextLine();
    }

    /**
     * Presents administrative action choices to the user and handles navigation.
     *
     * @return the next administrative action
     * @throws BackStepException if the user selects the option to go back
     */
    public static String askAdminActions() throws BackStepException {
        return promptUser("""
                Choose action:
                1. Manage products
                2. Manage orders
                3. Exit""", 3, true);
    }


    /**
     * Guides the user through the product creation process, validating inputs at each step.
     *
     * @return the newly created product
     */
    public static Product createProduct(){
        ProductBuilder productBuilder = new ProductBuilder();
        int creationStep = 0;
        boolean isProductCreated = false;

        while (!isProductCreated){
            try {
                switch (creationStep) {
                    case 0: {
                        System.out.println("Enter the title of the product: ");
                        String title = scanner.next();

                        productBuilder.setTitle(title);
                        creationStep++;
                        break;
                    }
                    case 1: {
                        System.out.println("Enter the price of the product: ");
                        String price = scanner.next();

                        if (!validatePrice(price)){
                            throw new IllegalArgumentException("Invalid price");
                        }

                        productBuilder.setPrice(Float.parseFloat(price));
                        creationStep++;
                        break;
                    }
                    case 2: {
                        System.out.println("Enter the description of the product: (you can leave empty)");
                        String description = scanner.next();

                        productBuilder.setDescription(description);
                        creationStep++;
                        break;
                    }
                    case 3: {
                        System.out.println("Enter the brand of the product: ");
                        String brand = scanner.next();

                        productBuilder.setBrand(brand);
                        creationStep++;
                        break;
                    }
                    case 4: {
                        System.out.println("Enter the category of the product: ");
                        String category = scanner.next();

                        productBuilder.setCategory(category);
                        creationStep++;
                        break;
                    }
                    case 5: {
                        productBuilder.setQuantity(askQuantity());
                        creationStep++;
                        break;
                    }
                    case 6: {
                        System.out.println("Enter the image url of the product: (you can leave empty)");
                        String image = scanner.next();

                        productBuilder.setImage(image);

                        isProductCreated = true;
                        break;
                    }
                }
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        return productBuilder.build();
    }
}
