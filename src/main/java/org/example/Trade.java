package org.example;

public class Trade {
    private final TradeInfo bidsTrade;
    private final TradeInfo asksTrade;

    public Trade(TradeInfo bidsTrade, TradeInfo asksTrade) {
        this.bidsTrade = bidsTrade;
        this.asksTrade = asksTrade;
    }

    public TradeInfo getAsksTrade() {
        return asksTrade;
    }

    public TradeInfo getBidsTrade() {
        return bidsTrade;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "bidsTrade=" + bidsTrade +
                ", asksTrade=" + asksTrade +
                '}';
    }
}
