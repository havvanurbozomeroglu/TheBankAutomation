
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class KrediIslemleri {
    private int bakiye;
    private int borc;
    private int limit;
    private ArrayList<Musteri> musteri;
    private ArrayList<KrediKartı> kredikarti;

    public KrediIslemleri(int bakiye, int borc, int limit) {
        this.bakiye = bakiye;
        this.borc = borc;
        this.limit = limit;
        
    }

    
    
    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    public int getBorc() {
        return borc;
    }

    public void setBorc(int borc) {
        this.borc = borc;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<Musteri> getMusteri() {
        return musteri;
    }

    public void setMusteri(ArrayList<Musteri> musteri) {
        this.musteri = musteri;
    }

    public ArrayList<KrediKartı> getKredikarti() {
        return kredikarti;
    }

    public void setKredikarti(ArrayList<KrediKartı> kredikarti) {
        this.kredikarti = kredikarti;
    }
    
    
    
}
