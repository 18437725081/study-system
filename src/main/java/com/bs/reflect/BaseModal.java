package com.bs.reflect;

import com.bs.common.ServerResponse;

import java.io.File;

/**
 * @author 暗香
 */
public abstract class BaseModal {

    private File file;

    public abstract ServerResponse parse() throws Exception;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    protected boolean excelVersion(String name) {
        return name.matches("^.+\\.(?i)(xls)$");
    }
}
