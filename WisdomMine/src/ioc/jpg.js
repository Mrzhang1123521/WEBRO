var UploadIoc = {  
    utils : {       
        type : 'cn.webro.util.UploadUtil',       
        fields : {           
            sc : {app:'$servlet'}   // �� ServletContext ����ע�� MyUtils  
        }  
    },  
    tmpFilePool : {       
        type : 'org.nutz.filepool.NutFilePool',// 临时文件最大个数为 1000 个
        args : [ {java:'$utils.getPath("/tmp")'}, 1000 ]      
    },  
    uploadPicFileContext : {   
        type : 'org.nutz.mvc.upload.UploadingContext',       
        singleton : false,       
        args : [ { refer : 'tmpFilePool' } ],  
        fields : {  
            ignoreNull : true,     // 是否忽略空文件, 默认为 false
            maxFileSize : 204800,         // 单个文件最大尺寸(大约的值，单位为字节，即 1048576 为 1M)  20971520:20M
         //   nameFilter : '^(.+[.])(gif|jpg|png)$'    // 正则表达式匹配可以支持的文件名
        }  
    },  
    jpg : {       
        type : 'org.nutz.mvc.upload.UploadAdaptor',       
        singleton : false,       
        args : [ { refer : 'uploadPicFileContext' } ]    
    }  
};  