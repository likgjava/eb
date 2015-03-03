

--修改已备案的招标项目
update ecp_tender_project t set t.tender_keep_on_r = '01' where t.tenderid in (
    select distinct p.tenderid
    from ecp_tender_project p
    join ecp_buyresult r on p.tenderid = r.proj_id
    where r.buyr_result = '02'
    and p.tendermethod = '00'
    and p.tender_keep_on_r is null
);

--修改已备案的竞价和议价项目
update ecp_tender_project t set t.tender_keep_on_r = '01' where t.tenderid in (
    select distinct p.tenderid
    from ecp_tender_project p
    join ecp_buyresult r on p.tenderid = r.proj_id
    where (p.tendermethod = '05' or p.tendermethod = '06')
    and p.tender_keep_on_r is null
);

--修改未备案项目
update ecp_tender_project t set t.tender_keep_on_r = '00' where t.tender_keep_on_r is null;


--冗余项目的过程状态字段
update ecp_tender_project t set t.tender_process_statuscn='指定项目经办人' where t.ProcessStatus='10' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='设定项目规则' where t.ProcessStatus='20' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='项目分包' where t.ProcessStatus='30' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='评标小组' where t.ProcessStatus='40' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='开标小组' where t.ProcessStatus='41' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='预定评标室' where t.ProcessStatus='50' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='上传采购文件' where t.ProcessStatus='60' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='采购人确认采购文件' where t.ProcessStatus='70' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='监察局审核采购文件' where t.ProcessStatus='80' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='采购办审核采购文件' where t.ProcessStatus='90' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='采购人确认采购文件' where t.ProcessStatus='70' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='起草采购公告' where t.ProcessStatus='100' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='提交采购公告' where t.ProcessStatus='110' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='采购办审核采购公告' where t.ProcessStatus='120' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='供应商报名' where t.ProcessStatus='130' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='代理机构确认供应商报名' where t.ProcessStatus='140' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='下载采购文件' where t.ProcessStatus='150' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='供应商投标' where t.ProcessStatus='160' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='开标' where t.ProcessStatus='170' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='专家评审' where t.ProcessStatus='180' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='经办人生成评标报告' where t.ProcessStatus='190' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='定标' where t.ProcessStatus='200' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='起草中标公告' where t.ProcessStatus='210' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='提交中标公告' where t.ProcessStatus='220' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='采购办审核中标公告' where t.ProcessStatus='230' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='起草中标通知书' where t.ProcessStatus='240' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='供应商起草合同' where t.ProcessStatus='250' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='采购人确认合同' where t.ProcessStatus='260' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='项目归档' where t.ProcessStatus='280' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='预定开标室' where t.ProcessStatus='290' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='起草资格预审公告' where t.ProcessStatus='300' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='提交资格预审公告' where t.ProcessStatus='310' and t.tender_process_statuscn is null;
commit;
update ecp_tender_project t set t.tender_process_statuscn='审核资格预审公告' where t.ProcessStatus='320' and t.tender_process_statuscn is null;
commit;