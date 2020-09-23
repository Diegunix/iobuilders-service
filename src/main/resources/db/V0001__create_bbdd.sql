CREATE TABLE public.useriobuilder (
    id bigserial NOT NULL,
    login varchar(255) not NULL,
    first_name varchar(255) not NULL,
    last_name varchar(255) not NULL,
    mail varchar(255) not NULL,
    active boolean not NULL DEFAULT true,
    created_date timestamp NOT NULL,
    updated_date timestamp NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_user_login UNIQUE (login));

    
CREATE TABLE public.payment (
    id bigserial NOT NULL,
    user_id int8 NOT NULL,
    created_date timestamp NOT NULL,
    transaction DOUBLE PRECISION not NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (id),
    CONSTRAINT payment_user_fk FOREIGN KEY (user_id) REFERENCES useriobuilder(id));
