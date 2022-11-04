SELECT*
FROM (
(SELECT 'YOUNGEST' AS TYPE, name, birthday AS "BIRTHDAY"
FROM worker
WHERE birthday=SELECT MAX (birthday) FROM worker)
UNION
(SELECT 'ELDEST' AS TYPE, name, birthday AS "BIRTHDAY"
FROM worker
WHERE birthday=SELECT MIN (birthday) FROM worker)
) ORDER BY TYPE DESC






