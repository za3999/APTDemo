package com.example.process_lib.base;

import com.example.process_lib.base.BindViewCreator;
import com.example.process_lib.creator.TestCreator;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

public class CreatorFactory {

    public static BindViewCreator getBindViewCreator(ProcessingEnvironment processingEnv, TypeElement typeElement) {
        return new BindViewCreator(processingEnv, typeElement).initClassMessage();
    }

    public static TestCreator getTestCreator(ProcessingEnvironment processingEnv) {
        return new TestCreator(processingEnv).initClassMessage();
    }
}
