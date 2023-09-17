create table transact.transact (
    transid bigint not null auto_increment,
    order_list_id bigint not null,
    total_amount integer,
    pay_method tinyint,
    orderer_id bigint,
    type tinyint,
    primary key (transid)
) engine=InnoDB;