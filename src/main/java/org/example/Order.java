package org.example;

public class Order {
    private final int orderId;
    private final Side side;
    private final OrderType orderType;
    private final double price;
    private final int initialQuantity;
    private int remainingQuantity;

    public int getOrderId() {
        return orderId;
    }

    public Side getSide() {
        return side;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public Order(int orderId, Side side, OrderType orderType, double price, int quantity) {
        this.orderId = orderId;
        this.side = side;
        this.orderType = orderType;
        this.price = price;
        this.initialQuantity = quantity;
        this.remainingQuantity = quantity;
    }

    public void fill(int quantity) {
        if (remainingQuantity < quantity) {
            throw new IllegalArgumentException("Order can't be filled for more than its remaining quantity. " + "Order id: " + getOrderId());
        }
        this.remainingQuantity -= quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", side=" + side +
                ", orderType=" + orderType +
                ", price=" + price +
                ", initialQuantity=" + initialQuantity +
                ", remainingQuantity=" + remainingQuantity +
                '}';
    }
}
