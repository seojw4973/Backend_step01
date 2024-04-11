
1) 화학과 학생과 평점이 동일한 학생들을 검색하세요
SELECT avr
 FROM student
 WHERE major='화학';

SELECT *
 FROM student
 WHERE avr IN (SELECT avr
                FROM student
                WHERE major='화학');

2) 화학과 교수와 부임일이 같은 직원을 검색하세요
ALTER SESSION SET NLS_DATE_FORMAT='MM/DD';

SELECT hiredate
 FROM professor
 WHERE section='화학';

SELECT *
 FROM emp
 WHERE hdate IN (SELECT hiredate
                    FROM professor
                    WHERE section='화학');

3) 화학과 학생과 같은 학년에서 평점이 동일한 학생들을 검색하세요
SELECT avr
 FROM student
 WHERE major='화학';

SELECT syear
 FROM student
 WHERE major='화학';

SELECT *
 FROM student
 WHERE (avr, syear) IN (SELECT avr, syear
                        FROM student
                        WHERE major='화학')
 AND major!='화학'; 