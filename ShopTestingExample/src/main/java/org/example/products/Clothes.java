package org.example.products;

import org.example.Product;

import java.util.Objects;

public class Clothes extends Product {
    private String size;
    private String color;


    public Clothes(String name, int amount, double price, Categories category, String size, String color) {
        super(name, amount, price, category);
        this.size = size;
        this.color = color;
    }

    public Clothes() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Clothes{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                "} " + super.toString();
    }

    @Override
    public void showProduct(int number) {
        System.out.println(number + ". " + getName() + " Количество: " + getAmount() +
                " Цена за штуку: " + getPrice() + " Размер: " + getSize() + " Цвет: " + getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Objects.equals(size, clothes.size) && Objects.equals(color, clothes.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color);
    }
}
