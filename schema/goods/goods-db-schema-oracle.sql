drop table 				goods  CASCADE CONSTRAINTS;																		
drop table 				goods_brand  CASCADE CONSTRAINTS;																		
drop table 				goods_brand_to_class  CASCADE CONSTRAINTS;																		
drop table 				goods_class  CASCADE CONSTRAINTS;																		
drop table 				goods_class_param  CASCADE CONSTRAINTS;																		
drop table 				goods_class_param_type  CASCADE CONSTRAINTS;																		
drop table 				goods_class_to_category  CASCADE CONSTRAINTS;																		
drop table 				goods_evaluate  CASCADE CONSTRAINTS;																		
drop table 				goods_modifier  CASCADE CONSTRAINTS;																		
drop table 				goods_optional_fitting  CASCADE CONSTRAINTS;																		
drop table 				goods_param  CASCADE CONSTRAINTS;				

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
  SPEC                     VARCHAR2(1000),
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
;
comment on column GOODS.VERIFIER_ID
  is '审核人';
comment on column GOODS.VERIFY_TIME
  is '审核时间';
comment on column GOODS.OPINION
  is '意见';
alter table GOODS
  add constraint PK_GOODS primary key (GOODS_ID);
  
  
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
;
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
alter table GOODS_BRAND
  add constraint PK_GOODS_BRAND primary key (GOODS_BRAND_ID);

create table GOODS_BRAND_TO_CLASS
(
  GOODS_CLASS_ID          VARCHAR2(36),
  GOODS_BRAND_ID          VARCHAR2(36),
  GOODS_BRAND_TO_CLASS_ID VARCHAR2(36) not null
)
;
comment on column GOODS_BRAND_TO_CLASS.GOODS_CLASS_ID
  is '分类id';
comment on column GOODS_BRAND_TO_CLASS.GOODS_BRAND_ID
  is '品牌id';
comment on column GOODS_BRAND_TO_CLASS.GOODS_BRAND_TO_CLASS_ID
  is '主键';

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
;
alter table GOODS_CLASS
  add constraint PK_GOODS_CLASS primary key (GOODS_CLASS_ID);

create table GOODS_CLASS_PARAM
(
  GOODS_CLASS_ID       VARCHAR2(36),
  PARAM_NAME           VARCHAR2(100),
  CAN_SELECT           CHAR(1),
  PARAM_DESC           VARCHAR2(300),
  SORT                 NUMBER,
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
;
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
alter table GOODS_CLASS_PARAM
  add constraint PK_GOODS_CLASS_PARAM primary key (GOODS_CLASS_PARAM_ID);

create table GOODS_CLASS_PARAM_TYPE
(
  PARAM_TYPE_DESC           VARCHAR2(100),
  PARAM_TYPE_CODE           VARCHAR2(100),
  PARAM_TYPE_NAME           VARCHAR2(100),
  SORT                      NUMBER(9),
  GOODS_CLASS_ID            VARCHAR2(36),
  CREATOR_ID                VARCHAR2(36),
  CREATE_TIME               DATE,
  MODIFIER_ID               VARCHAR2(36),
  MODIFY_TIME               DATE,
  GOODS_CLASS_PARAM_TYPE_ID VARCHAR2(36) not null
)
;
alter table GOODS_CLASS_PARAM_TYPE
  add constraint PK_GOODS_CLASS_PARAM_TYPE primary key (GOODS_CLASS_PARAM_TYPE_ID);

create table GOODS_CLASS_TO_CATEGORY
(
  GOODS_CLASS_ID             VARCHAR2(36),
  PUR_CATEGORY_ID            VARCHAR2(36),
  GOODS_CLASS_TO_CATEGORY_ID VARCHAR2(36) not null
)
;
comment on column GOODS_CLASS_TO_CATEGORY.GOODS_CLASS_ID
  is '分类id';
comment on column GOODS_CLASS_TO_CATEGORY.PUR_CATEGORY_ID
  is '品目id';
comment on column GOODS_CLASS_TO_CATEGORY.GOODS_CLASS_TO_CATEGORY_ID
  is '主键';
alter table GOODS_CLASS_TO_CATEGORY
  add constraint PK_GOODS_CLASS_TO_CATEGORY_ID primary key (GOODS_CLASS_TO_CATEGORY_ID);

create table GOODS_EVALUATE
(
  GOODS_EVALUATE_ID     VARCHAR2(50) not null,
  GOODS_ID              VARCHAR2(50),
  GOODS_EVALUATE_LEVEL  NUMBER,
  GOODS_EVALUATE_REMARK VARCHAR2(1000),
  CREATOR_NAME			VARCHAR2(50),
  CREATOR_ID            VARCHAR2(50),
  CREATE_TIME           DATE,
  GOODS_EVALUATE_TITEL  VARCHAR2(100)
)
;
comment on column GOODS_EVALUATE.GOODS_EVALUATE_ID
  is '主键';
comment on column GOODS_EVALUATE.GOODS_ID
  is '商品';
comment on column GOODS_EVALUATE.GOODS_EVALUATE_LEVEL
  is '评价级别';
comment on column GOODS_EVALUATE.GOODS_EVALUATE_REMARK
  is '描述';
comment on column GOODS_EVALUATE.CREATOR_NAME
  is '评价人中文名';
comment on column GOODS_EVALUATE.CREATOR_ID
  is '创建人';
comment on column GOODS_EVALUATE.CREATE_TIME
  is '创建时间';
comment on column GOODS_EVALUATE.GOODS_EVALUATE_TITEL
  is '标题';
alter table GOODS_EVALUATE
  add constraint PK_GOODS_EVALUATE_ID primary key (GOODS_EVALUATE_ID);

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
;
alter table GOODS_MODIFIER
  add constraint PK_GOODS_MODIFIER primary key (GOODS_MODIFIER_ID);

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
;
comment on column GOODS_OPTIONAL_FITTING.ISUSE
  is '是否可用';
alter table GOODS_OPTIONAL_FITTING
  add constraint PK_GOODS_OPTIONAL_FITTING primary key (GOODS_OPTIONAL_FITTING_ID);

create table GOODS_PARAM
(
  PARAM_NAME           VARCHAR2(100),
  SORT_NO              NUMBER(9),
  TYPE_NAME            VARCHAR2(100),
  PARAM_VALUE          VARCHAR2(100),
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
;
alter table GOODS_PARAM
  add constraint PK_GOODS_PARAM primary key (GOODS_PARAM_ID);



create table GOODS_ATTENTION_PRICE
(
  GOODS_ATTENTION_PRICE_ID VARCHAR2(36) not null,
  GOODS_ID                 VARCHAR2(36),
  ORG_INFO                 VARCHAR2(36),
  CREATOR_ID               VARCHAR2(36),
  CREATE_TIME              DATE,
  DISTRICT_ID              VARCHAR2(36)
)
;
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
alter table GOODS_ATTENTION_PRICE
  add constraint PK_GOODS_ATTENTION_PRICE_ID primary key (GOODS_ATTENTION_PRICE_ID);
