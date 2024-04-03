
--1) '__학과인 __학생의 현재 평점은 __입니다' 형태로 학생의 정보를 출력하라
SELECT major || ' 학과인 ' || sname || ' 학생의 현재 평점은 ' || TO_CHAR(avr, 'FM9990.99') || '입니다' AS "학생 정보"
FROM student;

--2) '__과목은 __학점 과목입니다.' 형태로 과목의 정보를 출력하라
SELECT cname || ' 과목은 ' || st_num || ' 학점 과목입니다.' AS "과목 정보"
FROM course;

--3) '__교수는 __학과 소속입니다.' 형태로 교수의 정보를 출력하라
SELECT pname || ' 교수는 ' || section || ' 학과 소속입니다.' AS "교수 정보"
FROM professor;

--4) 학교에는 어떤 학과가 있는지 검색한다(학생 테이블 기반으로 검색한다)
SELECT DISTINCT major 학과
 FROM student;

--5) 학교에는 어떤 학과가 있는지 검색한다(교수 테이블 기반으로 검색한다)
SELECT DISTINCT section 학과
 FROM professor;

--6) 교수의 지위는 어떤 것들이 있는지 검색한다
SELECT DISTINCT orders 직위
 FROM professor;







