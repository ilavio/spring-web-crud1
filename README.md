# spring-web-crud1
Круд приложение с использованием Spring и Thymeleaf, подключение к БД происходит через jdbc
Для запуска я использовал: tomcat 9.0.54, PostgreSQL 12, IDE - Eclipse 2021-06 (4.20.0) (на Eclipse так же использовал встроенный tomcat 9)
Установка Tomcat наглядно показана здесь: https://youtu.be/QWLjhk6LeSQ ;
Установка PostgreSQL показана здесь: https://youtu.be/oEi5IUgxaU0 ;
Установка Tomcat на Eclipse: https://youtu.be/CyJi6iwFJgY ;
Перед запуском вам потребуется: 
1) Изменить абсолютный путь (переменная PATH в классе PersonDAO, располагается в spring-web-crud1/src/main/java/il/cruds/com/dao/PersonDAO.java) 
на ваш абсалютный путь от файла database.properties (который располагается в spring-web-crud1/src/main/resources/database.properties)
2) изменить в database.properties переменные, на ваши настройки от базы данных PostgreSql (а именно:
  а) изменить адрес запроса - url
  б) изменить логин - login
  в) пароль - pass соответственно)
3) Если вы будете деплоить в desctop tomcat то пересобрать архив .war
  
  P.S.: Я знаю, что желательно использовать относительный путь, но он не хочет работать в этом приложении (относительный путь закоментирован в классе). 
  Не хочет работать инструмент от Spring: @PropertySource("classpath:database.properties") и @Value("${url}"), (они так же закоментированны по этой причине)
  classpath - файл осматривал каталог с path="src/main/resources" указан.
