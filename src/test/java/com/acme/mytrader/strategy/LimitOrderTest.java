package com.acme.mytrader.strategy;

import org.junit.Test;

import static org.junit.Assert.*;

public class LimitOrderTest {
    @Test
    public void testBuyOrderLimitNotGenerated(){
        // given buy order limit at 100 and volume 1000;
        double orderPrice = 100;
        Way orderWay = Way.BUY;
        int orderVolume = 1000;
        OrderStrategy orderStrategy = new LimitOrder(orderWay, orderPrice, orderVolume);
        // when receive price at 101
        Order order = orderStrategy.priceUpdate(101);
        // then no order generated
        assertNull(order);
    }

    @Test
    public void testBuyOrderLimitGeneratedWhenSourcePriceEquals(){
        // given buy order limit at 100 and volume 1000;
        double orderPrice = 100;
        Way orderWay = Way.BUY;
        int orderVolume = 1000;
        OrderStrategy orderStrategy = new LimitOrder(orderWay, orderPrice, orderVolume);
        // when receive price at 100
        Order order = orderStrategy.priceUpdate(100);
        // then no order generated
        assertNotNull(order);
        assertEquals(orderPrice, order.getPrice(),0);
        assertEquals(orderWay, order.getWay());
        assertEquals(orderVolume, order.getVolume());
    }

    @Test
    public void testBuyOrderLimitGeneratedWhenSourcePriceLower(){
        // given buy order limit at 100 and volume 1000;
        double orderPrice = 100;
        Way orderWay = Way.BUY;
        int orderVolume = 1000;
        OrderStrategy orderStrategy = new LimitOrder(orderWay, orderPrice, orderVolume);
        // when receive price at 99
        Order order = orderStrategy.priceUpdate(99);
        // then no order generated
        assertNotNull(order);
        assertEquals(orderPrice, order.getPrice(),0);
        assertEquals(orderWay, order.getWay());
        assertEquals(orderVolume, order.getVolume());
    }

    @Test
    public void testSellOrderLimitGeneratedWhenSourcePriceLower(){
        // given buy order limit at 100 and volume 1000;
        double orderPrice = 100;
        Way orderWay = Way.SELL;
        int orderVolume = 1000;
        OrderStrategy orderStrategy = new LimitOrder(orderWay, orderPrice, orderVolume);
        // when receive price at 101
        Order order = orderStrategy.priceUpdate(110);
        // then no order generated
        assertNotNull(order);
        assertEquals(orderPrice, order.getPrice(),0);
        assertEquals(orderWay, order.getWay());
        assertEquals(orderVolume, order.getVolume());
    }




}