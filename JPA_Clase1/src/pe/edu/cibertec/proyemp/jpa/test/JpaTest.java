package pe.edu.cibertec.proyemp.jpa.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;
import pe.edu.cibertec.proyemp.util.UtilFormat;

public class JpaTest {
	
	
	private EntityManager manager;
	
	//Inyeccion de dependencias con Constructor
	public JpaTest(EntityManager manager){
		this.manager = manager;
	}
	
	public static void main(String[] args){
		
		//Utilizamos patron factory
		EntityManagerFactory factory = 
					Persistence.createEntityManagerFactory("MyPersistenceUnit1");
		
		//Obtenemos el EntityManager
		EntityManager em = factory.createEntityManager();
		
		JpaTest test = new JpaTest(em);
		
		//definimos la transaccion
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
			//insert, update y delete
		test.crearEmpleados();
		
		tx.commit();
		
		test.listarEmpleados();
		
		test.obtenerEmpleadosPorId(new Long(1));
		
		
	}

	private void obtenerEmpleadosPorId(Long id) {
		Empleado emp = manager.createQuery("select e from Empleado e where e.id=?",Empleado.class)
				.setParameter("myId", id)
				.getSingleResult();
		
		//3ra forma(funciona por id o primary key)
		Empleado emp1 = manager.find(Empleado.class, id);
		
		System.out.println(emp1);
		
	}

	private void listarEmpleados() {
		String jql = "select e from Empleado e";
		String jql2 = "from Empleado";
		
		List<Empleado> empleados = 
				manager.createQuery(jql2,
						Empleado.class).getResultList();
		
		for(Empleado empleado : empleados){
			System.out.println(empleado.getNombre());
		}
		
	}

	private void crearEmpleados() {
		Departamento lima = new Departamento("Lima");
		manager.persist(lima);
		
		Departamento aqp = new Departamento("Arequipa");
		manager.persist(aqp);
		
		
		LocalDate fecha = LocalDate.of(2012, Month.JULY, 18);
		Date f = UtilFormat.getFecha(fecha);
		
		Empleado emp1 = new Empleado("Luis", lima);
		Empleado emp2 = new Empleado("Marco", lima);
		Empleado emp3 = new Empleado("Maria", aqp);
		Empleado emp4 = new Empleado("Carlos", aqp);
		
		manager.persist(emp1);
		manager.persist(emp2);
		manager.persist(emp3);
		manager.persist(emp4);
		
		
		
	}

}
