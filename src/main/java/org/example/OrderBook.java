package org.example;

import java.util.*;

public class OrderBook {
    private final TreeMap<Double, ArrayDeque<Order>> asks = new TreeMap<>();
    private final TreeMap<Double, ArrayDeque<Order>> bids = new TreeMap<>(Comparator.reverseOrder());
    private final Map<Integer, Order> orders = new HashMap<>();

    private boolean canMatchNewOrder(Side side, double price) {
        if (side == Side.BUY) {
            if (asks.isEmpty()) return false;
            return price >= asks.firstKey();
        } else {
            if (bids.isEmpty()) return false;
            return price <= bids.firstKey();
        }
    }
}
