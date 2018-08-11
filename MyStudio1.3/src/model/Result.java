package model;

public class Result {
	
	private int id;
	private int id_testcase;
	private String time;
	private String result;
	private String log;
	
	
	public Result() {
		super();
	}
	public Result(int id_testcase, String time, String result, String log) {
		super();
		this.id_testcase = id_testcase;
		this.time = time;
		this.result = result;
		this.log = log;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_testcase() {
		return id_testcase;
	}
	public void setId_testcase(int id_testcase) {
		this.id_testcase = id_testcase;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	@Override
	public String toString() {
		return "Result [id=" + id + ", id_testcase=" + id_testcase + ", time=" + time + ", result=" + result + ", log="
				+ log + "]";
	}
	
	
}
