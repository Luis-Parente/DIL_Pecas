package model.dao;

import java.util.List;

import model.entities.Venda;

public interface VendaDao {

	void insert(Venda obj);

	void update(Venda obj);

	void deleteById(Integer id);

	Venda findById(Integer id);

	List<Venda> findAll();
}
