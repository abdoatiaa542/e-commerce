package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Product;

import java.util.Set;

public interface FavoritesService {

    Set<Product> getFavorites(int id);

    Product addFavorite(int userId, int productId);

    Product removeFavorite(int userId, int productId);

}
