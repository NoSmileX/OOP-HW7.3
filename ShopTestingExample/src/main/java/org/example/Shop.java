package org.example;

import org.example.interfaces.ChangeAmount;
import org.example.interfaces.Customers;
import org.example.interfaces.ShopAdministration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Shop implements ShopAdministration, Customers, ChangeAmount {
    private List<Product> products;
    private List<Product> shoppingBasket = new ArrayList<>();

    public Shop(List<Product> products) {
        this.products = products;
    }

    public Shop(Product... products) {
        this.products = new ArrayList<>();
        Collections.addAll(this.products, products);
    }

    public Shop() {
    }


    public List<Product> getProducts() {
        return products;
    }


    @Override
    public List<Product> getShoppingBasket() {
        return shoppingBasket;
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }
        if (products.contains(product)) {
            System.out.println("Продукт уже существует");
            return false;
        } else {
            System.out.println("Продукт добавлен в магазин");
            products.add(product);
            return true;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        if (product == null) {
            return false;
        }
        if (products.contains(product)) {
            System.out.println("Продукт удален из магазина");
            products.remove(product);
            return true;
        } else {
            System.out.println("Такого продукта нет в магазине");
            return false;
        }
    }

    @Override
    public boolean changeAmountOfProduct(Product product, int amount) {
        if (product == null) {
            return false;
        }
        if (products.contains(product)) {
            int index = products.indexOf(product);
            int newAmount = product.getAmount() + amount;
            if (newAmount < 0) {
                System.out.println("Ошибка! Не верное количество.");
                return false;
            }
            product.setAmount(newAmount);
            products.set(index, product);
            System.out.println("Количество изменено.");
            return true;
        } else {
            System.out.println("Такого продукта нет в магазине.");
            return false;
        }
    }

    @Override
    public boolean changeAmount(String name, int amount) {
        if (name == null) {
            return false;
        }
        for (var i : products) {
            if (i.getName().equals(name)) {
                int newAmount = i.getAmount() + amount;
                i.setAmount(newAmount);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changePrice(Product product, double price) {
        if (product == null || price < 0) {
            System.out.println("Ошибка! Цена не может быть отрицательной или продукт не может отсутсвовать.");
            return false;
        }
        if (products.contains(product)) {
            int index = products.indexOf(product);
            product.setPrice(price);
            products.set(index, product);
            System.out.println("Цена изменена");
            return true;
        } else {
            System.out.println("Такого продукта нет в магазине");
            return false;
        }
    }

    @Override
    public int showCategories() {
        Product.Categories[] categories = Product.Categories.values();
        int count = 1;
        for (var i : categories) {
            System.out.println(count + ". " + i);
            count++;
        }
        return count-1;


    }

    @Override
    public int showAssortment(int numberOfCategories) {
        boolean flag = true;
        int count = 1;
        for (var i : products) {
            if (i.getCategory().ordinal() == numberOfCategories) {
                if (flag) {
                    flag = false;
                }
                i.showProduct(count);
                count++;
            }
        }
        return count-1;
    }

    @Override
    public boolean buyProducts(String name, int amount) {
        if (name == null || amount <= 0) {
            return false;
        }
        for (var i : products) {
            if (i.getName().equals(name)) {
                int sum = i.getAmount() - amount;
                for (var j : shoppingBasket) {
                    if (j.getName().equals(name)) {
                        sum -= j.getAmount();
                    }
                }
                if (sum >= 0) {
                    Product productToCart;
                    try {
                        productToCart = (Product) i.clone();
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    productToCart.setAmount(amount);
                    shoppingBasket.add(productToCart);
                    return true;
                } else {
                    System.out.println("Не достаточное количество товара в магазине.");
                    return false;
                }
            }
        }
        System.out.println("Такого товара нет в магазине.");
        return false;
    }

    public void showProduct() {
        if (products == null) {
            System.out.println();
            System.out.println("Товаров нет.");
            return;
        }
        Product.Categories categories = null;
        int count = 1;
        for (var i : products) {
            if (i.getCategory() != categories) {
                if (categories == null) {
                    System.out.println();
                    System.out.println("Товары:");
                    System.out.println("------------------------------------");
                } else {
                    System.out.println("------------------------------------");
                }
            }
            categories = i.getCategory();
            i.showProduct(count);
            count++;
        }
    }

    public void showShoppingCart() {
        if (shoppingBasket == null) {
            System.out.println("Корзина пустая");
            return;
        }
        Product.Categories categories = null;
        int count = 1;
        for (var i : shoppingBasket) {
            if (i.getCategory() != categories) {
                if (categories == null) {
                    System.out.println("Корзина:");
                    System.out.println("------------------------------------");
                } else {
                    System.out.println("------------------------------------");
                }
                categories = i.getCategory();
            }
            i.showProduct(count);
            count++;
        }
    }

    @Override
    public void clearBasket() {
        shoppingBasket.clear();
    }

    @Override
    public String toString() {
        return "Shop{" +
                "products=" + products +
                ", shoppingBasket=" + shoppingBasket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(products, shop.products) && Objects.equals(shoppingBasket, shop.shoppingBasket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, shoppingBasket);
    }
}
