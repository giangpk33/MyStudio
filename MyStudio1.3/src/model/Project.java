package model;

public class Project {
	private String name;
	private String description;
	private int id;
	public Project(String name, String description, int id) {
		super();
		this.name = name;
		this.description = description;
		this.id = id;
	}
	public Project(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	public Project() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return  name;
	}
	

}
