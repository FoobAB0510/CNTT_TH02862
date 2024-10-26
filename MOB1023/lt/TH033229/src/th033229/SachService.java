/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package th033229;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SachService {
    ArrayList<Sach> list = new ArrayList<>();

    public SachService() {
        list.add(new Sach("Java", "Trinh thám", 100));
        list.add(new Sach("C#", "Tiểu thuyết", 100));
        list.add(new Sach("PHP", "Tiểu thuyết", 120));
        list.add(new Sach("Angular Js", "Trinh thám", 110));
        list.add(new Sach("HTML & CSS", "Trinh thám", 90));
    }
    
    public ArrayList<Sach> docfile(){
        String path = "Sach.txt";
        ArrayList<Sach> listSach = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {                
                String[] parts = line.split(",\\s*");
                String ten = parts[0];
                String theLoai = parts[1];
                Integer gia = Integer.parseInt(parts[2]);
                
                Sach sach = new Sach(ten, theLoai, gia);
                listSach.add(sach);
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: "+e.getMessage());
        }
        return listSach;
    }
    
    public ArrayList<Sach> getDanhSach(){
        return list;
    }
   
    public String them(Sach sach){
        list.add(sach);
        return "Thêm và ghi file thành công";
    }
    
    public String xoa(int viTri){
        list.remove(viTri);
        return "Xoá thành công";
    }
}
