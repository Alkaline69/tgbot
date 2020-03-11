# AromaBot
# Тестовое задание для работы в группах

### Версия JAVA
Сборка и локальный запуск проекта поддерживаются на Oracle JDK 11 и OpenJDK 11 с последними исправлениями безопасности.

### Конфигурация запуска
* Зарегистрировать через API Telegram нового бота  (с помощью команд бота @BotFather)
* Прописать полученные username и токен в конфиг `botconfig.yaml`

### Сборка проекта
Сборка и запуск осуществляются с помощью команды `gradlew bootRun`

### СУБД
Для проекта используется H2. 
Сконфигурирована для работы in-memory, т.е. инициализируется при запуске приложения и уничтожается при останове.
Скрипты находятся здесь:
* ddl: `src/main/resources/schema.sql`
* dml: `src/main/resources/data.sql`

Для типобезопасного написания запросов в приложении используется библиотека JOOQ.
При старте приложения просле пролития скриптов таской `gradlew generateSampleJooqSchemaSource` создаются
java-артефакты, отражающие структуру таблиц БД.
По умолчанию логгирование библиотеки включено на DEBUG (вывод в лог построенных запросов с биндами переменных,
результаты селектов ограничиваются 15 строчками).
Если нужна детализация - можно изменить в `src/main/resources/application.yml` уровень логгирования на TRACE.
Лог лежит в `out/logs/test.log`