--该文件记录平台oracle数据库变更的SQL语句,每条SQL必须写描述信息
--且针对每次新增,需要同步修改对应的platform-db-schema-oracle.sql和platform-init-data-oracle.sql文件
--例:给AUTH_MENU表增加创建时间  CREATE_TIME,则需要填写以下(--*后面的)内容:
--*   --liuy 2010-02-05 11:21
--*   --AUTH_MENU表增加创建时间  CREATE_TIME
--*   alter table AUTH_MENU add CREATE_TIME TIMESTAMP(6);
--*   comment on column AUTH_MENU.CREATE_TIME is '创建时间';

--wangcl 2010-6-3
alter table AUTH_ORGNIZATION add SHORT_SPELL_NAME VARCHAR2(50 CHAR);
comment on column AUTH_ORGNIZATION.SHORT_SPELL_NAME is '名称拼音缩写';	
alter table AUTH_ORG_EMPLOYEE add SHORT_SPELL_NAME VARCHAR2(50 CHAR);
comment on column AUTH_ORG_EMPLOYEE.SHORT_SPELL_NAME is '名称拼音缩写';
--wangcl 2010-3-24
--中间表删除创建时间字段
ALTER TABLE AUTH_USER_ROLE DROP COLUMN CREATE_TIME;
ALTER TABLE AUTH_ROLE_RESOURCE DROP COLUMN CREATE_TIME;

--lic 2010-03-15 11:38
--AUTH_USER表增加员工邮箱  USR_EMAIL
alter table AUTH_USER add USR_EMAIL VARCHAR2(20 CHAR);
comment on column AUTH_USER.USR_EMAIL is '员工邮箱';		
	
--lic 2010-03-15 11:38
--AUTH_ORG_EMPLOYEE表增加联系地址  EMP_ADDRESS
alter table AUTH_ORG_EMPLOYEE add EMP_ADDRESS VARCHAR2(100 CHAR);
comment on column AUTH_ORG_EMPLOYEE.EMP_ADDRESS is '联系地址';	

--lic 2010-03-15 11:38
--AUTH_ORG_EMPLOYEE表增加邮政编码  EMP_ZIPCODE
alter table AUTH_ORG_EMPLOYEE add EMP_ZIPCODE VARCHAR2(6 CHAR);
comment on column AUTH_ORG_EMPLOYEE.EMP_ZIPCODE is '邮政编码';	
	
--lic 2010-03-15 11:38
--AUTH_ORG_EMPLOYEE表增加传真  EMP_FAX
alter table AUTH_ORG_EMPLOYEE add EMP_FAX VARCHAR2(20 CHAR);
comment on column AUTH_ORG_EMPLOYEE.EMP_FAX is '传真';		
	
--lic 2010-03-15 11:38
--AUTH_ORG_EMPLOYEE表增加身份证号  EMP_IDCARD
alter table AUTH_ORG_EMPLOYEE add EMP_IDCARD VARCHAR2(18 CHAR);
comment on column AUTH_ORG_EMPLOYEE.EMP_IDCARD is '身份证号';	
	
--lic 2010-03-15 11:38
--AUTH_ORG_EMPLOYEE表增加员工性别  EMP_SEX
alter table AUTH_ORG_EMPLOYEE add EMP_SEX VARCHAR2(1 CHAR);
comment on column AUTH_ORG_EMPLOYEE.EMP_SEX is '员工性别（0：女  1：男）';

--lic 2010-03-15 11:38
--AUTH_ORGNIZATION表增加手机号码  ORG_MOBILEPHONE
alter table AUTH_ORGNIZATION add ORG_MOBILEPHONE VARCHAR2(11 CHAR);
comment on column AUTH_ORGNIZATION.ORG_MOBILEPHONE is '手机号码';

--liuy 2010-02-05 11:21
--AUTH_CONFLICT_ROLE表增加创建时间  CREATE_TIME
alter table AUTH_CONFLICT_ROLE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_CONFLICT_ROLE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_DATA_RES_TYPE 表增加创建时间  CREATE_TIME
alter table AUTH_DATA_RES_TYPE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_DATA_RES_TYPE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_MENU 表增加创建时间  CREATE_TIME
alter table AUTH_MENU add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_MENU.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ORGNIZATION_RULE 表增加创建时间  CREATE_TIME
alter table AUTH_ORGNIZATION_RULE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ORGNIZATION_RULE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ORG_DETAIL 表增加创建时间  CREATE_TIME
alter table AUTH_ORG_DETAIL add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ORG_DETAIL.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ROLE_EXTEND 表增加创建时间  CREATE_TIME
alter table AUTH_ROLE_EXTEND add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ROLE_EXTEND.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ROLE_RESOURCE 表增加创建时间  CREATE_TIME edit by wangcl 2010-03-24 中间表暂不追加此字段
--alter table AUTH_ROLE_RESOURCE add CREATE_TIME TIMESTAMP(6);
--comment on column AUTH_ROLE_RESOURCE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_RESOURCE 表增加创建时间  CREATE_TIME
alter table AUTH_RESOURCE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_RESOURCE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21 edit by wangcl 2010-03-24 中间表暂不追加此字段
-- AUTH_USER_ROLE 表增加创建时间  CREATE_TIME
--alter table AUTH_USER_ROLE add CREATE_TIME TIMESTAMP(6);
--comment on column AUTH_USER_ROLE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_OPER_LOGS 表增加创建时间  CREATE_TIME
alter table BASE_OPER_LOGS add CREATE_TIME TIMESTAMP(6);
comment on column BASE_OPER_LOGS.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_LOGIN_LOGS 表增加创建时间  CREATE_TIME
alter table BASE_LOGIN_LOGS add CREATE_TIME TIMESTAMP(6);
comment on column BASE_LOGIN_LOGS.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_INDUSTRY 表增加创建时间  CREATE_TIME
alter table BASE_INDUSTRY add CREATE_TIME TIMESTAMP(6);
comment on column BASE_INDUSTRY.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_SEQUENCE_NUMBER 表增加创建时间  CREATE_TIME
alter table BASE_SEQUENCE_NUMBER add CREATE_TIME TIMESTAMP(6);
comment on column BASE_SEQUENCE_NUMBER.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- ORG_EMPLOYEE 表增加创建时间  CREATE_TIME
alter table ORG_EMPLOYEE add CREATE_TIME TIMESTAMP(6);
comment on column ORG_EMPLOYEE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- ORG_ROLE 表增加创建时间  CREATE_TIME
alter table ORG_ROLE add CREATE_TIME TIMESTAMP(6);
comment on column ORG_ROLE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ORG_STRU_TYPE 表增加创建时间  CREATE_TIME
alter table AUTH_ORG_STRU_TYPE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ORG_STRU_TYPE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_USER 表增加创建时间  CREATE_TIME
alter table AUTH_USER add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_USER.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ORG_EMPLOYEE 表增加创建时间  CREATE_TIME
alter table AUTH_ORG_EMPLOYEE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ORG_EMPLOYEE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ROLE 表增加创建时间  CREATE_TIME
alter table AUTH_ROLE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ROLE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_ORGNIZATION 表增加创建时间  CREATE_TIME
alter table AUTH_ORGNIZATION add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ORGNIZATION.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_DICTIONARY 表增加创建时间  CREATE_TIME
alter table BASE_DICTIONARY add CREATE_TIME TIMESTAMP(6);
comment on column BASE_DICTIONARY.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_DICTIONARY_TYPE 表增加创建时间  CREATE_TIME
alter table BASE_DICTIONARY_TYPE add CREATE_TIME TIMESTAMP(6);
comment on column BASE_DICTIONARY_TYPE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_DISTRICT 表增加创建时间  CREATE_TIME
alter table BASE_DISTRICT add CREATE_TIME TIMESTAMP(6);
comment on column BASE_DISTRICT.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- BASE_ATTACHMENT 表增加创建时间  CREATE_TIME
alter table BASE_ATTACHMENT add CREATE_TIME TIMESTAMP(6);
comment on column BASE_ATTACHMENT.CREATE_TIME is '创建时间';



create table AUTH_GADGET
(
  OBJID      VARCHAR2(50 CHAR) not null,
  NAME       VARCHAR2(100 CHAR) not null,
  DESCS      VARCHAR2(200 CHAR),
  RES_ID     VARCHAR2(200 CHAR) not null,
  CREATETIME DATE,
  IMAGE_ID   VARCHAR2(50 CHAR)
);


create table AUTH_USER_GADGET
(
  OBJID       VARCHAR2(50 CHAR) not null,
  USER_ID     VARCHAR2(50 CHAR),
  GADGET_ID   VARCHAR2(50 CHAR),
  OPENABLE    VARCHAR2(1 CHAR),
  COLUMNINDEX NUMBER(1),
  ROWINDEX    NUMBER(1),
  MAXABLE     VARCHAR2(1 CHAR)
);

 