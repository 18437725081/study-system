package com.bs.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author 张靖烽
 * @name FtpUtil
 * @description
 * @create 2017-11-02 14:14
 **/
public class FtpUtil {
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    private FtpUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static boolean upFileToFtp(List<File> fileList) throws IOException {
        boolean flag;
        FtpUtil ftp = new FtpUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器...");
        flag = ftp.uploadFile(fileList);
        logger.info("开始连接ftp服务器,结束上传,上传结果:{}");
        return flag;
    }

    private boolean uploadFile(List<File> fileList) throws IOException {
        boolean flag = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.changeWorkingDirectory("img");
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setBufferSize(1024);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }

            } catch (IOException e) {
                logger.error("上传文件异常!", e);
                flag = false;
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
                ftpClient.disconnect();
            }
        }
        return flag;
    }

    private boolean connectServer(String ip, int port, String user, String pwd) {
        boolean flag = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            flag = ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常", e);
        }
        return flag;
    }


    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
