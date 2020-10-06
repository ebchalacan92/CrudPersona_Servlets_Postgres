package com.crud.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.crud.persona.Persona;


@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {
	@Inject
	private DataSource datasource;

	@Override
	public void insertarPersona(Persona user) {
		PreparedStatement ps = null;
		Connection con = null;
		

		try {
			con = datasource.getConnection();
			ps = con.prepareStatement("INSERT INTO persona" + "  (nombre, direccion, email) VALUES " + " (?, ?, ?);");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getDireccion());
			ps.setString(3, user.getEmail());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ignore) {
			}
		}
	}

	@Override
	public Persona buscarPersona(int id) {
		Persona user = new Persona();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement("select * from persona where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setNombre(rs.getString(2));
				user.setDireccion(rs.getString(3));
				user.setEmail(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ignore) {
			}
		}
		return user;
	}

	@Override
	public List<Persona> buscarTodos() {
		List<Persona> list = new ArrayList<>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = datasource.getConnection();
			ps = con.prepareStatement("select * from persona");
			rs = ps.executeQuery();

			while (rs.next()) {
				Persona user = new Persona();
				user.setId(rs.getInt(1));
				user.setNombre(rs.getString(2));
				user.setDireccion(rs.getString(3));
				user.setEmail(rs.getString(4));
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ignore) {
			}
		}
		return list;
	}

	@Override
	public int eliminarPersona(int id) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement("delete from persona where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ignore) {
			}
		}
		return status;
	}

	@Override
	public int actualizarPersona(Persona user) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement("update persona set nombre=?,direccion=?,email=? where id=?");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getDireccion());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getId());

			status = ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ignore) {
			}
		}
		return status;
	}

}
