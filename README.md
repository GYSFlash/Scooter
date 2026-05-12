# Scooter

Сервис аренды электросамокатов: пользователи и вход, парк самокатов и точки их размещения, поездки с расчётом стоимости, push-уведомления. Микросервисы на Spring Boot, регистрация в Eureka, вход снаружи через API Gateway.

**Стек:** Java 17, Spring Boot, PostgreSQL + Liquibase, Kafka, JWT.

**Сервисы:** Eureka (8761), Gateway (8080), User (8081), Scooter (8082), Trip (8083), Push (8084). У каждого свой `pom.xml` и своя БД.

**Запуск:** поднять PostgreSQL и Kafka, затем Eureka, потом остальные сервисы, в конце Gateway. В `application.properties` — URL БД, Kafka, Eureka и секреты; для продакшена вынести в переменные окружения.

---

## Таблицы БД
**user_db**

<img width="603" height="433" alt="image" src="https://github.com/user-attachments/assets/26f27c10-bb0c-41e9-b3b2-17798f055680" />

**scooter_db**

<img width="582" height="428" alt="image" src="https://github.com/user-attachments/assets/4992c5a8-bfd7-4149-af5a-202c83ac46dd" />

**trip_db**

<img width="241" height="390" alt="image" src="https://github.com/user-attachments/assets/b4d7aa20-a4bf-4ed3-963b-9623f98179cd" />

**push_db**

<img width="278" height="455" alt="image" src="https://github.com/user-attachments/assets/e5127f12-7618-44b4-b20a-39ffb5c0a97a" />

---

## Схема взаимодействия сервисов

<img width="1192" height="653" alt="image" src="https://github.com/user-attachments/assets/47e65a91-d092-47db-92da-3ce9b245658d" />



---

## Описание Сервисов

**Eureka** — реестр сервисов.

**Gateway** — маршруты `/api/...` на сервисы по имени в Eureka.

**User** — регистрация, логин (JWT), пользователи, смена пароля.

**Scooter** — самокаты и точки размещения, статусы, привязка к станции, поиск точки размещения рядом с пользователем.

**Trip** — старт и завершение поездки, цена по времени или километражу, скидка на каждую 10-ю поездку, бесплатная езда с подпиской; обращение к User и Scooter по сети; события в Kafka для push.

**Push** — слушает Kafka (`trip.started`, `trip.ended`), сохраняет уведомления.

