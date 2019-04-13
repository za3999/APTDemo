package com.example.process_lib.creator;

import com.squareup.javapoet.JavaFile;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseClassCreator {

    protected ProcessingEnvironment mProcessingEnv;

    protected String mClassName;
    protected String mPackageName;

    public BaseClassCreator(ProcessingEnvironment processingEnv) {
        this.mProcessingEnv = processingEnv;
    }

    public abstract BaseClassCreator initClassMessage();

    public abstract JavaFile createJavaFile();

    public String getClassFullName() {
        return mPackageName + "." + mClassName;
    }

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
