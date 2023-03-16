package com.ameschot.olamqtest.common;

import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class MessageMarshaller<T>{

    private Marshaller marshaller;

    @SneakyThrows
    public MessageMarshaller(Class<? extends T> clazz){
        JAXBContext context = JAXBContext.newInstance(clazz);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @SneakyThrows
    public String marshall(T in){
        StringWriter sw = new StringWriter();
        marshaller.marshal(in, sw);
        return sw.toString();
    }

}
