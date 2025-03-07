INSERT INTO category (description, name) VALUES
('A category for electronic devices', 'Electronics'),
('Clothing and apparel', 'Apparel'),
('Books and educational material', 'Books'),
('Furniture for home and office', 'Furniture'),
('Outdoor and camping gear', 'Outdoors');


INSERT INTO product (description, name, available_quantity, price, category_id) VALUES
('Smartphone with latest features', 'Smartphone Model X', 120, 799.99, 1),
('Stylish jacket for winter', 'Winter Jacket', 50, 99.99, 2),
('Python programming book', 'Learn Python', 200, 29.99, 3),
('Wooden desk with modern design', 'Modern Desk', 30, 149.99, 4),
('Camping tent for 4 people', '4-Person Tent', 60, 249.99, 5);
