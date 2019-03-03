/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVienAPP;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class NhanVien {

    private String idNV;
    private String nameNV;
    private String sdtNV;
    private String emailNV;
    private String passNV;
    private String addrNV;

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getNameNV() {
        return nameNV;
    }

    public void setNameNV(String nameNV) {
        this.nameNV = nameNV;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    public String getPassNV() {
        return passNV;
    }

    public void setPassNV(String passNV) {
        this.passNV = passNV;
    }

    public String getAddrNV() {
        return addrNV;
    }

    public void setAddrNV(String addrNV) {
        this.addrNV = addrNV;
    }

    public NhanVien() {
    }

    public NhanVien(String idNV, String nameNV, String sdtNV, String emailNV, String passNV, String addrNV) {
        this.idNV = idNV;
        this.nameNV = nameNV;
        this.sdtNV = sdtNV;
        this.emailNV = emailNV;
        this.passNV = passNV;
        this.addrNV = addrNV;
    }

    public void addTTNV(String id) {

        QuanLiNhanVien qlnv = new QuanLiNhanVien();

        Scanner sc = new Scanner(System.in);
        System.out.println(" *************************  Mời nhập thông tin nhân viên *********************");

        idNV = id;
        do {
            System.out.print("Nhập tên nhân viên : ");
            nameNV = sc.nextLine();

        } while (qlnv.checkName(nameNV));

        do {
            System.out.print("Nhập số điện thoại nhân viên :");
            sdtNV = sc.nextLine();
        } while (qlnv.checksdt(sdtNV));

        do {
            System.out.print("Nhập email nhân viên :");
            emailNV = sc.nextLine();
        } while (qlnv.kiemTraEmail(emailNV));

        System.out.print("Nhâp mật khẩu nhân viên : ");
        passNV = md5(sc.nextLine());
        System.out.print("Nhập địa chỉ nhân viên :");
        addrNV = sc.nextLine();

    }

    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
