package com.qf.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUploadUtil {

    //配置upload对象的上传参数
    public ServletFileUpload uploadConfig(String uploadPath) {
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(5000000);
        // 设置临时存储目录  -- 所有文件上传操作统一流程，先生成临实文件，再将临实文件上传到目标地址
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(5000000);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(3000000);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        return upload;
    }
    //解析文本和非文本的请求数据
    public Map<String,String> formDetail(List<FileItem> formItems, String uploadPath) throws Exception {
        Map<String,String> map = new HashMap<String,String>();
        if (formItems != null && formItems.size() > 0) {
            // 迭代表单数据
            for (FileItem item : formItems) {
                //处理form中非文件内容
                if (item.isFormField()){
                    String name = item.getFieldName();
                    //表单内容如果是中文，需要ISO-8859-1编码解析成字节，再利用utf-8解析中文
                    String value = new String(item.getString().getBytes( "ISO-8859-1" ),"utf-8");
                    map.put(name, value);
                }else { // 处理不在表单中的字段(处理文件内容)
                    //获得文件原名称: abc.png
                    String fileName = new File(item.getName()).getName();
                    //生成文件新名称，放置上传多次图片，图片名一致，导致图片无法正确读取
                    int index = fileName.lastIndexOf(".");//获得文件中.的下标
                    String hzm = fileName.substring(index);//利用.下标获得文件后缀名
                    String fileNewName = UUID.randomUUID().toString();//生成文件新名称
                    fileNewName = fileNewName+hzm;
                    String filePath = uploadPath + File.separator + fileNewName;
                    map.put("simage", "images/"+fileNewName);
                    File storeFile = new File(filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);
                }
            }
        }
        return map;
    }
}
