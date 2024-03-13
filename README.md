# socialNetwork
skillBox изучение микросервисов
Первый этап - Restfull ********************************
добавить автора 1 и 2:
POST: localhost:8080/author { "firstName": "firstName1","lastName": "lastName1" }
POST: localhost:8080/author { "firstName": "firstName2", "lastName": "lastName2" }
POST: localhost:8080/subscription { "author": 2, "subscription": 1 }
GET: localhost:8080/subscription?author=2 -- последние 10 подписок
GET: localhost:8080/subscription?author=2&count=1 -- последняя подписка
--
Тестирование контроллеров:
 1. Загрузка тестовых авторов из Json с подписками
 2. Загрузка Постов
 3. Загрузка комментариев
Авторы -> Посты
Подписки
Комментарии
 4. Сформировать на основе полученной базы JSON и сравнить с исходным как эталоном 

{ "firstName": "Иван","lastName": "Иванов",  }

private String firstName;
private String lastName;
private String mail;
private String phone;
private Character sex;
private String city;