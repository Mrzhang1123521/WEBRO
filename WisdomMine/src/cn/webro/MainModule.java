package cn.webro;

import ioc.CheckUsers;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;


//@Filters(@By(type=CheckSession.class, args={"users", "/index.jsp"}))	

@Modules(scanPackage=true)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    "*anno", "cn.webro",
    "*tx"})
@SetupBy(value=MainSetup.class)
@Ok("json:full")
@Fail("jsp:500.jsp")
@Filters(@By(type = CheckUsers.class))
public class MainModule {

	
}
