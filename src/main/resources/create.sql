create table shopping_basket
(
   id IDENTITY not null,
   created_ts DATETIME,
   last_updated_ts DATETIME,
   username varchar(255) not null,
   sub_total NUMBER(9,2),
   tax_perc integer,
   tax_amount NUMBER(9,2),
   total NUMBER(9,2),
   primary key(id)
);

create table item
(
   id IDENTITY not null,
   quantity integer,
   name varchar(255) not null,
   price NUMBER(9,2),
   basket_id BIGINT,
   foreign key (basket_id) references shopping_basket (id), 
   primary key(id)
);

