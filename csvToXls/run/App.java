import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {

        //Creating a File object for directory
        String pathname = "C:\\workspace\\csvToXls";
        File directoryPath = new File(pathname);
        //List of all files and directories
        String contents[] = directoryPath.list();
        if (contents.length <= 0) {
            System.out.println("no files there");
            return;
        }
        List<String> csvs = new ArrayList<>();

        for (int i = 0; i < contents.length; i++) {
            if (contents[i].contains(".csv")) {
                String filePath = pathname + "\\" + contents[i];
                csvs.add(filePath);
                System.out.printf("run: " + filePath);
                csvToXLSX(filePath, filePath.replace(".csv", ".xlsx"));
            }
        }
    }

    public static Class[] fields = {
            String.class,//1
            String.class, //2
            String.class, //3
            String.class, //4
            Number.class, //5
            Date.class, //6
            Number.class, //7
            Date.class, //8
            String.class, //9
            String.class, //10
            String.class, //11
            Date.class,//12
            Date.class, //13
            Number.class, //14
            String.class, //15
            Date.class, //16
            Date.class, //17
            Date.class, //18
            Number.class, //19
            String.class, //20
            Date.class, //21
            String.class, //22
            String.class, //23
            String.class, //24
            Date.class, //25
            Date.class, //26
            String.class,//27
            Number.class//28
    };


    public static Object getField(int i, String value) throws ParseException {
        if (String.class.equals(fields[i])) {
            return value;
        } else if (Date.class.equals(fields[i])) {//"dd/MM/yyyy"
            Date date = new SimpleDateFormat("yyyyMMdd").parse(value);
            return date;
        } else if (Number.class.equals(fields[i])) {
            Float num = Float.parseFloat(value);
            return num;
            //return num;
        } else {
            throw new RuntimeException("format not supported");
        }
    }

    public static void csvToXLSX(String csvFileAddress, String xlsxFileAddress) {
        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine = null;
            int RowNum = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow = sheet.createRow(RowNum);
                for (int i = 0; i < str.length; i++) {
                    CellStyle cellStyle = workBook.createCellStyle();
                    XSSFCell cell = currentRow.createCell(i);
                    Object field = getField(i, str[i]);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Date) {
                        CreationHelper createHelper = workBook.getCreationHelper();
                        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

                        cell.setCellValue((Date) field);
                        cell.setCellStyle(cellStyle);
                    } else if (field instanceof Float) {
                        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                        DecimalFormat df = new DecimalFormat("0.00");
                        df.setMaximumFractionDigits(2);
                        String format = df.format(field);
                        cell.setCellValue(format);
                        cell.setCellStyle(cellStyle);
                    }
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddress);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
    }
}
