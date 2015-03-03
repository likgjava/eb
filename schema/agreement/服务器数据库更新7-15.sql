insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c9087f0311d5bd301311d721a56000a', '违法记录', 'IllegalRecController.do', '0', '0', '1', '402886d4266d4a0a01266d4b752c0002#402886dc29f2a12c0129f2fc5a2000d7#2c9087f0311d5bd301311d721a56000a', 3, '402886dc29f2a12c0129f2fc5a2000d7', 'URL', null, '12-7月 -11 04.24.02.390000 下午', 'all');



insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c9087f0311d5bd301311d721a560009', '', '违法记录', '1', null, '0', '', '402886dc29f2a12c0129f2fc5a2000d6', '', '0', '1', '402886d4266d4a0a01266d4c90600005#402886dc29f2a12c0129f2fc5a2000d6#2c9087f0311d5bd301311d721a560009', 3, '2c9087f0311d5bd301311d721a56000a', '12-7月 -11 04.24.02.390000 下午');



insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402886dc2a6949d5012a694c96360007', '2c9087f0311d5bd301311d721a56000a', '1', '');


-- Create table
create table ECP_PUB_ILLEGAL_RECORD
(
  ILLEGAL_RECORD_ID VARCHAR2(36) not null,
  ORGINFO_ID        VARCHAR2(36),
  TITLE             VARCHAR2(200),
  REASON            VARCHAR2(500),
  IS_SHOW           CHAR(1),
  CREATE_TIME       DATE,
  CREATOR_ID        VARCHAR2(36)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ECP_PUB_ILLEGAL_RECORD
  is '违法记录 ';
-- Add comments to the columns 
comment on column ECP_PUB_ILLEGAL_RECORD.ILLEGAL_RECORD_ID
  is 'Id
';
comment on column ECP_PUB_ILLEGAL_RECORD.ORGINFO_ID
  is '违法机构id';
comment on column ECP_PUB_ILLEGAL_RECORD.TITLE
  is '违规标题';
comment on column ECP_PUB_ILLEGAL_RECORD.REASON
  is '违规详情';
comment on column ECP_PUB_ILLEGAL_RECORD.IS_SHOW
  is '是否显示';
comment on column ECP_PUB_ILLEGAL_RECORD.CREATE_TIME
  is '创建时间';
comment on column ECP_PUB_ILLEGAL_RECORD.CREATOR_ID
  is '创建人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PUB_ILLEGAL_RECORD
  add constraint PK_ILLEGAL_RECORD_ID primary key (ILLEGAL_RECORD_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
--违法记录资源sql  
insert into auth_resource (RES_ID, RES_NAME, RES_URL, RES_IS_LOG, RES_IS_SYS, RES_IS_LEAF, RES_PATH, RES_LEVEL, RES_PARENT_ID, RES_TYPE, RES_SORT, CREATE_TIME, SYS_FLAG)
values ('2c9087f03199269e0131992a4bf70003', '我的违法记录', 'IllegalRecController.do?method=toIllegalRecOrgList', '0', '0', '1', '402886d4266d4a0a01266d4b752c0002#402886dc29f2a12c0129f2fc5a2000d7#2c9087f03199269e0131992a4bf70003', 3, '402886dc29f2a12c0129f2fc5a2000d7', 'URL', null, '05-8月 -11 04.58.31.287000 下午', 'all');

insert into auth_menu (MNU_ID, MNU_MEMO, MNU_NAME, MNU_SHOWFLAG, MNU_SORT, MNU_TARGET, MNU_TIP, MNU_PARENT_ID, MNU_ICON, MNU_IS_DEFAULT, MNU_IS_LEAF, MNU_PATH, MNU_LEVEL, RES_ID, CREATE_TIME)
values ('2c9087f03199269e0131992a4bf70002', '', '我的违法记录', '1', null, '0', '', '402886dc29f2a12c0129f2fc5a2000d6', '', '0', '1', '402886d4266d4a0a01266d4c90600005#402886dc29f2a12c0129f2fc5a2000d6#2c9087f03199269e0131992a4bf70002', 3, '2c9087f03199269e0131992a4bf70003', '05-8月 -11 04.58.31.287000 下午');

insert into auth_role_resource (ROL_ID, RES_ID, ISCHECKED, CREATE_TIME)
values ('402885ef2a9e3ae4012aa1b810ca005e', '2c9087f03199269e0131992a4bf70003', '1', '');






