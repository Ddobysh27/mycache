package by.dobysh.mycache;

import org.apache.taglibs.standard.lang.jstl.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//        private static Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);
//
//        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//        @ExceptionHandler(Exception.class)
//        public void notFoundHandler() {
//            log.debug("Item not found. HTTP 500 returned.");
//        }
//
//    }