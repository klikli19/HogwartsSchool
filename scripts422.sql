CREATE TABLE owners (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    have_license BOOLEAN NOT NULL ,
    cars_id int REFERENCES cars (id) );

CREATE TABLE cars (
    id SERIAL PRIMARY KEY ,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    price NUMERIC(9,2)
)