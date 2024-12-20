package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.WaterCare;

import java.util.List;

public interface WaterCareService {
    WaterCare save(WaterCare waterCare);

    WaterCare getById(Long id);

    List<WaterCare> getAll();
}
