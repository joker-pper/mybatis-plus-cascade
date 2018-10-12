package com.devloper.joker.mybatispluscascade.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class InjectorConfig  extends DefaultSqlInjector {

    @Resource
    private QuerySupportMethod querySupportMethod;

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(querySupportMethod);
        return methodList;
    }
}