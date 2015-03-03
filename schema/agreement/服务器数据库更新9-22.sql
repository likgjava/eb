--广告表添加排序字段
alter table eps_pub_advertising_subscribe add ad_sort number(9) default 1;
comment on column eps_pub_advertising_subscribe.ad_sort is '排序号';

--对排序号赋值
update eps_pub_advertising_subscribe s set s.ad_sort = rownum;