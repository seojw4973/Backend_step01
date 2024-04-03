
--[정렬]
--1) 사원을 이름을 급여순으로 검색하세요
  -- 내림차순 정렬 (ASC 오름차순)
-- 디폴트는 오름차순 ASC
SELECT eno, ename, sal FROM emp;

SELECT eno, ename, sal FROM emp ORDER BY sal ASC;

SELECT eno, ename, sal FROM emp ORDER BY sal DESC;


--2) 사원의 사번과 이름을 연봉 순으로 검색하세요
SELECT eno, ename, sal*12+NVL(comm,0) 연봉
 FROM emp;

-- 붙여준 별명을 이용해서 조건을 만들 수 있음
SELECT eno, ename, sal*12+NVL(comm,0) 연봉
 FROM emp ORDER BY 연봉 DESC;

-- 수식을 그대로 사용해도 됨
SELECT eno, ename, sal*12+NVL(comm,0) 연봉
 FROM emp ORDER BY sal*12+NVL(comm,0) DESC;

-- 3의 의미는 3번째 파라미터를 의미
SELECT eno, ename, sal*12+NVL(comm,0) 연봉
 FROM emp ORDER BY 3 DESC;

 
--[정렬을 이용한 묶음 검색]

--3) 업무별로 사원의 급여를 검색한다
SELECT job 업무, eno, ename, sal FROM emp;

SELECT job 업무, eno, ename, sal FROM emp ORDER BY 업무;

--4) 각 부서별로 사원의 급여를 검색하세요
--   단 급여를 많이 받는 사람부터 검색하세요
SELECT dno 부서번호, eno, ename, sal 급여 FROM emp ORDER BY dno;

SELECT dno 부서번호, eno, ename, sal 급여 FROM emp ORDER BY dno;

SELECT dno 부서번호, eno, ename, sal 급여 FROM emp ORDER BY dno, sal DESC;










