package lancill.test.pojo;

import java.io.FileInputStream;
import java.util.Properties;

import lancill.annotation.ReadPropertiesAnnotation;


public class City {
	
	private Properties props=new Properties();
	private final String PROPERTIES_FILE_NAME="city.properties";
	public City(){
		try {
			props.load(new FileInputStream(PROPERTIES_FILE_NAME));
		} catch (Exception e) {
			props.setProperty("name","N/A");
			props.setProperty("country","N/A");
			e.printStackTrace();
		}
	}
	
	@ReadPropertiesAnnotation (fileName=PROPERTIES_FILE_NAME, propertyName="name")
	public String getName(){
		return props.getProperty("name");
	}
	
	@ReadPropertiesAnnotation (fileName=PROPERTIES_FILE_NAME, propertyName="country")
	public String getCountry(){
		return props.getProperty("country");
	}

}
