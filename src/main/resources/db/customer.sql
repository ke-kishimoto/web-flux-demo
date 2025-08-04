CREATE TABLE customer AS
WITH RECURSIVE seq(id) AS (
  SELECT 1
  UNION ALL
  SELECT id + 1 FROM seq WHERE id < 100000
)
SELECT
  id,
  '顧客' || id AS name
FROM seq;