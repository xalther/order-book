package org.example.core;

import org.example.utils.MockData;
import org.example.models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class OrderBookTest {
    private OrderBook orderBook;

    @BeforeEach
    public void setUp() {
        orderBook = new OrderBook();
    }

    @Test
    @DisplayName("Should match buy and sell orders, fill transaction and clear them from orders")
    public void testFullMatch() {
        Order buyOrder = MockData.createBuyOrder(100.0, 10);
        Order sellOrder = MockData.createSellOrder(100.0, 10);

        orderBook.addNewOrder(buyOrder);
        orderBook.addNewOrder(sellOrder);

        Assertions.assertEquals(0, buyOrder.getRemainingQuantity());
        Assertions.assertEquals(0, sellOrder.getRemainingQuantity());
        Assertions.assertFalse(orderBook.getOrders().containsKey(buyOrder.getOrderId()));
        Assertions.assertFalse(orderBook.getOrders().containsKey(sellOrder.getOrderId()));
    }

    @Test
    @DisplayName("Should match orders and then cancel existing buy order")
    public void testPartialMatchAndCancel() {
        Order buyOrder = MockData.createBuyOrder(100.0, 10);
        Order sellOrder = MockData.createSellOrder(100.0, 7);

        orderBook.addNewOrder(buyOrder);
        orderBook.addNewOrder(sellOrder);

        Assertions.assertEquals(3, buyOrder.getRemainingQuantity());
        Assertions.assertTrue(orderBook.getOrders().containsKey(buyOrder.getOrderId()));

        orderBook.cancelOrder(buyOrder);
        Assertions.assertFalse(orderBook.getOrders().containsKey(buyOrder.getOrderId()));
    }

    @Test
    @DisplayName("Orders should not match when buy order price is lower than sell order price")
    public void testNoMatch() {
        Order buyOrder = MockData.createBuyOrder(50.0, 10);
        Order sellOrder = MockData.createSellOrder(100.0, 10);

        orderBook.addNewOrder(buyOrder);
        orderBook.addNewOrder(sellOrder);

        Assertions.assertEquals(10, buyOrder.getRemainingQuantity());
        Assertions.assertEquals(10, sellOrder.getRemainingQuantity());
        Assertions.assertTrue(orderBook.getOrders().containsKey(buyOrder.getOrderId()));
        Assertions.assertTrue(orderBook.getOrders().containsKey(sellOrder.getOrderId()));
    }
}
