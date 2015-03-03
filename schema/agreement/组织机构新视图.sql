CREATE OR REPLACE FORCE VIEW "ORG_INFO" ("CREATOR", "CREATE_TIME", "MODIFIER", "MODIFY_TIME", "COMPANY_ID", "AGENCY_ID", "BUYER_ID", "SUPERVISION_ID", "SUPPLIER_ID", "EMP_ID", "COMPANY_CODE", "COMPANY_NAME", "COMPANY_SHORT_NAME", "COMPANY_TEL", "COMPANY_EMAIL", "COMPANY_FAX", "COMPANY_ADDRESS", "COMPANY_POST_CODE", "COMPANY_CROPORATE", "USE_STATUS", "COMPANY_LOGO", "COMPANY_AREA_CODE", "COMPANY_AREA_NAME", "COMPANY_ENT_PRPT", "COMPANY_BELONG_INDUSTRY", "COMPANY_WEB_URL", "SUPPLIER_BID_RANGE_CODE", "SUPPLIER_MAIN_PRODUCTS", "SUPPLIER_ISMANUFACTURER", "AUDIT_OPINION", "COMPANY_EXT_DESC_CN", "COMPANY_EXT_UNIT_SCAPE", "COMPANY_EXT_REG_CAPITAL", "USR_ID", "IS_OFF", "AUDIT_STATUS", "AUDIT_TYPE") AS 
  select
        pc.creator,
        pc.create_time,
        pc.modifier,
        pc.modify_time,
        pc.company_id ,
        pc.agency_id,
        pc.buyer_id,
        pc.supervision_id,
        pc.supplier_id,
        pc.emp_id,
        pc.company_code,
        pc.company_name,
        pc.company_short_name,
        pc.company_tel,
        pc.company_email,
        pc.company_fax,
        pc.company_address,
        pc.company_post_code,
        pc.company_croporate,
        pc.use_status,
        pc.company_logo,
        pc.company_area_code,
        pc.company_area_name,
        pc.company_ent_prpt,
        pc.company_belong_industry,
        pc.company_web_url,
        s.supplier_bid_range_code,
        s.supplier_main_products,
        s.supplier_ismanufacturer,
        s.audit_opinion,
        pe.company_ext_desc_cn,
        pe.company_ext_unit_scape,
        pe.company_ext_reg_capital,
        u.usr_id,
        '1' is_off,
        '02' audit_status,
        '01' audit_type
from
pub_company pc
left join biz_supplier s on  s.supplier_id = pc.supplier_id
left join pub_company_ext pe on  pe.company_ext_id = pc.company_id
left join auth_user u on u.emp_id = pc.emp_id
;

CREATE OR REPLACE FORCE VIEW "SPL_SUPPLIER" ("SUPPLIER_ID", "COMPANY_ID", "SUPPLIER_MAIN_PRODUCTS", "SUPPLIER_REG_ADDRESS", "SUPPLIER_REG_AUTH_ORG", "SUPPLIER_REG_BUS_SCOPE", "SUPPLIER_REG_CODE", "SUPPLIER_REG_DATE", "SUPPLIER_REG_TO_DATE", "SUPPLIER_TRADE_END_DATE", "SUPPLIER_TRADE_START_DATE", "SUPPLIER_TRADE_ADDRESS", "SUPPLIER_UNIT_AWARD_UNIT", "SUPPLIER_UNIT_REG_NMBR", "SUPPLIER_UNIT_START_DATE", "SUPPLIER_UNIT_END_DATE", "SUPPLIER_BEGAINDATE", "SUPPLIER_ENTCAPACITY", "SUPPLIER_BID_RANGE_CODE", "SUPPLIER_BID_RANGE_NAME", "SUPPLIER_NAT_TAX_NMBR", "SUPPLIER_NAT_TAX_CMPT_NMBR", "SUPPLIER_LAND_TAX_NMBR", "SUPPLIER_LAND_TAX_CMPT_NMBR", "CREATOR", "CREATE_TIME", "MODIFIER", "MODIFY_TIME", "AUDITOR", "AUDIT_STATUS", "AUDIT_TIME", "AUDIT_OPINION", "SUPPLIER_ISMANUFACTURER", "USE_STATUS", "EVAL_SUM") AS 
  select
        s."SUPPLIER_ID",s."COMPANY_ID",s."SUPPLIER_MAIN_PRODUCTS",s."SUPPLIER_REG_ADDRESS",s."SUPPLIER_REG_AUTH_ORG",s."SUPPLIER_REG_BUS_SCOPE",s."SUPPLIER_REG_CODE",s."SUPPLIER_REG_DATE",s."SUPPLIER_REG_TO_DATE",s."SUPPLIER_TRADE_END_DATE",s."SUPPLIER_TRADE_START_DATE",s."SUPPLIER_TRADE_ADDRESS",s."SUPPLIER_UNIT_AWARD_UNIT",s."SUPPLIER_UNIT_REG_NMBR",s."SUPPLIER_UNIT_START_DATE",s."SUPPLIER_UNIT_END_DATE",s."SUPPLIER_BEGAINDATE",s."SUPPLIER_ENTCAPACITY",s."SUPPLIER_BID_RANGE_CODE",s."SUPPLIER_BID_RANGE_NAME",s."SUPPLIER_NAT_TAX_NMBR",s."SUPPLIER_NAT_TAX_CMPT_NMBR",s."SUPPLIER_LAND_TAX_NMBR",s."SUPPLIER_LAND_TAX_CMPT_NMBR",s."CREATOR",s."CREATE_TIME",s."MODIFIER",s."MODIFY_TIME",s."AUDITOR",s."AUDIT_STATUS",s."AUDIT_TIME",s."AUDIT_OPINION",s."SUPPLIER_ISMANUFACTURER",s."USE_STATUS",
        (select nvl(avg(e.summary_score),0) from ecp_pub_evaluate e where e.orginfo_id = s.company_id) eval_sum
from
biz_supplier s
;


CREATE OR REPLACE FORCE VIEW "BUY_BUYER" ("BUYER_ID", "COMPANY_ID", "PARENT_COMPANY_ID", "BUYER_PUR_SBJCT", "EXEC_DEPT", "BUYER_CMPT_DEP_TYPE", "BUYER_BUDGET_CODE", "BUYER_UNIT_TYPE", "BUYER_FUND_DEPT", "CREATOR", "CREATE_TIME", "MODIFIER", "MODIFY_TIME", "AUDITOR", "AUDIT_TIME", "AUDIT_STATUS", "AUDIT_OPINION", "USE_STATUS", "DEAL_TIME", "DEAL_TOTAL", "EVAL_SUM") AS 
  select
        b.BUYER_ID,
        b.COMPANY_ID,
        b.PARENT_COMPANY_ID,
        b.BUYER_PUR_SBJCT,
        b.EXEC_DEPT,
        b.BUYER_CMPT_DEP_TYPE,
        b.BUYER_BUDGET_CODE,
        b.BUYER_UNIT_TYPE,
        b.BUYER_FUND_DEPT,
        b.CREATOR,
        b.CREATE_TIME,
        b.MODIFIER,
        b.MODIFY_TIME,
        b.AUDITOR,
        b.AUDIT_TIME,
        b.AUDIT_STATUS,
        b.AUDIT_OPINION,
        b.USE_STATUS,
        (select max(c.contract_begin_time) from EPS_PUB_CONTRACT c where c.buyer_id = b.company_id and c.use_status = '01') deal_time,
        (select sum(GOODS_TOTAL) from EPS_AGREEMENT_ORDER o ,EPS_PUB_CONTRACT c where o.contract_id = c.contract_id and c.USE_STATUS='01' and c.buyer_id = b.company_id) deal_total,
        (select nvl(avg(e.summary_score),0) from ecp_pub_evaluate e where e.orginfo_id = b.company_id) eval_sum
from
biz_buyer b
;


CREATE OR REPLACE FORCE VIEW "USER_INFO" ("USR_ID", "EMP_ID", "COMPANY_ID") AS 
  select
      u.usr_id,
      e.emp_id,
      c.company_id
from auth_user u
inner join auth_org_employee e on u.emp_id = e.emp_id
left join pub_company c on c.company_id = e.emp_company_id;


CREATE OR REPLACE FORCE VIEW "ECP_V_PROJECT_COUNT" ("PROJID", "SUBPROJECTC", "SIGNUPC", "SIGNUPVALIDC", "BIDC", "BIDVALIDC", "WINNERC") AS 
  SELECT proj.tenderid as projId,(
       SELECT COUNT(pack.tenderid) FROM ECP_Tender_Project pack WHERE pack.parent_id = proj.tenderid
) subProjectC,(
       SELECT COUNT(apply.applyid) FROM ECP_TENDER_APPLY_REC apply WHERE apply.tenderid = proj.tenderid
) signUpC ,(
       SELECT COUNT(apply1.Applyid) FROM ECP_TENDER_APPLY_REC apply1 WHERE apply1.tenderid = proj.tenderid AND apply1.Auditstatus='01'
)signUpValidC,(
       SELECT COUNT(bid.bid_id) FROM ECP_BID bid WHERE bid.proj_id = proj.tenderid
) bidC,(
       SELECT COUNT(bid1.bid_id) FROM ECP_BID bid1 WHERE bid1.proj_id = proj.tenderid
)bidValidC ,(
       SELECT COUNT(winner.buy_w_id) FROM ECP_BUY_WINNER winner
       LEFT JOIN ECP_BUYRESULT buyResult on buyResult.BUYR_ID = winner.BUYR_ID
       WHERE winner.RESULT_TYPE = '01' AND buyResult.PROJ_ID = proj.tenderid
)winnerC
FROM ECP_Tender_Project proj WHERE proj.parent_id is null
;


CREATE OR REPLACE FORCE VIEW "ES_VW_BULLETIN_INFO" ("BULLETINNO", "BULLETINNAME", "PRJNO", "PRJNAME", "PACKNO", "PACKNAME", "BULLETINTYPE", "CONTENT", "SIGNUPSTARTDATE", "SIGNUPENDDATE", "SUBMITSTARTDATE", "SUBMITENDDATE", "RELDATE", "PURCATEGORYNOS", "PURCATEGORYNAMES", "HANDLER", "BUDGET", "PRJSTATUS", "LINKER", "LINKTEL", "LINKADDRESS", "SUMMARY") AS 
  select ebb.bulletin_no as bulletinNo,
       ebb.title as bulletinName,
       decode(etp_project.tenderno,null,etp_package.tenderno,etp_project.tenderno) as prjNo,
       decode(etp_project.tendername,null,etp_package.tendername,etp_project.tendername) as prjName,
       decode(etp_package.parent_id,null,'',etp_project.tenderno) as packNo,
       decode(etp_package.parent_id,null,'',etp_project.tendername) as packName,
       ebb.bulletin_type as bulletinType,
       ebb.CONTENT as content,
       ebb.SignUp_Start_Date as signupStartDate,
       ebb.SignUp_End_Date as signupEndDate,
       ebb.Submit_Start_Date as submitStartDate,
       ebb.Submit_End_Date as submitEndDate,
       ebb.RelDate as relDate,
       etp_package.PURCATEGORY_IDS as purCategoryNos,
       etp_package.PURCATEGORY_NAMES as purCategoryNames,
       aoe.emp_name as handler,
       (select sum(etmt.budget_money) from ecp_tend_m_task_p etmt where  etmt.tenderid = ebb.tenderid) as budget,
       etp_package.ProcessStatus as prjStatus,
       aoe.emp_name as linker,
       aoe.emp_tel_office as linkTel,
       aoe.emp_address as linkAddress,
       etp_package.summary
  from ECP_Base_Bulletin ebb
  left join ECP_Tender_Project etp_package on ebb.TenderID = etp_package.tenderid
  left join ecp_tender_project etp_project on etp_package.parent_id = etp_project.tenderid
  left join auth_org_employee aoe on etp_package.managerid = aoe.emp_id
;
  
    