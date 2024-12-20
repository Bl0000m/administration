package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Flower;
import kz.bloooom.administration.domain.entity.FlowerVariety;

import java.util.List;

public interface FlowerVarietyService {
    FlowerVariety save(FlowerVariety flowerVariety);

    FlowerVariety getById(Long id);

    List<FlowerVariety> getFlowerVarietiesByIdIn(List<Long> ids);

    List<FlowerVariety> getAll();
}
