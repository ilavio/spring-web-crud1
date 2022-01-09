package il.cruds.com.personal;

public class Personal {
	private int id;
	//@NotEmpty(message = "name do not must be empty")
	//@Size(min = 2, max = 30, message = "characters must be between 2 and 30")
	//�������
	private String name;
	private String surname;
	private int telephone;
	private String patronymic;
	private String description;
	
	//������
	private String d_name;
	private String d_surname;
	private String d_patronymic;
	private String d_specialization;
	private String datas;
	
	//@NotEmpty(message = "Email do not must be empty")
	//@Email
	//private String email;
	
	//@Min(value = 0, message = "Age must be more 0")
	//private int age;
	
	public Personal () {
		
	}
	
	

	public Personal(int id, String name, String surname, int telephone, String patronymic, String description,
			String d_name, String d_surname, String d_patronymic, String d_specialization, String datas) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.patronymic = patronymic;
		this.description = description;
		this.d_name = d_name;
		this.d_surname = d_surname;
		this.d_patronymic = d_patronymic;
		this.d_specialization = d_specialization;
		this.datas = datas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}



	public String getD_surname() {
		return d_surname;
	}



	public void setD_surname(String d_surname) {
		this.d_surname = d_surname;
	}



	public String getD_patronymic() {
		return d_patronymic;
	}



	public void setD_patronymic(String d_patronymic) {
		this.d_patronymic = d_patronymic;
	}



	public String getD_specialization() {
		return d_specialization;
	}



	public void setD_specialization(String d_specialization) {
		this.d_specialization = d_specialization;
	}



	public String getDatas() {
		return datas;
	}



	public void setDatas(String datas) {
		this.datas = datas;
	}



	@Override
	public String toString() {
		return "Personal [id=" + id + ", name=" + name + ", surname=" + surname + ", telephone=" + telephone
				+ ", patronymic=" + patronymic + ", description=" + description + ", d_name=" + d_name + ", d_surname="
				+ d_surname + ", d_patronymic=" + d_patronymic + ", d_specialization=" + d_specialization + ", datas="
				+ datas + "]";
	}

	
	
	
	

}
