INSERT INTO tb_curso(nome, ativo) VALUES ('Engenharia de Computação', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Análise e Desenvolvimento de Sistemas', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Sistemas da Informação', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Administração', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Educação Física', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Pedagogia', true);

INSERT INTO tb_role_details(role_name) VALUES ('ROLE_ALUNO');
INSERT INTO tb_role_details(role_name) VALUES ('ROLE_ORIENTADOR');
INSERT INTO tb_role_details(role_name) VALUES ('ROLE_COORDENADOR');
INSERT INTO tb_role_details(role_name) VALUES ('ROLE_ADMIN');

INSERT INTO tb_aluno(registro_aluno, nome, turma, telefone, email, curso_id) VALUES (511772020, 'Jhonatas Coutinho de Macedo', 'N2SICON', '11959669347', '511772020@eniac.edu.br', 1);
INSERT INTO tb_aluno(registro_aluno, nome, turma, telefone, email, curso_id) VALUES (500432020, 'Franklin Almeida', 'N2SICON', '11999996666', 'frank_teste@gmail.com', 3);
INSERT INTO tb_aluno(registro_aluno, nome, turma, telefone, email, curso_id) VALUES (520332022, 'Harry Potter', 'N2SICON', '11922223333', 'harry_teste@gmail.com', 2);

INSERT INTO tb_orientador(matricula, nome, telefone, email, area_conhecimento, titulacao) VALUES (23456789, 'Thiago Assumpção', '11901010202', '511772020@eniac.edu.br', 'Engenharia', 'DOUTORADO');
INSERT INTO tb_orientador(matricula, nome, telefone, email, area_conhecimento, titulacao) VALUES (12345699, 'Alvo Dumbledore', '11988885555', 'alvo_teste@gmail.com', 'Arte das Trevas', 'DOUTORADO');

INSERT INTO tb_orientador_disponibilidades(orientador_matricula, disponibilidades) VALUES (23456789, 'ORIENTADOR');
INSERT INTO tb_orientador_disponibilidades(orientador_matricula, disponibilidades) VALUES (12345699, 'ORIENTADOR');
INSERT INTO tb_orientador_disponibilidades(orientador_matricula, disponibilidades) VALUES (12345699, 'AVALIADOR_DE_BANCA');

INSERT INTO tb_coordenador(matricula, nome, telefone, ativo) VALUES (789456, 'Marcio Félix', '11933332222', true);

INSERT INTO tb_user(username, password) VALUES ('511772020', '$2a$10$OjegyskxxghLv.C58kKJzO8SoaYmPSxpBkVLy4arygXMswOYeBpBK');
INSERT INTO tb_user(username, password) VALUES ('500432020', '$2a$10$OjegyskxxghLv.C58kKJzO8SoaYmPSxpBkVLy4arygXMswOYeBpBK');
INSERT INTO tb_user(username, password) VALUES ('520332022', '$2a$10$OjegyskxxghLv.C58kKJzO8SoaYmPSxpBkVLy4arygXMswOYeBpBK');
INSERT INTO tb_user(username, password) VALUES ('23456789', '$2a$10$OjegyskxxghLv.C58kKJzO8SoaYmPSxpBkVLy4arygXMswOYeBpBK');
INSERT INTO tb_user(username, password) VALUES ('12345699', '$2a$10$OjegyskxxghLv.C58kKJzO8SoaYmPSxpBkVLy4arygXMswOYeBpBK');
INSERT INTO tb_user(username, password) VALUES ('789456', '$2a$10$OjegyskxxghLv.C58kKJzO8SoaYmPSxpBkVLy4arygXMswOYeBpBK');
INSERT INTO tb_user(username, password) VALUES ('admin', '$2a$10$a0pgeRRB79bWTGQdGOACDugz6OU5PlN6Pbd6P6q2VJC/IXQzE6KpK');

INSERT INTO tb_user_role(user_id, role_id) VALUES (1, 1)
INSERT INTO tb_user_role(user_id, role_id) VALUES (2, 1)
INSERT INTO tb_user_role(user_id, role_id) VALUES (3, 1)
INSERT INTO tb_user_role(user_id, role_id) VALUES (4, 2)
INSERT INTO tb_user_role(user_id, role_id) VALUES (5, 2)
INSERT INTO tb_user_role(user_id, role_id) VALUES (6, 3)
INSERT INTO tb_user_role(user_id, role_id) VALUES (7, 4)