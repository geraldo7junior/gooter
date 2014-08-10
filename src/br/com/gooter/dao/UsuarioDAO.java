package br.com.gooter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gooter.conexao.ConnectionFactory;
import br.com.gooter.vo.UsuarioVO;

public class UsuarioDAO {

	Connection con;

	public UsuarioDAO() throws ClassNotFoundException, SQLException {

		this.con = new ConnectionFactory().getConnection();
	}

	public void inserir(UsuarioVO usuarioVO) {

		String sql = "INSERT INTO Usuario (nomeUsuario, loginUsuario) VALUES (?,?) ";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, usuarioVO.getNomeUsuario());
			stmt.setString(2, usuarioVO.getLoginUsuario());
			stmt.execute();
			stmt.close();

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<UsuarioVO> listarUsuario(UsuarioVO usuarioVO) {

		List<UsuarioVO> listaUsuario = new ArrayList<UsuarioVO>();

		String sql = "SELECT * FROM Usuario WHERE loginUsuario = ?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, usuarioVO.getLoginUsuario());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				usuarioVO.setIdUsuario(rs.getInt("idUsuario"));
				usuarioVO.setNomeUsuario(rs.getString("nomeUsuario"));
				usuarioVO.setLoginUsuario(rs.getString("loginUsuario"));
				// usuarioVO.setSexoUsuario(rs.getString("sexoUsuario"));
				// usuarioVO.setDataNascimentoUsuario("dataNascimentoUsuario");

				listaUsuario.add(usuarioVO);
			}

			return listaUsuario;

		} catch (SQLException ex) {

			throw new RuntimeException(ex);

		}

	}

}
