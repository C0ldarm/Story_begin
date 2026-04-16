# SDD (Software Design Document)

## Загальна архітектура
Архітектурний стиль — мікросервіси на базі Kubernetes з використанням message queue (Apache Kafka) для асинхронної обробки подій.

## Основні компоненти
1. Ingestion Service — приймає дані від агентів.  
2. Processing Service — нормалізація, збагачення даних.  
3. Storage Layer — Elasticsearch (логи), Prometheus + Thanos (метрики), ClickHouse (аналітика).  
4. Query Service — уніфікований API для пошуку.  
5. Dashboard Service — frontend дашбордів.  
6. Alert Manager — правила алертів.  
7. Auth Service — централізована автентифікація.

## Взаємодія між компонентами
Ingestion → Kafka → Processing (асинхронно).  
Query Service використовує gRPC до Storage.  
Dashboard → Query Service (REST).  
Alert Manager підписується на Kafka topics.  

## Короткий опис API
POST /api/v1/ingest/logs — приймає JSON з полями timestamp, level, message, service.  
GET /api/v1/query/metrics — запит метрик.