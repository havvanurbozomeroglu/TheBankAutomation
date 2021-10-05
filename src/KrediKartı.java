/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class KrediKartı   {
    private String kartNo;
    private int musteriid;
    private int bakiye;
    private int limit;
    private int borc;

    public KrediKartı(String kartNo, int musteriid, int bakiye, int limit, int borc) {
        this.kartNo = kartNo;
        this.musteriid = musteriid;
        this.bakiye = bakiye;
        this.limit = limit;
        this.borc = borc;
    }

    
    
    public String getKartNo() {
        return kartNo;
    }

    public void setKartNo(String kartNo) {
        this.kartNo = kartNo;
    }

    public int getMusteriid() {
        return musteriid;
    }

    public void setMusteriid(int musteriid) {
        this.musteriid = musteriid;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getBorc() {
        return borc;
    }

    public void setBorc(int borc) {
        this.borc = borc;
    }
    
    
}
