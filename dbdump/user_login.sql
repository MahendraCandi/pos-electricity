create table user_login
(
    user_id   int      not null
        primary key,
    is_login  smallint null,
    timestamp datetime null
);
