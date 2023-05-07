drop table if exists pizza_types;
create table pizza_types (
    id bigint not null auto_increment,
    category varchar(255) not null,
    ingredients varchar(255) not null,
    name varchar(255) not null,
    pizza_type_id varchar(255) not null,
    constraint pk_id primary key (id),
    constraint uk_pizza_type_id unique (pizza_type_id)
)
