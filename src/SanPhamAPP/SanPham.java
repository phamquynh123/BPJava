/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanPhamAPP;


import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class SanPham {

    private String maSP;
    private String tenSP;
    private int soLuong;
    private int gia;

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String gettenSP() {
        return tenSP;
    }

    public void settenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, int soLuong, int gia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public void themTT(String id) {
//        quanLiSanPham qlsp = new quanLiSanPham();
        Scanner sc = new Scanner(System.in);
        System.out.println("************************Mời nhập thông tin sản phẩm*****************************");
        maSP = id;
        System.out.print("Nhập tên sản phẩm : ");
        tenSP = sc.nextLine();

        do {
            System.out.print("Nhập số lượng sản phẩm : ");
            soLuong = sc.nextInt();
        } while (soLuong < 0);

        do {
            System.out.print("Nhập giá sản phẩm : ");
            gia = sc.nextInt();
            //fm.format(this.gia); 
        } while (gia < 0);
    }
}

//class main1 {
//
//    public static void main(String[] args) {
//        
//        SanPham sp = new SanPham();
//       sp.themTT(id);
//    }
//}