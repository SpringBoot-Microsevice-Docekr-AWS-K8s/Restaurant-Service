package com.novozy.restaurantmicroservice.repository;

import com.novozy.restaurantmicroservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer > {
}
