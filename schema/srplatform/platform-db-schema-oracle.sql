/*
Source Server         : srplatform
Source Server Version : SVN-1595
Source Host           : 192.168.2.10
Source Database       : srplatform
Target Server Type    : Oracle
Date: 2010-02-24 20:13:00
*/

--角色冲突
DROP TABLE AUTH_CONFLICT_ROLE CASCADE CONSTRAINTS
;

--数据资源
DROP TABLE AUTH_DATA_RES_TYPE CASCADE CONSTRAINTS
;

DROP TABLE AUTH_GADGET CASCADE CONSTRAINTS
;

--菜单
DROP TABLE AUTH_MENU CASCADE CONSTRAINTS
;

--组织机构
DROP TABLE AUTH_ORGNIZATION CASCADE CONSTRAINTS
;

--组织规则
DROP TABLE AUTH_ORGNIZATION_RULE CASCADE CONSTRAINTS
;

--机构详细信息
DROP TABLE AUTH_ORG_DETAIL CASCADE CONSTRAINTS
;

--员工
DROP TABLE AUTH_ORG_EMPLOYEE CASCADE CONSTRAINTS
;

--机构组织类型
DROP TABLE AUTH_ORG_STRU_TYPE CASCADE CONSTRAINTS
;

--角色资源
DROP TABLE AUTH_ROLE_RESOURCE CASCADE CONSTRAINTS
;

--角色
DROP TABLE AUTH_ROLE CASCADE CONSTRAINTS
;

--角色扩展信息
DROP TABLE AUTH_ROLE_EXTEND CASCADE CONSTRAINTS
;

--资源
DROP TABLE AUTH_RESOURCE CASCADE CONSTRAINTS
;

--用户
DROP TABLE AUTH_USER CASCADE CONSTRAINTS
;

DROP TABLE AUTH_USER_GADGET CASCADE CONSTRAINTS
;

--用户角色
DROP TABLE AUTH_USER_ROLE CASCADE CONSTRAINTS
;

--附件
DROP TABLE BASE_ATTACHMENT CASCADE CONSTRAINTS
;

--字典
DROP TABLE BASE_DICTIONARY CASCADE CONSTRAINTS
;

--字典类型
DROP TABLE BASE_DICTIONARY_TYPE CASCADE CONSTRAINTS
;

--区域
DROP TABLE BASE_DISTRICT CASCADE CONSTRAINTS
;

--行业
DROP TABLE BASE_INDUSTRY CASCADE CONSTRAINTS
;

--登录日志
DROP TABLE BASE_LOGIN_LOGS CASCADE CONSTRAINTS
;

--操作日志
DROP TABLE BASE_OPER_LOGS CASCADE CONSTRAINTS
;

--序列号
DROP TABLE BASE_SEQUENCE_NUMBER CASCADE CONSTRAINTS
;

DROP TABLE BASE_SYSCONFIGITEM CASCADE CONSTRAINTS
;

DROP TABLE BASE_SYSCONFIGITEM_VALUE CASCADE CONSTRAINTS
;

DROP TABLE BASE_SYSCONFIG_PRE_VALUES CASCADE CONSTRAINTS
;

DROP TABLE BASE_SYSCONFIG_TYPE CASCADE CONSTRAINTS
;

DROP TABLE DEMO_ADDITIONAL CASCADE CONSTRAINTS
;

DROP TABLE DEMO_AREA CASCADE CONSTRAINTS
;

DROP TABLE DEMO_BRAND CASCADE CONSTRAINTS
;

DROP TABLE DEMO_GOODS CASCADE CONSTRAINTS
;

DROP TABLE DEMO_GOODS_CLASS CASCADE CONSTRAINTS
;

DROP TABLE DEMO_GOODS_ORDER CASCADE CONSTRAINTS
;

DROP TABLE DEMO_GOODS_PARAM CASCADE CONSTRAINTS
;

DROP TABLE DEMO_OPTION CASCADE CONSTRAINTS
;

DROP TABLE DEMO_ORDER CASCADE CONSTRAINTS
;

DROP TABLE DEMO_PURCHASE_CATEGORY CASCADE CONSTRAINTS
;

--组织类型字典
DROP TABLE ORG_ROLE CASCADE CONSTRAINTS
;

--组织机构中使用，代表员工
DROP TABLE ORG_EMPLOYEE CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_DEPLOYMENT CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_DEPLOYPROP CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_EXECUTION CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_HIST_ACTINST CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_HIST_DETAIL CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_HIST_PROCINST CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_HIST_TASK CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_HIST_VAR CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_ID_GROUP CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_ID_MEMBERSHIP CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_ID_USER CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_JOB CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_LOB CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_PARTICIPATION CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_PROPERTY CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_SWIMLANE CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_TASK CASCADE CONSTRAINTS
;

DROP TABLE JBPM4_VARIABLE CASCADE CONSTRAINTS
;

--﻿ Create table
create table AUTH_CONFLICT_ROLE
(
  CON_ID      VARCHAR2(50 CHAR) not null,
  CON_NAME    VARCHAR2(20 CHAR),
  ORG_ID      VARCHAR2(50 CHAR),
  CREATE_TIME DATE,
  CON_ROLES   VARCHAR2(500 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_CONFLICT_ROLE.CON_ID
  is '冲突——ID';
comment on column AUTH_CONFLICT_ROLE.CON_NAME
  is '冲突——名称';
comment on column AUTH_CONFLICT_ROLE.ORG_ID
  is '机构——冲突规则所属机构ID，关联AUTH_ORGNIZATION表。';
comment on column AUTH_CONFLICT_ROLE.CON_ROLES
  is '冲突——角色ID串号，以逗号分隔';

-- Create table
create table AUTH_DATA_RES_TYPE
(
  DAT_ID         VARCHAR2(50 CHAR) not null,
  DAT_IS_ORGAN   VARCHAR2(1 CHAR) default '0' not null,
  DAT_TABLE_NAME VARCHAR2(20 CHAR),
  DAT_TABLE_DESC VARCHAR2(50 CHAR),
  DAT_COL_VALUE  VARCHAR2(20 CHAR),
  DAT_VCOL_DESC  VARCHAR2(50 CHAR),
  DAT_COL_NAME   VARCHAR2(20 CHAR),
  DAT_NCOL_DESC  VARCHAR2(50 CHAR),
  DAT_USE_WHERE  VARCHAR2(1 CHAR) default '0' not null,
  CREATE_TIME    DATE,
  DAT_WHERE      VARCHAR2(50 CHAR)
);

-- Add comments to the table 
comment on table AUTH_DATA_RES_TYPE
  is 'NO';
-- Add comments to the columns 
comment on column AUTH_DATA_RES_TYPE.DAT_ID
  is '数据资源——ID';
comment on column AUTH_DATA_RES_TYPE.DAT_IS_ORGAN
  is '数据资源——是否为机构';
comment on column AUTH_DATA_RES_TYPE.DAT_TABLE_NAME
  is '数据资源——查询表名';
comment on column AUTH_DATA_RES_TYPE.DAT_TABLE_DESC
  is '数据资源——查询表描述';
comment on column AUTH_DATA_RES_TYPE.DAT_COL_VALUE
  is '数据资源——取值字段名';
comment on column AUTH_DATA_RES_TYPE.DAT_VCOL_DESC
  is '数据资源——取值字段描述 ';
comment on column AUTH_DATA_RES_TYPE.DAT_COL_NAME
  is '数据资源——取值字段名称';
comment on column AUTH_DATA_RES_TYPE.DAT_NCOL_DESC
  is '数据资源——显示字段描述 ';
comment on column AUTH_DATA_RES_TYPE.DAT_USE_WHERE
  is '数据资源——是否使用WHERE条件过滤
1：是 0：否 ';
comment on column AUTH_DATA_RES_TYPE.DAT_WHERE
  is '数据资源——如DAT_USE_WHERE为1，则这个字段设置过滤条件 ';

-- Create table
create table AUTH_GADGET
(
  OBJID      VARCHAR2(50 CHAR) not null,
  NAME       VARCHAR2(100 CHAR) not null,
  DESCS      VARCHAR2(200 CHAR),
  RES_ID     VARCHAR2(200 CHAR) not null,
  CREATETIME DATE,
  IMAGE_ID   VARCHAR2(50 CHAR)
);

-- Create table
create table AUTH_MENU
(
  MNU_ID         VARCHAR2(50 CHAR) not null,
  MNU_MEMO       VARCHAR2(100 CHAR),
  MNU_NAME       VARCHAR2(20 CHAR) not null,
  MNU_SHOWFLAG   VARCHAR2(1) default '1',
  MNU_SORT       NUMBER(19),
  MNU_TARGET     VARCHAR2(10 CHAR) default '0',
  MNU_TIP        VARCHAR2(20 CHAR),
  MNU_PARENT_ID  VARCHAR2(50 CHAR),
  MNU_ICON       VARCHAR2(20 CHAR),
  MNU_IS_DEFAULT VARCHAR2(2 CHAR) default '0',
  MNU_IS_LEAF    VARCHAR2(1) default '1',
  MNU_PATH       VARCHAR2(500),
  MNU_LEVEL      NUMBER(19),
  CREATE_TIME    DATE,
  RES_ID         VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_MENU.MNU_ID
  is '菜单——ID';
comment on column AUTH_MENU.MNU_MEMO
  is '菜单——备注';
comment on column AUTH_MENU.MNU_NAME
  is '菜单——名称';
comment on column AUTH_MENU.MNU_SHOWFLAG
  is '菜单——是否显示';
comment on column AUTH_MENU.MNU_SORT
  is '菜单——排序';
comment on column AUTH_MENU.MNU_TARGET
  is '菜单——定位目标';
comment on column AUTH_MENU.MNU_TIP
  is '菜单——提示语';
comment on column AUTH_MENU.MNU_PARENT_ID
  is '菜单——上级菜单ID';
comment on column AUTH_MENU.MNU_ICON
  is '菜单——图标';
comment on column AUTH_MENU.MNU_IS_DEFAULT
  is '菜单——是否默认菜单';
comment on column AUTH_MENU.MNU_IS_LEAF
  is '菜单——是否叶子节点';
comment on column AUTH_MENU.MNU_PATH
  is '菜单——路径';
comment on column AUTH_MENU.MNU_LEVEL
  is '菜单——树形层级';
comment on column AUTH_MENU.RES_ID
  is '资源ID——关联AUTH_RESOURCE表。';

-- Create table
create table AUTH_ORGNIZATION
(
  ORG_ID            VARCHAR2(50 CHAR) not null,
  ORG_ADDRESS       VARCHAR2(200 CHAR),
  ORG_AUDIT_OPINION VARCHAR2(2000 CHAR),
  ORG_AUDIT_STATUS  VARCHAR2(1 CHAR) default '0' not null,
  ORG_CODE          VARCHAR2(15 CHAR),
  ORG_CONTACT       VARCHAR2(50 CHAR),
  ORG_CROPORATE     VARCHAR2(50 CHAR),
  ORG_EMAIL         VARCHAR2(50 CHAR),
  ORG_FAX           VARCHAR2(50 CHAR),
  ORG_LEADER        VARCHAR2(50 CHAR),
  ORG_NAME          VARCHAR2(200 CHAR),
  ORG_POST_CODE     VARCHAR2(100 CHAR),
  ORG_SHORT_NAME    VARCHAR2(30 CHAR),
  ORG_SORT          NUMBER(19),
  ORG_STATUS        VARCHAR2(1 CHAR) default '1' not null,
  ORG_SUPERVISOR    VARCHAR2(50 CHAR),
  ORG_TEL           VARCHAR2(50 CHAR),
  ORG_TYPE          VARCHAR2(1 CHAR) not null,
  ORG_BUDGET_CODE   VARCHAR2(200 CHAR),
  ORG_CITY          VARCHAR2(50 CHAR),
  ORG_PARENT_ID     VARCHAR2(50 CHAR),
  ORG_PROVINCE      VARCHAR2(50 CHAR),
  ORG_TOWN          VARCHAR2(50 CHAR),
  ORG_PATH          VARCHAR2(500 CHAR),
  ORG_IS_LEAF       VARCHAR2(1 CHAR) default '1' not null,
  CREATE_TIME       DATE,
  ORG_LEVEL         VARCHAR2(1 CHAR)
);

-- Add comments to the table 
comment on table AUTH_ORGNIZATION
  is '2009-11-29 For  srplatform v1.0 updated alias ORG_ORGAN';
-- Add comments to the columns 
comment on column AUTH_ORGNIZATION.ORG_ID
  is '机构——ID';
comment on column AUTH_ORGNIZATION.ORG_ADDRESS
  is '机构——地址';
comment on column AUTH_ORGNIZATION.ORG_AUDIT_OPINION
  is '机构——审计项目';
comment on column AUTH_ORGNIZATION.ORG_AUDIT_STATUS
  is '机构——审计状态';
comment on column AUTH_ORGNIZATION.ORG_CODE
  is '机构——编码';
comment on column AUTH_ORGNIZATION.ORG_CONTACT
  is '机构——联系方式';
comment on column AUTH_ORGNIZATION.ORG_CROPORATE
  is '机构——合作方(cooperate)';
comment on column AUTH_ORGNIZATION.ORG_EMAIL
  is '机构——电子邮箱';
comment on column AUTH_ORGNIZATION.ORG_FAX
  is '机构——传真';
comment on column AUTH_ORGNIZATION.ORG_LEADER
  is '机构——领导';
comment on column AUTH_ORGNIZATION.ORG_NAME
  is '机构——名称';
comment on column AUTH_ORGNIZATION.ORG_POST_CODE
  is '机构——邮箱编号';
comment on column AUTH_ORGNIZATION.ORG_SHORT_NAME
  is '机构——简称';
comment on column AUTH_ORGNIZATION.ORG_SORT
  is '机构——排序';
comment on column AUTH_ORGNIZATION.ORG_STATUS
  is '机构——状态';
comment on column AUTH_ORGNIZATION.ORG_SUPERVISOR
  is '机构——主管';
comment on column AUTH_ORGNIZATION.ORG_TEL
  is '机构——电话';
comment on column AUTH_ORGNIZATION.ORG_TYPE
  is '机构——类型,''1''公司,''2''部门,''3''岗位.';
comment on column AUTH_ORGNIZATION.ORG_BUDGET_CODE
  is '机构——存取款账号';
comment on column AUTH_ORGNIZATION.ORG_CITY
  is '机构——所在城市';
comment on column AUTH_ORGNIZATION.ORG_PARENT_ID
  is '机构——上级机关';
comment on column AUTH_ORGNIZATION.ORG_PROVINCE
  is '机构——所在省区';
comment on column AUTH_ORGNIZATION.ORG_TOWN
  is '机构——所在镇区';
comment on column AUTH_ORGNIZATION.ORG_PATH
  is '机构——路径';
comment on column AUTH_ORGNIZATION.ORG_IS_LEAF
  is '机构——是否叶子节点';
comment on column AUTH_ORGNIZATION.ORG_LEVEL
  is '机构——树形层级';

-- Create table
create table AUTH_ORGNIZATION_RULE
(
  RULE_ID      VARCHAR2(50 CHAR) not null,
  ORG_SOURCE   VARCHAR2(1 CHAR),
  ORG_TARGET   VARCHAR2(1 CHAR),
  ORG_RULE     VARCHAR2(50 CHAR),
  CREATE_TIME  DATE,
  STRU_TYPE_ID VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_ORGNIZATION_RULE.RULE_ID
  is '规则——ID';
comment on column AUTH_ORGNIZATION_RULE.ORG_SOURCE
  is '机构——上级组织类型';
comment on column AUTH_ORGNIZATION_RULE.ORG_TARGET
  is '机构——下级组织类型';
comment on column AUTH_ORGNIZATION_RULE.ORG_RULE
  is '机构——拖动规则';
comment on column AUTH_ORGNIZATION_RULE.STRU_TYPE_ID
  is '组织——类型ID';

-- Create table
create table AUTH_ORG_DETAIL
(
  ORG_DETAIL_ID              VARCHAR2(60 CHAR) not null,
  ORG_REG_NO                 VARCHAR2(50 CHAR),
  ORG_REG_PERSON             VARCHAR2(50 CHAR),
  ORG_REG_BUSINESS           VARCHAR2(200 CHAR),
  ORG_REG_ISSUE_AUTHORITY    VARCHAR2(100 CHAR),
  ORG_REG_DATE               DATE,
  ORG_REG_VALIDITY           DATE,
  ORG_REG_ADDRESS            VARCHAR2(200 CHAR),
  ORG_FIRST_CERTIFICATE_DATE DATE,
  ORG_COUNT_B_DATE           DATE,
  ORG_COUNT_E_DATE           DATE,
  ORG_INTRODUCTION           VARCHAR2(2000 CHAR),
  ORG_CODE_FILE              VARCHAR2(50 CHAR),
  ORG_REG_CERTIFICATE        VARCHAR2(200 CHAR),
  ORG_NATURE                 VARCHAR2(50 CHAR),
  CREATE_TIME                DATE,
  ORG_LOGO                   VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_ORG_DETAIL.ORG_DETAIL_ID
  is '详细信息——ID';
comment on column AUTH_ORG_DETAIL.ORG_REG_NO
  is '资源——编号';
comment on column AUTH_ORG_DETAIL.ORG_REG_PERSON
  is '资源——法人';
comment on column AUTH_ORG_DETAIL.ORG_REG_BUSINESS
  is '资源——注册经营业务';
comment on column AUTH_ORG_DETAIL.ORG_REG_ISSUE_AUTHORITY
  is '资源——注册颁发机关';
comment on column AUTH_ORG_DETAIL.ORG_REG_DATE
  is '资源——日期';
comment on column AUTH_ORG_DETAIL.ORG_REG_VALIDITY
  is '资源——有效性';
comment on column AUTH_ORG_DETAIL.ORG_REG_ADDRESS
  is '资源——地址';
comment on column AUTH_ORG_DETAIL.ORG_FIRST_CERTIFICATE_DATE
  is '资源——执照首批日期';
comment on column AUTH_ORG_DETAIL.ORG_COUNT_B_DATE
  is '资源——建立日期';
comment on column AUTH_ORG_DETAIL.ORG_COUNT_E_DATE
  is '资源——终止日期';
comment on column AUTH_ORG_DETAIL.ORG_INTRODUCTION
  is '简介';
comment on column AUTH_ORG_DETAIL.ORG_CODE_FILE
  is '文档编号';
comment on column AUTH_ORG_DETAIL.ORG_REG_CERTIFICATE
  is '资源——工商注册执照';
comment on column AUTH_ORG_DETAIL.ORG_NATURE
  is '机构——企业性质（类型）';
comment on column AUTH_ORG_DETAIL.ORG_LOGO
  is '机构——标示';

-- Create table
create table AUTH_ORG_EMPLOYEE
(
  EMP_ID            VARCHAR2(50 CHAR) not null,
  EMP_NAME          VARCHAR2(50 CHAR) not null,
  EMP_MOBILE        VARCHAR2(50 CHAR),
  EMP_EMAIL         VARCHAR2(20 CHAR),
  EMP_MSN           VARCHAR2(20 CHAR),
  EMP_QQ            VARCHAR2(20 CHAR),
  EMP_TEL_OFFICE    VARCHAR2(20 CHAR),
  EMP_TEL_HOME      VARCHAR2(20 CHAR),
  EMP_SORT          NUMBER(19),
  EMP_CREATED_DATE  DATE,
  EMP_COMPANY_ID    VARCHAR2(50 CHAR),
  EMP_DEPARTMENT_ID VARCHAR2(50 CHAR),
  EMP_POST_ID       VARCHAR2(50 CHAR),
  EMP_NUMBER        VARCHAR2(10 CHAR),
  CREATE_TIME       DATE,
  EMP_ORG_ID        VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_ORG_EMPLOYEE.EMP_ID
  is '员工——ID';
comment on column AUTH_ORG_EMPLOYEE.EMP_NAME
  is '员工——姓名';
comment on column AUTH_ORG_EMPLOYEE.EMP_MOBILE
  is '员工——手机号';
comment on column AUTH_ORG_EMPLOYEE.EMP_EMAIL
  is '员工——邮箱';
comment on column AUTH_ORG_EMPLOYEE.EMP_MSN
  is '员工——MSN';
comment on column AUTH_ORG_EMPLOYEE.EMP_QQ
  is '员工——QQ';
comment on column AUTH_ORG_EMPLOYEE.EMP_TEL_OFFICE
  is '员工——办公电话';
comment on column AUTH_ORG_EMPLOYEE.EMP_TEL_HOME
  is '员工——家庭电话';
comment on column AUTH_ORG_EMPLOYEE.EMP_SORT
  is '员工——排序';
comment on column AUTH_ORG_EMPLOYEE.EMP_CREATED_DATE
  is '员工——创建日期';
comment on column AUTH_ORG_EMPLOYEE.EMP_COMPANY_ID
  is '员工——公司';
comment on column AUTH_ORG_EMPLOYEE.EMP_DEPARTMENT_ID
  is '员工——部门ID';
comment on column AUTH_ORG_EMPLOYEE.EMP_POST_ID
  is '员工——职位';
comment on column AUTH_ORG_EMPLOYEE.EMP_NUMBER
  is '员工——编号';
comment on column AUTH_ORG_EMPLOYEE.EMP_ORG_ID
  is '员工||最近的机构';
-- Create table
create table AUTH_ORG_STRU_TYPE
(
  STR_ID      VARCHAR2(50 CHAR) not null,
  CREATE_TIME DATE,
  STR_NAME    VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_ORG_STRU_TYPE.STR_ID
  is '组织——ID';
comment on column AUTH_ORG_STRU_TYPE.STR_NAME
  is '组织——名称';

-- Create table
create table AUTH_RESOURCE
(
  RES_ID        VARCHAR2(50 CHAR) not null,
  RES_NAME      VARCHAR2(20 CHAR) not null,
  RES_URL       VARCHAR2(100 CHAR),
  RES_IS_LOG    VARCHAR2(1),
  RES_IS_SYS    VARCHAR2(1) default '0',
  RES_IS_LEAF   VARCHAR2(1) default '1',
  RES_PATH      VARCHAR2(500 CHAR),
  RES_LEVEL     NUMBER(19),
  RES_PARENT_ID VARCHAR2(50 CHAR),
  RES_TYPE      VARCHAR2(6 CHAR),
  CREATE_TIME   DATE,
  RES_SORT      NUMBER(19)
);

-- Add comments to the columns 
comment on column AUTH_RESOURCE.RES_ID
  is '资源——ID';
comment on column AUTH_RESOURCE.RES_NAME
  is '资源——名称';
comment on column AUTH_RESOURCE.RES_URL
  is '资源——URL地址';
comment on column AUTH_RESOURCE.RES_IS_LOG
  is '资源——是否启用日志';
comment on column AUTH_RESOURCE.RES_IS_SYS
  is '资源——是否属于系统';
comment on column AUTH_RESOURCE.RES_IS_LEAF
  is '资源——是否叶子节点';
comment on column AUTH_RESOURCE.RES_PATH
  is '资源——路径';
comment on column AUTH_RESOURCE.RES_LEVEL
  is '资源——树型层级';
comment on column AUTH_RESOURCE.RES_PARENT_ID
  is '资源——上级ID';
comment on column AUTH_RESOURCE.RES_TYPE
  is '资源——URL的类型（链接*.do）Method方法（com.包名.service.方法名）';

-- Create table
create table AUTH_ROLE
(
  ROL_ID         VARCHAR2(50 CHAR) not null,
  ROL_EN_NAME    VARCHAR2(100 CHAR) not null,
  ORG_ID         VARCHAR2(50 CHAR),
  ROL_CH_NAME    VARCHAR2(40 CHAR) not null,
  ROL_IS_DEFAULT VARCHAR2(1) default '0' not null,
  ROL_MEMO       VARCHAR2(100 CHAR),
  ROL_STATUS     VARCHAR2(1) default '1' not null,
  ROL_TYPE       VARCHAR2(1 CHAR),
  ROL_PARENT_ID  VARCHAR2(50 CHAR),
  DEPT_ID        VARCHAR2(36 CHAR),
  CREATE_TIME    DATE,
  ROL_SORT       NUMBER(19)
);

-- Add comments to the columns 
comment on column AUTH_ROLE.ROL_ID
  is '角色——ID';
comment on column AUTH_ROLE.ROL_EN_NAME
  is '角色——英文名称';
comment on column AUTH_ROLE.ORG_ID
  is '部分——ID';
comment on column AUTH_ROLE.ROL_CH_NAME
  is '角色——中文名称';
comment on column AUTH_ROLE.ROL_IS_DEFAULT
  is '角色——是否是默认值';
comment on column AUTH_ROLE.ROL_MEMO
  is '角色——备注';
comment on column AUTH_ROLE.ROL_STATUS
  is '角色——状态';
comment on column AUTH_ROLE.ROL_TYPE
  is '角色——类型';
comment on column AUTH_ROLE.ROL_PARENT_ID
  is '角色——上级ID';

-- Create table
create table AUTH_ROLE_EXTEND
(
  EXTEND_ROLE_ID VARCHAR2(50 CHAR) not null,
  ROL_ID         VARCHAR2(50 CHAR) not null
);

-- Add comments to the columns 
comment on column AUTH_ROLE_EXTEND.EXTEND_ROLE_ID
  is '角色——扩展ID';
comment on column AUTH_ROLE_EXTEND.ROL_ID
  is '角色——ID';

-- Create table
create table AUTH_ROLE_RESOURCE
(
  ROL_ID    VARCHAR2(50 CHAR),
  RES_ID    VARCHAR2(50 CHAR),
  ISCHECKED VARCHAR2(1 CHAR) default '0'
);

-- Add comments to the columns 
comment on column AUTH_ROLE_RESOURCE.ROL_ID
  is '角色ID';
comment on column AUTH_ROLE_RESOURCE.RES_ID
  is '资源ID';
comment on column AUTH_ROLE_RESOURCE.ISCHECKED
  is '是否全选：2代表部分选中，1代表全部选中。';

-- Create table
create table AUTH_USER
(
  USR_ID                  VARCHAR2(50 CHAR) not null,
  USR_NAME                VARCHAR2(20 CHAR) not null,
  USR_CA_SN               VARCHAR2(100 CHAR),
  USR_IS_CREDENTIAL       VARCHAR2(1 CHAR) default '0' not null,
  USR_IS_EXPIRED          VARCHAR2(1 CHAR) default '0' not null,
  USR_IS_LOCKED           VARCHAR2(1 CHAR) default '0' not null,
  USR_PASSWORD            VARCHAR2(100 CHAR) not null,
  USR_PERIOD_VALIDITY     DATE,
  USR_PWD_IS_EXPIRED      VARCHAR2(1 CHAR) default '0' not null,
  USR_PWD_TIP             VARCHAR2(30 CHAR),
  USR_PWD_TIP_ANS         VARCHAR2(30 CHAR),
  USR_SORT                NUMBER(19),
  USR_STATUS              VARCHAR2(1 CHAR) default '1' not null,
  USR_PWD_PERIOD_VALIDITY DATE,
  USR_IS_USE_IPCHECK      VARCHAR2(1 CHAR) default '0' not null,
  USR_IS_ADMIN            VARCHAR2(1 CHAR) default '0' not null,
  USR_IPPOLICY            VARCHAR2(255 CHAR),
  USR_CREATED_DATE        DATE,
  USR_CHANGEPWD           VARCHAR2(1 CHAR) default '1' not null,
  CREATE_TIME             DATE,
  EMP_ID                  VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column AUTH_USER.USR_ID
  is '用户——ID';
comment on column AUTH_USER.USR_NAME
  is '用户——姓名';
comment on column AUTH_USER.USR_CA_SN
  is '用户——认证管理员序号';
comment on column AUTH_USER.USR_IS_CREDENTIAL
  is '用户——是否受信';
comment on column AUTH_USER.USR_IS_EXPIRED
  is '用户——是否到期';
comment on column AUTH_USER.USR_IS_LOCKED
  is '用户——是否锁定';
comment on column AUTH_USER.USR_PASSWORD
  is '用户——密码';
comment on column AUTH_USER.USR_PERIOD_VALIDITY
  is '用户——有效期';
comment on column AUTH_USER.USR_PWD_IS_EXPIRED
  is '用户——密码是否到期';
comment on column AUTH_USER.USR_PWD_TIP
  is '用户——密码提示';
comment on column AUTH_USER.USR_PWD_TIP_ANS
  is '用户——密码提示答案';
comment on column AUTH_USER.USR_SORT
  is '用户——排序';
comment on column AUTH_USER.USR_STATUS
  is '用户——状态';
comment on column AUTH_USER.USR_PWD_PERIOD_VALIDITY
  is '用户——密码有效期';
comment on column AUTH_USER.USR_IS_USE_IPCHECK
  is '用户——是否使用验证IP';
comment on column AUTH_USER.USR_IS_ADMIN
  is '用户——是否超级管理员';
comment on column AUTH_USER.USR_IPPOLICY
  is '用户——IP策略';
comment on column AUTH_USER.USR_CREATED_DATE
  is '用户——创建日期';
comment on column AUTH_USER.USR_CHANGEPWD
  is '用户——是否修改密码';
comment on column AUTH_USER.EMP_ID
  is '员工——ID';

-- Create table
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

-- Create table
create table AUTH_USER_ROLE
(
  USR_ID VARCHAR2(50 CHAR) not null,
  ROL_ID VARCHAR2(50 CHAR) not null
);

-- Add comments to the columns 
comment on column AUTH_USER_ROLE.USR_ID
  is '用户ID';
comment on column AUTH_USER_ROLE.ROL_ID
  is '角色ID';

-- Create table
create table BASE_ATTACHMENT
(
  RES_ID          VARCHAR2(50 CHAR) not null,
  ATT_CREATOR     VARCHAR2(50 CHAR),
  ATT_FILE_NAME   VARCHAR2(100 CHAR),
  ATT_FILE_SIZE   NUMBER(10),
  ATT_FILE_TYPE   VARCHAR2(50 CHAR),
  ATT_PATH        VARCHAR2(1000 CHAR),
  ATT_RELATION_ID VARCHAR2(50 CHAR),
  ATT_REMARK      VARCHAR2(200 CHAR),
  ATT_SORTNO      NUMBER(10),
  ATT_UPLOAD_IP   VARCHAR2(50 CHAR),
  ATT_UPLOAD_TIME DATE,
  CREATE_TIME     DATE,
  ATT_VIEW_NAME   VARCHAR2(200 CHAR)
);

-- Add comments to the columns 
comment on column BASE_ATTACHMENT.RES_ID
  is '资源——ID';
comment on column BASE_ATTACHMENT.ATT_CREATOR
  is '附件——创建者';
comment on column BASE_ATTACHMENT.ATT_FILE_NAME
  is '附件——文件名称';
comment on column BASE_ATTACHMENT.ATT_FILE_SIZE
  is '附件——文件大小';
comment on column BASE_ATTACHMENT.ATT_FILE_TYPE
  is '附件——文件类型';
comment on column BASE_ATTACHMENT.ATT_PATH
  is '附件——路径';
comment on column BASE_ATTACHMENT.ATT_RELATION_ID
  is '附件——关联ID';
comment on column BASE_ATTACHMENT.ATT_REMARK
  is '附件——备注';
comment on column BASE_ATTACHMENT.ATT_SORTNO
  is '附件——排序号码';
comment on column BASE_ATTACHMENT.ATT_UPLOAD_IP
  is '附件——上传IP';
comment on column BASE_ATTACHMENT.ATT_UPLOAD_TIME
  is '附件——上传时间';
comment on column BASE_ATTACHMENT.ATT_VIEW_NAME
  is '附件——显示名称';

-- Create table
create table BASE_DICTIONARY
(
  DIC_ID         VARCHAR2(50 CHAR) not null,
  DIC_NAME       VARCHAR2(20 CHAR) not null,
  DIC_GROUP_CODE VARCHAR2(60 CHAR),
  DIC_CACHE_NAME VARCHAR2(20 CHAR),
  DIC_USE_CACHE  VARCHAR2(1 CHAR),
  DIC_VALUE      VARCHAR2(50 CHAR),
  CREATE_TIME    DATE,
  DIC_MEMO       VARCHAR2(200 CHAR)
);

-- Add comments to the columns 
comment on column BASE_DICTIONARY.DIC_ID
  is '字典——ID';
comment on column BASE_DICTIONARY.DIC_NAME
  is '字典——名称';
comment on column BASE_DICTIONARY.DIC_GROUP_CODE
  is '字典——分组编号';
comment on column BASE_DICTIONARY.DIC_CACHE_NAME
  is '字典——缓存名称';
comment on column BASE_DICTIONARY.DIC_USE_CACHE
  is '字典——是否使用缓存';
comment on column BASE_DICTIONARY.DIC_VALUE
  is '字典——取值';
comment on column BASE_DICTIONARY.DIC_MEMO
  is '字典——备注';

-- Create table
create table BASE_DICTIONARY_TYPE
(
  DIC_GROUP_CODE VARCHAR2(60 CHAR) not null,
  CREATE_TIME    DATE,
  DIC_GROUP_NAME VARCHAR2(60 CHAR) not null
);

-- Add comments to the columns 
comment on column BASE_DICTIONARY_TYPE.DIC_GROUP_CODE
  is '字典——分组编号';
comment on column BASE_DICTIONARY_TYPE.DIC_GROUP_NAME
  is '字典——分组名称';

-- Create table
create table BASE_DISTRICT
(
  DISTRICT_ID         VARCHAR2(50 CHAR) not null,
  DISTRICT_CODE       VARCHAR2(40 CHAR),
  DISTRICT_NAME       VARCHAR2(60 CHAR),
  DISTRICT_SHORT_NAME VARCHAR2(40 CHAR),
  CREATE_TIME         DATE,
  DISTRICT_PARENT_ID  VARCHAR2(50 CHAR)
);

-- Add comments to the columns 
comment on column BASE_DISTRICT.DISTRICT_ID
  is '区域——ID';
comment on column BASE_DISTRICT.DISTRICT_CODE
  is '区域——编号';
comment on column BASE_DISTRICT.DISTRICT_NAME
  is '区域——名称';
comment on column BASE_DISTRICT.DISTRICT_SHORT_NAME
  is '区域——简称';
comment on column BASE_DISTRICT.DISTRICT_PARENT_ID
  is '区域——上级ID';

-- Create table
create table BASE_INDUSTRY
(
  INDUSTRY_ID        VARCHAR2(36 CHAR) not null,
  INDUSTRY_MEMO      VARCHAR2(100 CHAR),
  INDUSTRY_NAME      VARCHAR2(50 CHAR),
  INDUSTRY_SORT_NO   VARCHAR2(10 CHAR),
  CREATE_TIME        DATE,
  INDUSTRY_PARENT_ID VARCHAR2(36 CHAR)
);

-- Add comments to the columns 
comment on column BASE_INDUSTRY.INDUSTRY_ID
  is '行业——ID';
comment on column BASE_INDUSTRY.INDUSTRY_MEMO
  is '行业——备注';
comment on column BASE_INDUSTRY.INDUSTRY_NAME
  is '行业——名称';
comment on column BASE_INDUSTRY.INDUSTRY_SORT_NO
  is '行业——排序号';
comment on column BASE_INDUSTRY.INDUSTRY_PARENT_ID
  is '行业——上级ID';
-- Create table
create table BASE_LOGIN_LOGS
(
  LGN_ID       VARCHAR2(50 CHAR) not null,
  LGN_FROM_IP  VARCHAR2(20 CHAR),
  LGN_IN_TIME  DATE,
  LGN_OUT_TIME DATE,
  CREATE_TIME  DATE,
  USR_ID       VARCHAR2(50 CHAR)
);

-- Add comments to the table 
comment on table BASE_LOGIN_LOGS
  is '基础表_登录_日志';
-- Add comments to the columns 
comment on column BASE_LOGIN_LOGS.LGN_ID
  is '登录日志——ID';
comment on column BASE_LOGIN_LOGS.LGN_FROM_IP
  is '登录日志——登入IP';
comment on column BASE_LOGIN_LOGS.LGN_IN_TIME
  is '登录日志——登入时间';
comment on column BASE_LOGIN_LOGS.LGN_OUT_TIME
  is '登录日志——登出时间';
comment on column BASE_LOGIN_LOGS.USR_ID
  is '【关联项】用户——ID，用于关联AUTH_USER表的USR_ID。';
-- Create table
create table BASE_OPER_LOGS
(
  LOG_ID          VARCHAR2(50 CHAR) not null,
  LOG_CLASS_NAME  VARCHAR2(60 CHAR),
  LOG_RES_NAME    VARCHAR2(50 CHAR),
  LOG_URL         VARCHAR2(100 CHAR),
  LOG_USR_IP      VARCHAR2(20 CHAR),
  LOG_VISIT_DATE  DATE,
  LOG_METHOD_NAME VARCHAR2(60 CHAR),
  ORGNAME         VARCHAR2(50 CHAR),
  LOGIN_LOG_ID    VARCHAR2(50 CHAR),
  CREATE_TIME     DATE,
  LOG_USR_ID      VARCHAR2(50 CHAR)
);

-- Add comments to the table 
comment on table BASE_OPER_LOGS
  is '基础表_操作_日志';
-- Add comments to the columns 
comment on column BASE_OPER_LOGS.LOG_ID
  is '操作日志——ID';
comment on column BASE_OPER_LOGS.LOG_CLASS_NAME
  is '操作日志——分类名称';
comment on column BASE_OPER_LOGS.LOG_RES_NAME
  is '操作日志——资源名称';
comment on column BASE_OPER_LOGS.LOG_URL
  is '操作日志——URL地址';
comment on column BASE_OPER_LOGS.LOG_USR_IP
  is '操作日志——用户IP';
comment on column BASE_OPER_LOGS.LOG_VISIT_DATE
  is '操作日志——访问日期';
comment on column BASE_OPER_LOGS.LOG_METHOD_NAME
  is '操作日志——方法名称';
comment on column BASE_OPER_LOGS.ORGNAME
  is '机构名称';
comment on column BASE_OPER_LOGS.LOGIN_LOG_ID
  is '【关联项】登录日志——ID，用于关系BASE_LOGIN_LOGS表的LGN_ID。';
comment on column BASE_OPER_LOGS.LOG_USR_ID
  is '（弃用）日志用户——ID，原设计和现有设计均为使用此字段，字段可用于关联AUTH_USER表的USR_ID，目前是通过BASE_LOGIN_LOGS表间接关联的AUTH_USER表。';
-- Create table
create table BASE_SEQUENCE_NUMBER
(
  SEN_ID          VARCHAR2(50 CHAR) not null,
  SEN_BIZ_NAME    VARCHAR2(100 CHAR),
  CREATE_TIME     DATE,
  SEN_SEQUENCE_NO VARCHAR2(100 CHAR)
);

-- Add comments to the columns 
comment on column BASE_SEQUENCE_NUMBER.SEN_ID
  is '序列——ID';
comment on column BASE_SEQUENCE_NUMBER.SEN_BIZ_NAME
  is '序列——业务名称';
comment on column BASE_SEQUENCE_NUMBER.SEN_SEQUENCE_NO
  is '序列——号码';
-- Create table
create table BASE_SYSCONFIGITEM
(
  ITEM_ID        VARCHAR2(36) not null,
  TYPE_ID        VARCHAR2(36) not null,
  ITEM_CODE      VARCHAR2(50) not null,
  ITEM_NAME      VARCHAR2(50) not null,
  ITEM_DATA_TYPE VARCHAR2(20) not null,
  ITEM_SORT      NUMBER(8,2) default 0 not null,
  ITEM_NOTES     VARCHAR2(1000) not null,
  ITEM_PATH      VARCHAR2(100) not null,
  CREATE_TIME    DATE not null
);

-- Add comments to the table 
comment on table BASE_SYSCONFIGITEM
  is '系统配置条目';
-- Add comments to the columns 
comment on column BASE_SYSCONFIGITEM.TYPE_ID
  is '配置类型ID';
comment on column BASE_SYSCONFIGITEM.ITEM_CODE
  is '编号';
comment on column BASE_SYSCONFIGITEM.ITEM_NAME
  is '配置条目名称';
comment on column BASE_SYSCONFIGITEM.ITEM_DATA_TYPE
  is '条目数据类型';
comment on column BASE_SYSCONFIGITEM.ITEM_SORT
  is '配置条目排列序号';
comment on column BASE_SYSCONFIGITEM.ITEM_NOTES
  is '备注[必须要写明说明]';
comment on column BASE_SYSCONFIGITEM.ITEM_PATH
  is '系统配置条目编号所处的从根到叶子节点的路径[冗余]';
comment on column BASE_SYSCONFIGITEM.CREATE_TIME
  is '创建时间';
-- Create table
create table BASE_SYSCONFIGITEM_VALUE
(
  ITEM_VALUE_ID     VARCHAR2(36) not null,
  ITEM_ID           VARCHAR2(36) not null,
  ITEM_VALUE_DETAIL VARCHAR2(1000) not null,
  PRE_VALUE_ID      VARCHAR2(36),
  PRE_VALUE_NAME    VARCHAR2(50),
  ITEM_VALUE_NOTES  VARCHAR2(1000),
  CREATE_TIME       DATE not null
);

-- Add comments to the table 
comment on table BASE_SYSCONFIGITEM_VALUE
  is '参数配置条目对应数据';
-- Add comments to the columns 
comment on column BASE_SYSCONFIGITEM_VALUE.ITEM_VALUE_ID
  is '配置值主键';
comment on column BASE_SYSCONFIGITEM_VALUE.ITEM_ID
  is '系统配置条目ID';
comment on column BASE_SYSCONFIGITEM_VALUE.ITEM_VALUE_DETAIL
  is '配置值';
comment on column BASE_SYSCONFIGITEM_VALUE.PRE_VALUE_ID
  is '预设ID[若数据类型是枚举,则有效]';
comment on column BASE_SYSCONFIGITEM_VALUE.PRE_VALUE_NAME
  is '预设值名称[枚举类显示名称,冗余]';
comment on column BASE_SYSCONFIGITEM_VALUE.ITEM_VALUE_NOTES
  is '备注';
comment on column BASE_SYSCONFIGITEM_VALUE.CREATE_TIME
  is '创建时间';
-- Create table
create table BASE_SYSCONFIG_PRE_VALUES
(
  PRE_VALUE_ID    VARCHAR2(36) not null,
  ITEM_ID         VARCHAR2(36) not null,
  PRE_VALUE_NAME  VARCHAR2(50) not null,
  PRE_VALUE_CODE  VARCHAR2(50) not null,
  PRE_VALUE_SORT  NUMBER(8,2) default 0 not null,
  PRE_VALUE_NOTES VARCHAR2(1000) not null,
  CREATE_TIME     DATE not null
);

-- Add comments to the table 
comment on table BASE_SYSCONFIG_PRE_VALUES
  is '系统配置条目数据预设值[只针对权举类有效]';
-- Add comments to the columns 
comment on column BASE_SYSCONFIG_PRE_VALUES.ITEM_ID
  is '系统配置条目ID';
comment on column BASE_SYSCONFIG_PRE_VALUES.PRE_VALUE_NAME
  is '预设值名称[枚举类名称]';
comment on column BASE_SYSCONFIG_PRE_VALUES.PRE_VALUE_CODE
  is '预设值编号[枚举类具体值]';
comment on column BASE_SYSCONFIG_PRE_VALUES.PRE_VALUE_SORT
  is '排序号';
comment on column BASE_SYSCONFIG_PRE_VALUES.PRE_VALUE_NOTES
  is '备注[必填写]';
comment on column BASE_SYSCONFIG_PRE_VALUES.CREATE_TIME
  is '创建时间';
-- Create table
create table BASE_SYSCONFIG_TYPE
(
  TYPE_ID        VARCHAR2(36) not null,
  TYPE_PARENT_ID VARCHAR2(36),
  TYPE_NAME      VARCHAR2(50) not null,
  TYPE_CODE      VARCHAR2(50) not null,
  TYPE_KIND      VARCHAR2(50),
  TYPE_IS_LEAF   CHAR(1) default '1',
  TYPE_SORT      NUMBER(8,2) default 0 not null,
  TYPE_NOTES     VARCHAR2(1000) not null,
  TYPE_LEVEL     NUMBER(8,2),
  TYPE_PATH      VARCHAR2(100) not null,
  CREATE_TIME    DATE not null
);

-- Add comments to the table 
comment on table BASE_SYSCONFIG_TYPE
  is '系统配置类型';
-- Add comments to the columns 
comment on column BASE_SYSCONFIG_TYPE.TYPE_PARENT_ID
  is '父类配置类型ID';
comment on column BASE_SYSCONFIG_TYPE.TYPE_NAME
  is '类型名称';
comment on column BASE_SYSCONFIG_TYPE.TYPE_CODE
  is '类型编号';
comment on column BASE_SYSCONFIG_TYPE.TYPE_KIND
  is '系统配置类型种类[开发参数,部署参数等]';
comment on column BASE_SYSCONFIG_TYPE.TYPE_IS_LEAF
  is '是否叶子节点';
comment on column BASE_SYSCONFIG_TYPE.TYPE_SORT
  is '排序';
comment on column BASE_SYSCONFIG_TYPE.TYPE_NOTES
  is '备注[必须填写]';
comment on column BASE_SYSCONFIG_TYPE.TYPE_LEVEL
  is '树型层次';
comment on column BASE_SYSCONFIG_TYPE.TYPE_PATH
  is '系统配置类型编号所处的从根到叶子节点的路径[冗余]';
comment on column BASE_SYSCONFIG_TYPE.CREATE_TIME
  is '创建时间';

-- Create table
create table DEMO_ADDITIONAL
(
  ADDITIONAL_NAME VARCHAR2(100),
  ADDITIONAL_DESC VARCHAR2(100),
  GOODS_ID        VARCHAR2(36),
  ADDITIONAL_ID   VARCHAR2(36)
);

-- Create table
create table DEMO_AREA
(
  ARAE_ID    VARCHAR2(36) not null,
  EN_NAME    VARCHAR2(100),
  CN_NAME    VARCHAR2(100),
  CN_PY_NAME VARCHAR2(100),
  SUB_NAME   VARCHAR2(100)
);

-- Create table
create table DEMO_BRAND
(
  BRAND_ID      VARCHAR2(36) not null,
  BRAND_NAME    VARCHAR2(100),
  BRAND_FACTORY VARCHAR2(100)
);

-- Create table
create table DEMO_GOODS
(
  GOODS_ID       VARCHAR2(36) not null,
  GOODS_NAME     VARCHAR2(100),
  GOODS_CODE     VARCHAR2(100),
  BRAND_ID       VARCHAR2(36),
  GOODS_CLASS_ID VARCHAR2(36),
  CREATE_DATE    DATE,
  MANAGER_STATUS CHAR(1) default 0,
  CHECK_STATUS   CHAR(2) default 0
);

-- Create table
create table DEMO_GOODS_CLASS
(
  GOODS_CLASS_ID       VARCHAR2(36),
  CLASS_CODE           VARCHAR2(100),
  CLASS_NAME           VARCHAR2(100),
  PARAM_INPUT_TYPE     CHAR(1),
  PARENT_ID            VARCHAR2(36),
  PURCHASE_CATEGORY_ID VARCHAR2(36),
  PATH                 VARCHAR2(200),
  NODE_LEVEL           CHAR(1),
  NODE_ISLEAF          CHAR(1)
);

-- Create table
create table DEMO_GOODS_ORDER
(
  GOODS_ORDER_ID VARCHAR2(36),
  GOODS_ID       VARCHAR2(36),
  ORDER_ID       VARCHAR2(36)
);

-- Create table
create table DEMO_GOODS_PARAM
(
  GOODS_PARAM_ID   VARCHAR2(36) not null,
  GOODS_PARAM_NAME VARCHAR2(100),
  GOODS_PARAM_CODE VARCHAR2(100)
);

-- Create table
create table DEMO_OPTION
(
  OPTION_ID   VARCHAR2(36) not null,
  OPTION_NAME VARCHAR2(100),
  OPTION_CODE VARCHAR2(100),
  GOODS_ID    VARCHAR2(36)
);

-- Create table
create table DEMO_ORDER
(
  ORDER_ID   VARCHAR2(36) not null,
  ORDER_NAME VARCHAR2(100),
  ORDER_CODE VARCHAR2(100)
);

-- Create table
create table DEMO_PURCHASE_CATEGORY
(
  PURCHASE_CATEGORY_ID VARCHAR2(36),
  PARENT_ID            VARCHAR2(36),
  CATEGORY_CODE        VARCHAR2(100),
  CATEGORY_NAME        VARCHAR2(100),
  CATEGORY_NOTE        VARCHAR2(100),
  CATEGORY_VERID       NUMBER
);

-- Create table
create table ORG_EMPLOYEE
(
  EMP_ID            VARCHAR2(36) not null,
  EMP_NAME          VARCHAR2(50) not null,
  EMP_MOBILE        VARCHAR2(20),
  EMP_EMAIL         VARCHAR2(20),
  EMP_MSN           VARCHAR2(20),
  EMP_QQ            VARCHAR2(20),
  EMP_TEL_OFFICE    VARCHAR2(20),
  EMP_TEL_HOME      VARCHAR2(20),
  EMP_SORT          NUMBER(8,2),
  EMP_CREATED_DATE  DATE,
  USR_ID            VARCHAR2(36),
  EMP_COMPANY_ID    VARCHAR2(36),
  EMP_DEPARTMENT_ID VARCHAR2(36),
  CREATE_TIME       DATE,
  EMP_POST_ID       VARCHAR2(36)
);

-- Create table
create table ORG_ROLE
(
  RULE_ID     VARCHAR2(36 CHAR) not null,
  ORG_RULE    VARCHAR2(50 CHAR),
  ORG_SOURCE  CHAR(1 CHAR),
  ORG_TARGET  CHAR(1 CHAR),
  STR_ID      VARCHAR2(36 CHAR) not null,
  STR_NAME    VARCHAR2(20 CHAR),
  ORG_ID      VARCHAR2(36 CHAR),
  CREATE_TIME DATE,
  ROL_ID      VARCHAR2(36 CHAR)
);
  
    create table JBPM4_DEPLOYMENT (
        DBID_ number(19,0) not null,
        NAME_ clob,
        TIMESTAMP_ number(19,0),
        STATE_ varchar2(255 char),
        primary key (DBID_)
    );

    create table JBPM4_DEPLOYPROP (
        DBID_ number(19,0) not null,
        DEPLOYMENT_ number(19,0),
        OBJNAME_ varchar2(255 char),
        KEY_ varchar2(255 char),
        STRINGVAL_ varchar2(255 char),
        LONGVAL_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_EXECUTION (
        DBID_ number(19,0) not null,
        CLASS_ varchar2(255 char) not null,
        DBVERSION_ number(10,0) not null,
        ACTIVITYNAME_ varchar2(255 char),
        PROCDEFID_ varchar2(255 char),
        HASVARS_ number(1,0),
        NAME_ varchar2(255 char),
        KEY_ varchar2(255 char),
        ID_ varchar2(255 char) unique,
        STATE_ varchar2(255 char),
        SUSPHISTSTATE_ varchar2(255 char),
        PRIORITY_ number(10,0),
        HISACTINST_ number(19,0),
        PARENT_ number(19,0),
        INSTANCE_ number(19,0),
        SUPEREXEC_ number(19,0),
        SUBPROCINST_ number(19,0),
        PARENT_IDX_ number(10,0),
        primary key (DBID_)
    );

    create table JBPM4_HIST_ACTINST (
        DBID_ number(19,0) not null,
        CLASS_ varchar2(255 char) not null,
        DBVERSION_ number(10,0) not null,
        HPROCI_ number(19,0),
        TYPE_ varchar2(255 char),
        EXECUTION_ varchar2(255 char),
        ACTIVITY_NAME_ varchar2(255 char),
        START_ timestamp,
        END_ timestamp,
        DURATION_ number(19,0),
        TRANSITION_ varchar2(255 char),
        NEXTIDX_ number(10,0),
        HTASK_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_HIST_DETAIL (
        DBID_ number(19,0) not null,
        CLASS_ varchar2(255 char) not null,
        DBVERSION_ number(10,0) not null,
        USERID_ varchar2(255 char),
        TIME_ timestamp,
        HPROCI_ number(19,0),
        HPROCIIDX_ number(10,0),
        HACTI_ number(19,0),
        HACTIIDX_ number(10,0),
        HTASK_ number(19,0),
        HTASKIDX_ number(10,0),
        HVAR_ number(19,0),
        HVARIDX_ number(10,0),
        MESSAGE_ clob,
        OLD_STR_ varchar2(255 char),
        NEW_STR_ varchar2(255 char),
        OLD_INT_ number(10,0),
        NEW_INT_ number(10,0),
        OLD_TIME_ timestamp,
        NEW_TIME_ timestamp,
        PARENT_ number(19,0),
        PARENT_IDX_ number(10,0),
        primary key (DBID_)
    );

    create table JBPM4_HIST_PROCINST (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        ID_ varchar2(255 char),
        PROCDEFID_ varchar2(255 char),
        KEY_ varchar2(255 char),
        START_ timestamp,
        END_ timestamp,
        DURATION_ number(19,0),
        STATE_ varchar2(255 char),
        ENDACTIVITY_ varchar2(255 char),
        NEXTIDX_ number(10,0),
        primary key (DBID_)
    );

    create table JBPM4_HIST_TASK (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        EXECUTION_ varchar2(255 char),
        OUTCOME_ varchar2(255 char),
        ASSIGNEE_ varchar2(255 char),
        PRIORITY_ number(10,0),
        STATE_ varchar2(255 char),
        CREATE_ timestamp,
        END_ timestamp,
        DURATION_ number(19,0),
        NEXTIDX_ number(10,0),
        SUPERTASK_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_HIST_VAR (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        PROCINSTID_ varchar2(255 char),
        EXECUTIONID_ varchar2(255 char),
        VARNAME_ varchar2(255 char),
        VALUE_ varchar2(255 char),
        HPROCI_ number(19,0),
        HTASK_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_ID_GROUP (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        ID_ varchar2(255 char),
        NAME_ varchar2(255 char),
        TYPE_ varchar2(255 char),
        PARENT_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_ID_MEMBERSHIP (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        USER_ number(19,0),
        GROUP_ number(19,0),
        NAME_ varchar2(255 char),
        primary key (DBID_)
    );

    create table JBPM4_ID_USER (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        ID_ varchar2(255 char),
        PASSWORD_ varchar2(255 char),
        GIVENNAME_ varchar2(255 char),
        FAMILYNAME_ varchar2(255 char),
        BUSINESSEMAIL_ varchar2(255 char),
        primary key (DBID_)
    );

    create table JBPM4_JOB (
        DBID_ number(19,0) not null,
        CLASS_ varchar2(255 char) not null,
        DBVERSION_ number(10,0) not null,
        DUEDATE_ timestamp,
        STATE_ varchar2(255 char),
        ISEXCLUSIVE_ number(1,0),
        LOCKOWNER_ varchar2(255 char),
        LOCKEXPTIME_ timestamp,
        EXCEPTION_ clob,
        RETRIES_ number(10,0),
        PROCESSINSTANCE_ number(19,0),
        EXECUTION_ number(19,0),
        CFG_ number(19,0),
        SIGNAL_ varchar2(255 char),
        EVENT_ varchar2(255 char),
        REPEAT_ varchar2(255 char),
        primary key (DBID_)
    );

    create table JBPM4_LOB (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        BLOB_VALUE_ blob,
        DEPLOYMENT_ number(19,0),
        NAME_ clob,
        primary key (DBID_)
    );

    create table JBPM4_PARTICIPATION (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        GROUPID_ varchar2(255 char),
        USERID_ varchar2(255 char),
        TYPE_ varchar2(255 char),
        TASK_ number(19,0),
        SWIMLANE_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_PROPERTY (
        KEY_ varchar2(255 char) not null,
        VERSION_ number(10,0) not null,
        VALUE_ varchar2(255 char),
        primary key (KEY_)
    );

    create table JBPM4_SWIMLANE (
        DBID_ number(19,0) not null,
        DBVERSION_ number(10,0) not null,
        NAME_ varchar2(255 char),
        ASSIGNEE_ varchar2(255 char),
        EXECUTION_ number(19,0),
        primary key (DBID_)
    );

    create table JBPM4_TASK (
        DBID_ number(19,0) not null,
        CLASS_ char(1 char) not null,
        DBVERSION_ number(10,0) not null,
        NAME_ varchar2(255 char),
        DESCR_ clob,
        STATE_ varchar2(255 char),
        SUSPHISTSTATE_ varchar2(255 char),
        ASSIGNEE_ varchar2(255 char),
        FORM_ varchar2(255 char),
        PRIORITY_ number(10,0),
        CREATE_ timestamp,
        DUEDATE_ timestamp,
        PROGRESS_ number(10,0),
        SIGNALLING_ number(1,0),
        EXECUTION_ID_ varchar2(255 char),
        ACTIVITY_NAME_ varchar2(255 char),
        HASVARS_ number(1,0),
        SUPERTASK_ number(19,0),
        EXECUTION_ number(19,0),
        PROCINST_ number(19,0),
        SWIMLANE_ number(19,0),
        TASKDEFNAME_ varchar2(255 char),
        primary key (DBID_)
    );

    create table JBPM4_VARIABLE (
        DBID_ number(19,0) not null,
        CLASS_ varchar2(255 char) not null,
        DBVERSION_ number(10,0) not null,
        KEY_ varchar2(255 char),
        CONVERTER_ varchar2(255 char),
        HIST_ number(1,0),
        EXECUTION_ number(19,0),
        TASK_ number(19,0),
        LOB_ number(19,0),
        DATE_VALUE_ timestamp,
        DOUBLE_VALUE_ double precision,
        CLASSNAME_ varchar2(255 char),
        LONG_VALUE_ number(19,0),
        STRING_VALUE_ varchar2(255 char),
        TEXT_VALUE_ clob,
        EXESYS_ number(19,0),
        primary key (DBID_)
    );

-- Create/Recreate primary, unique key constraints

alter table ORG_ROLE
  add primary key (STR_ID)
  using index;
  
alter table BASE_SYSCONFIGITEM
  add constraint PK_B_SYSC_ITEM primary key (ITEM_ID)
  using index;
  
alter table ORG_EMPLOYEE
  add constraint PK_ORG_EMPLOYEE primary key (EMP_ID)
  using index;
  
alter table ORG_EMPLOYEE
  add constraint UQ_ORG_EMPLOYEE_EMP_EMAIL unique (EMP_EMAIL)
  using index;
  
alter table BASE_SYSCONFIG_TYPE
  add constraint PK_B_SYSC_TYPE primary key (TYPE_ID)
  using index;
  
alter table BASE_SYSCONFIG_TYPE
  add constraint UQ_BASE_SYSCONFIG_T_TYPE_PATH unique (TYPE_PATH)
  using index;
  
alter table BASE_SYSCONFIG_PRE_VALUES
  add constraint PK_B_SYSC_P_VAS primary key (PRE_VALUE_ID)
  using index;
  
alter table BASE_SYSCONFIGITEM_VALUE
  add constraint PK_B_SYSC_I_VAS primary key (ITEM_VALUE_ID)
  using index;
  
alter table BASE_SEQUENCE_NUMBER
  add constraint PK_SEN_ID primary key (SEN_ID)
  using index;
  
alter table BASE_OPER_LOGS
  add constraint PK_LOG_ID primary key (LOG_ID)
  using index;
  
alter table BASE_LOGIN_LOGS
  add constraint PK_LOGIN_ID primary key (LGN_ID)
  using index;

alter table BASE_INDUSTRY
  add constraint PK_INDUSTRY_ID primary key (INDUSTRY_ID)
  using index;
  
alter table BASE_DISTRICT
  add constraint PK_DISTRICT_ID primary key (DISTRICT_ID)
  using index;

alter table BASE_DICTIONARY_TYPE
  add constraint PK_GROUP_CODE_ID primary key (DIC_GROUP_CODE)
  using index;
  
alter table BASE_DICTIONARY
  add constraint PK_DIC_ID primary key (DIC_ID)
  using index;
  
alter table BASE_ATTACHMENT
  add constraint PK_ATT_RES_ID primary key (RES_ID)
  using index;
  
alter table AUTH_USER_ROLE
  add constraint PK_ROL_USR_ID primary key (ROL_ID, USR_ID)
  using index;
  
alter table AUTH_USER_GADGET
  add constraint KEYYYY primary key (OBJID)
  using index;
  
alter table AUTH_USER
  add constraint PK_USR_ID primary key (USR_ID)
  using index;
  
alter table AUTH_ROLE_EXTEND
  add constraint PK_EXTEND_ROLE_ID primary key (EXTEND_ROLE_ID, ROL_ID)
  using index;

alter table AUTH_ROLE
  add constraint PK_ROL_ID primary key (ROL_ID)
  using index;
 
alter table AUTH_RESOURCE
  add constraint PK_RES_ID primary key (RES_ID)
  using index;
  
alter table AUTH_ORG_STRU_TYPE
  add constraint PK_STR_ID primary key (STR_ID)
  using index;
  
alter table AUTH_ORG_EMPLOYEE
  add constraint PK_EMP_ID primary key (EMP_ID)
  using index;
  
alter table AUTH_ORG_DETAIL
  add constraint PK_DETAIL_ID primary key (ORG_DETAIL_ID)
  using index;
  
alter table AUTH_ORGNIZATION_RULE
  add constraint PK_RULE_ID primary key (RULE_ID)
  using index;

alter table AUTH_ORGNIZATION
  add constraint PK_ORG_ID primary key (ORG_ID)
  using index;
  
alter table AUTH_MENU
  add constraint PK_MNU_ID primary key (MNU_ID)
  using index;
  
alter table AUTH_GADGET
  add constraint KEYY primary key (OBJID)
  using index;
  
alter table AUTH_DATA_RES_TYPE
  add constraint PK_DAT_ID primary key (DAT_ID)
  using index;

alter table AUTH_CONFLICT_ROLE
  add constraint PK_CON_ID primary key (CON_ID)
  using index;
 
-- References
alter table ORG_ROLE
  add constraint FK5BD1AACE1DACDE95 foreign key (STR_ID)
  references BASE_DICTIONARY_TYPE (DIC_GROUP_CODE);
  
alter table ORG_ROLE
  add constraint FK5BD1AACE7434FB4A foreign key (ROL_ID)
  references AUTH_ROLE (ROL_ID);
  
alter table ORG_ROLE
  add constraint FK5BD1AACEBB0CA207 foreign key (ORG_ID)
  references AUTH_ORGNIZATION (ORG_ID);

alter table BASE_SYSCONFIG_PRE_VALUES
  add constraint FK_P_T_ITEM_ID foreign key (ITEM_ID)
  references BASE_SYSCONFIGITEM (ITEM_ID);

alter table BASE_SYSCONFIGITEM_VALUE
  add constraint FK_ITEM_ID foreign key (ITEM_ID)
  references BASE_SYSCONFIGITEM (ITEM_ID);

alter table BASE_SYSCONFIGITEM
  add constraint FK_TYPE_ID foreign key (TYPE_ID)
  references BASE_SYSCONFIG_TYPE (TYPE_ID);

alter table BASE_OPER_LOGS
  add constraint FK1CC681123F40EE15 foreign key (LOG_USR_ID)
  references AUTH_USER (USR_ID);
  
alter table BASE_OPER_LOGS
  add constraint FK_OPER_LOGIN_ID foreign key (LOGIN_LOG_ID)
  references BASE_LOGIN_LOGS (LGN_ID);

alter table BASE_LOGIN_LOGS
  add constraint FK_LOGIN_USR_ID foreign key (USR_ID)
  references AUTH_USER (USR_ID);

alter table BASE_INDUSTRY
  add constraint FK_INDU_PARENT_ID foreign key (INDUSTRY_PARENT_ID)
  references BASE_INDUSTRY (INDUSTRY_ID);
  
alter table BASE_DISTRICT
  add constraint FK_DISTRICT_PARENT_ID foreign key (DISTRICT_PARENT_ID)
  references BASE_DISTRICT (DISTRICT_ID);

alter table BASE_DICTIONARY
  add constraint FK63ABF6C4E83302BA foreign key (DIC_GROUP_CODE)
  references BASE_DICTIONARY_TYPE (DIC_GROUP_CODE);

alter table AUTH_USER_ROLE
  add constraint FK_USRROLE_ROLE_ID foreign key (ROL_ID)
  references AUTH_ROLE (ROL_ID);
  
alter table AUTH_USER_ROLE
  add constraint FK_USRROLE_USER_ID foreign key (USR_ID)
  references AUTH_USER (USR_ID);
  
alter table AUTH_USER
  add constraint FK_USER_EMP_ID foreign key (EMP_ID)
  references AUTH_ORG_EMPLOYEE (EMP_ID);

alter table AUTH_ROLE_RESOURCE
  add constraint FK_ROLERES_RES_ID foreign key (RES_ID)
  references AUTH_RESOURCE (RES_ID);
  
alter table AUTH_ROLE_RESOURCE
  add constraint FK_ROLERES_ROL_ID foreign key (ROL_ID)
  references AUTH_ROLE (ROL_ID);

alter table AUTH_ROLE
  add constraint FKFEEDA4AD53D50B06 foreign key (DEPT_ID)
  references AUTH_ORGNIZATION (ORG_ID);
  
alter table AUTH_ROLE
  add constraint FK_ROLE_PARENT_ID foreign key (ROL_PARENT_ID)
  references AUTH_ROLE (ROL_ID);

alter table AUTH_RESOURCE
  add constraint FK_RESOURCE_PARENT_ID foreign key (RES_PARENT_ID)
  references AUTH_RESOURCE (RES_ID);

alter table AUTH_ORG_EMPLOYEE
  add constraint FK_EMP_DEPT_ID foreign key (EMP_DEPARTMENT_ID)
  references AUTH_ORGNIZATION (ORG_ID);
  
alter table AUTH_ORG_EMPLOYEE
  add constraint FK_EMP_POST_ID foreign key (EMP_POST_ID)
  references AUTH_ORGNIZATION (ORG_ID);

alter table AUTH_ORG_DETAIL
  add constraint FK46A2D9233A359EBD foreign key (ORG_REG_CERTIFICATE)
  references BASE_ATTACHMENT (RES_ID);
  
alter table AUTH_ORG_DETAIL
  add constraint FK46A2D9234CE61CB4 foreign key (ORG_NATURE)
  references BASE_DICTIONARY (DIC_ID);
  
alter table AUTH_ORG_DETAIL
  add constraint FK_DETAIL_ATTA_ID foreign key (ORG_LOGO)
  references BASE_ATTACHMENT (RES_ID);
  
alter table AUTH_ORGNIZATION_RULE
  add constraint FK_ORGRULE_STR_ID foreign key (STRU_TYPE_ID)
  references AUTH_ORG_STRU_TYPE (STR_ID);

alter table AUTH_ORGNIZATION
  add constraint FKD31622E3D9C306E6 foreign key (ORG_PARENT_ID)
  references AUTH_ORGNIZATION (ORG_ID);
  
alter table AUTH_ORGNIZATION
  add constraint FK_ORGCITY_DISTRICT_ID foreign key (ORG_CITY)
  references BASE_DISTRICT (DISTRICT_ID);
  
alter table AUTH_ORGNIZATION
  add constraint FK_ORGTOWN_DISTRICT_ID foreign key (ORG_TOWN)
  references BASE_DISTRICT (DISTRICT_ID);
  
alter table AUTH_ORGNIZATION
  add constraint FK_ORG_PROVI_DISTRICT_ID foreign key (ORG_PROVINCE)
  references BASE_DISTRICT (DISTRICT_ID);

alter table AUTH_MENU
  add constraint FK_MENU_PARENT_ID foreign key (MNU_PARENT_ID)
  references AUTH_MENU (MNU_ID);
  
alter table AUTH_MENU
  add constraint FK_MENU_RES_ID foreign key (RES_ID)
  references AUTH_RESOURCE (RES_ID);
  
alter table AUTH_CONFLICT_ROLE
  add constraint FK_CONFLICT_ORG_ID foreign key (ORG_ID)
  references AUTH_ORGNIZATION (ORG_ID);
  
-- jBPM4.3 Database schema

create index IDX_DEPLPROP_DEPL on JBPM4_DEPLOYPROP (DEPLOYMENT_);

alter table JBPM4_DEPLOYPROP 
    add constraint FK_DEPLPROP_DEPL 
    foreign key (DEPLOYMENT_) 
    references JBPM4_DEPLOYMENT;

create index IDX_EXEC_SUPEREXEC on JBPM4_EXECUTION (SUPEREXEC_);

create index IDX_EXEC_INSTANCE on JBPM4_EXECUTION (INSTANCE_);

create index IDX_EXEC_SUBPI on JBPM4_EXECUTION (SUBPROCINST_);

create index IDX_EXEC_PARENT on JBPM4_EXECUTION (PARENT_);

alter table JBPM4_EXECUTION 
    add constraint FK_EXEC_PARENT 
    foreign key (PARENT_) 
    references JBPM4_EXECUTION;

alter table JBPM4_EXECUTION 
    add constraint FK_EXEC_SUBPI 
    foreign key (SUBPROCINST_) 
    references JBPM4_EXECUTION;

alter table JBPM4_EXECUTION 
    add constraint FK_EXEC_INSTANCE 
    foreign key (INSTANCE_) 
    references JBPM4_EXECUTION;

alter table JBPM4_EXECUTION 
    add constraint FK_EXEC_SUPEREXEC 
    foreign key (SUPEREXEC_) 
    references JBPM4_EXECUTION;

create index IDX_HACTI_HPROCI on JBPM4_HIST_ACTINST (HPROCI_);

create index IDX_HTI_HTASK on JBPM4_HIST_ACTINST (HTASK_);

alter table JBPM4_HIST_ACTINST 
    add constraint FK_HACTI_HPROCI 
    foreign key (HPROCI_) 
    references JBPM4_HIST_PROCINST;

alter table JBPM4_HIST_ACTINST 
    add constraint FK_HTI_HTASK 
    foreign key (HTASK_) 
    references JBPM4_HIST_TASK;

create index IDX_HDET_HACTI on JBPM4_HIST_DETAIL (HACTI_);

create index IDX_HDET_HPROCI on JBPM4_HIST_DETAIL (HPROCI_);

create index IDX_HDET_HVAR on JBPM4_HIST_DETAIL (HVAR_);

create index IDX_HDET_HTASK on JBPM4_HIST_DETAIL (HTASK_);

alter table JBPM4_HIST_DETAIL 
    add constraint FK_HDETAIL_HPROCI 
    foreign key (HPROCI_) 
    references JBPM4_HIST_PROCINST;

alter table JBPM4_HIST_DETAIL 
    add constraint FK_HDETAIL_HACTI 
    foreign key (HACTI_) 
    references JBPM4_HIST_ACTINST;

alter table JBPM4_HIST_DETAIL 
    add constraint FK_HDETAIL_HTASK 
    foreign key (HTASK_) 
    references JBPM4_HIST_TASK;

alter table JBPM4_HIST_DETAIL 
    add constraint FK_HDETAIL_HVAR 
    foreign key (HVAR_) 
    references JBPM4_HIST_VAR;

create index IDX_HSUPERT_SUB on JBPM4_HIST_TASK (SUPERTASK_);

alter table JBPM4_HIST_TASK 
    add constraint FK_HSUPERT_SUB 
    foreign key (SUPERTASK_) 
    references JBPM4_HIST_TASK;

create index IDX_HVAR_HPROCI on JBPM4_HIST_VAR (HPROCI_);

create index IDX_HVAR_HTASK on JBPM4_HIST_VAR (HTASK_);

alter table JBPM4_HIST_VAR 
    add constraint FK_HVAR_HPROCI 
    foreign key (HPROCI_) 
    references JBPM4_HIST_PROCINST;

alter table JBPM4_HIST_VAR 
    add constraint FK_HVAR_HTASK 
    foreign key (HTASK_) 
    references JBPM4_HIST_TASK;

create index IDX_GROUP_PARENT on JBPM4_ID_GROUP (PARENT_);

alter table JBPM4_ID_GROUP 
    add constraint FK_GROUP_PARENT 
    foreign key (PARENT_) 
    references JBPM4_ID_GROUP;

create index IDX_MEM_USER on JBPM4_ID_MEMBERSHIP (USER_);

create index IDX_MEM_GROUP on JBPM4_ID_MEMBERSHIP (GROUP_);

alter table JBPM4_ID_MEMBERSHIP 
    add constraint FK_MEM_GROUP 
    foreign key (GROUP_) 
    references JBPM4_ID_GROUP;

alter table JBPM4_ID_MEMBERSHIP 
    add constraint FK_MEM_USER 
    foreign key (USER_) 
    references JBPM4_ID_USER;

create index IDX_JOBRETRIES on JBPM4_JOB (RETRIES_);

create index IDX_JOB_CFG on JBPM4_JOB (CFG_);

create index IDX_JOB_PRINST on JBPM4_JOB (PROCESSINSTANCE_);

create index IDX_JOB_EXE on JBPM4_JOB (EXECUTION_);

create index IDX_JOBLOCKEXP on JBPM4_JOB (LOCKEXPTIME_);

create index IDX_JOBDUEDATE on JBPM4_JOB (DUEDATE_);

alter table JBPM4_JOB 
    add constraint FK_JOB_CFG 
    foreign key (CFG_) 
    references JBPM4_LOB;

create index IDX_LOB_DEPLOYMENT on JBPM4_LOB (DEPLOYMENT_);

alter table JBPM4_LOB 
    add constraint FK_LOB_DEPLOYMENT 
    foreign key (DEPLOYMENT_) 
    references JBPM4_DEPLOYMENT;

create index IDX_PART_TASK on JBPM4_PARTICIPATION (TASK_);

alter table JBPM4_PARTICIPATION 
    add constraint FK_PART_SWIMLANE 
    foreign key (SWIMLANE_) 
    references JBPM4_SWIMLANE;

alter table JBPM4_PARTICIPATION 
    add constraint FK_PART_TASK 
    foreign key (TASK_) 
    references JBPM4_TASK;

create index IDX_SWIMLANE_EXEC on JBPM4_SWIMLANE (EXECUTION_);

alter table JBPM4_SWIMLANE 
    add constraint FK_SWIMLANE_EXEC 
    foreign key (EXECUTION_) 
    references JBPM4_EXECUTION;

create index IDX_TASK_SUPERTASK on JBPM4_TASK (SUPERTASK_);

alter table JBPM4_TASK 
    add constraint FK_TASK_SWIML 
    foreign key (SWIMLANE_) 
    references JBPM4_SWIMLANE;

alter table JBPM4_TASK 
    add constraint FK_TASK_SUPERTASK 
    foreign key (SUPERTASK_) 
    references JBPM4_TASK;

create index IDX_VAR_EXESYS on JBPM4_VARIABLE (EXESYS_);

create index IDX_VAR_TASK on JBPM4_VARIABLE (TASK_);

create index IDX_VAR_EXECUTION on JBPM4_VARIABLE (EXECUTION_);

create index IDX_VAR_LOB on JBPM4_VARIABLE (LOB_);

alter table JBPM4_VARIABLE 
    add constraint FK_VAR_LOB 
    foreign key (LOB_) 
    references JBPM4_LOB;

alter table JBPM4_VARIABLE 
    add constraint FK_VAR_EXECUTION 
    foreign key (EXECUTION_) 
    references JBPM4_EXECUTION;

alter table JBPM4_VARIABLE 
    add constraint FK_VAR_EXESYS 
    foreign key (EXESYS_) 
    references JBPM4_EXECUTION;

alter table JBPM4_VARIABLE 
    add constraint FK_VAR_TASK 
    foreign key (TASK_) 
    references JBPM4_TASK;