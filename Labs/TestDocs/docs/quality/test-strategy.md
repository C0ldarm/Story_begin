# Test Strategy

## Підхід до тестування
- Unit (80 % покриття).  
- Integration (Testcontainers + Kafka).  
- E2E (Playwright).  
- Performance (k6 — 10k events/sec).  
- Security (OWASP ZAP).  

Автоматизація в CI/CD.  
Критерії виходу: 95 % покриття тестами, zero critical defects.