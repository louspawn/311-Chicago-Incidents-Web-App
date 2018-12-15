Stored Functions
----------------

For search by address and zip code
-- index on (zip_code, address)
-- index on zip_code
-- index on address

1.
CREATE FUNCTION function1(fromDate date, toDate date) RETURNS TABLE(f1 text, f2 bigint)
AS $$ 
SELECT request.type_of_service_request, count(*)
FROM request
WHERE DATE(request.creation_date) >= DATE(fromDate) and DATE(request.creation_date) <= DATE(toDate)
GROUP BY request.type_of_service_request
ORDER BY count(*) DESC
$$
LANGUAGE SQL;
-- index on creation_date

2.
CREATE FUNCTION function2(fromDate date, toDate date, typeOfRequest text) RETURNS TABLE(f1 date, f2 bigint)
AS $$ 
SELECT DATE(request.creation_date), count(*)
FROM request
WHERE request.type_of_service_request = typeOfRequest and DATE(request.creation_date) >= DATE(fromDate) and DATE(request.creation_date) <= DATE(toDate)
GROUP BY DATE(request.creation_date)
ORDER BY DATE(request.creation_date)
$$
LANGUAGE SQL;
-- index on composite key(type_of_service_request, creation_date)

3.
CREATE FUNCTION function3(currentDate date) RETURNS TABLE(f1 int, f2 text)
AS $$ 
SELECT foo.zip_code, (array_agg(foo.type_of_service_request))[1] 
FROM (
	SELECT request.zip_code, request.type_of_service_request, count(*) AS c
	FROM request
	WHERE DATE(request.creation_date) = DATE(currentDate)
	GROUP BY zip_code, type_of_service_request
	ORDER BY zip_code, count(*) DESC
) AS foo
GROUP BY foo.zip_code
$$
LANGUAGE SQL;
-- index on (creation_date, zip_code)

4.
CREATE FUNCTION function4(fromDate date, toDate date) RETURNS TABLE(f1 text, f2 float)
AS $$ 
SELECT request.type_of_service_request, avg(DATE_PART('day', completion_date - creation_date))
FROM request
WHERE DATE(request.creation_date) >= DATE(fromDate) and DATE(request.completion_date) <= DATE(toDate)
GROUP BY request.type_of_service_request
$$
LANGUAGE SQL;
-- index on creation_date AND
-- index on composite key(creation_date, type_of_service_request)

5.
CREATE FUNCTION function5(currentDate date, minLat float, maxLat float, minLong float, maxLong float) RETURNS TABLE(f1 text)
AS $$ 
SELECT type_of_service_request
FROM request
WHERE latitude >= minLat AND latitude <= maxLat AND longitude >= minLong AND longitude <= maxLong AND DATE(creation_date) = DATE(currentDate)
GROUP BY type_of_service_request
ORDER BY count(*) DESC
LIMIT 1
$$
LANGUAGE SQL;
-- index on creation_date
-- index on lat long?

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
-- index on type_of_service_request

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
-- index on color

9.
CREATE FUNCTION function9(uper_bound int) RETURNS TABLE(f1 text, f2 int)
AS $$ 
SELECT DISTINCT(r.service_request_number), b.number_of_premises_baited
FROM request AS r, rodent_baiting_request as b
WHERE r.id = b.id AND b.number_of_premises_baited < uper_bound
$$
LANGUAGE SQL;
-- index on number_of_premises_baited

10.
CREATE FUNCTION function11(uper_bound int) RETURNS TABLE(f1 text, f2 int)
AS $$ 
SELECT DISTINCT(r.service_request_number), b.number_of_premises_with_garbage
FROM request AS r, rodent_baiting_request as b
WHERE r.id = b.id AND b.number_of_premises_with_garbage < uper_bound
$$
LANGUAGE SQL;
-- index on number_of_premises_with_garbage

11.
CREATE FUNCTION function11(uper_bound int) RETURNS TABLE(f1 text, f2 int)
AS $$ 
SELECT DISTINCT(r.service_request_number), b.number_of_premises_with_rats
FROM request AS r, rodent_baiting_request as b
WHERE r.id = b.id AND b.number_of_premises_with_rats < uper_bound
$$
LANGUAGE SQL;
-- index on number_of_premises_with_rats





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

-- https://stackoverflow.com/questions/29437650/how-can-i-ensure-that-a-materialized-view-is-always-up-to-date

CREATE MATERIALIZED VIEW test_view2 AS
(
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
WHERE r.id = p.id)
)
WITH DATA;

-- Table abandoned_vehicles_request trigger
create or replace function trigger_on_request_revision()
    returns trigger
    language plpgsql as $body$

	Declare _id INT;
	Declare _current_activity VARCHAR(255);
	Declare _days_reported INT;
	Declare _license_plate VARCHAR(500);
	Declare _most_recent_action VARCHAR(255);
	Declare _ssa INT;
	Declare _vehicle_color VARCHAR(255);
	Declare _vehicle_model VARCHAR(255);
begin
    if (old.completion_date<>new.completion_date or 
        old.latitude<>new.latitude or 
        old.longitude<>new.longitude or
        old.status<>new.status or 
        old.street_address<>new.street_address or
        old.x_coordinate<>new.x_coordinate or
        old.y_coordinate<>new.y_coordinate or
        old.zip_code<>new.zip_code or
        old."location"<>new."location") then

        if old.type_of_service_request = 'ABANDONED_VEHICLE' then

            SELECT a.id, a.current_activity, a.days_reported, a.license_plate, a.most_recent_action, a.ssa, a.vehicle_color, a.vehicle_model INTO _id, _current_activity, _days_reported, _license_plate, _most_recent_action, _ssa, _vehicle_color, _vehicle_model 
			from abandoned_vehicles_request a 
			where a.id=new.id;
            
            insert into abandoned_vehicles_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, current_activity, days_reported, license_plate, most_recent_action, ssa, vehicle_color, vehicle_model) 
            values (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate, old.latitude, old.longitude, old.location, _current_activity, _days_reported, _license_plate, _most_recent_action, _ssa, _vehicle_color, _vehicle_model);
        end if;

    end if;
    return new;
end; $body$;

create trigger trigger_request_revision
  before update
  on request
  for each row
execute procedure trigger_on_request_revision();


create or replace function trigger_on_abandoned_vehicles_request_revision()
    returns trigger
    language plpgsql as $body$

	Declare _id INT;
	Declare _service_request_number VARCHAR(255);
	Declare _creation_date DATE;
	Declare _status VARCHAR(255);
	Declare _completion_date DATE;
	Declare _street_address VARCHAR(255);
	Declare _street_number INT;
	Declare _type_of_service_request VARCHAR(255);
	Declare _zip_code INT;
	Declare _x_coordinate FLOAT;
	Declare _y_coordinate FLOAT;
	Declare _latitude FLOAT;
	Declare _longitude FLOAT;
	Declare _location VARCHAR(255);
begin
    if (old.current_activity<>new.current_activity or 
        old.days_reported<>new.days_reported or 
        old.license_plate<>new.license_plate or
        old.most_recent_action<>new.most_recent_action or 
        old.ssa<>new.ssa or
        old.vehicle_color<>new.vehicle_color or
        old.vehicle_model<>new.vehicle_model) then

		SELECT r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		from request r 
		where r.id=new.id;
		
		insert into abandoned_vehicles_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, current_activity, days_reported, license_plate, most_recent_action, ssa, vehicle_color, vehicle_model) 
		values (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, old.current_activity, old.days_reported, old.license_plate, old.most_recent_action, old.ssa, old.vehicle_color, old.vehicle_model);

        -- insert into request_revisions (request_id, date_of_update, service_request_number, creation_date, status, completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location) 
        -- values (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate, old.latitude, old.longitude, old.location);
    end if;
    return new;
end; $body$;

create trigger trigger_abandoned_vehicles_request_revision
  before update
  on abandoned_vehicles_request
  for each row
execute procedure trigger_on_abandoned_vehicles_request_revision();


-- Check which fields, the user changed.

-- If field_only_from_request:
-- 	Update_request
-- If field_only_from_abandoned:
-- 	Update_abandoned
-- If field_from_abandoned and field_from_request:
-- 	Update_abandoned
-- 	Update_request

-- -- Update will be done with two queries.
-- -- 1. Update query on table (abandoned, garbage, etc...)
-- -- 2. Update request table (old values could be copied to the above table first)

-- -- Trigers only 