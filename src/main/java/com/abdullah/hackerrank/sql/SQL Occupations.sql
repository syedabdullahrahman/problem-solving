---- Solution by: Pivoting

SELECT Doctor, Professor, Singer, Actor FROM
(
    select o.name , o.occupation, DENSE_RANK() OVER (
    PARTITION BY o.occupation
        ORDER BY o.name
    ) rank_no
from OCCUPATIONS as o
) t
PIVOT(
    MAX( name)
    FOR occupation IN (Doctor, Professor, Singer, Actor)
) AS pivot_table;
