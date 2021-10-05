package com.acme.mytrader.strategy;

public class LimitOrder implements OrderStrategy{
    private final double limitPrice;
    private final Way way;
    private final int volume;
    public LimitOrder(Way way, double limitPrice, int volume){
        this.way = way;
        this.limitPrice = limitPrice;
        this.volume = volume;
    }
    @Override
    public Order priceUpdate(double price) {
        if(way == Way.BUY){
            if(price<=limitPrice){
                return new Order(limitPrice, way, volume);
            }
        } else if(way == Way.SELL) {
            if(price>=limitPrice){
                return new Order(limitPrice, way, volume);
            }
        }
        return null;
    }
}
