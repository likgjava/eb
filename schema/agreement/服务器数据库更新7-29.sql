--修改采购人、供货商的客户菜单为‘我的客户’
update auth_menu m
   set m.mnu_name = '我的客户'
 where m.mnu_id in
       (select m.mnu_id from auth_menu m where m.mnu_name like '%我的%客户%');

--修改资源我的客户的链接
update auth_resource r set r.res_url = 'ConcernController.do?method=toConcernBuyerList' where r.res_name = '我的客户';
