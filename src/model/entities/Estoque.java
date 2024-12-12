package model.entities;

import java.util.Map;
import java.util.Objects;

public class Estoque {

	private Long id;
	private Map<Peca, Integer> itensEmEstoque;

	public Estoque() {

	}

	public Estoque(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estoque other = (Estoque) obj;
		return Objects.equals(id, other.id);
	}

}
