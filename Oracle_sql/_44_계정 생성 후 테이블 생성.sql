DROP TABLE todo_user;

CREATE TABLE todo_user(
    uno NUMBER,
    uname VARCHAR2(100),
    addr VARCHAR2(1024),
    tel VARCHAR2(20)
);

DROP TABLE todo_matching;

CREATE TABLE todo_matching(
    uno NUMBER,
    pno Number
);

DROP TABLE todo_project;

CREATE TABLE todo_project(
    pno Number,
    pname VARCHAR2(100),
    sdate DATE,
    edate DATE,
    finished NUMBER(1)
);