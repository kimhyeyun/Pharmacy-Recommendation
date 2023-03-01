package dev.be.pharmacyrecommendation.pharmacy.service

import dev.be.pharmacyrecommendation.AbstractIntegrationContainerBaseTest
import dev.be.pharmacyrecommendation.pharmacy.entity.Pharmacy
import dev.be.pharmacyrecommendation.pharmacy.repository.PharmacyRepository
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired PharmacyRepositoryService pharmacyRepositoryService
    @Autowired PharmacyRepository pharmacyRepository

    def setup(){
        pharmacyRepository.deleteAll()
    }

    def "PharmacyRepository update - dirty chekcing success"(){
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy)
        pharmacyRepositoryService.updateAddress(entity.getId(), modifiedAddress)

        def result = pharmacyRepository.findAll()

        then:
        result.get(0).pharmacyAddress == modifiedAddress
    }

    def "PharmacyRepository update - dirty chekcing fail"(){
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy)
        pharmacyRepositoryService.updateAddressWithoutTransaction(entity.getId(), modifiedAddress)

        def result = pharmacyRepository.findAll()

        then:
        result.get(0).pharmacyAddress == inputAddress
    }

    def "self invocation"(){
        given:
        String address = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def pharmacy = Pharmacy.builder()
                .pharmacyName(name)
                .pharmacyAddress(address)
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when:
        pharmacyRepositoryService.bar(Arrays.asList(pharmacy))

        then:
        def e = thrown(RuntimeException.class)
        def result = pharmacyRepositoryService.findAll()
        result.size() == 1 // 트랜잭션이 적용되지 않음 (롤백 적용 x)
    }
}
