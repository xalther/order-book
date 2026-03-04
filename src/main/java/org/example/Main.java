package org.example;

import org.example.constants.OrderType;
import org.example.constants.Side;
import org.example.core.OrderBook;
import org.example.models.Order;
import org.example.utils.MockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        testSimpleMatch();
        testNoMatch();
    }

    private static void testSimpleMatch() {
        logger.info("Simple match test");
        OrderBook book = new OrderBook();

        book.addNewOrder(MockData.createNewOrder(Side.BUY, 100.0, 10));
        book.addNewOrder(MockData.createNewOrder(Side.SELL, 100.0, 7));
        Order order = new Order(1, Side.BUY, OrderType.GoodTillCancelled, 100.0, 10);
        book.cancelOrder(order);
    }

    private static void testNoMatch() {
        logger.info("No match test");
        OrderBook book = new OrderBook();

        book.addNewOrder(MockData.createNewOrder(Side.BUY, 95.0, 10));
        book.addNewOrder(MockData.createNewOrder(Side.SELL, 105.0, 10));
    }
}