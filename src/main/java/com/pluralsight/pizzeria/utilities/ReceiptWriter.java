package com.pluralsight.pizzeria.utilities;


import com.pluralsight.pizzeria.model.Order;
import com.pluralsight.pizzeria.model.item.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptWriter {
    private static final String RECEIPTS_DIRECTORY = "Receipts";
    private static final String FILE_EXTENSION = ".txt";

    public void displayReceipt(Order currentOrder){
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            return;
        }
        String receiptContent = formatReceipt(currentOrder);
        System.out.println(receiptContent);
    }

    private String formatReceipt(Order currentOrder) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n");
        receipt.append("=".repeat(80)).append("\n");
        receipt.append("                    PIZZA-licious Receipt\n");
        receipt.append("=".repeat(80)).append("\n");

        String receiptNumber = currentOrder.getReceiptNumber();
        if (receiptNumber != null) {
            receipt.append(String.format("Receipt #: %s\n", receiptNumber));
        }
        
        LocalDateTime orderTime = currentOrder.getOrderPlacedTime();
        if (orderTime != null) {
            receipt.append(String.format("Date: %s\n", 
                orderTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a"))));
        }
        receipt.append("-".repeat(80)).append("\n");

        List<Item> items = new ArrayList<>(currentOrder.getItems());
        Collections.reverse(items);
        int itemNumber = 0;
        for (Item item : items) {
            receipt.append(String.format("%d. %s\n", ++itemNumber, item.getDescription()));
            receipt.append(String.format("   Price: $%.2f\n", item.calculatePrice()));
        }

        receipt.append("\n");
        receipt.append("=".repeat(80)).append("\n");
        receipt.append(String.format("%-70s $%.2f\n", "Order Total:", currentOrder.calculateTotal()));
        receipt.append("=".repeat(80)).append("\n");
        receipt.append("\n");

        return receipt.toString();
    }

    public void saveReceipt(Order currentOrder) {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            return;
        }

        try {
            File receiptsDirectory = new File(RECEIPTS_DIRECTORY);
            if (!receiptsDirectory.exists()) {
                receiptsDirectory.mkdir();
            }

            String fileName = currentOrder.getReceiptNumber() + FILE_EXTENSION;
            File file = new File(receiptsDirectory, fileName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(formatReceipt(currentOrder));
            System.out.println("Receipt saved successfully: ");
            writer.close();
        } catch (Exception e) {
            System.err.println("Error saving receipt:");
        }
    }
}






