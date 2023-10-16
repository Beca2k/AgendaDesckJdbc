package com.rebeca.agendajdbc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rebeca.agendajdbc.domain.Pessoa;
import com.rebeca.agendajdbc.factory.ConnectionFactory;

public class PessoaDao {

	private Connection connection;

	public PessoaDao() {

		connection = ConnectionFactory.getConnection();

	}

	public List<Pessoa> getList() {

		List<Pessoa> pessoas = new ArrayList<>();

		try {

			PreparedStatement stmt = this.connection.prepareStatement("select * from pessoa");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pessoa pessoa = new Pessoa();
				pessoa.setCodigo(rs.getInt("codigo"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoas.add(pessoa);

			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return pessoas;
	}

	public void create(Pessoa pessoa) {

		try {

			String sql = "insert into pessoa" + "(nome, telefone)" + "values (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getTelefone());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void update(Pessoa pessoa) {

		try {

			String sql = "update pessoa set nome = ?, telefone = ? where codigo = ?";
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getTelefone());
			stmt.setInt(3, pessoa.getCodigo());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void delete(Pessoa pessoa) {
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement("delete from  pessoa where codigo = ?");
			stmt.setLong(1, pessoa.getCodigo());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
}
