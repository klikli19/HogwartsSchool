-- liquibase formatted sql

-- changeset klikli:1
CREATE INDEX student_name_ind ON student (name);

-- changeset klikli:2
CREATE INDEX  faculty_name_and_color_ind ON faculty (name, color);