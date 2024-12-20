package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.StemCare;

import java.util.List;

public interface StemCareService {
    StemCare save(StemCare stemCare);

    StemCare getById(Long id);

    List<StemCare> getAll();
}
