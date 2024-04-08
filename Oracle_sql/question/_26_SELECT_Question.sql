

<단일 행 함수를 이용하세요>

1) 교수들이 부임한 달에 근무한 일수는 몇 일인지 검색하세요
SELECT pno, pname, hiredate, LAST_DAY(TRUNC(hiredate))-TRUNC(hiredate) "부임달 근무일수"
 FROM professor;

2) 교수들의 오늘까지 근무한 주가 몇 주인지 검색하세요
SELECT pname,ROUND((TRUNC(sysdate, 'DD') - TRUNC(hiredate, 'DD') +1)/7) "오늘까지 근무한 주"
 FROM professor;

3) 1991년에서 1995년 사이에 부임한 교수를 검색하세요
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY/MM/DD HH24:MI/SS';

SELECT *
 FROM professor
 WHERE TRUNC(hiredate) BETWEEN '1991/01/01 00:00:00' AND '1995/12/31 23:59:59';

4) 학생들의 4.5 환산 평점을 검색하세요(단 소수 이하 둘째자리까지)
SELECT sno, sname, ROUND(avr/4.0*4.5, 2) "4.5 환산 평점"
 FROM student;

