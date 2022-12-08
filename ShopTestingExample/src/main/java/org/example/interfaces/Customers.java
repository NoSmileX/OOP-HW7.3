package org.example.interfaces;

import org.example.Product;

import java.util.List;

public interface Customers {
    int showCategories();

    int showAssortment(int numberOfCategories);

    boolean buyProducts(String name, int amount);

    List<Product> getShoppingBasket();
}
