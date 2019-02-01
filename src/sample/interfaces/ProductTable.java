package sample.interfaces;

import sample.objects.Product;

public interface ProductTable {
    void add(Product product);

    void update(Product product);

    void delete(Product product);
}
