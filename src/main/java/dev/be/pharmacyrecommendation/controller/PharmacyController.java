package dev.be.pharmacyrecommendation.controller;

import dev.be.pharmacyrecommendation.pharmacy.entity.Pharmacy;
import dev.be.pharmacyrecommendation.pharmacy.service.PharmacyRepositoryService;
import dev.be.pharmacyrecommendation.util.CsvUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyRepositoryService pharmacyRepositoryService;

    /* 데이터 초기 세팅을 위한 임시 메서드*/
    @GetMapping("/csv/save")
    public String saveCSV() {
        saveCsvToDatabase();

        return "sucess save";
    }

    public void saveCsvToDatabase() {
        List<Pharmacy> pharmacyList = loadPharmacyList();
        pharmacyRepositoryService.saveAll(pharmacyList);
    }

    public List<Pharmacy> loadPharmacyList() {
        return CsvUtils.convertToPharmacyDtoList()
                .stream().map(pharmacyDto ->
                    Pharmacy.builder()
                            .id(pharmacyDto.getId())
                            .pharmacyName(pharmacyDto.getPharmacyName())
                            .pharmacyAddress(pharmacyDto.getPharmacyAddress())
                            .latitude(pharmacyDto.getLatitude())
                            .longitude(pharmacyDto.getLongitude())
                            .build()
                ).collect(Collectors.toList());
    }
}

