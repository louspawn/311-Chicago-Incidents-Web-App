package gr.di.uoa.m1542m1552.databasesystems.enumerations;

public enum TypeOfServiceRequest {
    ABANDONED_VEHICLE("ABANDONED_VEHICLE"),
    ALLEY_LIGHTS_OUT("ALLEY_LIGHTS_OUT"),
    GARBAGE_CARTS("GARBAGE_CARTS"),
    GRAFFITI_REMOVAL("GRAFFITI_REMOVAL"),
    POT_HOLES("POT_HOLES"),
    RODENT_BAITING("RODENT_BAITING"),
    SANITATION_CODE_COMPLAINTS("SANITATION_CODE_COMPLAINTS"),
    LIGHTS_ALL_OUT("LIGHTS_ALL_OUT"),
    LIGHT_ONE_OUT("LIGHT_ONE_OUT"),
    TREE_DEBRIS("TREE_DEBRIS"),
    TREE_TRIMS("TREE_TRIMS");

    private String text;

    TypeOfServiceRequest(String text) {
      this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TypeOfServiceRequest fromString(String text) {
        for (TypeOfServiceRequest b : TypeOfServiceRequest.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
  
}