package com.br.smartzoo.model.enums;

public enum AnimalEnum {
    	
    	Leao("Leao",300.0,3,150),
    	Onca("Onca",200.0,2,100),
    	Leopardo("Leopardo",400.0,4,200),
    	Tigre("Tigre",500.0,5,250),
    	Javali("Javali",200.0,2,100),
        Arara("Arara",400.0,4,200),
        Cobra("Cobra",100.0,1,50),
        Tartaruga("Tartaruga",100.0,1,50),
        Ganso("Ganso",200.0,2,100),
        Macaco("Macaco",600.0,6,300),
		Girafa("Girafa",700.0,7,350),
		Elefante("Elefante",700.0,7,350),
		AranhaCaranguejeira("Aranha Caranguejeira",300.0,3,150),
	    Papagaio("Papagaio",200.0,2,100),
		Tucano("Tucano",200.0,2,100),
		Urso("Urso",400.0,4,200);
    	
        AnimalEnum(String name,Double price, int popularity, int reputationToUnlock){}

    	private String name;
		private Double price;
		private int popularity;
		private int reputationToUnlock;

		public Double getPrice() {
		return price;
		}

		public int getPopularity() {
		return popularity;
		}

    	public String getName(){
    		return this.name;
    	}


		public int getReputationToUnlock() {
		return reputationToUnlock;
		}

}
