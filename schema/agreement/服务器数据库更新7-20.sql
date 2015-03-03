-- 报名表增加标书费支付状态字段
alter table ECP_TENDER_APPLY_REC add DOCPRICE_PAYSTATUS CHAR(2);

comment on column ECP_TENDER_APPLY_REC.DOCPRICE_PAYSTATUS
  is '标书费支付状态';
  
--支付状态字段默认为00
alter table ECP_TENDER_APPLY_REC modify DOCPRICE_PAYSTATUS default '00';

-- 服务订阅表增加支付状态字段
alter table SERVICE_SUBSCRIBE add PAY_STATUS char(2) default '00';

comment on column SERVICE_SUBSCRIBE.PAY_STATUS
  is '支付状态';

  
-- 支付记录表
create table PAY_RECORD
(
  PAY_RECORD_ID     VARCHAR2(32) not null,
  PAY_NO            VARCHAR2(50) not null,
  PAY_AMOUNT        NUMBER,
  PAY_TIME          DATE,
  PAY_STATUS        VARCHAR2(20),
  PAY_BUSINESS_TYPE VARCHAR2(2),
  PAY_BUSINESS_ID   VARCHAR2(32),
  PAY_MODE          VARCHAR2(20),
  PAY_PERSON_NAME   VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column PAY_RECORD.PAY_RECORD_ID
  is '主键';
comment on column PAY_RECORD.PAY_NO
  is '支付号';
comment on column PAY_RECORD.PAY_AMOUNT
  is '支付金额';
comment on column PAY_RECORD.PAY_TIME
  is '支付时间';
comment on column PAY_RECORD.PAY_STATUS
  is '支付状态';
comment on column PAY_RECORD.PAY_BUSINESS_TYPE
  is '支付业务类型';
comment on column PAY_RECORD.PAY_BUSINESS_ID
  is '支付业务ID';
comment on column PAY_RECORD.PAY_MODE
  is '支付卡种';
comment on column PAY_RECORD.PAY_PERSON_NAME
  is '支付人';
