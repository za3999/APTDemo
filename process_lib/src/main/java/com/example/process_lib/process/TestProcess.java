package com.example.process_lib.process;

import com.example.process_lib.annotaion.TestAnnotation;
import com.example.process_lib.base.BaseAbstractProcessor;
import com.example.process_lib.base.CreatorFactory;
import com.google.auto.service.AutoService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.Set;

@AutoService(Processor.class)
public class TestProcess extends BaseAbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(TestAnnotation.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return CreatorFactory.getTestCreator(processingEnv).create();
    }

}
