insert into product (id, price, title)
values (1, 450.0, 'Cheese'),
       (2, 550.0, 'Beer'),
       (3, 650.0, 'Milk'),
       (4, 750.0, 'Tomato'),
       (5, 850.0, 'Potato'),
       (6, 950.0, 'Whiskey');

alter sequence product_seq restart with 7;