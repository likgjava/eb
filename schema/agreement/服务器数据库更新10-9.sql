
--重命名范本表中的‘区域编号’
ALTER TABLE EPS_TEMPLATE RENAME COLUMN DISTRICT_ID TO DISTRICT_CODE;

-- Add/modify columns 
alter table EPS_TEMPLATE add TEMPLATE_DESC VARCHAR2(500 CHAR);
-- Add comments to the columns 
comment on column EPS_TEMPLATE.TEMPLATE_DESC
  is '简要描述';

