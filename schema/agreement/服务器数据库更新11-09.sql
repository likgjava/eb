--发布静态首页的资源菜单
--资源
insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c90879233252f6701335e5aff9f05c3', '发布静态首页', 'view/srplatform/staticpage/make_static_page.jsp', '0', '0', '1', '2c9087f031a7811b0131a860da590033#2c9087f031a7811b0131a862b8510039#2c90879233252f6701335e5aff9f05c3', 3, '2c9087f031a7811b0131a862b8510039', 'URL', null, '01-11月-11 04.59.41.855000 下午', 'xejy');

--菜单
insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c90879233252f6701335e5bd30c05c5', '发布静态首页', '发布静态首页', '1', 2, '0', '发布静态首页', '2c9087f031acacb70131ad04a3f50004', '', '0', '1', '2c9087f031acacb70131acb9c2960002#2c9087f031acacb70131ad04a3f50004#2c90879233252f6701335e5bd30c05c5', 3, '2c90879233252f6701335e5aff9f05c3', '01-11月-11 05.00.35.980000 下午');

--角色资源
insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402886dc2a6949d5012a694c96360007', '2c90879233252f6701335e5aff9f05c3', '1', '');


--支付记录表中新增字段
-- Add/modify columns 
alter table PAY_RECORD add INVOICE_TITLE VARCHAR2(100);
-- Add comments to the columns 
comment on column PAY_RECORD.INVOICE_TITLE is '发票抬头';
-- Add/modify columns 
alter table PAY_RECORD add INVOICE_ITEMS VARCHAR2(100);
-- Add comments to the columns 
comment on column PAY_RECORD.INVOICE_ITEMS is '发票名目';
-- Add/modify columns 
alter table PAY_RECORD add MAILING_ADDRESS VARCHAR2(100);
-- Add comments to the columns 
comment on column PAY_RECORD.MAILING_ADDRESS is '邮寄地址';
