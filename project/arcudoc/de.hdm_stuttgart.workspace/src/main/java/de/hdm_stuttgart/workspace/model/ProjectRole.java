package de.hdm_stuttgart.workspace.model;

public enum ProjectRole {

    OWNER("owner"),ADMIN("admin"),EDITOR("editor"),VIEWER("viewer");

    private final String supabaseName;

    ProjectRole(String supabaseName) {
        this.supabaseName = supabaseName;
    }

    public String getSupabaseName() {
        return supabaseName;
    }
}
