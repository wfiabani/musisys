INSERT INTO musics (id, title, author, musical_key) VALUES
('00000001-0000-0000-0000-000000000001', 'Smells Like Teen Spirit', 'Nirvana', 'F'),
('00000002-0000-0000-0000-000000000002', 'Come As You Are', 'Nirvana', 'E'),
('00000003-0000-0000-0000-000000000003', 'Wonderwall', 'Oasis', 'F'),
('00000004-0000-0000-0000-000000000004', 'Don’t Look Back in Anger', 'Oasis', 'C'),
('00000005-0000-0000-0000-000000000005', 'Losing My Religion', 'R.E.M.', 'G'),
('00000006-0000-0000-0000-000000000006', 'Enter Sandman', 'Metallica', 'E'),
('00000007-0000-0000-0000-000000000007', 'Nothing Else Matters', 'Metallica', 'E'),
('00000008-0000-0000-0000-000000000008', 'Californication', 'Red Hot Chili Peppers', 'A'),
('00000009-0000-0000-0000-000000000009', 'Under the Bridge', 'Red Hot Chili Peppers', 'E'),
('00000010-0000-0000-0000-000000000010', 'Creep', 'Radiohead', 'G'),
('00000011-0000-0000-0000-000000000011', 'High and Dry', 'Radiohead', 'G'),
('00000012-0000-0000-0000-000000000012', 'Zombie', 'The Cranberries', 'E'),
('00000013-0000-0000-0000-000000000013', 'Linger', 'The Cranberries', 'D'),
('00000014-0000-0000-0000-000000000014', 'Black', 'Pearl Jam', 'E'),
('00000015-0000-0000-0000-000000000015', 'Alive', 'Pearl Jam', 'A'),
('00000016-0000-0000-0000-000000000016', 'Basket Case', 'Green Day', 'E'),
('00000017-0000-0000-0000-000000000017', 'When I Come Around', 'Green Day', 'G'),
('00000018-0000-0000-0000-000000000018', 'November Rain', 'Guns N’ Roses', 'C'),
('00000019-0000-0000-0000-000000000019', 'Sweet Child O’ Mine', 'Guns N’ Roses', 'D'),
('00000020-0000-0000-0000-000000000020', 'Iris', 'Goo Goo Dolls', 'A'),
('00000021-0000-0000-0000-000000000021', 'Always', 'Bon Jovi', 'E'),
('00000022-0000-0000-0000-000000000022', 'Bed of Roses', 'Bon Jovi', 'C'),
('00000023-0000-0000-0000-000000000023', 'My Heart Will Go On', 'Celine Dion', 'E'),
('00000024-0000-0000-0000-000000000024', 'I Don’t Want to Miss a Thing', 'Aerosmith', 'D'),
('00000025-0000-0000-0000-000000000025', 'Unchained Melody', 'The Righteous Brothers', 'C'),
('00000026-0000-0000-0000-000000000026', 'Endless Love', 'Lionel Richie & Diana Ross', 'G'),
('00000027-0000-0000-0000-000000000027', 'All of Me', 'John Legend', 'A'),
('00000028-0000-0000-0000-000000000028', 'Perfect', 'Ed Sheeran', 'G'),
('00000029-0000-0000-0000-000000000029', 'Thinking Out Loud', 'Ed Sheeran', 'D'),
('00000030-0000-0000-0000-000000000030', 'Careless Whisper', 'George Michael', 'D'),
('00000031-0000-0000-0000-000000000031', 'Every Breath You Take', 'The Police', 'G'),
('00000032-0000-0000-0000-000000000032', 'With or Without You', 'U2', 'D'),
('00000033-0000-0000-0000-000000000033', 'Still Loving You', 'Scorpions', 'C'),
('00000034-0000-0000-0000-000000000034', 'Is This Love', 'Whitesnake', 'G'),
('00000035-0000-0000-0000-000000000035', 'Nothing’s Gonna Change My Love for You', 'George Benson', 'F'),
('00000036-0000-0000-0000-000000000036', 'I Will Always Love You', 'Whitney Houston', 'A'),
('00000037-0000-0000-0000-000000000037', 'Right Here Waiting', 'Richard Marx', 'C'),
('00000038-0000-0000-0000-000000000038', 'Heaven', 'Bryan Adams', 'D'),
('00000039-0000-0000-0000-000000000039', 'All My Love', 'Led Zeppelin', 'A'),
('00000040-0000-0000-0000-000000000040', 'Faithfully', 'Journey', 'E');

INSERT INTO setlists (id, name) VALUES
('aaaaaaaa-0000-0000-0000-000000000001', 'Rock 90s - Set 1'),
('aaaaaaaa-0000-0000-0000-000000000002', 'Rock 90s - Set 2'),
('aaaaaaaa-0000-0000-0000-000000000003', 'Rock 90s - Set 3'),
('aaaaaaaa-0000-0000-0000-000000000004', 'Rock 90s - Set 4'),
('aaaaaaaa-0000-0000-0000-000000000005', 'Love Songs - Set 1'),
('aaaaaaaa-0000-0000-0000-000000000006', 'Love Songs - Set 2'),
('aaaaaaaa-0000-0000-0000-000000000007', 'Love Songs - Set 3'),
('aaaaaaaa-0000-0000-0000-000000000008', 'Love Songs - Set 4'),
('aaaaaaaa-0000-0000-0000-000000000009', 'Mixed - Set 1'),
('aaaaaaaa-0000-0000-0000-000000000010', 'Mixed - Set 2'),
('aaaaaaaa-0000-0000-0000-000000000011', 'Mixed - Set 3'),
('aaaaaaaa-0000-0000-0000-000000000012', 'Mixed - Set 4');

INSERT INTO setlist_items (id, setlist_id, music_id, position) VALUES
('bbbbbbbb-0000-0000-0000-000000000001','aaaaaaaa-0000-0000-0000-000000000001','00000001-0000-0000-0000-000000000001',1),
('bbbbbbbb-0000-0000-0000-000000000002','aaaaaaaa-0000-0000-0000-000000000001','00000002-0000-0000-0000-000000000002',2),
('bbbbbbbb-0000-0000-0000-000000000003','aaaaaaaa-0000-0000-0000-000000000001','00000003-0000-0000-0000-000000000003',3),
('bbbbbbbb-0000-0000-0000-000000000004','aaaaaaaa-0000-0000-0000-000000000001','00000004-0000-0000-0000-000000000004',4),
('bbbbbbbb-0000-0000-0000-000000000005','aaaaaaaa-0000-0000-0000-000000000001','00000005-0000-0000-0000-000000000005',5),
('bbbbbbbb-0000-0000-0000-000000000006','aaaaaaaa-0000-0000-0000-000000000001','00000006-0000-0000-0000-000000000006',6),
('bbbbbbbb-0000-0000-0000-000000000007','aaaaaaaa-0000-0000-0000-000000000001','00000007-0000-0000-0000-000000000007',7),
('bbbbbbbb-0000-0000-0000-000000000008','aaaaaaaa-0000-0000-0000-000000000001','00000008-0000-0000-0000-000000000008',8),
('bbbbbbbb-0000-0000-0000-000000000009','aaaaaaaa-0000-0000-0000-000000000001','00000009-0000-0000-0000-000000000009',9),
('bbbbbbbb-0000-0000-0000-000000000010','aaaaaaaa-0000-0000-0000-000000000001','00000010-0000-0000-0000-000000000010',10),
('bbbbbbbb-0000-0000-0000-000000000011','aaaaaaaa-0000-0000-0000-000000000001','00000011-0000-0000-0000-000000000011',11),
('bbbbbbbb-0000-0000-0000-000000000012','aaaaaaaa-0000-0000-0000-000000000001','00000012-0000-0000-0000-000000000012',12),
('bbbbbbbb-0000-0000-0000-000000000013','aaaaaaaa-0000-0000-0000-000000000001','00000013-0000-0000-0000-000000000013',13),
('bbbbbbbb-0000-0000-0000-000000000014','aaaaaaaa-0000-0000-0000-000000000001','00000014-0000-0000-0000-000000000014',14),
('bbbbbbbb-0000-0000-0000-000000000015','aaaaaaaa-0000-0000-0000-000000000001','00000015-0000-0000-0000-000000000015',15),
('bbbbbbbb-0000-0000-0000-000000000016','aaaaaaaa-0000-0000-0000-000000000001','00000016-0000-0000-0000-000000000016',16),
('bbbbbbbb-0000-0000-0000-000000000017','aaaaaaaa-0000-0000-0000-000000000001','00000017-0000-0000-0000-000000000017',17),
('bbbbbbbb-0000-0000-0000-000000000018','aaaaaaaa-0000-0000-0000-000000000001','00000018-0000-0000-0000-000000000018',18),
('bbbbbbbb-0000-0000-0000-000000000019','aaaaaaaa-0000-0000-0000-000000000001','00000019-0000-0000-0000-000000000019',19),
('bbbbbbbb-0000-0000-0000-000000000020','aaaaaaaa-0000-0000-0000-000000000001','00000020-0000-0000-0000-000000000020',20),
('bbbbbbbb-0000-0000-0000-000000000021','aaaaaaaa-0000-0000-0000-000000000001','00000021-0000-0000-0000-000000000021',21),
('bbbbbbbb-0000-0000-0000-000000000022','aaaaaaaa-0000-0000-0000-000000000001','00000022-0000-0000-0000-000000000022',22),
('bbbbbbbb-0000-0000-0000-000000000023','aaaaaaaa-0000-0000-0000-000000000001','00000023-0000-0000-0000-000000000023',23),
('bbbbbbbb-0000-0000-0000-000000000024','aaaaaaaa-0000-0000-0000-000000000001','00000024-0000-0000-0000-000000000024',24),
('bbbbbbbb-0000-0000-0000-000000000025','aaaaaaaa-0000-0000-0000-000000000001','00000025-0000-0000-0000-000000000025',25),
('bbbbbbbb-0000-0000-0000-000000000026','aaaaaaaa-0000-0000-0000-000000000001','00000026-0000-0000-0000-000000000026',26);


INSERT INTO events (id, type, date_time, location, notes, setlist_id) VALUES
('eeeeeeee-0000-0000-0000-000000000001', 'SHOW',     '2025-02-15T21:00:00', 'Teatro Municipal',        'Show principal da temporada', 'aaaaaaaa-0000-0000-0000-000000000001'),
('eeeeeeee-0000-0000-0000-000000000002', 'SHOW',     '2025-03-01T22:00:00', 'Arena Open Air',          'Evento ao ar livre',          'aaaaaaaa-0000-0000-0000-000000000002'),
('eeeeeeee-0000-0000-0000-000000000003', 'SHOW',     '2025-03-20T20:30:00', 'Pub Rock Station',        'Show intimista',              NULL),

('eeeeeeee-0000-0000-0000-000000000004', 'REHEARSAL','2025-02-10T19:00:00', 'Estúdio Central',         'Ensaio geral pré-show',       'aaaaaaaa-0000-0000-0000-000000000001'),
('eeeeeeee-0000-0000-0000-000000000005', 'REHEARSAL','2025-02-12T19:00:00', 'Estúdio Central',         'Ajustes finais',              NULL),

('eeeeeeee-0000-0000-0000-000000000006', 'MEETING',  '2025-02-05T18:00:00', 'Sala de Reuniões',        'Planejamento da turnê',       NULL),
('eeeeeeee-0000-0000-0000-000000000007', 'MEETING',  '2025-02-18T18:30:00', 'Sala de Reuniões',        'Revisão de repertório',       'aaaaaaaa-0000-0000-0000-000000000009'),

('eeeeeeee-0000-0000-0000-000000000008', 'SHOW',     '2025-04-10T21:30:00', 'Festival Rock Sul',       'Palco secundário',            'aaaaaaaa-0000-0000-0000-000000000003'),
('eeeeeeee-0000-0000-0000-000000000009', 'SHOW',     '2025-05-01T20:00:00', 'Centro de Eventos',       'Show comemorativo',           'aaaaaaaa-0000-0000-0000-000000000005'),

('eeeeeeee-0000-0000-0000-000000000010', 'REHEARSAL','2025-04-05T20:00:00', 'Estúdio Central',         'Ensaio especial para festival',NULL);

-- Eventos futuros (2026), para demonstrar profissionais sem vínculo passado
INSERT INTO events (id, type, date_time, location, notes, setlist_id) VALUES
('eeeeeeee-0000-0000-0000-000000000011', 'SHOW',     '2026-08-15T20:00:00', 'Praça Central',           'Show de aniversário da cidade', 'aaaaaaaa-0000-0000-0000-000000000004'),
('eeeeeeee-0000-0000-0000-000000000012', 'REHEARSAL','2026-07-20T19:00:00', 'Estúdio Central',         'Ensaio pré-show de agosto',      NULL);

INSERT INTO professionals (id, name, role, description, is_default) VALUES
('dddddddd-0000-0000-0000-000000000001', 'João Silva',       'Vocalista',              'Vocalista principal e fundador da banda', TRUE),
('dddddddd-0000-0000-0000-000000000002', 'Marcos Oliveira',  'Guitarrista',            'Guitarra base e backing vocals',          TRUE),
('dddddddd-0000-0000-0000-000000000003', 'Rafael Costa',     'Baixista',               'Baixo e produção musical',                TRUE),
('dddddddd-0000-0000-0000-000000000004', 'Diego Santos',     'Baterista',              'Bateria e percussão',                     TRUE),
('dddddddd-0000-0000-0000-000000000005', 'Ana Paula',        'Tecladista',             'Teclado e sintetizadores',                TRUE),
('dddddddd-0000-0000-0000-000000000006', 'Carlos Eduardo',   'Roadie',                 'Montagem e desmontagem de equipamentos',  TRUE),
('dddddddd-0000-0000-0000-000000000007', 'Fernanda Lima',    'Técnica de Som',         'Operação de mesa de som (FOH)',           TRUE),
('dddddddd-0000-0000-0000-000000000008', 'Bruno Alves',      'Motorista',              'Transporte da equipe e equipamentos',     TRUE),
('dddddddd-0000-0000-0000-000000000009', 'Lucas Ferreira',   'Guitarrista freelancer', 'Substituto eventual de guitarra',         FALSE),
('dddddddd-0000-0000-0000-000000000010', 'Patrícia Gomes',   'Baterista freelancer',   'Substituta eventual de bateria',          FALSE);

-- Equipe padrão (profissionais default) escalada na maioria dos eventos já ocorridos.
-- No evento 002, o baterista default faltou e a freelancer assumiu a posição.
INSERT INTO event_professionals (id, professional_id, event_id) VALUES
('ffffffff-0000-0000-0000-000000000001', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000002', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000003', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000004', 'dddddddd-0000-0000-0000-000000000004', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000005', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000006', 'dddddddd-0000-0000-0000-000000000006', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000007', 'dddddddd-0000-0000-0000-000000000007', 'eeeeeeee-0000-0000-0000-000000000001'),
('ffffffff-0000-0000-0000-000000000008', 'dddddddd-0000-0000-0000-000000000008', 'eeeeeeee-0000-0000-0000-000000000001'),

('ffffffff-0000-0000-0000-000000000009', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000010', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000011', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000012', 'dddddddd-0000-0000-0000-000000000010', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000013', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000014', 'dddddddd-0000-0000-0000-000000000006', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000015', 'dddddddd-0000-0000-0000-000000000007', 'eeeeeeee-0000-0000-0000-000000000002'),
('ffffffff-0000-0000-0000-000000000016', 'dddddddd-0000-0000-0000-000000000008', 'eeeeeeee-0000-0000-0000-000000000002'),

('ffffffff-0000-0000-0000-000000000017', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000004'),
('ffffffff-0000-0000-0000-000000000018', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000004'),
('ffffffff-0000-0000-0000-000000000019', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000004'),
('ffffffff-0000-0000-0000-000000000020', 'dddddddd-0000-0000-0000-000000000004', 'eeeeeeee-0000-0000-0000-000000000004'),
('ffffffff-0000-0000-0000-000000000021', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000004'),

('ffffffff-0000-0000-0000-000000000022', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000023', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000024', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000025', 'dddddddd-0000-0000-0000-000000000004', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000026', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000027', 'dddddddd-0000-0000-0000-000000000006', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000028', 'dddddddd-0000-0000-0000-000000000007', 'eeeeeeee-0000-0000-0000-000000000008'),
('ffffffff-0000-0000-0000-000000000029', 'dddddddd-0000-0000-0000-000000000008', 'eeeeeeee-0000-0000-0000-000000000008'),

('ffffffff-0000-0000-0000-000000000030', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000031', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000032', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000033', 'dddddddd-0000-0000-0000-000000000004', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000034', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000035', 'dddddddd-0000-0000-0000-000000000006', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000036', 'dddddddd-0000-0000-0000-000000000007', 'eeeeeeee-0000-0000-0000-000000000009'),
('ffffffff-0000-0000-0000-000000000037', 'dddddddd-0000-0000-0000-000000000008', 'eeeeeeee-0000-0000-0000-000000000009'),

-- Evento futuro: time completo escalado automaticamente (Default = true)
('ffffffff-0000-0000-0000-000000000038', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000039', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000040', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000041', 'dddddddd-0000-0000-0000-000000000004', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000042', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000043', 'dddddddd-0000-0000-0000-000000000006', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000044', 'dddddddd-0000-0000-0000-000000000007', 'eeeeeeee-0000-0000-0000-000000000011'),
('ffffffff-0000-0000-0000-000000000045', 'dddddddd-0000-0000-0000-000000000008', 'eeeeeeee-0000-0000-0000-000000000011'),

('ffffffff-0000-0000-0000-000000000046', 'dddddddd-0000-0000-0000-000000000001', 'eeeeeeee-0000-0000-0000-000000000012'),
('ffffffff-0000-0000-0000-000000000047', 'dddddddd-0000-0000-0000-000000000002', 'eeeeeeee-0000-0000-0000-000000000012'),
('ffffffff-0000-0000-0000-000000000048', 'dddddddd-0000-0000-0000-000000000003', 'eeeeeeee-0000-0000-0000-000000000012'),
('ffffffff-0000-0000-0000-000000000049', 'dddddddd-0000-0000-0000-000000000004', 'eeeeeeee-0000-0000-0000-000000000012'),
('ffffffff-0000-0000-0000-000000000050', 'dddddddd-0000-0000-0000-000000000005', 'eeeeeeee-0000-0000-0000-000000000012');

INSERT INTO financial_transactions (id, type, description, amount, due_date, payment_date, status, category, notes) VALUES
-- Abril 2026 (liquidados)
('cccccccc-0000-0000-0000-000000000001','INCOME', 'Cachê Show Teatro Municipal',     3500.00,'2026-04-15','2026-04-16','PAID',      'Cachê',       NULL),
('cccccccc-0000-0000-0000-000000000002','INCOME', 'Cachê Rock Bar Soho',             1200.00,'2026-04-22','2026-04-23','PAID',      'Cachê',       'Show particular — aniversário'),
('cccccccc-0000-0000-0000-000000000003','EXPENSE','Aluguel Estúdio Central — Abr',    450.00,'2026-04-05','2026-04-05','PAID',      'Estúdio',     NULL),
('cccccccc-0000-0000-0000-000000000004','EXPENSE','Manutenção guitarra',              180.00,'2026-04-12','2026-04-14','PAID',      'Equipamento', 'Troca de captador'),
-- Maio 2026 (liquidados)
('cccccccc-0000-0000-0000-000000000005','INCOME', 'Cachê Festival Rock Sul',         5000.00,'2026-05-10','2026-05-11','PAID',      'Cachê',       'Palco principal'),
('cccccccc-0000-0000-0000-000000000006','EXPENSE','Aluguel Estúdio Central — Mai',    450.00,'2026-05-03','2026-05-03','PAID',      'Estúdio',     NULL),
('cccccccc-0000-0000-0000-000000000007','EXPENSE','Transporte — Festival',            320.00,'2026-05-10','2026-05-10','PAID',      'Outros',      'Van + combustível ida e volta'),
('cccccccc-0000-0000-0000-000000000008','EXPENSE','Impressão fôlderes e cartazes',    280.00,'2026-05-20','2026-05-22','PAID',      'Marketing',   NULL),
-- Junho 2026 (mistura: pago, pendente, vencido)
('cccccccc-0000-0000-0000-000000000009','INCOME', 'Cachê Centro Cultural',           2800.00,'2026-06-05','2026-06-06','PAID',      'Cachê',       NULL),
('cccccccc-0000-0000-0000-000000000010','INCOME', 'Patrocínio Cerveja Brand',        1500.00,'2026-06-10','2026-06-12','PAID',      'Patrocínio',  'Logo no banner e camiseta'),
('cccccccc-0000-0000-0000-000000000011','EXPENSE','Aluguel Estúdio Central — Jun',    450.00,'2026-06-07','2026-06-07','PAID',      'Estúdio',     NULL),
('cccccccc-0000-0000-0000-000000000012','EXPENSE','Cordas e peles (reposição)',        95.00,'2026-06-15','2026-06-15','PAID',      'Equipamento', NULL),
('cccccccc-0000-0000-0000-000000000013','INCOME', 'Cachê Festa Junina — Associação', 2200.00,'2026-06-20', NULL,       'PENDING',   'Cachê',       'Aguardando confirmação bancária'),
('cccccccc-0000-0000-0000-000000000014','EXPENSE','Renovação site e redes sociais',   350.00,'2026-06-18', NULL,       'PENDING',   'Marketing',   'Serviço de design contratado'),
('cccccccc-0000-0000-0000-000000000015','INCOME', 'Cachê Show 28/06',                3000.00,'2026-06-28', NULL,       'PENDING',   'Cachê',       'Show corporativo'),
('cccccccc-0000-0000-0000-000000000016','EXPENSE','Locação PA e iluminação 28/06',    800.00,'2026-06-28', NULL,       'PENDING',   'Equipamento', NULL),
-- Julho 2026 (futuro)
('cccccccc-0000-0000-0000-000000000017','INCOME', 'Cachê Rock Nacional — Jul',       4000.00,'2026-07-12', NULL,       'PENDING',   'Cachê',       NULL),
('cccccccc-0000-0000-0000-000000000018','EXPENSE','Aluguel Estúdio Central — Jul',    450.00,'2026-07-05', NULL,       'PENDING',   'Estúdio',     NULL),
('cccccccc-0000-0000-0000-000000000019','EXPENSE','Revisão bateria',                  250.00,'2026-07-20', NULL,       'PENDING',   'Equipamento', NULL),
-- Agosto 2026 (futuro)
('cccccccc-0000-0000-0000-000000000020','INCOME', 'Cachê Show Aniversário da Cidade',3500.00,'2026-08-15', NULL,       'PENDING',   'Cachê',       NULL),
('cccccccc-0000-0000-0000-000000000021','EXPENSE','Aluguel Estúdio Central — Ago',    450.00,'2026-08-02', NULL,       'PENDING',   'Estúdio',     NULL),
('cccccccc-0000-0000-0000-000000000022','INCOME', 'Patrocínio Festival de Inverno',  2000.00,'2026-08-22', NULL,       'PENDING',   'Patrocínio',  'Contrato assinado');
