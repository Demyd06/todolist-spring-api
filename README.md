# To-Do List REST API

Повноцінний, захищений Enterprise-ready REST API додаток, розроблений на **Java та Spring Boot**. Проєкт реалізує повний цикл управління завданнями з урахуванням сучасних стандартів бекенд-розробки: безпеку на основі JWT, реляційну базу даних PostgreSQL, контейнеризацію за допомогою Docker та оптимізовану пагінацію.

---

## Технологічний стек

* **Core:** Java 21, Spring Boot, Spring Framework
* **Web & API:** Spring MVC, RESTful API, SpringDoc OpenAPI (Swagger UI)
* **Security:** Spring Security, JWT (JSON Web Tokens), BCrypt Password Encoding
* **Database & ORM:** PostgreSQL, Spring Data JPA, Hibernate
* **Data Validation:** Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Email`, `@Size`)
* **Pagination & Sorting:** Spring Data `Pageable` & `Page<T>`
* **Containerization:** Docker, Docker Compose
* **Build Tool:** Maven

---

## Архітектура проєкту

Проєкт побудований за класичним паттерном **3-Tier Architecture (Тришарова архітектура)**, що забезпечує чіткий поділ відповідальності:

1. **Controller Layer:** Приймає HTTP-запити, валідує вхідні дані та повертає клієнту безпечні DTO (`record`).
2. **Service Layer:** Містить всю бізнес-логіку програми, роботу з контекстом безпеки (`SecurityContextHolder`) та управління токенами.
3. **Repository Layer:** Спілкується з базою даних через Spring Data JPA / Hibernate без написання ручного SQL.

---

## Головні фічі проєкту

* **Безпека та JWT:** Автентифікація через JWT-токени з власним фільтром перехоплення (`OncePerRequestFilter`).
* **Приватність даних (Data Ownership):** Автоматична прив'язка завдань до поточного авторизованого користувача. Користувач має доступ виключно до власних даних.
* **Безпечне зберігання:** Хешування паролів за допомогою алгоритму **BCrypt**.
* **Пагінація та Сортування:** Оптимізована вибірка великих обсягів даних за допомогою об'єкта `Pageable`.
* **Глобальна обробка помилок:** Централізований перехоплювач винятків (`@RestControllerAdvice`), що повертає зрозумілі JSON-відповіді з відповідними HTTP-статусами.

---

## Швидкий запуск через Docker

Найпростіший спосіб запустити додаток разом із базою даних PostgreSQL — використати Docker Compose.

1. Переконайся, що у тебе запущений **Docker Desktop**.
2. Створи в коріні проєкту файл `.env` із налаштуванням пароля:

```env
   POSTGRES_PASSWORD=1234
 ```
Відкрийте термінал та напишіть

```bash
   docker-compose up -d