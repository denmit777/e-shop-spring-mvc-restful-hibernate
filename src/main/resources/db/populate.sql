insert into user(id, login, email, password, user_role) values (1, 'Den', 'den@mail.ru', '$2y$10$fAhhMrd4fKSawqNjJGse5ej/.zwAIFnsDv4JVLrchTWtBmPFliuJG', 'admin'),(2, 'Peter', 'pet@gmail.ru', '$2y$10$Jz/fkQ8zhUxIa0ANiDQJzuAwijen4gUPWN72Bv0QjWgP.c8B9c2Ue', 'user'),(3, 'Asya', 'acdc9@mail.ru', '$2y$10$9IDpH95MdvX0oxFWM92w1.qRpzoaNNunEqq5jD2ovXcps9IPCYA1G', 'user');

insert into orders(id, user_id, total_price) values (1, 1, 302.0),(2, 2, 505.5),(3, 3, 5.7);

insert into good (id, name, price) values (1, 'Book', 5.5),(2, 'Phone', 100.0),(3, 'Juice', 2.0),(4, 'Phone', 200.0),(5, 'Beer', 1.5),(6, 'Computer', 500.0),(7, 'Wisky', 4.2);


