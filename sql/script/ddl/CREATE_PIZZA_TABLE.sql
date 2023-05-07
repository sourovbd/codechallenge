drop table if exists pizza;
create table pizza(
   id bigint not null auto_increment,
   pizza_id varchar(255) not null,
   pizza_type_id varchar(255) not null,
   pizza_types_id bigint not null,
   size varchar(255) not null,
   price bigint not null,
   constraint pk_id primary key (id),
   constraint uk_pizza_id unique (pizza_id),
   constraint fk_pizza_types_id foreign key (pizza_types_id) references pizza_types (id)
)
