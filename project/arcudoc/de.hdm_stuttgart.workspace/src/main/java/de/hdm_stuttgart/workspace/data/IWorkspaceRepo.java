package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.workspace.model.InvitationResponse;
import de.hdm_stuttgart.workspace.model.MemberProjectResponse;
import de.hdm_stuttgart.workspace.model.ProjectMember;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;

import java.util.List;

public interface IWorkspaceRepo {
    void createProject(String projectTitle, List<ProjectMember> invitationMembers);

    void inviteMembers(List<ProjectMember> members, int projectId);

    void fetchProjectInvitations();

    void respondProjectInvitation(int projectId, boolean isAccepted);

    void deleteProjectInvitation(int projectId);

    void addProjectMember(int projectId, ProjectMember projectMember);

    void fetchMemberProjects();

    ListProperty<InvitationResponse> getProjectInvitationsProperty();

    ListProperty<MemberProjectResponse> getMemberProjectsProperty();

    ObjectProperty<NetworkStatus> getNetworkStatusObjectProperty();
}
