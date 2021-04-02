package cn.webro;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.lang.Tasks;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;



public class MainSetup implements Setup {

    @Override
    public void destroy(NutConfig arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(NutConfig conf) {
        // TODO Auto-generated method stub
        Ioc ioc = conf.getIoc();// 拿到Ioc容器
        Dao dao = ioc.get(Dao.class);// 通过Ioc容器可以拿到你想要的ioc bean


    }

}
