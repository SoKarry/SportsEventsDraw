create sequence hibernate_sequence start 1 increment 1;

create table event(id int8 not null, name varchar(255) not null, user_id int8, primary key (id), type_id int8, draw_id int8);

create table game (id int8 not null, event_id int8, player1_id int8, player2_id int8, primary key (id));
create table player (id int8 not null, plname varchar(255), primary key (id));
create table type (id int8 not null, name varchar(255), primary key (id));
create table draw (id int8 not null, name varchar(255), primary key (id));

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

alter table if exists game
    add constraint game_event_id_fk foreign key (event_id) references event;
alter table if exists game
    add constraint game_player1_id_fk foreign key (player1_id) references player;
alter table if exists game
    add constraint game_player2_id_fk foreign key (player2_id) references player;