package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.entity.BranchDivision;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.BranchDivisionRepository;
import kz.bloooom.administration.service.BranchDivisionService;
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
public class BranchDivisionServiceImpl implements BranchDivisionService {
    BranchDivisionRepository branchDivisionRepository;

    @Override
    @Transactional
    public BranchDivision save(BranchDivision branchDivision) {
        return branchDivisionRepository.save(branchDivision);
    }

    @Override
    public BranchDivision getById(Long branchDivisionId) {
        return branchDivisionRepository.findById(branchDivisionId)
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.BRANCH_WITH_THIS_ID_DOEST_EXISTS,
                        "messages.exception.branch-not-found", branchDivisionId));

    }

    @Override
    public List<BranchDivision> getAll() {
        return branchDivisionRepository.findAll();
    }

    @Override
    public List<BranchDivision> getAllByCompanyId(Long companyId) {
        return branchDivisionRepository.findAllByCompanyId(companyId);
    }
}
