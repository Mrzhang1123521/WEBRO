var ioc = {
        conf : {
            type : "org.nutz.ioc.impl.PropertiesProxy",
            fields : {
                paths : ["custom/"]
            }
        },
        dataSource : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                create : "init",
                depose : 'close'
            },
            fields : {
                url : {java:"$conf.get('db.url')"},
                username : {java:"$conf.get('db.username')"},
                password : {java:"$conf.get('db.password')"},
                testWhileIdle : true,
                validationQuery : {java:"$conf.get('db.validationQuery')"},
                maxActive : {java:"$conf.get('db.maxActive')"}
            }
        },
        dao : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"}]
        },
       /* dataSourceTongbu : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                create : "init",
                depose : 'close'
            },
            fields : {
                url : 'jdbc:sqlserver://127.0.0.1:1433;DatabaseName=att2000',
                username : 'sa',
                password : 'webro123',
                testWhileIdle : true,
                validationQuery : 'select 1',
                maxActive : '100'
            }
        },
        daoTongbu : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSourceTongbu"}]
        }*/
};