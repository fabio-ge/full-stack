create table book(
	id serial primary key,
	titolo varchar(255) not null,
	edizione varchar(255) not null,
	condizioni varchar(2000) not null
);

create table bundle(
	id serial primary key,
	nome varchar(1000)
);

create table bundle_book(
	bundle_id integer,
	book_id integer,
	CONSTRAINT fk_bundle FOREIGN KEY(bundle_id) REFERENCES bundle(id),
	constraint fk_book foreign key(book_id) references book(id)
);


CREATE TABLE users
(
    id serial primary key,
    username varchar(45) NOT NULL,
    password varchar(100) NOT NULL,
    enabled integer NOT NULL,
    CONSTRAINT users_unique_user UNIQUE (username)
);

CREATE TABLE authorities
(
    id serial primary key,
    username varchar(45) NOT NULL,
    authority varchar(45) NOT NULL,
	Constraint fk_username_user FOREIGN KEY(username) REFERENCES users(username)
);
