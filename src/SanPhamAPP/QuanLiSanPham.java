/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanPhamAPP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class QuanLiSanPham {

    DecimalFormat fm = new DecimalFormat("#,###");
    Big_project.run j16 = new Big_project.run();
    Scanner sc = new Scanner(System.in);
    ArrayList<SanPham> listSP = new ArrayList<>();
    int x;

    public void loadFile() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            listSP = new ArrayList<>();
            fr = new FileReader("TTSanPham1.txt");
            br = new BufferedReader(fr);
            String s = null;

            try {
                while ((s = br.readLine()) != null) {

                    String listSP[] = s.split("\t");
                    SanPham sp = new SanPham();
                    sp.setMaSP(listSP[0]);
                    sp.settenSP(listSP[1]);

                    sp.setSoLuong(Integer.parseInt(listSP[2]));
                    sp.setGia(Integer.parseInt(listSP[3]));
                    this.listSP.add(sp);
                }

            } catch (IOException ex) {
                Logger.getLogger(QuanLiSanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuanLiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void save() {

        try {
            Writer w = null;
            w = new FileWriter("TTSanPham1.txt");

            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listSP.size(); i++) {
                String row = "";
                row += listSP.get(i).getMaSP() + "\t";
                row += listSP.get(i).gettenSP() + "\t";
                row += listSP.get(i).getSoLuong() + "\t";
                row += listSP.get(i).getGia() + "\n";

                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(QuanLiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkName(String name) {
        for (int i = 0; i < 10; i++) {
            if (name.contains(i + "") || name.equalsIgnoreCase("")) {
                System.out.println("Tên khách hàng không được chứa số.");
                return true;
            }
        }
        return false;
    }

    public boolean checkID(String ID) {

        for (int i = 0; i < listSP.size(); i++) {

            if (ID.equalsIgnoreCase(listSP.get(i).getMaSP())) {
                x = i;
                return true;
            }
        }
        return false;
    }

    public void inTT() {
        loadFile();
        int dem = 0;
        if (listSP.isEmpty()) {
            System.out.println("Chưa có sản phẩm trong danh sách ");
            dem++;
        }
        if (dem == 0) {
            System.out.println("******************** Thông tin các sản phẩm*********************** ");
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println("|                         THÔNG  TIN  SẢN  PHẨM                                  |");
            System.out.println("+--------------+-------------------+--------------+------------------------------+");
            System.out.println("| Mã sản phẩm  |    Tên sản phẩm   |   Số lượng   |            giá               |");
            System.out.println("+--------------+-------------------+--------------+------------------------------+");
            for (int i = 0; i < listSP.size(); i++) {
                System.out.printf("|%14s|%19s|%14s|%30s|\n", listSP.get(i).getMaSP(), listSP.get(i).gettenSP(), fm.format(listSP.get(i).getSoLuong()),
                        fm.format(listSP.get(i).getGia()));
                System.out.println("+--------------+-------------------+--------------+------------------------------+");

            }
            System.out.println("");
            System.out.println("");

        }
    }

    public void timSP() {
        Scanner sc = new Scanner(System.in);
        String ID;
        System.out.print("Nhập mã sản phẩm/tên sản phẩm : ");
        ID = sc.nextLine();
        int dem = 0;
        System.out.println("+--------------------------------------------------------------------------------+");
        System.out.println("|                         THÔNG  TIN  SẢN  PHẨM                                  |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+");
        System.out.println("| Mã sản phẩm  |    Tên sản phẩm   |   Số lượng   |            giá               |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+");
        for (int i = 0; i < listSP.size(); i++) {

            if (checkID(ID) == true || listSP.get(i).gettenSP().contains(ID)) {
                System.out.printf("|%14s|%19s|%14s|%30s|\n", listSP.get(i).getMaSP(), listSP.get(i).gettenSP(), fm.format(listSP.get(i).getSoLuong()),
                        fm.format(listSP.get(i).getGia()));
                System.out.println("+--------------+-------------------+--------------+------------------------------+");
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("Không tồn tại sản phẩm có mã này");
        }

        System.out.println("");
        System.out.println("");
    }

    public void themNV() {

        loadFile();
        Scanner sc1 = new Scanner(System.in);
        SanPham kh = new SanPham();
        String ID;
        System.out.print("Nhập mã sản phẩm : ");
        ID = sc1.nextLine();

        if (checkID(ID) == true) {
            System.out.println("Đã tồn tại sản phẩm này.");
        } else {

            kh.themTT(ID);
            listSP.add(kh);
            save();
        }
    }

    public void suaTT() {
        SanPham sp = new SanPham();
        loadFile();
        String id, s;
        Scanner sc = new Scanner(System.in);
        System.out.println("*************************************************");
        System.out.println("Sửa thông tin sản phẩm : ");
        System.out.print("Nhập mã sản phẩm : ");
        id = sc.nextLine();
        System.out.println("Nhấn enter để bỏ qua thông tin không chỉnh sửa.");
        if (checkID(id) == true) {
            //arrL.get(i).setName(sc.nextLine());sp.themTT(id);
            System.out.println("Tên sản phẩm cũ : " + listSP.get(x).gettenSP());
            do {
                System.out.print("Tên sản phẩm mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listSP.get(x).settenSP(s);
            } while (checkName(listSP.get(x).gettenSP()));

            System.out.println("Số lượng sản phẩm cũ : " + listSP.get(x).getSoLuong());
            do {
                System.out.print("Số lượng sản phẩm mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listSP.get(x).setSoLuong(Integer.parseInt(s));
            } while ((listSP.get(x).getSoLuong()) < 0);

            System.out.println("Giá sản phẩm cũ : " + listSP.get(x).getGia());
            do {
                System.out.print("Giá sản phẩm mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listSP.get(x).setGia(Integer.parseInt(s));
            } while ((listSP.get(x).getGia()) < 0);

        } else {
            System.err.println("Không tồn tại sản phẩm này.");
        }
        save();
        System.out.println("Sửa thông tin sản phẩm thành công.");
        System.out.println("                    Thông tin sản phẩm sau khi sửa.");
        System.out.println("+--------------------------------------------------------------------------------+");
        System.out.println("|                         THÔNG  TIN  SẢN  PHẨM                                  |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+");
        System.out.println("| Mã sản phẩm  |    Tên sản phẩm   |   Số lượng   |            giá               |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+");

        System.out.printf("|%14s|%19s|%14s|%30s|\n", listSP.get(x).getMaSP(), listSP.get(x).gettenSP(), listSP.get(x).getSoLuong(),
                fm.format(listSP.get(x).getGia()));
        System.out.println("+--------------+-------------------+--------------+------------------------------+");

        System.out.println("");
        System.out.println("");
    }

    public void xoaTT() {
        Scanner sc = new Scanner(System.in);
        loadFile();
        String ID;
        System.out.print("Nhập mã sản phẩm : ");
        ID = sc.nextLine();
        checkID(ID);
        if (checkID(ID) == true) {
            listSP.remove(x);
            System.err.println("ĐÃ xóa thông tin sản phẩm.");
        }
        System.out.println("");
        System.out.println("");
        save();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                               QUẢN   LÍ   SẢN  PHẨM                                                    |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       1. XEM  DANH  SÁCH  SẢN   PHẨM                                                   |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       2. TÌM  SẢN  PHẨM                                                                |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       3. THÊM  MỚI  1  SẢN  PHẨM                                                       |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       4. SỬA  THÔNG  TIN  SẢN  PHẨM                                                    |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       5. XÓA  THÔNG  TIN  SẢN  PHẨM                                                    |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       6. QUAY  LẠI  MENU  CHÍNH                                                        |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            do {
                System.out.print("Mời bạn chọn chức năng : ");
                chon = sc.nextInt();
                switch (chon) {
                    case 1: {
                        inTT();
                        break;
                    }
                    case 2: {
                        timSP();
                        break;
                    }
                    case 3: {
                        themNV();
                        break;
                    }
                    case 4: {
                        suaTT();
                        break;
                    }
                    case 5: {
                        xoaTT();
                        break;
                    }
                    case 6: {
                        j16.menuMain();
                        break;
                    }
                    default: {
                        System.out.println("Mời nhập số từ 1 đến 6 !!");
                    }
                }

            } while (chon < 0 || chon > 6);

        } while (chon != 6);

    }
}

class main {

    public static void main(String[] args) {
        QuanLiSanPham qlsp = new QuanLiSanPham();
        qlsp.menu();
    }
}
