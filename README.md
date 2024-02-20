# socialNetwork
skillBox изучение микросервисов
Первый этап - Restfull ********************************
добавить автора 1 и 2:
POST: localhost:8080/author { "firstName": "firstName1","lastName": "lastName1" }
POST: localhost:8080/author { "firstName": "firstName2", "lastName": "lastName2" }
POST: localhost:8080/subscription { "author": 2, "subscription": 1 }
GET: localhost:8080/subscription?author=2 -- последние 10 подписок
GET: localhost:8080/subscription?author=2&count=1 -- последняя подписка