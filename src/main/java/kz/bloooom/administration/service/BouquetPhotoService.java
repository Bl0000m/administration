package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.BouquetPhoto;

import java.util.List;

public interface BouquetPhotoService {
    List<BouquetPhoto> saveAll(List<BouquetPhoto> photos);
}
