package com.bagicode.www.bagipukesmasvideo;

/**
 * Created by bagicode on 06/04/17.
 */

public class model_lapor {

    private String _id, _judul, _by, _tgl;

    public model_lapor(String id, String konsumen, String paket, String persen) {
        this._id = id;
        this._judul = konsumen;
        this._by = paket;
        this._tgl = persen;
    }

    public model_lapor() {
    }

    public void set_id (String id){
        this._id = id;
    }

    public String get_id (){
        return this._id;
    }

    public void set_judul (String judul){
        this._judul = judul;
    }

    public String get_judul(){
        return this._judul;
    }

    public void set_by(String by){
        this._by = by;
    }

    public String get_by(){
        return this._by;
    }

    public void set_tgl(String tgl){
        this._tgl = tgl;
    }

    public String get_tgl(){
        return this._tgl;
    }

}
