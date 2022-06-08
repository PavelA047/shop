insert into users (id, archive, email, name, password, role)
values (1, false, 'mail@mail.ru', 'admin', 'pass', 'ADMIN');

insert into users (id, archive, email, name, password, role)
values (2, false, 'mail@mail.ru', '111', 'pass', 'CLIENT');

alter sequence user_seq restart with 3;