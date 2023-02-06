DROP TABLE IF EXISTS people;

CREATE TABLE people (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(21) NOT NULL,
    last_name VARCHAR(21) NOT NULL,
    date_of_birth TIMESTAMP NOT NULL);

