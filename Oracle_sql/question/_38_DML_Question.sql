
--1) 모든 학생의 성적을 4.5만점 기준으로 수정하세요
SELECT avr FROM student;

UPDATE student SET avr=avr*4.5/4;

ROLLBACK;

SELECT avr FROM student;


--2) 모든 교수의 부임일자를 100일 앞으로 수정하세요
SELECT hiredate FROM professor;

UPDATE professor SET hiredate=hiredate-100;
SELECT hiredate FROM professor;
ROLLBACK;

--3) 화학과 2학년 학생의 정보를 삭제하세요
SELECT * FROM student WHERE major='화학' AND syear=2;

DELETE FROM student WHERE major='화학' AND syear=2;

--4) 조교수의 정보를 삭제하세요
SELECT * FROM professor WHERE orders='조교수';

DELETE FROM professor WHERE orders='조교수';

