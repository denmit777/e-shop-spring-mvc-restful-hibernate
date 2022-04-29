UserController:
GetAllUsers(GET): http://localhost:8081/users;
GetUserById(GET): http://localhost:8081/users/id;
SaveUser(POST): http://localhost:8081/users + body;
UpdateUser(PUT): http://localhost:8081/users/id + body;
DeleteUser(DELETE): http://localhost:8081/users/id;

GoodController:
GetAllGoods(GET): http://localhost:8081/goods;
GetGoodById(GET): http://localhost:8081/goods/id;
SaveGood(POST): http://localhost:8081/goods + body;
UpdateGood(PUT): http://localhost:8081/goods/id + body;
DeleteGood(DELETE): http://localhost:8081/goods/id;

OrderController:
GetAllOrders(GET): http://localhost:8081/orders;
GetOrderById(GET): http://localhost:8081/orders/id;
GetOrderByUserId(GET): http://localhost:8081/orders/users/userId;
SaveOrder(POST): http://localhost:8081/orders + body;
UpdateOrder(PUT): http://localhost:8081/orders/id + body;
DeleteOrder(DELETE): http://localhost:8081/orders/id;
