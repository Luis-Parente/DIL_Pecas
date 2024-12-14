package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import db.DbException;
import model.dao.VendaDao;
import model.entities.Peca;
import model.entities.Venda;

public class VendaDaoJDBC implements VendaDao {

	private Connection conn;

	public VendaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Venda obj) {
		String sqlVenda = "INSERT INTO venda (data, forma_pagamento) VALUES (?, ?)";
		String sqlVendaPeca = "INSERT INTO venda_peca (venda_id, peca_id, quantidade) VALUES (?, ?, ?)";
		try (PreparedStatement stVenda = conn.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS)) {
			conn.setAutoCommit(false);

			// Inserir venda
			stVenda.setDate(1, java.sql.Date.valueOf(obj.getData()));
			stVenda.setString(2, obj.getFormaDePagamento().name());
			stVenda.executeUpdate();

			// Obter ID gerado
			try (ResultSet rs = stVenda.getGeneratedKeys()) {
				if (rs.next()) {
					obj.setId(rs.getLong(1));
				}
			}

			// Inserir itens vendidos
			try (PreparedStatement stVendaPeca = conn.prepareStatement(sqlVendaPeca)) {
				for (Map.Entry<Peca, Integer> entry : obj.getPecaVendida().entrySet()) {
					stVendaPeca.setLong(1, obj.getId());
					stVendaPeca.setLong(2, entry.getKey().getId());
					stVendaPeca.setInt(3, entry.getValue());
					stVendaPeca.executeUpdate();
				}
			}

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException rollbackEx) {
				throw new DbException("Erro ao fazer rollback: " + rollbackEx.getMessage());
			}
			throw new DbException(e.getMessage());
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	@Override
	public void update(Venda obj) {
		// Implementação similar ao insert, ajustando a lógica para atualização
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM venda WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Venda findById(Integer id) {
		// Implementação para buscar venda e seus itens
		return null;
	}

	@Override
	public List<Venda> findAll() {
		// Implementação para buscar todas as vendas e seus itens
		return null;
	}
}
