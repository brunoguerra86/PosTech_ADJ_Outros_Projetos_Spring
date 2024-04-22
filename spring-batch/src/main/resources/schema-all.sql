DROP TABLE IF EXISTS person;

create table person
{
    id              serial,
    name_person     varchar(240),
    street_name     varchar(240),
    street_number   varchar(240),
    city            varchar(240),
    country         varchar(240),
    email           varchar(240),
    phone_number    varchar(240),
    create_date_time timestamp
}