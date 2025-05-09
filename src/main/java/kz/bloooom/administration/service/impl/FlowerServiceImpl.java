package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.Flower;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.FlowerRepository;
import kz.bloooom.administration.service.FlowerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlowerServiceImpl implements FlowerService {

    FlowerRepository flowerRepository;

    @Override
    @Transactional
    public Flower create(Flower flower) {
        return flowerRepository.save(flower);
    }

    @Override
    public Flower getById(Long id) {
        return flowerRepository.findById(id)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.FLOWER_THIS_ID_DOEST_EXISTS,
                        "messages.exception.flower-not-found", id));
    }

    @Override
    public List<Flower> getFlowersByIdIn(List<Long> ids) {
        return flowerRepository.findByIdIn(ids);
    }

    @Override
    public List<Flower> getAll() {
        return flowerRepository.findAll();
    }
}
