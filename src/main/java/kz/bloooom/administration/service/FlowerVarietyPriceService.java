package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.FlowerVarietyPrice;

public interface FlowerVarietyPriceService {
    FlowerVarietyPrice create(FlowerVarietyPrice flowerVarietyPrice);

    FlowerVarietyPrice getByFlowerVarietyId(Long flowerVarietyId);

    FlowerVarietyPrice getById(Long id);
}
