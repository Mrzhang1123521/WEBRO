package cn.webro;


import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.mvc.websocket.AbstractWsEndpoint;
import org.nutz.plugins.mvc.websocket.NutWsConfigurator;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket", configurator=NutWsConfigurator.class)
@IocBean // 使用NutWsConfigurator的必备条件
public class MyWebsocket extends AbstractWsEndpoint {
	
}
