package com.shashank.admin.ncrb_new_admin;

public class Vari {
    public String reg_no;
    public String name;

    public Vari(){

    }

    public Vari(String reg_no, String name){
        this.reg_no = reg_no;
        this.name = name;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
