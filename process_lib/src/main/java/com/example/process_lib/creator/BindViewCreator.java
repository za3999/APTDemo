package com.example.process_lib.creator;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
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
    public boolean createJavaFile() {
        try {
            JavaFileObject jfo = mProcessingEnv.getFiler().createSourceFile(getClassFullName(), mTypeElement);
            Writer writer = jfo.openWriter();
            writer.write(generateJavaCode());
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建Java代码
     *
     * @return
     */
    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(mPackageName).append(";\n\n");
        builder.append("import com.example.za399.aptdemo.*;\n");
        builder.append('\n');
        builder.append("public class ").append(mClassName);
        builder.append(" {\n");

        generateMethods(builder);
        builder.append('\n');
        builder.append("}\n");
        return builder.toString();
    }

    /**
     * 加入Method
     *
     * @param builder
     */
    private void generateMethods(StringBuilder builder) {
        builder.append("public void bind(" + mTypeElement.getQualifiedName() + " host ) {\n");
        for (int id : mVariableElementMap.keySet()) {
            VariableElement element = mVariableElementMap.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            builder.append("host." + name).append(" = ");
            builder.append("(" + type + ")(((android.app.Activity)host).findViewById( " + id + "));\n");
        }
        builder.append("  }\n");
    }
}
