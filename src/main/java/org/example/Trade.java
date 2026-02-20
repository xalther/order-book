package org.example;

public class Trade {
    private TradeInfo bidsTrade;
    private TradeInfo asksTrade;

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
}
