package vsfam.ss.invMan.common;

public class TransactionResult {

	private Object object;
	private boolean status = false;
	private String message;
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public TransactionResult(Object object, boolean status) {
		super();
		this.object = object;
		this.status = status;
	}
	public TransactionResult(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public TransactionResult(Object object, boolean status, String message) {
		super();
		this.object = object;
		this.status = status;
		this.message = message;
	}
}
