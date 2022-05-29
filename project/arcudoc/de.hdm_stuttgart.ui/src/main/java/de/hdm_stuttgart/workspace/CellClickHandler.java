package de.hdm_stuttgart.workspace;

/**
 * should be implemented by "parent" scenes which are responsible for handling actions of their "child" nodes (e.g. InvitationCells)
 */
public interface CellClickHandler {

    void onAcceptInvitationClicked(int projectId);
}
