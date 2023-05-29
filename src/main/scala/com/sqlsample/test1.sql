-- 行列转换，多行转多列
-- 多行转多列
-- 5.分组 2.对分组进行 聚合[MAX] + case when 筛选 5.多次聚合 + case when 制作多列
-- a    b   c
--2055  B   9
--2055  A   50
--2055  B   7
--2055  A   8
select
       a,
       MAX(case b when 'A' then c else 0 end) as col_A,
       MAX(case b when 'B' then c else 0 end) as col_B
from t5
group by a

-- 再给我转回去
-- 多列转多行
-- noinspection SqlNoDataSourceInspection

select
       a,
       'A' as b,
       col_A as c
from temp
union all
select
       a,
       'B' as b,
       col_B as c
from temp


-- 问题三
select
    a,
       b,
       c,
       row_number() over (partition by a order by b asc)
from tmp

