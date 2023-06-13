INSERT INTO role (id, description, title)
SELECT 1, 'Роль пользователя', 'USER'
    WHERE NOT EXISTS (SELECT 1 FROM role WHERE id = 1)
UNION ALL
SELECT 2, 'Роль библиотекаря', 'LIBRARIAN'
    WHERE NOT EXISTS (SELECT 1 FROM role WHERE id = 2);

