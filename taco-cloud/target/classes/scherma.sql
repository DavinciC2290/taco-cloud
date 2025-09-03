CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	username VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	street VARCHAR(50) NOT NULL,
	city VARCHAR(30) NOT NULL,
	user_state VARCHAR(30) NOT NULL,
	zip VARCHAR(10) NOT NULL

);


CREATE TABLE ingredient(
	id VARCHAR(10) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	type VARCHAR(10) NOT NULL
);


-- The relationship between users and taco_order tables is established for foreign key column "user_id"
-- One-to-many relationship
-- A user can order many taco orders.
-- One taco order can only belong to a one user
CREATE TABLE taco_order (
    id SERIAL PRIMARY KEY,
    placed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_name VARCHAR(50) NOT NULL,
    delivery_street VARCHAR(50) NOT NULL,
    delivery_city VARCHAR(50) NOT NULL,
    delivery_state VARCHAR(10) NOT NULL,
    delivery_zip VARCHAR(10) NOT NULL,
	contact_phone VARCHAR(10) NOT NULL,
    cc_number VARCHAR(20) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
	user_id INTEGER REFERENCES users(id) ON DELETE CASCADE
);


-- Relationship between taco and taco_order tables is established for foreign key column "taco_order_id"
-- (one-to-many relationship)
-- A taco order can contain many tacos
-- A custom taco can only belong to one taco order.
CREATE TABLE taco(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)  NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	taco_order_id INTEGER REFERENCES taco_order(id) ON DELETE CASCADE
);


-- Relationship between taco and ingredient tables (many-to-many relationship)
-- A taco can cotain many ingredients
-- the same ingredient can be in many tacos,
CREATE TABLE taco_ingredients(
	taco_id INTEGER REFERENCES taco(id) ON DELETE CASCADE,
	ingredient_id VARCHAR(10) REFERENCES ingredient(id),
	PRIMARY KEY(taco_id, ingredient_id)
);

INSERT INTO ingredient(id, name, type) VALUES
('FLTO', 'Flour Tortilla', 'WRAP'),
('COTO', 'Corn Tortilla', 'WRAP'),
('GRBF', 'Ground Beef', 'PROTEIN'),
('CARN', 'Carnitas', 'PROTEIN'),
('TMTO', 'Diced Tomatoes', 'VEGGIES'),
('LETC', 'Lettuce', 'VEGGIES'),
('CHED', 'Cheddar', 'CHEESE'),
('JACK', 'Monterrey Jack', 'CHEESE'),
('SLSA', 'Salsa', 'SAUCE'),
('SRCR', 'Sour Cream', 'SAUCE');

SELECT CURRENT_USER;