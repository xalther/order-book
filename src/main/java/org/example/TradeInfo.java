package org.example;

public class TradeInfo {
    private final int orderId;
    private final int quantity;
    private final double price;


    public TradeInfo(int orderId, int quantity, double price) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "TradeInfo{" +
                "orderId=" + orderId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
