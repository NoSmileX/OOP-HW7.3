package org.example;

import org.example.products.Clothes;
import org.example.products.Foods;
import org.example.products.Furnitures;
import org.example.products.Souvenirs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Clothes shirt = new Clothes("Shirt", 10, 299.9,
                Product.Categories.CLOTHES, "XL", "brown");
        Clothes bikini = new Clothes("Bikini", 5, 890.0,
                Product.Categories.CLOTHES, "XS", "red");

        Foods eggs = new Foods("Eggs", 100, 6.2,
                Product.Categories.FOODS, "12.03.2023");
        Foods milk = new Foods("Milk", 10, 32,
                Product.Categories.FOODS, "01.01.2023");

        Furnitures sofa = new Furnitures("Sofa", 3, 2400.0,
                Product.Categories.FURNITURE, "wood", "brown");
        Furnitures vase = new Furnitures("Vase", 5, 199.9,
                Product.Categories.FURNITURE, "glass", "red");

        Souvenirs bracelet = new Souvenirs("Bracelet", 100, 25.5,
                Product.Categories.SOUVENIRS, 78);
        Souvenirs cup = new Souvenirs("Cup", 60, 120.9,
                Product.Categories.SOUVENIRS, 234);

        Product[] products = {shirt, bikini, eggs, milk, sofa, vase, bracelet};
        Customer customer = new Customer("testuser", "password", 2000);
        Administrator administrator = new Administrator("admin", "qwerty");
        Shop store = new Shop(products);
        PaymentSyst paymentSystem = new PaymentSyst();
        UI userInterface = new UI(store, customer, paymentSystem);

        System.out.println("Введите логин и пароль:");
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        if (login.equals(administrator.getName()) && password.equals(administrator.getPassword())) {
            do {
                System.out.println("Управление магазином. Сделайте свой выбор.");
                System.out.println("1. Добавить продукт. \n2. Удалить продукт. \n3. Изменить " +
                        "количество продукта. \n4. Изменить цену продукта. \n5. Выйти.");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Добавляем в продажу Чашку");
                        administrator.addProduct(store, cup);
                    }
                    case 2 -> {
                        System.out.println("Удаляем из продажи Браслет");
                        administrator.deleteProduct(store, bracelet);
                    }
                    case 3 -> {
                        System.out.println("Меняем количество Яиц в продаже. Введите новое количество:");
                        administrator.changeAmount(store, eggs, scanner.nextInt());
                    }
                    case 4 -> {
                        System.out.println("Меняем цену Яиц. Введите новую цену:");
                        administrator.changePrice(store, eggs, scanner.nextDouble());
                    }
                    case 5 -> {
                        System.out.println("Всего хорошего! Приходите еще!");
                        return;
                    }
                }
            }while (true);

        } else if (login.equals(customer.getName()) && password.equals(customer.getPassword())) {
            userInterface.showInterface();
        } else
            System.out.println("Не правильный логин или пароль.");
    }
}