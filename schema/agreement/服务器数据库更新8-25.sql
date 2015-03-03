-- Create table
create table EPS_ASSESSMENT_RULE
(
  ASSESSMENT_RULE_ID VARCHAR2(50) not null,
  ASSESSMENT_FILE    VARCHAR2(50) not null,
  CATEGORY_ID        VARCHAR2(500) not null,
  CATEGORY_NAME      VARCHAR2(500) not null,
  CREATOR            VARCHAR2(50),
  CREATE_TIME        DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table EPS_ASSESSMENT_RULE
  is '评审规则表';
-- Add comments to the columns 
comment on column EPS_ASSESSMENT_RULE.ASSESSMENT_RULE_ID
  is 'ID
';
comment on column EPS_ASSESSMENT_RULE.ASSESSMENT_FILE
  is '区域名称
';
comment on column EPS_ASSESSMENT_RULE.CATEGORY_ID
  is '品目id';
comment on column EPS_ASSESSMENT_RULE.CATEGORY_NAME
  is '品目名称';
comment on column EPS_ASSESSMENT_RULE.CREATOR
  is '上级区域
';
comment on column EPS_ASSESSMENT_RULE.CREATE_TIME
  is '备注
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_ASSESSMENT_RULE
  add constraint PK_ASSESSMENT_RULE_ID primary key (ASSESSMENT_RULE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
  -- Add/modify columns 
alter table EPS_ASSESSMENT_RULE add ASSESSMENT_RULE_NAME VARCHAR2(200);
-- Add comments to the columns 
comment on column EPS_ASSESSMENT_RULE.ASSESSMENT_RULE_NAME
  is '规则名称';
  
-- Add/modify columns 
alter table ECP_PROJECT_SIGN_INFO add ASSESSMENT_FILE VARCHAR2(50);
-- Add comments to the columns 
comment on column ECP_PROJECT_SIGN_INFO.ASSESSMENT_FILE
  is '附件';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ECP_PROJECT_SIGN_INFO
  add constraint fk_PROJECT_SIGN foreign key (ASSESSMENT_FILE)
  references base_attachment (RES_ID);