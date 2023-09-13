--liquibase formatted sql

--changeset apejkovic:1

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    name VARCHAR(255),
    created_date DATE
);

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(36) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(36) NOT NULL UNIQUE
);

CREATE TABLE roles_permissions (
    role_id BIGINT,
    permission_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE CASCADE
  );

CREATE TABLE business (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    director VARCHAR(255),
    registration_number VARCHAR(255),
    tax_number VARCHAR(255),
    bank_account VARCHAR(255),
    street VARCHAR(255),
    zip VARCHAR(255),
    city VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    website VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    image BLOB,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE condiments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    category_id BIGINT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE food (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    image BLOB,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number INT,
    creation_date date,
    order_status VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity DOUBLE,
    food_id BIGINT,
    order_id BIGINT,
    FOREIGN KEY (food_id) REFERENCES food (id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE TABLE items_condiments (
    condiment_id BIGINT,
    item_id BIGINT,
    FOREIGN KEY (condiment_id) REFERENCES condiments (id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);

--changeset apejkovic:2

INSERT INTO roles VALUES
	(1, 'USER'),
	(2, 'ADMIN'),
	(3, 'MASTER_ADMIN');

INSERT INTO permissions VALUES
	(1, 'READ'),
	(2, 'WRITE'),
	(3, 'UPDATE'),
	(4, 'DELETE'),
	(5, 'GRANT_ADMIN'),
	(6, 'UNRESTRICTED');

INSERT INTO roles_permissions (role_id, permission_id) VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (3, 5),
    (3, 6),
    (2, 1);

INSERT INTO users VALUES
(
    1,
    'pejko89',
    '$2a$10$c4k24Pk4lNy/v9wEZRsuT.LrTsYRLK7Jj7.mLahhCZwCgoWwAY7IW',
    'pejko89.ap@gmail.com',
    'Aleksandar Pejković',
    '2023-09-13'
);

INSERT INTO users_roles VALUES
    (1, 1),
    (1, 2),
    (1, 3);
