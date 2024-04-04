-- [관계 연산자 : AND, OR]

--1) 20번 부서원 중에 급여가 2000이상인 사원을 검색하세요
SELECT * FROM emp;

SELECT * FROM emp WHERE dno='20' AND sal>=2000;

SELECT dno, sal, eno, ename FROM emp WHERE dno='20' AND sal>=2000;

--2) 30번 부서원 중에 급여가 2000이상이고 개발 업무를 담당하는
--  사원을 검색하세요
SELECT dno, eno, ename, sal, job
 FROM emp 
 WHERE dno='30';

SELECT dno, eno, ename, sal, job
 FROM emp 
 WHERE sal>=2000 
 ORDER BY sal DESC;

SELECT dno, eno, ename, sal, job
 FROM emp 
 WHERE job='개발';

SELECT dno, sal, job, eno, ename
 FROM emp 
 WHERE dno='30' AND sal>=2000 AND job='개발' 
 ORDER BY sal DESC;


--3) 관계 연산자 우선순위
-- NOT > AND > OR

--10번 부서이거나 월급이 1600초과하는 사원중에
--보너스가 600을 초과하는 사원
--=> 실제로는
--10번 부서이거나 또는 월급이 1600초과하면서 보너스가 600초과하는 사원
--=> 문맥의 구분을 정확하게 하지 않으면 잘못된 결과가 나오기 쉽다.

--10번 부서이거나, 월급이 1600초과하는 사원중에 보너스가 600을 초과하는 사원
--10번 부서이거나 월급이 1600초과하는 사원중에, 보너스가 600을 초과하는 사원

-- 1. AND가 먼저 연산되고 OR이 연산됨
SELECT *
 FROM emp
 WHERE dno='10' 
 OR sal>1600 
 AND comm>600;

-- 2. OR이 먼저 연산되도록 괄호로 묶고, AND 연산
SELECT *
 FROM emp
 WHERE (dno='10' 
 OR sal>1600) 
 AND comm>600;

-- 3. 1번과 동일
SELECT *
 FROM emp
 WHERE dno='10' 
 OR (sal>1600 
 AND comm>600);


--[LIKE 연산자]
-- ; a. 문자열 검색시 사용
--   b. '_' : 반드시 한개의 문자를 대체
--      '%' : 문자열을 대체(문자가 없는 경우도 포함)
--   c.
--     LIKE '경%' => '경'으로 시작하는 모든 문자열
--                 경, 경제, 경영학과
--     LIKE '%과' => '과'로 끝나는 모든 문자열
--                 과, 다과, 화학과, 물리학과
--     LIKE '%김%' => '김'이란 글자가 들어간 문자열
--                 김, 김씨, 돌김, 되새김질
--     LIKE '화_' => '화'로 시작하는 두 글자 단어
--                 화학, 화약, 화장
--     LIKE '__화' => '화'로 끝나는 세글자 단어
--                 무궁화, 해당화, 운동화, 들국화
--     LIKE '_동_' => '동'이 가운데 들어간 세글자 단어
--                 원동기, 전동차

--4) 김씨 성을 가진 사원을 검색하세요
SELECT *
 FROM emp
 WHERE ename LIKE '김%';

--아래는 김퍼센트를 찾으므로 잘못되었다.
SELECT *
 FROM emp
 WHERE ename ='김%';

--5) 이름이 '하늘'인 사원
SELECT *
 FROM emp
 WHERE ename LIKE '%하늘';

-- 6) 성과 이름이 각각 한 글자인 사원을 검색하세요
SELECT *
 FROM emp
 WHERE ename LIKE '__';

-- 7) 성+이름이 3글자인 학생을 검색
SELECT *
 FROM student
 WHERE sname LIKE '___';
  
-- 8) 성과 이름이 각각 한 글자인 학생을 검색하세요
SELECT *
 FROM student
 WHERE sname LIKE '__';
  
-- 9) 성이 '사마'인 학생을 검색하세요
SELECT *
 FROM student
 WHERE sname LIKE '사마%';