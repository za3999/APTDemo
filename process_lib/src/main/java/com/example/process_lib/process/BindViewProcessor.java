package com.example.process_lib.process;

import com.example.process_lib.annotaion.BindView;
import com.example.process_lib.base.BaseAbstractProcessor;
import com.example.process_lib.creator.BaseClassCreator;
import com.example.process_lib.base.BindViewCreator;
import com.example.process_lib.base.CreatorFactory;
import com.google.auto.service.AutoService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.*;

@AutoService(Processor.class)
public class BindViewProcessor extends BaseAbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(BindView.class.getCanonicalName());
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<String, BindViewCreator> mProxyMap = new HashMap<>();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element : elements) {
            VariableElement variableElement = (VariableElement) element;
            TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
            String fullClassName = classElement.getQualifiedName().toString();
            BindView bindAnnotation = variableElement.getAnnotation(BindView.class);
            int id = bindAnnotation.value();
            BindViewCreator classCreator = mProxyMap.get(fullClassName);
            if (classCreator == null) {
                classCreator = CreatorFactory.getBindViewCreator(processingEnv, classElement);
                mProxyMap.put(fullClassName, classCreator);
            }
            classCreator.putElement(id, variableElement);
        }
        for (String key : mProxyMap.keySet()) {
            BaseClassCreator classCreator = mProxyMap.get(key);
            classCreator.create();
        }
        return true;
    }
}
