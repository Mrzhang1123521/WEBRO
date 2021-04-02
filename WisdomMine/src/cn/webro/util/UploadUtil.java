package cn.webro.util;

import javax.servlet.ServletContext;

public class UploadUtil {

	private ServletContext sc;        
    public String getPath(String path) {           
        return sc.getRealPath(path);       
    }   
}
