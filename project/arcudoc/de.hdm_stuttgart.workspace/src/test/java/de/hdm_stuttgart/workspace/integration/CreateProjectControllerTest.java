package de.hdm_stuttgart.workspace.integration;

import de.hdm_stuttgart.workspace.data.IWorkspaceRepo;
import de.hdm_stuttgart.workspace.data.WorkspaceRepo;
import de.hdm_stuttgart.workspace.model.ProjectRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CreateProjectControllerTest {

    private CreateProjectController createProjectController;

    @BeforeEach
    void setUp() {
        IWorkspaceRepo mockRepo = Mockito.mock(WorkspaceRepo.class);
        createProjectController = new CreateProjectController(mockRepo);
    }

    @Test
    public void should_ReturnErrorString_When_MemberAlreadyAdded(){
        createProjectController.addProjectMember("testmail@mail.com","testlabel","role");
        assertEquals("Member testmail@mail.com already added",
                createProjectController.addProjectMember("testmail@mail.com","testlabel","role"));
    }

    @Test
    public void should_ReturnSuccessString_When_AddMember(){
        assertEquals("Member added",
                createProjectController.addProjectMember("testmail@mail.com","testlabel","role"));
    }

    @Test
    public void should_ReturnStringList_When_GettingProjectRoles(){
        String[] roles = {"owner","admin","editor","viewer"};
        assertArrayEquals(roles,createProjectController.getProjectRoles().toArray(new String[0]));
    }

    @Test
    public void should_Not_ReturnEnumList_When_GettingProjectRoles(){
        ProjectRole[] roles = ProjectRole.values();
        assertNotEquals(roles,createProjectController.getProjectRoles().toArray());
    }

}