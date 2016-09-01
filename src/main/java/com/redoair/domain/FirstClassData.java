package com.redoair.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("first_class")
public class FirstClassData extends AbstractTravelingClassData implements Serializable{

}
