package com.joaovictor.sicronizacaoreceita.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;
    
    private final Locale BRAZIL = new Locale("pt","BR");

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, BRAZIL);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }
    
    public static String customMessage(String mensagem) {
    	return mensagem;
    }

}