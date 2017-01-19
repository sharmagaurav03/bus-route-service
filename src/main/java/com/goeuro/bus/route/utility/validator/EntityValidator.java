package com.goeuro.bus.route.utility.validator;

public interface EntityValidator<Entity, EntityValue> {
	public void validate(Entity entity, EntityValue entityValue);

}
