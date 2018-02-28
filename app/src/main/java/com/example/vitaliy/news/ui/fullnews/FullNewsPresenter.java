package com.example.vitaliy.news.ui.fullnews;

/**Full article info presenter
 *
 * @author Vitaliy
 * @version 1.0
 */
public class FullNewsPresenter implements FullNewsContract.IFullNewsPresenter {

    private FullNewsContract.IFullNewsView view;

    @Override
    public void attachView(FullNewsContract.IFullNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getUrl(String url) {
        view.showContent(url);
    }
}
