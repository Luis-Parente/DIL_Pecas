package model.dao;

import java.util.List;

import model.entities.Peca;

public interface PecaDao {

	void insert(Peca obj);

	void update(Peca obj);

	void deleteById(Integer id);

	Peca findById(Integer id);

	List<Peca> findAll();
}
