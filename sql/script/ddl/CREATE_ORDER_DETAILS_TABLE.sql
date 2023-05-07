drop table if exists order_details;
create table order_details (
   id bigint not null auto_increment,
   order_details_id bigint,
   order_id bigint not null,
   orders_id bigint not null,
   pizza_id varchar(255) not null,
   quantity bigint not null,
   primary key (id),
   constraint uk_orders_id unique (orders_id),
   constraint fk_orders_id foreign key (orders_id) references orders (id)
)

