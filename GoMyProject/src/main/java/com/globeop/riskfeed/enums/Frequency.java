package com.globeop.riskfeed.enums;

public enum Frequency {
	
	D("Daily"),
	W("Weekly"),
	M("Monthly");
	
	
	private final String displayValue;
    
    private Frequency(String displayValue) {
        this.displayValue = displayValue;
    }
     
    public String getDisplayValue() {
        return displayValue;
    }
    
    public static Frequency getEnum(String value) {
        for(Frequency v : values())
            if(v.getDisplayValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
    
    
}

