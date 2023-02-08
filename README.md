# Pharmacy-Recommendation

---

[외부 API (카카오 주소 검색 API)](https://developers.kakao.com/docs/latest/ko/local/dev-guide)와 [공공 데이터 (약국 현황 정보)](https://www.data.go.kr/data/15065023/fileData.do)를 활용함으로써 혼자 개발하고 마무리 되는 프로젝트가 아닌 실제 서비스 가능한 수준의 프로젝트를 경험해본다.

추천된 약국 길 안내는 [카카오 지도 및 로드뷰 바로가기 URL](https://apis.map.kakao.com/web/guide/#routeurl) 로 제공된다.

## [요구사항 분석](/document/requirement.md)

---

## Feature List

---

- Spring Data JPA를 이용한 CRUD 메서드 구현하기
- Spock를 이용한 테스트 코드 작성하기
- Testcontainers를 이용하여 독립 테스트 환경 구축하기
- 카카오 주소 검색 API 연동하기 주소를 위도, 경도로 변환하기
- 추천 결과를 카카오 지도 URL로 연동하여 제공하기
- 공공 데이터를 활용하여 개발하기 (약국 현황 데이터)
- Handlerbars를 이용한 간단한 View 만들기
- 도커를 사용하여 다중 컨테이너 애플리케이션 만들기
- 애플리케이션을 클라우드 서비스에 배포하기
- Spring retry를 이용한 재처리 구현하기 (카카오 API의 네트워크 오류 등에 대한 재처리)
- base62를 이용한 shorten url 개발하기 (길 안내 URL)
- redis를 이용하여 성능 최적화하기

## Tech Stack

---

- JDK 17
- Spring Boot 3.0.2
- Spring Data JPA
- Gradle
- Handlers
- Lombok
- Github
- Docker
- AWS EC2
- Redis
- MariaDB
- Spock
- Testcontainers

