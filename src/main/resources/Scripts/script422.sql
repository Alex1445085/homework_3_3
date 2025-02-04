create table car (id serial primary key, carBrand text, carModel text, carPrice integer);
alter table car alter column carBrand set not null,
                alter column carModel set not null,
                add constraint carPrice_constraint check (carPrice > 0);

create table menAndCar (id serial,
                        name text,
                        age integer,
                        driverLicense boolean,
                        carId integer references car (id));
alter table menAndCar add constraint age_constraint check (age > 0);
alter table menAndCar alter column name set not null;
alter table menAndCar add constraint carId unique(carId);