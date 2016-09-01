
package com.redoair.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("economy_class")
public class EconomyClassData extends AbstractTravelingClassData{
	
}
