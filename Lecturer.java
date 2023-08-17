package model;

public class Lecturer {
	protected int id;
    protected String hoten;
    protected String gioitinh;
    protected String ngaysinh;
    protected String diachi;
    protected String anhdaidien;
    protected String chuyenmon;
    protected String bangcap;
    protected int sdt;
    protected String email;
    protected boolean trangthai;

    public Lecturer(){}

    public Lecturer(int id){}

    public Lecturer(String hoten, String gioitinh, String ngaysinh, String diachi, String anhdaidien, String chuyenmon, String bangcap, int sdt, String email, boolean trangthai){
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.anhdaidien = anhdaidien;
        this.chuyenmon = chuyenmon;
        this.bangcap = bangcap;
        this.sdt = sdt;
        this.email = email;
        this.trangthai = trangthai;
    }

    public Lecturer(int id,String hoten, String gioitinh, String ngaysinh, String diachi, String anhdaidien, String chuyenmon, String bangcap, int sdt, String email, boolean trangthai){
        this.id = id;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.anhdaidien = anhdaidien;
        this.chuyenmon = chuyenmon;
        this.bangcap = bangcap;
        this.sdt = sdt;
        this.email = email;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getAnhdaidien() {
        return anhdaidien;
    }

    public void setAnhdaidien(String anhdaidien) {
        this.anhdaidien = anhdaidien;
    }

    public String getChuyenmon() {
        return chuyenmon;
    }

    public void setChuyenmon(String chuyenmon) {
        this.chuyenmon = chuyenmon;
    }

    public String getBangcap() {
        return bangcap;
    }

    public void setBangcap(String bangcap) {
        this.bangcap = bangcap;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }


}
