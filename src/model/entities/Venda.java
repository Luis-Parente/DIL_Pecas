package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private LocalDate data;

	public Venda() {

	}

	public Venda(Long id, LocalDate data) {
		super();
		this.id = id;
		this.data = data;
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
