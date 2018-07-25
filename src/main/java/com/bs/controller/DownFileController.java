package com.bs.controller;

import com.bs.common.ServerResponse;
import com.bs.reflect.BaseModal;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 项目名称： bs
 * 类名称： DownFileController
 * 描述：文件下载
 *
 * @author 张靖烽
 * 创建时间 2018-05-10 18:51
 * 修改人：张靖烽  修改日期： 2018-05-10
 * 修改备注：
 **/
@Controller
@RequestMapping("/file/")
public class DownFileController {

    private static final Logger log = LoggerFactory.getLogger(DownFileController.class);

    /**
     * @author 张靖烽
     * @description 下载模板文件
     * @createtime 2018-05-11 15:20
     */
    @RequestMapping(value = "downFile.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse downFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            String path = request.getServletContext().getRealPath("") + "\\file\\download\\" + fileName;
            File file = new File(path);
            String filename = file.getName();
            fis = new BufferedInputStream(new FileInputStream(path));
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.setContentType("application/octet-stream");
            toClient = new BufferedOutputStream(response.getOutputStream());
            IOUtils.copy(fis, toClient);
        } catch (Exception e) {
            log.error("下载文件出错", e);
            return ServerResponse.createByErrorMessage("下载文件出错");
        } finally {
            try {
                assert fis != null;
                assert toClient != null;
                fis.close();
                toClient.flush();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.createBySuccessMessage("下载文件成功");
    }

    /**
     * @author 张靖烽
     * @description 上传文件
     * @createtime 2018-05-11 15:20
     */
    @RequestMapping(value = "upFile.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upFile(HttpServletRequest request, MultipartFile multipartFile, String className) {
        if (multipartFile == null) {
            return ServerResponse.createByErrorMessage("文件不能为空");
        }
        if (StringUtils.isBlank(className)) {
            return ServerResponse.createByErrorMessage("系统错误");
        }
        try {
            String path = request.getServletContext().getRealPath("") + "\\file\\upload";
            String fileName = multipartFile.getOriginalFilename();
            File file = new File(path, fileName);
            //MultipartFile自带的解析方法
            multipartFile.transferTo(file);
            Class<?> clazz = Class.forName(className);
            BaseModal bm = (BaseModal) clazz.newInstance();
            bm.setFile(file);
            return bm.parse();
        } catch (Exception e) {
            log.error("上传文件出错", e);
            return ServerResponse.createByErrorMessage("上传文件出错");
        }
    }
}
