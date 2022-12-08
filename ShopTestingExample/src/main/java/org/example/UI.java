package org.example;

import java.util.Objects;
import java.util.Scanner;

public class UI {
    private Shop shop;
    private Customer customer;
    private PaymentSyst paymentSystem;

    public UI (Shop shop, Customer customer, PaymentSyst paymentSystem) {
        this.shop = shop;
        this.customer = customer;
        this.paymentSystem = paymentSystem;
    }

    public UI () {
    }

    public Shop getStore() {
        return shop;
    }

    public void setStore(Shop shop) {
        this.shop = shop;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentSyst getPaymentSystem() {
        return paymentSystem;
    }

    public void setPaymentSystem(PaymentSyst paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public void showInterface() {
        int category = 0;
        int index = 0;
        int amount = 0;
        try (Scanner sc = new Scanner(System.in)) {
            for (; ; ) {
                category = quireInt(sc, customer.showCategories(shop)) - 1;
                switch (category) {
                    case -1 -> {
                        customer.showBoughtProducts();
                        return;
                    }
                    case -2 -> {
                        if (dialogPay(sc)) {
                            continue;
                        }else{
                            return;
                        }
                    }
                }
                do {
                    index = quireInt(sc, customer.showAssortment(shop, category)) - 1;
                    if (index == -1) {
                        break;
                    } else if (index == -2) {
                        if (dialogPay(sc)) {
                            continue;
                        }else{
                            return;
                        }
                    }
                    System.out.println("Какое количество добавить в корзину?");
                    amount = quireInt(sc, (1 << -1) - 1);
                    buyProduct(index, category, amount);
                } while (true);
            }
        }
    }
    private int quireInt(Scanner sc, int limit) {
        int input = -1;
        while (input < 0) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine();
                if (input < 0 || input > limit) {
                    System.out.println("Не верное значение," +
                            " введите число от 0 до " + limit);
                    input = -1;
                } else {
                    return input;
                }
            } else {
                if (sc.nextLine().equals("buy")) {
                    return -1;
                }
                System.out.println("Ошибка. Попробуйте еще раз.");
            }
        }
        return 0;
    }

    private boolean dialogPay(Scanner sc) {
        customer.showShoppingCart();
        System.out.println("1. Оплатить заказ \n2. Отменить заказ \n3. Вернуться \n4. Выйти");

        switch (sc.nextInt()) {
            case 1 -> {
                paymentSystem.payForProduct(customer, shop);
                customer.showBoughtProducts();
            }
            case 2 -> shop.clearBasket();
            case 4 -> {customer.showBoughtProducts();
                return false;
            }
        }
        return true;
    }

    private void buyProduct(int index, int category, int amount) {
        int count = -1;
        for (var i : shop.getProducts()) {
            if (i.getCategory().ordinal() == category) {
                count++;
            }
            if (count == index) {
                customer.buyProduct(shop, i.getName(), amount);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "UI{" +
                "shop=" + shop +
                ", customer=" + customer +
                ", paymentSystem=" + paymentSystem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UI ui = (UI) o;
        return Objects.equals(shop, ui.shop) && Objects.equals(customer, ui.customer) && Objects.equals(paymentSystem, ui.paymentSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shop, customer, paymentSystem);
    }
}
