create table cart (
    id uuid not null,
    user_id uuid,
    item_list jsonb,
    primary key (id));

create index cart_user_id on cart (user_id);