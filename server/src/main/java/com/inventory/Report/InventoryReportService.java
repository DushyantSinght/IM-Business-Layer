package com.inventory.Report;
import com.database.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class InventoryReportService {
    public void checkAndSendLowStockAlert(){
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM inventory");

            List<String> lowStockLines = new ArrayList<>();

            while (rs.next()) {
                int id           = rs.getInt("id");
                String name      = rs.getString("productname");
                int quantity     = rs.getInt("productquantity");
                double price     = rs.getDouble("price");
                int minQty       = rs.getInt("minquantity");

                System.out.println(id + " | " + name + " | " + quantity + " | " + price + " | " + minQty);

                // collect products that are below minimum quantity
                if (quantity <= minQty) {
                    lowStockLines.add(
                            String.format("  - %s (ID: %d) | Stock: %d | Min required: %d | Price: %.2f",
                                    name, id, quantity, minQty, price)
                    );
                }
            }

            // only send email if there are low stock items
            if (!lowStockLines.isEmpty()) {
                sendLowStockAlert(lowStockLines);
            } else {
                System.out.println("All products are sufficiently stocked.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendLowStockAlert(List<String> lowStockLines) {
        EmailService emailService = new EmailService();

        String subject = "LOW STOCK ALERT — Action Required";

        StringBuilder body = new StringBuilder();
        body.append("Hello,\n\n");
        body.append("The following products have dropped below their minimum quantity threshold:\n\n");
        for (String line : lowStockLines) {
            body.append(line).append("\n");
        }
        body.append("\nPlease restock these items as soon as possible.\n\n");
        body.append("-- Inventory System");

        emailService.sendEmail(
                "bhargavkallepally9@gmail.com",   // ← Change this email to get message to your email
                subject,
                body.toString()
        );

        System.out.println("Low stock alert sent for " + lowStockLines.size() + " product(s).");
    }
}