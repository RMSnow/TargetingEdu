package org.sklse.targetedcourse.controller;

import com.qiniu.util.Auth;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController

@RequestMapping(value = "upload")
public class UploadController {

    String ACCESS_KEY = "R-VySGh3wLO40m5r3WEkaLNhUQM2xUp5yiYY2DmR";
    String SECRET_KEY = "ZPzvJiPq9gY9q8rmro2RQOQMyALlqoOTWohh7uzZ";
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    @ApiOperation(value = "获取上传token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bucket", value = "bucket空间名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "key", value = "一个随机字符串用于增加token安全等级（可以为null）", required = false, dataType = "String"),

    })
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String getCurrentChild(String bucket, String key) {
        if (bucket!=null){
            return auth.uploadToken(bucket, key, 3600, null, true);

        }else {
            return "未填写bucket";
        }

    }
}
