package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TradingStrategyTest {

    @Test
    public void testNoOrderGeneratedWhenSourcePriceAboveBuyPrice() {
        // given IBM buy order limit at 100 and volume 1000;
        double orderPrice = 100;
        Way orderWay = Way.BUY;
        int orderVolume = 1000;
        String security = "IBM";
        OrderStrategy orderStrategy = new LimitOrder(orderWay, orderPrice, orderVolume);
        TradingStrategy tradingStrategy = new TradingStrategy();
        tradingStrategy.addOrder(security, orderStrategy);
        ExecutionService executionService = mock(ExecutionService.class);
        tradingStrategy.setExecutionService(executionService);
        // when receive 3 price equal or below 100
        tradingStrategy.priceUpdate(security, orderPrice + 1);
        tradingStrategy.priceUpdate(security, orderPrice + 2);
        tradingStrategy.priceUpdate(security, orderPrice + 3);
        // then NO order is generated
        verify(executionService, times(0)).buy(security, orderPrice, orderVolume);
    }

    @Test
    public void testBuyOrderLimitGeneratedWhenSourcePriceEquals() {
        // given IBM buy order limit at 100 and volume 1000;
        double orderPrice = 100;
        Way orderWay = Way.BUY;
        int orderVolume = 1000;
        String security = "IBM";
        OrderStrategy orderStrategy = new LimitOrder(orderWay, orderPrice, orderVolume);
        TradingStrategy tradingStrategy = new TradingStrategy();
        tradingStrategy.addOrder(security, orderStrategy);
        ExecutionService executionService = mock(ExecutionService.class);
        tradingStrategy.setExecutionService(executionService);
        // when receive 3 price equal or below 100
        tradingStrategy.priceUpdate(security, orderPrice);
        tradingStrategy.priceUpdate(security, orderPrice - 1);
        tradingStrategy.priceUpdate(security, orderPrice - 2);
        // then only one order is generated

        verify(executionService, times(1)).buy(security, orderPrice, orderVolume);

    }


}
