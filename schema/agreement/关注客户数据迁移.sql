--插入存在所属人的客户关系
insert into ecp_pub_concern
  (CONCERN_ID,
   CONCERN_GROUP,
   ORGINFO,
   USER_ID,
   CREATOR_ID,
   CREATE_TIME,
   LIST_TYPE,
   BELONGS_USER)
  select concern_main_key.nextval,
         '',
         hq.clier_org,
         '',
         hq.owner,
         sysdate,
         '01',
         hq.owner
    from (select distinct oc.owner, oc.clier_org
            from (
                  --竞价项目项目创建人不等于报名创建人(非邀请供货商)   所属人为项目创建人，采购人
                  select distinct p.creuser    as owner,
                                   r.supplyerid as clier_org
                    from ecp_tender_project p, ecp_tender_apply_rec r
                   where p.tenderid = r.tenderid
                     and p.tendermethod = '06'
                     and p.creuser <> r.creuser
                  union
                  --竞价项目项目创建人等于报名创建人(邀请供货商)   所属人为项目创建人，采购人
                  select distinct p.creuser    as owner,
                                  r.supplyerid as clier_org
                    from ecp_tender_project p, ecp_tender_apply_rec r
                   where p.tenderid = r.tenderid
                     and p.tendermethod = '06'
                     and p.creuser = r.creuser
                  union
                  --竞价项目项目创建人不等于报名创建人   所属人为项目报名人，供货商
                  select distinct r.creuser as owner, p.buyers_id as clier_org
                    from ecp_tender_project p, ecp_tender_apply_rec r
                   where p.tenderid = r.tenderid
                     and p.tendermethod = '06'
                     and p.creuser <> r.creuser
                  union
                  --议价项目   所属人为项目创建人，采购人
                  select distinct p.creuser    as owner,
                                  r.supplyerid as clier_org
                    from ecp_tender_project p, ecp_tender_apply_rec r
                   where p.tenderid = r.tenderid
                     and p.tendermethod = '05'
                  union
                  --订单   所属人为订单创建人，采购人
                  select distinct o.cre_user_id as owner,
                                  o.supplier_id as clier_org
                    from eps_agreement_order o
                   where o.project_id is null) oc) hq;
commit;

--插入不存在所属人只有所属机构的客户关系
insert into ecp_pub_concern
  (CONCERN_ID,
   CONCERN_GROUP,
   ORGINFO,
   USER_ID,
   CREATOR_ID,
   CREATE_TIME,
   LIST_TYPE,
   BELONGS_USER)
  select concern_main_key.nextval,
         '',
         hq.clier_org,
         '',
         hq.owner_org,
         sysdate,
         '01',
         ''
    from (select distinct oc.owner_org, oc.clier_org
            from (
                  ----竞价项目项目创建人等于报名创建人(邀请供货商)   所属人为邀请供货商，供货商
                  select distinct r.supplyerid as owner_org,
                                   p.buyers_id  as clier_org
                    from ecp_tender_project p, ecp_tender_apply_rec r
                   where p.tenderid = r.tenderid
                     and p.tendermethod = '06'
                     and p.creuser = r.creuser
                  union
                  --议价项目   所属人为邀请的供货商，供货商
                  select distinct r.supplyerid as owner_org,
                                  p.buyers_id  as clier_org
                    from ecp_tender_project p, ecp_tender_apply_rec r
                   where p.tenderid = r.tenderid
                     and p.tendermethod = '05'
                  union
                  --订单   所属人为订单供货商，供货商
                  select distinct o.supplier_id as owner,
                                  o.buyer_id    as clier_org
                    from eps_agreement_order o
                   where o.project_id is null) oc) hq;
commit;

--更改当所属人为空时，记录的创建人为所属机构的管理员
update ecp_pub_concern c
   set c.creator_id =
       (select o.user_id from org_info o where o.org_info_id = c.creator_id)
 where c.belongs_user is null;
commit;

--更改客户的管理员
update ecp_pub_concern c
   set c.user_id      =
       (select o.user_id from org_info o where o.org_info_id = c.orginfo),
       c.concern_group = c.creator_id;
commit;

--添加客户默认分组
insert into ecp_pub_concern_group
  (concern_group_id,
   group_name,
   group_type,
   belongs_user,
   belongs_org,
   create_time,
   sort_no)
  select distinct c.concern_group,
         '默认分组',
         '01',
         c.concern_group,
         '',
         sysdate,
         '0'
    from ecp_pub_concern c;
commit;

--修改所属人机构
update ecp_pub_concern_group g
   set g.belongs_org =
       (select o.org_info_id
          from auth_user u, auth_org_employee e, org_info o
         where e.emp_id = u.emp_id
           and e.emp_company_id = o.company_id
           and u.usr_id = g.belongs_user);
commit;
