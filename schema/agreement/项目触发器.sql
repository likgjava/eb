--作废项目，同时作废订单和合同
create or replace trigger update_project_order_contract
  before  update  of USESTATUS --指定列触发
  on ecp_tender_project
  for each row
begin
      --项目状态变为作废
      if :new.USESTATUS = '02'  then
         --更新合同状态为作废
         update EPS_PUB_CONTRACT c set c.use_status = '02' where c.contract_id in (
          select t.contract_id from EPS_AGREEMENT_ORDER t where t.project_id = :new.TENDERID
          );
         --更新订单状态为作废
         update EPS_AGREEMENT_ORDER t set t.use_status = '02' where t.project_id = :new.TENDERID;
      end if;
end;
