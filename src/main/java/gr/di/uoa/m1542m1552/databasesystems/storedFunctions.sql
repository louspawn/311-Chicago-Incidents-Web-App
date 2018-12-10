Stored Functions
----------------

1.
CREATE FUNCTION function1(fromDate date, toDate date) RETURNS TABLE(f1 text, f2 bigint)
AS $$ 
SELECT request.type_of_service_request, count(*)
FROM request
WHERE request.creation_date >= fromDate and request.creation_date <= toDate
GROUP BY request.type_of_service_request
ORDER BY count(*) DESC
$$
LANGUAGE SQL;

2.
CREATE FUNCTION function2(fromDate date, toDate date, typeOfRequest text) RETURNS TABLE(f1 date, f2 bigint)
AS $$ 
SELECT DATE(request.creation_date), count(*)
FROM request
WHERE request.creation_date >= fromDate and request.creation_date <= toDate and request.type_of_service_request = typeOfRequest
GROUP BY DATE(request.creation_date)
ORDER BY DATE(request.creation_date)
$$
LANGUAGE SQL;

3.
CREATE FUNCTION function3(currentDate date) RETURNS TABLE(f1 int, f2 text)
AS $$ 
SELECT foo.zip_code, (array_agg(foo.type_of_service_request))[1] FROM (
SELECT request.zip_code, request.type_of_service_request, count(*) AS c
FROM request
WHERE request.creation_date = currentDate 
GROUP BY zip_code, type_of_service_request
ORDER BY zip_code, count(*) DESC
) AS foo
GROUP BY foo.zip_code
$$
LANGUAGE SQL;

4.
CREATE FUNCTION function4(fromDate date, toDate date) RETURNS TABLE(f1 text, f2 float)
AS $$ 
SELECT request.type_of_service_request, avg(DATE_PART('day', completion_date - creation_date))
FROM request
WHERE DATE(request.creation_date) >= DATE(fromDate) and DATE(request.completion_date) <= DATE(toDate)
GROUP BY request.type_of_service_request
$$
LANGUAGE SQL;

5.
CREATE FUNCTION function5(currentDate date, minLat float, maxLat float, minLong float, maxLong float) RETURNS TABLE(f1 text)
AS $$ 
SELECT type_of_service_request
FROM request
WHERE latitude >= minLat AND latitude <= maxLat AND longitude >= minLong AND longitude <= maxLong AND DATE(creation_date) = currentDate
GROUP BY type_of_service_request
ORDER BY count(*) DESC
LIMIT 1
$$
LANGUAGE SQL;

6.
CREATE FUNCTION function6(fromDate date, toDate date) RETURNS TABLE(f1 int, f2 bigint)
AS $$ 
SELECT ssa, count(*)
FROM test_view
WHERE DATE(creation_date) >= DATE(fromDate) and DATE(creation_date) <= DATE(toDate) AND ssa > 0
GROUP BY ssa
ORDER BY count(*) DESC
LIMIT 5
$$
LANGUAGE SQL;

7.
CREATE FUNCTION function7() RETURNS TABLE(f1 text)
AS $$ 
SELECT lic.license_plate
FROM (
	SELECT a.license_plate
	FROM request AS r, abandoned_vehicles_request AS a
	WHERE r.id = a.id
	GROUP BY r.service_request_number, a.license_plate
) AS lic
WHERE lic.license_plate not in ('', 'NONE', 'UNKNOWN', 'TEMP', 'NO INFO BY CALLER', 'UNK') AND lic.license_plate NOT LIKE '%NO%PLATE%' AND lic.license_plate NOT LIKE '%?%' AND lic.license_plate NOT LIKE '%---%'
GROUP BY lic.license_plate
HAVING count(*) >= 2
$$
LANGUAGE SQL;

8.
CREATE FUNCTION function8() RETURNS TABLE(f1 text)
AS $$ 
SELECT vehicle_color
FROM abandoned_vehicles_request 
GROUP BY vehicle_color
ORDER BY count(*) DESC
LIMIT 1 OFFSET 1
$$
LANGUAGE SQL;

9.
CREATE FUNCTION function9(uper_bound int) RETURNS TABLE(f1 text, f2 int)
AS $$ 
SELECT DISTINCT(r.service_request_number), b.number_of_premises_baited
FROM request AS r, rodent_baiting_request as b
WHERE r.id = b.id AND b.number_of_premises_baited < uper_bound
$$
LANGUAGE SQL;

10.
CREATE FUNCTION function11(uper_bound int) RETURNS TABLE(f1 text, f2 int)
AS $$ 
SELECT DISTINCT(r.service_request_number), b.number_of_premises_with_garbage
FROM request AS r, rodent_baiting_request as b
WHERE r.id = b.id AND b.number_of_premises_with_garbage < uper_bound
$$
LANGUAGE SQL;

11.
CREATE FUNCTION function11(uper_bound int) RETURNS TABLE(f1 text, f2 int)
AS $$ 
SELECT DISTINCT(r.service_request_number), b.number_of_premises_with_rats
FROM request AS r, rodent_baiting_request as b
WHERE r.id = b.id AND b.number_of_premises_with_rats < uper_bound
$$
LANGUAGE SQL;

12.




Create View
-----------
CREATE VIEW test_view AS

(SELECT r.id, a.ssa, r.creation_date
FROM  request as r, abandoned_vehicles_request as a
WHERE r.id = a.id)
    UNION
(SELECT r.id, g.ssa, r.creation_date 
FROM  request as r, garbage_carts_request as g
WHERE r.id = g.id)
    UNION
(SELECT r.id, g.ssa, r.creation_date 
FROM  request as r, graffiti_removal_request as g
WHERE r.id = g.id)
    UNION
(SELECT r.id, p.ssa, r.creation_date 
FROM  request as r, pot_holes_request as p
WHERE r.id = p.id);
