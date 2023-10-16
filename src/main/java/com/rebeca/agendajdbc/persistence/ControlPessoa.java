package com.rebeca.agendajdbc.persistence;

import java.util.ArrayList;
import java.util.List;

import com.rebeca.agendajdbc.domain.Pessoa;

public class ControlPessoa {

	private static List<Pessoa> pessoas = new ArrayList<>();
	private static PessoaDao dao = new PessoaDao();

	public static void carregaLista() {

		pessoas = dao.getList();

	}

	public static void adiciona(Pessoa pessoa) {

		dao.create(pessoa);
		carregaLista();

	}

	public static void update(Pessoa pessoa) {

		dao.update(pessoa);
	}

	public static Pessoa get(int posicao) {
		if (pessoas == null || pessoas.isEmpty())
			return null;
		return pessoas.get(posicao);

	}

	public static int getUltimaPosicao() {

		return pessoas.size() - 1;

	}

	public static void apaga(Pessoa pessoa) {

		pessoas.remove(pessoa);

	}

	public static void delete(Pessoa pessoa) {

		dao.delete(pessoa);
		carregaLista();

	}

}
