ALTER TABLE student
        ADD CHECK  (age>=16),
        ALTER COLUMN name SET NOT NULL,
        ADD CONSTRAINT unique_name UNIQUE (name),
        ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (color, name);