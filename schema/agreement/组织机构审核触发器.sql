--初始化会员触发器，发生在机构审核通过后
create or replace trigger audit_org_init_member
  before  update  of USE_STATUS --指定列触发
  on biz_supplier
  for each row

  
DECLARE
  companyId varchar(50);
  memberCount NUMBER(18,6);
  memberId varchar(50);
  PRAGMA AUTONOMOUS_TRANSACTION;
begin
      --由临时变为有效状态
      if :old.USE_STATUS = '00' and :new.USE_STATUS = '01'  then
         DBMS_OUTPUT.PUT_LINE('supplier_id--' || :old.SUPPLIER_ID);
         select s.company_id into companyId from biz_supplier s where s.supplier_id=:old.SUPPLIER_ID;
         DBMS_OUTPUT.PUT_LINE('supplier_id--' || companyId);
         select count(m.member_id) into memberCount from member m where m.org_info_id=companyId;
         --如果还不是会员则添加会员
         if(memberCount <= 0) then   
           insert into member values(SYS_GUID(),companyId,0,0,'C00',:new.CREATOR,:new.CREATE_TIME,null,null);
           commit;
         end if;
      end if;
end;
