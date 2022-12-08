package org.example;

import org.example.interfaces.ChangeAmount;
import org.example.interfaces.Payment;

import java.util.List;

public class PaymentSyst {

    public PaymentSyst() {
    }


    @Override
    public String toString() {
        return "PaymentSyst{}";
    }

    public boolean payForProduct(Payment payment, ChangeAmount ca) {
        List<Product> products = payment.payForProducts();
        if (products == null) {
            return false;
        }
        for (var i : products) {
            ca.changeAmount(i.getName(), -i.getAmount());
        }
        payment.clearShoppingBasket();
        ca.clearBasket();
        return true;
    }
}
