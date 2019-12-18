declare
    userName varchar2(15);
    password varchar2(15);
    mail     varchar2(30);
begin
    for n in 1..100
        loop
            userName := 'kylu' || n;
            password := '1234' || n;
            mail := 'kylu' || n || '@kylu.com';
            insert into D_PLAYER (ROW_ID, PLAYER_ID, PLAYER_NAME, PASSWORD, MAIL, VALID_FROM, VALID_TO)
            values (D_PLAYER_ROW_ID.nextval, D_PLAYER_PLAYER_ID.nextval, userName, GET_HASH(userName, PASSWORD),
                    mail, SYSDATE, null);

            insert into D_SINGLE_PLAYER_GAME (ID, START_TIME, END_TIME)
            values (D_SINGLE_PLAYER_ID.nextval, sysdate, sysdate);

            insert into F_SINGLE_PLAYER_GAME (GAME_ID, PLAYER_ID, WORD_CREATED, SCORE)
            values (D_SINGLE_PLAYER_ID.currval, D_PLAYER_ROW_ID.currval, 'batman', 1000);
        end loop;
end;

declare
    userName varchar2(15);
    password varchar2(15);
    mail     varchar2(30);
begin
    for n in 101..150
        loop
            userName := 'kylu' || n;
            password := '1234' || n;
            mail := 'kylu' || n || '@kylu.com';
            insert into D_PLAYER (ROW_ID, PLAYER_ID, PLAYER_NAME, PASSWORD, MAIL, VALID_FROM, VALID_TO)
            values (D_PLAYER_ROW_ID.nextval, D_PLAYER_PLAYER_ID.nextval, userName, GET_HASH(userName, PASSWORD),
                    mail, SYSDATE, null);

            insert into D_SINGLE_PLAYER_GAME (ID, START_TIME, END_TIME)
            values (D_SINGLE_PLAYER_ID.nextval, sysdate, sysdate);

            insert into F_SINGLE_PLAYER_GAME (GAME_ID, PLAYER_ID, WORD_CREATED, SCORE)
            values (D_SINGLE_PLAYER_ID.currval, D_PLAYER_ROW_ID.currval, 'spiderman', 1000);
        end loop;
end;


insert into D_DAILY_CHALLENGE (ID, WORD, VALID_FROM, VALID_TO)
values (D_DAILY_CHALLENGE_ID.nextval, 'one', TRUNC(SYSDATE), TRUNC(SYSDATE + 1) - 1 / 86400);
insert into D_DAILY_CHALLENGE (ID, WORD, VALID_FROM, VALID_TO)
values (D_DAILY_CHALLENGE_ID.nextval, 'two', TRUNC(SYSDATE - 1), TRUNC(SYSDATE) - 1 / 86400);
insert into D_DAILY_CHALLENGE (ID, WORD, VALID_FROM, VALID_TO)
values (D_DAILY_CHALLENGE_ID.nextval, 'batman', TRUNC(SYSDATE - 2), TRUNC(SYSDATE - 1) - 1 / 86400);

commit;


