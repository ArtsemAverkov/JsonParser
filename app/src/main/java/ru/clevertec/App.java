/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.clevertec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.clevertec.entity.MetaInfProduct;
import ru.clevertec.entity.Product;
import ru.clevertec.parser.Parser;
import ru.clevertec.parser.ParserI;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws
            InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, ClassNotFoundException, JsonProcessingException {
        ParserI parserI = new Parser();
        ObjectMapper mapper = new ObjectMapper();

        Product product1= new Product(1L, "Name", 12.1, 1, 23.2, new MetaInfProduct(1L, true));
        Product product = new Product();
//        String s = mapper.writeValueAsString(product1);
//        System.out.println("s = " + s);

        String s = parserI.generateJsonDefault(product1);
        System.out.println("s = " + s);
//        Object o1 = parserI.generateObjectFromJson(Product.class, s);
//        System.out.println("o1 = " + o1);

        String stringBuilder = parserI.generateJsonDefault(product);
        String stringBuilders = parserI.generateJsonDefault(product1);
        System.out.println("jsonDefaultConstructor = " + stringBuilder);
        System.out.println("json = " + stringBuilders);

       // Object o = parserI.generateObjectFromJson(Product.class, stringBuilders);
//        System.out.println("productOutput = " + o);


    }
}
//{"id":1,"name":"Name","price":12.1,"amount":1,"sum":23.2,"metaInfProduct":{"id":1,"discount":true}}
//json = {"id":1,"name":Name,"price":12.1,"amount":1,"sum":23.2,"metaInfProduct":MetaInfProduct{id=1, isDiscount=true}}