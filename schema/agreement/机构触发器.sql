create or replace trigger update_usestatus_orginfo
  before  update  of use_status --指定列触发
  on org_info
  for each row
begin
      --由临时变为有效状态
      if :old.use_status = '00' and :new.use_status = '01'  then
         if :old.current_valid_id is not null then
           DBMS_OUTPUT.PUT_LINE('currentId：' || :old.current_valid_id);
           --更新资质
           update ORG_QUALIFICATION q set q.BELONG_OBJECT_ID = :new.org_info_id where q.belong_object_id = :old.current_valid_id;
           --更新案例
           update ECP_PUB_SUCCESS_CASE s set s.ORGINFO_ID = :new.org_info_id where s.ORGINFO_ID = :old.current_valid_id;
           --更新评价
           update ECP_PUB_EVALUATE e set e.ORGINFO_ID = :new.org_info_id where e.ORGINFO_ID = :old.current_valid_id;
           --更新收藏
           update ECP_PUB_FAVORITES f set f.favorites_object_id = :new.org_info_id where f.favorites_object_id = :old.current_valid_id;
           --更新维护商
           update goods_modifier m set m.supplier_id = :new.org_info_id where m.supplier_id = :old.current_valid_id;
           --更新项目的创建机构ID
           update ecp_tender_project t set t.buyers_id = replace(t.buyers_id,:old.current_valid_id,:new.org_info_id)
                  where t.buyers_id like '%'||:old.current_valid_id||'%';
         end if;
      end if;
end;
