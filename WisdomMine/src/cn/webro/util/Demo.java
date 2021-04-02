package cn.webro.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.nutz.http.Http;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 * 
 * @author admin
 *
 */
public class Demo {

	public static void main(String[] args) {
		getUrl();
	}
	public static void getUrl() {

		
		// 参数集合
		JSONArray hlsBeanXoList = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("deviceCode", "设备编码。。。");
		object.put("chnSeq", "通道序号。。");
		object.put("streamType", "码流类型。。");
		hlsBeanXoList.add(object);
		JSONObject object2 = new JSONObject();
		object2.put("hlsBeanXoList", hlsBeanXoList);
		
		Map<String,Object> map = new HashMap<>();
		map.put("hlsBeanXoList", hlsBeanXoList);
		
		String post = Http.post("http://127.0.0.1:8080/admin/rest/hls/getLiveUrl", map, 30*10);
		System.out.println(post);
	}

}
