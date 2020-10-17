drop table if exists game_sessions cascade;
drop table if exists game_session_question cascade;
drop table if exists questions cascade;
drop table if exists answers cascade;


create table "game_sessions"
(
    "id"                  bigserial               not null,
    "start"               timestamp default now() not null,
    "score"               bigint,
    constraint "game_sessions_pk" primary key ("id")
);

create table "game_session_question"
(
    "id"                  bigserial               not null,
    "game_session_id"     bigint                  not null,
    "question_id"         bigint                  not null,
    "selected_answer_id"  bigint,
    constraint "game_session_question_pk" primary key ("id")
);


create table "questions"
(
    "id"                  bigserial               not null,
    "question"            varchar(255)            not null UNIQUE,
    constraint "questions_pk" primary key ("id")
);

create table "answers"
(
    "id"                   bigserial               not null,
    "value"                varchar(255)            not null,
    "question_id"          bigint,
    "is_true"              bool                    not null,
    constraint "answers_pk" primary key ("id")
);

alter table "answers"
    add constraint "answers_fk1" foreign key ("question_id") references "questions" ("id");
alter table "game_session_question"
    add constraint "game_session_question_fk1" foreign key ("game_session_id") references "game_sessions" ("id");
alter table "game_session_question"
    add constraint "game_session_question_fk2" foreign key ("question_id") references "questions" ("id");
alter table "game_session_question"
    add constraint "game_session_question_fk3" foreign key ("selected_answer_id") references "answers" ("id");