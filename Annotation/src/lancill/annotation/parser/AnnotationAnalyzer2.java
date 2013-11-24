package lancill.annotation.parser;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Properties;

import lancill.annotation.ReadPropertiesAnnotation;

public class AnnotationAnalyzer2 {

    
	public void analyze() {

		try {

			Class annotationClass = AnnotationAnalyzer2.class.getClassLoader()
					.loadClass(("lancill.test.pojo.City"));
			
			for (Method method : annotationClass.getMethods()) {

				// checks if MethodInfo annotation is present for the method

				if (method
						.isAnnotationPresent(lancill.annotation.ReadPropertiesAnnotation.class)) {

					try {

						// iterates all the annotations available in the method

						for (Annotation annotation : method.getDeclaredAnnotations()) {

							System.out.println("\nAnnotation in Method " + method
									+ " : " + annotation);

						}

						
						ReadPropertiesAnnotation metodoAnnotation = method
								.getAnnotation(ReadPropertiesAnnotation.class);

						if (!metodoAnnotation.fileName().isEmpty()) {

							System.out.println("Dettaglio Annotation @ReadPropertiesAnnotation:");
							System.out.println("\t- Metodo: "+ method);
							
							System.out.println("\t- Nome file di properties: "+ metodoAnnotation.fileName());
							System.out.println("\t- Property associata al metodo: "+  metodoAnnotation.propertyName());
							System.out.println("\t- Valore restituito dal metodo: "+ getPropertyValue(metodoAnnotation.fileName(),metodoAnnotation.propertyName()));

						}

					} catch (Throwable ex) {

						ex.printStackTrace();

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private String getPropertyValue(String fileName, String propertyName) {
		Properties props=new Properties();
		
		try {
			props.load(new FileInputStream(fileName));
			return props.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Impossibile calcolare il valore";
	}
}
