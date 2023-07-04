# FindHospital

### 사용한 기술
----------
- Retrofit을 통해 API 통신을 하였습니다.
- Navigation을 이용해 Fragment를 다뤘습니다.
- Hilt를 사용해 의존성 주입을 하였습니다.
- Coroutine을 이용해 비동기 처리를 하였습니다.
- Repository Pattern을 적용해 코드의 가독성을 높였습니다.
- MVVM 패턴으로 작성되었습니다.
  - LiveData를 이용해 데이터가 변경될 때 뷰에 알립니다.
  - AAC ViewModel를 이용해 데이터를 보존합니다.
  - Databindig을 이용해 선언적으로 데이터와 뷰를 바인딩합니다.

### UI
----------
#### 병원 검색

![Screen_Recording_20230602_015331_LifeSemantics_1](https://github.com/spicypunch/LifeSemantics/assets/72846127/ab38f20c-1ad1-4857-96a5-0e8340fefd44)

- 검색어를 입력하면 검색어를 포함한 병원 리스트가 출력됩니다.
- 10개 병원 단위로 출력되고 검색 결과가 나옵니다.
- 페이지를 넘길 수 있는 버튼이 표시되고 현재 페이지 번호가 출력됩니다.

<br>

#### 병원 선택

![Screen_Recording_20230602_015331_LifeSemantics_2](https://github.com/spicypunch/LifeSemantics/assets/72846127/9fadc379-34b6-4d7d-95d7-3d422d4284c0)

- 병원 중 한 곳을 선택하면 해당 병원의 세부 정보가 표시 됩니다.

<br>

#### 페이지 이동

![Screen_Recording_20230602_015331_LifeSemantics_3](https://github.com/spicypunch/LifeSemantics/assets/72846127/aa2f4abd-cec5-488c-befb-8f9a5a100948)

- 페이지 이동 버튼을 통해 앞 뒤 병원 목록을 확인할 수 있습니다.

<br>

#### 검색 결과 없음
![Screen_Recording_20230602_015331_LifeSemantics_4](https://github.com/spicypunch/LifeSemantics/assets/72846127/a7e053fb-b2d7-400c-9fb7-de3e41b230c8)

- 검색 결과가 없을 경우 다른 뷰들은 사라지고 검색된 병원이 없습니다 라는 문구가 나옵니다.

<br>

#### 다시 검색할 경우
![Screen_Recording_20230602_015331_LifeSemantics_5](https://github.com/spicypunch/LifeSemantics/assets/72846127/d40b88c7-1f49-4acf-a30c-a85ee75b274d)

- 검색결과와 병원 리스트, 페이지 이동 버튼이 나타납니다.


