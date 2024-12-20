package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BouquetStyle;

import java.util.List;

public interface BouquetStyleService {
    BouquetStyle save(BouquetStyle bouquetStyle);

    BouquetStyle getById(Long id);

    List<BouquetStyle> getAll();
}
