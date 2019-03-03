/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HoaDonAPP;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class HoaDon {

   // public HoaDon(String string, String idKH, String maSP, int soLuong, int gia) {
   // }

    public void banHang() {
        String maKH;
        String maSP;
        ArrayList listhd = new ArrayList();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã khách hàng : ");
        maKH = sc.nextLine();
        KhachHangAPP.QuanLiKhachHang qlkh = new KhachHangAPP.QuanLiKhachHang();
        if (!qlkh.checkID(maKH)) {
            System.out.println("Không tồn tại khách hàng có mã " + maKH);
        }
        System.out.print("Nhập mã sản phẩm : ");
        maSP = sc.nextLine();
        SanPhamAPP.QuanLiSanPham qlsp = new SanPhamAPP.QuanLiSanPham();
        if (!qlsp.checkID(maSP)) {
            System.out.println("Không tồn tại sản phẩm có mã : " + maSP);
        }
        System.out.print("Nhập số lượng mua : ");
        int soLuong = sc.nextInt();
        SanPhamAPP.SanPham sp = new SanPhamAPP.SanPham();
        double giaBan = sp.getGia();
        double thanhToan = soLuong * giaBan;

        KhachHangAPP.KhachHang kh = new KhachHangAPP.KhachHang();
        // SanPhamAPP.SanPham sp=new SanPhamAPP.SanPham();
        //HoaDon hoadon = new HoaDon("", kh.getIdKH(), sp.getMaSP(), soLuong, sp.getGia());
       // listhd.add(hoadon);
    }
}

class main {

    SanPhamAPP.SanPham sp = new SanPhamAPP.SanPham();
    KhachHangAPP.KhachHang kh = new KhachHangAPP.KhachHang();

//    public static void main(String[] args) {
//       // HoaDon HoaDon=new HoaDon("", Integer.parseInt(kh.getIdKH()), masp.getMaSP(), 0, 0);
//        HoaDon HoaDon = new HoaDon("", kh.getIdKH(), sp.getMaSP(), soLuong, sp.getGia());
//        HoaDon.banHang();
   // }
}
