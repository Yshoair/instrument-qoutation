#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  CREATE DATABASE $APP_DB_NAME;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $APP_DB_USER
  BEGIN;
    CREATE TABLE IF NOT EXISTS event (
	  id CHAR(26) NOT NULL CHECK (CHAR_LENGTH(id) = 26) PRIMARY KEY,
	  aggregate_id CHAR(26) NOT NULL CHECK (CHAR_LENGTH(aggregate_id) = 26),
	  event_data JSON NOT NULL,
	  version INT,
	  UNIQUE(aggregate_id, version)
	);
	CREATE INDEX idx_event_aggregate_id ON event (aggregate_id);

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

  create or replace view instrument_quotation.instrument_candlesticks
            (isin, open_timestamp, open_price, high_price, low_price, close_price, close_timestamp) as
    select gq.instrument_isin,
       gq.open_timestamp,
       opq.price open_price,
       gq.high_price,
       gq.low_price,
       cpq.price close_price,
       gq.close_timestamp
    from (
         select date_trunc('minute', priced_on)                    open_timestamp,
                min(priced_on)                                     open_price_date,
                max(price)                                         high_price,
                min(price)                                         low_price,
                max(priced_on)                                     close_price_date,
                date_trunc('minute', priced_on) + Interval '1 min' close_timestamp,
                count(1),
                instrument_isin
         from instrument_quotation.quotes
         group by 1, instrument_isin
      ) gq
         inner join instrument_quotation.quotes opq
                    on gq.instrument_isin = opq.instrument_isin and gq.open_price_date = opq.priced_on
         inner join instrument_quotation.quotes cpq
                    on gq.instrument_isin = cpq.instrument_isin and gq.close_price_date = cpq.priced_on
    order by 1, 2;
  COMMIT;
EOSQL
