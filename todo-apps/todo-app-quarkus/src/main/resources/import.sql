INSERT INTO todo(id, title, completed, ordering) VALUES (nextval('todo_id_seq'), 'Introduction to Quarkus', true, 0);
INSERT INTO todo(id, title, completed, ordering) VALUES (nextval('todo_id_seq'), 'Hibernate with Panache', false, 1);
INSERT INTO todo(id, title, completed, ordering) VALUES (nextval('todo_id_seq'), 'Visit Quarkus web site', false, 2);
INSERT INTO todo(id, title, completed, ordering) VALUES (nextval('todo_id_seq'), 'Star Quarkus project', false, 3);

ALTER SEQUENCE todo_id_seq RESTART WITH 20;