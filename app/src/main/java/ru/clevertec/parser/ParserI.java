package ru.clevertec.parser;


public interface ParserI {
    String generateJsonDefault(Object obj) throws Exception;
    Object generateObjectFromJson(Class<?> clas, String json) throws Exception;
}
