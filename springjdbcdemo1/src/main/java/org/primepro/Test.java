package org.primepro;

import java.util.List;

import org.primepro.config.AppConfig;
import org.primepro.dao.EmpDao;
import org.primepro.dao.EmpDaoImpl;
import org.primepro.entities.Emp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		EmpDao dao=context.getBean("dao",EmpDaoImpl.class);
		dao.insert(new Emp(1,"Tisha","Hyderabad"));
		dao.delete(1);
		List<Emp> employees=dao.getEmployees();
		for(Emp e:employees)
		{
			System.out.println(e.getEno()+"\t"+ e.getName()+"\t"+e.getAddress());
		}
	}
}

				
//        Emp e=dao.getEmployee(1);
//        System.out.println("________________________________________________________________");
//        System.out.println(e.getEno()+"\t"+e.getName()+"\t"+e.getAddress());
//	}



