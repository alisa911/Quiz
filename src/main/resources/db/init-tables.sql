drop table if exists questions cascade;
drop table if exists answers;

create table "questions"
(
    "id"             bigserial    not null,
    "question"       varchar(255) not null UNIQUE,
    constraint "questions_pk" primary key ("id")
);

create table "answers"
(
    "id"             bigserial    not null,
    "value"          varchar(255) not null,
    "question_id"    bigint,
    "is_true"        bool         not null,
    constraint "answers_pk" primary key ("id")
);

alter table "answers"
    add constraint "answers_fk1" foreign key ("question_id") references "questions" ("id");
