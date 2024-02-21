# MICROSERVICES WITH SPRING CLOUD

Proyecto final que se realizó como ejercicio de un curso de microservicios con spring cloud. Cuenta con 3 microservicios que se comunican entre si products-service->carts-service->sales-service en donde las API son consumidas con Feign

En dichos microservicios se utilizaron los siguientes patrones de diseño:

* Service Regsitry y Service discovery (Eureka server)
* Load Balancing (Spring Cloud Load Balancer)
* Circuit Breaker (Resilience4J)
* API gateway (Spring Cloud Gateway)

Además se creó un Dockerfile para cada servicio con el cual se generó un imagen y posteriormente sus contenedores