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

    @POST("project-invitations")
    Call<Void> createNewProjectInvitation(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Body List<InvitationRequest> invitationRequests
            );

    @GET("project-invitations")
    Call<List<InvitationResponse>> getProjectInvitations(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("invitationMail") String invitationMailFilter,
            @Query("select") String selectFilter
    );
}
