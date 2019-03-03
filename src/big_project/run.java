/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Big_project;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class run {

    Scanner sc = new Scanner(System.in);
    NhanVienAPP.NhanVien nhapNV = new NhanVienAPP.NhanVien();

    static String maNV;
    String mk;

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

    public void checkLogIn() {
//        nv.save();
        NhanVienAPP.QuanLiNhanVien nv = new NhanVienAPP.QuanLiNhanVien();
        nv.loadFile();

        System.out.println("mời bạn đăng nhập.");
        System.out.print("Nhập mã nhân viên :");
        maNV = sc.nextLine();
        System.out.print("Nhập mật khẩu : ");
        mk = sc.nextLine();
        int dem = 0;

        for (int i = 0; i < NhanVienAPP.QuanLiNhanVien.listNV.size(); i++) {
            if (nv.listNV.get(i).getIdNV().equalsIgnoreCase(maNV)
                    && NhanVienAPP.QuanLiNhanVien.listNV.get(i).getPassNV().equalsIgnoreCase(md5(mk))) {
                System.out.println("ĐÃ ĐĂNG NHẬP THÀNH CÔNG.");
                dem++;
                menuMain();
            }
        }
        if (dem == 0) {
            System.out.println("Đăng nhập không thành công !");
            checkLogIn();
        }

    }

    public void menuMain() {
        int chon;
        SanPhamAPP.QuanLiSanPham sp = new SanPhamAPP.QuanLiSanPham();
        NhanVienAPP.QuanLiNhanVien nv = new NhanVienAPP.QuanLiNhanVien();
        KhachHangAPP.QuanLiKhachHang kh = new KhachHangAPP.QuanLiKhachHang();

        do {
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                         MỜI  CHỌN  CHỨC  NĂNG                                |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                       1. QUẢN  LÍ  NHÂN  VIÊN                                |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                       2. QUẢN  LÍ  KHÁCH  HÀNG                               |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                       3. QUẢN  LÍ  SẢN  PHẨM                                 |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                       4. ĐĂNG XUẤT                                           |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.println("|                       5. THOÁT                                               |");
            System.out.println("+------------------------------------------------------------------------------+");
            System.out.print("Mời bạn chọn chức năng : ");
            chon = sc.nextInt();
            switch (chon) {
                case 1: {
                    nv.menu();
                    break;
                }
                case 2: {
                    kh.menu();
                    break;
                }
                case 3: {
                    sp.menu();
                    break;
                }
                case 4: {
                    sc.nextLine();
                    checkLogIn();
                    break;
                }
                case 5:
                    System.err.println("Thoát chương trình thành công!!!");
                    System.exit(0);
                default:
                    System.out.println("Mời nhập số từ 1 đến 5 !!");
                    break;
            }

        } while (chon != 5);
    }
}

class mainMenu {

    public static void main(String[] args) {
        run run = new run();
        run.checkLogIn();
    }
}
