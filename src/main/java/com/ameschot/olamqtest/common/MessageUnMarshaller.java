package com.ameschot.olamqtest.common;

import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MessageUnMarshaller<T>{

    private Unmarshaller unmarshaller;

    @SneakyThrows
    public MessageUnMarshaller(Class<? extends T> clazz){
        JAXBContext context = JAXBContext.newInstance(clazz);
        unmarshaller = context.createUnmarshaller();
    }

    @SneakyThrows
    public T unmarshall(String in){
        StringWriter sw = new StringWriter();
        return (T) unmarshaller.unmarshal(new StringReader(in));
    }

}
