package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BouquetFlowers;

import java.util.List;

public interface BouquetFlowersService {
    void saveAll(List<BouquetFlowers> bouquetFlowers);
}
