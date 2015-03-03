--初始会员级别
insert into member_class (MEMBER_CLASS_ID, MEMBER_CLASS_NUM, MEMBER_CLASS_NAME, MEMBER_CLASS_PIC, MEMBER_CLASS_DESC, MIN_AGE, MAX_AGE, MIN_FEE, MAX_FEE, CREATOR, CREATETIME)
values ('C01', '1', 'VIP会员', '', '', 0, 0, 1000.00, 10000.00, '402886db2aa1b3b9012aa1cae8080020', to_date('24-03-2011 14:16:50', 'dd-mm-yyyy hh24:mi:ss'));

insert into member_class (MEMBER_CLASS_ID, MEMBER_CLASS_NUM, MEMBER_CLASS_NAME, MEMBER_CLASS_PIC, MEMBER_CLASS_DESC, MIN_AGE, MAX_AGE, MIN_FEE, MAX_FEE, CREATOR, CREATETIME)
values ('C00', '0', '普通会员', '', '', 0, 0, 0.00, 0.00, '402886db2aa1b3b9012aa1cae8080020', to_date('24-03-2011 14:16:50', 'dd-mm-yyyy hh24:mi:ss'));

--初始服务
insert into service_base (SERVICE_ID, SERVICE_NAME, SERVICE_PIC, SERVICE_DESC, SERVICE_LINK, SERVICE_PRICE, SERVICE_UNIT, SERVICE_PREPOSITION, IS_SINGLE_PURCHASE, IS_RECOMMENDATION, CREATOR, CREATETIME, SERVICE_AGREEMENT_LINK, USE_STATUS)
values ('A', '商圈会员', '', '', '', 0.0000000, '01', '', '1', '1', '402886db2aa1b3b9012aa1cae8080020', to_date('01-04-2011 16:22:34', 'dd-mm-yyyy hh24:mi:ss'), '', '01');

commit;

--服务订阅数据迁移
insert into service_subscribe 
        select t.businessmember_id||'_a',t.org_info_id,'A',
         t.begain_date,t.end_date,0,t.create_user,t.create_time,
         t.audit_status,
         (select u.usr_id from auth_user u where u.usr_name='manager'),
         sysdate,''
         from ecp_business_member t where t.audit_status='02';
commit;         

--会员数据迁移
insert into member 
         select t.org_info_id||'_m',t.org_info_id,0,0,
         'C00',t.creator,t.create_time,t.modifier,t.modify_time
         from org_info t where t.use_status='01';
         
commit;

--初始化标准计费信息
insert into service_charging (SERVICE_CHARGING_ID, MEMBER_CLASS_ID, SERVICE_ID, DURATION, AMOUNT, DISCOUNT, USE_STATUS, CREATOR, CREATETIME)
values ('2c9087922f4483b4012f477351071111', '', 'A', 0, 0.0000000, 0.0000000, '01', '402886db2aa1b3b9012aa1cae8080020', to_date('01-04-2011 16:22:34', 'dd-mm-yyyy hh24:mi:ss'));
commit;