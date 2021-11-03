package com.chendehe.config;

import feign.Target;

/**
 * @author cdh
 */
public class FeignTarget extends Target.HardCodedTarget<FeignTarget> {

    public FeignTarget(Class<FeignTarget> type, String name, String url) {
        super(type, name, url);
    }

}
