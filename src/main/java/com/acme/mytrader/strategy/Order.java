package com.acme.mytrader.strategy;

public class Order {
    private final double price;
    private final Way way;
    private final int volume;

    public Order(double price, Way way, int volume) {
        this.price = price;
        this.way = way;
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public Way getWay() {
        return way;
    }

    public int getVolume() {
        return volume;
    }
}
