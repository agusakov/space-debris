package model;

public enum Image {
    
    // environments and characteristics
    EARTH(new String[] {"Earth/Earth1.png", "Earth/Earth1.png","Earth/Earth1.png","Earth/Earth1.png","Earth/Earth1.png","Earth/Earth1.png","Earth/Earth1.png","Earth/Earth1.png",
                        "Earth/Earth2.png", "Earth/Earth2.png", "Earth/Earth2.png", "Earth/Earth2.png", "Earth/Earth2.png", "Earth/Earth2.png", "Earth/Earth2.png", "Earth/Earth2.png"}),

    SATELLITE(new String[] {"Satellite/satellite.png"}),

    TUNGSTENDUST(new String[] {"dust.png"});

    private String[] displayText;
    private String[] fileNames;
	
	private Image(String[] fileNames/*, String[] displayText*/){
        this.fileNames = fileNames;
        //this.displayText = displayText;
	}
	
	public String[] getDisplayText() {
		return displayText;
    }
    
    public String[] getFileNames() {
        return fileNames;
    }

}