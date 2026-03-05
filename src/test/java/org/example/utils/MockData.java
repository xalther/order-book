package org.example.utils;

import org.example.constants.OrderType;
import org.example.constants.Side;
import org.example.models.Order;

public final class MockData {
    private MockData() {
        throw new AssertionError();
    }

    private static int idCounter = 1;

    public static Order createBuyOrder(double price, int quantity) {
        return new Order(
                idCounter++,
                Side.BUY,
                OrderType.GoodTillCancelled,
                price,
                quantity
        );
    }

    public static Order createSellOrder(double price, int quantity) {
        return new Order(
                idCounter++,
                Side.SELL,
                OrderType.GoodTillCancelled,
                price,
                quantity
        );
    }
}
