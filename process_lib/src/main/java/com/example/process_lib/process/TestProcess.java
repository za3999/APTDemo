package com.example.process_lib.process;

import com.example.process_lib.annotaion.TestAnnotation;
import com.google.auto.service.AutoService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
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
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import android.widget.Toast;\n");
        builder.append("import android.content.Context;\n");
        builder.append('\n');
        builder.append("public class ").append(className);
        builder.append(" {\n");
        generateMethods(builder);
        builder.append('\n');
        builder.append("}\n");
        createJavaFile(className + "." + className, builder.toString());
        return true;
    }

    private void generateMethods(StringBuilder builder) {
        builder.append("public static void showToast(Context context,String string) {\n");
        builder.append(" Toast.makeText(context,string,Toast.LENGTH_LONG).show();");
        builder.append("  }\n");
    }

    private void createJavaFile(String className, String javaCode) {
        try {
            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(className, null);
            Writer writer = jfo.openWriter();
            writer.write(javaCode);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
