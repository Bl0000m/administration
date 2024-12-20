package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BouquetFlowerVariety;

import java.util.List;

public interface BouquetFlowersService {
    void saveAll(List<BouquetFlowerVariety> bouquetFlowers);
}
