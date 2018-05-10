package com.bs.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 项目名称： bs
 * 类名称： DownFileController
 * 描述：文件下载
 * @author 张靖烽
 * 创建时间 2018-05-10 18:51
 * 修改人：张靖烽  修改日期： 2018-05-10
 * 修改备注：
 **/
@Controller
@RequestMapping("/excel/")
public class DownFileController {

    @RequestMapping(value = "export.do", method = RequestMethod.POST)
    @ResponseBody
    private static void testWriteExcel(HttpServletRequest request, HttpServletResponse response, String fileName)
            throws Exception {
        String path = request.getServletContext().getRealPath("") + "\\" + fileName;
        File file = new File(path);
        String filename = file.getName();
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.setContentType("application/octet-stream");
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        IOUtils.copy(fis, toClient);
        fis.close();
        toClient.flush();
        toClient.close();
    }
}
