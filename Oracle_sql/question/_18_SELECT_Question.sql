

1) 학생중에 동명이인을 검색한다
SELECT DISTINCT s1.sno, s1.sname
 FROM student s1, student s2
 WHERE s1.sno!=s2.sno
 AND s1.sname = s2.sname; 

2) 전체 교수 명단과 교수가 담당하는 과목의 이름을 학과 순으로 검색한다
SELECT section, p.pno, pname, cname
 FROM professor p, course c
 WHERE p.pno = c.pno(+)     -- null 값도 표현하기 위해
 ORDER BY section;


3) 이번 학기 등록된 모드 과목과 담당 교수의 학점 순으로 검색한다
SELECT DISTINCT c.cno, cname, pname, st_num
 FROM course c, professor p
 WHERE c.pno = p.pno(+)
 ORDER BY st_num DESC;