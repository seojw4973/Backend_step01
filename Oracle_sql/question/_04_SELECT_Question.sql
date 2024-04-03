--1) 각 학생의 평점을 검색하라(별명을 사용)
SELECT sno 학번, sname 이름, avr 평점
FROM student;

--2) 각 과목의 학점수를 검색하라(별명을 사용)
SELECT cno 과목번호, cname 과목명, st_num 학점
 FROM course;

--3) 각 교수의 지위를 검색하라(별명을 사용)
SELECT pno "교수 번호", pname 이름, orders 직위
 FROM professor;

--4) 급여를 10%인상했을 때 연간 지급되는 급여를 검색하라(별명을 사용)
SELECT eno 사번, ename 사원명, sal*1.1*12+NVL(comm,0) "10퍼 인상된 연간 급여"
 FROM emp;

--5) 현재 학생의 평균 평점은 4.0만점이다. 이를 4.5만점으로 환산해서 검색하라(별명을 사용)
SELECT sno 학번, sname 이름, TO_CHAR(avr*4.5/4, 'FM9990.99')  "4.5만점 기준 평점"
FROM student;



