DELETE FROM user_role;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals(date_time, description, calories, user_id)
VALUES ('2023-10-21T10:18', 'завтрак', 350, 100000),
       ('2023-10-21T13:20', 'обед', 870, 100000),
       ('2023-10-21T18:20', 'ужин', 800, 100000),
       ('2023-10-21T14:30', 'обед', 530, 100001)

