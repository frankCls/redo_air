package com.redoair.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(value = "dateConverter")
public class DateConverter  implements Converter{

	@Override
	public Date getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value == null || value.equals("")) {
	            return null;
	        }

	       
	         SimpleDateFormat format = new SimpleDateFormat(DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME);
	        
	        try {
				return format.parse(value.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 
		return value.toString();
	}

}
