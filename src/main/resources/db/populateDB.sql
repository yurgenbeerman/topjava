DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

  id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id      INTEGER REFERENCES users (id),
  date_time    TIMESTAMP DEFAULT now(),
  description  VARCHAR NOT NULL,
  calories     INTEGER DEFAULT 1000 NOT NULL

INSERT INTO meals (user_id, description, calories)
VALUES (100000, 'User ланч', '500');

INSERT INTO meals (user_id, description, calories)
VALUES (100000, 'User ужин', '1500');
--        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), ADMIN_ID);
--        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500), ADMIN_ID);