package net.galaxyvirus.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tier 1: Epic");
        System.out.println("Tier 2: Rare");
        System.out.println("Tier 1: Legendary");
        System.out.println();
        System.out.print("Enter the number of giftcodes to create for tier 1: ");
        int quantityTier1 = sc.nextInt();
        System.out.print("Enter the number of giftcodes to create for tier 2: ");
        int quantityTier2 = sc.nextInt();
        System.out.print("Enter the number of giftcodes to create for tier 3: ");
        int quantityTier3 = sc.nextInt();
        try {
            File myFile = new File("./result/giftcode.json");
            if (myFile.createNewFile()) {
                System.out.println("created a file containing data for your event ");
            }
            try {
                FileWriter giftcodeData = new FileWriter("./result/giftcode.json");
                FileWriter overviewFileWriter = new FileWriter("./result/giftcode.txt");

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

                giftcodeData.write("{\n");
                overviewFileWriter.write("Giftcode list:\n");

                // Tier 1
                overviewFileWriter.write("Tier 1:\n");
                for (int i = 1; i <= quantityTier1; i++) {
                    String giftcode = "";
                    for (int j = 0; j < giftcodeLength; j++) {
                        double random = Math.floor(Math.random() * allowedChars.length());
                        giftcode += allowedChars.charAt((int) random);
                    }
                    giftcodeData.write("\t\"" + giftcode + "\": {\n\t\t\"tier\": \"1\",\n\t\t\"giftcode\": \"" + giftcode + "\"\n\t},\n");
                    overviewFileWriter.write("\t" + giftcode + "\n");
                    System.out.println("Successfully exported giftcode number " + i + " of tier 1");
                }
                overviewFileWriter.write("Tier 2:\n");
                for (int i = 1; i <= quantityTier2; i++) {
                    String giftcode = "";
                    for (int j = 0; j < giftcodeLength; j++) {
                        double random = Math.floor(Math.random() * allowedChars.length());
                        giftcode += allowedChars.charAt((int) random);
                    }
                    giftcodeData.write("\t\"" + giftcode + "\": {\n\t\t\"tier\": \"2\",\n\t\t\"giftcode\": \"" + giftcode + "\"\n\t},\n");
                    overviewFileWriter.write("\t" + giftcode + "\n");
                    System.out.println("Successfully exported giftcode number " + i + " of tier 2");
                }
                overviewFileWriter.write("Tier 3:\n");
                for (int i = 1; i <= quantityTier3; i++) {
                    String giftcode = "";
                    for (int j = 0; j < giftcodeLength; j++) {
                        double random = Math.floor(Math.random() * allowedChars.length());
                        giftcode += allowedChars.charAt((int) random);
                    }
                    if (i == quantityTier3) {
                        giftcodeData.write("\t\"" + giftcode + "\": {\n\t\t\"tier\": \"3\",\n\t\t\"giftcode\": \"" + giftcode + "\"\n\t}\n");
                        overviewFileWriter.write("\t" + giftcode);
                    }
                    else {
                        giftcodeData.write("\t\"" + giftcode + "\": {\n\t\t\"tier\": \"3\",\n\t\t\"giftcode\": \"" + giftcode + "\"\n\t},\n");
                        overviewFileWriter.write("\t" + giftcode + "\n");
                    }
                    System.out.println("Successfully exported giftcode number " + i + " of tier 3");
                }
                giftcodeData.write("}");
                giftcodeData.close();
                overviewFileWriter.close();

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                System.out.println("successfully exported " + (quantityTier1 + quantityTier2 + quantityTier3) + " giftcode json");
                System.out.println("successfully exported " + (quantityTier1 + quantityTier2 + quantityTier3) + " giftcode lists");

                System.out.println("Completed in: " + elapsedTime + " ms");
            } catch (IOException e) {
                System.out.println("Error while processing file.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error when reading file.");
            e.printStackTrace();
        }
    }
}