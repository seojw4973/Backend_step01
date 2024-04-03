
--1) 성적순으로 학생의 이름을 검색하라
SELECT sname 이름, avr 평점
 FROM student
 ORDER BY avr DESC;

--2) 학과별 성적순으로 학생의 정보를 검색하라
SELECT * FROM student;

SELECT major 전공, sno 학번, sname 이름, sex 성별, syear 학년, avr 평점
 FROM student
 ORDER BY major, avr DESC;


--3) 학년별 성적순으로 학생의 정보를 검색하라
SELECT syear 학년, sno 학번, sname 이름, sex 성별, major 전공, avr 평점
 FROM student
 ORDER BY syear DESC, avr DESC;

--4) 학과별 학년별로 학생의 정보를 성적순으로 검색하라
SELECT major 전공, syear 학년, sno 학번, sname 이름, sex 성별,  avr 평점
 FROM student
 ORDER BY major, syear DESC, avr DESC;

--5) 학점순으로 과목 이름을 검색하라
SELECT * FROM course;

SELECT st_num 학점, cno 과목번호, cname 과목명
FROM course
ORDER BY st_num DESC;

--6) 각 학과별로 교수의 정보를 검색하라
SELECT * FROM professor;

SELECT section 학과, pno 교수번호, pname 이름, orders 직위, hiredate 부임일자
 FROM professor
 ORDER BY section;

--7) 지위별로 교수의 정보를 검색하라
SELECT orders 직위, section 학과, pno 교수번호, pname 이름, hiredate 부임일자
 FROM professor
 ORDER BY orders;


--8) 각 학과별로 교수의 정보를 부임일자 순으로 검색하라
SELECT section 학과, hiredate 부임일자, pno 교수번호, pname 이름, orders 직위
 FROM professor
 ORDER BY section, hiredate;


