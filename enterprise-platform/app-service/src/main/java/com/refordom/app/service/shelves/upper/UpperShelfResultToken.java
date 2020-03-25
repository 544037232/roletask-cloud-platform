package com.refordom.app.service.shelves.upper;

import com.omc.builder.ResultToken;
import lombok.Data;

@Data
public class UpperShelfResultToken implements ResultToken {
    private String id;

    private String appId;
}
