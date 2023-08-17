package model;

public class Student {
	protected int id;
    protected int mssv;
    protected String hoten;
    protected String gioitinh;
    protected String ngaysinh;
    protected String diachi;
    protected String anhdaidien;
    protected int sdt;
    protected String email;
    protected boolean trangthai;

    public Student(){}

    public Student(int id){}

    public Student(int mssv, String hoten, String gioitinh, String ngaysinh, String diachi, String anhdaidien, int sdt, String email, boolean trangthai){
        this.mssv = mssv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.anhdaidien = anhdaidien;
        this.sdt = sdt;
        this.email = email;
        this.trangthai = trangthai;
    }

    public Student(int id, int mssv, String hoten, String gioitinh, String ngaysinh, String diachi, String anhdaidien, int sdt, String email, boolean trangthai){
        this.id = id;
        this.mssv = mssv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.anhdaidien = anhdaidien;
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

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
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
