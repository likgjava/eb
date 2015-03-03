--修改商品品牌的品牌序列号
update goods_brand gb set gb.brand_code = 'B' || to_char(gb.create_time, 'yyyy') || LPAD(rownum, 6, '0')
where to_char(gb.create_time, 'yyyy') = '2010';

update goods_brand gb set gb.brand_code = 'B' || to_char(gb.create_time, 'yyyy') || LPAD(rownum, 6, '0')
where to_char(gb.create_time, 'yyyy') = '2011';

--在序列表中添加记录
insert into base_sequence_number
(SEN_ID, SEN_BIZ_NAME, SEN_SEQUENCE_NO)
select 'B' || substr(gb.brand_code, 2, 4) , 'B' || substr(gb.brand_code, 2, 4), count(*)
from goods_brand gb
group by substr(gb.brand_code, 2, 4);