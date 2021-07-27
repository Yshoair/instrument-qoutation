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

CREATE OR REPLACE VIEW instrument_quotation.instruments_latest_quotes (isin, description, price, priced_on) AS
select i.isin, i.description, q.price, q.priced_on
from instrument_quotation.quotes q
         inner join (select instrument_isin, max(priced_on) as priced_on
                     from instrument_quotation.quotes
                     group by instrument_isin) lq
                    on lq.instrument_isin = q.instrument_isin and lq.priced_on = q.priced_on
         right join instrument_quotation.instruments i on i.isin = q.instrument_isin;