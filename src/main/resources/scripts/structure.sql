create schema if not exists instrument_quotation;

create table instrument_quotation.instruments
(
    isin varchar(12) constraint instruments_pk primary key,
    description varchar(250) not null,
    added_on timestamp default current_timestamp not null
);

create sequence instrument_quotation.quotes_id_seq;
create table instrument_quotation.quotes
(
    id int default NEXTVAL('instrument_quotation.quotes_id_seq') constraint quotes_pk primary key,
    price decimal not null,
    priced_on timestamp default current_timestamp,
    instrument_isin varchar(12) constraint quotes_instruments_isin_fk references instrument_quotation.instruments
);

