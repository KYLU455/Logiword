drop sequence D_PLAYER_PLAYER_ID;
drop sequence D_PLAYER_ROW_ID;
drop sequence D_MULTIPLAYER_GAME_ID;
drop sequence D_DAILY_CHALLENGE_ID;
drop sequence D_SINGLE_PLAYER_ID;

drop table F_MULTIPLAYER_GAME_MOVE;
drop table F_MULTIPLAYER_GAME_RESULTS;
drop table F_DAILY_CHALLENGE_ATTEMPT;
drop table F_SINGLE_PLAYER_GAME;

drop table D_PLAYER;
drop table D_MULTIPLAYER_GAME;
drop table D_DAILY_CHALLENGE;
drop table D_SINGLE_PLAYER_GAME;

CREATE SEQUENCE D_PLAYER_ROW_ID
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NOMAXVALUE;

CREATE SEQUENCE D_PLAYER_PLAYER_ID
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NOMAXVALUE;

create table D_PLAYER(
    row_id number not null,
    player_id number not null unique,
    player_name varchar2(32) not null,
    mail varchar2(32) not null check (mail like '%@%'),
    valid_from date not null,
    valid_to date,
    constraint d_player_id_pk primary key (row_id)
);

CREATE SEQUENCE D_DAILY_CHALLENGE_ID
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NOMAXVALUE;

create table D_DAILY_CHALLENGE(
    id number not null,
    word varchar2(64) not null,
    valid_from date not null,
    valid_to date not null,
    constraint d_daily_challenge_id_pk primary key (id)
)pctfree 0;

create table F_DAILY_CHALLENGE_ATTEMPT(
    player_id number not null,
    challenge_id number not null,
    score_number number not null,
    is_successful char(1) check ( is_successful in ('Y', 'N')),
    constraint f_daily_challenge_fk_d_player foreign key (player_id) references D_PLAYER,
    constraint f_daily_challenge_fk_d_daily_challenge foreign key (challenge_id) references D_DAILY_CHALLENGE
)pctfree 0;

CREATE SEQUENCE D_MULTIPLAYER_GAME_ID
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NOMAXVALUE;

create table D_MULTIPLAYER_GAME(
    id number not null,
    is_friend_duel char(1) check ( is_friend_duel in ('Y', 'N')) not null,
    word varchar2(64) not null ,
    start_time date not null,
    end_time date,
    constraint d_mp_match_duel_id_pk primary key (id)
);

create table F_MULTIPLAYER_GAME_RESULTS(
    game_id number not null,
    winner_id number not null,
    looser_id number not null,
    winner_score number not null,
    looser_score number not null,
    constraint f_multiplayer_game_results_fk_d_multiplayer_game foreign key (game_id) references D_MULTIPLAYER_GAME,
    constraint f_multiplayer_game_results_fk_d_player_winner foreign key (winner_id) references D_PLAYER,
    constraint f_multiplayer_game_results_fk_d_player_looser foreign key (looser_id) references D_PLAYER
)pctfree 0;

create table F_MULTIPLAYER_GAME_MOVE(
    game_id number not null,
    player_id number not null,
    action_type char(30) check ( action_type in ('MATH OPERATION', 'LETTER CHOOSE', 'WORD SUBMISSION')) not null,
    score_change number not null,
    constraint f_multiplayer_game_move_fk_d_multiplayer_game foreign key (game_id) references D_MULTIPLAYER_GAME,
    constraint f_multiplayer_game_move_fk_d_player foreign key (player_id) references D_PLAYER
)pctfree 0;

CREATE SEQUENCE D_SINGLE_PLAYER_ID
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NOMAXVALUE;

create table D_SINGLE_PLAYER_GAME(
    id number not null,
    start_time date not null,
    end_time date,
    constraint d_single_player_game primary key (id)
);

create table F_SINGLE_PLAYER_GAME(
    game_id number not null,
    player_id number not null,
    word_created varchar2(64) not null ,
    is_valid_word char(1) check ( is_valid_word in ('Y', 'N')) not null,
    score number not null,
    constraint f_single_player_game_fk_d_single_player_game foreign key (game_id) references D_SINGLE_PLAYER_GAME,
    constraint f_single_player_game_fk_d_player foreign key (player_id) references D_PLAYER
)pctfree 0;

create or replace function get_hash (p_password  IN  VARCHAR2)
    RETURN VARCHAR2 AS
  BEGIN
    RETURN DBMS_CRYPTO.HASH(UTL_RAW.CAST_TO_RAW(p_password),DBMS_CRYPTO.HASH_SH1);
  END;

commit;
