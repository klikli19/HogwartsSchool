ALTER TABLE student
    ADD CONSTRAINT age CHECK ( age>=16 ),
    ADD CONSTRAINT name_unique UNIQUE (name),
    ADD CONSTRAINT name CHECK ( length(name) >=2 AND  length(name) <= 100),
    ALTER COLUMN age SET DEFAULT 20;


ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);
