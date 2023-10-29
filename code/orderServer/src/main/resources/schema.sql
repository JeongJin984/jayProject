create table order_list (
    id bigint not null auto_increment,
    amount decimal(38,2),
    price decimal(38,2),
    quantity integer,
    product_id bigint,
    product_order_id bigint,
    primary key (id),

    foreign key (product_id) references product (id),
    foreign key (product_order_id) references product_order (order_id)
) engine=InnoDB;

create table product (
    id bigint not null auto_increment,
    name varchar(255),
    price decimal(38,2),
    description varchar(255),
    primary key (id)
) engine=InnoDB;


create table product_order (
    order_id bigint not null auto_increment,
    order_state tinyint,
    total_amounts decimal(38,2),
    shipping_info_id bigint,
    owner_id bigint,
    primary key (order_id),

    foreign key (shipping_info_id) references shipping_info (id)
) engine=InnoDB;


create table shipping_info (
    id bigint not null auto_increment,
    address1 varchar(255),
    address2 varchar(255),
    zip_code varchar(255),
    name varchar(255),
    phone varchar(255),
    primary key (id)
) engine=InnoDB;