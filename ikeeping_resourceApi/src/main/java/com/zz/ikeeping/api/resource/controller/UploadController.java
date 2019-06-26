package com.zz.ikeeping.api.resource.controller;

import com.zz.ikeeping.api.resource.util.KodoUtil;
import com.zz.ikeeping.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DJL
 * @create 2019-06-26 21:18
 * @desc 文件上传控制器
 **/
@RestController
@Api(value = "文件上传的API接口", tags = "文件上传至七牛云的数据接口")
public class UploadController {

    @Autowired(required = false)
    private KodoUtil KodoUtil;

    @ApiOperation("单个文件上传接口")
    @PostMapping("/api/resource/qiniuUpload.do")
    public R upload(MultipartFile file, HttpServletRequest request){
        if (!file.isEmpty()) {
            String fileUrl = KodoUtil.uploadMultipartFile(file);
            return R.setOK("OK", fileUrl);
        } else {
            return R.setERROR("请选择上传的文件");
        }
    }
}
