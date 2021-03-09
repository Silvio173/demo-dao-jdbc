package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartamentDaoJdbc implements DepartmentDao{
	private Connection conn = null;
	
	public DepartamentDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null; 
		ResultSet rs = null; 
		try {
			st = conn.prepareStatement( "Insert into department(name) values(?)", Statement.RETURN_GENERATED_KEYS); 
			st.setString(1, obj.getName());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0 ) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}else {
				throw new DbException("Nenuma linha foi inserida por erro");
			}
		}catch(SQLException e) {
			throw new DbException("Erro ao Inserir dados =" + e.getMessage() ); 
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null; 
		ResultSet rs = null; 
		try {
			st = conn.prepareStatement("update department set name = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			int rowsAffected = st.executeUpdate(); 
			if(rowsAffected > 0 ) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}else {
				throw new DbException("Erro ao atualizar dados");
			}
		}catch(SQLException e) {
			throw new DbException("Erro ao atualizar = " + e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null; 
		try {
			st = conn.prepareStatement("delete from department where id = ?");
			st.setInt(1, id);
			st.executeUpdate(); 
			
		}catch(SQLException e) {
			throw new DbException("Error delete = " + e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		try {
			st = conn.prepareStatement("select department.* from department where id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				return instantiateDepartment(rs);
			}
			return null; 
			
		}catch(SQLException e) {
			throw new DbException("Busca por ID com erro = " + e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("id"),rs.getString("name"));
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		try{
			st = conn.prepareStatement("select * from department");
			rs = st.executeQuery();
			while(rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException("Erro ao realizar a busca = " + e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
