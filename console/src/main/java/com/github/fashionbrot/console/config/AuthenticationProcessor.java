package com.github.fashionbrot.console.config;


import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.processor.AbstractStandardConditionalVisibilityTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

@Component
public class AuthenticationProcessor extends AbstractStandardConditionalVisibilityTagProcessor {



    public AuthenticationProcessor() {
        super(TemplateMode.HTML, "hzzy", "hasRole", 300);
    }

    protected boolean isVisible(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue) {

        return true;//roleInfoService.checkRole(attributeValue);
    }

    /**
     * html头 添加
     * xmlns:hzzy="http://www.thymeleaf.org"
     *
     * html 标签使用  hzzy:hasRole="role:add"
     */
}