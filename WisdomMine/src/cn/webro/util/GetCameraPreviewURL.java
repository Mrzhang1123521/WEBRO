package cn.webro.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GetCameraPreviewURL {
    public static NutMap GetCameraPreviewURL(String url, JSONObject jsonBody) {

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = "15.77.17.2:443"; // artemis网关服务器ip端口
        ArtemisConfig.appKey = "22274446";  // 秘钥appkey
        ArtemisConfig.appSecret = "KK4b6cIpC1FIgd4OY5WG";// 秘钥appSecret

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + url;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        String body = "";
        if (jsonBody != null) {
            body = jsonBody.toJSONString();
        }

        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);// post请求application/json类型参数
        return Json.fromJson(NutMap.class, result);
    }

    public static NutMap GetCameraPreviewUrlArray(String url, JSONArray jsonArray) {

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = "15.77.17.2:443"; // artemis网关服务器ip端口
        ArtemisConfig.appKey = "22274446";  // 秘钥appkey
        ArtemisConfig.appSecret = "KK4b6cIpC1FIgd4OY5WG";// 秘钥appSecret


        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + url;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */

        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */

        String body = jsonArray.toJSONString();
        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);// post请求application/json类型参数
        return Json.fromJson(NutMap.class, result);
    }

    //订阅事件
    public static JSONObject query() {
        String url = "/api/eventService/v1/eventSubscriptionByEventTypes";
        JSONObject jsonBody = new JSONObject();
        JSONArray even = new JSONArray();
        //198914 合法卡比对通过
        //196893 人脸认证通过
        //197162 人证比对通过
        even.add(197162);//事件id
        jsonBody.put("eventTypes", even);
        jsonBody.put("eventDest", "http://15.77.17.4:8080/Barracks/subion/renzheng");

        jsonBody.put("subType", 0);
        int[] eventLvl = new int[1];
        eventLvl[0] = 2;
        jsonBody.put("eventLvl", eventLvl);
        NutMap getCameraPreviewURL = GetCameraPreviewURL.GetCameraPreviewURL(url, jsonBody);
        System.out.println(getCameraPreviewURL);
        return jsonBody;
    }

    //查询2
    public static void queryTwo() {
        String url = "/api/eventService/v1/eventSubscriptionView";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", "1");
        jsonBody.put("pageSize", "30");
        jsonBody.put("startTime", "2020-01-07T12:00:00+08:00");
        jsonBody.put("endTime", "2020-03-07T12:00:00+08:00");

        NutMap map = GetCameraPreviewURL(url, jsonBody);
        System.out.println("result结果示例: " + map.toString());
    }


    public static void main(String[] args) {
        //query();
        //delete();
        //add();
        //peopleAdd();
        //aabb();
        //delpeople();
        //queryTwo();
        //aaa();
        //b9bb();
        //ccc();
        //bbb();
        shuakaa();
/*    	JSONArray personId = new JSONArray();
    	personId.add("F0BF113E5E8C4010956073865A1799B9");//人员唯一id
    	personId.add("54BB7285DD974BA5827A0EC6C1799B21");//人员唯一id
    	personId.add("04850957D8764E20A21904FEA28EDAB1");//人员唯一id
		shuaka(personId,"2020-03-23 06:00:00","2020-03-23 07:00:00");*/
    }

    public static void add() {
        String url = "/api/resource/v1/org/batch/add";
        JSONObject jsonBody = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonBody.put("clientId", getRandomNickname(10));
        jsonBody.put("orgName", "sdssdsad");
        jsonBody.put("parentIndexCode", "root000000");
        jsonArray.add(jsonBody);
        NutMap map = new GetCameraPreviewURL().GetCameraPreviewUrlArray(url, jsonArray);
        if (map.getString("code").equals("0")) {
            NutMap maps = Json.fromJson(NutMap.class, map.getString("data"));
            NutMap maps1 = Json.fromJson(NutMap.class, maps.getString("successes"));
            System.out.println(maps1.getString("orgIndexCode"));
        }
        System.out.println("result结果示例: " + map.toString());
    }

    public static void peopleAdd() {
        String url = "/api/resource/v1/person/batch/add";
        JSONObject jsonBody = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonBody.put("personId", new GUID().getguid());
        jsonBody.put("personName", "蒂萨");
        jsonBody.put("orgIndexCode", "root000000");
        jsonBody.put("certificateType", "111");//111:身份证414:护照113:户口簿335:驾驶证131:工作证133:学生证990:其他
        jsonBody.put("certificateNo", "370921199811061238");
        jsonBody.put("jobNo", "Abbbbba");
        jsonArray.add(jsonBody);
        NutMap map = new GetCameraPreviewURL().GetCameraPreviewUrlArray(url, jsonArray);
        NutMap maps = Json.fromJson(NutMap.class, map.getString("data"));
        NutMap masuccesses = Json.fromJson(NutMap.class, maps.getString("successes"));
        masuccesses.getString("personId");
    	/*if(map.getString("code").equals("0")){
    		System.out.println(map.getString("data"));
    	}*/
        System.out.println("result结果示例: " + map.toString());
    }

    public static void aabb() {
        JSONObject jsonFace = new JSONObject();
        jsonFace.put("personId", "F6B2F719027E4D9F8B587AF8D86F740D");
        //jsonFace.put("faceData", );
        String faceUrl = "/api/resource/v1/face/single/add";
        NutMap mapFace = GetCameraPreviewURL.GetCameraPreviewURL(faceUrl, jsonFace);
        System.out.println("mapFace  " + mapFace);
    }

    public static void delete() {
        String url = "/api/resource/v1/org/batch/add";
        JSONObject jsonBody = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonBody.put("indexCodes", "qyc8c894ch1y94c19y4c2");
        jsonArray.add(jsonBody);
        NutMap map = new GetCameraPreviewURL().GetCameraPreviewUrlArray(url, jsonArray);
        if (map.getString("code").equals("0")) {
            System.out.println("删除成功！");
        }
    }

    public static void delpeople() {
        String url = "/api/resource/v1/person/batch/delete";
        JSONObject jsonBody = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("E41A43E6984E4A5A913426C11F43D4BB");
        jsonBody.put("personIds", jsonArray);
        NutMap map = new GetCameraPreviewURL().GetCameraPreviewURL(url, jsonBody);
        if (map.getString("code").equals("0")) {
            System.out.println("删除成功！");
        }
    }

    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    /**
     * 请假刷卡下发权限
     *
     * @param personId
     * @param startTime
     * @param endTime
     */
    public static boolean shuakaxiafa(JSONArray personId, String startTime, String endTime) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            startTime = sdf2.format(sdf1.parse(startTime));
            endTime = sdf2.format(sdf1.parse(endTime));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //添加权限
        String url = "/api/acps/v1/auth_config/add";
        JSONObject jsonBody = new JSONObject();
        JSONArray personDatas = new JSONArray();
        JSONObject json1 = new JSONObject();
/*    	JSONArray personId = new JSONArray();
    	personId.add("c2504d5d50084d1dae23b13f5882d2bd");//人员唯一id
    	personId.add("3da4d58f31c24415a339ab4a67b1a02e");//人员唯一id
    	personId.add("6d61b70036144e5d94b3625630b71836");//人员唯一id
*/
        json1.put("indexCodes", personId);
        json1.put("personDataType", "person");//person  人员类型
        personDatas.add(json1);
        jsonBody.put("personDatas", personDatas);
        JSONArray resourceInfos = new JSONArray();

        JSONObject jsonA = new JSONObject();
        jsonA.put("resourceIndexCode", "5d8bf4f7b7934078ab0769797d93b5b4");//设备唯一id 西侧通道控制器_门1
        jsonA.put("resourceType", "door");
        JSONArray channelNos = new JSONArray();
        channelNos.add(1);//通道
        jsonA.put("channelNos", channelNos);

        JSONObject jsonB = new JSONObject();
        jsonB.put("resourceIndexCode", "5bd60a6a05ac4ce59abae0cbce6795f2");//设备唯一id 东侧通道控制器门1
        jsonB.put("resourceType", "door");
        JSONArray channelNos1 = new JSONArray();
        channelNos1.add(1);//通道
        jsonB.put("channelNos", channelNos1);

        JSONObject jsonC = new JSONObject();
        jsonC.put("resourceIndexCode", "282b7c0505374da2a5b657fd22bcac04");//设备唯一id 东侧人脸识别出门1
        jsonC.put("resourceType", "door");
        JSONArray channelNos2 = new JSONArray();
        channelNos2.add(1);//通道
        jsonC.put("channelNos", channelNos2);

        JSONObject jsonD = new JSONObject();
        jsonD.put("resourceIndexCode", "02959606d05a46c78d5c5b2d339eae37");//设备唯一id 西侧人脸识别出_门1
        jsonD.put("resourceType", "door");
        JSONArray channelNos3 = new JSONArray();
        channelNos3.add(1);//通道
        jsonD.put("channelNos", channelNos3);

        resourceInfos.add(jsonA);
        resourceInfos.add(jsonB);
        resourceInfos.add(jsonC);
        resourceInfos.add(jsonD);
        jsonBody.put("resourceInfos", resourceInfos);
        jsonBody.put("startTime", startTime);//开始时间
        jsonBody.put("endTime", endTime);//结束时间
        System.out.println(jsonBody);
        NutMap map = GetCameraPreviewURL(url, jsonBody);
        System.out.println("result结果示例: " + map.toString());
        if (map.getString("code").equals("0")) {
            //创建下载任务
            String createurl = "/api/acps/v1/download/configuration/task/add";
            JSONObject jsonCreate = new JSONObject();
            jsonCreate.put("taskType", 1);//1	卡片2	指纹3	卡片+指纹（组合）4	人脸5	卡片+人脸（组合）6	人脸+指纹（组合）7	卡片+指纹+人脸（组合）
            NutMap mapCreate = GetCameraPreviewURL(createurl, jsonCreate);
            System.out.println("createurl结果示例: " + mapCreate.toString());
            NutMap maps = Json.fromJson(NutMap.class, mapCreate.getString("data"));
            String taskId = maps.getString("taskId");
            System.out.println("taskId " + taskId);
            if (mapCreate.getInt("code") == 0) {
                //创建下载任务
                String daiUrl = "/api/acps/v1/download/configuration/data/add";
                JSONArray array1 = new JSONArray();
                array1.add(1);

                JSONObject o1 = new JSONObject();
                o1.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");//西侧通道控制器
                o1.put("resourceType", "acsDevice");
                o1.put("channelNos", array1);

                JSONObject o2 = new JSONObject();
                o2.put("resourceIndexCode", "48d7b435165443b9b1921fb503cf0383");//东侧通道控制器
                o2.put("resourceType", "acsDevice");
                o2.put("channelNos", array1);

                JSONObject o3 = new JSONObject();
                o3.put("resourceIndexCode", "acdce14452444162bca015ab9d50516d");//西侧人脸识别出
                o3.put("resourceType", "acsDevice");
                o3.put("channelNos", array1);

                JSONObject o4 = new JSONObject();
                o4.put("resourceIndexCode", "cf2427a7f900493d9b70387fe1ab3ea6");//东侧人脸识别出
                o4.put("resourceType", "acsDevice");
                o4.put("channelNos", array1);

                JSONArray array2 = new JSONArray();
                array2.add(o1);
                array2.add(o2);
                array2.add(o3);
                array2.add(o4);

                JSONObject jsonOo = new JSONObject();
                jsonOo.put("taskId", taskId);
                jsonOo.put("resourceInfos", array2);

                NutMap daiMap = GetCameraPreviewURL(daiUrl, jsonOo);

                System.out.println("daiMap结果示例: " + daiMap.toString());
                if (daiMap.getString("code").equals("0")) {
                    String taskurl = "/api/acps/v1/authDownload/task/start";
                    JSONObject jsonTask = new JSONObject();
                    jsonTask.put("taskId", maps.getString("taskId"));
                    NutMap mapTask = GetCameraPreviewURL(taskurl, jsonTask);
                    System.out.println("开始下载结果示例: " + mapTask.toString());
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            return false;
        }

    }


    public static void shuakaa() {

        //添加权限
        String url = "/api/acps/v1/auth_config/add";
        JSONObject jsonBody = new JSONObject();
        JSONArray personDatas = new JSONArray();
        JSONObject json1 = new JSONObject();
        JSONArray personId = new JSONArray();
/*    	personId.add("F0BF113E5E8C4010956073865A1799B9");//人员唯一id
    	personId.add("54BB7285DD974BA5827A0EC6C1799B21");//人员唯一id
    	personId.add("04850957D8764E20A21904FEA28EDAB1");//人员唯一id*/
        personId.add("53B90FF6397D4D3D81631945942E6261");
        json1.put("indexCodes", personId);
        json1.put("personDataType", "person");//person  人员类型
        personDatas.add(json1);
        jsonBody.put("personDatas", personDatas);
        JSONArray resourceInfos = new JSONArray();

        JSONObject jsonA = new JSONObject();
        jsonA.put("resourceIndexCode", "282b7c0505374da2a5b657fd22bcac04");//设备唯一id 东侧人脸识别出门1
        jsonA.put("resourceType", "door");
        JSONArray channelNos = new JSONArray();
        channelNos.add(1);//通道
        jsonA.put("channelNos", channelNos);
    	
/*    	JSONObject jsonB = new JSONObject();
    	jsonB.put("resourceIndexCode", "2ab5cba785d246d7946e3c6f09815713");//设备唯一id 东侧人脸识别进门1
    	jsonB.put("resourceType", "door");
    	JSONArray channelNos1 = new JSONArray();
    	channelNos1.add(1);//通道
    	jsonB.put("channelNos", channelNos1);*/

        JSONObject jsonC = new JSONObject();
        jsonC.put("resourceIndexCode", "02959606d05a46c78d5c5b2d339eae37");//设备唯一id 西侧人脸识别出_门1
        jsonC.put("resourceType", "door");
        JSONArray channelNos2 = new JSONArray();
        channelNos2.add(1);//通道
        jsonC.put("channelNos", channelNos2);
    	
/*    	JSONObject jsonD = new JSONObject();
    	jsonD.put("resourceIndexCode", "069b0c1ca1d441808456d2e525f8e6ff");//设备唯一id 西侧人脸识别进_门1   aaa
    	jsonD.put("resourceType", "door");
    	JSONArray channelNos3 = new JSONArray();
    	channelNos3.add(1);//通
    	jsonD.put("channelNos", channelNos3);*/
    	
/*    	JSONObject jsonE = new JSONObject();
    	jsonE.put("resourceIndexCode", "5d8bf4f7b7934078ab0769797d93b5b4");//设备唯一id 西侧通道控制器_门1
    	jsonE.put("resourceType", "door");
    	JSONArray channelNos4 = new JSONArray();
    	channelNos4.add(1);//通道
    	jsonE.put("channelNos", channelNos4);*/
    	
/*    	JSONObject jsonF = new JSONObject();
    	jsonF.put("resourceIndexCode", "c0c345a3887349a59f4c65535db8abad");//设备唯一id 西侧通道控制器门2
    	jsonF.put("resourceType", "door");
    	JSONArray channelNos5 = new JSONArray();
    	channelNos5.add(2);//通道
    	jsonF.put("channelNos", channelNos5);*/
    	
/*    	JSONObject jsonG = new JSONObject();
    	jsonG.put("resourceIndexCode", "5bd60a6a05ac4ce59abae0cbce6795f2");//设备唯一id 东侧通道控制器门1
    	jsonG.put("resourceType", "door");
    	JSONArray channelNos6 = new JSONArray();
    	channelNos6.add(1);//通道
    	jsonG.put("channelNos", channelNos6);*/
    	
/*    	JSONObject jsonH = new JSONObject();
    	jsonH.put("resourceIndexCode", "1ddfee8ef96f4fa0b8236ab6c244176a");//设备唯一id 东侧通道控制器门2
    	jsonH.put("resourceType", "door");
    	JSONArray channelNos7 = new JSONArray();
    	channelNos7.add(2);//通道
    	jsonH.put("channelNos", channelNos7);*/


        resourceInfos.add(jsonA);
        /*resourceInfos.add(jsonB);*/
        resourceInfos.add(jsonC);
        /*resourceInfos.add(jsonD);*/
        //resourceInfos.add(jsonE);
        /*resourceInfos.add(jsonF);*/
        //resourceInfos.add(jsonG);
        /*resourceInfos.add(jsonH);*/
        jsonBody.put("resourceInfos", resourceInfos);
        jsonBody.put("startTime", "2020-03-27T14:36:00+08:00");//开始时间
        jsonBody.put("endTime", "2020-03-27T14:45:00+08:00");//结束时间
        System.out.println(jsonBody);
        NutMap map = GetCameraPreviewURL(url, jsonBody);
        System.out.println("result结果示例: " + map.toString());
        if (map.getString("code").equals("0")) {
            //创建下载任务
            String createurl = "/api/acps/v1/download/configuration/task/add";
            JSONObject jsonCreate = new JSONObject();
            jsonCreate.put("taskType", 1);//1	卡片2	指纹3	卡片+指纹（组合）4	人脸5	卡片+人脸（组合）6	人脸+指纹（组合）7	卡片+指纹+人脸（组合）
            NutMap mapCreate = GetCameraPreviewURL(createurl, jsonCreate);
            System.out.println("createurl结果示例: " + mapCreate.toString());
            NutMap maps = Json.fromJson(NutMap.class, mapCreate.getString("data"));
            String taskId = maps.getString("taskId");
            System.out.println("taskId " + taskId);

            //创建下载任务
            String daiUrl = "/api/acps/v1/download/configuration/data/add";
            JSONArray array1 = new JSONArray();
            array1.add(1);
        	
/*        	JSONObject o1 = new JSONObject();
        	o1.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");//西侧通道控制器
        	o1.put("resourceType", "acsDevice");
        	o1.put("channelNos", array1);
        	
        	JSONObject o2 = new JSONObject();
        	o2.put("resourceIndexCode", "48d7b435165443b9b1921fb503cf0383");//东侧通道控制器
        	o2.put("resourceType", "acsDevice");
        	o2.put("channelNos", array1);*/

            JSONObject o3 = new JSONObject();
            o3.put("resourceIndexCode", "acdce14452444162bca015ab9d50516d");//西侧人脸识别出
            o3.put("resourceType", "acsDevice");
            o3.put("channelNos", array1);

            JSONObject o4 = new JSONObject();
            o4.put("resourceIndexCode", "cf2427a7f900493d9b70387fe1ab3ea6");//东侧人脸识别出
            o4.put("resourceType", "acsDevice");
            o4.put("channelNos", array1);

            JSONArray array2 = new JSONArray();
/*        	array2.add(o1);
        	array2.add(o2);*/
            array2.add(o3);
            array2.add(o4);

            JSONObject jsonOo = new JSONObject();
            jsonOo.put("taskId", taskId);
            jsonOo.put("resourceInfos", array2);

            NutMap daiMap = GetCameraPreviewURL(daiUrl, jsonOo);

            System.out.println("daiMap结果示例: " + daiMap.toString());

            String taskurl = "/api/acps/v1/authDownload/task/start";
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("taskId", maps.getString("taskId"));
            NutMap mapTask = GetCameraPreviewURL(taskurl, jsonTask);
            System.out.println("开始下载结果示例: " + mapTask.toString());
        }
    }


    public static void bbb() {
        //添加权限
        String url = "/api/acps/v1/auth_config/add";
        JSONObject jsonBody = new JSONObject();
        JSONArray personDatas = new JSONArray();
        JSONObject json1 = new JSONObject();
        JSONArray personId = new JSONArray();
        personId.add("F0BF113E5E8C4010956073865A1799B9");//人员唯一id
        personId.add("54BB7285DD974BA5827A0EC6C1799B21");//人员唯一id
        personId.add("04850957D8764E20A21904FEA28EDAB1");//人员唯一id
        json1.put("indexCodes", personId);
        json1.put("personDataType", "person");//person = 人员类型
        personDatas.add(json1);
        jsonBody.put("personDatas", personDatas);
        JSONArray resourceInfos = new JSONArray();

        JSONObject jsonA = new JSONObject();
        jsonA.put("resourceIndexCode", "282b7c0505374da2a5b657fd22bcac04");//设备唯一id 东侧人脸识别出门1
        jsonA.put("resourceType", "door");
        JSONArray channelNos = new JSONArray();
        channelNos.add(1);//通道
        jsonA.put("channelNos", channelNos);

        JSONObject jsonB = new JSONObject();
        jsonB.put("resourceIndexCode", "2ab5cba785d246d7946e3c6f09815713");//设备唯一id 东侧人脸识别进门1
        jsonB.put("resourceType", "door");
        JSONArray channelNos1 = new JSONArray();
        channelNos1.add(1);//通道
        jsonB.put("channelNos", channelNos1);

        JSONObject jsonC = new JSONObject();
        jsonC.put("resourceIndexCode", "02959606d05a46c78d5c5b2d339eae37");//设备唯一id 西侧人脸识别出_门1
        jsonC.put("resourceType", "door");
        JSONArray channelNos2 = new JSONArray();
        channelNos2.add(1);//通道
        jsonC.put("channelNos", channelNos2);

        JSONObject jsonD = new JSONObject();
        jsonD.put("resourceIndexCode", "b7778ff0d04b44e9a72293019da5b0fe");//设备唯一id 西侧人脸识别进_门1
        jsonD.put("resourceType", "door");
        JSONArray channelNos3 = new JSONArray();
        channelNos3.add(1);//通道
        jsonD.put("channelNos", channelNos3);

        JSONObject jsonE = new JSONObject();
        jsonE.put("resourceIndexCode", "5d8bf4f7b7934078ab0769797d93b5b4");//设备唯一id 西侧通道控制器_门1
        jsonE.put("resourceType", "door");
        JSONArray channelNos4 = new JSONArray();
        channelNos4.add(1);//通道
        jsonE.put("channelNos", channelNos4);

        JSONObject jsonF = new JSONObject();
        jsonF.put("resourceIndexCode", "c0c345a3887349a59f4c65535db8abad");//设备唯一id 西侧通道控制器门2
        jsonF.put("resourceType", "door");
        JSONArray channelNos5 = new JSONArray();
        channelNos5.add(2);//通道
        jsonF.put("channelNos", channelNos5);

        JSONObject jsonG = new JSONObject();
        jsonG.put("resourceIndexCode", "5bd60a6a05ac4ce59abae0cbce6795f2");//设备唯一id 东侧通道控制器门1
        jsonG.put("resourceType", "door");
        JSONArray channelNos6 = new JSONArray();
        channelNos6.add(1);//通道
        jsonG.put("channelNos", channelNos6);

        JSONObject jsonH = new JSONObject();
        jsonH.put("resourceIndexCode", "1ddfee8ef96f4fa0b8236ab6c244176a");//设备唯一id 东侧通道控制器门2
        jsonH.put("resourceType", "door");
        JSONArray channelNos7 = new JSONArray();
        channelNos7.add(2);//通道
        jsonH.put("channelNos", channelNos7);


        resourceInfos.add(jsonA);
        resourceInfos.add(jsonB);
        resourceInfos.add(jsonC);
        resourceInfos.add(jsonD);
        resourceInfos.add(jsonE);
        resourceInfos.add(jsonF);
        resourceInfos.add(jsonG);
        resourceInfos.add(jsonH);
        jsonBody.put("resourceInfos", resourceInfos);
        jsonBody.put("startTime", "2020-03-21T08:00:08+08:00");//开始时间
        jsonBody.put("endTime", "2020-03-21T14:00:08+08:00");//结束时间
        System.out.println(jsonBody);
        NutMap map = GetCameraPreviewURL(url, jsonBody);
        System.out.println("result结果示例: " + map.toString());

        //创建下载任务
        String createurl = "/api/acps/v1/download/configuration/task/add";
        JSONObject jsonCreate = new JSONObject();
        jsonCreate.put("taskType", 1);//1	卡片2	指纹3	卡片+指纹（组合）4	人脸5	卡片+人脸（组合）6	人脸+指纹（组合）7	卡片+指纹+人脸（组合）
        NutMap mapCreate = GetCameraPreviewURL(createurl, jsonCreate);
        System.out.println("createurl结果示例: " + mapCreate.toString());
        NutMap maps = Json.fromJson(NutMap.class, mapCreate.getString("data"));
        String taskId = maps.getString("taskId");
        System.out.println("taskId " + taskId);

        //创建下载任务
        String daiUrl = "/api/acps/v1/download/configuration/data/add";
        JSONArray array1 = new JSONArray();
        array1.add(1);

        JSONObject o1 = new JSONObject();
        o1.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");
        o1.put("resourceType", "acsDevice");
        o1.put("channelNos", array1);

        JSONObject o2 = new JSONObject();
        o2.put("resourceIndexCode", "48d7b435165443b9b1921fb503cf0383");
        o2.put("resourceType", "acsDevice");
        o2.put("channelNos", array1);

        JSONObject o3 = new JSONObject();
        o3.put("resourceIndexCode", "aaad205c4c2d425eb2c13d9d6e6ee675");
        o3.put("resourceType", "acsDevice");
        o3.put("channelNos", array1);

        JSONObject o4 = new JSONObject();
        o4.put("resourceIndexCode", "acdce14452444162bca015ab9d50516d");
        o4.put("resourceType", "acsDevice");
        o4.put("channelNos", array1);

        JSONObject o5 = new JSONObject();
        o5.put("resourceIndexCode", "077eb1f3b6404165b5674b5e35a1065f");
        o5.put("resourceType", "acsDevice");
        o5.put("channelNos", array1);

        JSONObject o6 = new JSONObject();
        o6.put("resourceIndexCode", "cf2427a7f900493d9b70387fe1ab3ea6");
        o6.put("resourceType", "acsDevice");
        o6.put("channelNos", array1);

        JSONArray array2 = new JSONArray();
        array2.add(o1);
        array2.add(o2);
        array2.add(o3);
        array2.add(o4);
        array2.add(o5);
        array2.add(o6);

        JSONObject jsonOo = new JSONObject();
        jsonOo.put("taskId", taskId);
        jsonOo.put("resourceInfos", array2);

        NutMap daiMap = GetCameraPreviewURL(daiUrl, jsonOo);

        System.out.println("daiMap结果示例: " + daiMap.toString());

        String taskurl = "/api/acps/v1/authDownload/task/start";
        JSONObject jsonTask = new JSONObject();
        jsonTask.put("taskId", maps.getString("taskId"));
        NutMap mapTask = GetCameraPreviewURL(taskurl, jsonTask);
        System.out.println("开始下载结果示例: " + mapTask.toString());
    }

    public static void ccc() {
        String url = "/api/acps/v1/authDownload/task/addition";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("taskType", 4);
        NutMap map = GetCameraPreviewURL(url, jsonBody);
        System.out.println("aaaa " + map);
        if (map.getString("code").equals("0")) {//1
            String shujuUrl = "/api/acps/v1/authDownload/data/addition";

            JSONObject parse = JSONObject.parseObject(map.getString("data"));
            String taskId = parse.getString("taskId");

            JSONArray array1 = new JSONArray();
            array1.add(1);

            JSONObject o1 = new JSONObject();
            o1.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");
            o1.put("resourceType", "acsDevice");
            o1.put("channelNos", array1);

            JSONObject o2 = new JSONObject();
            o2.put("resourceIndexCode", "48d7b435165443b9b1921fb503cf0383");
            o2.put("resourceType", "acsDevice");
            o2.put("channelNos", array1);

            JSONObject o3 = new JSONObject();
            o3.put("resourceIndexCode", "aaad205c4c2d425eb2c13d9d6e6ee675");
            o3.put("resourceType", "acsDevice");
            o3.put("channelNos", array1);

            JSONObject o4 = new JSONObject();
            o4.put("resourceIndexCode", "acdce14452444162bca015ab9d50516d");
            o4.put("resourceType", "acsDevice");
            o4.put("channelNos", array1);

            JSONObject o5 = new JSONObject();
            o5.put("resourceIndexCode", "077eb1f3b6404165b5674b5e35a1065f");
            o5.put("resourceType", "acsDevice");
            o5.put("channelNos", array1);

            JSONObject o6 = new JSONObject();
            o6.put("resourceIndexCode", "cf2427a7f900493d9b70387fe1ab3ea6");
            o6.put("resourceType", "acsDevice");
            o6.put("channelNos", array1);

            JSONArray array2 = new JSONArray();
            array2.add(o1);
            array2.add(o2);
            array2.add(o3);
            array2.add(o4);
            array2.add(o5);
            array2.add(o6);


            JSONObject peoson = new JSONObject();
            peoson.put("personId", "F0BF113E5E8C4010956073865A1799B9");
            peoson.put("operatorType", 0);
            peoson.put("startTime", "2020-03-20T08:00:08+08:00");
            peoson.put("endTime", "2020-03-21T23:59:08+08:00");
            peoson.put("personType", 1);

            JSONObject peoson1 = new JSONObject();
            peoson1.put("personId", "54BB7285DD974BA5827A0EC6C1799B21");
            peoson1.put("operatorType", 0);
            peoson1.put("startTime", "2020-03-20T08:00:08+08:00");
            peoson1.put("endTime", "2020-03-21T23:59:08+08:00");
            peoson1.put("personType", 1);

            JSONObject peoson2 = new JSONObject();
            peoson2.put("personId", "04850957D8764E20A21904FEA28EDAB1");
            peoson2.put("operatorType", 0);
            peoson2.put("startTime", "2020-03-20T08:00:08+08:00");
            peoson2.put("endTime", "2020-03-21T23:59:08+08:00");
            peoson2.put("personType", 1);

            JSONArray peosonArray = new JSONArray();
            peosonArray.add(peoson);
            peosonArray.add(peoson1);
            peosonArray.add(peoson2);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("taskId", taskId);
            jsonObject.put("resourceInfos", array2);
            jsonObject.put("personInfos", peosonArray);

            NutMap maps = GetCameraPreviewURL(shujuUrl, jsonObject);

            System.out.println("maps: " + maps);
            String taskurl = "/api/acps/v1/authDownload/task/start";
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("taskId", taskId);
            NutMap mapTask = GetCameraPreviewURL(taskurl, jsonTask);
            System.out.println("开始下载结果示例: " + mapTask.toString());
        }//1

    }

    public static void kuaijie() {
        //根据出入权限配置快捷下载
/*    	String kuaijieurl = "/api/acps/v1/authDownload/configuration/shortcut";
    	JSONArray a1 = new JSONArray();
    	a1.add(1);

    	JSONObject b1 = new JSONObject();
    	b1.put("channelNos", a1);
    	b1.put("resourceType", "acsDevice");
    	b1.put("resourceIndexCode", "acdce14452444162bca015ab9d50516d");//资源的唯一标识，32位数字+字母（小写）字符串；资源为设备
    	
    	JSONArray c1 = new JSONArray();
    	c1.add(b1);
    	
    	JSONObject d1 = new JSONObject();
    	d1.put("taskType", 1);
    	d1.put("resourceInfos", c1);
    	
    	NutMap mapKuaijie = GetCameraPreviewURL(kuaijieurl,d1);
    	System.out.println("快捷 " + mapKuaijie);
    	NutMap maps = Json.fromJson(NutMap.class, mapKuaijie.getString("data"));*/
    }

    /**
     * 批量添加车 object[]
     *
     * @param clientId     True  调用方指定Id
     * @param plateNo      True   车牌号码
     * @param personId     True  人员ID
     * @param plateType    车牌类型
     * @param plateColor   车牌颜色
     * @param vehicleType  车辆类型
     * @param vehicleColor 车辆颜色
     * @param description  车辆描述
     */
    public static JSONObject addcar(long clientId, String plateNo, String personId,
                                    String plateType, String plateColor, String vehicleType, String vehicleColor,
                                    String description) {

        JSONObject jsonBody = new JSONObject();
        //调用方指定id int
        jsonBody.put("clientId", clientId);
        //车牌号码 string
        jsonBody.put("plateNo", plateNo);
        //人员ID
        jsonBody.put("personId", personId);
        //车牌类型 string
        jsonBody.put("plateType", plateType);
        //车辆颜色 string
        jsonBody.put("plateColor", plateColor);
        //车辆类型 string
        jsonBody.put("vehicleType", vehicleType);
        //车辆颜色 string
        jsonBody.put("vehicleColor", vehicleColor);
        //车辆描述description
        jsonBody.put("description", description);
        return jsonBody;
    }

    public static void tongbuxiazai() {
        String url = "/api/acps/v1/authDownload/task/simpleDownload";
        JSONArray channelNos = new JSONArray();
        channelNos.add(1);//通道

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelNos", channelNos);
        jsonObject.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");
        jsonObject.put("resourceType", "acsDevice");
    	
/*    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("channelNos", channelNos);
    	jsonObject.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");
    	jsonObject.put("resourceType", "acsDevice");
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("channelNos", channelNos);
    	jsonObject.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");
    	jsonObject.put("resourceType", "acsDevice");
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("channelNos", channelNos);
    	jsonObject.put("resourceIndexCode", "96714ba76b79408a8f5b7c7ce4a95d3e");
    	jsonObject.put("resourceType", "acsDevice");*/


    }

}
