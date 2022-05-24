package de.hdm_stuttgart.workspace.model;

public class InvitationRequest {

    private final String invitationMail;
    private final int project;

    public InvitationRequest(String invitationMail, int project) {
        this.invitationMail = invitationMail;
        this.project = project;
    }

    public String getInvitationMail() {
        return invitationMail;
    }

    public int getProject() {
        return project;
    }
}
