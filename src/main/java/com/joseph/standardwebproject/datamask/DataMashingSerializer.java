package com.joseph.standardwebproject.datamask;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.function.BinaryOperator;

/**
 * 仿照Jackson的StringSerializer，编写DataMashingSerializer
 * @author joseph
 * @create 2023-11-07
 */
public class DataMashingSerializer extends StdSerializer<Object> {

    private final BinaryOperator<String> dataMaskingOperator;

    public DataMashingSerializer(){
        super(String.class,false);
        this.dataMaskingOperator = null;
    }

    public DataMashingSerializer(BinaryOperator<String> dataMaskingOperator) {
        super(String.class,false);
        this.dataMaskingOperator = dataMaskingOperator;
    }

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String afterDataMask;
        if(this.dataMaskingOperator == null){
            afterDataMask = DataMaskingFunc.ALL_MASK.operator().apply((String) o, null);
        }else{
            afterDataMask = this.dataMaskingOperator.apply((String) o, null);
        }
        jsonGenerator.writeString(afterDataMask);
    }
}
