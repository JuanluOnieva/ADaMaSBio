-- POSTGRESQL PLSQL FUNCTION

CREATE FUNCTION drop_constraints() RETURNS integer AS $$
DECLARE
    each_row RECORD;
BEGIN
    RAISE NOTICE 'Dropping constraints...';

    FOR each_row IN SELECT tablename,indexname FROM pg_indexes WHERE tablename IN (SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname='public')
    LOOP
        -- Now "mviews" has one record from cs_materialized_views
        RAISE NOTICE 'Dropping constraint %s on table %s',each_row.indexname, each_row.tablename;
        EXECUTE format('ALTER TABLE %s DROP CONSTRAINT %s CASCADE;',each_row.tablename, each_row.indexname);
    END LOOP;

    RAISE NOTICE 'Done dropping constraints.';
    RETURN 1;
END;
$$ LANGUAGE plpgsql;

--Usage: SELECT drop_constraints();
