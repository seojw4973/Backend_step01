-- [SQL문의 조건(WHERE절)]

--1) 사원중에 급여가 4000 이상인 사원의 명단
SELECT eno, ename, sal
 FROM emp;

SELECT eno, ename, sal
 FROM emp
 WHERE sal>=4000;

SELECT eno, ename, sal
 FROM emp
 WHERE sal>=4000 
 ORDER BY sal DESC;


--2) 이름이 '김연아'인 사원을 검색하세요 
SELECT eno, ename
 FROM emp
 WHERE ename='김연아';

--3) 10번 부서를 제외한 사원의 명단
-- dno는 VARCHAR2(2)인 문자열 자료형이다
-- 아래처럼 dno=10 을 하면 테이블의 모든 행의 dno값을
-- 정수로 형변환이 일어나서 비교하게 된다.
-- dno='10'은 자료형이 일치하므로 그냥 비교만 하게 된다
-- 기업 환경에서는 수천만/수억건의 테이블이 비일비재하므로
-- dno=10 처럼 비교하면 결과를 출력되나 속도가 매우 저하될 수 있다
-- 그러므로 동일한 자료형으로 비교해야 한다.

SELECT dno, eno, ename
 FROM emp
 WHERE dno!=10 
 ORDER BY dno;

SELECT dno, eno, ename
 FROM emp
 WHERE dno!='10' 
 ORDER BY dno;

--10번 부서 사원들의 명단
SELECT dno, eno, ename
 FROM emp
 WHERE dno='10';
 
--4) 연봉이 30000 이상인 사원의 이름을 검색하세요
-- WHRER 절에는 별명을 사용할 수 없음
SELECT eno, ename, sal*12+NVL(comm,0) 연봉
 FROM emp
 WHERE sal*12+NVL(comm,0) >= 30000
 ORDER BY 3 DESC;


--5) 보너스가 600 이하인 사원을 검색하세요
-- null값은 계산의 대상이 아니기 떄문에 자동 제외된다.
-- 하지만 null을 0으로 치환해서 계산하는 규정이 있다면 0으로 변경해서 계산한다.
SELECT eno, ename, comm 보너스
 FROM emp
 WHERE comm <= 600;

SELECT eno, ename, NVL(comm,0) 보너스
 FROM emp
 WHERE NVL(comm,0) <= 600;

--7) 입사일이 1996년 이후인 사원의 정보를 검색하세요

-- 세션이 연결되어있을 때 세션의 형식을 date-format를 이렇게 바꿔준다
ALTER SESSION SET NLS_DATE_FORMAT='YYYY/MM/DD';

ALTER SESSION SET NLS_DATE_FORMAT='YYYY/MM/DD-HH:MI:SS';

SELECT *
 FROM emp
 WHERE hdate > '1995/12/31';

SELECT *
 FROM emp
 WHERE hdate > '1995/12/31';

 --1995년 12월 31일 00시 00분 00초 이후

--8) 널값을 검색
-- IS NULL : 널 값을 검색
-- IS NOT NULL : 널이 아닌 값을 검색

--  보너스 컬럼이 널 값인 사원을 검색하세요
SELECT eno 사번, ename 사원명, comm 보너스
 FROM emp
 WHERE comm IS NULL;

SELECT eno 사번, ename 사원명, comm 보너스
 FROM emp
 WHERE comm IS NOT NULL
 ORDER BY comm DESC;  
















