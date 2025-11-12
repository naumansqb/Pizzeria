package com.pluralsight.pizzeria.model;

import com.pluralsight.pizzeria.model.item.Item;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Order {
    private String receiptNumber;
    private LocalDateTime orderPlacedTime;
    private List <Item> items;

    public Order(LocalDateTime orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
        this.receiptNumber = orderPlacedTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        this.items = new ArrayList<>();
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public LocalDateTime getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }

    public double calculateTotal(){
        return items.stream().mapToDouble(Item::calculatePrice).sum();
    }

    public String orderSummary() {
        return "Order{" +
                "receiptNumber='" + receiptNumber + '\'' +
                ", orderPlacedTime=" + orderPlacedTime +
                ", items=" + items +
                '}';
    }
}
