package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProducts;
    private final Map<UUID, Article> storageArticles;

    public StorageService() {
        this.storageProducts = fillStorageProducts();
        this.storageArticles = fillStorageArticles();

    }

    public Collection<Product> getStorageProducts() {
        return Collections.unmodifiableCollection(storageProducts.values());
    }

    public Collection<Article> getStorageArticles() {
        return Collections.unmodifiableCollection(storageArticles.values());
    }

    private Map<UUID, Product> fillStorageProducts() {
        SimpleProduct pr1 = new SimpleProduct(UUID.randomUUID(), "Сыр", 209);
        FixPriceProduct pr2 = new FixPriceProduct(UUID.randomUUID(), "Колбаса");
        FixPriceProduct pr3 = new FixPriceProduct(UUID.randomUUID(), "Вино");
        DiscountedProduct pr4 = new DiscountedProduct(UUID.randomUUID(), "Пуддинг", 140);
        DiscountedProduct pr5 = new DiscountedProduct(UUID.randomUUID(), "Хлеб", 78);
        SimpleProduct pr6 = new SimpleProduct(UUID.randomUUID(), "ТОРТ", 700);
        Map<UUID, Product> basket = new HashMap<>();
        basket.put(pr1.getId(), pr1);
        basket.put(pr2.getId(), pr2);
        basket.put(pr3.getId(), pr3);
        basket.put(pr4.getId(), pr4);
        basket.put(pr5.getId(), pr5);
        basket.put(pr6.getId(), pr6);
        return basket;
    }

    public void add(Product pr) {
        storageProducts.put(pr.getId(), pr);
        System.out.println("Добавлен продукт: " + pr.getName());
    }

    private Map<UUID, Article> fillStorageArticles() {
        Article art1 = new Article(UUID.randomUUID(), "Сыр: Искусство вкуса и традиций", "Сыр — это не просто молочный продукт, это целая культура, насчитывающая тысячелетия истории. Наш сыр создается по традиционным рецептам из отборного молока, с соблюдением всех технологических процессов. Каждая головка сыра вызревает не менее 60 дней, что придает ему неповторимый насыщенный вкус и аромат.");
        Article art2 = new Article(UUID.randomUUID(), "Колбаса: Наслаждение классикой", "Наша колбаса — это возвращение к истокам качества. Изготовленная из отборной говядины и свинины с добавлением натуральных специй, она сохраняет все лучшие традиции мясного производства. Нежная текстура, насыщенный мясной вкус и аппетитный аромат делают эту колбасу идеальным выбором для завтраков, бутербродов или как компонент для салатов.");
        Article art3 = new Article(UUID.randomUUID(), "Вино: Элегантность в каждом глотке", "Это вино — настоящая поэзия в бокале. Созданное из винограда, собранного на солнечных склонах, оно сочетает в себе богатый букет ароматов и сбалансированный вкус. Каждая бутылка — результат кропотливого труда виноделов, которые сохраняют уникальный характер терруара.");
        Article art4 = new Article(UUID.randomUUID(), "Пудинг: Нежность в каждой ложке", "Наш пудинг — это облако нежности, которое тает во рту. Приготовленный по особой рецептуре с использованием натуральных ингредиентов, он обладает нежной кремовой текстурой и приятным ванильным ароматом. Это идеальный десерт для тех, кто ценит изысканность без излишней сладости. Благодаря специальной технологии приготовления пудинг сохраняет свою воздушную структуру и свежесть.");
        Article art5 = new Article(UUID.randomUUID(), "Хлеб: Традиция в каждой крошке", "Наш хлеб выпекается по старинным рецептам с использованием натуральной закваски. Хрустящая корочка золотистого цвета и пористый, ароматный мякиш — вот отличительные черты этого традиционного хлеба. Мы используем только качественную муку и соблюдаем все этапы правильного брожения теста. Очень вкусно его кушать на завтрак с сыром и колбасой и запивая вином.");
        Map<UUID, Article> jornal = new HashMap<>();
        jornal.put(art1.getId(), art1);
        jornal.put(art2.getId(), art2);
        jornal.put(art3.getId(), art3);
        jornal.put(art4.getId(), art4);
        jornal.put(art5.getId(), art5);
        return jornal;
    }

    public int returnCount() {
        int c = 0;
        for (Product pr : storageProducts.values()) {
            c += pr.getPrice();
        }
        return c;
    }
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageProducts.get(id));
    }
    public void whatContainBasket() {
        int sum = returnCount();
        int specialGoods = 0;
        if (sum == 0) {
            System.out.println("В корзине пусто!");
        } else {
            for (Product pr : storageProducts.values()) {
                System.out.println(pr);
                if (pr.isSpecial())
                    specialGoods++;
            }
            System.out.println("Итого: " + sum);
            System.out.println("Специальных товаров: " + specialGoods);
        }
    }

    public boolean findProduct(String name) {
        for (Product pr : storageProducts.values()) {
            if (pr.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String name) {
        for (Product pr : storageProducts.values()) {
            if (Objects.equals(name, pr.getName())) {
                storageProducts.remove(pr.getId());
            }
        }
        return true;
    }

    public void cleanBasket() {
        storageProducts.clear();
    }



    public void printBasket() {
        if (storageProducts != null) {
            System.out.println("Теперь в корзине находятся: ");
            for (Product pr : storageProducts.values()) {
                System.out.println(pr);
            }
        } else {
            System.out.println("Корзина пуста.");
        }
    }
}
