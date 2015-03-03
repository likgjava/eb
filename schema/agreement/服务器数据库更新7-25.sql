-- 手机短信表
create table EPS_PUB_MOBILE_MESSAGE
(
  MB_MSG_ID     VARCHAR2(50) not null,
  MOBILE_NUMBER VARCHAR2(20),
  CONTENT       VARCHAR2(200),
  SENDER        VARCHAR2(50),
  SENDER_NAME   VARCHAR2(100),
  SEND_TIME     DATE,
  RECEIVER      VARCHAR2(50),
  RECEIVER_NAME VARCHAR2(100),
  CREUSER       VARCHAR2(50),
  CREUSERNAME   VARCHAR2(100),
  CREDATE       DATE
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
comment on table EPS_PUB_MOBILE_MESSAGE
  is '短信表';
-- Add comments to the columns 
comment on column EPS_PUB_MOBILE_MESSAGE.MB_MSG_ID
  is '主键';
comment on column EPS_PUB_MOBILE_MESSAGE.MOBILE_NUMBER
  is '电话';
comment on column EPS_PUB_MOBILE_MESSAGE.CONTENT
  is '内容';
comment on column EPS_PUB_MOBILE_MESSAGE.SENDER
  is '发送人';
comment on column EPS_PUB_MOBILE_MESSAGE.SENDER_NAME
  is '发送人姓名';
comment on column EPS_PUB_MOBILE_MESSAGE.SEND_TIME
  is '发送时间';
comment on column EPS_PUB_MOBILE_MESSAGE.RECEIVER
  is '接收人';
comment on column EPS_PUB_MOBILE_MESSAGE.RECEIVER_NAME
  is '接收人姓名';
comment on column EPS_PUB_MOBILE_MESSAGE.CREUSER
  is '创建人';
comment on column EPS_PUB_MOBILE_MESSAGE.CREUSERNAME
  is '创建人姓名';
comment on column EPS_PUB_MOBILE_MESSAGE.CREDATE
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EPS_PUB_MOBILE_MESSAGE
  add constraint EPS_PUB_MOBILE_MESSAGE_PK primary key (MB_MSG_ID)
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
