package HomeDZ3.Task4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void getAllInterfacesOfParents(Class<?> cls, List<Class<?>> interfacesFound) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                if (!interfacesFound.contains(anInterface)) {
                    interfacesFound.add(anInterface);
                    getAllInterfacesOfParents(anInterface, interfacesFound);
                }
            }
            cls = cls.getSuperclass();
        }
    }
}




   
