package user.client.exception;



/**
 * 自定义异常实体类
 */
public class BIZException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int code;
    private String msg;

    public BIZException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BIZException(String msg){
        super(msg);
        this.msg = msg;
    }

	public long getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
