create table project(

    id bigint not null auto_increment,
    name varchar(60) not null,
    user_id bigint not null,
    date_init datetime,
    date_final datetime,

    primary key(id)

);

alter table project add constraint fk_project_user 
foreign key (user_id) references user (id)