--SYSTEM 계정으로 처리하는 부분 START
-- 현재 bituser계정이 있으면 삭제하라
-- 만약 없으면 Error 발생, 무시해도 된다.
-- [Account가 ncp08이고, 비밀번호가 ncp08인 새로운 사용자 생성]

-- 사용자를 생성하는 권한은 system으로 접속해야 가능하다.
-- system/oracle: 초기비밀번호, 당연히 가능하다

-- 기존에 ncp08 사용자가 존재하면 제거해주세요.
DROP USER ncp08 CASCADE;

-- 새로운 사용자인 ncp08을 생성하고, 비밀번호도 ncp08로 설정하고 기본 스키마 리소스 사용권한을 부여
CREATE USER ncp08 IDENTIFIED BY ncp08 DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp PROFILE DEFAULT;

-- 권한을 부여한다. 연결, 리소스 관리
GRANT CONNECT, RESOURCE TO ncp08 ;

-- ncp08한테 권한을 부여한다. 뷰생성(가상 테이블) 권한, SYNONYM권한 부여
GRANT CREATE VIEW, CREATE SYNONYM TO ncp08;

-- ncp08계정으로 연결하기 전에 현재 계정의 Lock을 해제한다.
ALTER USER ncp08 ACCOUNT UNLOCK;

--------------------------------------------------------------------------------------------------

--ncp08/ ncp08 로 접속한 후에 테이블을 생성한다.
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

--bituser계정으로 실행한다.
--client 테이블을 삭제하라


--client 테이블을 생성하라


--server 테이블을 삭제하라


--server 테이블을 생성하라




