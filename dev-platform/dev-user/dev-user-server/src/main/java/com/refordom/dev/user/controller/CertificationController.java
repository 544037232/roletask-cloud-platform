package com.refordom.dev.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.refordom.common.core.util.R;
import com.refordom.dev.user.api.DevUserDetails;
import com.refordom.dev.user.entity.Certification;
import com.refordom.dev.user.service.CertificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/11/28 18:05
 */
@RestController
@RequestMapping("/dev/user")
public class CertificationController {

    @Resource
    private CertificationService certificationService;

    @PostMapping
    public R add(Certification certification) {
        certificationService.save(certification);
        return R.ok();
    }

    @GetMapping
    public R getByUserId(@AuthenticationPrincipal DevUserDetails devUserDetails){
        QueryWrapper<Certification> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("dev_user_id",devUserDetails.getId());
        return R.ok(certificationService.getOne(queryWrapper));
    }
}
