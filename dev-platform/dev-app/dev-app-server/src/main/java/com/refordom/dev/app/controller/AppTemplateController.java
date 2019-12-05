package com.refordom.dev.app.controller;

import com.refordom.common.core.util.R;
import com.refordom.dev.app.template.entity.AppTemplate;
import com.refordom.dev.app.template.service.AppTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/3 11:58
 */

@RestController
@RequestMapping("/template")
public class AppTemplateController {

    @Resource
    private AppTemplateService appTemplateService;

    @GetMapping
    public R<List<AppTemplate>> list() {
        return R.ok(appTemplateService.list());
    }
}
