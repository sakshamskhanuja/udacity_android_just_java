package com.basic.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // Stores the number of cups ordered.
    private int quantity = 2;

    // Price of 1 coffee.
    private static final int PRICE = 414;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is invoked when the "Order" Button is clicked.
     *
     * @param view is the clicked Button.
     */
    public void submitOrder(View view) {
        // Store the price for the order.
        int price = calculatePrice();

        // Displays the price.
        displayMessage(createOrderSummary(price));
    }

    /**
     * Creates an order summary.
     *
     * @param price is the total cost of the order.
     * @return order summary.
     */
    private String createOrderSummary(int price) {
        // Initializing Locale for India.
        Locale locale = new Locale("eng", "IN");

        return "Name: John Doe" +
                "\nQuantity: " + quantity +
                "\nTotal: " + NumberFormat.getCurrencyInstance(locale).format(price) +
                "\nThank you!";
    }

    /**
     * Calculates the price of the order.
     *
     * @return price of the order.
     */
    private int calculatePrice() {
        return quantity * PRICE;
    }

    /**
     * This method is invoked when the "+" Button is clicked. It increases the quantity by 1.
     */
    public void increment(View view) {
        quantity++;

        // Updates the quantity.
        displayQuantity(quantity);
    }

    /**
     * This method is invoked when the "-" Button is clicked. It decrases the quantity by 1.
     */
    public void decrement(View view) {
        quantity--;

        // Restricts the quantity to be negative.
        if (quantity < 0) {
            quantity = 0;
        }

        // Updates the quantity.
        displayQuantity(quantity);
    }

    /**
     * This method displays a message along with the total amount of order.
     */
    private void displayMessage(String priceMessage) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param numberOfCoffee is the number of coffee ordered.
     */
    private void displayQuantity(int numberOfCoffee) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfCoffee));
    }
}