create or replace trigger change_contract_status
 after  update of use_status --指定列触发
 on EPS_PUB_CONTRACT 
 for each row
DECLARE 
  dec_goods_total NUMBER(18,6);
  dec_buyer_id VARCHAR2(50);
  --ORACLE DB里默认在写TRIGGER的时候把本表锁死,不允许对其进行操作，当对某个表T进行update时，
  --在trigger的body或trigger调用的存储过程又有对update表的查询，这时常会出错误。
  --治标的解决办法利用自治事物进行解决。
  PRAGMA AUTONOMOUS_TRANSACTION;
begin
 --当合同状态由临时变为正式时
  if :old.use_status = '00' and :new.use_status = '01'  then
     --DBMS_OUTPUT.PUT_LINE('合同'||:old.contract_id ||'变更为正式合同');
     DBMS_OUTPUT.PUT_LINE('当前采购人'||dec_buyer_id);
     select b.buyer_id into dec_buyer_id from buy_buyer b , org_info o where o.buyer_id = b.buyer_id and o.org_info_id = :new.buyer_id;
     --修改采购人的成交时间
     update buy_buyer  set deal_time = :new.contract_begin_time where buyer_id = dec_buyer_id;
     
     DBMS_OUTPUT.PUT_LINE('当前采购人的交易总金额'||dec_goods_total);
     select sum(GOODS_TOTAL) into dec_goods_total from EPS_AGREEMENT_ORDER  where CONTRACT_ID in (select c.contract_id from EPS_PUB_CONTRACT c where c.USE_STATUS='01' and c.buyer_id = :new.buyer_id);
     --修改采购人的交易总额
     update buy_buyer  set deal_total = dec_goods_total where buyer_id = dec_buyer_id;
 end if ;
 commit;
 end;
