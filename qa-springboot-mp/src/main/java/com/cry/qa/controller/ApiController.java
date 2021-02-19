package com.cry.qa.controller;

import com.cry.qa.config.AppSettings;
import com.cry.qa.http.HttpResult;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016-12-18.
 */
@Api(value = "文件上传", protocols = "http", description = "文件上传接口")
@Controller
@CrossOrigin
@RequestMapping(value = "/api")
public class ApiController {

    /**
     * 文件上传路径
     */
    public static final String UPLOAD_FOLDER = "/upload_files/";

    @Autowired
    LoginService loginService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    AppSettings appSettings;

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传", httpMethod = "POST")
    public HttpResult upload(@RequestParam("file") MultipartFile file) throws IOException {

        // 文件上传前先要求用户登录
        // 获取缓存中的{token: userViewModel}
        UserViewModel userViewModel = (UserViewModel) loginService.getUserViewModel();
        logger.info(String.valueOf(userViewModel));
        if (userViewModel == null) {
            HttpResult.error("亲！登个录先~~");
        }
        // 服务器上的文件名
        String filename = format(file.getOriginalFilename());
        // linux不能用\\
        String path = System.getProperty("user.dir") + UPLOAD_FOLDER + filename;
        logger.info("upload path:" + path);
        File newFile = new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);

        String url = "http://" + request.getServerName() + ":" + appSettings.getPicfilesport();
        String data = url + UPLOAD_FOLDER + filename;
        return HttpResult.ok(data);
    }

    private String format(String oldFilename) {
        String[] str = oldFilename.split("\\.");

        return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + "." + str[1];
    }
}
