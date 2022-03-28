package com.task.springshop.formal.repository;

import com.task.springshop.model.entity.Order;
import com.task.springshop.model.entity.Product;
import com.task.springshop.model.entity.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public class ProductsRepository {
    private static ProductsRepository instance;
    private final List<Product> products;

    ProductsRepository() {
        products = List.of(
                new Product(1, "Гипюр \"GAMMA\" (14983) 57 мм", "some description",
                        "https://klub-ok.by/images/thumbnails/1280/1048/detailed/20/10_nzw7-sj.jpg",
                        new BigDecimal("4.00"), 35, ProductStatus.IN_STOCK, 1),
                new Product(2, "ГИПЮР \"GAMMA\" (3033) 35 ММ", "some description",
                        "https://klub-ok.by/images/thumbnails/1280/1048/detailed/20/4_bgp0-2m.jpg",
                        new BigDecimal("3.00"), 101, ProductStatus.IN_STOCK, 1),
                new Product(3, "КАРТИНА СТРАЗАМИ WIZARDI «COLOURFUL BOUQUET 40Х50», WD2520", "some description",
                        "https://klub-ok.by/images/thumbnails/560/458/detailed/37/colourful-bouquet-40kh50-wd2520.jpg",
                        new BigDecimal("71.88"), 50, ProductStatus.IN_STOCK, 1),
                new Product(4, "ПРЯЖА ALIZE «ANGORA GOLD OMBRE BATIK 150Г/850М", "some description",
                        "https://klub-ok.by/images/thumbnails/1834/1500/detailed/30/ANGORA_GOLD_OMBRE_BAT--K_7247_3.jpg",
                        new BigDecimal("15.00"), 49, ProductStatus.IN_STOCK, 1),
                new Product(5, "ПРЯЖА \"СЕМЕНОВСКАЯ\" LIDIYA 100Г/1613 М", "some description",
                        "https://klub-ok.by/images/thumbnails/978/800/detailed/42/0216.jpg",
                        new BigDecimal("7.00"), 0, ProductStatus.OUT_OF_STOCK, 1),
                new Product(6, "FNB-005 ИГЛЫ ДЛЯ ВАЛЯНИЯ (ФЕЛТИНГА)", "some description",
                        "https://klub-ok.by/images/thumbnails/1223/1000/detailed/35/d11241227062p.jpg",
                        new BigDecimal("7.20"), 47, ProductStatus.IN_STOCK, 1),
                new Product(7, "HPU-6 ЩИПЦЫ - ПРОБОЙНИК \"MICRON\" 6 НАСАДОК", "some description",
                        "https://klub-ok.by/images/thumbnails/978/800/detailed/27/df1402565032l.jpg",
                        new BigDecimal("45.00"), 97, ProductStatus.IN_STOCK, 1),
                new Product(8, "1657A \"PERSONAL\" 11Х3 СМ ТЕРМОАППЛИКАЦИЯ", "some description",
                        "https://klub-ok.by/images/thumbnails/400/328/detailed/27/d5154041782l.jpg",
                        new BigDecimal("1.70"), 127, ProductStatus.IN_STOCK, 1),
                new Product(9, "ПАЙЕТКИ \"ZLATKA\" РОССЫПЬЮ ZF-27 15 ММ ЦВЕТ 03 (КРАСНЫЙ)", "some description",
                        "https://klub-ok.by/images/thumbnails/612/500/detailed/40/d67391338084u_1.jpg",
                        new BigDecimal("1.84"), 250, ProductStatus.IN_STOCK, 1),
                new Product(10, "CIB-07 ДЕКОРАТИВНЫЕ ЭЛЕМЕНТЫ 170501 ЛОВЕЦ СНОВ", "some description",
                        "https://klub-ok.by/images/thumbnails/1223/1000/detailed/19/d51988008762l.jpg",
                        new BigDecimal("2.94"), 4, ProductStatus.IN_STOCK, 1)
        );
    }

    public static ProductsRepository getInstance() {
        if (instance == null) {
            instance = new ProductsRepository();
        }
        return instance;
    }

    public List<Product> getAll() {
        return products;
    }
}
