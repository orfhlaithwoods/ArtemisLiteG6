package artemisLite;

public enum eSpaceSystem {
	    NEUTRAL {
	    	@Override
	    	public String toString() {
	    		return "Neutral";
	    	}
	    }, 
	    
	    SPACE_LAUNCH_SYSTEM {
	    	@Override
	    	public String toString() {
	    		return "Space Launch System";
	    	}
	    }, 
	    
	    ORION_SPACECRAFT {
	    	@Override
	    	public String toString() {
	    		return "Orion Spacecraft";
	    	}
	    }, 
	    
	    LANDING_SYSTEMS {
	    	@Override
	    	public String toString() {
	    		return "Landing Systems";
	    	}
	    }, 
	    
	    ARTEMIS_BASE_CAMP {
	    	@Override
	    	public String toString() {
	    		return "Artemis Base Camp";
	    	}
	    };
}

