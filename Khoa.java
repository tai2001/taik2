package model;

public class Khoa {
	protected int id;
    protected String tenkhoa;
    protected boolean trangthai;

    public Khoa(){}

    public Khoa(int id){}

    public Khoa(String tenkhoa, boolean trangthai ){
        this.tenkhoa = tenkhoa;
        this.trangthai = trangthai;
    }

    public Khoa(int id, String tenkhoa, boolean trangthai ){
        this.id = id;
        this.tenkhoa = tenkhoa;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }



}
