package com.basic.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    // Price of Whipped cream topping.
    private static final int WHIPPED_CREAM = 82;

    // Price of Chocolate topping.
    private static final int CHOCOLATE = 165;

    // Shows a short message to the user.
    private Toast toast;

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
        // Get the entered name in "Name" EditText.
        EditText nameEditText = findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();

        // Get the checked state of "Whipped cream" CheckBox.
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCreamChecked = whippedCreamCheckBox.isChecked();

        // Get the checked state of "Chocolate" CheckBox.
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean chocolateChecked = chocolateCheckBox.isChecked();

        // Store the price for the order.
        int price = calculatePrice(whippedCreamChecked, chocolateChecked);

        // Stores order summary.
        String orderSummary = createOrderSummary(name, price, whippedCreamChecked,
                chocolateChecked);

        // Initializing Intent to send orderSummary to any email app.
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        // Set Data on this Intent -> Makes sure only email apps will handle this Intent.
        intent.setData(Uri.parse("mailto:"));

        // Adding subject.
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject, name));

        // Adding body.
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        // Send Intent.
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Informs user that no email app is preset on their device.
            showToast(R.string.toast_no_email_app);
        }
    }

    /**
     * Creates an order summary.
     *
     * @param name            is the entered username in EditText.
     * @param price           is the total cost of the order.
     * @param addWhippedCream is true when the user has checked the "Whipped cream" Checkbox,
     *                        otherwise, false.
     * @param addChocolate    is true when the user has checked the "Chocolate" Checkbox, otherwise,
     *                        false.
     * @return order summary.
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream,
                                      boolean addChocolate) {
        // Initializing Locale for India.
        Locale locale = new Locale("eng", "IN");

        return getString(R.string.summary_name, name) +
                "\n" + getString(R.string.summary_whipped_cream, addWhippedCream) +
                "\n" + getString(R.string.summary_chocolate, addChocolate) +
                "\n" + getString(R.string.summary_quantity, quantity) +
                "\n" + getString(R.string.summary_total,
                NumberFormat.getCurrencyInstance(locale).format(price)) +
                "\n" + getString(R.string.summary_thank_you);
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is true when the user has checked the "Whipped cream" Checkbox,
     *                        otherwise, false.
     * @param addChocolate    is true when the user has checked the "Chocolate" Checkbox, otherwise,
     *                        false.
     * @return price of the order.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Checks if user has checked both the toppings.
        if (addWhippedCream && addChocolate) {
            return quantity * (PRICE + CHOCOLATE + WHIPPED_CREAM);
        } else if (addWhippedCream) {
            // Only whipped cream is checked.
            return quantity * (PRICE + WHIPPED_CREAM);
        } else if (addChocolate) {
            // Only chocolate is checked.
            return quantity * (PRICE + CHOCOLATE);
        } else {
            // No toppings is checked.
            return quantity * PRICE;
        }
    }

    /**
     * This method is invoked when the "+" Button is clicked. It increases the quantity by 1.
     */
    public void increment(View view) {
        // Restricts the quantity to be 100.
        if (quantity == 100) {
            // Informs user that he/she cannot order less more than 100 coffee.
            showToast(R.string.toast_hundred);
            return;
        }

        // Increasing the quantity by 1.
        quantity++;

        // Updates the quantity.
        displayQuantity(quantity);
    }

    /**
     * This method is invoked when the "-" Button is clicked. It decreases the quantity by 1.
     */
    public void decrement(View view) {
        // Restricts the quantity to be 1.
        if (quantity == 1) {
            // Informs user that he/she cannot order less than 1 coffee.
            showToast(R.string.toast_one);
            return;
        }

        // Decreasing the quantity by 1.
        quantity--;

        // Updates the quantity.
        displayQuantity(quantity);
    }

    /**
     * Shows a Toast message.
     *
     * @param resourceID is the String resource ID.
     */
    private void showToast(int resourceID) {
        // Disappears the current showing Toast.
        if (toast != null) {
            toast.cancel();
        }

        // Shows the new Toast.
        toast = Toast.makeText(this, resourceID, Toast.LENGTH_SHORT);
        toast.show();
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