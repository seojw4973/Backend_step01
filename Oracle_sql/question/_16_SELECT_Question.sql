
-- 1) 송강 교수가 강의하는 과목을 검색한다
SELECT professor.pno, course.pno, pname, cname
 FROM professor, course
 WHERE professor.pno=course.pno AND professor.pno='1001';

-- SELECT p.pno, pname, cno, cname
-- FROM professor p, course c
-- WHERE p.pno=c.pno
-- AND pname='송강';

-- 2) 화학 관련 과목을 강의하는 교수의 명단을 검색한다
SELECT professor.pno, course.pno, pname, cname
 FROM professor, course
 WHERE professor.pno=course.pno AND cname LIKE '%화학%';

-- 3) 학점이 2학점인 과목과 이를 강의하는 교수를 검색한다
SELECT cname, pname, st_num
 FROM professor, course
 WHERE professor.pno=course.pno AND st_num=2;

-- 4) 화학과 교수가 강의하는 과목을 검색한다
SELECT pname, section, cname
 FROM professor, course
 WHERE professor.pno=course.pno AND section='화학';

-- 5) 화학과 1학년 학생의 기말고사 성적을 검색한다
SELECT syear, major, student.sno, score.sno, sname, cname, result
 FROM student, score, course
 WHERE student.sno=score.sno AND course.cno=score.cno AND major='화학' AND syear=1;

-- 6) 일반화학 과목의 기말고사 점수를 검색한다
SELECT course.cno, score.cno, cname, result
 FROM course, score
 WHERE course.cno = score.cno AND cname = '일반화학';

-- 7) 화학과 1학년 학생의 일반화학 기말고사 점수를 검색한다
SELECT major, syear, sname, cname, result
 FROM course, score, student
 WHERE course.cno = score.cno 
 AND student.sno = score.sno 
 AND cname = '일반화학' 
 AND syear=1
 AND major='화학';

-- 8) 화학과 1학년 학생이 수강하는 과목을 검색한다
SELECT major, syear, sname, cname
 FROM course, score, student
 WHERE course.cno = score.cno AND student.sno = score.sno AND major = '화학' AND syear=1;

-- SELECT DISTINCT cname
-- FROM student s, course c, score r
-- WHERE s.sno=r.sno AND c.cno=r.cno
-- AND major='화학' AND syear=1; 

-- 9) 유기화학 과목의 평가점수가 F인 학생의 명단을 검색한다
SELECT cname, major, sname, result, grade
 FROM course, score, student, scgrade
 WHERE course.cno = score.cno AND student.sno = score.sno
 AND result BETWEEN loscore AND hiscore
 AND grade='F' AND cname='유기화학';

