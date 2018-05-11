package com.bs.reflect.fill;

import com.bs.common.ServerResponse;
import com.bs.reflect.BaseModal;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;

/**
 * 项目名称： qx
 * 类名称： StudentTem
 * 描述：TODO
 * @author 张靖烽
 * 创建时间 2018-05-11 16:07
 * 修改人：张靖烽  修改日期： 2018-05-11
 * 修改备注：
 **/
public class StudentTem extends BaseModal  {

    @Override
    public ServerResponse parse() throws Exception {
        //创建输入流
        FileInputStream fis = new FileInputStream(getFile());
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
        return null;
    }
}
