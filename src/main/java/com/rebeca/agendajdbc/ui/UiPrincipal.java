package com.rebeca.agendajdbc.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.rebeca.agendajdbc.domain.Pessoa;
import com.rebeca.agendajdbc.persistence.ControlPessoa;

public class UiPrincipal {

	private int posicao = -1;
	private boolean novo = true;

	private JFrame frmAgenda;
	private JTextField textCodigo;
	private JTextField textTelefone;
	private JTextField textNome;
	private JButton btnEditar;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnApagar;
	private JButton btnFechar;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;

	/**
	 * Create the application.
	 */
	public UiPrincipal() {
		initialize();
		ControlPessoa.carregaLista();
		carregaPrimeiraPessoa();
	}

	private void carregaPrimeiraPessoa() {
		posicao = 0;
		Pessoa pessoa = ControlPessoa.get(posicao);
		preencher(pessoa);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmAgenda = new JFrame();
		frmAgenda.getContentPane().setBackground(new Color(98, 255, 255));
		frmAgenda.getContentPane().setLayout(null);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textNome.setText("");
				textTelefone.setText("");
				textCodigo.setText("");
				habilitaBotoes(true);
				novo = true;

			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNovo.setBackground(new Color(255, 179, 217));
		btnNovo.setBounds(398, 11, 89, 34);
		frmAgenda.getContentPane().add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validaTela()) {
					habilitaBotoes(false);
					Pessoa pessoa = null;
					if (novo) {

						pessoa = new Pessoa();
					} else {
						pessoa = ControlPessoa.get(posicao);
					}
					pessoa.setNome(textNome.getText());
					pessoa.setTelefone(textTelefone.getText());
					if (novo) {

						ControlPessoa.adiciona(pessoa);
						posicao = ControlPessoa.getUltimaPosicao();
						pessoa = ControlPessoa.get(posicao);
						preencher(pessoa);
					} else {
						ControlPessoa.update(pessoa);
					}
					textCodigo.setText(pessoa.getCodigo() + "");
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBackground(new Color(255, 179, 217));
		btnSalvar.setBounds(398, 99, 89, 34);
		frmAgenda.getContentPane().add(btnSalvar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				habilitaBotoes(true);
				novo = false;

			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEditar.setBackground(new Color(255, 179, 217));
		btnEditar.setBounds(399, 53, 89, 34);
		frmAgenda.getContentPane().add(btnEditar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pessoa pessoa = new Pessoa();
				habilitaBotoes(false);
				pessoa = ControlPessoa.get(posicao);
				preencher(pessoa);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBackground(new Color(255, 179, 217));
		btnCancelar.setBounds(398, 146, 89, 34);
		frmAgenda.getContentPane().add(btnCancelar);

		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resultado = JOptionPane.showConfirmDialog(frmAgenda, "Deseja realmente apagar imbecil??");

				Pessoa pessoa = ControlPessoa.get(posicao);
				if (resultado == JOptionPane.YES_OPTION) {

					ControlPessoa.apaga(pessoa);
					ControlPessoa.delete(pessoa);
					posicao = 0;
					pessoa = ControlPessoa.get(posicao);
					preencher(pessoa);
				} else {
					
					posicao = ControlPessoa.getUltimaPosicao();
					pessoa = ControlPessoa.get(posicao);
					preencher(pessoa);
				}
			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagar.setBackground(new Color(255, 179, 217));
		btnApagar.setBounds(398, 191, 89, 34);
		frmAgenda.getContentPane().add(btnApagar);

		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFechar.setBackground(new Color(255, 179, 217));
		btnFechar.setBounds(398, 236, 89, 34);
		frmAgenda.getContentPane().add(btnFechar);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(38, 16, 61, 22);
		frmAgenda.getContentPane().add(lblCodigo);

		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setBounds(38, 38, 73, 28);
		frmAgenda.getContentPane().add(textCodigo);
		textCodigo.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(38, 83, 61, 22);
		frmAgenda.getContentPane().add(lblNome);

		textNome = new JTextField();
		textNome.setEditable(false);
		textNome.setColumns(10);
		textNome.setBounds(38, 105, 156, 28);
		frmAgenda.getContentPane().add(textNome);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(38, 146, 61, 22);
		frmAgenda.getContentPane().add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setEditable(false);
		textTelefone.setColumns(10);
		textTelefone.setBounds(38, 166, 156, 28);
		frmAgenda.getContentPane().add(textTelefone);

		btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ultimo = ControlPessoa.getUltimaPosicao();
				if (posicao == ultimo) {
					JOptionPane.showMessageDialog(frmAgenda, "Não tem para onde ir imbecil ");
				} else {
					posicao = ultimo;
					Pessoa pessoa = ControlPessoa.get(posicao);
					preencher(pessoa);
				}
			}
		});
		btnUltimo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUltimo.setBackground(new Color(255, 179, 217));
		btnUltimo.setBounds(291, 237, 54, 34);
		frmAgenda.getContentPane().add(btnUltimo);

		btnProximo = new JButton(">");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ultima = ControlPessoa.getUltimaPosicao();
				if (posicao >= ultima) {
					JOptionPane.showMessageDialog(frmAgenda, "Não tem para onde ir imbecil ");
				} else {
					posicao++;
					Pessoa pessoa = ControlPessoa.get(posicao);
					preencher(pessoa);
					// funciona
				}
			}
		});
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProximo.setBackground(new Color(255, 179, 217));
		btnProximo.setBounds(207, 236, 54, 34);
		frmAgenda.getContentPane().add(btnProximo);

		btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (posicao <= 0) {
					JOptionPane.showMessageDialog(frmAgenda, "Não tem para onde voltar imbecil ");
				} else {
					posicao--;
					Pessoa pessoa = ControlPessoa.get(posicao);
					preencher(pessoa);
					// funcina
				}
			}
		});
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnterior.setBackground(new Color(255, 179, 217));
		btnAnterior.setBounds(125, 236, 54, 34);
		frmAgenda.getContentPane().add(btnAnterior);

		btnPrimeiro = new JButton("<<");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (posicao < 1) {
					JOptionPane.showMessageDialog(frmAgenda, "Não tem para onde voltar imbecil ");
				} else {
					posicao = 0;
					Pessoa pessoa = ControlPessoa.get(posicao);
					preencher(pessoa);
					// funciona
				}
			}
		});
		btnPrimeiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPrimeiro.setBackground(new Color(255, 179, 217));
		btnPrimeiro.setBounds(38, 236, 54, 34);
		frmAgenda.getContentPane().add(btnPrimeiro);

		frmAgenda.getContentPane().add(textNome);
		frmAgenda.setBackground(new Color(0, 225, 225));
		frmAgenda.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dev\\Pictures\\IconeAgenda.jpg"));
		frmAgenda.setTitle("Agenda");
		frmAgenda.setBounds(100, 100, 513, 321);
		frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void mostrar() {

		frmAgenda.setVisible(true);
	}

	private void habilitaBotoes(boolean ativo) {

		btnNovo.setEnabled(!ativo);
		btnEditar.setEnabled(!ativo);
		btnSalvar.setEnabled(ativo);
		btnCancelar.setEnabled(ativo);
		btnApagar.setEnabled(!ativo);
		btnFechar.setEnabled(!ativo);

		textNome.setEditable(ativo);
		textTelefone.setEditable(ativo);

		btnPrimeiro.setEnabled(!ativo);
		btnUltimo.setEnabled(!ativo);
		btnProximo.setEnabled(!ativo);
		btnAnterior.setEnabled(!ativo);
	}

	private void preencher(Pessoa pessoa) {
		if (pessoa != null) {
			textCodigo.setText(pessoa.getCodigo() + "");
			textNome.setText(pessoa.getNome());
			textTelefone.setText(pessoa.getTelefone());
		}
	}

	private boolean validaTela() {

		if (textNome.getText().trim().length() < 4) {
			JOptionPane.showMessageDialog(frmAgenda, "Preencha o campo nome imbecil");
			textNome.requestFocus();
			return false;
		}

		if (textTelefone.getText().isBlank()) {
			JOptionPane.showMessageDialog(frmAgenda, "Preencha o campo telefone imbecil");
			textTelefone.requestFocus();
			return false;
		}

		return true;
	}
}