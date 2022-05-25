package de.hdm_stuttgart.workspace.model;

public class InvitationRequest {

    private final String member_mail;
    private final int project_id;

    public InvitationRequest(String member_mail, int project_id) {
        this.member_mail = member_mail;
        this.project_id = project_id;
    }

    public String getMember_mail() {
        return member_mail;
    }

    public int getProject_id() {
        return project_id;
    }
}
