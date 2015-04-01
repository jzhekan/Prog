import java.lang.reflect.Method;

public class Tester {
	public static boolean test(Class<?>... ls) {
		try {
			for (Class<?> cls : ls) {
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(Test.class)) {
						Boolean b = (Boolean) method.invoke(null, new Object[] {});
						if ( ! b)
							return false;
					}
                                        
                                        if (method.isAnnotationPresent(TestAnnotation.class)) {
                                            TestAnnotation test = method.getAnnotation(TestAnnotation.class);
                                            
                                                Object[] arguments = new Object[] { test.a(),test.b() }; 
						int rezult = (int) method.invoke(cls.newInstance(), arguments);
						System.out.println("sum:"+rezult);
                                                
					}
                                        
				}
			}
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

        
        public static void saveStringFields(Object ... arr) throws Exception{
            
            for(Object obj : arr){
                Class<?> cls = obj.getClass();
                System.out.println("Class name: " + cls.getName());
                if(cls.isAnnotationPresent(AnnotationSave.class)){
                    AnnotationSave as = cls.getAnnotation(AnnotationSave.class);
                    System.out.println("Path:" + as.passSave()+", method:" + as.nameMethod());
                    
                    String pathSave   = as.passSave();
                    String nameMethod = as.nameMethod();
                    
                    Method[] methods = cls.getDeclaredMethods();
                    for (Method method : methods) {
                        method.setAccessible(true);
                        System.out.println(method.getName());
                        if(nameMethod.equals(method.getName())){
                           Object[] arguments = new Object[] { pathSave };
                           method.invoke(obj, arguments); 
                        }
                    }
                    
                }
                
            }
        
        }
}
