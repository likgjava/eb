--修改‘基本信息’资源路径
update auth_resource t set t.res_url='BaseCompanyController.do?method=toAddBizCompanyInfo'
where t.res_url = 'ExOrgInfoController.do?method=toEntBaseInfo';
commit;

--修改‘机构变更’资源路径
update auth_resource t set t.res_url='OrgInfoModifyController.do?method=toOrgModifyView'
where t.res_url = 'OrgInfoModifyController.do?method=toModifyOrg';
commit;

--修改‘申请为服务商’资源路径
update auth_resource t set t.res_url='OrgInfoApplyController.do?method=toOrgApplyServiceView'
where t.res_name = '申请为服务商';
commit;

--修改‘联系人管理’资源路径
update auth_resource t set t.res_url='EmployeeController.do?method=listContact'
where t.res_url = 'OrgInfoController.do?method=listContact';
commit;

--修改‘机构管理’资源路径
update auth_resource t set t.res_url='BaseCompanyController.do?method=toCompanyAllList'
where t.res_url = 'view/bizplatform/organization/manager/organization_manage_list.jsp?appType=XEJY';
commit;

--修改‘供应商审核’资源路径
update auth_resource t set t.res_url='BaseSupplierController.do?method=toSupplierAuditList'
where t.res_url = 'view/bizplatform/suppliers/suppliers/supplier_audit_list.jsp';
commit;

--修改‘采购人审核’资源路径
update auth_resource t set t.res_url='BaseBuyerController.do?method=toBuyerAuditList'
where t.res_url = 'view/bizplatform/buyers/buyers/buyer_audit_list.jsp';
commit;

--修改‘机构变更审核’资源路径
update auth_resource t set t.res_url='view/bizplatform/organization/manager/org_modify_audit_list.jsp'
where t.res_url = 'OrgInfoModifyController.do?method=toAuditModifyOrgList';
commit;

--修改‘机构申请审核’资源路径
update auth_resource t set t.res_url='view/bizplatform/organization/manager/org_apply_audit_list.jsp'
where t.res_url = 'view/bizplatform/organization/manager/organization_apply_audit_list.jsp';
commit;

--屏蔽’机构审核‘菜单
update AUTH_MENU m set m.mnu_showflag = '0'
where m.MNU_NAME = '机构审核';
commit;

--屏蔽’财务信息‘菜单
update AUTH_MENU m set m.mnu_showflag = '0'
where m.MNU_NAME = '财务信息';
commit;

--屏蔽’法务信息‘菜单
update AUTH_MENU m set m.mnu_showflag = '0'
where m.MNU_NAME = '法务信息';
commit;

--屏蔽’申请为厂商‘菜单
update AUTH_MENU m set m.mnu_showflag = '0'
where m.MNU_NAME = '申请为厂商';
commit;

--修改‘机构变更’表中的字段名
alter table ORG_INFO_MODIFY rename column ORG_INFO_ID to COMPANY_ID;

--修改‘机构申请’表中的字段名
alter table ORG_INFO_APPLY rename column ORG_INFO_ID to COMPANY_ID;

--修改变更类型字段长度为char(4)
alter table ORG_INFO_APPLY modify apply_type char(4);

--修改我发起的项目资源路径
update auth_resource t set t.res_url='view/agreement/bargin/project/bargain/bargain_project_list.jsp'
where t.res_url='view/agreement/bargin/project/bargain/s_bargain_project_list.jsp';
commit;

--修改AUTH_ORG_ROLE的ORG_ROLE_ID为空(先去掉主键)
alter table AUTH_ORG_ROLE
  drop constraint AUTH_ORG_ROLE_PRIMARY cascade;
alter table AUTH_ORG_ROLE modify ORG_ROLE_ID null;
alter table AUTH_ORG_ROLE
  add constraint AUTH_ORG_ROLE_PRIMARY primary key (ORG_ROLE_ID);


