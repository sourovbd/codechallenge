drop table if exists orders;
create table orders
(
   id       bigint not null auto_increment,
   order_id bigint not null,
   date varchar(255) not null,
   time     varchar(255) not null,
   primary key (id),
   constraint uk_order_id unique (order_id)
)

