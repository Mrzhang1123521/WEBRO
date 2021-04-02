package cn.webro.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.upload.TempFile;

import java.io.*;


public class UploadUtils {
	

	public static String uploadMulti(TempFile[] tempfiles) {
		
		JSONArray jsonArr = new JSONArray();
		
		for (int i = 0; i < tempfiles.length; i++) {
			TempFile tempFile = tempfiles[i]; 						  
			String oldName = tempFile.getSubmittedFileName();        
			String uuidName = (new GUID().getguid()) + oldName.substring(oldName.lastIndexOf("."));  
			String savePath = Mvcs.getServletContext().getRealPath("/img/"+uuidName); 			
			
			InputStream  input = null;
			OutputStream out   = null;
			try {
				input = tempFile.getInputStream(); 
				out = new FileOutputStream(new File(savePath));
				byte[] buffered = new byte[1024 * 1024 * 2];
				int length = 0; 
				while ((length = input.read(buffered)) != -1) {
					out.write(buffered, 0, length);
				}	
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("oldName" , oldName);
			jsonObj.put("uuidName", uuidName);
			
			jsonArr.add(jsonObj);
		}
		return jsonArr.toJSONString(); 
	}
	
	
	public static String uploadSingle(TempFile tempfile) {
		
		String oldName = tempfile.getSubmittedFileName();       
		String uuidName = (new GUID().getguid()) + oldName.substring(oldName.lastIndexOf(".")); 
		String savePath = Mvcs.getServletContext().getRealPath("/img/"+uuidName); 			
		
		InputStream  input = null;
		OutputStream out   = null;
		try {
			input = tempfile.getInputStream(); 
			out = new FileOutputStream(new File(savePath));
			byte[] buffered = new byte[1024 * 1024 * 2];
			int length = 0; 
			while ((length = input.read(buffered)) != -1) {
				out.write(buffered, 0, length);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}





























