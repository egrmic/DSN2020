#root
create database dsn2020 default character set utf8;
grant select, insert, update, delete on dsn2020.osoba to 'dsn2020'@'localhost' identified by 'dsn2020'; 

use dsn2020;
create table osoba(
sifra int not null primary key auto_increment,
ime varchar(255) not null,
prezime varchar(255) not null
);


