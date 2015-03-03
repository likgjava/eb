/*
Source Server         : srplatform
Source Server Version : SVN-1595
Source Host           : 192.168.2.10
Source Database       : srplatform
Target Server Type    : MySQL
Date: 2010-2-25 21:10:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `auth_conflict_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_conflict_role`;
CREATE TABLE `auth_conflict_role` (
  `CON_ID` varchar(100) not NULL,
  `CON_NAME` varchar(40),
  `ORG_ID` varchar(100),
  `CREATE_TIME` datetime,
  `CON_ROLES` text,
  PRIMARY KEY (`CON_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_data_res_type`
-- ----------------------------
DROP TABLE IF EXISTS `auth_data_res_type`;
CREATE TABLE `auth_data_res_type` (
  `DAT_ID` varchar(100) not NULL,
  `DAT_IS_ORGAN` varchar(2) default '0' not null,
  `DAT_TABLE_NAME` varchar(40),
  `DAT_TABLE_DESC` varchar(100),
  `DAT_COL_VALUE` varchar(40),
  `DAT_VCOL_DESC` varchar(100),
  `DAT_COL_NAME` varchar(40),
  `DAT_NCOL_DESC` varchar(100),
  `DAT_USE_WHERE` varchar(2) default '0' not null,
  `CREATE_TIME` datetime,
  `DAT_WHERE` varchar(100),
  PRIMARY KEY (`DAT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_gadget`
-- ----------------------------
DROP TABLE IF EXISTS `auth_gadget`;
CREATE TABLE `auth_gadget` (
  `OBJID` varchar(100) not null,
  `NAME` varchar(200) not null,
  `DESCS` text,
  `RES_ID` text not null,
  `CREATETIME` datetime  ,
  `IMAGE_ID` varchar(100)  ,
  PRIMARY KEY (`OBJID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_menu`
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE `auth_menu` (
  `MNU_ID` varchar(100) not null,
  `MNU_MEMO` varchar(200)  ,
  `MNU_NAME` varchar(40) not null,
  `MNU_SHOWFLAG` varchar(1) default '1',
  `MNU_SORT` decimal(19,0)  ,
  `MNU_TARGET` varchar(20) default '0',
  `MNU_TIP` varchar(40)  ,
  `MNU_PARENT_ID` varchar(100)  ,
  `MNU_ICON` varchar(40)  ,
  `MNU_IS_DEFAULT` varchar(4) default '0',
  `MNU_IS_LEAF` varchar(1) default '1',
  `MNU_PATH` text,
  `MNU_LEVEL` decimal(19,0)  ,
  `CREATE_TIME` datetime  ,
  `RES_ID` varchar(100)  ,
  PRIMARY KEY (`MNU_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_orgnization`
-- ----------------------------
DROP TABLE IF EXISTS `auth_orgnization`;
CREATE TABLE `auth_orgnization` (
  `ORG_ID` varchar(100) not null,
  `ORG_ADDRESS` text,
  `ORG_AUDIT_OPINION` text,
  `ORG_AUDIT_STATUS` varchar(2) default '0' not null,
  `ORG_CODE` varchar(30)  ,
  `ORG_CONTACT` varchar(100)  ,
  `ORG_CROPORATE` varchar(100)  ,
  `ORG_EMAIL` varchar(100)  ,
  `ORG_FAX` varchar(100)  ,
  `ORG_LEADER` varchar(100)  ,
  `ORG_NAME` text,
  `ORG_POST_CODE` varchar(200)  ,
  `ORG_SHORT_NAME` varchar(60)  ,
  `ORG_SORT` decimal(19,0)  ,
  `ORG_STATUS` varchar(2) default '1' not null,
  `ORG_SUPERVISOR` varchar(100)  ,
  `ORG_TEL` varchar(100)  ,
  `ORG_TYPE` varchar(2) not null,
  `ORG_BUDGET_CODE` text,
  `ORG_CITY` varchar(100)  ,
  `ORG_PARENT_ID` varchar(100)  ,
  `ORG_PROVINCE` varchar(100)  ,
  `ORG_TOWN` varchar(100)  ,
  `ORG_PATH` text,
  `ORG_IS_LEAF` varchar(2) default '1' not null,
  `CREATE_TIME` datetime  ,
  `ORG_LEVEL` varchar(2)  ,
  PRIMARY KEY (`ORG_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_orgnization_rule`
-- ----------------------------
DROP TABLE IF EXISTS `auth_orgnization_rule`;
CREATE TABLE `auth_orgnization_rule` (
  `RULE_ID` varchar(100) not null,
  `ORG_SOURCE` varchar(2)  ,
  `ORG_TARGET` varchar(2)  ,
  `ORG_RULE` varchar(100)  ,
  `CREATE_TIME` datetime  ,
  `STRU_TYPE_ID` varchar(100)  ,
  PRIMARY KEY (`RULE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_org_detail`
-- ----------------------------
DROP TABLE IF EXISTS `auth_org_detail`;
CREATE TABLE `auth_org_detail` (
  `ORG_DETAIL_ID` varchar(120) not null,
  `ORG_REG_NO` varchar(100)  ,
  `ORG_REG_PERSON` varchar(100)  ,
  `ORG_REG_BUSINESS` text,
  `ORG_REG_ISSUE_AUTHORITY` varchar(200)  ,
  `ORG_REG_DATE` datetime  ,
  `ORG_REG_VALIDITY` datetime  ,
  `ORG_REG_ADDRESS` text,
  `ORG_FIRST_CERTIFICATE_DATE` datetime  ,
  `ORG_COUNT_B_DATE` datetime  ,
  `ORG_COUNT_E_DATE` datetime  ,
  `ORG_INTRODUCTION` text,
  `ORG_CODE_FILE` varchar(100)  ,
  `ORG_REG_CERTIFICATE` text,
  `ORG_NATURE` varchar(100)  ,
  `CREATE_TIME` datetime  ,
  `ORG_LOGO` varchar(100)  ,
  PRIMARY KEY (`ORG_DETAIL_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_org_employee`
-- ----------------------------
DROP TABLE IF EXISTS `auth_org_employee`;
CREATE TABLE `auth_org_employee` (
  `EMP_ID` varchar(100) not null,
  `EMP_NAME` varchar(100) not null,
  `EMP_MOBILE` varchar(100)  ,
  `EMP_EMAIL` varchar(40)  ,
  `EMP_MSN` varchar(40)  ,
  `EMP_QQ` varchar(40)  ,
  `EMP_TEL_OFFICE` varchar(40)  ,
  `EMP_TEL_HOME` varchar(40)  ,
  `EMP_SORT` decimal(19,0)  ,
  `EMP_CREATED_DATE` datetime  ,
  `EMP_COMPANY_ID` varchar(100)  ,
  `EMP_DEPARTMENT_ID` varchar(100)  ,
  `EMP_POST_ID` varchar(100)  ,
  `EMP_NUMBER` varchar(20)  ,
  `CREATE_TIME` datetime  ,
  `EMP_ORG_ID` varchar(100)  ,
  PRIMARY KEY (`EMP_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_org_stru_type`
-- ----------------------------
DROP TABLE IF EXISTS `auth_org_stru_type`;
CREATE TABLE `auth_org_stru_type` (
  `STR_ID` varchar(100) not null,
  `CREATE_TIME` datetime  ,
  `STR_NAME` varchar(100)  ,
  PRIMARY KEY (`STR_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_resource`
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource` (
  `RES_ID` varchar(100) not null,
  `RES_NAME` varchar(40) not null,
  `RES_URL` varchar(200)  ,
  `RES_IS_LOG` varchar(1)  ,
  `RES_IS_SYS` varchar(1) default '0',
  `RES_IS_LEAF` varchar(1) default '1',
  `RES_PATH` text,
  `RES_LEVEL` decimal(19,0)  ,
  `RES_PARENT_ID` varchar(100)  ,
  `RES_TYPE` varchar(12)  ,
  `CREATE_TIME` datetime  ,
  `RES_SORT` decimal(19,0)  ,
  PRIMARY KEY (`RES_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `ROL_ID` varchar(100) not null,
  `ROL_EN_NAME` varchar(200) not null,
  `ORG_ID` varchar(100)  ,
  `ROL_CH_NAME` varchar(80) not null,
  `ROL_IS_DEFAULT` varchar(1) default '0' not null,
  `ROL_MEMO` varchar(200)  ,
  `ROL_STATUS` varchar(1) default '1' not null,
  `ROL_TYPE` varchar(2)  ,
  `ROL_PARENT_ID` varchar(100)  ,
  `DEPT_ID` varchar(72)  ,
  `CREATE_TIME` datetime  ,
  `ROL_SORT` decimal(19,0)  ,
  PRIMARY KEY (`ROL_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_role_extend`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_extend`;
CREATE TABLE `auth_role_extend` (
  `EXTEND_ROLE_ID` varchar(100) not null,
  `ROL_ID` varchar(100) not null
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_resource`;
CREATE TABLE `auth_role_resource` (
  `ROL_ID` varchar(100)  ,
  `RES_ID` varchar(100)  ,
  `ISCHECKED` varchar(2) default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_user`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `USR_ID` varchar(100) not null,
  `USR_NAME` varchar(40) not null,
  `USR_CA_SN` varchar(200)  ,
  `USR_IS_CREDENTIAL` varchar(2)default '0' not null,
  `USR_IS_EXPIRED` varchar(2)default '0' not null,
  `USR_IS_LOCKED` varchar(2)default '0' not null,
  `USR_PASSWORD` varchar(200) not null,
  `USR_PERIOD_VALIDITY` datetime  ,
  `USR_PWD_IS_EXPIRED` varchar(2) default '0' not null,
  `USR_PWD_TIP` varchar(60)  ,
  `USR_PWD_TIP_ANS` varchar(60)  ,
  `USR_SORT` decimal(19,0)  ,
  `USR_STATUS` varchar(2) default '1' not null,
  `USR_PWD_PERIOD_VALIDITY` datetime  ,
  `USR_IS_USE_IPCHECK` varchar(2) default '0' not null,
  `USR_IS_ADMIN` varchar(2) default '0' not null,
  `USR_IPPOLICY` text,
  `USR_CREATED_DATE` datetime  ,
  `USR_CHANGEPWD` varchar(2) default '1' not null,
  `CREATE_TIME` datetime  ,
  `EMP_ID` varchar(100)  ,
  PRIMARY KEY (`USR_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_user_gadget`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_gadget`;
CREATE TABLE `auth_user_gadget` (
  `OBJID` varchar(100) not null,
  `USER_ID` varchar(100)  ,
  `GADGET_ID` varchar(100)  ,
  `OPENABLE` varchar(2)  ,
  `COLUMNINDEX` decimal(1,0)  ,
  `ROWINDEX` decimal(1,0)  ,
  `MAXABLE` varchar(2)  ,
  PRIMARY KEY (`OBJID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `USR_ID` varchar(100) not null,
  `ROL_ID` varchar(100) not null
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `base_attachment`;
CREATE TABLE `base_attachment` (
  `RES_ID` varchar(100) not null,
  `ATT_CREATOR` varchar(100)  ,
  `ATT_FILE_NAME` varchar(200)  ,
  `ATT_FILE_SIZE` decimal(10,0)  ,
  `ATT_FILE_TYPE` varchar(100)  ,
  `ATT_PATH` text,
  `ATT_RELATION_ID` varchar(100)  ,
  `ATT_REMARK` text,
  `ATT_SORTNO` decimal(10,0)  ,
  `ATT_UPLOAD_IP` varchar(100)  ,
  `ATT_UPLOAD_TIME` datetime  ,
  `CREATE_TIME` datetime  ,
  `ATT_VIEW_NAME` text,
  PRIMARY KEY (`RES_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary`;
CREATE TABLE `base_dictionary` (
  `DIC_ID` varchar(100) not null,
  `DIC_NAME` varchar(40) not null,
  `DIC_GROUP_CODE` varchar(120)  ,
  `DIC_CACHE_NAME` varchar(40)  ,
  `DIC_USE_CACHE` varchar(2)  ,
  `DIC_VALUE` varchar(100)  ,
  `CREATE_TIME` datetime  ,
  `DIC_MEMO` text,
  PRIMARY KEY (`DIC_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_dictionary_type`
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionary_type`;
CREATE TABLE `base_dictionary_type` (
  `DIC_GROUP_CODE` varchar(120) not null,
  `CREATE_TIME` datetime  ,
  `DIC_GROUP_NAME` varchar(120) not null,
  PRIMARY KEY (`DIC_GROUP_CODE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_district`
-- ----------------------------
DROP TABLE IF EXISTS `base_district`;
CREATE TABLE `base_district` (
  `DISTRICT_ID` varchar(100) not null,
  `DISTRICT_CODE` varchar(80)  ,
  `DISTRICT_NAME` varchar(120)  ,
  `DISTRICT_SHORT_NAME` varchar(80)  ,
  `CREATE_TIME` datetime  ,
  `DISTRICT_PARENT_ID` varchar(100)  ,
  PRIMARY KEY (`DISTRICT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_industry`
-- ----------------------------
DROP TABLE IF EXISTS `base_industry`;
CREATE TABLE `base_industry` (
  `INDUSTRY_ID` varchar(72) not null,
  `INDUSTRY_MEMO` varchar(200)  ,
  `INDUSTRY_NAME` varchar(100)  ,
  `INDUSTRY_SORT_NO` varchar(20)  ,
  `CREATE_TIME` datetime  ,
  `INDUSTRY_PARENT_ID` varchar(72) ,
  PRIMARY KEY (`INDUSTRY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_login_logs`
-- ----------------------------
DROP TABLE IF EXISTS `base_login_logs`;
CREATE TABLE `base_login_logs` (
  `LGN_ID` varchar(100) not null,
  `LGN_FROM_IP` varchar(40)  ,
  `LGN_IN_TIME` datetime  ,
  `LGN_OUT_TIME` datetime  ,
  `CREATE_TIME` datetime  ,
  `USR_ID` varchar(100)  ,
  PRIMARY KEY (`LGN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_oper_logs`
-- ----------------------------
DROP TABLE IF EXISTS `base_oper_logs`;
CREATE TABLE `base_oper_logs` (
  `LOG_ID` varchar(100) not null,
  `LOG_CLASS_NAME` varchar(120)  ,
  `LOG_RES_NAME` varchar(100)  ,
  `LOG_URL` varchar(200)  ,
  `LOG_USR_IP` varchar(40)  ,
  `LOG_VISIT_DATE` datetime  ,
  `LOG_METHOD_NAME` varchar(120)  ,
  `ORGNAME` varchar(100)  ,
  `LOGIN_LOG_ID` varchar(100)  ,
  `CREATE_TIME` datetime  ,
  `LOG_USR_ID` varchar(100)  ,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_sequence_number`
-- ----------------------------
DROP TABLE IF EXISTS `base_sequence_number`;
CREATE TABLE `base_sequence_number` (
  `SEN_ID` varchar(100) not null,
  `SEN_BIZ_NAME` varchar(200) ,
  `CREATE_TIME` datetime ,
  `SEN_SEQUENCE_NO` varchar(200) ,
  PRIMARY KEY (`SEN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_sysconfigitem`
-- ----------------------------
DROP TABLE IF EXISTS `base_sysconfigitem`;
CREATE TABLE `base_sysconfigitem` (
  `ITEM_ID` varchar(36) not null,
  `TYPE_ID` varchar(36) not null,
  `ITEM_CODE` varchar(50) not null,
  `ITEM_NAME` varchar(50) not null,
  `ITEM_DATA_TYPE` varchar(20) not null,
  `ITEM_SORT` decimal(8,2) default 0 not null,
  `ITEM_NOTES` text not null,
  `ITEM_PATH` varchar(100) not null,
  `CREATE_TIME` datetime not null,
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_sysconfigitem_value`
-- ----------------------------
DROP TABLE IF EXISTS `base_sysconfigitem_value`;
CREATE TABLE `base_sysconfigitem_value` (
  `ITEM_VALUE_ID` varchar(36) not null,
  `ITEM_ID` varchar(36) not null,
  `ITEM_VALUE_DETAIL` text not null,
  `PRE_VALUE_ID` varchar(36)  ,
  `PRE_VALUE_NAME` varchar(50)  ,
  `ITEM_VALUE_NOTES` text,
  `CREATE_TIME` datetime not null,
  PRIMARY KEY (`ITEM_VALUE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_sysconfig_pre_values`
-- ----------------------------
DROP TABLE IF EXISTS `base_sysconfig_pre_values`;
CREATE TABLE `base_sysconfig_pre_values` (
  `PRE_VALUE_ID` varchar(36) not null,
  `ITEM_ID` varchar(36) not null,
  `PRE_VALUE_NAME` varchar(50) not null,
  `PRE_VALUE_CODE` varchar(50) not null,
  `PRE_VALUE_SORT` decimal(8,2) default 0 not null,
  `PRE_VALUE_NOTES` text not null,
  `CREATE_TIME` datetime not null,
  PRIMARY KEY (`PRE_VALUE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `base_sysconfig_type`
-- ----------------------------
DROP TABLE IF EXISTS `base_sysconfig_type`;
CREATE TABLE `base_sysconfig_type` (
  `TYPE_ID` varchar(36) not null,
  `TYPE_PARENT_ID` varchar(36)  ,
  `TYPE_NAME` varchar(50) not null,
  `TYPE_CODE` varchar(50) not null,
  `TYPE_KIND` varchar(50)  ,
  `TYPE_IS_LEAF` varchar(1) default '1',
  `TYPE_SORT` decimal(8,2) default 0 not null,
  `TYPE_NOTES` text not null,
  `TYPE_LEVEL` decimal(8,2)  ,
  `TYPE_PATH` varchar(100) not null,
  `CREATE_TIME` datetime not null,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_additional`
-- ----------------------------
DROP TABLE IF EXISTS `demo_additional`;
CREATE TABLE `demo_additional` (
  `ADDITIONAL_NAME` varchar(100)  ,
  `ADDITIONAL_DESC` varchar(100)  ,
  `GOODS_ID` varchar(36)  ,
  `ADDITIONAL_ID` varchar(36)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_area`
-- ----------------------------
DROP TABLE IF EXISTS `demo_area`;
CREATE TABLE `demo_area` (
  `ARAE_ID` varchar(36) not null,
  `EN_NAME` varchar(100)  ,
  `CN_NAME` varchar(100)  ,
  `CN_PY_NAME` varchar(100)  ,
  `SUB_NAME` varchar(100)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_brand`
-- ----------------------------
DROP TABLE IF EXISTS `demo_brand`;
CREATE TABLE `demo_brand` (
  `BRAND_ID` varchar(36) not null,
  `BRAND_NAME` varchar(100)  ,
  `BRAND_FACTORY` varchar(100)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_goods`
-- ----------------------------
DROP TABLE IF EXISTS `demo_goods`;
CREATE TABLE `demo_goods` (
  `GOODS_ID` varchar(36) not null,
  `GOODS_NAME` varchar(100)  ,
  `GOODS_CODE` varchar(100)  ,
  `BRAND_ID` varchar(36)  ,
  `GOODS_CLASS_ID` varchar(36)  ,
  `CREATE_DATE` datetime  ,
  `MANAGER_STATUS` varchar(1) default 0,
  `CHECK_STATUS` varchar(2) default 0 
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_goods_class`
-- ----------------------------
DROP TABLE IF EXISTS `demo_goods_class`;
CREATE TABLE `demo_goods_class` (
  `GOODS_CLASS_ID` varchar(36)  ,
  `CLASS_CODE` varchar(100)  ,
  `CLASS_NAME` varchar(100)  ,
  `PARAM_INPUT_TYPE` varchar(1)  ,
  `PARENT_ID` varchar(36)  ,
  `PURCHASE_CATEGORY_ID` varchar(36)  ,
  `PATH` varchar(200)  ,
  `NODE_LEVEL` varchar(1)  ,
  `NODE_ISLEAF` varchar(1)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_goods_order`
-- ----------------------------
DROP TABLE IF EXISTS `demo_goods_order`;
CREATE TABLE `demo_goods_order` (
  `GOODS_ORDER_ID` varchar(36)  ,
  `GOODS_ID` varchar(36)  ,
  `ORDER_ID` varchar(36)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_goods_param`
-- ----------------------------
DROP TABLE IF EXISTS `demo_goods_param`;
CREATE TABLE `demo_goods_param` (
  `GOODS_PARAM_ID` varchar(36) not null,
  `GOODS_PARAM_NAME` varchar(100) ,
  `GOODS_PARAM_CODE` varchar(100)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_option`
-- ----------------------------
DROP TABLE IF EXISTS `demo_option`;
CREATE TABLE `demo_option` (
  `OPTION_ID` varchar(36) not null,
  `OPTION_NAME` varchar(100)  ,
  `OPTION_CODE` varchar(100)  ,
  `GOODS_ID` varchar(36)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_order`
-- ----------------------------
DROP TABLE IF EXISTS `demo_order`;
CREATE TABLE `demo_order` (
  `ORDER_ID` varchar(36) not null,
  `ORDER_NAME` varchar(100)  ,
  `ORDER_CODE` varchar(100)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `demo_purchase_category`
-- ----------------------------
DROP TABLE IF EXISTS `demo_purchase_category`;
CREATE TABLE `demo_purchase_category` (
  `PURCHASE_CATEGORY_ID` varchar(36)  ,
  `PARENT_ID` varchar(36)  ,
  `CATEGORY_CODE` varchar(100)  ,
  `CATEGORY_NAME` varchar(100)  ,
  `CATEGORY_NOTE` varchar(100)  ,
  `CATEGORY_VERID` bigint(22)  
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `org_employee`
-- ----------------------------
DROP TABLE IF EXISTS `org_employee`;
CREATE TABLE `org_employee` (
  `EMP_ID` varchar(36) not null,
  `EMP_NAME` varchar(50) not null,
  `EMP_MOBILE` varchar(20)  ,
  `EMP_EMAIL` varchar(20)  ,
  `EMP_MSN` varchar(20)  ,
  `EMP_QQ` varchar(20)  ,
  `EMP_TEL_OFFICE` varchar(20)  ,
  `EMP_TEL_HOME` varchar(20)  ,
  `EMP_SORT` decimal(8,2)  ,
  `EMP_CREATED_DATE` datetime  ,
  `USR_ID` varchar(36)  ,
  `EMP_COMPANY_ID` varchar(36)  ,
  `EMP_DEPARTMENT_ID` varchar(36)  ,
  `CREATE_TIME` datetime  ,
  `EMP_POST_ID` varchar(36)  ,
  PRIMARY KEY (`EMP_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `org_role`
-- ----------------------------
DROP TABLE IF EXISTS `org_role`;
CREATE TABLE `org_role` (
  `RULE_ID` varchar(72) not null,
  `ORG_RULE` varchar(100)  ,
  `ORG_SOURCE` varchar(2)  ,
  `ORG_TARGET` varchar(2)  ,
  `STR_ID` varchar(72) not null,
  `STR_NAME` varchar(40)  ,
  `ORG_ID` varchar(72)  ,
  `CREATE_TIME` datetime  ,
  `ROL_ID` varchar(72)  ,
  PRIMARY KEY (`STR_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 修改点

create table JBPM4_DEPLOYMENT (
    DBID_ bigint not null,
    NAME_ longtext,
    TIMESTAMP_ bigint,
    STATE_ varchar(255),
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_DEPLOYPROP (
    DBID_ bigint not null,
    DEPLOYMENT_ bigint,
    OBJNAME_ varchar(255),
    KEY_ varchar(255),
    STRINGVAL_ varchar(255),
    LONGVAL_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_EXECUTION (
    DBID_ bigint not null,
    CLASS_ varchar(255) not null,
    DBVERSION_ integer not null,
    ACTIVITYNAME_ varchar(255),
    PROCDEFID_ varchar(255),
    HASVARS_ bit,
    NAME_ varchar(255),
    KEY_ varchar(255),
    ID_ varchar(255) unique,
    STATE_ varchar(255),
    SUSPHISTSTATE_ varchar(255),
    PRIORITY_ integer,
    HISACTINST_ bigint,
    PARENT_ bigint,
    INSTANCE_ bigint,
    SUPEREXEC_ bigint,
    SUBPROCINST_ bigint,
    PARENT_IDX_ integer,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_HIST_ACTINST (
    DBID_ bigint not null,
    CLASS_ varchar(255) not null,
    DBVERSION_ integer not null,
    HPROCI_ bigint,
    TYPE_ varchar(255),
    EXECUTION_ varchar(255),
    ACTIVITY_NAME_ varchar(255),
    START_ datetime,
    END_ datetime,
    DURATION_ bigint,
    TRANSITION_ varchar(255),
    NEXTIDX_ integer,
    HTASK_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_HIST_DETAIL (
    DBID_ bigint not null,
    CLASS_ varchar(255) not null,
    DBVERSION_ integer not null,
    USERID_ varchar(255),
    TIME_ datetime,
    HPROCI_ bigint,
    HPROCIIDX_ integer,
    HACTI_ bigint,
    HACTIIDX_ integer,
    HTASK_ bigint,
    HTASKIDX_ integer,
    HVAR_ bigint,
    HVARIDX_ integer,
    MESSAGE_ longtext,
    OLD_STR_ varchar(255),
    NEW_STR_ varchar(255),
    OLD_INT_ integer,
    NEW_INT_ integer,
    OLD_TIME_ datetime,
    NEW_TIME_ datetime,
    PARENT_ bigint,
    PARENT_IDX_ integer,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_HIST_PROCINST (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    ID_ varchar(255),
    PROCDEFID_ varchar(255),
    KEY_ varchar(255),
    START_ datetime,
    END_ datetime,
    DURATION_ bigint,
    STATE_ varchar(255),
    ENDACTIVITY_ varchar(255),
    NEXTIDX_ integer,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_HIST_TASK (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    EXECUTION_ varchar(255),
    OUTCOME_ varchar(255),
    ASSIGNEE_ varchar(255),
    PRIORITY_ integer,
    STATE_ varchar(255),
    CREATE_ datetime,
    END_ datetime,
    DURATION_ bigint,
    NEXTIDX_ integer,
    SUPERTASK_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_HIST_VAR (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    PROCINSTID_ varchar(255),
    EXECUTIONID_ varchar(255),
    VARNAME_ varchar(255),
    VALUE_ varchar(255),
    HPROCI_ bigint,
    HTASK_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_ID_GROUP (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    ID_ varchar(255),
    NAME_ varchar(255),
    TYPE_ varchar(255),
    PARENT_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_ID_MEMBERSHIP (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    USER_ bigint,
    GROUP_ bigint,
    NAME_ varchar(255),
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_ID_USER (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    ID_ varchar(255),
    PASSWORD_ varchar(255),
    GIVENNAME_ varchar(255),
    FAMILYNAME_ varchar(255),
    BUSINESSEMAIL_ varchar(255),
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_JOB (
    DBID_ bigint not null,
    CLASS_ varchar(255) not null,
    DBVERSION_ integer not null,
    DUEDATE_ datetime,
    STATE_ varchar(255),
    ISEXCLUSIVE_ bit,
    LOCKOWNER_ varchar(255),
    LOCKEXPTIME_ datetime,
    EXCEPTION_ longtext,
    RETRIES_ integer,
    PROCESSINSTANCE_ bigint,
    EXECUTION_ bigint,
    CFG_ bigint,
    SIGNAL_ varchar(255),
    EVENT_ varchar(255),
    REPEAT_ varchar(255),
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_LOB (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    BLOB_VALUE_ longblob,
    DEPLOYMENT_ bigint,
    NAME_ longtext,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_PARTICIPATION (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    GROUPID_ varchar(255),
    USERID_ varchar(255),
    TYPE_ varchar(255),
    TASK_ bigint,
    SWIMLANE_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_PROPERTY (
    KEY_ varchar(255) not null,
    VERSION_ integer not null,
    VALUE_ varchar(255),
    primary key (KEY_)
) type=InnoDB;

create table JBPM4_SWIMLANE (
    DBID_ bigint not null,
    DBVERSION_ integer not null,
    NAME_ varchar(255),
    ASSIGNEE_ varchar(255),
    EXECUTION_ bigint,
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_TASK (
    DBID_ bigint not null,
    CLASS_ char(1) not null,
    DBVERSION_ integer not null,
    NAME_ varchar(255),
    DESCR_ longtext,
    STATE_ varchar(255),
    SUSPHISTSTATE_ varchar(255),
    ASSIGNEE_ varchar(255),
    FORM_ varchar(255),
    PRIORITY_ integer,
    CREATE_ datetime,
    DUEDATE_ datetime,
    PROGRESS_ integer,
    SIGNALLING_ bit,
    EXECUTION_ID_ varchar(255),
    ACTIVITY_NAME_ varchar(255),
    HASVARS_ bit,
    SUPERTASK_ bigint,
    EXECUTION_ bigint,
    PROCINST_ bigint,
    SWIMLANE_ bigint,
    TASKDEFNAME_ varchar(255),
    primary key (DBID_)
) type=InnoDB;

create table JBPM4_VARIABLE (
    DBID_ bigint not null,
    CLASS_ varchar(255) not null,
    DBVERSION_ integer not null,
    KEY_ varchar(255),
    CONVERTER_ varchar(255),
    HIST_ bit,
    EXECUTION_ bigint,
    TASK_ bigint,
    LOB_ bigint,
    DATE_VALUE_ datetime,
    DOUBLE_VALUE_ double precision,
    CLASSNAME_ varchar(255),
    LONG_VALUE_ bigint,
    STRING_VALUE_ varchar(255),
    TEXT_VALUE_ longtext,
    EXESYS_ bigint,
    primary key (DBID_)
) type=InnoDB;

create index IDX_DEPLPROP_DEPL on JBPM4_DEPLOYPROP (DEPLOYMENT_);

alter table JBPM4_DEPLOYPROP 
    add index FK_DEPLPROP_DEPL (DEPLOYMENT_), 
    add constraint FK_DEPLPROP_DEPL 
    foreign key (DEPLOYMENT_) 
    references JBPM4_DEPLOYMENT (DBID_);

create index IDX_EXEC_SUPEREXEC on JBPM4_EXECUTION (SUPEREXEC_);

create index IDX_EXEC_INSTANCE on JBPM4_EXECUTION (INSTANCE_);

create index IDX_EXEC_SUBPI on JBPM4_EXECUTION (SUBPROCINST_);

create index IDX_EXEC_PARENT on JBPM4_EXECUTION (PARENT_);

alter table JBPM4_EXECUTION 
    add index FK_EXEC_PARENT (PARENT_), 
    add constraint FK_EXEC_PARENT 
    foreign key (PARENT_) 
    references JBPM4_EXECUTION (DBID_);

alter table JBPM4_EXECUTION 
    add index FK_EXEC_SUBPI (SUBPROCINST_), 
    add constraint FK_EXEC_SUBPI 
    foreign key (SUBPROCINST_) 
    references JBPM4_EXECUTION (DBID_);

alter table JBPM4_EXECUTION 
    add index FK_EXEC_INSTANCE (INSTANCE_), 
    add constraint FK_EXEC_INSTANCE 
    foreign key (INSTANCE_) 
    references JBPM4_EXECUTION (DBID_);

alter table JBPM4_EXECUTION 
    add index FK_EXEC_SUPEREXEC (SUPEREXEC_), 
    add constraint FK_EXEC_SUPEREXEC 
    foreign key (SUPEREXEC_) 
    references JBPM4_EXECUTION (DBID_);

create index IDX_HACTI_HPROCI on JBPM4_HIST_ACTINST (HPROCI_);

create index IDX_HTI_HTASK on JBPM4_HIST_ACTINST (HTASK_);

alter table JBPM4_HIST_ACTINST 
    add index FK_HACTI_HPROCI (HPROCI_), 
    add constraint FK_HACTI_HPROCI 
    foreign key (HPROCI_) 
    references JBPM4_HIST_PROCINST (DBID_);

alter table JBPM4_HIST_ACTINST 
    add index FK_HTI_HTASK (HTASK_), 
    add constraint FK_HTI_HTASK 
    foreign key (HTASK_) 
    references JBPM4_HIST_TASK (DBID_);

create index IDX_HDET_HACTI on JBPM4_HIST_DETAIL (HACTI_);

create index IDX_HDET_HPROCI on JBPM4_HIST_DETAIL (HPROCI_);

create index IDX_HDET_HVAR on JBPM4_HIST_DETAIL (HVAR_);

create index IDX_HDET_HTASK on JBPM4_HIST_DETAIL (HTASK_);

alter table JBPM4_HIST_DETAIL 
    add index FK_HDETAIL_HPROCI (HPROCI_), 
    add constraint FK_HDETAIL_HPROCI 
    foreign key (HPROCI_) 
    references JBPM4_HIST_PROCINST (DBID_);

alter table JBPM4_HIST_DETAIL 
    add index FK_HDETAIL_HACTI (HACTI_), 
    add constraint FK_HDETAIL_HACTI 
    foreign key (HACTI_) 
    references JBPM4_HIST_ACTINST (DBID_);

alter table JBPM4_HIST_DETAIL 
    add index FK_HDETAIL_HTASK (HTASK_), 
    add constraint FK_HDETAIL_HTASK 
    foreign key (HTASK_) 
    references JBPM4_HIST_TASK (DBID_);

alter table JBPM4_HIST_DETAIL 
    add index FK_HDETAIL_HVAR (HVAR_), 
    add constraint FK_HDETAIL_HVAR 
    foreign key (HVAR_) 
    references JBPM4_HIST_VAR (DBID_);

create index IDX_HSUPERT_SUB on JBPM4_HIST_TASK (SUPERTASK_);

alter table JBPM4_HIST_TASK 
    add index FK_HSUPERT_SUB (SUPERTASK_), 
    add constraint FK_HSUPERT_SUB 
    foreign key (SUPERTASK_) 
    references JBPM4_HIST_TASK (DBID_);

create index IDX_HVAR_HPROCI on JBPM4_HIST_VAR (HPROCI_);

create index IDX_HVAR_HTASK on JBPM4_HIST_VAR (HTASK_);

alter table JBPM4_HIST_VAR 
    add index FK_HVAR_HPROCI (HPROCI_), 
    add constraint FK_HVAR_HPROCI 
    foreign key (HPROCI_) 
    references JBPM4_HIST_PROCINST (DBID_);

alter table JBPM4_HIST_VAR 
    add index FK_HVAR_HTASK (HTASK_), 
    add constraint FK_HVAR_HTASK 
    foreign key (HTASK_) 
    references JBPM4_HIST_TASK (DBID_);

create index IDX_GROUP_PARENT on JBPM4_ID_GROUP (PARENT_);

alter table JBPM4_ID_GROUP 
    add index FK_GROUP_PARENT (PARENT_), 
    add constraint FK_GROUP_PARENT 
    foreign key (PARENT_) 
    references JBPM4_ID_GROUP (DBID_);

create index IDX_MEM_USER on JBPM4_ID_MEMBERSHIP (USER_);

create index IDX_MEM_GROUP on JBPM4_ID_MEMBERSHIP (GROUP_);

alter table JBPM4_ID_MEMBERSHIP 
    add index FK_MEM_GROUP (GROUP_), 
    add constraint FK_MEM_GROUP 
    foreign key (GROUP_) 
    references JBPM4_ID_GROUP (DBID_);

alter table JBPM4_ID_MEMBERSHIP 
    add index FK_MEM_USER (USER_), 
    add constraint FK_MEM_USER 
    foreign key (USER_) 
    references JBPM4_ID_USER (DBID_);

create index IDX_JOBRETRIES on JBPM4_JOB (RETRIES_);

create index IDX_JOB_CFG on JBPM4_JOB (CFG_);

create index IDX_JOB_PRINST on JBPM4_JOB (PROCESSINSTANCE_);

create index IDX_JOB_EXE on JBPM4_JOB (EXECUTION_);

create index IDX_JOBLOCKEXP on JBPM4_JOB (LOCKEXPTIME_);

create index IDX_JOBDUEDATE on JBPM4_JOB (DUEDATE_);

alter table JBPM4_JOB 
    add index FK_JOB_CFG (CFG_), 
    add constraint FK_JOB_CFG 
    foreign key (CFG_) 
    references JBPM4_LOB (DBID_);

create index IDX_LOB_DEPLOYMENT on JBPM4_LOB (DEPLOYMENT_);

alter table JBPM4_LOB 
    add index FK_LOB_DEPLOYMENT (DEPLOYMENT_), 
    add constraint FK_LOB_DEPLOYMENT 
    foreign key (DEPLOYMENT_) 
    references JBPM4_DEPLOYMENT (DBID_);

create index IDX_PART_TASK on JBPM4_PARTICIPATION (TASK_);

alter table JBPM4_PARTICIPATION 
    add index FK_PART_SWIMLANE (SWIMLANE_), 
    add constraint FK_PART_SWIMLANE 
    foreign key (SWIMLANE_) 
    references JBPM4_SWIMLANE (DBID_);

alter table JBPM4_PARTICIPATION 
    add index FK_PART_TASK (TASK_), 
    add constraint FK_PART_TASK 
    foreign key (TASK_) 
    references JBPM4_TASK (DBID_);

create index IDX_SWIMLANE_EXEC on JBPM4_SWIMLANE (EXECUTION_);

alter table JBPM4_SWIMLANE 
    add index FK_SWIMLANE_EXEC (EXECUTION_), 
    add constraint FK_SWIMLANE_EXEC 
    foreign key (EXECUTION_) 
    references JBPM4_EXECUTION (DBID_);

create index IDX_TASK_SUPERTASK on JBPM4_TASK (SUPERTASK_);

alter table JBPM4_TASK 
    add index FK_TASK_SWIML (SWIMLANE_), 
    add constraint FK_TASK_SWIML 
    foreign key (SWIMLANE_) 
    references JBPM4_SWIMLANE (DBID_);

alter table JBPM4_TASK 
    add index FK_TASK_SUPERTASK (SUPERTASK_), 
    add constraint FK_TASK_SUPERTASK 
    foreign key (SUPERTASK_) 
    references JBPM4_TASK (DBID_);

create index IDX_VAR_EXESYS on JBPM4_VARIABLE (EXESYS_);

create index IDX_VAR_TASK on JBPM4_VARIABLE (TASK_);

create index IDX_VAR_EXECUTION on JBPM4_VARIABLE (EXECUTION_);

create index IDX_VAR_LOB on JBPM4_VARIABLE (LOB_);

alter table JBPM4_VARIABLE 
    add index FK_VAR_LOB (LOB_), 
    add constraint FK_VAR_LOB 
    foreign key (LOB_) 
    references JBPM4_LOB (DBID_);

alter table JBPM4_VARIABLE 
    add index FK_VAR_EXECUTION (EXECUTION_), 
    add constraint FK_VAR_EXECUTION 
    foreign key (EXECUTION_) 
    references JBPM4_EXECUTION (DBID_);

alter table JBPM4_VARIABLE 
    add index FK_VAR_EXESYS (EXESYS_), 
    add constraint FK_VAR_EXESYS 
    foreign key (EXESYS_) 
    references JBPM4_EXECUTION (DBID_);

alter table JBPM4_VARIABLE 
    add index FK_VAR_TASK (TASK_), 
    add constraint FK_VAR_TASK 
    foreign key (TASK_) 
    references JBPM4_TASK (DBID_);

