--该文件记录平台oracle数据库变更的SQL语句,每条SQL必须写描述信息
--且针对每次新增,需要同步修改对应的platform-db-schema-oracle.sql和platform-init-data-oracle.sql文件
--例:给AUTH_MENU表增加创建时间  CREATE_TIME,则需要填写以下(--*后面的)内容:
--*   --liuy 2010-02-05 11:21
--*   --AUTH_MENU表增加创建时间  CREATE_TIME
--*   alter table AUTH_MENU add CREATE_TIME TIMESTAMP(6);
--*   comment on column AUTH_MENU.CREATE_TIME is '创建时间';


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
-- AUTH_ROLE_RESOURCE 表增加创建时间  CREATE_TIME
alter table AUTH_ROLE_RESOURCE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_ROLE_RESOURCE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_RESOURCE 表增加创建时间  CREATE_TIME
alter table AUTH_RESOURCE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_RESOURCE.CREATE_TIME is '创建时间';


--liuy 2010-02-05 11:21
-- AUTH_USER_ROLE 表增加创建时间  CREATE_TIME
alter table AUTH_USER_ROLE add CREATE_TIME TIMESTAMP(6);
comment on column AUTH_USER_ROLE.CREATE_TIME is '创建时间';


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


