--������ռ�
create tablespace itcast
datafile 'f:\itcast.dbf'
size 100m
autoextend on
next 10m;

--ɾ����ռ�
drop tablespace itcast;

--�����û�
create user itcast
identified by itcast
default tablespace itcast;

--���û���Ȩ
--oracle���ݿ��г��ý�ɫ
connect--���ӽ�ɫ
resource--�����߽�ɫ
dba--��������Ա��ɫ

--��itcast�û���Ȩdba��ɫ
grant dba to itcast;

--����һ��person��
create table person(
       pid number(20),
       pname varchar2(10)
);

--�޸ı�ṹ
---���һ��
alter table person add (gender number(1), money number(6,2));

---�޸�������
alter table person modify gender char(1);

---�޸�������
alter table person rename column gender to sex;

---ɾ��һ��
alter table person drop column sex;

--���һ����¼
insert into person(pid,pname,money) values(1,'zz',99.3);
commit;

---��ѯ���еļ�¼
select *from person;

--�޸�һ����¼
update person set pname = '����' where pid = 1;
commit;

---����ɾ��
--ɾ�����е�ȫ������
delete from person;

--ɾ����ṹ
drop table person;

--��ɾ�����ٴ�����
--���������������£������ڱ��д�����������£��ò���Ч�ʸ�
--���������ṩ��ѯЧ�ʣ����ǻ�Ӱ����ɾ�ĵ�Ч��
truncate table person;

--���У�Ĭ�ϴ�1��ʼ�����ε�������Ҫ�Ǹ�������ֵ��
---���в�����������κ�һ�ű����ǿ����߼��ͱ�����
---dual:���ֻ��Ϊ�˲�ȫ�﷨��û���κ�����
create sequence s_person;
select s_person.currval from dual;

---������
insert into person(pid,pname,money) values(s_person.nextval,'zz',99.3);
commit;
select *from person;

---scott�û�������tiger
--����scott�û�
alter user scott account unlock;
--����scott�û�������
alter user scott identified by tiger;

--���к�����������һ�У�����һ��ֵ
---�ַ�����
select upper('yes') from dual;
select lower('YES') from dual;

---��ֵ����
select round(26.18,1) from dual; --�������룬����Ĳ�����ʾ������С��λ
select trunc(26.18,1) from dual; --ֱ�ӽ�ȡ�����ڿ�������ֵ�Ƿ����5
select mod(10,3) from dual;--ȡ����

----���ں���
----��ѯemp��������Ա����ְ�������ڼ���
select sysdate-e.hiredate from emp e;
----��������ʱ��
select sysdate+1 from dual;
----��ѯemp��������Ա����ְ�������ڼ���
select months_between(sysdate,e.hiredate) from emp e;
----��ѯemp��������Ա����ְ�������ڼ���
select months_between(sysdate,e.hiredate)/12 from emp e;
----��ѯemp��������Ա����ְ�������ڼ���
select round((sysdate-e.hiredate)/7) from emp e;

----ת������,����ת�ַ���
select to_char(sysdate,'yyyy-mm-dd hh24-mi-ss') from dual;
--�ַ���ת����
select to_date('2020-11-06 15-45-27','yyyy-mm-dd hh24-mi-ss') from dual;

----ͨ�ú���
--���emp���е�����Ա������н
select  e.sal*12+nvl(e.comm,0) from emp e;

----�������ʽ
----�������ʽ��ͨ��д��
---��emp������������
select e.ename,
       case e.ename
          when 'SMITH' then '����'
            when 'ALLEN' then '�����'
              when 'WARD' then '���۶�'
                else '�����'  --ȥ��else �����Ķ���null
                  end
                 
from emp e;

---�жϱ��е�Ա�����ʣ��������3000��ʾ�����룬�����1500��3000֮����ʾ�еȣ�������ʾ������
select e.sal,
       case
          when  e.sal>3000 then '������'
            when e.sal>1500 then '�е�����'
                else '������'  
                  end              
from emp e;
-----reacle�г�������������õ�����
----oracleר�ñ��ʽ
select e.ename,
      decode( e.ename,
           'SMITH',  '����',
             'ALLEN',  '�����',
               'WARD',  '���۶�',
                 '�����'  --ȥ��else �����Ķ���null
                  )������
                 
from emp e;

select e.ename from emp e;





--���к����������ڶ��У�����һ��ֵ
select count(1) from emp;--��ѯ�ܼ�¼��
select sum(sal) from emp;--�����ܺ�
select max(sal) from emp;--�����
select min(sal) from emp;--��С����
select round(avg(sal)) from emp;--����ƽ��ֵ

---�����ѯ
--��ѯ��ÿ�����ŵ�ƽ������
--�����ѯ�У�������group by�����ԭʼ�У����ܳ��֣�û�г�����group by ������У�����select������֣�������ϾۺϺ���
--�ۺϺ��������԰Ѷ��м�¼���һ��ֵ
select  e.deptno,avg(e.sal),count(1)
from emp e
group by e.deptno;

--��ѯ��ÿ�����ŵ�ƽ�����ʸ�����ǧ��
select  e.deptno ����,avg(e.sal) ƽ������,count(1) ����
from emp e
group by e.deptno 
having avg(e.sal)>2000;

--��ѯ��ÿ�����Ź�������800��Ա����ƽ������
select  e.deptno ����,avg(e.sal) ƽ������,count(1) ����
from emp e
where  e.sal>800
group by e.deptno 
having avg(e.sal)>2000;
----where�ǹ��˷���ǰ�����ݣ�having�ǹ��˷���������

--��ѯ��ÿ�����Ź�������800��Ա����ƽ�����ʣ��ٲ�ѯ��ƽ�����ʸ���2000�Ĳ���
select  e.deptno ����,avg(e.sal) ƽ������,count(1) ����
from emp e
where  e.sal>800
group by e.deptno 
having avg(e.sal)>2000;

---��ѯ�����в��źͲ����µ�����Ա����Ϣ
select *
from dept d
left join
emp e
on 
e.deptno =d.deptno

---oracleר��������
select * 
from emp e,dept d
where e.deptno(+) = d.deptno; 

select *from emp

---��ѯ��Ա��������Ա���쵼����
select e1.empno,e1.ename,e2.ename,e2.empno
from emp e1,emp e2
where
e1.mgr=e2.empno

----��ѯ��Ա��������Ա����������Ա���쵼������Ա���쵼����
select e1.empno,e1.ename,d1.dname,e2.ename,e2.empno,d2.dname
from emp e1,emp e2,dept d1,dept d2
where
e1.mgr=e2.empno 
and e1.deptno=d1.deptno 
and e2.deptno=d2.deptno

----��ѯ��Ա��������Ա�����ʵȼ���Ա����������Ա���쵼�������쵼���ʵȼ���Ա���쵼����
select e1.empno,e1.ename,s1.grade,d1.dname,e2.ename,s2.grade,e2.empno,d2.dname
from emp e1,emp e2,dept d1,dept d2,salgrade s1,salgrade s2
where
e1.mgr=e2.empno 
and e1.deptno=d1.deptno 
and e2.deptno=d2.deptno
and e1.sal>=s1.losal and e1.sal<=s1.hisal
and e2.sal>=s2.losal and e2.sal<=s2.hisal

----�Ӳ�ѯ
---�Ӳ�ѯ����һ��ֵ
--��ѯ�����ʺ�scottһ����Ա��
select * from emp e 
where 
e.sal
in
(select sal from emp e where e.ename='SCOTT');


---�Ӳ�ѯ����һ������
--��ѯ�����ʺ�10�Ų�������Ա��һ����Ա����Ϣ
select *from emp where sal 
in
(select sal from emp where deptno =10);

---�Ӳ�ѯ����һ�ű�
--��ѯ��ÿ��������͹��ʣ�����͹��ʵ������͸�Ա�����ڲ�������
select min(e.sal) from emp e group by e.deptno;

select 
e.sal,d.dname,e.ename
from 
emp e,dept d,(select e.deptno deptno ,min(e.sal) msal from emp e group by e.deptno) t
where
    e.sal = t.msal 
and e.deptno = d.deptno
and t.deptno = d.deptno

----oracle�еķ�ҳ
---rownum�кţ���������select������ʱ��
---ÿ��ѯ��һ�м�¼���ͻ��ڸ����ϼ�һ���к� 
---�кŴ�1��ʼ�����ε���������������
----emp���ʵ������к�ÿҳ5����¼����ѯ�ڶ�ҳ
----���������Ӱ��rownum��˳��
select rownum, e.* from emp e order by sal Desc;

--����漰�����򣬵��ǻ���Ҫʹ��rownum�Ļ������ǿ����ٴ�Ƕ�ײ�ѯ
select rownum ,t.*from(
select e.* from emp e order by sal Desc) t;
 
select rownum , t.* from(
select e.* from emp e order by sal Desc) t where rownum <11 ;

select *from
(      select rownum rn, t.* from(
              select e.* from emp e order by sal Desc) t 
where rownum <11) t1
where t1.rn>5;


----��ͼ
---��ͼ�ĸ����ͼ����һ����ѯ�Ĵ��ڣ���������������ԭ��

---��ѯ��䴴����
create table emp as select *from scott.emp;
select * from emp;

create view v_emp as select ename,job from emp;
---��ѯ��ͼ
select * from v_emp;
---�޸���ͼ(���Ƽ�)
update v_emp set job='clear' where ename='ALLEN';
commit;
---����ֻ����ͼ
create view v_emp1 as select ename,job from emp with read only;
select * from v_emp1;

---��ͼ�����ã�
---��һ����ͼ��������һЩ�����ֶΡ�
---�ڶ�����֤�ܲ��ͷֲ����ݼ�ʱͬһ

----����
---�����ĸ�����������ڱ�����Ϲ���һ�����������ﵽ��������
---��ѯЧ�ʵ�Ŀ�ģ�����������Ӱ����ɾ�ĵ�Ч��
---��������
--������������
create index idx_ename on emp(ename);
---���������������������������������е�ԭʼֵ 
---���к�����ģ����ѯ����Ӱ�������Ĵ���
select *from emp where ename='SCOTT';

---��������
--������������
create index idx_enamejob on emp(ename,job);
--���������е�һ��Ϊ���ȼ�����
---���Ҫ������������������������ȼ������е�ԭʼֵ
select *from emp where ename='SCOTT' and job ='xx';--��������
select *from emp where ename='SCOTT' or job ='xx';--����������
select *from emp where ename='SCOTT';--������������


-----pl/sql�������
---pl/sql��������Ƕ�sql���Ե���չ����sql���Ծ��й��̻���̵����ԡ�
---pl/sql������Ա�һ��Ĺ��̻�������Ը�������Ч��
---pl/sql���������Ҫ������д�洢���̺ʹ洢�����ȣ�

---��������
--��ֵ��������ʹ��:= Ҳ����ʹ��into��ѯ��丳ֵ
declare
  i  number(4,2) := 10;
  s varchar2(10) := '��ûϷ��';
  ena emp.ename%type;--�����ͱ���
  emprow emp%rowtype;--��¼�ͱ���
begin
  dbms_output.put_line(i);
  dbms_output.put_line(s);
  select ename into ena from emp where empno=7788;
  dbms_output.put_line(ena);
  select * into emprow from emp where empno=7788;
  dbms_output.put_line(emprow.ename|| '����Ϊ��'  || emprow.job || '���ű��Ϊ��' || emprow.deptno);
end;

---pl/sql�е�if�ж�
---����С��18�����֣����δ����
---�������18С��40�����֣�����г���
---�������40�����֣�����ϳ���
declare
  i number(3) := &i;
begin
  if i<18 then
    dbms_output.put_line('δ����');
    elsif i< 40 then
       dbms_output.put_line('�г���');
      else
        dbms_output.put_line('������');
      end if;
end;

----pl/sql�е�loopѭ��
---�����ַ�ʽ���1��10������
--whileѭ��
declare
 i number(2) :=1;
begin
   while i<11 loop
     dbms_output.put_line(i);
     i :=i+1;
     end loop; 
end;

--exitѭ��
declare
 i number(2) :=1;
begin
  loop
    exit when i>10;
     dbms_output.put_line(i);
     i :=i+1;
     end loop; 
end;

--forѭ��
declare

begin
  for i in 1..10 loop
     dbms_output.put_line(i);
    end loop;
end;

----�α꣺����java�ļ��ϣ����Դ�Ŷ�����󣬶��м�¼
---���emp��������Ա��������
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

----��ָ������Ա���ǹ���
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

----�洢����
----�洢���̣��洢���̾�����ǰ�Ѿ�����õ�һ��pl/sql���ԣ����������ݿ⣬����ֱ�ӱ�����
----��һ��pl/sqlһ�㶼�ǹ̶������ҵ��
---��ָ��Ա����100��Ǯ
create or replace procedure p1(enp emp.empno%type)
is

begin
  update emp set sal=sal+100 where empno=enp;
  commit;
end;

select *from emp where empno=7788;
----����p1
declare

begin
  p1(7788);
end;

----ͨ���洢����ʵ�ּ��㣬ָ��Ա���ĵ���н
----�洢���̺ʹ洢�����Ĳ������ܴ�����
----�洢�����ķ���ֵ���Ͳ��ܴ�����
create or replace function f_yearsal(enp emp.empno%type) return number
is
  s number(10);
begin
  select sal*12+nvl(emp.comm,0) into s from emp where empno=enp;
  return s;
end; 

---����f_yearsla
---�洢�����ڵ����ǣ�����ֵ��Ҫ����
declare
 s number(10);
begin
  s:=f_yearsal(7788);
  dbms_output.put_line(s);
end;

----out���Ͳ���Ȼ��ʹ��
create or replace procedure p_yearsal(enp emp.empno%type,yearsal out number) 
is
  s number(10);
  c emp.comm%type;
begin
    select sal*12,nvl(comm,0) into s,c from emp where empno=enp;
    yearsal := s+c;
end;

---����p_yearsla
---�洢�����ڵ����ǣ�����ֵ��Ҫ����
declare
 yearsal number(10);
begin
  p_yearsal(7788,yearsal);
  dbms_output.put_line(yearsal);
end;

--in��out���Ͳ�����������ʲô��
--�����漰��into��ѯ��丳ֵ���ߣ�=��ֵ�����Ĳ�����������ʹ��out�����Σ�������in


----�洢�����ʹ洢���̵�����
---1.�ؼ��ֲ�һ��
---2.�洢�����з���ֵ���洢����û�з���ֵ���洢�����ȴ洢���̶�������return
create table dept as  select * from scott.dept;


create or replace function fdna(dno dept.deptno%type) return dept.dname%type
is
   dna dept.dname%type;
begin
  select dname into dna from dept where deptno=dno;
  return dna;
end;

--����fdna�ҵ�������
select e.ename ,fdna(e.deptno)
from emp e;

----�������������ƶ�һ����������������ɾ�ĵ�ʱ��ֻҪ����ù����Զ�����
---��䴥������������for each row �Ĵ�����
---�м�������������for each row �Ĵ�����
-------����for each row ��Ϊ��ʹ�ã�old���ߣ�new�������һ�м�¼��

---��䴥����������һ����䣬���һ����Ա����ְ
create or replace trigger t1
after 
insert
on person
declare

begin
   dbms_output.put_line('һ����Ա����ְ');
end;

select *from person;

--����t1
insert into person values(s_person.nextval,'�ҵ�',100.9);
commit;

---�м�������
---���ܸ�Ա����н
create or replace trigger t2
before
update
on emp
for each row
declare

begin
    if:old.sal>:new.sal then
       raise_application_error(-20001,'���ܸ�Ա����н');
     end if;     
end;

--����t2
update emp set sal=sal-1 where empno = 7788;
commit;
select *from emp where empno = 7788;

-----������ʵ�������������м��𴥷�����
----���������û����������֮ǰ���õ��������������
----�������ݵ������ำֵ

create or replace trigger t3
before
insert
on person
for each row
declare
begin
  select s_person.nextval into :new.pid from dual;
end;

---ʹ��t3
insert into person(pname,money) values('��������',999.9);
commit;
select *from person;














