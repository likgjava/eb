																																																																												
drop table 				auth_conflict_role CASCADE CONSTRAINTS;																																																																											
drop table 				auth_data_res_type CASCADE CONSTRAINTS;																																																																											
drop table 				auth_gadget  CASCADE CONSTRAINTS;																																																																							
drop table 				auth_menu	 CASCADE CONSTRAINTS;																																																																						
drop table 				auth_orgnization  CASCADE CONSTRAINTS;																																																																										
drop table 				auth_orgnization_rule  CASCADE CONSTRAINTS;																																																																												
drop table 				auth_org_detail  CASCADE CONSTRAINTS;																																																																									
drop table 				auth_org_employee  CASCADE CONSTRAINTS;																																																																										
drop table 				auth_org_stru_type  CASCADE CONSTRAINTS;																																																																											
drop table 				auth_resource  CASCADE CONSTRAINTS;																																																																								
drop table 				auth_role  CASCADE CONSTRAINTS;																																																																						
drop table 				auth_role_extend  CASCADE CONSTRAINTS;																																																																																							
drop table 				auth_role_resource  CASCADE CONSTRAINTS;																																																																																								
drop table 				auth_user  CASCADE CONSTRAINTS;																																																																																																
drop table 				auth_user_gadget  CASCADE CONSTRAINTS;																																																																																																				
drop table 				auth_user_role  CASCADE CONSTRAINTS;																																																																																																			
drop table 				base_attachment  CASCADE CONSTRAINTS;																																																																																																			
drop table 				base_biz_param  CASCADE CONSTRAINTS;																																																																																																			
drop table 				base_dictionary  CASCADE CONSTRAINTS;																																																																																																			
drop table 				base_dictionary_type  CASCADE CONSTRAINTS;																																																																																																																			
drop table 				base_district  CASCADE CONSTRAINTS;																																																																																																															
drop table 				base_industry  CASCADE CONSTRAINTS;																																																																																																															
drop table 				base_login_logs  CASCADE CONSTRAINTS;																																																																																																																
drop table 				base_oper_logs  CASCADE CONSTRAINTS;																																																																																																																
drop table 				base_org_industry  CASCADE CONSTRAINTS;																																																																																																																	
drop table 				base_purcategory_title  CASCADE CONSTRAINTS;																																																																																																																																																																																																																																																													
drop table 				base_quality_define  CASCADE CONSTRAINTS;																																																																																																																															
drop table 				base_quality_grades  CASCADE CONSTRAINTS;																																																																																																																															
drop table 				base_quality_param  CASCADE CONSTRAINTS;																																																																																																																															
drop table 				base_sequence_number  CASCADE CONSTRAINTS;																															
drop table 				ecp_base_bulletin  CASCADE CONSTRAINTS;																	
drop table 				ecp_base_plan_template  CASCADE CONSTRAINTS;																		
drop table 				ecp_base_plan_template_task  CASCADE CONSTRAINTS;																		
drop table 				ecp_project_contract  CASCADE CONSTRAINTS;																		
drop table 				ecp_pub_contract  CASCADE CONSTRAINTS;															
drop table 				ecp_pub_favorites  CASCADE CONSTRAINTS;				
drop table 				ecp_pub_user_security  CASCADE CONSTRAINTS;																		
drop table 				ecp_task  CASCADE CONSTRAINTS;																		
drop table 				ecp_tender_apply_rec  CASCADE CONSTRAINTS;																		
drop table 				ecp_tender_procfile  CASCADE CONSTRAINTS;																		
drop table 				ecp_tender_project  CASCADE CONSTRAINTS;																		
drop table 				ecp_tend_m_task  CASCADE CONSTRAINTS;																		
drop table 				eps_agreement_bargain  CASCADE CONSTRAINTS;																		
drop table 				eps_agreement_bargain_item  CASCADE CONSTRAINTS;																		
drop table 				eps_agreement_bargain_supplyer  CASCADE CONSTRAINTS;																		
drop table 				eps_agreement_order  CASCADE CONSTRAINTS;																		
drop table 				eps_agreement_order_item  CASCADE CONSTRAINTS;																		
drop table 				eps_agree_bargain_goods_option  CASCADE CONSTRAINTS;																		
drop table 				eps_agree_order_goods_option  CASCADE CONSTRAINTS;
drop table 				es_bid_package；
drop table 				es_bid  CASCADE CONSTRAINTS;																		
drop table 				es_buy_winner  CASCADE CONSTRAINTS;																		
drop table 				ecp_pub_concern  CASCADE CONSTRAINTS;																		
drop table 				ecp_pub_concern_group  CASCADE CONSTRAINTS;																		


create table AUTH_ORGNIZATION
(
  ORG_ID            VARCHAR2(50 CHAR) not null,
  ORG_ADDRESS       VARCHAR2(200 CHAR),
  ORG_AUDIT_OPINION VARCHAR2(2000 CHAR),
  ORG_AUDIT_STATUS  VARCHAR2(1 CHAR) default '0',
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
  ORG_STATUS        VARCHAR2(1 CHAR) default '1',
  ORG_SUPERVISOR    VARCHAR2(50 CHAR),
  ORG_TEL           VARCHAR2(50 CHAR),
  ORG_TYPE          VARCHAR2(1 CHAR),
  ORG_BUDGET_CODE   VARCHAR2(200 CHAR),
  ORG_CITY          VARCHAR2(50 CHAR),
  ORG_PARENT_ID     VARCHAR2(50 CHAR),
  ORG_PROVINCE      VARCHAR2(50 CHAR),
  ORG_TOWN          VARCHAR2(50 CHAR),
  ORG_PATH          VARCHAR2(500 CHAR),
  ORG_IS_LEAF       VARCHAR2(1 CHAR) default '1',
  ORG_LEVEL         VARCHAR2(1 CHAR),
  CREATE_TIME       TIMESTAMP(6),
  ORG_MOBILEPHONE   VARCHAR2(11 CHAR),
  SHORT_SPELL_NAME  VARCHAR2(50)
)
;
comment on table AUTH_ORGNIZATION
  is '2009-11-29 For  srplatform v1.0 updated alias ORG_ORGAN';
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
comment on column AUTH_ORGNIZATION.CREATE_TIME
  is '创建时间';
comment on column AUTH_ORGNIZATION.ORG_MOBILEPHONE
  is '手机号码';
comment on column AUTH_ORGNIZATION.SHORT_SPELL_NAME
  is '拼音简称';
alter table AUTH_ORGNIZATION
  add constraint PK_ORG_ID primary key (ORG_ID);
alter table AUTH_ORGNIZATION
  add constraint FKD31622E3D9C306E6 foreign key (ORG_PARENT_ID)
  references AUTH_ORGNIZATION (ORG_ID);

create table AUTH_CONFLICT_ROLE
(
  CON_ID      VARCHAR2(50 CHAR) not null,
  CON_NAME    VARCHAR2(20 CHAR),
  ORG_ID      VARCHAR2(50 CHAR),
  CON_ROLES   VARCHAR2(500 CHAR),
  CREATE_TIME TIMESTAMP(6)
)
;
comment on column AUTH_CONFLICT_ROLE.CON_ID
  is '冲突——ID';
comment on column AUTH_CONFLICT_ROLE.CON_NAME
  is '冲突——名称';
comment on column AUTH_CONFLICT_ROLE.ORG_ID
  is '机构——冲突规则所属机构ID，关联AUTH_ORGNIZATION表。';
comment on column AUTH_CONFLICT_ROLE.CON_ROLES
  is '冲突——角色ID串号，以逗号分隔';
comment on column AUTH_CONFLICT_ROLE.CREATE_TIME
  is '创建时间';
alter table AUTH_CONFLICT_ROLE
  add constraint PK_CON_ID primary key (CON_ID);
alter table AUTH_CONFLICT_ROLE
  add constraint FK_CONFLICT_ORG_ID foreign key (ORG_ID)
  references AUTH_ORGNIZATION (ORG_ID);

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
  DAT_WHERE      VARCHAR2(50 CHAR),
  CREATE_TIME    TIMESTAMP(6)
)
;
comment on table AUTH_DATA_RES_TYPE
  is 'NO';
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
comment on column AUTH_DATA_RES_TYPE.CREATE_TIME
  is '创建时间';
alter table AUTH_DATA_RES_TYPE
  add constraint PK_DAT_ID primary key (DAT_ID);

create table AUTH_GADGET
(
  OBJID      VARCHAR2(50 CHAR) not null,
  NAME       VARCHAR2(100 CHAR) not null,
  DESCS      VARCHAR2(200 CHAR),
  RES_ID     VARCHAR2(200 CHAR) not null,
  CREATETIME DATE,
  IMAGE_ID   VARCHAR2(50 CHAR)
)
;

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
  RES_SORT      NUMBER(19),
  CREATE_TIME   TIMESTAMP(6)
)
;
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
comment on column AUTH_RESOURCE.RES_SORT
  is '资源的排序';
comment on column AUTH_RESOURCE.CREATE_TIME
  is '创建时间';
alter table AUTH_RESOURCE
  add constraint PK_RES_ID primary key (RES_ID);
alter table AUTH_RESOURCE
  add constraint FK_RESOURCE_PARENT_ID foreign key (RES_PARENT_ID)
  references AUTH_RESOURCE (RES_ID);

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
  RES_ID         VARCHAR2(50 CHAR),
  CREATE_TIME    TIMESTAMP(6)
)
;
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
comment on column AUTH_MENU.CREATE_TIME
  is '创建时间';
alter table AUTH_MENU
  add constraint PK_MNU_ID primary key (MNU_ID);
alter table AUTH_MENU
  add constraint FK_MENU_PARENT_ID foreign key (MNU_PARENT_ID)
  references AUTH_MENU (MNU_ID);
alter table AUTH_MENU
  add constraint FK_MENU_RES_ID foreign key (RES_ID)
  references AUTH_RESOURCE (RES_ID);

create table AUTH_ORG_STRU_TYPE
(
  STR_ID      VARCHAR2(50 CHAR) not null,
  STR_NAME    VARCHAR2(50 CHAR),
  CREATE_TIME TIMESTAMP(6)
)
;
comment on column AUTH_ORG_STRU_TYPE.STR_ID
  is '组织——ID';
comment on column AUTH_ORG_STRU_TYPE.STR_NAME
  is '组织——名称';
comment on column AUTH_ORG_STRU_TYPE.CREATE_TIME
  is '创建时间';
alter table AUTH_ORG_STRU_TYPE
  add constraint PK_STR_ID primary key (STR_ID);

create table AUTH_ORGNIZATION_RULE
(
  RULE_ID      VARCHAR2(50 CHAR) not null,
  ORG_SOURCE   VARCHAR2(1 CHAR),
  ORG_TARGET   VARCHAR2(1 CHAR),
  ORG_RULE     VARCHAR2(50 CHAR),
  STRU_TYPE_ID VARCHAR2(50 CHAR),
  CREATE_TIME  TIMESTAMP(6)
)
;
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
comment on column AUTH_ORGNIZATION_RULE.CREATE_TIME
  is '创建时间';
alter table AUTH_ORGNIZATION_RULE
  add constraint PK_RULE_ID primary key (RULE_ID);
alter table AUTH_ORGNIZATION_RULE
  add constraint FK_ORGRULE_STR_ID foreign key (STRU_TYPE_ID)
  references AUTH_ORG_STRU_TYPE (STR_ID);

create table BASE_ATTACHMENT
(
  RES_ID          VARCHAR2(50 CHAR) not null,
  ATT_CREATOR     VARCHAR2(50 CHAR),
  ATT_FILE_NAME   VARCHAR2(100 CHAR),
  ATT_FILE_SIZE   FLOAT,
  ATT_FILE_TYPE   VARCHAR2(50 CHAR),
  ATT_PATH        VARCHAR2(1000 CHAR),
  ATT_RELATION_ID VARCHAR2(50 CHAR),
  ATT_REMARK      VARCHAR2(200 CHAR),
  ATT_SORTNO      NUMBER(10),
  ATT_UPLOAD_IP   VARCHAR2(50 CHAR),
  ATT_UPLOAD_TIME TIMESTAMP(6),
  ATT_VIEW_NAME   VARCHAR2(200 CHAR),
  CREATE_TIME     TIMESTAMP(6),
  IS_USED         VARCHAR2(100),
  DX_FLAG         CHAR(1),
  DX_UUID         NUMBER
)
;
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
comment on column BASE_ATTACHMENT.CREATE_TIME
  is '创建时间';
alter table BASE_ATTACHMENT
  add constraint PK_ATT_RES_ID primary key (RES_ID);

create table BASE_DICTIONARY_TYPE
(
  DIC_GROUP_CODE VARCHAR2(60 CHAR) not null,
  DIC_GROUP_NAME VARCHAR2(60 CHAR) not null,
  CREATE_TIME    TIMESTAMP(6)
)
;
comment on column BASE_DICTIONARY_TYPE.DIC_GROUP_CODE
  is '字典——分组编号';
comment on column BASE_DICTIONARY_TYPE.DIC_GROUP_NAME
  is '字典——分组名称';
comment on column BASE_DICTIONARY_TYPE.CREATE_TIME
  is '创建时间';
alter table BASE_DICTIONARY_TYPE
  add constraint PK_GROUP_CODE_ID primary key (DIC_GROUP_CODE);

create table BASE_DICTIONARY
(
  DIC_ID         VARCHAR2(50 CHAR) not null,
  DIC_NAME       VARCHAR2(20 CHAR) not null,
  DIC_GROUP_CODE VARCHAR2(60 CHAR),
  DIC_CACHE_NAME VARCHAR2(20 CHAR),
  DIC_USE_CACHE  VARCHAR2(1 CHAR),
  DIC_VALUE      VARCHAR2(50 CHAR),
  DIC_MEMO       VARCHAR2(200 CHAR),
  CREATE_TIME    TIMESTAMP(6)
)
;
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
comment on column BASE_DICTIONARY.CREATE_TIME
  is '创建时间';
alter table BASE_DICTIONARY
  add constraint PK_DIC_ID primary key (DIC_ID);
alter table BASE_DICTIONARY
  add constraint FK63ABF6C4E83302BA foreign key (DIC_GROUP_CODE)
  references BASE_DICTIONARY_TYPE (DIC_GROUP_CODE);

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
  ORG_LOGO                   VARCHAR2(50 CHAR),
  CREATE_TIME                TIMESTAMP(6)
)
;
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
comment on column AUTH_ORG_DETAIL.CREATE_TIME
  is '创建时间';
alter table AUTH_ORG_DETAIL
  add constraint PK_DETAIL_ID primary key (ORG_DETAIL_ID);
alter table AUTH_ORG_DETAIL
  add constraint FK46A2D9233A359EBD foreign key (ORG_REG_CERTIFICATE)
  references BASE_ATTACHMENT (RES_ID);
alter table AUTH_ORG_DETAIL
  add constraint FK46A2D9234CE61CB4 foreign key (ORG_NATURE)
  references BASE_DICTIONARY (DIC_ID);
alter table AUTH_ORG_DETAIL
  add constraint FK_DETAIL_ATTA_ID foreign key (ORG_LOGO)
  references BASE_ATTACHMENT (RES_ID);

create table AUTH_ORG_EMPLOYEE
(
  EMP_ID            VARCHAR2(50 CHAR) not null,
  EMP_NAME          VARCHAR2(50 CHAR) not null,
  EMP_MOBILE        VARCHAR2(50 CHAR),
  EMP_EMAIL         VARCHAR2(50 CHAR),
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
  EMP_ORG_ID        VARCHAR2(50 CHAR),
  CREATE_TIME       TIMESTAMP(6),
  EMP_ADDRESS       VARCHAR2(200 CHAR),
  EMP_ZIPCODE       VARCHAR2(6 CHAR),
  EMP_FAX           VARCHAR2(20 CHAR),
  EMP_IDCARD        VARCHAR2(18 CHAR),
  EMP_SEX           VARCHAR2(1 CHAR),
  SHORT_SPELL_NAME  VARCHAR2(50)
)
;
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
comment on column AUTH_ORG_EMPLOYEE.CREATE_TIME
  is '创建时间';
comment on column AUTH_ORG_EMPLOYEE.EMP_ADDRESS
  is '联系地址';
comment on column AUTH_ORG_EMPLOYEE.EMP_ZIPCODE
  is '邮政编码';
comment on column AUTH_ORG_EMPLOYEE.EMP_FAX
  is '传真';
comment on column AUTH_ORG_EMPLOYEE.EMP_IDCARD
  is '身份证号';
comment on column AUTH_ORG_EMPLOYEE.EMP_SEX
  is '员工性别（0：女  1：男）';
alter table AUTH_ORG_EMPLOYEE
  add constraint PK_EMP_ID primary key (EMP_ID);
alter table AUTH_ORG_EMPLOYEE
  add constraint FK_EMP_DEPT_ID foreign key (EMP_DEPARTMENT_ID)
  references AUTH_ORGNIZATION (ORG_ID);
alter table AUTH_ORG_EMPLOYEE
  add constraint FK_EMP_POST_ID foreign key (EMP_POST_ID)
  references AUTH_ORGNIZATION (ORG_ID);

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
  ROL_SORT       NUMBER(19),
  CREATE_TIME    TIMESTAMP(6)
)
;
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
comment on column AUTH_ROLE.CREATE_TIME
  is '创建时间';
alter table AUTH_ROLE
  add constraint PK_ROL_ID primary key (ROL_ID);
alter table AUTH_ROLE
  add constraint FKFEEDA4AD53D50B06 foreign key (DEPT_ID)
  references AUTH_ORGNIZATION (ORG_ID);
alter table AUTH_ROLE
  add constraint FK_ROLE_PARENT_ID foreign key (ROL_PARENT_ID)
  references AUTH_ROLE (ROL_ID);

create table AUTH_ROLE_EXTEND
(
  EXTEND_ROLE_ID VARCHAR2(50 CHAR) not null,
  ROL_ID         VARCHAR2(50 CHAR) not null,
  CREATE_TIME    TIMESTAMP(6)
)
;
comment on column AUTH_ROLE_EXTEND.EXTEND_ROLE_ID
  is '角色——扩展ID';
comment on column AUTH_ROLE_EXTEND.ROL_ID
  is '角色——ID';
comment on column AUTH_ROLE_EXTEND.CREATE_TIME
  is '创建时间';
alter table AUTH_ROLE_EXTEND
  add constraint PK_EXTEND_ROLE_ID primary key (EXTEND_ROLE_ID, ROL_ID);

create table AUTH_ROLE_RESOURCE
(
  ROL_ID      VARCHAR2(50 CHAR),
  RES_ID      VARCHAR2(50 CHAR),
  ISCHECKED   VARCHAR2(1 CHAR) default '0',
  CREATE_TIME TIMESTAMP(6)
)
;
comment on column AUTH_ROLE_RESOURCE.ROL_ID
  is '角色ID';
comment on column AUTH_ROLE_RESOURCE.RES_ID
  is '资源ID';
comment on column AUTH_ROLE_RESOURCE.ISCHECKED
  is '是否全选：2代表部分选中，1代表全部选中。';
comment on column AUTH_ROLE_RESOURCE.CREATE_TIME
  is '创建时间';
alter table AUTH_ROLE_RESOURCE
  add constraint FK_ROLERES_ROL_ID foreign key (ROL_ID)
  references AUTH_ROLE (ROL_ID);

create table AUTH_USER
(
  USR_ID                  VARCHAR2(50 CHAR) not null,
  USR_NAME                VARCHAR2(20 CHAR) not null,
  USR_CA_SN               VARCHAR2(100 CHAR),
  USR_IS_CREDENTIAL       VARCHAR2(1 CHAR) default '0' not null,
  USR_IS_EXPIRED          VARCHAR2(1 CHAR) default '0' not null,
  USR_IS_LOCKED           VARCHAR2(1 CHAR) default '0' not null,
  USR_IS_MANAGER          VARCHAR2(1 CHAR) default '0' not null,
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
  EMP_ID                  VARCHAR2(50 CHAR),
  CREATE_TIME             TIMESTAMP(6),
  USR_EMAIL               VARCHAR2(50 CHAR),
  USR_PICTURE             VARCHAR2(36),
  USR_AVATAR              VARCHAR2(50)
)
;
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
comment on column AUTH_USER.USR_IS_MANAGER
  is '用户——是否经理';
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
comment on column AUTH_USER.CREATE_TIME
  is '创建时间';
comment on column AUTH_USER.USR_EMAIL
  is '员工邮箱';
comment on column AUTH_USER.USR_PICTURE
  is '用户头像';
comment on column AUTH_USER.USR_AVATAR
  is '用户头像';
alter table AUTH_USER
  add constraint PK_USR_ID primary key (USR_ID);
alter table AUTH_USER
  add constraint FK_USER_EMP_ID foreign key (EMP_ID)
  references AUTH_ORG_EMPLOYEE (EMP_ID);

create table AUTH_USER_GADGET
(
  OBJID       VARCHAR2(50 CHAR) not null,
  USER_ID     VARCHAR2(50 CHAR),
  GADGET_ID   VARCHAR2(50 CHAR),
  OPENABLE    VARCHAR2(1 CHAR),
  COLUMNINDEX NUMBER(1),
  ROWINDEX    NUMBER(1),
  MAXABLE     VARCHAR2(1 CHAR)
)
;

create table AUTH_USER_ROLE
(
  USR_ID VARCHAR2(50 CHAR) not null,
  ROL_ID VARCHAR2(50 CHAR) not null
)
;
comment on column AUTH_USER_ROLE.USR_ID
  is '用户ID';
comment on column AUTH_USER_ROLE.ROL_ID
  is '角色ID';
alter table AUTH_USER_ROLE
  add constraint PK_ROL_USR_ID primary key (ROL_ID, USR_ID);
alter table AUTH_USER_ROLE
  add constraint FK_USRROLE_ROLE_ID foreign key (ROL_ID)
  references AUTH_ROLE (ROL_ID);
alter table AUTH_USER_ROLE
  add constraint FK_USRROLE_USER_ID foreign key (USR_ID)
  references AUTH_USER (USR_ID);

create table BASE_BIZ_PARAM
(
  PARAM_ID         VARCHAR2(50 CHAR) not null,
  PARAM_NAME       VARCHAR2(100 CHAR),
  PARAM_KEY        VARCHAR2(100 CHAR),
  PARAM_DES        VARCHAR2(100 CHAR),
  PARAM_VALUE      VARCHAR2(200 CHAR),
  PARAM_VALUE_TYPE VARCHAR2(100 CHAR),
  PARAM_TYPE       VARCHAR2(100 CHAR),
  PARAM_MODULE     VARCHAR2(100 CHAR),
  PARAM_IS_SHOW    VARCHAR2(1 CHAR) default '1' not null,
  PARAM_SORT       NUMBER(19)
)
;
alter table BASE_BIZ_PARAM
  add constraint PARAM_KEY primary key (PARAM_ID);

create table BASE_DISTRICT
(
  DISTRICT_ID         VARCHAR2(50 CHAR) not null,
  DISTRICT_CODE       VARCHAR2(40 CHAR),
  DISTRICT_NAME       VARCHAR2(60 CHAR),
  DISTRICT_SHORT_NAME VARCHAR2(40 CHAR),
  DISTRICT_PARENT_ID  VARCHAR2(50 CHAR),
  CREATE_TIME         TIMESTAMP(6),
  IS_LEAF             CHAR(1),
  SORT                NUMBER(19),
  DIS_LEVEL           NUMBER(1)
)
;
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
comment on column BASE_DISTRICT.CREATE_TIME
  is '创建时间';
comment on column BASE_DISTRICT.DIS_LEVEL
  is '层级';
alter table BASE_DISTRICT
  add constraint PK_DISTRICT_ID primary key (DISTRICT_ID);

create table BASE_INDUSTRY
(
  INDUSTRY_ID        VARCHAR2(36 CHAR) not null,
  INDUSTRY_MEMO      VARCHAR2(100 CHAR),
  INDUSTRY_NAME      VARCHAR2(50 CHAR),
  INDUSTRY_SORT_NO   VARCHAR2(10 CHAR),
  INDUSTRY_PARENT_ID VARCHAR2(36 CHAR),
  CREATE_TIME        TIMESTAMP(6)
)
;
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
comment on column BASE_INDUSTRY.CREATE_TIME
  is '创建时间';
alter table BASE_INDUSTRY
  add constraint PK_INDUSTRY_ID primary key (INDUSTRY_ID);
alter table BASE_INDUSTRY
  add constraint FK_INDU_PARENT_ID foreign key (INDUSTRY_PARENT_ID)
  references BASE_INDUSTRY (INDUSTRY_ID);

create table BASE_LOGIN_LOGS
(
  LGN_ID       VARCHAR2(50 CHAR) not null,
  LGN_FROM_IP  VARCHAR2(20 CHAR),
  LGN_IN_TIME  TIMESTAMP(6),
  LGN_OUT_TIME TIMESTAMP(6),
  USR_ID       VARCHAR2(50 CHAR),
  CREATE_TIME  TIMESTAMP(6)
)
;
comment on table BASE_LOGIN_LOGS
  is '基础表_登录_日志';
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
comment on column BASE_LOGIN_LOGS.CREATE_TIME
  is '创建时间';
alter table BASE_LOGIN_LOGS
  add constraint PK_LOGIN_ID primary key (LGN_ID);
alter table BASE_LOGIN_LOGS
  add constraint FK_LOGIN_USR_ID foreign key (USR_ID)
  references AUTH_USER (USR_ID);

create table BASE_OPER_LOGS
(
  LOG_ID          VARCHAR2(50 CHAR) not null,
  LOG_CLASS_NAME  VARCHAR2(60 CHAR),
  LOG_RES_NAME    VARCHAR2(50 CHAR),
  LOG_URL         VARCHAR2(100 CHAR),
  LOG_USR_IP      VARCHAR2(20 CHAR),
  LOG_VISIT_DATE  TIMESTAMP(6),
  LOG_METHOD_NAME VARCHAR2(60 CHAR),
  ORGNAME         VARCHAR2(50 CHAR),
  LOGIN_LOG_ID    VARCHAR2(50 CHAR),
  LOG_USR_ID      VARCHAR2(50 CHAR),
  CREATE_TIME     TIMESTAMP(6)
)
;
comment on table BASE_OPER_LOGS
  is '基础表_操作_日志';
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
comment on column BASE_OPER_LOGS.CREATE_TIME
  is '创建时间';
alter table BASE_OPER_LOGS
  add constraint PK_LOG_ID primary key (LOG_ID);
alter table BASE_OPER_LOGS
  add constraint FK1CC681123F40EE15 foreign key (LOG_USR_ID)
  references AUTH_USER (USR_ID);
alter table BASE_OPER_LOGS
  add constraint FK_OPER_LOGIN_ID foreign key (LOGIN_LOG_ID)
  references BASE_LOGIN_LOGS (LGN_ID);

create table BASE_ORG_INDUSTRY
(
  ORG_IND_ID  VARCHAR2(50 CHAR) not null,
  INDUSTRY_ID VARCHAR2(50 CHAR),
  ORG_ID      VARCHAR2(50 CHAR)
)
;
comment on column BASE_ORG_INDUSTRY.ORG_IND_ID
  is '机构——行业ID';
comment on column BASE_ORG_INDUSTRY.INDUSTRY_ID
  is '行业——ID';
comment on column BASE_ORG_INDUSTRY.ORG_ID
  is '机构——ID';
alter table BASE_ORG_INDUSTRY
  add constraint PK_ORG_IND_ID primary key (ORG_IND_ID);
alter table BASE_ORG_INDUSTRY
  add constraint FK_ORGINDU_INDU_ID foreign key (INDUSTRY_ID)
  references BASE_INDUSTRY (INDUSTRY_ID);
alter table BASE_ORG_INDUSTRY
  add constraint FK_ORGINDU_ORG_ID foreign key (ORG_ID)
  references AUTH_ORGNIZATION (ORG_ID);

create table BASE_PURCATEGORY_TITLE
(
  CONTENT     VARCHAR2(2000),
  ID          VARCHAR2(36) not null,
  CREATE_TIME DATE
)
;
alter table BASE_PURCATEGORY_TITLE
  add constraint ID primary key (ID);



create table BASE_QUALITY_DEFINE
(
  PARENT_CLASS_ID   VARCHAR2(36),
  QUALITY_NAME      VARCHAR2(100),
  QUALITY_CODE      VARCHAR2(100),
  QUALITY_TYPE      VARCHAR2(100),
  REMARKS           VARCHAR2(2000),
  CREATOR           VARCHAR2(100),
  CREATE_TIME       DATE,
  MODIFIER          VARCHAR2(100),
  MODIFY_TIME       DATE,
  STATUS            VARCHAR2(100),
  IS_LEAF           VARCHAR2(100),
  SHORT_SPELL_NAME  VARCHAR2(100),
  SORT              NUMBER(13),
  QUALITY_DEFINE_ID VARCHAR2(36) not null
)
;
comment on table BASE_QUALITY_DEFINE
  is '资质分类信息   @gpcsoft.package packageDir="com.gpcsoft.eps.base.qualitymanagement"   @gpcsoft.page domain="qualitymanagement"   @hibernate.class table="BASE_QUALITY_DEFINE"   ';
comment on column BASE_QUALITY_DEFINE.PARENT_CLASS_ID
  is '父类对象     ';
comment on column BASE_QUALITY_DEFINE.QUALITY_NAME
  is '资质分类名称     ';
comment on column BASE_QUALITY_DEFINE.QUALITY_CODE
  is '资质分类编号     ';
comment on column BASE_QUALITY_DEFINE.QUALITY_TYPE
  is '资质类型：1企业资质、2行业资质、3产品资质、4人员资质、5项目资质、6其它。     ';
comment on column BASE_QUALITY_DEFINE.REMARKS
  is '说明     ';
comment on column BASE_QUALITY_DEFINE.STATUS
  is '删除标记,1.未删除 2.已删除     ';
comment on column BASE_QUALITY_DEFINE.IS_LEAF
  is '是否叶子节点     ';
comment on column BASE_QUALITY_DEFINE.SHORT_SPELL_NAME
  is '拼音简码，每个字的首字母，如 联想=LX     ';
comment on column BASE_QUALITY_DEFINE.SORT
  is '排序     ';
alter table BASE_QUALITY_DEFINE
  add constraint PK_QUALITY_PARAM_DEFINE primary key (QUALITY_DEFINE_ID);

create table BASE_QUALITY_GRADES
(
  GRADES_NAME       VARCHAR2(100),
  GRADES_CODE       VARCHAR2(100),
  GRADES_SORT       NUMBER(9),
  REMARKS           VARCHAR2(2000),
  CREATOR           VARCHAR2(100),
  CREATE_TIME       DATE,
  MODIFIER          VARCHAR2(100),
  MODIFY_TIME       DATE,
  QUALITY_DEFINE_ID VARCHAR2(36),
  STATUS            VARCHAR2(100),
  QUALITY_GRADES_ID VARCHAR2(36) not null
)
;
comment on table BASE_QUALITY_GRADES
  is '资质信息定义   @gpcsoft.package packageDir="com.gpcsoft.eps.base.qualitymanagement"   @gpcsoft.page domain="qualitymanagement"table="BASE_QUALITY_GRADES"   ';
comment on column BASE_QUALITY_GRADES.GRADES_NAME
  is '资质名称     ';
comment on column BASE_QUALITY_GRADES.GRADES_CODE
  is '资质编号     ';
comment on column BASE_QUALITY_GRADES.GRADES_SORT
  is '评级顺序     ';
comment on column BASE_QUALITY_GRADES.REMARKS
  is '说明';
comment on column BASE_QUALITY_GRADES.QUALITY_DEFINE_ID
  is '资质定义信息     ';
comment on column BASE_QUALITY_GRADES.STATUS
  is '删除标记，1.未删除 2.已删除     ';
alter table BASE_QUALITY_GRADES
  add constraint PK_QUALITY_GRADES primary key (QUALITY_GRADES_ID);

create table BASE_QUALITY_PARAM
(
  CREATOR           VARCHAR2(100),
  CREATE_TIME       DATE,
  MODIFIER          VARCHAR2(100),
  MODIFY_TIME       DATE,
  PARAM_NAME        VARCHAR2(100),
  PARAM_SORT        NUMBER(9),
  IS_REQUIRED       VARCHAR2(5),
  PARAM_VALUE_TYPE  VARCHAR2(100),
  PARAM_VALUE_LEN   NUMBER(9),
  SPARE_COLUMN1     VARCHAR2(100),
  SPARE_COLUMN2     VARCHAR2(100),
  SPARE_COLUMN3     VARCHAR2(100),
  QUALITY_PARAM_ID  VARCHAR2(36) not null,
  QUALITY_GRADES_ID VARCHAR2(36)
)
;
comment on table BASE_QUALITY_PARAM
  is '资质参数   @gpcsoft.package packageDir="com.gpcsoft.eps.base.qualitymanagement"   @gpcsoft.page domain="qualitymanagement"   @hibernate.class table="BASE_QUALITY_DEFINE_PARAM"   ';
comment on column BASE_QUALITY_PARAM.CREATOR
  is '创建人     ';
comment on column BASE_QUALITY_PARAM.CREATE_TIME
  is '创建时间     ';
comment on column BASE_QUALITY_PARAM.MODIFIER
  is '修改人     ';
comment on column BASE_QUALITY_PARAM.MODIFY_TIME
  is '修改时间     ';
comment on column BASE_QUALITY_PARAM.PARAM_NAME
  is '参数名称     ';
comment on column BASE_QUALITY_PARAM.IS_REQUIRED
  is '是否必填项     ';
comment on column BASE_QUALITY_PARAM.PARAM_VALUE_TYPE
  is '参数值类型：1.字符 2.整数 3.含小数 4.日期 5.附件 6.图片     ';
comment on column BASE_QUALITY_PARAM.PARAM_VALUE_LEN
  is '参数值长度     ';
comment on column BASE_QUALITY_PARAM.SPARE_COLUMN1
  is '备用字段     ';
comment on column BASE_QUALITY_PARAM.SPARE_COLUMN2
  is '备用字段     ';
comment on column BASE_QUALITY_PARAM.SPARE_COLUMN3
  is '备用字段     ';
alter table BASE_QUALITY_PARAM
  add constraint PK_QUALITY_PARAM primary key (QUALITY_PARAM_ID);

create table BASE_SEQUENCE_NUMBER
(
  SEN_ID          VARCHAR2(50 CHAR) not null,
  SEN_BIZ_NAME    VARCHAR2(100 CHAR),
  SEN_SEQUENCE_NO VARCHAR2(100 CHAR),
  CREATE_TIME     TIMESTAMP(6)
)
;
comment on column BASE_SEQUENCE_NUMBER.SEN_ID
  is '序列——ID';
comment on column BASE_SEQUENCE_NUMBER.SEN_BIZ_NAME
  is '序列——业务名称';
comment on column BASE_SEQUENCE_NUMBER.SEN_SEQUENCE_NO
  is '序列——号码';
comment on column BASE_SEQUENCE_NUMBER.CREATE_TIME
  is '创建时间';
alter table BASE_SEQUENCE_NUMBER
  add constraint PK_SEN_ID primary key (SEN_ID);





create table ECP_BASE_BULLETIN
(
  BULLETIN_ID       VARCHAR2(50) not null,
  TENDERID          VARCHAR2(50),
  BULLETIN_NO       VARCHAR2(50),
  BULLETIN_TYPE     VARCHAR2(2),
  TITLE             VARCHAR2(100),
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
  SRC_APP           VARCHAR2(50),
  SIGNUP_URL        VARCHAR2(200),
  SUBMIT_URL        VARCHAR2(200),
  REGION            VARCHAR2(2) default 00,
  DX_FLAG           CHAR(1),
  DX_UUID           NUMBER
)
;
comment on table ECP_BASE_BULLETIN
  is '公告/公示';
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
comment on column ECP_BASE_BULLETIN.SRC_APP
  is '公告来源';
comment on column ECP_BASE_BULLETIN.SIGNUP_URL
  is '报名URL';
comment on column ECP_BASE_BULLETIN.SUBMIT_URL
  is '投标URL';
comment on column ECP_BASE_BULLETIN.REGION
  is '区域(省市县等) 省00,市县01';
alter table ECP_BASE_BULLETIN
  add constraint PK_ECP_BASE_BULLETIN primary key (BULLETIN_ID);







create table ECP_BASE_PLAN_TEMPLATE
(
  ORG_ID        VARCHAR2(50),
  TEMPLATE_ID   VARCHAR2(50),
  MEMO          VARCHAR2(250),
  RULES         VARCHAR2(50),
  PROC_TYPE     CHAR(2),
  TEMPLATE_NAME VARCHAR2(50),
  CREATE_TIME   TIMESTAMP(6),
  CREATE_USER   VARCHAR2(50)
)
;

create table ECP_BASE_PLAN_TEMPLATE_TASK
(
  IS_LEAF     CHAR(1) default 1,
  RULES       VARCHAR2(50),
  ROLE_ID     VARCHAR2(50),
  RES_ID      VARCHAR2(50),
  TASK_NO     VARCHAR2(50),
  TASK_NAME   VARCHAR2(50),
  PARENT_ID   VARCHAR2(50),
  PRE_TASK    VARCHAR2(50),
  MEMO        VARCHAR2(250),
  TEMPLATE_ID VARCHAR2(50),
  TASK_ID     VARCHAR2(50),
  DURATION    NUMBER(10),
  RES_URL     VARCHAR2(100),
  TREE_PATH   VARCHAR2(100),
  TREE_LEVEL  CHAR(1),
  CREATE_TIME TIMESTAMP(6),
  CREATE_USER VARCHAR2(50),
  IS_AUTO_END CHAR(1)
)
;

create table ECP_PROJECT_CONTRACT
(
  PC_ID       VARCHAR2(50) not null,
  TENDERID    VARCHAR2(50) not null,
  CONTRACT_ID VARCHAR2(50) not null
)
;
comment on table ECP_PROJECT_CONTRACT
  is '项目合同中间表';
comment on column ECP_PROJECT_CONTRACT.PC_ID
  is '主键';
comment on column ECP_PROJECT_CONTRACT.TENDERID
  is '项目ID';
comment on column ECP_PROJECT_CONTRACT.CONTRACT_ID
  is '合同ID';
alter table ECP_PROJECT_CONTRACT
  add constraint PK_PC_ID primary key (PC_ID);

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
  SRC_APP              VARCHAR2(50),
  BUYER_NAME           VARCHAR2(50),
  SUPPLIER_NAME        VARCHAR2(50),
  SRC_URL              VARCHAR2(50)
)
;
comment on table ECP_PUB_CONTRACT
  is '采购合同';
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
comment on column ECP_PUB_CONTRACT.BUYER_NAME
  is '采购人名称';
comment on column ECP_PUB_CONTRACT.SUPPLIER_NAME
  is '供应商名称';
comment on column ECP_PUB_CONTRACT.SRC_URL
  is '来源地址';
alter table ECP_PUB_CONTRACT
  add constraint PK_CONTRACT_ID primary key (CONTRACT_ID);



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
;
comment on table ECP_PUB_FAVORITES
  is '收藏';
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
alter table ECP_PUB_FAVORITES
  add constraint PK_FAVORITES_ID primary key (FAVORITES_ID);



create table ECP_PUB_USER_SECURITY
(
  US_ID       VARCHAR2(50) not null,
  U_ID        VARCHAR2(50) not null,
  DIC_ID      VARCHAR2(50) not null,
  ANSWER      VARCHAR2(50) not null,
  CREATE_USER VARCHAR2(50),
  CREATE_TIME DATE
)
;
alter table ECP_PUB_USER_SECURITY
  add constraint PK_US_ID primary key (US_ID);

create table ECP_TASK
(
  TASK_ID     NVARCHAR2(50) not null,
  NAME        NVARCHAR2(200),
  TASK_TYPE   NVARCHAR2(2),
  OPERATOR    NVARCHAR2(50),
  CREATE_TIME DATE,
  ORIGIN      NVARCHAR2(50),
  URL         NVARCHAR2(100)
)
;

create table ECP_TENDER_APPLY_REC
(
  APPLYID       VARCHAR2(50) not null,
  TENDERID      VARCHAR2(50),
  SUPPLIER_ID   VARCHAR2(50),
  APPLYDATE     DATE,
  LINKER        VARCHAR2(50),
  IDCARD        VARCHAR2(50),
  LINKER_TEL    VARCHAR2(50),
  ADDRESS       VARCHAR2(100),
  ZIPCODE       VARCHAR2(50),
  AUDITSTATUS   CHAR(2),
  AUDITUSER     VARCHAR2(50),
  AUDITDATE     DATE,
  APPLYSTATUS   CHAR(2),
  UNAPPLYREASON VARCHAR2(100),
  UNAPPLYUSER   VARCHAR2(50),
  UNAPPLYDATE   DATE,
  MEMO          VARCHAR2(100),
  SUPPLIER_NAME VARCHAR2(50)
)
;
comment on table ECP_TENDER_APPLY_REC
  is '供应商报名';
comment on column ECP_TENDER_APPLY_REC.TENDERID
  is '招标项目ID';
comment on column ECP_TENDER_APPLY_REC.SUPPLIER_ID
  is '供应商ID';
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
comment on column ECP_TENDER_APPLY_REC.SUPPLIER_NAME
  is '供应商名称';
alter table ECP_TENDER_APPLY_REC
  add constraint PK_ECP_TENDER_APPLY_REC primary key (APPLYID);

create table ECP_TENDER_PROCFILE
(
  FILEID      VARCHAR2(50) not null,
  FILENO      VARCHAR2(50),
  TENDERID    VARCHAR2(50),
  FILETYPE    CHAR(2),
  KEYWORD     VARCHAR2(50),
  CONTENT     VARCHAR2(1000),
  BID_FILE    VARCHAR2(50),
  AUDITSTATUS CHAR(2),
  RELSTATUS   CHAR(2),
  RELDATE     DATE,
  RELUSER     VARCHAR2(50),
  CREDATE     DATE,
  CREUSER     VARCHAR2(50),
  USESTATUS   CHAR(2)
)
;
comment on table ECP_TENDER_PROCFILE
  is '招标文件';
comment on column ECP_TENDER_PROCFILE.FILENO
  is '编号';
comment on column ECP_TENDER_PROCFILE.TENDERID
  is '招标项目ID';
comment on column ECP_TENDER_PROCFILE.FILETYPE
  is '文件类型00：采购文件；01：澄清文件';
comment on column ECP_TENDER_PROCFILE.KEYWORD
  is '关键字';
comment on column ECP_TENDER_PROCFILE.CONTENT
  is '摘要';
comment on column ECP_TENDER_PROCFILE.BID_FILE
  is '投标文件[关联附件ID]';
comment on column ECP_TENDER_PROCFILE.AUDITSTATUS
  is '审核状态  00待提交   01 采购人确认通过  02  监察局审核通过 03 采购办审核通过';
comment on column ECP_TENDER_PROCFILE.RELSTATUS
  is '发布状态';
comment on column ECP_TENDER_PROCFILE.RELDATE
  is '发布时间';
comment on column ECP_TENDER_PROCFILE.RELUSER
  is '发布人';
comment on column ECP_TENDER_PROCFILE.CREDATE
  is '创建时间';
comment on column ECP_TENDER_PROCFILE.CREUSER
  is '创建人';
comment on column ECP_TENDER_PROCFILE.USESTATUS
  is '使用状态';
alter table ECP_TENDER_PROCFILE
  add constraint PK_ECP_T_PROCFILE primary key (FILEID);
create table ECP_TENDER_PROJECT
(
  TENDERID          VARCHAR2(50) not null,
  TENDERNO          VARCHAR2(50),
  AGREEMENTID       VARCHAR2(50),
  AGENCIESID        VARCHAR2(50),
  TENDERNAME        VARCHAR2(100),
  TENDERMETHOD      CHAR(2),
  SUMMARY           VARCHAR2(50),
  SIGNUP_START_DATE DATE,
  SIGNUP_END_DATE   DATE,
  SUBMIT_START_DATE DATE,
  SUBMIT_END_DATE   DATE,
  MEETINGROOM       VARCHAR2(50),
  PLAN_START_DATE   DATE,
  PLAN_END_DATE     DATE,
  START_DATE        DATE,
  END_DATE          DATE,
  PARENT_ID         VARCHAR2(50),
  MANAGERID         VARCHAR2(50),
  MONITORID         VARCHAR2(50),
  PROCESSSTATUS     CHAR(3),
  AUDITSTATUS       CHAR(2),
  CREDATE           DATE,
  CREUSER           VARCHAR2(50),
  TEMPLATE_ID       VARCHAR2(50),
  USESTATUS         CHAR(2),
  TASK_PLAN_ID      VARCHAR2(50),
  PURCATEGORY_CODES VARCHAR2(500),
  SRC_APP           VARCHAR2(50),
  PURCHASER_AMOUT   NUMBER,
  IMPLSTATUS        CHAR(2),
  BUYER_ID          VARCHAR2(50),
  APPLYID           VARCHAR2(50),
  PURCATEGORY_NAMES VARCHAR2(500),
  AGENCIESNAME      VARCHAR2(50),
  SRC_URL           VARCHAR2(50),
  ISDIVIDEPACK      VARCHAR2(50),
  BUYERSNAME        VARCHAR2(50)
)
;
comment on table ECP_TENDER_PROJECT
  is '招标项目';
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
comment on column ECP_TENDER_PROJECT.PURCHASER_AMOUT
  is '项目金额';
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
alter table ECP_TENDER_PROJECT
  add constraint PK_ECP_TENDER_PROJECT primary key (TENDERID);

create table ECP_TEND_M_TASK
(
  TEND_M_TASK_ID   VARCHAR2(50) not null,
  TENDERID         VARCHAR2(50),
  TASK_PLAN_SUB_ID VARCHAR2(50),
  BUYER_ID         VARCHAR2(50),
  BUYER_NAME       VARCHAR2(50)
)
;
comment on table ECP_TEND_M_TASK
  is '招标项目与申请书中间表[创建项目时使用]';
comment on column ECP_TEND_M_TASK.TENDERID
  is '招标项目ID';
comment on column ECP_TEND_M_TASK.TASK_PLAN_SUB_ID
  is '申报书条目ID';
comment on column ECP_TEND_M_TASK.BUYER_ID
  is '采购人ID';
comment on column ECP_TEND_M_TASK.BUYER_NAME
  is '采购人名称';
alter table ECP_TEND_M_TASK
  add constraint PK_E_TEND_M_T primary key (TEND_M_TASK_ID);
alter table ECP_TEND_M_TASK
  add constraint FK_TASK_M_TEND foreign key (TENDERID)
  references ECP_TENDER_PROJECT (TENDERID);

create table EPS_AGREEMENT_BARGAIN
(
  BARGAIN_ID   VARCHAR2(50) not null,
  BARGAIN_NO   VARCHAR2(15) not null,
  BUYER_ID     VARCHAR2(50) not null,
  GOODS_QTY    NUMBER(16,6) default 0,
  GOODS_TOTAL  NUMBER(16,6) default 0,
  BEGIN_DATE   DATE,
  END_DATE     DATE,
  CRE_TIME     DATE default sysdate,
  CRE_USER_ID  VARCHAR2(50),
  USE_STATUS   CHAR(2) default 00,
  MEMO         VARCHAR2(200),
  BARGAIN_NAME VARCHAR2(100) not null,
  TOTAL_CUT    NUMBER(16,6) default 0,
  BUYER_NAME   VARCHAR2(50)
)
;
comment on table EPS_AGREEMENT_BARGAIN
  is '议价单';
comment on column EPS_AGREEMENT_BARGAIN.BARGAIN_ID
  is 'ID
';
comment on column EPS_AGREEMENT_BARGAIN.BARGAIN_NO
  is '编号
';
comment on column EPS_AGREEMENT_BARGAIN.BUYER_ID
  is '采购人
';
comment on column EPS_AGREEMENT_BARGAIN.GOODS_QTY
  is '总数量
';
comment on column EPS_AGREEMENT_BARGAIN.GOODS_TOTAL
  is '总金额
';
comment on column EPS_AGREEMENT_BARGAIN.BEGIN_DATE
  is '开始时间
';
comment on column EPS_AGREEMENT_BARGAIN.END_DATE
  is '结束时间
';
comment on column EPS_AGREEMENT_BARGAIN.CRE_TIME
  is '创建时间
';
comment on column EPS_AGREEMENT_BARGAIN.CRE_USER_ID
  is '创建人
';
comment on column EPS_AGREEMENT_BARGAIN.USE_STATUS
  is '状态
00：未启动；01：已启动；02：已完成';
comment on column EPS_AGREEMENT_BARGAIN.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_BARGAIN.BARGAIN_NAME
  is '名称';
comment on column EPS_AGREEMENT_BARGAIN.TOTAL_CUT
  is '降价幅度';
comment on column EPS_AGREEMENT_BARGAIN.BUYER_NAME
  is '采购人名称';
alter table EPS_AGREEMENT_BARGAIN
  add constraint PK_BARGAIN primary key (BARGAIN_ID);



create table EPS_AGREEMENT_BARGAIN_ITEM
(
  BARGAIN_DTL_ID VARCHAR2(50) not null,
  BARGAIN_ID     VARCHAR2(50),
  GOODS_ID       VARCHAR2(50) not null,
  GOODS_QTY      NUMBER(16,6) default 0 not null,
  AGREE_PRICE    NUMBER(16,6) default 0 not null,
  MARKET_PRICE   NUMBER(16,6) default 0,
  GOODS_TOTAL    NUMBER(16,6) default 0 not null,
  MEMO           VARCHAR2(200),
  GOODS_UNIT     VARCHAR2(20),
  GOODS_PRICE    NUMBER(16,6) default 0 not null,
  SUPPLIER_ID    VARCHAR2(50) not null,
  SUPPLIER_NAME  VARCHAR2(50)
)
;
comment on table EPS_AGREEMENT_BARGAIN_ITEM
  is '议价单条目';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.BARGAIN_DTL_ID
  is 'ID
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.BARGAIN_ID
  is '议价ID
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.GOODS_ID
  is '商品ID
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.GOODS_QTY
  is '数量
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.AGREE_PRICE
  is '协议价
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.MARKET_PRICE
  is '市场价
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.GOODS_TOTAL
  is '金额
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.MEMO
  is '备注
';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.GOODS_UNIT
  is '计量单位';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.GOODS_PRICE
  is '单价';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.SUPPLIER_ID
  is '供应商id';
comment on column EPS_AGREEMENT_BARGAIN_ITEM.SUPPLIER_NAME
  is '供应商名称';
alter table EPS_AGREEMENT_BARGAIN_ITEM
  add constraint PK_BARGAIN_DTL primary key (BARGAIN_DTL_ID);
alter table EPS_AGREEMENT_BARGAIN_ITEM
  add constraint FK_BARGAIN_DTL_GOODS foreign key (GOODS_ID)
  references GOODS (GOODS_ID);

create table EPS_AGREEMENT_BARGAIN_SUPPLYER
(
  BARGAIN_ORG_ID VARCHAR2(50) not null,
  BARGAIN_ID     VARCHAR2(50),
  SUPPLIER_ID    VARCHAR2(50),
  SUPPLIER_NAME  VARCHAR2(50)
)
;
comment on table EPS_AGREEMENT_BARGAIN_SUPPLYER
  is '议价供应商';
comment on column EPS_AGREEMENT_BARGAIN_SUPPLYER.BARGAIN_ORG_ID
  is 'ID
';
comment on column EPS_AGREEMENT_BARGAIN_SUPPLYER.BARGAIN_ID
  is '议价ID
';
comment on column EPS_AGREEMENT_BARGAIN_SUPPLYER.SUPPLIER_ID
  is '供应商ID
';
comment on column EPS_AGREEMENT_BARGAIN_SUPPLYER.SUPPLIER_NAME
  is '供应商名称';
alter table EPS_AGREEMENT_BARGAIN_SUPPLYER
  add constraint PK_BARGAIN_ORG primary key (BARGAIN_ORG_ID);

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
  SRC_APP          VARCHAR2(50),
  SUPPLIER_NAME    VARCHAR2(50),
  BUYER_NAME       VARCHAR2(50),
  SRC_URL          VARCHAR2(50)
)
;
comment on table EPS_AGREEMENT_ORDER
  is '订单';
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
comment on column EPS_AGREEMENT_ORDER.SRC_APP
  is '来源';
comment on column EPS_AGREEMENT_ORDER.SUPPLIER_NAME
  is '供应商名称';
comment on column EPS_AGREEMENT_ORDER.BUYER_NAME
  is '采购人名称';
comment on column EPS_AGREEMENT_ORDER.SRC_URL
  is '来源地址';
alter table EPS_AGREEMENT_ORDER
  add constraint PK_ORDER_ID primary key (ORDER_ID);

create table EPS_AGREEMENT_ORDER_ITEM
(
  ORDER_DTL_ID  VARCHAR2(50) not null,
  ORDER_ID      VARCHAR2(50),
  SUPPLIER_ID   VARCHAR2(50) not null,
  GOODS_ID      VARCHAR2(50) not null,
  GOODS_QTY     NUMBER(16,6) default 0 not null,
  GOODS_PRICE   NUMBER(16,6) default 0 not null,
  AGREE_PRICE   NUMBER(16,6) default 0 not null,
  MARKET_PRICE  NUMBER(16,6) default 0,
  GOODS_TOTAL   NUMBER(16,6) default 0 not null,
  MEMO          VARCHAR2(200),
  GOODS_UNIT    VARCHAR2(20),
  SUPPLIER_NAME VARCHAR2(50)
)
;
comment on table EPS_AGREEMENT_ORDER_ITEM
  is '订单明细';
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
comment on column EPS_AGREEMENT_ORDER_ITEM.SUPPLIER_NAME
  is '供应商名称';
alter table EPS_AGREEMENT_ORDER_ITEM
  add constraint PK_ORDER_DTL primary key (ORDER_DTL_ID);
alter table EPS_AGREEMENT_ORDER_ITEM
  add constraint FK_ORDER_DTL_ORDER_ID foreign key (ORDER_ID)
  references EPS_AGREEMENT_ORDER (ORDER_ID);

create table EPS_AGREE_BARGAIN_GOODS_OPTION
(
  BARGAIN_OPT_ID   VARCHAR2(50) not null,
  OPTION_ID        VARCHAR2(100) not null,
  BARGAIN_DTL_ID   VARCHAR2(50),
  OPT_MARKET_PRICE NUMBER(16,6) default 0,
  OPT_AGREE_PRICE  NUMBER(16,6) default 0 not null,
  OPT_QTY          NUMBER(16,6) default 0 not null,
  OPT_UNIT         VARCHAR2(20),
  OPT_PRICE        NUMBER(16,6) default 0 not null
)
;
comment on table EPS_AGREE_BARGAIN_GOODS_OPTION
  is '议价商品选配';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.BARGAIN_OPT_ID
  is '记录号
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.OPTION_ID
  is '商品库选配Id
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.BARGAIN_DTL_ID
  is '议价商品明细id
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.OPT_MARKET_PRICE
  is '选配市场价
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.OPT_AGREE_PRICE
  is '选配协议价
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.OPT_QTY
  is '数量
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.OPT_UNIT
  is '计量单位
';
comment on column EPS_AGREE_BARGAIN_GOODS_OPTION.OPT_PRICE
  is '选配价';
alter table EPS_AGREE_BARGAIN_GOODS_OPTION
  add constraint PK_BARGAIN_OPT_ID primary key (BARGAIN_OPT_ID);

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
;
comment on table EPS_AGREE_ORDER_GOODS_OPTION
  is '采购商品选配';
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
alter table EPS_AGREE_ORDER_GOODS_OPTION
  add constraint PK_ORDER_OPT_ID primary key (ORDER_OPT_ID);

  
create table ES_BID_PACKAGE
(
  BID_P_ID       VARCHAR2(50) not null,
  BID_ID         VARCHAR2(50),
  SUB_PROJ_ID    VARCHAR2(50),
  BID_P_QUOTESUM NUMBER
);
comment on column ES_BID_PACKAGE.BID_ID
  is '投标ID';
comment on column ES_BID_PACKAGE.SUB_PROJ_ID
  is '所属包件';
comment on column ES_BID_PACKAGE.BID_P_QUOTESUM
  is '包件金额';
alter table ES_BID_PACKAGE
  add constraint PK_BID_P_ID primary key (BID_P_ID);
  
create table ES_BID
(
  BID_ID            VARCHAR2(36) not null,
  SUPPLIER_ID       VARCHAR2(36),
  PROJ_ID           VARCHAR2(36),
  BID_QUOTESUM      NUMBER(16,2),
  BID_BRAND         VARCHAR2(50),
  BID_STATEMENT     VARCHAR2(500),
  BID_LINKER        VARCHAR2(50),
  BID_LINKER_IDCARD VARCHAR2(50),
  SIGNDATAATTACHID  VARCHAR2(50),
  BID_REMARK        VARCHAR2(50),
  BID_SUBMIT_TYPE   VARCHAR2(50),
  CREATE_USER       VARCHAR2(36),
  CREATE_TIME       DATE,
  MODIFY_USER       VARCHAR2(36),
  MODIFY_TIME       DATE,
  USE_STATUS        CHAR(2),
  BID_FILE          VARCHAR2(50),
  BID_PACKAGE       VARCHAR2(100),
  SUPPLIER_NAME     VARCHAR2(50)
)
;
comment on column ES_BID.SUPPLIER_ID
  is '供应商ID';
comment on column ES_BID.PROJ_ID
  is '关联项目(或包组)ID';
comment on column ES_BID.BID_QUOTESUM
  is '报价总金额';
comment on column ES_BID.BID_BRAND
  is '投标品牌';
comment on column ES_BID.BID_STATEMENT
  is '投标声明';
comment on column ES_BID.BID_LINKER
  is '联系人
';
comment on column ES_BID.BID_LINKER_IDCARD
  is '联系人
身份证';
comment on column ES_BID.SIGNDATAATTACHID
  is '报价总体签名（注：一次签名）   没有在domain中标记';
comment on column ES_BID.BID_REMARK
  is '备注';
comment on column ES_BID.BID_SUBMIT_TYPE
  is '投标方式（注：网上投标、网下投标&lt;代理机构录入报名情况&gt;）';
comment on column ES_BID.CREATE_USER
  is '创建人';
comment on column ES_BID.CREATE_TIME
  is '创建时间';
comment on column ES_BID.MODIFY_USER
  is '修改人';
comment on column ES_BID.MODIFY_TIME
  is '修改时间';
comment on column ES_BID.USE_STATUS
  is '使用状态';
comment on column ES_BID.BID_FILE
  is '投标文件';
comment on column ES_BID.BID_PACKAGE
  is '包组';
comment on column ES_BID.SUPPLIER_NAME
  is '供应商名称';
alter table ES_BID
  add constraint PK_BID_ID primary key (BID_ID);

create table ES_BUY_WINNER
(
  BUY_W_ID      VARCHAR2(36) not null,
  SUPPLIER_ID   VARCHAR2(36),
  CREATE_USER   VARCHAR2(50),
  CREATE_TIME   DATE,
  MODIFY_USER   VARCHAR2(50),
  MODIFY_TIME   DATE,
  USE_STATUS    CHAR(2),
  RESULT_TYPE   CHAR(2),
  SUPPLIER_NAME VARCHAR2(50)
)
;
comment on column ES_BUY_WINNER.BUY_W_ID
  is '主键';
comment on column ES_BUY_WINNER.SUPPLIER_ID
  is '供应商ID';
comment on column ES_BUY_WINNER.CREATE_USER
  is '创建人';
comment on column ES_BUY_WINNER.CREATE_TIME
  is '创建时间';
comment on column ES_BUY_WINNER.MODIFY_USER
  is '修改人';
comment on column ES_BUY_WINNER.MODIFY_TIME
  is '修改时间';
comment on column ES_BUY_WINNER.USE_STATUS
  is '使用状态';
comment on column ES_BUY_WINNER.RESULT_TYPE
  is '成交类型[00:未成交;01:成交]';
comment on column ES_BUY_WINNER.SUPPLIER_NAME
  is '供应商名称';
alter table ES_BUY_WINNER
  add constraint BUY_W_ID primary key (BUY_W_ID)
  disable;

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
;
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
  ;

create table ECP_PUB_CONCERN_GROUP
(
  CONCERN_GROUP_ID NVARCHAR2(50) not null,
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_CONCERN_GROUP
  add constraint PK_CONCERN_GROUP_ID primary key (CONCERN_GROUP_ID)
  using index 
 ;






