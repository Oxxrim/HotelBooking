create table room (
number integer not null,
breakfast_cost bigint not null,
category varchar(255), cleaning_cost bigint not null,
price bigint not null,
primary key (number)
);

create table usr (id integer not null, phone_number varchar(255), username varchar(255), primary key (id));

create table book (
id integer not null,
breakfast boolean,
cleaning boolean,
since varchar(255),
to varchar(255),
total_price bigint not null,
user_id integer not null,
room_number integer not null,
primary key (id)
);

alter table if exists book add constraint FKjfrx801wpfa7wrm6ajgj3yy4q foreign key (room_number) references room;

alter table if exists book add constraint FKs3pkb7fhff07sbf52ew13pt1k foreign key (user_id) references usr;
