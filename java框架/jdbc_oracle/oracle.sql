--创建表空间
create tablespace itcast
datafile 'f:\itcast.dbf'
size 100m
autoextend on
next 10m;

--删除表空间
drop tablespace itcast;

--创建用户
create user itcast
identified by itcast
default tablespace itcast;

--给用户授权
--oracle数据库中常用角色
connect--连接角色
resource--开发者角色
dba--超级管理员角色

--给itcast用户授权dba角色
grant dba to itcast;

--创建一个person表
create table person(
       pid number(20),
       pname varchar2(10)
);

--修改表结构
---添加一列
alter table person add (gender number(1), money number(6,2));

---修改列类型
alter table person modify gender char(1);

---修改列名称
alter table person rename column gender to sex;

---删除一列
alter table person drop column sex;

--添加一条记录
insert into person(pid,pname,money) values(1,'zz',99.3);
commit;

---查询表中的记录
select *from person;

--修改一条记录
update person set pname = '李四' where pid = 1;
commit;

---三个删除
--删除表中的全部数据
delete from person;

--删除表结构
drop table person;

--先删除表，再创建表
--在数据量大的情况下，尤其在表中带索引的情况下，该操作效率高
--索引可以提供查询效率，但是会影响增删改的效率
truncate table person;

--序列：默认从1开始，依次递增，主要是给主键赋值。
---序列不真真的属于任何一张表，但是可以逻辑和表做绑定
---dual:虚表，只是为了补全语法，没有任何意义
create sequence s_person;
select s_person.currval from dual;

---添加语句
insert into person(pid,pname,money) values(s_person.nextval,'zz',99.3);
commit;
select *from person;

---scott用户，密码tiger
--解锁scott用户
alter user scott account unlock;
--解锁scott用户的密码
alter user scott identified by tiger;

--单行函数：作用于一行，返回一个值
---字符函数
select upper('yes') from dual;
select lower('YES') from dual;

---数值函数
select round(26.18,1) from dual; --四舍五入，后面的参数表示保留的小数位
select trunc(26.18,1) from dual; --直接截取，不在看后面数值是否大于5
select mod(10,3) from dual;--取余数

----日期函数
----查询emp表中所有员工入职距离现在几天
select sysdate-e.hiredate from emp e;
----算出明天此时刻
select sysdate+1 from dual;
----查询emp表中所有员工入职距离现在几月
select months_between(sysdate,e.hiredate) from emp e;
----查询emp表中所有员工入职距离现在几年
select months_between(sysdate,e.hiredate)/12 from emp e;
----查询emp表中所有员工入职距离现在几周
select round((sysdate-e.hiredate)/7) from emp e;

----转换函数,日期转字符串
select to_char(sysdate,'yyyy-mm-dd hh24-mi-ss') from dual;
--字符串转日期
select to_date('2020-11-06 15-45-27','yyyy-mm-dd hh24-mi-ss') from dual;

----通用函数
--算出emp表中的所有员工的年薪
select  e.sal*12+nvl(e.comm,0) from emp e;

----条件表达式
----条件表达式的通用写法
---给emp表中起中文名
select e.ename,
       case e.ename
          when 'SMITH' then '曹贼'
            when 'ALLEN' then '大耳贼'
              when 'WARD' then '碧眼儿'
                else '诸葛村夫'  --去掉else 其他的都是null
                  end
                 
from emp e;

---判断表中的员工工资，如果高于3000显示高收入，如果在1500和3000之间显示中等，其他显示低收入
select e.sal,
       case
          when  e.sal>3000 then '高收入'
            when e.sal>1500 then '中等收入'
                else '低收入'  
                  end              
from emp e;
-----reacle中除了起别名，都用单引号
----oracle专用表达式
select e.ename,
      decode( e.ename,
           'SMITH',  '曹贼',
             'ALLEN',  '大耳贼',
               'WARD',  '碧眼儿',
                 '诸葛村夫'  --去掉else 其他的都是null
                  )中文名
                 
from emp e;

select e.ename from emp e;





--多行函数：作用于多行，返回一个值
select count(1) from emp;--查询总记录数
select sum(sal) from emp;--工资总和
select max(sal) from emp;--最大工资
select min(sal) from emp;--最小工资
select round(avg(sal)) from emp;--工资平均值

---分组查询
--查询出每个部门的平均工资
--分组查询中，出现在group by后面的原始列，才能出现，没有出现在group by 后面的列，想在select后面出现，必须加上聚合函数
--聚合函数，可以把多行记录变成一个值
select  e.deptno,avg(e.sal),count(1)
from emp e
group by e.deptno;

--查询出每个部门的平均工资高于两千的
select  e.deptno 部门,avg(e.sal) 平均工资,count(1) 人数
from emp e
group by e.deptno 
having avg(e.sal)>2000;

--查询出每个部门工作高于800的员工的平均工资
select  e.deptno 部门,avg(e.sal) 平均工资,count(1) 人数
from emp e
where  e.sal>800
group by e.deptno 
having avg(e.sal)>2000;
----where是过滤分组前的数据，having是过滤分组后的数据

--查询出每个部门工作高于800的员工的平均工资，再查询出平均工资高于2000的部门
select  e.deptno 部门,avg(e.sal) 平均工资,count(1) 人数
from emp e
where  e.sal>800
group by e.deptno 
having avg(e.sal)>2000;

---查询出所有部门和部门下的所有员工信息
select *
from dept d
left join
emp e
on 
e.deptno =d.deptno

---oracle专用外连接
select * 
from emp e,dept d
where e.deptno(+) = d.deptno; 

select *from emp

---查询出员工姓名，员工领导姓名
select e1.empno,e1.ename,e2.ename,e2.empno
from emp e1,emp e2
where
e1.mgr=e2.empno

----查询出员工姓名，员工部门名，员工领导姓名，员工领导部门
select e1.empno,e1.ename,d1.dname,e2.ename,e2.empno,d2.dname
from emp e1,emp e2,dept d1,dept d2
where
e1.mgr=e2.empno 
and e1.deptno=d1.deptno 
and e2.deptno=d2.deptno

----查询出员工姓名，员工工资等级，员工部门名，员工领导姓名，领导工资等级，员工领导部门
select e1.empno,e1.ename,s1.grade,d1.dname,e2.ename,s2.grade,e2.empno,d2.dname
from emp e1,emp e2,dept d1,dept d2,salgrade s1,salgrade s2
where
e1.mgr=e2.empno 
and e1.deptno=d1.deptno 
and e2.deptno=d2.deptno
and e1.sal>=s1.losal and e1.sal<=s1.hisal
and e2.sal>=s2.losal and e2.sal<=s2.hisal

----子查询
---子查询返回一个值
--查询出工资和scott一样的员工
select * from emp e 
where 
e.sal
in
(select sal from emp e where e.ename='SCOTT');


---子查询返回一个集合
--查询出工资和10号部门任意员工一样的员工信息
select *from emp where sal 
in
(select sal from emp where deptno =10);

---子查询返回一张表
--查询出每个部门最低工资，和最低工资的姓名和该员工所在部门名称
select min(e.sal) from emp e group by e.deptno;

select 
e.sal,d.dname,e.ename
from 
emp e,dept d,(select e.deptno deptno ,min(e.sal) msal from emp e group by e.deptno) t
where
    e.sal = t.msal 
and e.deptno = d.deptno
and t.deptno = d.deptno

----oracle中的分页
---rownum行号：当我们做select操作的时候，
---每查询出一行记录，就会在改行上加一个行号 
---行号从1开始，依次递增，不能跳着走
----emp表工资倒序排列后，每页5条记录，查询第二页
----排序操作会影响rownum的顺序
select rownum, e.* from emp e order by sal Desc;

--如果涉及到排序，但是还是要使用rownum的话，我们可以再次嵌套查询
select rownum ,t.*from(
select e.* from emp e order by sal Desc) t;
 
select rownum , t.* from(
select e.* from emp e order by sal Desc) t where rownum <11 ;

select *from
(      select rownum rn, t.* from(
              select e.* from emp e order by sal Desc) t 
where rownum <11) t1
where t1.rn>5;


----视图
---视图的概念：视图就是一个查询的窗口，所有数据来自于原表

---查询语句创建表
create table emp as select *from scott.emp;
select * from emp;

create view v_emp as select ename,job from emp;
---查询视图
select * from v_emp;
---修改视图(不推荐)
update v_emp set job='clear' where ename='ALLEN';
commit;
---创建只读视图
create view v_emp1 as select ename,job from emp with read only;
select * from v_emp1;

---视图的作用？
---第一：视图可以屏蔽一些敏感字段。
---第二：保证总部和分部数据及时同一

----索引
---索引的概念：索引就是在表的列上构建一个二叉树，达到大幅度提高
---查询效率的目的，但是索引会影响增删改的效率
---单列索引
--创建单列索引
create index idx_ename on emp(ename);
---单列索引触发规则，条件必须是索引列中的原始值 
---单行函数，模糊查询都有影响索引的触发
select *from emp where ename='SCOTT';

---复合索引
--创建复合索引
create index idx_enamejob on emp(ename,job);
--复合索引中第一列为优先检索引
---如果要触发复合索引，必须包含优先检索引中的原始值
select *from emp where ename='SCOTT' and job ='xx';--复合索引
select *from emp where ename='SCOTT' or job ='xx';--不触发索引
select *from emp where ename='SCOTT';--触发单列索引


-----pl/sql编程语言
---pl/sql编程语言是对sql语言的拓展，是sql语言具有过程化编程的特性。
---pl/sql编程语言比一般的过程化编程语言更加灵活高效，
---pl/sql编程语言主要用来编写存储过程和存储函数等；

---声明方法
--赋值操作可以使用:= 也可以使用into查询语句赋值
declare
  i  number(4,2) := 10;
  s varchar2(10) := '都没戏了';
  ena emp.ename%type;--引用型变量
  emprow emp%rowtype;--记录型变量
begin
  dbms_output.put_line(i);
  dbms_output.put_line(s);
  select ename into ena from emp where empno=7788;
  dbms_output.put_line(ena);
  select * into emprow from emp where empno=7788;
  dbms_output.put_line(emprow.ename|| '工作为：'  || emprow.job || '部门编号为：' || emprow.deptno);
end;

---pl/sql中的if判断
---输入小于18的数字，输出未成年
---输入大于18小于40的数字，输出中成年
---输入大于40的数字，输出老成年
declare
  i number(3) := &i;
begin
  if i<18 then
    dbms_output.put_line('未成年');
    elsif i< 40 then
       dbms_output.put_line('中成年');
      else
        dbms_output.put_line('老年人');
      end if;
end;

----pl/sql中的loop循环
---用三种方式输出1到10的数字
--while循环
declare
 i number(2) :=1;
begin
   while i<11 loop
     dbms_output.put_line(i);
     i :=i+1;
     end loop; 
end;

--exit循环
declare
 i number(2) :=1;
begin
  loop
    exit when i>10;
     dbms_output.put_line(i);
     i :=i+1;
     end loop; 
end;

--for循环
declare

begin
  for i in 1..10 loop
     dbms_output.put_line(i);
    end loop;
end;

----游标：类似java的集合，可以存放多个对象，多行记录
---输出emp表中所用员工的姓名
declare
    cursor cl is select *from emp;
    emprow emp%rowtype;
begin
  open cl;
       loop
         fetch cl into emprow;
           exit when cl%notfound;
                dbms_output.put_line(emprow.ename);
         end loop;
  close cl;
end;

----给指定部门员工涨工资
declare
  cursor c2(enp emp.deptno%type) 
    is select empno from emp where deptno = enp;
    en emp.empno%type;
begin
  open c2(10);
      loop
        fetch c2 into en ;
        exit when c2%notfound;
            update emp set sal=sal+100 where empno=en;
            commit;
      end loop;
  close c2;
end;
 
select *from emp where deptno=10;

----存储过程
----存储过程：存储过程就是提前已经编译好的一段pl/sql语言，放置在数据库，可以直接被调用
----这一段pl/sql一般都是固定步骤的业务。
---给指定员工涨100块钱
create or replace procedure p1(enp emp.empno%type)
is

begin
  update emp set sal=sal+100 where empno=enp;
  commit;
end;

select *from emp where empno=7788;
----测试p1
declare

begin
  p1(7788);
end;

----通过存储函数实现计算，指定员工的的年薪
----存储过程和存储函数的参数不能带长度
----存储函数的返回值类型不能带长度
create or replace function f_yearsal(enp emp.empno%type) return number
is
  s number(10);
begin
  select sal*12+nvl(emp.comm,0) into s from emp where empno=enp;
  return s;
end; 

---测试f_yearsla
---存储函数在调用是，返回值需要接收
declare
 s number(10);
begin
  s:=f_yearsal(7788);
  dbms_output.put_line(s);
end;

----out类型参数然后使用
create or replace procedure p_yearsal(enp emp.empno%type,yearsal out number) 
is
  s number(10);
  c emp.comm%type;
begin
    select sal*12,nvl(comm,0) into s,c from emp where empno=enp;
    yearsal := s+c;
end;

---测试p_yearsla
---存储函数在调用是，返回值需要接收
declare
 yearsal number(10);
begin
  p_yearsal(7788,yearsal);
  dbms_output.put_line(yearsal);
end;

--in和out类型参数的区别是什么？
--凡是涉及到into查询语句赋值或者：=赋值操作的参数，都必须使用out来修饰，其余用in


----存储函数和存储过程的区别
---1.关键字不一样
---2.存储函数有返回值，存储过程没有返回值，存储函数比存储过程多了两个return
create table dept as  select * from scott.dept;


create or replace function fdna(dno dept.deptno%type) return dept.dname%type
is
   dna dept.dname%type;
begin
  select dname into dna from dept where deptno=dno;
  return dna;
end;

--测试fdna找到部门名
select e.ename ,fdna(e.deptno)
from emp e;

----触发器，就是制定一个规则，在我们做增删改的时候，只要满足该规则，自动触发
---语句触发器：不包含for each row 的触发器
---行级触发器：包含for each row 的触发器
-------加上for each row 是为了使用：old或者：new对象或者一行记录。

---语句触发器，插入一条语句，输出一个新员工入职
create or replace trigger t1
after 
insert
on person
declare

begin
   dbms_output.put_line('一个新员工入职');
end;

select *from person;

--触发t1
insert into person values(s_person.nextval,'我的',100.9);
commit;

---行级触发器
---不能给员工降薪
create or replace trigger t2
before
update
on emp
for each row
declare

begin
    if:old.sal>:new.sal then
       raise_application_error(-20001,'不能给员工降薪');
     end if;     
end;

--触发t2
update emp set sal=sal-1 where empno = 7788;
commit;
select *from emp where empno = 7788;

-----触发器实现主键自增（行级别触发器）
----分析：在用户做插入操作之前，拿到即将插入的数据
----给该数据的主键类赋值

create or replace trigger t3
before
insert
on person
for each row
declare
begin
  select s_person.nextval into :new.pid from dual;
end;

---使用t3
insert into person(pname,money) values('德玛西亚',999.9);
commit;
select *from person;














