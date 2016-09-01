
package com.redoair.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("business_class")
public class BusinessClassData extends AbstractTravelingClassData {
	
}
