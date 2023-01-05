package com.example.demo.args;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 代码清单 14-9 一个早期版本代码，只支持Boolean 参数
 * @author lilei
 * @date 2022/4/12 12:32
 */
public class Args149 {
    private String schema;
    private String[] args;
    private boolean valid;
    private Set<Character> unexpectArguments = new TreeSet<>();
    private Map<Character, Boolean> booleanArgs = new HashMap<>();
    private int numberOfArguments = 0;

    public Args149(String schema, String[] args) {
        this.schema = schema;
        this.args = args;
        valid = parse();
    }

    public boolean getBoolean(char arg) {
        return booleanArgs.get(arg);//TODO 校验key是否存在，不存在：给提示（抛错太不友好；了）
    }

    public int cardinality() {
        return numberOfArguments;
    }

    public String usage() {
        if (schema.length() > 0)
            return "-[" + schema + "]";
        else
            return "";
    }

    public String errorMessage() {
        if (unexpectArguments.size() > 0)
            return unexpectArgumentsMessage();
        else
            return "";
    }

    public boolean isValid() {
        return valid;
    }

    private boolean parse() {
        if (schema.length() == 0 && args.length == 0) return true;
        parseSchema();
        parseArguments();
        return unexpectArguments.size() == 0;
    }

    private boolean parseSchema() {
        for (String element : schema.split(",")) {
            parseSchemaElement(element);
        }
        return true;
    }

    private void parseSchemaElement(String element) {
        if (element.length() == 1) {
            parseBooleanSchemaElement(element);
        }
    }


    private void parseBooleanSchemaElement(String element) {
        char c = element.charAt(0);
        if (Character.isLetter(c)) {
            booleanArgs.put(c, false);
        }
    }

    private boolean parseArguments() {
        for (String arg : args)
            parseArgument(arg);
        return true;
    }

    private void parseArgument(String arg) {
        if (arg.startsWith("-")) {
            parseElements(arg);
        }
    }

    private void parseElements(String arg) {
        for (int i = 1; i < arg.length(); i++) {
            parseElement(arg.charAt(i));
        }
    }

    private void parseElement(char argChar) {
        if (isBoolean(argChar)) {
            numberOfArguments++;
            setBooleanArg(argChar, true);
        } else
            unexpectArguments.add(argChar);
    }

    private void setBooleanArg(char argChar, boolean value) {
        booleanArgs.put(argChar, value);
    }

    private boolean isBoolean(char argChar) {
        return booleanArgs.containsKey(argChar);
    }

    private String unexpectArgumentsMessage() {
        StringBuffer message = new StringBuffer("Argument(s) -");
        for (Character c : unexpectArguments) {
            message.append(c);
        }
        message.append("  unexpected.");
        return message.toString();
    }

}
