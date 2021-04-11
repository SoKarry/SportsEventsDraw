create sequence hibernate_sequence start 1 increment 1;

create table event(id int8 not null, name varchar(255) not null, user_id int8, primary key (id));

create table user_role (
    user_id int8 not null,
    roles varchar(255));
create table usr (
    id int8 not null,
    password varchar(255) not null,
    status boolean not null,
    username varchar(255) not null,
    primary key (id));
alter table if exists event
    add constraint event_usr_fk foreign key (user_id) references usr;
alter table if exists user_role
    add constraint user_role_usr_fk foreign key (user_id) references usr;