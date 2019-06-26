package com.zz.ikeeping.resource.controller;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.util.FileUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.resource.util.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class ResourceController {

    @Autowired
    private OSSUtil ossUtil;

    //传统方式上传 保存到指定路径 返回可访问的url路径
    @PostMapping("/api/resource/fileupload.do")
    public R fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        if(!file.isEmpty()){

            File desFile=new File(
                    FileUtil.createDir(new File(request.getServletContext().getRealPath("/")).getParent(),
                            "ljbresource"),
                    FileUtil.renameFile(file.getOriginalFilename()));
            file.transferTo(desFile);
            System.out.println(desFile.getAbsolutePath());
            return R.setOK("OK", ProjectConfig.RESOURCEURL+desFile.getName());
        }else {
            return R.setERROR("请选择上传的文件");
        }
    }
    @PostMapping("/api/resource/fileoss.do")
    public R fileOSS(MultipartFile file, HttpServletRequest request) throws IOException {
        if (!file.isEmpty()) {
           String url= ossUtil.uploadByte(FileUtil.renameFile(file.getOriginalFilename()),file.getBytes());
            return R.setOK("OK",url);
        }else {
            return R.setERROR("请选择上传的文件");
        }
    }

}
