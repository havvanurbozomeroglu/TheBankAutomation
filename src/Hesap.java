/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class Hesap {
    private String hesapNo;
    private int bakiye;
    private int id;

    public Hesap(String hesapNo,  int id, int bakiye) {
        this.hesapNo = hesapNo;
        this.bakiye = bakiye;
        this.id = id;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    public String getHesapNo() {
        return hesapNo;
    }

    public void setHesapNo(String hesapNo) {
        this.hesapNo = hesapNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    
}
