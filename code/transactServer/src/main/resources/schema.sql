create table transact (
    trans_id varchar(255) not null,
    cardNo varchar(255),
    description varchar(255),
    reqDt datetime(6),
    resCode enum ('CHARGEBACK','PURCHASE_ERROR','PURCHASE_SUCCESS','REJECT','SALE','SERVER_ERROR'),
    resDt datetime(6),
    serviceAmount decimal(38,2),
    serviceFee decimal(38,2),
    totalAmount decimal(38,2),
    transType enum ('BUY','REFUND'),
    primary key (trans_id)
) engine=InnoDB