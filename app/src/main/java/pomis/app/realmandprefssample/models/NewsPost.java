package pomis.app.realmandprefssample.models;

import io.realm.RealmObject;

/**
 * Created by romanismagilov on 18.10.16.
 */

public class NewsPost extends RealmObject {

    String title;
    String text;
    String author;
    int number;

    public NewsPost(String title, String text, String author, int number) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.number = number;
    }

    public NewsPost(){}
}
