package com.br.smartzoo.model.enums;

public enum AnimalEnum {
    	
    	Leao("Leao"),
    	Onca("Onca"),
    	Leopardo("Leopardo"),
    	Tigre("Tigre"),
    	Javali("Javali"),
    	Guepardo("Guepardo"),
        Arara("Arara"),
        Cobra("Cobra"),
        Tartaruga("Tartaruga"),
        Cisne("Cisne"),
        Ganso("Ganso"),
        Macaco("Macaco");
    	
        AnimalEnum(String animal){}

    	private String animal;

    	public String getValue(){
    		return this.animal;
    	}


}
