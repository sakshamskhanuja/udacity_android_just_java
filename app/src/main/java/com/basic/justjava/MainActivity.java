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
     * @param view It is the clicked Button.
     */
    public void submitOrder(View view) {
        displayMessage(quantity * PRICE);
    }

    /**
     * This method is invoked when the "+" Button is clicked. It increases the quantity by 1.
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
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
        display(quantity);
    }

    /**
     * This method displays a message along with the total amount of order.
     */
    private void displayMessage(int price) {
        TextView priceTextView = findViewById(R.id.price_text_view);

        // Initialing Locale for India.
        Locale locale = new Locale("eng", "IN");

        // Initialize the message.
        String message = "Total: ".concat(NumberFormat.getCurrencyInstance(locale).format(price))
                .concat("\nThank you!");
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }
}