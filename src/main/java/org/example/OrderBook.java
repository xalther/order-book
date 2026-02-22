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

    public List<Trade> matchingEngine() {
        List<Trade> trades = new ArrayList<>();
            while (!asks.isEmpty() && !bids.isEmpty() && bids.firstKey() >= asks.firstKey()) {
               ArrayDeque<Order> askQue = asks.firstEntry().getValue();
               ArrayDeque<Order> bidQue = bids.firstEntry().getValue();

               Order ask = askQue.peekFirst();
               Order bid = bidQue.peekFirst();


                int quantity = Math.min(ask.getRemainingQuantity(), bid.getRemainingQuantity());
                ask.fill(quantity);
                bid.fill(quantity);

                if (ask.getRemainingQuantity() == 0) {
                    askQue.pollFirst();
                }
                if (askQue.isEmpty()) {
                    asks.pollFirstEntry();
                }

                if (bid.getRemainingQuantity() == 0) {
                    bidQue.pollFirst();
                }
                if (bidQue.isEmpty()) {
                    bids.pollFirstEntry();
                }
                TradeInfo askTradeInfo = new TradeInfo(ask.getOrderId(), ask.getRemainingQuantity(), ask.getPrice());
                TradeInfo bidTradeInfo = new TradeInfo(bid.getOrderId(), bid.getRemainingQuantity(), bid.getPrice());
                Trade trade = new Trade(bidTradeInfo, askTradeInfo);
                trades.add(trade);
            }
        return trades;
    }

}
