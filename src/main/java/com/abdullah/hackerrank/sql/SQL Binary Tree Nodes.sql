select distinct l.N, CASE
when l.p is null then 'Root'
when i.n is null then 'Leaf'
when i.n is not null then 'Inner'
end
from BST l
left join BST i on l.N = i.p
order by l.n

------- Inputs --------

-- 1 2
-- 3 2
-- 5 6
-- 7 6
-- 2 4
-- 6 4
-- 4 15
-- 8 9
-- 10 9
-- 12 13
-- 14 13
-- 9 11
-- 13 11
-- 11 15
-- 15 NULL