package org.example.core;

import org.example.constants.Side;
import org.example.models.Order;
import org.example.models.Trade;
import org.example.models.TradeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class OrderBook {
    private final static Logger logger = LoggerFactory.getLogger(OrderBook.class);

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
                logger.info("matchingEngine(): Ask order {} filled and removed from que", ask);
            }
            if (askQue.isEmpty()) {
                asks.pollFirstEntry();
            }

            if (bid.getRemainingQuantity() == 0) {
                bidQue.pollFirst();
                logger.info("matchingEngine(): Bid order {} filled and removed from que", bid);
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

    public void addNewOrder(Order order) {
        var orderId = order.getOrderId();

        if (orders.containsKey(orderId)) {
            logger.warn("addNewOrder(): Adding new order failed: Order Id {} already exists", order.getOrderId());
            return;
        }
        logger.info("addNewOrder(): Adding new order: {}", order);

        if (order.getSide() == Side.BUY) {
            bids.computeIfAbsent(order.getPrice(), price -> new ArrayDeque<>()).add(order);
        } else {
            asks.computeIfAbsent(order.getPrice(), price -> new ArrayDeque<>()).add(order);
        }
        orders.put(orderId, order);

        logger.info("addNewOrder(): New order added, orders: {}", orders);
        logger.info("addNewOrder(): Asks: {}", asks);
        logger.info("addNewOrder(): Bids: {}", bids);

        List<Trade> trades = matchingEngine();
        if (!trades.isEmpty()) logger.info("addNewOrder(): Filled transaction: {}", trades);
    }
}
