package com.example.demo.args;

import java.text.ParseException;
import java.util.*;

/**
 * 代码清单 14-9 一个早期版本代码，只支持Boolean 参数
 * @author lilei
 * @date 2022/4/12 12:32
 */
public class Args1410 {
    private String schema;
    private String[] args;
    private boolean valid = true;
    private Set<Character> unexpectArguments = new TreeSet<>();
    private Map<Character, Boolean> booleanArgs = new HashMap<>();
    private Map<Character, String> stringArgs = new HashMap<>();
    private Set<Character> argsFound = new HashSet<>();
    private int currentArgument;
    private char errorArgument = '\0';

    enum ErrorCode {
        OK, MISSING_STRING
    }

    private ErrorCode errorCode = ErrorCode.OK;

    private int numberOfArguments = 0;


    public Args1410(String schema, String[] args) throws ParseException {
        this.schema = schema;
        this.args = args;
        valid = parse();
    }

    private boolean parse() throws ParseException {
        if (schema.length() == 0 && args.length == 0) return true;
        parseSchema();
        parseArguments();
        return valid;
    }

    private boolean parseSchema() throws ParseException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                String trimElement = element.trim();
                parseSchemaElement(trimElement);
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ParseException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (isBooleanSchemaElement(elementTail))
            parseBooleanSchemaElement(elementId);
        else if (isStringSchemaElement(elementTail))
            parseStringSchemaElement(elementId);
    }

    private void validateSchemaElementId(char elementId) throws ParseException {
        if (!Character.isLetter(elementId)) {
            throw new ParseException("Bad character:" + elementId + "in Args format: " + schema, 0);
        }
    }

    private boolean isBooleanSchemaElement(String elementTail) {
        return elementTail.length() == 0;
    }

    private boolean isStringSchemaElement(String elementTail) {
        return "*".equals(elementTail);
    }

    private void parseBooleanSchemaElement(char elementId) {
        booleanArgs.put(elementId, false);
    }

    private void parseStringSchemaElement(char elementId) {
        stringArgs.put(elementId, "");
    }

    private boolean parseArguments() {
        for (currentArgument = 0; currentArgument < args.length; currentArgument++) {
            String arg = args[currentArgument];
            parseArgument(arg);
        }
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
        if (setArgument(argChar)) {
            argsFound.add(argChar);
        } else {
            unexpectArguments.add(argChar);
            valid = false;
        }
    }

    private boolean setArgument(char argChar) {
        boolean set = true;
        if (isBoolean(argChar))
            setBooleanArg(argChar, true);
        else if (isString(argChar))
            setStringArg(argChar);
        else
            set = false;
        return set;
    }

    private void setBooleanArg(char argChar, boolean value) {
        booleanArgs.put(argChar, value);
    }

    private void setStringArg(char argChar) {
        currentArgument++;
        try {
            stringArgs.put(argChar, args[currentArgument]);
        } catch (ArrayIndexOutOfBoundsException e) {
            valid = false;
            errorArgument = argChar;
            errorCode = ErrorCode.MISSING_STRING;
        }
    }

    private boolean isBoolean(char argChar) {
        return booleanArgs.containsKey(argChar);
    }

    private boolean isString(char argChar) {
        return stringArgs.containsKey(argChar);
    }

    public int cardinality() {
        return argsFound.size();
    }

    public String usage() {
        if (schema.length() > 0)
            return "-[" + schema + "]";
        else
            return "";
    }

    public String errorMessage() throws Exception {
        if (unexpectArguments.size() > 0)
            return unexpectArgumentsMessage();
        else
            switch (errorCode) {
                case MISSING_STRING:
                    return String.format("Could not find string paramenter for -%c.", errorArgument);
                case OK:
                    throw new Exception("TILT: should not get here.");
            }
        return "";

    }

    private String unexpectArgumentsMessage() {
        StringBuffer message = new StringBuffer("Argument(s) -");
        for (Character c : unexpectArguments) {
            message.append(c);
        }
        message.append("  unexpected.");
        return message.toString();
    }

    public boolean getBoolean(char arg) {
        return falseIfNull(booleanArgs.get(arg));
    }

    private boolean falseIfNull(Boolean b) {
        return b == null ? false : b;
    }

    public String getString(char arg) {
        return blankIfNull(stringArgs.get(arg));
    }

    public String blankIfNull(String s) {
        return s == null ? "" : s;
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    public boolean isValid() {
        return valid;
    }

}
