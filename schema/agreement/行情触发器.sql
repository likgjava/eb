--更新商品行情
create or replace trigger update_goods_price
  before  update  of manger_status --指定列触发
  on goods
  for each row
begin
      --由临时变为有效状态
      if :old.manger_status = '00' and :new.manger_status = '01'  then
         if :old.current_valid_id is not null then
           DBMS_OUTPUT.PUT_LINE('currentId：' || :old.current_valid_id);
           --更新行情供应商
           update goods_price_supplier t set t.Goods_Id = :new.goods_id where t.goods_id = :old.current_valid_id;
         end if;
      end if;
end;
