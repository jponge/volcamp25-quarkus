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
INSERT INTO review (id, rating, product_id, comment) VALUES (4, 5, 1, 'Screen is bright and colors are vivid');
INSERT INTO review (id, rating, product_id, comment) VALUES (5, 3, 1, 'Battery lasts long, but charging is slow');
INSERT INTO review (id, rating, product_id, comment) VALUES (6, 4, 1, 'User interface is intuitive');
INSERT INTO review (id, rating, product_id, comment) VALUES (7, 1, 1, 'Phone overheats during gaming');
INSERT INTO review (id, rating, product_id, comment) VALUES (8, 5, 1, 'Great value for the price');
INSERT INTO review (id, rating, product_id, comment) VALUES (9, 2, 1, 'Speaker quality is poor');
INSERT INTO review (id, rating, product_id, comment) VALUES (10, 4, 1, 'Good camera in daylight, weak in low light');

-- Reviews for BetaWatch
INSERT INTO review (id, rating, product_id, comment) VALUES (11, 5, 2, 'Very useful for tracking workouts');
INSERT INTO review (id, rating, product_id, comment) VALUES (12, 3, 2, 'Good overall, but battery drains quickly');
INSERT INTO review (id, rating, product_id, comment) VALUES (13, 4, 2, 'Comfortable to wear all day');
INSERT INTO review (id, rating, product_id, comment) VALUES (14, 2, 2, 'Screen scratches easily');
INSERT INTO review (id, rating, product_id, comment) VALUES (15, 5, 2, 'Heart rate monitor is accurate');
INSERT INTO review (id, rating, product_id, comment) VALUES (16, 3, 2, 'Notifications sometimes arrive late');
INSERT INTO review (id, rating, product_id, comment) VALUES (17, 4, 2, 'Good design and lightweight');
INSERT INTO review (id, rating, product_id, comment) VALUES (18, 1, 2, 'Stopped working after a month');
INSERT INTO review (id, rating, product_id, comment) VALUES (19, 5, 2, 'Perfect for running and cycling');
INSERT INTO review (id, rating, product_id, comment) VALUES (20, 3, 2, 'Charging cable feels flimsy');

-- Reviews for GammaBuds
INSERT INTO review (id, rating, product_id, comment) VALUES (21, 4, 3, 'Sound quality is clear and immersive');
INSERT INTO review (id, rating, product_id, comment) VALUES (22, 1, 3, 'Noise cancellation barely works');
INSERT INTO review (id, rating, product_id, comment) VALUES (23, 5, 3, 'Comfortable fit and strong bass');
INSERT INTO review (id, rating, product_id, comment) VALUES (24, 4, 3, 'Easy to pair with my phone');
INSERT INTO review (id, rating, product_id, comment) VALUES (25, 2, 3, 'Battery drains after two hours');
INSERT INTO review (id, rating, product_id, comment) VALUES (26, 5, 3, 'Case is compact and charges fast');
INSERT INTO review (id, rating, product_id, comment) VALUES (27, 3, 3, 'Fit is okay but slips during running');
INSERT INTO review (id, rating, product_id, comment) VALUES (28, 4, 3, 'Good balance of treble and bass');
INSERT INTO review (id, rating, product_id, comment) VALUES (29, 1, 3, 'Left bud stopped working');
INSERT INTO review (id, rating, product_id, comment) VALUES (30, 5, 3, 'Great for calls, microphone is clear');

-- Reviews for DeltaPad
INSERT INTO review (id, rating, product_id, comment) VALUES (31, 3, 4, 'Nice for reading, but performance is average');
INSERT INTO review (id, rating, product_id, comment) VALUES (32, 5, 4, 'Perfect size for travel and entertainment');
INSERT INTO review (id, rating, product_id, comment) VALUES (33, 4, 4, 'Screen resolution is sharp');
INSERT INTO review (id, rating, product_id, comment) VALUES (34, 2, 4, 'Slow when running multiple apps');
INSERT INTO review (id, rating, product_id, comment) VALUES (35, 5, 4, 'Battery lasts all weekend');
INSERT INTO review (id, rating, product_id, comment) VALUES (36, 3, 4, 'Speakers are decent but lack bass');
INSERT INTO review (id, rating, product_id, comment) VALUES (37, 4, 4, 'Great for streaming videos');
INSERT INTO review (id, rating, product_id, comment) VALUES (38, 1, 4, 'Stopped charging after a few weeks');
INSERT INTO review (id, rating, product_id, comment) VALUES (39, 5, 4, 'Lightweight and easy to carry around');
INSERT INTO review (id, rating, product_id, comment) VALUES (40, 2, 4, 'Touchscreen is unresponsive sometimes');

-- Reviews for EpsilonLaptop
INSERT INTO review (id, rating, product_id, comment) VALUES (41, 4, 5, 'Great for work, light and portable');
INSERT INTO review (id, rating, product_id, comment) VALUES (42, 2, 5, 'Keyboard feels cheap and uncomfortable');
INSERT INTO review (id, rating, product_id, comment) VALUES (43, 5, 5, 'Boots up quickly and runs smoothly');
INSERT INTO review (id, rating, product_id, comment) VALUES (44, 3, 5, 'Battery life is average');
INSERT INTO review (id, rating, product_id, comment) VALUES (45, 4, 5, 'Display is bright and sharp');
INSERT INTO review (id, rating, product_id, comment) VALUES (46, 1, 5, 'Overheats during video calls');
INSERT INTO review (id, rating, product_id, comment) VALUES (47, 5, 5, 'Lightweight and easy to travel with');
INSERT INTO review (id, rating, product_id, comment) VALUES (48, 2, 5, 'Trackpad feels unresponsive');
INSERT INTO review (id, rating, product_id, comment) VALUES (49, 4, 5, 'Good performance for office apps');
INSERT INTO review (id, rating, product_id, comment) VALUES (50, 3, 5, 'Fan noise is noticeable under load');

-- Generated ID Sequences
ALTER SEQUENCE product_seq RESTART WITH 6;
ALTER SEQUENCE review_seq RESTART WITH 51;
