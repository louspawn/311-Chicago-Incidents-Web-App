package gr.di.uoa.m1542m1552.databasesystems.domain;

public enum Status {
    Completed("Completed"),
    Open("Open"),
    Open_Dup("Open_Dup"),
    Completed_Dup("Completed_Dup");

    private String text;

    Status(String text) {
      this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Status fromString(String text) {
        for (Status b : Status.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
  
}