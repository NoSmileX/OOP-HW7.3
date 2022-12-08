package org.example.products;

import org.example.Product;

import java.util.Objects;

public class Furnitures extends Product {
    private String material;
    private String color;

    public Furnitures(String name, int amount, double price, Categories category, String material, String color) {
        super(name, amount, price, category);
        this.material = material;
        this.color = color;
    }

    public Furnitures() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Furnitures{" +
                "material='" + material + '\'' +
                ", color='" + color + '\'' +
                "} " + super.toString();
    }

    @Override
    public void showProduct(int number) {
        System.out.println(number + ". " + getName() + " Количество: " + getAmount() +
                " Цена за штуку: " + getPrice() + " Цвет: " + getColor() + " Материал: " + getMaterial());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furnitures that = (Furnitures) o;
        return Objects.equals(material, that.material) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, color);
    }
}
