## 목적

> MSA 환경을 유사하게 구현하여 몇가지 목표를 환경에 맞춰 학습

학습을 위해 가장 대중적인 이커머스를 구현(UI는 작성하지 않음)

1. DDD를 적용하여 Architecture 구분
2. 각 서비스에 맞는 단위 테스트 작성
3. ELK(or EFK)를 이용하여 서버 클러스터링에 따른 로그 중앙화
4. Kafka를 이용하여 비동기 메시징 구현

## 사용 스택

## 언어

- Kotlin
- MySQL
- Java

## Framework

- Spring Boot

## Infra

- Elastic Search
- Kibana
- LogStash
- Kafka
- Docker (VMware -> Docker : 네트워크 Issue)
- AWS(Optional)

## 특이사항

1. 편의를 위해 다수의 데이터베이스 생성 X(Schema로 분리)
2. Kubernetes 사용 X

## 시나리오

---

### 주문 및 결제 

1. 주문 데이터 생성 ✅
2. 주문 확정 ✅
3. 결제 데이터 생성 ✅
4. 결제 확정 ( 쿠폰 사용을 통한 총 금액 할인 ) ✅
5. Transact 서버에서 거래 데이터 생성(order Topic Listening) ✅
6. 결제 실패 (Transact에서 실패 시 보상 트랜잭션 생성) ✅
7. Order서버에서 데이터 원복 (order-cancel Topic Listening) ✅

> 현재 도메인 재설계로 인해 없어짐
  
**목표**

1. Kafka를 이용한 보상트랜잭션을 사용하여 에러 처리
2. replyingKafkaTemplate, kafkaTemplate을 이용하여 동기식, 비동기식으로 메시지 처리 (삭제)
   - replyingKafkaTemplate으로 결제 실패를 처리(1:1로 처리)하려 했으나 많은 서버에서 실패를 처리(1:N)해야 하므로 kafkaTemplate으로 교체
   - 즉 OrderServer에서 데이터 원복만 하려 했는데 MemberServer에서도 쿠폰 데이터 원복을 처리해야해서 replying은 못씀

---

### 상품 정보 조회

1. 상품 정보 조회
2. grpc를 활용하여 Review 서버, Member 서버에서 필요한 데이터 수집

**목표**

1. grpc를 활용하여 REST 방식과 비교하여 장단점 비교
