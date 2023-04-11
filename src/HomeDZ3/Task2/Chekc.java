package HomeDZ3.Task2;

import HomeDZ3.Task1.IsLike;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class Chekc {
    public static void check(Class<?> cls) {
        if (!cls.isAnnotationPresent(IsLike.class)) {
            System.out.println("Annotation not found");
            return;
        }
        IsLike isLike = cls.getAnnotation(IsLike.class);
        System.out.println("IsLike: " + isLike.value());
    }

}

