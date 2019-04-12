package com.example.process_lib.process;

import javax.annotation.processing.AbstractProcessor;
import javax.lang.model.SourceVersion;

public abstract class BaseAbstractProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
