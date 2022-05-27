package de.hdm_stuttgart.workspace.service;

import de.hdm_stuttgart.workspace.model.Project;

public interface IInvitationResponse {

    int getProjectId();

    String getMemberMail();

    Project getProject();

    String getJobLabel();

    String getProjectRole();

    String getProjectTitle();
}
