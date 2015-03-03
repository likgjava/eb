-- Add/modify columns 
alter table EPS_AGREEMENT_AREA add area_is_valid char(1);
-- Add comments to the columns 
comment on column EPS_AGREEMENT_AREA.area_is_valid
  is '是否有效';
  
-- Add/modify columns 
alter table EPS_AGREEMENT_AREA add area_usestatus CHAR(2);
-- Add comments to the columns 
comment on column EPS_AGREEMENT_AREA.area_usestatus
  is '使用状态';
  
  -- Create table
create table EPS_AGREE_PURCHASE_AGREEMENT
(
  agreement_id   VARCHAR2(50) not null,
  agreement_no   VARCHAR2(50) not null,
  org_id         VARCHAR2(50) not null,
  supplyer_id    VARCHAR2(50) not null,
  period_id      VARCHAR2(50) not null,
  agreement_file VARCHAR2(50),
  cre_time       DATE,
  cre_user_id    VARCHAR2(50),
  memo           VARCHAR2(200),
  use_status     CHAR(2),
  agreement_name VARCHAR2(100),
  modify_time    DATE,
  area_ids       VARCHAR2(500),
  area_names     VARCHAR2(500)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column EPS_AGREE_PURCHASE_AGREEMENT.agreement_id
  is 'id';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.agreement_no
  is '编码';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.org_id
  is '代理人';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.supplyer_id
  is '供应商';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.period_id
  is '"??????
"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.agreement_file
  is '"????
"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.cre_time
  is '"????
"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.cre_user_id
  is '"???
"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.memo
  is '"??
"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.use_status
  is '"????
00????01????02???"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.agreement_name
  is '??';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.modify_time
  is '????';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.area_ids
  is '"??????
"';
comment on column EPS_AGREE_PURCHASE_AGREEMENT.area_names
  is '区域名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_AGREE_PURCHASE_AGREEMENT
  add constraint PK_AGREEMENT primary key (AGREEMENT_ID)
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
