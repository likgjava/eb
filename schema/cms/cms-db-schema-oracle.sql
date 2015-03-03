prompt PL/SQL Developer import file
prompt Created on 2010年7月19日 by guoyongrong
set feedback off
set define off
prompt Dropping CMS_CHANNEL...
drop table CMS_CHANNEL cascade constraints;
prompt Dropping CMS_CHANNEL_DATA...
drop table CMS_CHANNEL_DATA cascade constraints;
prompt Dropping CMS_CHANNEL_MODEL...
drop table CMS_CHANNEL_MODEL cascade constraints;
prompt Dropping CMS_CHANNEL_MODEL_ITEM...
drop table CMS_CHANNEL_MODEL_ITEM cascade constraints;
prompt Dropping CMS_NEWS...
drop table CMS_NEWS cascade constraints;
prompt Dropping CMS_TEMPLATE_FILE...
drop table CMS_TEMPLATE_FILE cascade constraints;
prompt Dropping CMS_VOTE_ITEM...
drop table CMS_VOTE_ITEM cascade constraints;
prompt Dropping CMS_VOTE_TOPIC...
drop table CMS_VOTE_TOPIC cascade constraints;
prompt Creating CMS_CHANNEL...
create table CMS_CHANNEL
(
  CHANNEL_ID        NVARCHAR2(50) not null,
  PARENT_CHANNEL_ID NVARCHAR2(50),
  CHANNEL_CODE      NVARCHAR2(50),
  CHANNEL_NAME      NVARCHAR2(50),
  CHANNEL_MEMO      NVARCHAR2(50),
  SORT_NO           NUMBER(38) default 10,
  CREATE_TIME       DATE,
  CREATE_USER_ID    NVARCHAR2(36),
  MODIFY_TIME       DATE,
  MODIFY_USER_ID    NVARCHAR2(36),
  TOTAL_COUNT       NUMBER(38) default 0,
  VISTI_COUNT       NUMBER(38) default 0,
  CHANNEL_TEMPLATE  NVARCHAR2(100),
  CONTENT_TEMPLATE  NVARCHAR2(100),
  CHANNEL_MODEL_ID  NVARCHAR2(50),
  FILE_PAGE         CHAR(1) default 0,
  URL               NVARCHAR2(100),
  OUT_URL           NVARCHAR2(100),
  PAGE_SIZE         NUMBER(38) default 20,
  SORT_TYPE         NVARCHAR2(10) default 1,
  IS_LEAF           CHAR(1) default 0,
  DISPLAY           CHAR(1) default 1,
  CHECK_COUNT       NUMBER(3) default 0
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
  -- Add/modify columns 
alter table CMS_CHANNEL add ISALONE CHAR(1) default 0;
alter table CMS_CHANNEL add ISQUERY CHAR(1) default 1;
alter table CMS_CHANNEL add SPECIFIED_PATH NVARCHAR2(100);
-- Add comments to the columns 
comment on column CMS_CHANNEL.ISALONE
  is '是否独立栏目';
comment on column CMS_CHANNEL.ISQUERY
  is '是否查询数据';
comment on column CMS_CHANNEL.SPECIFIED_PATH
  is '栏目指定的生成路径';

alter table CMS_CHANNEL
  add constraint PK_CMS_CHANNEL primary key (CHANNEL_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_CHANNEL_DATA...
create table CMS_CHANNEL_DATA
(
  CHANNEL_DATA_ID  NVARCHAR2(50) not null,
  SORT_NO          NUMBER(38),
  DISPLAY          CHAR(1),
  CHANNEL_ID       NVARCHAR2(50),
  DATA_TYPE        NVARCHAR2(50),
  DEFAULT_VALUE    NVARCHAR2(50),
  CHANNEL_MODEL_ID NVARCHAR2(50),
  NUM              NUMBER(38),
  NEED_LOAD        CHAR(1),
  DATA_KEY         NVARCHAR2(50),
  DATA_VAL         NVARCHAR2(50),
  TMP_TYPE         NVARCHAR2(50),
  SORT_TYPE        NVARCHAR2(10) default 1,
  NAME             NVARCHAR2(50),
  FTL_TEMPLATE     NVARCHAR2(100)
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CMS_CHANNEL_DATA
  add constraint PK_CHANNEL_DATA_ID primary key (CHANNEL_DATA_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_CHANNEL_MODEL...
create table CMS_CHANNEL_MODEL
(
  CHANNEL_MODEL_ID   NVARCHAR2(50) not null,
  NAME               NVARCHAR2(50),
  SHORT_NAME         NVARCHAR2(50),
  CHANNEL_TPL_PREFIX NVARCHAR2(50),
  CONTENT_TPL_PREFIX NVARCHAR2(50),
  SORT_NO            NUMBER(38),
  DISPLAY            CHAR(1),
  CREATE_USER        NVARCHAR2(50),
  MODIFY_USER        NVARCHAR2(50),
  CREATE_TIME        DATE,
  MODIFY_TIME        DATE
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CMS_CHANNEL_MODEL
  add constraint PK_CMS_CHANNEL_MODEL_ID primary key (CHANNEL_MODEL_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_CHANNEL_MODEL_ITEM...
create table CMS_CHANNEL_MODEL_ITEM
(
  CHANNEL_MODEL_ITEM_ID NVARCHAR2(50) not null,
  NAME                  NVARCHAR2(50),
  SORT_NO               NUMBER(38),
  DISPLAY               CHAR(1),
  LABEL                 NVARCHAR2(50),
  REQUIRED              CHAR(1),
  DEFAULT_VALUE         NVARCHAR2(500),
  CHANNEL_MODEL_ID      NVARCHAR2(50),
  HELP                  NVARCHAR2(50),
  FORM_TYPE             NVARCHAR2(50),
  TEXT_LENGTH           NUMBER(3) default 30,
  TEXTAREA_ROWS         NUMBER(3) default 50,
  TEXTAREA_COLS         NUMBER(3) default 60,
  KEY_VAL               NVARCHAR2(500),
  DATA_TYPE             NVARCHAR2(50),
  FULL_LINE             CHAR(1) default 1,
  CREATE_USER           NVARCHAR2(50),
  MODIFY_USER           NVARCHAR2(50),
  CREATE_TIME           DATE,
  MODIFY_TIME           DATE
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CMS_CHANNEL_MODEL_ITEM
  add constraint PK_CHANNEL_MODEL_ITEM_ID primary key (CHANNEL_MODEL_ITEM_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_NEWS...
create table CMS_NEWS
(
  CMS_NEWS_ID      NVARCHAR2(50) not null,
  TITLE            NVARCHAR2(200) not null,
  CONTENT          NVARCHAR2(200),
  NEWS_TYPE        NVARCHAR2(36),
  NEWS_IMAGE       NVARCHAR2(50),
  NEWS_ATTACHMENT  NVARCHAR2(50),
  CREATE_USER      NVARCHAR2(50),
  MODIFY_USER      NVARCHAR2(50),
  CREATE_TIME      DATE,
  MODIFY_TIME      DATE,
  NEWS_TYPE_CN     NVARCHAR2(50),
  DESCRIPTION      NVARCHAR2(500),
  ORIGIN           NVARCHAR2(50),
  AUTHOR           NVARCHAR2(50),
  FILE_ATTACHMENT  NVARCHAR2(50),
  CHANNEL_ID       NVARCHAR2(50),
  CHANNEL_TEMPLATE NVARCHAR2(100),
  CONTENT_TEMPLATE NVARCHAR2(100),
  URL              NVARCHAR2(100),
  OUT_URL          NVARCHAR2(100),
  VISTI_COUNT      NUMBER(38) default 0,
  IMG_URL          NVARCHAR2(100),
  FILE_URL         NVARCHAR2(100),
  FILE1_URL        NVARCHAR2(100),
  FILE2_URL        NVARCHAR2(100),
  CHECK_STATUS     NVARCHAR2(10) default 00
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table CMS_NEWS
  add constraint PK_CMS_NEWS_ID primary key (CMS_NEWS_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_TEMPLATE_FILE...
create table CMS_TEMPLATE_FILE
(
  TEMP_FILE_ID        NVARCHAR2(50) not null,
  PARENT_TEMP_FILE_ID NVARCHAR2(50),
  TEMP_FILE_NAME      NVARCHAR2(50),
  TEMP_FILE_PATH      NVARCHAR2(500),
  MODIFY_TIME         DATE,
  IS_LEAF             CHAR(1) default 0,
  FILE_SIZE           NUMBER(38),
  TEMPFILETYPE        NVARCHAR2(50),
  SORT_NO             NUMBER(38),
  FILE_ICO            NVARCHAR2(100)
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
alter table CMS_TEMPLATE_FILE
  add constraint PK_TEMP_FILE_ID primary key (TEMP_FILE_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_VOTE_ITEM...
create table CMS_VOTE_ITEM
(
  VOTE_ITEM_ID  NVARCHAR2(50) not null,
  TITLE         NVARCHAR2(50),
  VOTE_COUNT    NUMBER(10) default 0,
  VOTE_TOPIC_ID NVARCHAR2(50)
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column CMS_VOTE_ITEM.TITLE
  is '投票项目名称';
comment on column CMS_VOTE_ITEM.VOTE_COUNT
  is '投票数';
comment on column CMS_VOTE_ITEM.VOTE_TOPIC_ID
  is '所属投票主题';
alter table CMS_VOTE_ITEM
  add constraint PK_VOTE_ITEM_ID primary key (VOTE_ITEM_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CMS_VOTE_TOPIC...
create table CMS_VOTE_TOPIC
(
  VOTE_TOPIC_ID NVARCHAR2(50) not null,
  TITLE         NVARCHAR2(50),
  TOTAL_COUNT   NUMBER(10),
  CREATE_USER   NVARCHAR2(50),
  MODIFY_USER   NVARCHAR2(50),
  CREATE_TIME   DATE,
  MODIFY_TIME   DATE,
  MULT_SELECT   NVARCHAR2(50) default 0,
  START_TIME    DATE,
  END_TIME      DATE,
  CLOSE         CHAR(1) default 0
)
tablespace GPCSOFT
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column CMS_VOTE_TOPIC.TITLE
  is '标题';
comment on column CMS_VOTE_TOPIC.TOTAL_COUNT
  is '投票总数';
comment on column CMS_VOTE_TOPIC.CREATE_USER
  is '创建人';
comment on column CMS_VOTE_TOPIC.MODIFY_USER
  is '修改人';
comment on column CMS_VOTE_TOPIC.CREATE_TIME
  is '创建时间';
comment on column CMS_VOTE_TOPIC.MODIFY_TIME
  is '修改时间';
comment on column CMS_VOTE_TOPIC.MULT_SELECT
  is '多选';
comment on column CMS_VOTE_TOPIC.START_TIME
  is '投票开始时间';
comment on column CMS_VOTE_TOPIC.END_TIME
  is '投票结束时间';
comment on column CMS_VOTE_TOPIC.CLOSE
  is '关闭投票';
alter table CMS_VOTE_TOPIC
  add constraint PK_VOTE_TOPIC_ID primary key (VOTE_TOPIC_ID)
  using index 
  tablespace GPCSOFT
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for CMS_CHANNEL...
alter table CMS_CHANNEL disable all triggers;
prompt Disabling triggers for CMS_CHANNEL_DATA...
alter table CMS_CHANNEL_DATA disable all triggers;
prompt Disabling triggers for CMS_CHANNEL_MODEL...
alter table CMS_CHANNEL_MODEL disable all triggers;
prompt Disabling triggers for CMS_CHANNEL_MODEL_ITEM...
alter table CMS_CHANNEL_MODEL_ITEM disable all triggers;
prompt Disabling triggers for CMS_NEWS...
alter table CMS_NEWS disable all triggers;
prompt Disabling triggers for CMS_TEMPLATE_FILE...
alter table CMS_TEMPLATE_FILE disable all triggers;
prompt Disabling triggers for CMS_VOTE_ITEM...
alter table CMS_VOTE_ITEM disable all triggers;
prompt Disabling triggers for CMS_VOTE_TOPIC...
alter table CMS_VOTE_TOPIC disable all triggers;
prompt Loading CMS_CHANNEL...
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a556d10129a58ac06d0845', 'ff8080812954cf9901295502e4350041', 'agent', '代理机构', 'fd', 100, to_date('06-07-2010 10:17:00', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/agent/agent.html', null, 20, '2', '1', '0', 1);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299da411e80848', 'ff8080812954cf9901295502691a003e', 'zhaobgg', '招标公告', 'fd', 10, to_date('04-07-2010 21:27:42', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/zhaobgg/zhaobgg.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299dae37130851', 'ff8080812954cf9901295502691a003e', 'cjgg', '成交公告', 'fd', 10, to_date('04-07-2010 21:38:47', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/cjgg/cjgg.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299dc52501299dc8090f0008', 'ff808081299dc52501299dc7781a0004', 'question', '问题解答', 'fd', 12, to_date('04-07-2010 22:06:59', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/question/question.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299de09b01299de46735000a', 'ff8080812954cf9901295502e4350041', 'ztts', '质疑投诉', 'fd', 11, to_date('04-07-2010 22:37:58', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/ztts/ztts.html', null, 20, '2', '1', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299de09b01299de60c690016', 'ff8080812954cf9901295502e4350041', 'onLine', '在线反馈', 'fd', 100, to_date('04-07-2010 22:39:46', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/onLine/onLine.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a14e050129a1871ed1044c', 'ff8080812954cf9901295502e4350041', 'notice', '重要通知', 'fd', 100, to_date('05-07-2010 15:34:33', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/notice/notice.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a14e050129a1b4ad4b0473', 'ff808081299dc52501299dc7781a0004', 'ywlc', '业务流程', 'fd', 10, to_date('05-07-2010 16:24:19', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/ywlc/ywlc.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a14e050129a1bc365f0482', 'ff808081299de09b01299de35a970006', 'lltt', '理论探讨', 'fd', 10, to_date('05-07-2010 16:32:33', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/lltt/lltt.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a14e050129a1bcb2260486', 'ff808081299de09b01299de35a970006', 'sjts', '实践探索', 'fd', 10, to_date('05-07-2010 16:33:05', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/sjts/sjts.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a14e050129a1bddefe048a', 'ff808081299de09b01299de35a970006', 'talk', '领导讲话', 'fd', 10, to_date('05-07-2010 16:34:22', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/talk/talk.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a14e050129a1be7fc2048e', 'ff808081299de09b01299de35a970006', 'case', '案例分析', 'fd', 10, to_date('05-07-2010 16:35:03', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/case/case.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a556d10129a5893623083a', 'ff8080812954cf9901295502e4350041', 'supplier', '最新供应商', 'fd', 100, to_date('06-07-2010 10:15:19', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/supplier/supplier.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a556d10129a58aad040841', 'ff8080812954cf9901295502e4350041', 'study', '学习园地', 'fd', 9, to_date('06-07-2010 10:16:55', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/study/study.html', null, 20, '2', '1', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d429a556d10129a58f5d650852', 'ff8080812954cf9901295502e4350041', 'bgt', '曝光台', 'fd', 100, to_date('06-07-2010 10:22:03', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/bgt/bgt.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff8080812954cf9901295502691a003e', 'ff8080812954cf9901295502e4350041', 'cggg', '采购公告', 'fd', 4, to_date('05-07-2010', 'dd-mm-yyyy'), '8a808083204329sr01204330167b0005', null, null, 1, 1, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/cggg/cggg.html', null, 1, '2', '0', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff8080812954cf9901295502e4350041', null, 'index', '首页', 'fd', 1, to_date('05-07-2010', 'dd-mm-yyyy'), '8a808083204329sr01204330167b0005', null, null, null, null, 'index.ftl', 'content.ftl', 'ff8080812954cf9901295519c2a40047', '0', '/cms/view/srplatform/portal/index.jsp', null, 20, '2', '0', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('402883d42981b6ab012981de69b40035', 'ff8080812954cf9901295502e4350041', 'resource', '资源中心', 'fd', 5, to_date('29-06-2010 12:02:03', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 1, 1, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/resource/resource.html', null, 20, '1', '1', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299d9c32d50836', 'ff8080812954cf9901295502e4350041', 'jgxx', '机构信息', 'fd', 2, to_date('04-07-2010 21:19:06', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/jgxx/jgxx.html', null, 20, '1', '1', '1', 1);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299d9f11f0083d', 'ff8080812954cf9901295502e4350041', 'work', '工作动态', 'fd', 3, to_date('04-07-2010 21:22:14', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/work/work.html', null, 20, '1', '1', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299da59a5d084b', 'ff8080812954cf9901295502691a003e', 'zhongbgg', '中标公告', 'fd', 10, to_date('04-07-2010 21:29:22', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/zhongbgg/zhongbgg.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299daebf9c0854', 'ff8080812954cf9901295502691a003e', 'gzgg', '更正公告', 'fd', 10, to_date('04-07-2010 21:39:22', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/gzgg/gzgg.html', null, 20, '2', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299dafdd510857', 'ff8080812954cf9901295502e4350041', 'policy', '政策法规', 'fd', 6, to_date('04-07-2010 21:40:35', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, null, null, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/policy/policy.html', null, 20, '2', '0', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299db0c3e8085a', 'ff808081299d88a301299dafdd510857', 'gj_policy', '国家法规', 'fd', 10, to_date('04-07-2010 21:41:34', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/gj_policy/gj_policy.html', null, 20, '1', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299d88a301299db119f7085d', 'ff808081299d88a301299dafdd510857', 'df_policy', '地方法规', 'fd', 11, to_date('04-07-2010 21:41:56', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/df_policy/df_policy.html', null, 20, '1', '1', '0', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299dc52501299dc7781a0004', 'ff8080812954cf9901295502e4350041', 'bszn', '办事指南', 'fd', 8, to_date('04-07-2010 22:06:22', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, null, 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/bszn/bszn.html', null, 20, '2', '0', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299de09b01299de35a970006', 'ff8080812954cf9901295502e4350041', 'llsj', '理论实践', 'fd', 7, to_date('04-07-2010 22:36:49', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/llsj/llsj.html', null, 20, '2', '0', '1', 0);
insert into CMS_CHANNEL (CHANNEL_ID, PARENT_CHANNEL_ID, CHANNEL_CODE, CHANNEL_NAME, CHANNEL_MEMO, SORT_NO, CREATE_TIME, CREATE_USER_ID, MODIFY_TIME, MODIFY_USER_ID, TOTAL_COUNT, VISTI_COUNT, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, CHANNEL_MODEL_ID, FILE_PAGE, URL, OUT_URL, PAGE_SIZE, SORT_TYPE, IS_LEAF, DISPLAY, CHECK_COUNT)
values ('ff808081299de09b01299de590050012', 'ff8080812954cf9901295502e4350041', 'down', '下载中心', 'fd', 10, to_date('04-07-2010 22:39:14', 'dd-mm-yyyy hh24:mi:ss'), '8a808083204329sr01204330167b0005', null, null, 0, 0, 'channel.ftl', 'content.ftl', 'ff808081299d88a301299d8ca2170826', '1', '/cms/view/staticPags/down/down.html', null, 20, '2', '1', '1', 0);
commit;
prompt 28 records loaded
prompt Loading CMS_CHANNEL_DATA...
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6aac69e002f', 6, '0', 'ff808081299d88a301299dae37130851', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6aac69e0030', 6, '0', 'ff808081299d88a301299dae37130851', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ac49a40042', 14, '0', 'ff808081299d88a301299db0c3e8085a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ac49a40043', 13, '0', 'ff808081299d88a301299db0c3e8085a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ac913c0047', 16, '0', 'ff808081299d88a301299db119f7085d', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ac913c0048', 15, '0', 'ff808081299d88a301299db119f7085d', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6acd25c004c', 18, '0', 'ff808081299de09b01299de35a970006', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6acd25c004d', 17, '0', 'ff808081299de09b01299de35a970006', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b027920083', 84, '0', 'ff808081299de09b01299de60c690016', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b027920084', 24, '0', 'ff808081299de09b01299de60c690016', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b06d650088', 87, '0', '402883d429a556d10129a58f5d650852', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b06d650089', 27, '0', '402883d429a556d10129a58f5d650852', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b0ab87008d', 0, '0', '402883d429a556d10129a58ac06d0845', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b0ab87008e', 0, '0', '402883d429a556d10129a58ac06d0845', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b4f64800bc', 98, '0', '402883d429a14e050129a1b4ad4b0473', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b4f64800bd', 75, '0', '402883d429a14e050129a1b4ad4b0473', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b5356500c3', 100, '0', 'ff808081299dc52501299dc8090f0008', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6b5356500c4', 77, '0', 'ff808081299dc52501299dc8090f0008', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1653f100013', 0, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 5, '0', '招标公告', 'ff808081299d88a301299da411e80848', 'channel', '1', 'zhaob', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a16f6871001f', 1, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 5, '0', '成交公告', 'ff808081299d88a301299dae37130851', 'channel', '1', 'cjgg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a17d96820440', 2, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 5, '0', '中标公告', 'ff808081299d88a301299da59a5d084b', 'channel', '1', 'zbgg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a17d96820441', 3, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 5, '0', '更正公告', 'ff808081299d88a301299daebf9c0854', 'channel', '1', 'gzgg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a18dff02045c', 4, '0', 'ff8080812954cf9901295502e4350041', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a195c6f40461', 5, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '国家法规', 'ff808081299d88a301299db0c3e8085a', 'channel', '1', 'policy_1', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a195c6f40462', 6, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '地方法规', 'ff808081299d88a301299db119f7085d', 'channel', '1', 'policy_2', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1b5f8f50478', 7, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 5, '0', '问题解答', 'ff808081299dc52501299dc8090f0008', 'channel', '1', 'question', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1b5f8f50479', 8, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 5, '0', '业务流程', '402883d429a14e050129a1b4ad4b0473', 'channel', '1', 'ywlc', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1c3fa870493', 9, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '理论探讨', '402883d429a14e050129a1bc365f0482', 'channel', '1', 'lltt', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1c3ff890494', 10, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '实践探索', '402883d429a14e050129a1bcb2260486', 'channel', '1', 'sjts', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1c403de0495', 11, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '领导讲话', '402883d429a14e050129a1bddefe048a', 'channel', '1', 'talk', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a14e050129a1c4071a0496', 12, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '案例分析', '402883d429a14e050129a1be7fc2048e', 'channel', '1', 'case', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a556d10129a5914d2f0856', 13, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '曝光台', '402883d429a556d10129a58f5d650852', 'channel', '1', 'bgt', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a556d10129a5914d2f0857', 14, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '质疑投诉', 'ff808081299de09b01299de46735000a', 'channel', '1', 'zyts', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a556d10129a5914d2f0858', 15, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '工作动态', 'ff808081299d88a301299d9f11f0083d', 'channel', '1', 'work', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a556d10129a5914d2f0859', 16, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '最新供应商', '402883d429a556d10129a5893623083a', 'channel', '1', 'supplier', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a556d10129a5914d2f085a', 17, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '代理机构', '402883d429a556d10129a58ac06d0845', 'channel', '1', 'agent', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a556d10129a5914d2f085b', 18, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '学习园地', '402883d429a556d10129a58aad040841', 'channel', '1', 'study', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6a4d87d001a', 37, '0', 'ff808081299d88a301299da411e80848', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6a4d87d001b', 14, '0', 'ff808081299d88a301299da411e80848', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6a807110025', 3, '0', 'ff808081299d88a301299daebf9c0854', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6a807300026', 3, '0', 'ff808081299d88a301299daebf9c0854', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6a8729c002a', 5, '0', 'ff808081299d88a301299da59a5d084b', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6a8729c002b', 5, '0', 'ff808081299d88a301299da59a5d084b', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d4296472d9012964778d5c0002', 2, '0', 'ff8080812954cf9901295502691a003e', 'content', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '2', 'test5', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d4296472d9012964778d5c0003', 3, '0', 'ff8080812954cf9901295502691a003e', 'channel', null, null, 4, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '3', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('ff8080812964c4dc0129651c66980011', 19, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '重要通知', '402883d429a14e050129a1871ed1044c', 'channel', '1', 'notice', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d42981b6ab0129821935e30042', 9, '0', '402883d42981b6ab012981de69b40035', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6abf692003d', 12, '0', 'ff808081299d88a301299dafdd510857', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6abf692003e', 11, '0', 'ff808081299d88a301299dafdd510857', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ad13e90051', 20, '0', '402883d429a14e050129a1bc365f0482', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ad13f90052', 19, '0', '402883d429a14e050129a1bc365f0482', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ad50b40056', 22, '0', '402883d429a14e050129a1be7fc2048e', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ad50b40057', 21, '0', '402883d429a14e050129a1be7fc2048e', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6adc920005b', 24, '0', '402883d429a14e050129a1bddefe048a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6adc920005c', 23, '0', '402883d429a14e050129a1bddefe048a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ae09840060', 26, '0', '402883d429a14e050129a1bcb2260486', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ae09840061', 25, '0', '402883d429a14e050129a1bcb2260486', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ae4d440065', 28, '0', 'ff808081299dc52501299dc7781a0004', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ae4d440066', 27, '0', 'ff808081299dc52501299dc7781a0004', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ae8a1e006a', 73, '0', '402883d429a556d10129a58aad040841', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6ae8a1e006b', 13, '0', '402883d429a556d10129a58aad040841', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6aed725006f', 75, '0', 'ff808081299de09b01299de590050012', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6af40db0074', 77, '0', 'ff808081299de09b01299de46735000a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6af40db0075', 17, '0', 'ff808081299de09b01299de46735000a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'content', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6af7bf00079', 79, '0', '402883d429a556d10129a5893623083a', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429a689930129a6afc202007e', 81, '0', '402883d429a14e050129a1871ed1044c', 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'channel', '1', 'cggg', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429cf6eff0129d00ebce70433', 20, '0', 'ff8080812954cf9901295502e4350041', 'voteTopic', null, null, 10, '0', '投票主题二', '402883d429ceb6f30129cec98a790004', 'channel', '1', 'vote', null);
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429b5543b0129b5600f850054', 0, '0', null, 'channel', null, null, 10, '0', '采购公告', 'ff8080812954cf9901295502691a003e', 'template', '1', 'cggg', '/left.ftl');
insert into CMS_CHANNEL_DATA (CHANNEL_DATA_ID, SORT_NO, DISPLAY, CHANNEL_ID, DATA_TYPE, DEFAULT_VALUE, CHANNEL_MODEL_ID, NUM, NEED_LOAD, DATA_KEY, DATA_VAL, TMP_TYPE, SORT_TYPE, NAME, FTL_TEMPLATE)
values ('402883d429d9b6eb0129d9c15d740009', 22, '0', 'ff8080812954cf9901295502e4350041', 'content', null, null, 6, '0', '下载中心', 'ff808081299de09b01299de590050012', 'channel', '3', 'down', null);
commit;
prompt 69 records loaded
prompt Loading CMS_CHANNEL_MODEL...
insert into CMS_CHANNEL_MODEL (CHANNEL_MODEL_ID, NAME, SHORT_NAME, CHANNEL_TPL_PREFIX, CONTENT_TPL_PREFIX, SORT_NO, DISPLAY, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e43c801298e5da463000d', '采购公告', '采购公告', 'index', 'content', 3, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:16:28', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL (CHANNEL_MODEL_ID, NAME, SHORT_NAME, CHANNEL_TPL_PREFIX, CONTENT_TPL_PREFIX, SORT_NO, DISPLAY, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812954cf9901295519c2a40047', '首页模型', '首页', 'index/index', 'index/content', 1, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL (CHANNEL_MODEL_ID, NAME, SHORT_NAME, CHANNEL_TPL_PREFIX, CONTENT_TPL_PREFIX, SORT_NO, DISPLAY, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812964c4dc012965190145000d', '采购公告4', '采购公告4', 'channel/', 'channel/', 4, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL (CHANNEL_MODEL_ID, NAME, SHORT_NAME, CHANNEL_TPL_PREFIX, CONTENT_TPL_PREFIX, SORT_NO, DISPLAY, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca2170826', '通用模型', '通用模型', 'channel', 'content', 2, '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
commit;
prompt 4 records loaded
prompt Loading CMS_CHANNEL_MODEL_ITEM...
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca42c0005', 'title', 1, '1', '标题', '1', null, 'ff808081298e6ba201298e6ca3fd0003', 'title', '2', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b0006', 'author', 2, '1', '作者', '1', null, 'ff808081298e6ba201298e6ca3fd0003', '作者', '3', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b0007', 'description', 3, '1', '概要描述', '1', null, 'ff808081298e6ba201298e6ca3fd0003', '概要描述', '1', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b0008', 'origin', 4, '1', '来源', '1', null, 'ff808081298e6ba201298e6ca3fd0003', '来源', '1', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b0009', 'content', 5, '1', '内容', null, null, 'ff808081298e6ba201298e6ca3fd0003', '内容', '1', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b000a', 'newsImage', 6, '1', '图片', null, null, 'ff808081298e6ba201298e6ca3fd0003', '图片', '1', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b000b', 'fileAttachment', 7, '1', '单附件', null, null, 'ff808081298e6ba201298e6ca3fd0003', '单附件', '1', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081298e6ba201298e6ca43b000c', 'newsAttachment', 8, '1', '多附件', null, null, 'ff808081298e6ba201298e6ca3fd0003', '多附件', '1', 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('01-07-2010 22:32:51', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429af99df0129afbd36030012', 'imgUrl', 9, '1', '图片', '0', null, 'ff808081299d88a301299d8ca2170826', '图片', 'img', 30, 50, 60, null, 'String', '1', '8a808083204329sr01204330167b0005', null, to_date('08-07-2010 09:48:19', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429af99df0129b01ae23d0017', 'fileUrl', 10, '1', '附件', '0', null, 'ff808081299d88a301299d8ca2170826', '附件', 'file', 30, 50, 60, null, 'String', '1', '8a808083204329sr01204330167b0005', null, to_date('08-07-2010 11:30:38', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca2750828', 'title', 1, '1', '标题', '1', null, 'ff808081299d88a301299d8ca2170826', 'title', null, 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca2750829', 'author', 2, '1', '作者', '0', null, 'ff808081299d88a301299d8ca2170826', '作者', null, 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca275082a', 'description', 3, '1', '概要描述', '0', null, 'ff808081299d88a301299d8ca2170826', '概要描述', 'textarea', 30, 50, 60, null, 'String', '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca275082b', 'origin', 4, '1', '来源', '0', null, 'ff808081299d88a301299d8ca2170826', '采购方式', 'radio', 30, 50, 60, '测试发布,金采网,政采科技', 'String', '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca275082c', 'content', 5, '1', '内容', '0', null, 'ff808081299d88a301299d8ca2170826', '内容', null, 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca275082d', 'newsImage', 6, '0', '图片', '0', null, 'ff808081299d88a301299d8ca2170826', '图片', null, 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca275082e', 'fileAttachment', 7, '0', '单附件', '0', null, 'ff808081299d88a301299d8ca2170826', '单附件', null, 30, 50, 60, null, null, '1', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff808081299d88a301299d8ca275082f', 'newsAttachment', 8, '0', '多附件', '0', null, 'ff808081299d88a301299d8ca2170826', '多附件', null, 30, 50, 60, null, null, '0', '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:02:06', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955db83d60005', 'title', 1, '1', '标题', '1', null, 'ff8080812964c4dc012965190145000d', 'title', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955eb7fd1000a', 'content', 5, '1', '内容', null, null, 'ff8080812964c4dc012965190145000d', '内容', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955ec39ca000c', 'description', 3, '1', '概要描述', null, null, 'ff8080812964c4dc012965190145000d', '概要描述', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955edb2ce000e', 'origin', 4, '1', '来源', null, null, 'ff8080812964c4dc012965190145000d', '来源', 'checkbox', 30, 50, 60, '中国采购报,中国招投标网,政采科技', 'String', '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955edb2ce000f', 'author', 2, '1', '作者', null, null, 'ff8080812964c4dc012965190145000d', '作者', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955edb2ce0010', 'newsImage', 6, '1', '图片', null, null, 'ff8080812964c4dc012965190145000d', '图片', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955edb2ce0011', 'fileAttachment', 7, '1', '单附件', null, null, 'ff8080812964c4dc012965190145000d', '单附件', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('ff8080812955d867012955edb2ce0012', 'newsAttachment', 8, '1', '多附件', null, null, 'ff8080812964c4dc012965190145000d', '多附件', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a1000e', 'title', 1, '1', '标题', '1', null, 'ff8080812954cf9901295519c2a40047', 'title', null, 30, 50, 60, null, null, null, null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a1000f', 'author', 2, '1', '作者', '1', null, 'ff8080812954cf9901295519c2a40047', '作者', null, 30, 50, 60, null, null, null, null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a10010', 'description', 3, '1', '概要描述', '1', null, 'ff8080812954cf9901295519c2a40047', '概要描述', null, 30, 50, 60, null, null, null, null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a10011', 'origin', 4, '1', '来源', '1', null, 'ff8080812954cf9901295519c2a40047', '来源', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a10012', 'content', 5, '1', '内容', null, null, 'ff8080812954cf9901295519c2a40047', '内容', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a10013', 'newsImage', 6, null, '图片', null, null, 'ff8080812954cf9901295519c2a40047', '图片', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a10014', 'fileAttachment', 7, null, '单附件', null, null, 'ff8080812954cf9901295519c2a40047', '单附件', null, 30, 50, 60, null, null, '1', null, null, null, null);
insert into CMS_CHANNEL_MODEL_ITEM (CHANNEL_MODEL_ITEM_ID, NAME, SORT_NO, DISPLAY, LABEL, REQUIRED, DEFAULT_VALUE, CHANNEL_MODEL_ID, HELP, FORM_TYPE, TEXT_LENGTH, TEXTAREA_ROWS, TEXTAREA_COLS, KEY_VAL, DATA_TYPE, FULL_LINE, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME)
values ('402883d429677c84012967b0a9a10015', 'newsAttachment', 8, null, '多附件', null, null, 'ff8080812954cf9901295519c2a40047', '多附件', null, 30, 50, 60, null, null, '1', null, null, null, null);
commit;
prompt 34 records loaded
prompt Loading CMS_NEWS...
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429a556d10129a576a9aa0836', 'fsafsaf', '\2010-07\a0d23655_e074_47e5_8c9b_bd3b08d4c102.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('06-07-2010 09:55:04', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299de09b01299de60c690016', null, null, '/cms/view/staticPags/onLine/402883d429a556d10129a576a9aa0836.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429a6c7e40129a6c9e6b20005', '马濠公园亮化工程所需灯具和电缆项目变更公告', '\2010-07\540c315d_a596_4223_a360_76eccad75469.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('06-07-2010 16:05:36', 'dd-mm-yyyy hh24:mi:ss'), null, null, '马濠公园亮化工程所需灯具和电缆项目变更公告', '测试数据', '郭永荣', null, '402883d429a556d10129a58f5d650852', null, null, '/cms/view/staticPags/index/402883d429a6c7e40129a6c9e6b20005.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429a6c7e40129a6d07827000c', '法萨芬', '\2010-07\2b9b3501_f0be_4906_aff7_6b4e9493d8e3.txt', null, null, null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('06-07-2010 16:12:47', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-07-2010 16:53:41', 'dd-mm-yyyy hh24:mi:ss'), null, '法萨芬', null, '法萨芬', null, 'ff808081299d88a301299db0c3e8085a', null, null, '/cms/view/staticPags/gj_policy/402883d429a6c7e40129a6d07827000c.html', null, 0, null, null, null, null, '-1');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429ac463e0129ac4eb76c0006', '无可奈何花落去', '\2010-07\4491d2f2_6eb7_4d3f_822a_92302d40b8d0.txt', null, null, null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('07-07-2010 17:48:46', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-07-2010 18:04:36', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, 'ff808081299d88a301299d9c32d50836', null, null, '/cms/view/staticPags/jgxx/402883d429ac463e0129ac4eb76c0006.html', null, 0, null, null, null, null, '01');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d42990fa030129910ec37d0023', 'fdsfs', '\2010-06\0ffef3f3_7cb0_40dd_a91d_d773a30269df.txt', null, '402883d42990fa030129910dc297001e', null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('02-07-2010 10:49:10', 'dd-mm-yyyy hh24:mi:ss'), to_date('03-07-2010 02:37:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'fsf', '中国,美国,法国,日本', 'fsfdsa', '402883d42990fa030129910dde0e0021', 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/index/402883d42990fa030129910ec37d0023.html', null, 0, null, null, null, null, '-1');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299d88a301299d8b30090003', 'fafsaaaaaaa', '\2010-07\5567b18e_3170_4161_859e_77414a21d378.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 21:00:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, 'aaa', null, 'ff8080812954cf9901295502691a003e', null, null, '/cms/view/staticPags/index/ff808081299d88a301299d8b30090003.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299de09b01299df442c7001a', 'fsdf', '\2010-07\ef23cf91_fa4e_43c2_9f9c_a4a3bda925a1.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 22:55:17', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/ff808081299de09b01299df442c7001a.html', null, 0, null, null, null, null, '-1');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299de09b01299df6f043001e', 'ccc', '\2010-07\0cc7f386_722c_475c_83dc_41ad98b6ca40.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 22:58:13', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/ff808081299de09b01299df6f043001e.html', null, 0, null, null, null, null, '01');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299de09b01299df745490020', 'fsfsfsf', '\2010-07\39a5b042_aee3_40f8_8a59_bbcd5fd77001.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 22:58:34', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/ff808081299de09b01299df745490020.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299de09b01299df8cb4c0022', 'fsds', '\2010-07\b7260930_a880_4b31_afdc_24bb6ef0b380.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 23:00:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299d88a301299da59a5d084b', null, null, '/cms/view/staticPags/zhongbgg/ff808081299de09b01299df8cb4c0022.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429a14e050129a20462040d03', 'rewrw', '\2010-07\a25a7799_127d_4398_86fb_9df1e9f0ef6c.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('05-07-2010 17:51:23', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, '402883d429a14e050129a1871ed1044c', null, null, '/cms/view/staticPags/notice/402883d429a14e050129a20462040d03.html', null, 0, null, null, null, null, '11');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429a14e050129a20de7a30d0a', 'fsadf', '\2010-07\db077374_50f8_4c93_bd53_688b66daa9b6.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('05-07-2010 18:01:47', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, '402883d429a14e050129a1871ed1044c', null, null, '/cms/view/staticPags/notice/402883d429a14e050129a20de7a30d0a.html', null, 0, null, null, null, null, '11');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429a14e050129a20ffb030d0c', '宝山区财政局到我司交流全流程网上招标平台实践经验', '\2010-07\d309787a_a916_4b06_9d8e_fe2dae12ef7c.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('05-07-2010 18:04:03', 'dd-mm-yyyy hh24:mi:ss'), null, null, '近日，宝山区财政局副局长', null, null, null, '402883d429a14e050129a1871ed1044c', null, null, '/cms/view/staticPags/index/402883d429a14e050129a20ffb030d0c.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d4295a4e4201295a50c0450007', '测试发布', '\2010-06\efdsfb55_6eda_4e7c_baf8_7aa2820707e2.txt', null, '402883d4295a4e4201295a4fd3350001', null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('21-06-2010', 'dd-mm-yyyy'), to_date('04-07-2010 19:23:22', 'dd-mm-yyyy hh24:mi:ss'), null, '测试发布', '测试发布', '郭永荣', '402883d4295a4e4201295a4ffce50003', '402883d429a14e050129a1871ed1044c', null, null, '/cms/view/staticPags/index/402883d4295a4e4201295a50c0450007.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff80808129653b060129654c1e4a000d', '标题', '\2010-06ada17b55_6eda_4e7c_baf8_7aa2820707e2.txt', null, null, null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('23-06-2010', 'dd-mm-yyyy'), to_date('23-06-2010', 'dd-mm-yyyy'), null, '概要描述', '来源', '作者', null, 'ff8080812954cf9901295502691a003e', null, null, '/cms/view/staticPags/index/ff80808129653b060129654c1e4a000d.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff80808129657a4801296583b30d0007', '423424', '\2010-06\e1617b55_6eda_4e7c_baf8_7aa2820707e2.txt', null, null, null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('23-06-2010', 'dd-mm-yyyy'), to_date('07-07-2010 18:04:05', 'dd-mm-yyyy hh24:mi:ss'), null, '24243', null, '4234', null, 'ff8080812954cf9901295502691a003e', null, null, '/cms/view/staticPags/index/ff80808129657a4801296583b30d0007.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429645efe01296466db110015', 'fsadfa', '\2010-06\934982df_34dd_4898_b310_1b90db38e522.txt', null, '402883d429645efe01296466933b000f', null, '8a808083204329sr01204330167b0005', null, to_date('23-06-2010', 'dd-mm-yyyy'), null, null, 'fasf', 'af', 'fsaf', '402883d429645efe01296466c9eb0013', 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/402883d429645efe01296466db110015.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d4295a402501295a4589b90003', 'fdf', '\2010-06\e1617b55_6fsfafsad_4e7c_baf8_7aa2820707e2.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('21-06-2010', 'dd-mm-yyyy'), null, null, 'fdsfdsa', 'af', 'afafdsaf', null, 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/402883d4295a402501295a4589b90003.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff80808129653b0601296546fd4d0009', '采购公告', '\2010-06\0e9492b0_a38f_47b0_af6f_cdc113696272.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('23-06-2010', 'dd-mm-yyyy'), null, null, '采购公告', '郭永荣', '郭永荣', null, 'ff8080812954cf9901295502691a003e', null, null, '/cms/view/staticPags/index/ff80808129653b0601296546fd4d0009.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d42990fa030129910bbe6b001b', '标题', '\2010-06\176c29e2_9ef7_45cb_a473_815a58dca1ab.txt', null, '402883d42990fa030129910b652f0019', null, '8a808083204329sr01204330167b0005', '8a808083204329sr01204330167b0005', to_date('02-07-2010 10:45:52', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-07-2010 16:50:22', 'dd-mm-yyyy hh24:mi:ss'), null, '概要描述', '美国', '作者', '402883d42990fa030129910ad5ef0016', '402883d429a556d10129a58ac06d0845', null, null, '/cms/view/staticPags/index/402883d42990fa030129910bbe6b001b.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429af99df0129b02eb724001b', 'vxcvxcv', '\2010-07\1e2d2434_6e5e_4292_91af_e27569024ae6.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('08-07-2010 11:52:18', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, '402883d429a556d10129a58ac06d0845', null, null, '/cms/view/staticPags/index/402883d429af99df0129b02eb724001b.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429af99df0129b03435160020', 'fds fs', '\2010-07\524854e1_24fc_4696_ba52_10ffc187a499.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('08-07-2010 11:58:18', 'dd-mm-yyyy hh24:mi:ss'), null, null, 'fsf', 'af', 'fs', null, '402883d429a556d10129a58ac06d0845', null, null, '/cms/view/staticPags/agent/402883d429af99df0129b03435160020.html', null, 0, 'cms/cmsNewsContent/contentUpload/image/0295a1a0_e73c_4fb2_b40e_e70f44e731cd.jpg', 'cms/cmsNewsContent/contentUpload/file/121b7c70_52d0_4c2d_a060_74fcdb129222.doc', null, null, '-1');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429d3bdf00129d3c503a70029', '测试问题解答发布', '\2010-07\1dd9c257_e73a_42f8_8417_2b87a9301ff1.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('15-07-2010 09:43:11', 'dd-mm-yyyy hh24:mi:ss'), null, null, '概要描述', '测试', '郭永荣', null, 'ff808081299dc52501299dc8090f0008', null, null, '/cms/view/staticPags/question/402883d429d3bdf00129d3c503a70029.html', null, 0, 'cms/cmsNewsContent/contentUpload/image/592c1ea8_4f45_42fa_a217_fdb97e83a3ef.jpg', 'cms/cmsNewsContent/contentUpload/file/4c68db7d_531a_42a9_81c1_d0348ad293ba.doc', null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('402883d429d9b6eb0129d9be153e0006', '测试发布', '\2010-07\a3e94567_71ae_4860_b751_8befc293bc98.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('16-07-2010 13:33:20', 'dd-mm-yyyy hh24:mi:ss'), null, null, '测试发布', '测试发布', '郭永荣', null, 'ff808081299de09b01299de590050012', null, null, '/cms/view/staticPags/down/402883d429d9b6eb0129d9be153e0006.html', null, 0, 'cms/cmsNewsContent/contentUpload/image/e4b28cd7_a9d6_4022_9067_68f3d0a88070.jpg', 'cms/cmsNewsContent/contentUpload/file/194b158e_fb67_414b_82a0_e244301b88b0.doc', null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299de09b01299df678c1001c', 'fsf', '\2010-07\366d4d37_a994_4f0f_839a_fa320f57ac76.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 22:57:42', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/ff808081299de09b01299df678c1001c.html', null, 0, null, null, null, null, '00');
insert into CMS_NEWS (CMS_NEWS_ID, TITLE, CONTENT, NEWS_TYPE, NEWS_IMAGE, NEWS_ATTACHMENT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, NEWS_TYPE_CN, DESCRIPTION, ORIGIN, AUTHOR, FILE_ATTACHMENT, CHANNEL_ID, CHANNEL_TEMPLATE, CONTENT_TEMPLATE, URL, OUT_URL, VISTI_COUNT, IMG_URL, FILE_URL, FILE1_URL, FILE2_URL, CHECK_STATUS)
values ('ff808081299de09b01299df9e46d0024', 'ccc', '\2010-07\beaec3a2_1900_4f0b_8d1d_5d6ebd1b0f3c.txt', null, null, null, '8a808083204329sr01204330167b0005', null, to_date('04-07-2010 23:01:26', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, 'ff808081299d88a301299da411e80848', null, null, '/cms/view/staticPags/zhaobgg/ff808081299de09b01299df9e46d0024.html', null, 0, null, null, null, null, '00');
commit;
prompt 26 records loaded
prompt Loading CMS_TEMPLATE_FILE...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c056f', '402883d429d9eec20129da557b6c0567', 'searchPage.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\searchPage.ftl', null, '1', 1, 'ftlTemplate', 407, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0570', '402883d429d9eec20129da557b6c0567', 'vote.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\vote.ftl', null, '1', 2, 'ftlTemplate', 408, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0571', '402883d429d9eec20129da557b6c055c', 'help', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\help', null, '0', 1, 'ftlTemplate', 4, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0572', '402883d429d9eec20129da557b7b0571', 'freemarker常用语法', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\help\freemarker常用语法', null, '1', 47, 'ftlTemplate', 500, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0573', '402883d429d9eec20129da557b7b0571', '模板使用帮助手册', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\help\模板使用帮助手册', null, '1', 4, 'ftlTemplate', 501, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0574', '402883d429d9eec20129da557b6c055c', 'include', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\include', null, '0', 1, 'ftlTemplate', 5, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0575', '402883d429d9eec20129da557b7b0574', 'filePage.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\include\filePage.ftl', null, '1', 1, 'ftlTemplate', 600, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0576', '402883d429d9eec20129da557b7b0574', 'menu.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\include\menu.ftl', null, '1', 1, 'ftlTemplate', 601, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0577', '402883d429d9eec20129da557b7b0574', 'searchForm.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\include\searchForm.ftl', null, '1', 1, 'ftlTemplate', 602, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0578', '402883d429d9eec20129da557b7b0574', 'searchResult.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\include\searchResult.ftl', null, '1', 5, 'ftlTemplate', 603, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0579', '402883d429d9eec20129da557b6c055c', 'index', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\index', null, '0', 1, 'ftlTemplate', 6, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b057a', '402883d429d9eec20129da557b7b0579', 'content.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\index\content.ftl', null, '1', 5, 'ftlTemplate', 700, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b057b', '402883d429d9eec20129da557b7b0579', 'index.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\index\index.ftl', null, '1', 19, 'ftlTemplate', 701, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b057c', '402883d429d9eec20129da557b6c055c', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\index.html', null, '1', 21, 'ftlTemplate', 111, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b057d', '402883d429d9eec20129da557b6c055c', 'macro', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro', null, '0', 1, 'ftlTemplate', 7, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b057e', '402883d429d9eec20129da557b7b057d', 'chnlPage.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\chnlPage.ftl', null, '1', 1, 'ftlTemplate', 800, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b057f', '402883d429d9eec20129da557b7b057d', 'gpcsoft.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\gpcsoft.ftl', null, '1', 1, 'ftlTemplate', 801, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0580', '402883d429d9eec20129da557b7b057d', 'position.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\position.ftl', null, '1', 1, 'ftlTemplate', 802, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0581', '402883d429d9eec20129da557b7b057d', 'queryList.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\queryList.ftl', null, '1', 1, 'ftlTemplate', 803, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903ca', '402883d429d9eec20129da4af9c903bb', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconWrite1.gif', null, '1', 1, 'resBase', 5214, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0582', '402883d429d9eec20129da557b7b057d', 'searchPage.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\searchPage.ftl', null, '1', 1, 'ftlTemplate', 804, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0583', '402883d429d9eec20129da557b7b057d', 'searchResult.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\searchResult.ftl', null, '1', 1, 'ftlTemplate', 805, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9b6eb0129d9e6564c015c', null, 'includeTemplate', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\includeTemplate', null, '0', null, 'includeTemplate', 0, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903cb', '402883d429d9eec20129da4af9c903bb', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconWrite2.gif', null, '1', 1, 'resBase', 5215, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903cc', '402883d429d9eec20129da4af9c903bb', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\leaf.gif', null, '1', 1, 'resBase', 5216, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b7b0584', '402883d429d9eec20129da557b7b057d', 'voteTopic.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\macro\voteTopic.ftl', null, '1', 1, 'ftlTemplate', 806, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c2', '402883d429d9eec20129da4af9c903bb', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconCheckGray.gif', null, '1', 1, 'resBase', 5206, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c3', '402883d429d9eec20129da4af9c903bb', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconFlag.gif', null, '1', 1, 'resBase', 5207, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c4', '402883d429d9eec20129da4af9c903bb', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconGraph.gif', null, '1', 1, 'resBase', 5208, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c5', '402883d429d9eec20129da4af9c903bb', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconSound.gif', null, '1', 1, 'resBase', 5209, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c6', '402883d429d9eec20129da4af9c903bb', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconText.gif', null, '1', 1, 'resBase', 5210, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c7', '402883d429d9eec20129da4af9c903bb', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconTexts.gif', null, '1', 1, 'resBase', 5211, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c8', '402883d429d9eec20129da4af9c903bb', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconUncheckAll.gif', null, '1', 1, 'resBase', 5212, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c9', '402883d429d9eec20129da4af9c903bb', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconUncheckDis.gif', null, '1', 1, 'resBase', 5213, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903cd', '402883d429d9eec20129da4af9c903bb', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\line.gif', null, '1', 1, 'resBase', 5217, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9b6eb0129d9e6564c015d', '402883d429d9b6eb0129d9e6564c015c', 'foot.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\includeTemplate\foot.ftl', null, '1', 1, 'includeTemplate', 100, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9b6eb0129d9e6564c015e', '402883d429d9b6eb0129d9e6564c015c', 'left.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\includeTemplate\left.ftl', null, '1', 1, 'includeTemplate', 101, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9b6eb0129d9e6564c015f', '402883d429d9b6eb0129d9e6564c015c', 'right.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\includeTemplate\right.ftl', null, '1', 1, 'includeTemplate', 102, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9b6eb0129d9e6564c0160', '402883d429d9b6eb0129d9e6564c015c', 'top.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\includeTemplate\top.ftl', null, '1', 1, 'includeTemplate', 103, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0307', '402883d429d9eec20129da4af96b02de', 'plus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus3_rtl.gif', null, '1', 1, 'resBase', 3940, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0308', '402883d429d9eec20129da4af96b02de', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus4.gif', null, '1', 1, 'resBase', 3941, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0309', '402883d429d9eec20129da4af96b02de', 'plus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus4_rtl.gif', null, '1', 1, 'resBase', 3942, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b030a', '402883d429d9eec20129da4af96b02de', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus5.gif', null, '1', 1, 'resBase', 3943, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b030b', '402883d429d9eec20129da4af96b02de', 'plus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus5_rtl.gif', null, '1', 1, 'resBase', 3944, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b030c', '402883d429d9eec20129da4af96b02de', 'plus_ar.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus_ar.gif', null, '1', 1, 'resBase', 3945, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b030d', '402883d429d9eec20129da4af96b02de', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\radio_off.gif', null, '1', 1, 'resBase', 3946, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b030e', '402883d429d9eec20129da4af96b02de', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\radio_on.gif', null, '1', 1, 'resBase', 3947, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b030f', '402883d429d9eec20129da4af96b02de', 'red.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\red.gif', null, '1', 1, 'resBase', 3948, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0310', '402883d429d9eec20129da4af96b02de', 'white.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\white.gif', null, '1', 1, 'resBase', 3949, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0311', '402883d429d9eec20129da4af96b02de', 'yellow.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\yellow.gif', null, '1', 1, 'resBase', 3950, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0312', '402883d429d9eec20129da4af93c0277', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\index.html', null, '1', 2, 'resBase', 2610, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0313', '402883d429d9eec20129da4af93c0277', 'initialization_general_settings', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\initialization_general_settings', null, '0', 1, 'resBase', 39, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0314', '402883d429d9eec20129da4af97b0313', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\initialization_general_settings\index.html', null, '1', 1, 'resBase', 4000, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0315', '402883d429d9eec20129da4af97b0313', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\initialization_general_settings\tree.xml', null, '1', 6, 'resBase', 4001, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0316', '402883d429d9eec20129da4af97b0313', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\initialization_general_settings\tree3.xml', null, '1', 6, 'resBase', 4002, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0317', '402883d429d9eec20129da4af97b0313', 'tree_init_from_html.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\initialization_general_settings\tree_init_from_html.html', null, '1', 14, 'resBase', 4003, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0318', '402883d429d9eec20129da4af97b0313', 'tree_init_xml.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\initialization_general_settings\tree_init_xml.html', null, '1', 6, 'resBase', 4004, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b0319', '402883d429d9eec20129da4af93c0277', 'interaction_other_components', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\interaction_other_components', null, '0', 1, 'resBase', 40, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b031a', '402883d429d9eec20129da4af97b0319', 'grid.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\interaction_other_components\grid.xml', null, '1', 3, 'resBase', 4100, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b031b', '402883d429d9eec20129da4af97b0319', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\interaction_other_components\index.html', null, '1', 1, 'resBase', 4101, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b031c', '402883d429d9eec20129da4af97b0319', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\interaction_other_components\tree3.xml', null, '1', 6, 'resBase', 4102, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b031d', '402883d429d9eec20129da4af93c0277', 'json_support', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support', null, '0', 1, 'resBase', 41, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b031e', '402883d429d9eec20129da4af97b031d', 'big_data.json', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\big_data.json', null, '1', 466, 'resBase', 4200, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af97b031f', '402883d429d9eec20129da4af97b031d', 'data.csv', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\data.csv', null, '1', 1, 'resBase', 4201, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0320', '402883d429d9eec20129da4af97b031d', 'data.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\data.js', null, '1', 1, 'resBase', 4202, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0321', '402883d429d9eec20129da4af97b031d', 'data.json', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\data.json', null, '1', 1, 'resBase', 4203, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0322', '402883d429d9eec20129da4af97b031d', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\index.html', null, '1', 1, 'resBase', 4204, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0323', '402883d429d9eec20129da4af97b031d', 'json.php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\json.php', null, '1', 1, 'resBase', 4205, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0324', '402883d429d9eec20129da4af97b031d', 'pro_dyn_loading_json.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\json_support\pro_dyn_loading_json.html', null, '1', 7, 'resBase', 4206, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0325', '402883d429d9eec20129da4af93c0277', 'loading_processing_data', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data', null, '0', 1, 'resBase', 42, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0326', '402883d429d9eec20129da4af98a0325', 'big_xml.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\big_xml.xml', null, '1', 479, 'resBase', 4300, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0327', '402883d429d9eec20129da4af98a0325', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\index.html', null, '1', 1, 'resBase', 4301, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0328', '402883d429d9eec20129da4af98a0325', 'pro_linked_form.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\pro_linked_form.html', null, '1', 9, 'resBase', 4302, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0329', '402883d429d9eec20129da4af98a0325', 'pro_paging.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\pro_paging.html', null, '1', 6, 'resBase', 4303, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a032a', '402883d429d9eec20129da4af98a0325', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\tree.xml', null, '1', 6, 'resBase', 4304, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a032b', '402883d429d9eec20129da4af98a0325', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\tree3.xml', null, '1', 6, 'resBase', 4305, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a032c', '402883d429d9eec20129da4af98a0325', 'tree_bg.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\tree_bg.xml', null, '1', 1616, 'resBase', 4306, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a032d', '402883d429d9eec20129da4af98a0325', 'tree_dyn_loading.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\tree_dyn_loading.html', null, '1', 7, 'resBase', 4307, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a032e', '402883d429d9eec20129da4af98a0325', 'tree_perf_xml.php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\tree_perf_xml.php', null, '1', 1, 'resBase', 4308, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a032f', '402883d429d9eec20129da4af98a0325', 'xml.php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\loading_processing_data\xml.php', null, '1', 1, 'resBase', 4309, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0330', '402883d429d9eec20129da4af93c0277', 'nodes_manipulation', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation', null, '0', 1, 'resBase', 43, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0331', '402883d429d9eec20129da4af98a0330', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation\index.html', null, '1', 1, 'resBase', 4400, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0332', '402883d429d9eec20129da4af98a0330', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation\tree.xml', null, '1', 6, 'resBase', 4401, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0333', '402883d429d9eec20129da4af98a0330', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation\tree3.xml', null, '1', 6, 'resBase', 4402, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0334', '402883d429d9eec20129da4af98a0330', 'tree_add_delete.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation\tree_add_delete.html', null, '1', 9, 'resBase', 4403, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0335', '402883d429d9eec20129da4af98a0330', 'tree_lc.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation\tree_lc.xml', null, '1', 6, 'resBase', 4404, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0336', '402883d429d9eec20129da4af98a0330', 'tree_open_close.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\nodes_manipulation\tree_open_close.html', null, '1', 8, 'resBase', 4405, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0337', '402883d429d9eec20129da4af93c0277', 'selection_sorting_navigation', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\selection_sorting_navigation', null, '0', 1, 'resBase', 44, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0338', '402883d429d9eec20129da4af98a0337', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\selection_sorting_navigation\index.html', null, '1', 1, 'resBase', 4500, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0339', '402883d429d9eec20129da4af98a0337', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\selection_sorting_navigation\tree.xml', null, '1', 6, 'resBase', 4501, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a033a', '402883d429d9eec20129da4af98a0337', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\selection_sorting_navigation\tree3.xml', null, '1', 3, 'resBase', 4502, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a033b', '402883d429d9eec20129da4af98a0337', 'tree_lg.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\selection_sorting_navigation\tree_lg.xml', null, '1', 3, 'resBase', 4503, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a033c', '402883d429d9eec20129da4af8b000f5', 'sources', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources', null, '0', 1, 'resBase', 45, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a033d', '402883d429d9eec20129da4af98a033c', 'dhtmlxcommon.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\dhtmlxcommon.js', null, '1', 23, 'resBase', 4600, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a033e', '402883d429d9eec20129da4af98a033c', 'dhtmlxtree.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\dhtmlxtree.js', null, '1', 120, 'resBase', 4601, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a033f', '402883d429d9eec20129da4af98a033c', 'ext', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\ext', null, '0', 1, 'resBase', 46, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0340', '402883d429d9eec20129da4af98a033f', 'dhtmlxtree_dragin.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\ext\dhtmlxtree_dragin.js', null, '1', 3, 'resBase', 4700, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0341', '402883d429d9eec20129da4af98a033f', 'dhtmlxtree_ed.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\ext\dhtmlxtree_ed.js', null, '1', 7, 'resBase', 4701, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0342', '402883d429d9eec20129da4af98a033f', 'dhtmlxtree_er.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\ext\dhtmlxtree_er.js', null, '1', 4, 'resBase', 4702, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0343', '402883d429d9eec20129da4af98a033f', 'dhtmlxtree_json.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\ext\dhtmlxtree_json.js', null, '1', 8, 'resBase', 4703, 'cms/fileIco/unknow.gif');
commit;
prompt 100 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0344', '402883d429d9eec20129da4af98a033f', 'dhtmlxtree_start.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\ext\dhtmlxtree_start.js', null, '1', 3, 'resBase', 4704, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0345', '402883d429d9eec20129da4af98a033c', 'imgs', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs', null, '0', 1, 'resBase', 47, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0346', '402883d429d9eec20129da4af98a0345', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\blank.gif', null, '1', 1, 'resBase', 4800, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0347', '402883d429d9eec20129da4af98a0345', 'but_cut.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\but_cut.gif', null, '1', 1, 'resBase', 4801, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0348', '402883d429d9eec20129da4af98a0345', 'csh_bluebooks', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks', null, '0', 1, 'resBase', 48, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af98a0349', '402883d429d9eec20129da4af98a0348', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\blank.gif', null, '1', 1, 'resBase', 4900, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a034a', '402883d429d9eec20129da4af98a0348', 'book.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\book.gif', null, '1', 1, 'resBase', 4901, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a034b', '402883d429d9eec20129da4af98a0348', 'book_titel.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\book_titel.gif', null, '1', 1, 'resBase', 4902, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a034c', '402883d429d9eec20129da4af98a0348', 'but_cut.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\but_cut.gif', null, '1', 1, 'resBase', 4903, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a034d', '402883d429d9eec20129da4af98a0348', 'close2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\close2.gif', null, '1', 2, 'resBase', 4904, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a034e', '402883d429d9eec20129da4af98a0348', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\folderClosed.gif', null, '1', 1, 'resBase', 4905, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a034f', '402883d429d9eec20129da4af98a0348', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\folderOpen.gif', null, '1', 1, 'resBase', 4906, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0350', '402883d429d9eec20129da4af98a0348', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconCheckAll.gif', null, '1', 1, 'resBase', 4907, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a035d', '402883d429d9eec20129da4af98a0348', 'line2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line2_rtl.gif', null, '1', 1, 'resBase', 4920, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a035e', '402883d429d9eec20129da4af98a0348', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line3.gif', null, '1', 1, 'resBase', 4921, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a035f', '402883d429d9eec20129da4af98a0348', 'line3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line3_rtl.gif', null, '1', 1, 'resBase', 4922, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0360', '402883d429d9eec20129da4af98a0348', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line4.gif', null, '1', 1, 'resBase', 4923, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0351', '402883d429d9eec20129da4af98a0348', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconCheckDis.gif', null, '1', 1, 'resBase', 4908, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0352', '402883d429d9eec20129da4af98a0348', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconChecked.gif', null, '1', 1, 'resBase', 4909, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0353', '402883d429d9eec20129da4af98a0348', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconCheckGray.gif', null, '1', 1, 'resBase', 4910, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0354', '402883d429d9eec20129da4af98a0348', 'iconSafe.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconSafe.gif', null, '1', 1, 'resBase', 4911, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0355', '402883d429d9eec20129da4af98a0348', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconText.gif', null, '1', 1, 'resBase', 4912, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0356', '402883d429d9eec20129da4af98a0348', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconUncheckAll.gif', null, '1', 1, 'resBase', 4913, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0357', '402883d429d9eec20129da4af98a0348', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\iconUncheckDis.gif', null, '1', 1, 'resBase', 4914, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0358', '402883d429d9eec20129da4af98a0348', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\leaf.gif', null, '1', 1, 'resBase', 4915, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0359', '402883d429d9eec20129da4af98a0348', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line.gif', null, '1', 1, 'resBase', 4916, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a035a', '402883d429d9eec20129da4af98a0348', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line1.gif', null, '1', 1, 'resBase', 4917, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a035b', '402883d429d9eec20129da4af98a0348', 'line1_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line1_rtl.gif', null, '1', 1, 'resBase', 4918, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a035c', '402883d429d9eec20129da4af98a0348', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line2.gif', null, '1', 1, 'resBase', 4919, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0361', '402883d429d9eec20129da4af98a0348', 'line4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\line4_rtl.gif', null, '1', 1, 'resBase', 4924, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0362', '402883d429d9eec20129da4af98a0348', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\lock.gif', null, '1', 1, 'resBase', 4925, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0363', '402883d429d9eec20129da4af98a0348', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus.gif', null, '1', 1, 'resBase', 4926, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0364', '402883d429d9eec20129da4af98a0348', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus2.gif', null, '1', 1, 'resBase', 4927, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0365', '402883d429d9eec20129da4af98a0348', 'minus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus2_rtl.gif', null, '1', 1, 'resBase', 4928, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0366', '402883d429d9eec20129da4af98a0348', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus3.gif', null, '1', 1, 'resBase', 4929, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0367', '402883d429d9eec20129da4af98a0348', 'minus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus3_rtl.gif', null, '1', 1, 'resBase', 4930, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0368', '402883d429d9eec20129da4af98a0348', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus4.gif', null, '1', 1, 'resBase', 4931, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0369', '402883d429d9eec20129da4af98a0348', 'minus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus4_rtl.gif', null, '1', 1, 'resBase', 4932, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a036a', '402883d429d9eec20129da4af98a0348', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus5.gif', null, '1', 1, 'resBase', 4933, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a036b', '402883d429d9eec20129da4af98a0348', 'minus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\minus5_rtl.gif', null, '1', 1, 'resBase', 4934, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a036c', '402883d429d9eec20129da4af98a0348', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus.gif', null, '1', 1, 'resBase', 4935, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a036d', '402883d429d9eec20129da4af98a0348', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus2.gif', null, '1', 1, 'resBase', 4936, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a036e', '402883d429d9eec20129da4af98a0348', 'plus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus2_rtl.gif', null, '1', 1, 'resBase', 4937, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a036f', '402883d429d9eec20129da4af98a0348', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus3.gif', null, '1', 1, 'resBase', 4938, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0370', '402883d429d9eec20129da4af98a0348', 'plus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus3_rtl.gif', null, '1', 1, 'resBase', 4939, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0371', '402883d429d9eec20129da4af98a0348', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus4.gif', null, '1', 1, 'resBase', 4940, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0372', '402883d429d9eec20129da4af98a0348', 'plus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus4_rtl.gif', null, '1', 1, 'resBase', 4941, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0373', '402883d429d9eec20129da4af98a0348', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus5.gif', null, '1', 1, 'resBase', 4942, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0374', '402883d429d9eec20129da4af98a0348', 'plus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\plus5_rtl.gif', null, '1', 1, 'resBase', 4943, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af99a0375', '402883d429d9eec20129da4af98a0348', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\radio_off.gif', null, '1', 1, 'resBase', 4944, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0376', '402883d429d9eec20129da4af98a0348', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\radio_on.gif', null, '1', 1, 'resBase', 4945, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0377', '402883d429d9eec20129da4af98a0348', 'tombs.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluebooks\tombs.gif', null, '1', 1, 'resBase', 4946, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0378', '402883d429d9eec20129da4af98a0345', 'csh_bluefolders', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders', null, '0', 1, 'resBase', 49, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0379', '402883d429d9eec20129da4af9aa0378', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\blank.gif', null, '1', 1, 'resBase', 5000, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa037a', '402883d429d9eec20129da4af9aa0378', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\folderClosed.gif', null, '1', 1, 'resBase', 5001, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa037b', '402883d429d9eec20129da4af9aa0378', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\folderOpen.gif', null, '1', 1, 'resBase', 5002, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa037c', '402883d429d9eec20129da4af9aa0378', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconCheckAll.gif', null, '1', 1, 'resBase', 5003, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa037d', '402883d429d9eec20129da4af9aa0378', 'iconCheckAll_2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconCheckAll_2.gif', null, '1', 1, 'resBase', 5004, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa037e', '402883d429d9eec20129da4af9aa0378', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconCheckDis.gif', null, '1', 1, 'resBase', 5005, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa037f', '402883d429d9eec20129da4af9aa0378', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconChecked.gif', null, '1', 1, 'resBase', 5006, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0380', '402883d429d9eec20129da4af9aa0378', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconCheckGray.gif', null, '1', 1, 'resBase', 5007, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0381', '402883d429d9eec20129da4af9aa0378', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconFlag.gif', null, '1', 1, 'resBase', 5008, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0382', '402883d429d9eec20129da4af9aa0378', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconGraph.gif', null, '1', 1, 'resBase', 5009, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0383', '402883d429d9eec20129da4af9aa0378', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconSound.gif', null, '1', 1, 'resBase', 5010, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0384', '402883d429d9eec20129da4af9aa0378', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconText.gif', null, '1', 1, 'resBase', 5011, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0385', '402883d429d9eec20129da4af9aa0378', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconTexts.gif', null, '1', 1, 'resBase', 5012, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0386', '402883d429d9eec20129da4af9aa0378', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconUncheckAll.gif', null, '1', 1, 'resBase', 5013, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0387', '402883d429d9eec20129da4af9aa0378', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconUncheckDis.gif', null, '1', 1, 'resBase', 5014, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0388', '402883d429d9eec20129da4af9aa0378', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconWrite1.gif', null, '1', 1, 'resBase', 5015, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0389', '402883d429d9eec20129da4af9aa0378', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\iconWrite2.gif', null, '1', 1, 'resBase', 5016, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa038a', '402883d429d9eec20129da4af9aa0378', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\leaf.gif', null, '1', 1, 'resBase', 5017, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa038b', '402883d429d9eec20129da4af9aa0378', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\line.gif', null, '1', 1, 'resBase', 5018, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa038c', '402883d429d9eec20129da4af9aa0378', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\line1.gif', null, '1', 1, 'resBase', 5019, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa038d', '402883d429d9eec20129da4af9aa0378', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\line2.gif', null, '1', 1, 'resBase', 5020, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa038e', '402883d429d9eec20129da4af9aa0378', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\line3.gif', null, '1', 1, 'resBase', 5021, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa038f', '402883d429d9eec20129da4af9aa0378', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\line4.gif', null, '1', 1, 'resBase', 5022, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0390', '402883d429d9eec20129da4af9aa0378', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\lock.gif', null, '1', 1, 'resBase', 5023, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0391', '402883d429d9eec20129da4af9aa0378', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\minus.gif', null, '1', 1, 'resBase', 5024, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9aa0392', '402883d429d9eec20129da4af9aa0378', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\minus2.gif', null, '1', 1, 'resBase', 5025, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90393', '402883d429d9eec20129da4af9aa0378', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\minus3.gif', null, '1', 1, 'resBase', 5026, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90394', '402883d429d9eec20129da4af9aa0378', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\minus4.gif', null, '1', 1, 'resBase', 5027, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90395', '402883d429d9eec20129da4af9aa0378', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\minus5.gif', null, '1', 1, 'resBase', 5028, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90396', '402883d429d9eec20129da4af9aa0378', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\plus.gif', null, '1', 1, 'resBase', 5029, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90397', '402883d429d9eec20129da4af9aa0378', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\plus2.gif', null, '1', 1, 'resBase', 5030, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90398', '402883d429d9eec20129da4af9aa0378', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\plus3.gif', null, '1', 1, 'resBase', 5031, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b90399', '402883d429d9eec20129da4af9aa0378', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\plus4.gif', null, '1', 1, 'resBase', 5032, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b9039a', '402883d429d9eec20129da4af9aa0378', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\plus5.gif', null, '1', 1, 'resBase', 5033, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b9039b', '402883d429d9eec20129da4af9aa0378', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\radio_off.gif', null, '1', 1, 'resBase', 5034, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b9039c', '402883d429d9eec20129da4af9aa0378', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_bluefolders\radio_on.gif', null, '1', 1, 'resBase', 5035, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b9039d', '402883d429d9eec20129da4af98a0345', 'csh_books', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books', null, '0', 1, 'resBase', 50, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b9039e', '402883d429d9eec20129da4af9b9039d', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\blank.gif', null, '1', 1, 'resBase', 5100, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b9039f', '402883d429d9eec20129da4af9b9039d', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\folderClosed.gif', null, '1', 1, 'resBase', 5101, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a0', '402883d429d9eec20129da4af9b9039d', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\folderOpen.gif', null, '1', 1, 'resBase', 5102, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a1', '402883d429d9eec20129da4af9b9039d', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\iconCheckAll.gif', null, '1', 1, 'resBase', 5103, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a2', '402883d429d9eec20129da4af9b9039d', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\iconCheckDis.gif', null, '1', 1, 'resBase', 5104, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a3', '402883d429d9eec20129da4af9b9039d', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\iconCheckGray.gif', null, '1', 1, 'resBase', 5105, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a4', '402883d429d9eec20129da4af9b9039d', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\iconUncheckAll.gif', null, '1', 1, 'resBase', 5106, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a5', '402883d429d9eec20129da4af9b9039d', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\iconUncheckDis.gif', null, '1', 1, 'resBase', 5107, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a6', '402883d429d9eec20129da4af9b9039d', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\leaf.gif', null, '1', 1, 'resBase', 5108, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a7', '402883d429d9eec20129da4af9b9039d', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\line.gif', null, '1', 1, 'resBase', 5109, 'cms/fileIco/gif.gif');
commit;
prompt 200 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a8', '402883d429d9eec20129da4af9b9039d', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\line1.gif', null, '1', 1, 'resBase', 5110, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903a9', '402883d429d9eec20129da4af9b9039d', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\line2.gif', null, '1', 1, 'resBase', 5111, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903aa', '402883d429d9eec20129da4af9b9039d', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\line3.gif', null, '1', 1, 'resBase', 5112, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903ab', '402883d429d9eec20129da4af9b9039d', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\line4.gif', null, '1', 1, 'resBase', 5113, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9b903ac', '402883d429d9eec20129da4af9b9039d', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\lock.gif', null, '1', 1, 'resBase', 5114, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903ad', '402883d429d9eec20129da4af9b9039d', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\minus.gif', null, '1', 1, 'resBase', 5115, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903ae', '402883d429d9eec20129da4af9b9039d', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\minus2.gif', null, '1', 1, 'resBase', 5116, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903af', '402883d429d9eec20129da4af9b9039d', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\minus3.gif', null, '1', 1, 'resBase', 5117, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b0', '402883d429d9eec20129da4af9b9039d', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\minus4.gif', null, '1', 1, 'resBase', 5118, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b1', '402883d429d9eec20129da4af9b9039d', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\minus5.gif', null, '1', 1, 'resBase', 5119, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b2', '402883d429d9eec20129da4af9b9039d', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\plus.gif', null, '1', 1, 'resBase', 5120, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b3', '402883d429d9eec20129da4af9b9039d', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\plus2.gif', null, '1', 1, 'resBase', 5121, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b4', '402883d429d9eec20129da4af9b9039d', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\plus3.gif', null, '1', 1, 'resBase', 5122, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b5', '402883d429d9eec20129da4af9b9039d', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\plus4.gif', null, '1', 1, 'resBase', 5123, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b6', '402883d429d9eec20129da4af9b9039d', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\plus5.gif', null, '1', 1, 'resBase', 5124, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b7', '402883d429d9eec20129da4af9b9039d', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\radio_off.gif', null, '1', 1, 'resBase', 5125, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b8', '402883d429d9eec20129da4af9b9039d', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\radio_on.gif', null, '1', 1, 'resBase', 5126, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903b9', '402883d429d9eec20129da4af9b9039d', 'tombs.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\tombs.gif', null, '1', 1, 'resBase', 5127, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903ba', '402883d429d9eec20129da4af9b9039d', 'tombs_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_books\tombs_open.gif', null, '1', 1, 'resBase', 5128, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903bb', '402883d429d9eec20129da4af98a0345', 'csh_scbrblue', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue', null, '0', 1, 'resBase', 51, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903bc', '402883d429d9eec20129da4af9c903bb', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\blank.gif', null, '1', 1, 'resBase', 5200, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903bd', '402883d429d9eec20129da4af9c903bb', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\folderClosed.gif', null, '1', 1, 'resBase', 5201, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903be', '402883d429d9eec20129da4af9c903bb', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\folderOpen.gif', null, '1', 1, 'resBase', 5202, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903bf', '402883d429d9eec20129da4af9c903bb', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconCheckAll.gif', null, '1', 1, 'resBase', 5203, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c0', '402883d429d9eec20129da4af9c903bb', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconCheckDis.gif', null, '1', 1, 'resBase', 5204, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903c1', '402883d429d9eec20129da4af9c903bb', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\iconChecked.gif', null, '1', 1, 'resBase', 5205, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c055c', null, 'ftlTemplate', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate', null, '0', null, 'ftlTemplate', 0, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c055d', '402883d429d9eec20129da557b6c055c', 'channel', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\channel', null, '0', 1, 'ftlTemplate', 1, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c055e', '402883d429d9eec20129da557b6c055d', 'content.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\channel\content.ftl', null, '1', 1, 'ftlTemplate', 200, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c055f', '402883d429d9eec20129da557b6c055d', 'index.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\channel\index.ftl', null, '1', 8, 'ftlTemplate', 201, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0560', '402883d429d9eec20129da557b6c055c', 'channel.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\channel.ftl', null, '1', 5, 'ftlTemplate', 101, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0561', '402883d429d9eec20129da557b6c055c', 'common', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\common', null, '0', 1, 'ftlTemplate', 2, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0562', '402883d429d9eec20129da557b6c0561', 'common.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\common\common.ftl', null, '1', 1, 'ftlTemplate', 300, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0563', '402883d429d9eec20129da557b6c055c', 'content.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\content.ftl', null, '1', 5, 'ftlTemplate', 103, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0564', '402883d429d9eec20129da557b6c055c', 'demo.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\demo.ftl', null, '1', 1, 'ftlTemplate', 104, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0565', '402883d429d9eec20129da557b6c055c', 'foot.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\foot.ftl', null, '1', 1, 'ftlTemplate', 105, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0566', '402883d429d9eec20129da557b6c055c', 'foot.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\foot.html', null, '1', 1, 'ftlTemplate', 106, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0567', '402883d429d9eec20129da557b6c055c', 'ftlLib', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib', null, '0', 1, 'ftlTemplate', 3, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0568', '402883d429d9eec20129da557b6c0567', 'chnlPage.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\chnlPage.ftl', null, '1', 1, 'ftlTemplate', 400, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c0569', '402883d429d9eec20129da557b6c0567', 'ftl_down.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\ftl_down.ftl', null, '1', 1, 'ftlTemplate', 401, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c056a', '402883d429d9eec20129da557b6c0567', 'ftl_list.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\ftl_list.ftl', null, '1', 1, 'ftlTemplate', 402, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c056b', '402883d429d9eec20129da557b6c0567', 'ftl_list2.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\ftl_list2.ftl', null, '1', 1, 'ftlTemplate', 403, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c056c', '402883d429d9eec20129da557b6c0567', 'ftl_menu.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\ftl_menu.ftl', null, '1', 1, 'ftlTemplate', 404, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c056d', '402883d429d9eec20129da557b6c0567', 'position.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\position.ftl', null, '1', 1, 'ftlTemplate', 405, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da557b6c056e', '402883d429d9eec20129da557b6c0567', 'search.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\ftlTemplate\ftlLib\search.ftl', null, '1', 1, 'ftlTemplate', 406, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf00fd', '402883d429d9eec20129da4af8b000fa', 'dhtmlxtree_er.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\ext\dhtmlxtree_er.js', null, '1', 3, 'resBase', 702, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf00fe', '402883d429d9eec20129da4af8b000fa', 'dhtmlxtree_json.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\ext\dhtmlxtree_json.js', null, '1', 6, 'resBase', 703, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf00ff', '402883d429d9eec20129da4af8b000fa', 'dhtmlxtree_start.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\ext\dhtmlxtree_start.js', null, '1', 3, 'resBase', 704, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0100', '402883d429d9eec20129da4af8b000f6', 'imgs', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs', null, '0', 1, 'resBase', 7, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0101', '402883d429d9eec20129da4af8bf0100', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\blank.gif', null, '1', 1, 'resBase', 800, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0102', '402883d429d9eec20129da4af8bf0100', 'but_cut.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\but_cut.gif', null, '1', 1, 'resBase', 801, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0103', '402883d429d9eec20129da4af8bf0100', 'csh_bluebooks', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks', null, '0', 1, 'resBase', 8, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0104', '402883d429d9eec20129da4af8bf0103', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\blank.gif', null, '1', 1, 'resBase', 900, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0105', '402883d429d9eec20129da4af8bf0103', 'book.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\book.gif', null, '1', 1, 'resBase', 901, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0106', '402883d429d9eec20129da4af8bf0103', 'book_titel.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\book_titel.gif', null, '1', 1, 'resBase', 902, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0107', '402883d429d9eec20129da4af8bf0103', 'but_cut.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\but_cut.gif', null, '1', 1, 'resBase', 903, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0108', '402883d429d9eec20129da4af8bf0103', 'close2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\close2.gif', null, '1', 2, 'resBase', 904, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf0109', '402883d429d9eec20129da4af8bf0103', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\folderClosed.gif', null, '1', 1, 'resBase', 905, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf010a', '402883d429d9eec20129da4af8bf0103', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\folderOpen.gif', null, '1', 1, 'resBase', 906, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf010b', '402883d429d9eec20129da4af8bf0103', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconCheckAll.gif', null, '1', 1, 'resBase', 907, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf010c', '402883d429d9eec20129da4af8bf0103', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconCheckDis.gif', null, '1', 1, 'resBase', 908, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf010d', '402883d429d9eec20129da4af8bf0103', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconChecked.gif', null, '1', 1, 'resBase', 909, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf010e', '402883d429d9eec20129da4af8bf0103', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconCheckGray.gif', null, '1', 1, 'resBase', 910, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf010f', '402883d429d9eec20129da4af8bf0103', 'iconSafe.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconSafe.gif', null, '1', 1, 'resBase', 911, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0110', '402883d429d9eec20129da4af8bf0103', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconText.gif', null, '1', 1, 'resBase', 912, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0111', '402883d429d9eec20129da4af8bf0103', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconUncheckAll.gif', null, '1', 1, 'resBase', 913, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0112', '402883d429d9eec20129da4af8bf0103', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\iconUncheckDis.gif', null, '1', 1, 'resBase', 914, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0113', '402883d429d9eec20129da4af8bf0103', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\leaf.gif', null, '1', 1, 'resBase', 915, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0114', '402883d429d9eec20129da4af8bf0103', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line.gif', null, '1', 1, 'resBase', 916, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0115', '402883d429d9eec20129da4af8bf0103', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line1.gif', null, '1', 1, 'resBase', 917, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0116', '402883d429d9eec20129da4af8bf0103', 'line1_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line1_rtl.gif', null, '1', 1, 'resBase', 918, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0117', '402883d429d9eec20129da4af8bf0103', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line2.gif', null, '1', 1, 'resBase', 919, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0118', '402883d429d9eec20129da4af8bf0103', 'line2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line2_rtl.gif', null, '1', 1, 'resBase', 920, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0119', '402883d429d9eec20129da4af8bf0103', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line3.gif', null, '1', 1, 'resBase', 921, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf011a', '402883d429d9eec20129da4af8bf0103', 'line3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line3_rtl.gif', null, '1', 1, 'resBase', 922, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf011b', '402883d429d9eec20129da4af8bf0103', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line4.gif', null, '1', 1, 'resBase', 923, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf011c', '402883d429d9eec20129da4af8bf0103', 'line4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\line4_rtl.gif', null, '1', 1, 'resBase', 924, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf011d', '402883d429d9eec20129da4af8bf0103', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\lock.gif', null, '1', 1, 'resBase', 925, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf011e', '402883d429d9eec20129da4af8bf0103', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus.gif', null, '1', 1, 'resBase', 926, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf011f', '402883d429d9eec20129da4af8bf0103', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus2.gif', null, '1', 1, 'resBase', 927, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0120', '402883d429d9eec20129da4af8bf0103', 'minus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus2_rtl.gif', null, '1', 1, 'resBase', 928, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0121', '402883d429d9eec20129da4af8bf0103', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus3.gif', null, '1', 1, 'resBase', 929, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0122', '402883d429d9eec20129da4af8bf0103', 'minus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus3_rtl.gif', null, '1', 1, 'resBase', 930, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0123', '402883d429d9eec20129da4af8bf0103', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus4.gif', null, '1', 1, 'resBase', 931, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0124', '402883d429d9eec20129da4af8bf0103', 'minus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus4_rtl.gif', null, '1', 1, 'resBase', 932, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0125', '402883d429d9eec20129da4af8bf0103', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus5.gif', null, '1', 1, 'resBase', 933, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0126', '402883d429d9eec20129da4af8bf0103', 'minus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\minus5_rtl.gif', null, '1', 1, 'resBase', 934, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0127', '402883d429d9eec20129da4af8bf0103', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus.gif', null, '1', 1, 'resBase', 935, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0128', '402883d429d9eec20129da4af8bf0103', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus2.gif', null, '1', 1, 'resBase', 936, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0129', '402883d429d9eec20129da4af8bf0103', 'plus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus2_rtl.gif', null, '1', 1, 'resBase', 937, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf012a', '402883d429d9eec20129da4af8bf0103', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus3.gif', null, '1', 1, 'resBase', 938, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf012b', '402883d429d9eec20129da4af8bf0103', 'plus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus3_rtl.gif', null, '1', 1, 'resBase', 939, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf012c', '402883d429d9eec20129da4af8bf0103', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus4.gif', null, '1', 1, 'resBase', 940, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf012d', '402883d429d9eec20129da4af8bf0103', 'plus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus4_rtl.gif', null, '1', 1, 'resBase', 941, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf012e', '402883d429d9eec20129da4af8bf0103', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus5.gif', null, '1', 1, 'resBase', 942, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf012f', '402883d429d9eec20129da4af8bf0103', 'plus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\plus5_rtl.gif', null, '1', 1, 'resBase', 943, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0130', '402883d429d9eec20129da4af8bf0103', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\radio_off.gif', null, '1', 1, 'resBase', 944, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0131', '402883d429d9eec20129da4af8bf0103', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\radio_on.gif', null, '1', 1, 'resBase', 945, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0132', '402883d429d9eec20129da4af8bf0103', 'tombs.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluebooks\tombs.gif', null, '1', 1, 'resBase', 946, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0133', '402883d429d9eec20129da4af8bf0100', 'csh_bluefolders', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders', null, '0', 1, 'resBase', 9, 'cms/fileIco/folderClosed.gif');
commit;
prompt 300 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0134', '402883d429d9eec20129da4af8cf0133', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\blank.gif', null, '1', 1, 'resBase', 1000, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0135', '402883d429d9eec20129da4af8cf0133', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\folderClosed.gif', null, '1', 1, 'resBase', 1001, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0136', '402883d429d9eec20129da4af8cf0133', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\folderOpen.gif', null, '1', 1, 'resBase', 1002, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0137', '402883d429d9eec20129da4af8cf0133', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconCheckAll.gif', null, '1', 1, 'resBase', 1003, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0138', '402883d429d9eec20129da4af8cf0133', 'iconCheckAll_2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconCheckAll_2.gif', null, '1', 1, 'resBase', 1004, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8cf0139', '402883d429d9eec20129da4af8cf0133', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconCheckDis.gif', null, '1', 1, 'resBase', 1005, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df013a', '402883d429d9eec20129da4af8cf0133', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconChecked.gif', null, '1', 1, 'resBase', 1006, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df013b', '402883d429d9eec20129da4af8cf0133', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconCheckGray.gif', null, '1', 1, 'resBase', 1007, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df013c', '402883d429d9eec20129da4af8cf0133', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconFlag.gif', null, '1', 1, 'resBase', 1008, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df013d', '402883d429d9eec20129da4af8cf0133', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconGraph.gif', null, '1', 1, 'resBase', 1009, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df013e', '402883d429d9eec20129da4af8cf0133', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconSound.gif', null, '1', 1, 'resBase', 1010, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df013f', '402883d429d9eec20129da4af8cf0133', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconText.gif', null, '1', 1, 'resBase', 1011, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0140', '402883d429d9eec20129da4af8cf0133', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconTexts.gif', null, '1', 1, 'resBase', 1012, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0141', '402883d429d9eec20129da4af8cf0133', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconUncheckAll.gif', null, '1', 1, 'resBase', 1013, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0142', '402883d429d9eec20129da4af8cf0133', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconUncheckDis.gif', null, '1', 1, 'resBase', 1014, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0143', '402883d429d9eec20129da4af8cf0133', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconWrite1.gif', null, '1', 1, 'resBase', 1015, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0144', '402883d429d9eec20129da4af8cf0133', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\iconWrite2.gif', null, '1', 1, 'resBase', 1016, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0145', '402883d429d9eec20129da4af8cf0133', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\leaf.gif', null, '1', 1, 'resBase', 1017, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0146', '402883d429d9eec20129da4af8cf0133', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\line.gif', null, '1', 1, 'resBase', 1018, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0147', '402883d429d9eec20129da4af8cf0133', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\line1.gif', null, '1', 1, 'resBase', 1019, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0148', '402883d429d9eec20129da4af8cf0133', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\line2.gif', null, '1', 1, 'resBase', 1020, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0149', '402883d429d9eec20129da4af8cf0133', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\line3.gif', null, '1', 1, 'resBase', 1021, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df014a', '402883d429d9eec20129da4af8cf0133', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\line4.gif', null, '1', 1, 'resBase', 1022, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df014b', '402883d429d9eec20129da4af8cf0133', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\lock.gif', null, '1', 1, 'resBase', 1023, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df014c', '402883d429d9eec20129da4af8cf0133', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\minus.gif', null, '1', 1, 'resBase', 1024, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df014d', '402883d429d9eec20129da4af8cf0133', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\minus2.gif', null, '1', 1, 'resBase', 1025, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df014e', '402883d429d9eec20129da4af8cf0133', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\minus3.gif', null, '1', 1, 'resBase', 1026, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df014f', '402883d429d9eec20129da4af8cf0133', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\minus4.gif', null, '1', 1, 'resBase', 1027, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0150', '402883d429d9eec20129da4af8cf0133', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\minus5.gif', null, '1', 1, 'resBase', 1028, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0151', '402883d429d9eec20129da4af8cf0133', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\plus.gif', null, '1', 1, 'resBase', 1029, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0152', '402883d429d9eec20129da4af8cf0133', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\plus2.gif', null, '1', 1, 'resBase', 1030, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0153', '402883d429d9eec20129da4af8cf0133', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\plus3.gif', null, '1', 1, 'resBase', 1031, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0154', '402883d429d9eec20129da4af8cf0133', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\plus4.gif', null, '1', 1, 'resBase', 1032, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0155', '402883d429d9eec20129da4af8cf0133', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\plus5.gif', null, '1', 1, 'resBase', 1033, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0156', '402883d429d9eec20129da4af8cf0133', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\radio_off.gif', null, '1', 1, 'resBase', 1034, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0157', '402883d429d9eec20129da4af8cf0133', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_bluefolders\radio_on.gif', null, '1', 1, 'resBase', 1035, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0158', '402883d429d9eec20129da4af8bf0100', 'csh_books', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books', null, '0', 1, 'resBase', 10, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0159', '402883d429d9eec20129da4af8df0158', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\blank.gif', null, '1', 1, 'resBase', 1100, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df015a', '402883d429d9eec20129da4af8df0158', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\folderClosed.gif', null, '1', 1, 'resBase', 1101, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df015b', '402883d429d9eec20129da4af8df0158', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\folderOpen.gif', null, '1', 1, 'resBase', 1102, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df015c', '402883d429d9eec20129da4af8df0158', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\iconCheckAll.gif', null, '1', 1, 'resBase', 1103, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df015d', '402883d429d9eec20129da4af8df0158', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\iconCheckDis.gif', null, '1', 1, 'resBase', 1104, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df015e', '402883d429d9eec20129da4af8df0158', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\iconCheckGray.gif', null, '1', 1, 'resBase', 1105, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df015f', '402883d429d9eec20129da4af8df0158', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\iconUncheckAll.gif', null, '1', 1, 'resBase', 1106, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0160', '402883d429d9eec20129da4af8df0158', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\iconUncheckDis.gif', null, '1', 1, 'resBase', 1107, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0161', '402883d429d9eec20129da4af8df0158', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\leaf.gif', null, '1', 1, 'resBase', 1108, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0162', '402883d429d9eec20129da4af8df0158', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\line.gif', null, '1', 1, 'resBase', 1109, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0163', '402883d429d9eec20129da4af8df0158', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\line1.gif', null, '1', 1, 'resBase', 1110, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0164', '402883d429d9eec20129da4af8df0158', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\line2.gif', null, '1', 1, 'resBase', 1111, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0165', '402883d429d9eec20129da4af8df0158', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\line3.gif', null, '1', 1, 'resBase', 1112, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0166', '402883d429d9eec20129da4af8df0158', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\line4.gif', null, '1', 1, 'resBase', 1113, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0167', '402883d429d9eec20129da4af8df0158', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\lock.gif', null, '1', 1, 'resBase', 1114, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0168', '402883d429d9eec20129da4af8df0158', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\minus.gif', null, '1', 1, 'resBase', 1115, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0169', '402883d429d9eec20129da4af8df0158', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\minus2.gif', null, '1', 1, 'resBase', 1116, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df016a', '402883d429d9eec20129da4af8df0158', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\minus3.gif', null, '1', 1, 'resBase', 1117, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df016b', '402883d429d9eec20129da4af8df0158', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\minus4.gif', null, '1', 1, 'resBase', 1118, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df016c', '402883d429d9eec20129da4af8df0158', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\minus5.gif', null, '1', 1, 'resBase', 1119, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df016d', '402883d429d9eec20129da4af8df0158', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\plus.gif', null, '1', 1, 'resBase', 1120, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df016e', '402883d429d9eec20129da4af8df0158', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\plus2.gif', null, '1', 1, 'resBase', 1121, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df016f', '402883d429d9eec20129da4af8df0158', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\plus3.gif', null, '1', 1, 'resBase', 1122, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0170', '402883d429d9eec20129da4af8df0158', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\plus4.gif', null, '1', 1, 'resBase', 1123, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0171', '402883d429d9eec20129da4af8df0158', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\plus5.gif', null, '1', 1, 'resBase', 1124, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0172', '402883d429d9eec20129da4af8df0158', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\radio_off.gif', null, '1', 1, 'resBase', 1125, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0173', '402883d429d9eec20129da4af8df0158', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\radio_on.gif', null, '1', 1, 'resBase', 1126, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0174', '402883d429d9eec20129da4af8df0158', 'tombs.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\tombs.gif', null, '1', 1, 'resBase', 1127, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0175', '402883d429d9eec20129da4af8df0158', 'tombs_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_books\tombs_open.gif', null, '1', 1, 'resBase', 1128, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0176', '402883d429d9eec20129da4af8bf0100', 'csh_scbrblue', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue', null, '0', 1, 'resBase', 11, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8df0177', '402883d429d9eec20129da4af8df0176', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\blank.gif', null, '1', 1, 'resBase', 1200, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0178', '402883d429d9eec20129da4af8df0176', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\folderClosed.gif', null, '1', 1, 'resBase', 1201, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0179', '402883d429d9eec20129da4af8df0176', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\folderOpen.gif', null, '1', 1, 'resBase', 1202, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee017a', '402883d429d9eec20129da4af8df0176', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconCheckAll.gif', null, '1', 1, 'resBase', 1203, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee017b', '402883d429d9eec20129da4af8df0176', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconCheckDis.gif', null, '1', 1, 'resBase', 1204, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee017c', '402883d429d9eec20129da4af8df0176', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconChecked.gif', null, '1', 1, 'resBase', 1205, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee017d', '402883d429d9eec20129da4af8df0176', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconCheckGray.gif', null, '1', 1, 'resBase', 1206, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee017e', '402883d429d9eec20129da4af8df0176', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconFlag.gif', null, '1', 1, 'resBase', 1207, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee017f', '402883d429d9eec20129da4af8df0176', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconGraph.gif', null, '1', 1, 'resBase', 1208, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0180', '402883d429d9eec20129da4af8df0176', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconSound.gif', null, '1', 1, 'resBase', 1209, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0181', '402883d429d9eec20129da4af8df0176', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconText.gif', null, '1', 1, 'resBase', 1210, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0182', '402883d429d9eec20129da4af8df0176', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconTexts.gif', null, '1', 1, 'resBase', 1211, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0183', '402883d429d9eec20129da4af8df0176', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconUncheckAll.gif', null, '1', 1, 'resBase', 1212, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0184', '402883d429d9eec20129da4af8df0176', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconUncheckDis.gif', null, '1', 1, 'resBase', 1213, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0185', '402883d429d9eec20129da4af8df0176', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconWrite1.gif', null, '1', 1, 'resBase', 1214, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0186', '402883d429d9eec20129da4af8df0176', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\iconWrite2.gif', null, '1', 1, 'resBase', 1215, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0187', '402883d429d9eec20129da4af8df0176', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\leaf.gif', null, '1', 1, 'resBase', 1216, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0188', '402883d429d9eec20129da4af8df0176', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\line.gif', null, '1', 1, 'resBase', 1217, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0189', '402883d429d9eec20129da4af8df0176', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\line1.gif', null, '1', 1, 'resBase', 1218, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee018a', '402883d429d9eec20129da4af8df0176', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\line2.gif', null, '1', 1, 'resBase', 1219, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee018b', '402883d429d9eec20129da4af8df0176', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\line3.gif', null, '1', 1, 'resBase', 1220, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee018c', '402883d429d9eec20129da4af8df0176', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\line4.gif', null, '1', 1, 'resBase', 1221, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee018d', '402883d429d9eec20129da4af8df0176', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\lock.gif', null, '1', 1, 'resBase', 1222, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee018e', '402883d429d9eec20129da4af8df0176', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\minus.gif', null, '1', 1, 'resBase', 1223, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee018f', '402883d429d9eec20129da4af8df0176', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\minus2.gif', null, '1', 1, 'resBase', 1224, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0190', '402883d429d9eec20129da4af8df0176', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\minus3.gif', null, '1', 1, 'resBase', 1225, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0191', '402883d429d9eec20129da4af8df0176', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\minus4.gif', null, '1', 1, 'resBase', 1226, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0192', '402883d429d9eec20129da4af8df0176', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\minus5.gif', null, '1', 1, 'resBase', 1227, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0193', '402883d429d9eec20129da4af8df0176', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\plus.gif', null, '1', 1, 'resBase', 1228, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0194', '402883d429d9eec20129da4af8df0176', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\plus2.gif', null, '1', 1, 'resBase', 1229, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0195', '402883d429d9eec20129da4af8df0176', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\plus3.gif', null, '1', 1, 'resBase', 1230, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0196', '402883d429d9eec20129da4af8df0176', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\plus4.gif', null, '1', 1, 'resBase', 1231, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0197', '402883d429d9eec20129da4af8df0176', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\plus5.gif', null, '1', 1, 'resBase', 1232, 'cms/fileIco/gif.gif');
commit;
prompt 400 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0198', '402883d429d9eec20129da4af8df0176', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\radio_off.gif', null, '1', 1, 'resBase', 1233, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee0199', '402883d429d9eec20129da4af8df0176', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\radio_on.gif', null, '1', 1, 'resBase', 1234, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee019a', '402883d429d9eec20129da4af8df0176', 'safe_close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\safe_close.gif', null, '1', 1, 'resBase', 1235, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee019b', '402883d429d9eec20129da4af8df0176', 'safe_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_scbrblue\safe_open.gif', null, '1', 1, 'resBase', 1236, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee019c', '402883d429d9eec20129da4af8bf0100', 'csh_vista', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista', null, '0', 1, 'resBase', 12, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8ee019d', '402883d429d9eec20129da4af8ee019c', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\blank.gif', null, '1', 1, 'resBase', 1300, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe019e', '402883d429d9eec20129da4af8ee019c', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\folderClosed.gif', null, '1', 1, 'resBase', 1301, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe019f', '402883d429d9eec20129da4af8ee019c', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\folderOpen.gif', null, '1', 1, 'resBase', 1302, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a0', '402883d429d9eec20129da4af8ee019c', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconCheckAll.gif', null, '1', 1, 'resBase', 1303, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a1', '402883d429d9eec20129da4af8ee019c', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconCheckDis.gif', null, '1', 1, 'resBase', 1304, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a2', '402883d429d9eec20129da4af8ee019c', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconChecked.gif', null, '1', 1, 'resBase', 1305, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a3', '402883d429d9eec20129da4af8ee019c', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconCheckGray.gif', null, '1', 1, 'resBase', 1306, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a4', '402883d429d9eec20129da4af8ee019c', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconFlag.gif', null, '1', 1, 'resBase', 1307, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a5', '402883d429d9eec20129da4af8ee019c', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconGraph.gif', null, '1', 1, 'resBase', 1308, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a6', '402883d429d9eec20129da4af8ee019c', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconSound.gif', null, '1', 1, 'resBase', 1309, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a7', '402883d429d9eec20129da4af8ee019c', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconText.gif', null, '1', 1, 'resBase', 1310, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a8', '402883d429d9eec20129da4af8ee019c', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconTexts.gif', null, '1', 1, 'resBase', 1311, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01a9', '402883d429d9eec20129da4af8ee019c', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconUncheckAll.gif', null, '1', 1, 'resBase', 1312, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01aa', '402883d429d9eec20129da4af8ee019c', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconUncheckDis.gif', null, '1', 1, 'resBase', 1313, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01ab', '402883d429d9eec20129da4af8ee019c', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconWrite1.gif', null, '1', 1, 'resBase', 1314, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01ac', '402883d429d9eec20129da4af8ee019c', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\iconWrite2.gif', null, '1', 1, 'resBase', 1315, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01ad', '402883d429d9eec20129da4af8ee019c', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\leaf.gif', null, '1', 1, 'resBase', 1316, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01ae', '402883d429d9eec20129da4af8ee019c', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\line.gif', null, '1', 1, 'resBase', 1317, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01af', '402883d429d9eec20129da4af8ee019c', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\line1.gif', null, '1', 1, 'resBase', 1318, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b0', '402883d429d9eec20129da4af8ee019c', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\line2.gif', null, '1', 1, 'resBase', 1319, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b1', '402883d429d9eec20129da4af8ee019c', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\line3.gif', null, '1', 1, 'resBase', 1320, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b2', '402883d429d9eec20129da4af8ee019c', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\line4.gif', null, '1', 1, 'resBase', 1321, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b3', '402883d429d9eec20129da4af8ee019c', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\lock.gif', null, '1', 1, 'resBase', 1322, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b4', '402883d429d9eec20129da4af8ee019c', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\minus.gif', null, '1', 1, 'resBase', 1323, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b5', '402883d429d9eec20129da4af8ee019c', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\minus2.gif', null, '1', 1, 'resBase', 1324, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b6', '402883d429d9eec20129da4af8ee019c', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\minus3.gif', null, '1', 1, 'resBase', 1325, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b7', '402883d429d9eec20129da4af8ee019c', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\minus4.gif', null, '1', 1, 'resBase', 1326, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b8', '402883d429d9eec20129da4af8ee019c', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\minus5.gif', null, '1', 1, 'resBase', 1327, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01b9', '402883d429d9eec20129da4af8ee019c', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\plus.gif', null, '1', 1, 'resBase', 1328, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01ba', '402883d429d9eec20129da4af8ee019c', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\plus2.gif', null, '1', 1, 'resBase', 1329, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01bb', '402883d429d9eec20129da4af8ee019c', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\plus3.gif', null, '1', 1, 'resBase', 1330, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01bc', '402883d429d9eec20129da4af8ee019c', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\plus4.gif', null, '1', 1, 'resBase', 1331, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01bd', '402883d429d9eec20129da4af8ee019c', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\plus5.gif', null, '1', 1, 'resBase', 1332, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01be', '402883d429d9eec20129da4af8ee019c', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\radio_off.gif', null, '1', 1, 'resBase', 1333, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01bf', '402883d429d9eec20129da4af8ee019c', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_vista\radio_on.gif', null, '1', 1, 'resBase', 1334, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01c0', '402883d429d9eec20129da4af8bf0100', 'csh_winstyle', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle', null, '0', 1, 'resBase', 13, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01c1', '402883d429d9eec20129da4af8fe01c0', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\blank.gif', null, '1', 1, 'resBase', 1400, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8fe01c2', '402883d429d9eec20129da4af8fe01c0', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\folderClosed.gif', null, '1', 1, 'resBase', 1401, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c3', '402883d429d9eec20129da4af8fe01c0', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\folderOpen.gif', null, '1', 1, 'resBase', 1402, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c4', '402883d429d9eec20129da4af8fe01c0', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconCheckAll.gif', null, '1', 1, 'resBase', 1403, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c5', '402883d429d9eec20129da4af8fe01c0', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconCheckDis.gif', null, '1', 1, 'resBase', 1404, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c6', '402883d429d9eec20129da4af8fe01c0', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconChecked.gif', null, '1', 1, 'resBase', 1405, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c7', '402883d429d9eec20129da4af8fe01c0', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconCheckGray.gif', null, '1', 1, 'resBase', 1406, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c8', '402883d429d9eec20129da4af8fe01c0', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconFlag.gif', null, '1', 1, 'resBase', 1407, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01c9', '402883d429d9eec20129da4af8fe01c0', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconGraph.gif', null, '1', 1, 'resBase', 1408, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01ca', '402883d429d9eec20129da4af8fe01c0', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconSound.gif', null, '1', 1, 'resBase', 1409, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01cb', '402883d429d9eec20129da4af8fe01c0', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconText.gif', null, '1', 1, 'resBase', 1410, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01cc', '402883d429d9eec20129da4af8fe01c0', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconTexts.gif', null, '1', 1, 'resBase', 1411, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01cd', '402883d429d9eec20129da4af8fe01c0', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconUncheckAll.gif', null, '1', 1, 'resBase', 1412, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01ce', '402883d429d9eec20129da4af8fe01c0', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconUncheckDis.gif', null, '1', 1, 'resBase', 1413, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01cf', '402883d429d9eec20129da4af8fe01c0', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconWrite1.gif', null, '1', 1, 'resBase', 1414, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d0', '402883d429d9eec20129da4af8fe01c0', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\iconWrite2.gif', null, '1', 1, 'resBase', 1415, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d1', '402883d429d9eec20129da4af8fe01c0', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\leaf.gif', null, '1', 1, 'resBase', 1416, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d2', '402883d429d9eec20129da4af8fe01c0', 'leaves.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\leaves.gif', null, '1', 1, 'resBase', 1417, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d3', '402883d429d9eec20129da4af8fe01c0', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\line.gif', null, '1', 1, 'resBase', 1418, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d4', '402883d429d9eec20129da4af8fe01c0', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\line1.gif', null, '1', 1, 'resBase', 1419, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d5', '402883d429d9eec20129da4af8fe01c0', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\line2.gif', null, '1', 1, 'resBase', 1420, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d6', '402883d429d9eec20129da4af8fe01c0', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\line3.gif', null, '1', 1, 'resBase', 1421, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af90d01d7', '402883d429d9eec20129da4af8fe01c0', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\line4.gif', null, '1', 1, 'resBase', 1422, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01d8', '402883d429d9eec20129da4af8fe01c0', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\lock.gif', null, '1', 1, 'resBase', 1423, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01d9', '402883d429d9eec20129da4af8fe01c0', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\minus.gif', null, '1', 1, 'resBase', 1424, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01da', '402883d429d9eec20129da4af8fe01c0', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\minus2.gif', null, '1', 1, 'resBase', 1425, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01db', '402883d429d9eec20129da4af8fe01c0', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\minus3.gif', null, '1', 1, 'resBase', 1426, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01dc', '402883d429d9eec20129da4af8fe01c0', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\minus4.gif', null, '1', 1, 'resBase', 1427, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01dd', '402883d429d9eec20129da4af8fe01c0', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\minus5.gif', null, '1', 1, 'resBase', 1428, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af89000ea', null, 'resBase', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase', null, '0', null, 'resBase', 0, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000eb', '402883d429d9eec20129da4af89000ea', 'gif.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\gif.gif', null, '1', 1, 'resBase', 100, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000ec', '402883d429d9eec20129da4af89000ea', 'media', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\media', null, '0', 1, 'resBase', 1, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000ed', '402883d429d9eec20129da4af8b000ec', 'anhuiMap.swf', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\media\anhuiMap.swf', null, '1', 70, 'resBase', 200, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000ee', '402883d429d9eec20129da4af8b000ec', 'anhuiMap1.swf', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\media\anhuiMap1.swf', null, '1', 72, 'resBase', 201, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000ef', '402883d429d9eec20129da4af8b000ec', 'anhuiMap2.swf', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\media\anhuiMap2.swf', null, '1', 23, 'resBase', 202, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f0', '402883d429d9eec20129da4af8b000ec', 'anhuiMap3.swf', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\media\anhuiMap3.swf', null, '1', 70, 'resBase', 203, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f1', '402883d429d9eec20129da4af89000ea', 'openingProject.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\openingProject.gif', null, '1', 3, 'resBase', 102, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f2', '402883d429d9eec20129da4af89000ea', 'plug-in', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in', null, '0', 1, 'resBase', 2, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f3', '402883d429d9eec20129da4af8b000f2', 'dateandtime', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dateandtime', null, '0', 1, 'resBase', 3, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f4', '402883d429d9eec20129da4af8b000f3', 'dateandtime.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dateandtime\dateandtime.js', null, '1', 1, 'resBase', 400, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f5', '402883d429d9eec20129da4af8b000f2', 'dhtmlxTree', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree', null, '0', 1, 'resBase', 4, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f6', '402883d429d9eec20129da4af8b000f5', 'codebase', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase', null, '0', 1, 'resBase', 5, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f7', '402883d429d9eec20129da4af8b000f6', 'dhtmlxcommon.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\dhtmlxcommon.js', null, '1', 18, 'resBase', 600, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f8', '402883d429d9eec20129da4af8b000f6', 'dhtmlxtree.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\dhtmlxtree.css', null, '1', 3, 'resBase', 601, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000f9', '402883d429d9eec20129da4af8b000f6', 'dhtmlxtree.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\dhtmlxtree.js', null, '1', 65, 'resBase', 602, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8b000fa', '402883d429d9eec20129da4af8b000f6', 'ext', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\ext', null, '0', 1, 'resBase', 6, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf00fb', '402883d429d9eec20129da4af8b000fa', 'dhtmlxtree_dragin.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\ext\dhtmlxtree_dragin.js', null, '1', 3, 'resBase', 700, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af8bf00fc', '402883d429d9eec20129da4af8b000fa', 'dhtmlxtree_ed.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\ext\dhtmlxtree_ed.js', null, '1', 4, 'resBase', 701, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01ee', '402883d429d9eec20129da4af91d01e5', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\iconText.gif', null, '1', 1, 'resBase', 1508, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01ef', '402883d429d9eec20129da4af91d01e5', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\iconUncheckAll.gif', null, '1', 1, 'resBase', 1509, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f0', '402883d429d9eec20129da4af91d01e5', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\iconUncheckDis.gif', null, '1', 1, 'resBase', 1510, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f1', '402883d429d9eec20129da4af91d01e5', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\leaf.gif', null, '1', 1, 'resBase', 1511, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f2', '402883d429d9eec20129da4af91d01e5', 'leaf2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\leaf2.gif', null, '1', 1, 'resBase', 1512, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f3', '402883d429d9eec20129da4af91d01e5', 'leaf3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\leaf3.gif', null, '1', 1, 'resBase', 1513, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f4', '402883d429d9eec20129da4af91d01e5', 'leaf_2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\leaf_2.gif', null, '1', 1, 'resBase', 1514, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f5', '402883d429d9eec20129da4af91d01e5', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\line.gif', null, '1', 1, 'resBase', 1515, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f6', '402883d429d9eec20129da4af91d01e5', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\line1.gif', null, '1', 1, 'resBase', 1516, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f7', '402883d429d9eec20129da4af91d01e5', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\line2.gif', null, '1', 1, 'resBase', 1517, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f8', '402883d429d9eec20129da4af91d01e5', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\line3.gif', null, '1', 1, 'resBase', 1518, 'cms/fileIco/gif.gif');
commit;
prompt 500 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01f9', '402883d429d9eec20129da4af91d01e5', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\line4.gif', null, '1', 1, 'resBase', 1519, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01fa', '402883d429d9eec20129da4af91d01e5', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\lock.gif', null, '1', 1, 'resBase', 1520, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01fb', '402883d429d9eec20129da4af91d01e5', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\minus.gif', null, '1', 1, 'resBase', 1521, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01fc', '402883d429d9eec20129da4af91d01e5', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\minus2.gif', null, '1', 1, 'resBase', 1522, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01fd', '402883d429d9eec20129da4af91d01e5', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\minus3.gif', null, '1', 1, 'resBase', 1523, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01fe', '402883d429d9eec20129da4af91d01e5', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\minus4.gif', null, '1', 1, 'resBase', 1524, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01ff', '402883d429d9eec20129da4af91d01e5', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\minus5.gif', null, '1', 1, 'resBase', 1525, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0200', '402883d429d9eec20129da4af91d01e5', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\plus.gif', null, '1', 1, 'resBase', 1526, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0201', '402883d429d9eec20129da4af91d01e5', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\plus2.gif', null, '1', 1, 'resBase', 1527, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0202', '402883d429d9eec20129da4af91d01e5', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\plus3.gif', null, '1', 1, 'resBase', 1528, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0203', '402883d429d9eec20129da4af91d01e5', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\plus4.gif', null, '1', 1, 'resBase', 1529, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0204', '402883d429d9eec20129da4af91d01e5', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\plus5.gif', null, '1', 1, 'resBase', 1530, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0205', '402883d429d9eec20129da4af91d01e5', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\radio_off.gif', null, '1', 1, 'resBase', 1531, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0206', '402883d429d9eec20129da4af91d01e5', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\radio_on.gif', null, '1', 1, 'resBase', 1532, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0207', '402883d429d9eec20129da4af91d01e5', 'tombs.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\tombs.gif', null, '1', 1, 'resBase', 1533, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903ce', '402883d429d9eec20129da4af9c903bb', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\line1.gif', null, '1', 1, 'resBase', 5218, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903cf', '402883d429d9eec20129da4af9c903bb', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\line2.gif', null, '1', 1, 'resBase', 5219, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d0', '402883d429d9eec20129da4af9c903bb', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\line3.gif', null, '1', 1, 'resBase', 5220, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d1', '402883d429d9eec20129da4af9c903bb', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\line4.gif', null, '1', 1, 'resBase', 5221, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d2', '402883d429d9eec20129da4af9c903bb', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\lock.gif', null, '1', 1, 'resBase', 5222, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d3', '402883d429d9eec20129da4af9c903bb', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\minus.gif', null, '1', 1, 'resBase', 5223, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d4', '402883d429d9eec20129da4af9c903bb', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\minus2.gif', null, '1', 1, 'resBase', 5224, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d5', '402883d429d9eec20129da4af9c903bb', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\minus3.gif', null, '1', 1, 'resBase', 5225, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d6', '402883d429d9eec20129da4af9c903bb', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\minus4.gif', null, '1', 1, 'resBase', 5226, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9c903d7', '402883d429d9eec20129da4af9c903bb', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\minus5.gif', null, '1', 1, 'resBase', 5227, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903d8', '402883d429d9eec20129da4af9c903bb', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\plus.gif', null, '1', 1, 'resBase', 5228, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429e88fa00129e898f88f000f', null, 'loadTemplate', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\loadTemplate', null, '0', null, 'loadTemplate', 0, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429e88fa00129e898f88f0010', '402883d429e88fa00129e898f88f000f', 'foot.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\loadTemplate\foot.ftl', null, '1', 1, 'loadTemplate', 100, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429e88fa00129e898f88f0011', '402883d429e88fa00129e898f88f000f', 'left.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\loadTemplate\left.ftl', null, '1', 1, 'loadTemplate', 101, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429e88fa00129e898f88f0012', '402883d429e88fa00129e898f88f000f', 'right.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\loadTemplate\right.ftl', null, '1', 1, 'loadTemplate', 102, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429e88fa00129e898f88f0013', '402883d429e88fa00129e898f88f000f', 'top.ftl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\loadTemplate\top.ftl', null, '1', 1, 'loadTemplate', 103, 'cms/fileIco/leaf.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01de', '402883d429d9eec20129da4af8fe01c0', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\plus.gif', null, '1', 1, 'resBase', 1429, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01df', '402883d429d9eec20129da4af8fe01c0', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\plus2.gif', null, '1', 1, 'resBase', 1430, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e0', '402883d429d9eec20129da4af8fe01c0', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\plus3.gif', null, '1', 1, 'resBase', 1431, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e1', '402883d429d9eec20129da4af8fe01c0', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\plus4.gif', null, '1', 1, 'resBase', 1432, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e2', '402883d429d9eec20129da4af8fe01c0', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\plus5.gif', null, '1', 1, 'resBase', 1433, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e3', '402883d429d9eec20129da4af8fe01c0', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\radio_off.gif', null, '1', 1, 'resBase', 1434, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e4', '402883d429d9eec20129da4af8fe01c0', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_winstyle\radio_on.gif', null, '1', 1, 'resBase', 1435, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e5', '402883d429d9eec20129da4af8bf0100', 'csh_yellowbooks', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks', null, '0', 1, 'resBase', 14, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e6', '402883d429d9eec20129da4af91d01e5', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\blank.gif', null, '1', 1, 'resBase', 1500, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e7', '402883d429d9eec20129da4af91d01e5', 'books_close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\books_close.gif', null, '1', 1, 'resBase', 1501, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e8', '402883d429d9eec20129da4af91d01e5', 'books_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\books_open.gif', null, '1', 1, 'resBase', 1502, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01e9', '402883d429d9eec20129da4af91d01e5', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\folderClosed.gif', null, '1', 1, 'resBase', 1503, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01ea', '402883d429d9eec20129da4af91d01e5', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\folderOpen.gif', null, '1', 1, 'resBase', 1504, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01eb', '402883d429d9eec20129da4af91d01e5', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\iconCheckAll.gif', null, '1', 1, 'resBase', 1505, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01ec', '402883d429d9eec20129da4af91d01e5', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\iconCheckDis.gif', null, '1', 1, 'resBase', 1506, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d01ed', '402883d429d9eec20129da4af91d01e5', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\iconCheckGray.gif', null, '1', 1, 'resBase', 1507, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0219', '402883d429d9eec20129da4af8bf0100', 'line4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line4_rtl.gif', null, '1', 1, 'resBase', 825, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d021a', '402883d429d9eec20129da4af8bf0100', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\lock.gif', null, '1', 1, 'resBase', 826, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d021b', '402883d429d9eec20129da4af8bf0100', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus.gif', null, '1', 1, 'resBase', 827, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d021c', '402883d429d9eec20129da4af8bf0100', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus2.gif', null, '1', 1, 'resBase', 828, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d021d', '402883d429d9eec20129da4af8bf0100', 'minus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus2_rtl.gif', null, '1', 1, 'resBase', 829, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d021e', '402883d429d9eec20129da4af8bf0100', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus3.gif', null, '1', 1, 'resBase', 830, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d021f', '402883d429d9eec20129da4af8bf0100', 'minus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus3_rtl.gif', null, '1', 1, 'resBase', 831, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0220', '402883d429d9eec20129da4af8bf0100', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus4.gif', null, '1', 1, 'resBase', 832, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0221', '402883d429d9eec20129da4af8bf0100', 'minus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus4_rtl.gif', null, '1', 1, 'resBase', 833, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0222', '402883d429d9eec20129da4af8bf0100', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus5.gif', null, '1', 1, 'resBase', 834, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0223', '402883d429d9eec20129da4af8bf0100', 'minus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus5_rtl.gif', null, '1', 1, 'resBase', 835, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0224', '402883d429d9eec20129da4af8bf0100', 'minus_ar.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\minus_ar.gif', null, '1', 1, 'resBase', 836, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0225', '402883d429d9eec20129da4af8bf0100', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus.gif', null, '1', 1, 'resBase', 837, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0226', '402883d429d9eec20129da4af8bf0100', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus2.gif', null, '1', 1, 'resBase', 838, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0227', '402883d429d9eec20129da4af8bf0100', 'plus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus2_rtl.gif', null, '1', 1, 'resBase', 839, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0228', '402883d429d9eec20129da4af8bf0100', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus3.gif', null, '1', 1, 'resBase', 840, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0229', '402883d429d9eec20129da4af8bf0100', 'plus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus3_rtl.gif', null, '1', 1, 'resBase', 841, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d022a', '402883d429d9eec20129da4af8bf0100', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus4.gif', null, '1', 1, 'resBase', 842, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d022b', '402883d429d9eec20129da4af8bf0100', 'plus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus4_rtl.gif', null, '1', 1, 'resBase', 843, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d022c', '402883d429d9eec20129da4af8bf0100', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus5.gif', null, '1', 1, 'resBase', 844, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d022d', '402883d429d9eec20129da4af8bf0100', 'plus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus5_rtl.gif', null, '1', 1, 'resBase', 845, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d022e', '402883d429d9eec20129da4af8bf0100', 'plus_ar.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\plus_ar.gif', null, '1', 1, 'resBase', 846, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d022f', '402883d429d9eec20129da4af8bf0100', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\radio_off.gif', null, '1', 1, 'resBase', 847, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0230', '402883d429d9eec20129da4af8bf0100', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\radio_on.gif', null, '1', 1, 'resBase', 848, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0231', '402883d429d9eec20129da4af8b000f5', 'doc', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc', null, '0', 1, 'resBase', 15, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0232', '402883d429d9eec20129da4af92d0231', '!!doc.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\!!doc.xml', null, '1', 1, 'resBase', 1600, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0233', '402883d429d9eec20129da4af92d0231', 'alpha.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\alpha.html', null, '1', 62, 'resBase', 1601, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0234', '402883d429d9eec20129da4af92d0231', 'common', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\common', null, '0', 1, 'resBase', 16, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ff', '402883d429d9eec20129da4af96b02de', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus5.gif', null, '1', 1, 'resBase', 3932, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0300', '402883d429d9eec20129da4af96b02de', 'minus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus5_rtl.gif', null, '1', 1, 'resBase', 3933, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0301', '402883d429d9eec20129da4af96b02de', 'minus_ar.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus_ar.gif', null, '1', 1, 'resBase', 3934, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0302', '402883d429d9eec20129da4af96b02de', 'open2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\open2.gif', null, '1', 1, 'resBase', 3935, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0303', '402883d429d9eec20129da4af96b02de', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus.gif', null, '1', 1, 'resBase', 3936, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0304', '402883d429d9eec20129da4af96b02de', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus2.gif', null, '1', 1, 'resBase', 3937, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0305', '402883d429d9eec20129da4af96b02de', 'plus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus2_rtl.gif', null, '1', 1, 'resBase', 3938, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0235', '402883d429d9eec20129da4af92d0234', 'code.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\common\code.gif', null, '1', 1, 'resBase', 1700, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0236', '402883d429d9eec20129da4af92d0234', 'image001.jpg', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\common\image001.jpg', null, '1', 25, 'resBase', 1701, 'cms/fileIco/jpg.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0237', '402883d429d9eec20129da4af92d0234', 'styles.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\common\styles.css', null, '1', 4, 'resBase', 1702, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0238', '402883d429d9eec20129da4af92d0231', 'dyn_loading.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\dyn_loading.html', null, '1', 13, 'resBase', 1603, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0239', '402883d429d9eec20129da4af92d0231', 'events.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\events.html', null, '1', 14, 'resBase', 1604, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d023a', '402883d429d9eec20129da4af92d0231', 'faq.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\faq.html', null, '1', 22, 'resBase', 1605, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d023b', '402883d429d9eec20129da4af92d0231', 'guide.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\guide.html', null, '1', 39, 'resBase', 1606, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d023c', '402883d429d9eec20129da4af92d0231', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\index.html', null, '1', 1, 'resBase', 1607, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d023d', '402883d429d9eec20129da4af92d0231', 'objects.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\objects.html', null, '1', 2, 'resBase', 1608, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d023e', '402883d429d9eec20129da4af92d0231', 'ogroup.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\ogroup.html', null, '1', 56, 'resBase', 1609, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d023f', '402883d429d9eec20129da4af92d0231', 'special.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\special.html', null, '1', 3, 'resBase', 1610, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0240', '402883d429d9eec20129da4af92d0231', 'tgroup.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\tgroup.html', null, '1', 3, 'resBase', 1611, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0241', '402883d429d9eec20129da4af92d0231', 'trouble.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\doc\trouble.html', null, '1', 2, 'resBase', 1612, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0242', '402883d429d9eec20129da4af8b000f5', 'imgs', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs', null, '0', 1, 'resBase', 17, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0243', '402883d429d9eec20129da4af92d0242', 'csh_bluebooks', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_bluebooks', null, '0', 1, 'resBase', 18, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0244', '402883d429d9eec20129da4af92d0243', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_bluebooks\Thumbs.db', null, '1', 8, 'resBase', 1900, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0245', '402883d429d9eec20129da4af92d0242', 'csh_bluefolders', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_bluefolders', null, '0', 1, 'resBase', 19, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0246', '402883d429d9eec20129da4af92d0245', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_bluefolders\Thumbs.db', null, '1', 8, 'resBase', 2000, 'cms/fileIco/unknow.gif');
commit;
prompt 600 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0247', '402883d429d9eec20129da4af92d0242', 'csh_books', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_books', null, '0', 1, 'resBase', 20, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0248', '402883d429d9eec20129da4af92d0247', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_books\Thumbs.db', null, '1', 9, 'resBase', 2100, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0249', '402883d429d9eec20129da4af92d0242', 'csh_scbrblue', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_scbrblue', null, '0', 1, 'resBase', 21, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c024a', '402883d429d9eec20129da4af92d0249', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_scbrblue\Thumbs.db', null, '1', 8, 'resBase', 2200, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c024b', '402883d429d9eec20129da4af92d0242', 'csh_vista', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista', null, '0', 1, 'resBase', 22, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c024c', '402883d429d9eec20129da4af93c024b', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\blank.gif', null, '1', 1, 'resBase', 2300, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c024d', '402883d429d9eec20129da4af93c024b', 'book.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\book.gif', null, '1', 1, 'resBase', 2301, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c024e', '402883d429d9eec20129da4af93c024b', 'book_titel.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\book_titel.gif', null, '1', 1, 'resBase', 2302, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c024f', '402883d429d9eec20129da4af93c024b', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\folderClosed.gif', null, '1', 1, 'resBase', 2303, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0250', '402883d429d9eec20129da4af93c024b', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\folderOpen.gif', null, '1', 1, 'resBase', 2304, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0251', '402883d429d9eec20129da4af93c024b', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconCheckAll.gif', null, '1', 1, 'resBase', 2305, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0252', '402883d429d9eec20129da4af93c024b', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconCheckDis.gif', null, '1', 1, 'resBase', 2306, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0253', '402883d429d9eec20129da4af93c024b', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconChecked.gif', null, '1', 1, 'resBase', 2307, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0254', '402883d429d9eec20129da4af93c024b', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconCheckGray.gif', null, '1', 1, 'resBase', 2308, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0255', '402883d429d9eec20129da4af93c024b', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconFlag.gif', null, '1', 1, 'resBase', 2309, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0256', '402883d429d9eec20129da4af93c024b', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconGraph.gif', null, '1', 1, 'resBase', 2310, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0257', '402883d429d9eec20129da4af93c024b', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconSound.gif', null, '1', 1, 'resBase', 2311, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0258', '402883d429d9eec20129da4af93c024b', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconText.gif', null, '1', 1, 'resBase', 2312, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0259', '402883d429d9eec20129da4af93c024b', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconTexts.gif', null, '1', 1, 'resBase', 2313, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c025a', '402883d429d9eec20129da4af93c024b', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconUncheckAll.gif', null, '1', 1, 'resBase', 2314, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c025b', '402883d429d9eec20129da4af93c024b', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconUncheckDis.gif', null, '1', 1, 'resBase', 2315, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c025c', '402883d429d9eec20129da4af93c024b', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconWrite1.gif', null, '1', 1, 'resBase', 2316, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c025d', '402883d429d9eec20129da4af93c024b', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\iconWrite2.gif', null, '1', 1, 'resBase', 2317, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c025e', '402883d429d9eec20129da4af93c024b', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\leaf.gif', null, '1', 1, 'resBase', 2318, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c025f', '402883d429d9eec20129da4af93c024b', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\line1.gif', null, '1', 1, 'resBase', 2319, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0260', '402883d429d9eec20129da4af93c024b', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\line2.gif', null, '1', 1, 'resBase', 2320, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0261', '402883d429d9eec20129da4af93c024b', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\line3.gif', null, '1', 1, 'resBase', 2321, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0262', '402883d429d9eec20129da4af93c024b', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\line4.gif', null, '1', 1, 'resBase', 2322, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0263', '402883d429d9eec20129da4af93c024b', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\lock.gif', null, '1', 1, 'resBase', 2323, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0264', '402883d429d9eec20129da4af93c024b', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\minus.gif', null, '1', 1, 'resBase', 2324, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0265', '402883d429d9eec20129da4af93c024b', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\minus2.gif', null, '1', 1, 'resBase', 2325, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0266', '402883d429d9eec20129da4af93c024b', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\minus3.gif', null, '1', 1, 'resBase', 2326, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0267', '402883d429d9eec20129da4af93c024b', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\minus4.gif', null, '1', 1, 'resBase', 2327, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0268', '402883d429d9eec20129da4af93c024b', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\minus5.gif', null, '1', 1, 'resBase', 2328, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0269', '402883d429d9eec20129da4af93c024b', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\plus.gif', null, '1', 1, 'resBase', 2329, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c026a', '402883d429d9eec20129da4af93c024b', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\plus2.gif', null, '1', 1, 'resBase', 2330, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c026b', '402883d429d9eec20129da4af93c024b', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\plus3.gif', null, '1', 1, 'resBase', 2331, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c026c', '402883d429d9eec20129da4af93c024b', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\plus4.gif', null, '1', 1, 'resBase', 2332, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c026d', '402883d429d9eec20129da4af93c024b', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\plus5.gif', null, '1', 1, 'resBase', 2333, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c026e', '402883d429d9eec20129da4af93c024b', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\radio_off.gif', null, '1', 1, 'resBase', 2334, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c026f', '402883d429d9eec20129da4af93c024b', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\radio_on.gif', null, '1', 1, 'resBase', 2335, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0270', '402883d429d9eec20129da4af93c024b', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_vista\Thumbs.db', null, '1', 45, 'resBase', 2336, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0271', '402883d429d9eec20129da4af92d0242', 'csh_winstyle', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_winstyle', null, '0', 1, 'resBase', 23, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0272', '402883d429d9eec20129da4af93c0271', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_winstyle\Thumbs.db', null, '1', 8, 'resBase', 2400, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0273', '402883d429d9eec20129da4af92d0242', 'csh_yellowbooks', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_yellowbooks', null, '0', 1, 'resBase', 24, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0274', '402883d429d9eec20129da4af93c0273', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\csh_yellowbooks\Thumbs.db', null, '1', 8, 'resBase', 2500, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0275', '402883d429d9eec20129da4af92d0242', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\imgs\Thumbs.db', null, '1', 36, 'resBase', 1807, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0276', '402883d429d9eec20129da4af8b000f5', 'readme.txt', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\readme.txt', null, '1', 1, 'resBase', 503, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0277', '402883d429d9eec20129da4af8b000f5', 'samples', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples', null, '0', 1, 'resBase', 25, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0278', '402883d429d9eec20129da4af93c0277', 'appearance', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance', null, '0', 1, 'resBase', 26, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af93c0279', '402883d429d9eec20129da4af93c0278', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\index.html', null, '1', 1, 'resBase', 2700, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c027a', '402883d429d9eec20129da4af93c0278', 'pro_path.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\pro_path.html', null, '1', 8, 'resBase', 2701, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c027b', '402883d429d9eec20129da4af93c0278', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree.xml', null, '1', 6, 'resBase', 2702, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c027c', '402883d429d9eec20129da4af93c0278', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree3.xml', null, '1', 6, 'resBase', 2703, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c027d', '402883d429d9eec20129da4af93c0278', 'tree4.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree4.xml', null, '1', 3, 'resBase', 2704, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c027e', '402883d429d9eec20129da4af93c0278', 'tree_a.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_a.xml', null, '1', 2, 'resBase', 2705, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c027f', '402883d429d9eec20129da4af93c0278', 'tree_b.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_b.xml', null, '1', 2, 'resBase', 2706, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0280', '402883d429d9eec20129da4af93c0278', 'tree_design.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_design.html', null, '1', 10, 'resBase', 2707, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0281', '402883d429d9eec20129da4af93c0278', 'tree_iconset.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_iconset.html', null, '1', 8, 'resBase', 2708, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0282', '402883d429d9eec20129da4af93c0278', 'tree_ml.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_ml.xml', null, '1', 12, 'resBase', 2709, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0283', '402883d429d9eec20129da4af93c0278', 'tree_ol.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_ol.xml', null, '1', 3, 'resBase', 2710, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0284', '402883d429d9eec20129da4af93c0278', 'tree_p.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_p.xml', null, '1', 7, 'resBase', 2711, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0285', '402883d429d9eec20129da4af93c0278', 'tree_st.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_st.xml', null, '1', 6, 'resBase', 2712, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0286', '402883d429d9eec20129da4af93c0278', 'tree_text_image.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_text_image.html', null, '1', 11, 'resBase', 2713, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0287', '402883d429d9eec20129da4af93c0278', 'tree_to.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\appearance\tree_to.xml', null, '1', 6, 'resBase', 2714, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0288', '402883d429d9eec20129da4af93c0277', 'checkboxes', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes', null, '0', 1, 'resBase', 27, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0289', '402883d429d9eec20129da4af94c0288', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\index.html', null, '1', 1, 'resBase', 2800, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c028a', '402883d429d9eec20129da4af94c0288', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\tree.xml', null, '1', 6, 'resBase', 2801, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c028b', '402883d429d9eec20129da4af94c0288', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\tree3.xml', null, '1', 6, 'resBase', 2802, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c028c', '402883d429d9eec20129da4af94c0288', 'tree_checkboxes.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\tree_checkboxes.html', null, '1', 10, 'resBase', 2803, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c028d', '402883d429d9eec20129da4af94c0288', 'tree_cl.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\tree_cl.xml', null, '1', 3, 'resBase', 2804, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c028e', '402883d429d9eec20129da4af94c0288', 'tree_mixed.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\tree_mixed.xml', null, '1', 3, 'resBase', 2805, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c028f', '402883d429d9eec20129da4af94c0288', 'tree_radio.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\checkboxes\tree_radio.xml', null, '1', 2, 'resBase', 2806, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0290', '402883d429d9eec20129da4af93c0277', 'common', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common', null, '0', 1, 'resBase', 28, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0291', '402883d429d9eec20129da4af94c0290', 'bg_code.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\bg_code.gif', null, '1', 1, 'resBase', 2900, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0292', '402883d429d9eec20129da4af94c0290', 'config.php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\config.php', null, '1', 1, 'resBase', 2901, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0293', '402883d429d9eec20129da4af94c0290', 'demo_notification.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\demo_notification.tpl', null, '1', 1, 'resBase', 2902, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0294', '402883d429d9eec20129da4af94c0290', 'dhtmlxaccordion_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxaccordion_icon.gif', null, '1', 1, 'resBase', 2903, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0295', '402883d429d9eec20129da4af94c0290', 'dhtmlxajax_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxajax_icon.gif', null, '1', 1, 'resBase', 2904, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0296', '402883d429d9eec20129da4af94c0290', 'dhtmlxcalendar_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxcalendar_icon.gif', null, '1', 1, 'resBase', 2905, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0297', '402883d429d9eec20129da4af94c0290', 'dhtmlxcolorpicker_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxcolorpicker_icon.gif', null, '1', 1, 'resBase', 2906, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0298', '402883d429d9eec20129da4af94c0290', 'dhtmlxcombo_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxcombo_icon.gif', null, '1', 1, 'resBase', 2907, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c0299', '402883d429d9eec20129da4af94c0290', 'dhtmlxcommon_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxcommon_icon.gif', null, '1', 1, 'resBase', 2908, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c029a', '402883d429d9eec20129da4af94c0290', 'dhtmlxdataprocessor_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxdataprocessor_icon.gif', null, '1', 1, 'resBase', 2909, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c029b', '402883d429d9eec20129da4af94c0290', 'dhtmlxeditor_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxeditor_icon.gif', null, '1', 1, 'resBase', 2910, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c029c', '402883d429d9eec20129da4af94c0290', 'dhtmlxfolders_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxfolders_icon.gif', null, '1', 1, 'resBase', 2911, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c029d', '402883d429d9eec20129da4af94c0290', 'dhtmlxgantt_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxgantt_icon.gif', null, '1', 1, 'resBase', 2912, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c029e', '402883d429d9eec20129da4af94c0290', 'dhtmlxgrid_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxgrid_icon.gif', null, '1', 1, 'resBase', 2913, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c029f', '402883d429d9eec20129da4af94c0290', 'dhtmlxlayout_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxlayout_icon.gif', null, '1', 1, 'resBase', 2914, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a0', '402883d429d9eec20129da4af94c0290', 'dhtmlxmenu_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxmenu_icon.gif', null, '1', 1, 'resBase', 2915, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a1', '402883d429d9eec20129da4af94c0290', 'dhtmlxslider_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxslider_icon.gif', null, '1', 1, 'resBase', 2916, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a2', '402883d429d9eec20129da4af94c0290', 'dhtmlxtabbar_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxtabbar_icon.gif', null, '1', 1, 'resBase', 2917, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a3', '402883d429d9eec20129da4af94c0290', 'dhtmlxtoolbar_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxtoolbar_icon.gif', null, '1', 1, 'resBase', 2918, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a4', '402883d429d9eec20129da4af94c0290', 'dhtmlxtreegrid_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxtreegrid_icon.gif', null, '1', 1, 'resBase', 2919, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a5', '402883d429d9eec20129da4af94c0290', 'dhtmlxtree_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxtree_icon.gif', null, '1', 1, 'resBase', 2920, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a6', '402883d429d9eec20129da4af94c0290', 'dhtmlxvault_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxvault_icon.gif', null, '1', 1, 'resBase', 2921, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a7', '402883d429d9eec20129da4af94c0290', 'dhtmlxwebmenu_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxwebmenu_icon.gif', null, '1', 1, 'resBase', 2922, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a8', '402883d429d9eec20129da4af94c0290', 'dhtmlxwebtoolbar_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxwebtoolbar_icon.gif', null, '1', 1, 'resBase', 2923, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02a9', '402883d429d9eec20129da4af94c0290', 'dhtmlxwindows_icon.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\dhtmlxwindows_icon.gif', null, '1', 1, 'resBase', 2924, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02aa', '402883d429d9eec20129da4af94c0290', 'footer.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\footer.tpl', null, '1', 1, 'resBase', 2925, 'cms/fileIco/unknow.gif');
commit;
prompt 700 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02ab', '402883d429d9eec20129da4af94c0290', 'header.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\header.tpl', null, '1', 1, 'resBase', 2926, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02ac', '402883d429d9eec20129da4af94c0290', 'index_prod.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\index_prod.tpl', null, '1', 1, 'resBase', 2927, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af94c02ad', '402883d429d9eec20129da4af94c0290', 'index_sub.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\index_sub.tpl', null, '1', 1, 'resBase', 2928, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02ae', '402883d429d9eec20129da4af94c0290', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\leaf.gif', null, '1', 1, 'resBase', 2929, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02af', '402883d429d9eec20129da4af94c0290', 'leaf_new.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\leaf_new.gif', null, '1', 1, 'resBase', 2930, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b0', '402883d429d9eec20129da4af94c0290', 'leaf_pro.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\leaf_pro.gif', null, '1', 1, 'resBase', 2931, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b1', '402883d429d9eec20129da4af94c0290', 'leaf_pro_new.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\leaf_pro_new.gif', null, '1', 1, 'resBase', 2932, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b2', '402883d429d9eec20129da4af94c0290', 'logo_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\logo_bg.gif', null, '1', 8, 'resBase', 2933, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b3', '402883d429d9eec20129da4af94c0290', 'notproedition.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\notproedition.tpl', null, '1', 1, 'resBase', 2934, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b4', '402883d429d9eec20129da4af94c0290', 'sample_close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\sample_close.gif', null, '1', 1, 'resBase', 2935, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b5', '402883d429d9eec20129da4af94c0290', 'style.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\style.css', null, '1', 3, 'resBase', 2936, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b6', '402883d429d9eec20129da4af94c0290', 'supress_errors_block.tpl', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\supress_errors_block.tpl', null, '1', 1, 'resBase', 2937, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b7', '402883d429d9eec20129da4af94c0290', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\common\Thumbs.db', null, '1', 8, 'resBase', 2938, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b8', '402883d429d9eec20129da4af93c0277', 'context_menu', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\context_menu', null, '0', 1, 'resBase', 29, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02b9', '402883d429d9eec20129da4af95c02b8', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\context_menu\index.html', null, '1', 1, 'resBase', 3000, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02ba', '402883d429d9eec20129da4af95c02b8', 'pro_drag_menu.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\context_menu\pro_drag_menu.html', null, '1', 10, 'resBase', 3001, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02bb', '402883d429d9eec20129da4af95c02b8', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\context_menu\tree3.xml', null, '1', 6, 'resBase', 3002, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02bc', '402883d429d9eec20129da4af95c02b8', '_context.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\context_menu\_context.xml', null, '1', 1, 'resBase', 3003, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02bd', '402883d429d9eec20129da4af93c0277', 'dataprocessor', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\dataprocessor', null, '0', 1, 'resBase', 30, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02be', '402883d429d9eec20129da4af95c02bd', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\dataprocessor\index.html', null, '1', 1, 'resBase', 3100, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02bf', '402883d429d9eec20129da4af95c02bd', 'php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\dataprocessor\php', null, '0', 1, 'resBase', 31, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c0', '402883d429d9eec20129da4af95c02bf', 'get.php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\dataprocessor\php\get.php', null, '1', 2, 'resBase', 3200, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c1', '402883d429d9eec20129da4af95c02bf', 'sampleDB.sql', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\dataprocessor\php\sampleDB.sql', null, '1', 1, 'resBase', 3201, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c2', '402883d429d9eec20129da4af95c02bf', 'update.php', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\dataprocessor\php\update.php', null, '1', 5, 'resBase', 3202, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c3', '402883d429d9eec20129da4af93c0277', 'drag_n_drop', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop', null, '0', 1, 'resBase', 32, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c4', '402883d429d9eec20129da4af95c02c3', 'frames1', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames1', null, '0', 1, 'resBase', 33, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c5', '402883d429d9eec20129da4af95c02c4', 'pro_drag_frame_f1.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames1\pro_drag_frame_f1.html', null, '1', 1, 'resBase', 3400, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c6', '402883d429d9eec20129da4af95c02c4', 'pro_drag_frame_f2.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames1\pro_drag_frame_f2.html', null, '1', 1, 'resBase', 3401, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c7', '402883d429d9eec20129da4af95c02c4', 'pro_drag_frame_f3.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames1\pro_drag_frame_f3.html', null, '1', 1, 'resBase', 3402, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c8', '402883d429d9eec20129da4af95c02c3', 'frames2', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames2', null, '0', 1, 'resBase', 34, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02c9', '402883d429d9eec20129da4af95c02c8', 'pro_drag_frame2_f1.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames2\pro_drag_frame2_f1.html', null, '1', 1, 'resBase', 3500, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02ca', '402883d429d9eec20129da4af95c02c8', 'pro_drag_frame2_f2.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames2\pro_drag_frame2_f2.html', null, '1', 4, 'resBase', 3501, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af95c02cb', '402883d429d9eec20129da4af95c02c8', 'pro_drag_frame2_f3.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\frames2\pro_drag_frame2_f3.html', null, '1', 1, 'resBase', 3502, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02cc', '402883d429d9eec20129da4af95c02c3', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\index.html', null, '1', 1, 'resBase', 3302, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02cd', '402883d429d9eec20129da4af95c02c3', 'pro_disable_drop_check.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\pro_disable_drop_check.html', null, '1', 3, 'resBase', 3303, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ce', '402883d429d9eec20129da4af95c02c3', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\tree.xml', null, '1', 3, 'resBase', 3304, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02cf', '402883d429d9eec20129da4af95c02c3', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\tree3.xml', null, '1', 6, 'resBase', 3305, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d0', '402883d429d9eec20129da4af95c02c3', 'tree_a.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\tree_a.xml', null, '1', 2, 'resBase', 3306, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d1', '402883d429d9eec20129da4af95c02c3', 'tree_drag.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\drag_n_drop\tree_drag.html', null, '1', 7, 'resBase', 3307, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d2', '402883d429d9eec20129da4af93c0277', 'error_handlers', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\error_handlers', null, '0', 1, 'resBase', 35, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d3', '402883d429d9eec20129da4af96b02d2', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\error_handlers\index.html', null, '1', 1, 'resBase', 3600, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d4', '402883d429d9eec20129da4af96b02d2', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\error_handlers\tree3.xml', null, '1', 6, 'resBase', 3601, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d5', '402883d429d9eec20129da4af93c0277', 'events', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\events', null, '0', 1, 'resBase', 36, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d6', '402883d429d9eec20129da4af96b02d5', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\events\index.html', null, '1', 1, 'resBase', 3700, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d7', '402883d429d9eec20129da4af96b02d5', 'tree.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\events\tree.xml', null, '1', 6, 'resBase', 3701, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d8', '402883d429d9eec20129da4af96b02d5', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\events\tree3.xml', null, '1', 6, 'resBase', 3702, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02d9', '402883d429d9eec20129da4af96b02d5', 'tree_events.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\events\tree_events.html', null, '1', 9, 'resBase', 3703, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02da', '402883d429d9eec20129da4af93c0277', 'getting_tree_data', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\getting_tree_data', null, '0', 1, 'resBase', 37, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02db', '402883d429d9eec20129da4af96b02da', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\getting_tree_data\index.html', null, '1', 1, 'resBase', 3800, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02dc', '402883d429d9eec20129da4af96b02da', 'tree3.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\getting_tree_data\tree3.xml', null, '1', 6, 'resBase', 3801, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02dd', '402883d429d9eec20129da4af96b02da', 'tree_ud.xml', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\getting_tree_data\tree_ud.xml', null, '1', 6, 'resBase', 3802, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02de', '402883d429d9eec20129da4af93c0277', 'images', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images', null, '0', 1, 'resBase', 38, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02df', '402883d429d9eec20129da4af96b02de', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\blank.gif', null, '1', 1, 'resBase', 3900, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e0', '402883d429d9eec20129da4af96b02de', 'blue.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\blue.gif', null, '1', 1, 'resBase', 3901, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e1', '402883d429d9eec20129da4af96b02de', 'book.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\book.gif', null, '1', 1, 'resBase', 3902, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e2', '402883d429d9eec20129da4af96b02de', 'books_close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\books_close.gif', null, '1', 1, 'resBase', 3903, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e3', '402883d429d9eec20129da4af96b02de', 'books_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\books_open.gif', null, '1', 1, 'resBase', 3904, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e4', '402883d429d9eec20129da4af96b02de', 'close2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\close2.gif', null, '1', 1, 'resBase', 3905, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e5', '402883d429d9eec20129da4af96b02de', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\folderClosed.gif', null, '1', 1, 'resBase', 3906, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e6', '402883d429d9eec20129da4af96b02de', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\folderOpen.gif', null, '1', 1, 'resBase', 3907, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e7', '402883d429d9eec20129da4af96b02de', 'green.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\green.gif', null, '1', 1, 'resBase', 3908, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e8', '402883d429d9eec20129da4af96b02de', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\iconCheckAll.gif', null, '1', 1, 'resBase', 3909, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02e9', '402883d429d9eec20129da4af96b02de', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\iconCheckDis.gif', null, '1', 1, 'resBase', 3910, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ea', '402883d429d9eec20129da4af96b02de', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\iconCheckGray.gif', null, '1', 1, 'resBase', 3911, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02eb', '402883d429d9eec20129da4af96b02de', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\iconUncheckAll.gif', null, '1', 1, 'resBase', 3912, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ec', '402883d429d9eec20129da4af96b02de', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\iconUncheckDis.gif', null, '1', 1, 'resBase', 3913, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ed', '402883d429d9eec20129da4af96b02de', 'item2c.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\item2c.gif', null, '1', 1, 'resBase', 3914, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ee', '402883d429d9eec20129da4af96b02de', 'item2o.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\item2o.gif', null, '1', 1, 'resBase', 3915, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02ef', '402883d429d9eec20129da4af96b02de', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\leaf.gif', null, '1', 1, 'resBase', 3916, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f0', '402883d429d9eec20129da4af96b02de', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line1.gif', null, '1', 1, 'resBase', 3917, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f1', '402883d429d9eec20129da4af96b02de', 'line1_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line1_rtl.gif', null, '1', 1, 'resBase', 3918, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f2', '402883d429d9eec20129da4af96b02de', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line2.gif', null, '1', 1, 'resBase', 3919, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f3', '402883d429d9eec20129da4af96b02de', 'line2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line2_rtl.gif', null, '1', 1, 'resBase', 3920, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f4', '402883d429d9eec20129da4af96b02de', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line3.gif', null, '1', 1, 'resBase', 3921, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f5', '402883d429d9eec20129da4af96b02de', 'line3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line3_rtl.gif', null, '1', 1, 'resBase', 3922, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f6', '402883d429d9eec20129da4af96b02de', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line4.gif', null, '1', 1, 'resBase', 3923, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f7', '402883d429d9eec20129da4af96b02de', 'line4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\line4_rtl.gif', null, '1', 1, 'resBase', 3924, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f8', '402883d429d9eec20129da4af96b02de', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus.gif', null, '1', 1, 'resBase', 3925, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02f9', '402883d429d9eec20129da4af96b02de', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus2.gif', null, '1', 1, 'resBase', 3926, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02fa', '402883d429d9eec20129da4af96b02de', 'minus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus2_rtl.gif', null, '1', 1, 'resBase', 3927, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02fb', '402883d429d9eec20129da4af96b02de', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus3.gif', null, '1', 1, 'resBase', 3928, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02fc', '402883d429d9eec20129da4af96b02de', 'minus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus3_rtl.gif', null, '1', 1, 'resBase', 3929, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02fd', '402883d429d9eec20129da4af96b02de', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus4.gif', null, '1', 1, 'resBase', 3930, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b02fe', '402883d429d9eec20129da4af96b02de', 'minus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\minus4_rtl.gif', null, '1', 1, 'resBase', 3931, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0208', '402883d429d9eec20129da4af91d01e5', 'tombs_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\csh_yellowbooks\tombs_open.gif', null, '1', 1, 'resBase', 1534, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d0209', '402883d429d9eec20129da4af8bf0100', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\folderClosed.gif', null, '1', 1, 'resBase', 809, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d020a', '402883d429d9eec20129da4af8bf0100', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\folderOpen.gif', null, '1', 1, 'resBase', 810, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d020b', '402883d429d9eec20129da4af8bf0100', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\iconCheckAll.gif', null, '1', 1, 'resBase', 811, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af91d020c', '402883d429d9eec20129da4af8bf0100', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\iconCheckDis.gif', null, '1', 1, 'resBase', 812, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d020d', '402883d429d9eec20129da4af8bf0100', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\iconCheckGray.gif', null, '1', 1, 'resBase', 813, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d020e', '402883d429d9eec20129da4af8bf0100', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\iconUncheckAll.gif', null, '1', 1, 'resBase', 814, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d020f', '402883d429d9eec20129da4af8bf0100', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\iconUncheckDis.gif', null, '1', 1, 'resBase', 815, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0210', '402883d429d9eec20129da4af8bf0100', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\leaf.gif', null, '1', 1, 'resBase', 816, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0211', '402883d429d9eec20129da4af8bf0100', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line.gif', null, '1', 1, 'resBase', 817, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0212', '402883d429d9eec20129da4af8bf0100', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line1.gif', null, '1', 1, 'resBase', 818, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0213', '402883d429d9eec20129da4af8bf0100', 'line1_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line1_rtl.gif', null, '1', 1, 'resBase', 819, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0214', '402883d429d9eec20129da4af8bf0100', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line2.gif', null, '1', 1, 'resBase', 820, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0215', '402883d429d9eec20129da4af8bf0100', 'line2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line2_rtl.gif', null, '1', 1, 'resBase', 821, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0216', '402883d429d9eec20129da4af8bf0100', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line3.gif', null, '1', 1, 'resBase', 822, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0217', '402883d429d9eec20129da4af8bf0100', 'line3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line3_rtl.gif', null, '1', 1, 'resBase', 823, 'cms/fileIco/gif.gif');
commit;
prompt 800 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af92d0218', '402883d429d9eec20129da4af8bf0100', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\codebase\imgs\line4.gif', null, '1', 1, 'resBase', 824, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af96b0306', '402883d429d9eec20129da4af96b02de', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\samples\images\plus3.gif', null, '1', 1, 'resBase', 3939, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8046b', '402883d429d9eec20129da4af98a0345', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus2.gif', null, '1', 1, 'resBase', 4838, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8046c', '402883d429d9eec20129da4af98a0345', 'plus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus2_rtl.gif', null, '1', 1, 'resBase', 4839, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8046d', '402883d429d9eec20129da4af98a0345', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus3.gif', null, '1', 1, 'resBase', 4840, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8046e', '402883d429d9eec20129da4af98a0345', 'plus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus3_rtl.gif', null, '1', 1, 'resBase', 4841, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8046f', '402883d429d9eec20129da4af98a0345', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus4.gif', null, '1', 1, 'resBase', 4842, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80470', '402883d429d9eec20129da4af98a0345', 'plus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus4_rtl.gif', null, '1', 1, 'resBase', 4843, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80471', '402883d429d9eec20129da4af98a0345', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus5.gif', null, '1', 1, 'resBase', 4844, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80472', '402883d429d9eec20129da4af98a0345', 'plus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus5_rtl.gif', null, '1', 1, 'resBase', 4845, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80473', '402883d429d9eec20129da4af98a0345', 'plus_ar.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus_ar.gif', null, '1', 1, 'resBase', 4846, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80474', '402883d429d9eec20129da4af98a0345', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\radio_off.gif', null, '1', 1, 'resBase', 4847, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80475', '402883d429d9eec20129da4af98a0345', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\radio_on.gif', null, '1', 1, 'resBase', 4848, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80476', '402883d429d9eec20129da4af8b000f2', 'jqueryTabs', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs', null, '0', 1, 'resBase', 55, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80477', '402883d429d9eec20129da4af9f80476', 'ahah_1.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\ahah_1.html', null, '1', 1, 'resBase', 5600, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80478', '402883d429d9eec20129da4af9f80476', 'ahah_2.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\ahah_2.html', null, '1', 1, 'resBase', 5601, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80479', '402883d429d9eec20129da4af9f80476', 'ahah_3.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\ahah_3.html', null, '1', 1, 'resBase', 5602, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8047a', '402883d429d9eec20129da4af9f80476', 'index.html', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\index.html', null, '1', 26, 'resBase', 5603, 'cms/fileIco/html.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8047b', '402883d429d9eec20129da4af9f80476', 'jquery-1.1.3.1.pack.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery-1.1.3.1.pack.js', null, '1', 22, 'resBase', 5604, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8048d', '402883d429d9eec20129da4af9f80485', 'tab-close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-close.gif', null, '1', 1, 'resBase', 5707, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8047c', '402883d429d9eec20129da4af9f80476', 'jquery.history_remote.pack.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery.history_remote.pack.js', null, '1', 3, 'resBase', 5605, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8047d', '402883d429d9eec20129da4af9f80476', 'jquery.tabs-ie.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery.tabs-ie.css', null, '1', 1, 'resBase', 5606, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8047e', '402883d429d9eec20129da4af9f80476', 'jquery.tabs.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery.tabs.css', null, '1', 3, 'resBase', 5607, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8047f', '402883d429d9eec20129da4af9f80476', 'jquery.tabs.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery.tabs.js', null, '1', 32, 'resBase', 5608, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80480', '402883d429d9eec20129da4af9f80476', 'jquery.tabs.min.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery.tabs.min.js', null, '1', 9, 'resBase', 5609, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80481', '402883d429d9eec20129da4af9f80476', 'jquery.tabs.pack.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\jquery.tabs.pack.js', null, '1', 6, 'resBase', 5610, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80482', '402883d429d9eec20129da4af9f80476', 'loading.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\loading.gif', null, '1', 3, 'resBase', 5611, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80483', '402883d429d9eec20129da4af9f80476', 'META.json', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\META.json', null, '1', 1, 'resBase', 5612, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80484', '402883d429d9eec20129da4af9f80476', 'tab.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\jqueryTabs\tab.png', null, '1', 1, 'resBase', 5613, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80485', '402883d429d9eec20129da4af8b000f2', 'tabs', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs', null, '0', 1, 'resBase', 56, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80486', '402883d429d9eec20129da4af9f80485', 'scroll-left.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\scroll-left.gif', null, '1', 2, 'resBase', 5700, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80487', '402883d429d9eec20129da4af9f80485', 'scroll-right.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\scroll-right.gif', null, '1', 2, 'resBase', 5701, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80488', '402883d429d9eec20129da4af9f80485', 'scroller-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\scroller-bg.gif', null, '1', 2, 'resBase', 5702, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80489', '402883d429d9eec20129da4af9f80485', 'tab-btm-inactive-left-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-btm-inactive-left-bg.gif', null, '1', 1, 'resBase', 5703, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8048a', '402883d429d9eec20129da4af9f80485', 'tab-btm-inactive-right-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-btm-inactive-right-bg.gif', null, '1', 2, 'resBase', 5704, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8048b', '402883d429d9eec20129da4af9f80485', 'tab-btm-left-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-btm-left-bg.gif', null, '1', 1, 'resBase', 5705, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8048c', '402883d429d9eec20129da4af9f80485', 'tab-btm-right-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-btm-right-bg.gif', null, '1', 2, 'resBase', 5706, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8048e', '402883d429d9eec20129da4af9f80485', 'tab-strip-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-strip-bg.gif', null, '1', 1, 'resBase', 5708, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8048f', '402883d429d9eec20129da4af9f80485', 'tab-strip-bg.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-strip-bg.png', null, '1', 1, 'resBase', 5709, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80490', '402883d429d9eec20129da4af9f80485', 'tab-strip-btm-bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tab-strip-btm-bg.gif', null, '1', 1, 'resBase', 5710, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070491', '402883d429d9eec20129da4af9f80485', 'tabs-sprite.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\tabs\tabs-sprite.gif', null, '1', 3, 'resBase', 5711, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070492', '402883d429d9eec20129da4af89000ea', 'scripts', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts', null, '0', 1, 'resBase', 57, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070493', '402883d429d9eec20129da4afa070492', 'frame.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\frame.js', null, '1', 1, 'resBase', 5800, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070494', '402883d429d9eec20129da4afa070492', 'home.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\home.js', null, '1', 4, 'resBase', 5801, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070495', '402883d429d9eec20129da4afa070492', 'home2.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\home2.js', null, '1', 3, 'resBase', 5802, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070496', '402883d429d9eec20129da4afa070492', 'jquery.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\jquery.js', null, '1', 55, 'resBase', 5803, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070497', '402883d429d9eec20129da4afa070492', 'jqueryUI.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\jqueryUI.js', null, '1', 188, 'resBase', 5804, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070498', '402883d429d9eec20129da4afa070492', 'jquery_tabs.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\jquery_tabs.js', null, '1', 9, 'resBase', 5805, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa070499', '402883d429d9eec20129da4afa070492', 'listtree.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\listtree.js', null, '1', 3, 'resBase', 5806, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa07049a', '402883d429d9eec20129da4afa070492', 'loadJson.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\loadJson.js', null, '1', 1, 'resBase', 5807, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa07049b', '402883d429d9eec20129da4afa070492', 'loadPage.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\loadPage.js', null, '1', 6, 'resBase', 5808, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa07049c', '402883d429d9eec20129da4afa070492', 'tabSet.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\tabSet.js', null, '1', 3, 'resBase', 5809, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa07049d', '402883d429d9eec20129da4afa070492', 'tabSet2.js', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\scripts\tabSet2.js', null, '1', 2, 'resBase', 5810, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa07049e', '402883d429d9eec20129da4af89000ea', 'skin', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin', null, '0', 1, 'resBase', 58, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa07049f', '402883d429d9eec20129da4afa07049e', 'css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css', null, '0', 1, 'resBase', 59, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a0', '402883d429d9eec20129da4afa07049f', 'common.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\common.css', null, '1', 4, 'resBase', 6000, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a1', '402883d429d9eec20129da4afa07049f', 'f.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\f.css', null, '1', 26, 'resBase', 6001, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a2', '402883d429d9eec20129da4afa07049f', 'form.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\form.css', null, '1', 1, 'resBase', 6002, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a3', '402883d429d9eec20129da4afa07049f', 'index.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\index.css', null, '1', 13, 'resBase', 6003, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a4', '402883d429d9eec20129da4afa07049f', 'init.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\init.css', null, '1', 2, 'resBase', 6004, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a5', '402883d429d9eec20129da4afa07049f', 'jquery-ui-themeroller.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\jquery-ui-themeroller.css', null, '1', 27, 'resBase', 6005, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a6', '402883d429d9eec20129da4afa07049f', 'mainHome.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\mainHome.css', null, '1', 1, 'resBase', 6006, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a7', '402883d429d9eec20129da4afa07049f', 'nav1.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\nav1.css', null, '1', 10, 'resBase', 6007, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a8', '402883d429d9eec20129da4afa07049f', 'normalizes.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\normalizes.css', null, '1', 1, 'resBase', 6008, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704a9', '402883d429d9eec20129da4afa07049f', 'other.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\other.css', null, '1', 13, 'resBase', 6009, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704aa', '402883d429d9eec20129da4afa07049f', 'portalClassifying.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\portalClassifying.css', null, '1', 10, 'resBase', 6010, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704ab', '402883d429d9eec20129da4afa07049f', 'portalFrame.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\portalFrame.css', null, '1', 3, 'resBase', 6011, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704ac', '402883d429d9eec20129da4afa07049f', 'registration.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\registration.css', null, '1', 3, 'resBase', 6012, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704ad', '402883d429d9eec20129da4afa07049f', 'table.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\table.css', null, '1', 5, 'resBase', 6013, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa0704ae', '402883d429d9eec20129da4afa07049f', 'vivian.css', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\css\vivian.css', null, '1', 20, 'resBase', 6014, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704af', '402883d429d9eec20129da4afa07049e', 'img', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img', null, '0', 1, 'resBase', 60, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b0', '402883d429d9eec20129da4afa1704af', '20085279430_25.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\20085279430_25.gif', null, '1', 1, 'resBase', 6100, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b1', '402883d429d9eec20129da4afa1704af', '20085279430_25.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\20085279430_25.png', null, '1', 4, 'resBase', 6101, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b2', '402883d429d9eec20129da4afa1704af', 'advertising.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\advertising.gif', null, '1', 12, 'resBase', 6102, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b3', '402883d429d9eec20129da4afa1704af', 'banner--.jpg', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\banner--.jpg', null, '1', 90, 'resBase', 6103, 'cms/fileIco/jpg.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b4', '402883d429d9eec20129da4afa1704af', 'banner.jpg', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\banner.jpg', null, '1', 132, 'resBase', 6104, 'cms/fileIco/jpg.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b5', '402883d429d9eec20129da4afa1704af', 'banner2.jpg', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\banner2.jpg', null, '1', 271, 'resBase', 6105, 'cms/fileIco/jpg.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b6', '402883d429d9eec20129da4afa1704af', 'blue.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\blue.gif', null, '1', 1, 'resBase', 6106, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b7', '402883d429d9eec20129da4afa1704af', 'bullet_star.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\bullet_star.png', null, '1', 1, 'resBase', 6107, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b8', '402883d429d9eec20129da4afa1704af', 'butt_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\butt_bg.gif', null, '1', 1, 'resBase', 6108, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704b9', '402883d429d9eec20129da4afa1704af', 'caigou2003.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\caigou2003.gif', null, '1', 4, 'resBase', 6109, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704ba', '402883d429d9eec20129da4afa1704af', 'CaLogin.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\CaLogin.gif', null, '1', 1, 'resBase', 6110, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c4', '402883d429d9eec20129da4afa1704af', 'fastTrack_h4_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\fastTrack_h4_bg.gif', null, '1', 1, 'resBase', 6120, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c5', '402883d429d9eec20129da4afa1704af', 'footer_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\footer_bg.gif', null, '1', 1, 'resBase', 6121, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c6', '402883d429d9eec20129da4afa1704af', 'green.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\green.gif', null, '1', 1, 'resBase', 6122, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c7', '402883d429d9eec20129da4afa1704af', 'h4_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\h4_bg.gif', null, '1', 1, 'resBase', 6123, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c8', '402883d429d9eec20129da4afa1704af', 'h4_bg.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\h4_bg.png', null, '1', 3, 'resBase', 6124, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704bb', '402883d429d9eec20129da4afa1704af', 'collect.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\collect.gif', null, '1', 1, 'resBase', 6111, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704bc', '402883d429d9eec20129da4afa1704af', 'company1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company1.gif', null, '1', 4, 'resBase', 6112, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704bd', '402883d429d9eec20129da4afa1704af', 'company2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company2.gif', null, '1', 5, 'resBase', 6113, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704be', '402883d429d9eec20129da4afa1704af', 'company3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company3.gif', null, '1', 7, 'resBase', 6114, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704bf', '402883d429d9eec20129da4afa1704af', 'company4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company4.gif', null, '1', 6, 'resBase', 6115, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c0', '402883d429d9eec20129da4afa1704af', 'company5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company5.gif', null, '1', 6, 'resBase', 6116, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c1', '402883d429d9eec20129da4afa1704af', 'company6.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company6.gif', null, '1', 5, 'resBase', 6117, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c2', '402883d429d9eec20129da4afa1704af', 'company_h4_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\company_h4_bg.gif', null, '1', 3, 'resBase', 6118, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c3', '402883d429d9eec20129da4afa1704af', 'extraDiv_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\extraDiv_bg.gif', null, '1', 1, 'resBase', 6119, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704c9', '402883d429d9eec20129da4afa1704af', 'home.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\home.gif', null, '1', 1, 'resBase', 6125, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704ca', '402883d429d9eec20129da4afa1704af', 'homePage_bg.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\homePage_bg.png', null, '1', 4, 'resBase', 6126, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704cb', '402883d429d9eec20129da4afa1704af', 'icon', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\icon', null, '0', 1, 'resBase', 61, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704cc', '402883d429d9eec20129da4afa1704cb', 'icon-1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\icon\icon-1.gif', null, '1', 1, 'resBase', 6200, 'cms/fileIco/gif.gif');
commit;
prompt 900 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704cd', '402883d429d9eec20129da4afa1704af', 'input_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\input_bg.gif', null, '1', 1, 'resBase', 6128, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704ce', '402883d429d9eec20129da4afa1704af', 'lawGuide_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\lawGuide_bg.gif', null, '1', 2, 'resBase', 6129, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704cf', '402883d429d9eec20129da4afa1704af', 'lawGuide_bg1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\lawGuide_bg1.gif', null, '1', 1, 'resBase', 6130, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704d0', '402883d429d9eec20129da4afa1704af', 'lawGuide_selected.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\lawGuide_selected.gif', null, '1', 1, 'resBase', 6131, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704d1', '402883d429d9eec20129da4afa1704af', 'liStyle_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\liStyle_bg.gif', null, '1', 1, 'resBase', 6132, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704d2', '402883d429d9eec20129da4afa1704af', 'login.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\login.gif', null, '1', 1, 'resBase', 6133, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704d3', '402883d429d9eec20129da4afa1704af', 'login_input.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\login_input.gif', null, '1', 1, 'resBase', 6134, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa1704d4', '402883d429d9eec20129da4afa1704af', 'map.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\map.gif', null, '1', 1, 'resBase', 6135, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704d5', '402883d429d9eec20129da4afa1704af', 'narrow.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\narrow.gif', null, '1', 1, 'resBase', 6136, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704d6', '402883d429d9eec20129da4afa1704af', 'navMain_a.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\navMain_a.gif', null, '1', 1, 'resBase', 6137, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704d7', '402883d429d9eec20129da4afa1704af', 'navMain_a.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\navMain_a.png', null, '1', 3, 'resBase', 6138, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704d8', '402883d429d9eec20129da4afa1704af', 'navMain_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\navMain_bg.gif', null, '1', 1, 'resBase', 6139, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704d9', '402883d429d9eec20129da4afa1704af', 'navMain_bg.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\navMain_bg.png', null, '1', 3, 'resBase', 6140, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704da', '402883d429d9eec20129da4afa1704af', 'navMain_bg_hover.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\navMain_bg_hover.png', null, '1', 1, 'resBase', 6141, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704db', '402883d429d9eec20129da4afa1704af', 'navMain_bg_selected.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\navMain_bg_selected.png', null, '1', 3, 'resBase', 6142, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704dc', '402883d429d9eec20129da4afa1704af', 'number.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\number.gif', null, '1', 1, 'resBase', 6143, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704dd', '402883d429d9eec20129da4afa1704af', 'onlineServices.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\onlineServices.gif', null, '1', 3, 'resBase', 6144, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704de', '402883d429d9eec20129da4afa1704af', 'openinglogin.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\openinglogin.gif', null, '1', 7, 'resBase', 6145, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704df', '402883d429d9eec20129da4afa1704af', 'openingProject.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\openingProject.gif', null, '1', 3, 'resBase', 6146, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e0', '402883d429d9eec20129da4afa1704af', 'pointOfService.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\pointOfService.gif', null, '1', 3, 'resBase', 6147, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e1', '402883d429d9eec20129da4afa1704af', 'procurementCatalog.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\procurementCatalog.gif', null, '1', 3, 'resBase', 6148, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e2', '402883d429d9eec20129da4afa1704af', 'purple.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\purple.gif', null, '1', 1, 'resBase', 6149, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e3', '402883d429d9eec20129da4afa1704af', 'red.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\red.gif', null, '1', 1, 'resBase', 6150, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e4', '402883d429d9eec20129da4afa1704af', 'redCircle.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\redCircle.gif', null, '1', 1, 'resBase', 6151, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e5', '402883d429d9eec20129da4afa1704af', 'search_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\search_bg.gif', null, '1', 1, 'resBase', 6152, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e6', '402883d429d9eec20129da4afa1704af', 'search_p_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\search_p_bg.gif', null, '1', 1, 'resBase', 6153, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e7', '402883d429d9eec20129da4afa1704af', 'selected.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\selected.gif', null, '1', 1, 'resBase', 6154, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e8', '402883d429d9eec20129da4afa1704af', 'service.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\service.gif', null, '1', 12, 'resBase', 6155, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704e9', '402883d429d9eec20129da4afa1704af', 'sidebar1_h4_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\sidebar1_h4_bg.gif', null, '1', 1, 'resBase', 6156, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704ea', '402883d429d9eec20129da4afa1704af', 'sidebar2_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\sidebar2_bg.gif', null, '1', 1, 'resBase', 6157, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704eb', '402883d429d9eec20129da4afa1704af', 'sidebar2_h4_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\sidebar2_h4_bg.gif', null, '1', 2, 'resBase', 6158, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704ec', '402883d429d9eec20129da4afa1704af', 'sidebar2_h4_bg2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\sidebar2_h4_bg2.gif', null, '1', 1, 'resBase', 6159, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704ed', '402883d429d9eec20129da4afa1704af', 'tabs_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\tabs_bg.gif', null, '1', 3, 'resBase', 6160, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704ee', '402883d429d9eec20129da4afa1704af', 'tabs_bg.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\tabs_bg.png', null, '1', 4, 'resBase', 6161, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903d9', '402883d429d9eec20129da4af9c903bb', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\plus2.gif', null, '1', 1, 'resBase', 5229, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903da', '402883d429d9eec20129da4af9c903bb', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\plus3.gif', null, '1', 1, 'resBase', 5230, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903db', '402883d429d9eec20129da4af9c903bb', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\plus4.gif', null, '1', 1, 'resBase', 5231, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903dc', '402883d429d9eec20129da4af9c903bb', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\plus5.gif', null, '1', 1, 'resBase', 5232, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903dd', '402883d429d9eec20129da4af9c903bb', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\radio_off.gif', null, '1', 1, 'resBase', 5233, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903de', '402883d429d9eec20129da4af9c903bb', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\radio_on.gif', null, '1', 1, 'resBase', 5234, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903df', '402883d429d9eec20129da4af9c903bb', 'safe_close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\safe_close.gif', null, '1', 1, 'resBase', 5235, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e0', '402883d429d9eec20129da4af9c903bb', 'safe_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_scbrblue\safe_open.gif', null, '1', 1, 'resBase', 5236, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e1', '402883d429d9eec20129da4af98a0345', 'csh_vista', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista', null, '0', 1, 'resBase', 52, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e2', '402883d429d9eec20129da4af9d903e1', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\blank.gif', null, '1', 1, 'resBase', 5300, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e3', '402883d429d9eec20129da4af9d903e1', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\folderClosed.gif', null, '1', 1, 'resBase', 5301, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e4', '402883d429d9eec20129da4af9d903e1', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\folderOpen.gif', null, '1', 1, 'resBase', 5302, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e5', '402883d429d9eec20129da4af9d903e1', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconCheckAll.gif', null, '1', 1, 'resBase', 5303, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e6', '402883d429d9eec20129da4af9d903e1', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconCheckDis.gif', null, '1', 1, 'resBase', 5304, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e7', '402883d429d9eec20129da4af9d903e1', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconChecked.gif', null, '1', 1, 'resBase', 5305, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e8', '402883d429d9eec20129da4af9d903e1', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconCheckGray.gif', null, '1', 1, 'resBase', 5306, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704ef', '402883d429d9eec20129da4afa1704af', 'tabs_bg1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\tabs_bg1.gif', null, '1', 1, 'resBase', 6162, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa2704f0', '402883d429d9eec20129da4afa1704af', 'tabs_bg2.png', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\tabs_bg2.png', null, '1', 3, 'resBase', 6163, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f1', '402883d429d9eec20129da4afa1704af', 'tag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\tag.gif', null, '1', 1, 'resBase', 6164, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f2', '402883d429d9eec20129da4afa1704af', 'Thumbs.db', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\Thumbs.db', null, '1', 243, 'resBase', 6165, 'cms/fileIco/unknow.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f3', '402883d429d9eec20129da4afa1704af', 'triangle.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\triangle.gif', null, '1', 1, 'resBase', 6166, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f4', '402883d429d9eec20129da4afa1704af', 'userLogin_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\userLogin_bg.gif', null, '1', 1, 'resBase', 6167, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f5', '402883d429d9eec20129da4afa1704af', 'userLogin_h4_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\userLogin_h4_bg.gif', null, '1', 2, 'resBase', 6168, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f6', '402883d429d9eec20129da4afa1704af', 'userLogin_input_bg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\userLogin_input_bg.gif', null, '1', 1, 'resBase', 6169, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f7', '402883d429d9eec20129da4afa1704af', 'userLogin_input_bg2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\userLogin_input_bg2.gif', null, '1', 1, 'resBase', 6170, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f8', '402883d429d9eec20129da4afa1704af', 'width.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\width.gif', null, '1', 1, 'resBase', 6171, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604f9', '402883d429d9eec20129da4afa1704af', 'zgcjb.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\zgcjb.gif', null, '1', 4, 'resBase', 6172, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604fa', '402883d429d9eec20129da4afa1704af', 'zggjzb.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\zggjzb.gif', null, '1', 3, 'resBase', 6173, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4afa3604fb', '402883d429d9eec20129da4afa1704af', 'zgzfcg.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\skin\img\zgzfcg.gif', null, '1', 3, 'resBase', 6174, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903e9', '402883d429d9eec20129da4af9d903e1', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconFlag.gif', null, '1', 1, 'resBase', 5307, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903ea', '402883d429d9eec20129da4af9d903e1', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconGraph.gif', null, '1', 1, 'resBase', 5308, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903eb', '402883d429d9eec20129da4af9d903e1', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconSound.gif', null, '1', 1, 'resBase', 5309, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903ec', '402883d429d9eec20129da4af9d903e1', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconText.gif', null, '1', 1, 'resBase', 5310, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903ed', '402883d429d9eec20129da4af9d903e1', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconTexts.gif', null, '1', 1, 'resBase', 5311, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903ee', '402883d429d9eec20129da4af9d903e1', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconUncheckAll.gif', null, '1', 1, 'resBase', 5312, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903ef', '402883d429d9eec20129da4af9d903e1', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconUncheckDis.gif', null, '1', 1, 'resBase', 5313, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f0', '402883d429d9eec20129da4af9d903e1', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconWrite1.gif', null, '1', 1, 'resBase', 5314, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f1', '402883d429d9eec20129da4af9d903e1', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\iconWrite2.gif', null, '1', 1, 'resBase', 5315, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f2', '402883d429d9eec20129da4af9d903e1', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\leaf.gif', null, '1', 1, 'resBase', 5316, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f3', '402883d429d9eec20129da4af9d903e1', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\line.gif', null, '1', 1, 'resBase', 5317, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f4', '402883d429d9eec20129da4af9d903e1', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\line1.gif', null, '1', 1, 'resBase', 5318, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f5', '402883d429d9eec20129da4af9d903e1', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\line2.gif', null, '1', 1, 'resBase', 5319, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f6', '402883d429d9eec20129da4af9d903e1', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\line3.gif', null, '1', 1, 'resBase', 5320, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f7', '402883d429d9eec20129da4af9d903e1', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\line4.gif', null, '1', 1, 'resBase', 5321, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f8', '402883d429d9eec20129da4af9d903e1', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\lock.gif', null, '1', 1, 'resBase', 5322, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9d903f9', '402883d429d9eec20129da4af9d903e1', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\minus.gif', null, '1', 1, 'resBase', 5323, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e803fa', '402883d429d9eec20129da4af9d903e1', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\minus2.gif', null, '1', 1, 'resBase', 5324, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e803fb', '402883d429d9eec20129da4af9d903e1', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\minus3.gif', null, '1', 1, 'resBase', 5325, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e803fc', '402883d429d9eec20129da4af9d903e1', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\minus4.gif', null, '1', 1, 'resBase', 5326, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e803fd', '402883d429d9eec20129da4af9d903e1', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\minus5.gif', null, '1', 1, 'resBase', 5327, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e803fe', '402883d429d9eec20129da4af9d903e1', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\plus.gif', null, '1', 1, 'resBase', 5328, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e803ff', '402883d429d9eec20129da4af9d903e1', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\plus2.gif', null, '1', 1, 'resBase', 5329, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80400', '402883d429d9eec20129da4af9d903e1', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\plus3.gif', null, '1', 1, 'resBase', 5330, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80401', '402883d429d9eec20129da4af9d903e1', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\plus4.gif', null, '1', 1, 'resBase', 5331, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80402', '402883d429d9eec20129da4af9d903e1', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\plus5.gif', null, '1', 1, 'resBase', 5332, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80403', '402883d429d9eec20129da4af9d903e1', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\radio_off.gif', null, '1', 1, 'resBase', 5333, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80404', '402883d429d9eec20129da4af9d903e1', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_vista\radio_on.gif', null, '1', 1, 'resBase', 5334, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80405', '402883d429d9eec20129da4af98a0345', 'csh_winstyle', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle', null, '0', 1, 'resBase', 53, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80406', '402883d429d9eec20129da4af9e80405', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\blank.gif', null, '1', 1, 'resBase', 5400, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80407', '402883d429d9eec20129da4af9e80405', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\folderClosed.gif', null, '1', 1, 'resBase', 5401, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80408', '402883d429d9eec20129da4af9e80405', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\folderOpen.gif', null, '1', 1, 'resBase', 5402, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80409', '402883d429d9eec20129da4af9e80405', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconCheckAll.gif', null, '1', 1, 'resBase', 5403, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8040a', '402883d429d9eec20129da4af9e80405', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconCheckDis.gif', null, '1', 1, 'resBase', 5404, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8040b', '402883d429d9eec20129da4af9e80405', 'iconChecked.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconChecked.gif', null, '1', 1, 'resBase', 5405, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8040c', '402883d429d9eec20129da4af9e80405', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconCheckGray.gif', null, '1', 1, 'resBase', 5406, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8040d', '402883d429d9eec20129da4af9e80405', 'iconFlag.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconFlag.gif', null, '1', 1, 'resBase', 5407, 'cms/fileIco/gif.gif');
commit;
prompt 1000 records committed...
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8040e', '402883d429d9eec20129da4af9e80405', 'iconGraph.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconGraph.gif', null, '1', 1, 'resBase', 5408, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8040f', '402883d429d9eec20129da4af9e80405', 'iconSound.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconSound.gif', null, '1', 1, 'resBase', 5409, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80410', '402883d429d9eec20129da4af9e80405', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconText.gif', null, '1', 1, 'resBase', 5410, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80411', '402883d429d9eec20129da4af9e80405', 'iconTexts.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconTexts.gif', null, '1', 1, 'resBase', 5411, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80412', '402883d429d9eec20129da4af9e80405', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconUncheckAll.gif', null, '1', 1, 'resBase', 5412, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80413', '402883d429d9eec20129da4af9e80405', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconUncheckDis.gif', null, '1', 1, 'resBase', 5413, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80414', '402883d429d9eec20129da4af9e80405', 'iconWrite1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconWrite1.gif', null, '1', 1, 'resBase', 5414, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80415', '402883d429d9eec20129da4af9e80405', 'iconWrite2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\iconWrite2.gif', null, '1', 1, 'resBase', 5415, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80416', '402883d429d9eec20129da4af9e80405', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\leaf.gif', null, '1', 1, 'resBase', 5416, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80417', '402883d429d9eec20129da4af9e80405', 'leaves.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\leaves.gif', null, '1', 1, 'resBase', 5417, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80418', '402883d429d9eec20129da4af9e80405', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\line.gif', null, '1', 1, 'resBase', 5418, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80419', '402883d429d9eec20129da4af9e80405', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\line1.gif', null, '1', 1, 'resBase', 5419, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8041a', '402883d429d9eec20129da4af9e80405', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\line2.gif', null, '1', 1, 'resBase', 5420, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8041b', '402883d429d9eec20129da4af9e80405', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\line3.gif', null, '1', 1, 'resBase', 5421, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8041c', '402883d429d9eec20129da4af9e80405', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\line4.gif', null, '1', 1, 'resBase', 5422, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8041d', '402883d429d9eec20129da4af9e80405', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\lock.gif', null, '1', 1, 'resBase', 5423, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8041e', '402883d429d9eec20129da4af9e80405', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\minus.gif', null, '1', 1, 'resBase', 5424, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8041f', '402883d429d9eec20129da4af9e80405', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\minus2.gif', null, '1', 1, 'resBase', 5425, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80420', '402883d429d9eec20129da4af9e80405', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\minus3.gif', null, '1', 1, 'resBase', 5426, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80421', '402883d429d9eec20129da4af9e80405', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\minus4.gif', null, '1', 1, 'resBase', 5427, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80422', '402883d429d9eec20129da4af9e80405', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\minus5.gif', null, '1', 1, 'resBase', 5428, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80423', '402883d429d9eec20129da4af9e80405', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\plus.gif', null, '1', 1, 'resBase', 5429, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80424', '402883d429d9eec20129da4af9e80405', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\plus2.gif', null, '1', 1, 'resBase', 5430, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80425', '402883d429d9eec20129da4af9e80405', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\plus3.gif', null, '1', 1, 'resBase', 5431, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80426', '402883d429d9eec20129da4af9e80405', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\plus4.gif', null, '1', 1, 'resBase', 5432, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80427', '402883d429d9eec20129da4af9e80405', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\plus5.gif', null, '1', 1, 'resBase', 5433, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80428', '402883d429d9eec20129da4af9e80405', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\radio_off.gif', null, '1', 1, 'resBase', 5434, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80429', '402883d429d9eec20129da4af9e80405', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_winstyle\radio_on.gif', null, '1', 1, 'resBase', 5435, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8042a', '402883d429d9eec20129da4af98a0345', 'csh_yellowbooks', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks', null, '0', 1, 'resBase', 54, 'cms/fileIco/folderClosed.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8042b', '402883d429d9eec20129da4af9e8042a', 'blank.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\blank.gif', null, '1', 1, 'resBase', 5500, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8042c', '402883d429d9eec20129da4af9e8042a', 'books_close.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\books_close.gif', null, '1', 1, 'resBase', 5501, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8042d', '402883d429d9eec20129da4af9e8042a', 'books_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\books_open.gif', null, '1', 1, 'resBase', 5502, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8042e', '402883d429d9eec20129da4af9e8042a', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\folderClosed.gif', null, '1', 1, 'resBase', 5503, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8042f', '402883d429d9eec20129da4af9e8042a', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\folderOpen.gif', null, '1', 1, 'resBase', 5504, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80430', '402883d429d9eec20129da4af9e8042a', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\iconCheckAll.gif', null, '1', 1, 'resBase', 5505, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80431', '402883d429d9eec20129da4af9e8042a', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\iconCheckDis.gif', null, '1', 1, 'resBase', 5506, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80432', '402883d429d9eec20129da4af9e8042a', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\iconCheckGray.gif', null, '1', 1, 'resBase', 5507, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80433', '402883d429d9eec20129da4af9e8042a', 'iconText.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\iconText.gif', null, '1', 1, 'resBase', 5508, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80434', '402883d429d9eec20129da4af9e8042a', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\iconUncheckAll.gif', null, '1', 1, 'resBase', 5509, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80435', '402883d429d9eec20129da4af9e8042a', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\iconUncheckDis.gif', null, '1', 1, 'resBase', 5510, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80436', '402883d429d9eec20129da4af9e8042a', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\leaf.gif', null, '1', 1, 'resBase', 5511, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80437', '402883d429d9eec20129da4af9e8042a', 'leaf2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\leaf2.gif', null, '1', 1, 'resBase', 5512, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80438', '402883d429d9eec20129da4af9e8042a', 'leaf3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\leaf3.gif', null, '1', 1, 'resBase', 5513, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80439', '402883d429d9eec20129da4af9e8042a', 'leaf_2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\leaf_2.gif', null, '1', 1, 'resBase', 5514, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8043a', '402883d429d9eec20129da4af9e8042a', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\line.gif', null, '1', 1, 'resBase', 5515, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8043b', '402883d429d9eec20129da4af9e8042a', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\line1.gif', null, '1', 1, 'resBase', 5516, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8043c', '402883d429d9eec20129da4af9e8042a', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\line2.gif', null, '1', 1, 'resBase', 5517, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8043d', '402883d429d9eec20129da4af9e8042a', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\line3.gif', null, '1', 1, 'resBase', 5518, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8043e', '402883d429d9eec20129da4af9e8042a', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\line4.gif', null, '1', 1, 'resBase', 5519, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e8043f', '402883d429d9eec20129da4af9e8042a', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\lock.gif', null, '1', 1, 'resBase', 5520, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80440', '402883d429d9eec20129da4af9e8042a', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\minus.gif', null, '1', 1, 'resBase', 5521, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80441', '402883d429d9eec20129da4af9e8042a', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\minus2.gif', null, '1', 1, 'resBase', 5522, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80442', '402883d429d9eec20129da4af9e8042a', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\minus3.gif', null, '1', 1, 'resBase', 5523, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80443', '402883d429d9eec20129da4af9e8042a', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\minus4.gif', null, '1', 1, 'resBase', 5524, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80444', '402883d429d9eec20129da4af9e8042a', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\minus5.gif', null, '1', 1, 'resBase', 5525, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80445', '402883d429d9eec20129da4af9e8042a', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\plus.gif', null, '1', 1, 'resBase', 5526, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80446', '402883d429d9eec20129da4af9e8042a', 'plus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\plus2.gif', null, '1', 1, 'resBase', 5527, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80447', '402883d429d9eec20129da4af9e8042a', 'plus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\plus3.gif', null, '1', 1, 'resBase', 5528, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9e80448', '402883d429d9eec20129da4af9e8042a', 'plus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\plus4.gif', null, '1', 1, 'resBase', 5529, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80449', '402883d429d9eec20129da4af9e8042a', 'plus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\plus5.gif', null, '1', 1, 'resBase', 5530, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8044a', '402883d429d9eec20129da4af9e8042a', 'radio_off.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\radio_off.gif', null, '1', 1, 'resBase', 5531, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8044b', '402883d429d9eec20129da4af9e8042a', 'radio_on.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\radio_on.gif', null, '1', 1, 'resBase', 5532, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8044c', '402883d429d9eec20129da4af9e8042a', 'tombs.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\tombs.gif', null, '1', 1, 'resBase', 5533, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8044d', '402883d429d9eec20129da4af9e8042a', 'tombs_open.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\csh_yellowbooks\tombs_open.gif', null, '1', 1, 'resBase', 5534, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8044e', '402883d429d9eec20129da4af98a0345', 'folderClosed.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\folderClosed.gif', null, '1', 1, 'resBase', 4809, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8044f', '402883d429d9eec20129da4af98a0345', 'folderOpen.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\folderOpen.gif', null, '1', 1, 'resBase', 4810, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80450', '402883d429d9eec20129da4af98a0345', 'iconCheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\iconCheckAll.gif', null, '1', 1, 'resBase', 4811, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80451', '402883d429d9eec20129da4af98a0345', 'iconCheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\iconCheckDis.gif', null, '1', 1, 'resBase', 4812, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80452', '402883d429d9eec20129da4af98a0345', 'iconCheckGray.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\iconCheckGray.gif', null, '1', 1, 'resBase', 4813, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80453', '402883d429d9eec20129da4af98a0345', 'iconUncheckAll.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\iconUncheckAll.gif', null, '1', 1, 'resBase', 4814, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80454', '402883d429d9eec20129da4af98a0345', 'iconUncheckDis.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\iconUncheckDis.gif', null, '1', 1, 'resBase', 4815, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80455', '402883d429d9eec20129da4af98a0345', 'leaf.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\leaf.gif', null, '1', 1, 'resBase', 4816, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80456', '402883d429d9eec20129da4af98a0345', 'line.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line.gif', null, '1', 1, 'resBase', 4817, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80457', '402883d429d9eec20129da4af98a0345', 'line1.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line1.gif', null, '1', 1, 'resBase', 4818, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80458', '402883d429d9eec20129da4af98a0345', 'line1_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line1_rtl.gif', null, '1', 1, 'resBase', 4819, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80459', '402883d429d9eec20129da4af98a0345', 'line2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line2.gif', null, '1', 1, 'resBase', 4820, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8045a', '402883d429d9eec20129da4af98a0345', 'line2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line2_rtl.gif', null, '1', 1, 'resBase', 4821, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8045b', '402883d429d9eec20129da4af98a0345', 'line3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line3.gif', null, '1', 1, 'resBase', 4822, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8045c', '402883d429d9eec20129da4af98a0345', 'line3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line3_rtl.gif', null, '1', 1, 'resBase', 4823, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8045d', '402883d429d9eec20129da4af98a0345', 'line4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line4.gif', null, '1', 1, 'resBase', 4824, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8045e', '402883d429d9eec20129da4af98a0345', 'line4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\line4_rtl.gif', null, '1', 1, 'resBase', 4825, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8045f', '402883d429d9eec20129da4af98a0345', 'lock.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\lock.gif', null, '1', 1, 'resBase', 4826, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80460', '402883d429d9eec20129da4af98a0345', 'minus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus.gif', null, '1', 1, 'resBase', 4827, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80461', '402883d429d9eec20129da4af98a0345', 'minus2.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus2.gif', null, '1', 1, 'resBase', 4828, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80462', '402883d429d9eec20129da4af98a0345', 'minus2_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus2_rtl.gif', null, '1', 1, 'resBase', 4829, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80463', '402883d429d9eec20129da4af98a0345', 'minus3.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus3.gif', null, '1', 1, 'resBase', 4830, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80464', '402883d429d9eec20129da4af98a0345', 'minus3_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus3_rtl.gif', null, '1', 1, 'resBase', 4831, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80465', '402883d429d9eec20129da4af98a0345', 'minus4.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus4.gif', null, '1', 1, 'resBase', 4832, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80466', '402883d429d9eec20129da4af98a0345', 'minus4_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus4_rtl.gif', null, '1', 1, 'resBase', 4833, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80467', '402883d429d9eec20129da4af98a0345', 'minus5.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus5.gif', null, '1', 1, 'resBase', 4834, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80468', '402883d429d9eec20129da4af98a0345', 'minus5_rtl.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus5_rtl.gif', null, '1', 1, 'resBase', 4835, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f80469', '402883d429d9eec20129da4af98a0345', 'minus_ar.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\minus_ar.gif', null, '1', 1, 'resBase', 4836, 'cms/fileIco/gif.gif');
insert into CMS_TEMPLATE_FILE (TEMP_FILE_ID, PARENT_TEMP_FILE_ID, TEMP_FILE_NAME, TEMP_FILE_PATH, MODIFY_TIME, IS_LEAF, FILE_SIZE, TEMPFILETYPE, SORT_NO, FILE_ICO)
values ('402883d429d9eec20129da4af9f8046a', '402883d429d9eec20129da4af98a0345', 'plus.gif', 'D:\workspace\gpcsoft-eps-cms\webapp\cms\resBase\plug-in\dhtmlxTree\sources\imgs\plus.gif', null, '1', 1, 'resBase', 4837, 'cms/fileIco/gif.gif');
commit;
prompt 1093 records loaded
prompt Loading CMS_VOTE_ITEM...
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429ceb6f30129cecbee79000a', '投票项1', 28, '402883d429ceb6f30129cec98a790004');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429ceb6f30129cecbee79000b', '投票项2', 27, '402883d429ceb6f30129cec98a790004');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429ceb6f30129cecbee79000c', '投票项3', 24, '402883d429ceb6f30129cec98a790004');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429ceb6f30129cecc346b000f', '投票项1', 4, '402883d429ceb6f30129cec9ae6c0006');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429ceb6f30129cecc346b0010', '投票项2', 5, '402883d429ceb6f30129cec9ae6c0006');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429ceb6f30129cecc346b0011', '投票项3', 6, '402883d429ceb6f30129cec9ae6c0006');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429cf6eff0129cfbd76330423', 'fdf ', 0, '402883d429cae95d0129caea60ce0003');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429cb5e890129cb61ffab0004', '111c', 4, '402883d429cae95d0129caea60ce0003');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429cb5e890129cb6205c50005', '222a', 5, '402883d429cae95d0129caea60ce0003');
insert into CMS_VOTE_ITEM (VOTE_ITEM_ID, TITLE, VOTE_COUNT, VOTE_TOPIC_ID)
values ('402883d429cb5e890129cb6205c50006', '333cfdf', 3, '402883d429cae95d0129caea60ce0003');
commit;
prompt 10 records loaded
prompt Loading CMS_VOTE_TOPIC...
insert into CMS_VOTE_TOPIC (VOTE_TOPIC_ID, TITLE, TOTAL_COUNT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, MULT_SELECT, START_TIME, END_TIME, CLOSE)
values ('402883d429cae95d0129caea60ce0003', 'fdsf', 12, '8a808083204329sr01204330167b0005', null, to_date('13-07-2010 16:27:24', 'dd-mm-yyyy hh24:mi:ss'), null, '1', to_date('21-07-2010', 'dd-mm-yyyy'), to_date('21-07-2010', 'dd-mm-yyyy'), '0');
insert into CMS_VOTE_TOPIC (VOTE_TOPIC_ID, TITLE, TOTAL_COUNT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, MULT_SELECT, START_TIME, END_TIME, CLOSE)
values ('402883d429ceb6f30129cec98a790004', '投票主题二', 73, '8a808083204329sr01204330167b0005', null, to_date('14-07-2010 10:30:01', 'dd-mm-yyyy hh24:mi:ss'), null, '1', to_date('14-07-2010', 'dd-mm-yyyy'), to_date('14-07-2010', 'dd-mm-yyyy'), '0');
insert into CMS_VOTE_TOPIC (VOTE_TOPIC_ID, TITLE, TOTAL_COUNT, CREATE_USER, MODIFY_USER, CREATE_TIME, MODIFY_TIME, MULT_SELECT, START_TIME, END_TIME, CLOSE)
values ('402883d429ceb6f30129cec9ae6c0006', '投票主题三', 15, '8a808083204329sr01204330167b0005', null, to_date('14-07-2010 10:30:10', 'dd-mm-yyyy hh24:mi:ss'), null, '0', to_date('14-07-2010', 'dd-mm-yyyy'), to_date('14-07-2010', 'dd-mm-yyyy'), '0');
commit;
prompt 3 records loaded
prompt Enabling triggers for CMS_CHANNEL...
alter table CMS_CHANNEL enable all triggers;
prompt Enabling triggers for CMS_CHANNEL_DATA...
alter table CMS_CHANNEL_DATA enable all triggers;
prompt Enabling triggers for CMS_CHANNEL_MODEL...
alter table CMS_CHANNEL_MODEL enable all triggers;
prompt Enabling triggers for CMS_CHANNEL_MODEL_ITEM...
alter table CMS_CHANNEL_MODEL_ITEM enable all triggers;
prompt Enabling triggers for CMS_NEWS...
alter table CMS_NEWS enable all triggers;
prompt Enabling triggers for CMS_TEMPLATE_FILE...
alter table CMS_TEMPLATE_FILE enable all triggers;
prompt Enabling triggers for CMS_VOTE_ITEM...
alter table CMS_VOTE_ITEM enable all triggers;
prompt Enabling triggers for CMS_VOTE_TOPIC...
alter table CMS_VOTE_TOPIC enable all triggers;
set feedback on
set define on
prompt Done.
