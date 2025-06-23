CREATE TABLE user (
    id int primary key auto_increment,
    email varchar(255),
    username varchar(255),
    password varchar(255),
    createdAt timestamp
)