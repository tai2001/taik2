package model;

public class Lop {
	protected int id;
    protected String malop;
    protected String hotengvcn;
    protected boolean trangthai;

    public Lop(){}

    public Lop(int id){}

    public Lop(String malop,String hotengvcn, boolean trangthai){
        this.malop = malop;
        this.hotengvcn = hotengvcn;
        this.trangthai = trangthai;
    }

    public Lop(int id, String malop,String hotengvcn, boolean trangthai){
        this.id = id;
        this.malop = malop;
        this.hotengvcn = hotengvcn;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getHotengvcn() {
        return hotengvcn;
    }

    public void setHotengvcn(String hotengvcn) {
        this.hotengvcn = hotengvcn;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }


}

