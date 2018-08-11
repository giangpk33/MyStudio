package model;

public class TestScript {
	private int id;
	private String item;
	private String input;
	private String output;
	private String description;
	private int id_testcase;
	private String result;
	private Objects objects = new Objects();
	
	
	
	
	
	@Override
	public String toString() {
		return "TestScript [id=" + id + ", item=" + item + ", input=" + input + ", output=" + output + ", description="
				+ description + ", id_testcase=" + id_testcase + ", result=" + result + ", objects=" + objects + "]";
	}
	public TestScript() {
		super();
	}
	public TestScript(int id, String item, String input, String output, String description, int id_testcase,
			String result, Objects objects) {
		super();
		this.id = id;
		this.item = item;
		this.input = input;
		this.output = output;
		this.description = description;
		this.id_testcase = id_testcase;
		this.result = result;
		this.objects = objects;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId_testcase() {
		return id_testcase;
	}
	public void setId_testcase(int id_testcase) {
		this.id_testcase = id_testcase;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Objects getObjects() {
		return objects;
	}
	public void setObjects(Objects objects) {
		this.objects = objects;
	}
	
	

}
