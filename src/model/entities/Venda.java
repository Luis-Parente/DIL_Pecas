package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import model.entities.enums.FormaDePagamento;

public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private LocalDate data;
	private FormaDePagamento formaDePagamento;
	private Map<Peca, Integer> pecasVendidas = new HashMap<>();

	public Venda() {

	}

	public Venda(Long id, LocalDate data, FormaDePagamento formaDePagamento) {
		super();
		this.id = id;
		this.data = data;
		this.formaDePagamento = formaDePagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public void adicionarPeca(Peca peca, Integer quantidade) {
		pecasVendidas.put(peca, quantidade);
	}

	public void removerPeca(Peca peca) {
		pecasVendidas.remove(peca);
	}

	public Map<Peca, Integer> getPecaVendida() {
		return pecasVendidas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id);
	}

}
