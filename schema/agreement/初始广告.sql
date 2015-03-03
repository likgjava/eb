--删除旧的广告表
drop table eps_pub_advertisement;

--删除旧的广告菜单
delete from auth_menu m where m.mnu_name like '广告%';

--删除旧的广告资源
delete from auth_resource r where r.res_name like '广告%';

--删除旧的角色分配广告资源
delete from auth_role_resource r
 where r.res_id in ('402886de2bc34e07012bc376ef670024',
                    '2c9087db2ee6d540012ee6d7b0740004',
                    '402886ba2ba8782c012ba88c30600010',
                    '2c9087db2edc4ffa012edc70916b000a',
                    '2c9087db2edc4ffa012edc6f79830007');

--添加广告资源
insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c9087db2f28964a012f28b7f8600094', '广告管理', '1', '0', '0', '0', '402886dc2a69215e012a6943a8ee0055#2c9087db2f28964a012f28b7f8600094', 2, '402886dc2a69215e012a6943a8ee0055', 'URL', null, '06-4月 -11 10.50.36.000000 上午', '');

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c9087db2f28964a012f28bd4a02009a', '广告订阅管理', 'AdvertisingSubscribeController.do', '0', '0', '1', '402886dc2a69215e012a6943a8ee0055#2c9087db2f28964a012f28b7f8600094#2c9087db2f28964a012f28bd4a02009a', 3, '2c9087db2f28964a012f28b7f8600094', 'URL', null, '06-4月 -11 10.56.24.578000 上午', '');

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c9087db2f28964a012f28bcc58f0097', '广告位管理', 'AdvertisingPositionController.do', '0', '0', '1', '402886dc2a69215e012a6943a8ee0055#2c9087db2f28964a012f28b7f8600094#2c9087db2f28964a012f28bcc58f0097', 3, '2c9087db2f28964a012f28b7f8600094', 'URL', null, '06-4月 -11 10.55.50.671000 上午', '');

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c9087db2f28964a012f28bdc443009d', '广告订阅审核', 'AdvertisingSubscribeController.do?method=toAdverSubscribeAuditList', '0', '0', '1', '402886dc2a69215e012a6943a8ee0055#2c9087db2f28964a012f28b7f8600094#2c9087db2f28964a012f28bdc443009d', 3, '2c9087db2f28964a012f28b7f8600094', 'URL', null, '06-4月 -11 10.56.55.875000 上午', '');


--添加广告菜单
insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c9087db2f28964a012f28b7f8600093', '', '广告管理', '1', null, '0', '', '402886dc2bd3c915012bd4390ea100c8', '', '0', '0', '402886dc2bd3c915012bd4390ea100c8#2c9087db2f28964a012f28b7f8600093', 2, '2c9087db2f28964a012f28b7f8600094', '06-4月 -11 10.50.36.000000 上午');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c9087db2f28964a012f28bd4a020099', '', '广告订阅管理', '1', null, '0', '', '2c9087db2f28964a012f28b7f8600093', '', '0', '1', '402886dc2bd3c915012bd4390ea100c8#2c9087db2f28964a012f28b7f8600093#2c9087db2f28964a012f28bd4a020099', 3, '2c9087db2f28964a012f28bd4a02009a', '06-4月 -11 10.56.24.578000 上午');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c9087db2f28964a012f28bcc58f0096', '', '广告位管理', '1', null, '0', '', '2c9087db2f28964a012f28b7f8600093', '', '0', '1', '402886dc2bd3c915012bd4390ea100c8#2c9087db2f28964a012f28b7f8600093#2c9087db2f28964a012f28bcc58f0096', 3, '2c9087db2f28964a012f28bcc58f0097', '06-4月 -11 10.55.50.671000 上午');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c9087db2f28964a012f28bdc443009c', '', '广告订阅审核', '1', null, '0', '', '2c9087db2f28964a012f28b7f8600093', '', '0', '1', '402886dc2bd3c915012bd4390ea100c8#2c9087db2f28964a012f28b7f8600093#2c9087db2f28964a012f28bdc443009c', 3, '2c9087db2f28964a012f28bdc443009d', '06-4月 -11 10.56.55.875000 上午');


--添加角色分配广告资源
insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402886dc2a6949d5012a694c96360007', '2c9087db2f28964a012f28bcc58f0097', '1', '');

insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402886dc2a6949d5012a694c96360007', '2c9087db2f28964a012f28bdc443009d', '1', '');

insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402886dc2a6949d5012a694c96360007', '2c9087db2f28964a012f28bd4a02009a', '1', '');

insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402886dc2a6949d5012a694c96360007', '2c9087db2f28964a012f28b7f8600094', '1', '');


--删除旧的广告位置字典项
delete from base_dictionary d
 where d.dic_id in ('402886de2baf0196012baf2ad26b000f',
                    '402886de2baf0196012baf2b4c680011',
                    '402886de2bbd2dde012bbd72fc680006',
                    '402886de2bbd2dde012bbd7499730009',
                    '2c9087db2dc147dc012dc1521635007c',
                    '2c9087db2dc147dc012dc152ce99007e',
                    '2c9087db2dc147dc012dc15397380080');


--添加广告位字典项
insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f298e8c130031', '供应商库底部广告', '402886de2baf0196012baf2a4215000d', 'adver_supplier_botto', '1', 'adver_supplier_bottom.jsp', '供应商库底部广告', '06-4月 -11 02.44.58.500000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f298d98e9002f', '供应商库中部广告', '402886de2baf0196012baf2a4215000d', 'adver_supplier_middl', '1', 'adver_supplier_middle.jsp', '供应商库中部广告', '06-4月 -11 02.43.56.265000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f298c25e1002d', '供应商库中心广告', '402886de2baf0196012baf2a4215000d', 'adver_supplier_cente', '1', 'adver_supplier_center.jsp', '供应商库中心广告', '06-4月 -11 02.42.21.281000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f298aa4bf002b', '商品库左6广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left6', '1', 'adver_goods_left6.jsp', '商品库左6广告', '06-4月 -11 02.40.42.687000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f298992450029', '商品库左5广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left5', '1', 'adver_goods_left5.jsp', '商品库左5广告', '06-4月 -11 02.39.32.421000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f2976f5320026', '商品库左4广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left4', '1', 'adver_goods_left4.jsp', '商品库左4广告', '06-4月 -11 02.19.12.562000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f2974b0240024', '商品库左3广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left3', '1', 'adver_goods_left3.jsp', '商品库左3广告', '06-4月 -11 02.16.43.812000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f29729ea90022', '商品库左2广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left2', '1', 'adver_goods_left2.jsp', '商品库左2广告', '06-4月 -11 02.14.28.265000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f29709d5b0020', '商品库左1广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_left1', '1', 'adver_goods_left1.jsp', '商品库左1广告', '06-4月 -11 02.12.16.859000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f296e4f54001e', '商品库中心广告', '402886de2baf0196012baf2a4215000d', 'adver_goods_center', '1', 'adver_goods_center.jsp', '商品库中心广告', '06-4月 -11 02.09.45.812000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f296aa3920018', '采购需求底部广告', '402886de2baf0196012baf2a4215000d', 'adver_bulletin_bott', '1', 'adver_bulletin_bottom.jsp', '采购需求底部广告', '06-4月 -11 02.05.45.234000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f296917450016', '采购需求左侧广告', '402886de2baf0196012baf2a4215000d', 'adver_bulletin_left', '1', 'adver_bulletin_left.jsp', '采购需求左侧广告', '06-4月 -11 02.04.03.781000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f29679a780014', '采购需求中心广告', '402886de2baf0196012baf2a4215000d', 'adver_bulletin_cente', '1', 'adver_bulletin_center.jsp', '采购需求中心广告', '06-4月 -11 02.02.26.296000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f294a7922000c', '首页底部广告', '402886de2baf0196012baf2a4215000d', 'adver_index_bottom', '1', 'adver_index_bottom.jsp', '首页底部广告', '06-4月 -11 01.30.37.218000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f2949379a0009', '首页中部广告', '402886de2baf0196012baf2a4215000d', 'adver_index_middle', '1', 'adver_index_middle.jsp', '首页中部广告', '06-4月 -11 01.29.14.906000 下午');

insert into base_dictionary (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, DIC_MEMO, CREATE_TIME)
values ('2c9087db2f293c07012f2946e3b70003', '首页中心广告', '402886de2baf0196012baf2a4215000d', 'adver_index_center', '1', 'adver_index_center.jsp', '首页中心广告', '06-4月 -11 01.26.42.359000 下午');


--添加广告位管理数据
insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29fc6f2e00b4', '2c9087db2f293c07012f298c25e1002d', 'adver_supplier_center.jsp', '02', 240, 500, 500, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29fd787000b6', '2c9087db2f293c07012f298d98e9002f', 'adver_supplier_middle.jsp', '00', 295, 370, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29fe577600ba', '2c9087db2f293c07012f296e4f54001e', 'adver_goods_center.jsp', '02', 240, 500, 500, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29faca7700ae', '2c9087db2f293c07012f29679a780014', 'adver_bulletin_center.jsp', '02', 240, 500, 500, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29fb3bed00b0', '2c9087db2f293c07012f296917450016', 'adver_bulletin_left.jsp', '00', 173, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29feda0400bc', '2c9087db2f293c07012f29709d5b0020', 'adver_goods_left1.jsp', '00', 120, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29fbb4d700b2', '2c9087db2f293c07012f296aa3920018', 'adver_bulletin_bottom.jsp', '00', 119, 960, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29f8aa8500a8', '2c9087db2f293c07012f2946e3b70003', 'adver_index_center.jsp', '01', 300, 500, 500, 5000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29f9535700aa', '2c9087db2f293c07012f2949379a0009', 'adver_index_middle.jsp', '00', 119, 960, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29ff1bc000be', '2c9087db2f293c07012f29729ea90022', 'adver_goods_left2.jsp', '00', 120, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f2a004e8300c4', '2c9087db2f293c07012f298992450029', 'adver_goods_left5.jsp', '00', 120, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f2a008d9000c6', '2c9087db2f293c07012f298aa4bf002b', 'adver_goods_left6.jsp', '00', 120, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29ff9a3700c0', '2c9087db2f293c07012f2974b0240024', 'adver_goods_left3.jsp', '00', 120, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29f9f70a00ac', '2c9087db2f293c07012f294a7922000c', 'adver_index_bottom.jsp', '00', 90, 958, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29fdc91100b8', '2c9087db2f293c07012f298e8c130031', 'adver_supplier_bottom.jsp', '00', 119, 960, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

insert into eps_pub_advertising_position (ADVER_POSITION_ID, POSITION_DICTIONARY_ID, POSITION_NAME, ADVER_TYPE, ADVER_LENGTH, ADVER_WIDTH, ADVER_FILE_MAXVALUE, ADVER_OUTLAY, CREATOR_ID, CREATE_TIME, MODIFIER, MODIFIER_TIME)
values ('2c9087db2f293c07012f29ffefd900c2', '2c9087db2f293c07012f2976f5320026', 'adver_goods_left4.jsp', '00', 120, 180, 100, 4000.0000, '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'), '402886db2aa1b3b9012aa1cae8080020', to_date('06-04-2011', 'dd-mm-yyyy'));

