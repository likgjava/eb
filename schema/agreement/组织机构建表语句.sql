
create table BIZ_AGENT
(
  AGENT_ID                 VARCHAR2(36) not null,
  COMPANY_ID               VARCHAR2(36),
  AGENT_TYPE               VARCHAR2(10),
  AGENT_TOTAL_NMBR         NUMBER(9),
  AGENT_MIDDLE_TCHLG_NMBR  NUMBER(9),
  AGENT_SENIOR_TCHLG_NMBR  NUMBER(9),
  AGENT_REG_PRCTS_NMBR     NUMBER(9),
  AGENT_TOTAL_DEBT         NUMBER(23,7),
  AGENT_TOTAL_CAPITAL      NUMBER(23,7),
  AGENT_BID_PRPS_EVLT_ADDR VARCHAR2(200),
  AGENT_ECNM_NATURE        VARCHAR2(100),
  PARENT_COMPANY_ID        VARCHAR2(36),
  AGENT_ADMIN_GRD          VARCHAR2(10),
  AGENT_UNDTK_BID_PROJ     VARCHAR2(1000),
  AGENT_BUSS_CNDT          VARCHAR2(1000),
  AGENT_REMARKS            VARCHAR2(2000),
  CREATOR                  VARCHAR2(36),
  CREATE_TIME              DATE,
  MODIFIER                 VARCHAR2(36),
  MODIFY_TIME              DATE,
  AUDIT_STATUS             CHAR(2),
  AUDITOR                  VARCHAR2(36),
  AUDIT_TIME               DATE,
  AUDIT_OPINION            VARCHAR2(1000),
  USE_STATUS               CHAR(2)
)
;
comment on table BIZ_AGENT
  is '代理机构';
comment on column BIZ_AGENT.AGENT_ID
  is '主键';
comment on column BIZ_AGENT.COMPANY_ID
  is '所属机构';
comment on column BIZ_AGENT.AGENT_TYPE
  is '代理机构类型： 01:政府集中采购中心 02:政府部门采购中心 03:社会中介（招标公司） 04:企业专职采购部门';
comment on column BIZ_AGENT.AGENT_TOTAL_NMBR
  is '从业人员总数';
comment on column BIZ_AGENT.AGENT_MIDDLE_TCHLG_NMBR
  is '中级职称技术人员数';
comment on column BIZ_AGENT.AGENT_SENIOR_TCHLG_NMBR
  is '高级职称技术人员数';
comment on column BIZ_AGENT.AGENT_REG_PRCTS_NMBR
  is '注册执业人员数';
comment on column BIZ_AGENT.AGENT_TOTAL_DEBT
  is '负债总额';
comment on column BIZ_AGENT.AGENT_TOTAL_CAPITAL
  is '资产总额';
comment on column BIZ_AGENT.AGENT_BID_PRPS_EVLT_ADDR
  is '评标地址';
comment on column BIZ_AGENT.AGENT_ECNM_NATURE
  is '经济性质';
comment on column BIZ_AGENT.PARENT_COMPANY_ID
  is '主管单位，上级主管部门';
comment on column BIZ_AGENT.AGENT_ADMIN_GRD
  is '行政级别：1.省、2.市、3.县、4.区';
comment on column BIZ_AGENT.AGENT_UNDTK_BID_PROJ
  is '近三年承担过的招标代理项目';
comment on column BIZ_AGENT.AGENT_BUSS_CNDT
  is '近三年经营代理情况';
comment on column BIZ_AGENT.AGENT_REMARKS
  is '备注';
comment on column BIZ_AGENT.CREATOR
  is '创建人';
comment on column BIZ_AGENT.CREATE_TIME
  is '创建时间';
comment on column BIZ_AGENT.MODIFIER
  is '修改人';
comment on column BIZ_AGENT.MODIFY_TIME
  is '修改时间';
comment on column BIZ_AGENT.AUDIT_STATUS
  is '审核状态';
comment on column BIZ_AGENT.AUDITOR
  is '审核人';
comment on column BIZ_AGENT.AUDIT_TIME
  is '审核时间';
comment on column BIZ_AGENT.AUDIT_OPINION
  is '审核意见';
comment on column BIZ_AGENT.USE_STATUS
  is '使用状态';
alter table BIZ_AGENT
  add constraint PK_AGENT_ID primary key (AGENT_ID);


create table BIZ_BUYER
(
  BUYER_ID            VARCHAR2(36) not null,
  COMPANY_ID          VARCHAR2(36),
  PARENT_COMPANY_ID   VARCHAR2(36),
  BUYER_PUR_SBJCT     VARCHAR2(100),
  EXEC_DEPT           VARCHAR2(100),
  BUYER_CMPT_DEP_TYPE VARCHAR2(36),
  BUYER_BUDGET_CODE   VARCHAR2(100),
  BUYER_UNIT_TYPE     VARCHAR2(100),
  BUYER_FUND_DEPT     VARCHAR2(100),
  CREATOR             VARCHAR2(100),
  CREATE_TIME         DATE,
  MODIFIER            VARCHAR2(100),
  MODIFY_TIME         DATE,
  AUDITOR             VARCHAR2(36),
  AUDIT_TIME          DATE,
  AUDIT_STATUS        CHAR(2),
  AUDIT_OPINION       VARCHAR2(1000),
  USE_STATUS          CHAR(2)
)
;
comment on table BIZ_BUYER
  is '采购人表';
comment on column BIZ_BUYER.BUYER_ID
  is '主键';
comment on column BIZ_BUYER.COMPANY_ID
  is '所属机构';
comment on column BIZ_BUYER.PARENT_COMPANY_ID
  is '上级采购单位';
comment on column BIZ_BUYER.BUYER_PUR_SBJCT
  is '采购主体：01.国家机关 02.事业单位 03.团体组织 04.企业单位';
comment on column BIZ_BUYER.EXEC_DEPT
  is '行政部门：01:行政管理部门 02:公检法司部门 03:农林水气象部门';
comment on column BIZ_BUYER.BUYER_CMPT_DEP_TYPE
  is '主管部门 01:财政 02:建设 03:发改委 04:经贸委 05:监察 06:统计';
comment on column BIZ_BUYER.BUYER_BUDGET_CODE
  is '预算编码';
comment on column BIZ_BUYER.BUYER_UNIT_TYPE
  is '单位性质 01：国家机关 02：事业单位 03：团体组织';
comment on column BIZ_BUYER.BUYER_FUND_DEPT
  is '资金归口处室，01：行政政法 02：教科文 03：经济建设 04：农业 05：社会保障 06：企业 07：金融 08：其他';
comment on column BIZ_BUYER.CREATOR
  is '创建人';
comment on column BIZ_BUYER.CREATE_TIME
  is '创建时间';
comment on column BIZ_BUYER.MODIFIER
  is '修改人';
comment on column BIZ_BUYER.MODIFY_TIME
  is '修改时间';
comment on column BIZ_BUYER.AUDITOR
  is '审核人';
comment on column BIZ_BUYER.AUDIT_TIME
  is '审核时间';
comment on column BIZ_BUYER.AUDIT_STATUS
  is '00:草稿 01：待审核 02：审核通过 03：审核不通过';
comment on column BIZ_BUYER.AUDIT_OPINION
  is '审核意见';
comment on column BIZ_BUYER.USE_STATUS
  is '状态';
alter table BIZ_BUYER
  add constraint PK_BUYER_ID primary key (BUYER_ID);


create table BIZ_SUPPLIER
(
  SUPPLIER_ID                 VARCHAR2(36) not null,
  COMPANY_ID                  VARCHAR2(36),
  SUPPLIER_MAIN_PRODUCTS      VARCHAR2(1000),
  SUPPLIER_REG_ADDRESS        VARCHAR2(200),
  SUPPLIER_REG_AUTH_ORG       VARCHAR2(100),
  SUPPLIER_REG_BUS_SCOPE      VARCHAR2(1000),
  SUPPLIER_REG_CODE           VARCHAR2(100),
  SUPPLIER_REG_DATE           DATE,
  SUPPLIER_REG_TO_DATE        DATE,
  SUPPLIER_TRADE_END_DATE     DATE,
  SUPPLIER_TRADE_START_DATE   DATE,
  SUPPLIER_TRADE_ADDRESS      VARCHAR2(200),
  SUPPLIER_UNIT_AWARD_UNIT    VARCHAR2(100),
  SUPPLIER_UNIT_REG_NMBR      VARCHAR2(100),
  SUPPLIER_UNIT_START_DATE    DATE,
  SUPPLIER_UNIT_END_DATE      DATE,
  SUPPLIER_BEGAINDATE         DATE,
  SUPPLIER_ENTCAPACITY        VARCHAR2(2000),
  SUPPLIER_BID_RANGE_CODE     VARCHAR2(2000),
  SUPPLIER_BID_RANGE_NAME     VARCHAR2(2000),
  SUPPLIER_NAT_TAX_NMBR       VARCHAR2(100),
  SUPPLIER_NAT_TAX_CMPT_NMBR  VARCHAR2(100),
  SUPPLIER_LAND_TAX_NMBR      VARCHAR2(100),
  SUPPLIER_LAND_TAX_CMPT_NMBR VARCHAR2(100),
  CREATOR                     VARCHAR2(36),
  CREATE_TIME                 DATE,
  MODIFIER                    VARCHAR2(36),
  MODIFY_TIME                 DATE,
  AUDITOR                     VARCHAR2(36),
  AUDIT_STATUS                CHAR(2),
  AUDIT_TIME                  DATE,
  AUDIT_OPINION               VARCHAR2(1000),
  SUPPLIER_ISMANUFACTURER     CHAR(1),
  USE_STATUS                  CHAR(2)
)
;
comment on column BIZ_SUPPLIER.SUPPLIER_ID
  is '主键';
comment on column BIZ_SUPPLIER.COMPANY_ID
  is '所属机构 FK';
comment on column BIZ_SUPPLIER.SUPPLIER_MAIN_PRODUCTS
  is '主营产品范围';
comment on column BIZ_SUPPLIER.SUPPLIER_REG_ADDRESS
  is '工商注册地址，住所';
comment on column BIZ_SUPPLIER.SUPPLIER_REG_AUTH_ORG
  is '工商注册发证机关';
comment on column BIZ_SUPPLIER.SUPPLIER_REG_BUS_SCOPE
  is '工商注册营业范围';
comment on column BIZ_SUPPLIER.SUPPLIER_REG_CODE
  is '工商注册号';
comment on column BIZ_SUPPLIER.SUPPLIER_REG_DATE
  is '工商注册日期';
comment on column BIZ_SUPPLIER.SUPPLIER_REG_TO_DATE
  is '工商注册有效期';
comment on column BIZ_SUPPLIER.SUPPLIER_TRADE_END_DATE
  is '营业执照结束日期';
comment on column BIZ_SUPPLIER.SUPPLIER_TRADE_START_DATE
  is '营业执照开始日期';
comment on column BIZ_SUPPLIER.SUPPLIER_TRADE_ADDRESS
  is '经营地址';
comment on column BIZ_SUPPLIER.SUPPLIER_UNIT_AWARD_UNIT
  is '组织机构证颁发单位';
comment on column BIZ_SUPPLIER.SUPPLIER_UNIT_REG_NMBR
  is '组织机构证登记号';
comment on column BIZ_SUPPLIER.SUPPLIER_UNIT_START_DATE
  is '组织机构证开始日期';
comment on column BIZ_SUPPLIER.SUPPLIER_UNIT_END_DATE
  is '组织机构证结束日期';
comment on column BIZ_SUPPLIER.SUPPLIER_BEGAINDATE
  is '开业日期';
comment on column BIZ_SUPPLIER.SUPPLIER_ENTCAPACITY
  is '企业产能';
comment on column BIZ_SUPPLIER.SUPPLIER_BID_RANGE_CODE
  is '经营范围code[取品目表]';
comment on column BIZ_SUPPLIER.SUPPLIER_BID_RANGE_NAME
  is '经营范围name';
comment on column BIZ_SUPPLIER.SUPPLIER_NAT_TAX_NMBR
  is '国税登记编号';
comment on column BIZ_SUPPLIER.SUPPLIER_NAT_TAX_CMPT_NMBR
  is '税务登记证国税电脑编码';
comment on column BIZ_SUPPLIER.SUPPLIER_LAND_TAX_NMBR
  is '地税登记编号';
comment on column BIZ_SUPPLIER.SUPPLIER_LAND_TAX_CMPT_NMBR
  is '税务登记证地税电脑编码';
comment on column BIZ_SUPPLIER.CREATOR
  is '创建人';
comment on column BIZ_SUPPLIER.CREATE_TIME
  is '创建时间';
comment on column BIZ_SUPPLIER.MODIFIER
  is '修改人';
comment on column BIZ_SUPPLIER.MODIFY_TIME
  is '修改时间';
comment on column BIZ_SUPPLIER.AUDITOR
  is '审核人';
comment on column BIZ_SUPPLIER.AUDIT_STATUS
  is '审核状态[00.草稿 01.待审核 02.通过 03.不通过]';
comment on column BIZ_SUPPLIER.AUDIT_TIME
  is '审核时间';
comment on column BIZ_SUPPLIER.AUDIT_OPINION
  is '审核意见';
comment on column BIZ_SUPPLIER.SUPPLIER_ISMANUFACTURER
  is '是否厂家';
comment on column BIZ_SUPPLIER.USE_STATUS
  is '状态';
alter table BIZ_SUPPLIER
  add constraint PK_SUPPLIER_ID primary key (SUPPLIER_ID);

create table PUB_COMPANY
(
  COMPANY_ID              VARCHAR2(36) not null,
  COMPANY_CODE            VARCHAR2(100),
  COMPANY_NAME            VARCHAR2(100),
  COMPANY_SHORT_NAME      VARCHAR2(30),
  COMPANY_SPELL_NAME      VARCHAR2(40),
  COMPANY_ENT_PRPT        VARCHAR2(10),
  COMPANY_BELONG_INDUSTRY VARCHAR2(36),
  EMP_ID                  VARCHAR2(36),
  COMPANY_CROPORATE       VARCHAR2(50),
  COMPANY_TEL             VARCHAR2(50),
  COMPANY_EMAIL           VARCHAR2(50),
  COMPANY_FAX             VARCHAR2(50),
  COMPANY_ADDRESS         VARCHAR2(200),
  COMPANY_POST_CODE       CHAR(6),
  COMPANY_AREA_CODE       CHAR(6),
  COMPANY_AREA_NAME       VARCHAR2(200),
  COMPANY_WEB_URL         VARCHAR2(100),
  COMPANY_LOGO            VARCHAR2(36),
  AGENCY_ID               VARCHAR2(36),
  BUYER_ID                VARCHAR2(36),
  SUPPLIER_ID             VARCHAR2(36),
  SUPERVISION_ID          VARCHAR2(36),
  CREATOR                 VARCHAR2(36),
  CREATE_TIME             DATE,
  MODIFIER                VARCHAR2(36),
  MODIFY_TIME             DATE,
  USE_STATUS              CHAR(2)
)
;
comment on column PUB_COMPANY.COMPANY_ID
  is '主键';
comment on column PUB_COMPANY.COMPANY_CODE
  is '机构编码';
comment on column PUB_COMPANY.COMPANY_NAME
  is '机构名称';
comment on column PUB_COMPANY.COMPANY_SHORT_NAME
  is '机构简称';
comment on column PUB_COMPANY.COMPANY_SPELL_NAME
  is '拼音简码';
comment on column PUB_COMPANY.COMPANY_ENT_PRPT
  is '单位性质[枚举]';
comment on column PUB_COMPANY.COMPANY_BELONG_INDUSTRY
  is '所属行业 FK';
comment on column PUB_COMPANY.EMP_ID
  is '联系人(机构管理员）FK';
comment on column PUB_COMPANY.COMPANY_CROPORATE
  is '法定代表人';
comment on column PUB_COMPANY.COMPANY_TEL
  is '联系电话';
comment on column PUB_COMPANY.COMPANY_EMAIL
  is '邮箱';
comment on column PUB_COMPANY.COMPANY_FAX
  is '传真';
comment on column PUB_COMPANY.COMPANY_ADDRESS
  is '机构地址';
comment on column PUB_COMPANY.COMPANY_POST_CODE
  is '邮政编码';
comment on column PUB_COMPANY.COMPANY_AREA_CODE
  is '区域编码';
comment on column PUB_COMPANY.COMPANY_AREA_NAME
  is '行政区域[冗余字段]';
comment on column PUB_COMPANY.COMPANY_WEB_URL
  is '网址';
comment on column PUB_COMPANY.COMPANY_LOGO
  is '机构的logo图片';
comment on column PUB_COMPANY.AGENCY_ID
  is '代理机构ID';
comment on column PUB_COMPANY.BUYER_ID
  is '采购人ID';
comment on column PUB_COMPANY.SUPPLIER_ID
  is '供应商ID';
comment on column PUB_COMPANY.SUPERVISION_ID
  is '监管机构ID';
comment on column PUB_COMPANY.CREATOR
  is '创建人 FK';
comment on column PUB_COMPANY.CREATE_TIME
  is '创建时间';
comment on column PUB_COMPANY.MODIFIER
  is '修改人 FK';
comment on column PUB_COMPANY.MODIFY_TIME
  is '修改时间';
comment on column PUB_COMPANY.USE_STATUS
  is '使用状态[00:临时01:有效
02:无效]';
alter table PUB_COMPANY
  add constraint PK_COMPANY_ID primary key (COMPANY_ID);

create table PUB_COMPANY_EXT
(
  COMPANY_EXT_ID                VARCHAR2(36) not null,
  COMPANY_EXT_ORG_CODE          VARCHAR2(100),
  COMPANY_EXT_REG_CAPITAL       NUMBER(13,2),
  COMPANY_EXT_UNIT_SCAPE        VARCHAR2(2000),
  COMPANY_EXT_OPEN_BANK         VARCHAR2(100),
  COMPANY_EXT_OPEN_ACCOUNT      VARCHAR2(100),
  COMPANY_EXT_OPEN_ACCOUNT_NAME VARCHAR2(100),
  COMPANY_EXT_DESC_CN           VARCHAR2(2000),
  CREATOR                       VARCHAR2(36),
  CREATE_TIME                   DATE,
  MODIFIER                      VARCHAR2(36),
  MODIFY_TIME                   DATE
)
;
comment on table PUB_COMPANY_EXT
  is '单位扩展信息表';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_ID
  is '主键';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_ORG_CODE
  is '组织机构代码证号';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_REG_CAPITAL
  is '注册资金，注册资本';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_UNIT_SCAPE
  is '人员规模';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_OPEN_BANK
  is '开户银行名称';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_OPEN_ACCOUNT
  is '开户银行账号';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_OPEN_ACCOUNT_NAME
  is '开户人姓名';
comment on column PUB_COMPANY_EXT.COMPANY_EXT_DESC_CN
  is '企业简介（中）';
comment on column PUB_COMPANY_EXT.CREATOR
  is '创建人';
comment on column PUB_COMPANY_EXT.CREATE_TIME
  is '创建时间';
comment on column PUB_COMPANY_EXT.MODIFIER
  is '修改人';
comment on column PUB_COMPANY_EXT.MODIFY_TIME
  is '修改时间';
alter table PUB_COMPANY_EXT
  add constraint PK_COMPANY_EXT primary key (COMPANY_EXT_ID);
alter table PUB_COMPANY_EXT
  add constraint FK_COMPANY_EXT foreign key (COMPANY_EXT_ID)
  references PUB_COMPANY (COMPANY_ID);

create table PUB_ORGNIZATION
(
  ORG_ID         VARCHAR2(36) not null,
  ORG_NAME       VARCHAR2(200),
  ORG_SHORT_NAME VARCHAR2(30),
  ORG_TYPE       CHAR(1),
  ORG_CONTACTOR  VARCHAR2(36),
  COMPANY_ID     VARCHAR2(36),
  ORG_PARENT_ID  VARCHAR2(36),
  ORG_SUPERVISOR VARCHAR2(36),
  ORG_PATH       VARCHAR2(500),
  ORG_IS_LEAF    CHAR(1),
  ORG_LEVEL      CHAR(1),
  ORG_SORT       NUMBER(19),
  ORG_EMAIL      VARCHAR2(50),
  CREATOR        VARCHAR2(36),
  CREATE_TIME    DATE,
  MODIFIER       VARCHAR2(36),
  MODIFY_TIME    DATE,
  USE_STATUS     CHAR(2)
)
;
comment on table PUB_ORGNIZATION
  is '组织结构';
comment on column PUB_ORGNIZATION.ORG_ID
  is '机构ID';
comment on column PUB_ORGNIZATION.ORG_NAME
  is '机构名称';
comment on column PUB_ORGNIZATION.ORG_SHORT_NAME
  is '机构简称';
comment on column PUB_ORGNIZATION.ORG_TYPE
  is '机构类型';
comment on column PUB_ORGNIZATION.ORG_CONTACTOR
  is '联系人';
comment on column PUB_ORGNIZATION.COMPANY_ID
  is '所属机构';
comment on column PUB_ORGNIZATION.ORG_PARENT_ID
  is '上级机关';
comment on column PUB_ORGNIZATION.ORG_SUPERVISOR
  is '主管';
comment on column PUB_ORGNIZATION.ORG_PATH
  is '路径';
comment on column PUB_ORGNIZATION.ORG_IS_LEAF
  is '是否叶子节点';
comment on column PUB_ORGNIZATION.ORG_LEVEL
  is '树形层级';
comment on column PUB_ORGNIZATION.ORG_SORT
  is '排序';
comment on column PUB_ORGNIZATION.ORG_EMAIL
  is '电子邮箱';
comment on column PUB_ORGNIZATION.CREATOR
  is '创建人';
comment on column PUB_ORGNIZATION.CREATE_TIME
  is '创建时间';
comment on column PUB_ORGNIZATION.MODIFIER
  is '修改人';
comment on column PUB_ORGNIZATION.MODIFY_TIME
  is '修改时间';
comment on column PUB_ORGNIZATION.USE_STATUS
  is '使用状态';
alter table PUB_ORGNIZATION
  add constraint PK_ORGA_ID primary key (ORG_ID);

