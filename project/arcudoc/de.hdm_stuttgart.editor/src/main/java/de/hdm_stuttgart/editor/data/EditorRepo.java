package de.hdm_stuttgart.editor.data;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.model.HtmlResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorRepo implements IEditorRepo {

    private final GitLabClient gitLabClient = ServiceProvider.getGitLabClient();
    private HtmlResponse markdownString;
    ChangeListener listener;

    @Inject
    public EditorRepo(ChangeListener listener){
        this.listener = listener;
    }

    @Override
    public void fetchMarkDown(String textToRender){
        Call<HtmlResponse> call = gitLabClient.fetchMarkDown(textToRender);
        call.enqueue(new Callback<HtmlResponse>() {
            @Override
            public void onResponse(Call<HtmlResponse> call, Response<HtmlResponse> response) {
                if(response.isSuccessful()){
                    markdownString = response.body();
                    System.out.println(markdownString.getHtml());
                    listener.htmlReceived(markdownString);
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

    @Override
    public HtmlResponse getMarkdownString() {
        return markdownString;
    }

}
