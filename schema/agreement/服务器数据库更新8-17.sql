
--重命名推荐项目表中的‘项目Id’
ALTER TABLE RECOMMEND_PROJECT RENAME COLUMN BULLETIN TO PROJECT_ID;

--迁移推荐项目数据
update recommend_project p set p.project_id = 
(
    select b.tenderid from ecp_base_bulletin b 
    where b.bulletin_id = p.project_id
);
commit;