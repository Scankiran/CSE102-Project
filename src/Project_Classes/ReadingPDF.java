package Project_Classes;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
//import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class ReadingPDF
{
    private String tax; // Aidat tutarı
    private String IBAN; // ALICININ (YÖNETİCİNİN) IBANI.
    private int lineofIBAN; // hangi satırda IBAN bulunduğunu saklar.
    private int lineofAmount; // hangi satırda ödeme miktarı bulunduğunu saklar.
    private int lineofDate;
    private int rowofDate;

    public ReadingPDF (String tax , String IBAN , int lineofAmount , int lineofIBAN)
    {
        this.tax=tax;
        this.IBAN=IBAN;
        this.lineofAmount = lineofAmount;
        this.lineofIBAN = lineofIBAN;
    }
    //TODO:public abstract String getDate(String filePath);


    public boolean correctIBAN(String filePath)
    {
        try
        {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readIBAN = stripper.getText(pdDocument);
            Scanner input = new Scanner(readIBAN);
            for(int i = 1 ; i<lineofIBAN ; i++)
            {
                input.nextLine();
            }
                return input.nextLine().matches(".*"+IBAN+".*");


        }
        catch(Exception ex) //TODO: BİR SORUN MEYDANA GELDİ MESAJI VERİLSİN
        {

        }
        return false;
    }


    public boolean checkTax(String filePath) // String cinsinden girilen aidat değerinin ödenmiş olup olmadığına bakıyor.
    {

        try
        {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readFee = stripper.getText(pdDocument);
            Scanner input = new Scanner(readFee);
            for (int i = 1; i < lineofAmount; i++)
            {
                input.nextLine();
            }

            return input.nextLine().matches(".*" + tax + ".*");
        }
        catch (Exception ex) //TODO: BİR SORUN MEYDANA GELDİ MESAJI VERİLSİN
        {}
        return false;
    }

    //TODO: Yöneticiden IBAN'I boşluk karakterli içerecek TRXX XXXX XXXX XXXX XXXX XXXX XX şeklinde istenmeli..
    //TODO: ÖDEME ONAYLANDIKTAN SONRA MAKBUZ GÖNDER.



}

class Vakifbank extends ReadingPDF
{
    private String tax;
    private String IBAN;
    private final int lineofDate = 9;
    private int rowofDate = 4;


    public Vakifbank (String tax , String IBAN)
    {
        super(tax , IBAN , 13 , 11);
    }

    public String getDate(String filePath)
    {
        try {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readDate = stripper.getText(pdDocument);
            Scanner input = new Scanner(readDate);
            for (int i = 1; i < lineofDate; i++) {
                input.nextLine();
            }
            for (int i = 1; i < rowofDate; i++) {
                input.next();
            }

            return input.next().replace("", "");
        }catch(Exception ex){}
        return "";

    }
}

class Ziraatbank extends  ReadingPDF
{
    private String tax;
    private String IBAN;
    private final int lineofDate = 10;
    private int rowofDate = 3;

    public Ziraatbank (String tax , String IBAN)
    {
        super(tax , IBAN.replace(" " , "") , 20 , 17);
        this.IBAN=IBAN.replace(" " , "");
        this.tax=tax;
    }

    public String getDate(String filePath)
    {
        try {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readDate = stripper.getText(pdDocument);
            Scanner input = new Scanner(readDate);
            for (int i = 1; i < lineofDate; i++) {
                input.nextLine();
            }
            for (int i = 1; i < rowofDate; i++) {
                input.next();
            }

            return input.next().replace("", "");
        }catch(Exception ex){}
        return "";

    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN.replace(" " , "");
    }
}

class Isbank extends ReadingPDF
{
    private String tax;
    private String IBAN;
    public Isbank(String tax , String IBAN)
    {
        super(tax , IBAN , 14 , 12);
    }
}



class TEB extends ReadingPDF
{
    private String tax;
    private String IBAN;
    public TEB (String tax , String IBAN) { super (tax , IBAN , 9 , 7);}
}
//TODO: TEB doğru şekilde çalışıyor mu kontrol et.