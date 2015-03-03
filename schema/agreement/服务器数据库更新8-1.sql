--范本
create table EPS_TEMPLATE
(
  TEMPLATE_ID   VARCHAR2(36) not null,
  TEMPLATE_NAME VARCHAR2(200),
  DISTRICT_ID   VARCHAR2(200),
  DISTRICT_NAME VARCHAR2(1000),
  ORG_ID        VARCHAR2(36),
  CATEGORY_CODE VARCHAR2(200),
  CATEGORY_NAME VARCHAR2(1000),
  TEMPLATE_TYPE CHAR(2),
  TEMPLATE_FILE VARCHAR2(36),
  IS_SHARE      CHAR(1),
  USE_STATUS	CHAR(2),
  CREATE_USER   VARCHAR2(36),
  CREATE_TIME   DATE
)
;
comment on table EPS_TEMPLATE
  is '范本';
comment on column EPS_TEMPLATE.TEMPLATE_ID
  is '主键';
comment on column EPS_TEMPLATE.TEMPLATE_NAME
  is '名称';
comment on column EPS_TEMPLATE.DISTRICT_ID
  is '所属区域，以逗号分割';
comment on column EPS_TEMPLATE.DISTRICT_NAME
  is '区域名称，以逗号分割';
comment on column EPS_TEMPLATE.ORG_ID
  is '所属机构';
comment on column EPS_TEMPLATE.CATEGORY_CODE
  is '品目编号，以逗号分割';
comment on column EPS_TEMPLATE.CATEGORY_NAME
  is '品目名称，以逗号分割';
comment on column EPS_TEMPLATE.TEMPLATE_TYPE
  is '范本类型';
comment on column EPS_TEMPLATE.TEMPLATE_FILE
  is '范本附件';
comment on column EPS_TEMPLATE.IS_SHARE
  is '是否共享';
comment on column EPS_TEMPLATE.USE_STATUS
  is '使用状态';
comment on column EPS_TEMPLATE.CREATE_USER
  is '创建人';
comment on column EPS_TEMPLATE.CREATE_TIME
  is '创建时间';
alter table EPS_TEMPLATE
  add constraint EPS_TEMPLATE_PK primary key (TEMPLATE_ID);

--范本使用情况
create table EPS_TEMPLATE_USED
(
  TEMPLATE_USED_ID VARCHAR2(36) not null,
  TEMPLATE_ID      VARCHAR2(36),
  ORG_ID           VARCHAR2(36),
  PROJECT_CODE     VARCHAR2(36),
  PROJECT_NAME     VARCHAR2(200),
  CREATE_USER      VARCHAR2(36),
  CREATE_TIME      DATE
)
;
comment on table EPS_TEMPLATE_USED
  is '范本使用情况';
comment on column EPS_TEMPLATE_USED.TEMPLATE_USED_ID
  is '主键';
comment on column EPS_TEMPLATE_USED.TEMPLATE_ID
  is '使用的范本';
comment on column EPS_TEMPLATE_USED.ORG_ID
  is '使用的机构';
comment on column EPS_TEMPLATE_USED.PROJECT_CODE
  is '项目编号';
comment on column EPS_TEMPLATE_USED.PROJECT_NAME
  is '项目名称';
comment on column EPS_TEMPLATE_USED.CREATE_USER
  is '创建人';
comment on column EPS_TEMPLATE_USED.CREATE_TIME
  is '创建时间';
alter table EPS_TEMPLATE_USED
  add constraint EPS_TEMPLATE_USED_PK primary key (TEMPLATE_USED_ID);

--范本下载情况
create table EPS_TEMPLATE_DOWN
(
  TEMPLATE_DOWN_ID VARCHAR2(36) not null,
  TEMPLATE_ID      VARCHAR2(36),
  ORG_ID           VARCHAR2(36),
  CREATE_USER      VARCHAR2(36),
  CREATE_TIME      DATE
)
;
comment on table EPS_TEMPLATE_DOWN
  is '范本下载情况';
comment on column EPS_TEMPLATE_DOWN.TEMPLATE_DOWN_ID
  is '主键';
comment on column EPS_TEMPLATE_DOWN.TEMPLATE_ID
  is '下载的范本';
comment on column EPS_TEMPLATE_DOWN.ORG_ID
  is '下载的机构';
comment on column EPS_TEMPLATE_DOWN.CREATE_USER
  is '创建人';
comment on column EPS_TEMPLATE_DOWN.CREATE_TIME
  is '创建时间';
alter table EPS_TEMPLATE_DOWN
  add constraint EPS_TEMPLATE_DOWN_PK primary key (TEMPLATE_DOWN_ID);

--范本收藏
create table EPS_TEMPLATE_FAVORITE
(
  TEMPLATE_FAVORITE_ID   VARCHAR2(36) not null,
  TEMPLATE_FAVORITE_NAME VARCHAR2(200),
  TEMPLATE_ID            VARCHAR2(36),
  ORG_ID                 VARCHAR2(36),
  CREATE_USER            VARCHAR2(36),
  CREATE_TIME            DATE
)
;
comment on table EPS_TEMPLATE_FAVORITE
  is '范本收藏';
comment on column EPS_TEMPLATE_FAVORITE.TEMPLATE_FAVORITE_ID
  is '主键';
comment on column EPS_TEMPLATE_FAVORITE.TEMPLATE_FAVORITE_NAME
  is '收藏的名称';
comment on column EPS_TEMPLATE_FAVORITE.TEMPLATE_ID
  is '收藏的范本';
comment on column EPS_TEMPLATE_FAVORITE.ORG_ID
  is '机构';
comment on column EPS_TEMPLATE_FAVORITE.CREATE_USER
  is '创建人';
comment on column EPS_TEMPLATE_FAVORITE.CREATE_TIME
  is '创建时间';
alter table EPS_TEMPLATE_FAVORITE
  add constraint EPS_TEMPLATE_FAVORITE_PK primary key (TEMPLATE_FAVORITE_ID);


  
  -- Add/modify columns 
alter table AUTH_ORGNIZATION add org_site VARCHAR2(20 CHAR);
-- Add comments to the columns 
comment on column AUTH_ORGNIZATION.org_site
  is '机构域名';
  
 
--服务
insert into service_base (SERVICE_ID, SERVICE_NAME, SERVICE_PIC, SERVICE_DESC, SERVICE_LINK, SERVICE_PRICE, SERVICE_UNIT, SERVICE_PREPOSITION, IS_SINGLE_PURCHASE, IS_RECOMMENDATION, CREATOR, CREATETIME, SERVICE_AGREEMENT_LINK, USE_STATUS)
values ('B', '公告订阅', 'ff8080812f588033012f6bb9ddfc0a95', '【信息传递】传递财富 抢得一手消息！【订阅公告】及时告知 成功可以预定！', '', 0.0000000, '01', '', '1', '1', '402886db2aa1b3b9012aa1cae8080020', to_date('01-04-2011 16:22:34', 'dd-mm-yyyy hh24:mi:ss'), '', '01');

insert into service_base (SERVICE_ID, SERVICE_NAME, SERVICE_PIC, SERVICE_DESC, SERVICE_LINK, SERVICE_PRICE, SERVICE_UNIT, SERVICE_PREPOSITION, IS_SINGLE_PURCHASE, IS_RECOMMENDATION, CREATOR, CREATETIME, SERVICE_AGREEMENT_LINK, USE_STATUS)
values ('C', '机构域名', 'ff8080812f588033012f6bb9ddfc0a95', '【海量空间】装载无限财富！【独立域名】彰显企业文化！', '', 0.0000000, '01', '', '1', '1', '402886db2aa1b3b9012aa1cae8080020', to_date('01-04-2011 16:22:34', 'dd-mm-yyyy hh24:mi:ss'), '', '01');

--计费
insert into service_charging (SERVICE_CHARGING_ID, MEMBER_CLASS_ID, SERVICE_ID, DURATION, AMOUNT, DISCOUNT, USE_STATUS, CREATOR, CREATETIME)
values ('2c9087f031a6da210131a71f01d50007', '', 'C', 1, 0.0000000, 100.0000000, '01', '402886db2aa1b3b9012aa1cae8080020', to_date('08-08-2011 10:00:52', 'dd-mm-yyyy hh24:mi:ss'));

insert into service_charging (SERVICE_CHARGING_ID, MEMBER_CLASS_ID, SERVICE_ID, DURATION, AMOUNT, DISCOUNT, USE_STATUS, CREATOR, CREATETIME)
values ('2c9087f0315ece5501315edd4e260002', '', 'B', 1, 0.0000000, 100.0000000, '01', '402886db2aa1b3b9012aa1cae8080020', to_date('25-07-2011 09:16:27', 'dd-mm-yyyy hh24:mi:ss'));

