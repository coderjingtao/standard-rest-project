package com.joseph.standardwebproject.datamask;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

/**
 * 自定义AnnotationIntrospector，来适配自定义的DataMashingSerializer
 * 如果有自定义的@DataMasking注解，则使用DataMashingSerializer
 * @author joseph
 * @create 2023-11-07
 */
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {
    @Override
    public Object findSerializer(Annotated am) {
        DataMasking annotation = am.getAnnotation(DataMasking.class);
        if(annotation != null){
            return new DataMashingSerializer(annotation.maskFunc().operator());
        }
        return null;
    }
}
