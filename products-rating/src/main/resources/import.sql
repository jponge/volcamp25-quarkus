-- Products
INSERT INTO product (id, name, description) VALUES (1, 'AlphaPhone', 'Smartphone with long battery life and dual cameras');
INSERT INTO product (id, name, description) VALUES (2, 'BetaWatch', 'Smartwatch with fitness tracking and heart rate monitoring');
INSERT INTO product (id, name, description) VALUES (3, 'GammaBuds', 'Wireless earbuds with active noise cancellation');
INSERT INTO product (id, name, description) VALUES (4, 'DeltaPad', 'Lightweight tablet designed for reading and streaming');
INSERT INTO product (id, name, description) VALUES (5, 'EpsilonLaptop', 'Compact laptop suitable for work and travel');

-- Reviews for AlphaPhone
INSERT INTO review (id, rating, product_id, comment) VALUES (1, 5, 1, 'Excellent performance and great battery life');
INSERT INTO review (id, rating, product_id, comment) VALUES (2, 2, 1, 'Camera quality is disappointing');
INSERT INTO review (id, rating, product_id, comment) VALUES (3, 4, 1, 'Solid device, but a bit bulky');

-- Reviews for BetaWatch
INSERT INTO review (id, rating, product_id, comment) VALUES (4, 5, 2, 'Very useful for tracking workouts');
INSERT INTO review (id, rating, product_id, comment) VALUES (5, 3, 2, 'Good overall, but battery drains quickly');

-- Reviews for GammaBuds
INSERT INTO review (id, rating, product_id, comment) VALUES (6, 4, 3, 'Sound quality is clear and immersive');
INSERT INTO review (id, rating, product_id, comment) VALUES (7, 1, 3, 'Noise cancellation barely works');
INSERT INTO review (id, rating, product_id, comment) VALUES (8, 5, 3, 'Comfortable fit and strong bass');

-- Reviews for DeltaPad
INSERT INTO review (id, rating, product_id, comment) VALUES (9, 3, 4, 'Nice for reading, but performance is average');
INSERT INTO review (id, rating, product_id, comment) VALUES (10, 5, 4, 'Perfect size for travel and entertainment');

-- Reviews for EpsilonLaptop
INSERT INTO review (id, rating, product_id, comment) VALUES (11, 4, 5, 'Great for work, light and portable');
INSERT INTO review (id, rating, product_id, comment) VALUES (12, 2, 5, 'Keyboard feels cheap and uncomfortable');

-- Generated ID Sequences

ALTER SEQUENCE product_seq RESTART WITH 6;
ALTER SEQUENCE review_seq RESTART WITH 13;