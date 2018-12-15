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

12.
CREATE FUNCTION function12(selectedDate date) RETURNS TABLE(f1 int) 
AS $$
SELECT b.police_district
FROM (
    (SELECT r.police_district as police_district
     FROM request as r, pot_holes_request as p
     WHERE DATE(r.completion_date) = DATE(selectedDate) AND r.status = 'Completed' AND p.number_of_pot_holes_filled_on_block > 1 AND r.id = p.id)
  INTERSECT
    (SELECT r.police_district as police_district
    FROM request as r, rodent_baiting_request as rb
    WHERE DATE(r.completion_date) = DATE(selectedDate) AND r.status = 'Completed' AND rb.number_of_premises_baited > 1 AND r.id = rb.id)
) as b
$$
LANGUAGE SQL;
-- index on date



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
	Declare _days_reported INT;
	Declare _license_plate VARCHAR(500);
	Declare _current_activity VARCHAR(255);
	Declare _most_recent_action VARCHAR(255);
	Declare _ssa INT;
	Declare _vehicle_color VARCHAR(255);
	Declare _vehicle_model VARCHAR(255);

	Declare _number_of_black_carts_delivered INT;
	Declare _number_of_pot_holes_filled_on_block INT;
	Declare _number_of_premises_baited INT;
	Declare _number_of_premises_with_garbage INT;
	Declare _number_of_premises_with_rats INT;

	Declare _type_of_surface_is_on VARCHAR(255);
	Declare _where_is_located VARCHAR(255);
	Declare _nature_of_code_violation VARCHAR(255);
begin
    if (old.completion_date IS DISTINCT FROM new.completion_date or 
        old.latitude IS DISTINCT FROM new.latitude or 
        old.longitude IS DISTINCT FROM new.longitude or
        old.status IS DISTINCT FROM new.status or 
        old.street_address IS DISTINCT FROM new.street_address or
        old.x_coordinate IS DISTINCT FROM new.x_coordinate or
        old.y_coordinate IS DISTINCT FROM new.y_coordinate or
        old.zip_code IS DISTINCT FROM new.zip_code or
        old."location" IS DISTINCT FROM new."location") then

        if old.type_of_service_request = 'ABANDONED_VEHICLE' then
            SELECT a.id, a.current_activity, a.days_reported, a.license_plate, a.most_recent_action, a.ssa, a.vehicle_color, a.vehicle_model 
            INTO _id, _current_activity, _days_reported, _license_plate, _most_recent_action, _ssa, _vehicle_color, _vehicle_model 
			from abandoned_vehicles_request a 
			where a.id=new.id;
            
            insert into abandoned_vehicles_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location, 
                        current_activity, days_reported, license_plate, most_recent_action, ssa, vehicle_color, vehicle_model) 
            values (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate, 
                    old.latitude, old.longitude, old.location, _current_activity, _days_reported, _license_plate, _most_recent_action, 
                    _ssa, _vehicle_color, _vehicle_model);
        end if;
        if old.type_of_service_request = 'ALLEY_LIGHTS_OUT' then
            insert into alley_lights_out_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location) 
            values (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location);
        end if;
        if old.type_of_service_request = 'GARBAGE_CARTS' then
            SELECT g.id, g.current_activity, g.most_recent_action, g.ssa, g.number_of_black_carts_delivered
            INTO _id, _current_activity, _most_recent_action, _ssa, _number_of_black_carts_delivered 
			FROM garbage_carts_request g 
			WHERE g.id=new.id;

            INSERT INTO garbage_carts_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        number_of_black_carts_delivered, ssa, current_activity, most_recent_action                         ) 
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _number_of_black_carts_delivered, _ssa, _current_activity, _most_recent_action);
        end if;
        if old.type_of_service_request = 'GRAFFITI_REMOVAL' then
            SELECT g.id, g.type_of_surface_is_on, g.where_is_located, g.ssa
            INTO _id, _type_of_surface_is_on, _where_is_located, _ssa
			FROM graffiti_removal_request g 
			WHERE g.id=new.id;

            INSERT INTO graffiti_removal_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        type_of_surface_is_on, where_is_located, ssa)
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _type_of_surface_is_on, _where_is_located, _ssa);
        end if;
        if old.type_of_service_request = 'POT_HOLES' then
            SELECT g.id, g.current_activity, g.most_recent_action, g.ssa, g.number_of_pot_holes_filled_on_block
            INTO _id, _current_activity, _most_recent_action, _ssa, _number_of_pot_holes_filled_on_block 
			FROM pot_holes_request g 
			WHERE g.id=new.id;

            INSERT INTO pot_holes_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        number_of_pot_holes_filled_on_block, ssa, current_activity, most_recent_action                         ) 
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _number_of_pot_holes_filled_on_block, _ssa, _current_activity, _most_recent_action);
        end if;
        if old.type_of_service_request = 'RODENT_BAITING' then
            SELECT g.id, g.current_activity, g.most_recent_action, g.number_of_premises_baited, g.number_of_premises_with_garbage, g.number_of_premises_with_garbage
            INTO _id, _current_activity, _most_recent_action, _number_of_premises_baited, _number_of_premises_with_garbage, _number_of_premises_with_rats
			FROM rodent_baiting_request g 
			WHERE g.id=new.id;

            INSERT INTO rodent_baiting_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        current_activity, most_recent_action, number_of_premises_baited, number_of_premises_with_garbage, number_of_premises_with_rats) 
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _current_activity, _most_recent_action, _number_of_premises_baited, _number_of_premises_with_garbage, _number_of_premises_with_rats);
        end if;
        if old.type_of_service_request = 'SANITATION_CODE_COMPLAINTS' then
            SELECT g.id, g.nature_of_code_violation
            INTO _id, _nature_of_code_violation
			FROM sanitation_code_complaints_request g 
			WHERE g.id=new.id;

            INSERT INTO sanitation_code_complaints_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        nature_of_code_violation) 
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _nature_of_code_violation);
        end if;
        if old.type_of_service_request = 'LIGHT_ONE_OUT' then
            insert into light_one_out_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location) 
            values (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location);
        end if;
        if old.type_of_service_request = 'LIGHTS_ALL_OUT' then
            insert into lights_all_out_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location) 
            values (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location);
        end if;
        if old.type_of_service_request = 'TREE_DEBRIS' then
            SELECT g.id, g.current_activity, g.most_recent_action, g.where_is_located
            INTO _id, _current_activity, _most_recent_action, _where_is_located
			FROM tree_debris_request g 
			WHERE g.id=new.id;

            INSERT INTO tree_debris_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        current_activity, most_recent_action, where_is_located) 
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _current_activity, _most_recent_action, _where_is_located);
        end if;
        if old.type_of_service_request = 'TREE_TRIMS' then
            SELECT g.id, g.where_is_located
            INTO _id, _where_is_located
			FROM tree_trims_request g 
			WHERE g.id=new.id;

            INSERT INTO tree_trims_request_revisions (id, date_of_update, service_request_number, creation_date, status, 
                        completion_date, type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, 
                        latitude, longitude, location,
                        where_is_located) 
            VALUES (old.id, current_timestamp, old.service_request_number, old.creation_date, old.status, old.completion_date, 
                    old.type_of_service_request, old.street_address, old.street_number, old.zip_code, old.x_coordinate, old.y_coordinate,
                    old.latitude, old.longitude, old.location,
                    _where_is_located);
        end if;
    end if;
    return new;
end; $body$;

create trigger trigger_request_revision
  before update
  on request
  for each row
execute procedure trigger_on_request_revision();


-- Abandoned Vehicles
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
    if (old.current_activity IS DISTINCT FROM new.current_activity or 
        old.days_reported IS DISTINCT FROM new.days_reported or 
        old.license_plate IS DISTINCT FROM new.license_plate or
        old.most_recent_action IS DISTINCT FROM new.most_recent_action or 
        old.ssa IS DISTINCT FROM new.ssa or
        old.vehicle_color IS DISTINCT FROM new.vehicle_color or
        old.vehicle_model IS DISTINCT FROM new.vehicle_model) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO abandoned_vehicles_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    current_activity, days_reported, license_plate, most_recent_action, ssa, vehicle_color, vehicle_model) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.current_activity, old.days_reported, old.license_plate, old.most_recent_action, old.ssa, old.vehicle_color, 
                old.vehicle_model);

    end if;
    return new;
end; $body$;

create trigger trigger_abandoned_vehicles_request_revision
  before update
  on abandoned_vehicles_request
  for each row
execute procedure trigger_on_abandoned_vehicles_request_revision();


-- Garbage Carts
create or replace function trigger_on_garbage_carts_request_revision()
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
    if (old.current_activity IS DISTINCT FROM new.current_activity or 
        old.most_recent_action IS DISTINCT FROM new.most_recent_action or 
        old.ssa IS DISTINCT FROM new.ssa or
        old.number_of_black_carts_delivered IS DISTINCT FROM new.number_of_black_carts_delivered) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO garbage_carts_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    current_activity, most_recent_action, ssa, number_of_black_carts_delivered) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.current_activity, old.most_recent_action, old.ssa, old.number_of_black_carts_delivered);

    end if;
    return new;
end; $body$;

create trigger trigger_garbage_carts_request_revision
  before update
  on garbage_carts_request
  for each row
execute procedure trigger_on_garbage_carts_request_revision();

-- Graffiti Removal
create or replace function trigger_on_graffiti_removal_request_revision()
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
    if (old.type_of_surface_is_on IS DISTINCT FROM new.type_of_surface_is_on or 
        old.where_is_located IS DISTINCT FROM new.where_is_located or 
        old.ssa IS DISTINCT FROM new.ssa) then
		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO graffiti_removal_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    type_of_surface_is_on, where_is_located, ssa) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.type_of_surface_is_on, old.where_is_located, old.ssa);

    end if;
    return new;
end; $body$;

create trigger trigger_graffiti_removal_request_revision
  before update
  on graffiti_removal_request
  for each row
execute procedure trigger_on_graffiti_removal_request_revision();


-- Pot Holes
create or replace function trigger_on_pot_holes_request_revision()
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
    if (old.current_activity IS DISTINCT FROM new.current_activity or 
        old.most_recent_action IS DISTINCT FROM new.most_recent_action or 
        old.ssa IS DISTINCT FROM new.ssa or
        old.number_of_pot_holes_filled_on_block IS DISTINCT FROM new.number_of_pot_holes_filled_on_block) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO pot_holes_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    current_activity, most_recent_action, ssa, number_of_pot_holes_filled_on_block) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.current_activity, old.most_recent_action, old.ssa, old.number_of_pot_holes_filled_on_block);

    end if;
    return new;
end; $body$;

create trigger trigger_pot_holes_request_revision
  before update
  on pot_holes_request
  for each row
execute procedure trigger_on_pot_holes_request_revision();


-- Rodent Baiting
create or replace function trigger_on_rodent_baiting_request_revision()
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
    if (old.current_activity IS DISTINCT FROM new.current_activity or 
        old.most_recent_action IS DISTINCT FROM new.most_recent_action or 
        old.number_of_premises_baited IS DISTINCT FROM new.number_of_premises_baited or
        old.number_of_premises_with_garbage IS DISTINCT FROM new.number_of_premises_with_garbage or
        old.number_of_premises_with_rats IS DISTINCT FROM new.number_of_premises_with_rats) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO rodent_baiting_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    current_activity, most_recent_action, number_of_premises_baited, number_of_premises_with_garbage, number_of_premises_with_rats) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.current_activity, old.most_recent_action, old.number_of_premises_baited, old.number_of_premises_with_garbage, old.number_of_premises_with_rats);

    end if;
    return new;
end; $body$;

create trigger trigger_rodent_baiting_request_revision
  before update
  on rodent_baiting_request
  for each row
execute procedure trigger_on_rodent_baiting_request_revision();


-- Sanitation Code Complaints
create or replace function trigger_on_sanitation_code_complaints_request_revision()
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
    if (old.nature_of_code_violation IS DISTINCT FROM new.nature_of_code_violation) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO sanitation_code_complaints_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    nature_of_code_violation) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.nature_of_code_violation);

    end if;
    return new;
end; $body$;

create trigger trigger_sanitation_code_complaints_request_revision
  before update
  on sanitation_code_complaints_request
  for each row
execute procedure trigger_on_sanitation_code_complaints_request_revision();


--Tree Debris
create or replace function trigger_on_tree_debris_request_revision()
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
    if (old.current_activity IS DISTINCT FROM new.current_activity or 
        old.most_recent_action IS DISTINCT FROM new.most_recent_action or 
        old.where_is_located IS DISTINCT FROM new.where_is_located) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO tree_debris_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    current_activity, most_recent_action, where_is_located) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.current_activity, old.most_recent_action, old.where_is_located);

    end if;
    return new;
end; $body$;

create trigger trigger_tree_debris_request_revision
  before update
  on tree_debris_request
  for each row
execute procedure trigger_on_tree_debris_request_revision();


--Tree Trims
create or replace function trigger_on_tree_trims_request_revision()
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
    if (old.where_is_located IS DISTINCT FROM new.where_is_located) then

		SELECT  r.id, r.service_request_number, r.creation_date, r.status, r.completion_date, r.street_address, r.street_number, r.zip_code, 
                r.x_coordinate, r.y_coordinate, r.latitude, r.longitude, r.location, r.type_of_service_request 
        INTO _id, _service_request_number, _creation_date, _status, _completion_date, _street_address, _street_number, _zip_code,
             _x_coordinate, _y_coordinate, _latitude, _longitude, _location, _type_of_service_request
		FROM request r 
		WHERE r.id=new.id;

		INSERT INTO tree_trims_request_revisions (id, date_of_update, service_request_number, creation_date, status, completion_date, 
                    type_of_service_request, street_address, street_number, zip_code, x_coordinate, y_coordinate, latitude, longitude, location, 
                    where_is_located) 
		VALUES (old.id, current_timestamp, _service_request_number, _creation_date, _status, _completion_date, _type_of_service_request, 
                _street_address, _street_number, _zip_code, _x_coordinate, _y_coordinate, _latitude, _longitude, _location, 
                old.where_is_located);

    end if;
    return new;
end; $body$;

create trigger trigger_tree_trims_request_revision
  before update
  on tree_trims_request
  for each row
execute procedure trigger_on_tree_trims_request_revision();




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