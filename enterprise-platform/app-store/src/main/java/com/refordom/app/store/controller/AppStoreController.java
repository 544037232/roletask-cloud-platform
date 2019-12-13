package com.refordom.app.store.controller;

import com.refordom.common.core.util.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/13 17:07
 */
@RestController
@RequestMapping("/app/store")
public class AppStoreController {

    @GetMapping("/{action}")
    @PreAuthorize("@ps.hasPermission(#action)")
    public R list(@PathVariable("action") String action, HttpServletRequest request) {
        return R.ok(request.getAttribute("c"));
    }

}
