package org.example.products;

import org.example.Product;

import java.util.Objects;

public class Souvenirs extends Product {
    private int weight;


    public Souvenirs(String name, int amount, double price, Categories category, int weight) {
        super(name, amount, price, category);
        this.weight = weight;
    }

    public Souvenirs() {
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Souvenirs{" +
                "weight=" + weight +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenirs souvenirs = (Souvenirs) o;
        return weight == souvenirs.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public void showProduct(int number) {
        System.out.println(number + ". " + getName() + " Количество: " + getAmount() +
                " Цена за штуку: " + getPrice() + " Вес: " + getWeight());
    }
}
