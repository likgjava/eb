--删除原有的客户表
drop table ECP_PUB_CONCERN;
--关注客户表
create table ECP_PUB_CONCERN
(
  CONCERN_ID          NVARCHAR2(50) primary key,
  CONCERN_GROUP       NVARCHAR2(50),
  ORGINFO             NVARCHAR2(50),
  USER_ID             NVARCHAR2(50),
  CREATOR_ID          NVARCHAR2(50),
  CREATE_TIME         DATE,
  LIST_TYPE           NVARCHAR2(2),
  BARGAIN_SUM_NUM     NUMBER(18) default 0,
  BARGAIN_SUM_MONEY   NUMBER(16,6) default 0,
  BARGAIN_LASTLY_DATE DATE,
  BELONGS_USER        NVARCHAR2(50)
);

-- Add comments to the table 
comment on table ECP_PUB_CONCERN
  is '关注客户';
-- Add comments to the columns 
comment on column ECP_PUB_CONCERN.CONCERN_ID
  is '记录号';
comment on column ECP_PUB_CONCERN.CONCERN_GROUP
  is '所属分组';
comment on column ECP_PUB_CONCERN.ORGINFO
  is '客户人';
comment on column ECP_PUB_CONCERN.USER_ID
  is '客户联系人';
comment on column ECP_PUB_CONCERN.CREATOR_ID
  is '创建人';
comment on column ECP_PUB_CONCERN.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_CONCERN.LIST_TYPE
  is '所属名单类型：01:我的客户 02:黑名单';
comment on column ECP_PUB_CONCERN.BARGAIN_SUM_NUM
  is '与客户成交量(笔)';
comment on column ECP_PUB_CONCERN.BARGAIN_SUM_MONEY
  is '与客户成交总金额';
comment on column ECP_PUB_CONCERN.BARGAIN_LASTLY_DATE
  is '最近成交日期';
comment on column ECP_PUB_CONCERN.BELONGS_USER
  is '所属人';




--删除原有的客户表
drop table ECP_PUB_CONCERN_GROUP
--关注客户分组表
create table ECP_PUB_CONCERN_GROUP
(
  CONCERN_GROUP_ID NVARCHAR2(50) primary key,
  GROUP_NAME       NVARCHAR2(50),
  GROUP_TYPE       NVARCHAR2(2) not null,
  BELONGS_USER     NVARCHAR2(50),
  BELONGS_ORG      NVARCHAR2(50),
  CREATE_TIME      DATE,
  SORT_NO          NUMBER(38)
);
-- Add comments to the table 
comment on table ECP_PUB_CONCERN_GROUP
  is '我的关注';
-- Add comments to the columns 
comment on column ECP_PUB_CONCERN_GROUP.GROUP_NAME
  is '关注分组名称';
comment on column ECP_PUB_CONCERN_GROUP.GROUP_TYPE
  is '关注分组类型：01:供应商 02:采购人';
comment on column ECP_PUB_CONCERN_GROUP.BELONGS_USER
  is '所属人';
comment on column ECP_PUB_CONCERN_GROUP.BELONGS_ORG
  is '所属机构';
comment on column ECP_PUB_CONCERN_GROUP.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_CONCERN_GROUP.SORT_NO
  is '排序';