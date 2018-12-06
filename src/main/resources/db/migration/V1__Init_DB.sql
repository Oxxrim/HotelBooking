create table room (
number integer not null,
breakfast_cost bigint not null,
category varchar(255), cleaning_cost bigint not null,
price bigint not null, primary key (number)
);

create table usr (id integer not null, phone_number varchar(255), username varchar(255), primary key (id));