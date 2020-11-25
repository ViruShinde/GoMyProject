package com.globeop.riskfeed.enums;

public enum Frequency {
	
	D("DAILY"),
	DE("DAILY_ESTIMATE"),
	W("WEEKLY"),
	WE("WEEKLY_ESTIMATE"),
	M("MONTHLY"),
	ME("MONTHLY_ESTIMATE");
		
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

