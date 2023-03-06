package dev.be.pharmacyrecommendation.pharmacy.service;

import dev.be.pharmacyrecommendation.api.dto.DocumentDto;
import dev.be.pharmacyrecommendation.api.dto.KakaoApiResponseDto;
import dev.be.pharmacyrecommendation.api.service.KakaoAddressSearchService;
import dev.be.pharmacyrecommendation.direction.dto.OutputDto;
import dev.be.pharmacyrecommendation.direction.entity.Direction;
import dev.be.pharmacyrecommendation.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private static final String ROAD_VIEW_BASE_URL = "https://map.kakao.com/link/roadview/";

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public List<OutputDto> recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())){
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address : {}", address);
            return Collections.emptyList();
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

//        List<Direction> directionList = directionService.buildDirectionList(documentDto);
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);

        return directionService.saveAll(directionList)
                .stream()
                .map(this::conevertToOutputDto)
                .collect(Collectors.toList());
    }

    private OutputDto conevertToOutputDto(Direction direction) {
        return OutputDto.builder()
                .pharmacyName(direction.getTargetPharmacyName())
                .pharmacyAddress(direction.getTargetAddress())
                .directionUrl("todo") // TODO: url 넣어야함
                .roadViewUrl("todo") // TODO: url 넣어야함
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}
