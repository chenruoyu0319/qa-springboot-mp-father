package com.cry.qa.controller;

import com.cry.qa.http.HttpResult;
import com.cry.qa.response.Version;
import com.cry.qa.service.SysConfService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主控制器
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/main")
public class MainController {

    @Autowired
    SysConfService sysConfService;

    @ApiOperation(httpMethod = "GET", value = "index主页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "version";
    }

    @ApiOperation(httpMethod = "GET", value = "获取Version和Download")
    @ResponseBody
    @RequestMapping(value = "/getVersion", method = RequestMethod.GET)
    public HttpResult getVersion() {

        Version v = new Version();
        v.setVersion(sysConfService.getVersion());
        v.setDownload(sysConfService.getDownload());

        return HttpResult.ok(v, "获取成功！");
    }

    @ApiOperation(httpMethod = "GET", value = "下载次数 + 1")
    @ResponseBody
    @RequestMapping(value = "/addDownload", method = RequestMethod.GET)
    public HttpResult AddDownload() {

        try {
            sysConfService.addDownload();
            return HttpResult.ok("下载成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }

    }
}