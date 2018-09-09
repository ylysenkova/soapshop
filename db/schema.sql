CREATE SCHEMA soapshop;

CREATE TABLE soapshop.users
(
  id  SERIAL PRIMARY KEY,
  password character varying(100),
  salt character varying(100),
  login character varying(100)
)
WITH (
OIDS=FALSE
);

CREATE TABLE soapshop.products
(
  id SERIAL PRIMARY KEY,
  name character varying(100),
  price double precision,
  image_ref character varying(500),
  date timestamp without time zone
)
WITH (
OIDS=FALSE
);

