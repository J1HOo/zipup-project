# ZIP UP

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-007ACC?style=for-the-badge&logo=mybatis&logoColor=white)
![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Lombok](https://img.shields.io/badge/Project%20Lombok-FF2D20?style=for-the-badge&logo=lombok&logoColor=white)

## 프로젝트 개요
ZIP UP은 Java Spring Boot를 기반으로 한 쇼핑몰 프로젝트로, MyBatis를 통해 데이터베이스와 통신하며, Thymeleaf를 활용해 동적 HTML 페이지를 생성합니다. 이 프로젝트는 프론트엔드와 백엔드 기술을 조화롭게 결합하여 효율적이고 사용자 친화적인 쇼핑몰 서비스를 제공합니다.

---

## 주요 기술 스택

- **Java**: 객체 지향 언어로 프로젝트의 핵심 로직 작성
- **Spring Boot**: 빠른 애플리케이션 개발을 위한 프레임워크
- **MyBatis**: 간결한 SQL 매핑 프레임워크로 데이터베이스 작업 처리
- **HTML, CSS, JavaScript**: 사용자 인터페이스 설계
- **Thymeleaf**: 서버에서 동적으로 HTML 렌더링
- **Lombok**: 반복적인 Java 코드를 간소화

---

## 디렉토리 구조
```
zipup
├── .idea
├── .mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.teamzipup.zipup
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── mapper
│   │   │       ├── service
│   │   │       └── ZipupApplication
│   │   ├── resources
│   │       ├── mappers
│   │       ├── static
│   │       ├── templates
│   │       ├── application.properties
│   │       └── config.properties
│   └── test
├── target
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── 어노테이션.txt
```

---

## 주요 기능

1. **DBConfig**: 데이터베이스 설정과 연결 관리
2. **Controller**: 클라이언트 요청 처리
3. **DTO (Data Transfer Object)**: 데이터 교환용 객체
4. **Mapper**: SQL 쿼리 매핑 및 실행
5. **Service**: 비즈니스 로직 구현
6. **Thymeleaf Templates**: UI 렌더링을 위한 동적 HTML

---

## 팀원 소개

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/e01f451e-e3b5-4fd8-a777-011042ee0dbe" alt="팀원이미지1" width="320" height="320" /></td>
    <td><img src="https://github.com/user-attachments/assets/820fca8f-a6c5-42c4-9658-6ee0184ec83d"  alt="팀원이미지2" width="320" height="320"/></td>
    <td><img src="https://github.com/user-attachments/assets/5c09ae75-81cf-4f8b-bf84-7168fb4e592b" alt="팀원이미지3" width="320" height="320"/></td>
    <td><img src="https://github.com/user-attachments/assets/1c4faff2-f501-48f3-a401-b8ad18562b42"  alt="팀원이미지1" width="320" height="320"/></td>
  </tr>
  <tr>
    <td>박현진</td>
    <td>안정수</td>
    <td>박지호</td>
    <td>황아현</td>
  </tr>
  <tr>
    <td>이용자 회원가입,마이 페이지</td>
    <td>판매자 회원가입,검색 기능</td>
    <td>메인 페이지, 로그인, 검색 기능, 상품 생성, 상품 디테일</td>
    <td>상품 생성,상품 디테일</td>
  </tr>
  <tr>
    <td>ERD, DB, READ ME</td>
    <td>ERD, DB, PPT</td>
    <td>팀장, git </td>
    <td>노션 정리, 디자인</td>
  </tr>
</table>

---

## 실행 방법

1. 이 저장소를 클론합니다.
   ```bash
   git clone <repository_url>
   ```

2. 프로젝트 디렉토리로 이동합니다.
   ```bash
   cd zipup
   ```

3. 필요한 의존성을 설치합니다.
   ```bash
   mvn install
   ```

4. 애플리케이션을 실행합니다.
   ```bash
   mvn spring-boot:run
   ```

5. 브라우저에서 [http://localhost:8080](http://localhost:8080)을 열어 확인합니다.

---

## 의존성

`pom.xml` 파일에 포함된 주요 의존성:
- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- MyBatis Spring Boot Starter
- Lombok

---

## 기여 방법

1. 이 프로젝트를 포크합니다.
2. 새로운 브랜치를 생성합니다.
   ```bash
   git checkout -b feature/새로운_기능
   ```
3. 변경 사항을 커밋합니다.
   ```bash
   git commit -m "새로운 기능 추가"
   ```
4. 브랜치를 푸시합니다.
   ```bash
   git push origin feature/새로운_기능
   ```
5. Pull Request를 생성합니다.

---

## 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 LICENSE 파일을 확인하세요.

---

## 문의

프로젝트와 관련하여 문의 사항이 있으면 아래 이메일로 연락주세요.

- Email: [박현진 : zhwoddl0723@gmail.com](mailto:your_zhwoddl0723@gmail.com)
- Email: [안정수 : anj575981@gmail.com](mailto:your_anj575981@gmail.com)
- Email: [박지호 : businessj1ho@gmail.com](mailto:your_businessj1ho@gmail.com)
- Email: [황아현 : hah4815@gmail.com](mailto:your_hah4815@gmail.com)


