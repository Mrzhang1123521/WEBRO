package cn.webro.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 大华视频 获取
 */
@IocBean
public class DaHuaApi {


    //登录  返回token
    public static String tokenApi() {

        JSONObject data = new JSONObject();
        JSONObject data2 = new JSONObject();
        JSONObject data3 = new JSONObject();
        Md5 md5 = new Md5();
        String userName = "admin";
        String password = "rzttgs1234";
        String temp1 = "";
        String temp2 = "";
        String temp3 = "";
        String temp4 = "";
        String signature = "";
        // 登录用户名
        data.put("userName", "admin");
        data.put("ipAddress", "");

        data.put("clientType", "WINPC");

        // 创建请求header
        Header header = Header.create();
        header.set("Content-Type", "application/json");
        header.set("Content-Length", null);
        header.set("Host", null);
        //第一次鉴权 获取randomKey
        Response post1 = Http.post3("192.168.2.2:9000/admin/API/accounts/authorize", data, header, 50 * 1000);
        String content = post1.getContent();
        JSONObject success = JSONObject.parseObject(content);
        System.out.println("第一次鉴权返回结果:" + success);


        String realm = success.getString("realm");
        String randomKey = success.getString("randomKey");
        temp1 = md5.MD5s(password);
        temp2 = md5.MD5s(userName + temp1);
        temp3 = md5.MD5s(temp2);
        temp4 = md5.MD5s(userName + ":" + realm + ":" + temp3);
        signature = md5.MD5s(temp4 + ":" + randomKey);
        System.out.println("signature================" + signature);
        //第二次鉴权 获取randomKey
        data2.put("userName", userName);
        data2.put("randomKey", randomKey);
        data2.put("mac", "");
        data2.put("encryptType", "MD5");
        data2.put("ipAddress", "");
        data2.put("signature", signature);
        data2.put("clientType", "WINPC");
        System.out.println("data2" + data2);
        Header header2 = Header.create();
        header2.set("Content-Type", "application/json");
        header2.set("Content-Length", null);
        header2.set("Host", null);
        Response post2 = Http.post3("192.168.2.2:9000/admin/API/accounts/authorize", data2, header2, 50 * 1000);
        String content2 = post2.getContent();
        JSONObject success2 = JSONObject.parseObject(content2);
        System.out.println("第二次鉴权返回结果:" + success2);

        // /admin/API/accounts/updateToken更新token
        String token = success2.getString("token");
        signature = md5.MD5s(temp4 + ":" + token);
        data3.put("signature", signature);
        Header header3 = Header.create();
        header3.set("Content-Type", "application/json");
        header3.set("Content-Length", null);
        header3.set("Host", null);
        header3.set("X-Subject-Token", token);
        Response post3 = Http.post3("192.168.2.2:9000/admin/API/accounts/updateToken", data3, header3, 50 * 1000);
        String content3 = post3.getContent();
        JSONObject success3 = JSONObject.parseObject(content3);
        System.out.println("更新token:" + success3);
        return token;
    }


    //获取设备信息
    public static void devices() {
        String token = tokenApi();
        JSONObject data = new JSONObject();
        JSONArray deviceCodes = new JSONArray();
        JSONArray categories = new JSONArray();
        data.put("orgCode", "");
        data.put("deviceCodes", deviceCodes);
        data.put("categories", categories);
        Header header = Header.create();
        header.set("Content-Type", "application/json");
        header.set("Content-Length", null);
        header.set("Host", null);
        header.set("X-Subject-Token", token);
        System.out.println(data);
        Response post = Http.post3("111.35.4.81:8189/admin/API/tree/devices", data, header, 50 * 1000);
        String content = post.getContent();
        JSONObject success = JSONObject.parseObject(content);
        System.out.println("设备信息:" + success);
    }

    //获取hls拉流地址
    public static String getLiveUrl(String devicecode) {
        String token = tokenApi();
        JSONObject data = new JSONObject();
        JSONArray hlsBeanXoList = new JSONArray();
        JSONObject json = new JSONObject();
        // 设备编号
        json.put("devicecode", devicecode);
        // 单元序号，因为都是编码通道，填1即可
        json.put("unitSeq", "1");

        // 通道序号
        json.put("chnSeq", "0");

        // 录像来源 2-设备，3-中心
        json.put("recordSource", "3");


        // 录像类型 0-全部 1-普通录像 2-报警录像 当recordSource为3时，recordType的值要填0
        json.put("recordType", "");

        // 码流类型 1-主码流，2-辅码流
        json.put("streamType", "1");


        hlsBeanXoList.add(0, json);

        // 请求数组，每次请求可以获取多个hls拉流地址
        data.put("hlsBeanXoList", hlsBeanXoList);


        Header header = Header.create();
        header.set("Content-Type", "application/json");
        header.set("Content-Length", null);
        header.set("Host", null);
        header.set("X-Subject-Token", token);
        Response post = Http.post3("111.35.4.81:8189/admin/API/hls/getLiveUrl", data, header, 50 * 1000);
        String content = post.getContent();
        JSONObject success = JSONObject.parseObject(content);
        String url = success.getString("data");
        System.out.println("监控地址:" + url);
        return url + "?token=" + token;
    }

    //获取MQ配置信息
    public static void GetMqConfig() {
        String token = tokenApi();
        JSONObject data = new JSONObject();
        JSONObject json = new JSONObject();
        data.put("clientType", "LAPTOP-AVMK99GR");
        data.put("clientMac", "30-24-32-FA-2F-52");
        data.put("clientPushId", "");
        data.put("project", "PSDK");
        data.put("method", "BRM.Config.GetMqConfig");
        json.put("optional", "/admin/API/BRM/Config/GetMqConfig" + token);
        data.put("data", json);
        Header header = Header.create();
        header.set("Content-Type", "application/json");
        header.set("Content-Length", null);
        header.set("Host", null);
        header.set("X-Subject-Token", token);
        Response post = Http.post3("111.35.4.81:8189/admin/API/BRM/Config/GetMqConfig", data, header, 50 * 1000);
        String content = post.getContent();
        JSONObject success = JSONObject.parseObject(content);
        System.out.println("MQ配置信息:" + success);
    }

    //获取报警类型
    public static void GetAlarmTypes() {
        String token = tokenApi();
        JSONObject data = new JSONObject();
        JSONObject json = new JSONObject();
        data.put("clientType", "LAPTOP-AVMK99GR");
        data.put("clientMac", "30-24-32-FA-2F-52");
        data.put("clientPushId", "");
        data.put("project", "PSDK");
        data.put("method", "BRM.Alarm.GetAlarmTypes");
        json.put("optional", "/admin/API/BRM/Alarm/GetAlarmTypes" + token);
        json.put("locale", "zh_CN");
        data.put("data", json);
        Header header = Header.create();
        header.set("Content-Type", "application/json");
        header.set("Content-Length", null);
        header.set("Host", null);
        header.set("X-Subject-Token", token);
        Response post = Http.post3("111.35.4.81:8189/admin/API/BRM/Alarm/GetAlarmTypes", data, header, 50 * 1000);
        String content = post.getContent();
        JSONObject success = JSONObject.parseObject(content);
        System.out.println("获取报警类型:" + success);
    }

    /**
     * 历史报警信息
     */
    public static Object QueryAlarms(String startAlarmDate, String endAlarmDate) {

        long startAlarmDates = DateUtil.DateStrToTimeStamp(startAlarmDate, "yyyy-MM-dd HH:mm:ss");
        startAlarmDate = String.valueOf(startAlarmDates / 1000);
        long endAlarmDates = DateUtil.DateStrToTimeStamp(endAlarmDate, "yyyy-MM-dd HH:mm:ss");
        endAlarmDate = String.valueOf(endAlarmDates / 1000);
        System.out.println("时间" + startAlarmDate);
        String token = tokenApi();
        JSONObject data = new JSONObject();
        JSONObject json = new JSONObject();
        data.put("clientType", "LAPTOP-AVMK99GR");
        data.put("clientMac", "30-24-32-FA-2F-52");
        data.put("clientPushId", "");
        data.put("project", "PSDK");
        data.put("method", "BRM.Alarm.QueryAlarms");
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("orderType", "1");
        orderInfo.put("direction", "1");

        json.put("orderInfo", orderInfo);
        json.put("optional", "/admin/API/BRM/Alarm/QueryAlarms?token=" + token);
        JSONObject pageInfo = new JSONObject();
        pageInfo.put("pageSize", "10");
        pageInfo.put("pageNo", "1");
        json.put("pageInfo", pageInfo);
        JSONObject searchInfo = new JSONObject();
        JSONArray alarmStatus = new JSONArray();
        JSONArray handleStatus = new JSONArray();
        JSONArray alarmGrade = new JSONArray();
        JSONArray alarmType = new JSONArray();

        handleStatus.add("1");
        handleStatus.add("2");
        handleStatus.add("3");
        handleStatus.add("4");
        handleStatus.add("5");

        searchInfo.put("handleUser", "");
        searchInfo.put("deviceCode", "");
        searchInfo.put("endHandleDate", "");
        searchInfo.put("endAlarmDate", endAlarmDate);
        searchInfo.put("alarmId", "");
        searchInfo.put("alarmStatus", alarmStatus);
        searchInfo.put("handleStatus", handleStatus);
        searchInfo.put("alarmGrade", alarmGrade);
        searchInfo.put("alarmType", alarmType);
        searchInfo.put("alarmType", alarmType);
        searchInfo.put("startHandleDate", "");
        searchInfo.put("orgCode", "");
        searchInfo.put("channelId", "");
        searchInfo.put("startAlarmDate", startAlarmDate);
        searchInfo.put("alarmCode", "");

        json.put("searchInfo", searchInfo);
        data.put("data", json);
        Header header = Header.create();
        header.set("Content-Type", "application/json");
        header.set("Content-Length", null);
        header.set("Host", null);
        header.set("X-Subject-Token", token);
        System.out.println("data=" + data);
        Response post = Http.post3("111.35.4.81:8189/admin/API/BRM/Alarm/QueryAlarms", data, header, 50 * 1000);
        String content = post.getContent();
        JSONObject success = JSONObject.parseObject(content);
        JSONObject jsonObject = success.getJSONObject("data");
        JSONArray alarms = jsonObject.getJSONArray("alarms");
        JSONArray re = new JSONArray();
        for (int i = 0; i < alarms.size(); i++) {
            JSONObject obj = (JSONObject) alarms.get(i);
            JSONObject res = new JSONObject();
            //报警编码
            String alarmCode = obj.getString("alarmCode");
            alarmCode = alarmCode.substring(alarmCode.indexOf("{") + 1, alarmCode.lastIndexOf("}"));
            System.out.println("alarmCode:" + alarmCode);
            //设备编码
            String deviceCode = obj.getString("deviceCode");
            //设备名称
            String deviceName = obj.getString("deviceName");
            //通道编码
            String channelId = obj.getString("channelId");
            //通道名称
            String channelName = obj.getString("channelName");
            //报警状态，1:报警产生，2:报警消失
            String alarmStatuss = obj.getString("alarmStatus");
            if (alarmStatuss.equals("1")) {
                alarmStatuss = "报警产生";
            } else if (alarmStatuss.equals("2")) {
                alarmStatuss = "报警消失";
            } else {
                alarmStatuss = "未知";
            }
            //报警类型,详情见附录
            String alarmTypes = obj.getString("alarmType");
            //报警时间戳(单位:秒数)
            String alarmDate = obj.getString("alarmDate");
            alarmDate = DateUtil.timeStampToDateStr(alarmDate);
            //报警等级
            String alarmGrades = obj.getString("alarmGrade");
            //处理状态，1:处理中，2:已解决，3:误报，4;忽略，5:未解决
            String handleStatuss = obj.getString("handleStatus");
            if (handleStatuss.equals("1")) {
                handleStatuss = "处理中";
            } else if (handleStatuss.equals("2")) {
                handleStatuss = "已解决";
            } else if (handleStatuss.equals("3")) {
                handleStatuss = "误报";
            } else if (handleStatuss.equals("4")) {
                handleStatuss = "忽略";
            } else if (handleStatuss.equals("5")) {
                handleStatuss = "未解决";
            } else {
                handleStatuss = "未知";
            }
            //报警处理人(用户名)
            String handleUser = obj.getString("handleUser");
            //处理时间戳(单位:秒数)
            String handleDate = obj.getString("handleDate");
            handleDate = DateUtil.timeStampToDateStr(handleDate);
            //处理意见
            String handleMessage = obj.getString("handleMessage");
            //报警预案备注
            String memo = obj.getString("memo");
            res.put("alarmCode", alarmCode);
            res.put("deviceCode", deviceCode);
            res.put("deviceName", deviceName);
            res.put("channelId", channelId);
            res.put("channelName", channelName);
            res.put("alarmStatus", alarmStatuss);
            res.put("alarmType", alarmTypes);
            res.put("alarmDate", alarmDate);
            res.put("alarmGrade", alarmGrades);
            res.put("handleStatus", handleStatuss);
            res.put("handleUser", handleUser);
            res.put("handleDate", handleDate);
            res.put("handleMessage", handleMessage);
            res.put("memo", memo);
            re.add(res);
        }
        System.out.println("数量:" + alarms.size());
        System.out.println("返回历史报警信息:" + alarms);
        return re;
    }

    public static void main(String[] args) {
        //tokenApi();
    }
}
