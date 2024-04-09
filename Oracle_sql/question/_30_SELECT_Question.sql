
1) 3학년 학생의 학과별 평점 평균과 분산 및 편차를 검색하세요
SELECT major,
       AVG(avr) "학과별 평점 평균",
       VARIANCE(avr) "학과별 평점 분산",
       STDDEV(avr) "학과별 평점 표준편차"
 FROM student
 WHERE syear=3
 GROUP BY major;


2) 화학과 학년별 평균 평점을 검색하세요
SELECT syear 학년, AVG(avr) "화학과 학년별 평균"
 FROM student
 WHERE major='화학'
 GROUP BY syear
 ORDER BY syear;


3) 각 학생별 기말고사 평균을 검색하세요
SELECT sname 이름, ROUND(AVG(result)) "기말고사 평균"
 FROM student
  JOIN score USING(sno)  
 GROUP BY sname;

4) 각 학과별 학생 수를 검색하세요
SELECT major 학과,
      COUNT(*) "학과별 학생 수"
 FROM student
 GROUP BY major;

5) 화학과와 생물학과 학생 4.5 환산 평점의 평균을 각각 검색하세요
SELECT major 학과, TO_CHAR(AVG(avr*4.5/4), '9.999') "4.5 환산 평점 평균"
 FROM student
 WHERE major IN('화학', '생물')
 GROUP BY major;

6) 부임일이 10년 이상 된 직급별(정교수, 조교수, 부교수) 교수의 수를 
   검색하세요
SELECT orders 직급, COUNT(orders) "직급별 교수의 수"
 FROM professor
 WHERE TRUNC(MONTHS_BETWEEN(sysdate, hiredate))>=120
 GROUP BY orders;

7) 과목명에 화학이 포함된 과목의 학점수 총합을 검색하세요
SELECT SUM(st_num) "학점수 총합"
 FROM course
 WHERE cname LIKE '%화학%';

8) 화학과 학생들의 기말고사 성적을 성적순으로 검색하세요
SELECT major 전공, sname 이름, AVG(result) "기말고사 성적"
 FROM score
  JOIN student s USING(sno)
  WHERE major='화학'
  GROUP BY major, sname
  ORDER BY AVG(result) DESC;


9) 학과별 기말고사 평균을 성적순으로 검색하세요
SELECT major 학과, AVG(result) "기말고사 평균"
 FROM student
 JOIN score USING(sno)
 GROUP BY major;