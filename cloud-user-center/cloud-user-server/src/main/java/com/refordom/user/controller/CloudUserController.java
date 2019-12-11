package com.refordom.user.controller;

import com.refordom.common.core.util.R;
import com.refordom.user.entity.CloudUser;
import com.refordom.user.service.CloudUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2019/12/7 11:20
 */
@RequestMapping("/user")
@RestController
public class CloudUserController {

    @Resource
    private CloudUserService cloudUserService;

    @PostMapping
    public R add(CloudUser cloudUser){
        cloudUserService.save(cloudUser);
        return R.ok();
    }

    @PutMapping
    public R update(CloudUser cloudUser){
        cloudUserService.updateById(cloudUser);
        return R.ok();
    }
}
