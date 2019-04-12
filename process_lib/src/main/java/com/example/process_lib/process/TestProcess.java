package com.example.process_lib.process;

import com.example.process_lib.annotaion.TestAnnotation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@AutoService(Processor.class)
public class TestProcess extends BaseAbstractProcessor {
    private static final String packageName = "com.example.za399.aptdemo";
    private static final String className = "ToastUtil";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(TestAnnotation.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return createJavaFile();
    }

    private boolean createJavaFile() {
        MethodSpec showToast = MethodSpec.methodBuilder("showToast")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(ClassName.bestGuess("android.content.Context"), "context")
                .addParameter(String.class, "string")
                .addStatement("$T.makeText(context,string,Toast.LENGTH_LONG).show()", ClassName.bestGuess("android.widget.Toast"))
                .build();

        TypeSpec ToastUtil = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(showToast)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, ToastUtil)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
