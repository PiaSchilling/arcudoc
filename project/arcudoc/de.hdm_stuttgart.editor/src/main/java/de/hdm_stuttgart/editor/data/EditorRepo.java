package de.hdm_stuttgart.editor.data;

import de.hdm_stuttgart.editor.model.HtmlResponse;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditorRepo implements IEditorRepo {

    private static final Logger log = LogManager.getLogger(EditorRepo.class);

    private final GitLabClient gitLabClient = ServiceProvider.getGitLabClient(); //todo inject
    private final StringProperty htmlStringProperty = new SimpleStringProperty("empty");

    /**
     * converts markdown string into html string by calling gitlab endpoint which provides the conversion
     * @param textToRender the markdown string which should be rendered
     */
    @Override
    public void fetchMarkDown(String textToRender){
        Call<HtmlResponse> call = gitLabClient.fetchMarkDown(textToRender);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<HtmlResponse> call, @NotNull Response<HtmlResponse> response) {
                if (response.isSuccessful()) {
                    HtmlResponse htmlResponse = response.body();
                    if (htmlResponse != null) {
                        htmlStringProperty.setValue(htmlResponse.getHtml());
                    } else {
                        log.error("HtmlResponse is null");
                    }

                } else {
                    log.error("Fetching markdown not successful");
                }
            }

            @Override
            public void onFailure(@NotNull Call<HtmlResponse> call, @NotNull Throwable throwable) {
                log.error("Fetching markdown failed - " + throwable.getMessage());
            }
        });
    }



    public StringProperty getHtmlStringProperty() {
        return htmlStringProperty;
    }
}
