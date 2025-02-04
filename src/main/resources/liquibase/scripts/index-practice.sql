-- liquibase formatted sql

-- changeset  anikit:1
CREATE INDEX studentNameIndex ON student (name);

-- changeset anikit:2
CREATE INDEX facultyNameColorIndex ON faculty (name, color);