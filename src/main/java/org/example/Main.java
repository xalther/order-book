package org.example;

import org.example.constants.OrderType;
import org.example.constants.Side;
import org.example.core.OrderBook;
import org.example.core.OrderBookCLI;
import org.example.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();
        seedFakeData(orderBook);
        new OrderBookCLI(orderBook).run();
    }

    private static void seedFakeData(OrderBook orderBook) {
        orderBook.addNewOrder(new Order(1000, Side.BUY, OrderType.GoodTillCancelled, 100.0, 5));
        orderBook.addNewOrder(new Order(1001, Side.BUY, OrderType.GoodTillCancelled, 130.0, 12));
        orderBook.addNewOrder(new Order(1002, Side.SELL, OrderType.GoodTillCancelled, 150.0, 8));
        orderBook.addNewOrder(new Order(1003, Side.SELL, OrderType.GoodTillCancelled, 170.0, 10));
    }
}