package com.novozy.restaurantmicroservice.service;

import com.novozy.restaurantmicroservice.dto.RestaurantDTO;
import com.novozy.restaurantmicroservice.entity.Restaurant;
import com.novozy.restaurantmicroservice.mapper.RestaurantMapper;
import com.novozy.restaurantmicroservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> findAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO).collect(Collectors.toList());
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant saved = restaurantRepository.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(saved);
    }

    public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
        Optional<Restaurant> repositoryById = restaurantRepository.findById(id);
        return repositoryById.map(restaurant -> new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
