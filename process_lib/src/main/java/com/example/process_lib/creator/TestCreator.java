package com.example.process_lib.creator;

import com.example.process_lib.base.BaseClassCreator;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;

public class TestCreator extends BaseClassCreator {

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
    public JavaFile createJavaFile() {
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

        return JavaFile.builder(mPackageName, classSpec).build();
    }
}
