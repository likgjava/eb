--修改资源我的客户的链接
update auth_resource r set r.res_url = 'ConcernController.do?method=toConcernList' where r.res_name = '我的客户';
