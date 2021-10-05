package com.acme.mytrader.strategy;

import java.util.Optional;

public interface OrderStrategy {
    Optional<Order> priceUpdate(double price);
}
