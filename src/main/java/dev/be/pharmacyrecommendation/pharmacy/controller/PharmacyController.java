package dev.be.pharmacyrecommendation.pharmacy.controller;

import dev.be.pharmacyrecommendation.pharmacy.cache.PharmacyRedisTemplateService;
import dev.be.pharmacyrecommendation.pharmacy.dto.PharmacyDto;
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
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;

    /* 데이터 초기 세팅을 위한 임시 메서드*/
    @GetMapping("/csv/save")
    public String saveCSV() {
//        saveCsvToDatabase();
        saveCsvToRedis();

        return "sucess save";
    }

    private void saveCsvToRedis() {
        List<PharmacyDto> pharmacyDtoList = pharmacyRepositoryService.findAll()
                .stream().map(pharmacy -> PharmacyDto.builder()
                        .id(pharmacy.getId())
                        .pharmacyName(pharmacy.getPharmacyName())
                        .pharmacyAddress(pharmacy.getPharmacyAddress())
                        .latitude(pharmacy.getLatitude())
                        .longitude(pharmacy.getLongitude())
                        .build())
                .collect(Collectors.toList());

        pharmacyDtoList.forEach(pharmacyRedisTemplateService::save);
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

