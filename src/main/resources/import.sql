INSERT INTO tb_curso(nome, ativo) VALUES ('Engenharia de Computação', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Análise e Desenvolvimento de Sistemas', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Sistemas da Informação', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Administração', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Educação Física', true);
INSERT INTO tb_curso(nome, ativo) VALUES ('Pedagogia', true);

INSERT INTO tb_aluno(registro_aluno, nome, turma, telefone, curso_id) VALUES (511772020, 'Jhonatas Coutinho de Macedo', 'N2SICON', '11959669347', 1);
INSERT INTO tb_aluno(registro_aluno, nome, turma, telefone, curso_id) VALUES (500432020, 'Franklin Almeida', 'N2SICON', '11999996666', 3);
INSERT INTO tb_aluno(registro_aluno, nome, turma, telefone, curso_id) VALUES (520332022, 'Harry Potter', 'N2SICON', '11922223333', 2);

INSERT INTO tb_orientador(matricula, nome, telefone, area_conhecimento, titulacao) VALUES (23456789, 'Thiago Assumpção', '11901010202', 'Engenharia', 'DOUTORADO');
INSERT INTO tb_orientador(matricula, nome, telefone, area_conhecimento, titulacao) VALUES (12345699, 'Alvo Dumbledore', '11988885555', 'Arte das Trevas', 'DOUTORADO');

INSERT INTO tb_orientador_disponibilidades(orientador_matricula, disponibilidades) VALUES (23456789, 'ORIENTADOR');
INSERT INTO tb_orientador_disponibilidades(orientador_matricula, disponibilidades) VALUES (12345699, 'ORIENTADOR');
INSERT INTO tb_orientador_disponibilidades(orientador_matricula, disponibilidades) VALUES (12345699, 'AVALIADOR_DE_BANCA');

INSERT INTO tb_coordenador(matricula, nome, telefone, role, ativo) VALUES (789456, 'Marcio Félix', '11933332222', 'COORDENADOR', true);