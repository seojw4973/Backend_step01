
<서브 쿼리를 사용하세요>


1) 관우보다 평점이 우수한 학생의 학번과 이름을 검색하세요
SELECT avr
 FROM student
 WHERE sname='관우';

SELECT sno, sname, avr
 FROM student
 WHERE avr>(SELECT avr
            FROM student
            WHERE sname='관우');

2) 관우와 동일한 학년 학생 중에 평점이 사마감과 동일한 학생을 검색하세요
SELECT syear
 FROM student
 WHERE sname='관우';

SELECT avr
 FROM student
 WHERE sname='사마감'; 

SELECT syear, sname, avr
 FROM student
 WHERE syear=(SELECT syear
              FROM student
              WHERE sname='관우') 
 AND avr=(SELECT avr
          FROM student
          WHERE sname='사마감');

3) 관우보다 일반 화학과목의 학점이 더 낮은 학생의 명단을 학점과 검색하세요
SELECT sno
 FROM student
 WHERE sname='관우';

SELECT cno
 FROM course
 WHERE cname='일반화학';

-- 관우의 일반화학 학점
SELECT grade
 FROM student s, score r, course c, scgrade
 WHERE s.sno = r.sno AND r.cno = c.cno AND
        result BETWEEN loscore AND hiscore
        AND cname='일반화학' AND sname='관우';

SELECT s.sname, grade
 FROM student s, score r, course c, scgrade
 WHERE s.sno = r.sno AND r.cno = c.cno AND
        result BETWEEN loscore AND hiscore
 AND grade>(SELECT grade
            FROM student s, score r, course c, scgrade
            WHERE s.sno = r.sno AND r.cno = c.cno AND
                result BETWEEN loscore AND hiscore
                AND cname='일반화학' AND sname='관우')
 AND cname='일반화학';


4) 인원수가 가장 많은 학과를 검색하세요
SELECT major 학과, COUNT(*) "학과 인원수"
 FROM student
 GROUP BY major;

SELECT MAX(COUNT(*))
 FROM student
 GROUP BY major;

SELECT major 학과, COUNT(*) "학과 인원수"
 FROM student
 GROUP BY major
 HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM student
                    GROUP BY major); 

5) 학생 중 기말고사 성적이 가장 낮은 학생의 정보를 검색하세요
SELECT sno, AVG(result)
 FROM score
 GROUP BY sno
 ORDER BY AVG(result);

SELECT MIN(AVG(result))
 FROM score
 GROUP BY sno;

SELECT MIN(AVG(result))
 FROM student s, score r, course c
 WHERE s.sno=r.sno AND r.cno=c.cno
 GROUP BY s.sno;

SELECT s.sno 학번, sname 이름, AVG(result) "기말고사 성적"
 FROM student s, score r, course c
 WHERE s.sno=r.sno AND r.cno=c.cno
 GROUP BY s.sno, sname
 HAVING AVG(result)=(SELECT MIN(AVG(result))
                    FROM student s, score r, course c
                    WHERE s.sno=r.sno AND r.cno=c.cno
                    GROUP BY r.sno);

SELECT s.sno 학번, sname 이름, AVG(result) "기말고사 성적"
 FROM student s, score r, course c
 WHERE s.sno=r.sno AND r.cno=c.cno
 GROUP BY s.sno, sname
 HAVING AVG(result)=(SELECT MIN(AVG(result))
                    FROM score
                    GROUP BY sno);

SELECT sno 학번, sname 이름, AVG(result) "기말고사 성적"
 FROM student
  JOIN score USING(sno)
 GROUP BY sno, sname
 HAVING AVG(result)=(SELECT MIN(AVG(result))
                    FROM score
                    GROUP BY sno);