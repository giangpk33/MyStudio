package model;

public class TestCase {
	private int id;
	private String name;
	private int id_project;
	
	
	
	
	public TestCase(int id, String name, int id_project) {
		super();
		this.id = id;
		this.name = name;
		this.id_project = id_project;
		
	}
	public TestCase(String name, int id_project) {
		super();
		this.name = name;
		this.id_project = id_project;
	}
	public TestCase() {
		super();
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
	public int getId_project() {
		return id_project;
	}
	public void setId_project(int id_project) {
		this.id_project = id_project;
	}
	@Override
	public String toString() {
		return  name ;
	}
	
	

}
