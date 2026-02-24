package org.example.utils;

import org.example.constants.OrderType;
import org.example.constants.Side;
import org.example.models.Order;

abstract public class MockData {
    private static int idCounter = 1;

    public static Order createNewOrder(Side side, double price, int quantity) {
        return new Order(
                idCounter++,
                side,
                OrderType.FillAndKill,
                price,
                quantity
        );
    }
}
