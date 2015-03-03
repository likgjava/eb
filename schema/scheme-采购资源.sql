-- Create table
create table ORG_INFO
(
  CREATOR                 VARCHAR2(36),
  CREATE_TIME             DATE,
  MODIFIER                VARCHAR2(36),
  MODIFY_TIME             DATE,
  COMPANY_ID              VARCHAR2(36),
  SELF_DEFINE_ORG_LIST_ID VARCHAR2(36),
  AGENCY_ID               VARCHAR2(36),
  BUYER_ID                VARCHAR2(36),
  SUPERVISION_ID          VARCHAR2(36),
  SUPPLIER_ID             VARCHAR2(36),
  CMPT_DEP_ID             VARCHAR2(36),
  ORG_CODE                VARCHAR2(100),
  ORG_NAME                VARCHAR2(100),
  USER_ID                 VARCHAR2(36),
  AUDIT_STATUS            CHAR(2),
  ORG_INFO_ID             VARCHAR2(36) not null,
  HISTORY_ID              VARCHAR2(36),
  IN_TERRACE              VARCHAR2(36),
  IS_OFF                  VARCHAR2(50),
  ORG_DATA_TYPE           VARCHAR2(50),
  USE_STATUS              VARCHAR2(2),
  VALID_DATE              DATE,
  INVALID_DATE            DATE,
  CURRENT_VALID_ID        VARCHAR2(36),
  AUDIT_TYPE              CHAR(2),
  ORG_LOGO                VARCHAR2(36),
  DISTRICT_VALUE          VARCHAR2(200),
  VERIFIER_ID             VARCHAR2(50),
  VERIFY_TIME             DATE,
  OPINION                 VARCHAR2(200),
  ISMANUFACTURER          VARCHAR2(10) default '0',
  BEGAINDATE              DATE,
  ENTCAPACITY             VARCHAR2(2000),
  BID_FOR_RANGE           VARCHAR2(2000),
  MAIN_PRODUCTS           VARCHAR2(2000),
  DESC_CN                 VARCHAR2(2000),
  WEB_URL                 VARCHAR2(100),
  UNIT_SCAPE              VARCHAR2(2000),
  ENT_PRPT                VARCHAR2(100) default '01',
  BELONGINDUSTRY          VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 3M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ORG_INFO
  is '机构';
-- Add comments to the columns 
comment on column ORG_INFO.CREATOR
  is ' 创建人';
comment on column ORG_INFO.CREATE_TIME
  is '创建时间';
comment on column ORG_INFO.MODIFIER
  is ' 修改人';
comment on column ORG_INFO.MODIFY_TIME
  is '修改时间';
comment on column ORG_INFO.COMPANY_ID
  is '组织机构ID';
comment on column ORG_INFO.AGENCY_ID
  is '代理机构ID';
comment on column ORG_INFO.BUYER_ID
  is '采购人ID';
comment on column ORG_INFO.SUPERVISION_ID
  is '监管机构ID';
comment on column ORG_INFO.SUPPLIER_ID
  is '供应商ID';
comment on column ORG_INFO.CMPT_DEP_ID
  is '主管部门ID';
comment on column ORG_INFO.ORG_CODE
  is '机构编码';
comment on column ORG_INFO.ORG_NAME
  is '机构名称';
comment on column ORG_INFO.USER_ID
  is '联系人(机构管理员）';
comment on column ORG_INFO.AUDIT_STATUS
  is '审核状态';
comment on column ORG_INFO.ORG_INFO_ID
  is '主键';
comment on column ORG_INFO.IS_OFF
  is '禁用启用';
comment on column ORG_INFO.USE_STATUS
  is '使用状态[00:临时;01:有效;02:无效]';
comment on column ORG_INFO.VALID_DATE
  is '生效时间';
comment on column ORG_INFO.INVALID_DATE
  is '失效时间';
comment on column ORG_INFO.CURRENT_VALID_ID
  is '当期有效ID';
comment on column ORG_INFO.AUDIT_TYPE
  is '审核类型[00:注册审核,01:变更审核,02:申请审核,03:案例审核]';
comment on column ORG_INFO.ORG_LOGO
  is '机构的logo图片';
comment on column ORG_INFO.DISTRICT_VALUE
  is '行政区域';
comment on column ORG_INFO.VERIFIER_ID
  is '审核人';
comment on column ORG_INFO.VERIFY_TIME
  is '审核时间';
comment on column ORG_INFO.OPINION
  is '审核意见';
comment on column ORG_INFO.ISMANUFACTURER
  is '是否厂家';
comment on column ORG_INFO.BEGAINDATE
  is '开业日期';
comment on column ORG_INFO.ENTCAPACITY
  is '企业产能';
comment on column ORG_INFO.BID_FOR_RANGE
  is ' 投标范围及类别';
comment on column ORG_INFO.MAIN_PRODUCTS
  is '主营产品范围(中)';
comment on column ORG_INFO.DESC_CN
  is '企业简介（中）';
comment on column ORG_INFO.WEB_URL
  is '网址';
comment on column ORG_INFO.UNIT_SCAPE
  is '企业规模';
comment on column ORG_INFO.ENT_PRPT
  is '企业性质';
comment on column ORG_INFO.BELONGINDUSTRY
  is '所属行业';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_INFO
  add constraint PK_ORG_INFO primary key (ORG_INFO_ID)
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
create table AGENCY_AGENT
(
  HISTORY_ID              VARCHAR2(36),
  AGENT_TYPE              VARCHAR2(100),
  ORG_UNIT_CODE           VARCHAR2(100),
  ORG_CODE                VARCHAR2(100),
  REG_ADDRESS             VARCHAR2(200),
  REG_AUTH_ORG            VARCHAR2(100),
  REG_BUS_SCOPE           VARCHAR2(1000),
  REG_CODE                VARCHAR2(100),
  REG_COPORATE            VARCHAR2(100),
  REG_DATE                DATE,
  REG_TO_DATE             DATE,
  UNIT_ADDRESS            VARCHAR2(100),
  UNIT_DESC               VARCHAR2(1000),
  UNIT_SCAPE              VARCHAR2(1000),
  UNIT_TYPE               VARCHAR2(100),
  WEB_URL                 VARCHAR2(100),
  OPEN_ACCOUNT            VARCHAR2(100),
  OPEN_ACCOUNT_NAME       VARCHAR2(100),
  OPEN_BANK               VARCHAR2(100),
  AUDIT_STATUS            CHAR(2),
  DATA_TYPE               VARCHAR2(100),
  ORG_CONTACT_ID          VARCHAR2(36),
  ORG_UNIT_CODE_FILE_ID   VARCHAR2(36),
  REG_FILE_ID             VARCHAR2(36),
  UNIT_LOG_ID             VARCHAR2(36),
  ECNM_NATURE             VARCHAR2(100),
  UNIT_IN_CHARGE_ID       VARCHAR2(36),
  TRADE_END_DATE          DATE,
  TRADE_START_DATE        DATE,
  REG_CAPITAL             NUMBER(23,7),
  PRCT_TOTAL_NMBR         NUMBER(9),
  MIDDLE_TITLE_TCHST_NMBR NUMBER(9),
  HIGH_TITLE_TCHST_NMBR   NUMBER(9),
  REG_PRCTS_NMBR          NUMBER(9),
  FIXED_AST_DPRC_YR       NUMBER(23,7),
  REG_ISO                 VARCHAR2(100),
  CRNT_AST                NUMBER(23,7),
  TOTAL_CHARGE            NUMBER(23,7),
  BID_PRPS_EVLT_ADDR      VARCHAR2(200),
  ADMIN_GRD               VARCHAR2(100),
  PUR_AGENT               VARCHAR2(100),
  MIAN_BUSS_SCP           VARCHAR2(1000),
  CNCR_BUSS_SCP           VARCHAR2(1000),
  UNDTK_BID_PROJ          VARCHAR2(1000),
  AGENCY_BUSS_CNDT        VARCHAR2(1000),
  REMARKS                 VARCHAR2(2000),
  CREATOR                 VARCHAR2(100),
  CREATE_TIME             DATE,
  MODIFIER                VARCHAR2(100),
  MODIFY_TIME             DATE,
  AGENT_ID                VARCHAR2(36) not null,
  COMPANY_HISTORY_ID      VARCHAR2(36),
  ORG_INFO_ID             VARCHAR2(36),
  VER_NO                  VARCHAR2(50),
  TOTAL_ASSETS            NUMBER(23,7),
  VERIFIER_ID             VARCHAR2(50),
  VERIFY_TIME             DATE,
  OPINION                 VARCHAR2(200)
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
comment on table AGENCY_AGENT
  is '代理机构扩展信息';
-- Add comments to the columns 
comment on column AGENCY_AGENT.AGENT_TYPE
  is '代理机构类型： 01:政府集中采购中心 02:政府部门采购中心 03:社会中介（招标公司） 04:企业专职采购部门';
comment on column AGENCY_AGENT.REG_ADDRESS
  is '工商注册地址';
comment on column AGENCY_AGENT.REG_AUTH_ORG
  is '工商注册发证机关';
comment on column AGENCY_AGENT.REG_BUS_SCOPE
  is '工商注册营业范围';
comment on column AGENCY_AGENT.REG_CODE
  is '工商注册号';
comment on column AGENCY_AGENT.REG_COPORATE
  is '工商注册法人';
comment on column AGENCY_AGENT.REG_DATE
  is '工商注册日期';
comment on column AGENCY_AGENT.REG_TO_DATE
  is '工商注册有效期';
comment on column AGENCY_AGENT.UNIT_DESC
  is '企业简介';
comment on column AGENCY_AGENT.UNIT_SCAPE
  is '企业规模';
comment on column AGENCY_AGENT.UNIT_TYPE
  is '企业类型 01：国家机关 02：事业单位 03：团体组织';
comment on column AGENCY_AGENT.WEB_URL
  is '网址';
comment on column AGENCY_AGENT.OPEN_ACCOUNT
  is '开户帐号';
comment on column AGENCY_AGENT.OPEN_ACCOUNT_NAME
  is '开户帐号名称';
comment on column AGENCY_AGENT.OPEN_BANK
  is '开户银行';
comment on column AGENCY_AGENT.AUDIT_STATUS
  is ' 00:草稿 01：待审核 02：审核通过 03：审核不通过';
comment on column AGENCY_AGENT.REG_FILE_ID
  is '工商注册执照';
comment on column AGENCY_AGENT.ECNM_NATURE
  is '经济性质';
comment on column AGENCY_AGENT.UNIT_IN_CHARGE_ID
  is '主管单位，上级主管部门';
comment on column AGENCY_AGENT.TRADE_END_DATE
  is '营业执照结束日期';
comment on column AGENCY_AGENT.TRADE_START_DATE
  is '营业执照开始日期';
comment on column AGENCY_AGENT.REG_CAPITAL
  is '注册资金';
comment on column AGENCY_AGENT.PRCT_TOTAL_NMBR
  is '从业人员总数';
comment on column AGENCY_AGENT.MIDDLE_TITLE_TCHST_NMBR
  is '中级职称技术人员数';
comment on column AGENCY_AGENT.HIGH_TITLE_TCHST_NMBR
  is '高级职称技术人员数';
comment on column AGENCY_AGENT.REG_PRCTS_NMBR
  is '注册执业人员数';
comment on column AGENCY_AGENT.FIXED_AST_DPRC_YR
  is '固定资产年折旧额';
comment on column AGENCY_AGENT.REG_ISO
  is '是否ISO认证';
comment on column AGENCY_AGENT.CRNT_AST
  is '流动资产';
comment on column AGENCY_AGENT.TOTAL_CHARGE
  is '负责总额';
comment on column AGENCY_AGENT.BID_PRPS_EVLT_ADDR
  is '评标地址';
comment on column AGENCY_AGENT.ADMIN_GRD
  is '行政级别：1.省、2.市、3.县、4.区';
comment on column AGENCY_AGENT.PUR_AGENT
  is '采购代理:01.集中采购、02.招标代理';
comment on column AGENCY_AGENT.MIAN_BUSS_SCP
  is '营业范围（主营）';
comment on column AGENCY_AGENT.CNCR_BUSS_SCP
  is '营业范围（兼营）';
comment on column AGENCY_AGENT.UNDTK_BID_PROJ
  is '近三年承担过的招标代理项目';
comment on column AGENCY_AGENT.AGENCY_BUSS_CNDT
  is '近三年经营代理情况';
comment on column AGENCY_AGENT.REMARKS
  is ' 备注';
comment on column AGENCY_AGENT.CREATOR
  is '创建人';
comment on column AGENCY_AGENT.CREATE_TIME
  is '创建时间';
comment on column AGENCY_AGENT.MODIFIER
  is '修改人';
comment on column AGENCY_AGENT.MODIFY_TIME
  is '修改时间';
comment on column AGENCY_AGENT.AGENT_ID
  is '记录号';
comment on column AGENCY_AGENT.ORG_INFO_ID
  is '机构';
comment on column AGENCY_AGENT.TOTAL_ASSETS
  is '资产总额';
comment on column AGENCY_AGENT.VERIFIER_ID
  is '审核人';
comment on column AGENCY_AGENT.VERIFY_TIME
  is '审核时间';
comment on column AGENCY_AGENT.OPINION
  is '审核意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table AGENCY_AGENT
  add constraint PK_AGENT primary key (AGENT_ID)
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
create table BUY_BUYER
(
  HISTORY_ID         VARCHAR2(36),
  BUDGET_CODE        VARCHAR2(100),
  UNIT_INTRODUCTION  VARCHAR2(1000),
  UNIT_TYPE          VARCHAR2(100),
  FUND_DEPT          VARCHAR2(100),
  DATA_TYPE          VARCHAR2(100),
  AUDIT_STATUS       CHAR(2),
  DISTRICT_ID        VARCHAR2(36),
  FAX                VARCHAR2(50),
  PARENT_UNIT_ID     VARCHAR2(36),
  PUR_SBJCT          VARCHAR2(100),
  EXEC_DEPT          VARCHAR2(100),
  IN_TERRACE         VARCHAR2(100),
  CMPT_DEP_TYPE      VARCHAR2(50),
  CREATOR            VARCHAR2(100),
  CREATE_TIME        DATE,
  MODIFIER           VARCHAR2(100),
  MODIFY_TIME        DATE,
  BUYER_ID           VARCHAR2(36) not null,
  COMPANY_HISTORY_ID VARCHAR2(36),
  TERRACE            VARCHAR2(100),
  ORG_INFO_ID        VARCHAR2(36),
  VER_NO             VARCHAR2(50),
  VERIFIER_ID        VARCHAR2(50),
  VERIFY_TIME        DATE,
  OPINION            VARCHAR2(200),
  INDUSTRY_ID        VARCHAR2(50),
  DEAL_TOTAL         NUMBER(18,6) default 0,
  DEAL_TIME          DATE
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
-- Add comments to the table 
comment on table BUY_BUYER
  is '采购人扩展信息';
-- Add comments to the columns 
comment on column BUY_BUYER.BUDGET_CODE
  is '预算编码';
comment on column BUY_BUYER.UNIT_INTRODUCTION
  is '机构简介';
comment on column BUY_BUYER.UNIT_TYPE
  is '单位性质 01：国家机关 02：事业单位 03：团体组织';
comment on column BUY_BUYER.FUND_DEPT
  is '资金归口处室，01：行政政法 02：教科文 03：经济建设 04：农业 05：社会保障 06：企业 07：金融 08：其他';
comment on column BUY_BUYER.AUDIT_STATUS
  is '00:草稿 01：待审核 02：审核通过 03：审核不通过';
comment on column BUY_BUYER.DISTRICT_ID
  is '所属区域';
comment on column BUY_BUYER.FAX
  is '传真';
comment on column BUY_BUYER.PARENT_UNIT_ID
  is '上级采购单位';
comment on column BUY_BUYER.PUR_SBJCT
  is '采购主体：01.国家机关 02.事业单位 03.团体组织 04.企业单位';
comment on column BUY_BUYER.EXEC_DEPT
  is '行政部门';
comment on column BUY_BUYER.CMPT_DEP_TYPE
  is '主管部门 01:财政 02:建设 03:发改委 04:经贸委 05:监察 06:统计';
comment on column BUY_BUYER.CREATOR
  is '创建人';
comment on column BUY_BUYER.CREATE_TIME
  is '创建时间';
comment on column BUY_BUYER.MODIFIER
  is '修改人';
comment on column BUY_BUYER.MODIFY_TIME
  is ' 修改时间';
comment on column BUY_BUYER.BUYER_ID
  is '记录id';
comment on column BUY_BUYER.ORG_INFO_ID
  is '机构';
comment on column BUY_BUYER.VERIFIER_ID
  is '审核人';
comment on column BUY_BUYER.VERIFY_TIME
  is '审核时间';
comment on column BUY_BUYER.OPINION
  is '审核意见';
comment on column BUY_BUYER.INDUSTRY_ID
  is '所属行业';
comment on column BUY_BUYER.DEAL_TOTAL
  is '成交总额';
comment on column BUY_BUYER.DEAL_TIME
  is '成交时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BUY_BUYER
  add constraint PK_BUYER primary key (BUYER_ID)
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
create table SPL_SUPPLIER
(
  HISTORY_ID            VARCHAR2(36),
  CORPORATION           VARCHAR2(100),
  DESC_CN               VARCHAR2(2000),
  DESC_EN               VARCHAR2(2000),
  OPEN_ACCOUNT          VARCHAR2(100),
  OPEN_ACCOUNT_NMBR     VARCHAR2(100),
  OPEN_BANK             VARCHAR2(100),
  MAIN_PRODUCTS_CN      VARCHAR2(1000),
  MAIN_PRODUCTS_EN      VARCHAR2(1000),
  ORG_CODE              VARCHAR2(100),
  REG_ADDRESS           VARCHAR2(200),
  REG_AUTH_ORG          VARCHAR2(100),
  REG_BUS_SCOPE         VARCHAR2(1000),
  ENT_TYPE              VARCHAR2(100),
  REG_CODE              VARCHAR2(100),
  REG_DATE              DATE,
  REG_TO_DATE           DATE,
  TRADE_END_DATE        DATE,
  TRADE_START_DATE      DATE,
  REG_FILE_ID           VARCHAR2(36),
  UNIT_ADDRESS          VARCHAR2(200),
  REG_CAPITAL           NUMBER(13,2),
  PAID_UP_CAPITAL       NUMBER(13,2),
  REG_MONEY_TYPE        VARCHAR2(100),
  UNIT_SCAPE            VARCHAR2(100),
  UNIT_ZIP              VARCHAR2(100),
  WEB_URL               VARCHAR2(100),
  AUDIT_STATUS          CHAR(2),
  DATA_TYPE             VARCHAR2(100),
  ORG_CONTACT_ID        VARCHAR2(36),
  DISTRICT_ID           VARCHAR2(36),
  UNIT_LOGO_ID          VARCHAR2(36),
  ORG_UNIT_CODE         VARCHAR2(100),
  ORG_UNIT_CODE_FILE_ID VARCHAR2(36),
  ORG_UNIT_TYPE         VARCHAR2(100),
  ORG_UNIT_ADDRESS      VARCHAR2(100),
  ORG_UNIT_AWARD_UNIT   VARCHAR2(100),
  ORG_UNIT_REG_NMBR     VARCHAR2(100),
  ORG_UNIT_START_DATE   DATE,
  ORG_UNIT_END_DATE     DATE,
  CNTCT_NMBR            VARCHAR2(100),
  FAX                   VARCHAR2(100),
  BID_FOR_RANGE         VARCHAR2(2000),
  ENT_PRPT              VARCHAR2(100),
  NAT_TAX_NMBR          VARCHAR2(100),
  NAT_TAX_CMPT_NMBR     VARCHAR2(100),
  NAT_TAX_FILE_ID       VARCHAR2(36),
  LAND_TAX_NMBR         VARCHAR2(100),
  LAND_TAX_CMPT_NMBR    VARCHAR2(100),
  LAND_TAX_FILE_ID      VARCHAR2(36),
  IN_TERRACE            VARCHAR2(100),
  CREATOR               VARCHAR2(36),
  CREATE_TIME           DATE,
  MODIFIER              VARCHAR2(100),
  MODIFY_TIME           DATE,
  SUPPLIER_ID           VARCHAR2(36) not null,
  COMPANY_HISTORY_ID    VARCHAR2(36),
  ORG_INFO_ID           VARCHAR2(36),
  VER_NO                VARCHAR2(50),
  VERIFIER_ID           VARCHAR2(50),
  VERIFY_TIME           DATE,
  OPINION               VARCHAR2(200),
  ISMANUFACTURER        CHAR(1)
)
tablespace USERS
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 576K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SPL_SUPPLIER
  is '供应商扩展信息';
-- Add comments to the columns 
comment on column SPL_SUPPLIER.CORPORATION
  is '法定代表人';
comment on column SPL_SUPPLIER.DESC_CN
  is '企业简介（中）';
comment on column SPL_SUPPLIER.DESC_EN
  is '企业简介(英)';
comment on column SPL_SUPPLIER.OPEN_ACCOUNT
  is '开户帐号，银行账号';
comment on column SPL_SUPPLIER.OPEN_ACCOUNT_NMBR
  is '银行行号';
comment on column SPL_SUPPLIER.OPEN_BANK
  is '开户银行名称';
comment on column SPL_SUPPLIER.MAIN_PRODUCTS_CN
  is '主营产品范围(中)';
comment on column SPL_SUPPLIER.MAIN_PRODUCTS_EN
  is '主营产品范围(英)';
comment on column SPL_SUPPLIER.REG_ADDRESS
  is '工商注册地址，住所';
comment on column SPL_SUPPLIER.REG_AUTH_ORG
  is '工商注册发证机关';
comment on column SPL_SUPPLIER.REG_BUS_SCOPE
  is '工商注册营业范围';
comment on column SPL_SUPPLIER.ENT_TYPE
  is '企业类型，公司类型';
comment on column SPL_SUPPLIER.REG_CODE
  is '工商注册号';
comment on column SPL_SUPPLIER.REG_DATE
  is ' 工商注册日期';
comment on column SPL_SUPPLIER.REG_TO_DATE
  is '工商注册有效期';
comment on column SPL_SUPPLIER.TRADE_END_DATE
  is '营业执照结束日期';
comment on column SPL_SUPPLIER.TRADE_START_DATE
  is '营业执照开始日期';
comment on column SPL_SUPPLIER.UNIT_ADDRESS
  is '经营地址';
comment on column SPL_SUPPLIER.REG_CAPITAL
  is '注册资金，注册资本';
comment on column SPL_SUPPLIER.PAID_UP_CAPITAL
  is ' 实收资本';
comment on column SPL_SUPPLIER.REG_MONEY_TYPE
  is '货币类型 01：元 02：美元 03：欧元 04：日元 05：港币 06：台币';
comment on column SPL_SUPPLIER.UNIT_SCAPE
  is '企业规模';
comment on column SPL_SUPPLIER.WEB_URL
  is '网址';
comment on column SPL_SUPPLIER.AUDIT_STATUS
  is '审核状态，00.草稿 01.待审核 02.通过 03.不通过';
comment on column SPL_SUPPLIER.DISTRICT_ID
  is '所属区域';
comment on column SPL_SUPPLIER.ORG_UNIT_AWARD_UNIT
  is '组织机构证颁发单位';
comment on column SPL_SUPPLIER.ORG_UNIT_REG_NMBR
  is '组织机构证登记号';
comment on column SPL_SUPPLIER.ORG_UNIT_START_DATE
  is '组织机构证开始日期';
comment on column SPL_SUPPLIER.ORG_UNIT_END_DATE
  is '组织机构证结束日期';
comment on column SPL_SUPPLIER.BID_FOR_RANGE
  is ' 投标范围及类别';
comment on column SPL_SUPPLIER.ENT_PRPT
  is '企业性质';
comment on column SPL_SUPPLIER.NAT_TAX_NMBR
  is '国税登记编号';
comment on column SPL_SUPPLIER.NAT_TAX_CMPT_NMBR
  is '税务登记证国税电脑编码';
comment on column SPL_SUPPLIER.LAND_TAX_NMBR
  is '地税登记编号';
comment on column SPL_SUPPLIER.LAND_TAX_CMPT_NMBR
  is '税务登记证地税电脑编码';
comment on column SPL_SUPPLIER.CREATOR
  is '创建人';
comment on column SPL_SUPPLIER.CREATE_TIME
  is '创建时间';
comment on column SPL_SUPPLIER.MODIFIER
  is '修改人';
comment on column SPL_SUPPLIER.MODIFY_TIME
  is '修改时间';
comment on column SPL_SUPPLIER.SUPPLIER_ID
  is '记录id';
comment on column SPL_SUPPLIER.ORG_INFO_ID
  is '机构';
comment on column SPL_SUPPLIER.VERIFIER_ID
  is '审核人';
comment on column SPL_SUPPLIER.VERIFY_TIME
  is '审核时间';
comment on column SPL_SUPPLIER.OPINION
  is '审核意见';
comment on column SPL_SUPPLIER.ISMANUFACTURER
  is '是否厂家';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SPL_SUPPLIER
  add constraint PK_SPL_SUPPLIER primary key (SUPPLIER_ID)
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
create table ORG_INFO_APPLY
(
  ORG_INFO_APPLY_ID VARCHAR2(50) not null,
  APPLY_TYPE        VARCHAR2(50),
  APPLIER           VARCHAR2(50),
  APPLY_TIME        DATE,
  VERIFY_TIME       DATE,
  AUDIT_STATUS      VARCHAR2(2),
  OPINION           VARCHAR2(500),
  ORG_INFO_ID       VARCHAR2(50),
  VERIFIER_ID       VARCHAR2(50),
  CREATOR           VARCHAR2(50),
  CREATE_TIME       DATE
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
comment on table ORG_INFO_APPLY
  is '机构申请';
-- Add comments to the columns 
comment on column ORG_INFO_APPLY.ORG_INFO_APPLY_ID
  is '主键';
comment on column ORG_INFO_APPLY.APPLY_TYPE
  is '申请类型';
comment on column ORG_INFO_APPLY.APPLIER
  is '申请人';
comment on column ORG_INFO_APPLY.APPLY_TIME
  is '申请时间';
comment on column ORG_INFO_APPLY.VERIFY_TIME
  is '审核时间';
comment on column ORG_INFO_APPLY.AUDIT_STATUS
  is '审核状态';
comment on column ORG_INFO_APPLY.OPINION
  is '审核意见';
comment on column ORG_INFO_APPLY.ORG_INFO_ID
  is '机构ID';
comment on column ORG_INFO_APPLY.VERIFIER_ID
  is '审核人';
comment on column ORG_INFO_APPLY.CREATOR
  is '创建人';
comment on column ORG_INFO_APPLY.CREATE_TIME
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_INFO_APPLY
  add constraint INFO_APPLY_KEY primary key (ORG_INFO_APPLY_ID)
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
create table ORG_INFO_MODIFY
(
  ORG_INFO_MODIFY_ID VARCHAR2(50) not null,
  MODIFY_TYPE        VARCHAR2(50),
  OLD_VALUE          VARCHAR2(500),
  NEW_VALUE          VARCHAR2(500),
  MODIFIER           VARCHAR2(50),
  MODIFY_TIME        DATE,
  AUDIT_STATUS       VARCHAR2(2),
  VERIFY_TIME        DATE,
  OPINION            VARCHAR2(500),
  ORG_INFO_ID        VARCHAR2(50),
  VERIFIER_ID        VARCHAR2(50),
  CREATOR            VARCHAR2(50),
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
comment on table ORG_INFO_MODIFY
  is '机构变更历史';
-- Add comments to the columns 
comment on column ORG_INFO_MODIFY.ORG_INFO_MODIFY_ID
  is '主键';
comment on column ORG_INFO_MODIFY.MODIFY_TYPE
  is '变更类型';
comment on column ORG_INFO_MODIFY.OLD_VALUE
  is '旧值';
comment on column ORG_INFO_MODIFY.NEW_VALUE
  is '新值';
comment on column ORG_INFO_MODIFY.MODIFIER
  is '变更人';
comment on column ORG_INFO_MODIFY.MODIFY_TIME
  is '变更时间';
comment on column ORG_INFO_MODIFY.AUDIT_STATUS
  is '审核状态';
comment on column ORG_INFO_MODIFY.VERIFY_TIME
  is '审核时间';
comment on column ORG_INFO_MODIFY.OPINION
  is '审核意见';
comment on column ORG_INFO_MODIFY.ORG_INFO_ID
  is '机构ID';
comment on column ORG_INFO_MODIFY.VERIFIER_ID
  is '审核人';
comment on column ORG_INFO_MODIFY.CREATOR
  is '创建人';
comment on column ORG_INFO_MODIFY.CREATE_TIME
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_INFO_MODIFY
  add constraint ORG_INFO_MODIFY_KEY primary key (ORG_INFO_MODIFY_ID)
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
create table ORG_QUALIFICATION
(
  CREATOR            VARCHAR2(100),
  CREATE_TIME        DATE,
  MODIFIER           VARCHAR2(100),
  MODIFY_TIME        DATE,
  BELONG_OBJECT_NAME VARCHAR2(100),
  BELONG_OBJECT_ID   VARCHAR2(36),
  QUALITY_GRADES_ID  VARCHAR2(36),
  STATUS             VARCHAR2(100),
  BELONG_NAME        VARCHAR2(100),
  QUALIFICATION_ID   VARCHAR2(36) not null,
  USE_STATUS         VARCHAR2(100),
  QUALITY_CLASS_ID   VARCHAR2(36),
  QUALITY_DEFINE_ID  VARCHAR2(36),
  QUALIFICATION_FILE VARCHAR2(50),
  VERIFIER_ID        VARCHAR2(50),
  VERIFY_TIME        DATE,
  OPINION            VARCHAR2(200)
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
comment on table ORG_QUALIFICATION
  is '资质信息';
-- Add comments to the columns 
comment on column ORG_QUALIFICATION.CREATOR
  is '创建人     ';
comment on column ORG_QUALIFICATION.CREATE_TIME
  is '创建时间     ';
comment on column ORG_QUALIFICATION.MODIFIER
  is '修改人     ';
comment on column ORG_QUALIFICATION.MODIFY_TIME
  is '修改时间     ';
comment on column ORG_QUALIFICATION.BELONG_OBJECT_NAME
  is '所属对象名：类名或表名     ';
comment on column ORG_QUALIFICATION.BELONG_OBJECT_ID
  is '所属对象ID     ';
comment on column ORG_QUALIFICATION.QUALITY_GRADES_ID
  is '资质信息定义     ';
comment on column ORG_QUALIFICATION.STATUS
  is '审核状态     ';
comment on column ORG_QUALIFICATION.BELONG_NAME
  is '资质所属：企业单位或个人的名称，冗余字段     ';
comment on column ORG_QUALIFICATION.QUALIFICATION_ID
  is '主键';
comment on column ORG_QUALIFICATION.USE_STATUS
  is '使用状态';
comment on column ORG_QUALIFICATION.QUALITY_CLASS_ID
  is '资质类型';
comment on column ORG_QUALIFICATION.QUALITY_DEFINE_ID
  is '资质定义';
comment on column ORG_QUALIFICATION.QUALIFICATION_FILE
  is '资质文件';
comment on column ORG_QUALIFICATION.VERIFIER_ID
  is '审核人';
comment on column ORG_QUALIFICATION.VERIFY_TIME
  is '审核时间';
comment on column ORG_QUALIFICATION.OPINION
  is '审核意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_QUALIFICATION
  add constraint PK_QUALIFICATION primary key (QUALIFICATION_ID)
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
create table ORG_QUALIFICATION_DETAIL
(
  PARAM_VALUE             VARCHAR2(100),
  QUALITY_PARAM_ID        VARCHAR2(36),
  QUALIFICATION_DETAIL_ID VARCHAR2(36) not null,
  QUALIFICATION_ID        VARCHAR2(36)
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
comment on table ORG_QUALIFICATION_DETAIL
  is '供应商资质信息详细';
-- Add comments to the columns 
comment on column ORG_QUALIFICATION_DETAIL.PARAM_VALUE
  is '参数值';
comment on column ORG_QUALIFICATION_DETAIL.QUALITY_PARAM_ID
  is '资质参数';
comment on column ORG_QUALIFICATION_DETAIL.QUALIFICATION_DETAIL_ID
  is '主键';
comment on column ORG_QUALIFICATION_DETAIL.QUALIFICATION_ID
  is '资质主对象';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_QUALIFICATION_DETAIL
  add constraint PK_QUALIFICATION_DETAIL_ID primary key (QUALIFICATION_DETAIL_ID);

  -- Create table
create table ECP_PUB_SUCCESS_CASE
(
  CASE_ID          VARCHAR2(50) not null,
  ORGINFO_ID       VARCHAR2(50),
  PROJECT_NAME     VARCHAR2(50),
  START_DATE       DATE,
  USE_STATUS       CHAR(2) default 00,
  CREATOR          VARCHAR2(50),
  CREATE_TIME      DATE,
  END_DATE         DATE,
  CATEGORY_IDS     VARCHAR2(500),
  CATEGORY_NAMES   VARCHAR2(1000),
  CASE_DESCRIPTION VARCHAR2(4000),
  AUDIT_STATUS     CHAR(2) default 00,
  VERIFIER_ID      VARCHAR2(50),
  VERIFY_TIME      DATE,
  OPINION          VARCHAR2(200)
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
comment on column ECP_PUB_SUCCESS_CASE.CASE_ID
  is '主键';
comment on column ECP_PUB_SUCCESS_CASE.ORGINFO_ID
  is '所属机构id';
comment on column ECP_PUB_SUCCESS_CASE.PROJECT_NAME
  is '项目名称';
comment on column ECP_PUB_SUCCESS_CASE.START_DATE
  is '开始时间';
comment on column ECP_PUB_SUCCESS_CASE.USE_STATUS
  is '状态';
comment on column ECP_PUB_SUCCESS_CASE.CREATOR
  is '创建人';
comment on column ECP_PUB_SUCCESS_CASE.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_SUCCESS_CASE.END_DATE
  is '结束时间';
comment on column ECP_PUB_SUCCESS_CASE.CATEGORY_IDS
  is '采购品目IDS，以逗号分割';
comment on column ECP_PUB_SUCCESS_CASE.CATEGORY_NAMES
  is '采购品目名称，以逗号分割';
comment on column ECP_PUB_SUCCESS_CASE.CASE_DESCRIPTION
  is '案例描述';
comment on column ECP_PUB_SUCCESS_CASE.AUDIT_STATUS
  is '审核状态';
comment on column ECP_PUB_SUCCESS_CASE.VERIFIER_ID
  is '审核人';
comment on column ECP_PUB_SUCCESS_CASE.VERIFY_TIME
  is '审核时间';
comment on column ECP_PUB_SUCCESS_CASE.OPINION
  is '审核意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_SUCCESS_CASE
  add constraint PK_CASE_ID primary key (CASE_ID)
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
create table PURCATALOG_CATEGORY
(
  ID                  VARCHAR2(50) not null,
  PARENT_ID           VARCHAR2(50),
  CATEGORY_CODE       VARCHAR2(50) not null,
  CATEGORY_NAME       VARCHAR2(100) not null,
  REMARKS             VARCHAR2(300),
  RELEASE_DATE        DATE not null,
  RELEASE_STATUS      VARCHAR2(50),
  CREATE_USER         VARCHAR2(50),
  CREATE_DATE         DATE,
  UPDATE_USER         VARCHAR2(50),
  UPDATE_DATE         DATE,
  PURCATEGORY_IS_LEAF VARCHAR2(5),
  SHORT_SPELL_NAME    VARCHAR2(50),
  CATEGORY_SORT       NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table PURCATALOG_CATEGORY
  is '采购品目';
-- Add comments to the columns 
comment on column PURCATALOG_CATEGORY.ID
  is '主键';
comment on column PURCATALOG_CATEGORY.PARENT_ID
  is '下级品目';
comment on column PURCATALOG_CATEGORY.CATEGORY_CODE
  is '序号';
comment on column PURCATALOG_CATEGORY.CATEGORY_NAME
  is '品目名称';
comment on column PURCATALOG_CATEGORY.REMARKS
  is '备注';
comment on column PURCATALOG_CATEGORY.RELEASE_DATE
  is '发布日期';
comment on column PURCATALOG_CATEGORY.RELEASE_STATUS
  is '发布状态';
comment on column PURCATALOG_CATEGORY.CREATE_USER
  is '创建人';
comment on column PURCATALOG_CATEGORY.CREATE_DATE
  is '创建时间';
comment on column PURCATALOG_CATEGORY.UPDATE_USER
  is '修改人';
comment on column PURCATALOG_CATEGORY.UPDATE_DATE
  is '修改时间';
comment on column PURCATALOG_CATEGORY.PURCATEGORY_IS_LEAF
  is '是否叶子结点';
comment on column PURCATALOG_CATEGORY.SHORT_SPELL_NAME
  is '拼音缩写';
comment on column PURCATALOG_CATEGORY.CATEGORY_SORT
  is '排序';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PURCATALOG_CATEGORY
  add constraint PK_PURCATALOG_CATEGORY primary key (ID)
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
create table ECP_BASE_CATALOG
(
  CATALOG_ID  VARCHAR2(50) not null,
  AREA_CODE   VARCHAR2(50),
  AREA_NAME   VARCHAR2(50),
  YEAR        NUMBER(4),
  TITLE       VARCHAR2(100),
  CONTENT     VARCHAR2(4000),
  AUDITSTATUS CHAR(2),
  RELDATE     DATE,
  RELUSER     VARCHAR2(50),
  CREDATE     DATE,
  CREUSER     VARCHAR2(50),
  USESTATUS   CHAR(2),
  PUBDATE     DATE,
  PUBSTATUS   CHAR(2),
  VERIFIER_ID VARCHAR2(50),
  VERIFY_TIME DATE,
  OPINION     VARCHAR2(200)
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
comment on table ECP_BASE_CATALOG
  is '采购目录';
-- Add comments to the columns 
comment on column ECP_BASE_CATALOG.CATALOG_ID
  is 'ID
';
comment on column ECP_BASE_CATALOG.AREA_CODE
  is '使用区域编码
';
comment on column ECP_BASE_CATALOG.AREA_NAME
  is '区域名称
';
comment on column ECP_BASE_CATALOG.YEAR
  is '年度
';
comment on column ECP_BASE_CATALOG.TITLE
  is '标题
';
comment on column ECP_BASE_CATALOG.CONTENT
  is '有关说明及要求
';
comment on column ECP_BASE_CATALOG.AUDITSTATUS
  is '审核状态
';
comment on column ECP_BASE_CATALOG.RELDATE
  is '生效时间';
comment on column ECP_BASE_CATALOG.RELUSER
  is '发布人
';
comment on column ECP_BASE_CATALOG.CREDATE
  is '创建时间
';
comment on column ECP_BASE_CATALOG.CREUSER
  is '创建人
';
comment on column ECP_BASE_CATALOG.USESTATUS
  is '使用状态
00：临时；01：正式；02：作废
';
comment on column ECP_BASE_CATALOG.PUBDATE
  is '发布时间';
comment on column ECP_BASE_CATALOG.PUBSTATUS
  is '发布状态';
comment on column ECP_BASE_CATALOG.VERIFIER_ID
  is '审核人';
comment on column ECP_BASE_CATALOG.VERIFY_TIME
  is '审核时间';
comment on column ECP_BASE_CATALOG.OPINION
  is '审核意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BASE_CATALOG
  add constraint PK_CATALOG_ID primary key (CATALOG_ID)
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
alter table ECP_BASE_CATALOG
  add constraint FK_CREUSER foreign key (CREUSER)
  references AUTH_USER (USR_ID);
alter table ECP_BASE_CATALOG
  add constraint FK_RELUSER foreign key (RELUSER)
  references AUTH_USER (USR_ID);

  -- Create table
create table ECP_BASE_CATALOG_DETAIL
(
  CATALOG_DETAIL_ID VARCHAR2(50) not null,
  CATALOG_ID        VARCHAR2(50),
  CATEGORY_ID       VARCHAR2(50),
  GOODSPRICE        NUMBER(10,2),
  YEAR_PROC_TOTAL   NUMBER(10,2)
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
comment on column ECP_BASE_CATALOG_DETAIL.CATALOG_DETAIL_ID
  is 'ID
';
comment on column ECP_BASE_CATALOG_DETAIL.CATALOG_ID
  is '目录ID
';
comment on column ECP_BASE_CATALOG_DETAIL.CATEGORY_ID
  is '品目ID
';
comment on column ECP_BASE_CATALOG_DETAIL.GOODSPRICE
  is '单价
含指定金额（大于等于）
';
comment on column ECP_BASE_CATALOG_DETAIL.YEAR_PROC_TOTAL
  is '年批量采购金额
含指定金额（大于等于）
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BASE_CATALOG_DETAIL
  add constraint PK_CATALOG_DETAIL_ID primary key (CATALOG_DETAIL_ID)
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
alter table ECP_BASE_CATALOG_DETAIL
  add constraint FK_CATALOG_ID foreign key (CATALOG_ID)
  references ECP_BASE_CATALOG (CATALOG_ID);

  -- Create table
create table ECP_BASE_CATALOG_PROCTYPE
(
  CATALOG_PROCTYPE_ID VARCHAR2(50) not null,
  CATALOG_ID          VARCHAR2(50),
  CATEGORY_ID         VARCHAR2(50),
  PROCTYPE            CHAR(2),
  PROC_TOTAL          NUMBER(10,2)
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
comment on column ECP_BASE_CATALOG_PROCTYPE.CATALOG_PROCTYPE_ID
  is 'ID
';
comment on column ECP_BASE_CATALOG_PROCTYPE.CATALOG_ID
  is '目录ID
';
comment on column ECP_BASE_CATALOG_PROCTYPE.CATEGORY_ID
  is '品目ID
';
comment on column ECP_BASE_CATALOG_PROCTYPE.PROCTYPE
  is '采购方式
';
comment on column ECP_BASE_CATALOG_PROCTYPE.PROC_TOTAL
  is '金额
含指定金额（大于等于）
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BASE_CATALOG_PROCTYPE
  add constraint PK_CATALOG_PROCTYPE_ID primary key (CATALOG_PROCTYPE_ID)
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
alter table ECP_BASE_CATALOG_PROCTYPE
  add constraint FK_PROCTYPE_CATALOG_ID foreign key (CATALOG_ID)
  references ECP_BASE_CATALOG (CATALOG_ID);

  -- Create table
create table ECP_BASE_CTLOG_DSTRCT
(
  ECP_BASE_CTLOG_DSTRCT_ID VARCHAR2(50) not null,
  CATALOG_ID               VARCHAR2(50),
  DISTRICT_ID              VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_BASE_CTLOG_DSTRCT
  is '目录区域中间表';
-- Add comments to the columns 
comment on column ECP_BASE_CTLOG_DSTRCT.ECP_BASE_CTLOG_DSTRCT_ID
  is '主键';
comment on column ECP_BASE_CTLOG_DSTRCT.CATALOG_ID
  is '目录';
comment on column ECP_BASE_CTLOG_DSTRCT.DISTRICT_ID
  is '区域';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_BASE_CTLOG_DSTRCT
  add constraint PK_CTLOG_DSTRCT_ID primary key (ECP_BASE_CTLOG_DSTRCT_ID)
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
alter table ECP_BASE_CTLOG_DSTRCT
  add constraint FK_CTLOG_DSTRCT_CATALOG_ID foreign key (CATALOG_ID)
  references ECP_BASE_CATALOG (CATALOG_ID);
alter table ECP_BASE_CTLOG_DSTRCT
  add constraint FK_CTLOG_DSTRCT_DISTRICT_ID foreign key (DISTRICT_ID)
  references BASE_DISTRICT (DISTRICT_ID);

  -- Create table
create table BASE_QUALIFICATION
(
  QUALITY_ID       VARCHAR2(50) not null,
  QUALITY_CODE     VARCHAR2(50),
  QUALITY_NAME     VARCHAR2(100),
  SORT             NUMBER(19),
  CREATOR          VARCHAR2(50),
  CREATE_TIME      DATE,
  MODIFIER         VARCHAR2(50),
  MODIFY_TIME      DATE,
  USE_STATUS       CHAR(2),
  TYPE             CHAR(2),
  PARENT_ID        VARCHAR2(50),
  IS_LEAF          CHAR(1),
  CLASS_TYPE       VARCHAR2(100),
  PARAM_TYPE       VARCHAR2(100),
  IS_REQUIRED      CHAR(1),
  SHORT_SPELL_NAME VARCHAR2(50),
  TREE_PATH        VARCHAR2(500),
  LEVEL_DATA       VARCHAR2(500),
  UNIT             VARCHAR2(20),
  IS_DISPLAY       CHAR(1) default 1
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
comment on column BASE_QUALIFICATION.QUALITY_ID
  is '主键';
comment on column BASE_QUALIFICATION.QUALITY_CODE
  is '资质编号';
comment on column BASE_QUALIFICATION.QUALITY_NAME
  is '资质名称';
comment on column BASE_QUALIFICATION.SORT
  is '资质排序';
comment on column BASE_QUALIFICATION.CREATOR
  is '创建人';
comment on column BASE_QUALIFICATION.CREATE_TIME
  is '创建时间';
comment on column BASE_QUALIFICATION.MODIFIER
  is '修改人';
comment on column BASE_QUALIFICATION.MODIFY_TIME
  is '修改时间';
comment on column BASE_QUALIFICATION.USE_STATUS
  is '状态（00临时,01正式）';
comment on column BASE_QUALIFICATION.TYPE
  is '类型（00资质分类，01,资质定义，02,资质参数）';
comment on column BASE_QUALIFICATION.PARENT_ID
  is '上级资质';
comment on column BASE_QUALIFICATION.IS_LEAF
  is '是否叶子节点';
comment on column BASE_QUALIFICATION.CLASS_TYPE
  is '资质类型';
comment on column BASE_QUALIFICATION.PARAM_TYPE
  is '资质参数类型';
comment on column BASE_QUALIFICATION.IS_REQUIRED
  is '资质参数是否必填';
comment on column BASE_QUALIFICATION.SHORT_SPELL_NAME
  is '名称拼音缩写';
comment on column BASE_QUALIFICATION.TREE_PATH
  is '路径';
comment on column BASE_QUALIFICATION.LEVEL_DATA
  is '级别（级别之间用，隔开，每个级别的级别名称和级别值用#隔开）';
comment on column BASE_QUALIFICATION.UNIT
  is '参数单位';
comment on column BASE_QUALIFICATION.IS_DISPLAY
  is '是否显示';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BASE_QUALIFICATION
  add constraint PK_QUALITY_ID primary key (QUALITY_ID)
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
create table ECP_PUB_EVALUATE_QUOTA
(
  QUOTA_ID         VARCHAR2(50) not null,
  QUOTA_NAME       VARCHAR2(200),
  QUOTA_PROPORTION NUMBER(6,2),
  QUOTA_TYPE       CHAR(2)
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
comment on table ECP_PUB_EVALUATE_QUOTA
  is '评价指标表';
-- Add comments to the columns 
comment on column ECP_PUB_EVALUATE_QUOTA.QUOTA_ID
  is '指标id
';
comment on column ECP_PUB_EVALUATE_QUOTA.QUOTA_NAME
  is '指标名称
';
comment on column ECP_PUB_EVALUATE_QUOTA.QUOTA_PROPORTION
  is '比重
';
comment on column ECP_PUB_EVALUATE_QUOTA.QUOTA_TYPE
  is '角色类型
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_EVALUATE_QUOTA
  add constraint PK_QUOTA_ID primary key (QUOTA_ID)
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
create table ECP_PUB_EVALUATE
(
  EVALUATE_ID   VARCHAR2(50) not null,
  ORGINFO_ID    VARCHAR2(50),
  PROJECT_ID    VARCHAR2(50),
  RATED_ID      VARCHAR2(50),
  EVAL_TIME     DATE,
  EVAL_LEVAL    CHAR(1),
  EVAL_REMARK   VARCHAR2(3000),
  SUMMARY_SCORE NUMBER(6,2),
  IS_ANONYMOUS  CHAR(1),
  PROJECT_NAME  VARCHAR2(200),
  RATED_ORG     VARCHAR2(50)
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
comment on table ECP_PUB_EVALUATE
  is '评价表 ';
-- Add comments to the columns 
comment on column ECP_PUB_EVALUATE.EVALUATE_ID
  is 'Id
';
comment on column ECP_PUB_EVALUATE.ORGINFO_ID
  is '被评价机构id

';
comment on column ECP_PUB_EVALUATE.PROJECT_ID
  is '项目id

';
comment on column ECP_PUB_EVALUATE.RATED_ID
  is '评价人id

';
comment on column ECP_PUB_EVALUATE.EVAL_TIME
  is '评价时间

';
comment on column ECP_PUB_EVALUATE.EVAL_LEVAL
  is '评价级别

';
comment on column ECP_PUB_EVALUATE.EVAL_REMARK
  is '评价描述

';
comment on column ECP_PUB_EVALUATE.SUMMARY_SCORE
  is '总分

';
comment on column ECP_PUB_EVALUATE.IS_ANONYMOUS
  is '是否匿名

';
comment on column ECP_PUB_EVALUATE.PROJECT_NAME
  is '项目名称';
comment on column ECP_PUB_EVALUATE.RATED_ORG
  is '评价机构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_EVALUATE
  add constraint PK_EVALUATE_ID primary key (EVALUATE_ID)
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
create table ECP_PUB_EVALUATE_SCORE
(
  SCORE_ID         VARCHAR2(50) not null,
  EVALUATE_ID      VARCHAR2(50),
  QUOTA_ID         VARCHAR2(50),
  QUOTA_PROPORTION NUMBER(6,2),
  QUOTA_NAME       VARCHAR2(200),
  SCORE            NUMBER(6,2)
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
comment on table ECP_PUB_EVALUATE_SCORE
  is '评价指标分值表';
-- Add comments to the columns 
comment on column ECP_PUB_EVALUATE_SCORE.SCORE_ID
  is '主键
';
comment on column ECP_PUB_EVALUATE_SCORE.EVALUATE_ID
  is '评价主表id
';
comment on column ECP_PUB_EVALUATE_SCORE.QUOTA_ID
  is '指标id
';
comment on column ECP_PUB_EVALUATE_SCORE.QUOTA_PROPORTION
  is '比重
';
comment on column ECP_PUB_EVALUATE_SCORE.QUOTA_NAME
  is '名称
';
comment on column ECP_PUB_EVALUATE_SCORE.SCORE
  is '分值
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_EVALUATE_SCORE
  add constraint PK_SCORE_ID primary key (SCORE_ID)
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
alter table ECP_PUB_EVALUATE_SCORE
  add constraint FK_EVALUATE_ID foreign key (EVALUATE_ID)
  references ECP_PUB_EVALUATE (EVALUATE_ID);
alter table ECP_PUB_EVALUATE_SCORE
  add constraint FK_QUOTA_ID foreign key (QUOTA_ID)
  references ECP_PUB_EVALUATE_QUOTA (QUOTA_ID);

  -- Create table
create table GOODS_CLASS
(
  PARENT_CLASS_ID     VARCHAR2(36),
  GOODS_CLASS_CODE    VARCHAR2(100),
  GOODS_CLASS_NAME    VARCHAR2(100),
  PARAM_INPUT_TYPE    VARCHAR2(5),
  SORT_NO             NUMBER(9),
  REMARKS             VARCHAR2(2000),
  CREATOR_ID          VARCHAR2(36),
  CREATE_TIME         DATE,
  MODIFIER_ID         VARCHAR2(36),
  MODIFY_TIME         DATE,
  GOODS_CLASS_ID      VARCHAR2(36) not null,
  SORT                NUMBER(9),
  GOODS_CLASS_IS_LEAF VARCHAR2(5),
  NAME_FIRST_SPELL    CHAR(1),
  SHORT_SPELL_NAME    VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_CLASS
  is '商品分类';
-- Add comments to the columns 
comment on column GOODS_CLASS.PARENT_CLASS_ID
  is '父分类';
comment on column GOODS_CLASS.GOODS_CLASS_CODE
  is '分类编号';
comment on column GOODS_CLASS.GOODS_CLASS_NAME
  is '分类名称';
comment on column GOODS_CLASS.PARAM_INPUT_TYPE
  is '参数录入方式:分项录入、整体录入，两者任选';
comment on column GOODS_CLASS.REMARKS
  is '备注';
comment on column GOODS_CLASS.CREATOR_ID
  is '创建人';
comment on column GOODS_CLASS.CREATE_TIME
  is '创建时间';
comment on column GOODS_CLASS.MODIFIER_ID
  is '修改人';
comment on column GOODS_CLASS.MODIFY_TIME
  is '修改时间';
comment on column GOODS_CLASS.SORT
  is '排序字段';
comment on column GOODS_CLASS.GOODS_CLASS_IS_LEAF
  is '是否叶子结点';
comment on column GOODS_CLASS.SHORT_SPELL_NAME
  is '拼音简码规则';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_CLASS
  add constraint PK_GOODS_CLASS primary key (GOODS_CLASS_ID)
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
create table GOODS_CLASS_PARAM
(
  GOODS_CLASS_ID       VARCHAR2(36),
  PARAM_NAME           VARCHAR2(100),
  CAN_SELECT           CHAR(1),
  PARAM_DESC           VARCHAR2(300),
  SORT                 NUMBER(8),
  NEED_INPUT           CHAR(1),
  PARAM_UNIT           VARCHAR2(100),
  USUALLY_SEARCH       CHAR(1),
  MAIN_PARAM           CHAR(1),
  CREATOR_ID           VARCHAR2(36),
  CREATE_TIME          TIMESTAMP(6),
  MODIFIER_ID          VARCHAR2(36),
  MODIFY_TIME          TIMESTAMP(6),
  GOODS_CLASS_PARAM_ID VARCHAR2(36) not null,
  PARAM_TYPE           CHAR(2),
  PARAM_CODE           VARCHAR2(100),
  PARAM_PARENT_ID      VARCHAR2(36),
  PARAM_IS_LEAF        CHAR(1),
  TREE_PATH            VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 192K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_CLASS_PARAM
  is '商品分类参数';
-- Add comments to the columns 
comment on column GOODS_CLASS_PARAM.GOODS_CLASS_ID
  is '商品分类';
comment on column GOODS_CLASS_PARAM.PARAM_NAME
  is '参数分类名称';
comment on column GOODS_CLASS_PARAM.CAN_SELECT
  is '是否可选配 (分类参数属性)';
comment on column GOODS_CLASS_PARAM.PARAM_DESC
  is '参数分类说明';
comment on column GOODS_CLASS_PARAM.SORT
  is '排序号';
comment on column GOODS_CLASS_PARAM.NEED_INPUT
  is '是否必须填写(分类参数属性)';
comment on column GOODS_CLASS_PARAM.PARAM_UNIT
  is '参数值计量单位(分类参数属性)';
comment on column GOODS_CLASS_PARAM.USUALLY_SEARCH
  is '是否常用检索参数(分类参数属性)';
comment on column GOODS_CLASS_PARAM.MAIN_PARAM
  is '是否主要参数(分类参数属性)';
comment on column GOODS_CLASS_PARAM.CREATOR_ID
  is '创建人';
comment on column GOODS_CLASS_PARAM.CREATE_TIME
  is '创建时间';
comment on column GOODS_CLASS_PARAM.MODIFIER_ID
  is '修改人';
comment on column GOODS_CLASS_PARAM.MODIFY_TIME
  is '修改时间';
comment on column GOODS_CLASS_PARAM.PARAM_TYPE
  is '类型: 01.基类 02.分类参数';
comment on column GOODS_CLASS_PARAM.PARAM_CODE
  is '参数分类编号';
comment on column GOODS_CLASS_PARAM.PARAM_PARENT_ID
  is '父级分类';
comment on column GOODS_CLASS_PARAM.PARAM_IS_LEAF
  is '是否叶子节点';
comment on column GOODS_CLASS_PARAM.TREE_PATH
  is '路径';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_CLASS_PARAM
  add constraint PK_GOODS_CLASS_PARAM primary key (GOODS_CLASS_PARAM_ID)
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
create table GOODS_CLASS_TO_CATEGORY
(
  GOODS_CLASS_ID             VARCHAR2(36),
  PUR_CATEGORY_ID            VARCHAR2(36),
  GOODS_CLASS_TO_CATEGORY_ID VARCHAR2(36) not null
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
comment on table GOODS_CLASS_TO_CATEGORY
  is '商品分类与品目中间表';
-- Add comments to the columns 
comment on column GOODS_CLASS_TO_CATEGORY.GOODS_CLASS_ID
  is '分类id';
comment on column GOODS_CLASS_TO_CATEGORY.PUR_CATEGORY_ID
  is '品目id';
comment on column GOODS_CLASS_TO_CATEGORY.GOODS_CLASS_TO_CATEGORY_ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_CLASS_TO_CATEGORY
  add constraint PK_GOODS_CLASS_TO_CATEGORY_ID primary key (GOODS_CLASS_TO_CATEGORY_ID)
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
create table GOODS_BRAND
(
  AUDIT_STATUS      VARCHAR2(100),
  BRAND_CODE        VARCHAR2(100),
  BRAND_DESC        VARCHAR2(100),
  BRAND_NAME        VARCHAR2(100),
  ENGLISH_NAME      VARCHAR2(100),
  SHORT_SPELL_NAME  VARCHAR2(100),
  MAIN_LOGO_ID      VARCHAR2(36),
  SELL_STATUS       VARCHAR2(100),
  MANAGER_STATUS    VARCHAR2(100),
  ECDEMIC           VARCHAR2(5),
  FOREIGNER         VARCHAR2(5),
  HISTORY_ID        VARCHAR2(36),
  VER_NO            VARCHAR2(100),
  CREATOR_ID        VARCHAR2(36),
  CREATE_TIME       DATE,
  MODIFIER_ID       VARCHAR2(36),
  MODIFY_TIME       DATE,
  GOODS_BRAND_ID    VARCHAR2(36) not null,
  SUPPLIER_ID       VARCHAR2(36),
  PIN_YIN_CODE      VARCHAR2(100),
  CURRENT_VALID_ID  VARCHAR2(50),
  GOODS_CLASS_NAMES VARCHAR2(200),
  VALID_TIME        DATE,
  INVALID_TIME      DATE,
  VERIFIER_ID       VARCHAR2(50),
  VERIFY_TIME       DATE,
  OPINION           VARCHAR2(200),
  BELONGS_ID        VARCHAR2(50),
  BELONGS_NAME      VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 320K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_BRAND
  is '品牌';
-- Add comments to the columns 
comment on column GOODS_BRAND.AUDIT_STATUS
  is '审批状态：00.草稿（默认） 01.待审核 02.通过 03.不通过';
comment on column GOODS_BRAND.BRAND_CODE
  is '品牌编号';
comment on column GOODS_BRAND.BRAND_DESC
  is '品牌说明';
comment on column GOODS_BRAND.BRAND_NAME
  is '品牌名称';
comment on column GOODS_BRAND.ENGLISH_NAME
  is '品牌英文名称';
comment on column GOODS_BRAND.SHORT_SPELL_NAME
  is '拼音简写';
comment on column GOODS_BRAND.MAIN_LOGO_ID
  is '品牌主标识';
comment on column GOODS_BRAND.SELL_STATUS
  is '售卖状态（注：启卖、禁卖）';
comment on column GOODS_BRAND.MANAGER_STATUS
  is '有效状态（注：00.草稿,01.有效、02.报废）';
comment on column GOODS_BRAND.ECDEMIC
  is 'ECDEMIC 是否外地品牌';
comment on column GOODS_BRAND.FOREIGNER
  is 'ECDEMIC 是否国外品牌';
comment on column GOODS_BRAND.CREATOR_ID
  is '创建人';
comment on column GOODS_BRAND.CREATE_TIME
  is '创建时间';
comment on column GOODS_BRAND.MODIFIER_ID
  is '修改人';
comment on column GOODS_BRAND.MODIFY_TIME
  is '修改时间';
comment on column GOODS_BRAND.GOODS_BRAND_ID
  is '商品维护商';
comment on column GOODS_BRAND.CURRENT_VALID_ID
  is '当前有效id';
comment on column GOODS_BRAND.GOODS_CLASS_NAMES
  is '类别名称';
comment on column GOODS_BRAND.VALID_TIME
  is '生效时间';
comment on column GOODS_BRAND.INVALID_TIME
  is '失效时间';
comment on column GOODS_BRAND.VERIFIER_ID
  is '审核人';
comment on column GOODS_BRAND.VERIFY_TIME
  is '审核时间';
comment on column GOODS_BRAND.OPINION
  is '审核意见';
comment on column GOODS_BRAND.BELONGS_ID
  is '所属机构';
comment on column GOODS_BRAND.BELONGS_NAME
  is '所属机构名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_BRAND
  add constraint PK_GOODS_BRAND primary key (GOODS_BRAND_ID)
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
create table GOODS_BRAND_TO_CLASS
(
  GOODS_CLASS_ID          VARCHAR2(36),
  GOODS_BRAND_ID          VARCHAR2(36),
  GOODS_BRAND_TO_CLASS_ID VARCHAR2(36) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_BRAND_TO_CLASS
  is '品牌与分类中间表';
-- Add comments to the columns 
comment on column GOODS_BRAND_TO_CLASS.GOODS_CLASS_ID
  is '分类id';
comment on column GOODS_BRAND_TO_CLASS.GOODS_BRAND_ID
  is '品牌id';
comment on column GOODS_BRAND_TO_CLASS.GOODS_BRAND_TO_CLASS_ID
  is '主键';

  -- Create table
create table GOODS
(
  HISTORY_ID               VARCHAR2(36),
  VER_NO                   VARCHAR2(100),
  PRODUCT_NAME             VARCHAR2(100),
  PRODUCT_CODE             VARCHAR2(100),
  FUNCTION_INTRO           VARCHAR2(4000),
  IS_CUSTOM                VARCHAR2(5),
  MEASURE_UNIT             VARCHAR2(100),
  PICTURE_ID               VARCHAR2(36),
  SPEC                     VARCHAR2(2000),
  GOODS_CLASS_ID           VARCHAR2(36),
  MADE_IN                  VARCHAR2(100),
  FACTORY                  VARCHAR2(100),
  IS_ACCESSORY             VARCHAR2(5),
  ENERGY_SAVING_PRODUCT_NO VARCHAR2(100),
  ENVIRONMENT_LABEL        VARCHAR2(100),
  CRYPTOGRAPHY_TECH_CODE   VARCHAR2(100),
  CREATION_CODE            VARCHAR2(100),
  SOLE_TO_SELL             VARCHAR2(5),
  SPECIAL                  VARCHAR2(5),
  REMARK                   VARCHAR2(2000),
  AUDIT_STATUS             VARCHAR2(100),
  SELL_STATUS              VARCHAR2(100),
  MANGER_STATUS            VARCHAR2(100),
  PROC_STATUS              VARCHAR2(100),
  PRODUCT_DATE_ISSUED      DATE,
  FACTORY_PRICE            NUMBER(23,7),
  EXTERNAL_INFOR_LINK      VARCHAR2(100),
  SHORT_SPELL_NAME         VARCHAR2(100),
  GOODS_CODE               VARCHAR2(100),
  CREATOR_ID               VARCHAR2(36),
  CREATE_TIME              DATE,
  MODIFIER_ID              VARCHAR2(36),
  MODIFY_TIME              DATE,
  GOODS_ID                 VARCHAR2(36) not null,
  GOODS_BRAND_ID           VARCHAR2(36),
  PUR_CATEGORY_ID          VARCHAR2(36),
  NEW_DATA                 VARCHAR2(50),
  SUPPLIER_MODIFIER        VARCHAR2(36),
  REFER_PRICE              NUMBER(23,7),
  PARAM_INPUT_TYPE         CHAR(2),
  CURRENT_VALID_ID         VARCHAR2(50),
  ADDITION_PICTURE         VARCHAR2(36),
  VALID_TIME               DATE,
  INVALID_TIME             DATE,
  VERIFIER_ID              VARCHAR2(50),
  VERIFY_TIME              DATE,
  OPINION                  VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS
  is '商品信息';
-- Add comments to the columns 
comment on column GOODS.PRODUCT_NAME
  is '商品名称';
comment on column GOODS.PRODUCT_CODE
  is '商品型号';
comment on column GOODS.FUNCTION_INTRO
  is '功能描述';
comment on column GOODS.IS_CUSTOM
  is '是否自定义商品';
comment on column GOODS.MEASURE_UNIT
  is '计量单位，从数据字典列表选择，存入name，不存code';
comment on column GOODS.PICTURE_ID
  is '商品图片';
comment on column GOODS.SPEC
  is '规格说明（详细配置），文字形式描述的总体规格说明，详细质量指标的规格说明可以放到配置参数中描述。';
comment on column GOODS.GOODS_CLASS_ID
  is '商品分类';
comment on column GOODS.MADE_IN
  is '产地';
comment on column GOODS.FACTORY
  is '生产厂家：制造商';
comment on column GOODS.IS_ACCESSORY
  is '是否零配件';
comment on column GOODS.ENERGY_SAVING_PRODUCT_NO
  is '节能产品编号';
comment on column GOODS.ENVIRONMENT_LABEL
  is 'EnvironmentLabel 环境标志产品   是否"进入《环境标志产品政府采购清单》';
comment on column GOODS.CRYPTOGRAPHY_TECH_CODE
  is '如果进入《含有密码技术的信息产品政府采购清单》，则录入编号，否则为空';
comment on column GOODS.CREATION_CODE
  is '自主创新认定编号';
comment on column GOODS.SOLE_TO_SELL
  is '是否可单独出售';
comment on column GOODS.SPECIAL
  is '是否特供';
comment on column GOODS.REMARK
  is '备注';
comment on column GOODS.AUDIT_STATUS
  is '审核状态：00.草稿（默认） 01.待审核 02.通过 03.不通过';
comment on column GOODS.SELL_STATUS
  is '售卖状态：01.启卖 02.禁卖';
comment on column GOODS.MANGER_STATUS
  is '有效状态：[临时:00,有效:01,报废:02]';
comment on column GOODS.PRODUCT_DATE_ISSUED
  is '产品发布时间';
comment on column GOODS.EXTERNAL_INFOR_LINK
  is '外部信息链接';
comment on column GOODS.SHORT_SPELL_NAME
  is '拼音简码';
comment on column GOODS.GOODS_CODE
  is '商品编号，生成规则：商品分类编号+年（4位）+6位流水号';
comment on column GOODS.CREATOR_ID
  is '创建人';
comment on column GOODS.CREATE_TIME
  is '创建时间';
comment on column GOODS.MODIFIER_ID
  is '修改人 ';
comment on column GOODS.MODIFY_TIME
  is '修改时间';
comment on column GOODS.GOODS_ID
  is '商品参数';
comment on column GOODS.GOODS_BRAND_ID
  is '商品品牌';
comment on column GOODS.PUR_CATEGORY_ID
  is '商品品目';
comment on column GOODS.REFER_PRICE
  is '参考价';
comment on column GOODS.PARAM_INPUT_TYPE
  is '录入方式:分项录入、整体录入，两者任选 ';
comment on column GOODS.CURRENT_VALID_ID
  is '当前有效id';
comment on column GOODS.ADDITION_PICTURE
  is '附加图片';
comment on column GOODS.VALID_TIME
  is '生效时间';
comment on column GOODS.INVALID_TIME
  is '失效时间';
comment on column GOODS.VERIFIER_ID
  is '审核人';
comment on column GOODS.VERIFY_TIME
  is '审核时间';
comment on column GOODS.OPINION
  is '意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS
  add constraint PK_GOODS primary key (GOODS_ID)
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
create table GOODS_PARAM
(
  PARAM_NAME           VARCHAR2(800),
  SORT_NO              NUMBER(9),
  TYPE_NAME            VARCHAR2(100),
  PARAM_VALUE          VARCHAR2(800),
  IS_CUSTOM            VARCHAR2(5),
  PARAM_UNIT           VARCHAR2(100),
  CREATOR_ID           VARCHAR2(36),
  CREATE_TIME          DATE,
  MODIFIER_ID          VARCHAR2(36),
  MODIFY_TIME          DATE,
  GOODS_PARAM_ID       VARCHAR2(36) not null,
  GOODS_ID             VARCHAR2(36),
  GOODS_CLASS_PARAM_ID VARCHAR2(36)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 704K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_PARAM
  is '商品参数';
-- Add comments to the columns 
comment on column GOODS_PARAM.PARAM_NAME
  is '参数名称';
comment on column GOODS_PARAM.SORT_NO
  is '显示序号';
comment on column GOODS_PARAM.TYPE_NAME
  is '分类名称';
comment on column GOODS_PARAM.PARAM_VALUE
  is '参数值';
comment on column GOODS_PARAM.IS_CUSTOM
  is '是否商品自定义属性';
comment on column GOODS_PARAM.PARAM_UNIT
  is '参数值计量单位';
comment on column GOODS_PARAM.CREATOR_ID
  is '关联的外键  ';
comment on column GOODS_PARAM.CREATE_TIME
  is '创建时间';
comment on column GOODS_PARAM.MODIFIER_ID
  is '修改人';
comment on column GOODS_PARAM.MODIFY_TIME
  is '修改时间';
comment on column GOODS_PARAM.GOODS_PARAM_ID
  is '记录号';
comment on column GOODS_PARAM.GOODS_ID
  is '商品';
comment on column GOODS_PARAM.GOODS_CLASS_PARAM_ID
  is '商品分类参数';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_PARAM
  add constraint PK_GOODS_PARAM primary key (GOODS_PARAM_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 384K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table GOODS_CHANGE
(
  GOODS_CHANGE_ID VARCHAR2(50) not null,
  MODIFY_TYPE     VARCHAR2(50),
  OLD_VALUE       VARCHAR2(500),
  NEW_VALUE       VARCHAR2(500),
  MODIFIER        VARCHAR2(50),
  MODIFY_TIME     DATE,
  AUDIT_STATUS    VARCHAR2(2),
  VERIFY_TIME     DATE,
  OPINION         VARCHAR2(500),
  GOODS_ID        VARCHAR2(50),
  VERIFIER_ID     VARCHAR2(50),
  CREATOR         VARCHAR2(50),
  CREATE_TIME     DATE
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
comment on table GOODS_CHANGE
  is '商品变更历史';
-- Add comments to the columns 
comment on column GOODS_CHANGE.GOODS_CHANGE_ID
  is '主键';
comment on column GOODS_CHANGE.MODIFY_TYPE
  is '变更类型';
comment on column GOODS_CHANGE.OLD_VALUE
  is '旧值';
comment on column GOODS_CHANGE.NEW_VALUE
  is '新值';
comment on column GOODS_CHANGE.MODIFIER
  is '变更人';
comment on column GOODS_CHANGE.MODIFY_TIME
  is '变更时间';
comment on column GOODS_CHANGE.AUDIT_STATUS
  is '审核状态';
comment on column GOODS_CHANGE.VERIFY_TIME
  is '审核时间';
comment on column GOODS_CHANGE.OPINION
  is '审核意见';
comment on column GOODS_CHANGE.GOODS_ID
  is '机构ID';
comment on column GOODS_CHANGE.VERIFIER_ID
  is '审核人';
comment on column GOODS_CHANGE.CREATOR
  is '创建人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_CHANGE
  add constraint GOODS_CHANGE_KEY primary key (GOODS_CHANGE_ID)
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
create table GOODS_MODIFIER
(
  MODIFIER_ID       VARCHAR2(36),
  GOODS_CLASS_ID    VARCHAR2(36),
  GOODS_BRAND_ID    VARCHAR2(36),
  CREATOR_ID        VARCHAR2(36),
  CREATE_TIME       DATE,
  MODIFY_TIME       DATE,
  GOODS_MODIFIER_ID VARCHAR2(36) not null,
  SUPPLIER_ID       VARCHAR2(36)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 448K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GOODS_MODIFIER
  is '维护商';
-- Add comments to the columns 
comment on column GOODS_MODIFIER.MODIFIER_ID
  is '修改人';
comment on column GOODS_MODIFIER.GOODS_CLASS_ID
  is '商品分类列表';
comment on column GOODS_MODIFIER.GOODS_BRAND_ID
  is '品牌';
comment on column GOODS_MODIFIER.CREATOR_ID
  is '创建人';
comment on column GOODS_MODIFIER.CREATE_TIME
  is '创建时间';
comment on column GOODS_MODIFIER.MODIFY_TIME
  is '修改时间';
comment on column GOODS_MODIFIER.SUPPLIER_ID
  is '品维护商';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_MODIFIER
  add constraint PK_GOODS_MODIFIER primary key (GOODS_MODIFIER_ID)
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
create table GOODS_OPTIONAL_FITTING
(
  SORT_NO                   NUMBER(9),
  OPTION_CONTENT            VARCHAR2(100),
  SPECIFICATION             VARCHAR2(100),
  CREATOR_ID                VARCHAR2(36),
  CREATE_TIME               DATE,
  MODIFIER_ID               VARCHAR2(36),
  MODIFY_TIME               DATE,
  GOODS_OPTIONAL_FITTING_ID VARCHAR2(36) not null,
  GOODS_PARAM_ID            VARCHAR2(36),
  ISUSE                     VARCHAR2(2)
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
comment on table GOODS_OPTIONAL_FITTING
  is '商品选配';
-- Add comments to the columns 
comment on column GOODS_OPTIONAL_FITTING.SORT_NO
  is '顺序号';
comment on column GOODS_OPTIONAL_FITTING.OPTION_CONTENT
  is '选配内容 例如1G内存换为2G内存';
comment on column GOODS_OPTIONAL_FITTING.SPECIFICATION
  is '选配规格型号描述';
comment on column GOODS_OPTIONAL_FITTING.CREATOR_ID
  is '创建人';
comment on column GOODS_OPTIONAL_FITTING.CREATE_TIME
  is '创建时间';
comment on column GOODS_OPTIONAL_FITTING.MODIFIER_ID
  is '修改人';
comment on column GOODS_OPTIONAL_FITTING.MODIFY_TIME
  is '修改时间';
comment on column GOODS_OPTIONAL_FITTING.GOODS_PARAM_ID
  is '商品参数';
comment on column GOODS_OPTIONAL_FITTING.ISUSE
  is '是否可用[01:可用 02:不可用]';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_OPTIONAL_FITTING
  add constraint PK_GOODS_OPTIONAL_FITTING primary key (GOODS_OPTIONAL_FITTING_ID)
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
create table GOODS_ACCESSORIES
(
  GOODS_ACCESSORIES_ID VARCHAR2(36) not null,
  GOODS_ID             VARCHAR2(36),
  ACCESSORYGOODS_ID    VARCHAR2(36),
  ACCESSORY_QTY        NUMBER,
  IS_OFF               CHAR(1),
  CREATOR              VARCHAR2(36),
  CREATE_TIME          DATE,
  MODIFIER             VARCHAR2(36),
  MODIFY_TIME          DATE
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
comment on column GOODS_ACCESSORIES.GOODS_ACCESSORIES_ID
  is '主键';
comment on column GOODS_ACCESSORIES.GOODS_ID
  is '商品ID';
comment on column GOODS_ACCESSORIES.ACCESSORYGOODS_ID
  is '配件商品ID';
comment on column GOODS_ACCESSORIES.ACCESSORY_QTY
  is '配件数量';
comment on column GOODS_ACCESSORIES.IS_OFF
  is '是否启用[2.禁用 1.启用]';
comment on column GOODS_ACCESSORIES.CREATOR
  is ' 创建人';
comment on column GOODS_ACCESSORIES.CREATE_TIME
  is '创建时间';
comment on column GOODS_ACCESSORIES.MODIFIER
  is ' 修改人';
comment on column GOODS_ACCESSORIES.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_ACCESSORIES
  add primary key (GOODS_ACCESSORIES_ID)
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
create table GOODS_GIFT
(
  GOODS_GIFT_ID VARCHAR2(50) not null,
  GOODS_ID      VARCHAR2(50),
  SUPPLIER_ID   VARCHAR2(50),
  GIFT_NAME     VARCHAR2(100),
  GIFT_DESC     VARCHAR2(500),
  GIFT_PICTURE  VARCHAR2(50),
  CREATOR_ID    VARCHAR2(50),
  CREATE_TIME   DATE,
  MODIFIER_ID   VARCHAR2(50),
  MODIFY_TIME   DATE
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
comment on table GOODS_GIFT
  is '商品优惠礼包';
-- Add comments to the columns 
comment on column GOODS_GIFT.GOODS_GIFT_ID
  is '主键';
comment on column GOODS_GIFT.GOODS_ID
  is '商品id';
comment on column GOODS_GIFT.SUPPLIER_ID
  is '供应商id';
comment on column GOODS_GIFT.GIFT_NAME
  is '优惠品名称';
comment on column GOODS_GIFT.GIFT_DESC
  is '优惠品描述';
comment on column GOODS_GIFT.GIFT_PICTURE
  is '优惠品图片';
comment on column GOODS_GIFT.CREATOR_ID
  is '创建人ID';
comment on column GOODS_GIFT.CREATE_TIME
  is '创建时间';
comment on column GOODS_GIFT.MODIFIER_ID
  is '修改人ID';
comment on column GOODS_GIFT.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_GIFT
  add primary key (GOODS_GIFT_ID)
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
create table GOODS_PRICE_SUPPLIER
(
  CREATOR                 VARCHAR2(36),
  CREATE_TIME             TIMESTAMP(6),
  MODIFIER                VARCHAR2(36),
  MODIFY_TIME             TIMESTAMP(6),
  SUPPLIER_ID             VARCHAR2(36),
  GOODS_ID                VARCHAR2(36),
  IS_PROTOCOL             CHAR(1),
  IS_SHOW                 CHAR(1),
  GOODS_PRICE_SUPPLIER_ID VARCHAR2(36) not null
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
comment on table GOODS_PRICE_SUPPLIER
  is '行情供应商';
-- Add comments to the columns 
comment on column GOODS_PRICE_SUPPLIER.CREATOR
  is '创建人';
comment on column GOODS_PRICE_SUPPLIER.CREATE_TIME
  is '创建时间';
comment on column GOODS_PRICE_SUPPLIER.MODIFIER
  is ' 修改人';
comment on column GOODS_PRICE_SUPPLIER.MODIFY_TIME
  is '修改时间';
comment on column GOODS_PRICE_SUPPLIER.SUPPLIER_ID
  is '记录号';
comment on column GOODS_PRICE_SUPPLIER.GOODS_ID
  is '商品';
comment on column GOODS_PRICE_SUPPLIER.IS_PROTOCOL
  is '是否是协议供应商（0.否 1.是）';
comment on column GOODS_PRICE_SUPPLIER.IS_SHOW
  is '是否显示（0.否 1.是）';
comment on column GOODS_PRICE_SUPPLIER.GOODS_PRICE_SUPPLIER_ID
  is '记录号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_PRICE_SUPPLIER
  add constraint PK_GOODS_PRICE_MODIFIER primary key (GOODS_PRICE_SUPPLIER_ID)
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
create table GOODS_PRICE
(
  CREATOR           VARCHAR2(100),
  CREATE_TIME       TIMESTAMP(6),
  MODIFIER          VARCHAR2(100),
  MODIFY_TIME       TIMESTAMP(6),
  MRKT_UNIT_PRICE   NUMBER(23,7),
  DSCU_RATE         NUMBER(23,7),
  PRTC_PRICE        NUMBER(23,7),
  EFCT_DATE         DATE,
  END_DATE          DATE,
  DISTRICT_ID       VARCHAR2(36),
  REMARKS           VARCHAR2(2000),
  PRICE_SUPPLIER_ID VARCHAR2(36),
  USE_STATUS        CHAR(2),
  GOODS_PRICE_ID    VARCHAR2(36) not null,
  AUDIT_STAUTS      CHAR(2),
  AUDITOR           VARCHAR2(36),
  AUDIT_OPINION     VARCHAR2(2000),
  AUDIT_TIME        TIMESTAMP(6),
  CURRENT_ID        VARCHAR2(36),
  MEMBER_PRICE      NUMBER(23,7)
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
comment on table GOODS_PRICE
  is '商品行情';
-- Add comments to the columns 
comment on column GOODS_PRICE.CREATOR
  is ' 创建人';
comment on column GOODS_PRICE.CREATE_TIME
  is '创建时间';
comment on column GOODS_PRICE.MODIFIER
  is '修改人';
comment on column GOODS_PRICE.MODIFY_TIME
  is '修改时间';
comment on column GOODS_PRICE.MRKT_UNIT_PRICE
  is '市场价';
comment on column GOODS_PRICE.DSCU_RATE
  is '折扣率';
comment on column GOODS_PRICE.PRTC_PRICE
  is '协议价';
comment on column GOODS_PRICE.EFCT_DATE
  is '生效日期';
comment on column GOODS_PRICE.END_DATE
  is '失效日期';
comment on column GOODS_PRICE.DISTRICT_ID
  is '所属区域';
comment on column GOODS_PRICE.REMARKS
  is '备注';
comment on column GOODS_PRICE.PRICE_SUPPLIER_ID
  is '行情维护商id';
comment on column GOODS_PRICE.USE_STATUS
  is '使用状态';
comment on column GOODS_PRICE.GOODS_PRICE_ID
  is '主键';
comment on column GOODS_PRICE.AUDIT_STAUTS
  is '审核状态';
comment on column GOODS_PRICE.AUDITOR
  is '审核人';
comment on column GOODS_PRICE.AUDIT_OPINION
  is '审核意见';
comment on column GOODS_PRICE.AUDIT_TIME
  is '审核时间';
comment on column GOODS_PRICE.CURRENT_ID
  is '当前有效id';
comment on column GOODS_PRICE.MEMBER_PRICE
  is '会员价';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_PRICE
  add constraint PK_GOODS_PRICE primary key (GOODS_PRICE_ID)
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
create table GOODS_OPT_FIT_PRICE
(
  RELATIVE_PRICE         NUMBER(23,7),
  GOODS_OPT_FIT_ID       VARCHAR2(36),
  CREATOR                VARCHAR2(100),
  CREATE_TIME            TIMESTAMP(6),
  MODIFIER               VARCHAR2(100),
  MODIFY_TIME            TIMESTAMP(6),
  GOODS_OPT_FIT_PRICE_ID VARCHAR2(36) not null,
  GOODS_PRICE_ID         VARCHAR2(36)
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
comment on table GOODS_OPT_FIT_PRICE
  is '选配行情';
-- Add comments to the columns 
comment on column GOODS_OPT_FIT_PRICE.RELATIVE_PRICE
  is '相对价格';
comment on column GOODS_OPT_FIT_PRICE.GOODS_OPT_FIT_ID
  is '选配';
comment on column GOODS_OPT_FIT_PRICE.CREATOR
  is '创建人';
comment on column GOODS_OPT_FIT_PRICE.CREATE_TIME
  is '创建时间';
comment on column GOODS_OPT_FIT_PRICE.MODIFIER
  is '修改人';
comment on column GOODS_OPT_FIT_PRICE.MODIFY_TIME
  is '修改时间';
comment on column GOODS_OPT_FIT_PRICE.GOODS_OPT_FIT_PRICE_ID
  is '主键';
comment on column GOODS_OPT_FIT_PRICE.GOODS_PRICE_ID
  is '行情id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_OPT_FIT_PRICE
  add constraint PK_GOODS_OPT_FIT_PRICE primary key (GOODS_OPT_FIT_PRICE_ID)
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
create table GOODS_ACCESSORIES_PRICE
(
  GOODS_ACCESSORIES_PRICE_ID VARCHAR2(36) not null,
  GOODS_ACCESSORIES_ID       VARCHAR2(36),
  SUPPLIER_ID                VARCHAR2(36),
  GOODS_PRICE_ID             VARCHAR2(36),
  ACCESSORIES_PRICE          NUMBER(23,7),
  CREATOR                    VARCHAR2(36),
  CREATE_TIME                DATE,
  MODIFIER                   VARCHAR2(36),
  MODIFY_TIME                DATE
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
comment on column GOODS_ACCESSORIES_PRICE.GOODS_ACCESSORIES_PRICE_ID
  is '主键';
comment on column GOODS_ACCESSORIES_PRICE.GOODS_ACCESSORIES_ID
  is '零配件ID';
comment on column GOODS_ACCESSORIES_PRICE.SUPPLIER_ID
  is '供应商ID';
comment on column GOODS_ACCESSORIES_PRICE.GOODS_PRICE_ID
  is '行情ID';
comment on column GOODS_ACCESSORIES_PRICE.ACCESSORIES_PRICE
  is '价格';
comment on column GOODS_ACCESSORIES_PRICE.CREATOR
  is ' 创建人';
comment on column GOODS_ACCESSORIES_PRICE.CREATE_TIME
  is '创建时间';
comment on column GOODS_ACCESSORIES_PRICE.MODIFIER
  is ' 修改人';
comment on column GOODS_ACCESSORIES_PRICE.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_ACCESSORIES_PRICE
  add primary key (GOODS_ACCESSORIES_PRICE_ID)
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
create table GOODS_GIFT_PRICE
(
  GOODS_GIFT_PRICE_ID VARCHAR2(50) not null,
  GOODS_GIFT_ID       VARCHAR2(50),
  PRICE_SUPPLIER_ID   VARCHAR2(50),
  GOODS_PRICE_ID      VARCHAR2(50),
  GIFT_PRICE          NUMBER(23,7),
  CREATOR_ID          VARCHAR2(50),
  CREATE_TIME         DATE,
  MODIFIER_ID         VARCHAR2(50),
  MODIFY_TIME         DATE
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
comment on table GOODS_GIFT_PRICE
  is '商品礼包价格';
-- Add comments to the columns 
comment on column GOODS_GIFT_PRICE.GOODS_GIFT_PRICE_ID
  is '主键';
comment on column GOODS_GIFT_PRICE.GOODS_GIFT_ID
  is '商品礼包id';
comment on column GOODS_GIFT_PRICE.PRICE_SUPPLIER_ID
  is '行情供应商id';
comment on column GOODS_GIFT_PRICE.GOODS_PRICE_ID
  is '商品行情id';
comment on column GOODS_GIFT_PRICE.GIFT_PRICE
  is '礼包价格';
comment on column GOODS_GIFT_PRICE.CREATOR_ID
  is '创建人id';
comment on column GOODS_GIFT_PRICE.CREATE_TIME
  is '创建时间';
comment on column GOODS_GIFT_PRICE.MODIFIER_ID
  is '修改人id';
comment on column GOODS_GIFT_PRICE.MODIFY_TIME
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_GIFT_PRICE
  add primary key (GOODS_GIFT_PRICE_ID)
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
create table GOODS_EVALUATE
(
  GOODS_EVALUATE_ID     VARCHAR2(50) not null,
  GOODS_ID              VARCHAR2(50),
  GOODS_EVALUATE_LEVEL  NUMBER,
  GOODS_EVALUATE_REMARK VARCHAR2(1000),
  CREATOR_ID            VARCHAR2(50),
  CREATE_TIME           DATE,
  GOODS_EVALUATE_TITEL  VARCHAR2(100)
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
comment on table GOODS_EVALUATE
  is '商品评价';
-- Add comments to the columns 
comment on column GOODS_EVALUATE.GOODS_EVALUATE_ID
  is '主键';
comment on column GOODS_EVALUATE.GOODS_ID
  is '商品';
comment on column GOODS_EVALUATE.GOODS_EVALUATE_LEVEL
  is '评价级别';
comment on column GOODS_EVALUATE.GOODS_EVALUATE_REMARK
  is '描述';
comment on column GOODS_EVALUATE.CREATOR_ID
  is '创建人';
comment on column GOODS_EVALUATE.CREATE_TIME
  is '创建时间';
comment on column GOODS_EVALUATE.GOODS_EVALUATE_TITEL
  is '标题';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GOODS_EVALUATE
  add constraint PK_GOODS_EVALUATE_ID primary key (GOODS_EVALUATE_ID)
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
create table EXPERT_TRAINING
(
  TRAINING_ID     VARCHAR2(50) not null,
  EXPERT_ID       VARCHAR2(50),
  TRAINING_COURSE VARCHAR2(100),
  TRAINING_ORG    VARCHAR2(100),
  BEGIN_DATE      DATE,
  END_DATE        DATE,
  COURSE_MEMO     VARCHAR2(2000),
  FILE_ID         VARCHAR2(50),
  AUDIT_STATUS    CHAR(2)
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
comment on table EXPERT_TRAINING
  is 'EXPERT_TRAINING 培训信息   ';
-- Add comments to the columns 
comment on column EXPERT_TRAINING.EXPERT_ID
  is '专家     ';
comment on column EXPERT_TRAINING.TRAINING_COURSE
  is '培训课程     ';
comment on column EXPERT_TRAINING.TRAINING_ORG
  is '培训机构     ';
comment on column EXPERT_TRAINING.BEGIN_DATE
  is '开始时间     ';
comment on column EXPERT_TRAINING.END_DATE
  is '结束时间';
comment on column EXPERT_TRAINING.COURSE_MEMO
  is '课程介绍     ';
comment on column EXPERT_TRAINING.FILE_ID
  is '证书附件     ';
comment on column EXPERT_TRAINING.AUDIT_STATUS
  is '审核状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXPERT_TRAINING
  add constraint PK_CERTIFICATION primary key (TRAINING_ID)
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
create table EXPERT_INFO_APPLY
(
  EXPERT_INFO_APPLY_ID VARCHAR2(50) not null,
  EXPERT_INFO_ID       VARCHAR2(50),
  APPLY_TYPE           VARCHAR2(100),
  CREATOR              VARCHAR2(50),
  CREATE_TIME          DATE,
  APPLIER              VARCHAR2(50),
  APPLY_TIME           DATE,
  AUDIT_STATUS         CHAR(2),
  VERIFIER_ID          VARCHAR2(50),
  VERIFY_TIME          DATE,
  OPINION              VARCHAR2(500)
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
comment on table EXPERT_INFO_APPLY
  is '专家申请';
-- Add comments to the columns 
comment on column EXPERT_INFO_APPLY.EXPERT_INFO_APPLY_ID
  is '主键';
comment on column EXPERT_INFO_APPLY.EXPERT_INFO_ID
  is '专家id';
comment on column EXPERT_INFO_APPLY.APPLY_TYPE
  is '申请类型';
comment on column EXPERT_INFO_APPLY.CREATOR
  is '创建人id';
comment on column EXPERT_INFO_APPLY.CREATE_TIME
  is '创建时间';
comment on column EXPERT_INFO_APPLY.APPLIER
  is '申请人id';
comment on column EXPERT_INFO_APPLY.APPLY_TIME
  is '申请时间';
comment on column EXPERT_INFO_APPLY.AUDIT_STATUS
  is '审核状态';
comment on column EXPERT_INFO_APPLY.VERIFIER_ID
  is '审核人id';
comment on column EXPERT_INFO_APPLY.VERIFY_TIME
  is '审核时间';
comment on column EXPERT_INFO_APPLY.OPINION
  is '审核意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXPERT_INFO_APPLY
  add constraint EXPERT_INFO_APPLY_PK primary key (EXPERT_INFO_APPLY_ID)
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
create table EXPERT_INFO
(
  EXPERT_ID                      VARCHAR2(50) not null,
  USER_ID                        VARCHAR2(50),
  NAME                           VARCHAR2(50),
  BELONG_INDUSTRY                VARCHAR2(50),
  IS_RETIRED                     CHAR(2),
  DISTRICT_ID                    VARCHAR2(50),
  APP_DISTRICT_VALUE             VARCHAR2(500),
  APP_CATEGORY_VALUE             VARCHAR2(500),
  POLITICAL_LANDSCAPE            CHAR(2),
  PROFESSION_QUALIFICATION_LEVEL CHAR(2),
  BIRTHDAY                       DATE,
  PHOTO_ID                       VARCHAR2(36),
  SPECIFY_YEAR                   CHAR(2),
  TECHNICAL_EXCELLENCE           VARCHAR2(2000),
  TENDER_EXPERIENCE              NVARCHAR2(2000),
  HONOR_FILE                     VARCHAR2(50),
  CREATOR_ID                     VARCHAR2(36),
  CREATE_TIME                    DATE,
  MODIFER_ID                     VARCHAR2(36),
  MODIFY_TIME                    DATE,
  USE_STATUS                     CHAR(2),
  AUDIT_STATUS                   CHAR(2),
  VALID_DATE                     DATE,
  IS_OFF                         VARCHAR2(2),
  IS_CONSULTANT                  CHAR(1),
  IS_REVIEWERS                   CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EXPERT_INFO
  is '专家基本信息 EXPERT_INFO   ';
-- Add comments to the columns 
comment on column EXPERT_INFO.USER_ID
  is '专家对应系统用户帐号     ';
comment on column EXPERT_INFO.NAME
  is '专家姓名     ';
comment on column EXPERT_INFO.BELONG_INDUSTRY
  is '所属行业    ';
comment on column EXPERT_INFO.IS_RETIRED
  is '是否退休:N---未退休；Y;已退休     ';
comment on column EXPERT_INFO.DISTRICT_ID
  is '所属地区     ';
comment on column EXPERT_INFO.APP_DISTRICT_VALUE
  is '评审区域     ';
comment on column EXPERT_INFO.APP_CATEGORY_VALUE
  is '评审品目     ';
comment on column EXPERT_INFO.POLITICAL_LANDSCAPE
  is '政治面貌: 枚举      ';
comment on column EXPERT_INFO.PROFESSION_QUALIFICATION_LEVEL
  is '国家职业资格等级,枚举      ';
comment on column EXPERT_INFO.BIRTHDAY
  is '出生年月     ';
comment on column EXPERT_INFO.PHOTO_ID
  is '照片     ';
comment on column EXPERT_INFO.SPECIFY_YEAR
  is '从事特长年限';
comment on column EXPERT_INFO.TECHNICAL_EXCELLENCE
  is '特长描述     ';
comment on column EXPERT_INFO.TENDER_EXPERIENCE
  is '经验描述';
comment on column EXPERT_INFO.HONOR_FILE
  is '荣誉证书，多附件';
comment on column EXPERT_INFO.CREATOR_ID
  is '创建人     ';
comment on column EXPERT_INFO.CREATE_TIME
  is '创建时间     ';
comment on column EXPERT_INFO.MODIFER_ID
  is '修改人     ';
comment on column EXPERT_INFO.MODIFY_TIME
  is '修改时间     ';
comment on column EXPERT_INFO.USE_STATUS
  is '使用状态          ';
comment on column EXPERT_INFO.AUDIT_STATUS
  is '审核状态          ';
comment on column EXPERT_INFO.VALID_DATE
  is '生效时间          ';
comment on column EXPERT_INFO.IS_OFF
  is '启用状态          ';
comment on column EXPERT_INFO.IS_CONSULTANT
  is '是否为咨询员【0:不是 1:是】';
comment on column EXPERT_INFO.IS_REVIEWERS
  is '是否为评审员【0:不是 1:是】';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXPERT_INFO
  add constraint PK_EXPERT primary key (EXPERT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 320K
    minextents 1
    maxextents unlimited
  );

  -- Create table
create table EXPERT_EXPERIENCE
(
  EXPERIENCE_ID VARCHAR2(50) not null,
  EXPERT_ID     VARCHAR2(50),
  ORG_NAME      VARCHAR2(100),
  SPECIALTY     VARCHAR2(100),
  DUTY          VARCHAR2(100),
  START_TIME    DATE,
  END_TIME      DATE,
  ACHIEVEMENT   VARCHAR2(2000),
  AUDIT_STATUS  CHAR(2)
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
comment on table EXPERT_EXPERIENCE
  is 'EXPERT_EXPERIENCE   任职经历   ';
-- Add comments to the columns 
comment on column EXPERT_EXPERIENCE.EXPERT_ID
  is '专家     ';
comment on column EXPERT_EXPERIENCE.ORG_NAME
  is '工作单位';
comment on column EXPERT_EXPERIENCE.SPECIALTY
  is '职业     ';
comment on column EXPERT_EXPERIENCE.DUTY
  is '职务     ';
comment on column EXPERT_EXPERIENCE.START_TIME
  is '开始时间     ';
comment on column EXPERT_EXPERIENCE.END_TIME
  is '结束时间     ';
comment on column EXPERT_EXPERIENCE.AUDIT_STATUS
  is '审核状态      ';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXPERT_EXPERIENCE
  add constraint PK_EXPERIENCE_INFO primary key (EXPERIENCE_ID)
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
create table EXPERT_EDUCATION
(
  EXPERT_EDUCATION_ID VARCHAR2(50) not null,
  EXPERT_ID           VARCHAR2(50),
  GRADUATE_SCHOOL     VARCHAR2(100),
  ENROLL_DATE         DATE,
  GRADUATE_DATE       DATE,
  SPECIALITY          VARCHAR2(100),
  DEGREE              VARCHAR2(100),
  FILE_ID             VARCHAR2(50),
  AUDIT_STATUS        CHAR(2)
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
comment on table EXPERT_EDUCATION
  is 'EXPERT_EDUCATION   专家教育经历   ';
-- Add comments to the columns 
comment on column EXPERT_EDUCATION.EXPERT_ID
  is '专家     ';
comment on column EXPERT_EDUCATION.GRADUATE_SCHOOL
  is '毕业院校     ';
comment on column EXPERT_EDUCATION.ENROLL_DATE
  is '入学时间     ';
comment on column EXPERT_EDUCATION.GRADUATE_DATE
  is '毕业时间     ';
comment on column EXPERT_EDUCATION.SPECIALITY
  is '所学专业     ';
comment on column EXPERT_EDUCATION.DEGREE
  is '学历     ';
comment on column EXPERT_EDUCATION.FILE_ID
  is '证明文件     ';
comment on column EXPERT_EDUCATION.AUDIT_STATUS
  is '审核状态     ';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXPERT_EDUCATION
  add constraint PK_EXPERT_EDUCATION primary key (EXPERT_EDUCATION_ID)
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
create table EXPERT_CERTIFICATE
(
  CERTIFICATE_ID VARCHAR2(50) not null,
  EXPERT_ID      VARCHAR2(50),
  TITLE_NAME     VARCHAR2(50),
  CERTIFICATE_NO VARCHAR2(50),
  ISSUE_UNIT     VARCHAR2(100),
  ACQUIRE_TIME   DATE,
  VAL_DATE       DATE,
  FILE_ID        VARCHAR2(50),
  AUDIT_STATUS   CHAR(2)
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
comment on table EXPERT_CERTIFICATE
  is 'ERIS_TITLE_TYPE   职称管理   ';
-- Add comments to the columns 
comment on column EXPERT_CERTIFICATE.EXPERT_ID
  is '专家ID     ';
comment on column EXPERT_CERTIFICATE.TITLE_NAME
  is '职称信息     ';
comment on column EXPERT_CERTIFICATE.CERTIFICATE_NO
  is '证书编号     ';
comment on column EXPERT_CERTIFICATE.ISSUE_UNIT
  is '颁发机构     ';
comment on column EXPERT_CERTIFICATE.ACQUIRE_TIME
  is '获得证书时间     ';
comment on column EXPERT_CERTIFICATE.FILE_ID
  is '证书附件     ';
comment on column EXPERT_CERTIFICATE.AUDIT_STATUS
  is '审核状态     ';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXPERT_CERTIFICATE
  add constraint PK_TECHNOLOGY_TITLE_TYPE primary key (CERTIFICATE_ID)
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
create table SERVE_RECOMMEND_HOTEL
(
  RECOMMEND_HOTEL_ID VARCHAR2(50) not null,
  HOTEL_ID           VARCHAR2(50),
  RECOMMENDER        VARCHAR2(50),
  REASON             VARCHAR2(1000),
  AUDIT_STATUS       CHAR(2),
  USE_STATUS         CHAR(1),
  CREATE_TIME        DATE,
  SORT               NUMBER(6)
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
comment on table SERVE_RECOMMEND_HOTEL
  is '推荐酒店';
-- Add comments to the columns 
comment on column SERVE_RECOMMEND_HOTEL.RECOMMEND_HOTEL_ID
  is '主键';
comment on column SERVE_RECOMMEND_HOTEL.HOTEL_ID
  is '推荐的酒店id';
comment on column SERVE_RECOMMEND_HOTEL.RECOMMENDER
  is '推荐人id';
comment on column SERVE_RECOMMEND_HOTEL.REASON
  is '推荐理由';
comment on column SERVE_RECOMMEND_HOTEL.AUDIT_STATUS
  is '审核状态：(00:待审核 01:通过 02:不通过)';
comment on column SERVE_RECOMMEND_HOTEL.USE_STATUS
  is '有效状态：（0:无效 1:有效）';
comment on column SERVE_RECOMMEND_HOTEL.CREATE_TIME
  is '创建时间';
comment on column SERVE_RECOMMEND_HOTEL.SORT
  is '序号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVE_RECOMMEND_HOTEL
  add constraint PK_SERVE_RECOMMEND_HOTEL primary key (RECOMMEND_HOTEL_ID)
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
create table SERVE_HOTEL_EVLAUATE
(
  HOTEL_EVALUATE_ID     VARCHAR2(50) not null,
  HOTEL_ID              VARCHAR2(50),
  HOTEL_EVALUATE_LEVEL  NUMBER,
  HOTEL_EVALUATE_REMARK VARCHAR2(1000),
  CREATOR_ID            VARCHAR2(50),
  CREATE_TIME           DATE,
  HOTEL_EVALUATE_TITEL  VARCHAR2(100),
  CREATOR_NAME          VARCHAR2(100)
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
comment on table SERVE_HOTEL_EVLAUATE
  is '酒店评价';
-- Add comments to the columns 
comment on column SERVE_HOTEL_EVLAUATE.HOTEL_EVALUATE_ID
  is '主键';
comment on column SERVE_HOTEL_EVLAUATE.HOTEL_ID
  is '酒店';
comment on column SERVE_HOTEL_EVLAUATE.HOTEL_EVALUATE_LEVEL
  is '评价级别';
comment on column SERVE_HOTEL_EVLAUATE.HOTEL_EVALUATE_REMARK
  is '描述';
comment on column SERVE_HOTEL_EVLAUATE.CREATOR_ID
  is '创建人';
comment on column SERVE_HOTEL_EVLAUATE.CREATE_TIME
  is '创建时间';
comment on column SERVE_HOTEL_EVLAUATE.HOTEL_EVALUATE_TITEL
  is '标题';
comment on column SERVE_HOTEL_EVLAUATE.CREATOR_NAME
  is '创建人名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVE_HOTEL_EVLAUATE
  add constraint PK_HOTEL_EVALUATE_ID primary key (HOTEL_EVALUATE_ID)
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
create table SERVE_HOTEL
(
  HOTEL_ID             VARCHAR2(50) not null,
  HOTEL_CODE           VARCHAR2(100),
  ORG_INFO_ID          VARCHAR2(50),
  HOTEL_NAME           VARCHAR2(500),
  HOTEL_ADDRESS        VARCHAR2(500),
  START_TIME           DATE,
  DISTRICT_ID          VARCHAR2(50),
  STAR                 CHAR(2),
  SURROUNDINGS         VARCHAR2(500),
  HOTEL_DESC           VARCHAR2(2000),
  HOTEL_DETAIL         VARCHAR2(4000),
  PICTURE_ID           VARCHAR2(50),
  ADDITION_PICTURE     VARCHAR2(500),
  SERVICE_ITEMS        VARCHAR2(500),
  FOOD_FACILITIES      VARCHAR2(500),
  FUN_FACILITIES       VARCHAR2(500),
  GUESTROOM_FACILITIES VARCHAR2(500),
  CREDIT_CARD_TYPE     VARCHAR2(500),
  CREATOR_ID           VARCHAR2(50),
  CREATE_TIME          DATE,
  MODIFY_TIME          DATE,
  MODIFIER_ID          VARCHAR2(50),
  AUDIT_STATUS         CHAR(2),
  USE_STATUS           CHAR(2),
  IS_OFF               CHAR(1),
  GUEST_ROOM_TYPE      VARCHAR2(500),
  MEETING_ROOM_TYPE    VARCHAR2(500),
  CONTACT              VARCHAR2(50),
  FAX                  VARCHAR2(50)
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
comment on table SERVE_HOTEL
  is '酒店';
-- Add comments to the columns 
comment on column SERVE_HOTEL.HOTEL_ID
  is '主键';
comment on column SERVE_HOTEL.HOTEL_CODE
  is '编号';
comment on column SERVE_HOTEL.ORG_INFO_ID
  is '所属机构';
comment on column SERVE_HOTEL.HOTEL_NAME
  is '名称';
comment on column SERVE_HOTEL.HOTEL_ADDRESS
  is '地址';
comment on column SERVE_HOTEL.START_TIME
  is '开业时间';
comment on column SERVE_HOTEL.DISTRICT_ID
  is '区域';
comment on column SERVE_HOTEL.STAR
  is '星级';
comment on column SERVE_HOTEL.SURROUNDINGS
  is '周围环境';
comment on column SERVE_HOTEL.HOTEL_DESC
  is '酒店概括';
comment on column SERVE_HOTEL.HOTEL_DETAIL
  is '详细介绍';
comment on column SERVE_HOTEL.PICTURE_ID
  is '主图';
comment on column SERVE_HOTEL.ADDITION_PICTURE
  is '附图';
comment on column SERVE_HOTEL.SERVICE_ITEMS
  is '酒店服务项目';
comment on column SERVE_HOTEL.FOOD_FACILITIES
  is '餐饮设施';
comment on column SERVE_HOTEL.FUN_FACILITIES
  is '娱乐设施';
comment on column SERVE_HOTEL.GUESTROOM_FACILITIES
  is '客房设施和服务';
comment on column SERVE_HOTEL.CREDIT_CARD_TYPE
  is '可接受信用卡类型';
comment on column SERVE_HOTEL.CREATOR_ID
  is '创建人';
comment on column SERVE_HOTEL.CREATE_TIME
  is '创建时间';
comment on column SERVE_HOTEL.MODIFY_TIME
  is '修改时间';
comment on column SERVE_HOTEL.MODIFIER_ID
  is '修改人';
comment on column SERVE_HOTEL.AUDIT_STATUS
  is '审核状态';
comment on column SERVE_HOTEL.USE_STATUS
  is '使用状态';
comment on column SERVE_HOTEL.IS_OFF
  is '是否启用';
comment on column SERVE_HOTEL.GUEST_ROOM_TYPE
  is '客房冗余字段';
comment on column SERVE_HOTEL.MEETING_ROOM_TYPE
  is '会议室冗余字段';
comment on column SERVE_HOTEL.CONTACT
  is '联系电话';
comment on column SERVE_HOTEL.FAX
  is '传真';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVE_HOTEL
  add constraint PK_HOTEL_ID primary key (HOTEL_ID)
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
create table SERVE_GUESTROOM_PRICE
(
  GUESTROOM_PRICE_ID VARCHAR2(50) not null,
  GUESTROOM_ID       VARCHAR2(50),
  START_TIME         DATE,
  END_TIME           DATE,
  AGREE_PRICE        NUMBER(16,6),
  HAS_BREAKFAST      CHAR(1),
  BREAKFAST_NUM      NUMBER(3),
  REMARK             VARCHAR2(1000),
  CREATOR_ID         VARCHAR2(50),
  CREATE_TIME        DATE,
  MODIFY_TIME        DATE,
  MODIFIER_ID        VARCHAR2(50)
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
comment on table SERVE_GUESTROOM_PRICE
  is '客房价格';
-- Add comments to the columns 
comment on column SERVE_GUESTROOM_PRICE.GUESTROOM_PRICE_ID
  is '主键';
comment on column SERVE_GUESTROOM_PRICE.GUESTROOM_ID
  is '客房id';
comment on column SERVE_GUESTROOM_PRICE.START_TIME
  is '开始时间';
comment on column SERVE_GUESTROOM_PRICE.END_TIME
  is '结束时间';
comment on column SERVE_GUESTROOM_PRICE.AGREE_PRICE
  is '协议价';
comment on column SERVE_GUESTROOM_PRICE.HAS_BREAKFAST
  is '是否含早餐 ';
comment on column SERVE_GUESTROOM_PRICE.BREAKFAST_NUM
  is '早餐数量';
comment on column SERVE_GUESTROOM_PRICE.REMARK
  is '备注';
comment on column SERVE_GUESTROOM_PRICE.CREATOR_ID
  is '创建人';
comment on column SERVE_GUESTROOM_PRICE.CREATE_TIME
  is '创建时间';
comment on column SERVE_GUESTROOM_PRICE.MODIFY_TIME
  is '修改时间';
comment on column SERVE_GUESTROOM_PRICE.MODIFIER_ID
  is '修改人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVE_GUESTROOM_PRICE
  add constraint PK_GUESTROOM_PRICE_ID primary key (GUESTROOM_PRICE_ID)
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
create table SERVE_GUESTROOM
(
  GUESTROOM_ID   VARCHAR2(50) not null,
  GUESTROOM_CODE VARCHAR2(100),
  HOTEL_ID       VARCHAR2(50),
  TYPE           CHAR(2),
  AREA           NUMBER(16,6),
  RETAIL_PRICE   NUMBER(16,6),
  PICTURE_ID     VARCHAR2(50),
  BED_TYPE       CHAR(2),
  BREAKFAST_TYPE CHAR(2),
  FLOOR          VARCHAR2(50),
  BROADBAND      VARCHAR2(200),
  GUESTROOM_DESC VARCHAR2(1000),
  GUESTROOM_NUM  NUMBER(3),
  CREATOR_ID     VARCHAR2(50),
  CREATE_TIME    DATE,
  MODIFY_TIME    DATE,
  MODIFIER_ID    VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SERVE_GUESTROOM
  is '客房';
-- Add comments to the columns 
comment on column SERVE_GUESTROOM.GUESTROOM_ID
  is '主键';
comment on column SERVE_GUESTROOM.GUESTROOM_CODE
  is '编号';
comment on column SERVE_GUESTROOM.HOTEL_ID
  is '酒店id';
comment on column SERVE_GUESTROOM.TYPE
  is '客房类型';
comment on column SERVE_GUESTROOM.AREA
  is '面积';
comment on column SERVE_GUESTROOM.RETAIL_PRICE
  is '门市价';
comment on column SERVE_GUESTROOM.PICTURE_ID
  is '图片';
comment on column SERVE_GUESTROOM.BED_TYPE
  is '床型';
comment on column SERVE_GUESTROOM.BREAKFAST_TYPE
  is '早餐类型';
comment on column SERVE_GUESTROOM.FLOOR
  is '楼层';
comment on column SERVE_GUESTROOM.BROADBAND
  is '宽带';
comment on column SERVE_GUESTROOM.GUESTROOM_DESC
  is '房间描述';
comment on column SERVE_GUESTROOM.GUESTROOM_NUM
  is '房间数量';
comment on column SERVE_GUESTROOM.CREATOR_ID
  is '创建人';
comment on column SERVE_GUESTROOM.CREATE_TIME
  is '创建时间';
comment on column SERVE_GUESTROOM.MODIFY_TIME
  is '修改时间';
comment on column SERVE_GUESTROOM.MODIFIER_ID
  is '修改人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVE_GUESTROOM
  add constraint PK_GUESTROOM_ID primary key (GUESTROOM_ID)
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
create table SERVE_MEETING_ROOM
(
  MEETING_ROOM_ID         VARCHAR2(50) not null,
  MEETING_ROOM_CODE       VARCHAR2(50),
  HOTEL_ID                VARCHAR2(50),
  MEETING_ROOM_TYPE       CHAR(2),
  CONTAIN_NUM             NUMBER(3),
  MARKET_PRICE            NUMBER(16,6),
  UNITVALUE               VARCHAR2(10),
  MEETING_ROOM_DESC       VARCHAR2(1000),
  PICTURE_ID              VARCHAR2(50),
  MEETING_ROOM_FACILITIES VARCHAR2(500),
  CREATOR_ID              VARCHAR2(50),
  CREATE_TIME             DATE,
  MODIFY_TIME             DATE,
  MODIFIER_ID             VARCHAR2(50),
  MEETING_NUM_RANG        VARCHAR2(20)
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
comment on table SERVE_MEETING_ROOM
  is '会议室';
-- Add comments to the columns 
comment on column SERVE_MEETING_ROOM.MEETING_ROOM_ID
  is '主键';
comment on column SERVE_MEETING_ROOM.MEETING_ROOM_CODE
  is '编号';
comment on column SERVE_MEETING_ROOM.HOTEL_ID
  is '酒店id';
comment on column SERVE_MEETING_ROOM.MEETING_ROOM_TYPE
  is '会议室类型';
comment on column SERVE_MEETING_ROOM.CONTAIN_NUM
  is '坐席数量';
comment on column SERVE_MEETING_ROOM.MARKET_PRICE
  is '市场价';
comment on column SERVE_MEETING_ROOM.UNITVALUE
  is '价格单位';
comment on column SERVE_MEETING_ROOM.MEETING_ROOM_DESC
  is '会议室描述';
comment on column SERVE_MEETING_ROOM.PICTURE_ID
  is '图片';
comment on column SERVE_MEETING_ROOM.MEETING_ROOM_FACILITIES
  is '会议室设施';
comment on column SERVE_MEETING_ROOM.CREATOR_ID
  is '创建人';
comment on column SERVE_MEETING_ROOM.CREATE_TIME
  is '创建时间';
comment on column SERVE_MEETING_ROOM.MODIFY_TIME
  is '修改时间';
comment on column SERVE_MEETING_ROOM.MODIFIER_ID
  is '修改人';
comment on column SERVE_MEETING_ROOM.MEETING_NUM_RANG
  is '人数范围';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SERVE_MEETING_ROOM
  add constraint PK_MEETING_ROOM_ID primary key (MEETING_ROOM_ID)
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
create table RECOMMEND_GOODS
(
  ID           VARCHAR2(50) not null,
  RECOMMENDER  VARCHAR2(50),
  REASON       VARCHAR2(1000),
  AUDIT_STATUS CHAR(2) default 00,
  USE_STATUS   CHAR(1) default 0,
  CREATE_TIME  DATE,
  GOODS        VARCHAR2(50),
  SORT         NUMBER
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
comment on column RECOMMEND_GOODS.ID
  is '主键';
comment on column RECOMMEND_GOODS.RECOMMENDER
  is '推荐人';
comment on column RECOMMEND_GOODS.REASON
  is '推荐理由';
comment on column RECOMMEND_GOODS.AUDIT_STATUS
  is '审核状态';
comment on column RECOMMEND_GOODS.USE_STATUS
  is '有效状态';
comment on column RECOMMEND_GOODS.CREATE_TIME
  is '创建时间';
comment on column RECOMMEND_GOODS.GOODS
  is '推荐的商品ID';
comment on column RECOMMEND_GOODS.SORT
  is '排序序号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table RECOMMEND_GOODS
  add constraint RECOMMEND_GOODS_PK primary key (ID)
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
create table RECOMMEND_PROJECT
(
  ID           VARCHAR2(50) not null,
  RECOMMENDER  VARCHAR2(50),
  REASON       VARCHAR2(1000),
  AUDIT_STATUS CHAR(2) default 00,
  USE_STATUS   CHAR(1) default 0,
  CREATE_TIME  DATE,
  BULLETIN     VARCHAR2(50),
  SORT         NUMBER(6),
  PICTURE      VARCHAR2(50)
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
comment on column RECOMMEND_PROJECT.ID
  is '主键';
comment on column RECOMMEND_PROJECT.RECOMMENDER
  is '推荐人ID';
comment on column RECOMMEND_PROJECT.REASON
  is '推荐理由';
comment on column RECOMMEND_PROJECT.AUDIT_STATUS
  is '审核状态';
comment on column RECOMMEND_PROJECT.USE_STATUS
  is '有效状态';
comment on column RECOMMEND_PROJECT.CREATE_TIME
  is '创建时间';
comment on column RECOMMEND_PROJECT.BULLETIN
  is '推荐的项目公告ID';
comment on column RECOMMEND_PROJECT.SORT
  is '排序序号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table RECOMMEND_PROJECT
  add constraint RECOM_PRO_PK primary key (ID)
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
create table RECOMMEND_SUPPLIER
(
  ID           VARCHAR2(50) not null,
  RECOMMENDER  VARCHAR2(50),
  REASON       VARCHAR2(1000),
  AUDIT_STATUS CHAR(2) default 00,
  USE_STATUS   CHAR(1) default 0,
  CREATE_TIME  DATE,
  SUPPLIER     VARCHAR2(50),
  SORT         NUMBER,
  ORGINFO      VARCHAR2(50)
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
comment on column RECOMMEND_SUPPLIER.ID
  is '主键';
comment on column RECOMMEND_SUPPLIER.RECOMMENDER
  is '推荐人';
comment on column RECOMMEND_SUPPLIER.REASON
  is '推荐理由';
comment on column RECOMMEND_SUPPLIER.AUDIT_STATUS
  is '审核状态';
comment on column RECOMMEND_SUPPLIER.USE_STATUS
  is '有效状态';
comment on column RECOMMEND_SUPPLIER.CREATE_TIME
  is '创建时间';
comment on column RECOMMEND_SUPPLIER.SUPPLIER
  is '推荐的供应商ID';
comment on column RECOMMEND_SUPPLIER.SORT
  is '排序序号';
comment on column RECOMMEND_SUPPLIER.ORGINFO
  is '推荐的供应商的机构信息ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table RECOMMEND_SUPPLIER
  add constraint RECOMMEND_SUPPLIER_PK primary key (ID)
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
