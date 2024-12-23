INSERT INTO permissions (permission_id, name) VALUES
    (CAST(RANDOM_UUID() AS BINARY(16)), 'ADMIN'),
    (CAST(RANDOM_UUID() AS BINARY(16)), 'NORMAL');