package cn.webro.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DowloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String temp =request.getParameter("fname");//文件名
		String pat =request.getParameter("pat");//路径
		StringBuffer str =new StringBuffer(request.getServletContext().getRealPath(""));
		str.append("/img/");
		str.append(pat);
		
        try {
			// path是指欲下载的文件的路径。
			File file = new File(str.toString());
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response
					.getOutputStream());
			
			response.setContentType("application.x-msdownload");
			response.setCharacterEncoding("GB2312");
			//解决中文名不显示
			response.setHeader("Content-Disposition", "attachment;filename="+
					new String(temp.getBytes("GB2312"),"ISO-8859-1"));
			
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
        
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
