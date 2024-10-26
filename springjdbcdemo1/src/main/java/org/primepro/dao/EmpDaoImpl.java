package org.primepro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.primepro.entities.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class EmpDaoImpl implements EmpDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public EmpDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Emp e) {
		// update()- DML operations(Insert,Update,Delete)
		// query()- DRL opertaion(Select operation)
		jdbcTemplate.update("insert into emp values(?,?,?)", e.getEno(), e.getName(), e.getAddress());
		/*
		 * jdbcTemplate.update(new PreparedStatementCreator() //creating anonymous inner
		 * class object that implement PreparedStatementCreator interface {
		 * 
		 * @Override public PreparedStatement createPreparedStatement(Connection con)
		 * throws SQLException { PreparedStatement
		 * pst=con.prepareStatement("insert into emp values(?,?,?)"); pst.setInt(1,
		 * e.getEno()); pst.setString(2, e.getName()); pst.setString(3, e.getAddress());
		 * return pst; } });
		 */

		/*
		 * jdbcTemplate.update("insert into emp values(?,?,?)",new
		 * PreparedStatementSetter() {
		 * 
		 * @Override public void setValues(PreparedStatement ps) throws SQLException {
		 * ps.setInt(1, e.getEno()); ps.setString(2, e.getName()); ps.setString(3,
		 * e.getAddress());
		 * 
		 * 
		 * } });
		 */

	}

	@Override
	public void delete(int eno) {
		jdbcTemplate.update("delete from emp where eno=?", eno);

	}

	@Override
	public List<Emp> getEmployees() {
		List<Emp> employees = jdbcTemplate.query("select * from emp", new RowMapper<Emp>() 
		{

			@Override
			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp e=new Emp();
				e.setEno(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setAddress(rs.getString(3));
				return e;
			}

			
		});

		return employees;
		
		
	}

	@Override
	public Emp getEmployee(int eno) {
		Emp e=jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst=con.prepareStatement("select * from emp where eno=?",eno);
				pst.setInt(1, eno);
				return pst;
			}
		}, new ResultSetExtractor<Emp>() {

			@Override
			public Emp extractData(ResultSet rs) throws SQLException, DataAccessException {
				Emp e=new Emp();
				e.setEno(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setAddress(rs.getString(3));
				return e;
			}
		});
		return e;
	}


	@Override
	public void update(int eno, Emp e) {
		jdbcTemplate.update("update emp set name=? and address=? where eno=?", e.getName(),e.getAddress(),e.getEno());
	}
	
	
		
}
