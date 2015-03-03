-- Create table
create table EPS_PUB_CONTRACT
(
  CONTRACT_ID          VARCHAR2(50) not null,
  CONTRACT_NAME        VARCHAR2(100) not null,
  CONTRACT_NO          VARCHAR2(50) not null,
  BUYER_ID             VARCHAR2(50) not null,
  SUPPLIER_ID          VARCHAR2(50) not null,
  CONTRACT_SIGNED_TIME DATE not null,
  CONTRACT_BEGIN_TIME  DATE not null,
  CONTRACT_END_TIME    DATE not null,
  CONTRACT_TYPE        CHAR(2) default 00,
  MEMO                 VARCHAR2(3000),
  USE_STATUS           CHAR(2) default 00,
  CONTRACT_FILE        VARCHAR2(50),
  CRE_TIME             DATE default sysdate,
  CRE_USER_ID          VARCHAR2(50),
  B_CONFIRM_STATUS     CHAR(2) default 00,
  S_CONFIRM_STATUS     CHAR(2) default 00,
  B_CONFIRM_DATE       DATE,
  B_CONFIRM_USER       VARCHAR2(50),
  S_CONFIRM_DATE       DATE,
  S_CONFIRM_USER       VARCHAR2(50),
  DELIVERY_TIME        VARCHAR2(100) not null,
  DELIVERY_PLACE       VARCHAR2(500) not null,
  B_CONFIRM_OPTION     VARCHAR2(200),
  S_CONFIRM_OPTION     VARCHAR2(200)
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
comment on table EPS_PUB_CONTRACT
  is '采购合同';
-- Add comments to the columns 
comment on column EPS_PUB_CONTRACT.CONTRACT_ID
  is '记录号
';
comment on column EPS_PUB_CONTRACT.CONTRACT_NAME
  is '合同名称
';
comment on column EPS_PUB_CONTRACT.CONTRACT_NO
  is '合同编号
';
comment on column EPS_PUB_CONTRACT.BUYER_ID
  is '采购人ID
';
comment on column EPS_PUB_CONTRACT.SUPPLIER_ID
  is '供应商ID
';
comment on column EPS_PUB_CONTRACT.CONTRACT_SIGNED_TIME
  is '合同签订时间
';
comment on column EPS_PUB_CONTRACT.CONTRACT_BEGIN_TIME
  is '合同开始时间
';
comment on column EPS_PUB_CONTRACT.CONTRACT_END_TIME
  is '合同结束时间
';
comment on column EPS_PUB_CONTRACT.CONTRACT_TYPE
  is '合同类型
01:项目采购合同，02:协议采购合同
';
comment on column EPS_PUB_CONTRACT.MEMO
  is '备注
';
comment on column EPS_PUB_CONTRACT.USE_STATUS
  is '合同状态
00:草稿 01:正式 02：作废
';
comment on column EPS_PUB_CONTRACT.CONTRACT_FILE
  is '合同文件
';
comment on column EPS_PUB_CONTRACT.CRE_TIME
  is '创建时间
';
comment on column EPS_PUB_CONTRACT.CRE_USER_ID
  is '创建人
';
comment on column EPS_PUB_CONTRACT.B_CONFIRM_STATUS
  is '采购人确认状态
00：未确认(默认) 01：确认 
';
comment on column EPS_PUB_CONTRACT.S_CONFIRM_STATUS
  is '供货商确认状态
00：未确认(默认) 01：确认 
';
comment on column EPS_PUB_CONTRACT.B_CONFIRM_DATE
  is '采购人确认时间';
comment on column EPS_PUB_CONTRACT.B_CONFIRM_USER
  is '采购人确认人';
comment on column EPS_PUB_CONTRACT.S_CONFIRM_DATE
  is '供应商确认时间';
comment on column EPS_PUB_CONTRACT.S_CONFIRM_USER
  is '供应商确认人';
comment on column EPS_PUB_CONTRACT.DELIVERY_TIME
  is '交货日期';
comment on column EPS_PUB_CONTRACT.DELIVERY_PLACE
  is '交货地点';
comment on column EPS_PUB_CONTRACT.B_CONFIRM_OPTION
  is '采购人确认意见';
comment on column EPS_PUB_CONTRACT.S_CONFIRM_OPTION
  is '供应商确认意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_CONTRACT
  add constraint PK_CONTRACT_ID primary key (CONTRACT_ID)
  using index 
  tablespace SYSTEM
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
create table EPS_AGREEMENT_ORDER
(
  ORDER_ID         VARCHAR2(50) not null,
  ORDER_NO         VARCHAR2(15) not null,
  CONTRACT_ID      VARCHAR2(50),
  BUYER_ID         VARCHAR2(50) not null,
  SUPPLIER_ID      VARCHAR2(50) not null,
  GOODS_QTY        NUMBER(16,6),
  GOODS_TOTAL      NUMBER(16,6),
  CRE_TIME         DATE default sysdate,
  CRE_USER_ID      VARCHAR2(50),
  B_CONFIRM_DATE   DATE,
  B_CONFIRM_USER   VARCHAR2(50),
  S_CONFIRM_DATE   DATE,
  S_CONFIRM_USER   VARCHAR2(50),
  USE_STATUS       CHAR(2) default 00,
  MEMO             VARCHAR2(200),
  S_CONFIRM_STATUS CHAR(2) default 00,
  B_CONFIRM_STATUS CHAR(2) default 00,
  CATEGORY_NAMES   VARCHAR2(200),
  PROJECT_ID       VARCHAR2(50),
  B_CONFIRM_OPTION VARCHAR2(200),
  S_CONFIRM_OPTION VARCHAR2(200)
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
comment on table EPS_AGREEMENT_ORDER
  is '订单';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_ORDER.ORDER_ID
  is 'ID
';
comment on column EPS_AGREEMENT_ORDER.ORDER_NO
  is '编号
';
comment on column EPS_AGREEMENT_ORDER.CONTRACT_ID
  is '合同ID
';
comment on column EPS_AGREEMENT_ORDER.BUYER_ID
  is '采购人
';
comment on column EPS_AGREEMENT_ORDER.SUPPLIER_ID
  is '供应商
';
comment on column EPS_AGREEMENT_ORDER.GOODS_QTY
  is '总数量
';
comment on column EPS_AGREEMENT_ORDER.GOODS_TOTAL
  is '总金额
';
comment on column EPS_AGREEMENT_ORDER.CRE_TIME
  is '创建时间
';
comment on column EPS_AGREEMENT_ORDER.CRE_USER_ID
  is '创建人
';
comment on column EPS_AGREEMENT_ORDER.B_CONFIRM_DATE
  is '采购人确认时间
';
comment on column EPS_AGREEMENT_ORDER.B_CONFIRM_USER
  is '采购人确认人
';
comment on column EPS_AGREEMENT_ORDER.S_CONFIRM_DATE
  is '供应商确认时间
';
comment on column EPS_AGREEMENT_ORDER.S_CONFIRM_USER
  is '供应商确认人
';
comment on column EPS_AGREEMENT_ORDER.USE_STATUS
  is '状态
00：临时；01：正式；02：作废
';
comment on column EPS_AGREEMENT_ORDER.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_ORDER.S_CONFIRM_STATUS
  is '供货商确认状态
00：未确认(默认) 01：确认02: 退回  
';
comment on column EPS_AGREEMENT_ORDER.B_CONFIRM_STATUS
  is '采购人确认状态
00：未确认(默认) 01：确认02:退回
';
comment on column EPS_AGREEMENT_ORDER.CATEGORY_NAMES
  is '品目名称集';
comment on column EPS_AGREEMENT_ORDER.PROJECT_ID
  is '项目id';
comment on column EPS_AGREEMENT_ORDER.B_CONFIRM_OPTION
  is '采购人意见';
comment on column EPS_AGREEMENT_ORDER.S_CONFIRM_OPTION
  is '供应商意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_ORDER
  add constraint PK_ORDER_ID primary key (ORDER_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table EPS_AGREEMENT_ORDER
  add constraint FK_CONTRACT foreign key (CONTRACT_ID)
  references EPS_PUB_CONTRACT (CONTRACT_ID);

