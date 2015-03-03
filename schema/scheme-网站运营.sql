-- Create table
create table ECP_PUB_CONCERN
(
  CONCERN_ID    NVARCHAR2(50) not null,
  CONCERN_GROUP NVARCHAR2(50),
  ORGINFO       NVARCHAR2(50),
  USER_ID       NVARCHAR2(50),
  CREATOR_ID    NVARCHAR2(50),
  CREATE_TIME   DATE,
  LIST_TYPE     NVARCHAR2(2)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_PUB_CONCERN
  is '关注对象';
-- Add comments to the columns 
comment on column ECP_PUB_CONCERN.CONCERN_GROUP
  is '所属分组';
comment on column ECP_PUB_CONCERN.ORGINFO
  is '机构';
comment on column ECP_PUB_CONCERN.USER_ID
  is '用户';
comment on column ECP_PUB_CONCERN.CREATOR_ID
  is '创建人';
comment on column ECP_PUB_CONCERN.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_CONCERN.LIST_TYPE
  is '所属名单类型：01:短名单 02:长名单 03:黑名单';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_CONCERN
  add constraint PK_CONCERN_ID primary key (CONCERN_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_PUB_CONCERN_GROUP
(
  CONCERN_GROUP_ID NVARCHAR2(50) not null,
  GROUP_NAME       NVARCHAR2(50),
  GROUP_TYPE       NVARCHAR2(2) not null,
  BELONGS_USER     NVARCHAR2(50),
  BELONGS_ORG      NVARCHAR2(50),
  CREATE_TIME      DATE,
  SORT_NO          NUMBER(38)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_CONCERN_GROUP
  add constraint PK_CONCERN_GROUP_ID primary key (CONCERN_GROUP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_PUB_FAVORITES
(
  FAVORITES_ID          VARCHAR2(50) not null,
  FAVORITES_OBJECT_ID   VARCHAR2(50),
  FAVORITES_OBJECT_TYPE VARCHAR2(10),
  FAVORITES_OBJECT_KEY  VARCHAR2(200),
  CREATOR_ID            VARCHAR2(50),
  CREATE_TIME           DATE,
  FAVORITES_OBJECT_NAME VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_PUB_FAVORITES
  is '收藏';
-- Add comments to the columns 
comment on column ECP_PUB_FAVORITES.FAVORITES_ID
  is '记录号';
comment on column ECP_PUB_FAVORITES.FAVORITES_OBJECT_ID
  is '收藏对象Id';
comment on column ECP_PUB_FAVORITES.FAVORITES_OBJECT_TYPE
  is '收藏对象类型';
comment on column ECP_PUB_FAVORITES.FAVORITES_OBJECT_KEY
  is '收藏对象关键字';
comment on column ECP_PUB_FAVORITES.CREATOR_ID
  is '创建人';
comment on column ECP_PUB_FAVORITES.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_FAVORITES.FAVORITES_OBJECT_NAME
  is '收藏对象名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_FAVORITES
  add constraint PK_FAVORITES_ID primary key (FAVORITES_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table GOODS_ATTENTION_PRICE
(
  GOODS_ATTENTION_PRICE_ID VARCHAR2(36) not null,
  GOODS_ID                 VARCHAR2(36),
  ORG_INFO                 VARCHAR2(36),
  CREATOR_ID               VARCHAR2(36),
  CREATE_TIME              DATE,
  DISTRICT_ID              VARCHAR2(36)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_ATTENTION_PRICE
  is '关注的商品（行情）';
-- Add comments to the columns 
comment on column GOODS_ATTENTION_PRICE.GOODS_ATTENTION_PRICE_ID
  is '记录号';
comment on column GOODS_ATTENTION_PRICE.GOODS_ID
  is '商品';
comment on column GOODS_ATTENTION_PRICE.ORG_INFO
  is '所属机构';
comment on column GOODS_ATTENTION_PRICE.CREATOR_ID
  is '创建人';
comment on column GOODS_ATTENTION_PRICE.CREATE_TIME
  is '创建时间';
comment on column GOODS_ATTENTION_PRICE.DISTRICT_ID
  is '关注区域';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_ATTENTION_PRICE
  add constraint PK_GOODS_ATTENTION_PRICE_ID primary key (GOODS_ATTENTION_PRICE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_PUB_TAGS
(
  TAGS_ID          VARCHAR2(50) not null,
  TAGS_OBJECT_ID   VARCHAR2(50),
  TAGS_OBJECT_TYPE VARCHAR2(10),
  CREATOR_ID       VARCHAR2(50),
  CREATE_TIME      DATE,
  TAGS_OBJECT_NAME VARCHAR2(200),
  TAGS_OBJECT_DSCR VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_PUB_TAGS
  is '热门标签';
-- Add comments to the columns 
comment on column ECP_PUB_TAGS.TAGS_ID
  is '记录号';
comment on column ECP_PUB_TAGS.TAGS_OBJECT_ID
  is '收藏对象Id';
comment on column ECP_PUB_TAGS.TAGS_OBJECT_TYPE
  is '收藏对象类型';
comment on column ECP_PUB_TAGS.CREATOR_ID
  is '创建人';
comment on column ECP_PUB_TAGS.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_TAGS.TAGS_OBJECT_NAME
  is '收藏对象名称';
comment on column ECP_PUB_TAGS.TAGS_OBJECT_DSCR
  is '收藏对象描述';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_TAGS
  add constraint PK_TAGS_ID primary key (TAGS_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_PUB_USER_SECURITY
(
  US_ID       VARCHAR2(50) not null,
  U_ID        VARCHAR2(50) not null,
  DIC_ID      VARCHAR2(50) not null,
  ANSWER      VARCHAR2(50) not null,
  CREATE_USER VARCHAR2(50),
  CREATE_TIME DATE
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column ECP_PUB_USER_SECURITY.US_ID
  is '主键 ';
comment on column ECP_PUB_USER_SECURITY.U_ID
  is '用户';
comment on column ECP_PUB_USER_SECURITY.DIC_ID
  is '密保问题(取自数据字典)';
comment on column ECP_PUB_USER_SECURITY.ANSWER
  is '密保问题答案';
comment on column ECP_PUB_USER_SECURITY.CREATE_USER
  is '创建人';
comment on column ECP_PUB_USER_SECURITY.CREATE_TIME
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_USER_SECURITY
  add constraint PK_US_ID primary key (US_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PUB_ADVERTISING_POSITION
(
  ADVER_POSITION_ID      VARCHAR2(50) not null,
  POSITION_DICTIONARY_ID VARCHAR2(50),
  POSITION_NAME          VARCHAR2(50),
  ADVER_TYPE             CHAR(2),
  ADVER_LENGTH           NUMBER(8),
  ADVER_WIDTH            NUMBER(8),
  ADVER_FILE_MAXVALUE    NUMBER(8),
  ADVER_OUTLAY           NUMBER(16,4),
  CREATOR_ID             VARCHAR2(50),
  CREATE_TIME            DATE,
  MODIFIER               VARCHAR2(50),
  MODIFIER_TIME          DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_PUB_ADVERTISING_POSITION
  is '广告位';
-- Add comments to the columns 
comment on column EPS_PUB_ADVERTISING_POSITION.ADVER_POSITION_ID
  is '记录号';
comment on column EPS_PUB_ADVERTISING_POSITION.POSITION_DICTIONARY_ID
  is '位置字典项';
comment on column EPS_PUB_ADVERTISING_POSITION.POSITION_NAME
  is '位置名称';
comment on column EPS_PUB_ADVERTISING_POSITION.ADVER_TYPE
  is '广告类型';
comment on column EPS_PUB_ADVERTISING_POSITION.ADVER_LENGTH
  is '广告长度';
comment on column EPS_PUB_ADVERTISING_POSITION.ADVER_WIDTH
  is '广告宽度';
comment on column EPS_PUB_ADVERTISING_POSITION.ADVER_FILE_MAXVALUE
  is '广告文件最大值';
comment on column EPS_PUB_ADVERTISING_POSITION.ADVER_OUTLAY
  is '位置资费';
comment on column EPS_PUB_ADVERTISING_POSITION.CREATOR_ID
  is '创建人';
comment on column EPS_PUB_ADVERTISING_POSITION.CREATE_TIME
  is '创建时间';
comment on column EPS_PUB_ADVERTISING_POSITION.MODIFIER
  is '修改人';
comment on column EPS_PUB_ADVERTISING_POSITION.MODIFIER_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_ADVERTISING_POSITION
  add constraint ADVERTISING_POSITION_PK primary key (ADVER_POSITION_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PUB_ADVERTISING_SUBSCRIBE
(
  ADVER_SUBSCRIBE_ID VARCHAR2(50) not null,
  ADVER_POSITION_ID  VARCHAR2(50),
  ORGINFO_ID         VARCHAR2(50),
  ORGINFO_ORGNAME    VARCHAR2(100),
  ADVER_LINK         VARCHAR2(100),
  ADVER_FILE         VARCHAR2(50),
  START_TIME         TIMESTAMP(6),
  END_TIME           TIMESTAMP(6),
  TOTAL_OUTLAY       NUMBER(16,4),
  USE_STATUS         CHAR(2),
  AUDIT_STATUS       CHAR(2),
  AUDITOR            VARCHAR2(50),
  AUDIT_TIME         DATE,
  AUDIT_OPTION       VARCHAR2(200),
  CREATOR            VARCHAR2(50),
  CREATE_TIME        DATE,
  MODIFIER           VARCHAR2(50),
  MODIFY_TIME        DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_PUB_ADVERTISING_SUBSCRIBE
  is '广告订阅';
-- Add comments to the columns 
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.ADVER_SUBSCRIBE_ID
  is '记录号';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.ADVER_POSITION_ID
  is '广告位Id';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.ORGINFO_ID
  is '投放机构';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.ORGINFO_ORGNAME
  is '投放机构名称';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.ADVER_LINK
  is '广告链接';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.ADVER_FILE
  is '广告文件';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.START_TIME
  is '开始时间';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.END_TIME
  is '结束时间';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.TOTAL_OUTLAY
  is '总资费';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.USE_STATUS
  is '使用状态';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.AUDIT_STATUS
  is '审核状态';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.AUDITOR
  is '审核人';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.AUDIT_TIME
  is '审核时间';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.AUDIT_OPTION
  is '审核意见';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.CREATOR
  is '创建人';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.CREATE_TIME
  is '创建时间';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.MODIFIER
  is '修改人';
comment on column EPS_PUB_ADVERTISING_SUBSCRIBE.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_ADVERTISING_SUBSCRIBE
  add constraint ADVERTISING_SUBSCRIBE_PK primary key (ADVER_SUBSCRIBE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PUB_ADVISE
(
  ADV_ID        VARCHAR2(50) not null,
  CONTENT       VARCHAR2(4000) not null,
  ADVISER       VARCHAR2(50),
  CONTACT_TEL   VARCHAR2(50),
  CONTACT_EMAIL VARCHAR2(100),
  ADVISE_TIME   DATE,
  REPLY_CONTENT VARCHAR2(4000),
  REPLY_TIME    DATE,
  REPPIER       VARCHAR2(50),
  UPDATE_USER   VARCHAR2(50),
  UPDATE_TIME   DATE,
  IS_REPLY      NUMBER default 0
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column EPS_PUB_ADVISE.ADV_ID
  is '主键';
comment on column EPS_PUB_ADVISE.CONTENT
  is '内容';
comment on column EPS_PUB_ADVISE.ADVISER
  is '建议人';
comment on column EPS_PUB_ADVISE.CONTACT_TEL
  is '联系电话';
comment on column EPS_PUB_ADVISE.CONTACT_EMAIL
  is '联系邮件';
comment on column EPS_PUB_ADVISE.ADVISE_TIME
  is '建议时间';
comment on column EPS_PUB_ADVISE.REPLY_CONTENT
  is '回复内容';
comment on column EPS_PUB_ADVISE.REPLY_TIME
  is '回复时间';
comment on column EPS_PUB_ADVISE.REPPIER
  is '回复人';
comment on column EPS_PUB_ADVISE.UPDATE_USER
  is '修改人';
comment on column EPS_PUB_ADVISE.UPDATE_TIME
  is '修改时间';
comment on column EPS_PUB_ADVISE.IS_REPLY
  is '回复状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_ADVISE
  add primary key (ADV_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PUB_COMPLAIN
(
  CPL_ID           VARCHAR2(50) not null,
  TITLE            VARCHAR2(200),
  CONTENT          VARCHAR2(4000),
  ATT_FILE         VARCHAR2(50),
  REPLY_EMAIL      VARCHAR2(100),
  COMPLAINANT      VARCHAR2(50),
  COMPLAINANT_NAME VARCHAR2(50),
  COMPLAIN_TIME    TIMESTAMP(6),
  BE_COMPLAIN      VARCHAR2(50),
  BE_COMPLAIN_NAME VARCHAR2(50),
  TYPE             CHAR(2),
  IS_DISPOSE       CHAR(1) default 0,
  RESULT           VARCHAR2(4000),
  DISPOSE_TIME     DATE,
  DISPOSER         VARCHAR2(50),
  CREATE_TIME      DATE,
  BE_COMPANY_ID    VARCHAR2(50),
  BE_COMPANY_NAME  VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column EPS_PUB_COMPLAIN.CPL_ID
  is '主键ID';
comment on column EPS_PUB_COMPLAIN.TITLE
  is '标题';
comment on column EPS_PUB_COMPLAIN.CONTENT
  is ' 内容';
comment on column EPS_PUB_COMPLAIN.ATT_FILE
  is '附件';
comment on column EPS_PUB_COMPLAIN.REPLY_EMAIL
  is '回复信箱';
comment on column EPS_PUB_COMPLAIN.COMPLAINANT
  is '投诉人';
comment on column EPS_PUB_COMPLAIN.COMPLAINANT_NAME
  is '投诉人姓名 ';
comment on column EPS_PUB_COMPLAIN.COMPLAIN_TIME
  is '投诉时间 ';
comment on column EPS_PUB_COMPLAIN.BE_COMPLAIN
  is ' 被投诉人';
comment on column EPS_PUB_COMPLAIN.BE_COMPLAIN_NAME
  is '被投诉人姓名';
comment on column EPS_PUB_COMPLAIN.TYPE
  is '类型	 00:投诉 01:举报';
comment on column EPS_PUB_COMPLAIN.IS_DISPOSE
  is '是否处理  0:未处理 1:已处理';
comment on column EPS_PUB_COMPLAIN.RESULT
  is '处理结果';
comment on column EPS_PUB_COMPLAIN.DISPOSE_TIME
  is '处理时间';
comment on column EPS_PUB_COMPLAIN.DISPOSER
  is '处理人';
comment on column EPS_PUB_COMPLAIN.CREATE_TIME
  is '创建时间';
comment on column EPS_PUB_COMPLAIN.BE_COMPANY_ID
  is '被投诉人公司ID';
comment on column EPS_PUB_COMPLAIN.BE_COMPANY_NAME
  is '被投诉人公司名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_COMPLAIN
  add primary key (CPL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PUB_MESSAGE
(
  MSG_ID        VARCHAR2(50) not null,
  TITLE         VARCHAR2(200),
  CONTENT       VARCHAR2(4000),
  TYPE          CHAR(2),
  SENDER        VARCHAR2(50),
  SENDER_NAME   VARCHAR2(50),
  SEND_TIME     DATE,
  RES_SEND_TIME DATE,
  RECEIVER      VARCHAR2(50),
  RECEIVER_NAME VARCHAR2(50),
  READER        DATE,
  IS_READ       CHAR(1) default 0,
  IS_SAVE       CHAR(1) default 0,
  CREUSER       VARCHAR2(50),
  CREDATE       DATE,
  IS_TIP        CHAR(1) default 0
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column EPS_PUB_MESSAGE.MSG_ID
  is '主键';
comment on column EPS_PUB_MESSAGE.TITLE
  is '标题';
comment on column EPS_PUB_MESSAGE.CONTENT
  is '内容';
comment on column EPS_PUB_MESSAGE.TYPE
  is '消息类型';
comment on column EPS_PUB_MESSAGE.SENDER
  is '发送人';
comment on column EPS_PUB_MESSAGE.SENDER_NAME
  is '发送人姓名';
comment on column EPS_PUB_MESSAGE.SEND_TIME
  is '发送时间';
comment on column EPS_PUB_MESSAGE.RES_SEND_TIME
  is '预定发送时间';
comment on column EPS_PUB_MESSAGE.RECEIVER
  is '接收人';
comment on column EPS_PUB_MESSAGE.RECEIVER_NAME
  is '接收人姓名';
comment on column EPS_PUB_MESSAGE.READER
  is '阅读时间';
comment on column EPS_PUB_MESSAGE.IS_READ
  is '是否阅读';
comment on column EPS_PUB_MESSAGE.IS_SAVE
  is '是否保存发件箱';
comment on column EPS_PUB_MESSAGE.CREUSER
  is '创建人';
comment on column EPS_PUB_MESSAGE.CREDATE
  is '创建时间';
comment on column EPS_PUB_MESSAGE.IS_TIP
  is '是否已经提示';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_MESSAGE
  add constraint EPS_PUB_MESSAGE_PK primary key (MSG_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PUB_NOTE
(
  NOTE_ID       VARCHAR2(50) not null,
  CONTENT       VARCHAR2(4000),
  ATT_FILE      VARCHAR2(50),
  RECEIVER      VARCHAR2(50),
  RECEIVER_NAME VARCHAR2(50),
  GOODS_ID      VARCHAR2(50),
  TYPE          CHAR(2),
  LEAVER        VARCHAR2(50),
  LEAVER_NAME   VARCHAR2(50),
  LEAVER_TIME   DATE,
  REPLY_CONTENT VARCHAR2(4000),
  REPLY_TIME    DATE,
  IS_REPLY      CHAR(1),
  IS_ANNOY      CHAR(1),
  IS_READ       CHAR(1)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column EPS_PUB_NOTE.NOTE_ID
  is '主键';
comment on column EPS_PUB_NOTE.CONTENT
  is '留言内容';
comment on column EPS_PUB_NOTE.ATT_FILE
  is '表情图标';
comment on column EPS_PUB_NOTE.RECEIVER
  is '接收人';
comment on column EPS_PUB_NOTE.RECEIVER_NAME
  is '接收人姓名';
comment on column EPS_PUB_NOTE.GOODS_ID
  is '相关商品';
comment on column EPS_PUB_NOTE.TYPE
  is '类型(00：普通留言      01：客服咨询)';
comment on column EPS_PUB_NOTE.LEAVER
  is '留言人';
comment on column EPS_PUB_NOTE.LEAVER_NAME
  is '留言人姓名';
comment on column EPS_PUB_NOTE.LEAVER_TIME
  is '留言时间';
comment on column EPS_PUB_NOTE.REPLY_CONTENT
  is '回复内容';
comment on column EPS_PUB_NOTE.REPLY_TIME
  is '回复时间';
comment on column EPS_PUB_NOTE.IS_REPLY
  is '是否回复(0：没有回复      1：已经回复)';
comment on column EPS_PUB_NOTE.IS_ANNOY
  is '是否匿名(0：不匿名      1：匿名)';
comment on column EPS_PUB_NOTE.IS_READ
  is '是否已读(0：未阅读      1：已阅读)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_NOTE
  add constraint NOTE_PRIMARYKEY_ID primary key (NOTE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_BUSINESS_COMMUNITY
(
  COMMUNITY_ID     VARCHAR2(50) not null,
  COMMUNITY_NAME   VARCHAR2(100),
  TENDER_CATEGORYS VARCHAR2(2000),
  IS_DISPLAY       CHAR(1),
  CREATOR_ID       VARCHAR2(50),
  CREATE_TIME      DATE,
  MODIFIER_ID      VARCHAR2(50),
  MODIFY_TIME      DATE,
  MEMO             VARCHAR2(200),
  ISSPECIAL        CHAR(1),
  ISRECOMMENDED    CHAR(1),
  GROUP_ID         VARCHAR2(50),
  PICTURE          VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_BUSINESS_COMMUNITY
  is '社区';
-- Add comments to the columns 
comment on column ECP_BUSINESS_COMMUNITY.COMMUNITY_ID
  is '主键
';
comment on column ECP_BUSINESS_COMMUNITY.COMMUNITY_NAME
  is '社区名称
';
comment on column ECP_BUSINESS_COMMUNITY.TENDER_CATEGORYS
  is '投标品目
';
comment on column ECP_BUSINESS_COMMUNITY.IS_DISPLAY
  is '是否显示
';
comment on column ECP_BUSINESS_COMMUNITY.CREATOR_ID
  is '创建人';
comment on column ECP_BUSINESS_COMMUNITY.CREATE_TIME
  is '创建时间';
comment on column ECP_BUSINESS_COMMUNITY.MODIFIER_ID
  is '修改人
';
comment on column ECP_BUSINESS_COMMUNITY.MODIFY_TIME
  is '修改时间
';
comment on column ECP_BUSINESS_COMMUNITY.MEMO
  is '备注';
comment on column ECP_BUSINESS_COMMUNITY.ISSPECIAL
  is '是否特色社区';
comment on column ECP_BUSINESS_COMMUNITY.ISRECOMMENDED
  is '是否推荐';
comment on column ECP_BUSINESS_COMMUNITY.GROUP_ID
  is '社区群号';
comment on column ECP_BUSINESS_COMMUNITY.PICTURE
  is '社区图片';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BUSINESS_COMMUNITY
  add primary key (COMMUNITY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_BUSINESS_MEMBER
(
  BUSINESSMEMBER_ID VARCHAR2(50 CHAR) not null,
  ORG_INFO_ID       VARCHAR2(50 CHAR) not null,
  BEGAIN_DATE       DATE,
  END_DATE          DATE,
  TIME_TYPE         CHAR(1),
  AUDIT_STATUS      VARCHAR2(2),
  USE_STATUS        VARCHAR2(2),
  ISOFF             CHAR(1),
  CREATE_USER       VARCHAR2(50 CHAR),
  CREATE_TIME       DATE,
  UPDATE_USER       VARCHAR2(50 CHAR),
  UPDATE_TIME       DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column ECP_BUSINESS_MEMBER.BUSINESSMEMBER_ID
  is '主键';
comment on column ECP_BUSINESS_MEMBER.ORG_INFO_ID
  is '机构id';
comment on column ECP_BUSINESS_MEMBER.BEGAIN_DATE
  is '开始日期';
comment on column ECP_BUSINESS_MEMBER.END_DATE
  is '结束日期';
comment on column ECP_BUSINESS_MEMBER.TIME_TYPE
  is '会员时长';
comment on column ECP_BUSINESS_MEMBER.AUDIT_STATUS
  is '审核状态';
comment on column ECP_BUSINESS_MEMBER.USE_STATUS
  is '使用状态';
comment on column ECP_BUSINESS_MEMBER.ISOFF
  is '是否启用';
comment on column ECP_BUSINESS_MEMBER.CREATE_USER
  is '创建人';
comment on column ECP_BUSINESS_MEMBER.CREATE_TIME
  is '创建时间';
comment on column ECP_BUSINESS_MEMBER.UPDATE_USER
  is '修改人';
comment on column ECP_BUSINESS_MEMBER.UPDATE_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BUSINESS_MEMBER
  add primary key (BUSINESSMEMBER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_FORUM_TOPIC
(
  TOPIC_ID     VARCHAR2(50) not null,
  COMMUNITY_ID VARCHAR2(50),
  ORG_INFO_ID  VARCHAR2(50),
  TITLE        VARCHAR2(100),
  CONTENT      VARCHAR2(4000),
  ATTACHMENT   VARCHAR2(400),
  CREATOR_ID   VARCHAR2(50),
  CREATE_TIME  TIMESTAMP(6),
  MODIFY_ID    VARCHAR2(50),
  MODIFY_TIME  TIMESTAMP(6),
  IS_SHOW      CHAR(1) default 1,
  IS_TOP       CHAR(1),
  IS_ELITE     CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_FORUM_TOPIC
  is '企业论坛主题';
-- Add comments to the columns 
comment on column ECP_FORUM_TOPIC.COMMUNITY_ID
  is '社区id';
comment on column ECP_FORUM_TOPIC.ORG_INFO_ID
  is '发表机构id
';
comment on column ECP_FORUM_TOPIC.TITLE
  is '标题
';
comment on column ECP_FORUM_TOPIC.CONTENT
  is '内容
';
comment on column ECP_FORUM_TOPIC.ATTACHMENT
  is '附件
';
comment on column ECP_FORUM_TOPIC.CREATOR_ID
  is '创建人
';
comment on column ECP_FORUM_TOPIC.CREATE_TIME
  is '创建时间
';
comment on column ECP_FORUM_TOPIC.MODIFY_ID
  is '修改人
';
comment on column ECP_FORUM_TOPIC.MODIFY_TIME
  is '修改时间
';
comment on column ECP_FORUM_TOPIC.IS_SHOW
  is '是否显示(0：不显示,1：显示)';
comment on column ECP_FORUM_TOPIC.IS_TOP
  is '是否置顶(0：否,1：是)';
comment on column ECP_FORUM_TOPIC.IS_ELITE
  is '是否精华(0：否,1：是)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_FORUM_TOPIC
  add primary key (TOPIC_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_FORUM_REPLY
(
  REPLY_ID     VARCHAR2(50) not null,
  TOPIC_ID     VARCHAR2(50),
  ORG_INFO_ID  VARCHAR2(50),
  CONTENT      VARCHAR2(400),
  ATTACHMENT   VARCHAR2(400),
  CREATOR_ID   VARCHAR2(50),
  CREATE_TIME  TIMESTAMP(6),
  MODIFY_ID    VARCHAR2(50),
  MODIFY_TIME  TIMESTAMP(6),
  IS_SHOW      CHAR(1) default 1,
  CREATOR_NAME VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_FORUM_REPLY
  is '企业论坛主题回复';
-- Add comments to the columns 
comment on column ECP_FORUM_REPLY.TOPIC_ID
  is '主题表id
';
comment on column ECP_FORUM_REPLY.ORG_INFO_ID
  is '回复机构id
';
comment on column ECP_FORUM_REPLY.CONTENT
  is '内容
';
comment on column ECP_FORUM_REPLY.ATTACHMENT
  is '附件
';
comment on column ECP_FORUM_REPLY.CREATOR_ID
  is '创建人
';
comment on column ECP_FORUM_REPLY.CREATE_TIME
  is '创建时间
';
comment on column ECP_FORUM_REPLY.MODIFY_ID
  is '修改人
';
comment on column ECP_FORUM_REPLY.MODIFY_TIME
  is '修改时间
';
comment on column ECP_FORUM_REPLY.IS_SHOW
  is '是否显示(0：不显示,1：显示)';
comment on column ECP_FORUM_REPLY.CREATOR_NAME
  is '评论人中文名';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_FORUM_REPLY
  add primary key (REPLY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_POINT_RULES
(
  RULE_ID        VARCHAR2(50) not null,
  SOURCE_CODE    VARCHAR2(50),
  POINT_WAY      CHAR(1),
  POINT_NUMBER   NUMBER(8,2),
  POINT_PERCENT  NUMBER(8,2),
  CURRENT_STATUS CHAR(1),
  POINT_SIGN     NUMBER(1),
  AMOUNT_MIN     NUMBER(12,2),
  AMOUNT_MAX     NUMBER(12,2),
  CREATOR        VARCHAR2(50),
  CREATE_TIME    TIMESTAMP(6),
  MODIFIER       VARCHAR2(50),
  MODIFY_TIME    TIMESTAMP(6),
  PUB_DATE       TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_POINT_RULES
  is '积分规则';
-- Add comments to the columns 
comment on column EPS_POINT_RULES.SOURCE_CODE
  is '来源（枚举定义）';
comment on column EPS_POINT_RULES.POINT_WAY
  is '积分方式 0 额度 1 百分比';
comment on column EPS_POINT_RULES.POINT_NUMBER
  is '积分额度';
comment on column EPS_POINT_RULES.POINT_PERCENT
  is '百分比';
comment on column EPS_POINT_RULES.CURRENT_STATUS
  is '当前状态 1 有效 0无效';
comment on column EPS_POINT_RULES.POINT_SIGN
  is '累加方式,1,加分，-1减分';
comment on column EPS_POINT_RULES.AMOUNT_MIN
  is '额度最小值';
comment on column EPS_POINT_RULES.AMOUNT_MAX
  is '额度最大值';
comment on column EPS_POINT_RULES.CREATOR
  is '创建人';
comment on column EPS_POINT_RULES.CREATE_TIME
  is '创建时间';
comment on column EPS_POINT_RULES.MODIFIER
  is ' 修改人';
comment on column EPS_POINT_RULES.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_POINT_RULES
  add constraint PK_EPS_POINT_RULES primary key (RULE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table EPS_POINT_RULES
  add constraint UNIQ_CODE_AMOUNT_STATUS unique (SOURCE_CODE, CURRENT_STATUS, AMOUNT_MIN)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_POINT_EXCHANGE
(
  EXCHANGE_ID     VARCHAR2(50) not null,
  USER_ID         VARCHAR2(50),
  EXCHANGE_TYPE   CHAR(1),
  OBTAIN_DATE     DATE,
  OBTAIN_SOURCE   VARCHAR2(50),
  EXCHANGE_NUMBER NUMBER(8),
  CURRENT_STATUS  CHAR(1),
  EXCHANGE_MEMO   VARCHAR2(500),
  VAL_DATE        DATE,
  EXCHANGE_MONEY  NUMBER(12,2)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_POINT_EXCHANGE
  is '积分';
-- Add comments to the columns 
comment on column EPS_POINT_EXCHANGE.EXCHANGE_TYPE
  is '获取方式 0 购买积分 1 交易积分 3 奖励积分 4 交换积分 5礼券积分';
comment on column EPS_POINT_EXCHANGE.OBTAIN_DATE
  is '获得日期';
comment on column EPS_POINT_EXCHANGE.OBTAIN_SOURCE
  is '积分来源';
comment on column EPS_POINT_EXCHANGE.EXCHANGE_NUMBER
  is '积分额度';
comment on column EPS_POINT_EXCHANGE.CURRENT_STATUS
  is '状态 0-无效 1-有效';
comment on column EPS_POINT_EXCHANGE.EXCHANGE_MEMO
  is '备注';
comment on column EPS_POINT_EXCHANGE.VAL_DATE
  is '有效日期';
comment on column EPS_POINT_EXCHANGE.EXCHANGE_MONEY
  is '购买金额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_POINT_EXCHANGE
  add constraint PK_EPS_EXCHANGE_POINT primary key (EXCHANGE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_POINT_CASH
(
  CASH_ID     VARCHAR2(50) not null,
  USER_ID     VARCHAR2(50),
  CASH_DATE   DATE,
  CASH_NUMBER NUMBER(8),
  CASH_FILE   VARCHAR2(50),
  CASH_MEMO   VARCHAR2(500),
  CASH_MONEY  NUMBER(12,2)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_POINT_CASH
  is '积分兑现';
-- Add comments to the columns 
comment on column EPS_POINT_CASH.CASH_DATE
  is '兑换日期';
comment on column EPS_POINT_CASH.CASH_NUMBER
  is '兑换积分额度';
comment on column EPS_POINT_CASH.CASH_FILE
  is '相关附件';
comment on column EPS_POINT_CASH.CASH_MEMO
  is '备注';
comment on column EPS_POINT_CASH.CASH_MONEY
  is '兑现金额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_POINT_CASH
  add constraint PK_EPS_POINT_CASH primary key (CASH_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_POINT_CONSUME
(
  CONSUME_ID         VARCHAR2(50) not null,
  USER_ID            VARCHAR2(50),
  CONSUME_TYPE       CHAR(1),
  CONSUME_DATE       DATE,
  CONSUME_NUMBER     NUMBER(8),
  CONSUME_SOURCE     VARCHAR2(100),
  CONSUME_PROJECT_ID VARCHAR2(50),
  CONSUME_MEMO       VARCHAR2(500)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_POINT_CONSUME
  is '积分消费记录';
-- Add comments to the columns 
comment on column EPS_POINT_CONSUME.CONSUME_TYPE
  is '消费类型 0 参与采购 1罚分';
comment on column EPS_POINT_CONSUME.CONSUME_DATE
  is '消费日期';
comment on column EPS_POINT_CONSUME.CONSUME_NUMBER
  is '消费额度';
comment on column EPS_POINT_CONSUME.CONSUME_SOURCE
  is '使用来源';
comment on column EPS_POINT_CONSUME.CONSUME_PROJECT_ID
  is '所在项目';
comment on column EPS_POINT_CONSUME.CONSUME_MEMO
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_POINT_CONSUME
  add constraint PK_EPS_POINT_CONSUME primary key (CONSUME_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_POINT_DEAL
(
  DEAL_ID     VARCHAR2(50) not null,
  DEAL_DATE   DATE,
  DEAL_NUMBER NUMBER(8),
  FROM_USER   VARCHAR2(50),
  TO_USER     VARCHAR2(50),
  DEAL_MEMO   VARCHAR2(500)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_POINT_DEAL
  is '积分交易';
-- Add comments to the columns 
comment on column EPS_POINT_DEAL.DEAL_DATE
  is '交易日期';
comment on column EPS_POINT_DEAL.DEAL_NUMBER
  is '交易积分额度';
comment on column EPS_POINT_DEAL.FROM_USER
  is '谁赠送';
comment on column EPS_POINT_DEAL.TO_USER
  is '赠送给';
comment on column EPS_POINT_DEAL.DEAL_MEMO
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_POINT_DEAL
  add constraint PK_EPS_POINT_DEAL primary key (DEAL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_PROMOTER_INFO
(
  PROMOTER_INFO_ID VARCHAR2(50) not null,
  USER_ID          VARCHAR2(50),
  PROMOTER_TYPE    VARCHAR2(2),
  CREATE_TIME      TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_PROMOTER_INFO
  is '推广人扩展信息表';
-- Add comments to the columns 
comment on column EPS_PROMOTER_INFO.PROMOTER_INFO_ID
  is '记录号';
comment on column EPS_PROMOTER_INFO.USER_ID
  is '关联用户';
comment on column EPS_PROMOTER_INFO.PROMOTER_TYPE
  is '1 友善大使 2诚信大使 3爱心大使';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PROMOTER_INFO
  add primary key (PROMOTER_INFO_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_POINT_PROMOTER
(
  PROMOTER_ID        VARCHAR2(50) not null,
  USER_ID            VARCHAR2(50),
  ORG_ID             VARCHAR2(50),
  PROMOTER_DATE      DATE,
  P_USER_ID          VARCHAR2(50),
  PROMOTER_MEMO      VARCHAR2(500),
  CREATE_TIME        TIMESTAMP(6),
  RECORD_TYPE        VARCHAR2(2),
  DEAL_STATUS        VARCHAR2(2),
  PROMOTER_NAME      VARCHAR2(50),
  PROMOTER_CID       VARCHAR2(50),
  PROMOTER_UNIT_NAME VARCHAR2(100),
  PROMOTER_UNIT_CODE VARCHAR2(50),
  PROMOTED_LINK_NAME VARCHAR2(50),
  PROMOTED_LINK_TEL  VARCHAR2(50),
  VALIDATION_CODE    VARCHAR2(50),
  CREATOR_ID         VARCHAR2(50),
  DEALUSER_ID        VARCHAR2(50),
  EMAIL              VARCHAR2(50) default 0,
  BUY_DISPOSE        VARCHAR2(200) default 0
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_POINT_PROMOTER
  is '推广人';
-- Add comments to the columns 
comment on column EPS_POINT_PROMOTER.PROMOTER_ID
  is '主键ID';
comment on column EPS_POINT_PROMOTER.USER_ID
  is '推广人';
comment on column EPS_POINT_PROMOTER.ORG_ID
  is '推广单位';
comment on column EPS_POINT_PROMOTER.PROMOTER_DATE
  is '推广日期';
comment on column EPS_POINT_PROMOTER.P_USER_ID
  is '推广单位采购人';
comment on column EPS_POINT_PROMOTER.PROMOTER_MEMO
  is '备注';
comment on column EPS_POINT_PROMOTER.CREATE_TIME
  is '创建时间';
comment on column EPS_POINT_PROMOTER.RECORD_TYPE
  is '录入类型（00 采购人记录 01推广人记录,02注册产生）';
comment on column EPS_POINT_PROMOTER.DEAL_STATUS
  is '处理状态 00 未处理 01已处理, 02管理员已确认';
comment on column EPS_POINT_PROMOTER.PROMOTER_NAME
  is '推广人姓名';
comment on column EPS_POINT_PROMOTER.PROMOTER_CID
  is '推广人身份证号码';
comment on column EPS_POINT_PROMOTER.PROMOTER_UNIT_NAME
  is '推广单位名称';
comment on column EPS_POINT_PROMOTER.PROMOTER_UNIT_CODE
  is '推广单位组织机构代码';
comment on column EPS_POINT_PROMOTER.PROMOTED_LINK_NAME
  is '推广的单位联系人';
comment on column EPS_POINT_PROMOTER.PROMOTED_LINK_TEL
  is '推广的单位联系电话';
comment on column EPS_POINT_PROMOTER.VALIDATION_CODE
  is '验证码';
comment on column EPS_POINT_PROMOTER.CREATOR_ID
  is '创建人';
comment on column EPS_POINT_PROMOTER.DEALUSER_ID
  is '处理人';
comment on column EPS_POINT_PROMOTER.EMAIL
  is '推广的单位Email';
comment on column EPS_POINT_PROMOTER.BUY_DISPOSE
  is '采购意向';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_POINT_PROMOTER
  add primary key (PROMOTER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table EPS_POINT_PROMOTER
  add constraint ORG_NAME unique (PROMOTER_UNIT_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table VOTE_ASSESSED_GRADE
(
  VOTE_ASSESSED_GRADE_ID VARCHAR2(50),
  ASSESSED_ID            VARCHAR2(50),
  POINTER_ID             VARCHAR2(50),
  VOTE_GRADE             NUMBER,
  CREATOR_ID             VARCHAR2(50),
  CREATE_TIME            DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table VOTE_ASSESSED_GRADE
  is '投票评选分值表';
-- Add comments to the columns 
comment on column VOTE_ASSESSED_GRADE.VOTE_ASSESSED_GRADE_ID
  is '记录号';
comment on column VOTE_ASSESSED_GRADE.ASSESSED_ID
  is '投票评选表Id';
comment on column VOTE_ASSESSED_GRADE.POINTER_ID
  is '主题指标表Id';
comment on column VOTE_ASSESSED_GRADE.VOTE_GRADE
  is '评分分值';
comment on column VOTE_ASSESSED_GRADE.CREATOR_ID
  is '创建人';
comment on column VOTE_ASSESSED_GRADE.CREATE_TIME
  is '创建时间';

  -- Create table
create table SM_GIFT_SERIES
(
  SERIES_ID   VARCHAR2(50) not null,
  SERIES_NAME VARCHAR2(100),
  SERIES_CODE VARCHAR2(50),
  PARENT_ID   VARCHAR2(50),
  IS_USED     CHAR(1),
  IS_LEAF     CHAR(1),
  CREATOR_ID  VARCHAR2(50),
  CREATE_TIME TIMESTAMP(6),
  SORT        NUMBER(9)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SM_GIFT_SERIES
  is '礼品系列管理';
-- Add comments to the columns 
comment on column SM_GIFT_SERIES.SERIES_NAME
  is '系列名称';
comment on column SM_GIFT_SERIES.SERIES_CODE
  is '系列编号';
comment on column SM_GIFT_SERIES.PARENT_ID
  is '上级系列id';
comment on column SM_GIFT_SERIES.IS_USED
  is '是否启用 1:启用 2:禁用';
comment on column SM_GIFT_SERIES.IS_LEAF
  is '是否叶子节点';
comment on column SM_GIFT_SERIES.CREATOR_ID
  is '创建人';
comment on column SM_GIFT_SERIES.CREATE_TIME
  is '创建时间';
comment on column SM_GIFT_SERIES.SORT
  is '排序号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SM_GIFT_SERIES
  add primary key (SERIES_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SM_GIFT_SUPPLIER
(
  GIFT_SUPPLIER_ID VARCHAR2(50) not null,
  SUPPLIER_ID      VARCHAR2(50),
  SUPPLIER_NAME    VARCHAR2(200),
  START_TIME       TIMESTAMP(6),
  END_TIME         TIMESTAMP(6),
  CREATOR_ID       VARCHAR2(50),
  CREATE_TIME      TIMESTAMP(6),
  IS_USED          CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SM_GIFT_SUPPLIER
  is '礼品供货商管理';
-- Add comments to the columns 
comment on column SM_GIFT_SUPPLIER.SUPPLIER_ID
  is '供货商id';
comment on column SM_GIFT_SUPPLIER.SUPPLIER_NAME
  is '供货商名称';
comment on column SM_GIFT_SUPPLIER.START_TIME
  is '开始时间';
comment on column SM_GIFT_SUPPLIER.END_TIME
  is '结束时间';
comment on column SM_GIFT_SUPPLIER.CREATOR_ID
  is '创建人';
comment on column SM_GIFT_SUPPLIER.CREATE_TIME
  is '创建时间';
comment on column SM_GIFT_SUPPLIER.IS_USED
  is '是否启用 1:启用 2:禁用';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SM_GIFT_SUPPLIER
  add primary key (GIFT_SUPPLIER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SM_GIFT
(
  GIFT_ID          VARCHAR2(50) not null,
  GIFT_CODE        VARCHAR2(50),
  SERIES_ID        VARCHAR2(50),
  GOODS_ID         VARCHAR2(50),
  GIFT_NAME        VARCHAR2(200),
  GIFT_SUPPLIER_ID VARCHAR2(50),
  GIFT_TYPE        CHAR(2),
  EXCHANGE_COUNT   NUMBER(8),
  START_TIME       TIMESTAMP(6),
  END_TIME         TIMESTAMP(6),
  GIFT_COMMENT     VARCHAR2(4000),
  CREATOR_ID       VARCHAR2(50),
  CREATE_TIME      TIMESTAMP(6),
  IS_USED          CHAR(1),
  PICTURE_ID       VARCHAR2(50),
  IS_RECOMMENDED   CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SM_GIFT
  is '礼品管理';
-- Add comments to the columns 
comment on column SM_GIFT.GIFT_CODE
  is '礼品编号';
comment on column SM_GIFT.SERIES_ID
  is '所属系列id';
comment on column SM_GIFT.GOODS_ID
  is '商品id,虚拟礼品此项不填';
comment on column SM_GIFT.GIFT_NAME
  is '礼品名称';
comment on column SM_GIFT.GIFT_SUPPLIER_ID
  is '礼品供货商id';
comment on column SM_GIFT.GIFT_TYPE
  is '礼品类型,00:实物  01:虚拟';
comment on column SM_GIFT.EXCHANGE_COUNT
  is '库存数量';
comment on column SM_GIFT.START_TIME
  is '开始时间';
comment on column SM_GIFT.END_TIME
  is '结束时间';
comment on column SM_GIFT.GIFT_COMMENT
  is '礼品描述';
comment on column SM_GIFT.CREATOR_ID
  is '创建人';
comment on column SM_GIFT.CREATE_TIME
  is '创建时间';
comment on column SM_GIFT.IS_USED
  is '是否启用 1:启用 2:禁用';
comment on column SM_GIFT.PICTURE_ID
  is '图片';
comment on column SM_GIFT.IS_RECOMMENDED
  is '是否推荐 ';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SM_GIFT
  add primary key (GIFT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SM_EXCHANGE_RULE
(
  ERULE_ID    VARCHAR2(50) not null,
  GIFT_ID     VARCHAR2(50),
  SCORE       NUMBER(8),
  AMOUNT      NUMBER(8,2),
  CREATOR_ID  VARCHAR2(50),
  CREATE_TIME TIMESTAMP(6),
  IS_USED     CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SM_EXCHANGE_RULE
  is '礼品兑换规则';
-- Add comments to the columns 
comment on column SM_EXCHANGE_RULE.GIFT_ID
  is '礼品id';
comment on column SM_EXCHANGE_RULE.SCORE
  is '积分';
comment on column SM_EXCHANGE_RULE.AMOUNT
  is '金额';
comment on column SM_EXCHANGE_RULE.CREATOR_ID
  is '创建人';
comment on column SM_EXCHANGE_RULE.CREATE_TIME
  is '创建时间';
comment on column SM_EXCHANGE_RULE.IS_USED
  is '是否启用 1:启用 2:禁用';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SM_EXCHANGE_RULE
  add primary key (ERULE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SM_GIFT_COMMENT
(
  COMMENT_ID         VARCHAR2(50) not null,
  GIFT_ID            VARCHAR2(50),
  GIFT_COMMENT_TITEL VARCHAR2(100),
  GIFT_COMMENT_LEVEL NUMBER,
  GIFT_COMMENT       VARCHAR2(1000),
  CREATOR_NAME       VARCHAR2(100),
  CREATOR_ID         VARCHAR2(50),
  CREATE_TIME        DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SM_GIFT_COMMENT
  is '礼品评价';
-- Add comments to the columns 
comment on column SM_GIFT_COMMENT.COMMENT_ID
  is '记录号';
comment on column SM_GIFT_COMMENT.GIFT_ID
  is '礼品Id';
comment on column SM_GIFT_COMMENT.GIFT_COMMENT_TITEL
  is '标题';
comment on column SM_GIFT_COMMENT.GIFT_COMMENT_LEVEL
  is '评价级别';
comment on column SM_GIFT_COMMENT.GIFT_COMMENT
  is '评价描述';
comment on column SM_GIFT_COMMENT.CREATOR_NAME
  is '创建人名称';
comment on column SM_GIFT_COMMENT.CREATOR_ID
  is '创建人';
comment on column SM_GIFT_COMMENT.CREATE_TIME
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SM_GIFT_COMMENT
  add primary key (COMMENT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SM_VIRTUAL_GIFT_RECORD
(
  VIRTUAL_RECORD_ID VARCHAR2(50) not null,
  GIFT_ID           VARCHAR2(50),
  ERULE_ID          VARCHAR2(50),
  CREATOR_ID        VARCHAR2(50),
  CREATE_TIME       TIMESTAMP(6),
  DEAL_STATUS       CHAR(2) default 00,
  DEAL_USER         VARCHAR2(50),
  DEAL_TIME         TIMESTAMP(6),
  RECEIVE_EMAIL     VARCHAR2(50),
  CARD_CODE         VARCHAR2(50),
  PASSWORD          VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SM_VIRTUAL_GIFT_RECORD
  is '虚拟礼品兑换记录';
-- Add comments to the columns 
comment on column SM_VIRTUAL_GIFT_RECORD.GIFT_ID
  is '礼品id';
comment on column SM_VIRTUAL_GIFT_RECORD.ERULE_ID
  is '使用兑换规则';
comment on column SM_VIRTUAL_GIFT_RECORD.CREATOR_ID
  is '创建人';
comment on column SM_VIRTUAL_GIFT_RECORD.CREATE_TIME
  is '创建时间';
comment on column SM_VIRTUAL_GIFT_RECORD.DEAL_STATUS
  is '处理状态00 未处理 01已处理';
comment on column SM_VIRTUAL_GIFT_RECORD.DEAL_USER
  is '处理人';
comment on column SM_VIRTUAL_GIFT_RECORD.DEAL_TIME
  is '处理时间';
comment on column SM_VIRTUAL_GIFT_RECORD.RECEIVE_EMAIL
  is '接受邮件';
comment on column SM_VIRTUAL_GIFT_RECORD.CARD_CODE
  is '卡号';
comment on column SM_VIRTUAL_GIFT_RECORD.PASSWORD
  is '密码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SM_VIRTUAL_GIFT_RECORD
  add primary key (VIRTUAL_RECORD_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table MEMBER_CLASS
(
  MEMBER_CLASS_ID   VARCHAR2(50) not null,
  MEMBER_CLASS_NUM  CHAR(1),
  MEMBER_CLASS_NAME VARCHAR2(100),
  MEMBER_CLASS_PIC  VARCHAR2(50),
  MEMBER_CLASS_DESC VARCHAR2(2000),
  MIN_AGE           NUMBER(10),
  MAX_AGE           NUMBER(10),
  MIN_FEE           NUMBER(10,2),
  MAX_FEE           NUMBER(10,2),
  CREATOR           VARCHAR2(50),
  CREATETIME        DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table MEMBER_CLASS
  is '会员级别管理';
-- Add comments to the columns 
comment on column MEMBER_CLASS.MEMBER_CLASS_ID
  is '主键
';
comment on column MEMBER_CLASS.MEMBER_CLASS_NUM
  is '会员级别
';
comment on column MEMBER_CLASS.MEMBER_CLASS_NAME
  is '级别名称
';
comment on column MEMBER_CLASS.MEMBER_CLASS_PIC
  is '级别的图片
';
comment on column MEMBER_CLASS.MEMBER_CLASS_DESC
  is '级别描述
';
comment on column MEMBER_CLASS.MIN_AGE
  is '入会最小年限
';
comment on column MEMBER_CLASS.MAX_AGE
  is '入会最大年限
';
comment on column MEMBER_CLASS.MIN_FEE
  is '缴费最小金额
';
comment on column MEMBER_CLASS.MAX_FEE
  is '缴费最大金额
';
comment on column MEMBER_CLASS.CREATOR
  is '创建人';
comment on column MEMBER_CLASS.CREATETIME
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MEMBER_CLASS
  add constraint PK_MEMBER_CLASS_ID primary key (MEMBER_CLASS_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table MEMBER
(
  MEMBER_ID       VARCHAR2(50) not null,
  ORG_INFO_ID     VARCHAR2(50),
  MEMBER_DURATION NUMBER(10),
  PAY_AMOUNT      NUMBER(23,7),
  MEMBER_CLASS_ID VARCHAR2(50),
  CREATOR         VARCHAR2(50),
  CREATETIME      DATE,
  MODIFIER        VARCHAR2(36),
  MODIFY_TIME     DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 512K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table MEMBER
  is '会员管理';
-- Add comments to the columns 
comment on column MEMBER.MEMBER_ID
  is '主键';
comment on column MEMBER.ORG_INFO_ID
  is '机构名称
';
comment on column MEMBER.MEMBER_DURATION
  is '加入会员时长
';
comment on column MEMBER.PAY_AMOUNT
  is '已缴纳金额
';
comment on column MEMBER.MEMBER_CLASS_ID
  is '会员级别
';
comment on column MEMBER.CREATOR
  is '创建人';
comment on column MEMBER.CREATETIME
  is '创建时间
';
comment on column MEMBER.MODIFIER
  is ' 修改人';
comment on column MEMBER.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MEMBER
  add constraint PK_MEMBER_ID primary key (MEMBER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SERVICE_BASE
(
  SERVICE_ID             VARCHAR2(50) not null,
  SERVICE_NAME           VARCHAR2(200),
  SERVICE_PIC            VARCHAR2(50),
  SERVICE_DESC           VARCHAR2(1000),
  SERVICE_LINK           VARCHAR2(200),
  SERVICE_PRICE          NUMBER(23,7),
  SERVICE_UNIT           VARCHAR2(50),
  SERVICE_PREPOSITION    VARCHAR2(50),
  IS_SINGLE_PURCHASE     CHAR(1),
  IS_RECOMMENDATION      CHAR(1),
  CREATOR                VARCHAR2(50),
  CREATETIME             DATE,
  SERVICE_AGREEMENT_LINK VARCHAR2(200),
  USE_STATUS             CHAR(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SERVICE_BASE.SERVICE_ID
  is '主键';
comment on column SERVICE_BASE.SERVICE_NAME
  is '服务名称
';
comment on column SERVICE_BASE.SERVICE_PIC
  is '服务图片
';
comment on column SERVICE_BASE.SERVICE_DESC
  is '服务简单描述
';
comment on column SERVICE_BASE.SERVICE_LINK
  is '服务介绍外部链接
';
comment on column SERVICE_BASE.SERVICE_PRICE
  is '单价
';
comment on column SERVICE_BASE.SERVICE_UNIT
  is '金额单位
';
comment on column SERVICE_BASE.SERVICE_PREPOSITION
  is '前置服务
';
comment on column SERVICE_BASE.IS_SINGLE_PURCHASE
  is '是否可单独购买
';
comment on column SERVICE_BASE.IS_RECOMMENDATION
  is '是否推荐服务
';
comment on column SERVICE_BASE.CREATOR
  is '创建人
';
comment on column SERVICE_BASE.CREATETIME
  is '创建时间
';
comment on column SERVICE_BASE.SERVICE_AGREEMENT_LINK
  is '服务协议外部链接';
comment on column SERVICE_BASE.USE_STATUS
  is '使用状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVICE_BASE
  add constraint PK_SERVICE_ID primary key (SERVICE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SERVICE_CHARGING
(
  SERVICE_CHARGING_ID VARCHAR2(50) not null,
  MEMBER_CLASS_ID     VARCHAR2(50),
  SERVICE_ID          VARCHAR2(50),
  DURATION            NUMBER(10),
  AMOUNT              NUMBER(23,7),
  DISCOUNT            NUMBER(23,7),
  USE_STATUS          CHAR(2),
  CREATOR             VARCHAR2(50),
  CREATETIME          DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SERVICE_CHARGING
  is '服务计费管理';
-- Add comments to the columns 
comment on column SERVICE_CHARGING.SERVICE_CHARGING_ID
  is '主键';
comment on column SERVICE_CHARGING.MEMBER_CLASS_ID
  is '会员级别';
comment on column SERVICE_CHARGING.SERVICE_ID
  is '服务';
comment on column SERVICE_CHARGING.DURATION
  is '时长';
comment on column SERVICE_CHARGING.AMOUNT
  is '金额
';
comment on column SERVICE_CHARGING.DISCOUNT
  is '折扣率
';
comment on column SERVICE_CHARGING.USE_STATUS
  is '有效状态
';
comment on column SERVICE_CHARGING.CREATOR
  is '创建人
';
comment on column SERVICE_CHARGING.CREATETIME
  is '创建时间
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVICE_CHARGING
  add constraint PK_SERVICE_CHARGING_ID primary key (SERVICE_CHARGING_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SERVICE_GROUP
(
  SERVICE_GROUP_ID  VARCHAR2(50) not null,
  MAIN_SERVICE_ID   VARCHAR2(50),
  DISCOUNT          NUMBER(23,7),
  DURATION          NUMBER(10),
  AMOUNT            NUMBER(23,7),
  USE_STATUS        CHAR(2),
  CREATOR           VARCHAR2(50),
  CREATETIME        DATE,
  APPEND_SERVICE_ID VARCHAR2(50),
  MEMBER_CLASS_ID   VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SERVICE_GROUP.SERVICE_GROUP_ID
  is '主键';
comment on column SERVICE_GROUP.MAIN_SERVICE_ID
  is '主服务';
comment on column SERVICE_GROUP.DISCOUNT
  is '折扣';
comment on column SERVICE_GROUP.DURATION
  is '时长';
comment on column SERVICE_GROUP.AMOUNT
  is '缴费金额';
comment on column SERVICE_GROUP.USE_STATUS
  is '使用状态';
comment on column SERVICE_GROUP.CREATOR
  is '创建人
';
comment on column SERVICE_GROUP.CREATETIME
  is '创建时间
';
comment on column SERVICE_GROUP.APPEND_SERVICE_ID
  is '搭配服务';
comment on column SERVICE_GROUP.MEMBER_CLASS_ID
  is '会员级别';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVICE_GROUP
  add constraint PK_SERVICE_GROUP_ID primary key (SERVICE_GROUP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table SERVICE_SUBSCRIBE
(
  SERVICE_SUBSCRIBE_ID VARCHAR2(50) not null,
  ORG_INFO_ID          VARCHAR2(50),
  SERVICE_ID           VARCHAR2(50),
  START_TIME           DATE,
  END_TIME             DATE,
  PAY_AMOUNT           NUMBER(23,7),
  CREATOR              VARCHAR2(50),
  CREATETIME           DATE,
  AUDIT_STATUS         CHAR(2),
  AUDIT_USER           VARCHAR2(50),
  AUDIT_TIME           DATE,
  REMARK               VARCHAR2(1000)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SERVICE_SUBSCRIBE
  is '服务订阅管理';
-- Add comments to the columns 
comment on column SERVICE_SUBSCRIBE.SERVICE_SUBSCRIBE_ID
  is '主键
';
comment on column SERVICE_SUBSCRIBE.ORG_INFO_ID
  is '机构名称
';
comment on column SERVICE_SUBSCRIBE.SERVICE_ID
  is '订阅的服务
';
comment on column SERVICE_SUBSCRIBE.START_TIME
  is '开始时间
';
comment on column SERVICE_SUBSCRIBE.END_TIME
  is '结束时间
';
comment on column SERVICE_SUBSCRIBE.PAY_AMOUNT
  is '缴费金额
';
comment on column SERVICE_SUBSCRIBE.CREATOR
  is '创建人
';
comment on column SERVICE_SUBSCRIBE.CREATETIME
  is '创建时间
';
comment on column SERVICE_SUBSCRIBE.AUDIT_STATUS
  is '审核状态

';
comment on column SERVICE_SUBSCRIBE.AUDIT_USER
  is '审核人';
comment on column SERVICE_SUBSCRIBE.AUDIT_TIME
  is '审核时间
';
comment on column SERVICE_SUBSCRIBE.REMARK
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVICE_SUBSCRIBE
  add constraint PK_SERVICE_SUBSCRIBE_ID primary key (SERVICE_SUBSCRIBE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
