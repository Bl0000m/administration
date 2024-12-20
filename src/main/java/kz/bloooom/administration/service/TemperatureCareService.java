package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.TemperatureCare;

import java.util.List;

public interface TemperatureCareService {
    TemperatureCare save(TemperatureCare temperatureCare);

    TemperatureCare getById(Long id);

    List<TemperatureCare> getAll();
}
