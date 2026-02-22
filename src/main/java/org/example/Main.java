package org.example;


public class Main {
    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();
        orderBook.addNewOrder(
                new Order(
                        1,
                        Side.BUY,
                        OrderType.FillAndKill,
                        25.0,
                        10
                )
        );
        orderBook.addNewOrder(
                new Order(
                        2,
                        Side.SELL,
                        OrderType.FillAndKill,
                        25.0,
                        10
                )
        );
    }
}