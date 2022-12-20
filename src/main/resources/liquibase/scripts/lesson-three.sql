-- liquibase formatted sql

-- changeset belous:1

CREATE INDEX idx_students_name ON student (name);

-- changeset belous:2

CREATE UNIQUE INDEX idx_faculties_name_color ON faculty (name, color);