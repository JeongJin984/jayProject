## 목적

> MSA 환경을 유사하게 구현하여 몇가지 목표를 환경에 맞춰 학습

학습을 위해 가장 대중적인 이커머스를 구현(UI는 작성하지 않음)

1. DDD를 적용하여 Architecture 구분
2. 각 서비스에 맞는 단위 테스트 작성
3. ELK(or EFK)를 이용하여 서버 클러스터링에 따른 로그 중앙화
4. Kafka를 이용하여 비동기 메시징 구현

## 사용 스택

### 언어

- Kotlin
- MySQL
- Java

### Framework

- Spring Boot

### Infra

- Elastic Search
- Kibana
- FluentD or LogStash
- Kafka
- VmWare
- AWS(Optional)

## 특이사항

1. 편의를 위해 다수의 데이터베이스 생성 X(Schema로 분리)
2. Kubernetes 사용 X

## 시나리오

### 주문 및 결제

1. 주문 데이터 생성
2. 주문 확정
3. 결제 데이터 생성
4. 결제 확정