package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;

import java.util.List;

public interface FlowerVarietyPriceService {
    FlowerVarietyPrice create(FlowerVarietyPrice flowerVarietyPrice);

    List<FlowerVarietyPrice> getAllByFlowerVarietyId(Long flowerVarietyId);


    FlowerVarietyPrice getById(Long id);
}
