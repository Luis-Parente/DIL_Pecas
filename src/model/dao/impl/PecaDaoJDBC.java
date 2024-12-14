package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.PecaDao;
import model.entities.Peca;

public class PecaDaoJDBC implements PecaDao {

	private Connection conn;

	public PecaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Peca obj) {
		String sql = "INSERT INTO peca (nome, preco, quantidade, descricao) VALUES (?, ?, ?, ?)";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setInt(3, obj.getQuantidade());
			st.setString(4, obj.getDescricao());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void update(Peca obj) {
		String sql = "UPDATE peca SET nome = ?, preco = ?, quantidade = ?, descricao = ? WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setInt(3, obj.getQuantidade());
			st.setString(4, obj.getDescricao());
			st.setLong(5, obj.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM peca WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Peca findById(Integer id) {
		String sql = "SELECT * FROM peca WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return instanciarPeca(rs);
				}
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Peca> findAll() {
		String sql = "SELECT * FROM peca";
		List<Peca> lista = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				lista.add(instanciarPeca(rs));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return lista;
	}

	private Peca instanciarPeca(ResultSet rs) throws SQLException {
		Peca peca = new Peca();
		peca.setId(rs.getLong("id"));
		peca.setNome(rs.getString("nome"));
		peca.setPreco(rs.getDouble("preco"));
		peca.setQuantidade(rs.getInt("quantidade"));
		peca.setDescricao(rs.getString("descricao"));
		return peca;
	}
}
