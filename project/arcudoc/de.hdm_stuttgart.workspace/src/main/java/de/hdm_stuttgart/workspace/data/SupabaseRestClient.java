package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.workspace.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface SupabaseRestClient {

    // - - - - - projects - - - - -

    @POST("projects")
    Call<List<ProjectResponse>> createNewProject(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String returnRepresentation,
            @Body ProjectRequest projectRequest
            );

    // - - - - - project members - - - - -

    @POST("project_members")
    Call<Void> addProjectMember(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String returnRepresentation,
            @Body MemberRequest memberRequest
            );

    @GET("project_members") //filtering handled by RLS of supabase
    Call<List<MemberProjectResponse>> getMemberProjects(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter
    );

    // - - - - - project invitations - - - - -

    /**
     * post a new project invitation, duplicate invitation rows will be updated
     * only works if RLS 'select for invited_by' is enabled
     *
     * combination of  createNewProjectInvitationMergeDuplicates and createNewProjectInvitationReturnMinimal would be optimal
     * -> would require two Prefer fields (resolution=merge-duplicates,return=minimal) which is currently not possible/ not working
     * @param apikey
     * @param bearerToken
     * @param contentType
     * @param prefer
     * @param invitationRequests
     * @param conflictStrategy
     * @return
     */
    @POST("project_invitations")
    Call<Void> createNewProjectInvitationMergeDuplicates(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType, //application/json
            @Header("Prefer") String prefer, //resolution=merge-duplicates
            @Body List<InvitationRequest> invitationRequests,
            @Query("on_conflict") String conflictStrategy //member_mail,project_id
            );

    /**
     * post a new project invitation, if rows are duplicate an error occurs
     * works without 'select for invited_by' RLS
     * @param apikey
     * @param bearerToken
     * @param contentType
     * @param prefer
     * @param invitationRequests
     * @return
     */
    @POST("project_invitations")
    Call<Void> createNewProjectInvitationReturnMinimal(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String prefer, //return=minimal
            @Body List<InvitationRequest> invitationRequests
    );

    @GET("project_invitations")
    Call<List<InvitationResponse>> getProjectInvitations(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter
    );

    @GET("project_invitations")
    Call<List<ProjectMember>> getSingleProjectInvitationByProjectId(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("project_id") String invitationMailFilter,
            @Query("select") String selectFilter
    );


    /**
     * RLS: only the invitations belonging to the authenticated user can be deleted (JWT)
     */
    @DELETE("project_invitations")
    Call<Void> deleteProjectInvitation(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("project_id") String projectIdFilter
    );


}
