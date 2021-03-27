package com.example.process_lib.base;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public abstract class BaseAbstractProcessor extends AbstractProcessor {

    protected Elements mElementUtils;
    protected Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        Map<String, String> optionMap = processingEnv.getOptions();
        Set<Map.Entry<String, String>> entrySet = optionMap.entrySet();
        mMessager.printMessage(Diagnostic.Kind.NOTE, "---------------BaseAbstractProcessor init--------------------------");
        for (Map.Entry<String, String> entry : entrySet) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, "BaseAbstractProcessor init:" + entry.getKey() + "-" + entry.getValue());
        }
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
