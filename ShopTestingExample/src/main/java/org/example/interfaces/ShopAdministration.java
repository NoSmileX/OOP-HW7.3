package org.example.interfaces;

import org.example.Product;

public interface ShopAdministration {
    boolean addProduct(Product product);

    boolean deleteProduct(Product product);

    boolean changeAmountOfProduct(Product product, int amount);

    boolean changePrice(Product product, double price);
}
