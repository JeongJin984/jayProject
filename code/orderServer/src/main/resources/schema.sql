create table order_list (
    id bigint not null auto_increment,
    amount integer,
    price bigint,
    quantity integer,
    product_id bigint,
    product_order_id bigint,
    primary key (id)
) engine=InnoDBl;

create table product (
    id bigint not null auto_increment,
    detail varchar(255),
    name varchar(255),
    price integer,
    primary key (id)
) engine=InnoDB;


create table product_order (
    order_id bigint not null auto_increment,
    order_state tinyint,
    total_amounts integer,
    shipping_info_id bigint,
    primary key (order_id)
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

alter table order_list
    add constraint FKcj44h53c87hr23l208dyshj1n
        foreign key (product_id) references product (id);

alter table order_list
    add constraint FK82npqimwjgtf1xhx41agdjxi5
        foreign key (product_order_id) references product_order (order_id);

alter table product_order
    add constraint FKnsbnmfde3i9l8dwhywi3ypv4y
        foreign key (shipping_info_id) references shipping_info (id);