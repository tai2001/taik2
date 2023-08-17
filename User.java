package model;

public class User {
	protected int id;
    protected String user_name;
    protected String user_password;
    protected boolean trangthai;

    public boolean isTrangthai() {
		return trangthai;
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}

	public User(){}

    public User(int id){}

    public User(String user_name, String user_password,boolean trangthai){
        this.user_name = user_name;
        this.user_password = user_password;
        this.trangthai = trangthai;
    }

    public User(int id, String user_name, String user_password,boolean trangthai){
        this.id =id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


}
