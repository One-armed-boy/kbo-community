CREATE TABLE permissions (
    permission_id BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (permission_id)
);

CREATE TABLE members (
    member_id BINARY(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    decrypted_password VARCHAR(255),
    permission_id BINARY(16) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    PRIMARY KEY (member_id)
);

CREATE TABLE auth_infos (
    auth_info_id BINARY(16) NOT NULL,
    member_id BINARY(16) NOT NULL,
    device_id BINARY(16) NOT NULL,
    refresh_token VARCHAR(512) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    expired_at TIMESTAMP(6) NOT NULL,
    deleted_at TIMESTAMP(6),
    PRIMARY KEY (auth_info_id)
);

CREATE UNIQUE INDEX uq__idx__email ON members (email);
CREATE INDEX idx__permission_id ON members (permission_id);

CREATE UNIQUE INDEX uq__idx__member_id__device_id ON auth_infos (member_id, device_id);