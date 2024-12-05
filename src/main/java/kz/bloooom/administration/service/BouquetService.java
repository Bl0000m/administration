package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Bouquet;

import java.util.List;

public interface BouquetService {
    Bouquet create(Bouquet bouquet);

    Bouquet getById(Long id);

    List<Bouquet> getAll();
}
