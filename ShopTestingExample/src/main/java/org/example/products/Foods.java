package org.example.products;

import org.example.Product;

import java.util.Objects;

public class Foods extends Product {
    private String expirationDate;

    public Foods(String name, int amount, double price, Categories category, String expirationDate) {
        super(name, amount, price, category);
        this.expirationDate = expirationDate;
    }

    public Foods() {
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Foods{" +
                "expirationDate='" + expirationDate + '\'' +
                "} " + super.toString();
    }

    @Override
    public void showProduct(int number) {
        System.out.println(number + ". " + getName() + " Количество: " + getAmount() +
                " Цена за штуку: " + getPrice() + " Использовать до: " + getExpirationDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foods foods = (Foods) o;
        return Objects.equals(expirationDate, foods.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expirationDate);
    }
}
