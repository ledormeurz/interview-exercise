package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

import java.util.*;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements PriceListener {

    private ExecutionService executionService;

    private Map<String, List<OrderStrategy>> securityOrderStrategiesMap = new HashMap<>();

    public TradingStrategy(){
    }


    @Override
    public void priceUpdate(String security, double price) {
        List<OrderStrategy> orderStrategies = securityOrderStrategiesMap.get(security);
        if(orderStrategies == null){
            return;
        }
        Iterator<OrderStrategy> strategyIterable = orderStrategies.iterator();
        while ( strategyIterable.hasNext()){
            OrderStrategy orderStrategy = strategyIterable.next();
            Optional<Order> order = orderStrategy.priceUpdate(price);
            order.ifPresent(o -> {
                applyExecution(security, o);
                strategyIterable.remove();
            });
        }
    }

    private void applyExecution(String security, Order order){
        if(order.getWay() == Way.BUY){
            executionService.buy(security, order.getPrice(), order.getVolume());
        } else if( order.getWay() == Way.SELL){
            executionService.sell(security , order.getPrice(), order.getVolume());
        } else {
            System.out.println("Warring no order sent");
        }
    }

    public void addOrder(String security, OrderStrategy orderStrategy) {
        List<OrderStrategy> orderStrategies = securityOrderStrategiesMap.get(security);
        if(orderStrategies == null){
            orderStrategies = new ArrayList<>();
            securityOrderStrategiesMap.put(security, orderStrategies);
        }
        orderStrategies.add(orderStrategy);
    }

    public void setExecutionService(ExecutionService executionService) {
        this.executionService = executionService;
    }
}
