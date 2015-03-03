drop table 				agency_agent CASCADE CONSTRAINTS;		
drop table 				buy_buyer  CASCADE CONSTRAINTS;		
drop table 				spl_supplier;							
drop table 				org_info  CASCADE CONSTRAINTS;			
drop table 				org_qualification  CASCADE CONSTRAINTS;																		
drop table 				org_qualification_detail  CASCADE CONSTRAINTS;																		
drop table 				purcatalog_category  CASCADE CONSTRAINTS;			
drop table 				ecp_base_catalog  CASCADE CONSTRAINTS;		
drop table 				ecp_base_catalog_detail  CASCADE CONSTRAINTS;		
drop table 				ecp_pub_evaluate  CASCADE CONSTRAINTS;		
drop table 				ecp_pub_evaluate_quota  CASCADE CONSTRAINTS;	
drop table 				base_qualification  CASCADE CONSTRAINTS;	
drop table 				ecp_pub_evaluate_score  CASCADE CONSTRAINTS;
drop table 				ecp_pub_success_case  CASCADE CONSTRAINTS;	
drop table 				ecp_base_ctlog_dstrct  CASCADE CONSTRAINTS;	
drop table 				ecp_base_catalog_proctype  CASCADE CONSTRAINTS;	

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
;
comment on column AGENCY_AGENT.VERIFIER_ID
  is '审核人';
comment on column AGENCY_AGENT.VERIFY_TIME
  is '审核时间';
comment on column AGENCY_AGENT.OPINION
  is '审核意见';
alter table AGENCY_AGENT
  add constraint PK_AGENT primary key (AGENT_ID);


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
  OPINION            VARCHAR2(200)
)
;
comment on column BUY_BUYER.VERIFIER_ID
  is '审核人';
comment on column BUY_BUYER.VERIFY_TIME
  is '审核时间';
comment on column BUY_BUYER.OPINION
  is '审核意见';
alter table BUY_BUYER
  add constraint PK_BUYER primary key (BUYER_ID);
  
  
create table SPL_SUPPLIER
(
  HISTORY_ID            VARCHAR2(36),
  CORPORATION           VARCHAR2(100),
  DESC_CN               VARCHAR2(1000),
  DESC_EN               VARCHAR2(1000),
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
  ISMANUFACTURER        VARCHAR2(2)
)
;
comment on column SPL_SUPPLIER.CORPORATION
  is '法定代表人';
comment on column SPL_SUPPLIER.DESC_CN
  is '企业简介（中）';
comment on column SPL_SUPPLIER.VERIFIER_ID
  is '审核人';
comment on column SPL_SUPPLIER.VERIFY_TIME
  is '审核时间';
comment on column SPL_SUPPLIER.OPINION
  is '审核意见';
comment on column SPL_SUPPLIER.ISMANUFACTURER
  is '是否厂家';
alter table SPL_SUPPLIER
  add constraint PK_SPL_SUPPLIER primary key (SUPPLIER_ID);
  
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
  ISMANUFACTURER          VARCHAR2(10),
  BEGAINDATE              DATE,
  ENTCAPACITY             VARCHAR2(2000),
  BID_FOR_RANGE           VARCHAR2(2000),
  MAIN_PRODUCTS           VARCHAR2(2000),
  DESC_CN                 VARCHAR2(2000),
  WEB_URL                 VARCHAR2(100),
  UNIT_SCAPE              VARCHAR2(2000),
  ENT_PRPT                VARCHAR2(100),
  BELONGINDUSTRY          VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
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
;
comment on table ORG_QUALIFICATION
  is '资质信息   @gpcsoft.package packageDir="com.gpcsoft.eps.base.qualification"   @gpcsoft.page domain="qualication"   @hibernate.class table="BASE_QUALIFICATION"   ';
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
alter table ORG_QUALIFICATION
  add constraint PK_QUALIFICATION primary key (QUALIFICATION_ID);

create table ORG_QUALIFICATION_DETAIL
(
  PARAM_VALUE             VARCHAR2(100),
  QUALITY_PARAM_ID        VARCHAR2(36),
  QUALIFICATION_DETAIL_ID VARCHAR2(36) not null,
  QUALIFICATION_ID        VARCHAR2(36)
)
;
comment on table ORG_QUALIFICATION_DETAIL
  is '供应商资质信息详细';
comment on column ORG_QUALIFICATION_DETAIL.PARAM_VALUE
  is '参数值';
comment on column ORG_QUALIFICATION_DETAIL.QUALITY_PARAM_ID
  is '资质参数';
comment on column ORG_QUALIFICATION_DETAIL.QUALIFICATION_DETAIL_ID
  is '主键';
comment on column ORG_QUALIFICATION_DETAIL.QUALIFICATION_ID
  is '资质主对象';
alter table ORG_QUALIFICATION_DETAIL
  add constraint PK_QUALIFICATION_DETAIL_ID primary key (QUALIFICATION_DETAIL_ID);


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
;
alter table PURCATALOG_CATEGORY
  add constraint PK_PURCATALOG_CATEGORY primary key (ID);
  

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
;
comment on table ECP_BASE_CATALOG
  is '采购目录';
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
alter table ECP_BASE_CATALOG
  add constraint PK_CATALOG_ID primary key (CATALOG_ID);
alter table ECP_BASE_CATALOG
  add constraint FK_CREUSER foreign key (CREUSER)
  references AUTH_USER (USR_ID);
alter table ECP_BASE_CATALOG
  add constraint FK_RELUSER foreign key (RELUSER)
  references AUTH_USER (USR_ID);



create table ECP_BASE_CATALOG_DETAIL
(
  CATALOG_DETAIL_ID VARCHAR2(50) not null,
  CATALOG_ID        VARCHAR2(50),
  CATEGORY_ID       VARCHAR2(50),
  GOODSPRICE        NUMBER(10,2),
  YEAR_PROC_TOTAL   NUMBER(10,2)
)
;
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
alter table ECP_BASE_CATALOG_DETAIL
  add constraint PK_CATALOG_DETAIL_ID primary key (CATALOG_DETAIL_ID);
alter table ECP_BASE_CATALOG_DETAIL
  add constraint FK_CATALOG_ID foreign key (CATALOG_ID)
  references ECP_BASE_CATALOG (CATALOG_ID);
alter table ECP_BASE_CATALOG_DETAIL
  add constraint FK_CATEGORY_ID foreign key (CATEGORY_ID)
  references PURCATALOG_CATEGORY (ID);
  
  
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
;
comment on table ECP_PUB_EVALUATE
  is '评价表 ';
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
alter table ECP_PUB_EVALUATE
  add constraint PK_EVALUATE_ID primary key (EVALUATE_ID);

create table ECP_PUB_EVALUATE_QUOTA
(
  QUOTA_ID         VARCHAR2(50) not null,
  QUOTA_NAME       VARCHAR2(200),
  QUOTA_PROPORTION NUMBER(6,2),
  QUOTA_TYPE       CHAR(2)
)
;
comment on table ECP_PUB_EVALUATE_QUOTA
  is '评价指标表';
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
alter table ECP_PUB_EVALUATE_QUOTA
  add constraint PK_QUOTA_ID primary key (QUOTA_ID);

create table ECP_PUB_EVALUATE_SCORE
(
  SCORE_ID         VARCHAR2(50) not null,
  EVALUATE_ID      VARCHAR2(50),
  QUOTA_ID         VARCHAR2(50),
  QUOTA_PROPORTION NUMBER(6,2),
  QUOTA_NAME       VARCHAR2(200),
  SCORE            NUMBER(6,2)
)
;
comment on table ECP_PUB_EVALUATE_SCORE
  is '评价指标分值表';
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
alter table ECP_PUB_EVALUATE_SCORE
  add constraint PK_SCORE_ID primary key (SCORE_ID);
alter table ECP_PUB_EVALUATE_SCORE
  add constraint FK_EVALUATE_ID foreign key (EVALUATE_ID)
  references ECP_PUB_EVALUATE (EVALUATE_ID);
alter table ECP_PUB_EVALUATE_SCORE
  add constraint FK_QUOTA_ID foreign key (QUOTA_ID)
  references ECP_PUB_EVALUATE_QUOTA (QUOTA_ID);
  
  
  
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
  LEVEL_DATA       VARCHAR2(500)
)
;
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
alter table BASE_QUALIFICATION
  add constraint PK_QUALITY_ID primary key (QUALITY_ID);
-- Add/modify columns 
alter table BASE_QUALIFICATION add UNIT VARCHAR2(20);
alter table BASE_QUALIFICATION add IS_DISPLAY CHAR(1) default 1;
-- Add comments to the columns 
comment on column BASE_QUALIFICATION.UNIT
  is '参数单位';
comment on column BASE_QUALIFICATION.IS_DISPLAY
  is '是否显示';

  
  
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
;
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
alter table ECP_PUB_SUCCESS_CASE
  add constraint PK_CASE_ID primary key (CASE_ID);
  


create table ECP_BASE_CTLOG_DSTRCT
(
  ECP_BASE_CTLOG_DSTRCT_ID VARCHAR2(50) not null,
  CATALOG_ID               VARCHAR2(50),
  DISTRICT_ID              VARCHAR2(50)
)
;
comment on table ECP_BASE_CTLOG_DSTRCT
  is '目录区域中间表';
comment on column ECP_BASE_CTLOG_DSTRCT.ECP_BASE_CTLOG_DSTRCT_ID
  is '主键';
comment on column ECP_BASE_CTLOG_DSTRCT.CATALOG_ID
  is '目录';
comment on column ECP_BASE_CTLOG_DSTRCT.DISTRICT_ID
  is '区域';
alter table ECP_BASE_CTLOG_DSTRCT
  add constraint PK_CTLOG_DSTRCT_ID primary key (ECP_BASE_CTLOG_DSTRCT_ID);
alter table ECP_BASE_CTLOG_DSTRCT
  add constraint FK_CTLOG_DSTRCT_CATALOG_ID foreign key (CATALOG_ID)
  references ECP_BASE_CATALOG (CATALOG_ID);
alter table ECP_BASE_CTLOG_DSTRCT
  add constraint FK_CTLOG_DSTRCT_DISTRICT_ID foreign key (DISTRICT_ID)
  references BASE_DISTRICT (DISTRICT_ID);
  
  
  
create table ECP_BASE_CATALOG_PROCTYPE
(
  CATALOG_PROCTYPE_ID VARCHAR2(50) not null,
  CATALOG_ID          VARCHAR2(50),
  CATEGORY_ID         VARCHAR2(50),
  PROCTYPE            CHAR(2),
  PROC_TOTAL          NUMBER(10,2)
)
;
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
alter table ECP_BASE_CATALOG_PROCTYPE
  add constraint PK_CATALOG_PROCTYPE_ID primary key (CATALOG_PROCTYPE_ID);
alter table ECP_BASE_CATALOG_PROCTYPE
  add constraint FK_PROCTYPE_CATALOG_ID foreign key (CATALOG_ID)
  references ECP_BASE_CATALOG (CATALOG_ID);
alter table ECP_BASE_CATALOG_PROCTYPE
  add constraint FK_PROCTYPE_CATEGORY_ID foreign key (CATEGORY_ID)
  references PURCATALOG_CATEGORY (ID);
  
  