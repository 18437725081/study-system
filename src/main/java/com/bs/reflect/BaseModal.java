package com.bs.reflect;

import com.bs.common.ServerResponse;

import java.io.File;

/**
 * 项目名称： qx
 * 类名称： BaseModal
 * 描述：
 * @author 张靖烽
 * 创建时间 2018-05-11 16:04
 * 修改人：张靖烽  修改日期： 2018-05-11
 * 修改备注：
 **/
public abstract class BaseModal {

    private File file;

    public abstract ServerResponse parse() throws Exception;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    protected boolean excelVersion(String name){
        return name.matches("^.+\\.(?i)(xls)$");
    }
}
