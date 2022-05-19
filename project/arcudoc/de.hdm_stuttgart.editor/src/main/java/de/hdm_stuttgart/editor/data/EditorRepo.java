package de.hdm_stuttgart.editor.data;

import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.service.GitLabClient;
import de.hdm_stuttgart.editor.integration.Editor;
import de.hdm_stuttgart.data.model.HtmlResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorRepo {

    private GitLabClient gitLabClient = ServiceProvider.getGitLabClient();
    private HtmlResponse markdownString;

    public void fetchMarkDown(String textToRender){
        Call<de.hdm_stuttgart.data.model.HtmlResponse> call = gitLabClient.fetchMarkDown(textToRender);
        call.enqueue(new Callback<HtmlResponse>() {
            @Override
            public void onResponse(Call<HtmlResponse> call, Response<HtmlResponse> response) {
                if(response.isSuccessful()){
                    markdownString = response.body();
                    System.out.println(markdownString.getHtml());
                }else{
                    System.out.println("Error");
                }
            }

            @Override
            public void onFailure(Call<HtmlResponse> call, Throwable throwable) {
                System.out.println("Fail");
                System.out.println(throwable.getMessage());
            }
        });
    }

    public HtmlResponse getMarkdownString() {
        return markdownString;
    }

    public static void main(String[] args) {
        EditorRepo e = new EditorRepo();

        e.fetchMarkDown("# this should be a headline");
    }
}
