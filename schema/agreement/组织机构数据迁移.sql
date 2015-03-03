
--迁移【采购人表】中的数据：
--数据来源：BUY_BUYER  新表名：BIZ_BUYER
--注：机构ID（ORG_INFO_ID ==> COMPANY_ID）
INSERT INTO BIZ_BUYER
(BUYER_ID, PARENT_COMPANY_ID, BUYER_PUR_SBJCT, EXEC_DEPT,BUYER_CMPT_DEP_TYPE, BUYER_BUDGET_CODE, BUYER_UNIT_TYPE,    BUYER_FUND_DEPT, CREATOR,CREATE_TIME,MODIFIER,MODIFY_TIME,AUDITOR,    AUDIT_TIME, AUDIT_STATUS,AUDIT_OPINION,COMPANY_ID,USE_STATUS) SELECT
 b.BUYER_ID, b.PARENT_UNIT_ID,    b.PUR_SBJCT,       b.EXEC_DEPT,b.CMPT_DEP_TYPE,       b.BUDGET_CODE,       b.UNIT_TYPE,      b.FUND_DEPT,       b.CREATOR,b.CREATE_TIME,b.MODIFIER,b.MODIFY_TIME,b.VERIFIER_ID,b.VERIFY_TIME,b.AUDIT_STATUS,b.OPINION,b.ORG_INFO_ID,case when b.audit_status='02' then '01' else '00' end case
FROM BUY_BUYER b
join ORG_INFO o on b.BUYER_ID = o.BUYER_ID
and o.use_status != '02'
and o.is_off != '2' ;

--=========================================================================================================--

--修改是否厂商字段值
UPDATE ORG_INFO T SET T.ISMANUFACTURER='2'
       WHERE T.Ismanufacturer='0,2'
       or t.ismanufacturer='1,2'

--迁移【供应商表】中的数据
--数据来源：SPL_SUPPLIER,ORG_INFO  新表名：BIZ_SUPPLIER
INSERT INTO BIZ_SUPPLIER
--主键,         所属机构 FK,   主营产品范围,           工商注册地址住所,     工商注册发证机关,      工商注册营业范围,       工商注册号,        工商注册日期,      工商注册有效期,      营业执照结束日期,       营业执照开始日期,         经营地址,              组织机构证颁发单位,      组织机构证登记号,      组织机构证开始日期,       组织机构证结束日期,    开业日期,           企业产能,             经营范围code[取品目表],                                         经营范围name,                                               国税登记编号,         税务登记证国税电脑编码,    地税登记编号,          税务登记证地税电脑编码,     创建人,    创建时间,      修改人,     修改时间,      审核人,        审核状态,       审核时间,      是否厂家,状态,           审核意见
(SUPPLIER_ID,   COMPANY_ID,    SUPPLIER_MAIN_PRODUCTS, SUPPLIER_REG_ADDRESS, SUPPLIER_REG_AUTH_ORG, SUPPLIER_REG_BUS_SCOPE, SUPPLIER_REG_CODE, SUPPLIER_REG_DATE, SUPPLIER_REG_TO_DATE, SUPPLIER_TRADE_END_DATE,SUPPLIER_TRADE_START_DATE,SUPPLIER_TRADE_ADDRESS,SUPPLIER_UNIT_AWARD_UNIT,SUPPLIER_UNIT_REG_NMBR,SUPPLIER_UNIT_START_DATE, SUPPLIER_UNIT_END_DATE,SUPPLIER_BEGAINDATE,SUPPLIER_ENTCAPACITY, SUPPLIER_BID_RANGE_CODE,                                        SUPPLIER_BID_RANGE_NAME,                                    SUPPLIER_NAT_TAX_NMBR,SUPPLIER_NAT_TAX_CMPT_NMBR,SUPPLIER_LAND_TAX_NMBR,SUPPLIER_LAND_TAX_CMPT_NMBR,CREATOR,   CREATE_TIME,   MODIFIER,   MODIFY_TIME,   AUDITOR,       AUDIT_STATUS,   AUDIT_TIME,    SUPPLIER_ISMANUFACTURER, AUDIT_OPINION,USE_STATUS) SELECT
 s.SUPPLIER_ID, s.ORG_INFO_ID, o.MAIN_PRODUCTS,        s.REG_ADDRESS,        s.REG_AUTH_ORG,        s.REG_BUS_SCOPE,        s.REG_CODE,        s.REG_DATE,        s.REG_TO_DATE,       s.TRADE_END_DATE,       s.TRADE_START_DATE,       s.UNIT_ADDRESS,        s.ORG_UNIT_AWARD_UNIT,   s.ORG_UNIT_REG_NMBR,   s.ORG_UNIT_START_DATE,    s.ORG_UNIT_END_DATE,   o.BEGAINDATE,       o.ENTCAPACITY,        substr(o.BID_FOR_RANGE, 0, instr(o.BID_FOR_RANGE,'##||##')-1),  substr(o.BID_FOR_RANGE, instr(o.BID_FOR_RANGE,'##||##')+6), s.NAT_TAX_NMBR,       s.NAT_TAX_CMPT_NMBR,       s.LAND_TAX_NMBR,       s.LAND_TAX_CMPT_NMBR,       s.CREATOR, s.CREATE_TIME, s.MODIFIER, s.MODIFY_TIME, s.VERIFIER_ID, s.AUDIT_STATUS, s.VERIFY_TIME, o.ISMANUFACTURER,        s.OPINION  ,case when s.audit_status='02' then '01' else '00' end case
FROM SPL_SUPPLIER s 
left join ORG_INFO o on s.SUPPLIER_ID = o.SUPPLIER_ID
and o.use_status != '02'
and o.is_off != '2' ;

--=========================================================================================================--


--迁移【代理机构表】中的数据
--数据来源：AGENCY_AGENT  新表名：BIZ_AGENT
INSERT INTO BIZ_AGENT
--主键,      所属机构,      代理机构类型, 从业人员总数,      中级职称技术人员数,       高级职称技术人员数,      注册执业人员数,      负债总额,        资产总额,           评标地址,                经济性质,         主管单位-上级主管部门,行政级别,      近三年承担代理项目,  近三年经营代理情况, 创建人,    创建时间,      修改人,     修改时间,      审核状态,       审核人,        审核时间,      备注,使用状态, 审核意见
(AGENT_ID,   COMPANY_ID,    AGENT_TYPE,   AGENT_TOTAL_NMBR,  AGENT_MIDDLE_TCHLG_NMBR,  AGENT_SENIOR_TCHLG_NMBR, AGENT_REG_PRCTS_NMBR,AGENT_TOTAL_DEBT,AGENT_TOTAL_CAPITAL,AGENT_BID_PRPS_EVLT_ADDR,AGENT_ECNM_NATURE,PARENT_COMPANY_ID,   AGENT_ADMIN_GRD,AGENT_UNDTK_BID_PROJ,AGENT_BUSS_CNDT,    CREATOR,   CREATE_TIME,   MODIFIER,   MODIFY_TIME,   AUDIT_STATUS,   AUDITOR,       AUDIT_TIME,    AGENT_REMARKS, AUDIT_OPINION,USE_STATUS) SELECT
 a.AGENT_ID, a.ORG_INFO_ID, a.AGENT_TYPE, a.PRCT_TOTAL_NMBR, a.MIDDLE_TITLE_TCHST_NMBR,a.HIGH_TITLE_TCHST_NMBR, a.REG_PRCTS_NMBR,    a.TOTAL_CHARGE,  a.TOTAL_ASSETS,     a.BID_PRPS_EVLT_ADDR,    a.ECNM_NATURE,    a.UNIT_IN_CHARGE_ID, a.ADMIN_GRD,    a.UNDTK_BID_PROJ,    a.AGENCY_BUSS_CNDT, a.CREATOR, a.CREATE_TIME, a.MODIFIER, a.MODIFY_TIME, a.AUDIT_STATUS, a.VERIFIER_ID, a.VERIFY_TIME, a.REMARKS,     a.OPINION    ,case when a.audit_status='02' then '01' else '00' end case
FROM AGENCY_AGENT a
left join ORG_INFO o on a.AGENT_ID = o.AGENCY_ID
and o.use_status != '02'
and o.is_off != '2' ;

--=========================================================================================================--

--删除无效的区域编码
delete from BASE_DISTRICT d where length(d.district_code) > 6 ;


--迁移【机构信息表】中的数据
--数据来源：ORG_INFO，AUTH_ORGNIZATION, AUTH_USER  新表名：PUB_COMPANY
INSERT INTO PUB_COMPANY
--主键,         机构编码,     机构名称,     机构简称,           单位性质,         所属行业 FK,             联系人(机构管理员）FK, 法定代表人,        联系电话,    邮箱,          传真,        机构地址,        行政区域,                                                     区域编码,          网址,            logo图片,     代理机构ID,  采购人ID,   供应商ID,     监管机构ID,      创建人,   创建时间,     修改人,    修改时间,     使用状态,    拼音简码
(COMPANY_ID,    COMPANY_CODE, COMPANY_NAME, COMPANY_SHORT_NAME, COMPANY_ENT_PRPT, COMPANY_BELONG_INDUSTRY, EMP_ID,                COMPANY_CROPORATE, COMPANY_TEL, COMPANY_EMAIL, COMPANY_FAX, COMPANY_ADDRESS, COMPANY_AREA_NAME,                                            COMPANY_AREA_CODE, COMPANY_WEB_URL, COMPANY_LOGO, AGENCY_ID,   BUYER_ID,   SUPPLIER_ID,  SUPERVISION_ID,  CREATOR,  CREATE_TIME,  MODIFIER,  MODIFY_TIME,  USE_STATUS,  COMPANY_SPELL_NAME) SELECT
 o.ORG_INFO_ID, o.ORG_CODE,   o.ORG_NAME,   n.ORG_SHORT_NAME,   o.ENT_PRPT,       o.BELONGINDUSTRY,        u.EMP_ID,              n.ORG_CROPORATE,   n.ORG_TEL,   n.ORG_EMAIL,   n.ORG_FAX,   n.ORG_ADDRESS,   substr(o.DISTRICT_VALUE, instr(o.DISTRICT_VALUE,'##||##')+6), d.DISTRICT_CODE,   o.WEB_URL,       o.ORG_LOGO,   o.AGENCY_ID, o.BUYER_ID, o.SUPPLIER_ID,o.SUPERVISION_ID,o.CREATOR,o.CREATE_TIME,o.MODIFIER,o.MODIFY_TIME,o.USE_STATUS,n.SHORT_SPELL_NAME
FROM ORG_INFO o join AUTH_ORGNIZATION n
on o.COMPANY_ID = n.ORG_ID
and o.use_status != '02'
and o.is_off != '2'
left join AUTH_USER u
on o.USER_ID = u.USR_ID
left join BASE_DISTRICT d
on n.ORG_TOWN = d.DISTRICT_ID ;

--=========================================================================================================--


--迁移【机构扩展表】中的数据
--数据来源：ORG_INFO, SPL_SUPPLIER  新表名：PUB_COMPANY_EXT
INSERT INTO PUB_COMPANY_EXT
--主键，         组织机构代码证号,     注册资金资本,            人员规模,               开户银行名称,          开户银行账号,开户人姓名,  企业简介（中）,      创建人,   创建时间,     修改人,    修改时间
(COMPANY_EXT_ID, COMPANY_EXT_ORG_CODE, COMPANY_EXT_REG_CAPITAL, COMPANY_EXT_UNIT_SCAPE, COMPANY_EXT_OPEN_BANK, COMPANY_EXT_OPEN_ACCOUNT, COMPANY_EXT_DESC_CN, CREATOR,  CREATE_TIME,  MODIFIER,  MODIFY_TIME) SELECT
 o.ORG_INFO_ID,  o.ORG_CODE,           s.REG_CAPITAL,           o.UNIT_SCAPE,           s.OPEN_BANK,           s.OPEN_ACCOUNT,           o.DESC_CN,           o.CREATOR,o.CREATE_TIME,o.MODIFIER,o.MODIFY_TIME
FROM ORG_INFO o 
join AUTH_ORGNIZATION n
on o.COMPANY_ID = n.ORG_ID
and o.use_status != '02'
and o.is_off != '2'
left join SPL_SUPPLIER s
on o.SUPPLIER_ID = s.SUPPLIER_ID ;

--字段[开户人姓名COMPANY_EXT_OPEN_ACCOUNT_NAME]未迁移
--=========================================================================================================--


--迁移【组织结构】表中的数据
--数据来源：AUTH_ORGNIZATION,ORG_INFO  新表名：PUB_ORGNIZATION

--迁移部门数据（ORG_TYPE=2）
INSERT INTO PUB_ORGNIZATION
--机构ID,  机构名称,   机构简称,         机构类型,联系人, 所属机构,      上级机关,主管,   路径,       是否叶子节点,  树形层级,    排序,       电子邮箱,    创建人,   创建时间,      修改人,     修改时间,      使用状态
(ORG_ID,   ORG_NAME,   ORG_SHORT_NAME,   ORG_TYPE,        COMPANY_ID,                     ORG_PATH,   ORG_IS_LEAF,   ORG_LEVEL,   ORG_SORT,   ORG_EMAIL,   CREATOR,  CREATE_TIME,   MODIFIER,   MODIFY_TIME,   USE_STATUS) SELECT
 n.ORG_ID, n.ORG_NAME, n.ORG_SHORT_NAME, n.ORG_TYPE,      o.ORG_INFO_ID,                  n.ORG_PATH, n.ORG_IS_LEAF, n.ORG_LEVEL, n.ORG_SORT, n.ORG_EMAIL, o.CREATOR,o.CREATE_TIME, o.MODIFIER, o.MODIFY_TIME, o.USE_STATUS
FROM AUTH_ORGNIZATION n join AUTH_ORGNIZATION pn 
on n.org_parent_id = pn.org_id
join ORG_INFO o
on pn.ORG_ID = o.COMPANY_ID
and n.ORG_TYPE = '2'
and o.use_status != '02'
and o.is_off != '2' ;

--迁移岗位数据（ORG_TYPE=3）
INSERT INTO PUB_ORGNIZATION
--机构ID,  机构名称,   机构简称,         机构类型,联系人, 所属机构,      上级机关,主管,   路径,       是否叶子节点,  树形层级,    排序,       电子邮箱,    创建人,   创建时间,      修改人,     修改时间,      使用状态
(ORG_ID,   ORG_NAME,   ORG_SHORT_NAME,   ORG_TYPE,        COMPANY_ID,    ORG_PARENT_ID,   ORG_PATH,   ORG_IS_LEAF,   ORG_LEVEL,   ORG_SORT,   ORG_EMAIL,   CREATOR,  CREATE_TIME,   MODIFIER,   MODIFY_TIME,   USE_STATUS) SELECT
 n.ORG_ID, n.ORG_NAME, n.ORG_SHORT_NAME, n.ORG_TYPE,      nn.COMPANY_ID, n.ORG_PARENT_ID, n.ORG_PATH, n.ORG_IS_LEAF, n.ORG_LEVEL, n.ORG_SORT, n.ORG_EMAIL, o.CREATOR,o.CREATE_TIME, o.MODIFIER, o.MODIFY_TIME, o.USE_STATUS
FROM AUTH_ORGNIZATION n 
join PUB_ORGNIZATION nn 
on n.ORG_PARENT_ID = nn.ORG_ID
join ORG_INFO o
on o.ORG_INFO_ID = nn.COMPANY_ID
and n.ORG_TYPE = '3'
and o.use_status != '02'
and o.is_off != '2' ;

--机构角色
--=========================================================================================================--
delete from auth_org_role r;

INSERT INTO auth_org_role
(ORG_ROLE_ID, ROL_ID, ORG_ID) select
substr(orgid, -16) || substr(rolid, -16), rolid, orgid
from (
    select distinct o.org_info_id orgid, ur.rol_id rolid
    from auth_user u 
    join auth_user_role ur on u.usr_id = ur.usr_id
    join auth_org_employee e on u.emp_id = e.emp_id
    join auth_orgnization n on e.emp_company_id = n.org_id
    join org_info o on o.company_id = n.org_id
);


--字段[联系人ORG_CONTACTOR][主管ORG_SUPERVISOR]未迁移
--=========================================================================================================--
--需要去掉的外键约束：
--角色表中的外键
alter table AUTH_ROLE drop constraint FKFEEDA4AD53D50B06 ;

--角色冲突表中的外键
alter table AUTH_CONFLICT_ROLE drop constraint FK_CONFLICT_ORG_ID ;


--员工表中外键
alter table AUTH_ORG_EMPLOYEE drop constraint FK_EMP_DEPT_ID ;
alter table AUTH_ORG_EMPLOYEE drop constraint FK_EMP_POST_ID ;

--账号表中的外键
alter table AUTH_USER drop constraint FK_USER_EMP_ID ;

--=========================================================================================================--

--处理特殊情况
--select * from org_info o where o.company_id = '2c9087922c39746b012c39ab22e5015d' for update ;


--修改【员工表】AUTH_ORG_EMPLOYEE中的所属公司的外键值
--创建临时员工表
create table EMPLOYEE_TEMP as select * from AUTH_ORG_EMPLOYEE ;

--删除员工表中的所有数据
delete from AUTH_ORG_EMPLOYEE ;

--迁移员工表中的数据
INSERT INTO AUTH_ORG_EMPLOYEE
(EMP_ID,  EMP_NAME,   EMP_MOBILE,   EMP_EMAIL,   EMP_MSN,   EMP_QQ,   EMP_TEL_OFFICE,   EMP_TEL_HOME,   EMP_SORT,   EMP_CREATED_DATE,   EMP_COMPANY_ID,EMP_DEPARTMENT_ID,EMP_POST_ID,EMP_NUMBER,EMP_ORG_ID,CREATE_TIME,EMP_ADDRESS,EMP_ZIPCODE,EMP_FAX,EMP_IDCARD,EMP_SEX) SELECT
e.EMP_ID, e.EMP_NAME, e.EMP_MOBILE, e.EMP_EMAIL, e.EMP_MSN, e.EMP_QQ, e.EMP_TEL_OFFICE, e.EMP_TEL_HOME, e.EMP_SORT, e.EMP_CREATED_DATE, o.ORG_INFO_ID, e.EMP_DEPARTMENT_ID, e.EMP_POST_ID, e.EMP_NUMBER, e.EMP_ORG_ID, e.CREATE_TIME, e.EMP_ADDRESS, e.EMP_ZIPCODE, e.EMP_FAX, e.EMP_IDCARD, e.EMP_SEX
FROM EMPLOYEE_TEMP e 
join org_info o
on e.emp_company_id = o.company_id
and o.use_status != '02'
and o.is_off != '2' ;

commit;

--删除临时员工表
drop table EMPLOYEE_TEMP ;

--=========================================================================================================--
--删除旧表
drop table BUY_BUYER;

drop table SPL_SUPPLIER;

drop table AGENCY_AGENT;

drop table ORG_INFO;

drop table AUTH_ORGNIZATION;

--删除商圈会员表
drop table ECP_BUSINESS_MEMBER;

--给默认机构管理员角色授权(小额交易室等)
insert into gxst.auth_role_resource 
select t.* from sr_gxst.auth_role_resource t,auth_resource r where r.res_id=t.res_id 
       and (
       r.res_name like '%小额交易室%' or
       r.res_name like '%我的商品%' or
       r.res_name like '%我的品牌%' or
       r.res_name like '%我的采购需求%'
       )
and t.rol_id=(select o.rol_id from sr_gxst.auth_role o where o.rol_en_name='default_org_role');

