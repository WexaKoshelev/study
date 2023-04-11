create table if not exists flowers
(
    id      serial primary key,
    title   varchar(100) UNIQUE check ( title <> '') not null,
    price   integer not null
);
insert into flowers (title, price)
values ('Розы', 100);
insert into flowers (title, price)
values ('Лилии', 50);
select * from flowers;
insert into flowers (title, price)
values ('Ромашки', 25);
create table if not exists clients
(
    id    serial primary key,
    names  varchar(50) not null,
    phone varchar(30) not null
);
select * from flowers;


insert into clients (names, phone)
values ('Анна', '79601234567'),
       ('Вениамин', '79001234567'),
       ('Инга', '79510001122'),
       ('Николай', '79605552030'),
       ('Петр', '79085052139');
select * from clients;


create table if not exists orders
(
    id              serial primary key,
    client_id       integer references clients (id)                     not null,
    flower_id        integer references flowers (id)                       not null,
    amount          integer check ( amount >= 1 and amount <= 1000 )    not null,
    dates            timestamp                                           not null
);

insert into orders (client_id, flower_id, amount, dates)
values (2, 2, 10, '2022-09-01'),
       (2, 2, 5, '2022-09-10'),
       (3, 1, 3, '2022-09-30'),
       (3, 1, 6, '2022-10-02');
select * from orders;


select o.id, f.title, o.amount, c.names, c.phone
from orders o
        inner join clients c on c.id = o.client_id
        inner join flowers f on f.id = o.flower_id;

select o.id, f.title, f.price, o.amount, c.names, c.phone
from orders o
        inner join clients c on c.id = o.client_id
        inner join flowers f on f.id = o.flower_id
        where client_id = 2 and dates <= now() - interval '30 day' ;

select  amount , title
from orders o
        join flowers f on f.id = o.flower_id
        where amount = (select  max(amount) from orders);

select sum(o.amount * f.price) as total
from orders o inner join flowers f on f.id = o.flower_id;
