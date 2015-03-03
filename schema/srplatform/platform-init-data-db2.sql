/*
Source Server         : srplatform
Source Server Version : SVN-1595
Source Host           : 192.168.2.10
Source Database       : srplatform
Target Server Type    : Oracle
Date: 2010-02-25 13:22:00
*/

delete from AUTH_MENU;
commit;

delete from AUTH_CONFLICT_ROLE;
commit;

delete from AUTH_DATA_RES_TYPE;
commit;

delete from AUTH_GADGET;
commit;

delete from AUTH_ORGNIZATION;
commit;

delete from AUTH_ORGNIZATION_RULE;
commit;

delete from AUTH_USER_ROLE;
commit;

delete from AUTH_USER;
commit;

delete from AUTH_ROLE_RESOURCE;
commit;

delete from AUTH_ROLE;
commit;

delete from AUTH_ORG_EMPLOYEE;
commit;

delete from AUTH_RESOURCE;
commit;

delete from AUTH_ORG_DETAIL;
commit;

delete from AUTH_ORG_STRU_TYPE;
commit;

delete from AUTH_ROLE_EXTEND;
commit;

delete from AUTH_USER_GADGET;
commit;

delete from BASE_ATTACHMENT;
commit;

delete from BASE_DICTIONARY;
commit;

delete from BASE_DICTIONARY_TYPE;
commit;

delete from BASE_DISTRICT;
commit;

delete from BASE_INDUSTRY;
commit;

delete from BASE_LOGIN_LOGS;
commit;

delete from BASE_OPER_LOGS;
commit;

delete from BASE_SEQUENCE_NUMBER;
commit;

delete from BASE_SYSCONFIGITEM;
commit;

delete from BASE_SYSCONFIGITEM_VALUE;
commit;

delete from BASE_SYSCONFIG_PRE_VALUES;
commit;

delete from BASE_SYSCONFIG_TYPE;
commit;

delete from DEMO_ADDITIONAL;
commit;

delete from DEMO_AREA;
commit;

delete from DEMO_BRAND;
commit;

delete from DEMO_GOODS;
commit;

delete from DEMO_GOODS_CLASS;
commit;

delete from DEMO_GOODS_ORDER;
commit;

delete from DEMO_GOODS_PARAM;
commit;

delete from DEMO_OPTION;
commit;

delete from DEMO_ORDER;
commit;

delete from DEMO_PURCHASE_CATEGORY;
commit;

delete from JBPM4_DEPLOYMENT;
commit;

delete from JBPM4_DEPLOYPROP;
commit;

delete from JBPM4_EXECUTION;
commit;

delete from JBPM4_HIST_ACTINST;
commit;

delete from JBPM4_HIST_DETAIL;
commit;

delete from JBPM4_HIST_PROCINST;
commit;

delete from JBPM4_HIST_TASK;
commit;

delete from JBPM4_HIST_VAR;
commit;

delete from JBPM4_ID_GROUP;
commit;

delete from JBPM4_ID_MEMBERSHIP;
commit;

delete from JBPM4_ID_USER;
commit;

delete from JBPM4_JOB;
commit;

delete from JBPM4_LOB;
commit;

delete from JBPM4_PARTICIPATION;
commit;

delete from JBPM4_PROPERTY;
commit;

delete from JBPM4_SWIMLANE;
commit;

delete from JBPM4_TASK;
commit;

delete from JBPM4_VARIABLE;
commit;

delete from ORG_EMPLOYEE;
commit;

delete from ORG_ROLE;
commit;

-- BASE_DICTIONARY_TYPE...
insert into BASE_DICTIONARY_TYPE (DIC_GROUP_CODE, CREATE_TIME, DIC_GROUP_NAME)
values ('qylx', null, 'abc');

insert into BASE_DICTIONARY_TYPE (DIC_GROUP_CODE, CREATE_TIME, DIC_GROUP_NAME)
values ('khyh', null, 'bcd');

commit;

-- BASE_DICTIONARY...
insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('85', '外商投资股份有限公司', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('84', '外资企业', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('83', '中外合作经营企业
', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('82', '中外合资经营企业', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('81', '港、澳、台商投资股份有限公司', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('80', ' 港、澳、台商独资经营企业', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('79', '合作经营企业（港或澳、台资）', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('78', '合资经营企业（港或澳、台资）', 'qylx', 'cache_pool_1', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('77', '其他内资企业', 'qylx', 'cache_pool_1', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('76', '私营股份有限公司', 'qylx', 'cache_pool_1', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('75', '私营有限责任公司', 'qylx', 'cache_pool_1', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('74', '私营合伙企业', 'qylx', 'cache_pool_1', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('73', '私营独资企业
', 'qylx', 'cache_pool_1', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('72', '股份有限公司', 'qylx', 'cache_pool_1', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('71', '其他有限责任公司', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('70', '国有独资有限责任公司', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('69', '其他联营企业', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('68', '国有与集体联营企业', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('67', '集体联营企业', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('66', '国有联营企业', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('65', '股份合作企业', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('64', '国家机关', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('63', '事业单位', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('62', '团体组织', 'qylx', 'cache_pool_2', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('61', '财政厅（局）', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('60', '企业', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('59', '社会保障', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('58', '农业', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('57', '经济建设', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('56', '教科文', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('55', '行政政法', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('54', '金融', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('53', '其他', 'qylx', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('52', '中国农业发展银行', 'khyh', 'cache_pool_2', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('51', '中国进出口银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('50', '中国工商银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('49', '中信实业银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('48', '中国光大银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('47', '中国民生银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('46', '交通银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('45', '中国建设银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('44', '中国银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('43', '中国农业银行', 'khyh', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('42', '区级', 'qylx', 'cache_pool_3', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('41', '汇丰银行', 'khyh', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('40', '渣打银行', 'khyh', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('39', '大众银行（香港）', 'khyh', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('38', '浙商银行', 'khyh', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('37', '北京商业银行', 'khyh', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('36', '兴业银行', 'khyh', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('35', '广东发展银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('34', '深圳发展银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('33', '上海浦东发展银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('32', '招商银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('31', '华夏银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('30', '中国邮政储蓄银行安徽省分行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('29', '安徽省农村信用社联合社', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('28', '徽商银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('27', '合肥科技农村商业银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('26', '合肥市商业银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('25', '厦门国际银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('24', '县级', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('23', '采购三部', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('22', '预算外资金', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('21', '一般预算', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('20', '政府集中', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('19', '部门集中', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('18', '国家开发银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('17', '中国人民银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('16', '国有企业', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('15', '集体企业', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('14', '省级', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('13', '市级', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('12', '分散采购', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('11', '央行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('10', '政策性银行', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('9', '政府集中采购中心', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('8', '政府部门采购中心', 'qylx', 'cache_pool_3', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('7', '社会中介（招标公司）', 'qylx', 'cache_pool_4', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('6', '企业专职采购部门', 'qylx', 'cache_pool_4', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('5', '中介机构', 'qylx', 'cache_pool_4', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('4', '采购办', 'qylx', 'cache_pool_4', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('3', '供应商', 'qylx', 'cache_pool_4', '1', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('2', '采购人', 'qylx', 'cache_pool_4', '0', null, null, null);

insert into BASE_DICTIONARY (DIC_ID, DIC_NAME, DIC_GROUP_CODE, DIC_CACHE_NAME, DIC_USE_CACHE, DIC_VALUE, CREATE_TIME, DIC_MEMO)
values ('1', '专家', 'qylx', 'cache_pool_4', '0', null, null, null);

commit;

-- AUTH_ORG_EMPLOYEE...
insert into AUTH_ORG_EMPLOYEE (EMP_ID, EMP_NAME, EMP_MOBILE, EMP_EMAIL, EMP_MSN, EMP_QQ, EMP_TEL_OFFICE, EMP_TEL_HOME, EMP_SORT, EMP_CREATED_DATE, EMP_COMPANY_ID, EMP_DEPARTMENT_ID, EMP_POST_ID, EMP_NUMBER, CREATE_TIME, EMP_ORG_ID)
values ('402886de26bb36b70126bb3745120003', 'test3', null, null, null, null, null, null, null, null, '402886de269378fe012693d3ea720009', null, null, null, '2010-02-11-12.09.06.578000', null);

insert into AUTH_ORG_EMPLOYEE (EMP_ID, EMP_NAME, EMP_MOBILE, EMP_EMAIL, EMP_MSN, EMP_QQ, EMP_TEL_OFFICE, EMP_TEL_HOME, EMP_SORT, EMP_CREATED_DATE, EMP_COMPANY_ID, EMP_DEPARTMENT_ID, EMP_POST_ID, EMP_NUMBER, CREATE_TIME, EMP_ORG_ID)
values ('402886de2693eefc012693ef994d0001', '李强', '8866', null, null, null, null, null, null, null, '402886de269378fe012693d3ea720009', null, null, null, null, null);

insert into AUTH_ORG_EMPLOYEE (EMP_ID, EMP_NAME, EMP_MOBILE, EMP_EMAIL, EMP_MSN, EMP_QQ, EMP_TEL_OFFICE, EMP_TEL_HOME, EMP_SORT, EMP_CREATED_DATE, EMP_COMPANY_ID, EMP_DEPARTMENT_ID, EMP_POST_ID, EMP_NUMBER, CREATE_TIME, EMP_ORG_ID)
values ('402886de26bb34b30126bb353f6e0002', 'test22', null, null, null, null, null, null, null, null, '402886de269378fe012693d3ea720009', null, null, null, '2010-02-11-12.06.54.062000', null);

insert into AUTH_ORG_EMPLOYEE (EMP_ID, EMP_NAME, EMP_MOBILE, EMP_EMAIL, EMP_MSN, EMP_QQ, EMP_TEL_OFFICE, EMP_TEL_HOME, EMP_SORT, EMP_CREATED_DATE, EMP_COMPANY_ID, EMP_DEPARTMENT_ID, EMP_POST_ID, EMP_NUMBER, CREATE_TIME, EMP_ORG_ID)
values ('402886de26acbcf80126acc493b60005', 'tests', null, null, null, null, null, null, null, null, '402886de269bcfd001269c10fafa0003', null, null, null, '2010-02-08-16.49.09.046000', null);

commit;

-- AUTH_USER...
insert into AUTH_USER (USR_ID, USR_NAME, USR_CA_SN, USR_IS_CREDENTIAL, USR_IS_EXPIRED, USR_IS_LOCKED, USR_PASSWORD, USR_PERIOD_VALIDITY, USR_PWD_IS_EXPIRED, USR_PWD_TIP, USR_PWD_TIP_ANS, USR_SORT, USR_STATUS, USR_PWD_PERIOD_VALIDITY, USR_IS_USE_IPCHECK, USR_IS_ADMIN, USR_IPPOLICY, USR_CREATED_DATE, USR_CHANGEPWD, CREATE_TIME, EMP_ID)
values ('8a808083204329sr01204330167b0005', 'admin', null, '0', '0', '0', '7c4a8d09ca3762af61e59520943dc26494f8941b', '2010-05-26', '0', null, null, 1, '1', '2011-02-03', '0', '2', null, null, '1', null, null);

insert into AUTH_USER (USR_ID, USR_NAME, USR_CA_SN, USR_IS_CREDENTIAL, USR_IS_EXPIRED, USR_IS_LOCKED, USR_PASSWORD, USR_PERIOD_VALIDITY, USR_PWD_IS_EXPIRED, USR_PWD_TIP, USR_PWD_TIP_ANS, USR_SORT, USR_STATUS, USR_PWD_PERIOD_VALIDITY, USR_IS_USE_IPCHECK, USR_IS_ADMIN, USR_IPPOLICY, USR_CREATED_DATE, USR_CHANGEPWD, CREATE_TIME, EMP_ID)
values ('402886de2693eefc012693f059220002', 'liqiang', null, '0', '0', '0', '7c4a8d09ca3762af61e59520943dc26494f8941b', '2011-02-03', '0', null, null, null, '1', '2011-02-03', '0', '1', null, '2011-02-03', '1', null, '402886de2693eefc012693ef994d0001');

commit;

-- AUTH_ROLE...
insert into AUTH_ROLE (ROL_ID, ROL_EN_NAME, ORG_ID, ROL_CH_NAME, ROL_IS_DEFAULT, ROL_MEMO, ROL_STATUS, ROL_TYPE, ROL_PARENT_ID, DEPT_ID, CREATE_TIME, ROL_SORT)
values ('4028809925d348da0125d352656a0002', 'default_role', null, '默认角色', '1', null, '1', '0', null, null, null, null);

insert into AUTH_ROLE (ROL_ID, ROL_EN_NAME, ORG_ID, ROL_CH_NAME, ROL_IS_DEFAULT, ROL_MEMO, ROL_STATUS, ROL_TYPE, ROL_PARENT_ID, DEPT_ID, CREATE_TIME, ROL_SORT)
values ('8a80808720577fff012057cbd898002e', 'manager', null, '超级管理员', '0', '超级管理员', '1', '2', null, null, null, null);

insert into AUTH_ROLE (ROL_ID, ROL_EN_NAME, ORG_ID, ROL_CH_NAME, ROL_IS_DEFAULT, ROL_MEMO, ROL_STATUS, ROL_TYPE, ROL_PARENT_ID, DEPT_ID, CREATE_TIME, ROL_SORT)
values ('402886de269378fe012693d5f342000c', 'dep_manager', '402886de269378fe012693d4633c000a', '北京部门管理员角色', '0', null, '1', '1', null, null, null, null);

commit;

-- AUTH_USER_ROLE...
insert into AUTH_USER_ROLE (USR_ID, ROL_ID)
values ('402886de2693eefc012693f059220002', '4028809925d348da0125d352656a0002');

insert into AUTH_USER_ROLE (USR_ID, ROL_ID)
values ('402886de2693eefc012693f059220002', '402886de269378fe012693d5f342000c');

insert into AUTH_USER_ROLE (USR_ID, ROL_ID)
values ('8a808083204329sr01204330167b0005', '8a80808720577fff012057cbd898002e');

commit;

-- AUTH_ORGNIZATION_RULE...
insert into AUTH_ORGNIZATION_RULE (RULE_ID, ORG_SOURCE, ORG_TARGET, ORG_RULE, CREATE_TIME, STRU_TYPE_ID)
values ('1', '1', '1', '公司创建子公司', null, null);

insert into AUTH_ORGNIZATION_RULE (RULE_ID, ORG_SOURCE, ORG_TARGET, ORG_RULE, CREATE_TIME, STRU_TYPE_ID)
values ('2', '1', '2', '公司创建部门', null, null);

insert into AUTH_ORGNIZATION_RULE (RULE_ID, ORG_SOURCE, ORG_TARGET, ORG_RULE, CREATE_TIME, STRU_TYPE_ID)
values ('3', '1', '3', '公司创建岗位', null, null);

insert into AUTH_ORGNIZATION_RULE (RULE_ID, ORG_SOURCE, ORG_TARGET, ORG_RULE, CREATE_TIME, STRU_TYPE_ID)
values ('4', '2', '3', '部门创建岗位', null, null);

commit;

-- AUTH_ROLE_EXTEND...

-- auth_conflict_role...

-- auth_data_res_type...

-- auth_org_detail...

-- auth_org_stru_type...

-- base_attachment...

-- base_district...

-- base_industry...

-- base_login_logs...

-- base_oper_logs...

-- base_sequence_number...

-- base_sysconfigitem...

-- base_sysconfigitem_value...

-- base_sysconfig_pre_values...

-- base_sysconfig_type...

-- demo_additional...

-- demo_area...
insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('1111', 'beijing', '北京', 'beijing', 'bj');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('2222', 'hangzhou', '杭州', 'hangzhou', 'hz');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('3333', 'xinjiang', '新疆', 'xinjiang', 'xj');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('4444', 'ningxia', '宁夏', 'ningxia', 'nx');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('5555', 'guangxi', '广西', 'guangxi', 'gx');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('6666', 'hainan', '海南', 'hainan', 'hn');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('7777', 'hebei', '河北', 'hebei', 'hb');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('8888', 'anhui', '安徽', 'anhui', 'ah');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('9999', 'shanghai', '上海', 'shanghai', 'sh');

insert into demo_area (ARAE_ID, EN_NAME, CN_NAME, CN_PY_NAME, SUB_NAME)
values ('9998', 'guangzhou', '广州', 'guangzhou', 'gz');

commit;

-- demo_brand...
insert into demo_brand (BRAND_ID, BRAND_NAME, BRAND_FACTORY)
values ('402881e82617aeba012617b006970001', '国美', '江苏扬州国美分公司');

insert into demo_brand (BRAND_ID, BRAND_NAME, BRAND_FACTORY)
values ('402880ab260180fa012601c104540014', '海尔', '中国海尔集团');

insert into demo_brand (BRAND_ID, BRAND_NAME, BRAND_FACTORY)
values ('4028800525bff3c70125bff3e2530002', '大昌', '杭州大昌制冷');

insert into demo_brand (BRAND_ID, BRAND_NAME, BRAND_FACTORY)
values ('4028800525d32f3f0125d33326350002', 'TCL', 'TCL集团');

commit;

-- demo_goods...
insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('ff80808125fcd68f0125fcdac9c00005', '苏宁电热水器', 'A1001', '402881e82617aeba012617b006970001', '111111', '2010-01-11', '0', '0 ');

insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('402880ab260180fa0126018fcde10005', '海尔冰箱', 'A1002', '402880ab260180fa012601c104540014', '222222', '2010-01-12', '0', '0 ');

insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('402880ab260180fa0126018fda360009', '大昌电器空调', 'A1003', '4028800525bff3c70125bff3e2530002', '222222', '2010-01-13', '0', '0 ');

insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('402880ab260180fa01260192306a000f', '国美电热毯', 'A1004', '402880ab260180fa012601c104540014', '444444', '2010-01-14', '0', '0 ');

insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('402880ab260180fa01260192baf70011', 'A4复印纸', 'A1005', '4028800525d32f3f0125d33326350002', '444444', '2010-01-15', '0', '0 ');

insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('4028800525bff3c70125bff3e2440001', '国美电老鼠', 'A2007', '402881e82617aeba012617b006970001', '111111', '2010-01-17', '0', '0 ');

insert into demo_goods (GOODS_ID, GOODS_NAME, GOODS_CODE, BRAND_ID, GOODS_CLASS_ID, CREATE_DATE, MANAGER_STATUS, CHECK_STATUS)
values ('402880ab260180fa0126018fcde10005', '海尔电磁炉', 'A1002', '402880ab260180fa012601c104540014', '333333', '2010-01-18', '0', '0 ');

commit;


-- demo_goods_class...
insert into demo_goods_class (GOODS_CLASS_ID, CLASS_CODE, CLASS_NAME, PARAM_INPUT_TYPE, PARENT_ID, PURCHASE_CATEGORY_ID, PATH, NODE_LEVEL, NODE_ISLEAF)
values ('111111', 'A0001', '办公用品', '0', null, 'aaaaaa', '111111', '1', '0');

insert into demo_goods_class (GOODS_CLASS_ID, CLASS_CODE, CLASS_NAME, PARAM_INPUT_TYPE, PARENT_ID, PURCHASE_CATEGORY_ID, PATH, NODE_LEVEL, NODE_ISLEAF)
values ('222222', 'B0033', '台式电脑', '0', '111111', 'aaaaaa', '111111#222222', '2', '1');

insert into demo_goods_class (GOODS_CLASS_ID, CLASS_CODE, CLASS_NAME, PARAM_INPUT_TYPE, PARENT_ID, PURCHASE_CATEGORY_ID, PATH, NODE_LEVEL, NODE_ISLEAF)
values ('333333', 'B0053', '笔记本电脑', '0', '111111', 'bbbbbb', '111111#333333', '2', '1');

insert into demo_goods_class (GOODS_CLASS_ID, CLASS_CODE, CLASS_NAME, PARAM_INPUT_TYPE, PARENT_ID, PURCHASE_CATEGORY_ID, PATH, NODE_LEVEL, NODE_ISLEAF)
values ('444444', 'B0028', '复印纸', '0', '111111', 'bbbbbb', '111111#444444', '2', '1');

commit;

-- demo_goods_order...
insert into demo_goods_order (GOODS_ORDER_ID, GOODS_ID, ORDER_ID)
values (null, 'ff80808125bfed3b0125bfed54490001', 'ff80808125bfed3b0125bfed54690002');

insert into demo_goods_order (GOODS_ORDER_ID, GOODS_ID, ORDER_ID)
values (null, 'ff80808125bfed3b0125bfed54490001', 'ff80808125bfed3b0125bfed54690003');

commit;

-- demo_goods_param...

-- demo_option...
insert into demo_option (OPTION_ID, OPTION_NAME, OPTION_CODE, GOODS_ID)
values ('4028800525bff3c70125bff3e2630003', '罗技鼠标', 'S0001', 'ff80808125fcd68f0125fcdac9c00005');

insert into demo_option (OPTION_ID, OPTION_NAME, OPTION_CODE, GOODS_ID)
values ('4028800525bff3c70125bff3e2630004', '戴尔键盘', 'S0002', null);

insert into demo_option (OPTION_ID, OPTION_NAME, OPTION_CODE, GOODS_ID)
values ('4028800525bff3c70125bff3e2630005', '双飞燕耳机', 'S0003', null);

insert into demo_option (OPTION_ID, OPTION_NAME, OPTION_CODE, GOODS_ID)
values ('4444444', '索尼摄像头', 'S0004', null);

commit;

-- demo_order...
insert into demo_order (ORDER_ID, ORDER_NAME, ORDER_CODE)
values ('ff80808125bfed3b0125bfed54690003', '国美空调订单2', '国美空调订单222');

insert into demo_order (ORDER_ID, ORDER_NAME, ORDER_CODE)
values ('ff80808125bfed3b0125bfed54690002', '国美空调订单1', '国美空调订单111');

commit;

-- demo_purchase_category...
insert into demo_purchase_category (PURCHASE_CATEGORY_ID, PARENT_ID, CATEGORY_CODE, CATEGORY_NAME, CATEGORY_NOTE, CATEGORY_VERID)
values ('aaaaaa', null, 'ZZ78959', '桌子', '1', 1);

insert into demo_purchase_category (PURCHASE_CATEGORY_ID, PARENT_ID, CATEGORY_CODE, CATEGORY_NAME, CATEGORY_NOTE, CATEGORY_VERID)
values ('bbbbbb', 'aaaaaa', 'ZZ356G', '办公日用品', '注释', null);

commit;

-- org_employee...

-- org_role...

-- AUTH_RESOURCE...
insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057b714cd0003', '系统管理', '12', null, null, '0', '8a80808720577fff012057b714cd0003', 1, null, 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028800023e473390123e49024d80002', '自助服务', '18', '0', '0', '0', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002', 2, '8a80808720577fff012057b714cd0003', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bb890f0011', '机构管理', '1', null, null, '0', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bb890f0011', 2, '8a80808720577fff012057b714cd0003', 'URL', null, 2);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bbd2ba0012', '权限管理', '15', null, null, '0', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bbd2ba0012', 2, '8a80808720577fff012057b714cd0003', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bc75150014', '系统配置', '19', null, null, '0', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bc75150014', 2, '8a80808720577fff012057b714cd0003', 'URL', null, 4);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bcc7d80015', '系统监控', '16', null, null, '0', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bcc7d80015', 2, '8a80808720577fff012057b714cd0003', 'URL', null, 2);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bf12650019', '用户管理', 'UserController.do', '0', '0', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bbd2ba0012#8a80808720577fff012057bf12650019', 3, '8a80808720577fff012057bbd2ba0012', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057c0dc38001e', '在线用户', 'OnlineUserController.do', '0', '0', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bcc7d80015#8a80808720577fff012057c0dc38001e', 3, '8a80808720577fff012057bcc7d80015', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402880992180eb740121810578ee0012', '员工管理', 'EmployeeController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bb890f0011#402880992180eb740121810578ee0012', 3, '8a80808720577fff012057bb890f0011', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057c23b89001f', '登录日志', 'LoginLogsController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bcc7d80015#8a80808720577fff012057c23b89001f', 3, '8a80808720577fff012057bcc7d80015', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057be7d390017', '组织机构管理', 'OrganizationController.do', '0', '0', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bb890f0011#8a80808720577fff012057be7d390017', 1, '8a80808720577fff012057bb890f0011', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bf5f1d001a', '角色管理', 'RoleController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bbd2ba0012#8a80808720577fff012057bf5f1d001a', 3, '8a80808720577fff012057bbd2ba0012', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bfa83c001b', '菜单管理', 'MenuController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bbd2ba0012#8a80808720577fff012057bfa83c001b', 3, '8a80808720577fff012057bbd2ba0012', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057bffbbb001c', '资源管理', 'ResourceController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bbd2ba0012#8a80808720577fff012057bffbbb001c', 3, '8a80808720577fff012057bbd2ba0012', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057c0801e001d', '数据字典', 'DictionaryController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bc75150014#8a80808720577fff012057c0801e001d', 3, '8a80808720577fff012057bc75150014', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('8a80808720577fff012057c272390020', '操作日志', 'OperLogsController.do', null, null, '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bcc7d80015#8a80808720577fff012057c272390020', 3, '8a80808720577fff012057bcc7d80015', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402880992180eb740121810578ee0007', '修改密码', 'UserController.do?method=toUpdateMyPsw', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002#402880992180eb740121810578ee0007', 3, '4028800023e473390123e49024d80002', 'URL', null, 2);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402886e02627474c01262749b1700003', '维护机构新增规则', 'OrganizationRuleController.do', '0', '1', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bb890f0011#402886e02627474c01262749b1700003', 3, '8a80808720577fff012057bb890f0011', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402886e026450ad50126450caa970003', '系统日志', 'SysLogsController.do', '0', '0', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bcc7d80015#402886e026450ad50126450caa970003', 3, '8a80808720577fff012057bcc7d80015', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402886e0264b65d401264b6758760003', '维护账号信息', 'UserController.do?method=toUpdateMyInfo', '0', '0', '0', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002#402886e0264b65d401264b6758760003', 3, '4028800023e473390123e49024d80002', 'URL', null, 1);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402887e2269cf7bb01269d8a32d90012', '4', '4', '1', '1', '1', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002#402886e0264b65d401264b6758760003#402887e2269cf7bb01269d8a32d90012', 4, '402886e0264b65d401264b6758760003', 'METHOD', null, 2);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402887e2269cf7bb01269d6c9d4d000b', '3333', '3', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002#402886e0264b65d401264b6758760003#402887e2269cf7bb01269d6c9d4d000b', 1, '402886e0264b65d401264b6758760003', 'METHOD', null, 1);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402886dd26ab7ae50126ab8293570006', '系统工具', 'xtgj', '0', '0', '0', '8a80808720577fff012057b714cd0003#402886dd26ab7ae50126ab8293570006', 2, '8a80808720577fff012057b714cd0003', 'URL', null, 3);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402886dd26ab7ae50126ab8317aa0008', '图形插件xml工具', 'view/resource/plug-in/FusionChart/Tools/XMLGenerator/XMLGenerator.html', '0', '0', '1', '8a80808720577fff012057b714cd0003#402886dd26ab7ae50126ab8293570006#402886dd26ab7ae50126ab8317aa0008', 1, '402886dd26ab7ae50126ab8293570006', 'URL', null, null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726ee58760126ee5a69290002', '桌面小工具', '1', '0', '0', '0', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002', 2, '8a80808720577fff012057b714cd0003', 'URL', '2010-02-21-10.28.07.593000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726ee58760126ee5b584c0003', '天气预报', 'view/srplatform/portal/airReport.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726ee58760126ee5b584c0003', 1, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-21-10.29.08.812000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f35f200126f3776fb20005', '小工具管理', 'GadgetController.do', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002#4028e4e726f35f200126f3776fb20005', 3, '4028800023e473390123e49024d80002', 'URL', '2010-02-22-10.17.55.890000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f475ebc40004', '待确认合同', 'view/srplatform/portal/waitCheckContract.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f475ebc40004', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.55.53.796000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f476a6c70006', '待审批行情', 'view/srplatform/portal/waitCheckGoodsPrice.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f476a6c70006', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.56.41.671000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f47702930007', '待审批采购计划', 'view/srplatform/portal/waitCheckOrderPlan.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f47702930007', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.57.05.171000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f477cba00009', '待接收订单通知书', 'view/srplatform/portal/waitReceiveOrderNotic.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f477cba00009', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.57.56.625000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402887e326f490eb0126f492871b0003', '系统配置管理', 'SysConfigController.do?method=toSysconfigTypeListView', '0', '0', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bc75150014#402887e326f490eb0126f492871b0003', 1, '8a80808720577fff012057bc75150014', 'URL', '2010-02-22-07.27.08.571000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f473556a0003', '待审品牌', 'view/srplatform/portal/waitCheckBrand.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f473556a0003', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.53.04.234000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f476384e0005', '待审批商品', 'view/srplatform/portal/waitCheckGoods.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f476384e0005', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.56.13.390000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402887e2269cf7bb01269d6b2ce5000a', '22', '22', '1', '1', '1', '8a80808720577fff012057b714cd0003#4028800023e473390123e49024d80002#402886e0264b65d401264b6758760003#402887e2269cf7bb01269d6b2ce5000a', 1, '402886e0264b65d401264b6758760003', 'METHOD', null, 3);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f47752980008', '待审批项目方案', 'view/srplatform/portal/waitCheckProject.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f47752980008', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.57.25.656000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f470420126f478441c000a', '待接收采购计划', 'view/srplatform/portal/waitReceivePlanNotic.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f470420126f478441c000a', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-14.58.27.484000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('4028e4e726f4fe750126f50032340001', '万年历', 'view/srplatform/portal/calendarGadget.html', '0', '0', '1', '8a80808720577fff012057b714cd0003#4028e4e726ee58760126ee5a69290002#4028e4e726f4fe750126f50032340001', 3, '4028e4e726ee58760126ee5a69290002', 'GADGET', '2010-02-22-17.26.55.796000', null);

insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, CREATE_TIME, RES_SORT)
values ('402886dd26ac517d0126ac52df7f0003', '远程维护', 'view/srplatform/sysconfig/browser.jsp', '0', '0', '1', '8a80808720577fff012057b714cd0003#8a80808720577fff012057bcc7d80015#402886dd26ac517d0126ac52df7f0003', 1, '8a80808720577fff012057bcc7d80015', 'URL', null, null);

commit;

-- AUTH_ROLE_RESOURCE...
insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '8a80808720577fff012057b714cd0003', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '8a80808720577fff012057bb890f0011', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '8a80808720577fff012057bbd2ba0012', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f478441c000a', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726ee58760126ee5b584c0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f47702930007', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f475ebc40004', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726ee58760126ee5a69290002', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '8a80808720577fff012057be7d390017', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f47752980008', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f473556a0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f476384e0005', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '402880992180eb740121810578ee0012', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f476a6c70006', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f470420126f477cba00009', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '4028e4e726f4fe750126f50032340001', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('402886de269378fe012693d5f342000c', '8a80808720577fff012057bf5f1d001a', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '4028e4e726ee58760126ee5a69290002', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '8a80808720577fff012057bb890f0011', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '402886e0264b65d401264b6758760003', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '4028800023e473390123e49024d80002', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '8a80808720577fff012057b714cd0003', '2');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '4028e4e726f4fe750126f50032340001', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '402880992180eb740121810578ee0007', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '4028e4e726ee58760126ee5b584c0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('4028809925d348da0125d352656a0002', '402880992180eb740121810578ee0012', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402886dd26ac517d0126ac52df7f0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057b714cd0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028800023e473390123e49024d80002', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bb890f0011', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bbd2ba0012', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bc75150014', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bcc7d80015', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bf12650019', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057c0dc38001e', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402880992180eb740121810578ee0012', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057c23b89001f', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057be7d390017', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bf5f1d001a', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bfa83c001b', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057bffbbb001c', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057c0801e001d', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '8a80808720577fff012057c272390020', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402880992180eb740121810578ee0007', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402886e02627474c01262749b1700003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402886e026450ad50126450caa970003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402886e0264b65d401264b6758760003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402887e2269cf7bb01269d8a32d90012', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402887e2269cf7bb01269d6c9d4d000b', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402886dd26ab7ae50126ab8293570006', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402886dd26ab7ae50126ab8317aa0008', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726ee58760126ee5a69290002', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726ee58760126ee5b584c0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f35f200126f3776fb20005', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f475ebc40004', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f476a6c70006', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f47702930007', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f477cba00009', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402887e326f490eb0126f492871b0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f473556a0003', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f476384e0005', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '402887e2269cf7bb01269d6b2ce5000a', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f47752980008', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f470420126f478441c000a', '1');

insert into AUTH_ROLE_RESOURCE (ROL_ID, RES_ID, ISCHECKED)
values ('8a80808720577fff012057cbd898002e', '4028e4e726f4fe750126f50032340001', '1');

commit;

-- auth_user_gadget...
insert into auth_user_gadget (OBJID, USER_ID, GADGET_ID, OPENABLE, COLUMNINDEX, ROWINDEX, MAXABLE)
values ('402886de26feead40126feecc4be0009', '402886de2693eefc012693f059220002', '4028e4e726f35f200126f36f9ed70002', '1', 0, 0, '1');

insert into auth_user_gadget (OBJID, USER_ID, GADGET_ID, OPENABLE, COLUMNINDEX, ROWINDEX, MAXABLE)
values ('402886de26feead40126feeb5fee0003', '8a808083204329sr01204330167b0005', '4028e4e726f470420126f47c7460000c', '1', 0, 1, '1');

insert into auth_user_gadget (OBJID, USER_ID, GADGET_ID, OPENABLE, COLUMNINDEX, ROWINDEX, MAXABLE)
values ('402886de26fedd3e0126fee7088a0016', '8a808083204329sr01204330167b0005', '4028e4e726f35f200126f36f9ed70002', '1', 0, 0, '1');

commit;

-- auth_gadget...
insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f35f200126f36f9ed70002', '天气预报', '天气预报描述', '4028e4e726ee58760126ee5b584c0003', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47d3399000e', '待审批采购计划', '待审批采购计划', '4028e4e726f470420126f47702930007', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47c7460000c', '待确认合同', '待确认合同', '4028e4e726f470420126f475ebc40004', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47ce1d0000d', '待审批行情', '待审批行情', '4028e4e726f470420126f476a6c70006', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47df42a000f', '待接收订单通知书', '待接收订单通知书', '4028e4e726f470420126f477cba00009', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47e55160010', '待审品牌', '待审品牌', '4028e4e726f470420126f473556a0003', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47ea46f0011', '待审批项目方案', '待审批项目方案', '4028e4e726f470420126f47752980008', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f470420126f47f00990012', '待接收采购计划', '待接收采购计划', '4028e4e726f470420126f478441c000a', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f4a4c00126f4a60bb70003', '待审批商品', '待审批商品', '4028e4e726f470420126f476384e0005', null, null);

insert into auth_gadget (OBJID, NAME, DESCS, RES_ID, CREATETIME, IMAGE_ID)
values ('4028e4e726f4fe750126f50161ac0002', '万年历', '万年历', '4028e4e726f4fe750126f50032340001', null, null);

commit;

-- AUTH_MENU...
insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c505670022', '系统管理', '系统管理', '1', 0, '0', '系统管理', null, null, '0', '0', '8a80808720577fff012057c505670022', 1, null, '8a80808720577fff012057b714cd0003');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('4028800023e473390123e4a3f2d90004', '自助服务', '自助服务', '1', null, '0', '自助服务', '8a80808720577fff012057c505670022', null, '0', '0', '8a80808720577fff012057c505670022#4028800023e473390123e4a3f2d90004', 2, null, '4028800023e473390123e49024d80002');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c5c59a0024', '机构管理', '机构管理', '1', 1, '0', '机构管理', '8a80808720577fff012057c505670022', null, '0', '0', '8a80808720577fff012057c505670022#8a80808720577fff012057c5c59a0024', 2, null, '8a80808720577fff012057bb890f0011');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c63a4d0025', '权限管理', '权限管理', '1', 2, '0', '权限管理', '8a80808720577fff012057c505670022', null, '0', '0', '8a80808720577fff012057c505670022#8a80808720577fff012057c63a4d0025', 2, null, '8a80808720577fff012057bbd2ba0012');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c690ca0026', '系统配置', '系统配置', '1', 4, '0', '系统配置', '8a80808720577fff012057c505670022', null, '0', '0', '8a80808720577fff012057c505670022#8a80808720577fff012057c690ca0026', 2, null, '8a80808720577fff012057bc75150014');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c6e5df0027', '系统监控', '系统监控', '1', 3, '0', '系统监控', '8a80808720577fff012057c505670022', null, '0', '0', '8a80808720577fff012057c505670022#8a80808720577fff012057c6e5df0027', 2, null, '8a80808720577fff012057bcc7d80015');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('4028809921810884012181098cc10005', '员工管理', '员工管理', '1', 3, '0', '员工管理', '8a80808720577fff012057c5c59a0024', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c5c59a0024#4028809921810884012181098cc10005', 3, null, '402880992180eb740121810578ee0012');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c790290029', '组织机构管理', '组织机构管理', '1', 2, '0', '组织机构管理', '8a80808720577fff012057c5c59a0024', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c5c59a0024#8a80808720577fff012057c790290029', 3, null, '8a80808720577fff012057be7d390017');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c86ea2002a', '用户管理', '用户管理', '1', 1, '0', '用户管理', '8a80808720577fff012057c63a4d0025', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c63a4d0025#8a80808720577fff012057c86ea2002a', 3, null, '8a80808720577fff012057bf12650019');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c8c993002b', '角色管理', '角色管理', '1', 2, '0', '角色管理', '8a80808720577fff012057c63a4d0025', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c63a4d0025#8a80808720577fff012057c8c993002b', 3, null, '8a80808720577fff012057bf5f1d001a');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c959fc002c', '菜单管理', '菜单管理', '1', 3, '0', '菜单管理', '8a80808720577fff012057c63a4d0025', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c63a4d0025#8a80808720577fff012057c959fc002c', 3, null, '8a80808720577fff012057bfa83c001b');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a80808720577fff012057c9d7e6002d', '资源管理', '资源管理', '1', 4, '0', '资源管理', '8a80808720577fff012057c63a4d0025', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c63a4d0025#8a80808720577fff012057c9d7e6002d', 3, null, '8a80808720577fff012057bffbbb001c');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a808087205ac66c01205ace1b7c0001', '数据字典', '数据字典', '1', 1, '0', '数据字典', '8a80808720577fff012057c690ca0026', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c690ca0026#8a808087205ac66c01205ace1b7c0001', 3, null, '8a80808720577fff012057c0801e001d');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a808084205c3bab01205c3f8afc0008', '登录日志', '登录日志', '1', 1, '0', '登录日志', '8a80808720577fff012057c6e5df0027', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c6e5df0027#8a808084205c3bab01205c3f8afc0008', 3, null, '8a80808720577fff012057c23b89001f');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a808084205c3bab01205c4037b7000d', '在线用户', '在线用户', '1', 2, '0', '在线用户', '8a80808720577fff012057c6e5df0027', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c6e5df0027#8a808084205c3bab01205c4037b7000d', 3, null, '8a80808720577fff012057c0dc38001e');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('8a808084205c3bab01205c4072cc000f', '操作日志', '操作日志', '1', 3, '0', '操作日志', '8a80808720577fff012057c6e5df0027', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c6e5df0027#8a808084205c3bab01205c4072cc000f', 3, null, '8a80808720577fff012057c272390020');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('4028809921810884012181098cc10003', '修改密码', '修改密码', '1', 1, '0', '修改密码', '4028800023e473390123e4a3f2d90004', null, '0', '1', '8a80808720577fff012057c505670022#4028800023e473390123e4a3f2d90004#4028809921810884012181098cc10003', 3, null, '402880992180eb740121810578ee0007');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402886e02627474c01262749b1700002', '维护机构新增规则', '维护机构新增规则', '1', null, '0', '维护机构新增规则', '8a80808720577fff012057c5c59a0024', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c5c59a0024#402886e02627474c01262749b1700002', 3, null, '402886e02627474c01262749b1700003');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402886e026450ad50126450caa970002', '系统日志', '系统日志', '1', null, '0', '系统日志', '8a80808720577fff012057c6e5df0027', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c6e5df0027#402886e026450ad50126450caa970002', 3, null, '402886e026450ad50126450caa970003');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402886e0264b65d401264b6758760002', '维护账号信息', '维护账号信息', '1', null, '0', '维护账号信息', '4028800023e473390123e4a3f2d90004', null, '0', '0', '8a80808720577fff012057c505670022#4028800023e473390123e4a3f2d90004#402886e0264b65d401264b6758760002', 3, null, '402886e0264b65d401264b6758760003');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402887e2269cf7bb01269d6a417b0008', null, '1', '1', null, '0', null, '402886e0264b65d401264b6758760002', null, '0', '1', '8a80808720577fff012057c505670022#4028800023e473390123e4a3f2d90004#402886e0264b65d401264b6758760002', 4, null, null);

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402886dd26ab7ae50126ab8293570005', null, '系统工具', '1', null, '0', null, '8a80808720577fff012057c505670022', null, '0', '0', '8a80808720577fff012057c505670022', 2, null, '402886dd26ab7ae50126ab8293570006');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402886dd26ab7ae50126ab8317aa0007', null, '图形插件xml工具', '1', null, '0', null, '402886dd26ab7ae50126ab8293570005', null, '0', '1', '8a80808720577fff012057c505670022', 3, null, '402886dd26ab7ae50126ab8317aa0008');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('4028e4e726f35f200126f3776fb20004', null, '小工具管理', '1', null, '0', null, '4028800023e473390123e4a3f2d90004', null, '0', '1', '8a80808720577fff012057c505670022#4028800023e473390123e4a3f2d90004', 3, '2010-02-22-10.17.55.890000', '4028e4e726f35f200126f3776fb20005');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402887e326f490eb0126f4b4fb2c0010', '系统配置管理', '系统配置管理', '1', null, '0', '系统配置管理', '8a80808720577fff012057c690ca0026', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c690ca0026#402887e326f490eb0126f4b4fb2c0010', 3, '2010-02-22-08.04.46.508000', '402887e326f490eb0126f492871b0003');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, CREATE_TIME, RES_ID)
values ('402886dd26ac517d0126ac52df7f0002', null, '远程维护', '1', null, '0', null, '8a80808720577fff012057c6e5df0027', null, '0', '1', '8a80808720577fff012057c505670022#8a80808720577fff012057c6e5df0027', 3, null, '402886dd26ac517d0126ac52df7f0003');

commit;