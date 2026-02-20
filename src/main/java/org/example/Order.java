package org.example;

public class Order {
    private int orderId;
    private Side side;
    private OrderType orderType;
    private double price;
    private int initialQuantity;
    private int remainingQuantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
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
}
