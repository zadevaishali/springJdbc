package org.primepro.dao;

import java.util.List;

import org.primepro.entities.Emp;

public interface EmpDao {
void insert(Emp e);
void delete(int eno);
List<Emp>getEmployees();
Emp getEmployee(int eno);
void update(int eno,Emp e);

}
