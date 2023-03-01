package dev.be.pharmacyrecommendation.pharmacy.service;

import dev.be.pharmacyrecommendation.pharmacy.entity.Pharmacy;
import dev.be.pharmacyrecommendation.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {
    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public void updateAddress(Long id, String address) {
        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("[ PharmacyRepositoryService updateAddress ] not found id : {}", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }

    /*for dirty checking test*/
    public void updateAddressWithoutTransaction(Long id, String address) {
        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("[ PharmacyRepositoryService updateAddress ] not found id : {}", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }

    /*self invocation test*/
    public void bar(List<Pharmacy> pharmacyList) {
        log.info("bar CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
        foo(pharmacyList); // self-invocation
    }

    @Transactional
    public void foo(List<Pharmacy> pharmacyList) {
        log.info("foo CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
        pharmacyList.forEach(pharmacy -> {
            pharmacyRepository.save(pharmacy);
            throw new RuntimeException("error"); // 롤백 정책
            // 정상적으로 transactional 이 실행이 된다면, 롤백되어야 함
        });
    }

    @Transactional(readOnly = true)
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }
}
