package model;

public class Objects {
	
	private int id;
	private String methodType = null;
	private String objectLocators = null;
	private String name = null;
	private int id_project;
	
	public Objects() {
		super();
	}
	
	//Dùng ở frmMain khi lấy ra danh sách dể hiển thị lên table
	public Objects(int id, String methodType, String objectLocators, String name) {
		super();
		this.id = id;
		this.methodType = methodType;
		this.objectLocators = objectLocators;
		this.name = name;
	}

	//Dùng ở frmUpdateTestCase khi lấy thông tin của testscript tương ứng và hiển thị lên form
	
	public Objects(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	//
	public Objects(int id, String methodType, String objectLocators, String name, int id_project) {
		super();
		this.id = id;
		this.methodType = methodType;
		this.objectLocators = objectLocators;
		this.name = name;
		this.id_project = id_project;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getObjectLocators() {
		return objectLocators;
	}
	public void setObjectLocators(String objectLocators) {
		this.objectLocators = objectLocators;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId_project() {
		return id_project;
	}
	public void setId_project(int id_project) {
		this.id_project = id_project;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
