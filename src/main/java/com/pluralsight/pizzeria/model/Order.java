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

    public Order() {
        this.items = new ArrayList<>();
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public LocalDateTime getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
        this.receiptNumber = orderPlacedTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss"));
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Item::calculatePrice).sum(); }
}
