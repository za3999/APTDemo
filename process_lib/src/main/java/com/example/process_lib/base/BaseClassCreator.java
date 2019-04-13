package com.example.process_lib.base;

import com.squareup.javapoet.JavaFile;

import javax.annotation.processing.ProcessingEnvironment;
import java.io.IOException;

public abstract class BaseClassCreator {

    protected ProcessingEnvironment mProcessingEnv;

    protected String mClassName;
    protected String mPackageName;

    public BaseClassCreator(ProcessingEnvironment processingEnv) {
        this.mProcessingEnv = processingEnv;
    }

    public abstract BaseClassCreator initClassMessage();

    public abstract JavaFile createJavaFile();

    public final boolean create() {
        try {
            createJavaFile().writeTo(mProcessingEnv.getFiler());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
