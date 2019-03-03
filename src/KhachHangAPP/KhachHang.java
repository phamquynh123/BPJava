/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHangAPP;


import java.util.Scanner;


/**
 *
 * @author Asus
 */
public class KhachHang {

    private String idKH;
    private String nameKH;
    private String sdtKH;
    private String emailKH;
    private String addrKH;

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getNameKH() {
        return nameKH;
    }

    public void setNameKH(String nameKH) {
        this.nameKH = nameKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getAddrKH() {
        return addrKH;
    }

    public void setAddrKH(String addrKH) {
        this.addrKH = addrKH;
    }

    public KhachHang() {
    }

    public KhachHang(String idKH, String nameKH, String sdtKH, String emailKH, String addrKH) {
        this.idKH = idKH;
        this.nameKH = nameKH;
        this.sdtKH = sdtKH;
        this.emailKH = emailKH;
        this.addrKH = addrKH;
    }

    public void addTTKH(String id) {

        QuanLiKhachHang qlkh = new QuanLiKhachHang();
       
        Scanner sc = new Scanner(System.in);
        System.out.println(" *************************  Mời nhập thông tin khách hàng *********************");
       
        idKH=id;
        do {
            System.out.print("Nhập tên khách hàng : ");
            nameKH = sc.nextLine();

        } while (qlkh.checkName(nameKH));

        do {
            System.out.print("Nhập số điện thoại khách hàng :");
            sdtKH = sc.nextLine();
        } while (qlkh.checksdt(sdtKH));

        do {
            System.out.print("Nhập email khách hàng :");
            emailKH = sc.nextLine();
        } while (qlkh.kiemTraEmail(emailKH));

        System.out.print("Nhập địa chỉ khách hàng :");
        addrKH = sc.nextLine();
    }
}

