# ISD (Infrastructure Specification Document)

## Середовище розгортання
Хмарне середовище AWS EKS з Kubernetes 1.28+.

## Основні інфраструктурні компоненти
- Orchestration: Kubernetes + Helm charts.  
- Балансувальник: AWS ALB + Istio.  
- Бази даних: Elasticsearch 8.x, Prometheus + Thanos (S3), ClickHouse.  
- Message Broker: Confluent Kafka.  
- Секрет менеджмент: AWS Secrets Manager.  
- Backup: Velero + S3.

## Принципи масштабування
Horizontal Pod Autoscaler (HPA) за CPU/Memory та custom metrics.  
Cluster Autoscaler для нод.  
Multi-region disaster recovery.  
GitOps (ArgoCD) для декларативного розгортання.