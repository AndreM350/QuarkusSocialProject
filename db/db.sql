CREATE DATABASE quarkussocial;

CREATE TABLE USERS (

    id bigint auto_increment not null primary key,
    name varchar(255) not null,
    age integer not null

);

CREATE TABLE POSTS (

    id bigint auto_increment not null primary key,
    text varchar(150) not null,
    dateTime timestamp,
    user_id bigint not null references USERS(id)
);