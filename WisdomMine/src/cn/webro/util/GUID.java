package cn.webro.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GUID {

	public String getguid()
	{
		String guid="";
		UUID uuid = UUID.randomUUID();
	      // 得到对象产生的ID
		guid = uuid.toString();
	      // 转换为大写
		guid = guid.toUpperCase();
	      // 替换 -
		guid = guid.replaceAll("-", "");
		return guid;
	}
	public static int  getClientId(){
			SimpleDateFormat format = new SimpleDateFormat("MMddhhmmss");
			String fstimes = format.format(new Date());
			int parseInt = Integer.parseInt(fstimes);
			System.out.println(parseInt);
	        return parseInt;

    }

}
