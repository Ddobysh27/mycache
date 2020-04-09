USE test;

CREATE TABLE cars
(
    id int(10) PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year int(4)
);

CREATE UNIQUE INDEX cars_title_uindex ON cars (title);

INSERT INTO cars (title, year)
VALUES
('BMW', 2010),
('Porsche', 2001),
('Volkswagen', 2018),
('Ford', 1957),
('Audi', 1988);