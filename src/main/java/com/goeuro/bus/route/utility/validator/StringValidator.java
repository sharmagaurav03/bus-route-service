package com.goeuro.bus.route.utility.validator;

public class StringValidator implements EntityValidator<String, String>{
	
	public void validate(String entity, String entityString) {
		if(entityString ==  null)
			throw new IllegalArgumentException(entity + " can not be null.");
		if (entityString.trim().length()<=0)
			throw new IllegalArgumentException(entity + " can not be empty.");
	}

}
