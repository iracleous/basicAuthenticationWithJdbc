# Getting Started

### Reference Documentation

For further reference, please consider the following sections:




create table users(
username nvarchar(50) not null primary key,
password nvarchar(100) not null,
enabled bit  not null
);
create table authorities (
username nvarchar(50) not null,
authority nvarchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users(username,password,enabled)
values('admin','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',1);
insert into authorities(username,authority)
values('admin','ROLE_ADMIN');

insert into users(username,password,enabled)
values('user','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',1);
insert into authorities(username,authority)
values('user','ROLE_USER');


