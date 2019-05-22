create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table lessons (lesson_id bigint not null, date datetime, description varchar(255), teacher_info varchar(255), title varchar(255), primary key (lesson_id)) engine=InnoDB;
create table roles (role_id integer not null, name varchar(255), primary key (role_id)) engine=InnoDB;
create table roles_users (role_role_id integer not null, users_id bigint not null, primary key (role_role_id, users_id)) engine=InnoDB;
create table students (id bigint not null, enabled bit, encrypted_password varchar(100) not null, field_of_study varchar(255), person_index varchar(255), name varchar(255), sur_name varchar(255), user_name varchar(255), year_of_studies integer, primary key (id)) engine=InnoDB;
create table students_roles (user_id bigint not null, roles_role_id integer not null, primary key (user_id, roles_role_id)) engine=InnoDB;
create table user_lesson (user_id bigint not null, lesson_id bigint not null, primary key (user_id, lesson_id)) engine=InnoDB;
alter table roles_users add constraint FK21at9hqvxapo19265mkpvybct foreign key (users_id) references students (id);
alter table roles_users add constraint FKsh5t497i4tny4nst9f4fgjww7 foreign key (role_role_id) references roles (role_id);
alter table students_roles add constraint FKghbfi5b85404qbhjbjsb7dt2c foreign key (roles_role_id) references roles (role_id);
alter table students_roles add constraint FKb368moxiar5h614a0d8olgwkt foreign key (user_id) references students (id);
alter table user_lesson add constraint FKlrmxnjbe47moc33q1u6dcdpqs foreign key (lesson_id) references lessons (lesson_id);
alter table user_lesson add constraint FKh421lxv8o9s1hmgpctpuuy93t foreign key (user_id) references students (id);
