package org.example;

import org.example.interfaces.Payment;
import org.example.interfaces.Customers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer implements Payment {
    private String name;
    private String password;
    private double money;
    private List<Product> shoppingBasket;
    private List<Product> boughtProducts = new ArrayList<>();

    public Customer(String name, String password, double money) {
        this.name = name;
        this.password = password;
        this.money = money;
    }

    public Customer() {
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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

    public void setShoppingBasket(List<Product> shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public List<Product> getShoppingBasket() {
        return shoppingBasket;
    }


    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public int showCategories(Customers customers) {
        int result = customers.showCategories();
        System.out.println("Введите номер категории. Для выхода введите 0. Для перехода к оплате введите buy");
        return result;
    }

    public int showAssortment(Customers customers, int numberCategories) {
        int result = customers.showAssortment(numberCategories);
        System.out.println("Введите номер продукта. Для выхода введите 0. Для перехода к оплате введите buy");
        return result;
    }

    public boolean buyProduct(Customers customers, String name, int amount) {
        if (customers.buyProducts(name, amount)) {
            shoppingBasket = customers.getShoppingBasket();
        }
        return false;
    }

    public void showBoughtProducts() {
        if (boughtProducts == null) {
            System.out.println("Корзина: пустая");
            return;
        }

        Product.Categories categories = null;
        int count = 1;
        for (var i : boughtProducts) {
            if (i.getCategory() != categories) {
                if (categories == null) {
                    System.out.println("Купленные товары:");
                    System.out.println("..............................");
                } else {
                    System.out.println("..............................");
                }
                categories = i.getCategory();
            }
            i.showProduct(count);
            count++;
        }
        System.out.println("..............................");
        System.out.println("Сумма к оплате: " + getTotal(boughtProducts) + " UAH");
        System.out.println("Осталось денег: " + money + " UAH");

    }

    public void showShoppingCart() {
        if (shoppingBasket == null) {
            System.out.println("Корзина: пустая");
            return;
        }

        Product.Categories categories = null;
        int count = 1;
        for (var i : shoppingBasket) {
            if (i.getCategory() != categories) {
                if (categories == null) {
                    System.out.println("Товары в корзине:");
                    System.out.println("..............................");
                } else {
                    System.out.println("..............................");
                }
                categories = i.getCategory();
            }
            i.showProduct(count);
            count++;
        }
        System.out.println("..............................");
        System.out.println("Сумма к оплате: " + getTotal(shoppingBasket) + " UAH");
        System.out.println("В наличии : " + money + " UAH");
    }

    @Override
    public List<Product> payForProducts() {
        List<Product> products = shoppingBasket;
        if (products == null || products.size() == 0) {
            return null;
        }
        double total = getTotal(products);
        if (total <= money) {
            money = new BigDecimal("" + money).
                    subtract(new BigDecimal("" + total)).doubleValue();
            boughtProducts.addAll((ArrayList<Product>) ((ArrayList<Product>) products).clone());


            return products;
        }
        System.out.println("Не достаточно денег");
        return null;
    }

    private double getTotal(List<Product> products) {
        if (products == null || products.size() == 0) {
            return .0;
        }
        BigDecimal sum = new BigDecimal("" + products.get(0).getPrice());
        sum = sum.multiply(new BigDecimal("" + products.get(0).getAmount()));
        for (int i = 1; i < products.size(); i++) {
            sum = sum.add(new BigDecimal("" + products.get(i).getPrice()).multiply(
                    new BigDecimal("" + products.get(i).getAmount())));
        }
        return sum.doubleValue();
    }

    @Override
    public void clearShoppingBasket() {
        shoppingBasket.clear();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", shoppingBasket=" + shoppingBasket +
                ", boughtProducts=" + boughtProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Double.compare(customer.money, money) == 0 && Objects.equals(name, customer.name) && Objects.equals(password, customer.password) && Objects.equals(shoppingBasket, customer.shoppingBasket) && Objects.equals(boughtProducts, customer.boughtProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, money, shoppingBasket, boughtProducts);
    }
}
