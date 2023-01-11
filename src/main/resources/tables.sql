CREATE TABLE users (
    id bigint not null auto_increment,
    username varchar(50),
    password varchar(70),
    mobileNumber varchar(10),
    email varchar(50),
    role varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE order_invoice (
    ref_id bigint not null auto_increment,
    order_number bigint unique,
    showroom varchar(100),
    item_name varchar(200),
    gross_weight float,
    total_stones int,
    stone_weight float,
    net_weight float,
    gold_92_per float,
    gold_12_per float,
    delivery_date dateTime,
    making_charges double,
    PRIMARY KEY (ref_id)
);

CREATE TABLE show_rooms (
    id bigint not null auto_increment,
    showroom_name varchar(100),
    contact_name varchar(100),
    mobile_number varchar(10),
    address varchar(300),
    PRIMARY KEY (id)
);