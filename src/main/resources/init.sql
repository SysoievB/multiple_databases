create database if not exists my_sql_db;

create database if not exists my_sql_db_secondary;
GRANT ALL PRIVILEGES ON my_sql_db_secondary.* TO 'user'@'%';
FLUSH PRIVILEGES;