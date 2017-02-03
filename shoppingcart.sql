create database shoppingcart;

create table user (
	id int primary key auto_increment,
	username varchar(15) not null unique,
	password varchar(15) not null
);

create table product (
	id int primary key auto_increment,
	name varchar(50) not null,
	price double not null
);

create table wishlist (
	id int primary key auto_increment,
	name varchar(15) default 'new list' not null,
	description varchar(50) default null,
	uid int not null,
	constraint fk_userwish foreign key (uid) references user(id) on delete cascade
);

create table wishprod (
	wid int not null,
	pid int not null,
	primary key (wid, pid),
	constraint fk_wlist_wprod foreign key (wid) references wishlist(id) on delete cascade,
	constraint fk_prod_wprod foreign key (pid) references product(id) on delete cascade
);

insert into user (username, password) values ("admin", "admin");
insert into user (username, password) values ("Rui", "password");

insert into product (name, price) values ("iPhone6s", 500);
insert into product (name, price) values ("iPhone6s Plus", 600);
insert into product (name, price) values ("iPhone7", 700);
insert into product (name, price) values ("iPhone7 Plus", 800);
insert into product (name, price) values ("iPad Air 2", 500);
insert into product (name, price) values ("iPad Mini 2", 400);
insert into product (name, price) values ("iPad Pro 9.7", 600);
insert into product (name, price) values ("iPad Pro 12.9", 700);
