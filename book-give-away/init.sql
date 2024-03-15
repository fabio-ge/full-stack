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