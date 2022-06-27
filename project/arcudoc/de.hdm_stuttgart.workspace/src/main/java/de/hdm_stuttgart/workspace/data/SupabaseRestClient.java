package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.workspace.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * contains all api request methods needed in workspace module
 */
public interface SupabaseRestClient {

    // - - - - - projects - - - - -

    /**
     *
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param contentType content type application/json
     * @param returnRepresentation prefer header return=representation -> returns the added project
     * @param projectRequest json request body
     * @return a list of ProjectResponses
     */
    @POST("projects")
    Call<List<ProjectResponse>> createNewProject(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String returnRepresentation,
            @Body ProjectRequest projectRequest
            );

    // - - - - - project members - - - - -

    /**
     *
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param contentType content type application/json
     * @param returnRepresentation prefer header return=representation -> returns the added member //todo can be removed
     * @param memberRequest json request body
     * @return nothing (response is ignored)
     */
    @POST("project_members")
    Call<Void> addProjectMember(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String returnRepresentation,
            @Body MemberRequest memberRequest
            );

    /**
     * get all projects in which the authenticated user is a member
     * RLS ensures that only the projects the user is part of are returned
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param selectFilter specifies which columns to select
     * @return a list of MemberProjectResponses
     */
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
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param contentType content type application/json
     * @param prefer prefer header resolution=merge-duplicates
     * @param invitationRequests json request body
     * @param conflictStrategy name of the columns that are unique
     * @return nothing
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
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param contentType content type application/json
     * @param prefer prefer header resolution=merge-duplicates
     * @param invitationRequests json request body
     * @return nothing
     */
    @POST("project_invitations")
    Call<Void> createNewProjectInvitationReturnMinimal(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String prefer, //return=minimal
            @Body List<InvitationRequest> invitationRequests
    );

    /**
     * get a list of projects the user is invited to
     * filter handled by supabase RLS
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param selectFilter specifies which columns to select
     * @return a list of ProjectInvitations
     */
    @GET("project_invitations")
    Call<List<InvitationResponse>> getProjectInvitations(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter
    );

    /**
     *
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param projectIdFilter the filter for specifying the project id
     * @param selectFilter specifies which columns to select
     * @return a list of ProjectMembers
     */
    @GET("project_invitations")
    Call<List<ProjectMember>> getSingleProjectInvitationByProjectId(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("project_id") String projectIdFilter,
            @Query("select") String selectFilter
    );

    /**
     * delete a project invitation (RLS ensures that only invitations for the authenticated user can be deleted)
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param projectIdFilter the filter for specifying the project id
     * @return nothing
     */
    @DELETE("project_invitations")
    Call<Void> deleteProjectInvitation(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("project_id") String projectIdFilter
    );

    // - - - - user profile - - - -

    /**
     * get the users profile
     * @param apikey supabase key
     * @param bearerToken the users access token
     * @param selectFilter specifies which columns to select
     */
    @GET("profiles")
    Call<List<UserProfile>> fetchUserProfile(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter
    );


}
