alter table student add constraint age_constraint check (age > 10);
alter table student add constraint name unique (name);
alter table student alter column name set not null;
alter table faculty add constraint color_name unique(name, color);
alter table student alter age set default 11;