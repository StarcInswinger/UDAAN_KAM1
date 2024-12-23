CREATE DATABASE kam;

CREATE USER myuser WITH PASSWORD 'pass';

ALTER ROLE myuser SET client_encoding TO 'utf8';
ALTER ROLE myuser SET default_transaction_isolation TO 'read committed';
ALTER ROLE myuser SET timezone TO '+05:30';

GRANT ALL PRIVILEGES ON DATABASE kam TO myuser;

\c kam

