package com.njust.controller.statistics;

import com.njust.vo.AjaxResult;
import com.njust.domain.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("statistics/server")
public class ServerController
{
    @GetMapping("index")
    public AjaxResult getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
