package org.example;
import Cart.*;
import Exceptions.*;
import Filter.*;
import Order.*;
import Product.*;
import Sort.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //BUILDER
        ProductBuilder productBuilder = new ProductBuilder();
        Product product1 = productBuilder
                .setTitle("Pack of milk")
                .setPrice(30)
                .setBrand("some brand of milk")
                .setCategory("dairy")
                .setDescription("some description")
                .setQuantity(20)
                .setImage("image link")
                .build();
        Product product2 = productBuilder
                .setTitle("Milk chocolate")
                .setPrice(50)
                .setBrand("Milka")
                .setCategory("Sweets")
                .setDescription("description")
                .setQuantity(10)
                .setImage("image")
                .build();

        //OBSERVER
        EmailObserver emailObserver = new EmailObserver();

        //CART
        Cart cart = new Cart();

        // SORT
        List<SortStrategy> allowedSortStrategies = new ArrayList<>();
        SortStrategy sortByTitleStrategy = new SortByTitle();
        SortStrategy sortByPriceStrategy = new SortByPrice();

        allowedSortStrategies.add(sortByTitleStrategy);
        allowedSortStrategies.add(sortByPriceStrategy);

        //FILTER
        FilterType filterToConfigure = null;
        FilterService filterService = new FilterService();

        ProductsService productsService = new ProductsService(sortByTitleStrategy, filterService);
        productsService.addProduct(product1);
        productsService.addProduct(product2);

        OrderService orderService = new OrderService();

        String productNameToFind = "";
        String step = "Step0";

        while (true) {
            try {
                switch (step) {
                    case "Step0": {
                        step = Console.askUserType();
                        break;
                    }
                    case "Customer_Step1": {
                        String customerAction = Console.askCustomerActions();

                        step = step + "." + customerAction;
                        break;
                    }
                    case "Customer_Step1.1": {
                        List<Product> products = productsService.getProducts(productNameToFind);

                        if(products.isEmpty()){
                            System.out.println("Didn't find any products");

                            step = step.substring(0, step.length() - 2);
                            break;
                        }

                        products.forEach(System.out::println);

                        String productsAction = Console.askCustomerProductsActions();

                        if (Objects.equals(productsAction, "3")) {
                            step = step.substring(0, step.length() - 2);
                            productNameToFind = "";

                            break;
                        }

                        step = step + "." + productsAction;
                        break;
                    }
                    case "Customer_Step1.1.1": {
                        List<Product> products = productsService.getProducts(productNameToFind);

                        Product chosenProduct = Console.getItemFromList(products);
                        System.out.println(chosenProduct.getInfo());

                        if(chosenProduct.getQauntity() > 0){
                            String productAction = Console.askProductActions();

                            if (Objects.equals(productAction, "1")) {
                                boolean flag = true;

                                while (flag){
                                    try {
                                        int quantity = Console.askQuantity();

                                        if (quantity == 0) {
                                            flag = false;
                                            break;
                                        }

                                        cart.addProduct(chosenProduct, quantity);

                                        flag = false;
                                        System.out.println("Product was successfully added");
                                    } catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        } else {
                            System.out.println("Product is out of stock");
                        }

                        step = step.substring(0, step.length() - 2);
                        productNameToFind = "";
                        break;
                    }
                    case "Customer_Step1.1.2": {
                        productNameToFind = Console.askProductNameToSearch();
                        step = step.substring(0, step.length() - 2);

                        break;
                    }
                    case "Customer_Step1.2": {
                        productsService.printAppliedFilters();
                        String filtersAction = Console.askFilterActions();

                        if (!Objects.equals(filtersAction, "5")){
                            filterToConfigure = FilterType.values()[Integer.parseInt(filtersAction) - 1];
                        }

                        step = step + "." + filtersAction;
                        break;
                    }
                    case "Customer_Step1.2.1", "Customer_Step1.2.2": {
                        System.out.println(filterToConfigure == FilterType.BRAND ?
                                "Brands: " + filterService.getBrandFilters() :
                                "Categories: " + filterService.getCategoryFilters());

                        String filterAction = Console.askArrayFilterActions();

                        step = step + "." + filterAction;
                        break;
                    }
                    case "Customer_Step1.2.1.1", "Customer_Step1.2.2.1": {
                        String newFilter = Console.askNewArrayFilter(filterToConfigure == FilterType.BRAND ? "brand" : "category");

                        if (filterToConfigure == FilterType.BRAND) {
                            filterService.addBrandToFilter(newFilter);
                        } else {
                            filterService.addCategoryFilter(newFilter);
                        }

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.2.1.2", "Customer_Step1.2.2.2": {
                        List<String> currentFilters = filterToConfigure == FilterType.BRAND ?
                                filterService.getBrandFilters() :
                                filterService.getCategoryFilters();

                        if (currentFilters.isEmpty()) {
                            System.out.println("There are any applied filters");

                            step = step.substring(0, step.length() - 2);
                            break;
                        }

                        String filterToDelete = Console.getItemFromList(currentFilters);

                        filterService.removeArrayFilter(filterToConfigure, filterToDelete);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.2.1.3", "Customer_Step1.2.2.3": {
                        filterService.clearAllArrayFilters(filterToConfigure);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.2.3", "Customer_Step1.2.4": {
                        Float currentValue = filterToConfigure == FilterType.MIN_PRICE ?
                                filterService.getMinPrice() :
                                filterService.getMaxPrice();
                        boolean alreadyHasValue = currentValue != null;
                        String valueToOutput = alreadyHasValue ? String.valueOf(currentValue) : "unset";

                        System.out.println(filterToConfigure == FilterType.MIN_PRICE ?
                                "Min price: " + valueToOutput :
                                "Max price: " + valueToOutput);

                        String filterAction = Console.askConcreteValueFilterActions(alreadyHasValue);

                        step = step + "." + filterAction;
                        break;
                    }
                    case "Customer_Step1.2.3.1", "Customer_Step1.2.4.1": {
                        Float value = Console.askNewPriceFilter(filterToConfigure == FilterType.MIN_PRICE ? "min price" : "max price");

                        filterService.changePriceFilters(filterToConfigure, value);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.2.3.2", "Customer_Step1.2.4.2": {
                        filterService.changePriceFilters(filterToConfigure, null);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.2.5": {
                        filterService.clearAllFilters();

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.3": {
                        System.out.println("Current sorting criteria is: " + productsService.getSortStrategy());
                        Console.askSortingActions();

                        SortStrategy newSortStrategy = Console.getItemFromList(allowedSortStrategies);
                        productsService.setSortStrategy(newSortStrategy);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.4": {
                        try {
                            cart.logCart();

                            String cartAction = Console.askCartAction();

                            step = step + "." + cartAction;
                        } catch (EmptyException e) {
                            System.out.println(e.getMessage());

                            step = step.substring(0, step.length() - 2);
                        }

                        break;
                    }
                    case "Customer_Step1.4.1": {
                        System.out.println("Enter index of product to remove");

                        CartProduct productToRemove = Console.getItemFromList(cart.getProducts());
                        cart.removeProduct(productToRemove);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.4.2": {
                        System.out.println("Enter index of product to change quantity");

                        CartProduct productToChangeQuantity = Console.getItemFromList(cart.getProducts());

                        while (true){
                            try{
                                System.out.println(productToChangeQuantity);
                                int newQuantity = Console.askQuantity();

                                cart.changeQuantity(productToChangeQuantity, newQuantity);
                                System.out.println("The quantity was successfully changed");
                                break;
                            } catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Customer_Step1.5": {
                        try {
                            Order order = orderService.createOrder(cart);
                            order.registerObserver(emailObserver);
                            order.setStatus("created");

                            order.startPayment();

                            cart = new Cart();

                            step = step.substring(0, step.length() - 2);
                            break;
                        } catch (EmptyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case "Admin_Step1": {
                        String adminAction = Console.askAdminActions();

                        step = step + "." + adminAction;
                        break;
                    }
                    case "Admin_Step1.1": {
                        List<Product> products = productsService.getProducts(productNameToFind);
                        boolean hasProducts = !products.isEmpty();

                        if(hasProducts){
                            products.forEach(System.out::println);
                        }

                        String productsAction = Console.askAdminProductsActions(hasProducts);

                        step = step + "." + productsAction;
                        break;
                    }
                    case "Admin_Step1.1.1": {
                        Product newProduct = Console.createProduct();
                        productsService.addProduct(newProduct);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Admin_Step1.1.2": {
                        System.out.println("Enter index of product to remove");
                        List<Product> products = productsService.getProducts("");

                        Product productToRemove = Console.getItemFromList(products);
                        productsService.removeProduct(productToRemove);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Admin_Step1.1.3": {
                        System.out.println("Enter index of product to change quantity");
                        List<Product> products = productsService.getProducts("");

                        Product productToChangeQuantity = Console.getItemFromList(products);
                        int newQuantity = Console.askQuantity();

                        productToChangeQuantity.changeQuantity(newQuantity);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                    case "Admin_Step1.2": {
                        List<Order> orders = orderService.getOrders();

                        if(orders.isEmpty()){
                            System.out.println("There are not any orders yet");

                            step = step.substring(0, step.length() - 2);
                            break;
                        }

                        System.out.println("Choose order to manage");
                        Order order = Console.getItemFromList(orders);

                        System.out.println(order.getInfo());
                        System.out.println("Enter a new status");

                        String status = scanner.next();
                        order.setStatus(status);

                        step = step.substring(0, step.length() - 2);
                        break;
                    }
                }
            }
            catch (BackStepException e) {
                if(step.equals("Admin_Step1") || step.equals("Customer_Step1")){
                    step = "Step0";
                } else {
                    step = step.substring(0, step.length() - 2);
                }
            }
        }
    }
}