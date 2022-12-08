package org.example;

import org.example.products.Clothes;
import org.example.products.Foods;
import org.example.products.Furnitures;
import org.example.products.Souvenirs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopTest {
    private Clothes clothes;
    private Foods foods;
    private Souvenirs souvenirs;
    private Furnitures furnitures;
    private Product[] products;
    private Shop shop;


    @BeforeEach
    public void makeNewObject(){
        clothes = new Clothes("arg0", 1, 20, Product.Categories.CLOTHES, "XXL", "Red");
        foods = new Foods("arg1", 1, 10, Product.Categories.FOODS, "10.10.2022");
        souvenirs = new Souvenirs("arg2", 1, 12, Product.Categories.SOUVENIRS, 10);
        furnitures = new Furnitures("arg3", 1, 22, Product.Categories.FURNITURE, "wood", "red");
        products = new Product[]{clothes, foods, souvenirs, furnitures};
        shop = new Shop(products);
    }

    @Test
    void addProduct_False_ProductIsNull() {
        foods = null;
        Assertions.assertFalse(shop.addProduct(foods));
    }
    @Test
    void addProduct_False_ProductAlreadyContained() {
        Assertions.assertFalse(shop.addProduct(foods));
    }
    @Test
    void addProduct_True_ProductAdded() {
        shop.deleteProduct(foods);
        Assertions.assertTrue(shop.addProduct(foods));
    }

    @Test
    void deleteProduct_False_ProductIsNull() {
        foods = null;
        Assertions.assertFalse(shop.deleteProduct(foods));
    }
    @Test
    void deleteProduct_False_ProductNotContained() {
        shop.deleteProduct(foods);
        Assertions.assertFalse(shop.deleteProduct(foods));
    }
    @Test
    void deleteProduct_True_ProductDeleted() {
        Assertions.assertTrue(shop.deleteProduct(foods));
    }

    @Test
    void changeAmountOfProduct_False_ProductIsNull() {
        foods = null;
        Assertions.assertFalse(shop.changeAmountOfProduct(foods, 2));
    }
    @Test
    void changeAmountOfProduct_False_AmountOfProductLessThanZero() {
        Assertions.assertFalse(shop.changeAmountOfProduct(foods, -2));
    }
    @Test
    void changeAmountOfProduct_True_AmountOfProductChanged() {
        Assertions.assertTrue(shop.changeAmountOfProduct(foods, 2));
    }
    @Test
    void changeAmountOfProduct_False_ProductIsNotContained() {
        shop.deleteProduct(foods);
        Assertions.assertFalse(shop.changeAmountOfProduct(foods, 2));
    }

    @Test
    void changeAmount_False_WrongProductName() {
        Assertions.assertFalse(shop.changeAmount("Wrong name", 1));
    }
    @Test
    void changeAmount_False_ProductNameNull() {
        Assertions.assertFalse(shop.changeAmount(null, 1));
    }
    @Test
    void changeAmount_True_ProductAmountChanged() {
        Assertions.assertTrue(shop.changeAmount("arg1", 1));
    }

    @Test
    void changePrice_False_ProductPriceLessThanZero() {
        Assertions.assertFalse(shop.changePrice(foods, -1));
    }
    @Test
    void changePrice_False_ProductIsNull() {
        Assertions.assertFalse(shop.changePrice(null, 1));
    }
    @Test
    void changePrice_True_ProductPriceChanged() {
        Assertions.assertTrue(shop.changePrice(foods, 10));
    }
    @Test
    void changePrice_False_ProductNotContained() {
        shop.deleteProduct(foods);
        Assertions.assertFalse(shop.changePrice(foods, 10));
    }

    @Test
    void testShowCategories() {
        int result = shop.showCategories();
        int expected = 4;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testShowAssortment() {
        int result = shop.showAssortment(1);
        int expected = 1;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void buyProducts_False_ProductNameNull() {
        Assertions.assertFalse(shop.buyProducts(null, 1));
    }
    @Test
    void buyProducts_False_ProductAmountIsZeroOrLess() {
        Assertions.assertFalse(shop.buyProducts("arg1", 0));
    }
    @Test
    void buyProducts_True_EnoughAmountOfProduct() {
        Assertions.assertTrue(shop.buyProducts("arg1", 1));
    }
    @Test
    void buyProducts_False_NotEnoughAmountOfProduct() {
        Assertions.assertFalse(shop.buyProducts("arg1", 9));
    }
}