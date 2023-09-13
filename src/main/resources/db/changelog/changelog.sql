--liquibase formatted sql

--changeset apejkovic:1

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    name VARCHAR(255),
    created_date DATE
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(36) NOT NULL UNIQUE
);

INSERT INTO roles VALUES
	(1, 'ADMIN'),
	(2, 'USER');

CREATE TABLE users_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(36) NOT NULL UNIQUE
);

INSERT INTO permissions VALUES
	(1, 'READ'),
	(2, 'WRITE'),
	(3, 'UPDATE'),
	(4, 'DELETE'),
	(5, 'GRANT_ADMIN'),
	(6, 'UNRESTRICTED');

CREATE TABLE roles_permissions (
    role_id INT(11) NOT NULL,
    permission_id INT(11) NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
  );

INSERT INTO roles_permissions (role_id, permission_id) VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (2, 1);
