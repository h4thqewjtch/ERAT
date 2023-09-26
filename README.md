# Лабораторные работы ПнаЯВУ
---

1.	Intro
1.1 Создать и запустить локально простой веб/REST сервис, используя любой открытый (например, в открытом доступе в сети  интернет) пример с использованием Java stack: Spring (Spring Boot)/maven/gradle/Jersey/ Spring MVC.
1.2 Добавить GET ендпоинт, принимающий входные параметры в качестве queryParams в URL и возвращающий результат в виде JSON согласно варианту. 
2.	Error logging/handling
2.1 Добавить валидацию входных параметров с возвращением 400 ошибки.
2.2 Добавить обработку внутренних unchecked ошибок с возвратом 500 ошибки
2.3 Добавить логирование действий и ошибок
2.4 Написать unit test 
3.	Collections intro, project structure 
3.1 Добавить простейший кэш в виде in-memory Map для сервиса. Map должна содержаться в отдельном бине/классе, который должен добавляться в основной сервис с помощью dependency injection механизм Spring
4.	Concurrency 
4.1 Добавить сервис для подсчёта обращений к основному сервису. Счётчик должен быть реализован в виде отдельного класса, доступ к которому должен быть синхронизирован.
4.2 Используя jmeter/postman или любые другие средвста сконфигурировать нагрузочный тест и убедиться, что счётчик обращений работает правильно при большой нагрузке.
5.	Functional programming with Java 8
5.1 Преобразовать исходный сервис для работы со списком параметров для bulk операций используя Java 8 лямбда выражения.
5.2 Добавить POST метод для вызова bulk операции и передачи списка параметров в виде JSON
6.	Functional filtering and mapping
6.1 Добавить аггрегирующий функционал (подсчёт макс, мин, средних значений) для входных параметров и результатов с использованием Java 8 map/filters функций.
6.2 Расширить результат POST соотвественно.
7.	Data persistence
7.1 Добавить возможность сохранения всех результатов вычислений в базе данных или файле, используя стандартные persistence фреймворки Java (Spring Data/Hibernate/MyBatis)
8.	Asynchronous calls
8.1 Добавить возможность асинхронного вызова сервиса используя future, возвращать статус вызова REST сервиса не дожидаясь результатов подсчётов.
8.2 Результаты подсчётов должны быть представлены в БД по предопределённой ID

Сервис должен принимать два параметра (значение 1, значение 2) и вернуть результаты сложения, вычитания, произведения и деления предоставленных параметров.
