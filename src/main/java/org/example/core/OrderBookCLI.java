package org.example.core;

import org.example.constants.OrderType;
import org.example.constants.Side;
import org.example.models.Order;

import java.util.Scanner;

public class OrderBookCLI {
    private final OrderBook orderBook;
    private final Scanner scanner;
    private int idCounter = 1;

    public OrderBookCLI(OrderBook orderBook) {
        this.orderBook = orderBook;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean isRunning = true;
        System.out.println("\n======= ORDER BOOK =======");

        while (isRunning) {
            System.out.println("\nChoose action:");
            System.out.println("1. Add new BUY order");
            System.out.println("2. Add new SELL order");
            System.out.println("3. Cancel existing order");
            System.out.println("4. Show order book");
            System.out.println("5. Close");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewOrder(Side.BUY);
                    break;
                case "2":
                    addNewOrder(Side.SELL);
                    break;
                case "3":
                    cancelOrder();
                    break;
                case "4":
                    orderBook.printOrderBook();
                    break;
                case "5":
                    isRunning = false;
                    System.out.println("Closing order book");
                    break;
                default:
                    System.out.println("Command not found");
            }
        }
    }

    private void addNewOrder(Side side) {
        try {
            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            int orderId = idCounter++;
            Order order = new Order(orderId, side, OrderType.GoodTillCancelled, price, quantity);

            orderBook.addNewOrder(order);
            System.out.println("New order added, ID: " + orderId);
        } catch (NumberFormatException e) {
            System.out.println("Error: incorrect number format");
        }
    }

    private void cancelOrder() {
        try {
            System.out.print("ID order to cancel: ");
            int id = Integer.parseInt(scanner.nextLine());

            Order orderToCancel = orderBook.getOrders().get(id);
            if (orderToCancel != null) {
                orderBook.cancelOrder(orderToCancel);
                System.out.println("Order canceled, ID: " + id);
            } else {
                System.out.println("ID not found: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: incorrect number format");
        }
    }
}
