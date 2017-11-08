/*
 * GPLv3
 */
package XSterlyzer;

/**
 *
 * @author Xiao Zhou
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Xiao Zhou
 */
public class XSterlyzerDataSaver {

    private final FileOutputStream FileOut;
    private final XSSFWorkbook XWorkbook;
    private final ArrayList<XSSFSheet> XSheet;
    private final int[] RowCount;

    public XSterlyzerDataSaver(String fileName) throws FileNotFoundException {
        FileOut = new FileOutputStream(fileName);
        XWorkbook = new XSSFWorkbook();
        XSheet = new ArrayList<>();
        XSheet.add(XWorkbook.createSheet("Area and Mean"));
        XSheet.add(XWorkbook.createSheet("Aster Counts"));        
        RowCount = new int[2];
        XSSFRow row = XSheet.get(0).createRow(RowCount[0]);
        row.createCell(0).setCellValue("DataSet");
        row.createCell(1).setCellValue("Area");
        row.createCell(2).setCellValue("Mean");
        RowCount[0]++;
        row = XSheet.get(1).createRow(RowCount[1]);  
        row.createCell(0).setCellValue("DataSet");
        row.createCell(1).setCellValue("Aster Count");   
        RowCount[1]++;
        

    }

    public int saveAreaAndMean(double[][] dataSets) throws IOException {
        for (int i = 0; i < dataSets.length; i++) {
            saveData(XSheet.get(0), dataSets[i], 0);
            RowCount[0]++;
        }

        return RowCount[0];
    }

    public int saveAsterCount(double count) throws IOException {
        saveData(XSheet.get(1), new double[]{count}, 1);
        RowCount[1]++;
        return RowCount[1];
    }

    private void saveData(XSSFSheet sheet, double[] data, int sheetNum) {
        XSSFRow row = sheet.createRow(RowCount[sheetNum]);
        row.createCell(0).setCellValue("DataSet" + Integer.toString(RowCount[sheetNum] + 1));
        for (int i = 0; i < data.length; i++) {
            row.createCell(i + 1).setCellValue(data[i]);
        }

    }

    public void close() throws IOException {
        XWorkbook.write(FileOut);
        FileOut.close();
    }
}
