package main;

public class EnumTypes {
/*	public enum ACTIVITIES{
		OPEN,
		CLICK,
		INPUT,
		SELCECT,
		VERIFY
	}*/
	public enum ACTIONTYPES{
		CLICK,
		SUBMIT,
		SENDKEY,
		FIND,
		OPEN_BROWSER,
		VERIFY_EQUAL,
		PAGE_LOAD_TIMEOUT,
		CHECK_VISIBLE,
		CLOSE_BROWSER,
		WAIT,
		MAXIMIZE_WINDOW
	}
	
	public enum METHODTYPES{
		ID,
		XPATH,
		CSS,
		CLASS
	}

}
