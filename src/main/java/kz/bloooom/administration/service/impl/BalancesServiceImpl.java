package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.domain.entity.Balances;
import kz.bloooom.administration.repository.BalancesRepository;
import kz.bloooom.administration.service.BalancesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BalancesServiceImpl implements BalancesService {
    BalancesRepository balancesRepository;

    @Override
    public Balances getBalanceByUserId(Long userId) {
        return balancesRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Balances save(Balances balances) {
        return balancesRepository.save(balances);
    }
}
