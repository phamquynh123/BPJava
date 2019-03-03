/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHangAPP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class QuanLiKhachHang {

    KhachHang kh = new KhachHang();
    Big_project.run j16 = new Big_project.run();
    ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();

    int x = 0, dem = 0;

    public void loadFile() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            listKH = new ArrayList<>();
            fr = new FileReader("TTKhachHang11.txt");
            br = new BufferedReader(fr);
            String s = null;

            try {
                while ((s = br.readLine()) != null) {
                    String listKH[] = s.split("\t");
                    KhachHang kh = new KhachHang();

                    kh.setIdKH(listKH[0]);
                    kh.setNameKH(listKH[1]);
                    kh.setSdtKH(listKH[2]); // kh.setSdtKH(Integer.parseInt(listKH[2]));
                    kh.setEmailKH(listKH[3]);
                    kh.setAddrKH(listKH[4]);
                    this.listKH.add(kh); //listKH.add=no suggest
                }

            } catch (IOException ex) {
                Logger.getLogger(QuanLiKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuanLiKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void save() {
        Writer w = null;
        try {
            w = new FileWriter("TTKhachHang11.txt");

            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listKH.size(); i++) {
                String row = "";
                row += listKH.get(i).getIdKH() + "\t";
                row += listKH.get(i).getNameKH() + "\t";
                row += listKH.get(i).getSdtKH() + "\t";
                row += listKH.get(i).getEmailKH() + "\t";

                row += listKH.get(i).getAddrKH() + "\n";
                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(QuanLiKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkID(String ID) {

        for (int i = 0; i < listKH.size(); i++) {
            KhachHang kh = new KhachHang();
            kh = listKH.get(i);
            if (ID.equalsIgnoreCase(kh.getIdKH())) {
                x = i;
                return true;
            }
        }
        return false;
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

    public boolean checksdt(String sdt) {

        if (sdt.startsWith(0 + "") == false) {
            System.out.println("Số điện thoại phải bắt đầu bằng só 0");
            return true;
        }
        if (((sdt.length() >= 10) || (sdt.length() <= 11)) == false) {
            System.out.println("Số điện thoại phải có 10 hoặc 11 số");
            return true;
        }
        try {
            Double.parseDouble(sdt);
        } catch (Exception e) {
            System.out.println("Số điện thoại không được chứa kí tự");
            return true;
        }
        return false;
    }

    public boolean kiemTraEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        if (ktEmail == false) {
            System.err.println("Email sai, nhập lại theo dạng abc@domain.com");
            return true;
        }
        return false;
    }

    public void themKH() {
        loadFile();
        Scanner sc1 = new Scanner(System.in);
        KhachHang kh = new KhachHang();
        String ID;

        System.out.print("Nhập mã khách hàng : ");
        ID = sc1.nextLine();

        if (checkID(ID) == true) {

            System.out.println("Đã tồn tại khách hàng này.");
            System.out.print("Bạn có muốn thêm mới khách hàng(y/n) : ");
            String s = sc1.nextLine();
            if (s.equalsIgnoreCase("y")) {
                themKH();
            }
        } else {

            kh.addTTKH(ID);
            listKH.add(kh);
            save();
        }

    }

    public void inTT() {
        loadFile();
        int dem = 0;
        if (listKH.isEmpty()) {
            System.out.println("Chưa có khách hàng trong danh sách ");
            dem++;
        }
        if (dem == 0) {
            System.out.println(" Thông tin các khách hàng ");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                   THÔNG  TIN  KHÁCH  HÀNG                                              |");
            System.out.println("+----------------+-------------------------+--------------+---------------------------------------+----------------------+");
            System.out.println("| Mã khách hàng  |    Tên khách hàng       | Số điện thoại|              Email                    |       Địa chỉ        |");
            System.out.println("+----------------+-------------------------+--------------+---------------------------------------+----------------------+");
            for (int i = 0; i < listKH.size(); i++) {
                System.out.printf("|%16s|%25s|%14s|%39s|%22s|\n", listKH.get(i).getIdKH(), listKH.get(i).getNameKH(), listKH.get(i).getSdtKH(),
                        listKH.get(i).getEmailKH(), listKH.get(i).getAddrKH());
                System.out.println("+----------------+-------------------------+--------------+---------------------------------------+----------------------+");
            }

        }
        System.out.println("");
        System.out.println("");

    }

    public void suaTT() {
        String id;
        Scanner sc = new Scanner(System.in);
        loadFile();
        System.out.println("*************************************************");
        System.out.println("Sửa thông tin khách hàng : ");
        System.out.print("Nhập mã khách hàng : ");
        id = sc.nextLine();
        System.out.println("Nhấn enter để bỏ qua");
        String s = "";
        if (checkID(id) == true) {
            do {
                System.out.println("tên khách hàng cũ : " + listKH.get(x).getNameKH());
                System.out.print("Tên khách hàng mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listKH.get(x).setNameKH(s);
            } while (checkName(listKH.get(x).getNameKH()));

            do {
                System.out.println("Số điện thoại cũ : " + listKH.get(x).getSdtKH());
                System.out.print("Số điện thoại mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listKH.get(x).setSdtKH(s);
            } while (checksdt(listKH.get(x).getSdtKH()));

            do {
                System.out.println("Email cũ : " + listKH.get(x).getEmailKH());
                System.out.print("Email mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listKH.get(x).setEmailKH(s);
            } while (kiemTraEmail(listKH.get(x).getEmailKH()));

            System.out.println("Địa chỉ cũ : " + listKH.get(x).getAddrKH());
            do {
                System.out.print("Địa chỉ mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listKH.get(x).setAddrKH(s);
            } while (kiemTraEmail(listKH.get(x).getAddrKH()));
            save();

        } else {
            System.out.println("Không tồn tại khách hàng này.");
        }

    }

    public void xoaTT() {
        Scanner sc = new Scanner(System.in);
        loadFile();
        String ID;
        System.out.print("Nhập mã khách hàng : ");
        ID = sc.nextLine();
        checkID(ID);
        if (checkID(ID) == true) {
            listKH.remove(x);
            System.out.println("ĐÃ xóa thông tin khách hàng.");
        }
        save();
    }

    public void timKH() {
        Scanner sc = new Scanner(System.in);
        int dem = 0;
        String ID;
        System.out.print("Nhập mã/tên/số điên thoại khách hàng(nhập 1 trong 3 điều kiện) : ");
        ID = sc.nextLine();

        System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                   THÔNG  TIN  KHÁCH  HÀNG                                              |");
        System.out.println("+----------------+-------------------------+--------------+---------------------------------------+----------------------+");
        System.out.println("| Mã khách hàng  |    Tên khách hàng       | Số điện thoại|              Email                    |       Địa chỉ        |");
        System.out.println("+----------------+-------------------------+--------------+---------------------------------------+----------------------+");
        for (int i = 0; i < listKH.size(); i++) {
            if (checkID(ID) == true || listKH.get(i).getNameKH().contains(ID) || listKH.get(i).getSdtKH().contains(ID)) {
                System.out.printf("|%16s|%25s|%14s|%39s|%22s|\n", listKH.get(x).getIdKH(), listKH.get(x).getNameKH(), listKH.get(x).getSdtKH(),
                        listKH.get(i).getEmailKH(), listKH.get(i).getAddrKH());
                dem++;
                System.out.println("+----------------+-------------------------+--------------+---------------------------------------+----------------------+");
            }
        }
        if (dem == 0) {
            System.out.println("Không tồn tại khách hàng có mã này.");
        }
        System.out.println("");
        System.out.println("");
    }

    public void menu() {
        inTT();
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                               QUẢN   LÍ   KHÁCH  HÀNG                                                  |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       1. XEM  DANH SÁCH KHÁCH HÀNG                                                     |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       2. TÌM  KHÁCH  HÀNG(theo mã/tên/số điện thoại khách hàng)                        |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       3. THÊM  MỚI  1  KHÁCH  HÀNG                                                     |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       4. SỬA  THÔNG  TIN  KHÁCH  HÀNG                                                  |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       5. XÓA  THÔNG  TIN  KHÁCH  HÀNG                                                  |");
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
                        timKH();
                        break;
                    }
                    case 3: {
                        themKH();
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
                    default:
                        System.out.println("Mời nhập số từ 1 đến 6 !!");
                }
            } while (chon < 0 || chon > 6);

        } while (chon != 6);
    }

}

class main {

    public static void main(String[] args) {
        QuanLiKhachHang qlkh = new QuanLiKhachHang();
        qlkh.menu();
    }
}
