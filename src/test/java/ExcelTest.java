import org.apache.poi.hssf.usermodel.*;

import java.io.*;

/**
 * 项目名称： bs
 * 类名称： ExcelTest
 * 描述：
 * @author 张靖烽
 * 创建时间 2018-05-10 17:30
 * 修改人：张靖烽  修改日期： 2018-05-10
 * 修改备注：
 **/
public class ExcelTest {
    public static void main(String[] args) throws Exception {
        testWriteExcel();
        //testReadExcel();
    }

    private static void testWriteExcel() throws Exception {
         /*
         * 注意这只是07版本以前的做法对应的excel文件的后缀名为.xls
         * 07版本和07版本以后的做法excel文件的后缀名为.xlsx
         */
        //创建新工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        HSSFRow row = sheet.createRow(0);
        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        HSSFCell cell = row.createCell(2);
        //设置单元格的值,即C1的值(第一行,第三列)
        cell.setCellValue("hello sheet");
        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("E:\\bs\\target\\bs\\111.xls"));
        workbook.write(fos);
        workbook.close();
        fos.close();
    }

    private static void testReadExcel() throws Exception {
        //创建输入流
        FileInputStream fis = new FileInputStream(new File("E:\\bs\\target\\bs\\111.xls"));
        //通过构造函数传参
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        //获取工作表
        HSSFSheet sheet = workbook.getSheetAt(0);
        //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
        HSSFRow row = sheet.getRow(0);
        //获取单元格,row已经确定了行号,列号作为参数传递给getCell,第一列从0开始计算
        HSSFCell cell = row.getCell(2);
        //设置单元格的值,即C1的值(第一行,第三列)
        String cellValue = cell.getStringCellValue();
        System.out.println("第一行第三列的值是" + cellValue);
        workbook.close();
        fis.close();
    }
}
