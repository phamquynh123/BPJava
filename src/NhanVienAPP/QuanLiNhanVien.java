/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVienAPP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class QuanLiNhanVien {

    Big_project.run j16 = new Big_project.run();
    public static ArrayList<NhanVien> listNV = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int x;
    String a;

//    public boolean checkDN(String maNV) {
//        for (int i = 0; i < listNV.size(); i++) {
////            if (listNV.get(i).getIdNV().equalsIgnoreCase(maNV)
////                    && listNV.get(i).getPassNV().equalsIgnoreCase(maNV)) {
////
////            }
//            a = maNV;
//            if (maNV.contains("ad") || maNV.contains("AD")) {
//                return true;
//            }
//        }
//        return false;
//    }

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

    public void loadFile() {
        try {
            BufferedReader br = null;

            FileReader fr = null;
            fr = new FileReader("TTNhanVien1.txt");
            br = new BufferedReader(fr);
            listNV = new ArrayList<>();
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String listNV[] = s.split("\t");
                    NhanVien nv = new NhanVien();
                    nv.setIdNV(listNV[0]);
                    nv.setNameNV(listNV[1]);
                    nv.setSdtNV(listNV[2]);
                    nv.setEmailNV(listNV[3]);
                    nv.setPassNV(listNV[4]);
                    nv.setAddrNV(listNV[5]);
                    this.listNV.add(nv);

                }
            } catch (IOException ex) {
                Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void save() {

        try {
            Writer w = null;
            w = new FileWriter("TTNhanVien1.txt");

            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listNV.size(); i++) {
                String row = "";
                row += listNV.get(i).getIdNV() + "\t";
                row += listNV.get(i).getNameNV() + "\t";
                row += listNV.get(i).getSdtNV() + "\t";
                row += listNV.get(i).getEmailNV() + "\t";
                row += listNV.get(i).getPassNV() + "\t";
                row += listNV.get(i).getAddrNV() + "\n";
                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public boolean checkID(String ID) {

        for (int i = 0; i < listNV.size(); i++) {

            if (ID.equalsIgnoreCase(listNV.get(i).getIdNV())) {
                x = i;
                return true;
            }
        }
        return false;
    }

    public boolean checkName(String name) {
        for (int i = 0; i < 10; i++) {
            if (name.contains(i + "") || name.equalsIgnoreCase("")) {
                System.out.println("Tên nhân viên không được chứa số.");
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
        if (((sdt.length() >= 10) && (sdt.length() <= 11)) == false) {
            System.out.println("Số điện thoại phải có 10 hoặc 11 số");
            return true;
        }
        try {
            Integer.parseInt(sdt);
        } catch (Exception e) {
            System.out.println("Số điện thoại không được chứa kí tự");
            return true;
        }

        return false;
    }

    public void themNV() {
        //if (checkDN(maNV1)==true) {
        loadFile();
        Scanner sc1 = new Scanner(System.in);
        NhanVien kh = new NhanVien();
        String ID;
        System.out.println("Nhập mã nhân viên : ");
        ID = sc1.nextLine();

        if (checkID(ID) == true) {
            System.out.println("Đã tồn tại nhân viên này.");
        } else {

            kh.addTTNV(ID);
            listNV.add(kh);
            save();
        }
//        } else {
//            System.err.println("Bạn không có quyền truy cập chức năng này.");
//        }
    }

    public void inTT() {
        loadFile();
        int dem = 0;
        if (listNV.isEmpty()) {
            System.out.println("Chưa có nhân viên trong danh sách ");
            dem++;
        }
        if (dem == 0) {
            System.out.println(" Thông tin các nhân viên ");
            System.out.println("+------------------------------------------------------------------------------------------------+");
            System.out.println("|                                      THÔNG  TIN  NHÂN  VIÊN                                    |");
            System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
            System.out.println("| Mã nhân viên |    Tên nhân viên  | Số điện thoại|            Email             |     địa chỉ   |");
            System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
            for (int i = 0; i < listNV.size(); i++) {
                System.out.printf("|%14s|%19s|%14s|%30s|%15s|\n", listNV.get(i).getIdNV(), listNV.get(i).getNameNV(), listNV.get(i).getSdtNV(),
                        listNV.get(i).getEmailNV(), listNV.get(i).getAddrNV());
                System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
            }
            save();

        }
        System.out.println("");
        System.out.println("");

    }

    public void suaTT() {
        loadFile();
        String id;
        Scanner sc = new Scanner(System.in);
        loadFile();
        System.out.println("*************************************************");
        System.out.println("Sửa thông tin nhân viên : ");
        System.out.print("Nhập mã nhân viên : ");
        id = sc.nextLine();
        System.out.println("Nhấn enter để bỏ qua.");
        String s;
        if (checkID(id) == true) {
            //  arrL.get(i).setName(sc.nextLine());
            System.out.println("Tên nhân viên cũ : " + listNV.get(x).getNameNV());
            do {
                System.out.print("Tên nhân viên mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listNV.get(x).setNameNV(s);
            } while (checkName(listNV.get(x).getNameNV()));

            System.out.println("Số điện thoại cũ : " + listNV.get(x).getSdtNV());
            do {
                System.out.print("Số điện thoại mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }

                listNV.get(x).setSdtNV(s);
            } while (checksdt(listNV.get(x).getSdtNV()));

            System.out.println("Email cũ : " + listNV.get(x).getEmailNV());
            do {
                System.out.print("Email mới : ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("")) {
                    break;
                }
                listNV.get(x).setEmailNV(s);
            } while (kiemTraEmail(listNV.get(x).getEmailNV()));

            System.out.println("Địa chỉ cũ : " + listNV.get(x).getAddrNV());
            System.out.print("Địa chỉ mới : ");
            s = sc.nextLine();
            if (s.equalsIgnoreCase("")) {
                listNV.get(x).getAddrNV();
            } else {
                listNV.get(x).setAddrNV(s);
            }
        } else {
            System.out.println("Không tồn tại nhân viên này.");
        }
        System.out.println("Đã sửa thông tin thành công.");
        save();

        System.out.println(" Thông tin nhân viên đã sửa.");

        System.out.println("+------------------------------------------------------------------------------------------------+");
        System.out.println("|                                     THÔNG  TIN  NHÂN  VIÊN                                     |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
        System.out.println("| Mã nhân viên |    Tên nhân viên  | Số điện thoại|            Email             |     địa chỉ   |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");

        System.out.printf("|%14s|%19s|%14s|%30s|%15s|\n", listNV.get(x).getIdNV(), listNV.get(x).getNameNV(), listNV.get(x).getSdtNV(),
                listNV.get(x).getEmailNV(), listNV.get(x).getAddrNV());
        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
        System.out.println("");
        System.out.println("");
    }

    public void xoaTT() {
        Scanner sc = new Scanner(System.in);
        loadFile();
        String ID;
        System.out.print("Nhập mã nhân viên : ");
        ID = sc.nextLine();
        checkID(ID);
        if (checkID(ID) == true) {
            listNV.remove(x);
            System.err.println("ĐÃ xóa thông tin nhân viên.");
        }
        System.out.println("");
        System.out.println("");
        save();
    }

    public void timNV() {
        loadFile();
        Scanner sc = new Scanner(System.in);
        String tim;
        System.out.print("Nhập mã//số điện thoại//tên nhân viên : ");
        tim = sc.nextLine();
        int dem = 0;

        System.out.println(" Thông tin các nhân viên ");
        System.out.println("+------------------------------------------------------------------------------------------------+");
        System.out.println("|                                     THÔNG  TIN  NHÂN  VIÊN                                     |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
        System.out.println("| Mã nhân viên |    Tên nhân viên  | Số điện thoại|            Email             |     địa chỉ   |");
        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
        for (int i = 0; i < listNV.size(); i++) {
            if (checkID(tim) == true || listNV.get(i).getNameNV().contains(tim) || listNV.get(i).getSdtNV().contains(tim)) {
                System.out.printf("|%14s|%19s|%14s|%30s|%15s|\n", listNV.get(i).getIdNV(), listNV.get(i).getNameNV(), listNV.get(i).getSdtNV(),
                        listNV.get(i).getEmailNV(), listNV.get(i).getAddrNV());
                System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("                                  Không tồn tại nhân viên này !");
        }
        System.out.println("");
        System.out.println("");

    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                               QUẢN   LÍ   NHÂN  VIÊN                                                   |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       1. XEM  DANH SÁCH NHÂN  VIÊN                                                     |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       2. TÌM  NHÂN  VIÊN (theo tên/ID/số điên thoại)                                   |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       3. THÊM  MỚI  1  NHÂN  VIÊN                                                      |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       4. SỬA  THÔNG  TIN  NHÂN  VIÊN                                                   |");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       5. XÓA  THÔNG  TIN  NHÂN  VIÊN                                                   |");
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
                        timNV();
                        break;
                    }
                    case 3: {
                        themNV();
                        break;

//                        String maNV = a;
//                        if (checkDN(maNV) == true) {
//                            themNV(maNV);
//                            break;
//                        }else System.out.println("Bạn không có quyền truy cập chức năng này.");

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
        QuanLiNhanVien qlkh = new QuanLiNhanVien();
        qlkh.menu();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package NhanVienAPP;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.math.BigInteger;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Asus
// */
//public class QuanLiNhanVien {
//
//    Big_project.run j16 = new Big_project.run();
//    public static ArrayList<NhanVien> listNV = new ArrayList<>();
//    Scanner sc = new Scanner(System.in);
//    int x;
//
//    public String md5(String str) {
//        String result = "";
//        MessageDigest digest;
//        try {
//            digest = MessageDigest.getInstance("MD5");
//            digest.update(str.getBytes());
//            BigInteger bigInteger = new BigInteger(1, digest.digest());
//            result = bigInteger.toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public void loadFile() {
//        try {
//            BufferedReader br = null;
//
//            FileReader fr = null;
//            fr = new FileReader("TTNhanVien.txt");
//            br = new BufferedReader(fr);
//            listNV = new ArrayList<>();
//            String s = null;
//            try {
//                while ((s = br.readLine()) != null) {
//                    String listNV[] = s.split("\t");
//                    NhanVien nv = new NhanVien();
//                    nv.setIdNV(listNV[0]);
//                    nv.setNameNV(listNV[1]);
//                    nv.setSdtNV(listNV[2]);
//                    nv.setEmailNV(listNV[3]);
//                    nv.setPassNV(listNV[4]);
//                    nv.setAddrNV(listNV[5]);
//                    this.listNV.add(nv);
//
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void save() {
//
//        try {
//            Writer w = null;
//            w = new FileWriter("TTNhanVien.txt");
//
//            BufferedWriter bw = new BufferedWriter(w);
//            String data = "";
//            for (int i = 0; i < listNV.size(); i++) {
//                String row = "";
//                row += listNV.get(i).getIdNV() + "\t";
//                row += listNV.get(i).getNameNV() + "\t";
//                row += listNV.get(i).getSdtNV() + "\t";
//                row += listNV.get(i).getEmailNV() + "\t";
//                row += listNV.get(i).getPassNV() + "\t";
//                row += listNV.get(i).getAddrNV() + "\n";
//                data += row;
//            }
//            bw.write(data);
//            bw.close();
//        } catch (IOException ex) {
//            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public boolean kiemTraEmail(String email) {
//        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        boolean ktEmail = email.matches(dinhDangEmail);
//        if (ktEmail == false) {
//            System.err.println("Email sai, nhập lại theo dạng abc@domain.com");
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkID(String ID) {
//
//        for (int i = 0; i < listNV.size(); i++) {
//
//            if (ID.equalsIgnoreCase(listNV.get(i).getIdNV())) {
//                x = i;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean checkName(String name) {
//        for (int i = 0; i < 10; i++) {
//            if (name.contains(i + "") || name.equalsIgnoreCase("")) {
//                System.out.println("Tên nhân viên không được chứa số.");
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean checksdt(String sdt) {
//
//        if (sdt.startsWith(0 + "") == false) {
//            System.out.println("Số điện thoại phải bắt đầu bằng só 0");
//            return true;
//        }
//        if (((sdt.length() >= 10) && (sdt.length() <= 11)) == false) {
//            System.out.println("Số điện thoại phải có 10 hoặc 11 số");
//            return true;
//        }
//        try {
//            Integer.parseInt(sdt);
//        } catch (Exception e) {
//            System.out.println("Số điện thoại không được chứa kí tự");
//            return true;
//        }
//
//        return false;
//    }
//
//    public void themNV() {
//        loadFile();
//        Scanner sc1 = new Scanner(System.in);
//        NhanVien kh = new NhanVien();
//        String ID;
//        System.out.println("Nhập mã nhân viên : ");
//        ID = sc1.nextLine();
//
//        if (checkID(ID) == true) {
//            System.out.println("Đã tồn tại nhân viên này.");
//        } else {
//
//            kh.addTTNV(ID);
//            listNV.add(kh);
//            save();
//        }
//    }
//
//    public void inTT() { //save();
//        loadFile();
//        int dem = 0;
//        if (listNV.isEmpty()) {
//            System.out.println("Chưa có nhân viên trong danh sách ");
//            dem++;
//        }
//        if (dem == 0) {
//            System.out.println(" Thông tin các nhân viên ");
//            System.out.println("+------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                                   THÔNG  TIN  NHÂN                             |");
//            System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
//            System.out.println("| Mã nhân viên |    Tên nhân viên  | Số điện thoại|            Email             |     địa chỉ   |");
//            System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
//            for (int i = 0; i < listNV.size(); i++) {
//                System.out.printf("|%14s|%19s|%14s|%30s|%15s|\n", listNV.get(i).getIdNV(), listNV.get(i).getNameNV(), listNV.get(i).getSdtNV(),
//                        listNV.get(i).getEmailNV(), listNV.get(i).getAddrNV());
//                System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
//            }
//            save();
//        }
//        System.out.println("");
//        System.out.println("");
//
//    }
//
//    public void suaTT() {
//        loadFile();
//        String id;
//        Scanner sc = new Scanner(System.in);
//        loadFile();
//        System.out.println("*************************************************");
//        System.out.println("Sửa thông tin nhân viên : ");
//        System.out.println("Nhập mã nhân viên : ");
//        id = sc.nextLine();
//        System.out.println("Nhấn enter để bỏ qua.");
//        String s;
//        if (checkID(id) == true) {
//            do {
//                System.out.println("Tên nhân viên cũ : " + listNV.get(x).getNameNV());
//                System.out.print("Tên nhân viên mới : ");
//                s = sc.nextLine();
//                if (s.equalsIgnoreCase("")) {
//                    break;
//                }
//                listNV.get(x).setNameNV(s);
//            } while (checkName(listNV.get(x).getNameNV()));
//
//            do {
//                System.out.println("Số điện thoại cũ : " + listNV.get(x).getSdtNV());
//                System.out.print("Số điện thoại mới : ");
//                s = sc.nextLine();
//                if (s.equalsIgnoreCase("")) {
//                    break;
//                }
//
//                listNV.get(x).setSdtNV(s);
//            } while (checksdt(listNV.get(x).getSdtNV()));
//
//            do {
//                System.out.println("Email cũ : " + listNV.get(x).getEmailNV());
//                System.out.print("Email mới : ");
//                s = sc.nextLine();
//                if (s.equalsIgnoreCase("")) {
//                    break;
//                }
//                listNV.get(x).setEmailNV(s);
//            } while (kiemTraEmail(listNV.get(x).getEmailNV()));
//
//            System.out.println("Địa chỉ cũ : " + listNV.get(x).getAddrNV());
//            do {
//                System.out.print("Địa chỉ mới : ");
//                s = sc.nextLine();
//                if (s.equalsIgnoreCase("")) {
//                    break;
//                }
//                listNV.get(x).setAddrNV(s);
//            } while (listNV.get(x).getAddrNV());
//            save();
//
//        } else {
//            System.out.println("Không tồn tại nhân viên này.");
//        }
//        save();
//
//    }
//
//    public void xoaTT() {
//        Scanner sc = new Scanner(System.in);
//        loadFile();
//        String ID;
//        System.out.print("Nhập mã nhân viên : ");
//        ID = sc.nextLine();
//        checkID(ID);
//        if (checkID(ID) == true) {
//            listNV.remove(x);
//            System.err.println("ĐÃ xóa thông tin nhân viên.");
//        }
//        System.out.println("");
//        System.out.println("");
//        save();
//    }
//
//    public void timNV() {
//        loadFile();
//        Scanner sc = new Scanner(System.in);
//        String tim;
//        System.out.print("Nhập mã//số điện thoại//tên nhân viên : ");
//        tim = sc.nextLine();
//        int dem = 0;
//
//        System.out.println(" Thông tin các nhân viên ");
//        System.out.println("+------------------------------------------------------------------------------------------------+");
//        System.out.println("|                                     THÔNG  TIN  NHÂN  VIÊN                                     |");
//        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
//        System.out.println("| Mã nhân viên |    Tên nhân viên  | Số điện thoại|            Email             |     địa chỉ   |");
//        System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
//        for (int i = 0; i < listNV.size(); i++) {
//            if (checkID(tim) == true || listNV.get(x).getNameNV().contains(tim) || listNV.get(x).getSdtNV().contains(tim)) {
//                System.out.printf("|%14s|%19s|%14s|%30s|%15s|\n", listNV.get(i).getIdNV(), listNV.get(i).getNameNV(), listNV.get(i).getSdtNV(),
//                        listNV.get(i).getEmailNV(), listNV.get(i).getAddrNV());
//            }
//            System.out.println("+--------------+-------------------+--------------+------------------------------+---------------+");
//            dem++;
//        }
//
//        if (dem == 0) {
//            System.out.println("Không tồn tại nhân viên này !");
//
//            System.out.println("");
//            System.out.println("");
//        }
//    }
//
//    public void menu() {
//        Scanner sc = new Scanner(System.in);
//        int chon;
//        do {
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                               QUẢN   LÍ   NHÂN  VIÊN                                                   |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                       1. XEM  DANH SÁCH NHÂN  VIÊN                                                     |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                       2. TÌM  NHÂN  VIÊN (theo tên/ID/số điên thoại)                                   |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                       3. THÊM  MỚI  1  NHÂN  VIÊN                                                      |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                       4. SỬA  THÔNG  TIN  NHÂN  VIÊN                                                   |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                       5. XÓA  THÔNG  TIN  NHÂN  VIÊN                                                   |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            System.out.println("|                                       6. QUAY  LẠI  MENU  CHÍNH                                                        |");
//            System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
//            do {
//                System.out.print("Mời bạn chọn chức năng : ");
//                chon = sc.nextInt();
//                switch (chon) {
//                    case 1: {
//                        inTT();
//                        break;
//                    }
//                    case 2: {
//                        timNV();
//                        break;
//                    }
//                    case 3: {
//                        themNV();
//                        break;
//                    }
//                    case 4: {
//                        suaTT();
//                        break;
//                    }
//                    case 5: {
//                        xoaTT();
//                        break;
//                    }
//                    case 6: {
//                        j16.menuMain();
//                        break;
//                    }
//                    default:
//                        System.out.println("Mời nhập số từ 1 đến 6 !!");
//                }
//            } while (chon < 0 || chon > 6);
//
//        } while (chon != 6);
//    }
//}
//
//class main {
//
//    public static void main(String[] args) {
//        QuanLiNhanVien qlkh = new QuanLiNhanVien();
//        qlkh.menu();
//    }
//}
