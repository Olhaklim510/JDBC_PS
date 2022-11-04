SELECT client.name, count (project.id) AS "PROJECT_COUNT"
FROM project
JOIN client ON project.client_id=client.id
WHERE client.id IN (
    SELECT client_id,
    FROM project
    GROUP BY (client_id)
    HAVING count(id) IN (
        SELECT COUNT(id),
        FROM project
        GROUP BY (client_id)
        ORDER BY COUNT(id) DESC
        LIMIT 1
    )
) GROUP BY client.name