/**
*触发器 品牌审核通过后，更新相关联信息
*触发的表有：维护商,商品
**/
create or replace trigger update_usestatus_goodsbrand
  before  update  of manager_status --指定列触发
  on goods_brand
  for each row
begin
      --由临时变为有效状态
      if :old.manager_status = '00' and :new.manager_status = '01'  then
         if :old.current_valid_id is not null then
           DBMS_OUTPUT.PUT_LINE('oldCurrentId：' || :old.current_valid_id);
           DBMS_OUTPUT.PUT_LINE('newCurrentId：' || :new.current_valid_id);
           DBMS_OUTPUT.PUT_LINE('oldBrandId：' || :old.GOODS_BRAND_ID);
           DBMS_OUTPUT.PUT_LINE('newBrandId：' || :new.GOODS_BRAND_ID);
           --新增一批新品牌维护商
           insert into GOODS_MODIFIER (GOODS_MODIFIER_ID,GOODS_CLASS_ID,SUPPLIER_ID,GOODS_BRAND_ID ,CREATE_TIME,CREATOR_ID) 
                  select substr(m.goods_modifier_id,22,10) || 
                  substr(m.supplier_id,22,10) || 
                  substr(m.goods_brand_id,21,12) || '_', 
                  m.GOODS_CLASS_ID, 
                  m.supplier_id, 
                  :new.GOODS_BRAND_ID ,
                  m.CREATE_TIME, 
                  m.CREATOR_ID from goods_modifier m 
                  where m.goods_brand_id = :old.current_valid_id;
           --更新商品的品牌ID，为旧品牌的批量更新为新品牌ID(历史和报废品牌除外)
            update goods t set t.goods_brand_id = :new.GOODS_BRAND_ID where t.goods_brand_id = :old.current_valid_id
            and  t.manger_status <> '02';
         end if;
      end if;
end;
