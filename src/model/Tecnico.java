package model;

import java.util.Objects;

public class Tecnico {
	private String nome;
	private Selecao selecao;

	Tecnico(String nome) {
		this.nome = Funcoes.captilizeString(nome);
	}

	Tecnico(String nome, Selecao selecao) {
		this.nome = Funcoes.captilizeString(nome);
		this.selecao = selecao;
	}

	public String getNome() {
		return nome;
	}

	public boolean setNome(String nome) {

		if (nome.matches("[a-zA-Z\s]+")) {
			this.nome = Funcoes.captilizeString(nome);
			return true;
		}
		return false;
	}

	public Selecao getSelecao() {
		return selecao;
	}

	public void setSelecao(Selecao selecao) {
		this.selecao = selecao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tecnico other = (Tecnico) obj;
		return Objects.equals(nome, other.nome);
	}

	public String toString() {
		return "Nome: " + nome + " | Selecao: " + selecao.getNome();
	}

}
