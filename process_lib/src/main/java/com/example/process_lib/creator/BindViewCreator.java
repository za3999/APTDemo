package com.example.process_lib.creator;

import com.example.process_lib.base.BaseClassCreator;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class BindViewCreator extends BaseClassCreator {

    protected Map<Integer, VariableElement> mVariableElementMap = new HashMap<>();
    protected TypeElement mTypeElement;

    public BindViewCreator(ProcessingEnvironment mProcessingEnv, TypeElement typeElement) {
        super(mProcessingEnv);
        this.mTypeElement = typeElement;
    }

    @Override
    public BindViewCreator initClassMessage() {
        PackageElement packageElement = mProcessingEnv.getElementUtils().getPackageOf(mTypeElement);
        String packageName = packageElement.getQualifiedName().toString();
        String className = mTypeElement.getSimpleName().toString();
        mPackageName = packageName;
        mClassName = className + "_ViewBinding";
        return this;
    }

    public void putElement(int id, VariableElement element) {
        mVariableElementMap.put(id, element);
    }

    @Override
    public JavaFile createJavaFile() {
        MethodSpec.Builder bindMethod = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(ClassName.bestGuess(mTypeElement.getQualifiedName().toString()), "host");
        for (int id : mVariableElementMap.keySet()) {
            VariableElement element = mVariableElementMap.get(id);
            String name = element.getSimpleName().toString();
            ClassName clazz = ClassName.bestGuess(element.asType().toString());
            bindMethod.addStatement("$L.$L = ($T)host.findViewById( " + id + ")", "host", name, clazz);
        }

        TypeSpec classSpec = TypeSpec.classBuilder(mClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(bindMethod.build())
                .build();
        return JavaFile.builder(mPackageName, classSpec).build();
    }
}
