--新增首页顶部广告位
--字典表
insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f56c701012f56ee0af8000c', '首页顶部广告', '402886de2baf0196012baf2a4215000d', 'adver_index_top', '1', 'adver_index_top.jsp', '首页顶部广告', '15-4月 -11 10.12.11.640000 上午');
--广告位表
insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f56c701012f56f69cb20011', '2c9087db2f56c701012f56ee0af8000c', 'adver_index_top.jsp', '00', 55, 650, 100, 10000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('15-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('15-04-2011', 'dd-mm-yyyy'));



--修改广告订阅表广告链接的长度
alter table eps_pub_advertising_subscribe modify(adver_link varchar2(200));


--新增商品库左侧广告位
--字典表
insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f6c38ca012f6c860217001a', '商品库左12广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left12', '1', 'adver_goods_left12.jsp', '商品库左12广告', '19-4月 -11 02.50.12.375000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f6c38ca012f6c84e03b0017', '商品库左11广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left11', '1', 'adver_goods_left11.jsp', '商品库左11广告', '19-4月 -11 02.48.58.171000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f6c38ca012f6c8443210015', '商品库左10广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left10', '1', 'adver_goods_left10.jsp', '商品库左10广告', '19-4月 -11 02.48.17.953000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f6c38ca012f6c8277f60013', '商品库左9广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left9', '1', 'adver_goods_left9.jsp', '商品库左9广告', '19-4月 -11 02.46.20.406000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f6c38ca012f6c81ab9d0011', '商品库左8广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left8', '1', 'adver_goods_left8.jsp', '商品库左8广告', '19-4月 -11 02.45.28.093000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f6c38ca012f6c800d20000f', '商品库左7广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left7', '1', 'adver_goods_left7.jsp', '商品库左7广告', '19-4月 -11 02.43.41.984000 下午');

--广告位表
insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f6c38ca012f6c8adf350022', '2c9087db2f6c38ca012f6c81ab9d0011', 'adver_goods_left8.jsp', '00', 210, 180, 100, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f6c38ca012f6c879837001f', '2c9087db2f6c38ca012f6c800d20000f', 'adver_goods_left7.jsp', '00', 210, 180, 100, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f6c38ca012f6c8b3cb60024', '2c9087db2f6c38ca012f6c8277f60013', 'adver_goods_left9.jsp', '00', 210, 180, 100, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f6c38ca012f6c8c22630028', '2c9087db2f6c38ca012f6c84e03b0017', 'adver_goods_left11.jsp', '00', 210, 180, 100, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f6c38ca012f6c8b90830026', '2c9087db2f6c38ca012f6c8443210015', 'adver_goods_left10.jsp', '00', 210, 180, 100, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f6c38ca012f6c8c7853002a', '2c9087db2f6c38ca012f6c860217001a', 'adver_goods_left12.jsp', '00', 210, 180, 100, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('19-04-2011', 'dd-mm-yyyy'));


--修改广告位表
--修改商品库左侧广告位1-6号广告位
update eps_pub_advertising_position a
   set a.adver_length = '210'
 where a.adver_position_id in ('2c9087db2f293c07012f29feda0400bc',
                               '2c9087db2f293c07012f29ff1bc000be',
                               '2c9087db2f293c07012f29ff9a3700c0',
                               '2c9087db2f293c07012f29ffefd900c2',
                               '2c9087db2f293c07012f2a004e8300c4',
                               '2c9087db2f293c07012f2a008d9000c6');
