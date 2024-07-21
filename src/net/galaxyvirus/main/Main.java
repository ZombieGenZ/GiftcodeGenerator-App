package net.galaxyvirus.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên dự án: ");
        String fileName = sc.nextLine();
        System.out.print("Nhập giá trị nhỏ nhất: ");
        int min = sc.nextInt();
        System.out.print("Nhập giá trị lớn nhất: ");
        int max = sc.nextInt();
        System.out.print("Nhập số lượng mã cần tạo: ");
        int quantity = sc.nextInt();
        try {
            File myFile = new File("result/" + fileName.trim() + ".result.txt");
            if (myFile.createNewFile()) {
                System.out.println("Đã tạo file dự án mới với tên " + myFile.getName());
            }
            try {
                FileWriter myFileWriter = new FileWriter("result/" + fileName.trim() + ".result.txt");
                FileWriter overviewFileWriter = new FileWriter("result/" + fileName.trim() + ".list.txt");

                int giftcodeLength = 12;

                final boolean includeLowerCase = false;
                final boolean includeUpperCase = true;
                final boolean includeNumber = true;

                final String LowerCaseChars = "abcdefghijklmnopqrstuvwyz";
                final String UpperCaseChars = "ABCDEFGHJKLMNOPQRSTUVWYZ";
                final String NumberChars = "0123456789";

                String allowedChars = "";

                allowedChars += includeLowerCase ? LowerCaseChars : "";
                allowedChars += includeUpperCase ? UpperCaseChars : "";
                allowedChars += includeNumber ? NumberChars : "";

                long startTime = System.currentTimeMillis();

                overviewFileWriter.write("Giftcode list " + min + " - " + max + ":\n");

                for (int i = 1; i <= quantity; i++) {
                    String giftcode = "";
                    for (int j = 0; j < giftcodeLength; j++) {
                        double random = Math.floor(Math.random() * allowedChars.length());
                        giftcode += allowedChars.charAt((int) random);
                    }
                    if (i == quantity) {
                        myFileWriter.write("\t\"" + giftcode + "\": {\n\t\t\"giftcode\": \"" + giftcode + "\",\n\t\t\"min\": " + min + ",\n\t\t\"max\": " + max + "\n\t},");
                        overviewFileWriter.write("\t" + giftcode);
                    }
                    else {
                        myFileWriter.write("\t\"" + giftcode + "\": {\n\t\t\"giftcode\": \"" + giftcode + "\",\n\t\t\"min\": " + min + ",\n\t\t\"max\": " + max + "\n\t},\n");
                        overviewFileWriter.write("\t" + giftcode + "\n");
                    }
                    System.out.println("Đã xuất giftcode số " + i + " hoàn tất");
                }
                myFileWriter.close();
                overviewFileWriter.close();

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                System.out.println("Đã xuất hoàn tất " + quantity + " mã json giftcode vào đường dẩn: result/" + fileName.trim() + "/json.txt");
                System.out.println("Đã xuất hoàn tất " + quantity + " mã giftcode vào đường dẩn: result/" + fileName.trim() + "/json.txt");

                System.out.println("Thời gian thực hiện: " + elapsedTime + " ms");
            } catch (IOException e) {
                System.out.println("Lỗi khi xử lý file.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file file.");
            e.printStackTrace();
        }
    }
}