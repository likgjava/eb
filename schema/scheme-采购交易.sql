-- Create table
create table EPS_AGREEMENT_PROCUREMENTTASK
(
  TASK_ID        VARCHAR2(50) not null,
  TASK_NO        VARCHAR2(15) not null,
  BUYER_ID       VARCHAR2(50) not null,
  SUM_QTY        NUMBER(16,6) default 0,
  SUM_TOTAL      NUMBER(16,6) default 0,
  CRE_TIME       DATE default sysdate,
  CRE_USER_ID    VARCHAR2(50),
  MEMO           VARCHAR2(200),
  USE_STATUS     CHAR(2) default 00,
  CATEGORY_NAMES VARCHAR2(200) not null,
  PERIOD_TYPE    VARCHAR2(50),
  PERIOD_VALUE   VARCHAR2(50)
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
comment on column EPS_AGREEMENT_PROCUREMENTTASK.TASK_ID
  is '主键ID';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.TASK_NO
  is '编号';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.BUYER_ID
  is '采购人
';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.SUM_QTY
  is '总数量
';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.SUM_TOTAL
  is '总预算金额
';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.CRE_TIME
  is '创建时间
';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.CRE_USER_ID
  is '创建人
';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.MEMO
  is '备注';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.USE_STATUS
  is '使用状态00：临时；01：正式；02：作废';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.CATEGORY_NAMES
  is '品目名称集
';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.PERIOD_TYPE
  is '时期类型';
comment on column EPS_AGREEMENT_PROCUREMENTTASK.PERIOD_VALUE
  is '时期值';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_PROCUREMENTTASK
  add constraint PK_TASK_ID primary key (TASK_ID)
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
create table EPS_AGREEMENT_PROTASK_ITEM
(
  TASK_ITEM_ID    VARCHAR2(50) not null,
  TASK_ID         VARCHAR2(50),
  PUR_CATEGORY_ID VARCHAR2(50),
  GOODS_TOTAL     NUMBER(16,6) default 0,
  FIN_GOOD_SQTY   NUMBER(16,6) default 0,
  MEMO            VARCHAR2(200),
  GOODS_QTY       NUMBER(16,6) default 0,
  GOODS_PRICE     NUMBER(16,6) default 0,
  FIN_GOODS_TOTAL NUMBER(16,6) default 0,
  GOODS_CLASS_ID  VARCHAR2(50)
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
comment on table EPS_AGREEMENT_PROTASK_ITEM
  is '任务单条目';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_PROTASK_ITEM.TASK_ITEM_ID
  is 'ID
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.TASK_ID
  is '任务ID
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.PUR_CATEGORY_ID
  is '品目ID
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.GOODS_TOTAL
  is '预算金额
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.FIN_GOOD_SQTY
  is '已完成数量
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.GOODS_QTY
  is '数量
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.GOODS_PRICE
  is '单价
';
comment on column EPS_AGREEMENT_PROTASK_ITEM.FIN_GOODS_TOTAL
  is '已完成金额';
comment on column EPS_AGREEMENT_PROTASK_ITEM.GOODS_CLASS_ID
  is '分类';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_PROTASK_ITEM
  add constraint PK_TASKITEM_ID primary key (TASK_ITEM_ID)
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
create table ECP_TENDER_PROJECT
(
  TENDERID            VARCHAR2(50) not null,
  TENDERNO            VARCHAR2(50),
  AGREEMENTID         VARCHAR2(50),
  AGENCIESID          VARCHAR2(50),
  TENDERNAME          VARCHAR2(100),
  TENDERMETHOD        CHAR(2),
  SUMMARY             VARCHAR2(50),
  SIGNUP_START_DATE   DATE,
  SIGNUP_END_DATE     DATE,
  SUBMIT_START_DATE   DATE,
  SUBMIT_END_DATE     DATE,
  MEETINGROOM         VARCHAR2(50),
  PLAN_START_DATE     DATE,
  PLAN_END_DATE       DATE,
  START_DATE          DATE,
  END_DATE            DATE,
  PARENT_ID           VARCHAR2(50),
  MANAGERID           VARCHAR2(50),
  MONITORID           VARCHAR2(50),
  PROCESSSTATUS       VARCHAR2(3),
  AUDITSTATUS         CHAR(2),
  CREDATE             DATE,
  CREUSER             VARCHAR2(50),
  TEMPLATE_ID         VARCHAR2(50),
  USESTATUS           CHAR(2),
  TASK_PLAN_ID        VARCHAR2(50),
  PURCATEGORY_CODES   VARCHAR2(500),
  SRC_APP             VARCHAR2(50),
  BUDGET_TOTAL_MONEY  NUMBER,
  IMPLSTATUS          CHAR(2),
  BUYER_ID            VARCHAR2(50),
  APPLYID             VARCHAR2(50),
  PURCATEGORY_NAMES   VARCHAR2(500),
  AGENCIESNAME        VARCHAR2(50),
  SRC_URL             VARCHAR2(50),
  ISDIVIDEPACK        VARCHAR2(50),
  BUYERSNAME          VARCHAR2(50),
  TENDERTYPE          CHAR(2),
  EVAL_START_DATE     DATE,
  EVAL_END_DATE       DATE,
  PUR_DOC_PRICE       NUMBER,
  OPEN_BID_ADDR       VARCHAR2(200),
  OPEN_BID_START_DATE DATE,
  BAIL                NUMBER(16,2),
  CONTENT             VARCHAR2(4000),
  BUYERS_ID           VARCHAR2(500),
  BUYERS_NAME         VARCHAR2(500),
  CURRENT_VALID_ID    VARCHAR2(50),
  PURCATEGORY_IDS     VARCHAR2(200),
  GOODS_CLASS_IDS     VARCHAR2(200),
  GOODS_CLASS_NAMES   VARCHAR2(200),
  SYS_FLAG            VARCHAR2(200)
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
comment on table ECP_TENDER_PROJECT
  is '招标项目';
-- Add comments to the columns 
comment on column ECP_TENDER_PROJECT.TENDERNO
  is '项目编号';
comment on column ECP_TENDER_PROJECT.AGREEMENTID
  is '委托协议ID';
comment on column ECP_TENDER_PROJECT.AGENCIESID
  is '代理机构ID';
comment on column ECP_TENDER_PROJECT.TENDERNAME
  is '项目名称';
comment on column ECP_TENDER_PROJECT.TENDERMETHOD
  is '采购方式';
comment on column ECP_TENDER_PROJECT.SUMMARY
  is '摘要';
comment on column ECP_TENDER_PROJECT.SIGNUP_START_DATE
  is '报名开始时间';
comment on column ECP_TENDER_PROJECT.SIGNUP_END_DATE
  is '报名结束时间';
comment on column ECP_TENDER_PROJECT.SUBMIT_START_DATE
  is '投标开始时间';
comment on column ECP_TENDER_PROJECT.SUBMIT_END_DATE
  is '投标结束时间';
comment on column ECP_TENDER_PROJECT.MEETINGROOM
  is '标评室';
comment on column ECP_TENDER_PROJECT.PLAN_START_DATE
  is '计划开始时间';
comment on column ECP_TENDER_PROJECT.PLAN_END_DATE
  is '计划结束时间';
comment on column ECP_TENDER_PROJECT.START_DATE
  is '实际开始时间';
comment on column ECP_TENDER_PROJECT.END_DATE
  is '实际结束时间';
comment on column ECP_TENDER_PROJECT.PARENT_ID
  is '上级项目';
comment on column ECP_TENDER_PROJECT.MANAGERID
  is '项目负责人';
comment on column ECP_TENDER_PROJECT.MONITORID
  is '项目监管人';
comment on column ECP_TENDER_PROJECT.PROCESSSTATUS
  is '过程状态';
comment on column ECP_TENDER_PROJECT.AUDITSTATUS
  is '审核状态';
comment on column ECP_TENDER_PROJECT.CREDATE
  is '创建时间';
comment on column ECP_TENDER_PROJECT.CREUSER
  is '创建人';
comment on column ECP_TENDER_PROJECT.TEMPLATE_ID
  is '计划模版ID';
comment on column ECP_TENDER_PROJECT.USESTATUS
  is '使用状态';
comment on column ECP_TENDER_PROJECT.TASK_PLAN_ID
  is '关联申报书ID';
comment on column ECP_TENDER_PROJECT.PURCATEGORY_CODES
  is '采购品目';
comment on column ECP_TENDER_PROJECT.SRC_APP
  is '来源';
comment on column ECP_TENDER_PROJECT.BUDGET_TOTAL_MONEY
  is '预算总金额';
comment on column ECP_TENDER_PROJECT.IMPLSTATUS
  is '实施状态';
comment on column ECP_TENDER_PROJECT.BUYER_ID
  is '采购商';
comment on column ECP_TENDER_PROJECT.APPLYID
  is '供应商';
comment on column ECP_TENDER_PROJECT.PURCATEGORY_NAMES
  is '采购品目名称';
comment on column ECP_TENDER_PROJECT.AGENCIESNAME
  is '代理机构名称';
comment on column ECP_TENDER_PROJECT.SRC_URL
  is '来源地址';
comment on column ECP_TENDER_PROJECT.ISDIVIDEPACK
  is '是否分包';
comment on column ECP_TENDER_PROJECT.BUYERSNAME
  is '采购人名称';
comment on column ECP_TENDER_PROJECT.TENDERTYPE
  is '项目类型[00:非大宗;01:大宗]';
comment on column ECP_TENDER_PROJECT.EVAL_START_DATE
  is '评标开始时间';
comment on column ECP_TENDER_PROJECT.EVAL_END_DATE
  is '评标结束时间';
comment on column ECP_TENDER_PROJECT.PUR_DOC_PRICE
  is '招标文件价格';
comment on column ECP_TENDER_PROJECT.OPEN_BID_ADDR
  is '开标地点';
comment on column ECP_TENDER_PROJECT.OPEN_BID_START_DATE
  is '开标时间';
comment on column ECP_TENDER_PROJECT.BAIL
  is '保证金金额';
comment on column ECP_TENDER_PROJECT.CONTENT
  is '招标内容';
comment on column ECP_TENDER_PROJECT.BUYERS_ID
  is '项目关联的所有采购人[冗余]，ID以“，”分隔';
comment on column ECP_TENDER_PROJECT.BUYERS_NAME
  is '项目关联的所有采购人[冗余]，名称以“，”分隔';
comment on column ECP_TENDER_PROJECT.CURRENT_VALID_ID
  is '当前有效id';
comment on column ECP_TENDER_PROJECT.PURCATEGORY_IDS
  is '品目IDS';
comment on column ECP_TENDER_PROJECT.GOODS_CLASS_IDS
  is '商品分类IDS';
comment on column ECP_TENDER_PROJECT.GOODS_CLASS_NAMES
  is '商品分类名称';
comment on column ECP_TENDER_PROJECT.SYS_FLAG
  is '所属系统标识';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_TENDER_PROJECT
  add constraint PK_ECP_TENDER_PROJECT primary key (TENDERID)
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
create table ECP_PROJ_PROCESSRULE
(
  PROCE_ID      VARCHAR2(36) not null,
  PROJ_ID       VARCHAR2(36),
  SYS_ITEM_ID   VARCHAR2(36),
  SYS_ITEM_NAME VARCHAR2(50),
  RES_VALUE     VARCHAR2(500),
  CREATE_USER   VARCHAR2(36),
  CREATE_TIME   DATE,
  MODIFY_USER   VARCHAR2(36),
  MODIFY_TIME   DATE,
  ITEM_CODE     VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column ECP_PROJ_PROCESSRULE.PROCE_ID
  is '主键';
comment on column ECP_PROJ_PROCESSRULE.PROJ_ID
  is '关联项目ID';
comment on column ECP_PROJ_PROCESSRULE.SYS_ITEM_ID
  is '规则Id';
comment on column ECP_PROJ_PROCESSRULE.SYS_ITEM_NAME
  is '规则名称';
comment on column ECP_PROJ_PROCESSRULE.RES_VALUE
  is '响应值';
comment on column ECP_PROJ_PROCESSRULE.CREATE_USER
  is '创建人';
comment on column ECP_PROJ_PROCESSRULE.CREATE_TIME
  is '创建时间';
comment on column ECP_PROJ_PROCESSRULE.MODIFY_USER
  is '修改人';
comment on column ECP_PROJ_PROCESSRULE.MODIFY_TIME
  is '修改时间';
comment on column ECP_PROJ_PROCESSRULE.ITEM_CODE
  is '编号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PROJ_PROCESSRULE
  add constraint UQ_ECP_PROJ_PROCESSR_PROCE_ID primary key (PROCE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EPS_AGREEMENT_BARGAIN_TURN
(
  BARGAIN_TURN_ID VARCHAR2(50) not null,
  PROJECT_ID      VARCHAR2(50),
  TRUN_NO         NUMBER(2),
  START_TIME      DATE,
  END_TIME        DATE,
  TOTAL_CUT       NUMBER(16,6),
  TOTAL_CUT_TYPE  CHAR(2)
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
comment on table EPS_AGREEMENT_BARGAIN_TURN
  is '竞价轮次表';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_BARGAIN_TURN.BARGAIN_TURN_ID
  is '记录号';
comment on column EPS_AGREEMENT_BARGAIN_TURN.PROJECT_ID
  is '项目ID
';
comment on column EPS_AGREEMENT_BARGAIN_TURN.TRUN_NO
  is '轮次号
';
comment on column EPS_AGREEMENT_BARGAIN_TURN.START_TIME
  is '开始时间
';
comment on column EPS_AGREEMENT_BARGAIN_TURN.END_TIME
  is '结束时间
';
comment on column EPS_AGREEMENT_BARGAIN_TURN.TOTAL_CUT
  is '降价幅度
';
comment on column EPS_AGREEMENT_BARGAIN_TURN.TOTAL_CUT_TYPE
  is '降幅类型';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_BARGAIN_TURN
  add constraint PK_BARGAIN_TURN_ID primary key (BARGAIN_TURN_ID)
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
create table ECP_PROJECT_CONTACT_INFO
(
  ECP_PROJECT_CONTACT_INFO_ID VARCHAR2(50) not null,
  LINKER                      VARCHAR2(200),
  MOBILE_PHONE                VARCHAR2(200),
  FIXED_TELEPHONE             VARCHAR2(200),
  FAX                         VARCHAR2(200),
  ADDRESS                     VARCHAR2(200),
  POST_CODE                   VARCHAR2(200),
  PROJECT_ID                  VARCHAR2(50)
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
comment on column ECP_PROJECT_CONTACT_INFO.LINKER
  is '联系人';
comment on column ECP_PROJECT_CONTACT_INFO.MOBILE_PHONE
  is '移动电话';
comment on column ECP_PROJECT_CONTACT_INFO.FIXED_TELEPHONE
  is '固定电话';
comment on column ECP_PROJECT_CONTACT_INFO.FAX
  is '传真';
comment on column ECP_PROJECT_CONTACT_INFO.ADDRESS
  is '地址';
comment on column ECP_PROJECT_CONTACT_INFO.POST_CODE
  is '邮编';
comment on column ECP_PROJECT_CONTACT_INFO.PROJECT_ID
  is '项目';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PROJECT_CONTACT_INFO
  add primary key (ECP_PROJECT_CONTACT_INFO_ID)
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
create table ECP_PROJECT_PAY_INFO
(
  ECP_PROJECT_EXT_PAY_ID VARCHAR2(50) not null,
  PROJECT_ID             VARCHAR2(50),
  DELIVERY_DATE          VARCHAR2(100),
  DELIVERY_ADDRESS       VARCHAR2(100),
  DELIVERY_TYPE          VARCHAR2(100),
  PAY_TYPE               VARCHAR2(100),
  SIGN_CONDITION         VARCHAR2(1000),
  SUPPLEMENT             VARCHAR2(1000)
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
comment on table ECP_PROJECT_PAY_INFO
  is '项目支付信息';
-- Add comments to the columns 
comment on column ECP_PROJECT_PAY_INFO.ECP_PROJECT_EXT_PAY_ID
  is '主键';
comment on column ECP_PROJECT_PAY_INFO.PROJECT_ID
  is '项目';
comment on column ECP_PROJECT_PAY_INFO.DELIVERY_DATE
  is '交货时间';
comment on column ECP_PROJECT_PAY_INFO.DELIVERY_ADDRESS
  is '交货地点';
comment on column ECP_PROJECT_PAY_INFO.DELIVERY_TYPE
  is '交货方式';
comment on column ECP_PROJECT_PAY_INFO.PAY_TYPE
  is '付款方式';
comment on column ECP_PROJECT_PAY_INFO.SIGN_CONDITION
  is '报名条件';
comment on column ECP_PROJECT_PAY_INFO.SUPPLEMENT
  is '补充说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PROJECT_PAY_INFO
  add primary key (ECP_PROJECT_EXT_PAY_ID)
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
create table ECP_PROJECT_SIGN_INFO
(
  ECP_PROJECT_SIGN_ID   VARCHAR2(50) not null,
  COMPANY_QUALIFICATION VARCHAR2(1000),
  COMPANY_PRODUCTIVITY  VARCHAR2(1000),
  QUALITY_STANDARD      VARCHAR2(1000),
  ADDITIONAL_MEMO       VARCHAR2(200),
  REGISTERED_CAPITAL    VARCHAR2(50),
  PROJECT_ID            VARCHAR2(50)
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
comment on table ECP_PROJECT_SIGN_INFO
  is '项目报名扩展信息';
-- Add comments to the columns 
comment on column ECP_PROJECT_SIGN_INFO.ECP_PROJECT_SIGN_ID
  is '主键';
comment on column ECP_PROJECT_SIGN_INFO.COMPANY_QUALIFICATION
  is '企业资质';
comment on column ECP_PROJECT_SIGN_INFO.COMPANY_PRODUCTIVITY
  is '企业产能';
comment on column ECP_PROJECT_SIGN_INFO.QUALITY_STANDARD
  is '质量认证';
comment on column ECP_PROJECT_SIGN_INFO.ADDITIONAL_MEMO
  is '附加语';
comment on column ECP_PROJECT_SIGN_INFO.REGISTERED_CAPITAL
  is '注册资金';
comment on column ECP_PROJECT_SIGN_INFO.PROJECT_ID
  is '项目id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PROJECT_SIGN_INFO
  add primary key (ECP_PROJECT_SIGN_ID)
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
create table ECP_PROJECT_EXT_INFO
(
  ECP_PROJECT_EXT_INFO_ID VARCHAR2(50) not null,
  PROJECT_ID              VARCHAR2(50),
  DELIVERY_DATE           VARCHAR2(100),
  DELIVERY_ADDRESS        VARCHAR2(100),
  DELIVERY_TYPE           VARCHAR2(20),
  PAY_TYPE                VARCHAR2(20),
  SIGN_CONDITION          VARCHAR2(1000),
  SUPPLEMENT              VARCHAR2(1000)
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
comment on table ECP_PROJECT_EXT_INFO
  is '项目扩展信息';
-- Add comments to the columns 
comment on column ECP_PROJECT_EXT_INFO.ECP_PROJECT_EXT_INFO_ID
  is '主键';
comment on column ECP_PROJECT_EXT_INFO.PROJECT_ID
  is '项目';
comment on column ECP_PROJECT_EXT_INFO.DELIVERY_DATE
  is '交货时间';
comment on column ECP_PROJECT_EXT_INFO.DELIVERY_ADDRESS
  is '交货地点';
comment on column ECP_PROJECT_EXT_INFO.DELIVERY_TYPE
  is '交货方式';
comment on column ECP_PROJECT_EXT_INFO.PAY_TYPE
  is '付款方式';
comment on column ECP_PROJECT_EXT_INFO.SIGN_CONDITION
  is '报名条件';
comment on column ECP_PROJECT_EXT_INFO.SUPPLEMENT
  is '补充说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PROJECT_EXT_INFO
  add constraint ECP_PROJECT_EXT_INFO_PK primary key (ECP_PROJECT_EXT_INFO_ID)
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
create table EPS_AGREEMENT_REQUIRE_ITEM
(
  REQUIRE_DTL_ID  VARCHAR2(50) not null,
  PROJECT_ID      VARCHAR2(50),
  GOODS_ID        VARCHAR2(50),
  GOODS_QTY       NUMBER(16,6),
  GOODS_UNIT      VARCHAR2(20),
  AGREE_PRICE     NUMBER(16,6),
  MARKET_PRICE    NUMBER(16,6),
  GOODS_PRICE     NUMBER(16,6),
  GOODS_TOTAL     NUMBER(16,6),
  GOODS_DESCR     VARCHAR2(4000),
  MEMO            VARCHAR2(200),
  GOODS_CLASS_ID  VARCHAR2(50),
  PUR_CATEGORY_ID VARCHAR2(50)
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
comment on table EPS_AGREEMENT_REQUIRE_ITEM
  is '需求条目';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_REQUIRE_ITEM.REQUIRE_DTL_ID
  is 'ID
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.PROJECT_ID
  is '项目id
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_ID
  is '商品ID
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_QTY
  is '数量
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_UNIT
  is '计量单位
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.AGREE_PRICE
  is '协议价
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.MARKET_PRICE
  is '市场价
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_PRICE
  is '单价
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_TOTAL
  is '金额
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_DESCR
  is '商品描述
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.GOODS_CLASS_ID
  is '分类';
comment on column EPS_AGREEMENT_REQUIRE_ITEM.PUR_CATEGORY_ID
  is '品目';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_REQUIRE_ITEM
  add constraint PK_REQUIRE_DTL_ID primary key (REQUIRE_DTL_ID)
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
create table EPS_AGREE_REQUIRE_GOODS_OPTION
(
  REQUIRE_OPT_ID   VARCHAR2(50) not null,
  OPTION_ID        VARCHAR2(100),
  REQUIRE_DTL_ID   VARCHAR2(50),
  OPT_QTY          NUMBER(16,6),
  OPT_UNIT         VARCHAR2(20),
  OPT_PRICE        NUMBER(16,6),
  OPT_MARKET_PRICE NUMBER(16,6),
  OPT_AGREE_PRICE  NUMBER(16,6)
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
comment on table EPS_AGREE_REQUIRE_GOODS_OPTION
  is '需求商品选配';
-- Add comments to the columns 
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.REQUIRE_OPT_ID
  is '记录号
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.OPTION_ID
  is '商品库选配Id
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.REQUIRE_DTL_ID
  is '需求明细id
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.OPT_QTY
  is '数量
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.OPT_UNIT
  is '计量单位
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.OPT_PRICE
  is '选配价
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.OPT_MARKET_PRICE
  is '选配市场价
';
comment on column EPS_AGREE_REQUIRE_GOODS_OPTION.OPT_AGREE_PRICE
  is '选配协议价
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_REQUIRE_GOODS_OPTION
  add constraint PK_REQUIRE_OPT_ID primary key (REQUIRE_OPT_ID)
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
create table EPS_AGREE_REQUIRE_GOODS_ACCESS
(
  REQUIRE_ACC_ID       VARCHAR2(50) not null,
  GOODS_ACCESSORIES_ID VARCHAR2(100),
  REQUIRE_DTL_ID       VARCHAR2(50),
  ACC_PRICE            VARCHAR2(20)
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
comment on table EPS_AGREE_REQUIRE_GOODS_ACCESS
  is '需求商品配件';
-- Add comments to the columns 
comment on column EPS_AGREE_REQUIRE_GOODS_ACCESS.REQUIRE_ACC_ID
  is '主键';
comment on column EPS_AGREE_REQUIRE_GOODS_ACCESS.GOODS_ACCESSORIES_ID
  is '配件Id
';
comment on column EPS_AGREE_REQUIRE_GOODS_ACCESS.REQUIRE_DTL_ID
  is '需求明细id
';
comment on column EPS_AGREE_REQUIRE_GOODS_ACCESS.ACC_PRICE
  is '配件价格
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_REQUIRE_GOODS_ACCESS
  add constraint PK_REQUIRE_ACC_ID primary key (REQUIRE_ACC_ID)
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
create table EPS_AGREE_REQUIRE_GOODS_GIFT
(
  REQUIRE_GOODS_GIFT_ID VARCHAR2(50) not null,
  REQUIRE_ITEM_ID       VARCHAR2(50),
  GOODS_GIFT_ID         VARCHAR2(50),
  GIFT_PRICE            NUMBER(23,7)
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
comment on table EPS_AGREE_REQUIRE_GOODS_GIFT
  is '需求商品礼包';
-- Add comments to the columns 
comment on column EPS_AGREE_REQUIRE_GOODS_GIFT.REQUIRE_GOODS_GIFT_ID
  is '主键';
comment on column EPS_AGREE_REQUIRE_GOODS_GIFT.REQUIRE_ITEM_ID
  is '需求条目ID';
comment on column EPS_AGREE_REQUIRE_GOODS_GIFT.GOODS_GIFT_ID
  is '商品礼包ID';
comment on column EPS_AGREE_REQUIRE_GOODS_GIFT.GIFT_PRICE
  is '礼包价格';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_REQUIRE_GOODS_GIFT
  add constraint REQUIRE_GOODS_GIFT_PK primary key (REQUIRE_GOODS_GIFT_ID)
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
create table ECP_TEND_M_TASK
(
  TEND_M_TASK_ID   VARCHAR2(50) not null,
  TENDERID         VARCHAR2(50),
  TASK_PLAN_SUB_ID VARCHAR2(50),
  BUY_MAIN_BODY    VARCHAR2(50),
  PURCHASE_ID      VARCHAR2(50),
  PURCHASE_NAME    VARCHAR2(200)
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
comment on table ECP_TEND_M_TASK
  is '招标项目与申请书中间表[创建项目时使用]';
-- Add comments to the columns 
comment on column ECP_TEND_M_TASK.TEND_M_TASK_ID
  is 'ID';
comment on column ECP_TEND_M_TASK.TENDERID
  is '招标项目ID';
comment on column ECP_TEND_M_TASK.TASK_PLAN_SUB_ID
  is '申报书条目ID';
comment on column ECP_TEND_M_TASK.BUY_MAIN_BODY
  is '采购主体';
comment on column ECP_TEND_M_TASK.PURCHASE_ID
  is '品目ID';
comment on column ECP_TEND_M_TASK.PURCHASE_NAME
  is '品目名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_TEND_M_TASK
  add constraint PK_E_TEND_M_T primary key (TEND_M_TASK_ID)
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
create table EPS_AGREEMENT_REQUIRE_TASK
(
  REQUIRE_TASK_ID VARCHAR2(50) not null,
  CREATOR         VARCHAR2(36),
  CREATE_TIME     DATE,
  REQUIRE_ITEM_ID VARCHAR2(50),
  TASK_ITEM_ID    VARCHAR2(50)
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
comment on column EPS_AGREEMENT_REQUIRE_TASK.REQUIRE_TASK_ID
  is '主键';
comment on column EPS_AGREEMENT_REQUIRE_TASK.CREATOR
  is ' 创建人';
comment on column EPS_AGREEMENT_REQUIRE_TASK.CREATE_TIME
  is '创建时间';
comment on column EPS_AGREEMENT_REQUIRE_TASK.REQUIRE_ITEM_ID
  is '需求条目ID';
comment on column EPS_AGREEMENT_REQUIRE_TASK.TASK_ITEM_ID
  is '任务书条目ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_REQUIRE_TASK
  add constraint PK_REQUIRE_TASK_ID primary key (REQUIRE_TASK_ID)
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
create table ECP_BASE_BULLETIN
(
  BULLETIN_ID       VARCHAR2(50) not null,
  TENDERID          VARCHAR2(50),
  BULLETIN_NO       VARCHAR2(50),
  BULLETIN_TYPE     CHAR(2),
  TITLE             VARCHAR2(1000),
  CONTENT           VARCHAR2(50),
  SIGNUP_START_DATE DATE,
  SIGNUP_END_DATE   DATE,
  SUBMIT_START_DATE DATE,
  SUBMIT_END_DATE   DATE,
  AUDITSTATUS       CHAR(2),
  RELSTATUS         CHAR(2),
  RELDATE           DATE,
  RELUSER           VARCHAR2(50),
  CREDATE           DATE,
  CREUSER           VARCHAR2(50),
  USESTATUS         CHAR(2),
  DX_FLAG           CHAR(1),
  DX_UUID           NUMBER,
  CURRENT_VALID_ID  VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 640K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_BASE_BULLETIN
  is '公告/公示';
-- Add comments to the columns 
comment on column ECP_BASE_BULLETIN.TENDERID
  is '招标项目ID';
comment on column ECP_BASE_BULLETIN.BULLETIN_NO
  is '编号';
comment on column ECP_BASE_BULLETIN.BULLETIN_TYPE
  is '公告类型';
comment on column ECP_BASE_BULLETIN.TITLE
  is '标题';
comment on column ECP_BASE_BULLETIN.CONTENT
  is '公告内容[关联附件ID]';
comment on column ECP_BASE_BULLETIN.SIGNUP_START_DATE
  is '报名开始时间';
comment on column ECP_BASE_BULLETIN.SIGNUP_END_DATE
  is '报名结束时间';
comment on column ECP_BASE_BULLETIN.SUBMIT_START_DATE
  is '投标开始时间';
comment on column ECP_BASE_BULLETIN.SUBMIT_END_DATE
  is '投标结束时间';
comment on column ECP_BASE_BULLETIN.AUDITSTATUS
  is '审核状态';
comment on column ECP_BASE_BULLETIN.RELSTATUS
  is '发布状态';
comment on column ECP_BASE_BULLETIN.RELDATE
  is '发布时间';
comment on column ECP_BASE_BULLETIN.RELUSER
  is '发布人';
comment on column ECP_BASE_BULLETIN.CREDATE
  is '创建时间';
comment on column ECP_BASE_BULLETIN.CREUSER
  is '创建人';
comment on column ECP_BASE_BULLETIN.USESTATUS
  is '使用状态';
comment on column ECP_BASE_BULLETIN.CURRENT_VALID_ID
  is '当前有效id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BASE_BULLETIN
  add constraint PK_ECP_BASE_BULLETIN primary key (BULLETIN_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table ECP_TENDER_APPLY_REC
(
  APPLYID          VARCHAR2(50) not null,
  TENDERID         VARCHAR2(50),
  SUPPLYERID       VARCHAR2(50),
  APPLYDATE        DATE,
  LINKER           VARCHAR2(50),
  IDCARD           VARCHAR2(50),
  LINKER_TEL       VARCHAR2(50),
  ADDRESS          VARCHAR2(500),
  ZIPCODE          VARCHAR2(50),
  AUDITSTATUS      VARCHAR2(50),
  AUDITUSER        VARCHAR2(50),
  AUDITDATE        DATE,
  APPLYSTATUS      VARCHAR2(50),
  UNAPPLYREASON    VARCHAR2(50),
  UNAPPLYUSER      VARCHAR2(50),
  UNAPPLYDATE      DATE,
  MEMO             VARCHAR2(200),
  ATTACH_REAL_ID   VARCHAR2(50),
  SUPPLYERNAME     VARCHAR2(100),
  CREUSER          VARCHAR2(50),
  CREDATE          DATE,
  CURRENT_VALID_ID VARCHAR2(50)
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
comment on table ECP_TENDER_APPLY_REC
  is '供应商报名';
-- Add comments to the columns 
comment on column ECP_TENDER_APPLY_REC.TENDERID
  is '招标项目';
comment on column ECP_TENDER_APPLY_REC.SUPPLYERID
  is '供应商';
comment on column ECP_TENDER_APPLY_REC.APPLYDATE
  is '报名时间';
comment on column ECP_TENDER_APPLY_REC.LINKER
  is '联系人';
comment on column ECP_TENDER_APPLY_REC.IDCARD
  is '身份证号';
comment on column ECP_TENDER_APPLY_REC.LINKER_TEL
  is '联系电话';
comment on column ECP_TENDER_APPLY_REC.ADDRESS
  is '联系地址';
comment on column ECP_TENDER_APPLY_REC.ZIPCODE
  is '邮编';
comment on column ECP_TENDER_APPLY_REC.AUDITSTATUS
  is '审核状态';
comment on column ECP_TENDER_APPLY_REC.AUDITUSER
  is '审核人';
comment on column ECP_TENDER_APPLY_REC.AUDITDATE
  is '审核时间';
comment on column ECP_TENDER_APPLY_REC.APPLYSTATUS
  is '报名状态';
comment on column ECP_TENDER_APPLY_REC.UNAPPLYREASON
  is '撤销原因';
comment on column ECP_TENDER_APPLY_REC.UNAPPLYUSER
  is '撤销人';
comment on column ECP_TENDER_APPLY_REC.UNAPPLYDATE
  is '撤销时间';
comment on column ECP_TENDER_APPLY_REC.MEMO
  is '备注';
comment on column ECP_TENDER_APPLY_REC.ATTACH_REAL_ID
  is '关联附件';
comment on column ECP_TENDER_APPLY_REC.SUPPLYERNAME
  is '供应商名称（冗余）';
comment on column ECP_TENDER_APPLY_REC.CREUSER
  is '创建人';
comment on column ECP_TENDER_APPLY_REC.CREDATE
  is '创建时间';
comment on column ECP_TENDER_APPLY_REC.CURRENT_VALID_ID
  is '当前有效ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_TENDER_APPLY_REC
  add constraint PK_ECP_T_A_REC primary key (APPLYID)
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
create table ECP_INVITEROLLREQU
(
  INRQ_ID          VARCHAR2(36) not null,
  PROJ_ID          VARCHAR2(36),
  PROJ_CODE        VARCHAR2(100),
  PROJ_NAME        VARCHAR2(50),
  INRQ_KIND        VARCHAR2(50),
  VERSION          NUMBER(8,2),
  RECOM_DESC       VARCHAR2(500),
  SELECT_TYPE      VARCHAR2(50),
  ATTACH_REAL_ID   VARCHAR2(36),
  USE_STATUS       CHAR(2),
  CREATE_USER      VARCHAR2(36),
  CREATE_TIME      DATE,
  MODIFY_USER      VARCHAR2(36),
  MODIFY_TIME      DATE,
  AUDIT_STATUS     CHAR(2),
  CURRENT_VALID_ID VARCHAR2(50)
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
comment on table ECP_INVITEROLLREQU
  is '邀请名单申请单';
-- Add comments to the columns 
comment on column ECP_INVITEROLLREQU.INRQ_ID
  is '主键';
comment on column ECP_INVITEROLLREQU.PROJ_ID
  is '关联项目ID';
comment on column ECP_INVITEROLLREQU.PROJ_CODE
  is '项目编号';
comment on column ECP_INVITEROLLREQU.PROJ_NAME
  is '项目名称';
comment on column ECP_INVITEROLLREQU.INRQ_KIND
  is '邀请函种类（注：正常函、撤销函；系统默认为正常邀请函，当需要撤销某供应商邀请资格时使用撤销函；正常函及撤销函分不同对象）';
comment on column ECP_INVITEROLLREQU.VERSION
  is '邀请名单申请单版本号（注：每提交一次，版本号加1）';
comment on column ECP_INVITEROLLREQU.RECOM_DESC
  is '名单推荐说明';
comment on column ECP_INVITEROLLREQU.SELECT_TYPE
  is '供应商选择种类（人工指定、随机抽取、范围抽取、任意<抽取+指定>）';
comment on column ECP_INVITEROLLREQU.ATTACH_REAL_ID
  is '关联附件ID';
comment on column ECP_INVITEROLLREQU.USE_STATUS
  is '使用状态[00:临时;01:正式;02:作废]';
comment on column ECP_INVITEROLLREQU.CREATE_USER
  is '创建人';
comment on column ECP_INVITEROLLREQU.CREATE_TIME
  is '创建时间';
comment on column ECP_INVITEROLLREQU.MODIFY_USER
  is '修改人';
comment on column ECP_INVITEROLLREQU.MODIFY_TIME
  is '修改时间';
comment on column ECP_INVITEROLLREQU.AUDIT_STATUS
  is '00:新建;01:审核中;02:审核通过;03:审核不通过';
comment on column ECP_INVITEROLLREQU.CURRENT_VALID_ID
  is '当前有效id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_INVITEROLLREQU
  add constraint PK_ES_INVITEROLLREQU primary key (INRQ_ID)
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
create table ECP_INRQ_DETAIL
(
  INRQ_D_ID          VARCHAR2(36) not null,
  INRQ_ID            VARCHAR2(36) not null,
  SUB_PROJ_ID        VARCHAR2(36),
  SUB_PROJ_CODE      VARCHAR2(100),
  SUB_PROJ_NAME      VARCHAR2(50),
  SUPPLIER_ID        VARCHAR2(36) not null,
  INRQ_D_RECEM_RESON VARCHAR2(500),
  INRQ_D_KIND        VARCHAR2(50),
  INRQ_D_CONTENT     VARCHAR2(500),
  USE_STATUS         CHAR(2),
  CREATE_USER        VARCHAR2(36),
  CREATE_TIME        DATE,
  MODIFY_USER        VARCHAR2(36),
  MODIFY_TIME        DATE,
  SUPPLIER_NAME      VARCHAR2(500)
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
comment on table ECP_INRQ_DETAIL
  is '邀请名单';
-- Add comments to the columns 
comment on column ECP_INRQ_DETAIL.INRQ_D_ID
  is '主键';
comment on column ECP_INRQ_DETAIL.SUB_PROJ_ID
  is '关联包组ID';
comment on column ECP_INRQ_DETAIL.SUB_PROJ_CODE
  is '包组编号';
comment on column ECP_INRQ_DETAIL.SUB_PROJ_NAME
  is '包组名称';
comment on column ECP_INRQ_DETAIL.SUPPLIER_ID
  is '供应商ID';
comment on column ECP_INRQ_DETAIL.INRQ_D_RECEM_RESON
  is '推荐理由';
comment on column ECP_INRQ_DETAIL.INRQ_D_KIND
  is '邀请函种类（注：正常函、撤销函；系统默认为正常邀请函，当需要撤销某供应商邀请资格时使用撤销函；正常函及撤销函分不同对象）';
comment on column ECP_INRQ_DETAIL.INRQ_D_CONTENT
  is '邀请函件内容';
comment on column ECP_INRQ_DETAIL.USE_STATUS
  is '使用状态[00:临时;01:正式;02:作废]';
comment on column ECP_INRQ_DETAIL.CREATE_USER
  is '创建人';
comment on column ECP_INRQ_DETAIL.CREATE_TIME
  is '创建时间';
comment on column ECP_INRQ_DETAIL.MODIFY_USER
  is '修改人';
comment on column ECP_INRQ_DETAIL.MODIFY_TIME
  is '修改时间';
comment on column ECP_INRQ_DETAIL.SUPPLIER_NAME
  is '供应商名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_INRQ_DETAIL
  add constraint PK_ES_INRQ_DETAIL primary key (INRQ_D_ID)
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
alter table ECP_INRQ_DETAIL
  add constraint FK_INRQ_ID foreign key (INRQ_ID)
  references ECP_INVITEROLLREQU (INRQ_ID);

  -- Create table
create table ECP_BUYRESULT
(
  BUYR_ID          VARCHAR2(36) not null,
  PROJ_ID          VARCHAR2(50),
  PROJ_CODE        VARCHAR2(500),
  PROJ_NAME        VARCHAR2(500),
  SUB_PROJ_ID      VARCHAR2(36),
  SUB_PROJ_CODE    VARCHAR2(500),
  SUB_PROJ_NAME    VARCHAR2(500),
  EBUY_METHOD      VARCHAR2(50),
  BUYR_RESULT      VARCHAR2(50),
  BUYR_OPINION     VARCHAR2(500),
  CREATE_USER      VARCHAR2(50),
  CREATE_TIME      DATE,
  MODIFY_USER      VARCHAR2(50),
  MODIFY_TIME      DATE,
  USE_STATUS       CHAR(2),
  CURRENT_VALID_ID VARCHAR2(50)
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
comment on column ECP_BUYRESULT.BUYR_ID
  is '主键';
comment on column ECP_BUYRESULT.PROJ_ID
  is '相关项目ID
';
comment on column ECP_BUYRESULT.PROJ_CODE
  is '相关项目编号
';
comment on column ECP_BUYRESULT.PROJ_NAME
  is '相关项目名称';
comment on column ECP_BUYRESULT.SUB_PROJ_ID
  is '相关包组ID';
comment on column ECP_BUYRESULT.SUB_PROJ_CODE
  is '相关包组编号';
comment on column ECP_BUYRESULT.SUB_PROJ_NAME
  is '相关包组名称';
comment on column ECP_BUYRESULT.EBUY_METHOD
  is '采购方式';
comment on column ECP_BUYRESULT.BUYR_RESULT
  is '招标结果（选定、放弃、无人参与、没有足够的参与者）';
comment on column ECP_BUYRESULT.BUYR_OPINION
  is '意见';
comment on column ECP_BUYRESULT.CREATE_USER
  is '创建人';
comment on column ECP_BUYRESULT.CREATE_TIME
  is '创建时间';
comment on column ECP_BUYRESULT.MODIFY_USER
  is '修改人';
comment on column ECP_BUYRESULT.MODIFY_TIME
  is '修改时间';
comment on column ECP_BUYRESULT.USE_STATUS
  is '使用状态';
comment on column ECP_BUYRESULT.CURRENT_VALID_ID
  is '历史id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BUYRESULT
  add constraint BUYR_ID primary key (BUYR_ID)
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
create table ECP_BUY_WINNER
(
  BUY_W_ID    VARCHAR2(36) not null,
  SELLER_ID   VARCHAR2(36),
  SELLER_NAME VARCHAR2(100),
  BUYR_ID     VARCHAR2(36),
  CREATE_USER VARCHAR2(50),
  CREATE_TIME DATE,
  MODIFY_USER VARCHAR2(50),
  MODIFY_TIME DATE,
  USE_STATUS  CHAR(2),
  RESULT_TYPE CHAR(2)
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
comment on column ECP_BUY_WINNER.BUY_W_ID
  is '主键';
comment on column ECP_BUY_WINNER.SELLER_ID
  is '供应商ID';
comment on column ECP_BUY_WINNER.SELLER_NAME
  is '供应商名称';
comment on column ECP_BUY_WINNER.BUYR_ID
  is '成交结果ID';
comment on column ECP_BUY_WINNER.CREATE_USER
  is '创建人';
comment on column ECP_BUY_WINNER.CREATE_TIME
  is '创建时间';
comment on column ECP_BUY_WINNER.MODIFY_USER
  is '修改人';
comment on column ECP_BUY_WINNER.MODIFY_TIME
  is '修改时间';
comment on column ECP_BUY_WINNER.USE_STATUS
  is '使用状态';
comment on column ECP_BUY_WINNER.RESULT_TYPE
  is '成交类型[00:成交;01:不成交]';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BUY_WINNER
  add constraint BUY_W_ID primary key (BUY_W_ID)
  disable;

  -- Create table
create table ECP_BASE_NOTICE
(
  NOTICE_ID       VARCHAR2(50) not null,
  TENDERID        VARCHAR2(50),
  NOTICE_TYPE     CHAR(2),
  CRE_DATE        DATE,
  SEND_ORG_ID     VARCHAR2(50),
  SEND_USER_ID    VARCHAR2(50),
  SEND_STATUS     CHAR(2),
  SEND_DATE       DATE,
  RECVICE_ORG_ID  VARCHAR2(50),
  RECVICE_USER_ID VARCHAR2(50),
  RECVICE_STATUS  CHAR(2),
  RECVICE_DATE    DATE,
  TITLE           VARCHAR2(100),
  CONTENT         VARCHAR2(50),
  RET_CONTENT     VARCHAR2(50),
  USE_STATUS      CHAR(2)
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
comment on table ECP_BASE_NOTICE
  is '通知书';
-- Add comments to the columns 
comment on column ECP_BASE_NOTICE.NOTICE_ID
  is '通知书ID';
comment on column ECP_BASE_NOTICE.TENDERID
  is '招标项目ID';
comment on column ECP_BASE_NOTICE.NOTICE_TYPE
  is '通知书类型';
comment on column ECP_BASE_NOTICE.CRE_DATE
  is '创建时间';
comment on column ECP_BASE_NOTICE.SEND_ORG_ID
  is '发送单位';
comment on column ECP_BASE_NOTICE.SEND_USER_ID
  is '发送人';
comment on column ECP_BASE_NOTICE.SEND_STATUS
  is '发送状态';
comment on column ECP_BASE_NOTICE.SEND_DATE
  is '发送时间';
comment on column ECP_BASE_NOTICE.RECVICE_ORG_ID
  is '接收单位';
comment on column ECP_BASE_NOTICE.RECVICE_USER_ID
  is '接收人';
comment on column ECP_BASE_NOTICE.RECVICE_STATUS
  is '接收状态';
comment on column ECP_BASE_NOTICE.RECVICE_DATE
  is '接收时间';
comment on column ECP_BASE_NOTICE.TITLE
  is '主题';
comment on column ECP_BASE_NOTICE.CONTENT
  is '内容[关联附件ID]';
comment on column ECP_BASE_NOTICE.RET_CONTENT
  is '回执内容[关联附件ID]';
comment on column ECP_BASE_NOTICE.USE_STATUS
  is '使用状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BASE_NOTICE
  add constraint PK_ECP_BASE_NOTICE primary key (NOTICE_ID)
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
create table EPS_AGREE_ELIMINATE_SUPPLYER
(
  ELIMINATE_SUPPLYER_ID VARCHAR2(50) not null,
  PROJECT_ID            VARCHAR2(50),
  BARGAIN_TURN_ID       VARCHAR2(50),
  ELIMINATE_REASON      VARCHAR2(2000),
  ELIMINATE_TIME        DATE,
  ELIMINATER_ID         VARCHAR2(50),
  SUPPLIER_ID           VARCHAR2(50)
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
comment on table EPS_AGREE_ELIMINATE_SUPPLYER
  is '议价剔除供应商';
-- Add comments to the columns 
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.ELIMINATE_SUPPLYER_ID
  is 'ID
';
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.PROJECT_ID
  is '项目ID
';
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.BARGAIN_TURN_ID
  is '报价轮次ID
';
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.ELIMINATE_REASON
  is '剔除原因
';
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.ELIMINATE_TIME
  is '剔除时间
';
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.ELIMINATER_ID
  is '剔除人
';
comment on column EPS_AGREE_ELIMINATE_SUPPLYER.SUPPLIER_ID
  is '供应商ID
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_ELIMINATE_SUPPLYER
  add constraint PK_ELIMINATE_SUPPLYER_ID primary key (ELIMINATE_SUPPLYER_ID)
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
create table EPS_AGREE_BARGAIN_RECORD
(
  BARGAIN_RECORD_ID VARCHAR2(50) not null,
  BARGAIN_ID        VARCHAR2(50),
  BARGAIN_ORG_ID    VARCHAR2(50),
  BARGIN_TIME       DATE default sysdate not null,
  TOTAL_DISCOUNT    NUMBER(5,2) default 0,
  GOODS_TOTAL       NUMBER(16,6),
  BARGIN_STATUS     CHAR(2) default 00,
  MEMO              VARCHAR2(3000),
  BARGIN_FILE       VARCHAR2(50),
  BARGAIN_TURN_ID   VARCHAR2(50),
  SERVICE_CONTENT   VARCHAR2(2000),
  SUPLIER_ID        VARCHAR2(50),
  PROJECT_ID        VARCHAR2(50),
  CRE_USER_ID       VARCHAR2(50)
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
comment on table EPS_AGREE_BARGAIN_RECORD
  is '议价记录';
-- Add comments to the columns 
comment on column EPS_AGREE_BARGAIN_RECORD.BARGAIN_RECORD_ID
  is '记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD.BARGAIN_ID
  is '议价记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD.BARGAIN_ORG_ID
  is '议价人记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD.BARGIN_TIME
  is '议价时间
';
comment on column EPS_AGREE_BARGAIN_RECORD.TOTAL_DISCOUNT
  is '总折扣率
';
comment on column EPS_AGREE_BARGAIN_RECORD.GOODS_TOTAL
  is '金额
';
comment on column EPS_AGREE_BARGAIN_RECORD.BARGIN_STATUS
  is '报价状态
00：草稿； 01：已提交 02：被接受
';
comment on column EPS_AGREE_BARGAIN_RECORD.MEMO
  is '备注
';
comment on column EPS_AGREE_BARGAIN_RECORD.BARGIN_FILE
  is '报价文件
';
comment on column EPS_AGREE_BARGAIN_RECORD.BARGAIN_TURN_ID
  is '报价轮次ID
';
comment on column EPS_AGREE_BARGAIN_RECORD.SERVICE_CONTENT
  is '服务内容
';
comment on column EPS_AGREE_BARGAIN_RECORD.SUPLIER_ID
  is '供应商';
comment on column EPS_AGREE_BARGAIN_RECORD.PROJECT_ID
  is '项目';
comment on column EPS_AGREE_BARGAIN_RECORD.CRE_USER_ID
  is '创建人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_BARGAIN_RECORD
  add constraint PK_BARGAIN_RECORD_ID primary key (BARGAIN_RECORD_ID)
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
create table EPS_AGREE_BARGAIN_RECORD_ITEM
(
  BARGAIN_RECORD_DTL_ID VARCHAR2(50) not null,
  BARGAIN_RECORD_ID     VARCHAR2(50),
  BARGAIN_DTL_ID        VARCHAR2(50),
  GOODS_TOTAL           NUMBER(16,6) default 0 not null,
  MEMO                  VARCHAR2(3000),
  GOODS_PRICE           NUMBER(16,6),
  GOODS_QTY             NUMBER(16,6),
  GOODS_ID              VARCHAR2(50),
  SERVICE_CONTENT       VARCHAR2(2000),
  REQUIRE_DTL_ID        VARCHAR2(50)
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
comment on table EPS_AGREE_BARGAIN_RECORD_ITEM
  is '议价明细记录';
-- Add comments to the columns 
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.BARGAIN_RECORD_DTL_ID
  is '记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.BARGAIN_RECORD_ID
  is '议价记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.BARGAIN_DTL_ID
  is '议价条目id
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.GOODS_TOTAL
  is '金额
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.MEMO
  is '备注
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.GOODS_PRICE
  is '单价';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.GOODS_QTY
  is '数量';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.GOODS_ID
  is '商品ID
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.SERVICE_CONTENT
  is '服务内容
';
comment on column EPS_AGREE_BARGAIN_RECORD_ITEM.REQUIRE_DTL_ID
  is '需求条目id
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_BARGAIN_RECORD_ITEM
  add constraint PK_BARGAIN_RECORD_DTL_ID primary key (BARGAIN_RECORD_DTL_ID)
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
create table EPS_AGREE_BARGAIN_RECORD_OPT
(
  BARGAIN_RECORD_OPT_ID VARCHAR2(50) not null,
  BARGAIN_OPT_ID        VARCHAR2(50),
  BARGAIN_RECORD_DTL_ID VARCHAR2(50),
  OPT_PRICE             NUMBER(16,6) default 0 not null,
  OPT_QTY               NUMBER(16,6) default 0 not null,
  OPTION_ID             VARCHAR2(100),
  REQUIRE_OPT_ID        VARCHAR2(50)
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
comment on table EPS_AGREE_BARGAIN_RECORD_OPT
  is '议价商品选配记录';
-- Add comments to the columns 
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.BARGAIN_RECORD_OPT_ID
  is '记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.BARGAIN_OPT_ID
  is '明细选配记录号
';
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.BARGAIN_RECORD_DTL_ID
  is '议价明细id
';
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.OPT_PRICE
  is '选配价
';
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.OPT_QTY
  is '选配数量';
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.OPTION_ID
  is '商品库选配Id
';
comment on column EPS_AGREE_BARGAIN_RECORD_OPT.REQUIRE_OPT_ID
  is '需求明细选配记录号
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_BARGAIN_RECORD_OPT
  add constraint PK_BARGAIN_RECORD_OPT_ID primary key (BARGAIN_RECORD_OPT_ID)
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
create table EPS_AGREEMENT_BARGAIN_CHAT
(
  BARGAIN_CHAT_ID    VARCHAR2(50) not null,
  CONTENT            VARCHAR2(4000),
  SAY_ORGINFO_ID     VARCHAR2(50),
  CRE_USER_ID        VARCHAR2(50),
  CRE_TIME           DATE,
  RECEIVE_ORGINFO_ID VARCHAR2(50),
  RECEIVE_USER_ID    VARCHAR2(50),
  BARGAIN_RECORD_ID  VARCHAR2(50),
  IS_PRIVATE         CHAR(1) default 1,
  ATTACHMENT_ID      VARCHAR2(50),
  PROJECT_ID         VARCHAR2(50),
  BARGAIN_TURN_ID    VARCHAR2(50),
  BARGAIN_ID         VARCHAR2(50),
  READ_STATUS        CHAR(1) default 0
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 704K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column EPS_AGREEMENT_BARGAIN_CHAT.BARGAIN_CHAT_ID
  is '对象ID';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.CONTENT
  is '发言内容';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.SAY_ORGINFO_ID
  is '发言机构';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.CRE_USER_ID
  is '发言人';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.CRE_TIME
  is '发言时间';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.RECEIVE_ORGINFO_ID
  is '接收机构';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.RECEIVE_USER_ID
  is '接收人';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.BARGAIN_RECORD_ID
  is '报价记录id';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.IS_PRIVATE
  is '是否私聊 1私聊   0公聊';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.ATTACHMENT_ID
  is '附件';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.PROJECT_ID
  is '项目';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.BARGAIN_TURN_ID
  is '报价轮次id';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.BARGAIN_ID
  is '议价项目';
comment on column EPS_AGREEMENT_BARGAIN_CHAT.READ_STATUS
  is '是否已读';

  -- Create table
create table EPS_AGREEMENT_CART
(
  CART_ID     VARCHAR2(50) not null,
  CART_NO     VARCHAR2(15),
  BUYER_ID    VARCHAR2(50),
  GOODS_QTY   NUMBER(16,6),
  GOODS_TOTAL NUMBER(16,6),
  CRE_TIME    DATE default sysdate,
  CRE_USER_ID VARCHAR2(50)
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
comment on table EPS_AGREEMENT_CART
  is '购物车';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_CART.CART_ID
  is 'ID
';
comment on column EPS_AGREEMENT_CART.CART_NO
  is '编号
';
comment on column EPS_AGREEMENT_CART.BUYER_ID
  is '采购人
';
comment on column EPS_AGREEMENT_CART.GOODS_QTY
  is '总数量
';
comment on column EPS_AGREEMENT_CART.GOODS_TOTAL
  is '总金额
';
comment on column EPS_AGREEMENT_CART.CRE_TIME
  is '创建时间
';
comment on column EPS_AGREEMENT_CART.CRE_USER_ID
  is '创建人
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_CART
  add constraint PK_CART primary key (CART_ID)
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
create table EPS_AGREEMENT_CART_ITEM
(
  CART_DTL_ID  VARCHAR2(50) not null,
  CART_ID      VARCHAR2(50),
  SUPPLIER_ID  VARCHAR2(50),
  GOODS_ID     VARCHAR2(50),
  GOODS_QTY    NUMBER(16,6),
  GOODS_PRICE  NUMBER(16,6),
  MARKET_PRICE NUMBER(16,6),
  GOODS_TOTAL  NUMBER(16,6),
  CRE_TIME     DATE default sysdate,
  CRE_USER_ID  VARCHAR2(50),
  MEMO         VARCHAR2(200),
  GOODS_UNIT   VARCHAR2(20),
  AGREE_PRICE  NUMBER(16,6),
  TASK_ITEM_ID VARCHAR2(50)
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
comment on table EPS_AGREEMENT_CART_ITEM
  is '购物车条目';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_CART_ITEM.CART_DTL_ID
  is 'ID
';
comment on column EPS_AGREEMENT_CART_ITEM.CART_ID
  is '购物车ID
';
comment on column EPS_AGREEMENT_CART_ITEM.SUPPLIER_ID
  is '供应商ID
';
comment on column EPS_AGREEMENT_CART_ITEM.GOODS_ID
  is '商品ID
';
comment on column EPS_AGREEMENT_CART_ITEM.GOODS_QTY
  is '数量
';
comment on column EPS_AGREEMENT_CART_ITEM.GOODS_PRICE
  is '单价
';
comment on column EPS_AGREEMENT_CART_ITEM.MARKET_PRICE
  is '市场价
';
comment on column EPS_AGREEMENT_CART_ITEM.GOODS_TOTAL
  is '金额
';
comment on column EPS_AGREEMENT_CART_ITEM.CRE_TIME
  is '创建时间
';
comment on column EPS_AGREEMENT_CART_ITEM.CRE_USER_ID
  is '创建人
';
comment on column EPS_AGREEMENT_CART_ITEM.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_CART_ITEM.GOODS_UNIT
  is '计量单位';
comment on column EPS_AGREEMENT_CART_ITEM.AGREE_PRICE
  is '协议价';
comment on column EPS_AGREEMENT_CART_ITEM.TASK_ITEM_ID
  is '任务书明细ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_CART_ITEM
  add constraint PK_CART_DTL primary key (CART_DTL_ID)
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
create table EPS_AGREE_CART_GOODS_OPTION
(
  CART_OPT_ID      VARCHAR2(50) not null,
  OPTION_ID        VARCHAR2(100),
  CART_DTL_ID      VARCHAR2(50),
  OPT_AGREE_PRICE  NUMBER(16,6),
  OPT_QTY          NUMBER(16,6),
  OPT_UNIT         VARCHAR2(20),
  OPT_PRICE        NUMBER(16,6),
  OPT_MARKET_PRICE NUMBER(16,6)
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
comment on table EPS_AGREE_CART_GOODS_OPTION
  is '采购商品选配';
-- Add comments to the columns 
comment on column EPS_AGREE_CART_GOODS_OPTION.CART_OPT_ID
  is '记录号
';
comment on column EPS_AGREE_CART_GOODS_OPTION.OPTION_ID
  is '商品库选配Id
';
comment on column EPS_AGREE_CART_GOODS_OPTION.CART_DTL_ID
  is '购物车明细id
';
comment on column EPS_AGREE_CART_GOODS_OPTION.OPT_AGREE_PRICE
  is '选配协议价
';
comment on column EPS_AGREE_CART_GOODS_OPTION.OPT_QTY
  is '数量
';
comment on column EPS_AGREE_CART_GOODS_OPTION.OPT_UNIT
  is '计量单位
';
comment on column EPS_AGREE_CART_GOODS_OPTION.OPT_PRICE
  is '选配价
';
comment on column EPS_AGREE_CART_GOODS_OPTION.OPT_MARKET_PRICE
  is '市场价
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_CART_GOODS_OPTION
  add constraint PK_CART_OPT_ID primary key (CART_OPT_ID)
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
create table EPS_AGREE_CART_GOODS_ACCESS
(
  GOODS_ACCESS_CART_ITEM_ID VARCHAR2(50) not null,
  CART_ITEM_ID              VARCHAR2(50),
  GOODS_ACCESS_ID           VARCHAR2(50),
  ACCESS_PRICE              NUMBER(23,7)
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
comment on column EPS_AGREE_CART_GOODS_ACCESS.GOODS_ACCESS_CART_ITEM_ID
  is '主键';
comment on column EPS_AGREE_CART_GOODS_ACCESS.CART_ITEM_ID
  is '购物车条目';
comment on column EPS_AGREE_CART_GOODS_ACCESS.GOODS_ACCESS_ID
  is '零配件';
comment on column EPS_AGREE_CART_GOODS_ACCESS.ACCESS_PRICE
  is '零配件价格';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_CART_GOODS_ACCESS
  add constraint GOODS_ACCESS_CART_ITEM_PK primary key (GOODS_ACCESS_CART_ITEM_ID)
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
create table EPS_AGREE_CART_GOODS_GIFT
(
  CART_GOODS_GIFT_ID VARCHAR2(50) not null,
  CART_ITEM_ID       VARCHAR2(50),
  GOODS_GIFT_ID      VARCHAR2(50),
  GIFT_PRICE         NUMBER(23,7)
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
comment on table EPS_AGREE_CART_GOODS_GIFT
  is '采购商品礼包';
-- Add comments to the columns 
comment on column EPS_AGREE_CART_GOODS_GIFT.CART_GOODS_GIFT_ID
  is '主键';
comment on column EPS_AGREE_CART_GOODS_GIFT.CART_ITEM_ID
  is '购物车条目ID';
comment on column EPS_AGREE_CART_GOODS_GIFT.GOODS_GIFT_ID
  is '商品礼包ID';
comment on column EPS_AGREE_CART_GOODS_GIFT.GIFT_PRICE
  is '礼包价格';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_CART_GOODS_GIFT
  add constraint CART_GOODS_GIFT_PK primary key (CART_GOODS_GIFT_ID)
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
  CATEGORY_NAMES   VARCHAR2(500),
  PROJECT_ID       VARCHAR2(50),
  B_CONFIRM_OPTION VARCHAR2(2000),
  S_CONFIRM_OPTION VARCHAR2(2000)
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
  is '采购人确认意见';
comment on column EPS_AGREEMENT_ORDER.S_CONFIRM_OPTION
  is '供应商确认意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_ORDER
  add constraint PK_ORDER_ID primary key (ORDER_ID)
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
alter table EPS_AGREEMENT_ORDER
  add constraint FK_CONTRACT foreign key (CONTRACT_ID)
  references EPS_PUB_CONTRACT (CONTRACT_ID);

  -- Create table
create table EPS_AGREEMENT_ORDER_ITEM
(
  ORDER_DTL_ID   VARCHAR2(50) not null,
  ORDER_ID       VARCHAR2(50),
  SUPPLIER_ID    VARCHAR2(50) not null,
  GOODS_ID       VARCHAR2(50) not null,
  GOODS_QTY      NUMBER(16,6) default 0 not null,
  GOODS_PRICE    NUMBER(16,6) default 0 not null,
  AGREE_PRICE    NUMBER(16,6) default 0 not null,
  MARKET_PRICE   NUMBER(16,6) default 0,
  GOODS_TOTAL    NUMBER(16,6) default 0 not null,
  MEMO           VARCHAR2(200),
  GOODS_UNIT     VARCHAR2(20),
  REQUIRE_DTL_ID VARCHAR2(50)
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
comment on table EPS_AGREEMENT_ORDER_ITEM
  is '订单明细';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_ORDER_ITEM.ORDER_DTL_ID
  is 'ID
';
comment on column EPS_AGREEMENT_ORDER_ITEM.ORDER_ID
  is '订单ID
';
comment on column EPS_AGREEMENT_ORDER_ITEM.SUPPLIER_ID
  is '供应商ID
';
comment on column EPS_AGREEMENT_ORDER_ITEM.GOODS_ID
  is '商品ID
';
comment on column EPS_AGREEMENT_ORDER_ITEM.GOODS_QTY
  is '数量
';
comment on column EPS_AGREEMENT_ORDER_ITEM.GOODS_PRICE
  is '单价
';
comment on column EPS_AGREEMENT_ORDER_ITEM.AGREE_PRICE
  is '协议价
';
comment on column EPS_AGREEMENT_ORDER_ITEM.MARKET_PRICE
  is '市场价
';
comment on column EPS_AGREEMENT_ORDER_ITEM.GOODS_TOTAL
  is '金额
';
comment on column EPS_AGREEMENT_ORDER_ITEM.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_ORDER_ITEM.GOODS_UNIT
  is '计量单位';
comment on column EPS_AGREEMENT_ORDER_ITEM.REQUIRE_DTL_ID
  is '需求条目ID
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_ORDER_ITEM
  add constraint PK_ORDER_DTL primary key (ORDER_DTL_ID)
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
alter table EPS_AGREEMENT_ORDER_ITEM
  add constraint FK_ORDER_DTL_ORDER_ID foreign key (ORDER_ID)
  references EPS_AGREEMENT_ORDER (ORDER_ID);

  -- Create table
create table EPS_AGREE_ORDER_GOODS_OPTION
(
  ORDER_OPT_ID     VARCHAR2(50) not null,
  OPTION_ID        VARCHAR2(100),
  ORDER_DTL_ID     VARCHAR2(50),
  OPT_PRICE        NUMBER(16,6) default 0 not null,
  OPT_AGREE_PRICE  NUMBER(16,6) default 0 not null,
  OPT_QTY          NUMBER(16,6) default 0 not null,
  OPT_UNIT         VARCHAR2(20),
  OPT_MARKET_PRICE NUMBER(16,6) default 0
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
comment on table EPS_AGREE_ORDER_GOODS_OPTION
  is '采购商品选配';
-- Add comments to the columns 
comment on column EPS_AGREE_ORDER_GOODS_OPTION.ORDER_OPT_ID
  is '记录号
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.OPTION_ID
  is '商品库选配Id
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.ORDER_DTL_ID
  is '订单明细id
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.OPT_PRICE
  is '选配价
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.OPT_AGREE_PRICE
  is '选配协议价
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.OPT_QTY
  is '数量
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.OPT_UNIT
  is '计量单位
';
comment on column EPS_AGREE_ORDER_GOODS_OPTION.OPT_MARKET_PRICE
  is '市场价
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_ORDER_GOODS_OPTION
  add constraint PK_ORDER_OPT_ID primary key (ORDER_OPT_ID)
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
create table EPS_AGREE_ORDER_GOODS_ACCESS
(
  ORDER_GOODS_ACCESS_ID VARCHAR2(50) not null,
  ORDER_ITEM_ID         VARCHAR2(50),
  GOODS_ACCESSORIES_ID  VARCHAR2(50),
  ACCESS_PRICE          NUMBER(23,7)
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
comment on table EPS_AGREE_ORDER_GOODS_ACCESS
  is '采购商品礼包订单';
-- Add comments to the columns 
comment on column EPS_AGREE_ORDER_GOODS_ACCESS.ORDER_GOODS_ACCESS_ID
  is '主键';
comment on column EPS_AGREE_ORDER_GOODS_ACCESS.ORDER_ITEM_ID
  is '订单条目ID';
comment on column EPS_AGREE_ORDER_GOODS_ACCESS.GOODS_ACCESSORIES_ID
  is '零配件ID';
comment on column EPS_AGREE_ORDER_GOODS_ACCESS.ACCESS_PRICE
  is '零配件价格';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_ORDER_GOODS_ACCESS
  add constraint ORDER_GOODS_ACCESS_PK primary key (ORDER_GOODS_ACCESS_ID)
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
create table EPS_AGREE_ORDER_GOODS_GIFT
(
  ORDER_GOODS_GIFT_ID VARCHAR2(50) not null,
  ORDER_ITEM_ID       VARCHAR2(50),
  GOODS_GIFT_ID       VARCHAR2(50),
  GIFT_PRICE          NUMBER(23,7)
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
comment on table EPS_AGREE_ORDER_GOODS_GIFT
  is '采购商品礼包订单';
-- Add comments to the columns 
comment on column EPS_AGREE_ORDER_GOODS_GIFT.ORDER_GOODS_GIFT_ID
  is '主键';
comment on column EPS_AGREE_ORDER_GOODS_GIFT.ORDER_ITEM_ID
  is '订单条目ID';
comment on column EPS_AGREE_ORDER_GOODS_GIFT.GOODS_GIFT_ID
  is '商品礼包ID';
comment on column EPS_AGREE_ORDER_GOODS_GIFT.GIFT_PRICE
  is '礼包价格';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_ORDER_GOODS_GIFT
  add constraint ORDER_GOODS_GIFT_PK primary key (ORDER_GOODS_GIFT_ID)
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
create table EPS_AGREE_ORDER_PROTASK
(
  ORDER_TASK_ID    VARCHAR2(50) not null,
  ORDER_DTL_ID     VARCHAR2(50),
  TASK_ITEM_ID     VARCHAR2(50),
  ORDER_TASK_TOTAL NUMBER(16,6) default 0 not null,
  ORDER_TASK_QTY   NUMBER(16,6) default 0 not null
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
comment on table EPS_AGREE_ORDER_PROTASK
  is '订单与任务关联表';
-- Add comments to the columns 
comment on column EPS_AGREE_ORDER_PROTASK.ORDER_TASK_ID
  is '记录号
';
comment on column EPS_AGREE_ORDER_PROTASK.ORDER_DTL_ID
  is '订单明细id
';
comment on column EPS_AGREE_ORDER_PROTASK.TASK_ITEM_ID
  is '任务书明细ID
';
comment on column EPS_AGREE_ORDER_PROTASK.ORDER_TASK_TOTAL
  is '金额
';
comment on column EPS_AGREE_ORDER_PROTASK.ORDER_TASK_QTY
  is '抵消数量';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_ORDER_PROTASK
  add constraint PK_ORDER_TASK_ID primary key (ORDER_TASK_ID)
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
alter table EPS_AGREE_ORDER_PROTASK
  add constraint FK_ORDER_TASK_TASK_ITEM_ID foreign key (TASK_ITEM_ID)
  references EPS_AGREEMENT_PROTASK_ITEM (TASK_ITEM_ID);

  -- Create table
create table ECP_PUB_CONTRACT
(
  CONTRACT_ID          VARCHAR2(50) not null,
  CONTRACT_NAME        VARCHAR2(100) not null,
  CONTRACT_NO          VARCHAR2(50) not null,
  BUYER_ID             VARCHAR2(50) not null,
  SUPPLIER_ID          VARCHAR2(50) not null,
  CONTRACT_SIGNED_TIME DATE,
  CONTRACT_BEGIN_TIME  DATE not null,
  CONTRACT_END_TIME    DATE not null,
  CONTRACT_TYPE        CHAR(2) default 01,
  MEMO                 VARCHAR2(3000),
  USE_STATUS           CHAR(2) default 00,
  CONTRACT_FILE        VARCHAR2(50),
  CRE_TIME             DATE default sysdate not null,
  CRE_USER_ID          VARCHAR2(50),
  B_CONFIRM_STATUS     CHAR(2) default 00,
  S_CONFIRM_STATUS     CHAR(2) default 00,
  B_CONFIRM_DATE       DATE,
  B_CONFIRM_USER       VARCHAR2(50),
  S_CONFIRM_DATE       DATE,
  S_CONFIRM_USER       VARCHAR2(50),
  DELIVERY_TIME        VARCHAR2(100) not null,
  DELIVERY_PLACE       VARCHAR2(500) not null,
  SRC_APP              VARCHAR2(50)
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
comment on table ECP_PUB_CONTRACT
  is '采购合同';
-- Add comments to the columns 
comment on column ECP_PUB_CONTRACT.CONTRACT_ID
  is '记录号
';
comment on column ECP_PUB_CONTRACT.CONTRACT_NAME
  is '合同名称
';
comment on column ECP_PUB_CONTRACT.CONTRACT_NO
  is '合同编号
';
comment on column ECP_PUB_CONTRACT.BUYER_ID
  is '采购人ID
';
comment on column ECP_PUB_CONTRACT.SUPPLIER_ID
  is '供应商ID
';
comment on column ECP_PUB_CONTRACT.CONTRACT_SIGNED_TIME
  is '合同签订时间
';
comment on column ECP_PUB_CONTRACT.CONTRACT_BEGIN_TIME
  is '合同开始时间
';
comment on column ECP_PUB_CONTRACT.CONTRACT_END_TIME
  is '合同结束时间
';
comment on column ECP_PUB_CONTRACT.CONTRACT_TYPE
  is '合同类型
01:项目采购合同，02:协议采购合同
';
comment on column ECP_PUB_CONTRACT.MEMO
  is '备注
';
comment on column ECP_PUB_CONTRACT.USE_STATUS
  is '合同状态
00:草稿 01:正式 02：作废
';
comment on column ECP_PUB_CONTRACT.CONTRACT_FILE
  is '合同文件
';
comment on column ECP_PUB_CONTRACT.CRE_TIME
  is '创建时间
';
comment on column ECP_PUB_CONTRACT.CRE_USER_ID
  is '创建人
';
comment on column ECP_PUB_CONTRACT.B_CONFIRM_STATUS
  is '采购人确认状态
00：未确认(默认) 01：确认
';
comment on column ECP_PUB_CONTRACT.S_CONFIRM_STATUS
  is '供货商确认状态
00：未确认(默认) 01：确认
';
comment on column ECP_PUB_CONTRACT.B_CONFIRM_DATE
  is '采购人确认时间';
comment on column ECP_PUB_CONTRACT.B_CONFIRM_USER
  is '采购人确认人';
comment on column ECP_PUB_CONTRACT.S_CONFIRM_DATE
  is '供应商确认时间';
comment on column ECP_PUB_CONTRACT.S_CONFIRM_USER
  is '供应商确认人';
comment on column ECP_PUB_CONTRACT.DELIVERY_TIME
  is '交货日期';
comment on column ECP_PUB_CONTRACT.DELIVERY_PLACE
  is '交货地点';
comment on column ECP_PUB_CONTRACT.SRC_APP
  is '公告来源';

  -- Create table
create table EPS_AGREEMENT_PERIOD
(
  PERIOD_ID   VARCHAR2(50) not null,
  PERIOD_NAME VARCHAR2(100) not null,
  BEGIN_DATE  DATE not null,
  END_DATE    DATE not null,
  MEMO        VARCHAR2(200)
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
comment on table EPS_AGREEMENT_PERIOD
  is '协议期间';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_PERIOD.PERIOD_ID
  is 'ID
';
comment on column EPS_AGREEMENT_PERIOD.PERIOD_NAME
  is '名称
';
comment on column EPS_AGREEMENT_PERIOD.BEGIN_DATE
  is '开始日期
';
comment on column EPS_AGREEMENT_PERIOD.END_DATE
  is '结束日期
';
comment on column EPS_AGREEMENT_PERIOD.MEMO
  is '备注
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_PERIOD
  add constraint PK_PERIOD_ID primary key (PERIOD_ID)
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
create table EPS_AGREEMENT_AREA
(
  AREA_ID      VARCHAR2(50) not null,
  AREA_NAME    VARCHAR2(100) not null,
  AREA_CODES   VARCHAR2(1000) not null,
  AREA_NAMES   VARCHAR2(1000) not null,
  PARENT_ID    VARCHAR2(50),
  MEMO         VARCHAR2(200),
  AREA_SORT    NUMBER(19),
  AREA_IS_LEAF VARCHAR2(1 CHAR)
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
comment on table EPS_AGREEMENT_AREA
  is '协议区间';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_AREA.AREA_ID
  is 'ID
';
comment on column EPS_AGREEMENT_AREA.AREA_NAME
  is '区域名称
';
comment on column EPS_AGREEMENT_AREA.AREA_CODES
  is '行政区域编码集合
';
comment on column EPS_AGREEMENT_AREA.AREA_NAMES
  is '行政区域名称集合
';
comment on column EPS_AGREEMENT_AREA.PARENT_ID
  is '上级区域
';
comment on column EPS_AGREEMENT_AREA.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_AREA.AREA_SORT
  is '排序';
comment on column EPS_AGREEMENT_AREA.AREA_IS_LEAF
  is '是否叶子节点';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_AREA
  add constraint PK_AREA_ID primary key (AREA_ID)
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
create table EPS_AGREE_PURCHASE_AGREEMENT
(
  AGREEMENT_ID   VARCHAR2(50) not null,
  AGREEMENT_NO   VARCHAR2(50) not null,
  ORG_ID         VARCHAR2(50) not null,
  SUPPLYER_ID    VARCHAR2(50) not null,
  AREA_ID        VARCHAR2(50) not null,
  PERIOD_ID      VARCHAR2(50) not null,
  AGREEMENT_FILE VARCHAR2(50),
  CRE_TIME       DATE,
  CRE_USER_ID    VARCHAR2(50),
  MEMO           VARCHAR2(200),
  USE_STATUS     CHAR(2),
  AGREEMENT_NAME VARCHAR2(100),
  MODIFY_TIME    DATE
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
comment on table EPS_AGREE_PURCHASE_AGREEMENT
  is '采购协议';
-- Add comments to the columns 
comment on column EPS_AGREE_PURCHASE_AGREEMENT.AGREEMENT_ID
  is 'ID
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.AGREEMENT_NO
  is '编号
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.ORG_ID
  is '协议甲方
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.SUPPLYER_ID
  is '经销商
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.AREA_ID
  is '协议生效区域
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.PERIOD_ID
  is '协议生效期间
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.AGREEMENT_FILE
  is '协议附件
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.CRE_TIME
  is '创建时间
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.CRE_USER_ID
  is '创建人
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.MEMO
  is '备注
';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.USE_STATUS
  is '使用状态
00：临时；01：正式；02：作废';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.AGREEMENT_NAME
  is '名称';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_PURCHASE_AGREEMENT
  add constraint PK_AGREEMENT_ID primary key (AGREEMENT_ID)
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
alter table EPS_AGREE_PURCHASE_AGREEMENT
  add constraint FK_AGREEMENT_AREA_ID foreign key (AREA_ID)
  references EPS_AGREEMENT_AREA (AREA_ID);
alter table EPS_AGREE_PURCHASE_AGREEMENT
  add constraint FK_AGREEMENT_PERIOD_ID foreign key (PERIOD_ID)
  references EPS_AGREEMENT_PERIOD (PERIOD_ID);

    -- Create table
create table EPS_AGREEMENT_PURCHASE_SECOND
(
  SECOND_AGREEMENT_ID VARCHAR2(50) not null,
  AGREEMENT_ID        VARCHAR2(50) not null,
  SUPPLYER_ID         VARCHAR2(50) not null,
  BEGIN_DATE          DATE not null,
  END_DATE            DATE not null,
  MEMO                VARCHAR2(200),
  NAME                VARCHAR2(50) not null,
  AREA_ID             VARCHAR2(50),
  CONTENT             VARCHAR2(50)
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
comment on table EPS_AGREEMENT_PURCHASE_SECOND
  is '二级协议(供货商协议)';
-- Add comments to the columns 
comment on column EPS_AGREEMENT_PURCHASE_SECOND.SECOND_AGREEMENT_ID
  is 'ID
';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.AGREEMENT_ID
  is '主协议Id';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.SUPPLYER_ID
  is '供货商
';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.BEGIN_DATE
  is '开始日期
';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.END_DATE
  is '结束日期
';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.NAME
  is '名称';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.AREA_ID
  is '协议区间';
comment on column EPS_AGREEMENT_PURCHASE_SECOND.CONTENT
  is '附件';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREEMENT_PURCHASE_SECOND
  add constraint PK_SECOND_AGREEMENT_ID primary key (SECOND_AGREEMENT_ID)
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
alter table EPS_AGREEMENT_PURCHASE_SECOND
  add constraint FK_SECOND_AGREEMENT_AGREEMENT foreign key (AGREEMENT_ID)
  references EPS_AGREE_PURCHASE_AGREEMENT (AGREEMENT_ID);
alter table EPS_AGREEMENT_PURCHASE_SECOND
  add constraint FK_SECOND_AGREEMENT_AREA_ID foreign key (AREA_ID)
  references EPS_AGREEMENT_AREA (AREA_ID);
  
  -- Create table
create table EPS_AGREE_PURCHASE_GOODSCLASS
(
  AGREEMENT_CLASS_ID  VARCHAR2(50) not null,
  AGREEMENT_ID        VARCHAR2(50),
  SECOND_AGREEMENT_ID VARCHAR2(50),
  AGREEMENT_TYPE      CHAR(2),
  GOODSCLASS_ID       VARCHAR2(50) not null,
  BRAND_ID            VARCHAR2(50) not null,
  DISCOUNT_RATIO      NUMBER(16,6) default 0 not null,
  MEMO                VARCHAR2(200)
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
comment on table EPS_AGREE_PURCHASE_GOODSCLASS
  is '协议商品分类';
-- Add comments to the columns 
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.AGREEMENT_CLASS_ID
  is 'ID
';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.AGREEMENT_ID
  is '协议Id';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.SECOND_AGREEMENT_ID
  is '二级协议Id';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.AGREEMENT_TYPE
  is '协议类型';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.GOODSCLASS_ID
  is '分类
';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.BRAND_ID
  is '品牌
';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.DISCOUNT_RATIO
  is '折扣率
';
comment on column EPS_AGREE_PURCHASE_GOODSCLASS.MEMO
  is '备注
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_PURCHASE_GOODSCLASS
  add constraint PK_AGREEMENT_CLASS_ID primary key (AGREEMENT_CLASS_ID)
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
alter table EPS_AGREE_PURCHASE_GOODSCLASS
  add constraint FK_AGREEMENT_CLASS_AGREEMENT foreign key (AGREEMENT_ID)
  references EPS_AGREE_PURCHASE_AGREEMENT (AGREEMENT_ID);
alter table EPS_AGREE_PURCHASE_GOODSCLASS
  add constraint FK_AGREEMENT_CLASS_SECOND foreign key (SECOND_AGREEMENT_ID)
  references EPS_AGREEMENT_PURCHASE_SECOND (SECOND_AGREEMENT_ID);

  -- Create table
create table EPS_AGREE_PURCHASE_GOODS
(
  AGREEMENT_GOODS_ID  VARCHAR2(50) not null,
  AGREEMENT_ID        VARCHAR2(50),
  SECOND_AGREEMENT_ID VARCHAR2(50),
  AGREEMENT_TYPE      CHAR(2),
  AGREEMENT_CLASS_ID  VARCHAR2(50),
  GOODSCLASS_ID       VARCHAR2(50),
  BRAND_ID            VARCHAR2(50),
  GOODS_ID            VARCHAR2(50) not null,
  DISCOUNT_RATIO      NUMBER(16,6) default 0 not null,
  AGREEMENT_PRICE     NUMBER(16,6) default 0 not null,
  MARKET_PRICE        NUMBER(16,6) default 0 not null,
  MEMO                VARCHAR2(200)
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
comment on table EPS_AGREE_PURCHASE_GOODS
  is '协议单品';
-- Add comments to the columns 
comment on column EPS_AGREE_PURCHASE_GOODS.AGREEMENT_GOODS_ID
  is 'ID
';
comment on column EPS_AGREE_PURCHASE_GOODS.AGREEMENT_ID
  is '一级协议ID
';
comment on column EPS_AGREE_PURCHASE_GOODS.SECOND_AGREEMENT_ID
  is '二级协议ID
';
comment on column EPS_AGREE_PURCHASE_GOODS.AGREEMENT_TYPE
  is '协议类型
00：一级协议；01：二级协议';
comment on column EPS_AGREE_PURCHASE_GOODS.AGREEMENT_CLASS_ID
  is '协议商品分类';
comment on column EPS_AGREE_PURCHASE_GOODS.GOODSCLASS_ID
  is '分类
(冗余)';
comment on column EPS_AGREE_PURCHASE_GOODS.BRAND_ID
  is '品牌
(冗余)';
comment on column EPS_AGREE_PURCHASE_GOODS.GOODS_ID
  is '商品
';
comment on column EPS_AGREE_PURCHASE_GOODS.DISCOUNT_RATIO
  is '折扣率
';
comment on column EPS_AGREE_PURCHASE_GOODS.AGREEMENT_PRICE
  is '协议价
';
comment on column EPS_AGREE_PURCHASE_GOODS.MARKET_PRICE
  is '市场价
';
comment on column EPS_AGREE_PURCHASE_GOODS.MEMO
  is '备注
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_PURCHASE_GOODS
  add constraint PK_AGREEMENT_GOODS_ID primary key (AGREEMENT_GOODS_ID)
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
alter table EPS_AGREE_PURCHASE_GOODS
  add constraint FK_AGREEMENT_GOODS_AGREEMENT foreign key (AGREEMENT_ID)
  references EPS_AGREE_PURCHASE_AGREEMENT (AGREEMENT_ID);
alter table EPS_AGREE_PURCHASE_GOODS
  add constraint FK_AGREEMENT_GOODS_CLASS foreign key (AGREEMENT_CLASS_ID)
  references EPS_AGREE_PURCHASE_GOODSCLASS (AGREEMENT_CLASS_ID);
alter table EPS_AGREE_PURCHASE_GOODS
  add constraint FK_AGREEMENT_GOODS_SECOND foreign key (SECOND_AGREEMENT_ID)
  references EPS_AGREEMENT_PURCHASE_SECOND (SECOND_AGREEMENT_ID);


