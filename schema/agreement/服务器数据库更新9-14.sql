--后台菜单图标更改路径
update auth_menu m set m.mnu_icon = 'view/resource/skin/thems/default/img/menu' || SUBSTR(m.mnu_icon,37)
where m.mnu_icon like 'view/resource/skin/thems/default/img/%';
commit;


--更改菜单图标字段大小
-- Add/modify columns 
alter table AUTH_MENU modify MNU_ICON VARCHAR2(100 CHAR);

--更改经营范围字段大小
-- Add/modify columns 
alter table ORG_INFO rename column USE_STATUS to USE_STATUbbS;
alter table ORG_INFO modify BID_FOR_RANGE VARCHAR2(4000);


-- Add/modify columns 
alter table ECP_TEND_RULE add RULE_PRICE_CONTROL NUMBER(38,2);
-- Add comments to the columns 
comment on column ECP_TEND_RULE.RULE_PRICE_CONTROL
  is '控制价';
