package ru.clevertec.parser;


import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ParserI{
    private Map<String, Object> jsonMap = new LinkedHashMap<>();

    @Override
    public  String generateJsonDefault(Object obj) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object fieldValue = fields[i].get(obj);
            String fieldName = fields[i].getName();
            sb.append("\"").append(fieldName).append("\":");
            sb.append(convertToJson(fieldValue));
            if (i < fields.length - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public Map<String, Object> parseJson(String json) throws Exception {
        int startIndex = 1;
        int endIndex = json.length() - 1;
        String[] parts = json.substring(startIndex, endIndex).split(",");
        for (String part : parts) {
            String[] pair = part.split(":");
            String key = pair[0].replaceAll("\"", "").trim();
            String value = pair[1].trim();
            if (value.startsWith("{")) {
                Pattern p = Pattern.compile("^.+" + key+ "\":(.+)}");
                Matcher m = p.matcher(json);
                String value2 = m.matches() ? m.group(1) : null;
                assert value2 != null;
                parseJson(value2);
                break;
            } else if (value.startsWith("[") && value.endsWith("]")) {
            } else if (value.startsWith("\"") && value.endsWith("\"")) {
                if (jsonMap.containsKey(key)) {
                    Object oldValue = jsonMap.get(key);
                    if (oldValue instanceof List) {
                        ((List) oldValue).add(value.substring(1, value.length() - 1));
                    } else {
                        List<Object> newList = new ArrayList<>();
                        newList.add(oldValue);
                        newList.add(value.substring(1, value.length() - 1));
                        jsonMap.put(key, newList);
                    }
                } else {
                    jsonMap.put(key, value.substring(1, value.length() - 1));
                }
            } else if (value.equals("null")) {
                jsonMap.put(key, null);
            } else if (value.equals("true") || value.equals("false")) {
                jsonMap.put(key, Boolean.valueOf(value));
            } else if (value.contains(".")) {
                jsonMap.put(key, Double.valueOf(value));
            } else {
                jsonMap.put(key, Long.parseLong(value));
            }
        }
        return jsonMap;

    }


    private  String convertToJson(Object obj) throws Exception {
        if (obj == null) {
            return "null";
        } else if (obj instanceof String) {
            return "\"" + obj + "\"";
        } else if (obj instanceof Number) {
            return obj.toString();
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else {
            return generateJsonDefault(obj);
        }
    }

    @Override
    public  Object generateObjectFromJson(Class<?> clazz, String json) throws Exception {
        parseJson(json);
        Object obj = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = jsonMap.get(field.getName());
            if (value != null) {
                    field.set(obj, value);
            }else {
                Class<?> fieldClass = field.getType();
                Object fieldObj = fieldClass.newInstance();
                for (Field nestedField : fieldClass.getDeclaredFields()) {
                    nestedField.setAccessible(true);
                    Object nestedValue = jsonMap.get(nestedField.getName());
                    if (nestedValue != null) {
                        nestedField.set(fieldObj, nestedValue);
                    }
                    field.set(obj, fieldObj);
                }
            }
        }
        return obj;
    }
}
