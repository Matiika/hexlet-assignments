package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class Validator {
    public static ArrayList<String> validate(Address address) {
        ArrayList<String> notValidField = new ArrayList<>();

        Field[] fields = address.getClass().getDeclaredFields();

        for (var field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        notValidField.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return notValidField;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidField = new HashMap<>();

        Field[] fields = address.getClass().getDeclaredFields();


        for (var field : fields) {

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        notValidField.put(field.getName(), List.of("can not be null"));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                int minLength = field.getAnnotation(MinLength.class).minLength();
                try {
                    field.setAccessible(true);
                    if (field.get(address).toString().length() < minLength) {
                        if (notValidField.containsKey(field.getName())) {
                            notValidField.get(field.getName()).add("length less than 4");
                        } else {
                            notValidField.put(field.getName(), List.of("length less than " + minLength));
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return notValidField;
    }


}
// END
