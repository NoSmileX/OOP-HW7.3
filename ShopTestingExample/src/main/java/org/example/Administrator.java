package org.example;

import org.example.interfaces.ShopAdministration;

import java.util.Objects;

public class Administrator {
    private String name;
    private String password;

    public Administrator(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Administrator() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean addProduct(ShopAdministration editor, Product product) {
        return editor.addProduct(product);
    }

    public boolean deleteProduct(ShopAdministration editor, Product product) {
        return editor.deleteProduct(product);
    }

    public boolean changeAmount(ShopAdministration editor, Product product, int amount) {
        return editor.changeAmountOfProduct(product, amount);
    }

    public boolean changePrice(ShopAdministration editor, Product product, double price) {
        return editor.changePrice(product, price);
    }
    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(name, that.name) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
