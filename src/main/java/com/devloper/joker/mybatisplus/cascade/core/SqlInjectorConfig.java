package com.devloper.joker.mybatisplus.cascade.core;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import javax.annotation.Resource;
import java.util.List;

public class SqlInjectorConfig extends DefaultSqlInjector {

    @Resource
    private QuerySupportMethod querySupportMethod;

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(querySupportMethod);
        return methodList;
    }
}