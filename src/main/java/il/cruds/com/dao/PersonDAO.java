package il.cruds.com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import il.cruds.com.personal.Personal;

@Component
public class PersonDAO {
	private List<Personal> pers;
	private static int id;
	//@Value("${url}")
	private String url;
	//@Value("${login}")
	private String login;
	//@Value("${pass}")
	private String pass;
	private static Connection con;
	
	
	public PersonDAO() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); //Запуск драйвера hsqldb
			PersonDAO.con = getConnection(); //Создание соединения с hsqldb
			pers = new ArrayList <Personal> (); // Создание списка Personal
			create();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} 
		
	}
	
	public Connection getConnection() throws SQLException  { // ��������� ���������� � ����� ������, ������ � ����� 
		// ����� �� �����, ������ Connection	
		return DriverManager.getConnection("jdbc:hsqldb:testdb", "SA", "");
		
	}
	
	public Path getPathProperties(String relativePath) {
		Path pathU = FileSystems.getDefault().getPath("").toAbsolutePath();
		System.out.println("-------2!" + pathU.toString());
		return pathU;
	}
	
	public List<Personal> index() throws SQLException{
		
		String sqlAll = "SELECT * FROM recipe";
		PreparedStatement prepS = con.prepareStatement(sqlAll); 
		ResultSet result = prepS.executeQuery(); 
		
		while(result.next()) {
			boolean x = false;
			Personal personal = new Personal (result.getInt("id"), result.getString("name"), result.getString("surname"), 
					result.getInt("telephone"), result.getString("patronymic"), result.getString("description"), result.getString("d_name"),
					result.getString("d_surname"), result.getString("d_patronymic"), result.getString("d_specialization"), 
					result.getString("datas"));
			
			if(pers.size() == 0) { pers.add(personal); }
			if(pers.size()>0) {
				for(Personal p : pers) {
					if(p.getId() == personal.getId()) { x = true; }
				}
			if(x == false) { pers.add(personal);  }
			
			}
			personal = null;
		}
		
		return pers;
	}
	
	public Personal show(int id) {
		Personal per = null;

		for(Personal p : pers) {
			if(p.getId()==id) {per = p; break;}
		}

		return per;

	}
	
	// ������� ������ � ��
	public void save (Personal newPers) {
		// ������� �������� ��� �������
		String sqlAdd = "insert into recipe (name, surname, telephone, patronymic, description, d_name, d_surname, d_patronymic, d_specialization, datas) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			// ��������� ���������� ������ � �������� �������
			PreparedStatement prepS = con.prepareStatement(sqlAdd);
			prepS.setString(1, newPers.getName());
			prepS.setString(2, newPers.getSurname());
			prepS.setInt(3, newPers.getTelephone());
			prepS.setString(4, newPers.getPatronymic());
			prepS.setString(5, newPers.getDescription());
			prepS.setString(6, newPers.getD_name());
			prepS.setString(7, newPers.getD_surname());
			prepS.setString(8, newPers.getD_patronymic());
			prepS.setString(9, newPers.getD_specialization());
			prepS.setString(10, newPers.getDatas());
			// ���������� ������
			prepS.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("������ � ������ save");
		}
		
	}
	
	// �������� ������ � ��
	public void update(int id, String name, String surname, int telephone, String patronymic, String description, 
			String d_name, String d_surname, String d_patronymic, String d_specialization, String datas) {
		
		// ������� �������� ��� �������
		String sqlUpdate = "UPDATE recipe SET name = ?, surname = ?, telephone = ?, "
				+ "patronymic = ?, description = ?, d_name = ?, d_surname = ?, d_patronymic = ?, d_specialization = ?, datas = ? WHERE id = ?;";
		
		try {
			PreparedStatement prepS = con.prepareStatement(sqlUpdate);
			prepS.setString(1, name);
			prepS.setString(2, surname);
			prepS.setInt(3, telephone);
			prepS.setString(4, patronymic);
			prepS.setString(5, description);
			prepS.setString(6, d_name);
			prepS.setString(7, d_surname);
			prepS.setString(8, d_patronymic);
			prepS.setString(9, d_specialization);
			prepS.setString(10, datas);
			prepS.setInt(11, id);
			// ���������� ������
			prepS.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("������ � ������ update");
		}
		
		// ������ ��������� � ������ ������� ����������
		for(Personal p : pers) {
			if(p.getId()==id) {
				p.setName(name);
				p.setSurname(surname);
				p.setTelephone(telephone);
				p.setPatronymic(patronymic);
				p.setDescription(description);
				p.setD_name(d_name);
				p.setD_surname(d_surname);
				p.setD_patronymic(d_patronymic);
				p.setD_specialization(d_specialization);
				p.setDatas(datas);
				
			}
		}
		
	}
	
	//�������� �� �� 
	public void delete(int id) {
		
		String sqlDelete = "DELETE FROM recipe WHERE id = ?";
		
		try {
			PreparedStatement prepS = con.prepareStatement(sqlDelete);
			prepS.setInt(1, id);
			prepS.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("������ � ������ delete");
		}
		
		//������� �� ������ ������� ����������
		Personal p = show(id);
		pers.remove(p);
	}
	
	private void create () throws SQLException {
		String create = "DROP TABLE IF EXISTS recipe; create table recipe (id INT IDENTITY NOT NULL, name VARCHAR(50), surname VARCHAR(50), patronymic VARCHAR(50), telephone integer, description VARCHAR(300), d_name VARCHAR(50), d_surname VARCHAR(50), d_patronymic VARCHAR(50), d_specialization VARCHAR(50), datas VARCHAR(50), PRIMARY KEY (id));";
		con.createStatement().executeUpdate(create);
	}
}
