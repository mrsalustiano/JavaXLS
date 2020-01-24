package com.dev.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
  
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class EditaExcel {


    private static final String fileName = "C:/Users/m_sal/Desktop/java/novo.xls";
     
    public static void main(String[] args) throws IOException  {

          try {
                 FileInputStream file = new FileInputStream(new File(
                               EditaExcel.fileName));

                 HSSFWorkbook workbook = new HSSFWorkbook(file);
                 HSSFSheet sheetAlunos = workbook.getSheetAt(0);

                 for (int i = 0; i < sheetAlunos.getPhysicalNumberOfRows(); i++) {

                        Row row = sheetAlunos.getRow(i);
                        Cell cellNota1 = row.getCell(2);
                        if (cellNota1.getNumericCellValue() < 9) {
                               cellNota1.setCellValue(cellNota1.getNumericCellValue() + 1);
                        } else {
                               cellNota1.setCellValue(10);
                        }

                        Cell cellNota2 = row.getCell(3);
                        Cell cellMedia = row.getCell(4);
                        cellMedia
                                     .setCellValue((cellNota1.getNumericCellValue() + cellNota2
                                                   .getNumericCellValue()) / 2);
                        Cell cellAprovado = row.getCell(5);
                        cellAprovado.setCellValue(cellMedia.getNumericCellValue() >= 6);
                 }
                 file.close();

                 FileOutputStream outFile = new FileOutputStream(new File(EditaExcel.fileName));
                 workbook.write(outFile);
                 outFile.close();
                 System.out.println("Arquivo Excel editado com sucesso!");

          } catch (FileNotFoundException e) {
                 e.printStackTrace();
                 System.out.println("Arquivo Excel n�o encontrado!");
          } catch (IOException e) {
                 e.printStackTrace();
                 System.out.println("Erro na edi��o do arquivo!");
          }

    }

}
