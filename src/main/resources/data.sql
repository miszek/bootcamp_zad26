INSERT INTO Match (date, teamA, teamB, result, winacourse, draw_course, winbcourse)
VALUES
    ('2022-12-11', 'Poland', 'Mexico', '1:0', 2.4, 6.0, 1.9),
    ('2022-12-13', 'Poland', 'Argentina', '1:0', 5.9, 1.5, 1.1);

INSERT INTO Match (date, teamA, teamB, winacourse, draw_course, winbcourse)
VALUES
    ('2022-12-16', 'Poland', 'Saudi Arabia', 2.4, 6.0, 1.9),
    ('2022-12-19', 'France', 'Poland', 1.2, 6.0, 5.4),
    ('2023-02-22', 'Argentina', 'France', 1.4, 5.0, 1.6),
    ('2023-01-25', 'Spain', 'Greece', 1.1, 3.5, 3.7),
    ('2023-01-23', 'Germany', 'Italy', 2.0, 4.1, 1.9);

INSERT INTO Bet (bet_value, typer_Name, match_id, winner, id_string)
VALUES
    (100, 'miszek', 1, 'WINA', '2319491'),
    (40, 'pawels', 1, 'WINB', '2319501'),
    (55, 'lukasd', 1, 'DRAW', '2319511'),
    (200, 'miszek', 1, 'WINA', '2319521'),
    (350, 'pawels', 2, 'WINB', '2319532'),
    (15, 'lukasd', 3, 'DRAW', '2319503'),
    (20, 'miszek', 3, 'WINA', '2319513'),
    (100, 'miroslaws', 3, 'WINB', '2319523'),
    (200, 'waldekg',2, 'DRAW', '2319502'),
    (130, 'waldekg',6, 'WINA', '2319506'),
    (80, 'romeks',6, 'WINB', '2319516'),
    (45, 'romeks',5,'DRAW', '2319505');

INSERT INTO
    application_user (first_name, last_name, email, password)
VALUES
    ('Piotr', 'Nowak', 'admin@example.com', '{noop}test'),
    ('Jan', 'Kowalski', 'jank@example.com', '{noop}test');

INSERT INTO
    user_role (name, description)
VALUES
    ('ADMIN', 'pełen dostęp'),
    ('USER', 'dostęp do panelu i opcji usera');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);