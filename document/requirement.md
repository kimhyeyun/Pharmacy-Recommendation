# 요구사항 분석

---
- 약국 찾기 서비스 요구사항
  - `약국 현황 데이터(공공 데이터)를 관리하고 있다`라고 가정하고, 약국 현황 데이터는 위도, 경도의 위치 정보 데이터를 가지고 있다.
  - 해당 서비스로 주소 정보를 입력하여 요청하면, 위치 기준에서 가까운 약국 3곳을 추출한다.
  - 주소는 도로명 주소 또는 지번을 입력하여 요청받는다.
    - 정확한 주소를 입력 받기 위해 [Kakao 우편번호 서비스](https://postcode.map.daum.net/guide) 사용
  - 주소는 정확한 상세 주소(동, 호수)를 제외한 주소 정보를 이용하여 추천한다.
    - ex) 서울 성북구 종암로 10길
  - 입력 받은 주소를 위도, 경도로 변환하여 기존 약국 데이터와 비교 및 가까운 약국을 찾는다.
    - 지구는 평면이 아니기 때문에, 구면에서 두 점 사이의 최단 거리를 구하는 공식이 필요
    - 두 위의 경도 좌표 사이의 거리를 [haversine formula](https://en.wikipedia.org/wiki/Haversine_formula)로 계산
    - 지구가 완전한 구형이 아니기 때문에, 아주 조금의 오차가 있다.
  - 입력한 주소 정보에서 정해진 반경(10km) 내에 있는 약국만 추천한다.
  - 추출한 약국 데이터는 길안내 URL 및 로드뷰 URL로 제공하다.
    - ex)
      - 길안내 URL : https://map.kakao.com/?urlX=523954.0&urlY=1084098.0&name=우리회사
      - 로드뷰 URL : https://map.kakao.com/link/roadview/37.402056,127.108212
  - 길안내 URL은 고객에게 제공 되기 때문에 가독성을 위해 shorten url로 제공한다.
  - shorten url에 사용 되는 key 값은 인코딩하여 제공한다.
    - ex) http://localhost:8080/dir/nqxtX
    - base62 를 통한 인코딩
  - shorten url의 유효 기간은 30일로 제한한다.

## Pharmacy Recommendation Process

---
![img.png](Desktop/YUN/fastCampus/Pharmacy-Recommendation/document/pharmacy_recommendation_system.png)

## Direction Shorten Url Process

---
![img_1.png](direction_process(shorten_url).png)

