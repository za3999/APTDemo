package com.example.process_lib.creator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import java.io.IOException;

public class TestCreator extends BaseClassCreator{

    public TestCreator(ProcessingEnvironment mProcessingEnv) {
        super(mProcessingEnv);
    }

    @Override
    public TestCreator initClassMessage() {
        mPackageName = "com.example.za399.aptdemo";
        mClassName = "ToastUtil";
        return this;
    }

    @Override
    public boolean createJavaFile() {
        MethodSpec showToast = MethodSpec.methodBuilder("showToast")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(ClassName.bestGuess("android.content.Context"), "context")
                .addParameter(String.class, "string")
                .addStatement("$T.makeText(context,string,Toast.LENGTH_LONG).show()", ClassName.bestGuess("android.widget.Toast"))
                .build();

        TypeSpec classSpec = TypeSpec.classBuilder(mClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(showToast)
                .build();

        JavaFile javaFile = JavaFile.builder(mPackageName, classSpec)
                .build();
        try {
            javaFile.writeTo(mProcessingEnv.getFiler());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
