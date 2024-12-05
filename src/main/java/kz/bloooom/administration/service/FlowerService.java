package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Flower;

import java.util.List;

public interface FlowerService {
    Flower create(Flower flower);

    List<Flower> getFlowersByIdIn(List<Long> ids);
}
