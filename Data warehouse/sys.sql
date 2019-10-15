alter session set "_ORACLE_SCRIPT"=true;  
create user admin identified by admin;
grant dba to admin;
grant execute on sys.dbms_crypto to admin;