package pomis.app.realmandprefssample.models;

import io.realm.RealmObject;

/**
 * Created by romanismagilov on 18.10.16.
 */

public class NewsPost extends RealmObject {

    public String title;
    public String text;
    public String author;
    public int number;

    public NewsPost(String title, String text, String author, int number) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.number = number;
    }

    public NewsPost(){}
}
