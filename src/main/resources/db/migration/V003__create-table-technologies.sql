create table technologies(
    id bigint not null auto_increment,
    name varchar(60) not null,
    project_id bigint not null,

    primary key (id)
);

alter table technologies add constraint fk_technologies_project
foreign key (project_id) references project (id);