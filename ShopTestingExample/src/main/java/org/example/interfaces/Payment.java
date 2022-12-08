package org.example.interfaces;

import org.example.Product;

import java.util.List;

public interface Payment {
    List<Product> payForProducts();

    void clearShoppingBasket();
}
