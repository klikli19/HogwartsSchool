ALTER TABLE student
    ADD CONSTRAINT age CHECK ( age>=16 ),
    ADD CONSTRAINT name_unique UNIQUE (name),
    ALTER COLUMN age SET DEFAULT 20,
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);
